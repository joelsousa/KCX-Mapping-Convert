package com.kewill.kcx.component.mapping.formats.kids.emcs;


import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgValidDeclaration;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgValidDeclarationPos;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageEMCS;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        : BodyValidDeclarationKids<br>
 * Erstellt     : 04.05.2010<br>
 * Beschreibung : Body of Kids-ValidDeclaration.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class BodyValidDeclarationKids extends KidsMessageEMCS {

	private MsgValidDeclaration message; 
	private String version = "";         //EI20110701

    public BodyValidDeclarationKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgValidDeclaration getMessage() {
		return message;
	}
	public void setMessage(MsgValidDeclaration argument) {
		this.message = argument;
	}
		
    public void writeBody() {
        try {
        	version = this.kidsHeader.getRelease();
        	version = Utils.removeDots(version.substring(0, 3));
        	
            openElement("soap:Body");
            	openElement("EMCS");
                //openElement(this.kidsMessageName);
                    openElement("EMCSValidDeclaration");  
                    
                    	writeElement("ReferenceNumber", message.getReferenceNumber());
                    	writeElement("CustomerOrderNumber", message.getCustomerOrderNumber());
                    	writeElement("Clerk", message.getClerk());
                        writeElement("AadReferenceCode", message.getAadReferenceCode());
                        writeDateTimeToString("DateAndTimeOfValidation", 
                                                                     message.getDateAndTimeOfValidation());
                        writeElement("DispatchImportOffice", message.getDispatchImportOffice()); 
                        writeElement("DeliveryPlaceCustomsOffice", 
                                      message.getDeliveryPlaceCustomsOffice()); 
                        writeElement("CompetentAuthorityDispatchOffice", 
                                                            message.getCompetentAuthorityDispatchOffice()); 
                        writeDateTimeToString("DateAndTimeOfUpdateValidation", 
                                                               message.getDateAndTimeOfUpdateValidation());
                        writeElement("JourneyTime", message.getJourneyTime());                           
                        //writeCodeElement("DestinationTypeCode", message.getDestinationTypeCode(), "A0032"); 
                        writeElement("DestinationTypeCode", message.getDestinationTypeCode()); 
                        //writeCodeElement("TransportArrangement", message.getTransportArrangement(), "A0070");
                        writeElement("TransportArrangement", message.getTransportArrangement());
                        writeElement("SequenceNumber", message.getSequenceNumber());  
                        writeDateToString("DateOfDispatch", message.getDateOfDispatch());
                        writeTimeToString("TimeOfDispatch", message.getTimeOfDispatch());
                        //writeCodeElement("OriginTypeCode", message.getOriginTypeCode(), "A0030");
                        writeElement("OriginTypeCode", message.getOriginTypeCode());
                        writeElement("InvoiceNumber", message.getInvoiceNumber());
                        writeDateToString("InvoiceDate", message.getInvoiceDate()); 
                        writeList("ImportSad", message.getImportSadList());    
                        if (!version.equals("10")) {
                        	writeElement("UpstreamARC", message.getUpstreamARC());	
                        }
                        //writeCodeElement("TransportModeCode", message.getTransportModeCode(), "C0067");  
                        writeElement("TransportModeCode", message.getTransportModeCode());  
                        if (!version.equals("10")) {
                            if (message.getComplementaryInformation() != null) {
                            	//writeCodeElementWithAttribute("ComplementaryInformation", 
                            	//	message.getComplementaryInformation().getText(), "C0012", "language", 
                            	//	message.getComplementaryInformation().getLanguage());
                            	writeElementWithAttribute("ComplementaryInformation", 
                                		message.getComplementaryInformation().getText(), "language", 
                                		message.getComplementaryInformation().getLanguage());
                            }
                        }   //EI20110701-end 
                        //writeCodeElement("GuarantorTypeCode", message.getGuarantorTypeCode(), "A0029");
                        writeElement("GuarantorTypeCode", message.getGuarantorTypeCode());
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
                        
                        List list = message.getGoodsItemList();
                        if (list != null) {
                        	int size = list.size();
                        	for (int i = 0; i < size; i++) {	                        		
                    			writeGoodsItem((MsgValidDeclarationPos) list.get(i));
                    		}
                        	
                        }                        
                        writePDFInformation(message.getPdfInformation()); //EI20111007 doch nicht anders als in KidsMessage
                        //writePDFInformation(message.getPdfInformation(), version); //EI20111007
                       	
                    closeElement();
                closeElement();	    
           closeElement();	           
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	} 
 
    private void writeGoodsItem(MsgValidDeclarationPos goodsItem) throws XMLStreamException {
    openElement("GoodsItem");
    	writeElement("ItemNumber", goodsItem.getItemNumber());
    	//writeCodeElement("ExciseProductCode", goodsItem.getExciseProductCode(), "C0036");
    	writeElement("ExciseProductCode", goodsItem.getExciseProductCode());
    	//writeCodeElement("CnCode", goodsItem.getCnCode(), "C0037");
    	writeElement("CnCode", goodsItem.getCnCode());
    	writeElement("ArticleNumber", goodsItem.getArticleNumber());
    
        Text commercialDescription = goodsItem.getCommercialDescription();
        if (commercialDescription != null) {
            //writeCodeElementWithAttribute("CommercialDescription", commercialDescription.getText(), "C0012",
            //                                           "language", commercialDescription.getLanguage());
            writeElementWithAttribute("CommercialDescription", commercialDescription.getText(),
                    "language", commercialDescription.getLanguage());
        }
        Text designationOfOrigin = goodsItem.getDesignationOfOrigin();
        if (designationOfOrigin != null) {
            //writeCodeElementWithAttribute("DesignationOfOrigin", designationOfOrigin.getText(), "C0012",
            //                                         "language", designationOfOrigin.getLanguage());
            writeElementWithAttribute("DesignationOfOrigin", designationOfOrigin.getText(),
                    "language", designationOfOrigin.getLanguage());
        }
        Text brandNameOfProducts = goodsItem.getBrandNameOfProducts();
        if (brandNameOfProducts != null) {
            //writeCodeElementWithAttribute("BrandNameOfProducts", brandNameOfProducts.getText(), "C0012",
            //                                         "language", brandNameOfProducts.getLanguage());
            writeElementWithAttribute("BrandNameOfProducts", brandNameOfProducts.getText(),
                    "language", brandNameOfProducts.getLanguage());
        }
        //writeCodeElement("FiscalMarkUsedFlag", goodsItem.getFiscalMarkUsedFlag(), "C0027");
        writeElement("FiscalMarkUsedFlag", goodsItem.getFiscalMarkUsedFlag());
        Text fiscalMark = goodsItem.getFiscalMark();
        if (fiscalMark != null) {
            //writeCodeElementWithAttribute("FiscalMark", fiscalMark.getText(), "C0012",
            //                                "language", fiscalMark.getLanguage());
            writeElementWithAttribute("FiscalMark", fiscalMark.getText(), 
                    "language", fiscalMark.getLanguage());
        }
    	// 20101215MS end
    	
    	writeElement("Quantity", goodsItem.getQuantity());
    	writeElement("AlcoholicStrength", goodsItem.getAlcoholicStrength());
    	writeElement("DegreePlato", goodsItem.getDegreePlato());
    	writeElement("SizeOfProducer", goodsItem.getSizeOfProducer());
    	writeElement("Density", goodsItem.getDensity());
    	writeElement("NetWeight", goodsItem.getNetWeight());
    	writeElement("GrossWeight", goodsItem.getGrossWeight());
    	writePackageList(goodsItem.getPackageList());
    	writeWineProduct(goodsItem.getWineProduct());
    closeElement();	 
    }
}
