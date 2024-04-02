package algonquin.cst2335.final_project_w24.SunApp;

import androidx.appcompat.app.AppCompatActivity;
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

        // Initialize Volley request queue
        requestQueue = Volley.newRequestQueue(this);

        buttonLookup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call method to perform the lookup
                performLookup();
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
}
