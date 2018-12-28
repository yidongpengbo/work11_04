package com.example.lenovo.work11_04;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lenovo.work11_04.adapter.RecyAdapter;
import com.example.lenovo.work11_04.bean.NewsBean;
import com.example.lenovo.work11_04.until.Until;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
                @BindView(R.id.Button_Return)
            Button Button_Return;
                @BindView(R.id.Image_search)
                ImageView Image_Search;
                @BindView(R.id.Edit_Search)
            EditText Edit_Search;
                @BindView(R.id.Button_Switch)
            Button Button_Switch;
                @BindView(R.id.Recycler)
            RecyclerView Recycler;
    private RecyAdapter mRecyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //butterknife
        ButterKnife.bind(this);
        //EventBus接受消息的页面注册!!!!
        EventBus.getDefault().register(this);
        //RecyclerView操作
        initRecy();
    }

    private void initRecy() {
            //管理布局
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(OrientationHelper.VERTICAL);
        Recycler.setLayoutManager(manager);
        //适配器
        mRecyAdapter = new RecyAdapter(this);
        Recycler.setAdapter(mRecyAdapter);
        //数据请求
        initData();
    }

    /**
     * 根据网址请求数据
     */
    private void initData() {
  //  String path="http://www.zhaoapi.cn/product/searchProducts?keywords=手机&page=1";
        Until.getInstance().getEnque();
    }

    /**
     * 得到数据
     * @param bean
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    //!!!!!!!!!!
    public void onEventMainThread(List<NewsBean.DataBean> bean){
        Log.i("TAG","bean="+bean);
        mRecyAdapter.setMjihe(bean);
    }

    /**
     * 退出
     */
    @OnClick(R.id.Button_Return)
    public void button_Return(){
        Toast.makeText(this,"退出",Toast.LENGTH_LONG).show();
        finish();
    }

    /**
     * 搜索
     */
    @OnClick(R.id.Image_search)
    public void image_Search(){
        //得到输入的值
        String input_Edit = Edit_Search.getText().toString();
        String path="http://www.zhaoapi.cn/product/searchProducts";
        HashMap<String, String> map = new HashMap<>();
        map.put("keywords",input_Edit);
        map.put("page",1+"");
        Until.getInstance().post2(map,path);
    }

    /**
     * 切换
     */
    Boolean boo=false;
    @OnClick(R.id.Button_Switch)
    public void button_Switch(){
        if (boo){
            LinearLayoutManager manager = new LinearLayoutManager(this);
            manager.setOrientation(OrientationHelper.VERTICAL);
            Recycler.setLayoutManager(manager);
        }else {

            GridLayoutManager manager = new GridLayoutManager(this, 2);
            manager.setOrientation(OrientationHelper.VERTICAL);
            Recycler.setLayoutManager(manager);
        }
        boo=!boo;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
