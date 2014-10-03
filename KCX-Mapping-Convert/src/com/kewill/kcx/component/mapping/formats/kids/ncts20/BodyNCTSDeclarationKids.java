package com.kewill.kcx.component.mapping.formats.kids.ncts20;

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.SealNumber;
import com.kewill.kcx.component.mapping.countries.common.SpecialMention;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.AddressNCTS;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Amount;
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
import com.kewill.kcx.component.mapping.countries.de.ncts20.msg.MsgNCTSDeclaration;
import com.kewill.kcx.component.mapping.countries.de.ncts20.msg.MsgNCTSDeclarationPos;
import com.kewill.kcx.component.mapping.countries.de.ncts20.msg.common.CompletionItem;
import com.kewill.kcx.component.mapping.countries.de.ncts20.msg.common.ManifestCompletion;
import com.kewill.kcx.component.mapping.countries.de.ncts20.msg.common.ManifestCompletionItem;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageNCTS20;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module	   : NCTS OUT 20<br>
 * Created	   : 14.11.2012<br>
 * Description : Body for NCTSDeclaration.
 * 
 *               TODO KIDS not defined
 * 
 * @author iwaniuk
 * @version 4.1.00
 */

public class BodyNCTSDeclarationKids extends KidsMessageNCTS20 {

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
			writeCodeElement("TypeOfDeclaration", message.getTypeOfDeclaration(), "KCX0063");
			writePlaceOfLoading(message.getPlaceOfLoading());      
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
			writeElement("PaymentType", message.getPaymentType());			
			writeCodeElement("SecurityIndicator", message.getSecurityIndicator(), "KCX0001");
			writeElement("ConveyanceNumber", message.getConveyanceNumber());
			writeElement("PlaceOfUnLoading", message.getPlaceOfUnloading());			
			writeNCTSParty("Consignor", message.getConsignor());			
			writeNCTSParty("Consignee", message.getConsignee());						
			writeNCTSParty("AuthorisedConsignee", message.getAuthorisedConsignee());			
			writeNCTSParty("Principal", message.getPrincipal());
			writeElement("CarnetID", message.getCarnetID());
			//writeClerk("Clerk", message.getClerk());
			writeClerk("Contact", message.getClerk());  //EI20130823
			writeElement("OfficeOfDeparture", message.getOfficeOfDeparture());
			
			if (message.getOfficeOfTransitList() != null) {
				for (OfficeOfTransit transit : message.getOfficeOfTransitList()) {
					writeOfficeOfTransit(transit);
				}
			}
			writeElement("OfficeOfDestination", message.getOfficeOfDestination());
			writeElement("ControlResultCode", message.getControlResultCode());			
			writeFormattedDateOrTime("ControlResultDateLimit", message.getControlResultDateLimit(), 
						message.getControlResultDateLimitFormat(), EFormat.KIDS_Date);				
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
					writeCodeElement("SuspensionIndicator", message.getTransRoute().getSuspensionIndicator(), "KCX0001");
				closeElement();
			}					
			writeElement("IdentificationCode", message.getIdentificationCode());     		       
			writeElement("BondedWareHouseRefNum", message.getBondedWareHouseRefNum());			  
			writeElement("BondedWarehousePermitNumber", message.getBondedWarehousePermitNumber()); 					
			writeNCTSParty("Carrier", message.getCarrier());  
			writeSecurity(message.getSecurity());						
			writeNCTSParty("ConsignorSecurity", message.getConsignorSecurity());      		
			writeNCTSParty("ConsigneeSecurity", message.getConsigneeSecurity());       
			
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
			writeCodeElement("TypeOfDeclaration", argument.getTypeOfDeclaration(), "KCX0063");
			writeCommodityCode(argument.getCommodityCode());
			writeElement("Description", argument.getDescription());
			writeElement("ArticleNumber", argument.getArticleNumber());  
			writeElement("DestinationCountry", argument.getDestinationCountry());
			writeElement("DispatchCountry", argument.getDispatchCountry());
			writeElement("NetMass", argument.getNetMass());
			writeElement("GrossMass", argument.getGrossMass());			
			writeCodeElement("IdentificationCode", argument.getIdentificationCode(), "KCX0063");			
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
			writeNCTSParty("Consignor", argument.getConsignor());			
			writeNCTSParty("Consignee", argument.getConsignee());			
			writeNCTSParty("ConsignorSecurity", argument.getConsignorSecurity());			
			writeNCTSParty("ConsigneeSecurity", argument.getConsigneeSecurity());
						
			if (argument.getManifestCompletionList() != null) {				
				writeWriteOffSumAList(argument.getManifestCompletionList());
			}
		closeElement();
	}

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
	
	public void writeWriteOffSumAList(List <ManifestCompletion> list) 	throws XMLStreamException {
		if (list == null) {
			return;
		}
		for (ManifestCompletion writeOffSumA : list) {			
			if (writeOffSumA != null) {					
				openElement("ManifestCompletion");  				
				writeElement("Identification", writeOffSumA.getIdentification()); 
				writeElement("ReferenceNumber", writeOffSumA.getReference()); 				
				writeWriteOffDataList(writeOffSumA.getCompletionItemList()); 
				closeElement();
			}
		}
	}
	
	public void writeWriteOffDataList(List<ManifestCompletionItem> list) 	throws XMLStreamException {
		if (list == null) {
			return;
		}
		for (ManifestCompletionItem item : list) {
			if (item != null && !item.isEmpty()) {
				openElement("CompletionItem");
					writeElement("PositionNumber", item.getPositionNumber());
					writeElement("UCR", item.getUCR());
					writeElement("NumberOfPieces", item.getNumberOfPieces());
					writeElement("AWB", item.getAWB());                        //EI20140527
					writePartyTIN("CustodianTIN", item.getCustodianTIN());     //EI20140527
					writeElement("AtlasAlignment", item.getAtlasAlignment());  //EI20130821
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
			writeCodeElement("Kind", argument.getKind(), "KCX0002");
			writeElement("Number", argument.getNumber());			
			writeCodeElement("UseOfTydenSeals", argument.getUseOfTydenseals(), "KCX0001");			
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
    		writeCodeElement("Type", argument.getType(), "KCX0065");
    	    writeElement("Reference", argument.getReference());
    	    writeElement("AdditionalInformation", argument.getAdditionalInformation());
    	    writeFormattedDateOrTime("IssueDate", argument.getIssueDate(),					
					argument.getIssueDateFormat(), EFormat.KIDS_Date);	    	    
    	closeElement();						
	}
	
}
