package com.example.gsb_api.API;

import com.example.gsb_api.Model.Visiteur;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface GSBServices {
    @POST("/api/visiteurs/login")
    Call<Visiteur> login(@Body Map<String, String> credentials);

}
