package com.example.moham.footnews.base.retrofit;

import android.arch.lifecycle.LiveData;

public interface RequestListener<data> {

    void onResponse(LiveData<data> data);
    void onFailed(String msg);
    void onEmptyData(String msg);

    void onSessionExpired(String msg);

    void onResponseError(String msg);

    void onNetWorkError(String msg);

    void onComplete();
}
