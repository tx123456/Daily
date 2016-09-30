package com.example.srdz.daily.modle;

import com.google.gson.annotations.SerializedName;

/**
 * Created by SRDZ on 2016/9/27.
 */
public class Splash {

    /**
     * text : Ray Hennessy
     * img : https://pic2.zhimg.com/v2-8960c75578eb9b5a82f3ad27e275e4a1.jpg
     */
    @SerializedName("text")
    public String text;
    @SerializedName("img")
    public String img;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
