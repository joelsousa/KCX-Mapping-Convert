package com.kewill.kcx.component.mapping.countries.de.aes21.msg.common;

import java.util.List;
import java.util.Vector;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Export/AES21<br>
 * Created		: 13.09.2012<br>
 * Description	: Contains Data of PreviousDocument with all Fields used in UIDS and  KIDS. 
 * 
 * @author iwaniuk
 * @version 2.1.00
 */
public class PreviousDocumentV21 extends KCXMessage {
      
	 private String   type;    
	 private String   documentType;  //EI20130807: for KFF/BE
	 private String   marks;		
	 private List <String> referenceList;    //EI20120913 new in V21 for read of Uids-Kopf.PreviousDocument
	 private String   additionalInformation = "";
	 private String   date;  
	 private String   office;
	 
	 private boolean documentMapped = false;
	 
	 //EI20120913: for UIDS is PreviousDocument in Kopf not same definied as in GoodsItem
	 //            Kopf: direktly as CT_PreviousDocument
	 //            GoodsItem: PreviousDocument consists of: Document (CT_PreviousDocument), 
	 // 												    Product(CT_Procuct), 
	 //														Calculation(CT_Taxation),
	 //														Type (String)
	 
	 private enum EPreviousDocumentV21 {
		// Kids-TagNames, 			 UIDS-TagNames
		 	Type,		   			 //same 
		 	Reference,               //same, but List  //EI20130808: Reference for KIDS		 	                         
		 	Marks, MarksAndNumbers,	 //----		
		 	Number, //== Marks, BDP EI20130418
		 	AdditionalInformation,	 Remark, //here a list		 			 							
	        Date,					 DateOfCreation, 
	        Office,	 
	        
	        DocumentType,    //EI20130807: for kids2kids;  FSS/UIDS: is not mapped, no Tag/Fiels	
	        						 Document,	
	        						 Product,		//not used
			 						 Calculation,	//not used	
	 }
	
	 public PreviousDocumentV21() {
			super();
	 }	 
    
	 public PreviousDocumentV21(XmlMsgScanner scanner) {
			super(scanner);
	 }	 
	 
	 public void startElement(Enum tag, String value, Attributes attr) {
			if (value == null) {			
				switch ((EPreviousDocumentV21) tag) {
				case Document:    //ist eine Liste, wir erfassen aber nur das erste Element					
					PreviousDocumentV21 document = new PreviousDocumentV21(getScanner());
					document.parse(tag.name());
					setKidsFromDokument(document);
					documentMapped = true;
					break;					
				case Product:	//there are Complex-UIDS-Tags no classes defined
					break;				
				case Calculation: //there are Complex-UIDS-Tags no classes defined
					break;	
					
				default:
						return;
				}
			} else {				
				switch ((EPreviousDocumentV21) tag) {
					case Type:
						setType(value);
						break;						
					case Marks:
					case MarksAndNumbers:
					case Number:
						setMarks(value);
						break;					
					case AdditionalInformation:
						setAdditionalInformation(value);
						break;
					case Remark:      //ist eine List
						addAdditionalInformation(value);
						break;
					case Date:
					case DateOfCreation:
						setDate(value);
						break;
					case Office:
						setOffice(value);
						break;
					case Reference:   //ist eine List sowohl in Kids als auch in UIDS
						addReferenceList(value);
						break;
					case DocumentType:
						setDocumentType(value);
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
				return EPreviousDocumentV21.valueOf(token);
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
		this.date = date;
	}
	
	public String getOffice() {
		return office;	
	}
	public void setOffice(String office) {
		this.office = office;
	}
	
	/* EI20130308: JIRA: KCXSM-16: for KIDS can be used both: marks OR reference
	public String getMarks() { 
		String marks = this.marks;
		if (Utils.isStringEmpty(marks)) {
			marks = this.getReference();
		}
		return marks;
	}	
	*/
	//EI20130826: die Logik verlegt in kids2fss, weil in kids2kids wird sowohl Marks als auch Reference verwendet
	public String getMarks() { 		
		return marks;
	}		
	public void setMarks(String marks) {
		this.marks = marks;
	}
	////
	public String getReference() {               //EI20120913
		String reference = "";
		if (referenceList != null) {
			reference = referenceList.get(0);
		}
		return reference;
	}
	public void setReference(String value) {     //EI20120913
		referenceList = null;
		referenceList = new Vector<String>();
		referenceList.add("");
		
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
	
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String value) {
		this.documentType = value;
	}
	
	private void setKidsFromDokument(PreviousDocumentV21 document) {
		if (document == null) {
			return;
		}	
		if (documentMapped) {
			return;
		}
		referenceList = document.getReferenceList(); 
		additionalInformation = document.getAdditionalInformation(); //UIDS <= Remark
		date = document.getDate();         //EI20120717:   UIDS <= DateOfCreation		
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(type) && Utils.isStringEmpty(documentType) && 
				Utils.isStringEmpty(marks) && referenceList == null && 
				Utils.isStringEmpty(additionalInformation) &&				
		        Utils.isStringEmpty(date) && Utils.isStringEmpty(office));		       
	}
}
