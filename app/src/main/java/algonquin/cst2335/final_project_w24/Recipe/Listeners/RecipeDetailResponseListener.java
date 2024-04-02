package algonquin.cst2335.final_project_w24.Recipe.Listeners;

import algonquin.cst2335.final_project_w24.Recipe.DetailRecipe.DetailRecipeApiResponse;

public interface RecipeDetailResponseListener {
    void didFetch(DetailRecipeApiResponse response, String message);
    void didError(String message);
}
