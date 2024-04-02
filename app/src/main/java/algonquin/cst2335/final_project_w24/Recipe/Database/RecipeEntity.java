package algonquin.cst2335.final_project_w24.Recipe.Database;
import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "recipes")
public class RecipeEntity {
    @PrimaryKey
    @NonNull
    private int id; // Unique identifier for the recipe

    private String title; // Title of the recipe
    private String imageUrl; // URL of the recipe's image
    private String summary; // Summary of the recipe
    private String sourceUrl; // Source URL of the recipe

    // Constructor
    public RecipeEntity(int id, String title, String imageUrl, String summary, String sourceUrl) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.summary = summary;
        this.sourceUrl = sourceUrl;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }
}

