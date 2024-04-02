package algonquin.cst2335.final_project_w24.Recipe.SearchAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import algonquin.cst2335.final_project_w24.R;
import algonquin.cst2335.final_project_w24.Recipe.Database.RecipeEntity;

public class SavedRecipesAdapter extends RecyclerView.Adapter<SavedRecipesAdapter.RecipeViewHolder> {

    private final List<RecipeEntity> savedRecipes;
    private final Context context;

    public SavedRecipesAdapter(List<RecipeEntity> savedRecipes, Context context) {
        this.savedRecipes = savedRecipes;
        this.context = context;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.saved_recipe_item, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        RecipeEntity recipe = savedRecipes.get(position);
        holder.textViewRecipeName.setText(recipe.getTitle());
        holder.textViewRecipeDescription.setText(recipe.getSummary());

        holder.buttonViewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle viewing details of the recipe
                // You can implement this according to your application's logic
            }
        });

        holder.buttonDeleteRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle deleting the recipe
                // You can implement this according to your application's logic
            }
        });
    }

    @Override
    public int getItemCount() {
        return savedRecipes.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {
        TextView textViewRecipeName;
        TextView textViewRecipeDescription;
        Button buttonViewDetails;
        Button buttonDeleteRecipe;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewRecipeName = itemView.findViewById(R.id.text_view_recipe_name);
            textViewRecipeDescription = itemView.findViewById(R.id.text_view_recipe_description);
            buttonViewDetails = itemView.findViewById(R.id.button_view_details);
            buttonDeleteRecipe = itemView.findViewById(R.id.button_delete_recipe);
        }
    }
}
