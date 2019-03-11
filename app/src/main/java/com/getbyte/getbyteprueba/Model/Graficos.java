package com.getbyte.getbyteprueba.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by LISET on 15/01/2019.
 */

public class Graficos implements Serializable {




    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("num_uno")
    @Expose
    private int numUno;
    @SerializedName("num_dos")
    @Expose
    private int numDos;
    @SerializedName("num_tres")
    @Expose
    private int numTres;
    @SerializedName("num_cuatro")
    @Expose
    private int numCuatro;
    @SerializedName("num_cinco")
    @Expose
    private int numCinco;
    private final static long serialVersionUID = 6278929809643667084L;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Graficos withId(int id) {
        this.id = id;
        return this;
    }

    public int getNumUno() {
        return numUno;
    }

    public void setNumUno(int numUno) {
        this.numUno = numUno;
    }

    public Graficos withNumUno(int numUno) {
        this.numUno = numUno;
        return this;
    }

    public int getNumDos() {
        return numDos;
    }

    public void setNumDos(int numDos) {
        this.numDos = numDos;
    }

    public Graficos withNumDos(int numDos) {
        this.numDos = numDos;
        return this;
    }

    public int getNumTres() {
        return numTres;
    }

    public void setNumTres(int numTres) {
        this.numTres = numTres;
    }

    public Graficos withNumTres(int numTres) {
        this.numTres = numTres;
        return this;
    }

    public int getNumCuatro() {
        return numCuatro;
    }

    public void setNumCuatro(int numCuatro) {
        this.numCuatro = numCuatro;
    }

    public Graficos withNumCuatro(int numCuatro) {
        this.numCuatro = numCuatro;
        return this;
    }

    public int getNumCinco() {
        return numCinco;
    }

    public void setNumCinco(int numCinco) {
        this.numCinco = numCinco;
    }

    public Graficos withNumCinco(int numCinco) {
        this.numCinco = numCinco;
        return this;
    }

}