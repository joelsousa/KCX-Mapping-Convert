/*
 * Function    : MsgErrInf.java
 * Titel       :
 * Date        : 27.08.2008
 * Author      : Kewill CSF / iwaniuk
 * Description : Contains the Message Error 
 * 			   : with all Fields used in UIDS and  KIDS 
 * Parameters  : 

 * Changes 
 * -----------
 * Author      :
 * Date        :
 * Label       :
 * Description :
 *
 */

package com.kewill.kcx.component.mapping.countries.de.aes.msg;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.MsgErrorPos;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ErrorType;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: MsgErrInf<br>
 * Erstellt		: 27.08.2008<br>
 * Beschreibung	: Contains the Message Error with all Fields used in UIDS and  KIDS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MsgErrInf extends KCXMessage {
	private String uCRNumber;                   // MRN
	private String uCROtherSystem;				// Registriernummer Fremdsystem; 
	private String referenceNumber;				// Bezugsnummer
	private String procedureType = "Export";	// Fix "Export"
	private List<ErrorType> 					errorList = null;	
	private List<MsgErrorPos> 					goodsItemErrorList = null;   //AK20120227
	private String 	declarationTime;			//AK20120227	
	private String  receiverTime;				//AK20120227
	private Party 	customsOffice;				//AK20120227
	
	
	public MsgErrInf() {
		super();
	}
	
	public MsgErrInf(XMLEventReader parser) throws XMLStreamException {
		super(parser);
	}

	public Enum translate(String token) {
		try {
			return EErrMessTags.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
	
	public void stoppElement(Enum tag) {
	}
	
	private enum EErrMessTags {	
		// Kids-TagNames, 			UIDS-TagNames;
		ErrorMessage,				ErrorInformation,
		GoodsDeclaration,			Submit, Export,
		UCRNumber,	 				DocumentReferenceNumber,
		DeclarationTime, 										//AK20120227
		ReceiverTime,    										//AK20120227
		UCROtherSystem,				ExternalRegistrationNumber,
									RegistrationNumber,          //EI20100714
		ReferenceNumber,  			//UIDS same KIDS
		OrderNumber,
		//ProcedureType,			//not in UIDS
		ProcedureType,       //EI20120427    for kids2kids
		CustomsOffice,	  										//AK20120227
		Error,						//UIDS same KIDS
		GoodsItem;
	}
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {		
			switch ((EErrMessTags) tag) {
				case Error:
					ErrorType error = new ErrorType(getScanner());	
					error.parse(tag.name());
					addErrorList(error);
				break;
				
				case GoodsItem:									//AK20120227
					MsgErrorPos goodsItemError = new MsgErrorPos(getScanner());	
					goodsItemError.parse(tag.name());
					addGoodsItemErrorList(goodsItemError);
				break;
				
				case CustomsOffice:
					Party customsOffice = new Party(getScanner());	
					customsOffice.parse(tag.name());
				break;	
				
				default:
						return;
			}
		} else {	
			switch ((EErrMessTags) tag) {
				case UCRNumber:
				case DocumentReferenceNumber:
				case RegistrationNumber:
					setUCRNumber(value);
					break;
					
				case UCROtherSystem:
				case ExternalRegistrationNumber:
					setUCROtherSystem(value);
					break;
					
				case ReferenceNumber:
					setReferenceNumber(value);
					break;	
					
				case DeclarationTime:
					setDeclarationTime(value);
					break;
					
				case ReceiverTime:
					setReceiverTime(value);
					break;
			
				case ProcedureType:
					setProcedureType(value);
					break;
					
				default:
			}
		}
	}	

	public String getUCRNumber() {
		return uCRNumber;
	}
	public void setUCRNumber(String argument) {
		this.uCRNumber = argument;
	}
	
	public String getUCROtherSystem() {
		return uCROtherSystem;
	}
	public void setUCROtherSystem(String otherSystem) {
		uCROtherSystem = otherSystem;
	}
	
	public String getReferenceNumber() {
		return referenceNumber;
	}
	
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	
	public String getProcedureType() {
		return procedureType;
	}
	public void setProcedureType(String procedureType) {
		this.procedureType = procedureType;
	}
	
	public List<ErrorType> getErrorList() {
		return errorList;
	}	
	public void setErrorList(List<ErrorType> argument) {
		this.errorList = argument;
	}
	public void addErrorList(ErrorType argument) {
		if (errorList == null) {
			errorList = new Vector<ErrorType>();
		}
		this.errorList.add(argument);
	}
	
	private void addGoodsItemErrorList(MsgErrorPos goodsItemError) {
		if (goodsItemErrorList == null) {
			goodsItemErrorList = new Vector<MsgErrorPos>();
		}
		this.goodsItemErrorList.add(goodsItemError);
	}

	public List<MsgErrorPos> getGoodsItemErrorList() {
		return goodsItemErrorList;
	}

	public void setGoodsItemErrorList(List<MsgErrorPos> goodsItemErrorList) {
		this.goodsItemErrorList = goodsItemErrorList;
	}

	public String getDeclarationTime() {
		return declarationTime;
	}

	public void setDeclarationTime(String declarationTime) {
		this.declarationTime = Utils.checkNull(declarationTime);
	}

	public String getReceiverTime() {
		return receiverTime;
	}

	public void setReceiverTime(String receiverTime) {
		this.receiverTime = Utils.checkNull(receiverTime);
	}

	public Party getCustomsOffice() {
		return customsOffice;
	}

	public void setCustomsOffice(Party customsOffice) {
		this.customsOffice = customsOffice;
	}

	
}
