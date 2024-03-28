package algonquin.cst2335.final_project_w24.Recipe;
public class Recipe {
        private int id;
        private String title;
        private String image;
        private String sourceUrl;

        // Constructor
        public Recipe(int id, String title, String image, String sourceUrl) {
            this.id = id;
            this.title = title;
            this.image = image;
            this.sourceUrl = sourceUrl;
        }

        // Getters and Setters for each field
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
            if (title != null) {
                this.title = title;
            }
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            if (image != null) {
                this.image = image;
            }
        }

        public String getSourceUrl() {
            return sourceUrl;
        }

        public void setSourceUrl(String sourceUrl) {
            if (sourceUrl != null) {
                this.sourceUrl = sourceUrl;
            }
        }
    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", sourceUrl='" + sourceUrl + '\'' +
                '}';
    }
    }

