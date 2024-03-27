package algonquin.cst2335.final_project_w24.Recipe;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

import algonquin.cst2335.final_project_w24.R;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder> {

    private List<Recipe> recipesList;
    private View.OnClickListener onItemClickListener;

    // Constructor
    public RecipesAdapter(List<Recipe> recipesList) {
        this.recipesList = recipesList;
        this.onItemClickListener = onItemClickListener;
    }

    // ViewHolder class
    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public ImageView imageView;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    // Create new views
    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item, parent, false);
        itemView.setOnClickListener(onItemClickListener); // Set the listener to the view
        return new RecipeViewHolder(itemView);
    }

    // Replace the contents of a view
    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe recipe = recipesList.get(position);
        holder.titleTextView.setText(recipe.getTitle());
        Glide.with(holder.itemView.getContext()).load(recipe.getImage()).into(holder.imageView);

        holder.itemView.setTag(recipe); // Set the current Recipe as a tag for the view
    }

    // Return the size of the dataset
    @Override
    public int getItemCount() {
        return recipesList.size();
    }

    // Update the list of recipes and notify the adapter to refresh
    public void setRecipes(List<Recipe> newRecipesList) {
        recipesList = newRecipesList;
        notifyDataSetChanged();
    }
}
