package com.getbyte.getbyteprueba.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.getbyte.getbyteprueba.Activities.ADMIN_CAL_PROD.Eleccion_calProd;
import com.getbyte.getbyteprueba.Activities.BPM.MenuBPMActivity;
import com.getbyte.getbyteprueba.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void Registro(View view) {
        Intent intent = new Intent(this, EvaluacionEMainActivity.class);
        startActivity(intent);
    }
    public void BpmMenu(View view) {
        Intent intent = new Intent(this, MenuBPMActivity.class);
        startActivity(intent);
    }
    public void MenuCalidad(View view) {
        Intent intent = new Intent(this, Eleccion_calProd.class);
        startActivity(intent);
    }
}
