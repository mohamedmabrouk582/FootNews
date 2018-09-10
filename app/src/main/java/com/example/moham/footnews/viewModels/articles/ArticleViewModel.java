package com.example.moham.footnews.viewModels.articles;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProvider;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.support.v7.widget.SearchView;

import com.example.moham.footnews.base.retrofit.Request;
import com.example.moham.footnews.base.retrofit.RequestListener;
import com.example.moham.footnews.data.api.ArticleApi;
import com.example.moham.footnews.data.model.Results;
import com.example.moham.footnews.ui.fragment.ArticleFragment;
import com.example.moham.footnews.view.ArticlesView;
import com.example.moham.footnews.viewModels.base.BaseViewModel;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ArticleViewModel<v extends ArticlesView> extends BaseViewModel<v> implements ArticlesVmodel<v> {
    private ArticleApi api;
    private ArticleFragment fragment;
    public ObservableField<String> mQuery=new ObservableField<>();
    public ArticleViewModel(@NonNull ArticleFragment application, ArticleApi api) {
        super(application.getActivity().getApplication());
        this.api=api;
        this.fragment=application;
    }

    public ObservableField<String> getQuery() {
        return mQuery;
    }


    @BindingAdapter("setQueryTextListener")
    public static void setQueryTextListener(SearchView view,SearchView.OnQueryTextListener textListener){
        view.setOnQueryTextListener(textListener);
    }
    public SearchView.OnQueryTextListener listener=new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            mQuery.set(query);
            reqArticles(query,30,1);

            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };

    @Override
    public void reqArticles(String query, int pageSize, int page) {
        fragment.showLoader();
     api.getArticles(query,"91e82f0b9a834fc58c040b731536fe70",pageSize,page)
             .observeOn(AndroidSchedulers.mainThread())
             .subscribeOn(Schedulers.io())
             .subscribe(new Request<>(fragment.getContext(), new RequestListener<Results>() {
                 @Override
                 public void onResponse(LiveData<Results> data) {
                     data.observe(fragment,e ->{
                                if (e.getArticles().size()==0){
                                    onEmptyData("No Data Found");
                                }else {
                                    getView().reponse(e);
                                }
                             }
                     );
                 }

                 @Override
                 public void onFailed(String msg) {
                    getView().error(msg);
                 }

                 @Override
                 public void onEmptyData(String msg) {
                     getView().error(msg);

                 }

                 @Override
                 public void onSessionExpired(String msg) {
                     getView().error(msg);

                 }

                 @Override
                 public void onResponseError(String msg) {
                     getView().error(msg);

                 }

                 @Override
                 public void onNetWorkError(String msg) {
                     getView().error(msg);

                 }

                 @Override
                 public void onComplete() { }
             }));
    }

    public static class ArticleViewModelFactory implements ViewModelProvider.Factory{
        private ArticleApi api;
        private ArticleFragment fragment;

        public ArticleViewModelFactory(ArticleApi api, ArticleFragment fragment) {
            this.api = api;
            this.fragment = fragment;
        }

        @NonNull
        @Override
        public  ArticleViewModel create(@NonNull Class modelClass) {
            return new ArticleViewModel(fragment,api);
        }
    }
}
