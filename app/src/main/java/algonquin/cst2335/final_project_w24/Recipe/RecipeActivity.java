package algonquin.cst2335.final_project_w24.Recipe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import algonquin.cst2335.final_project_w24.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RecipeActivity extends AppCompatActivity {

    private EditText searchEditText;
    private RecyclerView recipesRecyclerView;
    private RecipesAdapter recipesAdapter;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        sharedPreferences = getSharedPreferences("MyRecipePreferences", MODE_PRIVATE);
        searchEditText = findViewById(R.id.searchEditText);
        Button searchButton = findViewById(R.id.searchButton);
        recipesRecyclerView = findViewById(R.id.recipesRecyclerView);

        // Set up RecyclerView
        recipesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recipesAdapter = new RecipesAdapter(new ArrayList<>());
//        recipesRecyclerView.setAdapter(recipesAdapter);

        // Load the last search term from SharedPreferences
        searchEditText.setText(sharedPreferences.getString("last_search", ""));

        // Set up the search button click listener
//        searchButton.setOnClickListener(v -> {
//            String query = searchEditText.getText().toString().trim();
//            if (!query.isEmpty()) {
//                performSearch(query);
//                // Save the last search term
//                sharedPreferences.edit().putString("last_search", query).apply();
//            } else {
//                Toast.makeText(RecipeActivity.this, R.string.enter_search_term, Toast.LENGTH_LONG).show();
//            }
//        });
//    }
//
//    private void performSearch(String query) {
//        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
//        SpoonacularApiService apiService = retrofit.create(SpoonacularApiService.class);
//        Call<RecipeSearchResponse> call = apiService.searchRecipes(query, getString(R.string.spoonacular_api_key)).clone();
//
//        call.enqueue(new Callback<RecipeSearchResponse>() {
//            @Override
//            public void onResponse(Call<RecipeSearchResponse> call, Response<RecipeSearchResponse> response) {
//                if(response.isSuccessful() && response.body() != null) {
//                    List<Recipe> recipes = response.body().getResults();
//                    recipesAdapter.setRecipes(recipes);
//                } else {
//                    Toast.makeText(RecipeActivity.this, "Failed to retrieve data", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<RecipeSearchResponse> call, Throwable t) {
//                Toast.makeText(RecipeActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    // Implement RecyclerView click event here and navigate to the detail view
//    // Example stub for RecyclerView item click listener
//    private void onRecipeClicked(Recipe recipe) {
//        Intent detailIntent = new Intent(this, RecipeDetailActivity.class);
//        detailIntent.putExtra("RECIPE_ID", recipe.getId());
//        startActivity(detailIntent);
//    }
//
//    // Other methods...
//    private void onRecipeClicked(Recipe recipe) {
//        // Intent to start RecipeDetailActivity
//        Intent detailIntent = new Intent(this, RecipeDetailActivity.class);
//        detailIntent.putExtra("RECIPE_ID", recipe.getId());
//        startActivity(detailIntent);
//    }
//
//    private void performSearch(String query) {
//        // Retrofit network call to search for recipes
//        // onResponse: update the RecyclerView adapter with results
//        // onFailure: display an error message
//    }
//
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        // Save the current state of the activity (e.g., the current search term)
//        super.onSaveInstanceState(outState);
//        outState.putString("last_search", searchEditText.getText().toString());
//    }
//
//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        // Restore the state of the activity
//        super.onRestoreInstanceState(savedInstanceState);
//        String lastSearch = savedInstanceState.getString("last_search", "");
//        searchEditText.setText(lastSearch);
//    }
//
//    // Add any other lifecycle method overrides or additional methods you need here...
//
//    // For example, you may need to override onDestroy to clean up resources
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        // Perform any cleanup tasks here
//    }
//    @Override
//    protected void onStart() {
//        super.onStart();
//        // This is called when the activity is about to become visible.
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        // This is called when the activity has become visible and is about to start interacting with the user.
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        // This is called when the system is about to put the activity into the background or when the activity is about to be destroyed.
//        // Commit any changes that should persist beyond the current user session.
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        // This is called when the activity is no longer visible to the user.
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        // This is called before the activity is destroyed. The activity is finishing or being destroyed by the system (e.g., in case of configuration change).
//    }
//
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        // This is called when the activity has been stopped and is restarting again.
//    }
//
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        // Save the current state of the activity (e.g., the current search term).
//        outState.putString("last_search", searchEditText.getText().toString());
//    }
//
//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        // Restore the state of the activity from the saved instance state.
//        searchEditText.setText(savedInstanceState.getString("last_search", ""));
//    }
//
//    // ... (The rest of your existing methods)
//
//    // You can also implement onActivityResult if you start any activities for result
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        // Handle the result returned by another activity.
//    }
    }
}


