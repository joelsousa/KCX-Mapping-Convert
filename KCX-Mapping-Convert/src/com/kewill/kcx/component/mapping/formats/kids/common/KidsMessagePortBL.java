package com.kewill.kcx.component.mapping.formats.kids.common;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.Party;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.AdditionalReferenceGroup;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.Address;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.AddressUnqualified;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.AllocatedEquipment;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.AttachedEquipment;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.AttachedEquipmentDetails;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.Carriage;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.CommunicationContact;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.Contact;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.Currency;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.CurrencyDetails;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.CurrencyGroup;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.DangerousGoods;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.DangerousGoodsDetails;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.DistributedOverSeveralContainer;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.DocumentDetails;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.Equipment;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.EquipmentDetails;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.EquipmentQualifier;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.Goods;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.GoodsItem;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.ItemDetails;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.ItemText;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.MeansOfTransport;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.PackingLevel1;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.PackingLevel2;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.Parties;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.PartyDetails;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.Place;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.PortsAndPlaces;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.Reference;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.ReferenceDetails;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.Seals;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.TextBL;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.TextOnEntireBL;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.Totals;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.KcxDateTime;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module : Port<br>
 * Created : 28.10.2011<br>
 * Description : common methods for KFF-Kids bodies.
 * 
 * @author Iwaniuk
 * @version 1.0.00
 */
public class KidsMessagePortBL extends KidsMessage {	
 	
	public void writeParty(String tag, Party argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		openElement(tag);
			writeElement("CustomerIdentifier", argument.getCustomerIdentifier());
			writeElement("CustomsIdentifier", argument.getCustomsIdentifier());
			writeElement("TerminalCustomerNumber", argument.getTerminalCustomerNumber());
			writeElement("PortCode", argument.getPortCode());
			writeElement("TIN", argument.getTin());
			writeAddress(argument.getAddress());
			writeContactPerson(tag, argument.getContactPerson());
		closeElement();					   		    		  
	}
	
	 public void writeAddress(Address argument) throws XMLStreamException {
	    	if (argument == null) {
	    	    return;
	    	}
	    	if (argument.isEmpty()) {
				return;
			}	    	
	    	openElement("AddressQualified"); 
	    		if (argument.getPartyName() != null) {
	    			openElement("PartyName"); 
	    				writeElement("FirstRow", argument.getPartyName().getFirstRow());
	    				if (argument.getPartyName().getNextRowList() != null) {	    					
	    					for (String nr : argument.getPartyName().getNextRowList()) {	    						
	    						writeElement("NextRow", nr);	    				
	    					}
	    				}
	    			closeElement();   
	    		}
	    		if (argument.getStreetOrBox() != null) {	    			
	    			openElement("StreetOrBox"); 
    					writeElement("FirstRow", argument.getStreetOrBox().getFirstRow());
    					if (argument.getStreetOrBox().getNextRowList() != null) {	    					
	    					for (String nr : argument.getStreetOrBox().getNextRowList()) {	    						
	    						writeElement("NextRow", nr);	    				
	    					}
	    				}
    				closeElement(); 	    			
	    		}	           
	            writeElement("City", argument.getCity());
	            writeElement("PostalCode", argument.getPostalCode());            
	            writeElement("CountryCode", argument.getCountryCode());
	            writeElement("CountrySubEntity",  argument.getCountrySubEntity()); 
	        closeElement();     
	}	
	public void writeAddressUnqualified(AddressUnqualified argument) throws XMLStreamException {
	    	if (argument == null) {
	    	    return;
	    	}
	    	if (argument.isEmpty()) {
				return;
			}	    		    	
	    	if (argument.getPartyNameAndAddress() != null) {
	    		openElement("AddressUnqualified"); 
	    			writeElement("FirstRow", argument.getPartyNameAndAddress().getFirstRow());
	    			if (argument.getPartyNameAndAddress().getNextRowList() != null) {	    					
	    				for (String nr : argument.getPartyNameAndAddress().getNextRowList()) {	    						
	    					writeElement("NextRow", nr);	    				
	    				}
	    			}
	    		closeElement();   
	    	}
	 }
	public void writeContactPerson(String tag, ContactPerson argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		openElement("ContactPerson");
		writeElement("Position", argument.getPosition());
		writeElement("Clerk", argument.getClerk());
		writeElement("Identity", argument.getIdentity());
		writeElement("Email", argument.getEmail());
		writeElement("FaxNumber", argument.getFaxNumber());
		writeElement("PhoneNumber", argument.getPhoneNumber());
		writeElement("Initials", argument.getInitials());
		writeElement("Title", argument.getTitle());
		closeElement();
	}

	public void writeFormattedDateOrTime(String tag, String dateOrTimeString,
										EFormat currentFormat, EFormat resultFormat)
										throws XMLStreamException {		
		if (dateOrTimeString == null) {
    		return;
    	}
		if (Utils.isStringEmpty(dateOrTimeString)) {
    		return;
    	}
		if (currentFormat == null || resultFormat == null) {  //EI20110526 for FssTokids
			writeElement(tag, dateOrTimeString);			
			return;
		}
		
		if (currentFormat == EFormat.KIDS_DateTime) {                      //EI20110617
			if (dateOrTimeString.length() > 12) {
				dateOrTimeString = dateOrTimeString.substring(0, 12);  
			} else if (dateOrTimeString.length() < 12) {
				dateOrTimeString = dateOrTimeString + "000000000000";
				dateOrTimeString = dateOrTimeString.substring(0, 12); 
			}
		}
		if (currentFormat == EFormat.KIDS_Date) {                      //EI20110617
			if (dateOrTimeString.length() > 8) {
				dateOrTimeString = dateOrTimeString.substring(0, 8);  
			} else if (dateOrTimeString.length() < 8) {
				dateOrTimeString = dateOrTimeString + "00000000";
				dateOrTimeString = dateOrTimeString.substring(0, 8); 
			}
		}
		if (currentFormat == EFormat.KIDS_Time) {                      //EI20110617
			if (dateOrTimeString.length() > 6) {
				dateOrTimeString = dateOrTimeString.substring(0, 6);  
			} else if (dateOrTimeString.length() < 6) {
				dateOrTimeString = dateOrTimeString + "000000";
				dateOrTimeString = dateOrTimeString.substring(0, 6); 
			}
		}
		
		KcxDateTime kcx;
		try {
			kcx = new KcxDateTime(currentFormat, dateOrTimeString);
			writeElement(tag, kcx.format(resultFormat));
		} catch (ParseException e) {
			Utils.log(Utils.LOG_ERROR, 
					String.format("Date/Time string '%s' for element '%s' " +
					"could not be processed for output - %s", 
					dateOrTimeString, tag, e.getLocalizedMessage()));
		}
	}
	
	public void writeCurrencyGroup(CurrencyGroup argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		openElement("CurrencyGroup");
			if (argument.getCurrencyList() != null && argument.getCurrencyList().size() > 0) {
				for (Currency curr : argument.getCurrencyList()) {
					writeCurrency(curr);
				}
			}
			writeElement("CurrencyValidationDate", argument.getCurrencyValidationDate());		
		closeElement();

	}
	public void writeCurrency(Currency argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		openElement("Currency");
			if (argument.getCurrencyDetailList() != null && argument.getCurrencyDetailList().size() > 0) {
				for (CurrencyDetails cd : argument.getCurrencyDetailList()) {
					writeCurrencyDetails(cd);
				}
			}
			writeElement("RateOfExchange", argument.getRateOfExchange());		
		closeElement();

	}
	public void writeCurrencyDetails(CurrencyDetails argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		openElement("CurrencyDetail");
			writeElement("EDIQualifier", argument.getEDIQualifier());	
			writeElement("CurrencyCode", argument.getCurrencyCode());	
			writeElement("CurrencyRateBase", argument.getCurrencyRateBase());		
		closeElement();
	}	
	

	public void writeContactBL(String person, Contact argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		openElement(person);
			writeElement("ContactName", argument.getContactName());	
			if (argument.getCommunicationContactList() != null) {
				for (CommunicationContact contact : argument.getCommunicationContactList()) {	
					if (contact != null && !contact.isEmpty()) {
						openElement("CommunicationContact");
							writeElement("CommunicationNumber", contact.getCommunicationNumber());	
							writeElement("CommunicationQualifier", contact.getCommunicationQualifier());
						closeElement();
					}
				}
			}
		closeElement();
	}	
	public void writeTotalsOnEntireBL(Totals argument)  throws XMLStreamException {		
	    if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		openElement("TotalsOnEntireBL");
			writeElement("GrossWeightKilogram", argument.getGrossWeightKilogram());	
			writeElement("NumberOfPackages", argument.getNumberOfPackages());	
			writeElement("GrossVolumeCubicMetre", argument.getGrossVolumeCubicMetre());	
		closeElement();
	}	
	public void writeDetailsOnDocument(DocumentDetails argument)  throws XMLStreamException {		
	    if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		openElement("DetailsOnDocument");
			writeElement("DocumentName", argument.getDocumentName());	
			writeElement("FormularLayoutKeyDakosy", argument.getFormularLayoutKeyDakosy());	
			writeElement("NumberOfCopiesRequired", argument.getNumberOfCopiesRequired());	
			writeElement("NumberOfOriginalsRequired", argument.getNumberOfOriginalsRequired());	
		closeElement();
	}	
	public void writeForwardersReference(ReferenceDetails argument)  throws XMLStreamException {		
	    if (argument == null) {
			return;
		}
		if (Utils.isStringEmpty(argument.getReference())) {
			return;
		}
		openElement("ForwardersReference");
			writeElement("Reference", argument.getReference());	
			writeElement("Date", argument.getDate());	
			writeElement("DateTime", argument.getDateTime());			
		closeElement();
	}
	public void writeAdditionalReferenceGroup(AdditionalReferenceGroup argument)  throws XMLStreamException {		
	    if (argument == null) {
			return;
		}
	    if (argument.getAdditionalReferenceList() == null) {
			return;
		}
	    openElement("AdditionalReferenceGroup");
			for (Reference ar : argument.getAdditionalReferenceList()) {
				if (ar != null && ar.getReferenceDetail() != null) {
					 openElement("AdditionalReference");
					writeElement("ReferenceName", ar.getReferenceName());					
					writeElement("Reference", ar.getReferenceDetail().getReference());	
					writeElement("DateTime", ar.getReferenceDetail().getDate());
					writeElement("DateTime", ar.getReferenceDetail().getDateTime());
					closeElement();
				}
			}
		closeElement();
	}
	public void writeTextOnEntireBLGroup(TextOnEntireBL argument)  throws XMLStreamException {		
	    if (argument == null) {
			return;
		}	      
		if (argument.isEmpty()) {
			return;
		}
		openElement("TextOnEntireBL");	
		if (argument.getBillOfLadingRemarksList() != null) {	
			openElement("BillOfLadingRemarks");
			for (TextBL tx : argument.getBillOfLadingRemarksList()) {	
				if (tx.getTextList() != null) {
					for (String tt : tx.getTextList()) {
						writeElement("Text", tt);	
					}
				}
				writeElement("LanguageCoded", tx.getLanguageCoded());	
			}
				
			closeElement();
		}
		if (argument.getPackingMarkingInformationList() != null) {	
			openElement("PackingMarkingInformation");
			for (TextBL tx : argument.getPackingMarkingInformationList()) {			
				//writeElement("TextReference", tx.getText());	
				if (tx.getTextList() != null) {
					for (String tt : tx.getTextList()) {
						writeElement("Text", tt);	
					}
				}
				writeElement("LanguageCoded", tx.getLanguageCoded());	
			}
			closeElement();
		}
		if (argument.getDocumentationInstructionsList() != null) {	
			openElement("DocumentationInstructions");
			for (TextBL tx : argument.getDocumentationInstructionsList()) {			
				writeElement("TextReference", tx.getTextReference());	
				if (tx.getTextList() != null) {
					for (String tt : tx.getTextList()) {
						writeElement("Text", tt);	
					}
				}
				writeElement("LanguageCoded", tx.getLanguageCoded());	
			}
			closeElement();
		}
		if (argument.getGoodsDimensionsInWords() != null) {				
			openElement("GoodsDimensionsInWords");
				//writeElement("TextReference", argument.getGoodsDimensionsInWords().getText());			
			if (argument.getGoodsDimensionsInWords().getTextList() != null) {
				for (String tt : argument.getGoodsDimensionsInWords().getTextList()) {
					writeElement("Text", tt);	
				}
			}
			writeElement("LanguageCoded", argument.getGoodsDimensionsInWords().getLanguageCoded());
			closeElement();
			
		}
		if (argument.getAdditionalInformationList() != null) {				
			openElement("AdditionalInformation");
			for (TextBL tx : argument.getAdditionalInformationList()) {			
				//writeElement("TextReference", tx.getText());	
				if (tx.getTextList() != null) {
					for (String tt : tx.getTextList()) {
						writeElement("Text", tt);	
					}
				}
				writeElement("LanguageCoded", tx.getLanguageCoded());	
			}
			closeElement();
		}
		if (argument.getCargoRemarks() != null) {	
			openElement("CargoRemarks");
			if (argument.getCargoRemarks() != null) {			
				writeElement("TextReference", argument.getCargoRemarks().getTextReference());					
			}
			closeElement();
		}
		if (argument.getComplianceWasChecked() != null) {				
			openElement("ComplianceWasChecked");
				//writeElement("TextReference", argument.getComplianceWasChecked().getText());
			if (argument.getComplianceWasChecked().getTextList() != null) {
				for (String tt : argument.getComplianceWasChecked().getTextList()) {
					writeElement("Text", tt);	
				}
			}
			writeElement("LanguageCoded", argument.getComplianceWasChecked().getLanguageCoded());
			closeElement();
			
		}
		closeElement();
	}	
	public void writeParties(Parties argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}		
		if (argument.getShipper() != null || argument.getConsignee() != null) {		
			openElement("Party");		
				writePartyDetails("Shipper", argument.getShipper());			
				writePartyDetails("Consignee", argument.getConsignee());
				writePartyDetails("Notify", argument.getNotify());			
				writePartyDetails("BookingAgent", argument.getBookingAgent());
				writePartyDetails("OriginalShipper", argument.getOriginalShipper());			
				writePartyDetails("SecondNotify", argument.getSecondNotify());
				writePartyDetails("OrderOfShipper", argument.getOrderOfShipper());			
				writePartyDetails("Deconsolidator", argument.getDeconsolidator());
				writePartyDetails("Carrier", argument.getShipper());			
				writePartyDetails("FreightForwarder", argument.getFreightForwarder());						
				writePartyDetails("FreightForwarderRequestorBranch", argument.getFreightForwarderRequestorBranch());						
			closeElement();	
		}
	}
	public void writePartyDetails(String person, PartyDetails argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		openElement(person);
		writeElement("PartyId", argument.getPartyId());			
			if (argument.getAddressQualified() != null) {
				writeAddress(argument.getAddressQualified());
			//EI20130422: else if (argument.getAddressUnqualified() != null) {
			}
			if (argument.getAddressUnqualified() != null) {
				writeAddressUnqualified(argument.getAddressUnqualified());
			}
			writeContactBL("Contact", argument.getContact());
			writeElement("TaxRegistrationNumber", argument.getTaxRegistrationNumber());
			if (argument.getReferenceList() != null) {
				for (String ref : argument.getReferenceList()) {
					writeElement("Reference", ref);		
				}
			}
		closeElement();	
	}
	public void writeCarriage(Carriage argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}		
		if (argument.isEmpty()) {
			return;
		}
		//mandatory: MainCarrige
		openElement("Carriage");
			if (argument.getMainCarriage() != null) {
				openElement("MainCarriage");				
					writeMeansOfTransport(argument.getMainCarriage().getMeansOfTransport());				
					writeElement("EstimatedDepartureDate", argument.getMainCarriage().getEstimatedDepartureDate());	
					writeElement("EstimatedArrivalDate", argument.getMainCarriage().getEstimatedArrivalDate());	
					writePortsAndPlaces(argument.getMainCarriage().getPortsAndPlaces());	
					writeElement("ShipReferenceNumber", argument.getMainCarriage().getShipReferenceNumber());	
				closeElement();
			}
			if (argument.getPreCarriageList() != null) {
				openElement("PreCarriage");
				for (MeansOfTransport preTrans : argument.getPreCarriageList()) {
					if (preTrans != null) {
						writeMeansOfTransport(preTrans);	
					}
				}
				closeElement();
			}
			if (argument.getOnCarriageList() != null) {
				openElement("OnCarriage");
				for (MeansOfTransport onTrans : argument.getOnCarriageList()) {
					if (onTrans != null) {
						writeMeansOfTransport(onTrans);	
					}
				}
				closeElement();
			}				
		closeElement();	
	}
	
	public void writeMeansOfTransport(MeansOfTransport argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}		
		if (argument.isEmpty()) {
			return;
		}		
		openElement("MeansOfTransport");		
			writeElement("ShipownerVoyageNumber", argument.getShipownerVoyageNumber());	
			writeElement("TypeOfMeansOfTransport", argument.getTypeOfMeansOfTransport());	
			writeElement("CarrierId", argument.getCarrierId());	
			writeElement("CarrierName", argument.getCarrierName());	
			writeElement("RadioCallSign", argument.getRadioCallSign());	
			writeElement("VesselName", argument.getVesselName());	
			writeElement("VesselNationalityCode", argument.getVesselNationalityCode());			
		closeElement();		
	}
	public void writePortsAndPlaces(PortsAndPlaces argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}		
		if (argument.isEmpty()) {
			return;
		}		
		openElement("PortsAndPlaces");		
			writePlace("PortOfLoading", argument.getPortOfLoading());	
			writePlace("PortOfDischarge", argument.getPortOfDischarge());	
			writePlace("PlaceOfDeparture", argument.getPlaceOfDeparture());	
			writePlace("PlaceOfReceipt", argument.getPlaceOfReceipt());	
			writePlace("PlaceOfDelivery", argument.getPlaceOfDelivery());	
			writePlace("PlaceOfAcceptance", argument.getPlaceOfAcceptance());	
			writePlace("PlaceOfTranshipment", argument.getPlaceOfTranshipment());	
			writePlace("OnCarriagePort", argument.getOnCarriagePort());	
			writePlace("PreCarriagePort", argument.getPreCarriagePort());	
			writePlace("PlaceOfDestination", argument.getPlaceOfDestination());				
		closeElement();		
	}
	public void writePlace(String placeName, Place argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}		
		if (argument.isEmpty()) {
			return;
		}		
		openElement(placeName);		
			writeElement("UNLocode", argument.getUnLocode());	
			writeElement("LocationClearText", argument.getLocationClearText());			
		closeElement();		
	}
	public void writeGoods(Goods argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}		
		if (argument.isEmpty()) {
			return;
		}
		if (argument.getGoodsItemList() != null && argument.getGoodsItemList().size() > 0) {
			openElement("Goods");	
				for (GoodsItem item : argument.getGoodsItemList()) {
					writeGoodsItemBL(item);	
				}
			closeElement();	
		}
	}
	public void writeGoodsItemBL(GoodsItem argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}		
		if (argument.isEmpty()) {
			return;
		}		
		openElement("GoodsItem");		
			writeElement("GoodsItemNumber", argument.getGoodsItemNumber());	
			writeFirstPackingLevel(argument.getFirstPackingLevel());
		closeElement();	
	}
	public void writeFirstPackingLevel(PackingLevel1 argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}		
		if (argument.isEmpty()) {
			return;
		}		
		openElement("FirstPackingLevel");
			writeDetailsOnItem(argument.getDetailsOnItem());
			writeTextOnItem(argument.getTextOnItem());
			writeElement("GrossWeightKilogram", argument.getGrossWeightKilogram());	
			if (argument.getAllocatedEquipmentList() != null) {
				for (AllocatedEquipment ae : argument.getAllocatedEquipmentList()) {
					writeAllocatedEquipment(ae);
				}
			}
			if (argument.getDangerousGoodsList() != null) {
				for (DangerousGoods gg : argument.getDangerousGoodsList()) {
					writeDangerousGoods(gg);
				}
			}			
			if (argument.getPackingLevel2List() != null) {
				for (PackingLevel2 sl : argument.getPackingLevel2List()) {
					writeSecondPackingLevel(sl);
				}
			}
		closeElement();	
	}
	
	public void writeDetailsOnItem(ItemDetails argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}		
		if (argument.isEmpty()) {
			return;
		}		
		openElement("DetailsOnItem");
		
		writeElement("NumberOfPackages", argument.getNumberOfPackages());	

		//AK20130419
		KidsMessagePortGpo  temp = new KidsMessagePortGpo();
		String 				colliArt = temp.getColliArtFromJOB(argument.getTypeOfPackagesIdentification()); 
		//EI20130513: writeElement("TypeOfPackagesIdentification", colliArt);	
		writeCodeElement("TypeOfPackagesIdentification", argument.getTypeOfPackagesIdentification(), 
									"KCX0066"); //EI20130513
		writeElement("PackagesText", argument.getTypeOfPackagesText());				
		writeElement("HarmonizedSystemCommodityCode", argument.getHarmonizedSystemCommodityCode());	
		writePartyDetails("OriginalShipper", argument.getOriginalShipper());			
		writePartyDetails("UltimateConsignee", argument.getUltimateConsignee());
		writeElement("NetWeightKilogram", argument.getNetWeightKilogram());	
		writeElement("TareWeightKilogram", argument.getTareWeightKilogram());	
		writeElement("GrossVolumeCubicMetre", argument.getGrossVolumeCubicMetre());	
		writeElement("BookingReferenceNumber", argument.getBookingReferenceNumber());	
		writeElement("CustomsDeclarationNumber", argument.getCustomsDeclarationNumber());	
		writeElement("ArticleNumber", argument.getArticleNumber());	
		writeElement("CargoControlNumber", argument.getCargoControlNumber());	
		if (argument.getMarksAndNumbersList() != null) {
			for (String marks : argument.getMarksAndNumbersList()) {
				if (marks != null) {
					writeElement("MarksAndNumbers", marks);	
				}
			}
		}
		closeElement();	
	}
	public void writeTextOnItem(ItemText argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}		
		if (argument.isEmpty()) {
			return;
		}		
		openElement("TextOnItem");
			writeTextBLList("GoodsDescription", argument.getGoodsDescriptionList());	
			writeTextBLList("GoodsDescriptionNoPrint", argument.getGoodsDescriptionNoPrintList());	
			writeTextBLList("RemarksBeforeGoodsDescription", argument.getRemarksBeforeGoodsDescriptionList());	
			writeTextBLList("RemarksAfterGoodsDescription", argument.getRemarksAfterGoodsDescriptionList());	
			writeTextBLList("PackagingInformation", argument.getPackagingInformationList());	
		closeElement();	
	}
	public void writeTextBLList(String textType, List<TextBL> list) throws XMLStreamException {
		if (list == null) {
			return;
		}	
		if (list.size() == 0) {
			return;
		}
		for (TextBL text : list) {
			if (text != null) {
				openElement(textType);	
				if (text.getTextList() != null) {
					for (String tx : text.getTextList()) {
						writeElement("Text", tx);											
					}
					writeElement("LanguageCoded", text.getLanguageCoded());		
				}
				closeElement();
			}
		}
	}
	public void writeAllocatedEquipment(AllocatedEquipment argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}		
		if (argument.isEmpty()) {
			return;
		}		
		openElement("AllocatedEquipment");		
			writeElement("EquipmentIdentificationNumber", argument.getEquipmentIdentificationNumber());							
			writeDistributedOverSeveralContainer(argument.getDistributedOverSeveralContainert());								
		closeElement();	
	}
	public void writeDangerousGoods(DangerousGoods argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}		
		if (argument.isEmpty()) {
			return;
		}		
		openElement("DangerousGoods");		
			writeDangerousGoodsDetails("IMD", argument.getImd());	
			writeDangerousGoodsDetails("ADR", argument.getAdr());	
			writeDangerousGoodsDetails("ANR", argument.getAnr());	
			writeDangerousGoodsDetails("RID", argument.getRid());				
		closeElement();	
	}
	public void writeDangerousGoodsDetails(String type, DangerousGoodsDetails argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}		
		if (argument.isEmpty()) {
			return;
		}		
		openElement(type);
			writeElement("HazardCodeIdentification", argument.getHazardCodeIdentification());	
			writeElement("HazardItemPageNumber", argument.getHazardItemPageNumber());	
			writeElement("HazardCodeVersionNumber", argument.getHazardCodeVersionNumber());	
			writeElement("UNDGNumber", argument.getUNDGNumber());
			if (argument.getShipmentFlashpoint() != null && !Utils.isStringEmpty(argument.getShipmentFlashpoint().getFlashpoint())) {
				openElement("ShipmentFlashpoint");
					writeElement("Flashpoint", argument.getShipmentFlashpoint().getFlashpoint());
					writeElement("FlashpointQualifier", argument.getShipmentFlashpoint().getFlashpointQualifier());
				closeElement();	
			}
			writeElement("LevelOfDanger", argument.getLevelOfDanger());	
			writeElement("EMSNumber", argument.getEMSNumber());	
			writeElement("MFAG", argument.getMFAG());	
			if (argument.getLabelMarkingList() != null) {				
				for (String lm : argument.getLabelMarkingList()) {
					writeElement("LabelMarking", lm);						
				}				
			}
			if (argument.getTechnicalName() != null && 
					(argument.getTechnicalName().getTLQ() != null || argument.getTechnicalName().getTEQ() != null)) {
				openElement("TechnicalName");
				if (argument.getTechnicalName().getTLQ() != null && argument.getTechnicalName().getTLQ().getTextList()  != null) {
					openElement("TLQ");
					for (String tx : argument.getTechnicalName().getTLQ().getTextList()) {
						writeElement("Text", tx);		
					}
					closeElement();	
				}
				if (argument.getTechnicalName().getTEQ() != null && argument.getTechnicalName().getTEQ().getTextList()  != null) {
					openElement("TEQ");
					for (String tx : argument.getTechnicalName().getTEQ().getTextList()) {
						writeElement("Text", tx);		
					}
					closeElement();	
				}
				closeElement();	
			}
			if (argument.getAdditionalInformation() != null && 
					(argument.getAdditionalInformation().getLevelOfMarinePollution() != null || argument.getAdditionalInformation().getTextList() != null)) {
				openElement("AdditionalInformation");
					writeElement("LevelOfMarinePollution", argument.getAdditionalInformation().getLevelOfMarinePollution());	
					if (argument.getAdditionalInformation().getLevelOfMarinePollution() != null) {
						for (String tx : argument.getAdditionalInformation().getTextList()) {
							writeElement("Text", tx);		
						}
					}
				closeElement();	
			}	
			if (argument.getEmergencyContactList() != null) { 
				for (Contact contact : argument.getEmergencyContactList()) {
					if (contact.getContactName() != null || contact.getCommunicationContactList() != null) {
					openElement("EmergencyContact");
						writeElement("ContactName", contact.getContactName());	
						if (contact.getCommunicationContactList() != null) {
							for (CommunicationContact cc : contact.getCommunicationContactList()) {
								if (cc != null) {
								openElement("CommunicationContact");
									writeElement("CommunicationNumber", cc.getCommunicationNumber());	
									writeElement("CommunicationQualifier", cc.getCommunicationQualifier());	
								closeElement();	
								}
							}
						}
					closeElement();	
					}
				}
			}			
			writeElement("GrossWeightKilogram", argument.getGrossWeightKilogram());	
			writeElement("NetWeightKilogram", argument.getNetWeightKilogram());	
			//writeElement("NetNetWeightKilogram", argument.???);
			writeElement("NetExplosiveWeightKilogram", argument.getNetExplosiveWeightKilogram());	
			writeElement("RadioactiveIndexOfTransportNumber", argument.getRadioactiveIndexOfTransportNumber());	
			writeElement("RadioactivityBecquerel", argument.getRadioactivityBecquerel());	
			writeElement("RadioactivityCurie", argument.getRadioactivityCurie());	
		closeElement();	
	}
	public void writeSecondPackingLevel(PackingLevel2 argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}		
		if (argument.isEmpty()) {
			return;
		}		
		if (argument.getDetailsOnItem() != null) {
			openElement("SecondPackingLevel");					
				writeElement("NumberOfPackages", argument.getDetailsOnItem().getNumberOfPackages());	
				writeCodeElement("TypeOfPackagesIdentification", argument.getDetailsOnItem().getTypeOfPackagesIdentification(),
										"KCX0066");   //EI20130513
				writeElement("NetWeightKilogram", argument.getDetailsOnItem().getNetWeightKilogram());
			closeElement();	
		}
	}
	///
	public void writeEquipment(Equipment argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}		
		if (argument.isEmpty()) {
			return;
		}	
		if (argument.getEquipmentQualifierList() != null && argument.getEquipmentQualifierList().size() > 0) {
			openElement("Equipment");
			for (EquipmentQualifier eq : argument.getEquipmentQualifierList()) {
				writeEquipmentQualifierBL(eq);	
			}
			closeElement();	
		}
	}
	public void writeEquipmentQualifierBL(EquipmentQualifier argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}		
		if (argument.isEmpty()) {
			return;
		}			
		openElement("EquipmentQualifier");	
		if (argument.getContainer() != null) {
			writeEquipmentDetail("Container", argument.getContainer());
		}
		if (argument.getTrailer() != null) {
			writeEquipmentDetail("Trailer", argument.getContainer());
		}				
		closeElement();	
	}
	public void writeEquipmentDetail(String type, EquipmentDetails argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}		
		if (argument.isEmpty()) {
			return;
		}		
		openElement(type);		
		//TODO-IWA wenn mit blank, dann zusammen schienem: MAEU 9832899 ==>MAEU9832899
			writeElement("EquipmentIdentificationNumber", argument.getEquipmentIdentificationNumber());	
			//writeElement("TypeAndSizeISOCode", argument.getTypeAndSizeISOCode());
			// AK20121212-2
			writeElement("TypeandSizeISOCode", argument.getTypeAndSizeISOCode());
			writeElement("TypeAndSizeText", argument.getTypeAndSizeText());	
			writeElement("EquipmentSuppliedBy", argument.getEquipmentSuppliedBy());	
			writeElement("FullEmptyIndicator", argument.getFullEmptyIndicator());	
			writeElement("MovementTypeCode", argument.getMovementTypeCode());	
			writeElement("MovementType", argument.getMovementType());	
			writeElement("EquipmentPlan", argument.getEquipmentPlan());	
			writeElement("WeightPerUnitKilogram", argument.getWeightPerUnitKilogram());	
			writeElement("TareWeightKilogram", argument.getTareWeightKilogram());	
			writeElement("GrossWeightKilogram", argument.getGrossWeightKilogram());	
			writeElement("GrossVolumeCubicMetre", argument.getGrossVolumeCubicMetre());				
			if (argument.getSealsList() != null) {
				for (Seals se : argument.getSealsList()) {
					writeSealsBL(se);	
				}
			}
			writeElement("CustomsDeclarationNumber", argument.getCustomsDeclarationNumber());
			if (argument.getAttachedEquipmentList() != null) {
				for (AttachedEquipment ae : argument.getAttachedEquipmentList()) {
					writeAttachedEquipment(ae);	
				}
			}
			
		closeElement();	
	}
	public void writeSealsBL(Seals argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}		
		if (argument.isEmpty()) {
			return;
		}			
		openElement("Seals");	
			writeElement("SealingPartyCode", argument.getSealingPartyCode());
			writeElement("SealingParty", argument.getSealingParty());
			writeElement("SealingNumber", argument.getSealingNumber());		
		closeElement();	
	}
	public void writeAttachedEquipment(AttachedEquipment argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}		
		if (argument.isEmpty()) {
			return;
		}			
		openElement("AttachedEquipment");	
			writeAttachedEquipmentDetails("ReeferGenerator", argument.getReeferGenerator());
			writeAttachedEquipmentDetails("Trailer", argument.getTrailer());
			writeAttachedEquipmentDetails("Chassis", argument.getChassis());		
		closeElement();	
	}
	public void writeAttachedEquipmentDetails(String type, AttachedEquipmentDetails argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}		
		if (argument.isEmpty()) {
			return;
		}			
		openElement(type);	
			writeElement("EquipmentIdentificationNumber", argument.getEquipmentIdentificationNumber());				
		closeElement();	
	}
	
	//EI20130412:
	public void writeDistributedOverSeveralContainer(DistributedOverSeveralContainer argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		/*
		if (argument.isEmpty()) {
			return;
		}
		*/
		openElement("DistributedOverSeveralContainer");	
			writeElement("NumberOfPackages", argument.getNumberOfPackages());	
			writeElement("GrossWeightKilogram", argument.getGrossWeightKilogram());
			writeElement("NetWeightKilogram", argument.getNetWeightKilogram());	
			writeElement("TareWeightKilogram", argument.getTareWeightKilogram());	
			writeElement("GrossVolumeCubicMetre", argument.getGrossVolumeCubicMetre());			
		closeElement();	
	}
	
	public String fillDecimalWithZero(String zahl, int vFill, int nFill)  {
		if (Utils.isStringEmpty(zahl)) {
			return "";
		}				
		
		String[] zahlSplit = zahl.split("\\.");
		int size = zahlSplit.length;
		if (size > 2) {
			return zahl;
		} else if (size == 2) { 
		
			String vorkomma = zahlSplit[0];		
			String nachkomma = zahlSplit[1];
			if (Utils.isStringEmpty(vorkomma)) {
				vorkomma = "00000000";
			}
			if (Utils.isStringEmpty(nachkomma)) {
				nachkomma = "000";
			}
			int vl = vorkomma.length();
			int nl = nachkomma.length();
		
			if (vl <= vFill) {
						
				for (int i = 0; i < vFill - vl; i++) {
					vorkomma = "0" + vorkomma;
				}
				if (nl >= nFill) {
					if (nFill == 2) {
						nachkomma = nachkomma.substring(0, 2);					
					} else if (nFill == 3) {
						nachkomma = nachkomma.substring(0, 3);	
					}
				} else {
				for (int i = 0; i < nFill - nl; i++) {
					nachkomma = nachkomma + "0";
				}
				}
				zahl = vorkomma + "." + nachkomma;
			}
		} else if (size == 1) {
			zahl = zahlSplit[0];
			if (Utils.isStringEmpty(zahl)) {
				zahl = "00000000";
			}
			int len = zahl.length();
			if (len < vFill)
			for (int i = 0; i < vFill - len; i++) {
				zahl = "0" + zahl;
			}	
			zahl = zahl + ".";
			for (int i = 0; i < nFill; i++) {
				zahl = zahl + "0";
			}
			
		} 
		
		return zahl;
	}

	public String getEdiQualifierOfText(String textName)  {
		String ediQualifier = "";
		if (Utils.isStringEmpty(textName)) {
			return "";
		}		
		String text = textName.trim();
		if (text.equalsIgnoreCase("BILLOFLADINGREMARKS")) {
			ediQualifier = "AAS";
		}
		if (text.equalsIgnoreCase("PACKINGMARKINGINFORMATION")) {
			ediQualifier = "PAC";
		}
		if (text.equalsIgnoreCase("DOCUMENTATIONINSTRUCTIONS")) {
			ediQualifier = "DOC";
		}
		if (text.equalsIgnoreCase("GOODSDIMENSIONSINWORDS")) {
			ediQualifier = "AAL";
		}
		if (text.equalsIgnoreCase("ADDITIONALINFORMATION")) {
			ediQualifier = "ACB";
		}
		if (text.equalsIgnoreCase("CARGOREMARKS")) {
			ediQualifier = "AEA";
		}
		if (text.equalsIgnoreCase("COMPLIANCEWASCHECKED")) {
			ediQualifier = "SSR";
		}
		//
		if (text.equalsIgnoreCase("GOODSDESCRIPTION")) {
			ediQualifier = "AAA";
		}
		if (text.equalsIgnoreCase("GOODSDESCRIPTIONNOPRINT")) {  
			ediQualifier = "AAA";   //TODO-IWA was soll hier stehen?
		}
		if (text.equalsIgnoreCase("REMARKSBEFOREGOODSDESCRIPTION")) {
			ediQualifier = "AAS";
			//ediQualifier = "3";
		}
		if (text.equalsIgnoreCase("REMARKSAFTERGOODSDESCRIPTION")) {
			ediQualifier = "AAS";
		}
		if (text.equalsIgnoreCase("PACKAGINGINFORMATION")) {
			ediQualifier = "AAQ";
		}
		
		return ediQualifier;
	}
	public String getEdiQualifierOfReferenceName(String refName)  {				
		if (Utils.isStringEmpty(refName)) {
			return "";
		}
		
		String ediQualifier = "";
		String text = refName.trim();
		
		if (text.equalsIgnoreCase("BillOfLadingNumber") || text.equalsIgnoreCase("BillOfLadingNo")) {
			ediQualifier = "BM";
		}
		/*
		if (text.equalsIgnoreCase("BookingReferenceNumber")) {
			ediQualifier = "BN";
		}
		*/
		if (text.equalsIgnoreCase("ExportersReferenceNumber") || text.equalsIgnoreCase("ExportersReferenceNo")) {
			ediQualifier = "ERN";
		}
		if (text.equalsIgnoreCase("ImportLicenseNumber") || text.equalsIgnoreCase("ImportLicenseNo")) {
			ediQualifier = "IP";
		}
		if (text.equalsIgnoreCase("CustomerSpecificationNumber") || text.equalsIgnoreCase("CustomerSpecificationNo")) {
			ediQualifier = "AEG";
		}
		if (text.equalsIgnoreCase("ShipmentReferenceNumber") || text.equalsIgnoreCase("ShipmentReferenceNo")) {
			ediQualifier = "SRN";
		}
		if (text.equalsIgnoreCase("DeclarantsCustomsIdentityNumber") || text.equalsIgnoreCase("DeclarantsCustomsIdentityNo")) {
			ediQualifier = "ABP";
		}
		if (text.equalsIgnoreCase("ContractNumber")|| text.equalsIgnoreCase("ContractNo")) {
			ediQualifier = "CT";
		}
		if (text.equalsIgnoreCase("PartConsignmentNumber") || text.equalsIgnoreCase("PartConsignmentNo")) {
			ediQualifier = "AAP";
		}
	
		return ediQualifier;
	}
	public String getEdiQualifierOfParty(String name)  {
		String ediQualifier = "";
		if (Utils.isStringEmpty(name)) {
			return "";
		}		
		String text = name.trim();
		
		if (text.equalsIgnoreCase("SHIPPER")) {   //consignor
			ediQualifier = "CZ";
		}	
		if (text.equalsIgnoreCase("CONSIGNEE")) {
			ediQualifier = "CN";
		}		
		if (text.equalsIgnoreCase("ULTIMATECONSIGNEE")) {  //EI20120515
			ediQualifier = "UC";
		}
		if (text.equalsIgnoreCase("NOTIFY")) {
			ediQualifier = "N1";
		}
		if (text.equalsIgnoreCase("BOOKINGAGENT")) {
			ediQualifier = "BA";
		}
		if (text.equalsIgnoreCase("ORIGINALSHIPPER")) {
			ediQualifier = "OS";
		}
		if (text.equalsIgnoreCase("SECONDNOTIFY")) {
			ediQualifier = "N2";
		}
		if (text.equalsIgnoreCase("ORDEROFSHIPPER")) {
			ediQualifier = "OO";
		}
		if (text.equalsIgnoreCase("DECONSOLIDATOR")) {   
			ediQualifier = "DC";
		}	
		if (text.equalsIgnoreCase("CARRIER")) {
			ediQualifier = "CA";
		}		
		if (text.equalsIgnoreCase("FREIGHTFORWARDER")) {
			ediQualifier = "FW";
		}
		if (text.equalsIgnoreCase("FREIGHTFORWARDERREQUESTORBRANCH")) {
			ediQualifier = "FW";   //TODO-IWA was hier???
		}
	
		return ediQualifier;
	}
	public String getFreightAndChargeId(String name) {
		String id = "";
		if (Utils.isStringEmpty(name)) {
			return "";
		}		
		String text = name.trim();
		
		if (text.equalsIgnoreCase("InlandFreightOrigin")) {   //consignor
			id = "101035";
		}	
		if (text.equalsIgnoreCase("TerminalHandlingChargeOrigin")) {
			id = "209060";
		}		
		if (text.equalsIgnoreCase("Seafreight")) {  //EI20120515
			id = "TODO-IWA";
		}
		if (text.equalsIgnoreCase("Airfreight") || text.equalsIgnoreCase("Airfreight(FF)")) {  //EI20120515
			id = "101021";
		}
		if (text.equalsIgnoreCase("TerminalHandlingChargeDestination")) {
			id = "209058";
		}
		if (text.equalsIgnoreCase("InlandFreightDestination")) {
			id = "101036";
		}
		if (text.equalsIgnoreCase("Disbursements")) {
			id = "607001";
		}
		return id;
	}
	public String addZabisDecimalPlace(String amount, int nk) {	 
		String ret = "";		
	    double d = 0;
	    
	    if (amount == null) {
	       return ret;
	    } 
	    
	    if (amount == "") {
	        ret = "";
	    } else {
	        	
			try {
					d = Double.parseDouble(amount);
			} catch (NumberFormatException e) {					
					e.printStackTrace();
			}
	
	        while (nk != 0) {
	           d = d * 10;              
	           nk--;
	        }
	        BigDecimal newDec = new BigDecimal(d);
	        newDec = newDec.setScale(0, BigDecimal.ROUND_HALF_UP);
	        ret = Utils.removeDots(newDec.toString());
	    }
	    return ret; 
	}	

	
	/*
    writeMonetaryAmountGroup(message.getMonetaryAmountGroup());   	
	writePlaceAndDateOf(message.getPlaceAndDateOf());
	writeTermsOfTransportList(message.getTermsOfTransportList());	
	writePaymentInstructionsGroup(message.getPaymentInstructionsGroup());
	writeFreightAndChargeGroup(message.getFreightAndChargeGroup());	
	*/	
}
