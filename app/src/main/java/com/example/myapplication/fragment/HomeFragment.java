package com.example.myapplication.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeFragment extends Fragment {

    private Context mContext;

    public HomeFragment() {

    }

    public HomeFragment(Context context) {
        // Required empty public constructor
        this.mContext = context;

    }
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        RecyclerView rvList = view.findViewById(R.id.rvList);

        OkHttpClient client = new OkHttpClient();

        // Build the request
        Request request = new Request.Builder()
                .url("https://api.github.com/users") // Replace with your URL
                .build();

        // Execute the request asynchronously
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, IOException e) {
                // Handle request failure
                e.printStackTrace();
                System.out.println("Request failed: " + e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    // Handle the response
                    String result =  response.body().string();

                    // Create a Gson instance
                    Gson gson = new Gson();

                    // Define the Type for List<Entity>
                    Type listType = new TypeToken<List<Person>>() {}.getType();

                    // Convert JSON string to List<Entity>
                    List<Person> entityList = gson.fromJson(result, listType);
                    // Now update the UI on the main thread
                    if (getActivity() != null) {

                        getActivity().runOnUiThread(() -> {
                        rvList.setLayoutManager(new LinearLayoutManager(mContext));
                        rvList.setAdapter(new PersonAdapter(mContext, entityList));
                    });
                        }

                } else {
                    // Handle unsuccessful response
                    System.out.println("Response failed with code: " + response.code());
                }
            }
        });
    }
}
