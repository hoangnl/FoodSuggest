package com.hoangnl.mac.food.datas;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Created by mac on 1/26/17.
 */

public class ParseDetail extends AsyncTask<String, Void, String> implements GetRawData.OnDownloadComplete {

    private static final String TAG = "ParseDetail";

    private String mNews = null;
    private String mBaseUrl;

    private final OnDataAvaiable mCallback;
    private boolean runningOnSameThread = false;

    public interface OnDataAvaiable {
        void onDataAvaiable(String data, DownloadStatus status);
    }

    public ParseDetail(OnDataAvaiable callback, String baseUrl) {
        Log.d(TAG, "ParseDetail: called");
        mCallback = callback;
        mBaseUrl = baseUrl;
    }

    void excuteOnSameThread(String criteria) {
        Log.d(TAG, "excuteOnSameThread: starts");

        runningOnSameThread = true;
        String destinationUri = createUri(criteria);

        GetRawData getRawData = new GetRawData(this);
        getRawData.execute(destinationUri);
        Log.d(TAG, "excuteOnSameThread: ends");
    }

    @Override
    protected void onPostExecute(String Newss) {
        Log.d(TAG, "onPostExecute: starts");
        if (mCallback != null) {
            mCallback.onDataAvaiable(mNews, DownloadStatus.OK);
        }
        Log.d(TAG, "onPostExecute: ends");
    }

    @Override
    protected String doInBackground(String... params) {
        Log.d(TAG, "doInBackground: starts");
        String destinationUri = createUri(params[0]);

        GetRawData getRawData = new GetRawData(this);
        getRawData.runInSameThread(destinationUri);
        Log.d(TAG, "doInBackground: ends");
        return mNews;
    }

    private String createUri(String criteria) {
        Log.d(TAG, "createUri: starts");

        return Uri.parse(mBaseUrl).buildUpon().build().toString();
    }

    @Override
    public void onDownloadComplete(String data, DownloadStatus status) {
        Log.d(TAG, "onDownloadComplete: starts");
        if (status == DownloadStatus.OK) {
            //mNews = new String();
            Document document = (Document) Jsoup.parse(data);
            if (document != null) {
                mNews = document.getElementsByClass("div-baiviet").outerHtml() + document.getElementsByClass("text-conent").outerHtml();
                Log.d(TAG, "onDownloadComplete: " + mNews);
                //mNews = document.getElementsByClass("div.newsItem").outerHtml();
                //Log.d(TAG, "onDownloadComplete: "+ data);
            }
        }

        if (runningOnSameThread && mCallback != null) {
            mCallback.onDataAvaiable(mNews, status);
        }

        Log.d(TAG, "onDownloadComplete: ends");
    }
}
