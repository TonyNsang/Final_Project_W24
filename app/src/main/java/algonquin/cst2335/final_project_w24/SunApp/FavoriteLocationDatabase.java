package algonquin.cst2335.final_project_w24.SunApp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {FavoriteLocation.class}, version = 1)
public abstract class FavoriteLocationDatabase extends RoomDatabase {

    public abstract FavoriteLocationDAO lDAO();
}