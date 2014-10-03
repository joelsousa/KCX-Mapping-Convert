package com.kewill.kcx.component.mapping.formats.uids.ncts;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSUnloadingPermission;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSUnloadingPermissionPos;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.NCTSTrader;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.PartyNCTS;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageNCTS;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: BodyNCTSUnloadingPermissionUids<br>
 * Created		: 02.09.2010<br>
 * Description	: Contains message structure for NCTSUnloadingPermission UIDS. 
 * 
 * @author Paul Pagdanganan
 * @version 1.0.00
 */

public class BodyNCTSUnloadingPermissionUids extends UidsMessageNCTS {
	
	private MsgNCTSUnloadingPermission message;
	
	public BodyNCTSUnloadingPermissionUids(XMLStreamWriter writer) {
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
			openElement("Submit");
			openElement("NCTS");
			openElement("NCTSUnloadingPermission");
			
			writeElement("LocalReferenceNumber", message.getReferenceNumber());
			writeElement("MRN", message.getUcrNumber());
			writeElement("TypeOfDeclaration", message.getTypeOfDeclaration());
			writeElement("PlaceOfTransfer", message.getPlaceOfTransfer());
			writeElement("CountryOfDestination", message.getDestinationCountry());
			writeElement("CountryOfDispatch", message.getDispatchCountry());
			writeTransport("TransportAtDeparture", message.getMeansOfTransportDeparture());
			writeElement("ContainerIndicator", message.getTransportInContainer());			
			writeFormattedDateOrTime("DateOfAcceptance", message.getAcceptanceTime(),
							message.getAcceptanceTimeFormat(), EFormat.ST_Date);						
			writeElement("TotalNumberOfItems", message.getTotalNumberPositions());
			writeElement("TotalNumberOfPackages", message.getTotalNumberPackages());
			writeElement("TotalGrossMass", message.getTotalGrossMass());
			writeElement("TotalNumberOfSeals", message.getTotalNumberSeals());
			writeSealNumberList(message.getSealNumbersList());
			//EI20111026: writeParty("Principal", message.getPrincipalTIN(), message.getPrincipal());
			writePartyNCTS("Principal", message.getPrincipalTIN(), message.getPrincipal());
			//EI20111026: writeParty("Consignor", message.getConsignorTIN(), message.getConsignor());
			writePartyNCTS("Consignor", message.getConsignorTIN(), message.getConsignor());
			//EI20111026: writeParty("Consignee", message.getConsigneeTIN(), message.getConsignee());
			writePartyNCTS("Consignee", message.getConsigneeTIN(), message.getConsignee());
			//EI: writeParty("DestinationTrader", message.getDestinationTraderTIN(), message.getDestinationTrader());
			writePartyNCTS("DestinationTrader", message.getDestinationTraderTIN(), message.getDestinationTrader());
			writeElement("OfficeOfDeparture", message.getOfficeOfDeparture());
			writeElement("OfficeOfPresentation", message.getOfficeOfPresentation());
			writeElement("OfficeOfDestination", message.getOfficeOfDestination());
			writeElement("ContinueUnloading", message.getContinueUnloading());			
			writeFormattedDateOrTime("PresentationOfConsignment", message.getEndOfPresentationDate(),
						message.getEndOfPresentationDateFormat(), EFormat.ST_Date);
			
			if (message.getGoodsItemList() != null) {
				for (MsgNCTSUnloadingPermissionPos item : 
					message.getGoodsItemList()) {
                	writeGoodsItem(item);
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
	
	public void writeGoodsItem(MsgNCTSUnloadingPermissionPos argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		openElement("GoodsItem");
			writeElement("ItemNumber", argument.getItemNumber());
			if (argument.getCommodityCode() != null) {   //EI20110523
				writeElement("CommodityCodeKN8", argument.getCommodityCode().getTarifCode());
			}
			writeElement("GoodsDescription", argument.getDescription());
			writeElement("GrossMass", argument.getGrossMass());
			writeElement("NetMass", argument.getNetMass());
			writeElement("MissingLineFlag", argument.getMissingLineIndicator());
			writeElement("CountryOfDispatch", argument.getDispatchCountry());
			writeElement("CountryOfDestination", argument.getDestinationCountry());
			//EI20111026: writeParty("Consignee",	argument.getConsigneeTIN(), argument.getConsignee());
			writePartyNCTS("Consignee",	argument.getConsigneeTIN(), argument.getConsignee());
			//EI20111026: writeParty("Consignor",	argument.getConsignorTIN(), argument.getConsignor());
			writePartyNCTS("Consignor",	argument.getConsignorTIN(), argument.getConsignor());
			writeSpecialMentionsList(argument.getSpecialMentionList());							
			writeProducedDocumentsList(argument.getDocumentList());			
			writeContainerList(argument.getContainers());			
			writePackagesList(argument.getPackageList());						
			writeSGICodesList(argument.getSensitiveGoodsList());
				
			
		closeElement();
	}
	/* EI20111026: replaced with writePartyNCTS(...)	
	public void writeParty(String tag, TIN tin, PartyNCTS party) throws XMLStreamException {
		if (tin == null || party == null) {
			return;
		}
		
		if (tin.isEmpty() && party.isEmpty()) {
			return;
		}
		
		openElement(tag);
			writeElement("CustomerID", tin.getCustomerIdentifier());
			writeElement("ETNAddress", party.getETNAddress());
			writeElement("TIN", tin.getTIN());
			writeElement("IsTINGermanApprovalNumber", tin.getIsTINGermanApprovalNumber());
			writeElement("VATID", party.getVATNumber());
			writeAddress(party.getAddress());
		closeElement();
	}	
	*/	
}
