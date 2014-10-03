package com.kewill.kcx.component.mapping.formats.kids.emcs20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgEMCSDeclaration;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgEMCSDeclarationPos;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageEMCS;

/**
 * Module		: BodyExportDeclarationKids<br>
 * Created		: 10.05.2010<br>
 * Description	: Body of EMCS Kids-ExportDeclaration.
 *              : V20 new Tag ComplementaryInformation
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class BodyDeclarationKids extends KidsMessageEMCS {

	private MsgEMCSDeclaration message; 
	
    public BodyDeclarationKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgEMCSDeclaration getMessage() {
		return message;
	}
	public void setMessage(MsgEMCSDeclaration argument) {
		this.message = argument;
	}
		
    public void writeBody() {    	
        try {        	
            openElement("soap:Body");
            openElement("EMCS");                
            openElement("EMCSDeclaration");  
                    
            	writeElement("ReferenceNumber", message.getReferenceNumber());
                writeElement("CustomerOrderNumber", message.getCustomerOrderNumber());
                writeElement("Clerk ", message.getClerk());
                writeElement("DeferredSubmissionFlag", message.getDeferredSubmissionFlag());
                writeElement("SubmissionMessageType", message.getSubmissionMessageType());
                writeElement("DispatchImportOffice", message.getDispatchImportOffice());
                writeElement("DeliveryPlaceCustomsOffice", message.getDeliveryPlaceCustomsOffice());
                writeElement("CompetentAuthorityDispatchOffice", message.getCompetentAuthorityDispatchOffice());
                writeElement("JourneyTime", message.getJourneyTime());                    	
                writeElement("DestinationTypeCode", message.getDestinationTypeCode());
                writeElement("TransportArrangement", message.getTransportArrangement());
                writeDateToString("DateOfDispatch", message.getDateOfDispatch());                        
                writeTimeToString("TimeOfDispatch", message.getTimeOfDispatch());
                writeElement("OriginTypeCode", message.getOriginTypeCode());
                writeElement("InvoiceNumber", message.getInvoiceNumber());
                writeDateToString("InvoiceDate", message.getInvoiceDate()); 
                writeList("ImportSad", message.getImportSadList());
                writeElement("TransportModeCode", message.getTransportModeCode());
                if (message.getComplementaryInformation() != null) {   									//V20 new
                    writeElementWithAttribute("ComplementaryInformation", 
                    				message.getComplementaryInformation().getText(), "language",
                    				message.getComplementaryInformation().getLanguage()); 
                }
                writeElement("GuarantorTypeCode", message.getGuarantorTypeCode());
                writeElement("PreviousProcedure", message.getPreviousProcedure());  //EI20100706
                writeTraderList("Guarantor", message.getGuarantorList());
                writeDocumentCertificateList(message.getDocumentCertificateList());    
                writeTrader("Consignee", message.getConsignee());
                writeComplementConsignee(message.getComplementConsignee());
                writeTrader("Consignor", message.getConsignor());
                writeTrader("PlaceOfDispatch", message.getPlaceOfDispatch()); 
                writeTrader("DeliveryPlace", message.getDeliveryPlace()); 
                writeTrader("TransportArranger", message.getTransportArranger());                       
                writeTrader("FirstTransporter", message.getFirstTransporter());
                writeTransportDetailsList(message.getTransportDetailsList()); 
               
                if (message.getGoodsItemList() != null) {                        	                        
                	for (MsgEMCSDeclarationPos pos : message.getGoodsItemList()) {	                        		
                		writeGoodsItem(pos);
                	}
                }
                        
           closeElement();  
           closeElement();	    
           closeElement();	        
           
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	} 
 
    private void writeGoodsItem(MsgEMCSDeclarationPos goodsItem) throws XMLStreamException {
    	
    	openElement("GoodsItem");
    	
	    	writeElement("ItemNumber", goodsItem.getItemNumber());
	    	writeElement("ExciseProductCode", goodsItem.getExciseProductCode());
	    	writeElement("CnCode", goodsItem.getCnCode());
	    	writeElement("ArticleNumber", goodsItem.getArticleNumber());
	    	if (goodsItem.getCommercialDescription() != null) {
	    		writeElementWithAttribute("CommercialDescription", goodsItem.getCommercialDescription().getText(),
	    									  "language", goodsItem.getCommercialDescription().getLanguage());
	    	}
	    	if (goodsItem.getDesignationOfOrigin() != null) {
	    		writeElementWithAttribute("DesignationOfOrigin", goodsItem.getDesignationOfOrigin().getText(),     	
	    				  					  "language", goodsItem.getDesignationOfOrigin().getLanguage());
	    	}
	    	if (goodsItem.getBrandNameOfProducts() != null) {
	    		writeElementWithAttribute("BrandNameOfProducts", goodsItem.getBrandNameOfProducts().getText(), 
	  					  		 			  "language", goodsItem.getBrandNameOfProducts().getLanguage());
	    	}
	    	writeElement("FiscalMarkUsedFlag", goodsItem.getFiscalMarkUsedFlag());
	    	
	    	if (goodsItem.getFiscalMark() != null) {
	    		writeElementWithAttribute("FiscalMark", goodsItem.getFiscalMark().getText(),
	    									  "language", goodsItem.getFiscalMark().getLanguage());
	    	}
	    	writeElement("Quantity", goodsItem.getQuantity());
	    	writeElement("AlcoholicStrength", goodsItem.getAlcoholicStrength());
	    	writeElement("DegreePlato", goodsItem.getDegreePlato());
	    	writeElement("SizeOfProducer", goodsItem.getSizeOfProducer());
	    	writeElement("Density", goodsItem.getDensity());
	    	writeElement("NetWeight", goodsItem.getNetWeight());
	    	writeElement("GrossWeight", goodsItem.getGrossWeight());
	    	writePackageList(goodsItem.getEmcsPackageList());
	    	writeWineProduct(goodsItem.getWineProduct());
	    	
	    closeElement(); //GoodsItem
    }
}
