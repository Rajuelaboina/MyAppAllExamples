package com.phycaresolutions.mymap.restfull;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
  @GET("/api/users")
 Call<Details> getAllUsers();

}
