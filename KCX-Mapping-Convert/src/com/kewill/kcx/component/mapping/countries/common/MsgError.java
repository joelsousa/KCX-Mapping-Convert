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

package com.kewill.kcx.component.mapping.countries.common;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ErrorType;

/**
 * Module		: KCX<br>
 * Created		: 16.12.2011<br>
 * Description	: Contains the Message Error for KIDS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MsgError extends KCXMessage {
	private String uCRNumber;                   // MRN
	private String uCROtherSystem;				// Registriernummer Fremdsystem; 
	private String referenceNumber;				// Bezugsnummer
	private String procedureType;	            // Fix "Export"
	private List<ErrorType> errorList;		
	private List<MsgErrorPos> goodsItemList;			
    
	public MsgError() {
		super();
	}
	
	public MsgError(XMLEventReader parser) throws XMLStreamException {
		super(parser);
	}

	public Enum translate(String token) {
		try {
			return EMsgError.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
	
	public void stoppElement(Enum tag) {
	}
	
	private enum EMsgError {	
		// Kids-TagNames, 			UIDS-TagNames;
		ErrorMessage,				ErrorInformation,
		GoodsDeclaration,			Submit, Export,
		UCRNumber,	 				DocumentReferenceNumber,
		UCROtherSystem,				ExternalRegistrationNumber,
									RegistrationNumber,          //EI20100714
		ReferenceNumber,  			//UIDS same KIDS
		//ProcedureType,			//not in UIDS
		Error,                      //UIDS same KIDS 	
		GoodsItem;
	}
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {		
			switch ((EMsgError) tag) {
				case Error:
					ErrorType error = new ErrorType(getScanner());	
					error.parse(tag.name());
					addErrorList(error);
				case GoodsItem:
					MsgErrorPos item = new MsgErrorPos(getScanner());	
					item.parse(tag.name());					
					addGoodsItemList(item);	
				default:
						return;
			}
		} else {	
			switch ((EMsgError) tag) {
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
	public void addErrorList(ErrorType list) {
		if (errorList == null) {
			errorList = new ArrayList<ErrorType>();		
		}
		this.errorList.add(list);
	}
	
	public List<MsgErrorPos> getGoodsItemList() {
		return goodsItemList;
	}	
	public void setGoodsItemList(List<MsgErrorPos> list) {
		this.goodsItemList = list;
	}
	public void addGoodsItemList(MsgErrorPos argument) {
		if (goodsItemList == null) {
			goodsItemList = new ArrayList<MsgErrorPos>();	
		}
		this.goodsItemList.add(argument);
	}
}
