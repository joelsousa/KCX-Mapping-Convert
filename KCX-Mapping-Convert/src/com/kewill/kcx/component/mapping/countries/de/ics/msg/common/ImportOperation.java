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

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: ImportOperation<br>
 * Erstellt		: 10.06.2010<br>
 * Beschreibung	: Contains the ImportOperation Data with all Fields used in UIDS and KIDS. 
 * 
 * @author Pete T
 * @version 1.0.00
 */
public class ImportOperation extends KCXMessage {

	private String mrn;
	private List<String> itemNumberList = new ArrayList<String>();

    private boolean debug   = false;

	private enum EImportOperation {
		ImportOperation,		
		MRN,
		ItemNumber;		
    }
	
	public ImportOperation() {
		super();
	}

    public ImportOperation(XmlMsgScanner scanner) {
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
  			switch ((EImportOperation) tag) {
  			default:
  					return;
  			}
  		} else {

  			switch ((EImportOperation) tag) {
				case MRN:
					 setMRN(value);
					 break;					 
				case ItemNumber:
					 itemNumberList.add(value);
					 break;	
				default: break;
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}

  	public Enum translate(String token) {
  		try {
  			return EImportOperation.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}


	public String getMRN() {
		return mrn;
	}

	public void setMRN(String mrn) {
		this.mrn = mrn;
	}

	public List<String> getItemNumberList() {
		return itemNumberList;
	}

	public void setItemNumberList(List<String> itemNumberList) {
		this.itemNumberList = itemNumberList;
	} 
	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.mrn) && itemNumberList == null );		       
	}	
}
