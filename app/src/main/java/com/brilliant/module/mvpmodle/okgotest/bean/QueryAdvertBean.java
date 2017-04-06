package com.brilliant.module.mvpmodle.okgotest.bean;


import com.brilliant.base.BaseBean;

/**
 * Created by Conan on 2017/3/30.
 */

public class QueryAdvertBean extends BaseBean {
    /**
     * data : {"title":"fffff","imgUrl":"http://xiangfajrtest.oss-cn-hangzhou.aliyuncs.com/advert/advert/058e7b19-637a-4df2-af8f-baa3c6e85dc9.jpg","linkUrl":"https://www.baidu.com/","key":"f1a669fc-d9a7-421a-a6be-067c3d042909","showTime":3,"showNum":3}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * title : fffff
         * imgUrl : http://xiangfajrtest.oss-cn-hangzhou.aliyuncs.com/advert/advert/058e7b19-637a-4df2-af8f-baa3c6e85dc9.jpg
         * linkUrl : https://www.baidu.com/
         * key : f1a669fc-d9a7-421a-a6be-067c3d042909
         * showTime : 3
         * showNum : 3
         */

        private String title;
        private String imgUrl;
        private String linkUrl;
        private String key;
        private int showTime;
        private int showNum;

        private int leftShowNum;//剩余可展示次数

        public int getLeftShowNum() {
            return leftShowNum;
        }

        public void setLeftShowNum(int leftShowNum) {
            this.leftShowNum = leftShowNum;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getLinkUrl() {
            return linkUrl;
        }

        public void setLinkUrl(String linkUrl) {
            this.linkUrl = linkUrl;
        }

        public String getKey() {
            return key == null ? "" : key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public int getShowTime() {
            return showTime;
        }

        public void setShowTime(int showTime) {
            this.showTime = showTime;
        }

        public int getShowNum() {
            return showNum;
        }

        public void setShowNum(int showNum) {
            this.showNum = showNum;
        }
    }
}
