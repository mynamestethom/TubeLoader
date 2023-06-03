package com.example.tubeloader.HistoryVideo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tubeloader.R;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>{
    private LayoutInflater layoutInflater;


    private Context context;
    private Fragment fragment;
    private List<Download_Videos> download_videos;
    private List<Download_Videos> newList;

    public HistoryAdapter(List<Download_Videos> download_videos , Fragment fragment, Context context){
        this.fragment = fragment;
        this.context = context;
        this.download_videos = download_videos;
        newList = new ArrayList<>();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item , parent , false);
        HistoryViewHolder personViewHolder = new HistoryViewHolder(view);
        return personViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        holder.title.setText("Название видеролика: "+download_videos.get(position).title+" ");
        holder.linkvideo.setText("Ссылка: "+download_videos.get(position).linkvideo);
    }

    @Override
    public int getItemCount() {
        return download_videos.size();
    }

    public static class HistoryViewHolder extends RecyclerView.ViewHolder{
        TextView linkvideo;
        TextView title;
        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            linkvideo = itemView.findViewById(R.id.linkvideo);
        }
    }
}
