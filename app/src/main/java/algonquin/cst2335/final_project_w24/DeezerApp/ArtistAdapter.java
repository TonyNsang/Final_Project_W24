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
    private OnArtistClickListener listener;

    public ArtistAdapter(List<Artist> artists,OnArtistClickListener listener) {
        this.artists = artists;//Assign the passed list of artists to field
        this.listener = listener;//Assign the passed listener to field

    }

//    public static class ArtistViewHolder extends RecyclerView.ViewHolder {
//        // Example view in ViewHolder
//        public TextView textViewName;
//        public ArtistViewHolder(View view, final OnArtistClickListener listener) {
//            super(view);
//            textViewName = view.findViewById(R.id.artist_name_text_view);
//
//            view.setOnClickListener(v -> {//click listener for the entire view holder's view
//
//                @Override
//            //    public void onClick(View v) {
//                    if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
//                        listener.onArtistClick(artists.get(getAdapterPosition()));
//                    }
//            //    }
//            });
//        }
//    }

    @Override
    public ArtistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View artistView = LayoutInflater.from(parent.getContext()).inflate(R.layout.artist_item, parent, false);
        //return new ArtistViewHolder(artistView, listener);//Pass the artistView and listener to the ViewHolder
        return new ArtistViewHolder(artistView);
    }

    @Override
    public void onBindViewHolder(ArtistViewHolder holder, int position) {
        Artist artist = artists.get(position);
        holder.textViewName.setText(artist.getName());

        holder.itemView.setOnClickListener(v -> { //Set the click listener here, where i have access to both the artist and position
            if (listener != null) {
                listener.onArtistClick(artist);
            }
        });
    }

    @Override
    public int getItemCount() {
        return artists.size();
    }

    // ViewHolder class
    public static class ArtistViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;

        public ArtistViewHolder(View view) {
            super(view);
            textViewName = view.findViewById(R.id.artist_name_text_view);
        }
    }

    public void updateData(List<Artist> newArtists) {  // Method to update the data in the adapter

        artists.clear();
        artists.addAll(newArtists);
        notifyDataSetChanged();
    }
    public interface OnArtistClickListener {
        void onArtistClick(Artist artist);
    }
}
