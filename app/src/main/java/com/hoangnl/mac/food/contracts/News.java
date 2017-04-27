package com.hoangnl.mac.food.contracts;

import java.io.Serializable;

/**
 * Created by mac on 4/18/17.
 */

public class News implements Serializable {

    private static final long serialVerionUID = 1L;

    private String mTitle;
    private String mLink;
    private String mImage;

    public News(String title, String link, String image) {
        mTitle = title;
        mLink = link;
        mImage = image;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getLink() {
        return mLink;
    }

    public String getImage() {
        return mImage;
    }

    @Override
    public String toString() {
        return "News {" +
                "mTitle='" + mTitle + '\'' +
                ",mLink='" + mLink + '\'' +
                ",mImage='" + mImage + '\'' +
                '}';
    }
}
