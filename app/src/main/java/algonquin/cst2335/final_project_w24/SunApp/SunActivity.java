package algonquin.cst2335.final_project_w24.SunApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;

import algonquin.cst2335.final_project_w24.R;

import algonquin.cst2335.final_project_w24.R;

public class SunActivity extends AppCompatActivity {

    private EditText editTextLatitude;
    private EditText editTextLongitude;
    private Button buttonLookup;
    private Button buttonFavorites;
    private Button buttonSaveLocation;
    private Button buttonDelete;
    private TextView textViewResults;
    private RequestQueue requestQueue;

    private RecyclerView recyclerViewFavorites;
    private FavoritesAdapter favoritesAdapter;
    private FavoriteLocationDatabase appDatabase;
    private FavoriteLocationDAO favoriteLocationDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sun);

        editTextLatitude = findViewById(R.id.editTextLatitude);
        editTextLongitude = findViewById(R.id.editTextLongitude);
        buttonLookup = findViewById(R.id.buttonLookup);
        buttonFavorites = findViewById(R.id.buttonFavorites);
        buttonSaveLocation = findViewById(R.id.buttonSaveLocation);
        buttonDelete = findViewById(R.id.buttonDelete);
        textViewResults = findViewById(R.id.textViewResults);
        recyclerViewFavorites = findViewById(R.id.recyclerViewFavorites);

        // Initialize Volley request queue
        requestQueue = Volley.newRequestQueue(this);

        appDatabase = Room.databaseBuilder(getApplicationContext(), FavoriteLocationDatabase.class, "favorite_locations_db")
                .allowMainThreadQueries() // For simplicity, allow database operations on the main thread (not recommended for production)
                .build();

        // Initialize DAO
        favoriteLocationDao = appDatabase.favoriteLocationDao();

        // Set up RecyclerView for displaying favorite locations
        recyclerViewFavorites.setLayoutManager(new LinearLayoutManager(this));
        updateFavoritesList();
        buttonLookup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call method to perform the lookup
                performLookup();
            }
        });
        buttonFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call method to show list of favorite locations
                updateFavoritesList();
            }
        });

        buttonSaveLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call method to save location in the database
                saveLocation();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call method to delete location from the database
                deleteLocation();
            }
        });
    }

    private void performLookup() {
        String latitude = editTextLatitude.getText().toString().trim();
        String longitude = editTextLongitude.getText().toString().trim();

        // Check if latitude and longitude are not empty
        if (!latitude.isEmpty() && !longitude.isEmpty()) {
            // Construct the API URL
            String apiUrl = "https://api.sunrise-sunset.org/json?lat=" + latitude + "&lng=" + longitude + "&date=today";

            // Create a JsonObjectRequest to send the API query
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, apiUrl, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                // Parse the JSON response
                                String sunrise = response.getJSONObject("results").getString("sunrise");
                                String sunset = response.getJSONObject("results").getString("sunset");

                                // Display the results
                                textViewResults.setText("Sunrise: " + sunrise + " - Sunset: " + sunset);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // Handle errors
                            textViewResults.setText("Error occurred: " + error.getMessage());
                        }
                    });

            // Add the request to the RequestQueue
            requestQueue.add(jsonObjectRequest);
        } else {
            // Show error if latitude or longitude is empty
            textViewResults.setText("Please enter latitude and longitude.");
        }
    }
    private void updateFavoritesList() {
        // Retrieve all favorite locations from the database
        List<FavoriteLocation> favoriteLocations = favoriteLocationDao.getAll();

        // Update RecyclerView adapter
        favoritesAdapter = new FavoritesAdapter(favoriteLocations);
        recyclerViewFavorites.setAdapter(favoritesAdapter);
    }

    private void saveLocation() {
        // Get location name from editText fields
        String locationName = editTextLatitude.getText().toString().trim() + ", " +
                editTextLongitude.getText().toString().trim();

        // Insert the location into the database
        FavoriteLocation favoriteLocation = new FavoriteLocation(locationName);
        favoriteLocationDao.insert(favoriteLocation);

        // Display success message
        textViewResults.setText("Location saved: " + locationName);

        // Update the favorites list
        updateFavoritesList();
    }

    private void deleteLocation() {
        // For demonstration purposes, let's delete the first favorite location from the database
        List<FavoriteLocation> favoriteLocations = favoriteLocationDao.getAll();
        if (!favoriteLocations.isEmpty()) {
            FavoriteLocation locationToDelete = favoriteLocations.get(0);
            favoriteLocationDao.delete(locationToDelete);
            textViewResults.setText("Location deleted: " + locationToDelete.getLocationName());

            // Update the favorites list
            updateFavoritesList();
        } else {
            textViewResults.setText("No favorite locations to delete.");
        }
    }
}
