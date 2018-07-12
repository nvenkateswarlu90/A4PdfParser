package com.a4.pdf.ipdfService;

import java.util.List;

import com.a4.pdf.model.PurchaseOrder;

public interface IPdfService {
	 public void savePoDetails(List<PurchaseOrder> purchaseOrder);
	 public void saveInvoiceDetails();
}
