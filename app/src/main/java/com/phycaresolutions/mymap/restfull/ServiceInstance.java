package com.phycaresolutions.mymap.restfull;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceInstance {
    public static String BASE_URL ="https://reqres.in";

    public static Retrofit retrofit;

    public static Retrofit getRetroFitInstance(){
     /* HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
      httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
     OkHttpClient okHttpClient = new OkHttpClient.Builder()
             .readTimeout(60, TimeUnit.SECONDS)
             .connectTimeout(60,TimeUnit.MINUTES)
             .addInterceptor(httpLoggingInterceptor)
             .build();*/

         retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in")
                 //.client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;

    }
}
