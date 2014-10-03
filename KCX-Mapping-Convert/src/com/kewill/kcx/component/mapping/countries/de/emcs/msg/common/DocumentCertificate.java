package com.kewill.kcx.component.mapping.countries.de.emcs.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Module        : EMCS<br>
* Created		: 05.05.2010<br>
* Description   : Contains the Member for save the tags of the KIDS and UIDS messages.       
* 
* @author iwaniuk
* @version 1.0.00
*/

public class DocumentCertificate extends KCXMessage {

	private Text documentDescription;
	private Text documentReference;
	
	
	private enum EDocumentCertificate {
		//KIDS                      //UIDS
		DocumentDescription,		//same
		DocumentReference,			ReferenceOfDocument;
	}
		 
	public DocumentCertificate(XmlMsgScanner scanner) {
		super(scanner);
	}

	public void startElement(Enum tag, String value, Attributes attr) {
 		if (value == null) {
  			switch ((EDocumentCertificate) tag) {	
				default:
  					return;
  			}
  		} else {
  			switch ((EDocumentCertificate) tag) {   
  				case DocumentDescription:
  					//documentDescription = new Text(value, attr.getValue("language"));  	
  					documentDescription = new Text(value, attr);  //EI20110926
					break;

  				case DocumentReference:					  
  				case ReferenceOfDocument:
  					//documentReference = new Text(value, attr.getValue("language"));
  					documentReference = new Text(value, attr);  //EI20110926
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
  			return EDocumentCertificate.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}	

	public Text getDocumentDescription() {
		return documentDescription;
	}
	public void setDocumentDescription(Text documentDescription) {
		this.documentDescription = documentDescription;
	}

	public Text getDocumentReference() {
		return documentReference;	
	}
	public void setDocumentReference(Text documentReference) {
		this.documentReference = documentReference;
	}

	public boolean isEmpty() {
		
		String text1 = "";
		String text2 = "";
		if (this.documentDescription != null) {
			text1 = this.documentDescription.getText();
		}
		if (this.documentReference != null) {
			text2 = this.documentReference.getText();
		}
		
		return (Utils.isStringEmpty(text1)	&&  Utils.isStringEmpty(text2));		
	}
	
}
