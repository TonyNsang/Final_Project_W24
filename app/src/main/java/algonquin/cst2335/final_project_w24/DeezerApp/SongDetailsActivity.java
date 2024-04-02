package algonquin.cst2335.final_project_w24.DeezerApp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import algonquin.cst2335.final_project_w24.R;

public class SongDetailsActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_details);

        ImageView albumCoverImageView = findViewById(R.id.albumCoverImageView);
        TextView songTitleTextView = findViewById(R.id.songTitleTextView);
        TextView albumNameTextView = findViewById(R.id.albumNameTextView);
        TextView durationTextView = findViewById(R.id.durationTextView);
        Button saveSongButton = findViewById(R.id.saveSongButton);


        Button favoriteBtn = findViewById(R.id.favorite_btn);


        // Retrieve data from intent
        Intent intent = getIntent();
        String songTitle = intent.getStringExtra("songTitle");
        String albumName = intent.getStringExtra("albumTitle");
        String duration = intent.getStringExtra("duration");
        String albumCoverUrl = intent.getStringExtra("coverImage");


        // Update UI with data
        songTitleTextView.setText(songTitle);
        albumNameTextView.setText(albumName);
        durationTextView.setText(duration);




        // Use Glide to load the album cover image
//        if (albumCoverUrl != null && !albumCoverUrl.isEmpty()) {
//            Glide.with(this).load(albumCoverUrl).into(albumCoverImageView);
//        }
        Glide.with(this)
                .load(albumCoverUrl)
                .placeholder(R.drawable.deezersong)
                .error(R.drawable.error_image)
                .into(albumCoverImageView);


        saveSongButton.setOnClickListener(view -> {
            Gson gson = new Gson();
            TrackFavoriteDetails trackDetails = new TrackFavoriteDetails(songTitle, albumName, duration, albumCoverUrl);
            String trackJson = gson.toJson(trackDetails);

            SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
            String favoritesJson = sharedPreferences.getString("favorites", null);
            Type type = new TypeToken<ArrayList<TrackFavoriteDetails>>() {}.getType();
            List<TrackFavoriteDetails> favoritesList = gson.fromJson(favoritesJson, type);

            // If there are no favorites yet, initialize the list
            if (favoritesList == null) {
                favoritesList = new ArrayList<>();
            }
            // Add the new favorite and save the updated list
            favoritesList.add(trackDetails);
            String updatedFavoritesJson = gson.toJson(favoritesList);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("favorites", updatedFavoritesJson);
            editor.apply();

            Toast.makeText(SongDetailsActivity.this, "Track saved", Toast.LENGTH_SHORT).show();

        });

        //click listener for favorite_btn
        favoriteBtn.setOnClickListener(view -> {
            Intent favoritesIntent = new Intent(SongDetailsActivity.this, FavoritesActivity.class);
            startActivity(favoritesIntent);
        });


//


    }
}
