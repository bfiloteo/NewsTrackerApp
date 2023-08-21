package com.example.newsappproject;


public class News {

    private String mTitle;

    private String mTopic;

    private String mDate;

    private String mImageUrl;

    private String mUrl;

    private String mAuthor;

    public News(String title, String topic, String date,String author, String imageUrl, String url) {
        mTitle = title;
        mTopic = topic;
        mDate = date;
        mAuthor = author;
        mImageUrl = imageUrl;
        mUrl = url;
    }

    public String getTitle(){
        return mTitle;
    }

    public String getTopic(){
        return mTopic;
    }

    public String getDate(){
        return mDate;
    }

    public String getAuthor(){
        return mAuthor;
    }

    public String getImageUrl(){
        return mImageUrl;
    }

    public String getUrl() {
        return mUrl;
    }

}
