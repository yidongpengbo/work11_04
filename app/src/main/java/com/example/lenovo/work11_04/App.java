package com.example.lenovo.work11_04;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);

        ImageLoaderConfiguration configuration=null;
        configuration=new ImageLoaderConfiguration.Builder(this)
                .memoryCacheSize(10)
                .diskCacheSize(50*1024*1024)
                .build();
        ImageLoader.getInstance().init(configuration);
    }
}
