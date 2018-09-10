package com.example.moham.footnews.viewModels.base;

import com.example.moham.footnews.view.BaseView;

public interface BaseVmodel<v extends BaseView> {
    void attachView(v view);
}
