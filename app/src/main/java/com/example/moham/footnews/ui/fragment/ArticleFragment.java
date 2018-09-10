package com.example.moham.footnews.ui.fragment;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

import com.example.moham.footnews.R;
import com.example.moham.footnews.app.MyApp;
import com.example.moham.footnews.base.fragment.RequestFragment;
import com.example.moham.footnews.data.api.ArticleApi;
import com.example.moham.footnews.data.model.Article;
import com.example.moham.footnews.data.model.Results;
import com.example.moham.footnews.databinding.ArticlesLayoutBinding;
import com.example.moham.footnews.di.components.ArticlesFragmentComponent;
import com.example.moham.footnews.di.components.DaggerArticlesFragmentComponent;
import com.example.moham.footnews.di.modules.ArticlesFragmentModule;
import com.example.moham.footnews.ui.activity.WebViewActivity;
import com.example.moham.footnews.ui.adapter.ArticlesAdapter;
import com.example.moham.footnews.view.ArticlesView;
import com.example.moham.footnews.viewModels.articles.ArticleViewModel;

import javax.inject.Inject;

public class ArticleFragment extends RequestFragment implements ArticlesView, ArticlesAdapter.ArticlesListener {
    private ArticlesLayoutBinding layoutBinding;
    private ArticleViewModel viewModel;
    private ArticlesAdapter adapter;
    @Inject public ArticleApi api;
    @Inject public ViewModelProvider.Factory factory;

    public static ArticleFragment getFragment(){
        return new ArticleFragment();
    }
    @Override
    public int SetContentView() {
        return R.layout.articles_layout;
    }

    @Override
    public Themes LoaderThemes() {
        return Themes.ChromeFloatingCirclesDrawable;
    }

    @Override
    public void onRetry() {
       reqArt(viewModel.mQuery==null?"mosalah":viewModel.mQuery.get().toString());
    }

    @Override
    public void iniViews() {
        ArticlesFragmentComponent component= DaggerArticlesFragmentComponent
                .builder()
                .articlesFragmentModule(new ArticlesFragmentModule(this))
                .appComponent(MyApp.get(getActivity()).getAppComponent())
                .build();
        component.inject(this);
        adapter=new ArticlesAdapter();
        adapter.setListener(this);
     layoutBinding= DataBindingUtil.bind(content);
        assert layoutBinding != null;
        layoutBinding.articleRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
     viewModel= ViewModelProviders.of(this,factory).get(ArticleViewModel.class);
     viewModel.attachView(this);
     reqArt("mosalah");
     layoutBinding.setArtVm(viewModel);
    }

    private void reqArt(String query){
        viewModel.reqArticles(query,30,1);
    }

    @Override
    public void error(String msg) {
        showErrorView(msg);
    }

    @Override
    public void reponse(Results results) {
        showContent();
        adapter.setData(results.getArticles());
        layoutBinding.articleRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(Article item, int pos) {
        WebViewActivity.start(getContext(),item.getUrl());
    }
}
