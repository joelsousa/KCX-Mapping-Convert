package com.kewill.kcx.component.mapping.formats.ireland;

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpDat;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpDatPos;
import com.kewill.kcx.component.mapping.countries.ie.IrelandMessage;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: Export Kids to Ireland<br>
 * Created		: 26.05.2014<br>
 * Description	: Body of Ireland-Format of ExportDeclaration. 
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class BodyExportDeclarationIreland extends IrelandMessage {

	private MsgExpDat message;	
    private List <MsgExpDatPos> goodsItemList = null;

    public BodyExportDeclarationIreland(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgExpDat getMessage() {
		return message;
	}
	public void setMessage(MsgExpDat argument) {
		this.message = argument;
	}
		
    public void writeBody() {
        try {            
            openElement("v1:ExportCustomsDeclaration"); 
            setAttribute("xmlns:v1", "http://www.ros.ie/schemas/ecs/IE515b/v1");
            openElement("v1:Declaration");                	                    	   
            	writeElement("v1:TypeCode", message.getAreaCode());
                writeElement("v1:IEMessageSendingDate", message.getDeclarationTime());                    	
                writeElement("v1:CustomsReferenceIdentifier", message.getUCRNumber());                    	
                writeElement("v1:DeclarationOfficeIdentifier", message.getCustomsOfficeExport());  
                writeElement("v1:SpecificCircumstanceIndicator", message.getSituationCode());
                if (!Utils.isStringEmpty(message.getPlaceOfDeclaration())) {
                	openElement("v1:Issuing");   
                		writeElement("v1:Place", message.getPlaceOfDeclaration());
                    closeElement();	
                }
                writeElement("v1:GoodsItemQuantity", message.getTotalNumberPositions());  
                if (message.getBusiness() != null) {
                	writeElement("v1:InvoiceAmount", message.getBusiness().getInvoicePrice());
                }
                    	                    	
                //GoodsShipment                   	
                openElement("v1:GoodsShipment");
                    writeElement("v1:ExportationCountryIdentifier", message.getDispatchCountry());
                    if (message.getBusiness() != null) {
                    	writeElement("v1:TransactionNatureCode", message.getBusiness().getBusinessTypeCode());
                    }                    		
                    writeWarehouse(message.getWareHouse());
                    openElement("v1:Ucr");  //ist ein MUSS-tag, daher ohne if()...
                    	writeElement("v1:TraderReferenceIdentifier", message.getReferenceNumber());
                    closeElement();	
                    if (message.getIncoTerms() != null) {
                    	writeTradeTerm(message.getIncoTerms().getIncoTerm(), message.getIncoTerms().getPlace(), "");
                    }
                    if (!Utils.isStringEmpty(message.getIntendedOfficeOfExit())) {
                    	openElement("v1:ExitCustomsOffice");
                    		writeElement("v1:CodeIdentifier", message.getIntendedOfficeOfExit());
                    	closeElement();	
                    }
                    if (!Utils.isStringEmpty(message.getDestinationCountry())) {
                    	openElement("v1:DeliveryDestination");
                    		writeElement("v1:CountryCode", message.getDestinationCountry());
                    	closeElement();	
                    }
                    //GoodsItems		
                    if (message.getGoodsItemList() != null) {
                    	for (MsgExpDatPos item : message.getGoodsItemList()) {
                    		writeCustomsGoodsItem(item, message.getMeansOfTransport());
                    	}
                    }
                    		
                    writeParty("v1:Consignor", message.getConsignor(), "");                    		
                    writeConsignment(message.getTransportMeansBorder(), message.getTransportMeansDeparture(),
                    				message.getTransportMeansInland(), message.getMeansOfTransport(), message.getTransportationRoute());
                    writeParty("v1:Consignee", message.getConsignee(), "");
                    writeElement("v1:IEMethodOfPaymentCode", message.getPaymentType());
                    writeAdditionalInformationList(message.getAddInfoStatementList());
                	writeAdditionalDocumentList(message.getDocumentList());
                    writeElement("v1:TransportMethodOfPaymentCode", message.getPaymentType());
                closeElement();
                    	
                if (!Utils.isStringEmpty(message.getTotalNumberOfPackages())) {
                    openElement("v1:DeclarationPackaging");   
                    	writeElement("v1:QuantityQuantity", message.getTotalNumberOfPackages());
                    closeElement();	
                }                    	                    	
                if (message.getBusiness() != null && !Utils.isStringEmpty(message.getBusiness().getCurrency())) {
                    openElement("v1:CurrencyExchange");   
                    	writeElement("v1:CurrencyTypeIdentifier", message.getBusiness().getCurrency());
                    closeElement();	
                }  
                writeParty("v1:Agent", message.getAgent(), message.getTypeOfRepresentation());
                writeElement("v1:TotalGrossMassMeasure", message.getGrossMass());                       	
                writeNumberOfSeals(message.getSeal()); 
                        
            closeElement();	 
            closeElement();	
       
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	}

	
	
}