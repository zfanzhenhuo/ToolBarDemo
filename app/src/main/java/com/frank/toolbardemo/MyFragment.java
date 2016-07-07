package com.frank.toolbardemo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;




public class MyFragment extends Fragment {
    private static final String ARG_POSITION = "position";
    private int position;

    public static MyFragment newInstance(int position) {
        MyFragment myFragment = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_POSITION, position);
        myFragment.setArguments(bundle);
        return myFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        position = getArguments().getInt(ARG_POSITION);
        View view = inflater.inflate(R.layout.page, container, false);
        return view;
    }
}
