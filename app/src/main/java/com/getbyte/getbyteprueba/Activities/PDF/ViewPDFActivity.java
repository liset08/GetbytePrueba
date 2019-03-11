package com.getbyte.getbyteprueba.Activities.PDF;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.getbyte.getbyteprueba.R;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

public class ViewPDFActivity extends AppCompatActivity {

    private PDFView pdfView;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pdf);

        pdfView=(PDFView)findViewById(R.id.pdfView);

    }
}
