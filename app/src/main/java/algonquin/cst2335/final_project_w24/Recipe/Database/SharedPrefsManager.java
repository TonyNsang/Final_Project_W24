package algonquin.cst2335.final_project_w24.Recipe.Database;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefsManager {
    private static final String PREF_NAME = "RecipeSearchPrefs";
    private static final String KEY_SEARCH_TERM = "searchTerm";

    public static void saveSearchTerm(Context context, String searchTerm) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(KEY_SEARCH_TERM, searchTerm);
        editor.apply();
    }

    public static String getSearchTerm(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return prefs.getString(KEY_SEARCH_TERM, "");
    }
}

