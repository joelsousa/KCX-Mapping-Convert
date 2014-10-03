/*
 * Function    : MsgExpCon.java
 * Titel       :
 * Date        : 04.09.2008
 * Author      : Kewill CSF / kron
 * Description : Contains the Message ExportConfirmation 
 * 			   : with all Fields used in UIDS and  KIDS 
 * Parameters  : 

 * Changes 
 * -----------
 * Author      : Christine Kron
 * Date        : 02.10.2008
 * Label       :
 * Description : added pdflist to handle pdfdata inside the xml message
 *
 */

package com.kewill.kcx.component.mapping.countries.de.aes.msg;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.PDFInformation;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.GoodsItemLong;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Modul		: MsgExpCon<br>
 * Erstellt		: 04.09.2008<br>
 * Beschreibung	: Contains the Message ExportConfirmation with all Fields used in UIDS and  KIDS. 
 * 
 * @author Kron
 * @version 1.0.00
 */
public class MsgExpCon extends KCXMessage {

	// simple tags	
	private String kindOfAnswer;
	private String uCRNumber;
	private String receiveTime;
	private String referenceNumber;	
	//private PDFInformation pdfInformation;
	private List<PDFInformation> pdfInformationList;
	private EFormat receiveTimeFormat;   //EI20110518
	
	//EI20110811 is defined in xsd:
	private String declarationTime;
	private String statusOfCompletion;
	private String statusOfCompletion2;
	private String statusOfCompletion3;
	private String orderNumber;
	private String customsOffice;
	private String loadingTime;
	
	private enum EExpConTags {		
	
		// Kids-TagNames, 					UIDS-TagNames;
		  Confirmation,						// UIDS: same
		  GoodsDeclaration,					Submit,
		  									Export,
		  //EI20110811: areaCode,							
		  //EI20110811: TypeOfPermit,					
		  KindOfAnswer,						FunctionCode,
		  UCRNumber,	                    DocumentReferenceNumber,
		  DeclarationTime,						//EI20110811 is defined in xsd 
		  ReceiveTime,						DateOfReceipt,
		  StatusOfCompletion,                	//EI20110811 is defined in xsd 
		  StatusOfCompletion2,               	//EI20110811 is defined in xsd for V1_1
		  StatusOfCompletion3,               	//EI20110811 is defined in xsd for V1_1
		  ReferenceNumber,					//same
		  OrderNumber,                       	//EI20110811 is defined in xsd 
		  PDFInformation,					PDF,
		  CustomsOffice,					 	//EI20110811 is defined in xsd 									 
		  LoadingTime;	                     	//EI20110811 is defined in xsd 	  
	}
	
	public MsgExpCon() {
		super();		
	}	
	public MsgExpCon(XMLEventReader parser) throws XMLStreamException {
		super(parser);		
	}	

	public void startElement(Enum tag, String value, Attributes attr) {

		if (value == null) {
			//--------------------------
			//  structures
			//--------------------------
			switch ((EExpConTags) tag) {
			case PDFInformation: 
			case PDF: 
				PDFInformation pdfInformation = new PDFInformation(getScanner());	
				pdfInformation.parse(tag.name());
				addPdfInformationList(pdfInformation);
				break;
			default:
					return;
			}
		} else {
			//--------------------------
			// values 
			//
			// use of "fall-through" mechanism
			// to use the same setter for two different tagnames !!
			//
			//--------------------------
			switch ((EExpConTags) tag) {
		
				case KindOfAnswer: 
				case FunctionCode:
					setKindOfAnswer(value);
					break;
				
				case UCRNumber: 
				case DocumentReferenceNumber:
					setUCRNumber(value);
					break;
				
				case ReceiveTime: 
				case DateOfReceipt:
					setReceiveTime(value);
					if (tag == EExpConTags.ReceiveTime) {
   					 	setReceiveTimeFormat(EFormat.KIDS_DateTime);
   				 	} else {
   				 		setReceiveTimeFormat(getUidsDateAndTimeFormat(value)); 
   				 	}	
					break;
				
				case ReferenceNumber:
					setReferenceNumber(value);
					break;
					
				//EI2011 not (yet) in use:	
				case DeclarationTime:					
					break;					
				case StatusOfCompletion:
				case StatusOfCompletion2:
				case StatusOfCompletion3:
					break;					
				case OrderNumber: 
					break;
				case CustomsOffice: 					
					break;
				case LoadingTime:
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
			return EExpConTags.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
	
	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
		
	public String getUCRNumber() {
		return uCRNumber;
	}

	public void setUCRNumber(
			String uCRNumber) {
		this.uCRNumber = uCRNumber;
	}

	public String getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}


	public String getKindOfAnswer() {
		return kindOfAnswer;
	}

	public void setKindOfAnswer(String kindOfAnswer) {
		this.kindOfAnswer = kindOfAnswer;
	}
	
	public List<PDFInformation> getPdfInformationList() {
		return pdfInformationList;
	}
	public void setPdfInformationList(List<PDFInformation> list) {
		this.pdfInformationList = list;
	}
	public void addPdfInformationList(PDFInformation argument) {
		if (this.pdfInformationList == null) {
	   		this.pdfInformationList = new Vector<PDFInformation>();
	   	}
	   	this.pdfInformationList.add(argument);	
	}
	
	public String getDeclarationTime() {
		return declarationTime;
	}

	public void setDeclarationTime(String areaCode) {
		this.declarationTime = areaCode;
	}

	public String getStatusOfCompletiont() {
		return statusOfCompletion;
	}

	public void setStatusOfCompletion(String typeOfPermit) {
		this.statusOfCompletion = typeOfPermit;
	}

	
/////////
	
	public EFormat getUidsDateAndTimeFormat(String value) {  
		EFormat eFormat;
		 
		 if (!value.substring(10, 11).equals("T")) {
			 eFormat = EFormat.ST_DateTime;
		 } else if (value.length() < 20 || !value.substring(19, 20).equals("Z")) {
			 eFormat = EFormat.ST_DateTimeT;
		 } else {
			 eFormat = EFormat.ST_DateTimeTZ;
		 }
		return eFormat;
	}

	public EFormat getReceiveTimeFormat() {
		return receiveTimeFormat;
	}
	public void setReceiveTimeFormat(EFormat argument) {
		this.receiveTimeFormat = argument;
	}
	
}
