package com.getbyte.getbyteprueba.Service;

import com.getbyte.getbyteprueba.Model.Graficos;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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

    //crear usuario en conjunto con un catador experto
    @Multipart
    @POST("/api/v1/catador")
    Call<ResponseMessage> createCalidad(@Part("Producto") String Producto,
                                        @Part("Nombre_Científico") String Nombre_Científico,
                                        @Part("Temperatura") Integer Temperatura,
                                        @Part("HR_Conservación") Integer HR_Conservación,
                                        @Part("Marca") Integer Marca,
                                        @Part("Presentación") Integer Presentación,
                                        @Part("Dimensión") Integer Dimensión,
                                        @Part("Material") Integer Material,
                                        @Part("Etiqueta_trazabilidad") Integer Etiqueta_trazabilidad,
                                        @Part("Peso_neto") Integer Peso_neto,
                                        @Part("Cajas_parihuela_aerea") Integer Cajas_parihuela_aerea,
                                        @Part("Cajas_parihuela_Marítima") Integer Cajas_parihuela_Marítima,
                                        @Part("Dimensiones_material_parihuela") Integer Dimensiones_material_parihuela,

                                        @Part MultipartBody.Part imagen);
}
