package algonquin.cst2335.final_project_w24.SunApp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavoriteLocationDAO {

    @Query("SELECT * FROM favorite_locations")
    List<FavoriteLocation> getAll();

    @Insert
    void insert(FavoriteLocation favoriteLocation);

    @Delete
    void delete(FavoriteLocation favoriteLocation);
}