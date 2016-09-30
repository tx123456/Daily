package com.example.srdz.daily.modle;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by SRDZ on 2016/9/27.
 */
public class News {

    /**
     * date : 20160927
     * stories : [{"images":["http://pic3.zhimg.com/0a34a572c668d1fb4f7702297aefa4be.jpg"],"type":0,"id":8834102,"ga_prefix":"092714","title":"编程到底是不是有技术含量的东西呢？也是也不是"},{"images":["http://pic3.zhimg.com/555345fbeab59079453c2e47fdcb71d2.jpg"],"type":0,"id":8834308,"ga_prefix":"092713","title":"分清「不开心」和「可能有问题的抑郁」，不必自己吓自己"},{"images":["http://pic4.zhimg.com/5072d82667474555b64a08e5a8a7c42b.jpg"],"type":0,"id":8829968,"ga_prefix":"092712","title":"大误 · 颤抖吧，战栗吧，仰慕我吧"},{"images":["http://pic4.zhimg.com/d9a7e143db640b0783cc833343afa627.jpg"],"type":0,"id":8831572,"ga_prefix":"092711","title":"汽车上有哪些鸡肋的伪科技和了不起的真科技？"},{"images":["http://pic4.zhimg.com/fd70edd6f8843efdce68a3b2ef8d818f.jpg"],"type":0,"id":8832044,"ga_prefix":"092710","title":"想着简单，但证明生成截图的当前时间真是个技术活"},{"images":["http://pic1.zhimg.com/f6ac28b94ec975b02f31ed5607b7f650.jpg"],"type":0,"id":8831129,"ga_prefix":"092709","title":"看到天上掉下来个大气球，请记得按照指示交给气象局"},{"images":["http://pic3.zhimg.com/169f6bebd272213f1c22169f7a1e462a.jpg"],"type":0,"id":8832043,"ga_prefix":"092708","title":"觉得自己很会学声音，可以试试当一名配音员"},{"images":["http://pic4.zhimg.com/230bff94091349c1b4bae9392d9e234b.jpg"],"type":0,"id":8832662,"ga_prefix":"092707","title":"如果把磁铁磨成球形，磁极会跑到哪里去？"},{"images":["http://pic3.zhimg.com/a6c6ea04db327f6fa288758660c5f9de.jpg"],"type":0,"id":8831484,"ga_prefix":"092707","title":"今天是被「睡眠闹钟」叫醒的吗？真正的睡眠监测长这样"},{"title":"电子竞技行业看起来热闹，其实比一下就知道泡沫很多","ga_prefix":"092707","images":["http://pic2.zhimg.com/5054a661a6d04c7f17398e757e26c809.jpg"],"multipic":true,"type":0,"id":8831458},{"images":["http://pic2.zhimg.com/2295256bb4a6da014a460d2cab0456b9.jpg"],"type":0,"id":8832586,"ga_prefix":"092707","title":"读读日报 24 小时热门 TOP 5 · 耶鲁村官"},{"images":["http://pic4.zhimg.com/5a6968d60b114234a6e21ed84a0b24db.jpg"],"type":0,"id":8831153,"ga_prefix":"092706","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"http://pic4.zhimg.com/cf662d0f2616358df1cd9e91ff3ef827.jpg","type":0,"id":8832043,"ga_prefix":"092708","title":"觉得自己很会学声音，可以试试当一名配音员"},{"image":"http://pic1.zhimg.com/88f6c753f3ef668df804525bd9d79434.jpg","type":0,"id":8831458,"ga_prefix":"092707","title":"电子竞技行业看起来热闹，其实比一下就知道泡沫很多"},{"image":"http://pic4.zhimg.com/bb56864dd315549703e76eda6d98868f.jpg","type":0,"id":8832586,"ga_prefix":"092707","title":"读读日报 24 小时热门 TOP 5 · 耶鲁村官"},{"image":"http://pic1.zhimg.com/371ff4976a5734dd85912ab3834ca070.jpg","type":0,"id":8831216,"ga_prefix":"092617","title":"知乎好问题 · 如何甄别一条网络消息的真假？"},{"image":"http://pic1.zhimg.com/108d765f0c5f6a8fe09fb8b7fcc199e8.jpg","type":0,"id":8830557,"ga_prefix":"092613","title":"为什么显示没票的高铁又在发车之前放出了好多票？"}]
     */
    @SerializedName("date")
    private String date;
    /**
     * images : ["http://pic3.zhimg.com/0a34a572c668d1fb4f7702297aefa4be.jpg"]
     * type : 0
     * id : 8834102
     * ga_prefix : 092714
     * title : 编程到底是不是有技术含量的东西呢？也是也不是
     */
    @SerializedName("stories")
    private List<StoriesBean> stories;
    /**
     * image : http://pic4.zhimg.com/cf662d0f2616358df1cd9e91ff3ef827.jpg
     * type : 0
     * id : 8832043
     * ga_prefix : 092708
     * title : 觉得自己很会学声音，可以试试当一名配音员
     */
    @SerializedName("top_stories")
    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean implements Parcelable {

        @SerializedName("type")
        private int type;
        @SerializedName("id")
        private int id;
       // @SerializedName("ga_prefix")
       // private String ga_prefix;
        @SerializedName("title")
        private String title;
        @SerializedName("images")
        private List<String> images;

        public StoriesBean(int type,int id,String title,List<String> images){
            this.type = type;
            this.id = id;
          //  this.ga_prefix = ga_prefix;
            this.title = title;
            this.images = images;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

//        public String getGa_prefix() {
//            return ga_prefix;
//        }
//
//        public void setGa_prefix(String ga_prefix) {
//            this.ga_prefix = ga_prefix;
//        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.type);
            dest.writeInt(this.id);
            dest.writeString(this.title);
            dest.writeStringList(this.images);
        }

        protected StoriesBean(Parcel in) {
            this.type = in.readInt();
            this.id = in.readInt();
            this.title = in.readString();
            this.images = in.createStringArrayList();
        }

        public static final Parcelable.Creator<StoriesBean> CREATOR = new Parcelable.Creator<StoriesBean>() {
            @Override
            public StoriesBean createFromParcel(Parcel source) {
                return new StoriesBean(source);
            }

            @Override
            public StoriesBean[] newArray(int size) {
                return new StoriesBean[size];
            }
        };
    }

    public static class TopStoriesBean {
        @SerializedName("image")
        private String image;
        @SerializedName("type")
        private int type;
        @SerializedName("id")
        private int id;
        @SerializedName("ga_prefix")
        private String ga_prefix;
        @SerializedName("title")
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
