package com.getbyte.getbyteprueba.Activities.ADMIN_CAL_PROD;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.getbyte.getbyteprueba.Activities.PDF.InvoiceDetails;
import com.getbyte.getbyteprueba.Activities.PDF.InvoiceObject;
import com.getbyte.getbyteprueba.Activities.RadarActivity;
import com.getbyte.getbyteprueba.Model.Calidad;
import com.getbyte.getbyteprueba.R;
import com.getbyte.getbyteprueba.Service.ApiServiceGenerator;
import com.getbyte.getbyteprueba.Service.UserClient;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;


import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Eleccion_calProd extends AppCompatActivity {

    private static final String TAG = RadarActivity.class.getSimpleName();
    private static final int REGISTER_FORM_REQUEST = 100;
    List<Calidad> catador;



    InvoiceObject invoiceObject = new InvoiceObject();
    private String INVOICES_FOLDER = "Invoices";
    private String FILENAME = "InvoiceSample.pdf";
    //Declaramos la clase PdfManager
    private PDFCalidadManager pdfCalidadManager = null;

    public Bitmap bt;
   public ImageView image;

    String[] permissions = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleccion_cal_prod);

        ///PDF///
        //Creamos una factura desde nuestro código solo para poder generar el documento PDF
        //con esta información
        initialize();

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());


        try {
            //Instanciamos la clase PdfManager
            pdfCalidadManager = new PDFCalidadManager(Eleccion_calProd.this);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Button create_pdf = (Button)findViewById(R.id.button_create_pdfmod);
        Button read_pdf = (Button)findViewById(R.id.button_read_pdfmod);
        Button send_email_pdf = (Button)findViewById(R.id.button_email_pdfmod);
         image= (ImageView) findViewById(R.id.img_bitmap);

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
                assert pdfCalidadManager != null;
                pdfCalidadManager.showPdfFile(INVOICES_FOLDER + File.separator + FILENAME,Eleccion_calProd.this);
            }
        });

        send_email_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailTo ="mailTo@gmail.com";
                String emailCC ="mailCC@yahoo.com";
                assert pdfCalidadManager != null;
                pdfCalidadManager.sendPdfByEmail(INVOICES_FOLDER + File.separator + FILENAME,emailTo,emailCC, Eleccion_calProd.this);
            }
        });
        /////

      //  entries = new ArrayList<>();

    }
    private void initialize() {

        UserClient service = ApiServiceGenerator.createService(UserClient.class);

        Call<List<Calidad>> call = service.findCalidad(16);

        call.enqueue(new Callback<List<Calidad>>() {
            @Override
            public void onResponse(Call<List<Calidad>> call, Response<List<Calidad>> response) {
                try {

                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {

                        catador = response.body();
                        Log.d(TAG, "catador: " + catador);
                        Log.d(TAG, "CATADORR0000: " + catador.get(0).getMarca());
                        String url = UserClient.API_BASE_URL+"/images/"+catador.get(0).getImagen();

                        Picasso.with(Eleccion_calProd.this).load(url).into(new Target() {
                            @Override
                            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {

                              image.setImageBitmap(bitmap);
                                Log.e(TAG, "BITMAPPPPP:: " + bitmap);
                                bt = bitmap;

                            }

                            @Override
                            public void onBitmapFailed(Drawable errorDrawable) {

                            }

                            @Override
                            public void onPrepareLoad(Drawable placeHolderDrawable) {

                            }
                        });

                        createInvoiceObject();


                    } else {
                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Error en el servicio");
                    }

                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                        Toast.makeText(Eleccion_calProd.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }catch (Throwable x){}
                }
            }

            @Override
            public void onFailure(Call<List<Calidad>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(Eleccion_calProd.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });
    }





    public void Calidad(View view) {
        Intent intent = new Intent(this, CalidadModulo.class);
        startActivity(intent);
    }
    public void Producción(View view) {
        Intent intent = new Intent(this, ProduccionModulo.class);
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
    invoiceDetails1.itemCode="Producto :";
    invoiceDetails1.itemName=catador.get(0).getProducto();

     InvoiceDetails invoiceDetails2 = new InvoiceDetails();
        invoiceDetails2.itemCode="Nombre Científico: ";
        invoiceDetails2.itemName=catador.get(0).getNombreCientifico();

        InvoiceDetails invoiceDetails3 = new InvoiceDetails();
        invoiceDetails3.itemCode="Temperatura :";
        invoiceDetails3.itemName=catador.get(0).getTemperatura();

        InvoiceDetails invoiceDetails4 = new InvoiceDetails();
        invoiceDetails4.itemCode="HR de Conservación:";
        invoiceDetails4.itemName= String.valueOf(catador.get(0).getHRConservacion());

        InvoiceDetails invoiceDetails5 = new InvoiceDetails();
        invoiceDetails5.itemCode="Marca :";
        invoiceDetails5.itemName=catador.get(0).getMarca();

        InvoiceDetails invoiceDetails6 = new InvoiceDetails();
        invoiceDetails6.itemCode="Presentación :";
        invoiceDetails6.itemName=catador.get(0).getPresentacion();

        InvoiceDetails invoiceDetails7 = new InvoiceDetails();
        invoiceDetails7.itemCode="Dimensión :";
        invoiceDetails7.itemName=catador.get(0).getDimension();

        InvoiceDetails invoiceDetails8 = new InvoiceDetails();
        invoiceDetails8.itemCode="Material :";
        invoiceDetails8.itemName=catador.get(0).getMaterial();

        InvoiceDetails invoiceDetails9 = new InvoiceDetails();
        invoiceDetails9.itemCode="Etiqueta de trazabilidad : ";
        invoiceDetails9.itemName=catador.get(0).getEtiquetaTrazabilidad();

        InvoiceDetails invoiceDetails10 = new InvoiceDetails();
        invoiceDetails10.itemCode="Peso neto :";
        invoiceDetails10.itemName=catador.get(0).getPesoNeto();

        InvoiceDetails invoiceDetails11 = new InvoiceDetails();
        invoiceDetails11.itemCode="Cajas por parihuela aérea :";
        invoiceDetails11.itemName=catador.get(0).getCajasParihuelaAerea();

        InvoiceDetails invoiceDetails12 = new InvoiceDetails();
        invoiceDetails12.itemCode="Cajas por parihuela Marítima :";
        invoiceDetails12.itemName=catador.get(0).getCajasParihuelaMaritima();

        InvoiceDetails invoiceDetails13 = new InvoiceDetails();
        invoiceDetails13.itemCode="Dimensiones y material de la parihuela :";
        invoiceDetails13.itemName=catador.get(0).getDimensionesMaterialParihuela();

        invoiceObject.invoiceDetailsList = new ArrayList<InvoiceDetails>();

        invoiceObject.invoiceDetailsList.add(invoiceDetails1);
        invoiceObject.invoiceDetailsList.add(invoiceDetails2);
        invoiceObject.invoiceDetailsList.add(invoiceDetails3);
        invoiceObject.invoiceDetailsList.add(invoiceDetails4);
        invoiceObject.invoiceDetailsList.add(invoiceDetails5);
        invoiceObject.invoiceDetailsList.add(invoiceDetails6);
        invoiceObject.invoiceDetailsList.add(invoiceDetails7);
        invoiceObject.invoiceDetailsList.add(invoiceDetails8);
        invoiceObject.invoiceDetailsList.add(invoiceDetails9);
        invoiceObject.invoiceDetailsList.add(invoiceDetails10);
        invoiceObject.invoiceDetailsList.add(invoiceDetails11);
        invoiceObject.invoiceDetailsList.add(invoiceDetails12);
        invoiceObject.invoiceDetailsList.add(invoiceDetails13);



        //////////////////////


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
                assert pdfCalidadManager != null;
                pdfCalidadManager.createPdfDocument(invoiceObject,bt);
            }
            return;
        }
    }



}
