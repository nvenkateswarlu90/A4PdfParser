package com.a4.pdf.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "InvoiceAddress")
public class InvoiceAddressEntity {
	@Column(name = "InvoiceAddress")
	private String invoiceAddress;
	@Column(name = "BillAddress")
	private String billAddress;
	@Column(name = "ShippingAddress")
	private String shippingAddress;
	@Column(name = "InvoiceNumber")
	private String invoiceNo;

	public String getInvoiceAddress() {
		return invoiceAddress;
	}

	public void setInvoiceAddress(String invoiceAddress) {
		this.invoiceAddress = invoiceAddress;
	}

	public String getBillAddress() {
		return billAddress;
	}

	public void setBillAddress(String billAddress) {
		this.billAddress = billAddress;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

}
