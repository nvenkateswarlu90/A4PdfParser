package com.a4.pdf.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "POShippingDetails")
public class POShippingDetailsEntity {
	@Column(name = "LogisticInfo")
	private String logisticInfo;
	@Column(name = "ShippingAddress")
	private String shippingAddress;
	@Column(name = "VendorNumber")
	private String vendorNo;
	@ManyToOne
	@JoinColumn(name = "vendorNo", nullable = false)
	private VendorDetailsEntity vendorDetails;

	public String getLogisticInfo() {
		return logisticInfo;
	}

	public void setLogisticInfo(String logisticInfo) {
		this.logisticInfo = logisticInfo;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getVendorNo() {
		return vendorNo;
	}

	public void setVendorNo(String vendorNo) {
		this.vendorNo = vendorNo;
	}

	public VendorDetailsEntity getVendorDetails() {
		return vendorDetails;
	}

	public void setVendorDetails(VendorDetailsEntity vendorDetails) {
		this.vendorDetails = vendorDetails;
	}
}
