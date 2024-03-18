package algonquin.cst2335.final_project_w24;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.EditText;

import algonquin.cst2335.final_project_w24.databinding.ActivityDictionaryBinding;

public class DictionaryActivity extends AppCompatActivity {
    ActivityDictionaryBinding binding;

    private static final String SHARED_PREFS_FILE = "MyData";

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
    private void fetchDefinitions(String word) {
        String url = "https://api.dictionaryapi.dev/api/v2/entries/en/" + word;

    }
}

