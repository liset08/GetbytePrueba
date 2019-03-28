package com.getbyte.getbyteprueba.Activities.ADMIN_CAL_PROD;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.text.Layout;
import android.util.Log;
import android.widget.Toast;

import com.getbyte.getbyteprueba.Activities.PDF.EvaluacionCua;
import com.getbyte.getbyteprueba.Activities.PDF.EvaluacionQuin;
import com.getbyte.getbyteprueba.Activities.PDF.EvaluacionTer;
import com.getbyte.getbyteprueba.Activities.PDF.EvalucaionSeg;
import com.getbyte.getbyteprueba.Activities.PDF.InvoiceDetails;
import com.getbyte.getbyteprueba.Activities.PDF.InvoiceObject;
import com.getbyte.getbyteprueba.R;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class PDFCalidadManager {

    private static Context mContext;
    private static final String APP_FOLDER_NAME = "com.movalink.pdf";
    private static final String INVOICES = "Invoices";
    private static Font catFont;
    private static Font subFont ;
    private static Font smallBold ;
    private static Font smallFont ;
    private static Font italicFont ;
    private static Font italicFontBold ;
    //Declaramos nuestra fuente base que se encuentra en la carpeta "assets/fonts" folder
    //Usaremos arialuni.ttf que permite imprimir en nuestro PDF caracteres Unicode Cirílicos (Ruso, etc)
    private static BaseFont unicode;

    //!!!Importante: La carpeta "assets/fonts/arialuni.ttf" debe estar creada en nuestro projecto en
    //la subcarpeta "PdfCreator/build/exploded-bundles/ComAndroidSupportAppcompactV71900.aar"
    //En el caso de que Android Studio la eliminara la copiamos manualmente
    //PdfCreator/build/exploded-bundles/ComAndroidSupportAppcompactV71900.aarassets/fonts/arialuni.ttf
    private static File fontFile = new File("res/font/arialuni.ttf");
    //Constructor set fonts and get context
    public PDFCalidadManager(Context context) throws IOException, DocumentException {
        mContext = context;
        //Creamos los distintos estilos para nuestro tipo de fuente.
        unicode = BaseFont.createFont(fontFile.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        catFont = new Font(unicode, 22,Font.BOLD, BaseColor.BLACK);
        subFont = new Font(unicode, 16,Font.BOLD, BaseColor.BLACK);
        smallBold = new Font(unicode, 12,Font.BOLD, BaseColor.BLACK);
        smallFont = new Font(unicode, 12,Font.NORMAL, BaseColor.BLACK);
        italicFont = new Font(unicode, 12,Font.ITALIC, BaseColor.BLACK);
        italicFontBold = new Font(unicode, 12,Font.ITALIC|Font.BOLD, BaseColor.BLACK);
    }

    public void createPdfDocument(InvoiceObject invoiceObject,Bitmap bitmap) {
        try {

            //Creamos las carpetas en nuestro dispositivo, si existen las eliminamos.
            String fullFileName = createDirectoryAndFileName();

            if(fullFileName.length()>0){
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(fullFileName));

                bitmap = bitmap.createScaledBitmap(bitmap,170,170,false);  // cambio de resolucion de la imagen mapeada bitmap 96x96
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                Image myImg = Image.getInstance(stream.toByteArray());
              //  myImg.setAbsolutePosition(400f, 650f);
                myImg.setAlignment(Image.ALIGN_CENTER);

                document.open();

                //Creamos los metadatos del alchivo
                addMetaData(document);
                //Adicionamos el logo de la empresa
                addImage(document);
                //Creamos el título del documento
                addTitlePage(document, invoiceObject);
                //Creamos el contenido en form de tabla del documento
                String title = "";
                addTitlePage2(document, title);
                addInvoiceContent(document,invoiceObject.invoiceDetailsList);
                addTitlePage2(document, title);
                document.newPage();

                String title2 = "Evidencia de la evaluación";

                addTitlePage2(document, title2);
                //Adicionamos el logo de la empresa
                document.add(myImg);
                String title3 = "Alimentos en buen estado sin presencia de lago extraño.";

                addTitlePage3(document, title3);

                document.add(myImg);

                addTitlePage3(document, title3);

                String title4 = "Identificacion de algun objeto extraño.";

                document.add(myImg);

                addTitlePage3(document, title4);
                //Creamos el total de la factura del documento

                document.close();

                Toast.makeText(mContext, "PDF creado exitosamente", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String createDirectoryAndFileName(){

        String FILENAME = "InvoiceSample.pdf";
        String fullFileName ="";
        //Obtenemos el directorio raiz "/sdcard"
        String extStorageDirectory = Environment.getExternalStorageDirectory().getPath();
        File pdfDir = new File(extStorageDirectory + File.separator + APP_FOLDER_NAME);

        //Creamos la carpeta "com.movalink.pdf" y la subcarpeta "Invoice"
        try {
            if (!pdfDir.exists()) {
                pdfDir.mkdir();
            }
            File pdfSubDir = new File(pdfDir.getPath() + File.separator + INVOICES);

            if (!pdfSubDir.exists()) {
                pdfSubDir.mkdir();
            }

            fullFileName = Environment.getExternalStorageDirectory() + File.separator + APP_FOLDER_NAME + File.separator + INVOICES + File.separator + FILENAME;

            File outputFile = new File(fullFileName);

            if (outputFile.exists()) {
                outputFile.delete();
            }
        } catch (ActivityNotFoundException e) {
            Toast.makeText(mContext,e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return fullFileName;
    }

    //PDF library add file metadata function
    private static void addMetaData(Document document) {
        document.addTitle("movalink PDF");
        document.addSubject("Using iText");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("movalink.com");
        document.addCreator("movalink.com");
    }

    //Creando el Título y los datos de la Empresa y el Cliente
    private static void addTitlePage(Document document, InvoiceObject invoiceObject)
            throws DocumentException {

        Paragraph preface = new Paragraph();
        // Adicionamos una línea en blanco
        addEmptyLine(preface, 1);
        // Adicionamos el títulos de la Factura y el número
        preface.add(new Paragraph(mContext.getResources().getString(R.string.invoice_number) , catFont));
        preface.add(new Paragraph(mContext.getResources().getString(R.string.invoice_date), subFont));



        addEmptyLine(preface, 1);
        addEmptyLine(preface, 1);
        addEmptyLine(preface, 1);



        //Adicionamos el párrafo creado al documento
        document.add(preface);

        // Si queremos crear una nueva página
        //document.newPage();
    }
    private static void addTitlePage2(Document document, String invoiceObject)
            throws DocumentException {

        Paragraph preface = new Paragraph();
        // Adicionamos una línea en blanco
        addEmptyLine(preface, 1);

        //Adicionamos los datos del Cliente
        preface.add(new Paragraph(invoiceObject,subFont));

        addEmptyLine(preface, 1);

        //Adicionamos el párrafo creado al documento
        document.add(preface);

        // Si queremos crear una nueva página
        //document.newPage();
    }

   public static Paragraph prefaces;
    private static void addTitlePage3(Document document, String invoiceObject)
            throws DocumentException {
try {


        prefaces = new Paragraph();
        // Adicionamos una línea en blanco
        addEmptyLine(prefaces, 1);

        //Adicionamos los datos del Cliente
        addChild(new Paragraph(invoiceObject,smallFont));

        addEmptyLine(prefaces, 1);

        //Adicionamos el párrafo creado al documento
        document.add(prefaces);
}catch (Exception e){
    Log.e("titlt",e.toString());
}
        // Si queremos crear una nueva página
        //document.newPage();
    }
    public static void addChild(Paragraph chilParagraph){
        chilParagraph.setAlignment(Element.ALIGN_CENTER);
        prefaces.add(chilParagraph);

    }

    //Creamos el contenido de la factura, las líneas con los artículos.
    private static void addInvoiceContent(Document document, java.util.List<InvoiceDetails> invoiceDetail) throws DocumentException {

        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 1);
        // Creamos una tabla con los títulos de las columnas
        createInvoiceTable(paragraph, invoiceDetail);
        // Adicionamos el párrafo al documento
        document.add(paragraph);

    }


    //Creamos el subtotal y el total de la factura.
    private static void addInvoiceTotal(Document document, InvoiceObject invoiceObject) throws DocumentException {

        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 1);
        // Adicionamos la tabla al párrafo
        createTotalInvoiceTable(paragraph, invoiceObject);
        // Adicionamos el párrafo al documento
        document.add(paragraph);

    }
    //Procedimiento para crear los títulos de las columnas de la factura.
    private static void createInvoiceTable(Paragraph tableSection, java.util.List<InvoiceDetails> invoiceDetails)
            throws DocumentException {

        int TABLE_COLUMNS = 2;
        //Instaciamos el objeto Pdf Table y creamos una tabla con las columnas definidas en TABLE_COLUMNS
        PdfPTable table = new PdfPTable(TABLE_COLUMNS);// number of table columns

        //Definimos el ancho que corresponde a cada una de las 5 columnas
        float[] columnWidths = new float[]{200f, 100f};
        table.setWidths(columnWidths);
        //Definimos el ancho de nuestra tabla en %
        table.setWidthPercentage(100);

        // Aquí les dejos otras propiedades que pueden aplicar a la tabla
      // table.setBorderColor(BaseColor.GRAY);
    table.setPaddingTop(20);

    table.setSpacingAfter(20);
    table.setSpacingBefore(20);
   // table.setBorderWidth(1);

        //Definimos los títulos para cada una de las 5 columnas
        PdfPCell cell = new PdfPCell(new Phrase(mContext.getResources().getString(R.string.detail_code),smallBold));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setMinimumHeight(40);
        //Adicionamos el título de la primera columna
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(mContext.getResources().getString(R.string.detail_description),smallBold));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        //Adicionamos el título de la segunda columna
        table.addCell(cell);


        //Creamos la fila de la tabla con las cabeceras
        table.setHeaderRows(1);

        //Creamos las lineas con los artículos de la factura;
        for (InvoiceDetails orderLine : invoiceDetails) {
            createInvoiceLine(orderLine, table);
        }

        tableSection.add(table);
    }




    //Procedimiento para crear una lines vacía
    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
    //Procedimiento para adicionar una imagen al documento PDF
    private static void addImage(Document document) throws IOException, DocumentException {

        Bitmap bitMap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.logo_blanco);
        bitMap = bitMap.createScaledBitmap(bitMap,96,96,false);  // cambio de resolucion de la imagen mapeada bitmap 96x96
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitMap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
        byte[] bitMapData = stream.toByteArray();
        Image image = Image.getInstance(bitMapData);
        //Posicionamos la imagen el el documento
        image.setAbsolutePosition(400f, 650f);


        document.add(image);
    }
    //Procedimiento para crear las líneas de la factura en forma de tabla.
    private static void createInvoiceLine(InvoiceDetails invoiceLine, PdfPTable table) {
        PdfPCell cell = new PdfPCell();

        //Adicionamos celdas sin formato ni estilos, solo el valor

        table.addCell(invoiceLine.itemCode);
        cell.setPhrase(new Phrase((invoiceLine.itemCode),smallFont));

        cell.setPhrase(new Phrase(String.valueOf(invoiceLine.itemName),smallFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setMinimumHeight(25);

        table.addCell(cell);






    }


    //Procedimiento para crear los totales y subtotales de la factura en forma de tabla.
    //Misma lógica utilizada para crear los títulos de las columnas de la factura
    private static void createTotalInvoiceTable(Paragraph tableSection, InvoiceObject orderHeaderModel)
            throws DocumentException {

        int TABLE_COLUMNS = 3;
        PdfPTable table = new PdfPTable(TABLE_COLUMNS);

        float[] columnWidths = new float[]{200f, 60f,60f};
        table.setWidths(columnWidths);

        table.setWidthPercentage(100);

        //Adicionamos el título de la celda
        PdfPCell cell = new PdfPCell(new Phrase(mContext.getResources().getString(R.string.invoice_subtotal),smallBold));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(cell);

        double subTotal = orderHeaderModel.total;
        //Adicionamos el contenido de la celda con el valor subtotal
        cell = new PdfPCell(new Phrase(String.valueOf(subTotal)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(String.valueOf(subTotal)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);


        tableSection.add(table);

    }
    //Procedimiento para mostrar el documento PDF generado
    public void showPdfFile(String fileName, Context context){
        Toast.makeText(context, "Leyendo documento", Toast.LENGTH_LONG).show();

        String sdCardRoot = Environment.getExternalStorageDirectory().getPath();
        String path = sdCardRoot + File.separator + APP_FOLDER_NAME + File.separator + fileName;

        File file = new File(path);

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file),"application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            context.startActivity(intent);
        }
        catch (ActivityNotFoundException e) {
            Toast.makeText(context, "No Application Available to View PDF", Toast.LENGTH_SHORT).show();
        }
    }

    //Procedimiento para enviar por email el documento PDF generado
    public void sendPdfByEmail(String fileName, String emailTo, String emailCC, Context context){

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Movalink PDF Tutorial email");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Working with PDF files in Android");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailTo});
        emailIntent.putExtra(Intent.EXTRA_BCC, new String[]{emailCC});

        String sdCardRoot = Environment.getExternalStorageDirectory().getPath();
        String fullFileName = sdCardRoot + File.separator + APP_FOLDER_NAME + File.separator + fileName;

        Uri uri = Uri.fromFile(new File(fullFileName));
        emailIntent.putExtra(Intent.EXTRA_STREAM, uri);
        emailIntent.setType("application/pdf");

        context.startActivity(Intent.createChooser(emailIntent, "Send email using:"));
    }

}
