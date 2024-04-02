package algonquin.cst2335.final_project_w24.Recipe.SearchAdapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import algonquin.cst2335.final_project_w24.R;
import algonquin.cst2335.final_project_w24.Recipe.SavedRecipe;

public class SavedRecipesAdapter extends RecyclerView.Adapter<SavedRecipesAdapter.ViewHolder> {

    private List<SavedRecipe> savedRecipes;
    private OnItemClickListener listener;

    public SavedRecipesAdapter(List<SavedRecipe> savedRecipes, OnItemClickListener listener) {
        this.savedRecipes = savedRecipes;
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(SavedRecipe savedRecipe);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.saved_recipe_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SavedRecipe savedRecipe = savedRecipes.get(position);
        holder.bind(savedRecipe, listener);
    }

    @Override
    public int getItemCount() {
        return savedRecipes.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView;
        private TextView summaryTextView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.text_view_recipe_name);
            summaryTextView = itemView.findViewById(R.id.text_view_recipe_description);
        }

        void bind(final SavedRecipe savedRecipe, final OnItemClickListener listener) {
            titleTextView.setText(savedRecipe.getTitle());
            summaryTextView.setText(savedRecipe.getSummary());

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemClick(savedRecipe);
                }
            });
        }
    }
}
