package com.example.srdz.daily;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.srdz.daily.Api.ContentVaual;
import com.example.srdz.daily.modle.News;
import com.example.srdz.daily.modle.NewsDetail;
import com.example.srdz.daily.weight.RevealBackgroundView;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecomDetailActivity extends AppCompatActivity implements RevealBackgroundView.OnStateChangeListener {


    @BindView(R.id.revealBackgroundView)
    RevealBackgroundView revealBackgroundView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.colltoolbar)
    CoordinatorLayout colltoolbar;
    @BindView(R.id.view_web)
    WebView viewWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recom_detail);
        ButterKnife.bind(this);
        initView();
        setupRevealBackground(savedInstanceState);
    }

    private void initView() {
        viewWeb.getSettings().setJavaScriptEnabled(true);
        viewWeb.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // 开启DOM storage API 功能
        viewWeb.getSettings().setDomStorageEnabled(true);
        // 开启database storage API功能
        viewWeb.getSettings().setDatabaseEnabled(true);
        // 开启Application Cache功能
        viewWeb.getSettings().setAppCacheEnabled(true);
        viewWeb.getSettings().setLoadsImagesAutomatically(true);
        News.StoriesBean storiesBean = getIntent().getParcelableExtra("storiesBean");
        initData(storiesBean.getId() + "");

        toolbar.setTitle(storiesBean.getTitle());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
    }

    private void initData(String id) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ContentVaual.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        ContentVaual vaual = retrofit.create(ContentVaual.class);
        Call<NewsDetail> call = vaual.getNewsDetail(id);
        call.enqueue(new Callback<NewsDetail>() {
            @Override
            public void onResponse(Call<NewsDetail> call, Response<NewsDetail> response) {
                String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/css/news.css\" type=\"text/css\">";
                String html = "<html><head>" + css + "</head><body>" + response.body().getBody() + "</body></html>";
                html = html.replace("<div class=\"img-place-holder\">", "");
                viewWeb.loadDataWithBaseURL("x-data://base", html, "text/html", "UTF-8", null);
            }

            @Override
            public void onFailure(Call<NewsDetail> call, Throwable t) {

            }
        });
    }


    private void setupRevealBackground(Bundle savedInstanceState) {
        revealBackgroundView.setOnStateChangeListener(this);
        if (savedInstanceState == null) {
            final int[] startingLocation = getIntent().getIntArrayExtra("start_location");
            revealBackgroundView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    revealBackgroundView.getViewTreeObserver().removeOnPreDrawListener(this);
                    revealBackgroundView.startFromLocation(startingLocation);
                    return true;
                }
            });
        } else {
            revealBackgroundView.setToFinishedFrame();
        }
    }

    @TargetApi(21)
    private void setStatusBarColor(int statusBarColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // If both system bars are black, we can remove these from our layout,
            // removing or shrinking the SurfaceFlinger overlay required for our views.
            Window window = this.getWindow();
            if (statusBarColor == Color.BLACK && window.getNavigationBarColor() == Color.BLACK) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            } else {
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            }
            window.setStatusBarColor(statusBarColor);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    @Override
    public void onStateChange(int state) {
        if (RevealBackgroundView.STATE_FINISHED == state) {
            colltoolbar.setVisibility(View.VISIBLE);
        }
    }
}
