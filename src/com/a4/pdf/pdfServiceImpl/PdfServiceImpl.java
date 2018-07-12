package com.a4.pdf.pdfServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.a4.pdf.entity.POEntity;
import com.a4.pdf.entity.POShippingDetailsEntity;
import com.a4.pdf.entity.VendorDetailsEntity;
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
	 POEntity poEntity = new POEntity();
	 List<VendorDetailsEntity> listOfVendorDetails = new ArrayList<>();
	 POShippingDetailsEntity poShippingDetails = new POShippingDetailsEntity();
	 int purchaseOrderNo = 1;
	 for (PurchaseOrder purchaseOrder : pruchaseOrdersList) {
		if(purchaseOrderNo == 1){
			poEntity.setPoNumber(purchaseOrder.getPoNo());
			//poEntity.setJobId(purchaseOrder.getJob());
			poEntity.setPoAddress(purchaseOrder.getPoAddress());
			poEntity.setVendorNo(purchaseOrder.getVendorNo());
			//poEntity.set
			
		} else {
			
		}
		
		purchaseOrderNo++;
	}    
	
		pdfDao.savePoDetails();
	}
}
