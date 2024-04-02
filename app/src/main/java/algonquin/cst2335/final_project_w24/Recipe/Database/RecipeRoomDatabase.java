package algonquin.cst2335.final_project_w24.Recipe.Database;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {RecipeEntity.class}, version = 1, exportSchema = false)
public abstract class RecipeRoomDatabase extends RoomDatabase {

    public abstract RecipeDao recipeDao();

    private static RecipeRoomDatabase INSTANCE;

    public static RecipeRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RecipeRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    RecipeRoomDatabase.class, "recipe_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

