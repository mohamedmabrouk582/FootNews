package com.example.moham.footnews.view;

import com.example.moham.footnews.data.model.Results;

public interface ArticlesView extends BaseView {
    void error(String msg);
    void reponse(Results results);
}
