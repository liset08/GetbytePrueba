package com.getbyte.getbyteprueba.Activities.BPM.Opciones;

import android.content.Context;
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

public class HarinasActivity extends AppCompatActivity {

    CheckBox che_u,che_d,che_t,che_c,che_ci,che_s,che_si;
    ArrayList<String> harina = new ArrayList<String>();

    RadioButton rb;
    EditText et1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harinas);

        che_u = (CheckBox) findViewById(R.id.ch_hu);
        che_d = (CheckBox) findViewById(R.id.ch_hd);
        che_t = (CheckBox) findViewById(R.id.ch_ht);
        che_c = (CheckBox) findViewById(R.id.ch_hc);
        che_ci = (CheckBox) findViewById(R.id.ch_hci);
        che_s = (CheckBox) findViewById(R.id.ch_hs);
        //EditText/
        et1 = (EditText) findViewById(R.id.et_ha);

        et1.setEnabled(false);
    }
    public void rbclickha(View v){
        if (che_u.isChecked()== true){
            harina.add(che_u.getText().toString()) ;

        }
        if (che_d.isChecked()){
            harina.add(che_d.getText().toString()) ;
        }
        if (che_t.isChecked()){
            harina.add(che_t.getText().toString()) ;
        }
        if (che_c.isChecked()){
            harina.add(che_c.getText().toString()) ;
        }
        if (che_ci.isChecked()){
            harina.add(che_ci.getText().toString()) ;
        }
        if (che_s.isChecked()){
            et1.setEnabled(true);
            harina.add(et1.getText().toString()) ;

        }else {
            et1.setEnabled(false);
            et1.setText("");

        }
    }

    public void ButtonNexth(View view) {
        String nextha = "1";
        String str = Arrays.toString(harina.toArray());
        Bundle bundle = new Bundle();

        bundle.putStringArrayList("harinas",harina);
        Intent intent = new Intent(getApplicationContext(), RestauranteMenuActivity.class);
        intent.putExtras(bundle);
        getApplicationContext().startActivity(intent);
            finish();
        //  intent.putExtra("nextha", nextha);
       // Toast.makeText(this, str,Toast.LENGTH_SHORT).show();



    }
}
