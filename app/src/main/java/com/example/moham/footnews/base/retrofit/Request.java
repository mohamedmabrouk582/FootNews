package com.example.moham.footnews.base.retrofit;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;


import com.example.moham.footnews.R;
import com.example.moham.footnews.base.network.CheckNetwork;
import com.google.gson.JsonSyntaxException;

import javax.net.ssl.SSLHandshakeException;

import io.reactivex.observers.DisposableObserver;
import retrofit2.HttpException;

public class Request<data> extends DisposableObserver<data>{
    private RequestListener<data> requestListener;
    private Context context;
    public Request(Context context,RequestListener<data> listener){
        this.context=context;
        this.requestListener=listener;
    }
    @Override
    public void onNext(data data) {
     if (data!=null){
         MutableLiveData<data> liveData=new MutableLiveData<>();
         liveData.setValue(data);
         requestListener.onResponse(liveData);
     }else {
         requestListener.onEmptyData("No Data Found");
     }
    }

    @Override
    public void onError(Throwable e) {
        if (!CheckNetwork.isConnected(context)) {
            Log.d("request","onNetWorkError");
            requestListener.onNetWorkError("No Internet Connection");
        } else {
            if (e instanceof HttpException) {
                int code = ((HttpException) e).code();
                Log.d("request","code : "+code);
                if (code == 401 ) {
                    Log.d("request","400");
                    requestListener.onFailed(e.getMessage());
                }else if (code == 400){
                    requestListener.onFailed(e.getMessage());
                }else if (code == 504) {
                    Log.d("request","504");
                    requestListener.onFailed(e.getMessage());
                } else if (code==404){
                    Log.d("request","404");
                    requestListener.onFailed(e.getMessage());
                }else {
                    Log.d("request",code+"");
                    requestListener.onFailed(e.getMessage());
                }
            } else if (e instanceof java.net.SocketTimeoutException) {
                Log.d("request","socketTimeout");
                requestListener.onSessionExpired(context.getString(R.string.socketTimeout));
            }
            else if (e instanceof JsonSyntaxException) {
                Log.d("request","JsonSyntaxException");

                requestListener.onResponseError(context.getString(R.string.jsonError));
            }
            else if (e instanceof SSLHandshakeException) {
                Log.d("request","SSLHandshakeException");
                requestListener.onResponseError(context.getString(R.string.connectionError));
            }
            else {
                    Log.d("request", "Throwable " + e.getMessage());
                requestListener.onResponseError(context.getString(R.string.serverError));
            }
        }
    }

    @Override
    public void onComplete() {
       requestListener.onComplete();
    }
}
