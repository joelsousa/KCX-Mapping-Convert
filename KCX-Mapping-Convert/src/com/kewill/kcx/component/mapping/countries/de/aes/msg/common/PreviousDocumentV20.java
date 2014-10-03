package com.kewill.kcx.component.mapping.countries.de.aes.msg.common;

import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;
import com.kewill.kcx.component.mapping.countries.common.SpecialMention;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;

import org.xml.sax.Attributes;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: Export/AES21<br>
 * Created		: 13.09.2012<br>
 * Description	: Contains Data of PreviousDocument with all Fields used in UIDS and  KIDS. 
 * 
 * @author iwaniuk
 * @version 2.1.00
 */
public class PreviousDocumentV20 extends KCXMessage {
      
	 private String   type;       
	 private String   marks;	
	 private String   additionalInformation = "";
	 
	 //private Document document;	  //UIDS - integrated document Kids+Uids 	 
	 //private String   product;      //UIDS
	 //private String   calculation;  //UIDS
	 
	 private String   date;  
	 private String   office;
	 private List <String>   referenceList;    //EI20120913 new in V21 for read of Uids-Kopf.PreviousDocument

	 //EI20120913: for UIDS is PreviousDocument in Kopf not same definied as in GoodsItem
	 //            Kopf: direktly as CT_PreviousDocument
	 //            GoodsItem: Document(CT_PreviousDocument), Product(CT_..), Calculation(CT_..), Type
	 private enum EPreviousDocumentV20 {
		// Kids-TagNames, 			 UIDS-TagNames
		 	Type,		   			 //same 
		 	          Reference,     //also Reference(list), but should be mapped on Kids:Marks 	
		 	                         //Kids:Reference is mapped, because UIDS uses it 
		 	Marks, MarksAndNumbers,	 //Reference			 	
		 	AdditionalInformation,	 Remark, //but a list		 			 							
	        Date,					 DateOfCreation, 
	        Office,	 
	        Number,    //BDP EI20130418
	 }
	 
	 private boolean debug   = false;
	
	 public PreviousDocumentV20() {
			super();
	 }	 
     public PreviousDocumentV20(boolean debug) {
        this.debug = debug;
     } 
 
	 public PreviousDocumentV20(XmlMsgScanner scanner) {
			super(scanner);
	 }	 
	 
	 public void startElement(Enum tag, String value, Attributes attr) {
			if (value == null) {			
				switch ((EPreviousDocumentV20) tag) {
												
				default:
						return;
				}
			} else {				
				switch ((EPreviousDocumentV20) tag) {
					case Type:
						setType(value);
						break;						
					case Marks:
					case Number:
						setMarks(value);
						break;					
					case AdditionalInformation:
						setAdditionalInformation(value);
						break;
					case Remark:      //EI20120913
						addAdditionalInformation(value);
						break;
					case Date:
					case DateOfCreation:
						setDate(value);
						break;
					case Office:
						setOffice(value);
						break;
					case Reference:
						addReferenceList(value);
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
				return EPreviousDocumentV20.valueOf(token);
			} catch (IllegalArgumentException e) {
				return null;
			}
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	//EI20130308: JIRA: KCXSM-16: for KIDS can be used both: marks OR reference
	//public String getMarks() {
	//	return marks;
	//}
	public String getMarks() { 
		String marks = this.marks;
		if (Utils.isStringEmpty(marks)) {
			marks = this.getReference();
		}
		return marks;
	}
	//
	public String getMarks(String fromFormat) { 
		if (Utils.isStringEmpty(fromFormat)) {
			fromFormat = "";
		}
		String marks = this.marks;
		if (fromFormat.equalsIgnoreCase("UIDS")) {
			marks = this.getReference();
		}
		return marks;
	}
	public void setMarks(String marks) {
		this.marks = marks;
	}

	public String getAdditionalInformation() {
		return additionalInformation;
	}
	public void setAdditionalInformation(String argument) {
		this.additionalInformation = argument;
	}
	public void addAdditionalInformation(String argument) {   //EI20120913
		if (Utils.isStringEmpty(argument)) {
			return;
		}		
		if (Utils.isStringEmpty(additionalInformation)) {
			additionalInformation = argument;
		} else {
			additionalInformation = additionalInformation + " " + argument;
		}
	}
  	
	public String getDate() {
		return date;	
	}
	public void setDate(String date) {
		this.date = Utils.checkNull(date);
	}
	
	public String getOffice() {
		return office;	
	}
	public void setOffice(String office) {
		this.office = Utils.checkNull(office);
	}
	
	public String getReference() {               //EI20120913
		String reference = "";
		if (referenceList != null) {
			reference = referenceList.get(0);
		}
		return reference;
	}
	public void setReference(String value) {     //EI20120913
		if (referenceList == null) {
			referenceList = new Vector<String>();
			referenceList.add("");
		}
		referenceList.set(0, value);
	}
	public List<String> getReferenceList() {               //EI20120913
		return referenceList;
	}
	public void setReferenceList(List<String> list) {     //EI20120913
		this.referenceList = list;
	}
	public void addReferenceList(String value) {     //EI20120913
		if (referenceList == null) {
			referenceList = new Vector<String>();
		}
		referenceList.add(value);
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(type) && Utils.isStringEmpty(marks) && 
				Utils.isStringEmpty(additionalInformation) &&				
		        Utils.isStringEmpty(date) && Utils.isStringEmpty(office));		       
	}
}
