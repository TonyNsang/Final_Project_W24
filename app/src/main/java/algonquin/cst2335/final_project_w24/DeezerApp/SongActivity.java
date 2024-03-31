package algonquin.cst2335.final_project_w24.DeezerApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import algonquin.cst2335.final_project_w24.R;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SongActivity extends AppCompatActivity {

    private EditText artistNameEditText;
    private Button searchButton;
    private Button favoriteButton1;
    private RecyclerView songsRecyclerView;
    private SongsAdapter songsAdapter;
    private  ConstraintLayout layout;

    private final List<TracklistResponse.Track> songList = new ArrayList<>();
    private OkHttpClient client = new OkHttpClient();
    private Gson gson = new Gson();
    private ExecutorService executorService = Executors.newSingleThreadExecutor();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        artistNameEditText = findViewById(R.id.artistNameEditText);
        searchButton = findViewById(R.id.searchButton);
        songsRecyclerView = findViewById(R.id.songsRecyclerView);
        favoriteButton1 = findViewById(R.id.favorite_btn1);

        // Setup RecyclerView
        songsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        SongsAdapter.OnItemClickListener listener = song -> {
            Intent intent = new Intent(SongActivity.this, SongDetailsActivity.class);
            intent.putExtra("songId", song.getId()); // Replace "getId()" with actual method to retrieve ID
            intent.putExtra("songTitle", song.getTitle());
            intent.putExtra("albumTitle", song.getAlbum().getTitle());
            intent.putExtra("duration", String.valueOf(song.getDuration())); // Assuming `getDuration` returns a value you can convert to String
            intent.putExtra("coverImage", song.getAlbum().getCover()); // Ensure you have such a method or adjust according to your data model

            startActivity(intent);
        };
        songsAdapter = new SongsAdapter(LayoutInflater.from(this), songList, listener);
        songsRecyclerView.setAdapter(songsAdapter);


        searchButton.setOnClickListener(v -> {
            String artistName = artistNameEditText.getText().toString().trim();
            if (!artistName.isEmpty()) {
                // Clear existing data
                songList.clear();
                songsAdapter.notifyDataSetChanged();

                // Perform the search and update UI
                searchForArtistAndFetchTopTracks(artistName);
            }

        });

        // Set the OnClickListener for the favorite button
        favoriteButton1.setOnClickListener(v -> {

            Intent intent = new Intent(SongActivity.this, FavoritesActivity.class);
            startActivity(intent);
        });

    }

    private void searchForArtistAndFetchTopTracks(String artistName) {

        // Step 1: Search for the artist by name
        String searchUrl = "https://api.deezer.com/search/artist?q=" + artistName.trim().replace(" ", "+");

        // Create the Request object with the search URL
        Request request = new Request.Builder()
                .url(searchUrl)
                .build();
        // Placeholder for network operation
        executorService.execute(() -> {
            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                ArtistSearchResponse searchResponse = gson.fromJson(response.body().string(), ArtistSearchResponse.class);
                if (searchResponse.getData().size() > 0) {

                    ArtistDetails artist = searchResponse.getData().get(0);
                    fetchTopTracksForArtist(artist.getId());

                } else {
                    // Handle "no artist found" case
                    runOnUiThread(() -> {
                        TextView tvEmptyMessage = findViewById(R.id.tvEmptyMessage);
                        tvEmptyMessage.setText("No artist found. Please try a different search.");
                        tvEmptyMessage.setVisibility(View.VISIBLE);
                        // Make sure the RecyclerView is hidden or cleared as it might contain old data
                        songsRecyclerView.setVisibility(View.GONE);
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
                // Handle errors (e.g., network issue)
            }
        });
    }
    private void fetchTopTracksForArtist(long artistId) {
        String tracksUrl = "https://api.deezer.com/artist/" + artistId + "/top?limit=50";

        executorService.execute(() -> {
            Request request = new Request.Builder().url(tracksUrl).build();
            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                TracklistResponse tracklistResponse = gson.fromJson(response.body().string(), TracklistResponse.class);
                List<TracklistResponse.Track> fetchedTracks = tracklistResponse.getData(); // Correctly refer to fetched tracks here

                runOnUiThread(() -> {
                    songList.clear(); // Clear existing songs in the list
                    songList.addAll(fetchedTracks); // Add all fetched tracks to the list
                    songsAdapter.notifyDataSetChanged(); // Notify the adapter that the data set has changed
                });
            } catch (IOException e) {
                e.printStackTrace();
                // Handle errors
            }
        });
    }
}