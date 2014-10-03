package com.kewill.kcx.component.mapping.formats.kids.ncts;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.common.SealNumber;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.common.SpecialMention;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSUnloadingPermission;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSUnloadingPermissionPos;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.AddressNCTS;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.CommodityCode;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Packages;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.PartyNCTS;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.SensitiveGoods;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageNCTS;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: BodyNCTSUnloadingPermissionKids<br>
 * Created		: 01.09.2010<br>
 * Description	: Contains message structure for NCTSUnloadingPermission KIDS. 
 * 
 * @author Paul Pagdanganan
 * @version 1.0.00
 */

public class BodyNCTSUnloadingPermissionKids extends KidsMessageNCTS {
	
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
				//EI29139221: writeElement("TotalCrossMass", message.getTotalCrossMass());
				writeElement("TotalGrossMass", message.getTotalGrossMass());
				//EI20111026: writeTIN("PrincipalTIN", message.getPrincipalTIN());
				writePartyTIN("PrincipalTIN", message.getPrincipalTIN());
				writeParty("Principal", message.getPrincipal());
				//EI20111026: writeTIN("ConsignorTIN", message.getConsignorTIN());
				writePartyTIN("ConsignorTIN", message.getConsignorTIN());
				writeParty("Consignor", message.getConsignor());
				//EI20111026: writeTIN("ConsigneeTIN", message.getConsigneeTIN());	
				writePartyTIN("ConsigneeTIN", message.getConsigneeTIN());				
				writeParty("Consignee", message.getConsignee());
				//EI20111026: writeTIN("DestinationTraderTIN", message.getDestinationTraderTIN());
				writePartyTIN("DestinationTraderTIN", message.getDestinationTraderTIN());
				writeParty("DestinationTrader", message.getDestinationTrader());
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
			
			writePartyTIN("ConsignorTIN", argument.getConsignorTIN());
			writeParty("Consignor", argument.getConsignor());
			writePartyTIN("ConsigneeTIN", argument.getConsigneeTIN());
			writeParty("Consignee", argument.getConsignee());
		closeElement();
	}	
			
}
