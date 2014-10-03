package com.kewill.kcx.component.mapping.countries.de.emcs.msg;

import java.util.Vector;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.EmcsTrader;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.ExciseMovementEaad;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.ExportAcceptance;

/**
 * Module		: EMCS<br>
 * Created		: 11.05.2010<br>
 * Description  : Contains Message Structure with fields used in EMCSAcceptedExport (KIDS),
 *              : EMCSNotificationOfAcceptedExport (UIDS)  
 *              : IE829 / C_EXP_NOT.      
 *                 
 * @author iwaniuk
 * @version 1.0.00
 */

public class MsgAcceptedExport extends KCXMessage {

	private String msgName = "EMCSAcceptedExport";
	private String referenceNumber;
	private String customerOrderNumber;
	private String clerk;
	private ExportAcceptance exportAcceptance;	
	private String exportPlaceCustomsOffice;
	private String dateAndTimeOfIssuance;
	private EmcsTrader consignee;	
	private List <ExciseMovementEaad> exciseMovementEaadList;
	
 
	private enum EAcceptedExport {
		//KIDS:									UIDS:		
		ReferenceNumber,        		//not defined
		CustomerOrderNumber,     		//not defined
		Clerk,                  		//not defined  
		ExportAcceptance,				//same
		ExportPlaceCustomsOffice,       //same
		DeliveryPlaceCustomsOffice,
		DateAndTimeOfIssuance,          //same
		Consignee,						ConsigneeTrader,
		ExciseMovementEaad;				//same 
	}
	
	public MsgAcceptedExport() {
		super();
	}

	public MsgAcceptedExport(XMLEventReader parser) throws XMLStreamException {
	super(parser);
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EAcceptedExport) tag) {			
			case Consignee:	
			case ConsigneeTrader:
				consignee = new EmcsTrader(getScanner());
				consignee.parse(tag.name());
				break;
			case ExciseMovementEaad:
				if (exciseMovementEaadList == null) {
					exciseMovementEaadList = new Vector <ExciseMovementEaad>();	
				}
				ExciseMovementEaad tempExc = new ExciseMovementEaad(getScanner());
				tempExc.parse(tag.name());
				exciseMovementEaadList.add(tempExc);
				break;
			case ExportAcceptance:				
				exportAcceptance = new ExportAcceptance(getScanner());
				exportAcceptance.parse(tag.name());
				break;				
			default:
				return;
			}
	    } else {
	    	switch ((EAcceptedExport) tag) {
			case ReferenceNumber:			
				 setReferenceNumber(value);
				 break;	
			case CustomerOrderNumber:
				 setCustomerOrderNumber(value);
				 break;		
			case Clerk:
				 setClerk(value);
				 break;				
			case ExportPlaceCustomsOffice:
			case DeliveryPlaceCustomsOffice:            //EI20110819
				 setExportPlaceCustomsOffice(value);
				 break;					 
			case DateAndTimeOfIssuance:
				 setDateAndTimeOfIssuance(value);
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
  			return EAcceptedExport.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}

	public String getMsgName() {
		return this.msgName;
	}
	public void setMsgName(String argument) {
		this.msgName = argument;
	}
	
	public String getReferenceNumber() {
		return this.referenceNumber;
	}
	public void setReferenceNumber(String argument) {
		this.referenceNumber = argument;
	}
	
	public String getCustomerOrderNumber() {
		return this.customerOrderNumber;
	}
	public void setCustomerOrderNumber(String argument) {
		this.customerOrderNumber = argument;
	}	

	public String getClerk() {
		return this.clerk;
	}
	public void setClerk(String argument) {
		this.clerk = argument;
	}
			
	public ExportAcceptance getExportAcceptance() {
		return this.exportAcceptance;
	}
	public void setExportAcceptance(ExportAcceptance argument) {
		this.exportAcceptance = argument;
	}	
	
	public String getExportPlaceCustomsOffice() {
		return this.exportPlaceCustomsOffice;
	}
	public void setExportPlaceCustomsOffice(String argument) {
		this.exportPlaceCustomsOffice = argument;
	}
	
	public String getDateAndTimeOfIssuance() {
		return this.dateAndTimeOfIssuance;
	}
	public void setDateAndTimeOfIssuance(String argument) {
		this.dateAndTimeOfIssuance = argument;
	}	
		
	public EmcsTrader getConsignee() {
		return this.consignee;
	}
	public void setConsignee(EmcsTrader argument) {
		this.consignee = argument;
	}
	
	public List<ExciseMovementEaad> getExciseMovementEaadList() {
		return this.exciseMovementEaadList;
	}
	public void setExciseMovementEaadList(List<ExciseMovementEaad> argument) {
		this.exciseMovementEaadList = argument;
	}		
}
