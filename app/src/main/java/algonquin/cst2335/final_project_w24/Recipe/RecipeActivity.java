package algonquin.cst2335.final_project_w24.Recipe;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import algonquin.cst2335.final_project_w24.R;
import algonquin.cst2335.final_project_w24.Recipe.Adapters.RandomRecipeAdapter;
import algonquin.cst2335.final_project_w24.Recipe.Listeners.RandomRecipeResponseListener;
import algonquin.cst2335.final_project_w24.Recipe.Models.RandomRecipeApiResponse;

public class RecipeActivity extends AppCompatActivity {

    Dialog dialog;
    RequestManager manager;
    RandomRecipeAdapter randomRecipeAdapter;
    RecyclerView recyclerView;
    Button searchButton;
    EditText searchEditText;

    private final RandomRecipeResponseListener randomRecipeResponseListener = new RandomRecipeResponseListener() {
        @Override
        public void didFetch(RandomRecipeApiResponse response, String message) {
            recyclerView = findViewById(R.id.recipesRecyclerView);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(RecipeActivity.this, 1));
            randomRecipeAdapter = new RandomRecipeAdapter(RecipeActivity.this, response.recipes);
            recyclerView.setAdapter(randomRecipeAdapter);
            dialog.dismiss(); // Hide the dialog after the data is fetched
        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeActivity.this, message, Toast.LENGTH_SHORT).show();
            dialog.dismiss(); // Hide the dialog if there is an error
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recipe);

        // Initialize your dialog, manager, recyclerView, and adapter here as you've done

        searchButton = findViewById(R.id.searchButton);
        searchEditText = findViewById(R.id.searchEditText);

        searchButton.setOnClickListener(v -> {
            String query = searchEditText.getText().toString();
            if (!query.isEmpty()) {
                // Assuming you have a method in RequestManager to search for recipes
                // You might need to modify this part to match your implementation
                manager.searchRecipes(query, (RequestManager.RandomRecipeRespondListener) randomRecipeResponseListener);
                dialog.show();
            } else {
                Toast.makeText(RecipeActivity.this, "Please enter a search term.", Toast.LENGTH_SHORT).show();
            }
        });

        // You may remove or modify the following line if you are not fetching random recipes on activity start
        // manager.getRandomRecipes(randomRecipeResponseListener);
        // dialog.show();
    }
}
