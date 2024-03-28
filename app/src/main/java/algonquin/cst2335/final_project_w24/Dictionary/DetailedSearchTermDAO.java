package algonquin.cst2335.final_project_w24.Dictionary;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

public interface DetailedSearchTermDAO {
    @Insert
    public  long insertRecipe(DetailedSearchTerm w);
    @Query("Select * from DetailedSearchTerm")
    public List<DetailedSearchTerm> getAllRecipes();
    @Query("SELECT * FROM DetailedSearchTerm WHERE id = :id")
    DetailedSearchTerm getRecipeById(long id);

    @Delete
    void deleteRecipe(DetailedSearchTerm m);
}
