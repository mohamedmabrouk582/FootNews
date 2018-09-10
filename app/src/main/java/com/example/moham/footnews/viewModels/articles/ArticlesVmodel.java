package com.example.moham.footnews.viewModels.articles;

import com.example.moham.footnews.view.ArticlesView;
import com.example.moham.footnews.viewModels.base.BaseVmodel;

public interface ArticlesVmodel<v extends ArticlesView> extends BaseVmodel<v> {
    void reqArticles(String query,int pageSize,int page);
}
