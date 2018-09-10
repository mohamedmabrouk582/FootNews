package com.example.moham.footnews.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.example.moham.footnews.R;
import com.example.moham.footnews.data.model.Article;
import com.example.moham.footnews.databinding.ArticleItemViewBinding;
import com.example.moham.footnews.BR;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.Holder> {
    int pos;
    private List<Article> data;

    public interface ArticlesListener {
        void onClick(Article item, int pos);
    }

    private ArticlesListener listener;

    public ArticlesAdapter() {
        data = new ArrayList<>();
    }

    public void setListener(ArticlesListener listener) {
        this.listener = listener;
    }

    public void setData(List<Article> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void addItem(Article item) {
        data.add(item);
        // notifyDataSetChanged();
        notifyItemInserted(data.size() - 1);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ArticleItemViewBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.article_item_view, parent, false);
        return new Holder(binding);
    }

    public int currentPos() {
        return pos;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        pos = position;
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        ArticleItemViewBinding itemViewBinding;

        public Holder(ArticleItemViewBinding itemViewBinding) {
            super(itemViewBinding.getRoot());
            this.itemViewBinding = itemViewBinding;

        }

        public void bind(Article product) {
            itemViewBinding.setVariable(BR.art, product);
            itemViewBinding.executePendingBindings();
            itemViewBinding.getRoot().setOnClickListener((v) ->
                    listener.onClick(product, getAdapterPosition())
            );
        }
    }
}