package com.getbyte.getbyteprueba.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.getbyte.getbyteprueba.R;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.zhouyou.view.seekbar.SignSeekBar;


public class CuartaEvaluacion extends AppCompatActivity {
    private static final String TAG = "CuartaEvaluacion";

    public int n1,n2,n3,n4,n5,promefinal,fin;
    EditText edit_prom;
    String pf1,pf2,pf3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuarta_evaluacion);

        edit_prom = (EditText) findViewById(R.id.etext_pf4);

        //Recibir el pf1
        Intent recibir = getIntent();
        pf1 = recibir.getStringExtra("pf1t");
        pf2 = recibir.getStringExtra("pf2t");
        pf3 = recibir.getStringExtra("pf3t");

        ///
        SignSeekBar signSeekBar = (SignSeekBar) findViewById(R.id.demo_5_seek_bar_1c);
        signSeekBar.getConfigBuilder()
                .min(0)
                .max(5)
                .progress(0)
                .sectionCount(5)
                .sectionTextSize(13)
                .build();
        signSeekBar.setOnProgressChangedListener(new SignSeekBar.OnProgressChangedListener() {
            @Override
            public void onProgressChanged(SignSeekBar signSeekBar, int progress, float progressFloat, boolean fromUser) {
                Log.v(TAG,"NUMLISTENNN........"+progress);
                n4 = progress;
                promefinal = n1+n2+n3+n4;
                fin = promefinal/4;
                edit_prom.setText(Integer.toString(fin));
                Log.v(TAG, "PROMEEDIIOOOOOOO......." + promefinal);
                Log.v(TAG, "PROMEEDIIOOOOOOOffff......." + fin);
            }

            @Override
            public void getProgressOnActionUp(SignSeekBar signSeekBar, int progress, float progressFloat) {

            }

            @Override
            public void getProgressOnFinally(SignSeekBar signSeekBar, int progress, float progressFloat, boolean fromUser) {


            }
        });
        SignSeekBar signSeekBar2 = (SignSeekBar) findViewById(R.id.demo_5_seek_bar_2c);
        signSeekBar2.getConfigBuilder()
                .min(0)
                .max(5)
                .progress(0)
                .sectionCount(5)
                .sectionTextSize(13)
                .build();
        signSeekBar2.setOnProgressChangedListener(new SignSeekBar.OnProgressChangedListener() {
            @Override
            public void onProgressChanged(SignSeekBar signSeekBar, int progress, float progressFloat, boolean fromUser) {
                Log.v(TAG,"NUMLISTENNN........"+progress);
                n1 = progress;
                promefinal = n1+n2+n3+n4;
                fin = promefinal/4;
                edit_prom.setText(Integer.toString(fin));
                Log.v(TAG, "PROMEEDIIOOOOOOO......." + promefinal);
                Log.v(TAG, "PROMEEDIIOOOOOOOffff......." + fin);
            }

            @Override
            public void getProgressOnActionUp(SignSeekBar signSeekBar, int progress, float progressFloat) {

            }

            @Override
            public void getProgressOnFinally(SignSeekBar signSeekBar, int progress, float progressFloat, boolean fromUser) {


            }
        });
        SignSeekBar signSeekBar3 = (SignSeekBar) findViewById(R.id.demo_5_seek_bar_4c);
        signSeekBar3.getConfigBuilder()
                .min(0)
                .max(5)
                .progress(0)
                .sectionCount(5)
                .sectionTextSize(13)
                .build();
        signSeekBar3.setOnProgressChangedListener(new SignSeekBar.OnProgressChangedListener() {
            @Override
            public void onProgressChanged(SignSeekBar signSeekBar, int progress, float progressFloat, boolean fromUser) {
                Log.v(TAG,"NUMLISTENNN........"+progress);
                n2 = progress;
                promefinal = n1+n2+n3+n4;
                fin = promefinal/4;
                edit_prom.setText(Integer.toString(fin));
                Log.v(TAG, "PROMEEDIIOOOOOOO......." + promefinal);
                Log.v(TAG, "PROMEEDIIOOOOOOOffff......." + fin);
            }

            @Override
            public void getProgressOnActionUp(SignSeekBar signSeekBar, int progress, float progressFloat) {

            }

            @Override
            public void getProgressOnFinally(SignSeekBar signSeekBar, int progress, float progressFloat, boolean fromUser) {


            }
        });

        SignSeekBar signSeekBar4 = (SignSeekBar) findViewById(R.id.demo_5_seek_bar_5c);
        signSeekBar4.getConfigBuilder()
                .min(0)
                .max(5)
                .progress(0)
                .sectionCount(5)
                .sectionTextSize(13)
                .build();

        signSeekBar4.setOnProgressChangedListener(new SignSeekBar.OnProgressChangedListener() {
            @Override
            public void onProgressChanged(SignSeekBar signSeekBar, int progress, float progressFloat, boolean fromUser) {
                Log.v(TAG,"NUMLISTENNN........"+progress);
                n3 = progress;

                promefinal = n1+n2+n3+n4;
                fin = promefinal/4;
                edit_prom.setText(Integer.toString(fin));
                Log.v(TAG, "PROMEEDIIOOOOOOO......." + promefinal);
                Log.v(TAG, "PROMEEDIIOOOOOOOffff......." + fin);

            }

            @Override
            public void getProgressOnActionUp(SignSeekBar signSeekBar, int progress, float progressFloat) {

            }

            @Override
            public void getProgressOnFinally(SignSeekBar signSeekBar, int progress, float progressFloat, boolean fromUser) {


            }
        });
    }
    public void promc(View view) {

        promefinal = n1+n2+n3+n4;
        fin = promefinal/4;
        edit_prom.setText(Integer.toString(fin));
        Log.v(TAG, "PROMEEDIIOOOOOOO......." + promefinal);
        Log.v(TAG, "PROMEEDIIOOOOOOOffff......." + fin);


    }
    public void QuintaEvalua(View view) {
        String strI = String.valueOf(fin);

        Intent intent = new Intent(this, QuintoEvaluacion.class);
        intent.putExtra("pf4c", strI);
        intent.putExtra("pf2c", pf2);
        intent.putExtra("pf1c", pf1);
        intent.putExtra("pf3c", pf3);
        Log.v(TAG, "OBTENINTENT......." + pf2);

        startActivity(intent);
    }
}
