package algonquin.cst2335.final_project_w24.Dictionary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.ServerError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import algonquin.cst2335.final_project_w24.DeezerApp.SongActivity;
import algonquin.cst2335.final_project_w24.MainActivity;
import algonquin.cst2335.final_project_w24.R;
import algonquin.cst2335.final_project_w24.Recipe.RecipeActivity;
import algonquin.cst2335.final_project_w24.SunApp.SunActivity;
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
    private ArrayList<DictionaryData> definitions = new ArrayList<>();
    /**
     * RecycleView Adapter
     */
    private RecyclerView.Adapter myAdapter;
    /**
     * Client Request to server
     */
     RequestQueue requestQueue=null;
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

        Toolbar toolbar = binding.myToolbar;
        setSupportActionBar(toolbar);

        binding.dictionaryView.setLayoutManager(new LinearLayoutManager(this));


        //Shared Preferences for Search Term in edit text
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        String lastSearchTerm = prefs.getString("last_search_term", "");
        binding.searchText.setText(lastSearchTerm);

        //OnClick listener for search button
        binding.searchButton.setOnClickListener(click -> {
            model.searchText.postValue( binding.searchText.getText().toString());


            String word = binding.searchText.getText().toString();
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("last_search_term", word);
            editor.apply();
            //definitions.add(binding.searchText.getText().toString());
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
                 View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_definition, parent, false);
                 return new MyRowHolder(itemView);
             }

             /**
              * This initializes a ViewHolder to go at the row specified by the position parameter.
              * @param holder   The ViewHolder which should be updated to represent the contents of the
              *                 item at the given position in the data set.
              * @param position The position of the item within the adapter's data set.
              */
             @Override
             public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {
                 DictionaryData currentTerm = definitions.get(position);
                holder.searchTermText.setText(currentTerm.getSearchTerm());

                 holder.definitionText.setText(formatDefinitions(currentTerm.getDefinitionsOfTerm()));

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
        requestQueue = Volley.newRequestQueue(this);
        String url = "https://api.dictionaryapi.dev/api/v2/entries/en/" + word;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONArray jsonArray = new JSONArray(word);
                        List<DictionaryData> wordDefinitions = new ArrayList<>();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject wordObject = jsonArray.getJSONObject(i);
                            JSONArray meaningsArray = wordObject.getJSONArray("meanings");
                            for (int j = 0; j < meaningsArray.length(); j++) {
                                JSONObject meaningObject = meaningsArray.getJSONObject(j);
                                JSONArray definitionsArray = meaningObject.getJSONArray("definitions");
                                for (int k = 0; k < definitionsArray.length(); k++) {
                                    JSONObject definitionObject = definitionsArray.getJSONObject(k);
                                    String definition = definitionObject.optString("definition");
                                    // Create a DictionaryData object to store the word and its definition
                                    DictionaryData data = new DictionaryData();
                                    data.setSearchTerm(word);
                                    ArrayList<String> definitionList = new ArrayList<>();
                                    definitionList.add(definition);
                                    data.setDefinitionsOfTerm(definitionList);
                                    // Add the DictionaryData object to the list
                                    wordDefinitions.add(data);
                                }
                            }
                        }
                        // Now you have a list of DictionaryData objects containing the word and its definitions
                        // Do whatever you need with this data
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    if (error instanceof NetworkError) {
                        Toast.makeText(DictionaryActivity.this, "Network error", Toast.LENGTH_SHORT).show();
                    } else if (error instanceof ServerError) {
                        Toast.makeText(DictionaryActivity.this, "Server error", Toast.LENGTH_SHORT).show();
                    } else if (error instanceof ParseError) {
                        Toast.makeText(DictionaryActivity.this, "Parse error", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DictionaryActivity.this, "Unknown error", Toast.LENGTH_SHORT).show();
                    }
                    error.printStackTrace();
                }
        );

        // Add the request to the RequestQueue
        requestQueue.add(jsonObjectRequest);
    }



    /**
     * an object for representing everything that goes on a row in the list
     */
    class MyRowHolder extends RecyclerView.ViewHolder {
        TextView searchTermText;
        TextView definitionText;

        public MyRowHolder(@NonNull View itemView) {
            super(itemView);
            searchTermText = itemView.findViewById(R.id.word);
            definitionText = itemView.findViewById(R.id.definitionText);
        }
    }
    private String formatDefinitions(ArrayList<String> definitions) {
        StringBuilder formattedDefinitions = new StringBuilder();
        for (String definition : definitions) {
            formattedDefinitions.append("\u2022 ").append(definition).append("\n"); // Unicode for bullet point
        }
        return formattedDefinitions.toString();
    }

    //Toolbar Menu items
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.dictionaryIcon) {
            Intent dictionaryPage = new Intent(DictionaryActivity.this, DictionaryActivity.class);
            startActivity(dictionaryPage);
            return true;
        } else if (item.getItemId() == R.id.recipeIcon) {
            Intent recipePage = new Intent(DictionaryActivity.this, RecipeActivity.class);
            startActivity(recipePage);
            return true;
        } else if (item.getItemId() == R.id.sunIcon) {
            Intent sunPage = new Intent(DictionaryActivity.this, SunActivity.class);
            startActivity(sunPage);
            return true;

        } else if (item.getItemId() == R.id.help_Icon) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Help");
            builder.setMessage("Instructions for using the Dictionary API:\n\n" +
                    "1. Enter a word to look up the definition.\n" +
                    "2. Definitions will be displayed in a RecyclerView.\n" +
                    "3. You can save search terms and definitions locally.\n" +
                    "4. Use the 'View Saved Terms' button to view and delete saved terms.\n" +
                    "5. SharedPreferences will save your last search term.");
            builder.setPositiveButton("OK", ((dialog, click) ->{} )).create().show();
            return true;
        }else {

        }
        return false;
    }
}

