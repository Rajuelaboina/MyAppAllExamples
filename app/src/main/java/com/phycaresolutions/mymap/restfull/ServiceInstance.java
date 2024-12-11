package com.phycaresolutions.mymap.restfull;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceInstance {
    public static String BASE_URL ="https://reqres.in";

    public static Retrofit retrofit;

    public static Retrofit getRetroFitInstance(){
         retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;

    }
}
