package com.example.lenovo.work11_04.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lenovo.work11_04.bean.PagerBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class Fragmet_ViewPager_Adapter extends PagerAdapter {
    private Context mContext;
    private PagerBean.DataBean mjihe;
    private Handler mHandler;
        //有参
    public Fragmet_ViewPager_Adapter(Context context, PagerBean.DataBean mjihe,Handler handler) {
        mContext = context;
        this.mjihe = mjihe;
        mHandler = handler;
    }


    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @SuppressLint("ClickableViewAccessibility")
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        String images = mjihe.getImages();
        String[] split = images.replace("https", "http").split("\\|");
        for (int i = 0; i <split.length ; i++) {
            ImageLoader.getInstance().displayImage(split[i],imageView);
        }
        Log.i("TAG","split="+split);
            imageView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()){
                            case MotionEvent.ACTION_DOWN:
                                mHandler.removeCallbacksAndMessages(null);
                                Toast.makeText(mContext,"按下",Toast.LENGTH_LONG).show();
                                break;
                            case MotionEvent.ACTION_UP:
                                mHandler.sendEmptyMessageDelayed(0,2000);
                                Toast.makeText(mContext,"抬起",Toast.LENGTH_LONG).show();
                                break;
                            default:
                                break;
                        }
                    return true;
                }
            });
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
