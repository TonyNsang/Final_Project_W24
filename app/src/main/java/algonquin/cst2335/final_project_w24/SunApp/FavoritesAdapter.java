package algonquin.cst2335.final_project_w24.SunApp;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import algonquin.cst2335.final_project_w24.R;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.LocationViewHolder> {

    private List<FavoriteLocation> locations = new ArrayList<>();

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_favorite_location, parent, false);
        return new LocationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, int position) {
        FavoriteLocation location = locations.get(position);
        holder.latitudeTextView.setText(location.getLatitude());
        holder.longitudeTextView.setText(location.getLongitude());
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    public void setLocations(List<FavoriteLocation> locations) {
        this.locations = locations;
        notifyDataSetChanged();
    }

    class LocationViewHolder extends RecyclerView.ViewHolder {

        private TextView latitudeTextView;
        private TextView longitudeTextView;

        public LocationViewHolder(@NonNull View itemView) {
            super(itemView);
            latitudeTextView = itemView.findViewById(R.id.latitudeTextView);
            longitudeTextView = itemView.findViewById(R.id.longitudeTextView);
        }
    }
}