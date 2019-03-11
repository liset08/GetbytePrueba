package com.getbyte.getbyteprueba.Activities.BPM.Opciones;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.getbyte.getbyteprueba.Activities.BPM.MenuBPMActivity;
import com.getbyte.getbyteprueba.Activities.BPM.RestauranteMenuActivity;
import com.getbyte.getbyteprueba.Activities.EvaluacionEMainActivity;
import com.getbyte.getbyteprueba.R;
import com.zhouyou.view.seekbar.SignSeekBar;

public class RestuOpcionActivity extends AppCompatActivity {
    private static final String TAG = "CuartaEvaluacion";

   RadioGroup rg,rg1,rg2,rg3,rg4;
   RadioButton rb;
EditText et1,et2,et3,et4,et5;
String size,num2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restu_opcion);


        rg = (RadioGroup) findViewById(R.id.rbgroup);
        rg1 = (RadioGroup) findViewById(R.id.rbgroup2);
        rg2 = (RadioGroup) findViewById(R.id.rbgroup3);
        rg3 = (RadioGroup) findViewById(R.id.rbgroup4);
        rg4 = (RadioGroup) findViewById(R.id.rbgroup5);


//EditText/
        et1 = (EditText) findViewById(R.id.et_p);
        et2 = (EditText) findViewById(R.id.et_p2);
        et3 = (EditText) findViewById(R.id.et_p3);
        et4 = (EditText) findViewById(R.id.et_p4);
        et5 = (EditText) findViewById(R.id.et_p5);

        et1.setEnabled(false);
        et2.setEnabled(false);
        et3.setEnabled(false);
        et4.setEnabled(false);
        et5.setEnabled(false);

        ///////
        ///

    }

    public void rbclick(View v){
        int radiobuttonid = rg.getCheckedRadioButtonId();
        rb = (RadioButton) findViewById(radiobuttonid);
        String f = rb.getText().toString();
        if (f.equalsIgnoreCase("Otros")){

            et1.setEnabled(true);

        }else {
            et1.setEnabled(false);

        }
    }
    public void rbclic2(View v){
        int radiobuttonid = rg1.getCheckedRadioButtonId();
        rb = (RadioButton) findViewById(radiobuttonid);
        String f = rb.getText().toString();
        if (f.equalsIgnoreCase("Otros")){

            et2.setEnabled(true);

        }else {
            et2.setEnabled(false);

        }

    }
    public void rbclic3(View v){
        int radiobuttonid = rg2.getCheckedRadioButtonId();
        rb = (RadioButton) findViewById(radiobuttonid);
        String f = rb.getText().toString();
        if (f.equalsIgnoreCase("Otros")){

            et3.setEnabled(true);

        }else {
            et3.setEnabled(false);

        }
    }
    public void rbclic4(View v){
        int radiobuttonid = rg3.getCheckedRadioButtonId();
        rb = (RadioButton) findViewById(radiobuttonid);
        String f = rb.getText().toString();
        if (f.equalsIgnoreCase("Otros")){

            et4.setEnabled(true);

        }else {
            et4.setEnabled(false);

        }
    }
    public void rbclic5(View v){
        int radiobuttonid = rg4.getCheckedRadioButtonId();
        rb = (RadioButton) findViewById(radiobuttonid);
        String f = rb.getText().toString();
        if (f.equalsIgnoreCase("Otros")){

            et5.setEnabled(true);

        }else {
            et5.setEnabled(false);

        }
    }

    public void NextTub(View view) {
        String next = "1";
            Intent intent = new Intent(this, RestauranteMenuActivity.class);
        intent.putExtra("next", next);

        startActivity(intent);

    }
}
