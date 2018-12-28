package com.example.lenovo.work11_04.until;

import android.database.Observable;

import com.example.lenovo.work11_04.bean.NewsBean;
import com.example.lenovo.work11_04.bean.PagerBean;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface Api {
    @GET("searchProducts?keywords=手机&page=1")
        //NewsBean:网址中的数据解析的类
    Call<NewsBean> getcall();
    @POST
    Call<PagerBean> post(@Url String url, @QueryMap Map<String, String> map);
    @POST
    Call<NewsBean> post2(@Url String url, @QueryMap Map<String, String> map);

}
