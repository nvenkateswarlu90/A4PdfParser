package com.a4.pdf.pdfServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.a4.pdf.entity.InvoiceAddressEntity;
import com.a4.pdf.entity.InvoiceDetailsEntity;
import com.a4.pdf.entity.POEntity;
import com.a4.pdf.entity.VendorDetailsEntity;
import com.a4.pdf.ipdfDao.IpdfDao;
import com.a4.pdf.ipdfService.IPdfService;
import com.a4.pdf.model.InVoiceBean;
import com.a4.pdf.model.PurchaseOrder;


public class PdfServiceImpl implements IPdfService {
	//@Autowired
	private IpdfDao pdfDao;

	@Override
	public void saveInvoiceDetails(InVoiceBean invoObj) {
		InvoiceDetailsEntity invoDetailEntity=new InvoiceDetailsEntity();
		InvoiceAddressEntity invoiceAddress=new InvoiceAddressEntity();
		//invoDetailEntity.setSrNo(Integer.parseInt(invoObj.getSrNo()));
		invoDetailEntity.setInvoiceNo(invoObj.getInvoiceNumber());
		invoDetailEntity.setOrdNo(invoObj.getOrderNo());
		invoDetailEntity.setOrderDate(invoObj.getOrderDate());
		invoDetailEntity.setShipDate(invoObj.getShipDate());
		invoDetailEntity.setInvoiceDate(invoObj.getInvoiceDate());
		invoDetailEntity.setSalesPerson(invoObj.getSalesPerson());
		invoDetailEntity.setCustomerDetails(invoObj.getCustomerDetails());
		invoDetailEntity.setLogisticInfo(invoObj.getLogisticInfo());
		invoDetailEntity.setTerms(invoObj.getTerms());
		invoDetailEntity.setShipAccount(invoObj.getShipAccount());
		invoDetailEntity.setCustPo(invoObj.getCustPO());
		invoiceAddress.setAddressId(invoObj.getInvoiceNumber());
		invoiceAddress.setBillAddress(invoObj.getBillAddress());
		invoiceAddress.setInvoiceAddress(invoObj.getInvoiceAddress());
		invoiceAddress.setShippingAddress(invoObj.getShippingAddress());
		invoDetailEntity.setInvoiceAddress(invoiceAddress);
		/*//invoDetailEntity.setSrNo(9879);
		invoDetailEntity.setInvoiceNo("123123");
		invoDetailEntity.setOrdNo("123999");
		invoDetailEntity.setOrderDate("11/07/1990");
		invoDetailEntity.setShipDate("22/2/12");
		invoDetailEntity.setInvoiceDate("23/1/23");
		invoDetailEntity.setSalesPerson("atrs rocks");
		invoDetailEntity.setCustomerDetails("Cherry berry");
		invoDetailEntity.setLogisticInfo("Harry Kane");
		invoDetailEntity.setTerms("IUh asdbiasd");
		invoDetailEntity.setShipAccount("Shipping acoount");
		invoDetailEntity.setCustPo("123PO");
		invoiceAddress.setAddressId("123123");
		//invoiceAddress.setInvoiceNumber("123123");
		invoiceAddress.setBillAddress("Hyderabad");
		//invoiceAddress.setInvoiceAddress("Banglore ,mumbai");
		invoiceAddress.setShippingAddress("pearl harbour");
		invoDetailEntity.setInvoiceAddress(invoiceAddress);*/
		//InvoiceDetailsEntity inVoiceEntity = pdfDao.getInvoiceDetails(invoObj);
		//InVoiceBean invoObj= new InVoiceBean();
		//return invoObj;
		pdfDao.saveInvoiceDetails(invoDetailEntity);
	}

	
	@Override
	public void savePoDetails(List<PurchaseOrder> pruchaseOrdersList) {
		POEntity poEntity = new POEntity();
		VendorDetailsEntity vendorDetailsEntity = null;
		int purchaseOrderNo = 1;
		for (PurchaseOrder purchaseOrder : pruchaseOrdersList) {
			vendorDetailsEntity = new VendorDetailsEntity();
			if (purchaseOrderNo == 1) {
				poEntity.setPoNumber(purchaseOrder.getPoNo());
				if(!StringUtils.isEmpty(purchaseOrder.getJob())){
					poEntity.setJobId(Integer.parseInt(purchaseOrder.getJob()));	
				}
				poEntity.setPoAddress(purchaseOrder.getPoAddress());
				poEntity.setVendorNo(purchaseOrder.getVendorNo());
				// set Vendor Details
				vendorDetailsEntity = getVendorDetals(purchaseOrder);
			} else {
				vendorDetailsEntity = getVendorDetals(purchaseOrder);
			}
			purchaseOrderNo++;
			poEntity.addVendorDetailsEntity(vendorDetailsEntity);
		}
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
	public List<PurchaseOrder> getPODetails(String poNo) {
		POEntity poEntity = pdfDao.getPODetails(poNo);
		List<PurchaseOrder> poList= new ArrayList<PurchaseOrder>();
		List<VendorDetailsEntity> vendorList = poEntity.getListOfVendorDetails();
		PurchaseOrder po = null;
		for (VendorDetailsEntity vendorDetailsEntity : vendorList) {
			po = new PurchaseOrder();
			po.setLogisticInfo(vendorDetailsEntity.getLogisticInfo());
			po.setPoAddress(poEntity.getPoAddress());
			po.setVendorAddress(vendorDetailsEntity.getVendorAddress());
			po.setVendorNo(vendorDetailsEntity.getVendorNo());
			po.setShippingRequest(vendorDetailsEntity.getRequestDate());
			po.setPoNo(poEntity.getPoNumber());
			if(!StringUtils.isEmpty(poEntity.getJobId())){
				po.setJob(Integer.toString(poEntity.getJobId()));	
			}
			po.setShippingAddress(vendorDetailsEntity.getShippingAddress());
			po.setCustomerPo(vendorDetailsEntity.getCustPo());
			po.setOrderDate(vendorDetailsEntity.getOrdDate());
			po.setTerms(vendorDetailsEntity.getTerms());
			po.setSalesPerson(vendorDetailsEntity.getSalesPerson());
			po.setOrderDetails(new StringBuilder(vendorDetailsEntity.getProductDetails()));
			if(!StringUtils.isEmpty(vendorDetailsEntity.getProductCriteriaInstruction())){
				po.setProductcriteria(new StringBuilder(vendorDetailsEntity.getProductCriteriaInstruction()));	
			}
			po.setProductImprintLocation(vendorDetailsEntity.getImprintLocation());
			po.setInstructionsToFactory1(vendorDetailsEntity.getInstructionToFactory1());
			po.setInstructionsToFactory2(vendorDetailsEntity.getInstructionToFactory2());
			poList.add(po);
		}
		return poList;
	}
	@Override
	public InVoiceBean getInvoiceDetails(String invoiceNo) {
		InvoiceDetailsEntity inVoiceEntity = pdfDao.getInvoiceDetails(invoiceNo);
		InVoiceBean invoObj= new InVoiceBean();
		InvoiceAddressEntity invAddress = inVoiceEntity.getInvoiceAddress();
		invoObj.setInvoiceNumber(inVoiceEntity.getInvoiceNo());
		invoObj.setOrderNo(inVoiceEntity.getOrdNo());
		invoObj.setShipDate(inVoiceEntity.getShipDate());
		invoObj.setInvoiceDate(inVoiceEntity.getInvoiceDate());
		invoObj.setSalesPerson(inVoiceEntity.getSalesPerson());
		invoObj.setCustomerDetails(inVoiceEntity.getCustomerDetails());
		invoObj.setLogisticInfo(inVoiceEntity.getLogisticInfo());
		invoObj.setTerms(inVoiceEntity.getTerms());
		invoObj.setShipAccount(inVoiceEntity.getShipAccount());
		invoObj.setCustPO(inVoiceEntity.getCustPo());
		invoObj.setInvoiceAddress(invAddress.getInvoiceAddress());
		invoObj.setBillAddress(invAddress.getBillAddress());
		invoObj.setShippingAddress(invAddress.getShippingAddress());
		return invoObj;
	}

	public IpdfDao getPdfDao() {
		return pdfDao;
	}

	public void setPdfDao(IpdfDao pdfDao) {
		this.pdfDao = pdfDao;
	}


}
