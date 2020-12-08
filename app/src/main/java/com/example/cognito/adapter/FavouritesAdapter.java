package com.example.cognito.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cognito.OnClickedListener;
import com.example.cognito.R;

import java.util.List;

public class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.favouritesViewHolder> {
    private List<String> favouriteList;
    private OnClickedListener listener;

    public FavouritesAdapter(List<String> favouriteList, OnClickedListener listener) {
        this.favouriteList = favouriteList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public favouritesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.favourites_layout_item, parent, false);
        return new favouritesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull favouritesViewHolder holder, int position) {
//        holder.favTitle.setText(favouriteList.get(position));
        holder.bind(position);


    }

    @Override
    public int getItemCount() {
        return favouriteList.size();
    }

    public class favouritesViewHolder extends RecyclerView.ViewHolder {
        private TextView favTitle;

        public favouritesViewHolder(@NonNull View itemView) {
            super(itemView);
            favTitle = itemView.findViewById(R.id.favourite_item_title);
        }

        void bind(Integer position) {
            favTitle.setText(favouriteList.get(position));
            itemView.setOnClickListener(v -> {
                listener.onItemClicked(favouriteList.get(position));
            });
        }
    }
}
