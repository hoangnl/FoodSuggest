package com.hoangnl.mac.food.contracts;

import java.io.Serializable;

/**
 * Created by mac on 4/18/17.
 */

public class Photo implements Serializable {

    private static final long serialVerionUID = 1L;

    private String mTitle;
    private String mAuthor;
    private String mAuthorId;
    private String mLink;
    private String mTags;
    private String mImage;

    public Photo(String title, String author, String authorId, String link, String tags, String image) {
        mTitle = title;
        mAuthor = author;
        mAuthorId = authorId;
        mLink = link;
        mTags = tags;
        mImage = image;
    }

    public String getTitle() {
        return mTitle;
    }

    String getAuthor() {
        return mAuthor;
    }

    String getAuthorId() {
        return mAuthorId;
    }

    String getLink() {
        return mLink;
    }

    String getTags() {
        return mTags;
    }

    public String getImage() {
        return mImage;
    }

    @Override
    public String toString() {
        return "Photo {" +
                "mTitle='" + mTitle + '\'' +
                ",mAuthor='" + mAuthor + '\'' +
                ",mAuthorId='" + mAuthorId + '\'' +
                ",mLink='" + mLink + '\'' +
                ",mTags='" + mTags + '\'' +
                ",mImage='" + mImage + '\'' +
                '}';
    }
}
