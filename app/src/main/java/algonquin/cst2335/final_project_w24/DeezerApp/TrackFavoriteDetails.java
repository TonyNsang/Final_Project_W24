package algonquin.cst2335.final_project_w24.DeezerApp;
public class TrackFavoriteDetails {
    private String songTitle;
    private String albumName;
    private String duration;
    private String albumCoverUrl;

    public TrackFavoriteDetails(String songTitle, String albumName, String duration, String albumCoverUrl) {
        this.songTitle = songTitle;
        this.albumName = albumName;
        this.duration = duration;
        this.albumCoverUrl = albumCoverUrl;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getAlbumCoverUrl() {
        return albumCoverUrl;
    }

    public void setAlbumCoverUrl(String albumCoverUrl) {
        this.albumCoverUrl = albumCoverUrl;
    }
}
