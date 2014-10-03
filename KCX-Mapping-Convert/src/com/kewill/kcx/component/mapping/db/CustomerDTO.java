package com.kewill.kcx.component.mapping.db;

/**
 * Modul		: CustomerDTO<br>
 * Erstellt		: 28.10.2008<br>
 * Beschreibung	: Data Transfer Object for the "customer" database table. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class CustomerDTO {
	private String   kcxId = null;
	private String   customerName = null;
	private String   corporateIdentity = null;
	private String   protocol = null;
	private String   targetDomain = null;
	private String   branchName = null;
	private String   country = null;
	private String   localId = null;
	private String   localIdType = null;
	
	
	public String getCorporateId() {
		return kcxId;
	}
	public void setCorporateId(String corporateId) {
		this.kcxId = corporateId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCorporateIdentity() {
		return corporateIdentity;
	}
	public void setCorporateIdentity(String corporateIdentity) {
		this.corporateIdentity = corporateIdentity;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public String getTargetDomain() {
		return targetDomain;
	}
	public void setTargetDomain(String targetDomain) {
		this.targetDomain = targetDomain;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getLocalId() {
		return localId;
	}
	public void setLocalId(String localId) {
		this.localId = localId;
	}
	public String getLocalIdType() {
		return localIdType;
	}
	public void setLocalIdType(String localIdType) {
		this.localIdType = localIdType;
	}
}
