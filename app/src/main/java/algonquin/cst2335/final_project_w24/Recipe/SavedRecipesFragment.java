package algonquin.cst2335.final_project_w24.Recipe;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import algonquin.cst2335.final_project_w24.R;
import algonquin.cst2335.final_project_w24.Recipe.SearchAdapters.SavedRecipesAdapter;

public class SavedRecipesFragment extends Fragment {

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment. Ensure the layout is correct here
        View view = inflater.inflate(R.layout.fragment_saved_recipes, container, false);
        recyclerView = view.findViewById(R.id.saved_recipes_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadSavedRecipes();
    }

    private void loadSavedRecipes() {
        // In SavedRecipesFragment, when loading recipes
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("SavedRecipes", MODE_PRIVATE);
        int recipeCount = sharedPreferences.getInt("RecipeCount", 0);
        List<SavedRecipe> savedRecipes = new ArrayList<>();
        for (int i = 0; i < recipeCount; i++) {
            String recipeData = sharedPreferences.getString("Recipe_" + i, null);
            if (recipeData != null) {
                String[] parts = recipeData.split(";");
                if (parts.length == 3) {
                    savedRecipes.add(new SavedRecipe(parts[0], parts[1], parts[2]));
                }
            }
        }

        // Define the listener
        SavedRecipesAdapter.OnItemClickListener listener = new SavedRecipesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SavedRecipe savedRecipe) {
                // Handle the item click event
                // For example, you might start a new Activity here or display a message
                Toast.makeText(getContext(), "Clicked on: " + savedRecipe.getTitle(), Toast.LENGTH_SHORT).show();
            }
        };
        SavedRecipesAdapter adapter = new SavedRecipesAdapter(savedRecipes, listener); // Assuming 'listener' is defined
        recyclerView.setAdapter(adapter);

    };


}
