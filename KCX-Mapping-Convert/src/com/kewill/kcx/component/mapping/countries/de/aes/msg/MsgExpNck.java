/*
 * Function    : MsgExpNck.java
 * Title       : InternalStatusMessage
 * Date        : 04.09.2008
 * Author      : Kewill CSF / Heise
 * Description : Contains the Message InternalStatusInformation 
 * 			   : with all Fields used in KIDS 

 * Changes 
 * -----------
 * Author      :
 * Date        :
 * Label       :
 * Description:
 */

package com.kewill.kcx.component.mapping.countries.de.aes.msg;


import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Modul		: MsgExpNck<br>
 * Erstellt		: 04.09.2008<br>
 * Beschreibung	: Contains the Message InternalStatusInformation with all Fields used in KIDS.
 * 
 * @author Heise
 * @version 1.0.00
 */
public class MsgExpNck extends KCXMessage {

	private String referenceNumber;
	private String orderNumber;
	private String correctionNumber;
	private String temporaryUCR;
	private String dateNewStatus;
	private String timeNewStatus;
	private String newStatus;
	
	private String newStatusText;
	private XMLEventReader 		parser	= null;
	
	private EFormat dateNewStatusFormat;   //EI20110518
	private EFormat timeNewStatusFormat;   //EI20110518

	private enum EExpNckTags {
		InternalStatusInformation,
		GoodsDeclaration,
		ReferenceNumber,
		OrderNumber,
		CorrectionNumber,
		TemporaryUCR,
		DateNewStatus,
		TimeNewStatus,
		NewStatus;
	}
	
	public MsgExpNck(XMLEventReader parser) throws XMLStreamException {
		super(parser);
	}
	
	public MsgExpNck() {
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {

		if (value == null) {			
			switch ((EExpNckTags) tag) {
			default:
					return;
			}
		} else {			
			switch ((EExpNckTags) tag) {
		
				case ReferenceNumber:
					setReferenceNumber(value);
					break;	
				case OrderNumber:
					setOrderNumber(value);
					break;				
				case CorrectionNumber:
					setCorrectionNumber(value);
					break;				
				case TemporaryUCR:
					setTemporaryUCR(value);
					break;
				case DateNewStatus:
					setDateNewStatus(value);					
   					setDateNewStatusFormat(EFormat.KIDS_Date);   				 	
					break;					
				case TimeNewStatus:
					setTimeNewStatus(value);					
   					setTimeNewStatusFormat(EFormat.KIDS_Time);   				 	
					break;					
				case NewStatus:
            		setNewStatusText(value);
	                if (getNewStatusText().equals("91")) {
	                	setNewStatus("failure");
	                	setNewStatusText("91-InhouseCreationError");
	                } else if (getNewStatusText().equals("92")) {
	                	setNewStatus("failure");
	                	setNewStatusText("92-EdifactCreationError");
	                } else {
	                	setNewStatus("status");
	                }
					break;
					
				default:
					break;
			}
		}
	}
	
	public void stoppElement(Enum tag) {
	}
	
	public EExpNckTags translate(String token) {
		try {
			return EExpNckTags.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
	
	public String getNewStatusText() {
		return newStatusText;
	}

	public void setNewStatusText(String argument) {
		newStatusText = argument;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String argument) {
		referenceNumber = argument;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String argument) {
		orderNumber = argument;
	}
	public String getCorrectionNumber() {
		return correctionNumber;
	}
	public void setCorrectionNumber(String argument) {
		correctionNumber = argument;
	}
	public String getTemporaryUCR() {
		return temporaryUCR;
	}
	public void setTemporaryUCR(String argument) {
		temporaryUCR = argument;
	}
	public String getDateNewStatus() {
		return dateNewStatus;
	}
	public void setDateNewStatus(String argument) {
		dateNewStatus = argument;
	}
	public String getTimeNewStatus() {
		return timeNewStatus;
	}
	public void setTimeNewStatus(String argument) {
		timeNewStatus = argument;
	}
	public String getNewStatus() {
		return newStatus;
	}
	public void setNewStatus(String argument) {
		newStatus = argument;
	}
	
	///
	public EFormat getDateNewStatusFormat() {
		return dateNewStatusFormat;
	}
	public void setDateNewStatusFormat(EFormat argument) {
		this.dateNewStatusFormat = argument;
	}
	
	public EFormat getTimeNewStatusFormat() {
		return timeNewStatusFormat;
	}
	public void setTimeNewStatusFormat(EFormat argument) {
		this.timeNewStatusFormat = argument;
	}
}

/* KIDS-Format:
<xsd:element name="InternalStatusInformation">
		<xsd:element  name="GoodsDeclaration" minOccurs="1" maxOccurs="1">
				<xsd:element name="ReferenceNumber" type="xsd:string" />
				<xsd:element name="OrderNumber" type="xsd:string" minOccurs="0"/>
				<xsd:element name="CorrectionNumber" type="xsd:int"/>
				<xsd:element name="TemporaryUCR" type="xsd:string"/>
				<xsd:element name="DateNewStatus" type="ST_Date"/>
				<xsd:element name="TimeNewStatus" type="ST_Time"/>
				<xsd:element name="NewStatus" type="xsd:int"/>
*
*/
