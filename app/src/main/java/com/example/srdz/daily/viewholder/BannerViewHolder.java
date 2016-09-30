package com.example.srdz.daily.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.example.srdz.daily.R;
import com.example.srdz.daily.modle.News;
import com.example.srdz.daily.utils.glideutil.GlideUtils;

import java.util.List;

/**
 * Created by SRDZ on 2016/9/28.
 */
public class BannerViewHolder implements Holder<News.TopStoriesBean> {

    private ImageView iv_title;
    private TextView tv_title;
    @Override
    public View createView(Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.item_banner_detail,null,false);
        iv_title = (ImageView) view.findViewById(R.id.iv_title);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        return view;
    }

    @Override
    public void UpdateUI(Context context, int position, News.TopStoriesBean data) {
        GlideUtils.getInstance().loadImageView(context, data.getImage(), iv_title);
        tv_title.setText(data.getTitle());
    }



}
