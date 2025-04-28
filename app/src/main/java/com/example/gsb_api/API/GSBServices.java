package com.example.gsb_api.API;

import com.example.gsb_api.Model.Motif;
import com.example.gsb_api.Model.Praticien;
import com.example.gsb_api.Model.Visite;
import com.example.gsb_api.Model.Visiteur;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GSBServices {
    @POST("/api/visiteurs/login")
    Call<Visiteur> login(@Body Map<String, String> credentials);

    @GET("/api/visiteurs/{id}")
    Call<Visiteur> getVisiteurById(
            @retrofit2.http.Path("id") String id,
            @retrofit2.http.Header("Authorization") String token);

    @GET("/api/praticiens")
    Call<List<Praticien>> getAllPraticiens();

    @GET("/api/praticiens/{id}")
    Call<Praticien> getPraticienById(
            @retrofit2.http.Path("id") String id,
            @retrofit2.http.Header("Authorization") String token);

    @POST("/api/visiteurs/{id}/portefeuille")
    Call<List<Praticien>> addPraticienToPortefeuille(
            @retrofit2.http.Path("id") String id,
            @retrofit2.http.Body Praticien praticien,
            @retrofit2.http.Header("Authorization") String token);

    @POST("/api/praticiens")
    Call<Praticien> createPraticien(
            @retrofit2.http.Header("Authorization") String token, @Body Praticien praticien);

    @GET("motifs")
    Call<List<Motif>> getMotifs();

    @POST("/api/visites")
    Call<Visite> createVisite(
            @retrofit2.http.Header("Authorization") String token, @Body Visite visite);

    @GET("/api/visites/{id}")
    Call<Visite> getVisiteById(
            @retrofit2.http.Header("Authorization") String token, @Path("id") String id);


    @GET("/api/visites/praticien/{praticienId}")
    Call<List<Visite>> getVisitesByPraticien(@Path("praticienId") String praticienId);

    @GET("/api/visites")
    Call<List<Visite>> getVisites();

}
