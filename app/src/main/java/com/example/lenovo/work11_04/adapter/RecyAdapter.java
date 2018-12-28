package com.example.lenovo.work11_04.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.work11_04.LoginActivity;
import com.example.lenovo.work11_04.R;
import com.example.lenovo.work11_04.bean.NewsBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class RecyAdapter extends RecyclerView.Adapter<RecyAdapter.ViewHolder> {
    private Context mContext;
    private List<NewsBean.DataBean> mjihe;

    public RecyAdapter(Context context) {
        mContext = context;
        mjihe=new ArrayList<>();
    }

    public void setMjihe(List<NewsBean.DataBean> mjihe) {
        this.mjihe = mjihe;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.recy_adapter,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        String images = mjihe.get(i).getImages();
        String[] split = images.split("\\|");
        String image = split[0].replace("https", "http");
        //图片
        Uri uri = Uri.parse(image);
        viewHolder.sdv.setImageURI(uri);
        //文字
        viewHolder.title.setText(mjihe.get(i).getTitle());
        //价格
        viewHolder.price.setText("¥"+mjihe.get(i).getPrice());
        //条目点击事件
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到详情页
                Intent intent = new Intent(mContext,LoginActivity.class);
                intent.putExtra("id",i+"");
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mjihe.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView sdv;
        TextView title,price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.Title);
            price=itemView.findViewById(R.id.Price);
            sdv=itemView.findViewById(R.id.SDV);
        }
    }
}
