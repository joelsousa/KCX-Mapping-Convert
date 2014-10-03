package com.kewill.kcx.component.mapping.countries.de.Import.msg;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Completion;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Manifest;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;

/**
 * Module		: Import<br>.
 * Created		: 12.09.2011<br>
 * Description	: KIDS ImportDeclarationConfirmation
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MsgImportDeclarationConfirmation extends KCXMessage { 
	
	private String			 msgName = "ImportDeclarationConfirmation";
	private String			 referenceNumber;
	private String			 temporaryMRN;
	private String			 declarantTIN;		
	private String			 goodsLocation;
	private String			 meansOfTransportArrival;
	
	private ContactPerson	 representative;
	private Document         previousDocument;               //only type and number
	
	private List<Manifest>   manifestCompletionList;
	private List<Completion> bondedWarehouseCompletionList;	
	private List<Completion> inwardProcessingCompletionList;
		
	public MsgImportDeclarationConfirmation() {
		super();
	}
	public MsgImportDeclarationConfirmation(XMLEventReader parser)throws XMLStreamException {
		super(parser);
	}
	
	private enum EImportTaxAssessment {
		//KIDS:							UIDS:
		ReferenceNumber,        		LocalReferenceNumber,
		TemporaryMRN,					
		DeclarantTIN,			
		Representative,	
		GoodsLocation,	
		MeansOfTransportArrival,	
		PreviousDocument,
		ManifestCompletion,
		BondedWarehouseCompletion,
		InwardProcessingCompletion;
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EImportTaxAssessment) tag) {
			case Representative:
				representative = new ContactPerson(getScanner());
				representative.parse(tag.name());
				break;						
			case PreviousDocument:
				previousDocument = new Document(getScanner());
				previousDocument.parse(tag.name());						
				break;			
			case ManifestCompletion:
				Manifest manifest = new Manifest(getScanner());
				manifest.parse(tag.name());
				addManifestCompletionList(manifest);
				break;			
			case BondedWarehouseCompletion:
				Completion bwCompletion = new Completion(getScanner());				
				bwCompletion.parse(tag.name());
				addBondedWarehouseCompletionList(bwCompletion);
				break;	
			case InwardProcessingCompletion:
				Completion ipCompletion = new Completion(getScanner());				
				ipCompletion.parse(tag.name());
				addInwardProcessingCompletionList(ipCompletion);
				break;	
			default:
				return;
			}
		} else {
			switch ((EImportTaxAssessment) tag) {
			case ReferenceNumber:
			case LocalReferenceNumber:
				setReferenceNumber(value);
				break;
			case TemporaryMRN:		
				setTemporaryMRN(value);
				break;
			case DeclarantTIN:			
				setDeclarantTIN(value);
				break;
			case GoodsLocation:
				setGoodsLocation(value);
				break;
			case MeansOfTransportArrival:	
				setMeansOfTransportArrival(value);
				break;
			
			default:
				return;	
			}
		}
	}

	public void stoppElement(Enum tag) {
		
	}

	public Enum translate(String token) {
		try {
			return EImportTaxAssessment.valueOf(token);
		} catch (IllegalArgumentException e) {
		return null;
		}
	}
	
	public String getMsgName() {
		return msgName;
	}
	public void setMsgName(String msgName) {
		this.msgName = msgName;
	}
	
	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	
	public String getTemporaryMRN() {
		return temporaryMRN;
	}
	public void setTemporaryMRN(String value) {
		this.temporaryMRN = value;
	}
	
	public String getDeclarantTIN() {
		return declarantTIN;
	}	
	public void setDeclarantTIN(String value) {
		this.declarantTIN = value;
	}
	
	public String getGoodsLocation() {
		return goodsLocation;
	}	
	public void setGoodsLocation(String value) {
		this.goodsLocation = value;
	}
	
	public String getMeansOfTransportArrival() {
		return meansOfTransportArrival;
	}
	public void setMeansOfTransportArrival(String value) {
		this.meansOfTransportArrival = value;
	}
	
	public ContactPerson getRepresentative() {
		return representative;
	}
	public void setRepresentative(ContactPerson contact) {
		this.representative = contact;
	}
		
	public Document getPreviousDocument() {
		return previousDocument;
	}
	public void setPreviousDocument(Document document) {
		this.previousDocument = document;
	}
		
	public List<Completion> getBondedWarehouseCompletionList() {
		return bondedWarehouseCompletionList;
	}
	public void setBondedWarehouseCompletionList(List<Completion> list) {
		this.bondedWarehouseCompletionList = list;
	}           
	public void addBondedWarehouseCompletionList(Completion argument) {
	   	if (this.bondedWarehouseCompletionList == null) {
	   		this.bondedWarehouseCompletionList = new Vector<Completion>();
	   	}
	   	this.bondedWarehouseCompletionList.add(argument);
	}		

	public List<Completion> getInwardProcessingCompletionList() {
		return inwardProcessingCompletionList;
	}
	public void setInwardProcessingCompletionList(List<Completion> list) {
		this.inwardProcessingCompletionList = list;
	}
	public void addInwardProcessingCompletionList(Completion argument) {
	   	if (this.inwardProcessingCompletionList == null) {
	   		this.inwardProcessingCompletionList = new Vector<Completion>();
	   	}
	   	this.inwardProcessingCompletionList.add(argument);
	}
	
	public List<Manifest> getManifestCompletionList() {
		return manifestCompletionList;
	}
	public void setManifestCompletionList(List<Manifest> list) {
		this.manifestCompletionList = list;
	}
	public void addManifestCompletionList(Manifest argument) {
	   	if (this.manifestCompletionList == null) {
	   		this.manifestCompletionList = new Vector<Manifest>();
	   	}
	   	this.manifestCompletionList.add(argument);
	}		
}
