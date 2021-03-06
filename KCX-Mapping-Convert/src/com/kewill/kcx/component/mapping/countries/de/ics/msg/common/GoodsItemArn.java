/*
 * Function    : GoodsItem.java
 * Titel       :
 * Date        : 16.06.2010
 * Author      : Pete T
 * Description : Contains the GoodsItem Data 
 * 			   : with all Fields used in UIDS and KIDS 
 * Parameters  : 
 * 
 * Changes
 * -----------
 * Author      : krzoska
 * Date        : 07.12.2010
 * Label       : AK20101207
 * Description : added Document  
 *
 *
 * Changes
 * -----------
 * Author      : 
 * Date        : 
 * Label       : 
 * Description : 
 *
 *
 * 
 */

package com.kewill.kcx.component.mapping.countries.de.ics.msg.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: GoodsItem<br>
 * Created		: 2011.01.27<br>
 * Description	: GoodsItem tag that is used in ICS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 *
 */
public class GoodsItemArn extends KCXMessage {

	private String itemNumber;
	private IcsDocument document;    
    private List<EsumaDataReference> esumaDataReferenceList; 
   
    private boolean debug   = false;

	protected enum EGoodsItemArn {
		//KIDS:							UIDS:
		ItemNumber,						//Same		
		EsumaDataReference,				CustomsDataReference, 	
		Document,						Documents;
    }
	
	public GoodsItemArn() {
		super();
	}

    public GoodsItemArn(XmlMsgScanner scanner) {
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
  			switch ((EGoodsItemArn) tag) {
  			case EsumaDataReference:		
			case CustomsDataReference:
					EsumaDataReference wrkEsumaDataReference = new EsumaDataReference(getScanner());
					wrkEsumaDataReference.parse(tag.name());
					addEsumaDataReferenceList(wrkEsumaDataReference);
				break;			
			case Document:
			case Documents:
					IcsDocument doc = new IcsDocument(getScanner());
					doc.parse(tag.name());
					setDocument(doc);
				break;
  			default:
  					return;
  			}
  		} else {

  			switch ((EGoodsItemArn) tag) {
				case ItemNumber:
  					setItemNumber(value);
  					break;  				
  				default:
  					break;
  			}
  		}
  	}

	public void stoppElement(Enum tag) {
  	}

  	public Enum translate(String token) {
  		try {
  			return EGoodsItemArn.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	public String getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(String argument) {
		itemNumber = argument;
	}
	
	public List<EsumaDataReference> getEsumaDataReferenceList() {		
		return esumaDataReferenceList;
	}

	public void setEsumaDataReferenceList(List<EsumaDataReference> list) {			
		this.esumaDataReferenceList = list;
	}
	public void addEsumaDataReferenceList(EsumaDataReference argument) {			
		if (esumaDataReferenceList == null) {
			esumaDataReferenceList = new ArrayList<EsumaDataReference>();
		}
		esumaDataReferenceList.add(argument);
	}
	
	public IcsDocument getDocument() {
		return document;	
	}
		
	public void setDocument(IcsDocument document) {
		this.document = document;
	}

}
