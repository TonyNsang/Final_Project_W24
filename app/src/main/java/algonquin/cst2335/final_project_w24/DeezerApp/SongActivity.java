package algonquin.cst2335.final_project_w24.DeezerApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);


        artistNameEditText = findViewById(R.id.artistNameEditText);
        searchButton = findViewById(R.id.searchButton);
        songsRecyclerView = findViewById(R.id.songsRecyclerView); //Initialize RecyclerView


        queue = Volley.newRequestQueue(this);  //i initialize Volley RequestQueue

        searchButton.setOnClickListener(v-> {
                String artistName = artistNameEditText.getText().toString();
                searchForArtist(artistName);
        });

        adapter = new ArtistAdapter(new ArrayList<>()); //Initialize Adapter
        songsRecyclerView.setAdapter(adapter); //Set the adapter to RecyclerView
        songsRecyclerView.setLayoutManager(new LinearLayoutManager(this));// Set LinearLayoutManager to lays out all item views in a single column or a single row depending on its orientation



    }
    private void searchForArtist(String artistName){
        String url = "https://api.deezer.com/search/artist/?q=" + artistName;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response ->{
            try {
                JSONArray artistsArray = response.getJSONArray("data");
                List<Artist> artistList  = new ArrayList<>();
                for (int i = 0; i < artistsArray.length(); i++) {
                    JSONObject artistJson  = artistsArray.getJSONObject(i);

                    Artist artist = new Artist(
                            artistJson.getLong("id"),
                            artistJson.getString("name"),
                            artistJson.getString("link"),
                            artistJson.getString("picture"),
                            artistJson.getString("picture_small"),
                            artistJson.getString("picture_medium"),
                            artistJson.getString("picture_big"),
                            artistJson.getString("picture_xl"),
                            artistJson.getInt("nbAlbum"),
                            artistJson.getInt("nbFan"),
                            artistJson.getBoolean("radio"),
                            artistJson.getString("tracklist"),
                            artistJson.getString("type")
                    );
                    artistList.add(artist);
                }
                runOnUiThread(() -> adapter.updateData(artistList)); //Update the adapter with the new list on the UI thread

            }catch (JSONException e) {
                e.printStackTrace();
            }
        },
        error -> {
            error.printStackTrace();
        });

    queue.add(jsonObjectRequest);

    }

}

