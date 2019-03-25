package com.getbyte.getbyteprueba.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import okhttp3.RequestBody;

public class Calidad {

        @SerializedName("id_calidad")
        @Expose
        private Integer idCalidad;
        @SerializedName("Producto")
        @Expose
        private String producto;
        @SerializedName("Nombre_Cientifico")
        @Expose
        private String nombreCientifico;
        @SerializedName("Temperatura")
        @Expose
        private String temperatura;
        @SerializedName("HR_Conservacion")
        @Expose
        private Integer hRConservacion;
        @SerializedName("Marca")
        @Expose
        private String marca;
        @SerializedName("Presentacion")
        @Expose
        private String presentacion;
        @SerializedName("Dimension")
        @Expose
        private String dimension;
        @SerializedName("Material")
        @Expose
        private String material;
        @SerializedName("Etiqueta_trazabilidad")
        @Expose
        private String etiquetaTrazabilidad;
        @SerializedName("Peso_neto")
        @Expose
        private String pesoNeto;
        @SerializedName("Cajas_parihuela_aerea")
        @Expose
        private String cajasParihuelaAerea;
        @SerializedName("Cajas_parihuela_Maritima")
        @Expose
        private String cajasParihuelaMaritima;
        @SerializedName("Dimensiones_material_parihuela")
        @Expose
        private String dimensionesMaterialParihuela;
        @SerializedName("imagen")
        @Expose
        private String imagen;

        public Integer getIdCalidad() {
            return idCalidad;
        }

        public void setIdCalidad(Integer idCalidad) {
            this.idCalidad = idCalidad;
        }

        public Calidad withIdCalidad(Integer idCalidad) {
            this.idCalidad = idCalidad;
            return this;
        }

        public String getProducto() {
            return producto;
        }

        public void setProducto(String producto) {
            this.producto = producto;
        }

        public Calidad withProducto(String producto) {
            this.producto = producto;
            return this;
        }

        public String getNombreCientifico() {
            return nombreCientifico;
        }

        public void setNombreCientifico(String nombreCientifico) {
            this.nombreCientifico = nombreCientifico;
        }

        public Calidad withNombreCientifico(String nombreCientifico) {
            this.nombreCientifico = nombreCientifico;
            return this;
        }

        public String getTemperatura() {
            return temperatura;
        }

        public void setTemperatura(String temperatura) {
            this.temperatura = temperatura;
        }

        public Calidad withTemperatura(String temperatura) {
            this.temperatura = temperatura;
            return this;
        }

        public Integer getHRConservacion() {
            return hRConservacion;
        }

        public void setHRConservacion(Integer hRConservacion) {
            this.hRConservacion = hRConservacion;
        }

        public Calidad withHRConservacion(Integer hRConservacion) {
            this.hRConservacion = hRConservacion;
            return this;
        }

        public String getMarca() {
            return marca;
        }

        public void setMarca(String marca) {
            this.marca = marca;
        }

        public Calidad withMarca(String marca) {
            this.marca = marca;
            return this;
        }

        public String getPresentacion() {
            return presentacion;
        }

        public void setPresentacion(String presentacion) {
            this.presentacion = presentacion;
        }

        public Calidad withPresentacion(String presentacion) {
            this.presentacion = presentacion;
            return this;
        }

        public String getDimension() {
            return dimension;
        }

        public void setDimension(String dimension) {
            this.dimension = dimension;
        }

        public Calidad withDimension(String dimension) {
            this.dimension = dimension;
            return this;
        }

        public String getMaterial() {
            return material;
        }

        public void setMaterial(String material) {
            this.material = material;
        }

        public Calidad withMaterial(String material) {
            this.material = material;
            return this;
        }

        public String getEtiquetaTrazabilidad() {
            return etiquetaTrazabilidad;
        }

        public void setEtiquetaTrazabilidad(String etiquetaTrazabilidad) {
            this.etiquetaTrazabilidad = etiquetaTrazabilidad;
        }

        public Calidad withEtiquetaTrazabilidad(String etiquetaTrazabilidad) {
            this.etiquetaTrazabilidad = etiquetaTrazabilidad;
            return this;
        }

        public String getPesoNeto() {
            return pesoNeto;
        }

        public void setPesoNeto(String pesoNeto) {
            this.pesoNeto = pesoNeto;
        }

        public Calidad withPesoNeto(String pesoNeto) {
            this.pesoNeto = pesoNeto;
            return this;
        }

        public String getCajasParihuelaAerea() {
            return cajasParihuelaAerea;
        }

        public void setCajasParihuelaAerea(String cajasParihuelaAerea) {
            this.cajasParihuelaAerea = cajasParihuelaAerea;
        }

        public Calidad withCajasParihuelaAerea(String cajasParihuelaAerea) {
            this.cajasParihuelaAerea = cajasParihuelaAerea;
            return this;
        }

        public String getCajasParihuelaMaritima() {
            return cajasParihuelaMaritima;
        }

        public void setCajasParihuelaMaritima(String cajasParihuelaMaritima) {
            this.cajasParihuelaMaritima = cajasParihuelaMaritima;
        }

        public Calidad withCajasParihuelaMaritima(String cajasParihuelaMaritima) {
            this.cajasParihuelaMaritima = cajasParihuelaMaritima;
            return this;
        }

        public String getDimensionesMaterialParihuela() {
            return dimensionesMaterialParihuela;
        }

        public void setDimensionesMaterialParihuela(String dimensionesMaterialParihuela) {
            this.dimensionesMaterialParihuela = dimensionesMaterialParihuela;
        }

        public Calidad withDimensionesMaterialParihuela(String dimensionesMaterialParihuela) {
            this.dimensionesMaterialParihuela = dimensionesMaterialParihuela;
            return this;
        }

        public String getImagen() {
            return imagen;
        }

        public void setImagen(String imagen, RequestBody requestFile) {
            this.imagen = imagen;
        }

        public Calidad withImagen(String imagen) {
            this.imagen = imagen;
            return this;
        }

    }

