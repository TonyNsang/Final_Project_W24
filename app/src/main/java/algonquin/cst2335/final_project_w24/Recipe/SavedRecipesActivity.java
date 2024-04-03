package algonquin.cst2335.final_project_w24.Recipe;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import algonquin.cst2335.final_project_w24.R;

public class SavedRecipesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saved_recipe_item);

        if (savedInstanceState == null) { // Check to prevent overlapping fragments on re-creation
            // Create an instance of the SavedRecipesFragment
            SavedRecipesFragment savedRecipesFragment = new SavedRecipesFragment();


            // Begin a transaction to add the fragment to the container
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragment_container, savedRecipesFragment);
            transaction.commit(); // Commit the transaction
        }
    }
}
