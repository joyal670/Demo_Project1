package com.demoproject.ApiInterface;

import com.demoproject.ApiModel.Model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Url;

public interface Interface
{
    @GET("?route=api/common")
    Call<Model> getProducts();
}
