/*
 * Function    : PreviousDocument(KIDS) == PreviousDocument(UIDS)
 * Titel       :
 * Date        : 09.09.2008
 * Author      : Kewill CSF / krzoska
 * Description : Contains Data of PreviousDocument 
 * 			   : with all Fields used in UIDS and  KIDS 
 * Parameters  : 

 * Changes 
 
 * Author      : EI
 * Date        :
 * Label       : 18.05.2009
 * Description : Kids/Uids  checked - no changes
 */

package com.kewill.kcx.component.mapping.countries.de.aes.msg.common;

import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;

import org.xml.sax.Attributes;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: PreviousDocument<br>
 * Erstellt		: 09.09.2008<br>
 * Beschreibung	: Contains Data of PreviousDocument with all Fields used in UIDS and  KIDS. 
 * 
 * @author krzoska
 * @version 1.0.00
 */
public class PreviousDocument extends KCXMessage {
      
	 private String   type;       
	 private String   marks;
	 private String   additionalInformation;
	 //private Completion bondedWarehouseCompletion;   
	 //private Completion inwardProcessingCompletion;   
	 
	 //private Document document;	    //UIDS - integrated document Kids+Uids 	 
	 //private String   product;      //UIDS
	 //private String   calculation;  //UIDS
	 
	 private String   date;  
	 private String   office;

	 private String   documentType;  //EI20130807: for KFF/BE
	 private String   reference;	 //EI20130807: for KFF/BE
	 
	 private enum EPreviousDocumentTags {
		// Kids-TagNames, 			UIDS-TagNames
		 	Type,		   			//same 
		 	Marks,					//UIDS Document.reference
		 	AdditionalInformation,	//UIDS Document.remarks		 	
		 							Product,		//not used
		 							Calculation,	//not used	
                                    Document,       //is of Type: PreviousDocument                                                                                       
	       Date,					DateOfCreation, //V20: DateOfCreation is in Document TODO-V21 
	       Office,
	       DocumentType,    //EI20130807: for KFF/BE
	       Reference,		//EI20130807: for KFF/BE
	 }
	 
	 private boolean debug   = false;
	
	 public PreviousDocument() {
			super();
	 }	 
     public PreviousDocument(boolean debug) {
        this.debug = debug;
     } 
 
	 public PreviousDocument(XmlMsgScanner scanner) {
			super(scanner);
	 }	 
	 
	 public void startElement(Enum tag, String value, Attributes attr) {
			if (value == null) {			
				switch ((EPreviousDocumentTags) tag) {
				
				case Document:
					Document document = new Document(getScanner());
					document.parse(tag.name());
					setKidsFromDokument(document);
					break;					
				case Product:	//there are Complex-UIDS-Tags no classes defined
					break;				
				case Calculation: //there are Complex-UIDS-Tags no classes defined
					break;										
				default:
						return;
				}
			} else {				
				switch ((EPreviousDocumentTags) tag) {
					case Type:
						setType(value);
						break;						
					case Marks:
						setMarks(value);
						break;					
					case AdditionalInformation:
						setAdditionalInformation(value);
						break;
					case Date:
					case DateOfCreation:
						setDate(value);
						break;
					case Office:
						setOffice(value);
						break;
						
					case DocumentType:
						setDocumentType(value);
						break;
					case Reference:
						setReference(value);
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
				//SH080930  EPreviousDocumentTags, not ApprovedTreatment
				return EPreviousDocumentTags.valueOf(token);
			} catch (IllegalArgumentException e) {
				return null;
			}
	}

	public String getMarks() {
		return marks;
	}
	public void setMarks(String marks) {
		this.marks = marks;
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
   /*		
	public Completion getInwardProcessingCompletion() {
		return inwardProcessingCompletion;
	}
	public void setInwardProcessingCompletion(Completion argument) {
		this.inwardProcessingCompletion = argument;
	}	
	
	public String getProduct() {
		return product;
	}
	public void setProduct(String argument) {
		this.product = argument;
	}
	
	public String getCalculation() {
		return calculation;
	}
	public void setCalculation(String argument) {
		this.calculation = argument;
	}
	*/	
	private void setKidsFromDokument(Document document) {
		if (document == null) {
			return;
		}		
		marks = document.getReference();		
		additionalInformation = document.getAdditionalInformation(); //UIDS <= Remark
		date = document.getIssueDate();         //EI20120717:   UIDS <= DateOfCreation
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
	
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String value) {
		this.documentType = value;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String value) {
		this.reference = value;
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(type) && Utils.isStringEmpty(marks) && 
				Utils.isStringEmpty(additionalInformation) &&				
		        Utils.isStringEmpty(date) && Utils.isStringEmpty(office));		       
	}
}
