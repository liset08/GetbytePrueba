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

import com.getbyte.getbyteprueba.Activities.BPM.MenuBPMActivity;
import com.getbyte.getbyteprueba.Activities.BPM.RestauranteMenuActivity;
import com.getbyte.getbyteprueba.R;

import java.util.ArrayList;
import java.util.Arrays;

public class HortalizaMainActivity extends AppCompatActivity {
    CheckBox che_u,che_d,che_t,che_c,che_ci;

    ArrayList<String> hortalizas = new ArrayList<String>();

    EditText et1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hortaliza_main);

        che_u = (CheckBox) findViewById(R.id.ch_hou);
        che_d = (CheckBox) findViewById(R.id.ch_hod);
        che_t = (CheckBox) findViewById(R.id.ch_hot);
        che_c = (CheckBox) findViewById(R.id.ch_hoc);
        che_ci = (CheckBox) findViewById(R.id.ch_hoci);

        //EditText/
        et1 = (EditText) findViewById(R.id.et_ph);

        et1.setEnabled(false);

    }
    public void rbclickh(View v){
        if (che_u.isChecked()== true){
            hortalizas.add(che_u.getText().toString()) ;

        }
        if (che_d.isChecked()){
            hortalizas.add(che_d.getText().toString()) ;
        }
        if (che_t.isChecked()){
            hortalizas.add(che_t.getText().toString()) ;
        }
        if (che_c.isChecked()){
            hortalizas.add(che_c.getText().toString()) ;
        }
        if (che_ci.isChecked()){
            et1.setEnabled(true);
            hortalizas.add( et1.getText().toString()) ;

        }else {
            et1.setEnabled(false);
            et1.setText("");

        }
    }

    public void ButtonNext(View view) {
        String nextho = "1";
        String str = Arrays.toString(hortalizas.toArray());

        Intent intent = new Intent(this, RestauranteMenuActivity.class);
        intent.putExtra("nextho", nextho);
     //   Toast.makeText(this, str,Toast.LENGTH_SHORT).show();

        startActivity(intent);


    }
}
