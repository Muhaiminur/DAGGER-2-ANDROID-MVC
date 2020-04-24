package com.abir.dagger_abir.ADAPTER;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abir.dagger_abir.POST_MODEL;
import com.abir.dagger_abir.R;

import java.util.ArrayList;
import java.util.List;

public class PostRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<POST_MODEL> posts = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_post, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((PostViewHolder) holder).bind(posts.get(position));
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void setPosts(List<POST_MODEL> p) {
        posts = p;
        notifyDataSetChanged();
        Log.d("FROM ADAPTER", posts.size() + "");
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {

        TextView id;
        TextView user_id;
        TextView title;
        TextView body;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.post_id);
            user_id = itemView.findViewById(R.id.post_user_id);
            title = itemView.findViewById(R.id.post_title);
            body = itemView.findViewById(R.id.post_body);
        }

        public void bind(POST_MODEL post) {
            id.setText(post.getId() + "");
            user_id.setText(post.getUserId() + "");
            title.setText(post.getTitle());
            body.setText(post.getBody());
        }
    }
}