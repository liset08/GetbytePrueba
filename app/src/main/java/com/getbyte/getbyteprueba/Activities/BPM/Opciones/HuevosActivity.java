package com.getbyte.getbyteprueba.Activities.BPM.Opciones;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.getbyte.getbyteprueba.Activities.BPM.RestauranteMenuActivity;
import com.getbyte.getbyteprueba.R;

import java.util.ArrayList;
import java.util.Arrays;

public class HuevosActivity extends AppCompatActivity {

    CheckBox che_u,che_d,che_t,che_c,che_ci,che_s,che_si,che_o,che_n;
    ArrayList<String> hcascara = new ArrayList<String>();
    ArrayList<String> hinterior = new ArrayList<String>();

    EditText et1,et2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huevos);

        che_u = (CheckBox) findViewById(R.id.ch_hcu);
        che_d = (CheckBox) findViewById(R.id.ch_hcd);
        che_t = (CheckBox) findViewById(R.id.ch_hct);
        che_c = (CheckBox) findViewById(R.id.ch_hcc);
        che_ci = (CheckBox) findViewById(R.id.ch_hiu);
        che_s = (CheckBox) findViewById(R.id.ch_hid);
        che_si = (CheckBox) findViewById(R.id.ch_hit);
        che_o = (CheckBox) findViewById(R.id.ch_hic);
        che_n = (CheckBox) findViewById(R.id.ch_hici);


        //EditText/
        et1 = (EditText) findViewById(R.id.et_hu);
        et2 = (EditText) findViewById(R.id.et_hu2);


        et1.setEnabled(false);
        et2.setEnabled(false);

    }
    public void rbclickhu(View v){
        if (che_u.isChecked()== true){
            hcascara.add(che_u.getText().toString()) ;

        }
        if (che_d.isChecked()){
            hcascara.add(che_d.getText().toString()) ;
        }
        if (che_t.isChecked()){
            hcascara.add(che_t.getText().toString()) ;
        }
        if (che_c.isChecked()){
            et1.setEnabled(true);
            hcascara.add( et1.getText().toString()) ;

        }else {
            et1.setEnabled(false);
            et1.setText("");

        }


    }
    public void rbclickhu2(View v){
        if (che_ci.isChecked()== true){
            hcascara.add(che_ci.getText().toString()) ;

        }
        if (che_s.isChecked()){
            hinterior.add(che_s.getText().toString()) ;
        }
        if (che_si.isChecked()){
            hinterior.add(che_si.getText().toString()) ;
        }
        if (che_o.isChecked()){
            hinterior.add(che_o.getText().toString()) ;
        }
        if (che_n.isChecked()){
            et2.setEnabled(true);
            hinterior.add( et2.getText().toString()) ;

        }else {
            et2.setEnabled(false);
            et2.setText("");

        }
    }

    public void ButtonNexthu(View view) {
        String nexthu = "1";
        String str = Arrays.toString(hcascara.toArray());
        String str2 = Arrays.toString(hinterior.toArray());

        Intent intent = new Intent(this, RestauranteMenuActivity.class);
        intent.putExtra("nexthu", nexthu);
     //   Toast.makeText(this, "hcascara "+ str + "hinterior "+ str2,Toast.LENGTH_SHORT).show();

        startActivity(intent);


    }
}
