package com.hoangnl.mac.food.datas;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.hoangnl.mac.food.contracts.News;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac on 1/26/17.
 */

public class ParseContent extends AsyncTask<String, Void, List<News>> implements GetRawData.OnDownloadComplete {

    private static final String TAG = "ParseContent";

    private List<News> mNewsList = null;
    private String mBaseUrl;

    private final OnDataAvaiable mCallback;
    private boolean runningOnSameThread = false;
    private String mPage;

    public interface OnDataAvaiable {
        void onDataAvaiable(List<News> data, DownloadStatus status);
    }

    public ParseContent(OnDataAvaiable callback, String baseUrl, int page) {
        Log.d(TAG, "GetFlickrjsonData: called");
        mCallback = callback;
        mBaseUrl = baseUrl;
        mPage = String.valueOf(page);
    }

    void excuteOnSameThread(String criteria) {
        Log.d(TAG, "excuteOnSameThread: starts");

        runningOnSameThread = true;
        String destinationUri = createUri(criteria, mPage);

        GetRawData getRawData = new GetRawData(this);
        getRawData.execute(destinationUri);
        Log.d(TAG, "excuteOnSameThread: ends");
    }

    @Override
    protected void onPostExecute(List<News> Newss) {
        Log.d(TAG, "onPostExecute: starts");
        if (mCallback != null) {
            mCallback.onDataAvaiable(mNewsList, DownloadStatus.OK);
        }
        Log.d(TAG, "onPostExecute: ends");
    }

    @Override
    protected List<News> doInBackground(String... params) {
        Log.d(TAG, "doInBackground: starts");
        String destinationUri = createUri(params[0], mPage);

        GetRawData getRawData = new GetRawData(this);
        getRawData.runInSameThread(destinationUri);
        Log.d(TAG, "doInBackground: ends");
        return mNewsList;
    }

    private String createUri(String criteria, String page) {
        Log.d(TAG, "createUri: starts");

        return Uri.parse(mBaseUrl).buildUpon().appendQueryParameter("page", page)
                .build().toString();
    }

    @Override
    public void onDownloadComplete(String data, DownloadStatus status) {
        Log.d(TAG, "onDownloadComplete: starts");
        if (status == DownloadStatus.OK) {
            mNewsList = new ArrayList<>();

            Document document = null;
            String title = null, link = null, thumbnail = null;
            document = (Document) Jsoup.parse(data);
            if (document != null) {
                Elements subjectElements = document.select("div.sktd-item");
                if (subjectElements != null && subjectElements.size() > 0) {
                    for (Element element : subjectElements) {

                        Element img = element.select("a img").first();
                        title = img.attr("alt");
                        link = element.select("a").first().attr("href");
                        thumbnail = img.attr("src");
                        News newsObject = new News(title, link, thumbnail);
                        mNewsList.add(newsObject);
                        Log.d(TAG, "onDownloadComplete: " + mNewsList.toString());


                    }
                }

            }


        }

        if (runningOnSameThread && mCallback != null) {
            mCallback.onDataAvaiable(mNewsList, status);
        }

        Log.d(TAG, "onDownloadComplete: ends");
    }
}
