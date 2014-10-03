/*
 * Function    : ImportOperation.java
 * Titel       :
 * Date        : 10.06.2010
 * Author      : Pete T
 * Description : Contains the ImportOperation Data 
 * 			   : with all Fields used in UIDS and KIDS 
 * Parameters  : 
 */

package com.kewill.kcx.component.mapping.countries.de.ics.msg.common;

import java.util.List;
import java.util.Vector;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.SealNumber;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: ImportOperationRelease<br>
 * Erstellt		: 08.02.2011<br>
 * Beschreibung	: Contains the ImportOperation Data with all Fields used in UIDS and KIDS. IE330
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class ImportOperationRelease extends KCXMessage {

	private String referenceNumber;
	private String mrn;
	private String shipmentNumber;
	private String conveyanceReference;
	private List<SealNumber> sealsIdList;
	private SealNumber seals;
	private List<GoodsItemShort> goodsItemList;

    private boolean debug   = false;

	private enum EImportOperationRelease {
		ImportOperation,
		ReferenceNumber,		LocalReferenceNumber,
		MRN,					//same
		ShipmentNumber,         CommercialReferenceNumber,
		ConveyanceReference, 	ConveyanceNumber,
		SealsID,		SealsIdentity, SealsIdentityLng,
		GoodsItem;				//same	
    }
	
	public ImportOperationRelease() {
		super();
	}

    public ImportOperationRelease(XmlMsgScanner scanner) {
  		super(scanner);
  	}
    
  	public boolean isDebug() {
  		return debug;
  	}
  	public void setDebug(boolean debug) {
  		this.debug = debug;
  	}
 
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EImportOperationRelease) tag) {
  			//case SealsID:    				
  				
  			case GoodsItem:
  				GoodsItemShort item = new GoodsItemShort(getScanner());					
				item.parse(tag.name());	
				addGoodsItemList(item);
  				break;
  				
  			default:
  					return;
  			}
  		} else {

  			switch ((EImportOperationRelease) tag) {
  				case ReferenceNumber:
  				case LocalReferenceNumber:				 
  					setReferenceNumber(value);
  					break;	
  				case MRN:
					 setMRN(value);
					 break;	
					 
  				case ShipmentNumber:
  				case CommercialReferenceNumber:  				
  					setShipmentNumber(value);
  					break;				 
  				case ConveyanceReference: 			
  				case ConveyanceNumber:
  					setConveyanceReference(value);
  					 break;					 
  				case SealsIdentity:
  					seals = new SealNumber();
  					seals.setNumber(value);	
  					addSealsIdList(seals);
  					break;
  				
  				case SealsIdentityLng: 
  					if (seals == null) {
  						seals = new SealNumber();
  					}
  					seals.setLanguage(value);	  					  					
  					break;
				default: break;
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}

  	public Enum translate(String token) {
  		try {
  			return EImportOperationRelease.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

  	public String getReferenceNumber() {
		return this.referenceNumber;	
	}
	public void setReferenceNumber(String argument) {
		this.referenceNumber = argument;
	}

	public String getShipmentNumber() {
		return this.shipmentNumber;	
	}
	
	public void setShipmentNumber(String argument) {
		this.shipmentNumber = argument;
	}
	
	public String getMRN() {
		return mrn;
	}
	public void setMRN(String mrn) {
		this.mrn = mrn;
	}
	public String getConveyanceReference() {
		return this.conveyanceReference;
	}
	public void setConveyanceReference(String argument) {
		this.conveyanceReference = argument;
		
	}
	
	public List<SealNumber> getSealsIdList() {
		return this.sealsIdList;	
	}
	
	public void setSealsIdList(List<SealNumber> list) {
		this.sealsIdList = list;
	}
	
	public void addSealsIdList(SealNumber sael) {
		if (this.sealsIdList == null) {
			this.sealsIdList = new Vector<SealNumber>();
		}
		this.sealsIdList.add(sael);
	}
	
	public List<GoodsItemShort> getGoodsItemList() {
		return this.goodsItemList;	
	}
	
	public void setGoodsItemList(List<GoodsItemShort> list) {
		this.goodsItemList = list;
	}
	
	public void addGoodsItemList(GoodsItemShort item) {
		if (this.goodsItemList == null) {
			this.goodsItemList = new Vector<GoodsItemShort>();
		}
		this.goodsItemList.add(item);
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.referenceNumber) && 
				Utils.isStringEmpty(this.mrn) &&
				Utils.isStringEmpty(this.shipmentNumber) &&
				Utils.isStringEmpty(this.conveyanceReference) &&  			
				goodsItemList == null);   //seatsID	
	}	
	
}
