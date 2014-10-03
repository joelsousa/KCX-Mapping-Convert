package com.kewill.kcx.component.mapping.countries.de.emcs.msg.common;

import java.util.List;
import java.util.Vector;
import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module       : EMCS<br>
 * Created		: 05.05.2010<br>
 * Description  : Contains the Member for save tags from the UIDS message.   
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class EaadDraft extends KCXMessage {

	private String dateOfDispatch;
	private String timeOfDispatch;
	private String originTypeCode;
	private String invoiceNumber;
	private String invoiceDate;	
	private List <String>importSadList;
	private String localReferenceNumber;   //??? is defined in "etn_emcs_types_V10.xsd"
	
	private enum EEaadDraft {
    //KIDS : are simple tags	//UIDS:
								LocalReferenceNumber,
								DateOfDispatch,
								TimeOfDispatch,         
								OriginTypeCode,    
								InvoiceNumber,                   
								InvoiceDate,		
								ImportSad;		
	}	
	
	public EaadDraft() {
  		super();  		
  	}
	 
	public EaadDraft(XmlMsgScanner scanner) {
  		super(scanner);  		
  	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EEaadDraft) tag) {
			default:
				return;
			}
	    } else {
	    	switch ((EEaadDraft) tag) {
	    	case LocalReferenceNumber:
	    		 setLocalReferenceNumber(value);
				 break;
	    	case DateOfDispatch:
	    		 setDateOfDispatch(value);
				 break;
			case TimeOfDispatch:			
				 setTimeOfDispatch(value);
				 break;	
			case OriginTypeCode:
				 setOriginTypeCode(value);
				 break;											
			case InvoiceNumber:
				 setInvoiceNumber(value);
				 break;					 
			case InvoiceDate:
				 setInvoiceDate(value);
				 break;					 				 
			case ImportSad:
				if (importSadList == null) {
					importSadList = new Vector<String>();
				}
				importSadList.add(value);
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
  			return EEaadDraft.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}
	
	public String getDateOfDispatch() {
		return this.dateOfDispatch;
	}
	public void setDateOfDispatch(String argument) {
		this.dateOfDispatch = argument;
	}
	
	public String getTimeOfDispatch() {
		return this.timeOfDispatch;
	}
	public void setTimeOfDispatch(String argument) {
		this.timeOfDispatch = argument;
	}	

	public String getOriginTypeCode() {
		return this.originTypeCode;
	}
	public void setOriginTypeCode(String argument) {
		this.originTypeCode = argument;
	}

	public List<String> getImportSadList() {
		return this.importSadList;
	}
	public void setImportSadList(List<String> argument) {
		this.importSadList = argument;
	}
	public void addImportSadList(String argument) {
		if (this.importSadList == null) {
			importSadList = new Vector<String>();
		}
		this.importSadList.add(argument);
	}	

	public String getInvoiceNumber() {
		return this.invoiceNumber;
	}
	public void setInvoiceNumber(String argument) {
		this.invoiceNumber = argument;
	}		

	public String getInvoiceDate() {
		return this.invoiceDate;
	}
	public void setInvoiceDate(String argument) {
		this.invoiceDate = argument;
	}
		
	public String getLocalReferenceNumber() {
		return this.localReferenceNumber;
	}
	public void setLocalReferenceNumber(String argument) {
		this.localReferenceNumber = argument;
	}	
	
	
	public boolean isEmpty() {
		int listSize = 0;
		if (this.importSadList != null) {
			listSize = this.importSadList.size();
		}
		
		return (Utils.isStringEmpty(this.dateOfDispatch) &&
			Utils.isStringEmpty(this.timeOfDispatch) &&
    		Utils.isStringEmpty(this.originTypeCode) &&
    		Utils.isStringEmpty(this.invoiceDate) &&
    		Utils.isStringEmpty(this.invoiceNumber) &&
    		Utils.isStringEmpty(this.localReferenceNumber) &&
    		(listSize == 0));
			
	}
}
