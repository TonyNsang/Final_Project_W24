package algonquin.cst2335.final_project_w24;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;


import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Rule;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;


import algonquin.cst2335.final_project_w24.DeezerApp.TrackFavoriteDetails;

@RunWith(AndroidJUnit4.class)
public class FavoritesTest {

    private SharedPreferences sharedPreferences;
    private Context context;

    @Before
    public void setUp() {
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        sharedPreferences = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
    }

    @Test
    public void saveAndLoadFavorites() {
        // Prepare data
        TrackFavoriteDetails favorite = new TrackFavoriteDetails("Like That", "Album", "267", "https://example.com/cover.jpg");
        List<TrackFavoriteDetails> favoritesList = new ArrayList<>();
        favoritesList.add(favorite);

        // Save the favorite list to SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(favoritesList);
        editor.putString("favorites", json);
        editor.apply();

        // Load the favorite list from SharedPreferences
        String loadedJson = sharedPreferences.getString("favorites", null);
        Type type = new TypeToken<ArrayList<TrackFavoriteDetails>>() {}.getType();
        List<TrackFavoriteDetails> loadedFavoritesList = gson.fromJson(loadedJson, type);

        // Verify
        assertNotNull(loadedFavoritesList);
        assertFalse(loadedFavoritesList.isEmpty());
        assertEquals(favoritesList.size(), loadedFavoritesList.size());
        assertEquals(favoritesList.get(0).getSongTitle(), loadedFavoritesList.get(0).getSongTitle());
    }

    @After
    public void tearDown() {
        sharedPreferences.edit().clear().apply();
    }
}