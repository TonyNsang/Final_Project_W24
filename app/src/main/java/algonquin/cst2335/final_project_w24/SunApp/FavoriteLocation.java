package algonquin.cst2335.final_project_w24.SunApp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite_locations")
public class FavoriteLocation {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String locationName;

    public FavoriteLocation(String locationName) {
        this.locationName = locationName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}