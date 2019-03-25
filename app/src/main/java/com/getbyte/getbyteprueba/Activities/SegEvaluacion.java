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


public class SegEvaluacion extends AppCompatActivity {
    private static final String TAG = "SegEvaluacion";

    public int n1,n2,n3,n4,n5,promefinal,fin;
    EditText edit_prom;
    String datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seg_evaluacion);

        edit_prom = (EditText) findViewById(R.id.etext_pf2);

        //Recibir el pf1
        Intent recibir = getIntent();
        datos = recibir.getStringExtra("pf1p");
        ///

        SignSeekBar signSeekBar2 = (SignSeekBar) findViewById(R.id.demo_5_seek_bar_1s);
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

                promefinal = n1+n2+n3;
                fin = promefinal/3;
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
        SignSeekBar signSeekBar1 = (SignSeekBar) findViewById(R.id.demo_5_seek_bar_2s);
        signSeekBar1.getConfigBuilder()
                .min(0)
                .max(5)
                .progress(0)
                .sectionCount(5)
                .sectionTextSize(13)
                .build();

        signSeekBar1.setOnProgressChangedListener(new SignSeekBar.OnProgressChangedListener() {
            @Override
            public void onProgressChanged(SignSeekBar signSeekBar, int progress, float progressFloat, boolean fromUser) {
                Log.v(TAG,"NUMLISTENNN........"+progress);
                n2 = progress;
                promefinal = n1+n2+n3;
                fin = promefinal/3;
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
        SignSeekBar signSeekBar3 = (SignSeekBar) findViewById(R.id.demo_5_seek_bar_4s);
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
                n3 = progress;

                promefinal = n1+n2+n3;
                fin = promefinal/3;
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

    public void proms(View view) {

        promefinal = n1+n2+n3;
        fin = promefinal/3;
        edit_prom.setText(Integer.toString(fin));
        Log.v(TAG, "PROMEEDIIOOOOOOO......." + promefinal);
        Log.v(TAG, "PROMEEDIIOOOOOOOffff......." + fin);


    }
    public void TerEvalua(View view) {
        Intent intent = new Intent(this, TerceraEvaluacion.class);
        String strI = String.valueOf(fin);

        intent.putExtra("pf2s", strI);
        intent.putExtra("pf1s", datos);
        Log.v(TAG, "INTENTEEEE......" + strI);


        startActivity(intent);
        finish();

    }
}
