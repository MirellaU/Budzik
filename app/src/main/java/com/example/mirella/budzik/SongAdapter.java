package com.example.mirella.budzik;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mirella on 02.01.2018.
 */

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {

    private ArrayList<Song> songs = new ArrayList<>();
    private OnItemClickListener listener;

    SongAdapter(ArrayList<Song> songs) {
        this.songs = songs;
    }

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.song, null);
        return new ViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setSongArtist(songs.get(position).getArtist());
        holder.setSongTitle(songs.get(position).getTitle());
        holder.getItemId();
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView songTitle;
        TextView songArtist;

        public ViewHolder(final View itemView) {
            super(itemView);
            songTitle = (TextView) itemView.findViewById(R.id.song_title);
            songTitle = (TextView) itemView.findViewById(R.id.song_artist);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    listener.onItemClick(itemView, position);
                }
            });
        }

        private void setSongTitle(String name) {
            songTitle.setText(name);
        }
        private void setSongArtist(String name) {
            songArtist.setText(name);
        }


        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
        }
    }
}