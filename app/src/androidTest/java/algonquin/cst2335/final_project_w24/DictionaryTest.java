package algonquin.cst2335.final_project_w24;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Toast;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class DictionaryTest {

    private SharedPreferences sharedPreferences;

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        sharedPreferences = context.getSharedPreferences("MyData", Context.MODE_PRIVATE);
    }

    @After
    public void tearDown() {
        // Clear saved search term
        sharedPreferences.edit().remove("last_search_term").apply();
    }

    @Test
    public void testSaveWordAndDefinitions() {
        // Enter a word
        onView(withId(R.id.searchText)).perform(typeText("example"));

        // Click on the Search button
        onView(withId(R.id.searchButton)).perform(click());

        // Click on the Save button
        onView(withId(R.id.save)).perform(click());

        // Check if the word is saved in SharedPreferences
        String savedSearchTerm = sharedPreferences.getString("last_search_term", "");
        assertEquals("example", savedSearchTerm);
    }

    @Test
    public void testRetrieveSavedWords() {
        // Set a saved search term in SharedPreferences
        sharedPreferences.edit().putString("last_search_term", "example").apply();

        // Click on the Saved Items button
        onView(withId(R.id.savedButton)).perform(click());

        // Check if the dialog is displayed
        onView(withText("Saved Search Terms")).check(matches(isDisplayed()));
    }

    @Test
    public void testClearAllSavedDefinitions() {
        // Set a saved search term in SharedPreferences
        sharedPreferences.edit().putString("last_search_term", "example").apply();

        // Click on the Saved Items button
        onView(withId(R.id.savedButton)).perform(click());

        // Click on the Clear All button
        onView(withText("Clear All")).perform(click());

        // Check if the saved search term is cleared from SharedPreferences
        String savedSearchTerm = sharedPreferences.getString("last_search_term", "");
        assertTrue(savedSearchTerm.isEmpty());
    }

    @Test
    public void testFetchDefinitionsFromAPI() {
        // Enter a valid word
        onView(withId(R.id.searchText)).perform(typeText("example"));

        // Click on the Search button
        onView(withId(R.id.searchButton)).perform(click());

        // Check if the RecyclerView is displayed with definitions
        onView(withId(R.id.dictionaryView)).check(matches(isDisplayed()));
    }

//    @Test
//    public void testErrorHandlingInvalidWord() {
//        // Enter an invalid word
//        onView(withId(R.id.searchText)).perform(typeText("invalidword"));
//
//        // Click on the Search button
//        onView(withId(R.id.searchButton)).perform(click());
//
//        // Check if the toast message is displayed
//        onView(withText("Sorry word does not exist")).inRoot(new ToastMatcher())
//                .check(matches(isDisplayed()));
//
//
//    }
}

