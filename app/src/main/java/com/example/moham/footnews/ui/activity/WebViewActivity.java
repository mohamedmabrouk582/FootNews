package com.example.moham.footnews.ui.activity;


import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.moham.footnews.R;
import com.example.moham.footnews.base.activity.BaseActivity;

public class WebViewActivity extends BaseActivity {
    private static String URLFB="url";
    WebView mWebView;
    ProgressBar mProgressBar;
    private String mUri;

    public static void start(Context context, String url){
        Intent intent=new Intent(context,WebViewActivity.class);
        intent.putExtra(URLFB,url);
        context.startActivity(intent);
    }
    @Override
    public int setContentView() {
        return R.layout.web_view_activty;
    }

    @Override
    public void iniViews() {
        mWebView=bind(R.id.fb_web_view);
        mProgressBar=bind(R.id.fb_progress_par);
        mUri=getIntent().getStringExtra(URLFB);
        mProgressBar.setMax(100);
        mWebView.getSettings().setJavaScriptEnabled(true);

        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress==100){
                    mProgressBar.setVisibility(View.GONE);
                }else{
                    mProgressBar.setVisibility(View.VISIBLE);
                    mProgressBar.setProgress(newProgress);
                }
            }


            @Override
            public void onReceivedTitle(WebView view, String title) {
                getSupportActionBar().setTitle(title);
            }
        });

        mWebView.setWebViewClient(new WebViewClient() {
            /******************   determines what will happen when a
             new URL is loaded in the WebView *******************/
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        mWebView.loadUrl(String.valueOf(mUri));
    }

}
