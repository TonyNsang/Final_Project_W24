package algonquin.cst2335.final_project_w24.SunApp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

        loadFavoriteLocations();
    }

    private void loadFavoriteLocations() {
        List<FavoriteLocation> locations = FavoriteLocationDatabase.getInstance(this).favoriteLocationDao().getAll();
        adapter.setLocations(locations);
    }
}