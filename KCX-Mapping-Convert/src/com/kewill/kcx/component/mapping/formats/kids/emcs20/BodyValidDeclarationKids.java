package com.kewill.kcx.component.mapping.formats.kids.emcs20;


import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgValidDeclaration;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgValidDeclarationPos;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageEMCS;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module        : BodyValidDeclarationKids<br>
 * Created       : 04.05.2010<br>
 * Description   : Body of Kids-ValidDeclaration.
 *               : V20 new Tags: UpstreamARC, ComplementaryInformation
 * 
 * @author iwaniuk
 * @version 2.0.00
 */
public class BodyValidDeclarationKids extends KidsMessageEMCS {

	private MsgValidDeclaration message; 
	
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
            openElement("soap:Body");
            openElement("EMCS");               
            openElement("EMCSValidDeclaration");  
                    
            	writeElement("ReferenceNumber", message.getReferenceNumber());
                writeElement("CustomerOrderNumber", message.getCustomerOrderNumber());
                writeElement("Clerk", message.getClerk());
                writeElement("AadReferenceCode", message.getAadReferenceCode());
                writeDateTimeToString("DateAndTimeOfValidation", message.getDateAndTimeOfValidation());
                writeElement("DispatchImportOffice", message.getDispatchImportOffice()); 
                writeElement("DeliveryPlaceCustomsOffice", message.getDeliveryPlaceCustomsOffice()); 
                writeElement("CompetentAuthorityDispatchOffice", message.getCompetentAuthorityDispatchOffice()); 
                writeDateTimeToString("DateAndTimeOfUpdateValidation", message.getDateAndTimeOfUpdateValidation());
                writeElement("JourneyTime", message.getJourneyTime());                           
                writeElement("DestinationTypeCode", message.getDestinationTypeCode()); 
                writeElement("TransportArrangement", message.getTransportArrangement());
                writeElement("SequenceNumber", message.getSequenceNumber());  
                writeDateToString("DateOfDispatch", message.getDateOfDispatch());
                writeTimeToString("TimeOfDispatch", message.getTimeOfDispatch());
                writeElement("OriginTypeCode", message.getOriginTypeCode());
                writeElement("InvoiceNumber", message.getInvoiceNumber());
                writeDateToString("InvoiceDate", message.getInvoiceDate()); 
                writeList("ImportSad", message.getImportSadList());                          
                writeElement("UpstreamARC", message.getUpstreamARC());						//V20 new                       
                writeElement("TransportModeCode", message.getTransportModeCode());                         
                if (message.getComplementaryInformation() != null) {        				//V20 new
                    writeElementWithAttribute("ComplementaryInformation", 
                            		message.getComplementaryInformation().getText(), "language", 
                            		message.getComplementaryInformation().getLanguage());
                }
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
                                      
                if (message.getGoodsItemList() != null) {                        
                    for (MsgValidDeclarationPos pos : message.getGoodsItemList()) {	                        		
                    	writeGoodsItem(pos);
                    }
                }                       
               writePDFInformation(message.getPdfInformation()); 
                                               	
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
    	writeElement("ExciseProductCode", goodsItem.getExciseProductCode());
    	writeElement("CnCode", goodsItem.getCnCode());
    	writeElement("ArticleNumber", goodsItem.getArticleNumber());
    
        Text commercialDescription = goodsItem.getCommercialDescription();
        if (commercialDescription != null) {
            writeElementWithAttribute("CommercialDescription", commercialDescription.getText(), 
                                                       "language", commercialDescription.getLanguage());
        }
        Text designationOfOrigin = goodsItem.getDesignationOfOrigin();
        if (designationOfOrigin != null) {
            writeElementWithAttribute("DesignationOfOrigin", designationOfOrigin.getText(), 
                                                     "language", designationOfOrigin.getLanguage());
        }
        Text brandNameOfProducts = goodsItem.getBrandNameOfProducts();
        if (brandNameOfProducts != null) {
            writeElementWithAttribute("BrandNameOfProducts", brandNameOfProducts.getText(), 
                                                     "language", brandNameOfProducts.getLanguage());
        }
        writeElement("FiscalMarkUsedFlag", goodsItem.getFiscalMarkUsedFlag());
        Text fiscalMark = goodsItem.getFiscalMark();
        if (fiscalMark != null) {
            writeElementWithAttribute("FiscalMark", fiscalMark.getText(),
                                            "language", fiscalMark.getLanguage());
        }    	    	
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
