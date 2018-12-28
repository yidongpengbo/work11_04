package com.example.lenovo.work11_04.fragment;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.lenovo.work11_04.R;
import com.example.lenovo.work11_04.adapter.Fragmet_ViewPager_Adapter;
import com.example.lenovo.work11_04.bean.NewsBean;
import com.example.lenovo.work11_04.bean.PagerBean;
import com.example.lenovo.work11_04.until.Until;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Fragment_Shop extends Fragment {
            @BindView(R.id.details_ViewPager)
            ViewPager details_ViewPager;
            @BindView(R.id.details_Title)
            TextView  details_Title;
            @BindView(R.id.details_Price)
            TextView details_Price;
            @BindView(R.id.Button_Title)
            Button Button_Title;
            @BindView(R.id.Button_Price)
            Button Button_Price;

    private Fragmet_ViewPager_Adapter mPagerAdapter;
    private int mI;
    private PagerBean.DataBean mDataBean1=null;
    private String mTitle;
    private double mPrice;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view=inflater.inflate(R.layout.fragment_details,container,false);
        ButterKnife.bind(this,view);
        initData();
        //标题事件
        inidButtonT();
        //价格事件
        initPrice();
        return view;
    }

    /**
     * 价格转移
     */
    @OnClick(R.id.Button_Price)
    public void initPrice() {
       // EventBus.getDefault().post(mPrice);
   }

    /**
     * 标题转移
     */
    @OnClick(R.id.Button_Title)
    public void inidButtonT() {
        //TODO:mTitle---null???
        //EventBus.getDefault().post(mTitle);
        String title = details_Title.getText().toString();
        EventBus.getDefault().post(title);
    }

    /**
     * 请求网络数据
     */
    private void initData() {
        EventBus.getDefault().register(this);
            String path="http://www.zhaoapi.cn/product/getProductDetail";
        HashMap<String, String> map = new HashMap<>();
        map.put("pid",1+"");
        Until.getInstance().postEnque(map,path);

    }

    /**
     * 得到数据
     * @param dataBean
     */
        @Subscribe
    public void onEventBus(PagerBean.DataBean dataBean){
            //展示图片
            initPager(dataBean);
            //标题：
            //TODO:得到标题
            mTitle = dataBean.getTitle();
            details_Title.setText(mTitle);
            details_Title.setTextColor(Color.GRAY);
            //价格:
            mPrice = dataBean.getPrice();
            details_Price.setText("¥"+mPrice);
            details_Price.setTextColor(Color.RED);
    }

    //ViewPager
    @SuppressLint("HandlerLeak")
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int i = msg.what;
                i++;
                details_ViewPager.setCurrentItem(i);
                mHandler.sendEmptyMessageDelayed(i,2000);

        }
    };
    private void initPager(PagerBean.DataBean dataBean) {
        mPagerAdapter = new Fragmet_ViewPager_Adapter(getActivity(), dataBean,mHandler);
        details_ViewPager.setAdapter(mPagerAdapter);
        //轮播
        int count = mPagerAdapter.getCount();
        mI = count % count;
        details_ViewPager.setCurrentItem(mI);
        mHandler.sendEmptyMessageDelayed(mI,2000);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
       // EventBus.getDefault().unregister(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
