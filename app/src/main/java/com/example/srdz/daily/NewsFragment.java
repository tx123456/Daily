package com.example.srdz.daily;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.srdz.daily.Api.ContentVaual;
import com.example.srdz.daily.adapter.ThemeNewsAdapter;
import com.example.srdz.daily.modle.News;
import com.example.srdz.daily.utils.glideutil.GlideUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class NewsFragment extends Fragment {

    String thumbnail;
    String description;
    String id;
    String name;
    @BindView(R.id.lv_news)
    ListView lvNews;
    TextView tv_title;
    ImageView iv_title;
    ThemeNewsAdapter adapter;

    public NewsFragment(String thumbnail, String description, String id, String name) {
        this.thumbnail = thumbnail;
        this.description = description;
        this.id = id;
        this.name = name;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        ButterKnife.bind(this, view);

        initView();
        initData();

        return view;
    }

    private void initView() {
        View header = LayoutInflater.from(getActivity()).inflate(R.layout.item_banner_detail,lvNews,false);
        lvNews.addHeaderView(header,null,false);
        tv_title = (TextView) header.findViewById(R.id.tv_title);
        iv_title = (ImageView) header.findViewById(R.id.iv_title);
        tv_title.setText(description);
        GlideUtils.getInstance().loadImageView(getActivity(),thumbnail,iv_title);
        adapter = new ThemeNewsAdapter(getActivity());
        lvNews.setAdapter(adapter);
        lvNews.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                if (lvNews!=null&&lvNews.getChildCount()>0){
                    boolean enable = (i==0)&&(absListView.getChildAt(i).getTop()==0);
                    ((MainActivity)getActivity()).setSwipeRefreshEnable(enable);
            }
            }
        });
        lvNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int[] startingLocation = new int[2];
                view.getLocationOnScreen(startingLocation);
                startingLocation[0] += view.getWidth() / 2;
                News.StoriesBean storiesBean = (News.StoriesBean) adapterView.getAdapter().getItem(i);
                Intent intent = new Intent(getActivity(),RecomDetailActivity.class);
                intent.putExtra("start_location", startingLocation);
                intent.putExtra("storiesBean",storiesBean);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ContentVaual.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        ContentVaual vaual = retrofit.create(ContentVaual.class);
        Call<News> call = vaual.getThemes(id);
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                List<News.StoriesBean> list = response.body().getStories();
                adapter.addAll(list);
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

            }
        });
    }


}
