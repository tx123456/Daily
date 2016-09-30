package com.example.srdz.daily;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.example.srdz.daily.Api.ContentVaual;
import com.example.srdz.daily.viewholder.BannerViewHolder;
import com.example.srdz.daily.adapter.MainNewsAdapter;
import com.example.srdz.daily.modle.News;
import com.example.srdz.daily.utils.BaseUtils;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainFragment extends Fragment {

    private MainNewsAdapter adapter;
    private ListView lv_news;
    private ConvenientBanner banner;
    private boolean isLoading = false;
    private String date;
    List<News.TopStoriesBean> topList = new ArrayList<>();



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {
        LoadFirst();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,container,false);
        lv_news = (ListView) view.findViewById(R.id.lv_news);
        View header = inflater.inflate(R.layout.item_banner,lv_news,false);
        banner = (ConvenientBanner) header.findViewById(R.id.CBanner);
        lv_news.addHeaderView(header);
        adapter = new MainNewsAdapter(getActivity());
        lv_news.setAdapter(adapter);
        lv_news.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                if (lv_news!=null&&lv_news.getChildCount()>0){
                    boolean enable = (i==0)&&(absListView.getChildAt(i).getTop()==0);
                    ((MainActivity)getActivity()).setSwipeRefreshEnable(enable);
                    //刷新数据
                    if (i+i1==i2&&!isLoading){
                        loadMore();//加载更多
                    }
                }
            }
        });
        lv_news.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int[] startingLocation = new int[2];
                view.getLocationOnScreen(startingLocation);
                startingLocation[0] += view.getWidth() / 2;
                News.StoriesBean storiesBean = (News.StoriesBean) adapterView.getAdapter().getItem(i);
                Intent intent = new Intent(getActivity(),NewsDetailActivity.class);
                intent.putExtra("start_location", startingLocation);
                intent.putExtra("storiesBean",storiesBean);
                startActivity(intent);
            }
        });

        banner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                List<String> image = new ArrayList<String>();
                image.add(topList.get(position).getImage());
                int[] startingLocation = new int[2];
                News.StoriesBean storiesBean = new News.StoriesBean(topList.get(position).getType(),
                        topList.get(position).getId(),topList.get(position).getTitle(),image);
                Intent intent = new Intent(getActivity(),NewsDetailActivity.class);
                intent.putExtra("start_location", startingLocation);
                intent.putExtra("storiesBean",storiesBean);
                startActivity(intent);
            }
        });
        return view;
    }

    private void loadMore() {
        isLoading = true;
        if (BaseUtils.isNetworkConnected(getActivity())){
            Retrofit retrofit = new Retrofit.Builder().baseUrl(ContentVaual.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build();
            ContentVaual vaual = retrofit.create(ContentVaual.class);
            Call<News> call = vaual.getbefore(date);
            call.enqueue(new Callback<News>() {
                @Override
                public void onResponse(Call<News> call, Response<News> response) {
                    List<News.StoriesBean> storiesList = response.body().getStories();
                    date = response.body().getDate();
                    News.StoriesBean stories = new News.StoriesBean(ContentVaual.TOPIC,233,getDate(date),null);
                    storiesList.add(0,stories);
                    adapter.addAll(storiesList);
                    isLoading = false;
                }

                @Override
                public void onFailure(Call<News> call, Throwable t) {

                }
            });
        }else {
            Toast.makeText(getActivity(), "无网络连接", Toast.LENGTH_SHORT).show();
        }

    }
    private void LoadFirst(){
        isLoading = true;
        if (BaseUtils.isNetworkConnected(getActivity())){
            Retrofit retrofit = new Retrofit.Builder().baseUrl(ContentVaual.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build();
            ContentVaual vaual = retrofit.create(ContentVaual.class);
            Call<News> call = vaual.getnews();
            call.enqueue(new Callback<News>() {
                @Override
                public void onResponse(Call<News> call, Response<News> response) {
                    List<News.StoriesBean> storiesList = response.body().getStories();
                    News.StoriesBean stories = new News.StoriesBean(ContentVaual.TOPIC,233,"今日新闻",null);
                    storiesList.add(0,stories);
                    adapter.addAll(storiesList);

                    date = response.body().getDate();

                    topList = response.body().getTop_stories();
                    banner.setPages(new CBViewHolderCreator<BannerViewHolder>() {
                        @Override
                        public BannerViewHolder createHolder() {
                            return new BannerViewHolder();
                        }
                    },topList).startTurning(3000).setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused});

                    isLoading = false;
                }

                @Override
                public void onFailure(Call<News> call, Throwable t) {

                }
            });
        }else {
            Toast.makeText(getActivity(), "无网络连接", Toast.LENGTH_SHORT).show();
        }

    }


    private String getDate(String date) {
        String result = date.substring(0, 4);
        result += "年";
        result += date.substring(4, 6);
        result += "月";
        result += date.substring(6, 8);
        result += "日";
        return result;
    }
}
