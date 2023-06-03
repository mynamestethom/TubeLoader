package com.example.tubeloader.Fragment;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tubeloader.HistoryVideo.DatabaseHelper;
import com.example.tubeloader.HistoryVideo.Download_Videos;
import com.example.tubeloader.HistoryVideo.HistoryAdapter;
import com.example.tubeloader.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class HistoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Download_Videos> videoDownloadeds;
    private DatabaseHelper database;
    private FloatingActionButton removeAllHistory;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_history, container, false);

        removeAllHistory = view.findViewById(R.id.removeAllHistory);

        recyclerView = view.findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(false);


        removeAllHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.deleteAllHistory();
                videoDownloadeds = new ArrayList<>();
                fetchAllHistory();
                recyclerView.setAdapter(new HistoryAdapter(videoDownloadeds, new HistoryFragment() , getContext()));
            }
        });



        videoDownloadeds = new ArrayList<>();

        database = new DatabaseHelper(getContext());

        initializeAdapter();
        fetchAllHistory();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        videoDownloadeds = new ArrayList<>();
        fetchAllHistory();
        recyclerView.setAdapter(new HistoryAdapter(videoDownloadeds, new HistoryFragment() , getContext()));
    }

    public void fetchAllHistory(){
        Cursor cursor = database.readHistory();

        if (cursor.getCount() == 0) {
        } else {
            while (cursor.moveToNext()){
                videoDownloadeds.add(new Download_Videos(cursor.getString(2), cursor.getString(1), cursor.getString(0)));
            }
        }
    }

    public void initializeAdapter(){
        HistoryFragment historyFragment = new HistoryFragment();
        HistoryAdapter historyAdapter = new HistoryAdapter(videoDownloadeds , historyFragment , getContext());
        recyclerView.setAdapter(historyAdapter);
  }
}