package com.example.srdz.daily;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.srdz.daily.Api.ContentVaual;
import com.example.srdz.daily.adapter.MenuThemesAdapter;
import com.example.srdz.daily.modle.ThemesList;
import com.example.srdz.daily.utils.BaseUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.lay_fragment)
    FrameLayout layFragment;
    @BindView(R.id.lay_navigation)
    NavigationView layNavigation;
    @BindView(R.id.lay_draw)
    DrawerLayout layDraw;
    TextView tv_login, tv_like, tv_xiazai;
    LinearLayout lay_shouye;
    ListView lv_themes;
    @BindView(R.id.lay_swipe)
    SwipeRefreshLayout laySwipe;

    private String curId;
    private MenuThemesAdapter adapter;
    List<ThemesList.OthersBean> others ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        loadLatest();
        initView();
        initData();


    }

    private void initData() {
        if (BaseUtils.isNetworkConnected(this)){
            Retrofit retrofit = new Retrofit.Builder().baseUrl(ContentVaual.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build();
            ContentVaual vaual = retrofit.create(ContentVaual.class);
            Call<ThemesList> call = vaual.getThemesList();
            call.enqueue(new Callback<ThemesList>() {
                @Override
                public void onResponse(Call<ThemesList> call, Response<ThemesList> response) {
                    others = response.body().getOthers();
                    adapter = new MenuThemesAdapter(MainActivity.this,others);
                    lv_themes.setAdapter(adapter);
                }

                @Override
                public void onFailure(Call<ThemesList> call, Throwable t) {

                }
            });
        }else {
            Toast.makeText(this, "无网络连接", Toast.LENGTH_SHORT).show();
        }

        lv_themes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_from_right, R.anim.slide_out_to_left)
                        .replace(R.id.lay_fragment, new NewsFragment(others.get(i).getThumbnail(),others.get(i).getDescription(),
                                others.get(i).getId()+"",others.get(i).getName()),"news").commit();
                setCurId(others.get(i).getId()+"");
                toolbar.setTitle(others.get(i).getName());
                layDraw.closeDrawer(GravityCompat.START);


            }
        });
    }

    private void initView() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, layDraw, toolbar, 0, 0);
        layDraw.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        others = new ArrayList<>();
        initHeardView();
        laySwipe.setColorSchemeResources(android.R.color.holo_blue_bright,android.R.color.holo_green_light
                ,android.R.color.holo_orange_light,android.R.color.holo_red_light);
        laySwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                replaceFragment();
                laySwipe.setRefreshing(false);
            }
        });

    }

    public void loadLatest(){
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_from_right, R.anim.slide_out_to_left)
                .replace(R.id.lay_fragment, new MainFragment(),"latest").commit();
        curId = "latest";
        toolbar.setTitle("Daily");
    }

    public void setCurId (String id){
        curId = id;
    }

    public void replaceFragment(){
        if (curId.equals("latest")){
            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_from_right, R.anim.slide_out_to_left)
                    .replace(R.id.lay_fragment, new MainFragment(),"latest").commit();

        }else {

        }
    }
    public void setSwipeRefreshEnable(boolean enable) {
        laySwipe.setEnabled(enable);
    }


    public void initHeardView(){
        View headView = layNavigation.getHeaderView(0);
        tv_login = (TextView) headView.findViewById(R.id.tv_login);
        tv_like = (TextView) headView.findViewById(R.id.tv_like);
        tv_xiazai = (TextView) headView.findViewById(R.id.tv_xiazai);
        lay_shouye = (LinearLayout) headView.findViewById(R.id.lay_shouye);
        lv_themes = (ListView) headView.findViewById(R.id.lv_themes);
        tv_login.setOnClickListener(this);
        tv_like.setOnClickListener(this);
        tv_xiazai.setOnClickListener(this);
        lay_shouye.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login:

                break;
            case R.id.tv_like:
                break;
            case R.id.tv_xiazai:
                break;
            case R.id.lay_shouye:
                loadLatest();
                break;

        }
        layDraw.closeDrawer(GravityCompat.START);
    }
}
