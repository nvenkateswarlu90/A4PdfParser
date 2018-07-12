package com.a4.pdf.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "VendorDetails")
public class VendorDetailsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SrNo")
	private Integer srNo;
	@Column(name = "VendorNumber")
	private String vendorNo;
	@Column(name = "RequestDate")
	private String requestDate;
	@Column(name = "CustPO")
	private String custPo;
	@Column(name = "OrderDate")
	private String ordDate;
	@Column(name = "Terms")
	private String terms;
	@Column(name = "SalesPerson")
	private String salesPerson;

	@Column(name = "ProductDetails")
	private String productDetails;
	@Column(name = "ProductCriteriaInstruction")
	private String productCriteriaInstruction;
	@Column(name = "ImprintLocation")
	private String imprintLocation;
	@Column(name = "InstructionToFactory1")
	private String instructionToFactory1;
	@Column(name = "InstructionToFactory2")
	private String instructionToFactory2;
	@Column(name = "VendorAddress")
	private String vendorAddress;
	@Column(name="ShippingAddress")
	private String shippingAddress;
	@Column(name="LogisticInfo")
	private String logisticInfo;
	
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

	@ManyToOne
	@JoinColumn(name = "poNumber", nullable = false)
	private POEntity poEntity;
	public Integer getSrNo() {
		return srNo;
	}

	public void setSrNo(Integer srNo) {
		this.srNo = srNo;
	}

	public String getVendorNo() {
		return vendorNo;
	}

	public void setVendorNo(String vendorNo) {
		this.vendorNo = vendorNo;
	}

	public String getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}

	public String getCustPo() {
		return custPo;
	}

	public void setCustPo(String custPo) {
		this.custPo = custPo;
	}

	public String getOrdDate() {
		return ordDate;
	}

	public void setOrdDate(String ordDate) {
		this.ordDate = ordDate;
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

	public String getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(String productDetails) {
		this.productDetails = productDetails;
	}

	public String getProductCriteriaInstruction() {
		return productCriteriaInstruction;
	}

	public void setProductCriteriaInstruction(String productCriteriaInstruction) {
		this.productCriteriaInstruction = productCriteriaInstruction;
	}

	public String getImprintLocation() {
		return imprintLocation;
	}

	public void setImprintLocation(String imprintLocation) {
		this.imprintLocation = imprintLocation;
	}

	public String getInstructionToFactory1() {
		return instructionToFactory1;
	}

	public void setInstructionToFactory1(String instructionToFactory1) {
		this.instructionToFactory1 = instructionToFactory1;
	}

	public String getInstructionToFactory2() {
		return instructionToFactory2;
	}

	public void setInstructionToFactory2(String instructionToFactory2) {
		this.instructionToFactory2 = instructionToFactory2;
	}

	public String getVendorAddress() {
		return vendorAddress;
	}

	public void setVendorAddress(String vendorAddress) {
		this.vendorAddress = vendorAddress;
	}

	public POEntity getPoEntity() {
		return poEntity;
	}

	public void setPoEntity(POEntity poEntity) {
		this.poEntity = poEntity;
	}
}
