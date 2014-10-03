package com.kewill.kcx.component.mapping.countries.de.suma70.kids2fss;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.MsgReExport;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.FlightDetails;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.GoodsItemReexport;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.suma.messages.V70.MsgSAK;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.TsSAK;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.TsSAP;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: Manifest<br>
 * Created		: 24.05.2013<br>
 * Description	: Mapping of KIDS ReExport to FSS SAK.
 * 
 * @author krzoska
 * @version 2.0.00
 */
public class MapReExportToSAK extends KidsMessage {
	
	private MsgReExport message;
	private MsgSAK msgSAK;
	
	public MapReExportToSAK(XMLEventReader parser, TsHead tsHead) throws XMLStreamException {		
		message = new MsgReExport(parser);
		msgSAK = new MsgSAK();		
		msgSAK.setHeadSubset(tsHead);
	}

	public String getMessage() {
    	String res = "";    	
    	    	
        try {         	
        	message.parse(HeaderType.KIDS);         	
            getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
                                 
            //msgSAK.getHeadSubset().setMsgid(getKidsHeader().getMessageID()); ist schon im converter
            msgSAK.setSakSubset(mapSAK());
           
            if (message.getGoodsItemList() != null) {            	
			   for (GoodsItemReexport item : message.getGoodsItemList()) {
				   if (item != null) {
					   msgSAK.addSapList(mapSAP(item));
				   }
			   }
            }
         
            res = msgSAK.getFssString();
           
            Utils.log("(ReExportToSAK getMessage) Msg = " + res);
		
	    } catch (FssException e) {	    	
	        e.printStackTrace();	        
	    }
		    
	    return res;
	}
	
	private TsSAK mapSAK() {
		TsSAK sak = new TsSAK();
		
		
		sak.setBeznr(message.getReferenceNumber());
		sak.setIdart(message.getReferenceIdentifier()); 
		//sak.setIdart(message.getTypeOfTransaction()); //TODO: ist TypeOfTransaction == ReferenceIdentifier???
		/*
		if (message.getTransport() != null) {			
			sak.setBefkz(message.getTransport().getIdentity());								
		} 
		*/		
		sak.setFltnum(this.getFormatedFlightIdentifier(message.getFlightDetails()));  //EI20131017
	
		sak.setElo(message.getPlaceOfUnloading());   
		sak.setVrfart(message.getProcedureType());  //Art des Verfahrens
		
		if (message.getAgent() != null && message.getAgent().getPartyTIN() != null) {
			sak.setVtrkdnr(message.getAgent().getPartyTIN().getCustomerIdentifier());
			sak.setVtreori(message.getAgent().getPartyTIN().getTIN());
			//sak.setVtrnlnr(message.getAgent().getPartyTIN().getBO()); TODO kein NL???
		}
		
		if (message.getHeaderExtensions() != null) {						
			sak.setKzbest(message.getHeaderExtensions().getConfirmationCode());  //Kennzeichen „Bestätigung
			sak.setKzflab(message.getHeaderExtensions().getFlightCompletionCode());  //Kennzeichen „Flugabschluss
		}
				
		
		if (message.getContact() != null) {
			sak.setSbnr(message.getContact().getIdentity());	
			sak.setSbname(message.getContact().getName());	
			sak.setSbdstl(message.getContact().getPosition());	
			sak.setSbtel(message.getContact().getPhoneNumber());	
		}	
		
		return sak;
	}
	
	private TsSAP mapSAP(GoodsItemReexport item) {
		if (item == null) {
			return null;
		}
		TsSAP sap = new TsSAP();
		
		
		sap.setBeznr(message.getReferenceNumber());
		sap.setPosnr(item.getItemNumber());
		if (item.getCustodianTIN() != null) {
			sap.setIdvwreori(item.getCustodianTIN().getTin());
			sap.setIdvwrkdnr(item.getCustodianTIN().getCustomerIdentifier());			
		}
		sap.setBesort(item.getDestinationPlace());
		if (item.getPackagesList() != null) {
			for (Packages pack : item.getPackagesList()) {
				if (pack != null) {					
					sap.setColanz(pack.getQuantity());						
				}
				break;  //in Zabis nur ein Pack pro SUP
			}		
		}	
		
		if (item.getReferencedSpecification() != null) {	//EI20130201
			sap.setKzawb(item.getReferencedSpecification().getTypeOfSpecificationID()); // Art des spezifischen Ordnungsbegriffes 
			sap.setAwbzzz(item.getReferencedSpecification().getSpecificationID()); // Spezifischer Ordnungsbegriff
		} 
		sap.setKzaend(item.getChangeFlag());
			
		if (item.getReference() != null) {
			sap.setAregnr(item.getReference().getRegistrationNumber());     // Registriernummer des Bezugsvorgangs
			sap.setAposnr(item.getReference().getItemNumber());     // Positionsnummer des Bezugs-Vorgangs
			if (item.getReference().getReferencedSpecification() != null) {
				sap.setIdkzawb(item.getReference().getReferencedSpecification().getTypeOfSpecificationID());
				sap.setIdawbzzz(item.getReference().getReferencedSpecification().getSpecificationID());
			}
			if (item.getReference().getCustodianTIN() != null) {
			sap.setIdvwreori(item.getReference().getCustodianTIN().getTin());
			sap.setIdvwrkdnr(item.getReference().getCustodianTIN().getCustomerIdentifier());
			}
		}
		if (item.getCustomsNotification() != null) {
			sap.setAsumamrn(item.getCustomsNotification().getContent());
			sap.setAsumaposnr(item.getCustomsNotification().getItemNumber());
		}
		
		return sap;	
	}

	private String getFormatedFlightIdentifier(FlightDetails argument) {
		if (argument == null) {
			return "";
		}
		if (Utils.isStringEmpty(argument.getFlightNumber())) {
			Utils.log("Kids2Fss REEXPORT: FlightNumber = LogisticTransportMovement.ID  is missing ");
			return "";
		}
		if (Utils.isStringEmpty(argument.getCarrierCode())) {
			Utils.log("Kids2Fss REEXPORT: CarrierCode = OnCarriageTransportMovement.CarrierParty.PrimaryID is missing ");
			return "";
		}
		if (Utils.isStringEmpty(argument.getNumberOfFlight())) {
			Utils.log("Kids2Fss REEXPORT: NumberOfFlight = OnCarriageTransportMovement.ID is missing ");
			return "";
		}			
		String flightNumber = argument.getFlightNumber();
		String carrierCode = argument.getCarrierCode();
		String numberOfFlight = argument.getNumberOfFlight();   
		String additionalCode = argument.getAdditionalQualifier();
		String date = argument.getDepartureDateTime();
		
		if (!flightNumber.contains(carrierCode)) {
			Utils.log("Kids2Fss REEXPORT: FlightNumber/CarrierCode not match: " + flightNumber + "/" + carrierCode);
			return "";
		}
		if (!flightNumber.contains(numberOfFlight)) {
			Utils.log("Kids2Fss REEXPORT: FlightID/FlightNumber not match: " + flightNumber + "/" + numberOfFlight);
			return "";
		}
		
		if (Utils.isStringEmpty(additionalCode)) {
			additionalCode = "";
		} else {
			additionalCode = additionalCode + " ";
		}
		if (date == null) {
			date = "";
		}
		String formatedDate = "";
		if (date.length() > 7 && date.startsWith("20")) {
			formatedDate =  date.substring(6, 8) + "." + date.substring(4, 6) + "." + date.substring(0, 4); //DD.MM.YYYY
		}
		return carrierCode + " " +  numberOfFlight + " " + additionalCode + formatedDate;
	}
}
