package com.example.srdz.daily.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.srdz.daily.R;
import com.example.srdz.daily.modle.ThemesList;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SRDZ on 2016/9/29.
 */
public class MenuThemesAdapter extends BaseAdapter {
    private List<ThemesList.OthersBean> list;
    private Context context;

    public MenuThemesAdapter(Context context,List<ThemesList.OthersBean> list) {
        this.context = context;
        this.list = list;
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
        if (view==null){
            view = LayoutInflater.from(context).inflate(R.layout.item_themes_list, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        ThemesList.OthersBean others = list.get(i);
        holder.tv.setText(others.getName());

        return view;
    }

    static class ViewHolder {
        @BindView(R.id.tv)
        TextView tv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
