package com.phycaresolutions.mymap.restfull;

import com.phycaresolutions.mymap.User;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;

public interface ApiService {
  @GET("/api/users")
  Call<Details> getAllUsers();
}
