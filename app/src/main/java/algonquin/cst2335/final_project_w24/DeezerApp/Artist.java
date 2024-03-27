package algonquin.cst2335.final_project_w24.DeezerApp;
public class Artist {
    private long id;
    private String name;
    private String link;
    private String picture;
    private String pictureSmall;
    private String pictureMedium;
    private String pictureBig;
    private String pictureXl;
    private int nbAlbum;
    private int nbFan;
    private boolean radio;
    private String tracklist;
    private String type;

    // Constructor
    public Artist(long id, String name, String link, String picture, String pictureSmall,
                  String pictureMedium, String pictureBig, String pictureXl, int nbAlbum,
                  int nbFan, boolean radio, String tracklist, String type) {
        this.id = id;
        this.name = name;
        this.link = link;
        this.picture = picture;
        this.pictureSmall = pictureSmall;
        this.pictureMedium = pictureMedium;
        this.pictureBig = pictureBig;
        this.pictureXl = pictureXl;
        this.nbAlbum = nbAlbum;
        this.nbFan = nbFan;
        this.radio = radio;
        this.tracklist = tracklist;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPictureSmall() {
        return pictureSmall;
    }

    public void setPictureSmall(String pictureSmall) {
        this.pictureSmall = pictureSmall;
    }

    public String getPictureMedium() {
        return pictureMedium;
    }

    public void setPictureMedium(String pictureMedium) {
        this.pictureMedium = pictureMedium;
    }

    public String getPictureBig() {
        return pictureBig;
    }

    public void setPictureBig(String pictureBig) {
        this.pictureBig = pictureBig;
    }

    public String getPictureXl() {
        return pictureXl;
    }

    public void setPictureXl(String pictureXl) {
        this.pictureXl = pictureXl;
    }

    public int getNbAlbum() {
        return nbAlbum;
    }

    public void setNbAlbum(int nbAlbum) {
        this.nbAlbum = nbAlbum;
    }

    public int getNbFan() {
        return nbFan;
    }

    public void setNbFan(int nbFan) {
        this.nbFan = nbFan;
    }

    public boolean isRadio() {
        return radio;
    }

    public void setRadio(boolean radio) {
        this.radio = radio;
    }

    public String getTracklist() {
        return tracklist;
    }

    public void setTracklist(String tracklist) {
        this.tracklist = tracklist;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
