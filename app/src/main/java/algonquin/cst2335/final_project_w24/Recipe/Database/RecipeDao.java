package algonquin.cst2335.final_project_w24.Recipe.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface RecipeDao {
    @Query("SELECT * FROM recipes")
    List<RecipeEntity> getAllRecipes();

    @Insert
    void insertRecipe(RecipeEntity recipe);

    @Delete
    void deleteRecipe(RecipeEntity recipe);


}

