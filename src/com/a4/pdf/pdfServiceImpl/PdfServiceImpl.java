package com.a4.pdf.pdfServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.a4.pdf.ipdfDao.IpdfDao;
import com.a4.pdf.ipdfService.IPdfService;
import com.a4.pdf.model.PurchaseOrder;

public class PdfServiceImpl implements IPdfService{
	@Autowired
	private IpdfDao pdfDao;
@Override
public void saveInvoiceDetails() {
	pdfDao.saveInvoiceDetails();
}
@Override
	public void savePoDetails(List<PurchaseOrder> pruchaseOrdersList) {
	
	 for (PurchaseOrder purchaseOrder : pruchaseOrdersList) {
		
	}    
	
		pdfDao.savePoDetails();
	}
}
