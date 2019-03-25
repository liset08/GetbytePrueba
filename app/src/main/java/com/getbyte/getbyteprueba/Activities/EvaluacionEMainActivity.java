package com.getbyte.getbyteprueba.Activities;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


import com.getbyte.getbyteprueba.R;
import com.zhouyou.view.seekbar.SignSeekBar;



public class EvaluacionEMainActivity extends AppCompatActivity {
    private static final String TAG = "EvaluacionEMainActivity";
    public int n1,n2,n3,n4,n5,promefinal,fin;
    EditText edit_prom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluacion_emain);
        edit_prom = (EditText) findViewById(R.id.prom_firs);

        SignSeekBar signSeekBar = (SignSeekBar) findViewById(R.id.demo_5_seek_bar_1);
        signSeekBar.getConfigBuilder()
                .min(0)
                .max(5)
                .progress(0)
                .sectionCount(5)
                .sectionTextSize(16)
                .build();

        signSeekBar.setOnProgressChangedListener(new SignSeekBar.OnProgressChangedListener() {
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

                if (progress>0){
                    signSeekBar.setEnabled(false);
                }else {
                    signSeekBar.setEnabled(true);

                }
            }
        });
        /*int[] intArray = getResources().getIntArray(R.array.progressGradientColors);
        signSeekBar.setDrawingCacheBackgroundColor(R.color.sliderBack);
*/

        SignSeekBar signSeekBar2 = (SignSeekBar) findViewById(R.id.demo_5_seek_bar_2);
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

        SignSeekBar signSeekBar3 = (SignSeekBar) findViewById(R.id.demo_5_seek_bar_4);
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
        SignSeekBar signSeekBar4 = (SignSeekBar) findViewById(R.id.demo_5_seek_bar_5);
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

    }
    public void prom(View view) {

        promefinal = n1+n2+n3+n4;
        fin = promefinal/4;

        edit_prom.setText(Integer.toString(fin));
        Log.v(TAG, "PROMEEDIIOOOOOOO......." + promefinal);
        Log.v(TAG, "PROMEEDIIOOOOOOOffff......." + fin);


    }

    public void SegEvalua(View view) {
        String strI = String.valueOf(fin);

        Intent intent = new Intent(this, SegEvaluacion.class);
        intent.putExtra("pf1p", strI);
        startActivity(intent);
        finish();

    }
}
