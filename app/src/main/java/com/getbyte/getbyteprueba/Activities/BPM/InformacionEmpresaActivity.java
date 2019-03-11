package com.getbyte.getbyteprueba.Activities.BPM;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.getbyte.getbyteprueba.R;

public class InformacionEmpresaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_empresa);

    }
    public void GoOption(View view) {
        Intent intent = new Intent(this, RestauranteMenuActivity.class);
        startActivity(intent);
    }
}
