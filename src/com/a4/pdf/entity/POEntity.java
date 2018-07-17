package com.a4.pdf.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "purchase_order_details")
public class POEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4204920809804667921L;
	/*@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SrNo")
	private Integer srNo;*/
	@Id
	@Column(name = "PONumber")
	private String poNumber;
	@Column(name = "VendorNumber")
	private String vendorNo;
	@Column(name = "JobId")
	private Integer jobId;
	@Column(name = "POAddress")
	private String poAddress;
	
	@OneToMany(mappedBy = "purchaseOrder",cascade = CascadeType.ALL)
	//@Cascade(CascadeType.ALL)
	private List<VendorDetailsEntity> listOfVendorDetails = new ArrayList<>();

	
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
	public void addVendorDetailsEntity(VendorDetailsEntity entity){
		if(entity!= null){
			getListOfVendorDetails().add(entity);
			entity.setPurchaseOrder(this);
		}
	}
}
