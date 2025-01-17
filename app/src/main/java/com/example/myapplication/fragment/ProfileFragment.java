package com.example.myapplication.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

public class ProfileFragment extends Fragment {
    private Context mContext;
    public ProfileFragment() {

    }
    public ProfileFragment(Context context) {
        // Required empty public constructor
        this.mContext = context;

    }
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {

    }
}
