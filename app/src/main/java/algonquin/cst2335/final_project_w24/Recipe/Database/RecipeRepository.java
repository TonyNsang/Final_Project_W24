package algonquin.cst2335.final_project_w24.Recipe.Database;
import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

public class RecipeRepository {
     final private RecipeDao recipeDao;
    private final List<RecipeEntity> allRecipes;

    public RecipeRepository(Application application) {
        RecipeRoomDatabase db = RecipeRoomDatabase.getDatabase(application);
        recipeDao = db.recipeDao();
        allRecipes = recipeDao.getAllRecipes();
    }

    public void insert(RecipeEntity recipe) {
        new InsertAsyncTask(recipeDao).execute(recipe);
    }

    public void delete(RecipeEntity recipe) {
        new DeleteAsyncTask(recipeDao).execute(recipe);
    }

    public List<RecipeEntity> getAllRecipes() {
        return allRecipes;
    }

    private static class InsertAsyncTask extends AsyncTask<RecipeEntity, Void, Void> {
        private final RecipeDao asyncTaskDao;

        InsertAsyncTask(RecipeDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final RecipeEntity... params) {
            asyncTaskDao.insertRecipe(params[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<RecipeEntity, Void, Void> {
        private final RecipeDao asyncTaskDao;

        DeleteAsyncTask(RecipeDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final RecipeEntity... params) {
            asyncTaskDao.deleteRecipe(params[0]);
            return null;
        }
    }
}

