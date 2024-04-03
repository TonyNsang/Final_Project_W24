package algonquin.cst2335.final_project_w24.Recipe.SearchAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import algonquin.cst2335.final_project_w24.R;
import algonquin.cst2335.final_project_w24.Recipe.SerachRecipe.Result;

public class SearchRecipeAdapter extends RecyclerView.Adapter<SearchRecipeAdapter.SearchRecipeViewHolder> {

    private final Context context;
    private final List<Result> results;
    private final OnItemClickListener listener;

    // Interface for handling clicks
    public interface OnItemClickListener {
        void onItemClick(Result item);
    }

    // Main constructor
    public SearchRecipeAdapter(Context context, List<Result> results, OnItemClickListener listener) {
        this.context = context;
        this.results = results;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SearchRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_recipe, parent, false);
        return new SearchRecipeViewHolder(view); // The listener will be set in bind method
    }

    @Override
    public void onBindViewHolder(@NonNull SearchRecipeViewHolder holder, int position) {
        Result result = results.get(position);
        holder.bind(result, listener); // Bind the result to the holder along with the click listener
    }

    @Override
    public int getItemCount() {
        return results != null ? results.size() : 0; // Guard against null
    }

    static class SearchRecipeViewHolder extends RecyclerView.ViewHolder {
        TextView textView_title;
        ImageView imageView_food;

        SearchRecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_title = itemView.findViewById(R.id.textView_title);
            imageView_food = itemView.findViewById(R.id.imageView_food);
        }

        void bind(Result result, OnItemClickListener listener) {
            textView_title.setText(result.getTitle());
            Picasso.get().load(result.getImage()).into(imageView_food);
            itemView.setOnClickListener(v -> {
                // Call onItemClick with the current Result item
                if(listener != null) {
                    listener.onItemClick(result);
                }
            });
        }
    }
}
