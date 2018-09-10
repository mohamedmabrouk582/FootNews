package com.example.moham.footnews.app;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.FragmentActivity;

import com.example.moham.footnews.R;
import com.example.moham.footnews.di.components.AppComponent;
import com.example.moham.footnews.di.components.DaggerAppComponent;
import com.example.moham.footnews.di.modules.ApiModule;
import com.example.moham.footnews.di.modules.AppModule;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class MyApp extends Application {
   private AppComponent appComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/GreatVibes-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());

        appComponent= DaggerAppComponent.builder()
                .apiModule(new ApiModule("https://newsapi.org/"))
                .appModule(new AppModule(this))
                .build();
        appComponent.inject(this);
    }

    public static MyApp get(Activity activity){
        return (MyApp) activity.getApplication();
    }
    public static MyApp get(FragmentActivity activity){
        return (MyApp) activity.getApplication();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
