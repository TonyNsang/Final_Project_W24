package algonquin.cst2335.final_project_w24.Recipe;

import android.content.Context;
import android.util.Log;
import algonquin.cst2335.final_project_w24.R;
import algonquin.cst2335.final_project_w24.Recipe.DetailRecipe.DetailRecipeApiResponse;
import algonquin.cst2335.final_project_w24.Recipe.Listeners.RecipeDetailResponseListener;
import algonquin.cst2335.final_project_w24.Recipe.Listeners.SearchRecipeResponseListener;
import algonquin.cst2335.final_project_w24.Recipe.SerachRecipe.SearchRecipeApiResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RequestManager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }

    private interface SearchRecipesService {
        @GET("recipes/complexSearch")
        Call<SearchRecipeApiResponse> searchRecipes(
                @Query("query") String query,
                @Query("apiKey") String apiKey);
    }

    private interface CallRecipeDetails {
        @GET("recipes/{id}/information")
        Call<DetailRecipeApiResponse> callRecipeDetail(
                @Path("id") int recipeId,
                @Query("apiKey") String apiKey);
    }

    public void getSearchRecipes(String query, final SearchRecipeResponseListener listener) {
        SearchRecipesService service = retrofit.create(SearchRecipesService.class);
        Call<SearchRecipeApiResponse> call = service.searchRecipes(query, context.getString(R.string.api_key));

        call.enqueue(new Callback<SearchRecipeApiResponse>() {
            @Override
            public void onResponse(Call<SearchRecipeApiResponse> call, Response<SearchRecipeApiResponse> response) {
                if (!response.isSuccessful()) {
                    listener.didError("Server returned an error: " + response.message());
                    return;
                }
                SearchRecipeApiResponse apiResponse = response.body();
                if (apiResponse != null && apiResponse.results != null) {
                    listener.didFetch(apiResponse, "Recipes fetched successfully");
                } else {
                    listener.didError("No recipes found");
                }
            }

            @Override
            public void onFailure(Call<SearchRecipeApiResponse> call, Throwable t) {
                listener.didError("Network failure: " + t.getMessage());
            }
        });
    }

    public void getRecipeDetails(int recipeId, final RecipeDetailResponseListener listener) {
        CallRecipeDetails callRecipeDetails = retrofit.create(CallRecipeDetails.class);
        Call<DetailRecipeApiResponse> call = callRecipeDetails.callRecipeDetail(recipeId, context.getString(R.string.api_key));

        call.enqueue(new Callback<DetailRecipeApiResponse>() {
            @Override
            public void onResponse(Call<DetailRecipeApiResponse> call, Response<DetailRecipeApiResponse> response) {
                if (!response.isSuccessful()) {
                    listener.didError("Server returned an error: " + response.message());
                    Log.e("RequestManager", "Server Error: " + response.code() + " " + response.message());
                    return;
                }
                DetailRecipeApiResponse recipeDetails = response.body();
                listener.didFetch(recipeDetails, "Recipe details fetched successfully");
                Log.d("RequestManager", "Success: " + (recipeDetails != null ? recipeDetails.toString() : "null"));
            }

            @Override
            public void onFailure(Call<DetailRecipeApiResponse> call, Throwable t) {
                listener.didError("Network failure: " + t.getMessage());
                Log.e("RequestManager", "Failure: " + t.getMessage());
            }
        });
    }
}
