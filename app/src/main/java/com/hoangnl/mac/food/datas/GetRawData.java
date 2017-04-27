package com.hoangnl.mac.food.datas;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

/**
 * Created by mac on 1/15/17.
 */

class GetRawData extends AsyncTask<String, Void, String> {
    private static final String TAG = "GetRawData";

    private final OnDownloadComplete mCallback;
    private DownloadStatus mDownloadStatus;

    interface OnDownloadComplete {
        void onDownloadComplete(String data, DownloadStatus status);
    }

    public GetRawData(OnDownloadComplete callback) {
        this.mDownloadStatus = DownloadStatus.IDLE;
        this.mCallback = callback;

    }


    @Override
    protected void onPostExecute(String s) {
        Log.d(TAG, "onPostExecute: parameter = " + s);
        if (mCallback != null) {
            mCallback.onDownloadComplete(s, mDownloadStatus);
        }
        Log.d(TAG, "onPostExecute: ends");
    }

    @Override
    protected String doInBackground(String... strings) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        if (strings == null) {
            mDownloadStatus = DownloadStatus.NOT_INTIALISED;
        }

        try {
            mDownloadStatus = DownloadStatus.PROCESSING;
            String result = Jsoup.connect(strings[0]).userAgent(
                    "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/46.0.1").get().toString();
            mDownloadStatus = DownloadStatus.OK;
            return result.toString();
        } catch (MalformedURLException e) {
            Log.d(TAG, "doInBackground: Invalid url: " + e.getMessage());
        } catch (IOException e) {
            Log.d(TAG, "doInBackground: IO Exception " + e.getMessage());

        } catch (SecurityException e) {
            Log.d(TAG, "doInBackground: security permssion. Need permission" + e.getMessage());
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.d(TAG, "doInBackground: Error closing stream " + e.getMessage());
                }
            }

        }
        mDownloadStatus = DownloadStatus.FAILED_OR_EMPTY;
        return null;
    }

    void runInSameThread(String s) {
        Log.d(TAG, "runInSameThread: starts");

        onPostExecute(doInBackground(s));
        Log.d(TAG, "runInSameThread: ends");
    }
}
