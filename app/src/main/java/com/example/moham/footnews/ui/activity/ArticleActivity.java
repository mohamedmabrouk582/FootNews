package com.example.moham.footnews.ui.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.moham.footnews.R;
import com.example.moham.footnews.ui.fragment.ArticleFragment;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_Container, ArticleFragment.getFragment()).commit();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
