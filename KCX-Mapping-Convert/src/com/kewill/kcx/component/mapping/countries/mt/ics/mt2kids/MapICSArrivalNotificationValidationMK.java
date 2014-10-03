package com.kewill.kcx.component.mapping.countries.mt.ics.mt2kids;

import java.util.ArrayList;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.Funerrer1;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgArrivalItemRejection;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgArrivalNotificationValidation;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.FunctionalError;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.ImportDetails;
import com.kewill.kcx.component.mapping.countries.mt.ics.msg.MsgCC348A;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.ics.BodyArrivalItemRejectionKids;
import com.kewill.kcx.component.mapping.formats.kids.ics.BodyArrivalNotificationValidationKids;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: MapICSArrivalNotificationValidationMK<br>
 * Created		: 21.08.2013<br>
 * Description	: Malta message CC348A to ICSArrivalNotificationValidation.
 * 
 * @author	krzoska
 * @version	1.0.00
 */


/**
 * Module		: MapICSArrivalNotificationValidationMK<br>
 * Created		: 09.09.2013<br>
 * Modification	: If Malta message CC348A contains Rejection issue Message
 * 					MsgArrivalNotificationValidationKids 
 * 
 * @author	krzoska
 * @version	1.0.00
 */


public class MapICSArrivalNotificationValidationMK extends KidsMessageMT {
	private BodyArrivalNotificationValidationKids 	bodyValidation 	= null;
	private BodyArrivalItemRejectionKids 			bodyRejection	= null;
	private MsgCC348A								msgMalta 		= null;
	
	public MapICSArrivalNotificationValidationMK(XMLEventReader parser, KidsHeader kidsHeader, String encoding
												) throws XMLStreamException {
		msgMalta = new MsgCC348A(parser);
		this.encoding = encoding;
		this.kidsHeader = kidsHeader;	
	}

	public void getMessage(XMLStreamWriter writer) {
		this.writer = writer;
		boolean isValidationMessage = true;
		try {
			//body = new BodyArrivalNotificationValidationKids(writer);
			
			writeStartDocument(encoding, "1.0");
			openElement("soap:Envelope");
			setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
			
			msgMalta.parse(HeaderType.CYPRUS);  
			
			isValidationMessage = isValidationMessage(msgMalta);
			if (isValidationMessage) {
				bodyValidation = new BodyArrivalNotificationValidationKids(writer);
			} else {
				getKidsHeader().setMessageName("ICSArrivalItemRejection");
				bodyRejection = new BodyArrivalItemRejectionKids(writer);
			}
			
			mapKidsHeaderFromMessage();
			kidsHeader.writeHeader();
			
			if (msgMalta.getArrivalOperation() != null) {
	            getCommonFieldsDTO().setReferenceNumber(msgMalta.getArrivalOperation().getArrivalLrn());	            	
	        }
 
			if (isValidationMessage) {
				MsgArrivalNotificationValidation 	kidsMsgValidation = getKidsValidationFromMalta();
				
				bodyValidation.setKidsHeader(kidsHeader);
				bodyValidation.setMessage(kidsMsgValidation);
				bodyValidation.writeBody();
			} else {
				MsgArrivalItemRejection				kidsItemRejection = getKidsRejectionFromMalta();
				bodyRejection.setKidsHeader(kidsHeader);
				bodyRejection.setMessage(kidsItemRejection);
				bodyRejection.writeBody();
			}
			
	        closeElement();  // soap:Envelope
	        writer.writeEndDocument();
	    
			writer.flush();
			writer.close();
			
		} catch (XMLStreamException e) {
	        e.printStackTrace();
	    }
	}
	
	private MsgArrivalItemRejection getKidsRejectionFromMalta() {
		MsgArrivalItemRejection msgItemRejection = new MsgArrivalItemRejection();
		if (msgMalta.getArrivalOperation() != null) {
			String refnrOrg = "";
			refnrOrg = msgMalta.getArrivalOperation().getArrivalLrn();
			if (!Utils.isStringEmpty(refnrOrg)) {				
				msgItemRejection.setReferenceNumber(refnrOrg);
			} else {
				msgItemRejection.setReferenceNumber(msgMalta.getArrivalOperation().getArrivalLrn());
			}

			if (msgMalta.getArrivalOperation() != null) {
				setImportDetailsList(msgItemRejection, msgMalta);
				msgItemRejection.setRejectionDateTime(msgMalta.getArrivalOperation().getArrivalRejectionDateAndTime());
				msgItemRejection.setDeclaredDateOfArrival(msgMalta.getArrivalOperation().getArrivalRegistrationDateAndTime());
				msgItemRejection.setRejectionReasonHeader(msgMalta.getArrivalOperation().getArrivalRejectionReason());
			}
		}
		
		if (msgMalta.getFcnErrorList() != null) {
			ArrayList<FunctionalError> functionalErrorList = new ArrayList<FunctionalError>();
			for (Funerrer1 funerrer : msgMalta.getFcnErrorList()) {
				if (funerrer != null) {
					FunctionalError error = new FunctionalError();					
					error.setErrorType(funerrer.getErrorType());
					error.setErrorPointer(funerrer.getErrorPointer());					
					//EI: error.setErrorReason(funerrer.getErrorReason());
					error.setErrorReason(funerrer.getText());
					error.setOriginalAttributeValue(funerrer.getOriginalAttributeValue());
					
					functionalErrorList.add(error);
				}
			}
			msgItemRejection.setFunctionalErrorList(functionalErrorList);
		}
		
		if (msgMalta.getTracarent() != null  && 
				!Utils.isStringEmpty(msgMalta.getTracarent().getTin())) {
			Party carrier = new Party();
			TIN tin 		= new TIN();
			tin.setTin(msgMalta.getTracarent().getTin());
			carrier.setPartyTIN(tin);
			msgItemRejection.setCarrier(carrier);
		}
		
		return msgItemRejection;
	}
	
	private void setImportDetailsList(MsgArrivalItemRejection msgRejection, MsgCC348A msgMalta) {
		if (msgMalta.getArrivalOperation() != null && !Utils.isStringEmpty(msgMalta.getArrivalOperation().getMrn())) {
			ImportDetails importDetails = new ImportDetails();
			
			importDetails.setMrn(msgMalta.getArrivalOperation().getMrn());
			ArrayList<ImportDetails> idList = new ArrayList<ImportDetails>();
			idList.add(importDetails);
			
			msgRejection.setImportDetailsList(idList);
		}
	}

	private boolean isValidationMessage(MsgCC348A msgMalta) {
		if ((msgMalta.getFcnErrorList() != null && msgMalta.getFcnErrorList().size() > 0) ||
			(msgMalta.getArrivalOperation() != null && !Utils.isStringEmpty(msgMalta.getArrivalOperation().getArrivalRejectionDateAndTime()))) {
			return false;
		}
			// !Utils.isStringEmpty(commonFieldsDTO.getMessageReferenceNumber()
		return true;
	}

	private void mapKidsHeaderFromMessage() {
        setInReplyTo(msgMalta.getCorIdeMES25());
		
		setMapping();
	}
	
	private MsgArrivalNotificationValidation getKidsValidationFromMalta() {
		MsgArrivalNotificationValidation msgKids = new MsgArrivalNotificationValidation();
		
		if (msgMalta.getArrivalOperation() != null) {
			String refnrOrg = "";
			refnrOrg = msgMalta.getArrivalOperation().getArrivalLrn();
			if (!Utils.isStringEmpty(refnrOrg)) {				
				msgKids.setReferenceNumber(refnrOrg);
			} else {
				msgKids.setReferenceNumber(msgMalta.getArrivalOperation().getArrivalLrn());
			}

			if (msgMalta.getArrivalOperation() != null) {
				msgKids.setMrn(msgMalta.getArrivalOperation().getMrn());
				msgKids.setRegistrationDateTime(msgMalta.getArrivalOperation().getDeclarationRegistrationDateAndTime());
				msgKids.setArrivalRegistrationDateTime(msgMalta.getArrivalOperation().getArrivalRegistrationDateAndTime());
				msgKids.setRejectionDateTime(msgMalta.getArrivalOperation().getArrivalRejectionDateAndTime());
				msgKids.setRejectionReason(msgMalta.getArrivalOperation().getArrivalRejectionReason());
			}
		}
		
		if (msgMalta.getFcnErrorList() != null) {
			ArrayList<FunctionalError> functionalErrorList = new ArrayList<FunctionalError>();
			for (Funerrer1 funerrer : msgMalta.getFcnErrorList()) {
				if (funerrer != null) {
					FunctionalError error = new FunctionalError();					
					error.setErrorType(funerrer.getErrorType());
					error.setErrorPointer(funerrer.getErrorPointer());					
					//EI: error.setErrorReason(funerrer.getErrorReason());
					error.setErrorReason(funerrer.getText());
					error.setOriginalAttributeValue(funerrer.getOriginalAttributeValue());
					
					functionalErrorList.add(error);
				}
			}
			msgKids.setFunctionalErrorList(functionalErrorList);
		}
		
		if (msgMalta.getTracarent() != null  && 
				!Utils.isStringEmpty(msgMalta.getTracarent().getTin())) {
			Party carrier = new Party();
			TIN tin 		= new TIN();
			tin.setTin(msgMalta.getTracarent().getTin());
			carrier.setPartyTIN(tin);
			msgKids.setCarrier(carrier);
		}
		
		if (msgMalta.getCusoffentactoff700() != null) {
			msgKids.setCustomsOfficeFirstEntry(
					msgMalta.getCusoffentactoff700().getActualOfficeFirstEntry());
		}

		return msgKids;
	}
}
