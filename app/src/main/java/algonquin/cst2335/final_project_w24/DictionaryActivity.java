package algonquin.cst2335.final_project_w24;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import algonquin.cst2335.final_project_w24.databinding.ActivityDictionaryBinding;

/**
 * This class runs the Dictionary Application where a word is entered and on click of search
 * It redirects the user to an API and retrieves the matching definitions of that word
 * @author Tony Nsang
 *
 */
public class DictionaryActivity extends AppCompatActivity {
    private static final String TAG = "DictionaryActivity";
    /**
     * Viewbinding variable
     */
    ActivityDictionaryBinding binding;
    /**
     * ViewModel class object of DictonaryActivity
     */
    private DictionaryViewModel model;
    /**
     * SharedPreferences File name static variable
     */
    private static final String SHARED_PREFS_FILE = "MyData";
    /**
     * Array to store definitions of a word
     */
    private List<String> definitions = new ArrayList<>();
    /**
     * Client Request to server
     */
    protected RequestQueue requestQueue;
    /**
     * On create function to load the Activities layout inclusing widgets
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        model = new ViewModelProvider(this).get(DictionaryViewModel.class);

        binding = ActivityDictionaryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        requestQueue = Volley.newRequestQueue(this);

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        String lastSearchTerm = prefs.getString("last_search_term", "");
        binding.searchText.setText(lastSearchTerm);

        binding.searchButton.setOnClickListener(click -> {
            model.searchText.postValue( binding.searchText.getText().toString());
            model.searchText.observe(this, s -> {
                binding.searchText.setText(lastSearchTerm);
            });
            String word = binding.searchText.getText().toString();
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("last_search_term", word);
            editor.apply();
            fetchDefinitions(word);

        });


    }

    /**
     * Sends request to Dictionary API and fetches the definitions related to the word entered by user
     * @param word Word entered by user
     */
    private void fetchDefinitions(String word) {
        String url = "https://api.dictionaryapi.dev/api/v2/entries/en/" + word;
        // Create the JSON request
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {

                        JSONArray definitionsArray = response.getJSONArray("meanings");
                        definitions.clear();
                        for (int i = 0; i < definitionsArray.length(); i++) {
                            JSONObject meaningObject = definitionsArray.getJSONObject(i);
                            JSONArray definitionsJsonArray = meaningObject.getJSONArray("definitions");
                            for (int j = 0; j < definitionsJsonArray.length(); j++) {
                                JSONObject definitionObject = definitionsJsonArray.getJSONObject(j);
                                String definition = definitionObject.getString("definition");
                                definitions.add(definition);
                            }
                        }
                        // definitionAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Toast.makeText(DictionaryActivity.this, "Error fetching definitions", Toast.LENGTH_SHORT).show());

        // Add the request to the RequestQueue
        requestQueue.add(jsonObjectRequest);
    }
}

