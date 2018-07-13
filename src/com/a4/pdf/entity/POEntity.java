package com.a4.pdf.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "purchase_order_details")
public class POEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SrNo")
	private Integer srNo;
	@Column(name = "PONumber")
	private String poNumber;
	@Column(name = "VendorNumber")
	private String vendorNo;
	@Column(name = "JobId")
	private Integer jobId;
	@Column(name = "POAddress")
	private String poAddress;
	@OneToMany(mappedBy = "poEntity")
	//@Cascade(Cascade = CascadeType.ALL)
	private List<VendorDetailsEntity> listOfVendorDetails;

	public Integer getSrNo() {
		return srNo;
	}

	public void setSrNo(Integer srNo) {
		this.srNo = srNo;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public String getVendorNo() {
		return vendorNo;
	}

	public void setVendorNo(String vendorNo) {
		this.vendorNo = vendorNo;
	}

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	public String getPoAddress() {
		return poAddress;
	}

	public void setPoAddress(String poAddress) {
		this.poAddress = poAddress;
	}

	public List<VendorDetailsEntity> getListOfVendorDetails() {
		return listOfVendorDetails;
	}

	public void setListOfVendorDetails(List<VendorDetailsEntity> listOfVendorDetails) {
		this.listOfVendorDetails = listOfVendorDetails;
	}
}
