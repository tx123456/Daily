package com.example.srdz.daily.Api;

import com.example.srdz.daily.modle.News;
import com.example.srdz.daily.modle.NewsDetail;
import com.example.srdz.daily.modle.Splash;
import com.example.srdz.daily.modle.ThemesList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by SRDZ on 2016/9/27.
 */
public interface ContentVaual {

    public static final String BASE_URL = "http://news-at.zhihu.com";
    public static final int TOPIC = 131;

    @GET("/api/4/start-image/1080*1776")
    Call<Splash> getsplash();

   // http://news-at.zhihu.com/api/4/news/latest
    @GET("/api/4/news/latest")
    Call<News> getnews();

    //http://news.at.zhihu.com/api/4/news/before/20131119
    @GET("/api/4/news/before/{date}")
    Call<News> getbefore(@Path("date") String date);

    @GET("/api/4/themes")
    Call<ThemesList> getThemesList();

    @GET("/api/4/theme/{id}")
    Call<News> getThemes(@Path("id") String id);

    //http://news-at.zhihu.com/api/4/news/3892357
    @GET("/api/4/news/{id}")
    Call<NewsDetail> getNewsDetail(@Path("id") String id);
}
