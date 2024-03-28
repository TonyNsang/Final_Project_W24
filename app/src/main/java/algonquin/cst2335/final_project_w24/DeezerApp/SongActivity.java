package algonquin.cst2335.final_project_w24.DeezerApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import algonquin.cst2335.final_project_w24.R;

public class SongActivity extends AppCompatActivity {

    EditText artistNameEditText;
    Button searchButton;
    RecyclerView songsRecyclerView;
    String url;
    RequestQueue queue;
    private ArtistAdapter adapter;
    ProgressBar progressBar;
    TextView tvEmptyMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);


        artistNameEditText = findViewById(R.id.artistNameEditText);
        searchButton = findViewById(R.id.searchButton);
        songsRecyclerView = findViewById(R.id.songsRecyclerView); //Initialize RecyclerView
        progressBar = findViewById(R.id.progressBar);
        tvEmptyMessage = findViewById(R.id.tvEmptyMessage);

        queue = Volley.newRequestQueue(this);  //i initialize Volley RequestQueue

        // Initialize the adapter with an empty list and a click listener
        adapter = new ArtistAdapter(new ArrayList<>(), artist -> {
            // Handle artist click
            Toast.makeText(SongActivity.this, "Clicked on " + artist.getName(), Toast.LENGTH_SHORT).show();
        });


        songsRecyclerView.setAdapter(adapter); //Set the adapter to RecyclerView
        songsRecyclerView.setLayoutManager(new LinearLayoutManager(this));// Set LinearLayoutManager to lays out all item views in a single column or a single row depending on its orientation

        searchButton.setOnClickListener(v-> {
            String artistName = artistNameEditText.getText().toString();
            searchForArtist(artistName);
        });

    }
    private void searchForArtist(String artistName){

        progressBar.setVisibility(View.VISIBLE); // Show ProgressBar
        songsRecyclerView.setVisibility(View.GONE); // Hide RecyclerView
        tvEmptyMessage.setVisibility(View.GONE); // Hide TextView (the empty/error message)

        String artistNameQuery = artistName.replace(" ", "%20");
        String url = "https://api.deezer.com/search/artist/?q=" + artistNameQuery;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response ->{
            // Log.d("SongActivity", "Response: " + response.toString()); i used this print statement to test fetching of data
            try {
                JSONArray artistsArray = response.getJSONArray("data");
                List<Artist> artistList  = new ArrayList<>();
                for (int i = 0; i < artistsArray.length(); i++) {
                    JSONObject artistJson = artistsArray.getJSONObject(i);

                    Artist artist = new Artist(
                            artistJson.getLong("id"),
                            artistJson.getString("name"),
                            artistJson.getString("link"),
                            artistJson.getString("picture"),
                            artistJson.getString("picture_small"),
                            artistJson.getString("picture_medium"),
                            artistJson.getString("picture_big"),
                            artistJson.getString("picture_xl"),
                            artistJson.getInt("nb_album"),
                            artistJson.getInt("nb_fan"),
                            artistJson.getBoolean("radio"),
                            artistJson.getString("tracklist"),
                            artistJson.getString("type")
                    );
                    artistList.add(artist);
                }
                    runOnUiThread(() -> {
                        progressBar.setVisibility(View.GONE); // Show empty message
                        if (!artistList.isEmpty()) {
                            tvEmptyMessage.setVisibility(View.GONE); // Ensure message is hidden
                            songsRecyclerView.setVisibility(View.VISIBLE); // Show the RecyclerView
                            adapter.updateData(artistList);
                        } else {
                            tvEmptyMessage.setVisibility(View.VISIBLE); // Show empty message
                            songsRecyclerView.setVisibility(View.GONE); // Keep RecyclerView hidden
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                    runOnUiThread(() -> {
                        progressBar.setVisibility(View.GONE);
                        tvEmptyMessage.setText("Failed to parse data.");
                        tvEmptyMessage.setVisibility(View.VISIBLE);
                        songsRecyclerView.setVisibility(View.GONE);
                    });
                }
            },
            error -> runOnUiThread(() -> {
                Log.e("SongActivity", "Error: " + error.toString());
                progressBar.setVisibility(View.GONE);
                songsRecyclerView.setVisibility(View.GONE);
                tvEmptyMessage.setText("Failed to load data. Please try again.");
                tvEmptyMessage.setVisibility(View.VISIBLE);
            })
    );

            queue.add(jsonObjectRequest);
        }


}
