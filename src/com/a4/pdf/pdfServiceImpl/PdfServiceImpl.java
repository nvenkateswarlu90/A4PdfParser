package com.a4.pdf.pdfServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.a4.pdf.entity.InvoiceDetailsEntity;
import com.a4.pdf.entity.POEntity;
import com.a4.pdf.entity.VendorDetailsEntity;
import com.a4.pdf.ipdfDao.IpdfDao;
import com.a4.pdf.ipdfService.IPdfService;
import com.a4.pdf.model.PurchaseOrder;


public class PdfServiceImpl implements IPdfService {
	//@Autowired
	private IpdfDao pdfDao;

	@Override
	public void saveInvoiceDetails() {
		pdfDao.saveInvoiceDetails(new InvoiceDetailsEntity());
	}

	
	@Override
	public void savePoDetails(List<PurchaseOrder> pruchaseOrdersList) {
		POEntity poEntity = new POEntity();
		List<VendorDetailsEntity> listOfVendorDetails = new ArrayList<>();
		VendorDetailsEntity vendorDetails = new VendorDetailsEntity();
		vendorDetails.setCustPo("123");
		vendorDetails.setImprintLocation("side Location");
		vendorDetails.setInstructionToFactory1("instructiontofactory");
		vendorDetails.setInstructionToFactory2("instructiontofactory2");
		vendorDetails.setLogisticInfo("via amazon");
		vendorDetails.setProductDetails("red,black,2x");
		//listOfVendorDetails.add(vendorDetails);
		poEntity.setJobId(123456);
		poEntity.setPoAddress("kp aurum,Mumbai");
		poEntity.setPoNumber("1201");
		poEntity.setVendorNo("Puma");
		poEntity.addVendorDetailsEntity(vendorDetails);
		//poEntity.setListOfVendorDetails(listOfVendorDetails);
		/*// POShippingDetailsEntity pOShippingDetailsEntity = null;
		VendorDetailsEntity vendorDetailsEntity = null;
		int purchaseOrderNo = 1;
		for (PurchaseOrder purchaseOrder : pruchaseOrdersList) {
			vendorDetailsEntity = new VendorDetailsEntity();
			if (purchaseOrderNo == 1) {
				poEntity.setPoNumber(purchaseOrder.getPoNo());
				poEntity.setJobId(Integer.parseInt(purchaseOrder.getJob()));
				poEntity.setPoAddress(purchaseOrder.getPoAddress());
				poEntity.setVendorNo(purchaseOrder.getVendorNo());
				// set Vendor Details
				vendorDetailsEntity = getVendorDetals(purchaseOrder);
			} else {
				vendorDetailsEntity = getVendorDetals(purchaseOrder);
			}
			purchaseOrderNo++;
			listOfVendorDetails.add(vendorDetailsEntity);
		}
		poEntity.setListOfVendorDetails(listOfVendorDetails);
		pdfDao.savePoDetails(poEntity);*/
		pdfDao.savePoDetails(poEntity);
	}
	//@Override
	public void savePoDetails1(List<PurchaseOrder> pruchaseOrdersList) {
		POEntity poEntity = new POEntity();
		List<VendorDetailsEntity> listOfVendorDetails = new ArrayList<>();
		// POShippingDetailsEntity pOShippingDetailsEntity = null;
		VendorDetailsEntity vendorDetailsEntity = null;
		int purchaseOrderNo = 1;
		for (PurchaseOrder purchaseOrder : pruchaseOrdersList) {
			vendorDetailsEntity = new VendorDetailsEntity();
			if (purchaseOrderNo == 1) {
				poEntity.setPoNumber(purchaseOrder.getPoNo());
				poEntity.setJobId(Integer.parseInt(purchaseOrder.getJob()));
				poEntity.setPoAddress(purchaseOrder.getPoAddress());
				poEntity.setVendorNo(purchaseOrder.getVendorNo());
				// set Vendor Details
				vendorDetailsEntity = getVendorDetals(purchaseOrder);
			} else {
				vendorDetailsEntity = getVendorDetals(purchaseOrder);
			}
			purchaseOrderNo++;
			listOfVendorDetails.add(vendorDetailsEntity);
		}
		poEntity.setListOfVendorDetails(listOfVendorDetails);
		pdfDao.savePoDetails(poEntity);
	}
	private VendorDetailsEntity getVendorDetals(PurchaseOrder purchaseOrder) {
		VendorDetailsEntity vendorDetailsEntity = new VendorDetailsEntity();
		vendorDetailsEntity.setCustPo(purchaseOrder.getCustomerPo());
		vendorDetailsEntity.setImprintLocation(purchaseOrder.getProductImprintLocation());
		vendorDetailsEntity.setInstructionToFactory1(purchaseOrder.getInstructionsToFactory1());
		vendorDetailsEntity.setInstructionToFactory2(purchaseOrder.getInstructionsToFactory2());
		vendorDetailsEntity.setOrdDate(purchaseOrder.getOrderDate());
		vendorDetailsEntity.setProductCriteriaInstruction(purchaseOrder.getProductcriteria().toString());
		vendorDetailsEntity.setProductDetails(purchaseOrder.getOrderDetails().toString());
		vendorDetailsEntity.setRequestDate(purchaseOrder.getShippingRequest());
		vendorDetailsEntity.setSalesPerson(purchaseOrder.getSalesPerson());
		vendorDetailsEntity.setTerms(purchaseOrder.getTerms());
		vendorDetailsEntity.setVendorAddress(purchaseOrder.getVendorAddress());
		vendorDetailsEntity.setVendorNo(purchaseOrder.getVendorNo());
		vendorDetailsEntity.setShippingAddress(purchaseOrder.getShippingAddress());
		vendorDetailsEntity.setLogisticInfo(purchaseOrder.getLogisticInfo());

		return vendorDetailsEntity;
	}
	@Override
	public List<String> getAllInvoiceNumber() {
		return pdfDao.getAllInvoiceNumber();
	}
	@Override
	public List<String> getAllPONumber() {
      return pdfDao.getAllPONumber();
	}
	@Override
	public POEntity getPODetails(String poNo) {
		
		return null;
	}
	@Override
	public InvoiceDetailsEntity getInvoiceDetails(String invoiceNo) {
		// TODO Auto-generated method stub
		return null;
	}
	public IpdfDao getPdfDao() {
		return pdfDao;
	}

	public void setPdfDao(IpdfDao pdfDao) {
		this.pdfDao = pdfDao;
	}

}
