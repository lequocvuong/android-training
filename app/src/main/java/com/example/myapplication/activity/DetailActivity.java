package com.example.myapplication.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.adapter.PersonAdapter;
import com.example.myapplication.model.Person;
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

public class DetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);

        initView();
    }

    private void initView() {
        TextView tvLogin = findViewById(R.id.tvLogin);
        TextView tvId = findViewById(R.id.tvId);
        ImageView ivAvatar = findViewById(R.id.ivAvatar);

        if (getIntent() != null) {
            if (getIntent().getSerializableExtra("PERSON") != null) {
                Person person = ((Person) getIntent().getSerializableExtra("PERSON"));

                if(person != null) {
                    tvLogin.setText(person.getLogin());
                    tvId.setText(String.valueOf(person.getId()));
                    Glide.with(DetailActivity.this)
                            .load(person.getAvatarUrl())
                            .placeholder(R.drawable.ic_launcher_foreground) // Optional placeholder while loading
                            .error(R.drawable.ic_launcher_foreground) // Optional error image
                            .into(ivAvatar);
                }

            }
        }
    }
}
