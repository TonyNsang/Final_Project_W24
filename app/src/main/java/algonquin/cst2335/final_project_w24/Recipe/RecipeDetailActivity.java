package algonquin.cst2335.final_project_w24.Recipe;

import android.os.Bundle;
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

        // Retrieve the recipe ID passed from RecipeActivity
        int recipeId = getIntent().getIntExtra("RECIPE_ID", -1);
        if (recipeId != -1) {
            fetchRecipeDetails(recipeId);
        } else {
            Toast.makeText(this, "Invalid recipe ID.", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity if the ID is invalid
        }
    }

    private void fetchRecipeDetails(int recipeId) {
        RequestManager manager = new RequestManager(this);
        manager.getRecipeDetails(recipeId, new RecipeDetailResponseListener() {
            @Override
            public void didFetch(DetailRecipeApiResponse response, String message) {
                // Set the title, summary, and source URL
                titleTextView.setText(response.getTitle());
                summaryTextView.setText(android.text.Html.fromHtml(response.getSummary()).toString());
                sourceUrlTextView.setText(response.getSourceUrl());

                // Use Picasso to load the image into the ImageView
                Picasso.get().load(response.getImage()).into(recipeImageView);
            }

            @Override
            public void didError(String message) {
                Toast.makeText(RecipeDetailActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }
}
