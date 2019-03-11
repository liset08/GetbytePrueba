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

public class FrutasActivity extends AppCompatActivity {
    CheckBox che_u,che_d,che_t,che_c,che_ci,che_s,che_si;

    ArrayList<String> frutas = new ArrayList<String>();
    EditText et1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frutas);

        che_u = (CheckBox) findViewById(R.id.chfu);
        che_d = (CheckBox) findViewById(R.id.chfd);
        che_t = (CheckBox) findViewById(R.id.chft);
        che_c = (CheckBox) findViewById(R.id.chfc);
        che_ci = (CheckBox) findViewById(R.id.chfci);
        che_s = (CheckBox) findViewById(R.id.chfs);
        che_si = (CheckBox) findViewById(R.id.chfsi);

        //EditText/
        et1 = (EditText) findViewById(R.id.et_fr);

        et1.setEnabled(false);
    }
    public void rbclickfr(View v){
        if (che_u.isChecked()== true){
            frutas.add(che_u.getText().toString()) ;

        }
        if (che_d.isChecked()){
            frutas.add(che_d.getText().toString()) ;
        }
        if (che_t.isChecked()){
            frutas.add(che_t.getText().toString()) ;
        }
        if (che_c.isChecked()){
            frutas.add(che_c.getText().toString()) ;
        }
        if (che_ci.isChecked()){
            frutas.add(che_ci.getText().toString()) ;
        }
        if (che_s.isChecked()){
            frutas.add(che_s.getText().toString()) ;
        }
        if (che_si.isChecked()){
            et1.setEnabled(true);
            frutas.add( et1.getText().toString()) ;

        }else {
            et1.setEnabled(false);
            et1.setText("");

        }
    }

    public void ButtonNextfr(View view) {
        String nextf = "1";
        String str = Arrays.toString(frutas.toArray());

        Intent intent = new Intent(this, RestauranteMenuActivity.class);
        intent.putExtra("nextf", nextf);
        Toast.makeText(this, str,Toast.LENGTH_SHORT).show();

        startActivity(intent);


    }
}
