package algonquin.cst2335.final_project_w24.DeezerApp;
public class Song {
    private String title;
    private String albumName;
    private int duration;
    private String coverImageUrl;

    // Constructor
    public Song(String title, String albumName, int duration, String coverImageUrl) {
        this.title = title;
        this.albumName = albumName;
        this.duration = duration;
        this.coverImageUrl = coverImageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public String getFormattedDuration() {
        int minutes = duration / 60;
        int seconds = duration % 60;
        return String.format("%d:%02d", minutes, seconds);

    }
}
