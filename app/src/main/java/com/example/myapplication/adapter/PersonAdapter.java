package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.activity.DetailActivity;
import com.example.myapplication.activity.MainActivity;
import com.example.myapplication.model.Person;

import java.io.Serializable;
import java.util.List;

public class PersonAdapter  extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {
    private List<Person> entityList;
    private Context mContext;

    // Constructor
    public PersonAdapter(Context context, List<Person> entityList) {
        this.entityList = entityList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_person, parent, false);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        Person entity = entityList.get(position);
        holder.tvId.setText(String.valueOf(entity.getId()));
        holder.tvLogin.setText(entity.getLogin());
        Glide.with(this.mContext)
                .load(entity.getAvatarUrl())
                .placeholder(R.drawable.ic_launcher_foreground) // Optional placeholder while loading
                .error(R.drawable.ic_launcher_foreground) // Optional error image
                .into(holder.ivAvatar);
        holder.llRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("PERSON", (Serializable) entity);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return entityList.size();
    }

    // ViewHolder class
    static class PersonViewHolder extends RecyclerView.ViewHolder {
        TextView tvId, tvLogin;
        ImageView ivAvatar;
        LinearLayout llRow;

        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvId);
            tvLogin = itemView.findViewById(R.id.tvLogin);
            ivAvatar = itemView.findViewById(R.id.ivAvatar);
            llRow = itemView.findViewById(R.id.llRow);
        }
    }
}
