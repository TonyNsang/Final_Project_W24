package algonquin.cst2335.final_project_w24.SunApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

import algonquin.cst2335.final_project_w24.R;
import algonquin.cst2335.final_project_w24.databinding.ActivityFavoriteLocationsBinding;

public class FavoriteLocationsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FavoritesAdapter adapter;
    private FavoriteLocationDatabase database;
    private ActivityFavoriteLocationsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFavoriteLocationsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new FavoritesAdapter();
        recyclerView.setAdapter(adapter);

        // Access the database using Room
        database = Room.databaseBuilder(getApplicationContext(),
                FavoriteLocationDatabase.class, "favorite_locations_database").build();

        // Load favorite locations asynchronously
        loadFavoriteLocations();

        // Set click listener for item view
        adapter.setOnItemClickListener(new FavoritesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                // Handle item click here if needed
            }

            @Override
            public void onDeleteClick(int position) {
                // Retrieve the favorite location to delete
                FavoriteLocation favoriteLocation = adapter.getItem(position);
                // Delete the favorite location from the database
                deleteFavoriteLocation(favoriteLocation);
            }
        });
    }

    private void loadFavoriteLocations() {
        // Use Kotlin coroutines to perform database operation off the main thread
        // This prevents database operations from blocking the UI
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Retrieve favorite locations from the database
                List<FavoriteLocation> favoriteLocations = database.lDAO().getAll();

                // Update the adapter with the retrieved favorite locations
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.setLocations(favoriteLocations);
                    }
                });
            }
        }).start();
    }

    // Method to delete a favorite location
    private void deleteFavoriteLocation(FavoriteLocation favoriteLocation) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Delete the favorite location from the database
                database.lDAO().delete(favoriteLocation);

                // Reload favorite locations after deletion
                loadFavoriteLocations();
            }
        }).start();
    }
}
