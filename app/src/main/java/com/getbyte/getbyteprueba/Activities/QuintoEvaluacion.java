package com.getbyte.getbyteprueba.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.getbyte.getbyteprueba.Model.Graficos;
import com.getbyte.getbyteprueba.R;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.getbyte.getbyteprueba.Service.ApiServiceGenerator;
import com.getbyte.getbyteprueba.Service.ResponseMessage;
import com.getbyte.getbyteprueba.Service.UserClient;
import com.zhouyou.view.seekbar.SignSeekBar;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuintoEvaluacion extends AppCompatActivity {
    private static final String TAG = "QuintoEvaluacion";

    public int n1,n2,n3,n4,n5,promefinal,fin;
    int pf1e,pf2e,pf3e,pf4e,pf5e;
    EditText edit_prom;
    String pf1,pf2,pf3,pf4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quinto_evaluacion);

        edit_prom = (EditText) findViewById(R.id.etext_pf5);

        //Recibir el pf1
        Intent recibir = getIntent();
        pf1 = recibir.getStringExtra("pf1c");
        pf2 = recibir.getStringExtra("pf2c");
        pf3 = recibir.getStringExtra("pf3c");
        pf4 = recibir.getStringExtra("pf4c");


        ///
        SignSeekBar signSeekBar = (SignSeekBar) findViewById(R.id.demo_5_seek_bar_1q);
        signSeekBar.getConfigBuilder()
                .min(0)
                .max(5)
                .progress(0)
                .sectionCount(5)
                .build();
        signSeekBar.setOnProgressChangedListener(new SignSeekBar.OnProgressChangedListener() {
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
        /*int[] intArray = getResources().getIntArray(R.array.progressGradientColors);
        signSeekBar.setDrawingCacheBackgroundColor(R.color.sliderBack);
*/

        SignSeekBar signSeekBar2 = (SignSeekBar) findViewById(R.id.demo_5_seek_bar_2q);
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
        SignSeekBar signSeekBar3 = (SignSeekBar) findViewById(R.id.demo_5_seek_bar_4q);
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
    public void promq(View view) {

        promefinal = n1+n2+n3;
        fin = promefinal/3;
        edit_prom.setText(Integer.toString(fin));
        Log.v(TAG, "PROMEEDIIOOOOOOO......." + promefinal);
        Log.v(TAG, "PROMEEDIIOOOOOOOffff......." + fin);


    }
    public void callRegister(View view) {
        String strI = String.valueOf(fin);

        pf1e = Integer.parseInt(pf1);
        pf2e = Integer.parseInt(pf2);
        pf3e = Integer.parseInt(pf3);
        pf4e = Integer.parseInt(pf4);
        pf5e = Integer.parseInt(strI);


        try {

            Graficos graf=new Graficos();
            graf.setNumUno(pf1e);
            graf.setNumDos(pf2e);
            graf.setNumTres(pf3e);
            graf.setNumCuatro(pf4e);
            graf.setNumCinco(pf5e);


            UserClient service = ApiServiceGenerator.createService(UserClient.class);

            Call<ResponseMessage> call = service.createGraf(graf);

            call.enqueue(new Callback<ResponseMessage>() {
                @Override
                public void onResponse(Call <ResponseMessage> call, Response <ResponseMessage> response) {
                    if (response.isSuccessful()){
                        ResponseMessage responseMessage = response.body();
                        Log.d(TAG, "responseMessageeeee...: " + responseMessage);
                        int id = responseMessage.getId();

                        String strI = String.valueOf(id);
                        Log.d(TAG, "responseMessageeeee...: " + strI);

                        Intent intent=new Intent(QuintoEvaluacion.this,RadarActivity.class);
                        intent.putExtra("idlast", strI);

                        startActivity(intent);
                        finish();


                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    Toast.makeText(QuintoEvaluacion.this, t.getMessage(), Toast.LENGTH_LONG).show();

                }
            });
            /*startActivityForResult(new Intent(this,
                    SegEvaluacion.class), REGISTER_FORM_REQUEST);
*/
        }catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();


        }};

    public void RepoEval(View view) {
        Intent intent = new Intent(this, RadarActivity.class);
        startActivity(intent);
        finish();

    }
}

