package com.example.moham.footnews.di.components;

import com.example.moham.footnews.di.modules.ArticlesFragmentModule;
import com.example.moham.footnews.di.scopes.ArticlesFragmentScope;
import com.example.moham.footnews.ui.fragment.ArticleFragment;

import dagger.Component;

@ArticlesFragmentScope
@Component(dependencies = AppComponent.class,modules = ArticlesFragmentModule.class)
public interface ArticlesFragmentComponent {
    void inject(ArticleFragment fragment);
}
