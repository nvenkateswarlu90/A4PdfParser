package com.a4.pdf.ipdfService;

import java.util.List;

import com.a4.pdf.model.InVoiceBean;
import com.a4.pdf.model.PurchaseOrder;

public interface IPdfService {
	public void savePoDetails(List<PurchaseOrder> purchaseOrder);

	public List<String> getAllPONumber();

	public List<String> getAllInvoiceNumber();

	public List<PurchaseOrder> getPODetails(String poNo);

	public InVoiceBean getInvoiceDetails(String invoiceNo);

	public void saveInvoiceDetails(InVoiceBean invoObj);

}
