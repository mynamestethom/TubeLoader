package com.example.tubeloader.Activityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.tubeloader.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;
import java.util.concurrent.TimeUnit;
import at.huber.youtubeExtractor.VideoMeta;
import at.huber.youtubeExtractor.YouTubeExtractor;
import at.huber.youtubeExtractor.YtFile;


public class InfoActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView author;
    private TextView title;
    private TextView duration;
    private TextView views;
    private TextView description;
    private FloatingActionButton view_video;
    private String videoinformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);


        view_video = findViewById(R.id.view_video);
        imageView = findViewById(R.id.web_view);
        callText();
        author = findViewById(R.id.author);
        title = findViewById(R.id.title);
        duration = findViewById(R.id.duration);
        views = findViewById(R.id.views);
        description = findViewById(R.id.description);

        Bundle argum = getIntent().getExtras();
        String ytlink = argum.get("link").toString();

        Picasso.with(getApplicationContext()).load(getImage(ytlink)).into(imageView);
        view_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoActivity.this, ViewVideoActivity.class);
                intent.putExtra("ytlink" , ytlink);
                startActivity(intent);
            }
        });
        getYoutubeDownloadUrl(ytlink);



    }

    private void getYoutubeDownloadUrl(String youtubeLink) {
        new YouTubeExtractor(this) {

            @Override
            public void onExtractionComplete(SparseArray<YtFile> ytFiles, VideoMeta vMeta) {
                videoinformation = "Об этом видео:\nАвтор: "+vMeta.getAuthor()+"\nНазвание: "+vMeta.getTitle()+"\nДлительность: "
                        + TimeUnit.SECONDS.toMinutes(vMeta.getVideoLength())+
                        "\nПросмотров: "+String.valueOf(vMeta.getViewCount())+"\nКраткое описание:"+"\n"+vMeta.getShortDescription();
                author.setText("Author: "+vMeta.getAuthor());
                title.setVisibility(View.VISIBLE);
                title.setText("Title: "+vMeta.getTitle());
                int minut = (int) vMeta.getVideoLength()/60;
                int remainingSeconds = (int)vMeta.getVideoLength()%60;
                if (minut == 0 ){
                    duration.setText("Video length: "+String.format("%d секунд", remainingSeconds));

                }else{
                    duration.setText("Video length: "+String.format("%d минуты %d секунд", minut, remainingSeconds));
                }
                duration.setVisibility(View.VISIBLE);
                views.setText("Views: "+String.valueOf( vMeta.getViewCount() ));
                views.setVisibility(View.VISIBLE);
                description.setText("Short description: "+vMeta.getShortDescription());
                description.setVisibility(View.VISIBLE);
            }
        }.extract(youtubeLink);
    }


    public static String getImage(String ytlink){
        String videoid;
        String [] link = ytlink.split("/");
        videoid = link[3];
        return "http://i1.ytimg.com/vi/" + videoid+ "/maxresdefault.jpg";
    }

    public void callText(){
        TextView author = findViewById(R.id.author);
        TextView title = findViewById(R.id.title);
        TextView duration = findViewById(R.id.duration);
        TextView views = findViewById(R.id.views);
        TextView description = findViewById(R.id.description);
    }

}
