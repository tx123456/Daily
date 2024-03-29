package com.example.srdz.daily.utils.glideutil;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.srdz.daily.R;

public class GlideUtils

{
	public static GlideUtils instance;

	public static GlideUtils getInstance() {
		if (instance == null) {
			instance = new GlideUtils();
		}
		return instance;
	}

	public void loadBanner(Context context, int imgUrl, ImageView imageView) {
		Glide.with(context).load(imgUrl).diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop().crossFade()
				.into(imageView);
	}

	/**
	 * 联网加载普通imageview
	 * 
	 * @param context
	 * @param imgUrl
	 * @param imageView
	 */
	public void loadImageView(Context context, String imgUrl, ImageView imageView) {

		Glide.with(context).load(imgUrl).diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop().crossFade()
				.into(imageView);
	}

	/**
	 * 本地加载普通imageview
	 * 
	 * @param context
	 * @param imgUrl
	 * @param imageView
	 */
	public void loadNormalImageView(Context context, Object imgUrl, ImageView imageView) {
		Glide.with(context).load(imgUrl).error(R.mipmap.ic_launcher).crossFade().into(imageView);
	}

	/***
	 * 加载圆行imageview（网络）
	 * 
	 * @param context
	 * @param imgUrl
	 * @param imageView
	 */
	public void loadRoundImageView(Context context, String imgUrl, ImageView imageView) {

		Glide.with(context).load(imgUrl).diskCacheStrategy(DiskCacheStrategy.ALL)
				.transform(new GlideCircleTransform(context)).crossFade().into(imageView);
	}

//	/***
//	 * 加载圆角imageview(网络)
//	 * 
//	 * @param context
//	 * @param imgUrl
//	 * @param imageView
//	 */
//	public void loadCornerImageView(Context context, String imgUrl, ImageView imageView) {
//
//		Glide.with(context).load(imgUrl).diskCacheStrategy(DiskCacheStrategy.ALL)
//				.transform(new GlideRoundTransform(context, 8)).into(imageView);
//
//	}

	/***
	 * 加载圆行imageview（本地）
	 * 
	 * @param context
	 * @param imgUrl
	 * @param imageView
	 */
	public void loadNomalRoundImageView(Context context, Object imgUrl, ImageView imageView) {
		Glide.with(context).load(imgUrl).transform(new GlideCircleTransform(context)).into(imageView);
	}
	
	
	/***
	 * 加载模糊图片
	 * 
	 * @param context
	 * @param imgUrl
	 * @param imageView
	 */
	public void loadBlurImageView(Context context, Object imgUrl, ImageView imageView) {
		Glide.with(context).load(imgUrl).transform(new BlurTransformation(context, 40)).centerCrop().crossFade().into(imageView);
	}
}
