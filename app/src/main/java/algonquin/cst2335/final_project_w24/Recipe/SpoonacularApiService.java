package algonquin.cst2335.final_project_w24.Recipe;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class SpoonacularApiService {
    @GET("recipes/complexSearch")
    <RecipeSearchResponse>
    Call<RecipeSearchResponse> searchRecipes(@Query("query") String query, @Query("apiKey") String apiKey) {
        return null;
    }

    @GET("recipes/{id}/information")
    Call<Recipe> getRecipeDetails(@Path("id") int recipeId, @Query("apiKey") String apiKey) {
        return null;
    }
}

