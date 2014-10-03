package com.kewill.kcx.component.mapping.formats.uids.ncts;

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.SealNumber;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.common.SpecialMention;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSDeclaration;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSDeclarationPos;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.AddressNCTS;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Amount;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Container;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Guarantee;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.OfficeOfTransit;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Packages;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.PartyNCTS;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Reference;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Seal;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Security;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.SensitiveGoods;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.WriteOffData;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.WriteOffInfo;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.WriteOffSumA;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageNCTS;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Description :Body for NCTSDeclaration.
 * 
 * @Date Started :September 1, 2010
 * @author Lassiter
 * @version 4.0.00
 * 
 */
public class BodyNCTSDeclarationUids extends UidsMessageNCTS {

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
			//EI: writeParty("Consignor", message.getConsignorTIN(), message.getConsignor());
			writePartyNCTS("Consignor", message.getConsignorTIN(), message.getConsignor());
			//EI: writeParty("Consignee", message.getConsigneeTIN(), message.getConsignee());
			writePartyNCTS("Consignee", message.getConsigneeTIN(), message.getConsignee());
			//EI: writeParty("Principal", message.getPrincipalTIN(), message.getPrincipal());
			writePartyNCTS("Principal", message.getPrincipalTIN(), message.getPrincipal());
			//TraderRepresentative
			writeElement("CarnetID", message.getCarnetID());
		//EI: writeParty("AuthorisedConsignee", message.getAuthorisedConsigneeTIN(), message.getAuthorisedConsignee());
		writePartyNCTS("AuthorisedConsignee", message.getAuthorisedConsigneeTIN(), message.getAuthorisedConsignee());
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
			writeElement("IdentificationCode", message.getIdentificationCode());		//EI20110519
			writeElement("BondedWarehouseRefNum", message.getBondedWareHouseRefNum());  //EI20110519
			writeElement("BondedWarehouseAuthorisationID", message.getBondedWarehousePermitNumber()); //EI
			//EI: writeParty("Carrier", message.getCarrierTIN(), message.getCarrier());
			writePartyNCTS("Carrier", message.getCarrierTIN(), message.getCarrier());
			writeSecurity(message.getSecurity());			
			//EI: writeParty("ConsignorSecurity", message.getConsignorSecurityTIN(), message.getConsignorSecurity());
			writePartyNCTS("ConsignorSecurity", message.getConsignorSecurityTIN(), message.getConsignorSecurity());
			//EI: writeParty("ConsigneeSecurity", message.getConsigneeSecurityTIN(), message.getConsigneeSecurity());
			writePartyNCTS("ConsigneeSecurity", message.getConsigneeSecurityTIN(), message.getConsigneeSecurity());
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
		writeElement("ArticleNumber", argument.getArticleNumber());  //EI20110524
		writeElement("GrossMass", argument.getGrossMass());
		writeElement("NetMass", argument.getNetMass());
		writeElement("CountryOfDispatch", argument.getDispatchCountry());
		writeElement("CountryOfDestination", argument.getDestinationCountry());
        // TransportmethodOfPayment, Commercial.., UNDGNumber
		writeElement("IdentificationCode", argument.getIdentificationCode()); //EI20110607
		
		writePreviousDocumentsList(argument.getPreviousDocumentList());		
		writeProducedDocumentsList(argument.getDocumentList());		
		writeSpecialMentionsList(argument.getSpecialMentionList());		
		writeContainerList(argument.getContainer());		
		writePackagesList(argument.getPackagesList());		
		writeSGICodesList(argument.getSensitiveGoodsList());		
		writeGuaranteeList(argument.getGuaranteeList());
		
		//EI: writeParty("Consignee", argument.getConsigneeTIN(), argument.getConsignee());
		writePartyNCTS("Consignee", argument.getConsigneeTIN(), argument.getConsignee());
		//EI: writeParty("Consignor", argument.getConsignorTIN(), argument.getConsignor());
		writePartyNCTS("Consignor", argument.getConsignorTIN(), argument.getConsignor());
		//EI: writeParty("ConsigneeSecurity", argument.getConsigneeSecurityTIN(), argument.getConsigneeSecurity());
		writePartyNCTS("ConsigneeSecurity", argument.getConsigneeSecurityTIN(), argument.getConsigneeSecurity());
		//EI: writeParty("ConsignorSecurity", argument.getConsignorSecurityTIN(), argument.getConsignorSecurity());
		writePartyNCTS("ConsignorSecurity", argument.getConsignorSecurityTIN(), argument.getConsignorSecurity());
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
	
   /* EI201026
	public void writeParty(String tag, TIN tin, PartyNCTS party)
			throws XMLStreamException {
		if (tin == null && party == null) {
			return;
		}	
		if (tin != null && party != null) {
			if (tin.isEmpty() && party.isEmpty()) {
				return;
			}
		} else if (tin != null && party == null) {
			if (tin.isEmpty()) {
				return;
			}
		} else if (tin == null && party != null) {
			if (party.isEmpty()) {
				return;
			}
		}

		openElement(tag);
			if (tin != null) {
				writeElement("CustomerID", tin.getCustomerIdentifier());
			}			
			if (party != null) {
				writeElement("ETNAddress", party.getETNAddress());
			}			
			if (tin != null) {
				writeElement("TIN", tin.getTin());
				writeElement("IsTINGermanApprovalNumber", tin.getIsTINGermanApprovalNumber());
			}			
			if (party != null) {
				writeElement("VATID", party.getVATNumber());
				writeAddress(party.getAddress());
			}
		closeElement();
	}	
    */
	
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
	
	public void writeWriteOffInfo(WriteOffInfo writeOffInfo) throws XMLStreamException {  //EI20110609
		if (writeOffInfo == null) {
			return;
		}
		openElement("WriteOffInfo");
			for (WriteOffSumA sumA : writeOffInfo.getWriteOffSumAList()) {
				if (sumA != null) {
					openElement("WriteOffSumA");
						writeWriteOffDataList(sumA.getWriteOffDataList());
					closeElement();
				}
			}
		closeElement();
	}
	
	public void writeWriteOffDataList(List <WriteOffData> list) throws XMLStreamException {  //EI20110609
		if (list == null) {
			return;
		}
		for (WriteOffData data : list) {			
			writeWriteOffData(data);			
		}
	}
	
	public void writeWriteOffData(WriteOffData argument) throws XMLStreamException { //EI20110609
    	if (argument == null) {
    	    return;
    	}    
    	if (argument.isEmpty())  {  		
    	    return;
    	}    	
    	openElement("WriteOffData");      	                    	   
    		writeElement("NumberOfPieces", argument.getNumberOfPieces());
    		writeElement("RegNumber", argument.getRegNumber());
    		writeElement("ItemNumber", argument.getItemNumber());
    		writeElement("AWBNumber", argument.getaWBNumber());
    		writeElement("Custodian", argument.getCustodian());
    		writeElement("CustomerID", argument.getCustomerID());
    		writeElement("SpoArt", argument.getSpoArt());
    		writeElement("SpO", argument.getSpo());
    		writeElement("ATLASAlignment", argument.getAtlasAlignment());
	    closeElement();
    }
}
