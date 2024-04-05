package algonquin.cst2335.final_project_w24.DeezerApp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.reflect.TypeToken;


import algonquin.cst2335.final_project_w24.R;

// FavoritesActivity.java
public class FavoritesActivity extends AppCompatActivity {

    private List<TrackFavoriteDetails> favoriteList = new ArrayList<>();
    private RecyclerView favoritesRecyclerView;
    private FavoritesAdapter favoritesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_favorite);

        favoritesRecyclerView = findViewById(R.id.favoritesRecyclerView);
        favoritesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        favoritesAdapter = new FavoritesAdapter(favoriteList, this::deleteFavorite);
        favoritesRecyclerView.setAdapter(favoritesAdapter);

        favoritesAdapter = new FavoritesAdapter(favoriteList, this::deleteFavorite, trackFavoriteDetails -> {
            Intent intent = new Intent(FavoritesActivity.this, SongDetailsActivity.class);
            // Pass the details of the selected favorite song to SongDetailsActivity
            intent.putExtra("songTitle", trackFavoriteDetails.getSongTitle());
            intent.putExtra("albumTitle", trackFavoriteDetails.getAlbumName());
            intent.putExtra("duration", trackFavoriteDetails.getDuration());
            intent.putExtra("coverImage", trackFavoriteDetails.getAlbumCoverUrl());
            startActivity(intent);

        });
        favoritesRecyclerView.setAdapter(favoritesAdapter);
        loadFavorites();
    }

    private void deleteFavorite(int position) {
        if (position >= 0 && position < favoriteList.size()) {
            favoriteList.remove(position);
            favoritesAdapter.notifyItemRemoved(position);

            // Save the updated list of favorites to SharedPreferences
            saveFavorites();
        }
    }

    private void saveFavorites() {
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String favoritesJson = gson.toJson(favoriteList);
        editor.putString("favorites", favoritesJson);
        editor.apply();
    }

    private void loadFavorites() {
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String favoritesJson = sharedPreferences.getString("favorites", null);
        if (favoritesJson != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<TrackFavoriteDetails>>() {}.getType();
            favoriteList = gson.fromJson(favoritesJson, type);
            favoritesAdapter.setFavoritesList(favoriteList);
            favoritesAdapter.notifyDataSetChanged();
        }
    }
}
