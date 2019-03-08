package com.example.assignment1.data.remote;

import com.example.assignment1.data.model.post;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {
    @POST("/posts")
    @FormUrlEncoded
    Call<post> savePost(@Field("id")long id,
                        @Field("name") String name,
                        @Field("token") String token);
}
