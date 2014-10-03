package com.kewill.kcx.component.mapping.countries.ie;

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.Seal;
import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.DocumentV20;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Route;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.WareHouse;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpDatPos;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module : KidsToIreland<br>
 * Created : 23.05.2014<br>
 * Description : Ireland Messages common Members and methods.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public abstract class IrelandMessage {
	
	public IrelandHeader irelandHeader = null;
	public KidsHeader kidsHeader = null;

    protected XMLStreamWriter writer = null;

    protected String encoding = "UTF-8";
    
    private CommonFieldsDTO commonFieldsDTO = null;
    
    public void setCommonFieldsDTO(CommonFieldsDTO commonFieldsDTO) {
    	this.commonFieldsDTO = commonFieldsDTO;
    }
    public CommonFieldsDTO getCommonFieldsDTO() {
    	return this.commonFieldsDTO;
    }
    /**
     * Schreibt das version und encoding tag einer xml
     * mit doppelten hochkomma anstelle von einfachen hochkomma.
     * @param encoding
     * @param version
     * @throws XMLStreamException
     */
    protected void writeStartDocument(String encoding, String version) throws XMLStreamException {
		writer.writeDTD("<?xml version=\"" + version + "\" encoding=\"" + encoding + "\"?>");
	}
    public void mapKidsToIrelandHeader() {
    	String msgnam = "";
    	
    	 /*
//    	irelandHeader.setTo(kidsHeader.getReceiver());
        irelandHeader.setTo(commonFieldsDTO.getUidsId());
//        irelandHeader.setTo(customerDTO.getLocalId().trim());
        irelandHeader.setFrom(kidsHeader.getTransmitter());
        irelandHeader.setMsgid(kidsHeader.getMessageID());
        irelandHeader.setSentat(kidsHeader.getSentAt());
        irelandHeader.setInreplyto(kidsHeader.getInReplyTo());
        irelandHeader.setUidsNamespaceVersion(commonFieldsDTO.getUidsVersion());
        irelandHeader.setTimeZone(kidsHeader.getTimeZone());  //EI20110519

        // 20100609MS
        irelandHeader.setProcedure(kidsHeader.getProcedure());
        
        irelandHeader.setMethod(kidsHeader.getMethod());
        
        msgnam = kidsHeader.getMessageName();
       
        // Version in Abh. von KIDS-Release/Procedure richtige Ireland Release setzen:
       
		if (kidsHeader.getRelease() == null) {
			Utils.log("(UidsMessage mapKidsToIrelandHeader) KIDSRelease ist null");
			irelandHeader.setMessageVersion("1.0");
		} else if (kidsHeader.getRelease().equals("2.0.00")) {
			Utils.log("(UidsMessage mapKidsToIrelandHeader) KIDSRelease ist 2.0.00");
			irelandHeader.setMessageVersion("2.0");
		} else if (kidsHeader.getRelease().equals("2.1.00")) {   //EI20120803
			Utils.log("(UidsMessage mapKidsToIrelandHeader) KIDSRelease ist 2.1.00");
			irelandHeader.setMessageVersion("2.1");
		} else if (kidsHeader.getRelease().equals("4.1.00")) {    //EI20130211
			Utils.log("(UidsMessage mapKidsToIrelandHeader) KIDSRelease ist 4.1.00");
			irelandHeader.setMessageVersion("4.1");
		} else if (kidsHeader.getRelease().equals("4.0.00")) {
			Utils.log("(UidsMessage mapKidsToIrelandHeader) KIDSRelease ist 4.0.00");
			irelandHeader.setMessageVersion("4.0");
		} else  { irelandHeader.setMessageVersion("1.0");
		}
	
		irelandHeader.setMessageType(Utils.getUidsMsgFromKidsMsg(msgnam, irelandHeader.getProcedure()));
  		
		Utils.log("(UidsMessage mapKidsToIrelandHeader) commonFieldsDTO.getDirection() = " + commonFieldsDTO.getDirection());
//		if (kidsHeader.getDirection().equalsIgnoreCase("TO_CUSTOMER")) {
	    if (commonFieldsDTO.getDirection() == EDirections.CountryToCustomer) {
			irelandHeader.setAct("inform");
		} else {
			irelandHeader.setAct("request");
		}
		
		// only this message has - independant from customs procedure - act "status"
		if (msgnam.equalsIgnoreCase("InternalStatusInformation")) {
			irelandHeader.setAct("status");
		}
		
		// with the message localAppResult the status is dependant from the content
		// and has to be set in MapFailureKU used with all customs procedures
		
        // 20100609MS
        irelandHeader.setDirection(kidsHeader.getDirection());
    	 		*/
    }
    
    protected void writeElement(String tag, String value) throws XMLStreamException {    	
        if (value != null) {
        	if (!value.trim().equals("")) {
        		writer.writeStartElement(tag);
        		writer.writeCharacters(value);
        		writer.writeEndElement();
        	}
        }
    }
  //iwa 20080924
    protected void writeElements(String tag, String value) throws XMLStreamException {
    	Utils.log("(UidsMessage writeElement) tag = " + tag);
        Utils.log("(UidsMessage writeElement) value = " + value);
        if (value == null || value.trim().equals("")) {
            writer.writeEmptyElement(tag);
        } else {
        	writeElement(tag, value);
        }
	} 
    
    //EI20100506: 
    protected void writeElementWithAttribute(String tag, String value, String attrName, 
                                                        String attrValue) throws XMLStreamException {
        if (value != null) {
        	if (!value.trim().equals("")) {
        		writer.writeStartElement(tag);
        		if (attrValue != null) {   //EI20110916
        			writer.writeAttribute(attrName, attrValue);        			
        		}         		
        		writer.writeCharacters(value);        		
        		writer.writeEndElement();
        	}
        }
    }    
    protected void setAttribute(String tag, String value) throws XMLStreamException {
    		writer.writeAttribute(tag, value);      	
    }
    
    protected void openElement(String tag) throws XMLStreamException {
//        Utils.log("(UidsMessage openElement) tag = " + tag);
        writer.writeStartElement(tag);
    }

    protected void closeElement() throws XMLStreamException {
//        Utils.log("(UidsMessage closeElement)");
        writer.writeEndElement();
    }

	public IrelandHeader getIrelandHeader() {
		return irelandHeader;
	}

	public void setIrelandHeader(IrelandHeader irelandHeader) {
		this.irelandHeader = irelandHeader;
	}

	public KidsHeader getKidsHeader() {
		return kidsHeader;
	}

	public void setKidsHeader(KidsHeader kidsHeader) {
		this.kidsHeader = kidsHeader;
	}
// from Kids to Uids beginn:
	public void writeTypeOfDeclaration(String areaCode, String typeOfPermit) throws XMLStreamException {
		if (Utils.isStringEmpty(areaCode) && Utils.isStringEmpty(typeOfPermit)) {
		    return;
		}		
		openElement("v1:TypeOfDeclaration"); //alle, reihenfolge egal
			////DeclarationCode
			writeElement("v1:RegionCode", areaCode);
			writeElement("v1:ProcedureCode", typeOfPermit);
			////TransitCode
		closeElement(); 
	}

	public void writeWarehouse(WareHouse argument) throws XMLStreamException {	
		if (argument == null) {
		    return;
		}       
		openElement("v1:Warehouse");
			writeElement("v1:IdentificationIdentifier", argument.getWarehouseIdentification());
		closeElement();
	}
	
	public void writeTradeTerm(String code, String place, String locationCode) throws XMLStreamException {
		if (Utils.isStringEmpty(code) && Utils.isStringEmpty(place) && Utils.isStringEmpty(locationCode)) {
			return;
		}
		openElement("v1:TradeTerm");			
			writeElement("v1:ConditionCode", code);
			writeElement("v1:LocationNameText", place);
			writeElement("v1:CountryRelationshipCode", locationCode);
		closeElement();
	}
	
	public void writeParty(String partyName, Party argument, String typeOfRepresentation) throws XMLStreamException {	
		if (argument == null) {
		    return;
		}
		String tin = "";
		String addressName = "";
		if (argument.getPartyTIN() != null) {
		    tin = argument.getPartyTIN().getTin();
		}
		if (argument.getAddress() != null) {
			addressName = argument.getAddress().getName();
		}
       
		openElement(partyName);			
			writeElement("v1:StatusCode", typeOfRepresentation);
			writeElement("v1:IdentityIdentifier", tin);
			writeElement("v1:NameText", addressName);
			writeAddress(argument.getAddress());
		closeElement();
	}
	
	public void writeParty(String partyName, String tin, Address address, String typeOfRepresentation) throws XMLStreamException {	
		if (tin == null && address == null) {
		    return;
		}		
		String addressName = "";		
		if (address != null) {
			addressName = address.getName();
		}
       
		openElement(partyName);			
			writeElement("v1:StatusCode", typeOfRepresentation);
			writeElement("v1:IdentityIdentifier", tin);
			writeElement("v1:NameText", addressName);
			writeAddress(address);
		closeElement();
	}
	
	public void writeAddress(Address argument) throws XMLStreamException {	
		if (argument == null) {
		    return;
		}
        String street = argument.getStreet();
        if (!Utils.isStringEmpty(street) && 
        	!Utils.isStringEmpty(argument.getHouseNumber())) {
        	street = street + " " + argument.getHouseNumber();
        }
		openElement("v1:Address");			
			writeElement("v1:LineText", street);
			writeElement("v1:CityName", argument.getCity());
			writeElement("v1:PostcodeIdentifier", argument.getPostalCode());
			writeElement("v1:CountryCode", argument.getCountry());			
		closeElement();
	}
	
	public void writeConsignment(TransportMeans trDeparture, TransportMeans trBorder, 
			TransportMeans trInland, TransportMeans tr, Route route) throws XMLStreamException {	
		if (trDeparture == null && trBorder == null && trInland == null && tr == null && route == null) {
		    return;
		}
        String dummy = "";
		openElement("v1:Consignment");
			if (trDeparture != null) {
				openElement("v1:DepartureTransportMeans");
					writeElement("v1:IdentificationText", trDeparture.getTransportationNumber());
				closeElement();
			}
			if (trBorder != null) {
				openElement("v1:BorderTransportMeans");
					writeElement("v1:IdentityText", trDeparture.getTransportationNumber());
					writeElement("v1:RegistrationNationalityIdentifier", trDeparture.getTransportationCountry());
					writeElement("v1:TransportType", trDeparture.getTransportationType());
					writeElement("v1:ModeAndTypeCode", trDeparture.getTransportMode());
					if (route != null && route.getCountryList() != null) {
						for (String country : route.getCountryList()) {
							openElement("v1:Itinerary");
								writeElement("v1:CountryCode", country);
							closeElement();
						}
					}
				closeElement();
			}
			if (trInland != null) {
				writeElement("v1:IEInlandModeOfTransportCode", trInland.getTransportMode());
			}
			if (tr != null) {
				openElement("v1:GoodsLocation");
					writeElement("v1:LocationIdentifier", tr.getPlaceOfLoading());
				closeElement();
			}
		closeElement();
	}
	
	public void writeNumberOfSeals(Seal argument) throws XMLStreamException {	
		if (argument == null) {
		    return;
		}
		if (argument.getSealNumberList() == null) {
		    return;
		}
		List <String> list = argument.getSealNumberList();
		if (list.size() == 0) {
			return;
		}
		openElement("v1:NumberOfSeals");
			for (String nr : list) {
				writeElement("v1:QuantityQuantity", nr);
			}
		closeElement();
	}
		
	public void writeCustomsGoodsItem(MsgExpDatPos item, TransportMeans tr) throws XMLStreamException {	
		if (item == null) {
		    return;
		}
		openElement("v1:CustomsGoodsItem");	
		
		if (item.getPackagesList() != null && item.getPackagesList().get(0) != null) {
			openElement("v1:GoodsPackaging");
				writeElement("v1:MarkNumberText", item.getPackagesList().get(0).getMarks());
				writeElement("v1:QuantityQuantity", item.getPackagesList().get(0).getQuantity());				
				writeElement("v1:TypeCode", item.getPackagesList().get(0).getType());
			closeElement();
		}
		writeElement("v1:IEUniqueLPRef", item.getShipmentNumber());
		writeElement("v1:SequenceIdentifier", item.getItemNumber());
		if (!Utils.isStringEmpty(item.getDescription()) || item.getCommodityCode() != null || 
				!Utils.isStringEmpty(item.getDangerousGoodsNumber())) {
			openElement("v1:Commodity");
				writeElement("v1:DescriptionText", item.getDescription());
				if (item.getCommodityCode() != null) {
							writeElement("v1:TariffClassificationCode", item.getCommodityCode().getTarifCode());
							writeElement("v1:EUTariffClassificationAdd1Code", item.getCommodityCode().getTarifAddition1());
							writeElement("v1:EUTariffClassificationAdd2Code", item.getCommodityCode().getTarifAddition2());
							writeElement("v1:EUTariffClassificationNatCode", item.getCommodityCode().getEUTarifCode());
							writeElement("v1:DangerousGoodsCodeIdentifier", item.getDangerousGoodsNumber());
				}
			closeElement();
		}
		if (!Utils.isStringEmpty(item.getOriginCountry()) || !Utils.isStringEmpty(item.getOriginFederalState())) {
			openElement("v1:Origin");
				writeElement("v1:OriginCountryIdentifier", item.getOriginCountry());
				writeElement("v1:OriginRegionIdentifier", item.getOriginFederalState());
			closeElement();
		}
		if (!Utils.isStringEmpty(item.getGrossMass()) || !Utils.isStringEmpty(item.getNetMass()) ||
				!Utils.isStringEmpty(item.getThirdQuantity())) {
			openElement("v1:GoodsMeasure");
				writeElement("v1:GrossMassMeasure", item.getGrossMass());
				writeElement("v1:NetNetWeightMeasure", item.getNetMass());
				writeElement("v1:TariffQuantity", item.getThirdQuantity());
			closeElement();
		}
		if (item.getApprovedTreatment() != null) {
			openElement("v1:CustomsProcedure");
				writeElement("v1:CurrentCode", item.getApprovedTreatment().getDeclared());
				writeElement("v1:PreviousCode", item.getApprovedTreatment().getPrevious());
				writeElement("v1:CategoryCode", item.getApprovedTreatment().getNational());
			closeElement();
		}
		if (item.getPreviousDocumentList() != null && item.getPreviousDocumentList().get(0) != null) {
			openElement("v1:PreviousCustomsDocument"); 
				writeElement("v1:ReferenceIdentifier", item.getPreviousDocumentList().get(0).getReference());
			closeElement();
		}
		if (item.getAddInfoStatementList() != null) {
			for (Text addInfo : item.getAddInfoStatementList()) {
				openElement("v1:AdditionalInformation");
					writeElement("v1:StatementCode", addInfo.getCode());
					writeElement("v1:ContentText", addInfo.getText());
				closeElement();
			}
		}
		if (item.getDocumentList() != null) {
			for (DocumentV20 doc : item.getDocumentList()) {
				openElement("v1:AdditionalDocument");
					writeElement("v1:TypeCode", doc.getType());
					writeElement("v1:ReferenceIdentifier", doc.getReference());
				closeElement();
			}
		}
		if (item.getStatistic() != null) {
			writeElement("v1:StatisticalValueAmount", item.getStatistic().getValue());
		}
		//ist in Max-Docu anders gemapped   todo-iwa
		//if (item.getContainer() != null && item.getContainer().getNumberList().get(0) != null) {
		if (tr != null) {
			openElement("v1:TransportEquipment");
			openElement("v1:EquipmentIdentification");
				//writeElement("v1:IdentificationIdentifier", item.getContainer().getNumberList().get(0));			
			writeElement("v1:IdentificationIdentifier", tr.getTransportationNumber());
			closeElement();
			closeElement();
		}
		closeElement();
	}
	public void writeAdditionalInformationList(List<Text> list) throws XMLStreamException {	
		if (list == null) {
		    return;
		}
        for (Text addInfo : list) {
        	openElement("v1:AdditionalInformation");
        		writeAdditionalInformation(addInfo.getCode(), addInfo.getText());        		
			closeElement();
        }
	}
	public void writeAdditionalInformation(String code, String text) throws XMLStreamException {	
		if (Utils.isStringEmpty(code) && Utils.isStringEmpty(text)) {
		    return;
		}        
        openElement("v1:AdditionalInformation");
        	writeElement("v1:StatementCode", code);
        	writeElement("v1:ContentText", text);
		closeElement();        
	}
	
	public void writeAdditionalDocumentList(List<DocumentV20> list) throws XMLStreamException {	
		if (list == null) {
		    return;
		}
        for (DocumentV20 doc : list) {
        	openElement("v1:AdditionalDocument");
        		writeAdditionalDocument(doc.getType(), doc.getReference());             		
			closeElement();
        }
	}
	public void writeAdditionalDocument(String code, String reference) throws XMLStreamException {	
		if (Utils.isStringEmpty(code) && Utils.isStringEmpty(reference)) {
		    return;
		}        
        openElement("v1:AdditionalDocument");
        	writeElement("v1:TypeCode", code);
        	writeElement("v1:ReferenceIdentifier", reference);
		closeElement();        
	}
	public void writeDummy(String argument) throws XMLStreamException {	
		if (argument == null) {
		    return;
		}
        String dummy = "";
		openElement("v1:MeansOfTransport");
			setAttribute("v1:TransportType", "Inland");
			writeElement("v1:Type", dummy);
		closeElement();
	}
	public void writeDummyList(String argument) throws XMLStreamException {	
		if (argument == null) {
		    return;
		}
        String dummy = "";
		openElement("v1:MeansOfTransport");
			setAttribute("v1:TransportType", "Inland");
			writeElement("v1:Type", dummy);
		closeElement();
	}
	
	
}

