package algonquin.cst2335.final_project_w24.Recipe;

public class SavedRecipe {
    private long id; // Adding ID field
    private String title;
    private String summary;
    private String sourceUrl;

    public SavedRecipe(String title, String summary, String sourceUrl) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.sourceUrl = sourceUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
