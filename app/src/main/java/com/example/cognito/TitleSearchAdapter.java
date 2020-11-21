package com.example.cognito;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cognito.model.TitleSearch;

import java.util.List;

public class TitleSearchAdapter extends RecyclerView.Adapter<TitleSearchAdapter.titleSearchViewHolder> {
    private List<TitleSearch> model;

    public TitleSearchAdapter(List<TitleSearch> model) {
        this.model = model;
    }

    @NonNull
    @Override
    public titleSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_layout_item, parent, false);
        return new titleSearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull titleSearchViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public class titleSearchViewHolder extends RecyclerView.ViewHolder {
        private TextView searchTitle;

        public titleSearchViewHolder(@NonNull View itemView) {
            super(itemView);
            searchTitle = itemView.findViewById(R.id.search_item_title);
        }

        public void bind(Integer position) {
            searchTitle.setText(model.get(position).getTitle());
        }
    }
}
