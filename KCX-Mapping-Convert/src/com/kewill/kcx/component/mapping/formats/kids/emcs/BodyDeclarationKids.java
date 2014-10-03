package com.kewill.kcx.component.mapping.formats.kids.emcs;

/**
 * Modul		: BodyExportDeclarationKids<br>
 * Erstellt		: 10.05.2010<br>
 * Beschreibung	: Body of emcs Kids-ExportDeclaration.
 * 
 * @author krzoska
 * @version 1.0.00
 * 
 * Changes:  
 * -----------
 * Author      : iwaniuk
 * Date        : 12.05.2010
 * Label       : 
 * Description : geprüft: etn_emcs_message_V10.xsd  * 
 */

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgEMCSDeclaration;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgEMCSDeclarationPos;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgValidDeclarationPos;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageEMCS;
import com.kewill.kcx.component.mapping.util.Utils;

public class BodyDeclarationKids extends KidsMessageEMCS {

	private MsgEMCSDeclaration message; 
	private String version = "";         //EI20110819

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
        	version = this.kidsHeader.getRelease();
        	version = Utils.removeDots(version.substring(0, 3));
        	
            openElement("soap:Body");
            	openElement("EMCS");
                //openElement(this.kidsMessageName);
                    openElement("EMCSDeclaration");  
                    
                    	writeElement("ReferenceNumber", message.getReferenceNumber());
                    	writeElement("CustomerOrderNumber", message.getCustomerOrderNumber());
                    	writeElement("Clerk ", message.getClerk());
                        //writeCodeElement("DeferredSubmissionFlag", message.getDeferredSubmissionFlag(), "A0027");
                        writeElement("DeferredSubmissionFlag", message.getDeferredSubmissionFlag());
                        //writeCodeElement("SubmissionMessageType", message.getSubmissionMessageType(), "A0071");
                        writeElement("SubmissionMessageType", message.getSubmissionMessageType());
                    	writeElement("DispatchImportOffice", message.getDispatchImportOffice());
                    	writeElement("DeliveryPlaceCustomsOffice", message.getDeliveryPlaceCustomsOffice());
                    	writeElement("CompetentAuthorityDispatchOffice", message.getCompetentAuthorityDispatchOffice());
                    	writeElement("JourneyTime", message.getJourneyTime());                    	
                        //writeCodeElement("DestinationTypeCode", message.getDestinationTypeCode(), "A0032");
                        writeElement("DestinationTypeCode", message.getDestinationTypeCode());
                        //writeCodeElement("TransportArrangement", message.getTransportArrangement(), "A0070");
                        writeElement("TransportArrangement", message.getTransportArrangement());
                        writeDateToString("DateOfDispatch", message.getDateOfDispatch());                        
                        writeTimeToString("TimeOfDispatch", message.getTimeOfDispatch());
                        //writeCodeElement("OriginTypeCode", message.getOriginTypeCode(), "A0030");
                        writeElement("OriginTypeCode", message.getOriginTypeCode());
                        writeElement("InvoiceNumber", message.getInvoiceNumber());
                        writeDateToString("InvoiceDate", message.getInvoiceDate()); 
                        writeList("ImportSad", message.getImportSadList());
                        //writeCodeElement("TransportModeCode", message.getTransportModeCode(), "C0067");
                        writeElement("TransportModeCode", message.getTransportModeCode());
                        if (!version.equalsIgnoreCase("10") && message.getComplementaryInformation() != null) {   //EI20110819
                        	//writeCodeElementWithAttribute("ComplementaryInformation", 
                    		//		message.getComplementaryInformation().getText(), "C0012", "language",
                    		//		message.getComplementaryInformation().getLanguage()); 
                        	writeElementWithAttribute("ComplementaryInformation", 
                    				message.getComplementaryInformation().getText(), "language",
                    				message.getComplementaryInformation().getLanguage()); 
                        }
                        //writeCodeElement("GuarantorTypeCode", message.getGuarantorTypeCode(), "A0029");
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
                        
                        List<MsgEMCSDeclarationPos> list = message.getGoodsItemList();
                        if (list != null) {                        	
                        	int size = list.size();
                        	for (int i = 0; i < size; i++) {	                        		
                    			writeGoodsItem((MsgEMCSDeclarationPos) list.get(i));
                    		}
                        }
                    closeElement();  //EMCSDeclaration
                closeElement();	     //EMCS
           closeElement();	         //soap:Body  
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	} 
 
    private void writeGoodsItem(MsgEMCSDeclarationPos goodsItem) throws XMLStreamException {
    	openElement("GoodsItem");
	    	writeElement("ItemNumber", goodsItem.getItemNumber());
	    	//writeCodeElement("ExciseProductCode", goodsItem.getExciseProductCode(), "C0036");
	    	writeElement("ExciseProductCode", goodsItem.getExciseProductCode());
	    	//writeCodeElement("CnCode", goodsItem.getCnCode(), "C0037");
	    	writeElement("CnCode", goodsItem.getCnCode());
	    	writeElement("ArticleNumber", goodsItem.getArticleNumber());
	    	if (goodsItem.getCommercialDescription() != null) {
	    		//writeCodeElementWithAttribute("CommercialDescription", goodsItem.getCommercialDescription().getText(), "C0012", 
	    		//							  "language", goodsItem.getCommercialDescription().getLanguage());
	    		writeElementWithAttribute("CommercialDescription", goodsItem.getCommercialDescription().getText(),  
					  "language", goodsItem.getCommercialDescription().getLanguage());
	    	}
	    	if (goodsItem.getDesignationOfOrigin() != null) {
	    		//writeCodeElementWithAttribute("DesignationOfOrigin", goodsItem.getDesignationOfOrigin().getText(), "C0012",
	    		//		  					  "language", goodsItem.getDesignationOfOrigin().getLanguage());
	    		writeElementWithAttribute("DesignationOfOrigin", goodsItem.getDesignationOfOrigin().getText(),
					  "language", goodsItem.getDesignationOfOrigin().getLanguage());
	    	}
	    	if (goodsItem.getBrandNameOfProducts() != null) {
	    		//writeCodeElementWithAttribute("BrandNameOfProducts", goodsItem.getBrandNameOfProducts().getText(), "C0012",
	  			//		  		 			  "language", goodsItem.getBrandNameOfProducts().getLanguage());
	    		writeElementWithAttribute("BrandNameOfProducts", goodsItem.getBrandNameOfProducts().getText(), 
  		 			  "language", goodsItem.getBrandNameOfProducts().getLanguage());
	    	}
	    	//writeCodeElement("FiscalMarkUsedFlag", goodsItem.getFiscalMarkUsedFlag(), "A0027");
	    	writeElement("FiscalMarkUsedFlag", goodsItem.getFiscalMarkUsedFlag());
	    	
	    	if (goodsItem.getFiscalMark() != null) {
	    		//writeCodeElementWithAttribute("FiscalMark", goodsItem.getFiscalMark().getText(), "C0012",
	    		//							  "language", goodsItem.getFiscalMark().getLanguage());
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