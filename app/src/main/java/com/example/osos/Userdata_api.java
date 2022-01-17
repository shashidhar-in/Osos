package com.example.osos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Userdata_api {

    @GET("users")
    Call<List<User>> getUsers();

}
