package com.getbyte.getbyteprueba.Service;

import com.getbyte.getbyteprueba.Model.Calidad;
import com.getbyte.getbyteprueba.Model.Graficos;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
    @POST("/api/v1/calidad")
    Call<ResponseMessage> createCalidad(@Part("Producto") RequestBody Producto,
                                        @Part("Nombre_Cientifico") RequestBody  Nombre_Cientifico,
                                        @Part("Temperatura") RequestBody  Temperatura,
                                        @Part("HR_Conservacion") RequestBody  HR_Conservacion,
                                        @Part("Marca") RequestBody  Marca,
                                        @Part("Presentacion") RequestBody  Presentacion,
                                        @Part("Dimension") RequestBody  Dimension,
                                        @Part("Material") RequestBody  Material,
                                        @Part("Etiqueta_trazabilidad") RequestBody  Etiqueta_trazabilidad,
                                        @Part("Peso_neto") RequestBody  Peso_neto,
                                        @Part("Cajas_parihuela_aerea") RequestBody  Cajas_parihuela_aerea,
                                        @Part("Cajas_parihuela_Maritima") RequestBody  Cajas_parihuela_Maritima,
                                        @Part("Dimensiones_material_parihuela") RequestBody  Dimensiones_material_parihuela,

                                        @Part MultipartBody.Part imagen);

    @GET("api/v1/calidad/{id}")
    Call<List<Calidad>> findCalidad(@Path("id") Integer id);

}
