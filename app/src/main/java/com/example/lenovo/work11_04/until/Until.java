package com.example.lenovo.work11_04.until;

import android.util.Log;

import com.example.lenovo.work11_04.bean.NewsBean;
import com.example.lenovo.work11_04.bean.PagerBean;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Until {
    /**
     * 1.单例
     */
    private static Until instance;
    private Api mApi;
    private final Retrofit mRetrofit;
    private final OkHttpClient mClient;

    public static Until getInstance(){
        if (instance==null){
            instance=new Until();
        }
        return instance;
    }
    private Until(){
        //2.日志拦截器
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //3.客户端client
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(15, TimeUnit.SECONDS);
        builder.writeTimeout(15, TimeUnit.SECONDS);
        builder.addInterceptor(interceptor);
        builder.retryOnConnectionFailure(true);
        mClient = builder.build();
        //4.retrofit
        String path="http://www.zhaoapi.cn/product/";
        mRetrofit = new Retrofit.Builder()
                .baseUrl(path)
                .client(mClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    /**
     * get请求
     */
    public void getEnque(){
         mApi = mRetrofit.create(Api.class);
        Call<NewsBean> call=mApi.getcall();
        //异步
        call.enqueue(new Callback<NewsBean>() {
            @Override   //成功
            public void onResponse(Call<NewsBean> call, Response<NewsBean> response) {
                List<NewsBean.DataBean> dataBeans = response.body().getData();
                //将值传入
                EventBus.getDefault().post(dataBeans);
            }

            @Override   //失败
            public void onFailure(Call<NewsBean> call, Throwable t) {

            }
        });
    }
    /**
     * post请求
     */

    public void postEnque(Map<String,String>map,String path){
        Call<PagerBean> call = mApi.post(path, map);
        call.enqueue(new Callback<PagerBean>() {
            @Override
            public void onResponse(Call<PagerBean> call, Response<PagerBean> response) {
                PagerBean.DataBean dataBean = response.body().getData();
                //传值
                EventBus.getDefault().post(dataBean);
            }

            @Override
            public void onFailure(Call<PagerBean> call, Throwable t) {

            }
        });

    }
    /**
     * post异步
     */
    public void post2(Map<String,String>map,String path){
        Call<NewsBean> newsBeanCall = mApi.post2(path, map);
        newsBeanCall.enqueue(new Callback<NewsBean>() {
            @Override
            public void onResponse(Call<NewsBean> call, Response<NewsBean> response) {
                List<NewsBean.DataBean> data = response.body().getData();
                EventBus.getDefault().post(data);
            }

            @Override
            public void onFailure(Call<NewsBean> call, Throwable t) {

            }
        });
    }


}
