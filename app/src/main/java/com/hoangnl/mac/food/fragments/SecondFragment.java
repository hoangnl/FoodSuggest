package com.hoangnl.mac.food.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hoangnl.mac.food.adapters.PhotoRecycleAdapater;
import com.hoangnl.mac.food.R;
import com.hoangnl.mac.food.contracts.Photo;

import java.util.ArrayList;


public class SecondFragment extends Fragment {

    private PhotoRecycleAdapater mPhotoRecycleAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_main, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mPhotoRecycleAdapter = new PhotoRecycleAdapater(this.getActivity(), getlistPhoto());
        recyclerView.setAdapter(mPhotoRecycleAdapter);
        return view;
    }

    private ArrayList<Photo> getlistPhoto() {
        ArrayList<Photo> photoList = new ArrayList<Photo>();

        photoList.add(new Photo("title 1", "author", "authorId", "", "", "ic_one"));
        photoList.add(new Photo("title 2", "author", "authorId", "", "", "ic_two"));
        photoList.add(new Photo("title 3", "author", "authorId", "", "", "ic_three"));
        return photoList;
    }

}
