package algonquin.cst2335.final_project_w24.SunApp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import algonquin.cst2335.final_project_w24.R;

public class SunActivity extends AppCompatActivity {

    private EditText latitudeEditText;
    private EditText longitudeEditText;
    private Button lookupButton;

    private Button favoriteButton;
    private TextView resultTextView;

    private static final String SUNRISE_SUNSET_API_URL = "https://api.sunrise-sunset.org/json?";
    private static final String TIMEZONE = "UTC";

    private RequestQueue requestQueue;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sun);

        // Inside onCreate() method
        Toolbar toolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true); // Enable the Up button
            actionBar.setTitle("Sun Activity"); // Set title for the activity
        }

        latitudeEditText = findViewById(R.id.editTextLatitude);
        longitudeEditText = findViewById(R.id.editTextLongitude);
        lookupButton = findViewById(R.id.buttonLookup);
        favoriteButton = findViewById(R.id.buttonFavorites);
        resultTextView = findViewById(R.id.textViewResults);

        requestQueue = Volley.newRequestQueue(this);
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        // Retrieve previous search term and set it in EditText
        String previousLatitude = sharedPreferences.getString("latitude", "");
        String previousLongitude = sharedPreferences.getString("longitude", "");
        latitudeEditText.setText(previousLatitude);
        longitudeEditText.setText(previousLongitude);

        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to FavoriteLocationsActivity
                Intent favoriteLocation = new Intent(SunActivity.this, FavoriteLocationsActivity.class);
                startActivity(favoriteLocation);
            }
        });

        lookupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String latitude = latitudeEditText.getText().toString();
                String longitude = longitudeEditText.getText().toString();

                if (latitude.isEmpty() || longitude.isEmpty()) {
                    Toast.makeText(SunActivity.this, "Please enter latitude and longitude", Toast.LENGTH_SHORT).show();
                } else {
                    fetchSunriseSunset(latitude, longitude);

                    // Save the entered latitude and longitude in SharedPreferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("latitude", latitude);
                    editor.putString("longitude", longitude);
                    editor.apply();
                }
            }
        });

        Button saveLocationButton = findViewById(R.id.buttonSaveLocation);
        saveLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String latitude = latitudeEditText.getText().toString();
                String longitude = longitudeEditText.getText().toString();

                if (latitude.isEmpty() || longitude.isEmpty()) {
                    Toast.makeText(SunActivity.this, "Please search for a location first", Toast.LENGTH_SHORT).show();
                } else {
                    // Save the location to favorites
                    FavoriteLocation favoriteLocation = new FavoriteLocation(latitude, longitude);
                    saveFavoriteLocation(favoriteLocation);

                    Toast.makeText(SunActivity.this, "Location saved to favorites", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void saveFavoriteLocation(FavoriteLocation favoriteLocation) {
        // Access the database using Room
        FavoriteLocationDatabase database = Room.databaseBuilder(getApplicationContext(),
                FavoriteLocationDatabase.class, "favorite_locations_database").build();

        // Insert the favorite location into the database
        new Thread(new Runnable() {
            @Override
            public void run() {
                database.lDAO().insert(favoriteLocation);
            }
        }).start();
    }



    private void fetchSunriseSunset(String latitude, String longitude) {
        String url = SUNRISE_SUNSET_API_URL + "lat=" + latitude + "&lng=" + longitude + "&timezone=" + TIMEZONE;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject results = response.getJSONObject("results");
                            String sunrise = results.getString("sunrise");
                            String sunset = results.getString("sunset");

                            String resultText = "Sunrise: " + sunrise + "\nSunset: " + sunset;
                            resultTextView.setText(resultText);

                            // Show Toast notification
                            Toast.makeText(SunActivity.this, "Sunrise and Sunset times retrieved successfully", Toast.LENGTH_SHORT).show();

                            // Show Snackbar notification
                            Snackbar.make(findViewById(android.R.id.content), "Sunrise and Sunset times retrieved successfully", Snackbar.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(SunActivity.this, "Error parsing JSON response", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(SunActivity.this, "Error fetching data", Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(jsonObjectRequest);
    }

    private void showAlertDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setPositiveButton("OK", null)
                .create()
                .show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_help) {
            showHelpDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showHelpDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Help");
        builder.setMessage("Enter the latitude and longitude of the location you want to lookup for sunrise and sunset times. Click the 'Lookup' button to get the results. The retrieved sunrise and sunset times will be displayed below.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
