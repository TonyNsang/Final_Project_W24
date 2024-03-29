package algonquin.cst2335.final_project_w24.DeezerApp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import algonquin.cst2335.final_project_w24.R;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoriteViewHolder> {

    private List<TrackFavoriteDetails> favoritesList;
    private OnDeleteListener deleteListener;

    public FavoritesAdapter(List<TrackFavoriteDetails> favoritesList, OnDeleteListener deleteListener) {
        this.favoritesList = favoritesList;
        this.deleteListener = deleteListener;
    }

    public void setFavoritesList(List<TrackFavoriteDetails> favoritesList) {
        this.favoritesList = favoritesList;
        notifyDataSetChanged(); // Notify adapter about the data change
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_item2, parent, false);
        return new FavoriteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        TrackFavoriteDetails favorite = favoritesList.get(position);

        holder.songTitleTextView.setText(favorite.getSongTitle());
        holder.albumTitleTextView.setText(favorite.getAlbumName());

        // Load image using Glide
        Glide.with(holder.itemView.getContext())
                .load(favorite.getAlbumCoverUrl())
                .into(holder.songImageView);

        holder.deleteButton.setOnClickListener(v -> {
            deleteListener.onDelete(position);
        });
    }

    @Override
    public int getItemCount() {
        return favoritesList.size();
    }

    public interface OnDeleteListener {
        void onDelete(int position);
    }

    static class FavoriteViewHolder extends RecyclerView.ViewHolder {
        ImageView songImageView;
        TextView songTitleTextView;
        TextView albumTitleTextView;
        Button deleteButton;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            songImageView = itemView.findViewById(R.id.songImage);
            songTitleTextView = itemView.findViewById(R.id.songTitle);
            albumTitleTextView = itemView.findViewById(R.id.albumTitle);
            deleteButton = itemView.findViewById(R.id.button);
        }
    }
}
