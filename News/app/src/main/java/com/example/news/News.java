package com.example.news;

public class News {
    private String Ntitle;
    private String Ntime;
    private String Nurl;

    public News(String title,String time,String url) {
        Ntitle=title;
        Ntime=time;
        Nurl=url;

    }



    public String getNtitle(){
        return Ntitle;

    }
    public String getNtime(){
        return Ntime;
    }

    public String getNurl(){
        return Nurl;
    }
}

