package com.example.tubeloader.HistoryVideo;

public class Download_Videos {
    String linkvideo;
    String title;
    String id;

    public Download_Videos(String linkvideo, String title , String id) {
        this.linkvideo = linkvideo;
        this.id = id;
        this.title = title;
    }

    public String getLinkvideo() {
        return linkvideo;
    }

    public String getTitle() {
        return title;
    }

    public void setLinkvideo(String linkvideo) {
        this.linkvideo = linkvideo;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
