package com.kewill.kcx.component.mapping.formats.kids.common;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.AdditionalData;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.ConsolidatedContainer;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.Container;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.DangerousGoods;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.EADocument;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.EAPosition;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.GoodsItem;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.GoodsLevel;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.MrnPosition;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.MrnStatement;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.Party;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.PostCarriage;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.PreCarriage;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.Voyage;
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
public class KidsMessagePortGpo extends KidsMessage {

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
			writeElement("AdditionalIdentifier", argument.getCustomsIdentifier());
			writeElement("TerminalCustomerNumber", argument.getTerminalCustomerNumber());
			writeElement("PortCode", argument.getPortCode());
			writeElement("TIN", argument.getTin());
			writeAddress(argument.getAddress());
			//EI20131015: writeContactPerson(tag, argument.getContactPerson());
			writeContactPerson("ContactPerson", argument.getContactPerson()); //EI20131015:
		closeElement();					   		    		  
	}
	
	 public void writeAddress(Address argument) throws XMLStreamException {
	    	if (argument == null) {
	    	    return;
	    	}
	    	if (argument.isEmpty()) {
				return;
			}	    	
	    	openElement("Address"); 
	            writeElement("Name", argument.getName());
	            writeElement("Street", argument.getStreet());
	            writeElement("HouseNumber", argument.getHouseNumber());
	            writeElement("City", argument.getCity());
	            writeElement("PostalCode", argument.getPostalCode());            
	            writeElement("Country", argument.getCountry());
	            writeElement("CountrySubEntity",  argument.getCountrySubEntity()); 
	        closeElement();     
	}
	 	
	public void writeContactPerson(String tag, ContactPerson argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		//EI20131015: openElement("ContactPerson");
		openElement(tag);   //EI20131015
		writeElement("Position", argument.getPosition());
		writeElement("Name", argument.getName());       //EI20120427
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
	
	public void writeVoyage(Voyage argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		openElement("VoyageDetails");
		//writeElement("ShipDepartureNumber", argument.getShipReferenceNumber());
		writeElement("ShipReferenceNumber", argument.getShipReferenceNumber());
		writeElement("VoyageNumber", argument.getVoyageNumber());
		writeElement("ShipName", argument.getShipName());	    
		writeElement("ShippCallSign", argument.getShipCallSign());  
		writeElement("ShipOwner", argument.getShipOwner());
		writeElement("ArrivalDate", argument.getArrivalDate());    //EI20120420
		writeElement("DepartureDate", argument.getDepartureDate()); //EI20120420
		writeElement("LoadingPort", argument.getLoadingPort());
		writeElement("DischargePort", argument.getDischargePort());
		writeElement("EndPort", argument.getFinalPort());
		writeElement("DestinationPlaceCode", argument.getDestinationPlaceCode());
		writeElement("DestinationPlaceName", argument.getDestinationPlaceName());
		writeElement("IMONumber", argument.getImoNumber());
		
		closeElement();

	}
	 	 
	public void writePostCarriage(PostCarriage argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		openElement("PostCarriage");
			writeElement("TransportType", argument.getTransportType());
			writeElement("CallSign", argument.getCallSign());
			writeElement("TransportationNumber", argument.getTransportationNumber());
		closeElement();
	}

	public void writePreCarriage(PreCarriage argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		openElement("PreCarriage");
			writeElement("TransportType", argument.getTransportType());
			writeElement("CallSign", argument.getCallSign());
			writeElement("TransportationNumber", argument.getTransportationNumber());
			writeElement("ExpectedDeliveryDate", argument.getExpectedDeliveryDate());	    
			writeElement("Annotation", argument.getAnnotation());  
			writeElement("Forwarder", argument.getForwarder());
			writeElement("ForwarderCode", argument.getForwarderCode());
		closeElement();
	}		

	public void writeAdditionInformation(AdditionalData argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}		
		openElement("AdditionInformation");
		//bookingNumber
			writeElement("DirectTakeover", argument.getDirectTakeover());
			writeElement("BookingNumber", argument.getBookingNumber());   //EI20120423
			writeElement("BillOfLadingNumber", argument.getBillOfLadingNumber());
			writeElement("Password", argument.getPassword());
			writeElement("InvoiceNote", argument.getInvoiceNote());
		closeElement();
	}
	
	public void writeConsolidatedContainer(ConsolidatedContainer argument)
			throws XMLStreamException {

		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		openElement("ConsolidatedContainer");
			writeElement("SequenceNumber", argument.getSequenceNumber());
			writeElement("PortReference", argument.getPortReference());
			writeElement("LadingFlag", argument.getLadingFlag());		
		closeElement();		
	}
	
	public void writeGoodsItem(GoodsItem argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		openElement("GoodsItem");
			writeElement("ItemNumber", argument.getItemNumber());
			writeElement("CustomsDeclarationType", argument.getCustomsDeclarationType());
			writeElement("ContainerNumber", argument.getContainerNumber());
			writeElement("HandlingMode", argument.getHandlingMode());
			writeElement("TarifCode", argument.getTarifCode());
			writeElement("TruckNumber", argument.getTruckNumber());
			writeElement("TransportationNumber", argument.getTransportationNumber());
			writeElement("TransportationAccesoryNumber", argument.getTransportationAccesoryNumber());
			writeGoodsLevel("FirstGoodsLevel", argument.getFirstGoodsLevel());
			if (argument.getSecondGoodsLevelList() != null) {
           	 	for (GoodsLevel level2 : argument.getSecondGoodsLevelList()) {
           	 	writeGoodsLevel("SecondGoodsLevel", level2);
           	 	}
            }
		closeElement();
	}
	
	public void writeGoodsLevel(String tag, GoodsLevel argument) throws XMLStreamException {
		if (argument == null) {
    	    return;
    	}
    	if (argument.isEmpty()) {
    		return;
    	}
		openElement(tag);				
			writeElement("SequenceNumber", argument.getSequenceNumber());
			writeElement("Quantity", argument.getQuantity());
			//EI20130513: writeElement("PackingType", argument.getPackingType());
			writeCodeElement("PackingType", argument.getPackingType(), "KCX0066"); //EI20130513
			writeElement("GrossMass", argument.getGrossMass());  
			writeElement("Volume", argument.getVolume());
			writeElement("Description", argument.getDescription());
			writeElement("Marks", argument.getMarks());			
			writeElement("ItemRemark", argument.getItemRemark());
			writeMrnLst(argument.getMrnStatementList()); 
			writeEADocument(argument.getEADocument());			
			writeDangerousGoods(argument.getDangerousGoods()); 
			
		closeElement();
	}
	
	public void writeMrnLst(List<MrnStatement>  list) throws XMLStreamException {
		if (list == null) {
    	    return;
    	}
    	if (list.isEmpty()) {
    		return;
    	}
    	if (list.get(0) == null) {
    		return;
    	}
		openElement("MrnStatement");
			writeElement("MRN", list.get(0).getMrn());		    		
			writeElement("MRNComplete", list.get(0).getMrnComplete());	
			writeElement("ReducedGoodsFlag", list.get(0).getReducedGoodsFlag());      //EI20120515
			if (list.get(0).getMrnPositionList() != null) {                          //EI20120515
				for (MrnPosition mrnpos : list.get(0).getMrnPositionList()) {
					if (mrnpos != null && !Utils.isStringEmpty(mrnpos.getPositionNumber())) {
						openElement("MrnPosition");
							writeElement("PositionNumber", mrnpos.getPositionNumber());   
							writeElement("MrnPositionComplete", mrnpos.getMrnPositionComplete());   
							writeElement("MrnPositionMissing", mrnpos.getMrnPositionMissing());   
							writeElement("ReducedGoodsFlag", mrnpos.getReducedGoodsFlag());   
							writeElement("NetMass", mrnpos.getNetMass());   
							writeElement("GrossMass", mrnpos.getGrossMass());   							
							if (mrnpos.getPackageIdList() != null) {
								for (String packageId : mrnpos.getPackageIdList()) {
									writeElement("PackageId", packageId);  
								}
							}
						closeElement();
					}
				}
			}
			writeElement("DestinationCountry", list.get(0).getDestinationCountry());   //EI20120515
			writeElement("DispatchCountry", list.get(0).getDispatchCountry());         //EI20120515			
		closeElement();
	 
	}
	public void writeEADocument(EADocument argument)  throws XMLStreamException {
		if (argument == null) {
    	    return;
    	}
    	if (argument.isEmpty()) {
    		return;
    	}
		openElement("EADocument");	
		writeElement("EANumber", argument.getEaNumber());
		//EI20140121:writeElement("DispatchMrn", argument.getDispatchMrn());
		writeElement("DispatchMRN", argument.getDispatchMrn()); //EI20140121
		writeElement("CustomsProcedure", argument.getCustomsProcedure());
		writeElement("SimplificationFlag", argument.getSimplificationFlag());
		writeElement("SimplificationCode", argument.getSimplificationCode());
		writeElement("CustomsOfficeExport", argument.getCustomsOfficeExport());
		writeElement("AuthorizationNumber", argument.getAuthorizationNumber());
		writeElement("PreviousDocument", argument.getPreviousDocument());
		writeElement("DestinationCountry", argument.getDestinationCountry());
		writeElement("DispatchCountry", argument.getDispatchCountry());
		
		writeParty("Declarant", argument.getDeclarant());
		writeParty("Consignor", argument.getConsignor());
		writeParty("Consignee", argument.getConsignee());
		writeParty("Notify", argument.getNotify());
		
		writeElement("MarketOrderFlag", argument.getMarketOrderFlag());
		writeElement("ValueGreater1000Flag", argument.getValueGreater1000Flag());
		
		writeElement("SumaReferenceNumber", argument.getSumaReferenceNumber());
		writeElement("Reference30A", argument.getReference30A());		
		writeElement("SumaSimplificationReason", argument.getSumaSimplificationReason());
		writeElement("OfficialStatement", argument.getOfficialStatement());
		if (argument.getPositionList() != null) {
			for (EAPosition eapos : argument.getPositionList()) {
				if (eapos != null) {
				openElement("EAPosition");	
				writeElement("EAPositionNumber", eapos.getEaPositionNumber());
				writeElement("TarifCode", eapos.getTarifCode());
				writeElement("Description", eapos.getDescription());
				writeElement("GrossMass", eapos.getGrossMass());
				writeElement("NetMass", eapos.getNetMass());
				writeElement("ProcedureCode", eapos.getProcedureCode());
				if (eapos.getAnnotationList() != null) {
					for (String annotation : eapos.getAnnotationList()) {						
						writeElement("Annotation", annotation);						
					}
				}
				closeElement();
				}
			}
		}
		closeElement();
	}

	public void writeDescriptionList(List<String>  list) throws XMLStreamException {
		if (list == null) {
    	    return;
    	}
    	if (list.isEmpty()) {
    		return;
    	}
    	for (String txt : list) {    		
    		writeElement("Description", txt);    	
    	}
	}
	public void writeDangerousGoods(DangerousGoods  argument) throws XMLStreamException {
		if (argument == null) {
    	    return;
    	}
    	if (argument.isEmpty()) {
    		return;
    	}
    	if (argument.getSeaTransportDetails() == null) {
    	    return;
    	}
		openElement("DangerousGoods");		
			writeElement("IMDGClass", argument.getImdgClass());  
			writeElement("UNNumber", argument.getUnNumber());
			writeElement("TechnicalSpecification", argument.getTechnicalSpecification());
			writeElement("DangerDescription", argument.getDangerDescription());			
			writeElement("DangerReleaser", argument.getDangerReleaser());  
			writeElement("Annotations", argument.getAnnotations());	
			writeElement("MarinePollutantFlag", argument.getMarinePollutantFlag());
			writeElement("NetWeight", argument.getNetWeight());
			writeElement("NetWeightExplosive", argument.getNetWeightExplosive());
			writeElement("ExplosiveGoodsCertificateFlag", argument.getExplosiveGoodsCertificateFlag());
			writeElement("DangerToleranceFlag", argument.getDangerToleranceFlag());
			//EI20130821: writeElement("PackagingSafetyGroup", argument.getPackagingSafetyGroup());
			//EI20130821: writeElement("PackagingType", argument.getPackagingType());
			//EI20130820: writeElement("LimitetQuantitiesCode", argument.getLimitetQuantitiesCode());
			writeElement("PackingSafetyGroup", argument.getPackagingSafetyGroup());   //EI20130821			
			//EI20140319: writeElement("PackingType", argument.getPackagingType()); 				//EI20130821
			writeCodeElement("PackingType", argument.getPackagingType(), "KCX0066"); //EI20140319	
			writeElement("LimitedQuantitiesCode", argument.getLimitetQuantitiesCode()); //EI20130820
			writeElement("ExpectedQuantitiesCode", argument.getExpectedQuantitiesCode()); //EI20130820
			writeElement("Flashpoint", argument.getFlashpoint());
			writeElement("FlashpointQualifier", argument.getFlashpointQualifier());
			writeElement("UnNumber", argument.getUnNumber());		
			if (argument.getSeaTransportDetails() != null && !argument.getSeaTransportDetails().isEmpty()) {
				openElement("SeaTransportDetails");	
				writeElement("WatterPollutionClass", argument.getSeaTransportDetails().getWatterPollutionClass());  
				writeElement("EmsNumber", argument.getSeaTransportDetails().getEmsNumber());	
				writeElement("EmsSecondNumber", argument.getSeaTransportDetails().getEmsSecondNumber());		
				writeElement("MFAG1", argument.getSeaTransportDetails().getMfag1());  
				writeElement("MFAG1", argument.getSeaTransportDetails().getMfag2());				
				writeElement("DangerLabel1", argument.getSeaTransportDetails().getDangerLabel1());
				writeElement("DangerLabel2", argument.getSeaTransportDetails().getDangerLabel2());
				writeElement("DangerLabel3", argument.getSeaTransportDetails().getDangerLabel3());
				writeElement("StuffingCategory", argument.getSeaTransportDetails().getStuffingCategory());  
				writeElement("StuffingDescription", argument.getSeaTransportDetails().getStuffingDescription());
				closeElement();
			}
			if (argument.getLandTransportDetails() != null && !argument.getLandTransportDetails().isEmpty()) {
				openElement("LandTransportDetails");	
				writeElement("LandTransportDangerFlag", argument.getLandTransportDetails().getLandTransportDangerFlag());  
				writeElement("TransportationRegulationsCode", argument.getLandTransportDetails().getTransportationRegulationsCode());				
				writeElement("TransportationRegulationsNumber", argument.getLandTransportDetails().getTransportationRegulationsNumber());
				writeElement("UNNumberForLandTransport", argument.getLandTransportDetails().getUNNumberForLandTransport());  
				writeElement("AnnotationsForLandTransport", argument.getLandTransportDetails().getAnnotationsForLandTransport());				
				writeElement("KemmlerNumber", argument.getLandTransportDetails().getKemmlerNumber());
				writeElement("GGVSClass", argument.getLandTransportDetails().getGGVSClass());
				writeElement("GGVSNumber", argument.getLandTransportDetails().getGGVSNumber());			
				closeElement();
			}
			if (argument.getRadioactiveGoodsDetails() != null && !argument.getRadioactiveGoodsDetails().isEmpty()) {
				openElement("RadioactiveGoodsDetails");	
				writeElement("UNNumber", argument.getRadioactiveGoodsDetails().getUnNumber());  
				//EI20130821: writeElement("Cathegory", argument.getRadioactiveGoodsDetails().getCathegory());				
				//EI20130821: writeElement("PackagingType", argument.getRadioactiveGoodsDetails().getPackagingType());
				writeElement("Category", argument.getRadioactiveGoodsDetails().getCathegory());	
				//EI20140319: writeElement("PackingType", argument.getRadioactiveGoodsDetails().getPackagingType());  //EI20130821:
				writeCodeElement("PackingType", argument.getPackagingType(), "KCX0066"); //EI20140319		
				writeElement("RadioactivityLevel", argument.getRadioactiveGoodsDetails().getRadioactivityLevel());  
				writeElement("RadioactivityQualifier", argument.getRadioactiveGoodsDetails().getRadioactivityQualifier());				
				writeElement("BfSNumber", argument.getRadioactiveGoodsDetails().getBfSNumber());
				writeElement("TransportIndex", argument.getRadioactiveGoodsDetails().getTransportIndex());
				writeElement("CriticalHazardIndex", argument.getRadioactiveGoodsDetails().getCriticalHazardIndex());			
				closeElement();
			}
			
		closeElement();	
	}
	
	public void writeContainer(Container argument) throws XMLStreamException {
		if (argument == null) {
    	    return;
    	}
    	if (argument.isEmpty()) {
    		return;
    	}
		openElement("Container");			
			 writeElement("ContainerNumber", argument.getContainerNumber());			
			 writeElement("Type", argument.getType());
			 writeElement("Owner", argument.getOwner());	    
			 writeElement("LadingFlag", argument.getLadingFlag());  
			 writeElement("GrossMass", argument.getGrossMass());
			 writeElement("TareMass", argument.getTareMass());	
			 writeElement("NetMass", argument.getNetMass());
			 writeElement("TemperaturUnit", argument.getTemperaturUnit());	
			 writeElement("MaxPermitedTemperatur", argument.getMaxPermitedTemperatur()); 
			 writeElement("MinPermitedTemperatur", argument.getMinPermitedTemperatur());	
			 writeElement("TemperaturMode", argument.getTemperaturMode());
             if (argument.getSealNumberList() != null) {
            	 for (String seal : argument.getSealNumberList()) {
            		 if (seal != null) {
            			 writeElement("SealNumber", seal);   //EI20120423
            		 }
            	 }
             }
			 writeElement("BookingNumber", argument.getBookingNumber());   
			 writeElement("CodeOfGoods", argument.getCodeOfGoods());   //EI20140108
			 writeElement("Description", argument.getLadingDescription());

		closeElement();
	}
	
	public void writeText(String tag, Text text) throws XMLStreamException {
		if (text == null) {
    	    return;
    	}
    	if (text.isEmpty()) {
    		return;
    	}
					
		writeElement(tag, text.getText());
	}
	
	public String getColliArtFromJOB(String colliArt) {
		// nur die Codes erfasst die nach abschneiden der 3-ten stelle sich von Zabis-Cdes unterscheiden
		
		if (Utils.isStringEmpty(colliArt)) {
			return "";
		}
			if (colliArt.equals("BAG")) {   //TODO-IWA: jetzt fuer Test sind die Codes hier, danach per DB.kcx_codes
				colliArt = "BG";            
			}                                
			if (colliArt.equals("BAL")) {
				colliArt = "BL";
			}
			if (colliArt.equals("BBL")) {
				colliArt = "2C";
			}
			if (colliArt.equals("BDL")) {
				colliArt = "BE";
			}			
			if (colliArt.equals("CAN")) {
				colliArt = "CA";
			}
			if (colliArt.equals("CAS")) {
				colliArt = "CS";
			}
			if (colliArt.equals("CCS")) {
				colliArt = "CL";
			}
			if (colliArt.equals("CDP")) {
				colliArt = "PX";
			}
			if (colliArt.equals("CNT")) {
				colliArt = "CN";
			}
			if (colliArt.equals("COL")) {
				colliArt = "CL";
			}
			if (colliArt.equals("CRT")) {
				colliArt = "CR";
			}
			if (colliArt.equals("CTN")) {
				colliArt = "CT";
			}
			if (colliArt.equals("CYL")) {
				colliArt = "CY";
			}
			if (colliArt.equals("DRM")) {
				colliArt = "DR";
			}
			if (colliArt.equals("PCS")) {
				//colliArt = "CL";
				colliArt = "PK";  //EI20130513
			}	
			if (colliArt.equals("PCL")) {
				colliArt = "ZZ";
			}
			if (colliArt.equals("PCP")) {
				colliArt = "PX";
			}
			if (colliArt.equals("PKG")) {
				colliArt = "PK";
			}			
			if (colliArt.equals("PLT")) {
				colliArt = "PB";
			}					
			if (colliArt.equals("PRB")) {
				colliArt = "BG";
			}
			if (colliArt.equals("PRC")) {
				colliArt = "CS";
			}
			if (colliArt.equals("PRP")) {
				colliArt = "PB";
			}
			if (colliArt.equals("RCK")) {
				colliArt = "RK";
			}
			if (colliArt.equals("ROL")) {
				colliArt = "RL";
			}
			if (colliArt.equals("SAK")) {
				colliArt = "BG";
			}
			if (colliArt.equals("STC")) {
				colliArt = "SS";
			}
			if (colliArt.equals("UNP")) {
				colliArt = "CL";
			}	
			if (colliArt.equals("WDC")) {
				colliArt = "QP";
			}
			
			//EI20130419: return colliArt;
			if (colliArt.length() == 2) { //EI20130419 
				return colliArt;
			} else {
				return "";
			}	
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
	
	public String roundKffDecimalPlaces(String amount) {	 
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
	        BigDecimal newDec = new BigDecimal(d);
	        newDec = newDec.setScale(0, BigDecimal.ROUND_HALF_UP);
	        ret = Utils.removeDots(newDec.toString());
	    }
	    return ret; 
	}
	
	public String getCntNrFromJOB(String cntNr) {
		String ret = "";
		if (cntNr == null) {
			return "";
		}
		if (cntNr.length() > 6 && cntNr.substring(4, 5).equals(" ")) {
			String nr = cntNr.substring(0, 4) + cntNr.substring(5);
			ret = nr;
		} else {
			ret = cntNr;
		}
		return ret;
	}
	
}
