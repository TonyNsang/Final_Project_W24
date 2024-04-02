package algonquin.cst2335.final_project_w24.SunApp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {FavoriteLocation.class}, version = 1)
public abstract class FavoriteLocationDatabase extends RoomDatabase {

    private static volatile FavoriteLocationDatabase INSTANCE;

    public abstract FavoriteLocationDAO favoriteLocationDao();

    public static FavoriteLocationDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (FavoriteLocationDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    FavoriteLocationDatabase.class, "favorite_location_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}