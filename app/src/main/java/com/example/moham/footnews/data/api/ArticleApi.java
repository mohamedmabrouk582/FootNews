package com.example.moham.footnews.data.api;

import com.example.moham.footnews.data.model.Results;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ArticleApi {
    @GET("v2/everything")
    Observable<Results> getArticles(@Query("q") String query,@Query("apiKey") String apiKey, @Query("pageSize") int pageSize, @Query("page") int page);
}
