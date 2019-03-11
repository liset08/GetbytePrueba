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

public class GranosActivity extends AppCompatActivity {

    CheckBox che_u,che_d,che_t,che_c,che_ci,che_s,che_si;
    EditText et1;
    ArrayList<String> granos = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_granos);

        che_u = (CheckBox) findViewById(R.id.che_u);
        che_d = (CheckBox) findViewById(R.id.che_d);
        che_t = (CheckBox) findViewById(R.id.che_t);
        che_c = (CheckBox) findViewById(R.id.che_c);
        che_ci = (CheckBox) findViewById(R.id.che_ci);
        che_s = (CheckBox) findViewById(R.id.che_s);
        che_si = (CheckBox) findViewById(R.id.che_si);


        //EditText/
        et1 = (EditText) findViewById(R.id.et_gr);

        et1.setEnabled(false);

    }
    public void rbclickgr(View v) {

        if (che_u.isChecked() == true) {
            granos.add(che_u.getText().toString());

        }
    }
    public void rbclickgrd(View v) {
        if (che_d.isChecked()){
            granos.add(che_d.getText().toString()) ;
        }
    }
    public void rbclickgrt(View v) {

        if (che_t.isChecked()){
            granos.add(che_t.getText().toString()) ;
        }
    }
    public void rbclickgrtc(View v) {

        if (che_c.isChecked()){
            granos.add(che_c.getText().toString()) ;
        }}
    public void rbclickgrci(View v) {

        if (che_ci.isChecked()){
            granos.add(che_ci.getText().toString()) ;
        }}
    public void rbclickgrs(View v) {

        if (che_s.isChecked()){
            granos.add(che_s.getText().toString()) ;
        }}
    public void rbclickgrsi(View v) {

        if (che_si.isChecked()){
            et1.setEnabled(true);
            granos.add( et1.getText().toString()) ;

        }else {
            et1.setEnabled(false);
            et1.setText("");

        }}


    public void ButtonNextg(View view) {



        String nextg = "1";
        String str = Arrays.toString(granos.toArray());

      /*  Intent intent = new Intent(this, RestauranteMenuActivity.class);
        intent.putExtra("nextg", nextg);
        intent.putStringArrayListExtra("miLista", granos);

        Toast.makeText(this, str,Toast.LENGTH_SHORT).show();

        startActivity(intent);*/
        Bundle bundle = new Bundle();

        bundle.putStringArrayList("granos",granos);
        bundle.putString("nextg",nextg);

        Intent intent = new Intent(getApplicationContext(), RestauranteMenuActivity.class);
        intent.putExtras(bundle);
        getApplicationContext().startActivity(intent);
        finish();


    }
}
