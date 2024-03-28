package algonquin.cst2335.final_project_w24.DeezerApp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import algonquin.cst2335.final_project_w24.R;

public class SongDetailsActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(algonquin.cst2335.final_project_w24.R.layout.activity_song_details);

        ImageView albumCoverImageView = findViewById(R.id.albumCoverImageView);
        TextView songTitleTextView = findViewById(R.id.songTitleTextView);
        TextView albumNameTextView = findViewById(R.id.albumNameTextView);
        TextView durationTextView = findViewById(R.id.durationTextView);
        Button saveSongButton = findViewById(R.id.saveSongButton);

        // Retrieve and display the song details from the intent
        Intent intent = getIntent();
        songTitleTextView.setText(intent.getStringExtra("title"));
        albumNameTextView.setText(intent.getStringExtra("albumName"));
        durationTextView.setText(intent.getStringExtra("duration"));

        String albumCoverUrl = intent.getStringExtra("albumCover");
        Glide.with(this).load(albumCoverUrl).into(albumCoverImageView);

        // Handle save button click
        saveSongButton.setOnClickListener(view -> {

        });


    }
}
