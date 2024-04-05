package algonquin.cst2335.final_project_w24.Recipe.SearchAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import algonquin.cst2335.final_project_w24.R;
import algonquin.cst2335.final_project_w24.Recipe.DetailRecipe.DetailRecipeApiResponse;

public class DetailRecipeAdapter extends RecyclerView.Adapter<DetailRecipeAdapter.DetailRecipeViewHolder> {

    private final Context context;
    private final DetailRecipeApiResponse detailRecipe;

    public DetailRecipeAdapter(Context context, DetailRecipeApiResponse detailRecipe) {
        this.context = context;
        this.detailRecipe = detailRecipe;
    }

    @NonNull
    @Override
    public DetailRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Ensure the layout used here is suitable for displaying a single item's details.
        View view = LayoutInflater.from(context).inflate(R.layout.activity_recipe_detail, parent, false);
        return new DetailRecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailRecipeViewHolder holder, int position) {
        holder.bind(detailRecipe);
    }

    @Override
    public int getItemCount() {
        return 1; // Since there is only one detail recipe
    }

    static class DetailRecipeViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewSummary, textViewSourceUrl;

        DetailRecipeViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.recipeTitleTextView);
            textViewSummary = itemView.findViewById(R.id.recipeSummaryTextView);
            textViewSourceUrl = itemView.findViewById(R.id.recipeSourceUrlTextView);
        }

        void bind(DetailRecipeApiResponse detailRecipe) {
            // Bind data to views
            textViewTitle.setText(detailRecipe.getTitle());
            textViewSummary.setText(detailRecipe.getSummary());
            textViewSourceUrl.setText(detailRecipe.getSourceUrl());
        }
    }
}
