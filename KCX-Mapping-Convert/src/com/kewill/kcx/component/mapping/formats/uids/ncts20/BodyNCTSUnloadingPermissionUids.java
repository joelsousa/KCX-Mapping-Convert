package com.kewill.kcx.component.mapping.formats.uids.ncts20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ncts20.msg.MsgNCTSUnloadingPermission;
import com.kewill.kcx.component.mapping.countries.de.ncts20.msg.MsgNCTSUnloadingPermissionPos;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageNCTS20;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: NCTS<br>
 * Created		: 02.02.2013<br>
 * Description	: BodyNCTSUnloadingPermissionUids. 
 * 
 * @author iwaniuk
 * @version 4.1.00
 */

public class BodyNCTSUnloadingPermissionUids extends UidsMessageNCTS20 {
	
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
			writePartyNCTS("Principal", message.getPrincipal());	
			writeElement("CarnetID", message.getCarnetID());   //EI20130325
			writePartyNCTS("Consignor", message.getConsignor());			
			writePartyNCTS("Consignee", message.getConsignee());			
			writePartyNCTS("DestinationTrader", message.getDestinationTrader());
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
			if (argument.getCommodityCode() != null) {   
				writeElement("CommodityCodeKN8", argument.getCommodityCode().getTarifCode());
			}
			writeElement("GoodsDescription", argument.getDescription());
			writeElement("GrossMass", argument.getGrossMass());
			writeElement("NetMass", argument.getNetMass());
			writeElement("MissingLineFlag", argument.getMissingLineIndicator());
			writeElement("CountryOfDispatch", argument.getDispatchCountry());
			writeElement("CountryOfDestination", argument.getDestinationCountry());			
			writePartyNCTS("Consignee", argument.getConsignee());			
			writePartyNCTS("Consignor",	argument.getConsignor());
			writeSpecialMentionsList(argument.getSpecialMentionList());							
			writeProducedDocumentsList(argument.getDocumentList());			
			writeContainerList(argument.getContainers());			
			writePackagesList(argument.getPackageList());						
			writeSGICodesList(argument.getSensitiveGoodsList());
						
		closeElement();
	}	
}
