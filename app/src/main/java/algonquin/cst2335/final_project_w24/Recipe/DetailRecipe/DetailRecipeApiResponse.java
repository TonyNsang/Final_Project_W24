package algonquin.cst2335.final_project_w24.Recipe.DetailRecipe;

import java.util.ArrayList;

public class DetailRecipeApiResponse {
    private boolean vegetarian;
    private boolean vegan;
    private boolean glutenFree;
    private boolean dairyFree;
    private boolean veryHealthy;
    private boolean cheap;
    private boolean veryPopular;
    private boolean sustainable;
    private boolean lowFodmap;
    private int weightWatcherSmartPoints;
    private String gaps;
    private int preparationMinutes;
    private int cookingMinutes;
    private int aggregateLikes;
    private int healthScore;
    private String creditsText;
    private String license;
    private String sourceName;
    private double pricePerServing;
    private ArrayList<ExtendedIngredient> extendedIngredients;

    public boolean isVegan() {
        return vegan;
    }

    public void setVegan(boolean vegan) {
        this.vegan = vegan;
    }

    private int id;
    private String title;
    private int readyInMinutes;
    private int servings;
    private String sourceUrl;
    private String image;
    private String imageType;
    private Taste taste;
    private String summary;
    private ArrayList<Object> cuisines;
    private ArrayList<String> dishTypes;
    private ArrayList<Object> diets;
    private ArrayList<Object> occasions;
    private WinePairing winePairing;
    private String instructions;
    private ArrayList<Object> analyzedInstructions;
    private Object originalId;
    private double spoonacularScore;
    private String spoonacularSourceUrl;

    // Getters and Setters
    public boolean isVegetarian() { return vegetarian; }
    public void setVegetarian(boolean vegetarian) { this.vegetarian = vegetarian; }
    // Add getters and setters for other fields...

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }

    public String getSourceUrl() { return sourceUrl; }
    public void setSourceUrl(String sourceUrl) { this.sourceUrl = sourceUrl; }

    public ArrayList<ExtendedIngredient> getExtendedIngredients() { return extendedIngredients; }
    public void setExtendedIngredients(ArrayList<ExtendedIngredient> extendedIngredients) { this.extendedIngredients = extendedIngredients; }


}
