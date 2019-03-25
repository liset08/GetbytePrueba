package com.getbyte.getbyteprueba.Activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.getbyte.getbyteprueba.Activities.PDF.EvaluacionCua;
import com.getbyte.getbyteprueba.Activities.PDF.EvaluacionQuin;
import com.getbyte.getbyteprueba.Activities.PDF.EvaluacionTer;
import com.getbyte.getbyteprueba.Activities.PDF.EvalucaionSeg;
import com.getbyte.getbyteprueba.Activities.PDF.InvoiceDetails;
import com.getbyte.getbyteprueba.Activities.PDF.InvoiceObject;
import com.getbyte.getbyteprueba.Activities.PDF.PdfManager;
import com.getbyte.getbyteprueba.R;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.github.mikephil.charting.charts.RadarChart;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.itextpdf.text.DocumentException;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class RadarActivity extends AppCompatActivity {
    ArrayList entries;
    public String id;
    public Bitmap bitmap;
    private static final String TAG = RadarActivity.class.getSimpleName();
    private static final int REGISTER_FORM_REQUEST = 100;

    InvoiceObject invoiceObject = new InvoiceObject();
    private String INVOICES_FOLDER = "Invoices";
    private String FILENAME = "InvoiceSample.pdf";
    //Declaramos la clase PdfManager
    private PdfManager pdfManager = null;

    String[] permissions = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar);
        ///PDF///
        //Creamos una factura desde nuestro código solo para poder generar el documento PDF
        //con esta información

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        createInvoiceObject();

        try {
            //Instanciamos la clase PdfManager
            pdfManager = new PdfManager(RadarActivity.this);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Button create_pdf = (Button)findViewById(R.id.button_create_pdf);
        Button read_pdf = (Button)findViewById(R.id.button_read_pdf);
        Button send_email_pdf = (Button)findViewById(R.id.button_email_pdf);

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
                pdfManager.showPdfFile(INVOICES_FOLDER + File.separator + FILENAME,RadarActivity.this);
            }
        });

        send_email_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailTo ="mailTo@gmail.com";
                String emailCC ="mailCC@yahoo.com";
                assert pdfManager != null;
                pdfManager.sendPdfByEmail(INVOICES_FOLDER + File.separator + FILENAME,emailTo,emailCC, RadarActivity.this);
            }
        });
        /////

        entries = new ArrayList<>();

        // mChart.setBackgroundColor(Color.rgb(60, 65, 82));
        Intent recibir = getIntent();
        id = recibir.getStringExtra("idlast");
        Log.d(TAG, "responsefinal..: " + id);

        load_data_from_server();


    }
    public void load_data_from_server() {
        String l = "https://nexa-api-liset08.c9users.io/api/v1/graficos/";
        String url = l+id;

        Log.d("URLLLLLLLLLL",url);


        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        Log.d("string",response);

                        try {

                            JSONArray jsonarray = new JSONArray(response);

                            for(int i=0; i < jsonarray.length(); i++) {

                                JSONObject jsonobject = jsonarray.getJSONObject(i);


                                String target = jsonobject.getString("num_uno").trim();
                                String passing = jsonobject.getString("num_dos").trim();
                                String skills = jsonobject.getString("num_tres").trim();
                                String dribbling = jsonobject.getString("num_cuatro").trim();
                                String penalty = jsonobject.getString("num_cinco").trim();
                                entries.add(new Entry(Float.valueOf(target), 0));
                                entries.add(new Entry(Float.valueOf(passing), 1));
                                entries.add(new Entry(Float.valueOf(skills), 2));
                                entries.add(new Entry(Float.valueOf(dribbling), 3));
                                entries.add(new Entry(Float.valueOf(penalty), 4));




                            }
                        } catch (JSONException e) {
                            e.printStackTrace();


                        }
                        RadarChart chart = (RadarChart) findViewById(R.id.chart23);

                        RadarDataSet dataset_comp1 = new RadarDataSet(entries, "P001");

                        dataset_comp1.setColor(Color.BLUE);
                        dataset_comp1.setDrawFilled(true);

                        ArrayList dataSets = new ArrayList();
                        dataSets.add(dataset_comp1);


                        ArrayList labels = new ArrayList();
                        labels.add("Calidad");
                        labels.add("Conocimiento");
                        labels.add("Presentismo");
                        labels.add("Liderazgo");
                        labels.add("Trabajo en equipo");


                        RadarData data = new RadarData(labels, dataSets);
                        chart.setData(data);
                        String description = "Getbyte";
                        chart.setDescription(description);

                        chart.invalidate();
                        chart.animate();
                        chart.getChartBitmap();

                        bitmap = chart.getChartBitmap();



                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(error != null){

                            Toast.makeText(getApplicationContext(), "Hubo un error.", Toast.LENGTH_LONG).show();
                        }
                    }
                }


        );

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

    }



    ///////PDF METODOS//////////////


    ///////////////////////////////





    public void BackMenu(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


    //Creando la factura por hard code
    private void createInvoiceObject(){
        invoiceObject.id=1234;
        invoiceObject.companyName="Getbyte.com";
        invoiceObject.companyAddress="Lima, 1024. Cp. 30100";
        invoiceObject.companyCountry="Perú";
        invoiceObject.clientName="Nombre de la empresa";
        invoiceObject.clientAddress="Calle Asturias, 25, Cp. 40100";
        invoiceObject.clientTelephone="900 123 123";
        invoiceObject.date= "12/11/2013";
        invoiceObject.total=3.5;

        InvoiceDetails invoiceDetails1 = new InvoiceDetails();
        invoiceDetails1.itemCode="Precision y calidad del trabajo realizado";
        invoiceDetails1.itemName="3";
        invoiceDetails1.quantity=2;


        InvoiceDetails invoiceDetails2 = new InvoiceDetails();
        invoiceDetails2.itemCode="Cantidad de trabajo completada";
        invoiceDetails2.itemName="4";
        invoiceDetails2.quantity=5;

        InvoiceDetails invoiceDetails3 = new InvoiceDetails();
        invoiceDetails3.itemCode="Cuidado de herramientas y equipo";
        invoiceDetails3.itemName="2";
        invoiceDetails3.quantity=2;

        InvoiceDetails invoiceDetails4 = new InvoiceDetails();
        invoiceDetails4.itemCode="Prevenir de los equipos";
        invoiceDetails4.itemName="1";
        invoiceDetails4.quantity=1;

        invoiceObject.invoiceDetailsList = new ArrayList<InvoiceDetails>();
        invoiceObject.invoiceDetailsList.add(invoiceDetails1);
        invoiceObject.invoiceDetailsList.add(invoiceDetails2);
        invoiceObject.invoiceDetailsList.add(invoiceDetails3);
        invoiceObject.invoiceDetailsList.add(invoiceDetails4);

        //////////////////////

        EvalucaionSeg invoiceDetails5 = new EvalucaionSeg();
        invoiceDetails5.itemCode="Nivel de experiencia y conocimiento técnico para el trabajo requerido";
        invoiceDetails5.itemName="3";
        invoiceDetails5.quantity=2;


        EvalucaionSeg invoiceDetails6 = new EvalucaionSeg();
        invoiceDetails6.itemCode="Uso y conocimiento de métodos y procedimientos";
        invoiceDetails6.itemName="4";
        invoiceDetails6.quantity=5;

        EvalucaionSeg invoiceDetails7 = new EvalucaionSeg();
        invoiceDetails7.itemCode="Puede desempeñarse con poca o ninguna ayuda";
        invoiceDetails7.itemName="2";
        invoiceDetails7.quantity=2;

        invoiceObject.invoiceDetailsList2 = new ArrayList<EvalucaionSeg>();
        invoiceObject.invoiceDetailsList2.add(invoiceDetails5);
        invoiceObject.invoiceDetailsList2.add(invoiceDetails6);
        invoiceObject.invoiceDetailsList2.add(invoiceDetails7);

        //////////////////////
        EvaluacionTer invoiceDetails8 = new EvaluacionTer();
        invoiceDetails8.itemCode="Trabaja sin necesidad de supervisión";
        invoiceDetails8.itemName="3";
        invoiceDetails8.quantity=2;


        EvaluacionTer invoiceDetails9 = new EvaluacionTer();
        invoiceDetails9.itemCode="Se esfuerza más si la situación lo requiere";
        invoiceDetails9.itemName="4";
        invoiceDetails9.quantity=5;

        EvaluacionTer invoiceDetails10 = new EvaluacionTer();
        invoiceDetails10.itemCode="Puntualidad";
        invoiceDetails10.itemName="2";
        invoiceDetails10.quantity=2;

        EvaluacionTer invoiceDetails100= new EvaluacionTer();
        invoiceDetails100.itemCode="Presentismo";
        invoiceDetails100.itemName="2";
        invoiceDetails100.quantity=2;
        invoiceObject.invoiceDetailsList3 = new ArrayList<EvaluacionTer>();
        invoiceObject.invoiceDetailsList3.add(invoiceDetails8);
        invoiceObject.invoiceDetailsList3.add(invoiceDetails9);
        invoiceObject.invoiceDetailsList3.add(invoiceDetails10);
        invoiceObject.invoiceDetailsList3.add(invoiceDetails100);


        //////////////////////

        EvaluacionCua invoiceDetails11 = new EvaluacionCua();
        invoiceDetails11.itemCode="Cuando completa sus tareas, busca nuevas asignaciones";
        invoiceDetails11.itemName="3";
        invoiceDetails11.quantity=2;


        EvaluacionCua invoiceDetails12 = new EvaluacionCua();
        invoiceDetails12.itemCode="Elige prioridades de forma eficiente";
        invoiceDetails12.itemName="4";
        invoiceDetails12.quantity=5;

        EvaluacionCua invoiceDetails13 = new EvaluacionCua();
        invoiceDetails13.itemCode="Cuidado de herramientas y equipo";
        invoiceDetails13.itemName="2";
        invoiceDetails13.quantity=2;

        EvaluacionCua invoiceDetails132 = new EvaluacionCua();
        invoiceDetails132.itemCode="Identifica errores y trabaja para arreglarlos";
        invoiceDetails132.itemName="5";
        invoiceDetails132.quantity=5;

        invoiceObject.invoiceDetailsList4 = new ArrayList<EvaluacionCua>();
        invoiceObject.invoiceDetailsList4.add(invoiceDetails11);
        invoiceObject.invoiceDetailsList4.add(invoiceDetails12);
        invoiceObject.invoiceDetailsList4.add(invoiceDetails13);
        invoiceObject.invoiceDetailsList4.add(invoiceDetails132);

        //////////////////////////

        EvaluacionQuin invoiceDetails14 = new EvaluacionQuin();
        invoiceDetails14.itemCode="Trabaja fluidamente con supervisores, pares y subordinados";
        invoiceDetails14.itemName="3";
        invoiceDetails14.quantity=2;


        EvaluacionQuin invoiceDetails15 = new EvaluacionQuin();
        invoiceDetails15.itemCode="Tiene una actitud positiva y proactiva";
        invoiceDetails15.itemName="4";
        invoiceDetails15.quantity=5;

        EvaluacionQuin invoiceDetails16 = new EvaluacionQuin();
        invoiceDetails16.itemCode="Promueve el trabajo en equipo";
        invoiceDetails16.itemName="2";
        invoiceDetails16.quantity=2;

        invoiceObject.invoiceDetailsList5 = new ArrayList<EvaluacionQuin>();
        invoiceObject.invoiceDetailsList5.add(invoiceDetails14);
        invoiceObject.invoiceDetailsList5.add(invoiceDetails15);
        invoiceObject.invoiceDetailsList5.add(invoiceDetails16);
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
                pdfManager.createPdfDocument(invoiceObject,bitmap);
            }
            return;
        }
    }
}
