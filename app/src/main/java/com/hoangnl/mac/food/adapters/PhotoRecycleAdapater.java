package com.hoangnl.mac.food.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoangnl.mac.food.R;
import com.hoangnl.mac.food.contracts.Photo;

import java.util.List;

/**
 * Created by mac on 4/18/17.
 */

public class PhotoRecycleAdapater extends RecyclerView.Adapter<PhotoRecycleAdapater.ImageViewHolder> {
    private static final String TAG = "PhotoRecycleAdapter";
    private List<Photo> mPhotoList;
    private Context mContext;

    public PhotoRecycleAdapater(Context context, List<Photo> photoList) {
        mContext = context;
        mPhotoList = photoList;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: new view requests");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.browse, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        if (mPhotoList == null || mPhotoList.size() == 0) {
            Log.d(TAG, "onBindViewHolder: empty photo");
            holder.thumbnail.setImageResource(R.drawable.ic_one);
            holder.title.setText(R.string.empty_photo);
        } else {
            Photo photoItem = mPhotoList.get(position);
            Log.d(TAG, "onBindViewHolder: " + photoItem.getTitle() + "-->" + position);
//            Picasso.with(mContext).load(photoItem.getImage()).
//                    error(R.drawable.common_full_open_on_phone).placeholder(R.drawable.ic_one).into(holder.thumbnail);
            holder.title.setText(photoItem.getTitle());

            String uri = "@drawable/" + photoItem.getImage();
            int imageResource = mContext.getResources().getIdentifier(uri, null, mContext.getPackageName());
            Drawable res = mContext.getResources().getDrawable(imageResource);
            holder.thumbnail.setImageDrawable(res);

        }
    }

    @Override
    public int getItemCount() {
        return ((mPhotoList != null) && (mPhotoList.size() != 0)) ? mPhotoList.size() : 1;
    }

    void loadNewData(List<Photo> newPhotos) {
        mPhotoList = newPhotos;
        notifyDataSetChanged();
    }

    public Photo getPhoto(int postion) {
        return ((mPhotoList != null) && mPhotoList.size() != 0) ? mPhotoList.get(postion) : null;
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