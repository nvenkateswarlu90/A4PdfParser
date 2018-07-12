package com.a4.pdf.pdfServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.a4.pdf.entity.InvoiceDetailsEntity;
import com.a4.pdf.entity.POEntity;
import com.a4.pdf.entity.VendorDetailsEntity;
import com.a4.pdf.ipdfDao.IpdfDao;
import com.a4.pdf.ipdfService.IPdfService;
import com.a4.pdf.model.PurchaseOrder;

public class PdfServiceImpl implements IPdfService {
	@Autowired
	private IpdfDao pdfDao;

	@Override
	public void saveInvoiceDetails() {
		pdfDao.saveInvoiceDetails(new InvoiceDetailsEntity());
	}

	@Override
	public void savePoDetails(List<PurchaseOrder> pruchaseOrdersList) {
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
}
