package com.getbyte.getbyteprueba.Activities.BPM;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.getbyte.getbyteprueba.Activities.BPM.Opciones.FrutasActivity;
import com.getbyte.getbyteprueba.Activities.BPM.Opciones.GranosActivity;
import com.getbyte.getbyteprueba.Activities.BPM.Opciones.HarinasActivity;
import com.getbyte.getbyteprueba.Activities.BPM.Opciones.HortalizaMainActivity;
import com.getbyte.getbyteprueba.Activities.BPM.Opciones.HuevosActivity;
import com.getbyte.getbyteprueba.Activities.BPM.Opciones.RestuOpcionActivity;
import com.getbyte.getbyteprueba.Activities.BPM.Opciones.TuberculosActivity;
import com.getbyte.getbyteprueba.Activities.PDF.EvaluacionCua;
import com.getbyte.getbyteprueba.Activities.PDF.EvaluacionQuin;
import com.getbyte.getbyteprueba.Activities.PDF.EvaluacionTer;
import com.getbyte.getbyteprueba.Activities.PDF.EvalucaionSeg;
import com.getbyte.getbyteprueba.Activities.PDF.InvoiceDetails;
import com.getbyte.getbyteprueba.Activities.PDF.InvoiceObject;
import com.getbyte.getbyteprueba.Activities.PDF.PdfManager;
import com.getbyte.getbyteprueba.Activities.RadarActivity;
import com.getbyte.getbyteprueba.R;
import com.itextpdf.text.DocumentException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RestauranteMenuActivity extends AppCompatActivity {
    ArrayList<String> lacteos = new ArrayList<String>();

CardView cvFish , cvHarina ,cvFruta ,cvGranos, cvHuevos,cvHortaliza, cvTuber;

    String id_f, id_hu, id_ha,id_t,id_ho, id_g,id_fr;
    String extra,extraf, extratu, nextha ,nextho, nexthu ,nextg;
    ArrayList<String> lista;
    ArrayList<String> harina;

    private static final String TAG = RadarActivity.class.getSimpleName();
    private static final int REGISTER_FORM_REQUEST = 100;

    InvoiceObject invoiceObject = new InvoiceObject();
    private String INVOICES_FOLDER = "Invoices";
    private String FILENAME = "InvoiceSample.pdf";
    //Declaramos la clase PdfManager

    private InformeBPMActivity pdfManager = null;
    String[] permissions = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurante_menu);

        ///recibimos el arrray//
        //lista = (ArrayList<String>) getIntent().getStringArrayListExtra("miLista");

///PDF///

        Bundle parametros = this.getIntent().getExtras();
        Bundle parametros2 = this.getIntent().getExtras();

        Intent recibir = getIntent();

        extra = recibir.getStringExtra("next");
        extraf = recibir.getStringExtra("nextf");
        extratu = recibir.getStringExtra("nexttu");
        nextha = recibir.getStringExtra("nextha");
        nextho = recibir.getStringExtra("nextho");
        nexthu = recibir.getStringExtra("nexthu");
      //  nextg = recibir.getStringExtra("nextg");
      //  nextg = parametros2.getString("nextg");
        //String str = Arrays.toString(lista.toArray());

        //Creamos una factura desde nuestro código solo para poder generar el documento PDF
        //con esta información

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
      //  String text = lista.get(0);


        if (parametros == null || parametros2 == null) {
            Toast.makeText(this, "add",Toast.LENGTH_SHORT).show();

            clicks();

        } else if (parametros == null || parametros2 != null){
            Toast.makeText(this, "add",Toast.LENGTH_SHORT).show();
            lista = parametros2.getStringArrayList("granos");
           // String str2 = Arrays.toString(lista.toArray());

          //  Toast.makeText(this, str2,Toast.LENGTH_SHORT).show();
            clicks();
            createInvoiceObject();
            try {
                //Instanciamos la clase PdfManager
                pdfManager = new InformeBPMActivity(RestauranteMenuActivity.this);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (DocumentException e) {
                e.printStackTrace();
            }
            Button create_pdf = (Button)findViewById(R.id.button_create_pdf2);
            Button read_pdf = (Button)findViewById(R.id.button_read_pdf2);
            Button send_email_pdf = (Button)findViewById(R.id.button_email_pdf2);

            create_pdf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Create PDF document
                    checkPermissions();

                }
            });

            read_pdf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    assert pdfManager != null;
                    pdfManager.showPdfFile(INVOICES_FOLDER + File.separator + FILENAME,RestauranteMenuActivity.this);
                }
            });

            }
           else if (parametros != null || parametros2 != null){
        clicks();
            lista = parametros2.getStringArrayList("granos");
        harina = parametros.getStringArrayList("harinas");
        String str = Arrays.toString(harina.toArray());

        Toast.makeText(this, str,Toast.LENGTH_SHORT).show();
            createInvoiceObject();
            try {
                //Instanciamos la clase PdfManager
                pdfManager = new InformeBPMActivity(RestauranteMenuActivity.this);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (DocumentException e) {
                e.printStackTrace();
            }
            Button create_pdf = (Button)findViewById(R.id.button_create_pdf2);
            Button read_pdf = (Button)findViewById(R.id.button_read_pdf2);
            Button send_email_pdf = (Button)findViewById(R.id.button_email_pdf2);

            create_pdf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Create PDF document
                    checkPermissions();

                }
            });

            read_pdf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    assert pdfManager != null;
                    pdfManager.showPdfFile(INVOICES_FOLDER + File.separator + FILENAME,RestauranteMenuActivity.this);
                }
            });
        }}
     /*   if (parametros2 != null){
            clicks();

    }*/
   /* public void RestauranteOpcion(View view) {
        Intent intent = new Intent(this, RestuOpcionActivity.class);
        startActivity(intent);
    }*/

///menu///

    public void crearPdf(){

    }



@Override
public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu items for use in the action bar
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.action_good, menu);
    return super.onCreateOptionsMenu(menu);
}


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_search:

                String date = lacteos.get(0);
               String num = String.valueOf(lacteos.size());

                Toast.makeText(this, num,Toast.LENGTH_SHORT).show();

                if (date.equalsIgnoreCase(id_f)){
                    Intent intent = new Intent(this,RestuOpcionActivity.class);
                    startActivity(intent);



                }else if (date.equalsIgnoreCase(id_ho)){

                        Intent intent = new Intent(this,HortalizaMainActivity.class);
                    startActivity(intent);


                }else if (date.equalsIgnoreCase(id_t)){

                        Intent intent = new Intent(this,TuberculosActivity.class);
                    startActivity(intent);


                }else if (date.equalsIgnoreCase(id_hu)){

                        Intent intent = new Intent(this,HuevosActivity.class);
                    startActivity(intent);

                }else if (date.equalsIgnoreCase(id_g)){

                        Intent intent = new Intent(this,GranosActivity.class);
                    startActivity(intent);


                }else if (date.equalsIgnoreCase(id_ha)){

                    Intent intent = new Intent(this,HarinasActivity.class);
                    startActivity(intent);

                }else if (date.equalsIgnoreCase(id_fr)){
                    Intent intent = new Intent(this,FrutasActivity.class);
                    startActivity(intent);

                }




                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    ////////////
    //Creando la factura por hard code
    private void createInvoiceObject(){
        invoiceObject.id=1234;
        invoiceObject.companyName="Getbyte.com";
        invoiceObject.companyAddress="Lima, 1024. Cp. 30100";

int f= lista.size();
        if (f == 1){
            InvoiceDetails invoiceDetails1 = new InvoiceDetails();
            invoiceDetails1.itemCode = lista.get(0);

            invoiceObject.invoiceDetailsList = new ArrayList<InvoiceDetails>();
            invoiceObject.invoiceDetailsList.add(invoiceDetails1);

        }if (f == 2){

            InvoiceDetails invoiceDetails1 = new InvoiceDetails();
            invoiceDetails1.itemCode = lista.get(0);

            InvoiceDetails invoiceDetails2 = new InvoiceDetails();
            invoiceDetails2.itemCode = lista.get(1);

            invoiceObject.invoiceDetailsList = new ArrayList<InvoiceDetails>();
            invoiceObject.invoiceDetailsList.add(invoiceDetails1);
            invoiceObject.invoiceDetailsList.add(invoiceDetails2);

        }if (f == 3){

            InvoiceDetails invoiceDetails1 = new InvoiceDetails();
            invoiceDetails1.itemCode = lista.get(0);

            InvoiceDetails invoiceDetails2 = new InvoiceDetails();
            invoiceDetails2.itemCode = lista.get(1);

            InvoiceDetails invoiceDetails3 = new InvoiceDetails();
            invoiceDetails3.itemCode = lista.get(2);

            invoiceObject.invoiceDetailsList = new ArrayList<InvoiceDetails>();
            invoiceObject.invoiceDetailsList.add(invoiceDetails1);
            invoiceObject.invoiceDetailsList.add(invoiceDetails2);
            invoiceObject.invoiceDetailsList.add(invoiceDetails3);

        }if (f == 4){

            InvoiceDetails invoiceDetails1 = new InvoiceDetails();
            invoiceDetails1.itemCode = lista.get(0);

            InvoiceDetails invoiceDetails2 = new InvoiceDetails();
            invoiceDetails2.itemCode = lista.get(1);

            InvoiceDetails invoiceDetails3 = new InvoiceDetails();
            invoiceDetails3.itemCode = lista.get(2);

            InvoiceDetails invoiceDetails4 = new InvoiceDetails();
            invoiceDetails4.itemCode = lista.get(3);

            invoiceObject.invoiceDetailsList = new ArrayList<InvoiceDetails>();
            invoiceObject.invoiceDetailsList.add(invoiceDetails1);
            invoiceObject.invoiceDetailsList.add(invoiceDetails2);
            invoiceObject.invoiceDetailsList.add(invoiceDetails3);
            invoiceObject.invoiceDetailsList.add(invoiceDetails4);

        }if (f == 5){

            InvoiceDetails invoiceDetails1 = new InvoiceDetails();
            invoiceDetails1.itemCode = lista.get(0);

            InvoiceDetails invoiceDetails2 = new InvoiceDetails();
            invoiceDetails2.itemCode = lista.get(1);

            InvoiceDetails invoiceDetails3 = new InvoiceDetails();
            invoiceDetails3.itemCode = lista.get(2);

            InvoiceDetails invoiceDetails4 = new InvoiceDetails();
            invoiceDetails4.itemCode = lista.get(3);

            InvoiceDetails invoiceDetails5 = new InvoiceDetails();
            invoiceDetails5.itemCode = lista.get(4);

            invoiceObject.invoiceDetailsList = new ArrayList<InvoiceDetails>();
            invoiceObject.invoiceDetailsList.add(invoiceDetails1);
            invoiceObject.invoiceDetailsList.add(invoiceDetails2);
            invoiceObject.invoiceDetailsList.add(invoiceDetails3);
            invoiceObject.invoiceDetailsList.add(invoiceDetails4);
            invoiceObject.invoiceDetailsList.add(invoiceDetails5);


        }



    }
    private boolean checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permissions) {
            result = ContextCompat.checkSelfPermission(this, p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 100);
            return false;
        }
        return true;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == 100) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                assert pdfManager != null;
                pdfManager.createPdfDocument(invoiceObject);
            }
            return;
        }
    }

    public void clicks(){
        cvFish = (CardView) findViewById(R.id.cv_fish);

        if (extra == null) {

            cvFish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = v.getId();
                    id_f = String.valueOf(id);
                    lacteos.add(id_f);
                    cvFish.setBackgroundColor(Color.GRAY);


                }
            });
        } else if (extra.equalsIgnoreCase("1")) {
            cvFish.setBackgroundColor(Color.LTGRAY);

            cvFish.setEnabled(false);

        }

        cvHarina = (CardView) findViewById(R.id.cv_harina);

        if (nextha == null) {

            cvHarina.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = v.getId();
                    id_ha = String.valueOf(id);
                    lacteos.add(id_ha);
                    cvHarina.setBackgroundColor(Color.GRAY);
                }
            });
        } else if (nextha.equalsIgnoreCase("1")) {
            cvHarina.setBackgroundColor(Color.LTGRAY);

            cvHarina.setEnabled(false);

        }


        cvFruta = (CardView) findViewById(R.id.cv_fruta);

        if (extraf == null) {
            cvFruta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = v.getId();
                    id_fr = String.valueOf(id);
                    lacteos.add(id_fr);
                    cvFruta.setBackgroundColor(Color.GRAY);


                }
            });

        } else if (extraf.equalsIgnoreCase("1")) {

            //cvFruta.setBackgroundColor(Color.LTGRAY);

            cvFruta.setEnabled(false);
        }


        cvGranos = (CardView) findViewById(R.id.cv_granos);
        if (nextg == null) {
            cvGranos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = v.getId();
                    id_g = String.valueOf(id);
                    lacteos.add(id_g);


                    cvGranos.setBackgroundColor(Color.GRAY);
                }
            });
        } else if (nextg.equalsIgnoreCase("1")) {

            cvGranos.setBackgroundColor(Color.LTGRAY);

            cvGranos.setEnabled(false);
        }


        cvHuevos = (CardView) findViewById(R.id.cv_huevos);
        if (nexthu == null) {
            cvHuevos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = v.getId();
                    id_hu = String.valueOf(id);
                    lacteos.add(id_hu);

                    cvHuevos.setBackgroundColor(Color.GRAY);
                }
            });
        } else if (nexthu.equalsIgnoreCase("1")) {

            cvHuevos.setBackgroundColor(Color.LTGRAY);

            cvHuevos.setEnabled(false);
        }


        cvHortaliza = (CardView) findViewById(R.id.cv_hortaliza);
        if (nextho == null) {
            cvHortaliza.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = v.getId();
                    id_ho = String.valueOf(id);
                    lacteos.add(id_ho);

                    cvHortaliza.setBackgroundColor(Color.GRAY);
                }
            });
        } else if (nextho.equalsIgnoreCase("1")) {

            cvHortaliza.setBackgroundColor(Color.LTGRAY);

            cvHortaliza.setEnabled(false);
        }


        cvTuber = (CardView) findViewById(R.id.cv_tuber);
        if (extratu == null) {
            cvTuber.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = v.getId();
                    id_t = String.valueOf(id);
                    lacteos.add(id_t);


                    cvTuber.setBackgroundColor(Color.GRAY);
                }
            });

        } else if (extratu.equalsIgnoreCase("1")) {
            cvTuber.setBackgroundColor(Color.LTGRAY);

            cvTuber.setEnabled(false);

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("CONT", lista);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);

        lista = savedInstanceState.getStringArrayList("CONT");
        String str = Arrays.toString(lista.toArray());

        Toast.makeText(this, str,Toast.LENGTH_SHORT).show();

    }
}
