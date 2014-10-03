package com.kewill.kcx.component.mapping.countries.mt.ics.msg;

import java.util.HashMap;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.cy.ics.msg.CyprusMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Modul		: ArrivalOperation<br>
* Erstellt		: 21.03.2013<br>
* Beschreibung	: Conversion Malta to KIDS.
* 
* @author krzoska
* @version 1.0.00
*/

public class ArrivalOperation extends CyprusMessage {
	private String		arrivalLrn;
	private String		mrn;
	private String		lrn;
	private String		declarationRegistrationDateAndTime;
	private String		arrivalRegistrationDateAndTime;
	private String		arrivalRejectionDateAndTime;
	private String		arrivalRejectionReason;
	private String		arrivalRejectionReasonLng;

	private HashMap<String, String> enumMap = null;
	private HashMap<String, String> enumBack = null;
	
	public ArrivalOperation() {
		super();
		initEnumMap(); 
		initEnumBack();
	}

	public ArrivalOperation(XMLEventReader parser) throws XMLStreamException {
		initEnumMap(); 
		initEnumBack();
	}
	public ArrivalOperation(XmlMsgScanner scanner)  {
		super(scanner);
		initEnumMap();
		initEnumBack();
	}
	
	private void initEnumMap() {
	    enumMap = new HashMap<String, String>();
	    enumMap.put("arrival-lrn", "ArrivalLrn"); 
	    enumMap.put("mrn", "Mrn");
	    enumMap.put("lrn", "Lrn");
	    enumMap.put("declaration-registration-date-and-time", "DeclarationRegistrationDateAndTime"); 
	    enumMap.put("arrival-registration-date-and-time", "ArrivalRegistrationDateAndTime"); 
	    enumMap.put("arrival-rejection-date-and-time", "ArrivalRejectionDateAndTime"); 
	    enumMap.put("arrival-rejection-reason", "ArrivalRejectionReason");
	    enumMap.put("arrival-rejection-reason-lng", "ArrivalRejectionReasonLng");
	}

	
	private void initEnumBack() {
		enumBack = new HashMap<String, String>();
	    enumBack.put("ArrivalLrn", "arrival-lrn"); 
	    enumBack.put("Mrn", "mrn");
	    enumBack.put("Lrn", "lrn");
	    enumBack.put("DeclarationRegistrationDateAndTime", "declaration-registration-date-and-time"); 
	    enumBack.put("ArrivalRegistrationDateAndTime", "arrival-registration-date-and-time"); 
	    enumBack.put("ArrivalRejectionDateAndTime", "arrival-rejection-date-and-time"); 
	    enumBack.put("ArrivalRejectionReason", "arrival-rejection-reason");
	    enumBack.put("ArrivalRejectionReasonLng", "arrival-rejection-reason-lng");

	}
		

	protected enum EArrivalOperation {
		ArrivalLrn,
		Mrn,
		Lrn,
		DeclarationRegistrationDateAndTime,
		ArrivalRegistrationDateAndTime,
		ArrivalRejectionDateAndTime,	
		ArrivalRejectionReason,
		ArrivalRejectionReasonLng; 
	}
		
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		String text;		
		if (value == null) {
			switch ((EArrivalOperation) tag) {
				default:
					return;
			}
		} else {
			switch ((EArrivalOperation) tag) {
				case ArrivalLrn:	
					setArrivalLrn(value);
					break;
				case Mrn:	
					setMrn(value);
					break;			
				case Lrn:	
					setLrn(value);
					break;			
				case DeclarationRegistrationDateAndTime:
					setDeclarationRegistrationDateAndTime(value);
					break;			
				case ArrivalRegistrationDateAndTime:
					setArrivalRegistrationDateAndTime(value);
					break;			
				case ArrivalRejectionDateAndTime:
					setArrivalRejectionDateAndTime(value);
					break;			
				case ArrivalRejectionReason:	
					setArrivalRejectionReason(value);
					break;			
				case ArrivalRejectionReasonLng:
					setArrivalRejectionReasonLng(value);
					break;			
				default:
					return;
			}
		}	
	}

	@Override
	public void stoppElement(Enum tag) {
		String dummy;
		dummy = "";
	}

	@Override
	public Enum translate(String token) {  
		String text = enumMap.get(token);
		if (text != null) {
			token = text;
		}
		try {
			return EArrivalOperation.valueOf(token);	
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getArrivalLrn() {
		return arrivalLrn;
	}

	public void setArrivalLrn(String arrivalLrn) {
		this.arrivalLrn = Utils.checkNull(arrivalLrn);
	}

	public String getMrn() {
		return mrn;
	}

	public void setMrn(String mrn) {
		this.mrn = Utils.checkNull(mrn);
	}

	public String getLrn() {
		return lrn;
	}

	public void setLrn(String lrn) {
		this.lrn = Utils.checkNull(lrn);
	}

	public String getDeclarationRegistrationDateAndTime() {
		return declarationRegistrationDateAndTime;
	}

	public void setDeclarationRegistrationDateAndTime(
			String declarationRegistrationDateAndTime) {
		this.declarationRegistrationDateAndTime = Utils
				.checkNull(declarationRegistrationDateAndTime);
	}

	public String getArrivalRegistrationDateAndTime() {
		return arrivalRegistrationDateAndTime;
	}

	public void setArrivalRegistrationDateAndTime(
			String arrivalRegistrationDateAndTime) {
		this.arrivalRegistrationDateAndTime = Utils
				.checkNull(arrivalRegistrationDateAndTime);
	}

	public String getArrivalRejectionDateAndTime() {
		return arrivalRejectionDateAndTime;
	}

	public void setArrivalRejectionDateAndTime(String arrivalRejectionDateAndTime) {
		this.arrivalRejectionDateAndTime = Utils
				.checkNull(arrivalRejectionDateAndTime);
	}

	public String getArrivalRejectionReason() {
		return arrivalRejectionReason;
	}

	public void setArrivalRejectionReason(String arrivalRejectionReason) {
		this.arrivalRejectionReason = Utils.checkNull(arrivalRejectionReason);
	}

	
	public String getArrivalRejectionReasonLng() {
		return arrivalRejectionReasonLng;
	}

	public void setArrivalRejectionReasonLng(String arrivalRejectionReasonLng) {
		this.arrivalRejectionReasonLng = Utils.checkNull(arrivalRejectionReasonLng);
	}

}
