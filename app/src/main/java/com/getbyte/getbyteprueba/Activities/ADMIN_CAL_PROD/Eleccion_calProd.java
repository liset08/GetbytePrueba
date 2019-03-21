package com.getbyte.getbyteprueba.Activities.ADMIN_CAL_PROD;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.getbyte.getbyteprueba.Activities.BPM.MenuBPMActivity;
import com.getbyte.getbyteprueba.Activities.EvaluacionEMainActivity;
import com.getbyte.getbyteprueba.R;

public class Eleccion_calProd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleccion_cal_prod);
    }
    public void Calidad(View view) {
        Intent intent = new Intent(this, CalidadModulo.class);
        startActivity(intent);
    }
    public void Producción(View view) {
        Intent intent = new Intent(this, ProduccionModulo.class);
        startActivity(intent);
    }
}
