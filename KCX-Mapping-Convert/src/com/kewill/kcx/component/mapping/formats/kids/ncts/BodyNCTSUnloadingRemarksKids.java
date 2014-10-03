package com.kewill.kcx.component.mapping.formats.kids.ncts;

import javax.xml.stream.XMLStreamException;

import javax.xml.stream.XMLStreamWriter;


import com.kewill.kcx.component.mapping.countries.common.SealNumber;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSUnloadingRemarks;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSUnloadingRemarksPos;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.AddressNCTS;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.CommodityCode;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.NCTSTrader;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.EnRouteEvent;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Incident;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Packages;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.ResultsOfControl;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.SensitiveGoods;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.TransShipment;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Transport;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.UnloadingRemark;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageNCTS;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: BodyNCTSUnloadingRemarksKids<br>
 * Created		: 09.01.2010<br>
 * Description	: Contains message structure for NCTSUnloadingRemarks KIDS. 
 * 
 * @author Jude Eco
 * @version 1.0.00
 */

public class BodyNCTSUnloadingRemarksKids extends KidsMessageNCTS {
	private MsgNCTSUnloadingRemarks message;
	
	public BodyNCTSUnloadingRemarksKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
	
	public MsgNCTSUnloadingRemarks getMessage() {
		return message;
	}
	
	public void setMessage(MsgNCTSUnloadingRemarks argument) {
		this.message = argument;
	}
	
	public void writeBody() {
		try {
			openElement("soap:Body");
			openElement("UnloadingRemarks");
			openElement("GoodsDeclaration");
				writeElement("UCRNumber", message.getmRN());
				writeMeansOfTransportDeparture("MeansOfTransportDeparture", message.getTransportAtDeparture());
				writeElement("TotalNumberPositions", message.getTotalNumberOfItems());
				writeElement("TotalNumberPackages", message.getTotalNumberOfPackages());
				//EI20130221: writeElement("TotalCrossMass", message.getTotalGrossMass());
				writeElement("TotalGrossMass", message.getTotalGrossMass());
				writeDestinationTrader("DestinationTrader", message.getDestinationTrader());
				//EI20110929: TODO wollte mit den ueblichen ersetzen, geht aber nicht! weil der DestinationTrader 
				// vom Typ DestinationTrader ist???!!!
				//writePartyTIN("DestinationTraderTIN", message.getDestinationTrader().get);
				//writeParty("Consignor", message.getConsignor());
				writeElement("OfficeOfDeparture", message.getOfficeOfPresentation());
				writeUnloadingRemark(message.getUnloadingRemark());
				writeResultsOfControl(message.getResultsOfControl());
				writeElement("TotalNumberOfSeals", message.getTotalNumberOfSeals());
						
				if (message.getListSealNumbers() != null) {
					for (SealNumber sealNumber : message.getListSealNumbers()) {
	                    writeSealNumber(sealNumber);
	                }
				}						
				writeEnRouteEvent(message.getEnRouteEvent());
						
				if (message.getGoodsItemList() != null) {  //EI20110608
					for (MsgNCTSUnloadingRemarksPos item : message.getGoodsItemList()) {
						writeGoodsItem(item);
					}
				}
						
			closeElement();
			closeElement();
			closeElement();
		} catch (XMLStreamException e) {
	            e.printStackTrace();
	    }     
	}
	
	public void writeMeansOfTransportDeparture(String tag, Transport argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}    
    	if (argument.isEmpty())  {  		
    	    return;
    	}
    	
    	openElement(tag);             	                    	   
    		writeElement("TransportMode", argument.getMode());
	    	writeElement("TransportationNumber", argument.getIdentity());
	    	writeElement("TransportationCountry", argument.getsetNationality());
	    closeElement();
    }
	
	public void writeDestinationTrader(String tag, NCTSTrader argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}    
    	if (argument.isEmpty())  {  		
    	    return;
    	}
    	
    	openElement(tag);             	                    	   
    		writeElement("VATNumber", argument.getvATID());
	    	writeElement("ETNAddress", argument.geteTNAddress());
	    	writeAddr("Address", argument.getAddress());
	    closeElement();
    }
	
	public void writeAddr(String tag, AddressNCTS argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		
		openElement(tag);
			writeElement("Name", argument.getName());
			writeElement("Street", argument.getStreet());
			writeElement("HouseNumber", argument.getHouseNumber());
			writeElement("City", argument.getCity());
			writeElement("PostalCode", argument.getPostalCode());
			writeElement("Country", argument.getCountry());
		closeElement();
	}
	
	public void writeUnloadingRemark(UnloadingRemark argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}    
    	if (argument.isEmpty())  {  		
    	    return;
    	}    	
    	openElement("UnloadingRemark");             	                    	   
    		writeElement("StateOfSealsOk", argument.getStateOfSealsOk());
	    	writeElement("Remarks", argument.getUnloadingRemark()); //EE20100914
	    	writeElement("Conform", argument.getConform());
	    	writeElement("UnloadingCompletion", argument.getUnloadingCompletion());	    		    	
	    	writeFormattedDateOrTime("UnloadingDate", argument.getUnloadingDate(), 
	    			argument.getUnloadingDateFormat(),	EFormat.KIDS_Date);	    	
	    closeElement();
    }
	
	public void writeResultsOfControl(ResultsOfControl argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}    
    	if (argument.isEmpty())  {  		
    	    return;
    	}
    	
    	openElement("ResultsOfControl");             	                    	   
    		writeElement("Description", argument.getDescription());
    		writeElement("ControlIndicator", argument.getControlIndicator());
    		writeElement("AttributePointer", argument.getAttributePointer());
    		writeElement("CorrectedValue", argument.getCorrectedValue());
	    closeElement();
    }
		
	public void writeGoodsItem(MsgNCTSUnloadingRemarksPos argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}    
    	if (argument.isEmpty())  {  		
    	    return;
    	}    	
    	openElement("GoodsItem");             	                    	   
    		writeElement("ItemNumber", argument.getItemNumber());
    		writeCommodityCode(argument.getCommodityCode());
    		writeElement("Description", argument.getGoodsDescription());
    		writeElement("NetMass", argument.getNetMass());
    		writeElement("GrossMass", argument.getGrossMass());
    		writeElement("MissingLineIndicator", argument.getMissingLineFlag());
    		
    		if (argument.getListProducedDocuments() != null) {
	    		for (Document document : argument.getListProducedDocuments()) {
	    			//TODO-EI20110929 : in writeDocument(..) ist noch zusaetzlich Qualifier, ist es falsch???
	    			//writeProducedDocuments("Document", document);
	    			writeDocument(document);   //EI20110929
	    		}
    		}    		
    		writeResultsOfControl(argument.getResultsOfControl());
    		//EI20110929: writeContainerNumberList(argument.getContainer());   //EE20100914
    		writeContainers(argument.getContainer());   //E120110929
    		
    		if (argument.getListPackages() != null) {
	    		for (Packages packages : argument.getListPackages()) {
	    			writePackage(packages);
	    		}
    		}    		
    		if (argument.getSensitiveGoodsList() != null) {
	    		for (SensitiveGoods sGICodes : argument.getSensitiveGoodsList()) {
	    			writeSensitiveGoods(sGICodes);
	    		}
    		}    		
	    closeElement();
    }	
	
	public void writeProducedDocuments(String tag, Document argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}    
    	if (argument.isEmpty())  {  		
    	    return;
    	}
    	
    	openElement(tag);
    		writeElement("Type", argument.getType());
    		writeElement("Reference", argument.getReference());
    		writeElement("AdditionalInformation", argument.getAdditionalInformation());    	
	    	writeFormattedDateOrTime("IssueDate", argument.getIssueDate(), 
	    			argument.getIssueDateFormat(), EFormat.KIDS_Date);	    	
	    closeElement();
    }
	
	
}
