package algonquin.cst2335.final_project_w24.DeezerApp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import algonquin.cst2335.final_project_w24.R;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder> {
    private List<Artist> artists = new ArrayList<>();

    public ArtistAdapter(List<Artist> artists) {
        this.artists = artists;
    }

    public static class ArtistViewHolder extends RecyclerView.ViewHolder {
        // Example view in ViewHolder
        public TextView textViewName;
        public ArtistViewHolder(View v) {
            super(v);
            textViewName = v.findViewById(R.id.artist_name_text_view);
        }
    }

    @Override
    public ArtistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View artistView = LayoutInflater.from(parent.getContext()).inflate(R.layout.artist_item, parent, false);
        return new ArtistViewHolder(artistView);
    }

    @Override
    public void onBindViewHolder(ArtistViewHolder holder, int position) {
        Artist artist = artists.get(position);
        holder.textViewName.setText(artist.getName());
    }

    @Override
    public int getItemCount() {
        return artists.size();
    }

    public void updateData(List<Artist> newArtists) {  // Method to update the data in the adapter

        artists.clear();
        artists.addAll(newArtists);
        notifyDataSetChanged();
    }
}