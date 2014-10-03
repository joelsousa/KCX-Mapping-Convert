package com.kewill.kcx.component.mapping.formats.uids.ncts20;

import java.util.ArrayList;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.common.SealNumber;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Guarantee;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.OfficeOfTransit;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Seal;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Security;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.WriteOffData;
import com.kewill.kcx.component.mapping.countries.de.ncts20.msg.MsgNCTSDeclaration;
import com.kewill.kcx.component.mapping.countries.de.ncts20.msg.MsgNCTSDeclarationPos;
import com.kewill.kcx.component.mapping.countries.de.ncts20.msg.common.ManifestCompletion;
import com.kewill.kcx.component.mapping.countries.de.ncts20.msg.common.ManifestCompletionItem;
import com.kewill.kcx.component.mapping.countries.de.ncts20.msg.common.WriteOffInfo;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageNCTS20;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: NCTS<br>
 * Created		: 08.02.2013<br>
 * Description	: Contains message structure for NCTSDeclaration UIDS. 
 * 
 * @author iwaniuk
 * @version 4.1.00
 */

public class BodyNCTSDeclarationUids extends UidsMessageNCTS20 {

	private MsgNCTSDeclaration message;

	public BodyNCTSDeclarationUids(XMLStreamWriter writer) {
		this.writer = writer;
	}

	public MsgNCTSDeclaration getMessage() {
		return message;
	}

	public void setMessage(MsgNCTSDeclaration message) {
		this.message = message;
	}

	public void writeBody() {
		try {
			openElement("soap:Body");
			openElement("Submit");
			openElement("NCTS");
			openElement("NCTSDeclaration");
			
			writeElement("LocalReferenceNumber", message.getReferenceNumber());
			writeElement("CommercialReferenceNumber", message.getShipmentNumber());
			writeElement("AuthorisationID", message.getAuthorisationNumber());
			writeElement("TypeOfDeclaration", message.getTypeOfDeclaration());
			// CK if-null
			if (message.getPlaceOfLoading() != null) {
				writeElement("AgreedLocationOfGoodsCode", message.getPlaceOfLoading().getAgreedLocationOfGoodsCode());
				writeElement("AgreedLocationOfGoods", message.getPlaceOfLoading().getAgreedLocationOfGoods());
			}
			writeElement("CountryOfDestination", message.getDestinationCountry());
			writeElement("CountryOfDispatch", message.getDispatchCountry());
			// CK if-null
			if (message.getPlaceOfLoading() != null) {
		      writeElement("AuthorizedLocationOfGoods", message.getPlaceOfLoading().getAuthorisedLocationOfGoodsCode());
				writeElement("PlaceOfLoading", message.getPlaceOfLoading().getCode());
			}
			writeElement("CustomsSubPlace", message.getCustomsSubPlace());
			writeTransport("InlandTransport", message.getMeansOfTransportInland());
			writeTransport("TransportAtBorder", message.getMeansOfTransportBorder());
			writeTransport("TransportAtDeparture", message.getMeansOfTransportDeparture());
			writeElement("ContainerIndicator", message.getTransportInContainer());
			writeElement("NCTSDocumentLanguage", message.getNctsDocumentLanguage());
			writeElement("TotalNumberOfItems", message.getTotalNumberPositions());
			writeElement("TotalNumberOfPackages", message.getTotalNumberPackages());
			writeElement("TotalGrossMass", message.getTotalGrossMass());			
			writeFormattedDateOrTime("DeclarationDate", message.getDeclarationDate(), 
						message.getDeclarationDateFormat(), EFormat.ST_Date);				
			writeElement("DeclarationPlace", message.getDeclarationPlace());
			writeElement("SpecificCircumstanceIndicator", message.getSituationCode());
			writeElement("TransportMethodOfPayment", message.getPaymentType());
			writeElement("SecurityIndicator", message.getSecurityIndicator());
			writeElement("ConveyanceNumber", message.getConveyanceNumber());
			writeElement("PlaceOfUnloading", message.getPlaceOfUnloading());
			//IssuingDate, AcceptanceDate, BindingItinerary, NoReleaseMotivation			
			writePartyNCTS("Consignor",  message.getConsignor());			
			writePartyNCTS("Consignee", message.getConsignee());			
			writePartyNCTS("Principal", message.getPrincipal());
			//TraderRepresentative
			writeElement("CarnetID", message.getCarnetID());		
			writePartyNCTS("AuthorisedConsignee", message.getAuthorisedConsignee());
			writeClerk(message.getClerk());
			writeElement("OfficeOfDeparture", message.getOfficeOfDeparture());

			if (message.getOfficeOfTransitList() != null) {
				for (OfficeOfTransit transit : message.getOfficeOfTransitList()) {
					writeOfficeOfTransit(transit);
				}
			}
			writeElement("OfficeOfDestination", message.getOfficeOfDestination());
			writeElement("ControlResultCode", message.getControlResultCode());			
			writeFormattedDateOrTime("ControlResultDateLimit", message.getControlResultDateLimit(),
						message.getControlResultDateLimitFormat(), EFormat.ST_Date);						
		    // ControlledBy, ControlDate		
			writeElement("SimplifiedProcedureFlag", message.getSimplifiedProcedureIndicator());
			writeElement("RepresentativeName", message.getRepresentativeName());
			writeElement("RepresentativeCapacity", message.getRepresentativeCapacity());
			writeElement("TotalNumberOfSeals", message.getTotalNumberOfSeals());
			writeSeals(message.getSeals());

			// CK if-null 
			if (message.getSeals() != null) {
				writeElement("SealsType", message.getSeals().getKind());	
				if (message.getSeals().getSealNumbersList() != null) {
					for (SealNumber seals : message.getSeals().getSealNumbersList()) {
						if (seals != null) {
							writeElement("SealsIdentity", seals.getNumber());
						}
					}
				}
			}
			if (message.getGuaranteeListH() != null) {
				for (Guarantee guarantee : message.getGuaranteeListH()) {
					if (guarantee != null) {
						writeGuarantee(guarantee);
					}
				}
			}
			// CK if-null
			if (message.getTransRoute() != null) {
				if (message.getTransRoute().getCountryList() != null) {
					for (String trans : message.getTransRoute().getCountryList()) {						
						writeElement("Itinerary", trans);						
					}
				}			
                writeElement("ItinerarySuspensionFlag", message.getTransRoute().getSuspensionIndicator());
			}
			writeElement("IdentificationCode", message.getIdentificationCode());		
			writeElement("BondedWarehouseRefNum", message.getBondedWareHouseRefNum()); 
			writeElement("BondedWarehouseAuthorisationID", message.getBondedWarehousePermitNumber()); 			
			writePartyNCTS("Carrier",  message.getCarrier());
			writeSecurity(message.getSecurity());						
			writePartyNCTS("ConsignorSecurity", message.getConsignorSecurity());		
			writePartyNCTS("ConsigneeSecurity", message.getConsigneeSecurity());
			// ResultOfControl

			if (message.getGoodsItemList() != null) {
				for (MsgNCTSDeclarationPos goodsItem : message.getGoodsItemList()) {					
					writeGoodsItem(goodsItem);				
				}
			}
			closeElement();
			closeElement();
			closeElement();
			closeElement();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	public void writeGoodsItem(MsgNCTSDeclarationPos argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		openElement("GoodsItem");
		
		writeElement("ItemNumber", argument.getItemNumber());
		writeElement("TypeOfDeclaration", argument.getTypeOfDeclaration());
		if (argument.getCommodityCode() != null) {
			writeElement("CommodityCodeKN8", argument.getCommodityCode().getTarifCode());	
		}		
		writeElement("GoodsDescription", argument.getDescription());
		writeElement("ArticleNumber", argument.getArticleNumber());  
		writeElement("GrossMass", argument.getGrossMass());
		writeElement("NetMass", argument.getNetMass());
		writeElement("CountryOfDispatch", argument.getDispatchCountry());
		writeElement("CountryOfDestination", argument.getDestinationCountry());
        // TransportmethodOfPayment, Commercial.., UNDGNumber
		writeElement("IdentificationCode", argument.getIdentificationCode()); 
		
		writePreviousDocumentsList(argument.getPreviousDocumentList());		
		writeProducedDocumentsList(argument.getDocumentList());		
		writeSpecialMentionsList(argument.getSpecialMentionList());		
		writeContainerList(argument.getContainer());		
		writePackagesList(argument.getPackagesList());		
		writeSGICodesList(argument.getSensitiveGoodsList());		
		writeGuaranteeList(argument.getGuaranteeList());
				
		writePartyNCTS("Consignee", argument.getConsignee());		
		writePartyNCTS("Consignor", argument.getConsignor());		
		writePartyNCTS("ConsigneeSecurity", argument.getConsigneeSecurity());		
		writePartyNCTS("ConsignorSecurity", argument.getConsignorSecurity());
		// ResultOfControl, 		
		writeWriteOffInfo(argument.getWriteOffInfo());  //EI20110609
		
		closeElement();
	}

	
	public void writeOfficeOfTransit(OfficeOfTransit argument)
			throws XMLStreamException {
		if (argument == null) {
			return;
		}

		openElement("OfficeOfTransit");
			writeElement("Code", argument.getCode());						
			writeFormattedDateOrTime("ArrivalDateAndTime", argument.getArrivalDateAndTime(),
					argument.getArrivalDateAndTimeFormat(), EFormat.ST_DateTimeTNoZ);			
		closeElement();
	}

	public void writeSeals(Seal argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}		
		if (argument.isTydenSealsEmpty()) {
			return;
		}		
		openElement("TydenSeals");
			writeElement("TydenSealFlag", argument.getUseOfTydenseals());
			writeElement("TydenSealStockFlag", argument.getUseOfTydensealStock());
			writeElement("Quantity", argument.getNumber());
		closeElement();
	}
	
  
	public void writeSecurity(Security argument)
			throws XMLStreamException {
		if (argument == null) {
			return;
		}
		openElement("Security");
		writeElement("ShipmentNumber", argument.getShipmentNumber());
		writeElement("PlaceOfLoading", argument.getPlaceOfLoading());
		writeElement("PlaceOfUnloading", argument.getPlaceOfUnloading());
		if (argument.getItineraryList() != null) {
			for (String entry : argument.getItineraryList()) {
				writeElement("Itinerary", entry);
			}
		}
		closeElement();
	}	
	
	public void writeWriteOffInfo(WriteOffInfo writeOffInfo) throws XMLStreamException {  
		if (writeOffInfo == null) {
			return;
		}
		openElement("WriteOffInfo");
			for (ManifestCompletion sumA : writeOffInfo.getWriteOffSumAList()) {
				if (sumA != null) {
					openElement("WriteOffSumA");
						writeWriteOffDataList(sumA.getCompletionItemList());
					closeElement();
				}
			}
		closeElement();
	}
	
	public void writeWriteOffDataList(ArrayList<ManifestCompletionItem> list) throws XMLStreamException {  
		if (list == null) {
			return;
		}
		for (ManifestCompletionItem data : list) {			
			writeWriteOffData(data);			
		}
	}
	
	public void writeWriteOffData(ManifestCompletionItem argument) throws XMLStreamException { 
    	if (argument == null) {
    	    return;
    	}    
    	if (argument.isEmpty())  {  		
    	    return;
    	}    	
    	openElement("WriteOffData");      	                    	   
    		writeElement("NumberOfPieces", argument.getNumberOfPieces());
    		writeElement("RegNumber", argument.getUCR());
    		writeElement("ItemNumber", argument.getPositionNumber());
    		writeElement("AWBNumber", argument.getAWB());
    		if (argument.getCustodianTIN() != null) {
    			writeElement("CustodianTIN", argument.getCustodianTIN().getTIN());
    			writeElement("CustodianBO", argument.getCustodianTIN().getBO());
    			writeElement("CustomerID", argument.getCustodianTIN().getCustomerIdentifier());
    		}
    		writeElement("SpoArt", argument.getSpoArt());
    		writeElement("SpO", argument.getSpo());
    		writeElement("ATLASAlignment", argument.getAtlasAlignment());
	    closeElement();
    }
}
