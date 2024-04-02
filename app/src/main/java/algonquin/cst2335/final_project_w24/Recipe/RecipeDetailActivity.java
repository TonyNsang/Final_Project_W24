package algonquin.cst2335.final_project_w24.Recipe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.squareup.picasso.Picasso;

import algonquin.cst2335.final_project_w24.R;
import algonquin.cst2335.final_project_w24.Recipe.DetailRecipe.DetailRecipeApiResponse;
import algonquin.cst2335.final_project_w24.Recipe.Listeners.RecipeDetailResponseListener;

public class RecipeDetailActivity extends AppCompatActivity {

    private ImageView recipeImageView;
    private TextView titleTextView;
    private TextView summaryTextView;
    private TextView sourceUrlTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        recipeImageView = findViewById(R.id.recipeImageView);
        titleTextView = findViewById(R.id.recipeTitleTextView);
        summaryTextView = findViewById(R.id.recipeSummaryTextView);
        sourceUrlTextView = findViewById(R.id.recipeSourceUrlTextView);

        int recipeId = getIntent().getIntExtra("RECIPE_ID", -1);
        if (recipeId != -1) {
            fetchRecipeDetails(recipeId);
        } else {
            Toast.makeText(this, "Invalid recipe ID.", Toast.LENGTH_SHORT).show();
            finish();
        }

        Button button_save_recipe = findViewById(R.id.button_save_recipe);
        button_save_recipe.setOnClickListener(v -> {
            saveRecipeDetails();
        });

        Button button_favorite = findViewById(R.id.button_favorite);
        button_favorite.setOnClickListener(v -> {
            saveRecipeDetails();
            navigateToSavedRecipes();
        });
    }

    private void fetchRecipeDetails(int recipeId) {
        RequestManager manager = new RequestManager(this);
        manager.getRecipeDetails(recipeId, new RecipeDetailResponseListener() {
            @Override
            public void didFetch(DetailRecipeApiResponse response, String message) {
                titleTextView.setText(response.getTitle());
                summaryTextView.setText(android.text.Html.fromHtml(response.getSummary()).toString());
                sourceUrlTextView.setText(response.getSourceUrl());
                Picasso.get().load(response.getImage()).into(recipeImageView);
            }

            @Override
            public void didError(String message) {
                Toast.makeText(RecipeDetailActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }


    private void saveRecipeDetails() {
        String title = titleTextView.getText().toString();
        String summary = summaryTextView.getText().toString();
        String sourceUrl = sourceUrlTextView.getText().toString();

        // Generate a unique key for the recipe. For simplicity, using the title here but it should be unique.
        String uniqueKey = title + "_" + System.currentTimeMillis();

        SharedPreferences sharedPreferences = getSharedPreferences("SavedRecipes", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        int recipeCount = sharedPreferences.getInt("RecipeCount", 0);
        String recipeKey = "Recipe_" + recipeCount;
        editor.putString(recipeKey, title + ";" + summary + ";" + sourceUrl);
        editor.putInt("RecipeCount", recipeCount + 1);
        editor.apply();

        Toast.makeText(this, "Recipe saved successfully", Toast.LENGTH_SHORT).show();

        // Navigate to the list of saved recipes
        navigateToSavedRecipes();

    }

    private void navigateToSavedRecipes() {
        Intent intent = new Intent(RecipeDetailActivity.this, SavedRecipesActivity.class);
        startActivity(intent);
    }

    }

