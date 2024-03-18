package algonquin.cst2335.final_project_w24;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import algonquin.cst2335.final_project_w24.databinding.ActivityDictionaryBinding;

public class DictionaryActivity extends AppCompatActivity {
    ActivityDictionaryBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDictionaryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}