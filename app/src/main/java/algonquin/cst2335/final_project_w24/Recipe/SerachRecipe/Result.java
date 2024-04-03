package algonquin.cst2335.final_project_w24.Recipe.SerachRecipe;

public class Result {
    private int id;
    private String title;
    private String image;
    private Nutrition nutrition;

    // Getter methods
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public Nutrition getNutrition() {
        return nutrition;
    }

    // Setter methods
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setNutrition(Nutrition nutrition) {
        this.nutrition = nutrition;
    }
}

