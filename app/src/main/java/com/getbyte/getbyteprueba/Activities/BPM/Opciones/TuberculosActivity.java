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

public class TuberculosActivity extends AppCompatActivity {
    RadioButton rb;
    CheckBox che_u,che_d,che_t,che_c,che_ci;
    ArrayList<String> tuberculo = new ArrayList<String>();

    EditText et1;
    String size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuberculos);

        che_u = (CheckBox) findViewById(R.id.ch_tu);
        che_d = (CheckBox) findViewById(R.id.ch_td);
        che_t = (CheckBox) findViewById(R.id.ch_tt);
        che_c = (CheckBox) findViewById(R.id.ch_tc);
        che_ci = (CheckBox) findViewById(R.id.ch_tci);


        //EditText/
        et1 = (EditText) findViewById(R.id.et_pt);

        et1.setEnabled(false);
    }

    public void rbclickt(View v){
        if (che_u.isChecked()== true){
            tuberculo.add(che_u.getText().toString()) ;

        }
        if (che_d.isChecked()){
            tuberculo.add(che_d.getText().toString()) ;
        }
        if (che_t.isChecked()){
            tuberculo.add(che_t.getText().toString()) ;
        }
        if (che_c.isChecked()){
            tuberculo.add(che_c.getText().toString()) ;
        }

        if (che_ci.isChecked()){
            et1.setEnabled(true);
            tuberculo.add( et1.getText().toString()) ;

        }else {
            et1.setEnabled(false);
            et1.setText("");

        }
    }
    public void NextHort(View view) {
        String nexttu = "1";
        String str = Arrays.toString(tuberculo.toArray());

        Intent intent = new Intent(this, RestauranteMenuActivity.class);
        intent.putExtra("nexttu", nexttu);
        Toast.makeText(this, str,Toast.LENGTH_SHORT).show();

        startActivity(intent);
        }

}
