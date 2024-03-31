package algonquin.cst2335.final_project_w24.Recipe.Listeners;

import algonquin.cst2335.final_project_w24.Recipe.Models.RandomRecipeApiResponse;

public interface RandomRecipeResponseListener {

    void didError(String message);
    void didFetch(RandomRecipeApiResponse response, String message);
}

