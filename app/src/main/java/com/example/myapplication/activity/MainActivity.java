package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.FragmentManager;
import com.example.myapplication.R;
import com.example.myapplication.adapter.PersonAdapter;
import com.example.myapplication.fragment.HomeFragment;
import com.example.myapplication.fragment.ProfileFragment;
import com.example.myapplication.fragment.SearchFragment;
import com.example.myapplication.model.Person;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static final MediaType JSON = MediaType.get("application/json");

    private Fragment homeFragment;
    private Fragment searchFragment;
    private Fragment profileFragment;
    private FragmentManager fm;
    private Fragment active;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initView(savedInstanceState);
    }

    private void initView(Bundle savedInstanceState) {
        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);



        // Handle navigation item selection
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//        navigation.setItemIconTintList(null);

        // get Results from realm

        homeFragment = new HomeFragment(MainActivity.this);
        searchFragment = new SearchFragment();
        profileFragment = new ProfileFragment();
        fm = getSupportFragmentManager();
        active = homeFragment;

        fm.beginTransaction().add(R.id.main_content, profileFragment, "profile").hide(profileFragment).setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
        fm.beginTransaction().add(R.id.main_content, searchFragment, "search").hide(searchFragment).setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
        fm.beginTransaction().add(R.id.main_content, homeFragment, "home").setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();

    }




}
