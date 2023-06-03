package com.example.tubeloader.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tubeloader.Activityapp.DownloadActivity;
import com.example.tubeloader.Activityapp.InfoActivity;
import com.example.tubeloader.R;


public class HomeFragment extends Fragment {


    private Button download_video;
    private Button info_video;
    private EditText ytlinkvideo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        download_video = view.findViewById(R.id.download_video);
        info_video = view.findViewById(R.id.info_video);
        ytlinkvideo = view.findViewById(R.id.ytlinkvideo);


        download_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ytLink = ytlinkvideo.getText().toString();
                if (ytLink != null && (ytLink.contains("://youtu.be/") || ytLink.contains("youtube.com/watch?v="))) {
                    Intent intent = new Intent(getContext(), DownloadActivity.class);
                    intent.putExtra("link" , ytLink);
                    startActivity(intent);
                } else {
                    Toast.makeText(getContext() , "Write corect url video please" , Toast.LENGTH_SHORT).show();
                }
            }
        });

        info_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ytLink = ytlinkvideo.getText().toString();
                if (ytLink != null && (ytLink.contains("://youtu.be/") || ytLink.contains("youtube.com/watch?v="))) {
                    Intent intent = new Intent(getContext(), InfoActivity.class);
                    intent.putExtra("link" , ytLink);
                    startActivity(intent);
                } else {
                    Toast.makeText(getContext() , "Write corect url video please" , Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

}