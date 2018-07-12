package com.a4.pdf.ipdfDao;

import com.a4.pdf.entity.InvoiceDetailsEntity;
import com.a4.pdf.entity.POEntity;

public interface IpdfDao {
 public void savePoDetails(POEntity poEntity);
 public void saveInvoiceDetails(InvoiceDetailsEntity invoiceDetailsEntity);
 
}
