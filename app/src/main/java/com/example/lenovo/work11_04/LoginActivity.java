package com.example.lenovo.work11_04;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import com.example.lenovo.work11_04.adapter.Login_ViewPager_Adapter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {
        @BindView(R.id.Login_Tab)
        TabLayout Login_Tab;
        @BindView(R.id.Login_ViewPager)
        ViewPager Login_ViewPager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initViewPager();
    }

    private void initViewPager() {
        Login_ViewPager.setAdapter(new Login_ViewPager_Adapter(getSupportFragmentManager()));
        Login_Tab.setupWithViewPager(Login_ViewPager);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
