package com.example.moham.footnews.data.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.moham.footnews.BR;
import com.example.moham.footnews.R;

public class Article extends BaseObservable{
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private Source source;


    @BindingAdapter("loadImage")
    public static void loadImage(ImageView view,String urlToImage){
        Glide.with(view.getContext())
                .load(urlToImage)
                .apply(RequestOptions.placeholderOf(R.drawable.img))
                .into(view);
    }

    @Bindable
    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
        notifyPropertyChanged(BR.source);
    }

    @Bindable
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
        notifyPropertyChanged(BR.author);
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    @Bindable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        notifyPropertyChanged(BR.description);
    }

    @Bindable
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        notifyPropertyChanged(BR.url);
    }

    @Bindable
    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
        notifyPropertyChanged(BR.urlToImage);
    }

    @Bindable
    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
        notifyPropertyChanged(BR.publishedAt);
    }
}
