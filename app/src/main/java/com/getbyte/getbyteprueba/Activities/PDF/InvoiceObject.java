package com.getbyte.getbyteprueba.Activities.PDF;

import java.util.List;

/**
 * Created by LISET on 17/01/2019.
 */

public class InvoiceObject {


    public int id;
    public String companyName;
    public String companyAddress;
    public String companyCountry;
    public String clientName;
    public String clientAddress;
    public String clientTelephone;
    public String date;
    public double total;

    public List<InvoiceDetails> invoiceDetailsList;
    public List<EvalucaionSeg> invoiceDetailsList2;
    public List<EvaluacionTer> invoiceDetailsList3;
    public List<EvaluacionCua> invoiceDetailsList4;
    public List<EvaluacionQuin> invoiceDetailsList5;

    public float clientCountry;
}
