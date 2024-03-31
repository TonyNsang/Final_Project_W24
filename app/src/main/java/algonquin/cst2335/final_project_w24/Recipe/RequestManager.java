package algonquin.cst2335.final_project_w24.Recipe;

import android.content.Context;

import algonquin.cst2335.final_project_w24.R;
import algonquin.cst2335.final_project_w24.Recipe.Listeners.RandomRecipeResponseListener;
import algonquin.cst2335.final_project_w24.Recipe.Models.RandomRecipeApiResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
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

    public void getRandomRecipes(RandomRecipeRespondListener listener) {
        // Implementation of getRandomRecipes remains unchanged
    }

    public void searchRecipes(String query, RandomRecipeRespondListener listener) {
        CallSearchRecipes callSearchRecipes = retrofit.create(CallSearchRecipes.class);
        Call<RandomRecipeApiResponse> call = callSearchRecipes.callSearchRecipe(
                context.getString(R.string.api_key),
                query);

        call.enqueue(new Callback<RandomRecipeApiResponse>() {
            @Override
            public void onResponse(Call<RandomRecipeApiResponse> call, Response<RandomRecipeApiResponse> response) {
                if (!response.isSuccessful()) {
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RandomRecipeApiResponse> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    private interface CallRandomRecipes {
        @GET("recipes/random")
        Call<RandomRecipeApiResponse> callRandomRecipe(
                @Query("apiKey") String apiKey,
                @Query("number") String number
        );
    }

    private interface CallSearchRecipes {
        @GET("recipes/complexSearch")
        Call<RandomRecipeApiResponse> callSearchRecipe(
                @Query("apiKey") String apiKey,
                @Query("query") String query
        );
    }

    public interface RandomRecipeRespondListener extends RandomRecipeResponseListener {
        void didError(String message);
        void didFetch(RandomRecipeApiResponse response, String message);
    }

}
