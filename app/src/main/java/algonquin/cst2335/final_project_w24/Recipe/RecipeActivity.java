package algonquin.cst2335.final_project_w24.Recipe;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import algonquin.cst2335.final_project_w24.R;
import algonquin.cst2335.final_project_w24.Recipe.Listeners.SearchRecipeResponseListener;
import algonquin.cst2335.final_project_w24.Recipe.SearchAdapters.SearchRecipeAdapter;
import algonquin.cst2335.final_project_w24.Recipe.SerachRecipe.Result;
import algonquin.cst2335.final_project_w24.Recipe.SerachRecipe.SearchRecipeApiResponse;

public class RecipeActivity extends AppCompatActivity {

    Dialog dialog;
    RequestManager manager;
    RecyclerView recyclerView;
    Button searchButton;
    EditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        initViews();
        manager = new RequestManager(this);

        searchButton.setOnClickListener(v -> {
            String query = searchEditText.getText().toString().trim();
            if (!query.isEmpty()) {
                performSearch(query);
            } else {
                Toast.makeText(RecipeActivity.this, "Please enter a search term.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recipesRecyclerView);
        searchButton = findViewById(R.id.searchButton);
        searchEditText = findViewById(R.id.searchEditText);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // Adjust columns as needed
        dialog = new Dialog(RecipeActivity.this);
        dialog.setTitle("Loading...");
        dialog.setCancelable(false); // Make dialog non-cancelable if desired
    }

    private void performSearch(String query) {
        dialog.show();
        manager.getSearchRecipes(query, new SearchRecipeResponseListener() {
            @Override
            public void didFetch(SearchRecipeApiResponse response, String message) {
                SearchRecipeAdapter adapter = new SearchRecipeAdapter(
                        RecipeActivity.this,
                        response.getResults(),
                        item -> navigateToDetailActivity(item) // Using lambda expression
                );
                recyclerView.setAdapter(adapter);
                dialog.dismiss();
            }

            @Override
            public void didError(String message) {
                Toast.makeText(RecipeActivity.this, message, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }

    private void navigateToDetailActivity(Result item) {
        Intent detailIntent = new Intent(RecipeActivity.this, RecipeDetailActivity.class);
        detailIntent.putExtra("RECIPE_ID", item.getId());
        startActivity(detailIntent);
    }
}
