package com.hoangnl.mac.food.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hoangnl.mac.food.adapters.NewsRecycleAdapter;
import com.hoangnl.mac.food.adapters.RecyclerItemClickListener;
import com.hoangnl.mac.food.datas.DownloadStatus;
import com.hoangnl.mac.food.datas.ParseContent;
import com.hoangnl.mac.food.R;
import com.hoangnl.mac.food.activities.NewsDetailActivity;
import com.hoangnl.mac.food.adapters.EndlessRecyclerViewScrollListener;
import com.hoangnl.mac.food.contracts.News;

import java.util.ArrayList;
import java.util.List;


public class ThirdFragment extends Fragment implements ParseContent.OnDataAvaiable, RecyclerItemClickListener.OnRecyclerClickListener {

    private static final String TAG = "ThirdFragment";
    private NewsRecycleAdapter mNewsRecycleAdapter;
    private String URL = "http://www.24h.com.vn/ajax/box_bai_viet_trang_su_kien/index/460/2552/1/13/0";
    private EndlessRecyclerViewScrollListener scrollListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_main, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this.getContext(), recyclerView, this));

        mNewsRecycleAdapter = new NewsRecycleAdapter(this.getActivity(), new ArrayList<News>());
        recyclerView.setAdapter(mNewsRecycleAdapter);

        final ParseContent parseContent = new ParseContent(this, URL, 1);
        parseContent.execute(URL);

        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                //loadNextDataFromApi(page);
                Log.d(TAG, "onLoadMore: page -->" + page);
                ParseContent parseContent = new ParseContent(ThirdFragment.this, URL, page);
                parseContent.execute(URL);

            }
        };
        // Adds the scroll listener to RecyclerView
        recyclerView.addOnScrollListener(scrollListener);

        return view;
    }

    public void loadNextDataFromApi(int offset) {
        // Send an API request to retrieve appropriate paginated data
        //  --> Send the request including an offset value (i.e `page`) as a query parameter.
        //  --> Deserialize and construct new model objects from the API response
        //  --> Append the new data objects to the existing set of items inside the array of items
        //  --> Notify the adapter of the new items made with `notifyItemRangeInserted()`
    }

    @Override
    public void onDataAvaiable(List<News> data, DownloadStatus status) {
        Log.d(TAG, "onDataAvaiable: starts");
        if (status == DownloadStatus.OK) {
            mNewsRecycleAdapter.loadNewsData(data);
        } else {
            Log.d(TAG, "onDownloadComplete: onDownloadData failed with status " + status);
        }
        Log.d(TAG, "onDataAvaiable: ends");
    }


    @Override
    public void onItemClick(View view, int position) {
        Log.d(TAG, "onItemClick: " + position);
        Intent intent = new Intent(this.getActivity(), NewsDetailActivity.class);
        intent.putExtra("PHOTO_TRANSFER", mNewsRecycleAdapter.getNews(position));
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(View view, int position) {
        Log.d(TAG, "onItemLongClick: " + position);
    }
}
