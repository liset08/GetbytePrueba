package com.getbyte.getbyteprueba.Service;

import com.getbyte.getbyteprueba.Model.Graficos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by LISET on 15/01/2019.
 */

public interface UserClient {

    String API_BASE_URL = "https://nexa-api-liset08.c9users.io";


    ///Recursos de los graficos
    @GET("/api/v1/graficos")
    Call<List<Graficos>> getGraf();

    @POST("/api/v1/graficos")
    Call<ResponseMessage> createGraf(@Body Graficos graficos);

    @GET("/api/v1/graficos/{id}")
    Call<Graficos> findGraf(@Path("id") Integer id);
}
