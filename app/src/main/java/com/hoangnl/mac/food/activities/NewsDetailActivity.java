package com.hoangnl.mac.food.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.hoangnl.mac.food.datas.DownloadStatus;
import com.hoangnl.mac.food.datas.ParseDetail;
import com.hoangnl.mac.food.R;
import com.hoangnl.mac.food.contracts.News;

public class NewsDetailActivity extends BaseActivity implements ParseDetail.OnDataAvaiable {

    private static final String TAG = "NewsDetailActivity";
    private WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        activateToolBar(true);

        Intent intent = getIntent();
        News news = (News) intent.getSerializableExtra("PHOTO_TRANSFER");
        if (news != null) {
            wv = (WebView) findViewById(R.id.wvNews);
            final ParseDetail parseContent = new ParseDetail(this, news.getLink());
            parseContent.execute(news.getLink());

        }
    }

    @Override
    public void onDataAvaiable(String data, DownloadStatus status) {
        Log.d(TAG, "onDataAvaiable: starts");
        if (status == DownloadStatus.OK) {
            if(wv!= null) {
                Log.d(TAG, "Data: " + data);
                // For API level below 18 (This method was deprecated in API level 18)
                wv.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);

                wv.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
                if (Build.VERSION.SDK_INT >= 19) {
                    wv.setLayerType(View.LAYER_TYPE_HARDWARE, null);
                }
                else {
                    wv.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
                }
                wv.loadDataWithBaseURL("file:///android_asset/.", data, "text/html", "UTF-8", null);
            }
        } else {
            Log.d(TAG, "onDownloadComplete: onDownloadData failed with status " + status);
        }
        Log.d(TAG, "onDataAvaiable: ends");
    }
}
