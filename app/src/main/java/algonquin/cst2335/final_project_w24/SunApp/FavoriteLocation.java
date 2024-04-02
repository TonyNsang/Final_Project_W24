package algonquin.cst2335.final_project_w24.SunApp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite_locations")
public class FavoriteLocation {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private double latitude;
    private double longitude;

    public FavoriteLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}