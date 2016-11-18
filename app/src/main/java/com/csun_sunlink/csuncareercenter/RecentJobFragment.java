package com.csun_sunlink.csuncareercenter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;


/**
 * Created by olgak on 11/7/16.
 */

public class RecentJobFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for the Recent Job Posts fragment
        return inflater.inflate(R.layout.recent_jobs_fragment, container, false);
    }
}