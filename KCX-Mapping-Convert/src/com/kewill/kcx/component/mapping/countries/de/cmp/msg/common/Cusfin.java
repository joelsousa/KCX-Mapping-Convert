package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import java.util.ArrayList;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Module		: CMP<br>
* Created		: 02.07.2013<br>
* Description	: CUSFIN (Completion)- NotificationOfCompletion (kids),  SEK (fss)
* 				: Mitteilung der Erledigung.
* 
* @author iwaniuk
* @version 1.0.00
*/

public class Cusfin extends KCXMessage {
	
    private HeaderDetail headerDetail;
    private String typeOfCompletion;        //EI20140217
    private String completionAtlasReference;
    private String completionOtherReference;
    private PartyDetails custodyDetails;
    private ArrayList<ItemDetails> itemDetailsList;
       
    private enum ECusfin {
    	HeaderDetail,
    	TypeOfCompletion,
    	CompletionAtlasReference,
    	CompletionOtherReference,
    	CustodyDetails,    	
    	ItemDetails;
    }

    public Cusfin() {
	      	super();	       
    }
    
    public Cusfin(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((ECusfin) tag) {
    			case HeaderDetail:
    				headerDetail = new HeaderDetail(getScanner());
    				headerDetail.parse(tag.name());
    				break;	
    				
    			case CustodyDetails:
    				custodyDetails = new PartyDetails(getScanner());
    				custodyDetails.parse(tag.name());
    				break;	
    		
    			case ItemDetails:
    				ItemDetails itemDetails = new ItemDetails(getScanner());
    				itemDetails.parse(tag.name());
    				addItemDetailsList(itemDetails);
    				break;
    				
    			default:
    					return;
    			}
    		} else {

    			switch ((ECusfin) tag) {
    			case CompletionAtlasReference:
    				setCompletionAtlasReference(value);
    				break;    				
    			case CompletionOtherReference:
    				setCompletionOtherReference(value);
    				break;
    			case TypeOfCompletion:
    				setTypeOfCompletion(value);
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
    			return ECusfin.valueOf(token);
    		} catch (IllegalArgumentException e) {
    			return null;
    		}
    }
    
	public HeaderDetail getHeaderDetail() {
		return headerDetail;
	}
	public void setHeaderDetail(HeaderDetail detail) {
		this.headerDetail = detail;
	}
		
	public String getCompletionAtlasReference() {
		return completionAtlasReference;
	}
	public void setCompletionAtlasReference(String completionAtlasReference) {
		this.completionAtlasReference = completionAtlasReference;
	}

	public String getCompletionOtherReference() {
		return completionOtherReference;
	}
	public void setCompletionOtherReference(String completionOtherReference) {
		this.completionOtherReference = completionOtherReference;
	}

	public PartyDetails getCustodyDetails() {
		return custodyDetails;
	}
	public void setCustodyDetails(PartyDetails detail) {
		this.custodyDetails = detail;
	}
		
	public ArrayList<ItemDetails> getItemDetailsList() {
		return itemDetailsList;
	}
	public void setItemDetailsList(ArrayList<ItemDetails> list) {
		this.itemDetailsList = list;
	}
	public void addItemDetailsList(ItemDetails item) {
		if (itemDetailsList == null) {
			itemDetailsList = new ArrayList<ItemDetails>();
		}
		this.itemDetailsList.add(item);
	}
	
	
	public String getTypeOfCompletion() {
		return typeOfCompletion;
	}
	public void setTypeOfCompletion(String typeOfCompletion) {
		this.typeOfCompletion = typeOfCompletion;
	}

	public boolean isEmpty() {
		return headerDetail == null && custodyDetails == null && 
		Utils.isStringEmpty(completionAtlasReference) && Utils.isStringEmpty(completionOtherReference) &&
		itemDetailsList == null;
	}
	 
}
