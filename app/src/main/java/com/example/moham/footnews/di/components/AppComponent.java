package com.example.moham.footnews.di.components;

import com.example.moham.footnews.app.MyApp;
import com.example.moham.footnews.data.api.ArticleApi;
import com.example.moham.footnews.di.modules.ApiModule;
import com.example.moham.footnews.di.modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface AppComponent {
    ArticleApi getArticleApi();
    void inject(MyApp myApp);
}
