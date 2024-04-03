package algonquin.cst2335.final_project_w24.Recipe.SerachRecipe;

import java.util.ArrayList;

public class SearchRecipeApiResponse {
    public ArrayList<Result> results;
    public int offset;
    public int number;
    public int totalResults;
        // Getter for the list of result objects
        public ArrayList<Result> getResults() {
            return results;
        }

        // Setter for the list of result objects
        public void setResults(ArrayList<Result> results) {
            this.results = results;
        }

        // Getter for offset
        public int getOffset() {
            return offset;
        }

        // Setter for offset
        public void setOffset(int offset) {
            this.offset = offset;
        }

        // Getter for number
        public int getNumber() {
            return number;
        }

        // Setter for number
        public void setNumber(int number) {
            this.number = number;
        }

        // Getter for total results
        public int getTotalResults() {
            return totalResults;
        }

        // Setter for total results
        public void setTotalResults(int totalResults) {
            this.totalResults = totalResults;
        }
    }

