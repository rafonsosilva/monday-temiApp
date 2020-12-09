package com.gbc.monday.UploadAPI.Service;

import com.gbc.monday.UploadAPI.Model.FormaResponse;
import com.gbc.monday.UploadAPI.Model.RawBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface FormaUploadInterface {
    @POST("tryon")
    Call<FormaResponse> uploadImage(@Body RawBody body);
}
