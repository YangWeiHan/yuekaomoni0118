package com.example.mryang.yuekaomoni0118.data.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mryang.yuekaomoni0118.R;
import com.example.mryang.yuekaomoni0118.data.bean.NewsBean;

import java.util.ArrayList;
import java.util.List;

public class NewsTwoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ONE = 1;
    private static final int TYPE_TWO = 2;
    private Context context;
    private List<NewsBean.DataBean> newsList;

    public List<NewsBean.DataBean> getNewsList() {
        return newsList;
    }

    public NewsTwoAdapter(Context context) {
        this.context = context;
        newsList = new ArrayList<>();
    }

    public void setNewsList(List<NewsBean.DataBean> newsList) {
        if (newsList != null){
            this.newsList = newsList;
        }
        notifyDataSetChanged();
    }
    public void addNewsList(List<NewsBean.DataBean> newsList) {
        if (newsList != null){
            this.newsList.addAll(newsList);
        }
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == TYPE_ONE){
            View view = LayoutInflater.from(context).inflate(R.layout.news_oneitem,viewGroup,false);
            return new ViewHolerOne(view);
        }else {
            View view = LayoutInflater.from(context).inflate(R.layout.news_twoitem,viewGroup,false);
            return new ViewHolerTwo(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        int itemViewType = getItemViewType(i);
        switch (itemViewType){
            case TYPE_ONE :
                ViewHolerOne viewHolerOne = (ViewHolerOne) viewHolder;
                viewHolerOne.tv_title.setText(newsList.get(i).getTitle());
                viewHolerOne.tv_time.setText(newsList.get(i).getDate());
                viewHolerOne.tv_name.setText(newsList.get(i).getAuthor_name());
                break;
            case TYPE_TWO :
                ViewHolerTwo viewHolerTwo = (ViewHolerTwo) viewHolder;
                viewHolerTwo.tv_category.setText(newsList.get(i).getCategory());
                viewHolerTwo.tv_name.setText(newsList.get(i).getAuthor_name());
                Glide.with(context).load(newsList.get(i).getThumbnail_pic_s()).into(viewHolerTwo.iv_icon);
                break;
                default:break;
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClickListener(v,i);
            }
        });
    }
    //删除
    public void remove(int position){
        newsList.remove(position);
     /*   notifyDataSetChanged();*/
    }
    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public OnItemClickListener onItemClickListener;

    public interface OnItemClickListener{
       public void onItemClickListener(View v , int position );
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemViewType(int position) {

        if (position%2 == 1){
            return TYPE_ONE;
        }else {
            return TYPE_TWO;
        }
    }

    class ViewHolerOne extends RecyclerView.ViewHolder {
        TextView tv_title,tv_time,tv_name;
        public ViewHolerOne(@NonNull View itemView) {
            super(itemView);
           tv_name = itemView.findViewById(R.id.tv_name);
           tv_time = itemView.findViewById(R.id.tv_time);
           tv_title = itemView.findViewById(R.id.tv_title);
        }
    }
    class ViewHolerTwo extends RecyclerView.ViewHolder {
        ImageView iv_icon;
        TextView tv_category,tv_name;
        public ViewHolerTwo(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_category = itemView.findViewById(R.id.tv_category);
            iv_icon = itemView.findViewById(R.id.iv_icon);
        }
    }
}
