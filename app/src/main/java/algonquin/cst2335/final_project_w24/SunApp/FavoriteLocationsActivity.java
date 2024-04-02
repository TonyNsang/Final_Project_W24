package algonquin.cst2335.final_project_w24.SunApp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

import algonquin.cst2335.final_project_w24.R;

public class FavoriteLocationsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FavoritesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_locations);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new FavoritesAdapter();
        recyclerView.setAdapter(adapter);

        // Load favorite locations asynchronously
        loadFavoriteLocations();
    }

    private void loadFavoriteLocations() {
        // Use Kotlin coroutines to perform database operation off the main thread
        // This prevents database operations from blocking the UI
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Access the database using Room
                FavoriteLocationDatabase database = Room.databaseBuilder(getApplicationContext(),
                        FavoriteLocationDatabase.class, "favorite_locations_database").build();

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


}
