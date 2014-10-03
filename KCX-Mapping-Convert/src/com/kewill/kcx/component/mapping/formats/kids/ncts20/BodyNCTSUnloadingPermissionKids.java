package com.kewill.kcx.component.mapping.formats.kids.ncts20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.common.SealNumber;
import com.kewill.kcx.component.mapping.countries.common.SpecialMention;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Packages;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.SensitiveGoods;
import com.kewill.kcx.component.mapping.countries.de.ncts20.msg.MsgNCTSUnloadingPermission;
import com.kewill.kcx.component.mapping.countries.de.ncts20.msg.MsgNCTSUnloadingPermissionPos;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageNCTS20;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: NCTS<br>
 * Created		: 01.02.2013<br>
 * Description	: Contains message structure for NCTSUnloadingPermission KIDS. 
 * 
 * @author iwaniuk
 * @version 4.1.00
 */

public class BodyNCTSUnloadingPermissionKids extends KidsMessageNCTS20 {
	
	private MsgNCTSUnloadingPermission message;

	public BodyNCTSUnloadingPermissionKids(XMLStreamWriter writer) {
		this.writer = writer;
	}

	public MsgNCTSUnloadingPermission getMsgNCTSMRNAllocated() {
		return message;
	}

	public void setMsgNCTSUnloadingPermission(MsgNCTSUnloadingPermission message) {
		this.message = message;
	}
	
	public void writeBody() {
		try {
			openElement("soap:Body");
			openElement("UnloadingPermission");
			openElement("GoodsDeclaration");
				writeElement("ReferenceNumber", message.getReferenceNumber());
				writeElement("UCRNumber", message.getUcrNumber());
				writeElement("TypeOfDeclaration", message.getTypeOfDeclaration());
				writeElement("PlaceOfTransfer", message.getPlaceOfTransfer());
				writeElement("DestinationCountry", message.getDestinationCountry());
				writeElement("DispatchCountry", message.getDispatchCountry());
				writeTransport("MeansOfTransportDeparture", message.getMeansOfTransportDeparture());
				writeElement("TransportInContainer", message.getTransportInContainer());					
				writeFormattedDateOrTime("AcceptanceTime", message.getAcceptanceTime(),
							 message.getAcceptanceTimeFormat(), EFormat.KIDS_DateTime);
				writeElement("TotalNumberPositions", message.getTotalNumberPositions());
				writeElement("TotalNumberPackages", message.getTotalNumberPackages());
				writeElement("TotalGrossMass", message.getTotalGrossMass());
				if (message.getPrincipal() != null) {
					writePartyTIN("PrincipalTIN", message.getPrincipal().getPartyTIN());
					writeParty("Principal", message.getPrincipal());
				}	
				writeElement("CarnetID", message.getCarnetID());   //EI20130325
				if (message.getConsignor() != null) {
					writePartyTIN("ConsignorTIN", message.getConsignor().getPartyTIN());
					writeParty("Consignor", message.getConsignor());
				}				
				if (message.getConsignee() != null) {
					writePartyTIN("ConsigneeTIN", message.getConsignee().getPartyTIN());	
					writeParty("Consignee", message.getConsignee());
				}				
				if (message.getDestinationTrader() != null) {
					writePartyTIN("DestinationTraderTIN", message.getDestinationTrader().getPartyTIN());
					writeParty("DestinationTrader", message.getDestinationTrader());
				}				
				writeElement("OfficeOfDeparture", message.getOfficeOfDeparture());
				writeElement("OfficeOfPresentation", message.getOfficeOfPresentation());
				writeElement("OfficeOfDestination", message.getOfficeOfDestination());
				writeFormattedDateOrTime("EndOfPresentationDate", message.getEndOfPresentationDate(),
							message.getEndOfPresentationDateFormat(), EFormat.KIDS_Date);
				writeElement("ContinueUnlaoding", message.getContinueUnloading());
				writeElement("TotalNumberSeals", message.getTotalNumberSeals());
						
				if (message.getSealNumbersList() != null) {
					for (SealNumber sealNumber : message.getSealNumbersList()) {
	                    writeSealNumber(sealNumber);
	                }
				}
						
				if (message.getGoodsItemList() != null) {
					for (MsgNCTSUnloadingPermissionPos item : message.getGoodsItemList()) {
	                    writeGoodsItem(item);
	                }
				}
			closeElement();
			closeElement();
			closeElement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void writeGoodsItem(MsgNCTSUnloadingPermissionPos argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		openElement("GoodsItem");
			writeElement("ItemNumber", argument.getItemNumber());
			writeCommodityCode(argument.getCommodityCode());
			writeElement("TypeOfDeclaration", argument.getTypeOfDeclaration());
			writeElement("Description", argument.getDescription());
			writeElement("NetMass", argument.getNetMass());
			writeElement("GrossMass", argument.getGrossMass());
			writeElement("MissingLineIndicator", argument.getMissingLineIndicator());
			writeElement("DestinationCountry", argument.getDestinationCountry());
			writeElement("DispatchCountry", argument.getDispatchCountry());
			
			if (argument.getSpecialMentionList() != null) {
				for (SpecialMention specialMention : argument.getSpecialMentionList()) {
	        		writeSpecialMention(specialMention);
	        	}
			}			
			if (argument.getDocumentList() != null) {
				for (Document document : argument.getDocumentList()) {
	        		writeDocument(document);
	        	}
			}			
			writeContainers(argument.getContainers());			
			if (argument.getPackageList() != null) {
				for (Packages packages : argument.getPackageList()) {
	        		writePackage(packages);
	        	}
			}			
			if (argument.getSensitiveGoodsList() != null) {
				for (SensitiveGoods sensitiveGoods : argument.getSensitiveGoodsList()) {
					writeSensitiveGoods(sensitiveGoods);
	        	}
			}
			if (argument.getConsignor() != null) {
				writePartyTIN("ConsignorTIN", argument.getConsignor().getPartyTIN());
				writeParty("Consignor", argument.getConsignor());
			}			
			if (argument.getConsignee() != null) {
				writePartyTIN("ConsigneeTIN", argument.getConsignee().getPartyTIN());
				writeParty("Consignee", argument.getConsignee());
			}
			
		closeElement();
	}	
			
}
