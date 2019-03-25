package com.getbyte.getbyteprueba.Activities.ADMIN_CAL_PROD;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.getbyte.getbyteprueba.Activities.PDF.EvaluacionCua;
import com.getbyte.getbyteprueba.Activities.PDF.EvaluacionQuin;
import com.getbyte.getbyteprueba.Activities.PDF.EvaluacionTer;
import com.getbyte.getbyteprueba.Activities.PDF.EvalucaionSeg;
import com.getbyte.getbyteprueba.Activities.PDF.InvoiceDetails;
import com.getbyte.getbyteprueba.Activities.PDF.InvoiceObject;
import com.getbyte.getbyteprueba.Activities.PDF.PdfManager;
import com.getbyte.getbyteprueba.Activities.RadarActivity;
import com.getbyte.getbyteprueba.Model.Calidad;
import com.getbyte.getbyteprueba.Model.Graficos;
import com.getbyte.getbyteprueba.R;
import com.getbyte.getbyteprueba.Service.ApiServiceGenerator;
import com.getbyte.getbyteprueba.Service.ResponseMessage;
import com.getbyte.getbyteprueba.Service.UserClient;
import com.itextpdf.awt.geom.Dimension;
import com.itextpdf.text.DocumentException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalidadModulo extends AppCompatActivity {
    private static final String TAG = "CalidadModulo";
    private ImageView imagePreview;
   EditText Producto,Nombre_Científico,Temperatura,HR_Conservación,Marca
            ,Presentacion,Dimensión,Material, Etiqueta_trazabilidad,Peso_neto,
            Cajas_parihuela_aerea,Cajas_parihuela_Marítima,Dimensiones_material_parihuela;


    String producto,nombre_Científico,marca,temperatura,peso_neto,
            dimensión,material,etiqueta_trazabilidad ,presentacion,
            cajas_parihuela_aerea,cajas_parihuela_Marítima,dimensiones_material_parihuela ;

    Integer hR_Conservación;

    /*variable para el pdf*/
    InvoiceObject invoiceObject = new InvoiceObject();
    private String INVOICES_FOLDER = "Invoices";
    private String FILENAME = "InvoiceSample.pdf";

    private PDFCalidadManager pdfCalidadManager = null;

    String[] permissions = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calidad_modulo);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());


       imagePreview = findViewById(R.id.imagepreview);

        Producto = (EditText)findViewById(R.id.etxt_producto);
        Nombre_Científico = (EditText)findViewById(R.id.etxt_namecienti);

       // HR_Conservación = (EditText) findViewById(R.id.etxt_c);
        Marca = (EditText)findViewById(R.id.etxt_marca);
        Presentacion = (EditText)findViewById(R.id.etxt_presentacion);
        Dimensión = (EditText)findViewById(R.id.etxt_dimension);
        Material = (EditText)findViewById(R.id.etxt_material);
        Temperatura = (EditText)findViewById(R.id.etxt_temperatura);

        Etiqueta_trazabilidad = (EditText)findViewById(R.id.etxt_trazabi);
        Peso_neto = (EditText) findViewById(R.id.etxt_pesoneto);
        Cajas_parihuela_aerea = (EditText) findViewById(R.id.etxt_cajasaerea);
        Dimensiones_material_parihuela = (EditText) findViewById(R.id.etxt_dimensionMatparihuela);
        Cajas_parihuela_Marítima = (EditText) findViewById(R.id.etxt_cajasmaritima);




    }


    private static final int CAPTURE_IMAGE_REQUEST = 300;

    private Uri mediaFileUri;

    public void takePicture(View view) {
        try {
            /*******************/
            producto = Producto.getText().toString();
            nombre_Científico = Nombre_Científico.getText().toString();
            hR_Conservación =Integer.parseInt("900");
            presentacion =Presentacion.getText().toString();
            dimensión = Presentacion.getText().toString();
            material = Material.getText().toString();
            temperatura = Temperatura.getText().toString();
            marca = Marca.getText().toString();
            etiqueta_trazabilidad = Etiqueta_trazabilidad.getText().toString();
            peso_neto =  Peso_neto.getText().toString();
            cajas_parihuela_aerea = Cajas_parihuela_aerea.getText().toString();
            dimensiones_material_parihuela = Dimensiones_material_parihuela.getText().toString();
            cajas_parihuela_Marítima = Cajas_parihuela_Marítima.getText().toString();

Log.d(TAG,"REVISASSERRRRRR : "+producto);
            if (!permissionsGranted()) {
                ActivityCompat.requestPermissions(this, PERMISSIONS_LIST, PERMISSIONS_REQUEST);
                return;
            }

            // Creando el directorio de imágenes (si no existe)
            File mediaStorageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            if (!mediaStorageDir.exists()) {
                if (!mediaStorageDir.mkdirs()) {
                    throw new Exception("Failed to create directory");
                }
            }

            // Definiendo la ruta destino de la captura (Uri)
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
            mediaFileUri = Uri.fromFile(mediaFile);

            // Iniciando la captura
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, mediaFileUri);
            startActivityForResult(intent, CAPTURE_IMAGE_REQUEST);

        } catch (Exception e) {
            Log.e(TAG, e.toString());
            Toast.makeText(this, "Error en captura: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_REQUEST) {
            // Resultado en la captura de la foto
            if (resultCode == RESULT_OK) {
                try {
                    Log.d(TAG, "ResultCode: RESULT_OK");
                    // Toast.makeText(this, "Image saved to: " + mediaFileUri.getPath(), Toast.LENGTH_LONG).show();

                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), mediaFileUri);

                    // Reducir la imagen a 800px solo si lo supera
                    bitmap = scaleBitmapDown(bitmap, 200);

                    imagePreview.setImageBitmap(bitmap);
                    crearCata();
                } catch (Exception e) {
                    Log.d(TAG, e.toString());
                    Toast.makeText(this, "Error al procesar imagen: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            } else if (resultCode == RESULT_CANCELED) {
                Log.d(TAG, "ResultCode: RESULT_CANCELED");
            } else {
                Log.d(TAG, "ResultCode: " + resultCode);
            }
        }
    }
    private static final int PERMISSIONS_REQUEST = 200;


    private static String[] PERMISSIONS_LIST = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };


    private boolean permissionsGranted() {
        for (String permission : PERMISSIONS_LIST) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED)
                return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST: {
                for (int i = 0; i < grantResults.length; i++) {
                    Log.d(TAG, "" + grantResults[i]);
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, PERMISSIONS_LIST[i] + " permiso rechazado!", Toast.LENGTH_LONG).show();

                        return;
                    }
                }
                Toast.makeText(this, "Permisos concedidos, intente nuevamente.", Toast.LENGTH_LONG).show();

            }
        }
    }

    // Redimensionar una imagen bitmap
    private Bitmap scaleBitmapDown(Bitmap bitmap, int maxDimension) {

        int originalWidth = bitmap.getWidth();
        int originalHeight = bitmap.getHeight();
        int resizedWidth = maxDimension;
        int resizedHeight = maxDimension;

        if (originalHeight > originalWidth) {
            resizedHeight = maxDimension;
            resizedWidth = (int) (resizedHeight * (float) originalWidth / (float) originalHeight);
        } else if (originalWidth > originalHeight) {
            resizedWidth = maxDimension;
            resizedHeight = (int) (resizedWidth * (float) originalHeight / (float) originalWidth);
        } else if (originalHeight == originalWidth) {
            resizedHeight = maxDimension;
            resizedWidth = maxDimension;
        }
        return Bitmap.createScaledBitmap(bitmap, resizedWidth, resizedHeight, false);
    }

    public void crearCata(){

        File file = new File(mediaFileUri.getPath());
        Log.d(TAG, "File: " + file.getPath() + " - exists: " + file.exists());

        // Podemos enviar la imagen con el tamaño original, pero lo mejor será comprimila antes de subir (byteArray)
        // RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), file);

        Bitmap bitmap = BitmapFactory.decodeFile(mediaFileUri.getPath());

        // Reducir la imagen a 800px solo si lo supera
        bitmap = scaleBitmapDown(bitmap, 800);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), byteArray);

        Calidad cal=new Calidad();
        cal.setProducto(producto);
        cal.setNombreCientifico(nombre_Científico);
        cal.setTemperatura(temperatura);
        cal.setHRConservacion(hR_Conservación);
        cal.setMarca(marca);
        cal.setPresentacion(presentacion);
        cal.setDimension(dimensión);
        cal.setMaterial(material);
        cal.setEtiquetaTrazabilidad(etiqueta_trazabilidad);
        cal.setPesoNeto(peso_neto);
        cal.setCajasParihuelaAerea(cajas_parihuela_aerea);
        cal.setCajasParihuelaMaritima(cajas_parihuela_Marítima);
        cal.setDimensionesMaterialParihuela(dimensiones_material_parihuela);


    MultipartBody.Part imagenPart = MultipartBody.Part.createFormData("imagen", file.getName(), requestFile);

        RequestBody nombrePart = RequestBody.create(MultipartBody.FORM, producto);
        RequestBody precioPart = RequestBody.create(MultipartBody.FORM, nombre_Científico);
        RequestBody detallesPart = RequestBody.create(MultipartBody.FORM, temperatura);
        RequestBody hRPart = RequestBody.create(MultipartBody.FORM, String.valueOf(hR_Conservación));
        RequestBody marcaPart = RequestBody.create(MultipartBody.FORM, marca);
        RequestBody presentacionPart = RequestBody.create(MultipartBody.FORM, presentacion);
        RequestBody dimensiónPart = RequestBody.create(MultipartBody.FORM, dimensión);
        RequestBody materialPart = RequestBody.create(MultipartBody.FORM, material);
        RequestBody etiquetaPart = RequestBody.create(MultipartBody.FORM, etiqueta_trazabilidad);
        RequestBody pesoPart = RequestBody.create(MultipartBody.FORM, peso_neto);
        RequestBody cajasaPart = RequestBody.create(MultipartBody.FORM, cajas_parihuela_aerea);
        RequestBody cajasmPart = RequestBody.create(MultipartBody.FORM, cajas_parihuela_Marítima);
        RequestBody dimensionesPart = RequestBody.create(MultipartBody.FORM, dimensiones_material_parihuela);








        UserClient service = ApiServiceGenerator.createService(UserClient.class);

        Call<ResponseMessage> calls = service.createCalidad(nombrePart, precioPart,detallesPart,hRPart,marcaPart,presentacionPart,dimensiónPart,materialPart,etiquetaPart,pesoPart,cajasaPart,cajasmPart,dimensionesPart,imagenPart);

        calls.enqueue(new Callback<ResponseMessage>() {
            @Override
            public void onResponse(Call<ResponseMessage> calls, Response<ResponseMessage> response) {
                try {

                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {

                        ResponseMessage responseMessage = response.body();
                        Log.d(TAG, "responseMessage: " + responseMessage);
                        Toast.makeText(CalidadModulo.this, "Registro completado",Toast.LENGTH_SHORT).show();

                        finish();

                    } else {
                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Error en el servicio");
                    }

                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                    } catch (Throwable x) {
                    }
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
}


}