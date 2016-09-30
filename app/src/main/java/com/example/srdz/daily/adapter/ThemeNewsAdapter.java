package com.example.srdz.daily.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.srdz.daily.Api.ContentVaual;
import com.example.srdz.daily.R;
import com.example.srdz.daily.modle.News;
import com.example.srdz.daily.utils.glideutil.GlideUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SRDZ on 2016/9/28.
 */
public class ThemeNewsAdapter extends BaseAdapter {
    private List<News.StoriesBean> list;
    private Context context;


    public ThemeNewsAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    public void addAll(List<News.StoriesBean> item){
        this.list.addAll(item);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if(view==null){
            view = LayoutInflater.from(context).inflate(R.layout.item_main_news, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        News.StoriesBean storise = list.get(i);

        holder.tvTitle.setText(storise.getTitle());
        if (storise.getImages()!=null){
            GlideUtils.getInstance().loadImageView(context,storise.getImages().get(0),holder.ivTitle);
        }else {
            holder.ivTitle.setVisibility(View.GONE);
        }


        return view;
    }


    static class ViewHolder {
        @BindView(R.id.topic)
        TextView topic;
        @BindView(R.id.iv_title)
        ImageView ivTitle;
        @BindView(R.id.tv_title)
        TextView tvTitle;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
