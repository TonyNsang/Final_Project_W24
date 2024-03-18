package algonquin.cst2335.final_project_w24;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.EditText;

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
    /**
     * Viewbinding variable
     */
    ActivityDictionaryBinding binding;
    /**
     * SharedPreferences File name static variable
     */
    private static final String SHARED_PREFS_FILE = "MyData";
    /**
     * Array to store definitions of a word
     */
    private List<String> definitions = new ArrayList<>();
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

        binding = ActivityDictionaryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        String lastSearchTerm = prefs.getString("last_search_term", "");
        binding.searchText.setText(lastSearchTerm);

        binding.searchButton.setOnClickListener(click -> {
            String word = binding.searchText.getText().toString();
            fetchDefinitions(word);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("last_search_term", word);
            editor.apply();
        });


    }

    /**
     * Redirects user to Dictionary API and fetches the definitions related to the word entered by user
     * @param word Word entered by user
     */
    private void fetchDefinitions(String word) {
        String url = "https://api.dictionaryapi.dev/api/v2/entries/en/" + word;

    }
}

