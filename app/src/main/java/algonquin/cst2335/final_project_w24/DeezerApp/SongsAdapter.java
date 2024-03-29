package algonquin.cst2335.final_project_w24.DeezerApp;

//RecyclerView.Adapter responsible for populating
// each item in the RecyclerView

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import algonquin.cst2335.final_project_w24.R;

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.SongViewHolder>{

    private final List<TracklistResponse.Track> songs;
    private final LayoutInflater inflater;

    private final OnItemClickListener listener;

    public SongsAdapter(LayoutInflater inflater, List<TracklistResponse.Track> songs, OnItemClickListener listener) {
        this.inflater = inflater;
        this.songs = songs;
        this.listener = listener;
    }
    public void updateSongs(List<TracklistResponse.Track> newSongs) {
        songs.clear();
        songs.addAll(newSongs);
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(TracklistResponse.Track song);
    }


    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.song_item, parent, false);
        //return new SongViewHolder(itemView, scrollItem, scrollItem1);
        return new SongViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        TracklistResponse.Track song = songs.get(position);
        holder.songTitle.setText(song.getTitle());
        holder.albumTitle.setText(song.getAlbum().getTitle());
        Glide.with(holder.itemView.getContext()).load(song.getAlbum().getCover_small()).into(holder.songImage);

        // Set the click listener
        holder.scrollItem.setOnClickListener(v -> listener.onItemClick(song));
    }


    @Override
    public int getItemCount() {
        return songs.size();
    }


    static class SongViewHolder extends RecyclerView.ViewHolder {
        final ImageView songImage;
        final TextView songTitle;
        final TextView albumTitle;
        final ConstraintLayout scrollItem;

     //   SongViewHolder(View itemView, ConstraintLayout scrollItem, ConstraintLayout scrollItem1) {
          SongViewHolder(View itemView) {
                super(itemView);
            songImage = itemView.findViewById(algonquin.cst2335.final_project_w24.R.id.songImage);
            songTitle = itemView.findViewById(R.id.songTitle);
            albumTitle = itemView.findViewById(R.id.albumTitle);
//            this.scrollItem = scrollItem1;
            scrollItem = itemView.findViewById(R.id.scrollItem); // Find the scrollItem within itemView.

        }
    }

}
