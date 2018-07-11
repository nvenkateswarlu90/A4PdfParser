package com.a4.pdf.model;

public class PurchaseOrder {

	private String 		fileName;
	private String 		receivedDate;
	private String 		processedDate;
	private String 		poAddress;
	private String 		vendorAddress;
	private String 		vendorNo;
	private String 		shippingRequest;
	private String 		poNo;
	private String 		job;
	private String 		shippingAddress;
	private String 		logisticInfo;
	private String 		customerPo;
	private String 		orderDate;
	private String 		terms;
	private String 		salesPerson;
	private StringBuilder orderDetails;
	private StringBuilder productcriteria;
	private String		  productImprintLocation;
	private String 		  instructionsToFactory1;
	private String 		  termsAndConditions;
	private String 		  instructionsToFactory2;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(String receivedDate) {
		this.receivedDate = receivedDate;
	}
	public String getProcessedDate() {
		return processedDate;
	}
	public void setProcessedDate(String processedDate) {
		this.processedDate = processedDate;
	}
	public String getPoAddress() {
		return poAddress;
	}
	public void setPoAddress(String poAddress) {
		this.poAddress = poAddress;
	}
	public String getVendorAddress() {
		return vendorAddress;
	}
	public void setVendorAddress(String vendorAddress) {
		this.vendorAddress = vendorAddress;
	}
	public String getVendorNo() {
		return vendorNo;
	}
	public void setVendorNo(String vendorNo) {
		this.vendorNo = vendorNo;
	}
	public String getShippingRequest() {
		return shippingRequest;
	}
	public void setShippingRequest(String shippingRequest) {
		this.shippingRequest = shippingRequest;
	}
	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public String getLogisticInfo() {
		return logisticInfo;
	}
	public void setLogisticInfo(String logisticInfo) {
		this.logisticInfo = logisticInfo;
	}
	public String getCustomerPo() {
		return customerPo;
	}
	public void setCustomerPo(String customerPo) {
		this.customerPo = customerPo;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getTerms() {
		return terms;
	}
	public void setTerms(String terms) {
		this.terms = terms;
	}
	public String getSalesPerson() {
		return salesPerson;
	}
	public void setSalesPerson(String salesPerson) {
		this.salesPerson = salesPerson;
	}
	public StringBuilder getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(StringBuilder orderDetails) {
		this.orderDetails = orderDetails;
	}
	public StringBuilder getProductcriteria() {
		return productcriteria;
	}
	public void setProductcriteria(StringBuilder productcriteria) {
		this.productcriteria = productcriteria;
	}
	public String getProductImprintLocation() {
		return productImprintLocation;
	}
	public void setProductImprintLocation(String productImprintLocation) {
		this.productImprintLocation = productImprintLocation;
	}
	
	public String getInstructionsToFactory1() {
		return instructionsToFactory1;
	}
	public void setInstructionsToFactory1(String instructionsToFactory1) {
		this.instructionsToFactory1 = instructionsToFactory1;
	}
	public String getInstructionsToFactory2() {
		return instructionsToFactory2;
	}
	public void setInstructionsToFactory2(String instructionsToFactory2) {
		this.instructionsToFactory2 = instructionsToFactory2;
	}
	public String getTermsAndConditions() {
		return termsAndConditions;
	}
	public void setTermsAndConditions(String termsAndConditions) {
		this.termsAndConditions = termsAndConditions;
	}

	
}