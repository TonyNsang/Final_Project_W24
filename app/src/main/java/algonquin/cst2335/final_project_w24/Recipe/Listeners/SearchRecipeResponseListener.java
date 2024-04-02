package algonquin.cst2335.final_project_w24.Recipe.Listeners;

import algonquin.cst2335.final_project_w24.Recipe.SerachRecipe.SearchRecipeApiResponse;

public interface SearchRecipeResponseListener {
    void didFetch(SearchRecipeApiResponse response, String message);
    void didError(String message);
}
