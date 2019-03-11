package com.getbyte.getbyteprueba.Activities.BPM;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.getbyte.getbyteprueba.Activities.LoginActivity;
import com.getbyte.getbyteprueba.R;

public class MenuBPMActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_bpm);
    }
    public void MenRestauran(View view) {
        Intent intent = new Intent(this, InformacionEmpresaActivity.class);
        startActivity(intent);
    }
}
