package algonquin.cst2335.final_project_w24.Dictionary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import algonquin.cst2335.final_project_w24.R;
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
    private final ArrayList<String> definitions = new ArrayList<>();
    /**
     * RecycleView Adapter
     */
    private RecyclerView.Adapter myAdapter;
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
        Log.w("DictionaryActivity", "In OnCreate()-widget");
        model = new ViewModelProvider(this).get(DictionaryViewModel.class);

        binding = ActivityDictionaryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.dictionaryView.setLayoutManager(new LinearLayoutManager(this));

        requestQueue = Volley.newRequestQueue(this);

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        String lastSearchTerm = prefs.getString("last_search_term", "");
        binding.searchText.setText(lastSearchTerm);


        binding.searchButton.setOnClickListener(click -> {
            model.searchText.postValue( binding.searchText.getText().toString());

            String word = binding.searchText.getText().toString();
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("last_search_term", word);
            editor.apply();
            definitions.add(binding.searchText.getText().toString());
            fetchDefinitions(word);

        });
         binding.dictionaryView.setAdapter(myAdapter= new RecyclerView.Adapter<MyRowHolder>() {
             /**
              * This function creates a ViewHolder object. It represents a single row in the list
              * @param parent   The ViewGroup into which the new View will be added after it is bound to
              *                 an adapter position.
              * @param viewType The view type of the new View.
              * @return MyRowHolder
              */
             @NonNull
             @Override
             public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                 return null;
             }

             /**
              * This initializes a ViewHolder to go at the row specified by the position parameter.
              * @param holder   The ViewHolder which should be updated to represent the contents of the
              *                 item at the given position in the data set.
              * @param position The position of the item within the adapter's data set.
              */
             @Override
             public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {
                 String obj = definitions.get(position);
                holder.definitionText.setText(obj);
             }

             /**
              * This function just returns an int specifying how many items to draw.
              * @return int
              */
             @Override
             public int getItemCount() {
                 return definitions.size();
             }

             public int getItemViewType(int position){
                 return 0;
             }
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

    /**
     * an object for representing everything that goes on a row in the list
     */
    class MyRowHolder extends RecyclerView.ViewHolder {
        TextView definitionText;

        public MyRowHolder(@NonNull View itemView) {
            super(itemView);
            definitionText = itemView.findViewById(R.id.definitions);
        }
    }
}

