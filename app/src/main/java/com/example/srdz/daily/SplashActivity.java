package com.example.srdz.daily;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.srdz.daily.Api.ContentVaual;

import com.example.srdz.daily.modle.News;
import com.example.srdz.daily.modle.Splash;
import com.example.srdz.daily.utils.BaseUtils;
import com.example.srdz.daily.utils.glideutil.GlideUtils;
import com.orhanobut.logger.Logger;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.img_splash)
    ImageView imgSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
     //   getSplash();
        initImage();


    }

    private void initImage() {

        final ScaleAnimation animtion = new ScaleAnimation(1.0f,1.2f,1.0f,1.2f,
                Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        animtion.setFillAfter(true);
        animtion.setDuration(3000);
        animtion.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                    startActivity();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        imgSplash.startAnimation(animtion);
    }


    private void getSplash() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ContentVaual.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build();
        ContentVaual vaual = retrofit.create(ContentVaual.class);
        Call<Splash> call = vaual.getsplash();
        call.enqueue(new Callback<Splash>() {
            @Override
            public void onResponse(Call<Splash> call, Response<Splash> response) {
                Splash splash = response.body();
                GlideUtils.getInstance().loadImageView(SplashActivity.this,splash.getImg(),imgSplash);
            }

            @Override
            public void onFailure(Call<Splash> call, Throwable t) {

            }
        });

    }

    private void startActivity() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out);
        finish();
    }
}
