package com.example.moham.footnews.di.modules;

import android.arch.lifecycle.ViewModelProvider;

import com.example.moham.footnews.data.api.ArticleApi;
import com.example.moham.footnews.di.scopes.ArticlesFragmentScope;
import com.example.moham.footnews.ui.fragment.ArticleFragment;
import com.example.moham.footnews.viewModels.articles.ArticleViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class ArticlesFragmentModule {
    private ArticleFragment fragment;

    public ArticlesFragmentModule(ArticleFragment fragment) {
        this.fragment = fragment;
    }

    @ArticlesFragmentScope
    @Provides
    public ViewModelProvider.Factory getFactory(ArticleApi api){
        return new ArticleViewModel.ArticleViewModelFactory(api,fragment);
    }

    @ArticlesFragmentScope
    @Provides
    public ArticleFragment getFragment() {
        return fragment;
    }
}
