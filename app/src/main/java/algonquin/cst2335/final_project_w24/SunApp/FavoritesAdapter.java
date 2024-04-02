package algonquin.cst2335.final_project_w24.SunApp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import algonquin.cst2335.final_project_w24.R;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {

    private List<FavoriteLocation> favoriteLocations;

    public FavoritesAdapter(List<FavoriteLocation> favoriteLocations) {
        this.favoriteLocations = favoriteLocations;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite_location, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FavoriteLocation location = favoriteLocations.get(position);
        holder.textViewFavoriteLocation.setText("Latitude: " + location.getLatitude() + ", Longitude: " + location.getLongitude());
    }

    @Override
    public int getItemCount() {
        return favoriteLocations.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewFavoriteLocation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewFavoriteLocation = itemView.findViewById(R.id.textViewFavoriteLocation);
        }
    }
}