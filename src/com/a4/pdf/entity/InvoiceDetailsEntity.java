package com.a4.pdf.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="InvoiceDetails")
public class InvoiceDetailsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SrNo")
	private Integer srNo;
	@Column(name = "InvoiceNumber")
	private String invoiceNo;
	@Column(name = "OrderNo")
	private String ordNo;
	@Column(name = "OrderDate")
	private String orderDate;
	@Column(name = "ShipDate")
	private String shipDate;
	@Column(name = "InvoiceDate")
	private String invoiceDate;
	@Column(name = "SalesPerson")
	private String salesPerson;
	@Column(name = "CustomerDetails")
	private String customerDetails;
	@Column(name = "LogisticInfo")
	private String logisticInfo;
	@Column(name = "Terms")
	private String terms;
	@Column(name = "ShipAccount")
	private String shipAccount;
	@Column(name = "CustPO")
	private String custPo;
	@OneToOne(mappedBy="")
   private InvoiceAddressEntity invoiceAddress;
	public Integer getSrNo() {
		return srNo;
	}

	public void setSrNo(Integer srNo) {
		this.srNo = srNo;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getOrdNo() {
		return ordNo;
	}

	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getShipDate() {
		return shipDate;
	}

	public void setShipDate(String shipDate) {
		this.shipDate = shipDate;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getSalesPerson() {
		return salesPerson;
	}

	public void setSalesPerson(String salesPerson) {
		this.salesPerson = salesPerson;
	}

	public String getCustomerDetails() {
		return customerDetails;
	}

	public void setCustomerDetails(String customerDetails) {
		this.customerDetails = customerDetails;
	}

	public String getLogisticInfo() {
		return logisticInfo;
	}

	public void setLogisticInfo(String logisticInfo) {
		this.logisticInfo = logisticInfo;
	}

	public String getTerms() {
		return terms;
	}

	public void setTerms(String terms) {
		this.terms = terms;
	}

	public String getShipAccount() {
		return shipAccount;
	}

	public void setShipAccount(String shipAccount) {
		this.shipAccount = shipAccount;
	}

	public String getCustPo() {
		return custPo;
	}

	public void setCustPo(String custPo) {
		this.custPo = custPo;
	}

}
