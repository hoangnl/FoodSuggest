package com.hoangnl.mac.food.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoangnl.mac.food.R;
import com.hoangnl.mac.food.contracts.News;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by mac on 4/18/17.
 */

public class NewsRecycleAdapter extends RecyclerView.Adapter<NewsRecycleAdapter.ImageViewHolder> {
    private static final String TAG = "NewsRecycleAdapter";
    private List<News> mNewsList;
    private Context mContext;

    public NewsRecycleAdapter(Context context, List<News> NewsList) {
        mContext = context;
        mNewsList = NewsList;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: new view requests");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.browse, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        if (mNewsList == null || mNewsList.size() == 0) {
            Log.d(TAG, "onBindViewHolder: empty News");
            holder.thumbnail.setImageResource(R.drawable.ic_one);
            holder.title.setText(R.string.empty_news);
        } else {
            News newsItem = mNewsList.get(position);
            Log.d(TAG, "onBindViewHolder: " + newsItem.getTitle() + "-->" + position);
            Picasso.with(mContext).load(newsItem.getImage()).
                    error(R.drawable.common_full_open_on_phone).placeholder(R.drawable.ic_one).into(holder.thumbnail);
            holder.title.setText(newsItem.getTitle());

        }
    }

    @Override
    public int getItemCount() {
        return ((mNewsList != null) && (mNewsList.size() != 0)) ? mNewsList.size() : 1;
    }

    public void loadNewsData(List<News> newsList) {
        mNewsList.addAll(newsList);
        notifyDataSetChanged();
    }

    public News getNews(int postion) {
        return ((mNewsList != null) && mNewsList.size() != 0) ? mNewsList.get(postion) : null;
    }

    static class ImageViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "ImageViewHolder";
        ImageView thumbnail = null;
        TextView title = null;

        public ImageViewHolder(View itemView) {
            super(itemView);
            Log.d(TAG, "FlickrImageViewHolder: starts");
            this.thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            this.title = (TextView) itemView.findViewById(R.id.title);
        }
    }
}