package com.a4.pdf.ipdfService;

import java.util.List;

import com.a4.pdf.entity.InvoiceDetailsEntity;
import com.a4.pdf.entity.POEntity;
import com.a4.pdf.model.PurchaseOrder;

public interface IPdfService {
	public void savePoDetails(List<PurchaseOrder> purchaseOrder);

	public void saveInvoiceDetails();

	public List<String> getAllPONumber();

	public List<String> getAllInvoiceNumber();

	public POEntity getPODetails(String poNo);

	public InvoiceDetailsEntity getInvoiceDetails(String invoiceNo);

}
