package com.a4.pdf.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "invoice_address")
public class InvoiceAddressEntity {
	
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name="Invoice_Number")
	private String addressId;
	
	@Column(name = "InvoiceAddress")
	private String invoiceAddress;
	
	@Column(name = "BillAddress")
	private String billAddress;
	
	@Column(name = "ShippingAddress")
	private String shippingAddress;
	/*@OneToOne
	@JoinColumn(name="InvoiceNumber",nullable=false)
	private InvoiceDetailsEntity invoiceDetails;
	 */

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

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

	
}
