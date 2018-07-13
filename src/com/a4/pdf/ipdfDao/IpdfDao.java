package com.a4.pdf.ipdfDao;

import java.util.List;

import com.a4.pdf.entity.InvoiceDetailsEntity;
import com.a4.pdf.entity.POEntity;

public interface IpdfDao {
	public void savePoDetails(POEntity poEntity);

	public void saveInvoiceDetails(InvoiceDetailsEntity invoiceDetailsEntity);

	public List<String> getAllPONumber();

	public List<String> getAllInvoiceNumber();

	public POEntity getPODetails(String poNo);

	public InvoiceDetailsEntity getInvoiceDetails(String invoiceNo);

}
