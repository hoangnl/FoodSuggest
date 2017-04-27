package com.hoangnl.mac.food.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.hoangnl.mac.food.adapters.ListviewContactAdapter;
import com.hoangnl.mac.food.R;
import com.hoangnl.mac.food.contracts.ListviewContactItem;

import java.util.ArrayList;

public class FirstFragment extends Fragment {

    public FirstFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_first, container, false);

        ArrayList<ListviewContactItem> listContact = GetlistContact();
        ListView lv = (ListView) rootView.findViewById(R.id.lv_contact);
        lv.setAdapter(new ListviewContactAdapter(getActivity(), listContact));

        return rootView;
    }

    private ArrayList<ListviewContactItem> GetlistContact() {
        ArrayList<ListviewContactItem> contactlist = new ArrayList<ListviewContactItem>();

        ListviewContactItem contact = new ListviewContactItem();

        contact.setName("Topher");
        contact.setPhone("01213113568");
        contactlist.add(contact);

        contact = new ListviewContactItem();
        contact.setName("Jean");
        contact.setPhone("01213869102");
        contactlist.add(contact);

        contact = new ListviewContactItem();
        contact.setName("Andrew");
        contact.setPhone("01213123985");
        contactlist.add(contact);

        return contactlist;
    }

}
