package com.kewill.kcx.component.mapping.formats.kids.ncts;


import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.SealNumber;
import com.kewill.kcx.component.mapping.countries.common.SpecialMention;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSDeclaration;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSDeclarationPos;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.AddressNCTS;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Amount;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.CommodityCode;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Container;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Guarantee;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.OfficeOfTransit;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Packages;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.PlaceOfLoading;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Reference;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Seal;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Security;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.SensitiveGoods;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.WriteOffData;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.WriteOffSumA;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageNCTS;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Description :Body for NCTSDeclaration.
 * 
 * @Date Started :September 1, 2010
 * @author Lassiter
 * @version 4.0.00
 * 
 */

public class BodyNCTSDeclarationKids extends KidsMessageNCTS {

	private MsgNCTSDeclaration message;

	public BodyNCTSDeclarationKids(XMLStreamWriter writer) {
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
			openElement("NCTSDeclaration");
			openElement("GoodsDeclaration");
			
			writeElement("ReferenceNumber", message.getReferenceNumber());
			writeElement("ShipmentNumber", message.getShipmentNumber());
			writeElement("AuthorisationNumber", message.getAuthorisationNumber());
			// CK20111103 writeElement("TypeOfDeclaration", message.getTypeOfDeclaration());	
			writeCodeElement("TypeOfDeclaration", message.getTypeOfDeclaration(), "KCX0063");
			writePlaceOfLoading(message.getPlaceOfLoading());      //EI20110524
			writeElement("DestinationCountry", message.getDestinationCountry());
			writeElement("DispatchCountry", message.getDispatchCountry());
			writeElement("CustomsSubPlace", message.getCustomsSubPlace());
			writeTransport("MeansOfTransportInland", message.getMeansOfTransportInland());
			writeTransport("MeansOfTransportBorder", message.getMeansOfTransportBorder());
			writeTransport("MeansOfTransportDeparture", message.getMeansOfTransportDeparture());
			writeTransport("MeansOfTransportCrossingBorder", message.getMeansOfTransportCrossingBorder());
			writeElement("TransportInContainer", message.getTransportInContainer());
			writeElement("NCTSDocumentLanguage", message.getNctsDocumentLanguage());
			writeElement("TotalNumberPositions", message.getTotalNumberPositions());
			writeElement("TotalNumberPackages", message.getTotalNumberPackages());
			writeElement("TotalGrossMass", message.getTotalGrossMass());					
			writeFormattedDateOrTime("DeclarationDate", message.getDeclarationDate(),
						 message.getDeclarationDateFormat(), EFormat.KIDS_Date);						
			writeElement("DeclarationPlace", message.getDeclarationPlace());
			writeElement("SituationCode", message.getSituationCode());			
			// EI20110620: PresentationTime no (more) in xsd, now is ControlResultDateLimit	
			// EI20110929: moved down, so in xsd
			// writeFormattedDateOrTime("ControlResultDateLimit", message.getControlResultDateLimit(), 
			//			message.getControlResultDateLimitFormat(), EFormat.KIDS_Date);						
			writeElement("PaymentType", message.getPaymentType());
			// CK20111103 writeElement("SecurityIndicator", message.getSecurityIndicator());
			writeCodeElement("SecurityIndicator", message.getSecurityIndicator(), "KCX0001");
			writeElement("ConveyanceNumber", message.getConveyanceNumber());
			writeElement("PlaceOfUnLoading", message.getPlaceOfUnloading());
			//writeTINs("ConsignorTIN", message.getConsignorTIN());
			writePartyTIN("ConsignorTIN", message.getConsignorTIN());
			writeParty("Consignor", message.getConsignor());
			writePartyTIN("ConsigneeTIN", message.getConsigneeTIN());
			writeParty("Consignee", message.getConsignee());
			writePartyTIN("AuthorisedConsigneeTIN", message.getAuthorisedConsigneeTIN());
			writeParty("AuthorisedConsignee", message.getAuthorisedConsignee());
			writePartyTIN("PrincipalTIN", message.getPrincipalTIN());
			writeParty("Principal", message.getPrincipal());
			writeElement("CarnetID", message.getCarnetID());
			writeClerk("Clerk", message.getClerk());
			writeElement("OfficeOfDeparture", message.getOfficeOfDeparture());
			
			if (message.getOfficeOfTransitList() != null) {
				for (OfficeOfTransit transit : message.getOfficeOfTransitList()) {
					writeOfficeOfTransit(transit);
				}
			}
			writeElement("OfficeOfDestination", message.getOfficeOfDestination());
			writeElement("ControlResultCode", message.getControlResultCode());
			//EI20110929: moved from top
			writeFormattedDateOrTime("ControlResultDateLimit", message.getControlResultDateLimit(), 
						message.getControlResultDateLimitFormat(), EFormat.KIDS_Date);	
			// CK20111103 writeElement("SimplifiedProcedureIndicator", message.getSimplifiedProcedureIndicator());
			writeCodeElement("SimplifiedProcedureIndicator", message.getSimplifiedProcedureIndicator(), "KCX0001");
			writeElement("RepresentativeName", message.getRepresentativeName());
			writeElement("RepresentativeCapacity", message.getRepresentativeCapacity());
			writeElement("TotalNumberOfSeals", message.getTotalNumberOfSeals());
			writeSeals(message.getSeals());
			
			if (message.getGuaranteeListH() != null) {
				for (Guarantee guarantee : message.getGuaranteeListH()) {
					writeGuarantee(guarantee);
				}
			}				
			if (message.getTransRoute() != null) {
				openElement("TransportationRoute");
					for (String entry : message.getTransRoute().getCountryList()) {
						writeElement("Country", entry);
					}
					// CK20111103 writeElement("SuspensionIndicator", message.getTransRoute().getSuspensionIndicator());
					writeCodeElement("SuspensionIndicator", message.getTransRoute().getSuspensionIndicator(), "KCX0001");
				closeElement();
			}		
			//TODO-EI20110929: die drei sind nicht in xsd
			writeElement("IdentificationCode", message.getIdentificationCode());     		       //EI20110517
			writeElement("BondedWareHouseRefNum", message.getBondedWareHouseRefNum());			   //EI20110517
			writeElement("BondedWarehousePermitNumber", message.getBondedWarehousePermitNumber()); //EI20110517
			//
			writePartyTIN("CarrierTIN", message.getCarrierTIN());
			//TODO-EI20110929: Carrier ist nicht in xsd
			writeParty("Carrier", message.getCarrier());  //EI20110524 
			writeSecurity(message.getSecurity());			
			writePartyTIN("ConsignorSecurityTIN", message.getConsignorSecurityTIN());   //EI20110523
			writeParty("ConsignorSecurity", message.getConsignorSecurity());       //EI20110523
			writePartyTIN("ConsigneeSecurityTIN", message.getConsigneeSecurityTIN());   //EI20110523
			writeParty("ConsigneeSecurity", message.getConsigneeSecurity());       //EI20110523
			
			if (message.getGoodsItemList() != null) {
				for (MsgNCTSDeclarationPos goodsItem : message.getGoodsItemList()) {
					writeGoodsItem(goodsItem);
				}
			}
			
			closeElement();
			closeElement();
			closeElement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void writeGoodsItem(MsgNCTSDeclarationPos argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		openElement("GoodsItem");
			writeElement("ItemNumber", argument.getItemNumber());
			// CK20111103 writeElement("TypeOfDeclaration", argument.getTypeOfDeclaration());
			writeCodeElement("TypeOfDeclaration", argument.getTypeOfDeclaration(), "KCX0063");
			writeCommodityCode(argument.getCommodityCode());
			writeElement("Description", argument.getDescription());
			writeElement("ArticleNumber", argument.getArticleNumber());  //EI20110524
			writeElement("DestinationCountry", argument.getDestinationCountry());
			writeElement("DispatchCountry", argument.getDispatchCountry());
			writeElement("NetMass", argument.getNetMass());
			writeElement("GrossMass", argument.getGrossMass());
			//TODO EI20110929 is in xsd: DangerousGoodsNumber 
			// CK20111103 writeElement("IdentificationCode", argument.getIdentificationCode()); 
			writeCodeElement("IdentificationCode", argument.getIdentificationCode(), "KCX0063");
			//TODO EI20110929 is in xsd: BondedWarehouseRefNum, BondedWarehouseAuthorisationID
			if (argument.getPreviousDocumentList() != null) {
				for (PreviousDocument prevDoc : argument.getPreviousDocumentList()) {
					writePreviousDocument(prevDoc);
				}
			}			
			if (argument.getDocumentList() != null) {
				for (Document document : argument.getDocumentList()) {
					writeDocument(document);
				}
			}			
			if (argument.getSpecialMentionList() != null) {
				for (SpecialMention special : argument.getSpecialMentionList()) {
					writeSpecialMention(special);
				}
			}
			writeContainers(argument.getContainer());			
			if (argument.getPackagesList() != null) {
				for (Packages packages : argument.getPackagesList()) {
		    		writePackage(packages);
		    	}
			}			
			if (argument.getSensitiveGoodsList() != null) {
				for (SensitiveGoods sensitiveGoods : argument.getSensitiveGoodsList()) {
					writeSensitiveGoods(sensitiveGoods);
		    	}
			}			
			if (argument.getGuaranteeList() != null) {
				for (Guarantee guarantee : argument.getGuaranteeList()) {
					writeGuarantee(guarantee);
				}
			}			
			writePartyTIN("ConsignorTIN", argument.getConsignorTIN());
			writeParty("Consignor", argument.getConsignor());
			writePartyTIN("ConsigneeTIN", argument.getConsigneeTIN());
			writeParty("Consignee", argument.getConsignee());
			writePartyTIN("ConsignorSecurityTIN", argument.getConsignorSecurityTIN());
			writeParty("ConsignorSecurity", argument.getConsignorSecurity());
			writePartyTIN("ConsigneeSecurityTIN", argument.getConsigneeSecurityTIN());
			writeParty("ConsigneeSecurity", argument.getConsigneeSecurity());
			
			// added WriteOffSuma CK 2011-02-11
			if (argument.getWriteOffInfo() != null) {
				//EI20110609 is a list: writeWriteOff("ManifestCompletion", argument.getWriteOffInfo().getWriteOffSumA());
				writeWriteOffSumAList(argument.getWriteOffInfo().getWriteOffSumAList());
			}
		closeElement();

	}

	// added writeWriteOff CK 2011-02-11
	public void writeWriteOff(String tag, WriteOffSumA writeOffSumA) 	throws XMLStreamException {

		if (writeOffSumA == null) {
			return;
		}

		if (writeOffSumA.getWriteOffDataList() != null) {
			openElement(tag);  
			for (WriteOffData writeOff : writeOffSumA.getWriteOffDataList()) {
				openElement("CompletionItem");
					writeElement("PositionNumber", writeOff.getItemNumber());
					writeElement("UCR", writeOff.getRegNumber());
					writeElement("NumberOfPieces", writeOff.getNumberOfPieces());
				closeElement();
			}
			closeElement();
		}
	}
	//EI20110609:
	public void writeWriteOffSumAList(List <WriteOffSumA> list) 	throws XMLStreamException {
		if (list == null) {
			return;
		}
		for (WriteOffSumA writeOffSumA : list) {			
			if (writeOffSumA != null) {					
				openElement("ManifestCompletion");  
				 //EI20111207-b
				writeElement("Identification", writeOffSumA.getIdentification()); // in KIDS-xsd nicht definiert 
				writeElement("ReferenceNumber", writeOffSumA.getReference()); 
				//EI20111207-end
				writeWriteOffDataList(writeOffSumA.getWriteOffDataList()); 
				closeElement();
			}
		}
	}
	//EI20110609:
	public void writeWriteOffDataList(List<WriteOffData> list) 	throws XMLStreamException {
		if (list == null) {
			return;
		}
		for (WriteOffData writeOffData : list) {
			if (writeOffData != null && !writeOffData.isEmpty()) {
				openElement("CompletionItem");
					writeElement("PositionNumber", writeOffData.getItemNumber());
					writeElement("UCR", writeOffData.getRegNumber());
					writeElement("NumberOfPieces", writeOffData.getNumberOfPieces());
				closeElement();		
			}
		}
	}
	
	public void writePlaceOfLoading(PlaceOfLoading argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		openElement("PlaceOfLoading");
			writeElement("Code", argument.getCode());
			writeElement("Street", argument.getStreet());
			writeElement("PostalCode", argument.getPostalCode());
			writeElement("City", argument.getCity());
			writeElement("AgreedLocationOfGoods", argument.getAgreedLocationOfGoods());
			writeElement("AgreedLocationOfGoodsCode", argument.getAgreedLocationOfGoodsCode());
			writeElement("AuthorisedLocationOfGoodsCode", argument.getAuthorisedLocationOfGoodsCode());
		closeElement();
	}
	
	public void writeClerk(String tag, ContactPerson argument)
			throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		openElement(tag);
			writeElement("Identity", argument.getIdentity());
		closeElement();
	}

	public void writeOfficeOfTransit(OfficeOfTransit argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}

		openElement("OfficeOfTransit");
			writeElement("Code", argument.getCode());			
			writeFormattedDateOrTime("ArrivalDateAndTime", argument.getArrivalDateAndTime(),					
					argument.getArrivalDateAndTimeFormat(), EFormat.KIDS_DateTime);			
		closeElement();
	}

	public void writeSeals(Seal argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}

		openElement("Seals");
			// CK20111103 writeElement("Kind", argument.getKind());
			writeCodeElement("Kind", argument.getKind(), "KCX0002");
			writeElement("Number", argument.getNumber());
			// CK20111103 writeElement("UseOfTydenSeals", argument.getUseOfTydenseals());
			writeCodeElement("UseOfTydenSeals", argument.getUseOfTydenseals(), "KCX0001");
			// CK20111103 writeElement("UseOfTydenSealStock", argument.getUseOfTydensealStock());
			writeCodeElement("UseOfTydenSealStock", argument.getUseOfTydensealStock(), "KCX0001");
			
			if (argument.getSealNumbersList() != null) {
				for (SealNumber seal : argument.getSealNumbersList()) {
					writeSealNumber(seal);
				}
			}
		closeElement();
	}

	public void writeGuarantee(Guarantee argument)
			throws XMLStreamException {
		if (argument == null) {
			return;
		}
		openElement("Guarantee");
		// CK20111103 writeElement("TypeOfGuarantee", argument.getTypeOfGuarantee());
		writeCodeElement("TypeOfGuarantee", argument.getTypeOfGuarantee(), "KCX0067");
		if (argument.getReferenceList() != null) {
			for (Reference reference : argument.getReferenceList()) {
				writeReference(reference);
			}
		}

		closeElement();
	}

	public void writeReference(Reference argument)
			throws XMLStreamException {
		if (argument == null) {
			return;
		}
		openElement("Reference");
			writeElement("GRN", argument.getGrn());
			writeElement("AccessCode", argument.getAccessCode());
			writeElement("OtherNumber", argument.getOtherNumber());
			writeElement("IssuingOffice", argument.getIssuingOffice());
			writeElement("OwnerTIN", argument.getOwnerTin());
			writeElement("PriceGroup", argument.getPriceGroup());
			writeElement("NotValidForEC", argument.getNotValidForEC());
		
			if (argument.getNotValidForCountryList() != null) {
				for (String countryList : argument.getNotValidForCountryList()) {
					writeElement("NotValidForCountry", countryList);
				}
			}
			writeAmount(argument.getAmount());
		closeElement();
	}

	public void writeAmount(Amount argument)
			throws XMLStreamException {
		if (argument == null) {
			return;
		}
		openElement("Amounts");
			writeElement("Amount", argument.getAmount());
			writeElement("Currency", argument.getCurrency());
			writeElement("LocalAmount", argument.getLocalAmount());
			writeElement("LocalCurrency", argument.getLocalCurrency());
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

	public void writeAddres(String tag, AddressNCTS argument)
			throws XMLStreamException {
		if (argument == null) {
			return;
		}
		openElement(tag);
			writeElement("Name", argument.getName());
			writeElement("Street", argument.getStreet());
			writeElement("HouseNumber", argument.getHouseNumber());
			writeElement("PostalCode", argument.getPostalCode());
			writeElement("City", argument.getCity());
			writeElement("Country", argument.getCountry());
		closeElement();
	}
	
	public void writePreviousDocument(PreviousDocument argument) throws XMLStreamException {   
	    	if (argument == null) {
	    		return;
	    	}
					
	    	openElement("PreviousDocument");   
	    		writeElement("Type", argument.getType());
	    	    writeElement("Marks", argument.getMarks());
	    	    writeElement("AdditionalInformation", argument.getAdditionalInformation()); 	    	      	   
	    	    writeFormattedDateOrTime("Date", argument.getDate(),					
						argument.getDateFormat(), EFormat.KIDS_Date);	    	    
	    	closeElement();						
	}		
	
	public void writeSealNumber(SealNumber argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		openElement("SealNumbers");
			writeElement("Number", argument.getNumber());
		closeElement();
	}
	
	public void writeDocument(Document argument) throws XMLStreamException {   
    	if (argument == null) {
    		return;
    	}
				
    	openElement("Document");   
    		// CK20111103 writeElement("Qualifier", argument.getType());
    		writeCodeElement("Type", argument.getType(), "KCX0065");
    	    writeElement("Reference", argument.getReference());
    	    writeElement("AdditionalInformation", argument.getAdditionalInformation());
    	    writeFormattedDateOrTime("IssueDate", argument.getIssueDate(),					
					argument.getIssueDateFormat(), EFormat.KIDS_Date);	    	    
    	closeElement();						
	}
	
}
