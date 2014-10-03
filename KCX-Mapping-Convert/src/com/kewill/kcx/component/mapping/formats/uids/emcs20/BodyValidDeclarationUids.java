package com.kewill.kcx.component.mapping.formats.uids.emcs20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgValidDeclaration;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgValidDeclarationPos;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageEMCS20;

 /**
 * Module		: EMCS<br>
 * Created		: 04.05.2010<br>
 * Description	: Body of UidsMessages ValidDeclaration.
 *              : V20 - DateTime format was changed, Trader.Addres.StreetAndNumber now in two Tags
 *              : new Tags: UpstreamARC, TransportMode structure was changed
 * 
 * @author iwaniuk
 * @version 2.0.00
 */

public class BodyValidDeclarationUids extends UidsMessageEMCS20 {
	
	private MsgValidDeclaration message; 
	
    public BodyValidDeclarationUids(XMLStreamWriter writer) {
        this.writer = writer;
    }

	public MsgValidDeclaration getMessage() {
		return message;
	}

	public void setMessage(MsgValidDeclaration message) {
		this.message = message;
	}
    
	public void writeBody() {		
		
        try {          	
        	openElement("soap:Body");
            openElement("Submit");
                setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/emcs/body/200911");                
            openElement("EMCS");
            openElement("EMCSValidDeclaration"); 
            
                writeElement("LocalReferenceNumber", message.getReferenceNumber());                
                //EI20110919: writeElement("DeliveryPlaceCustomsOffice", message.getDeliveryPlaceCustomsOffice());
                writeExciseMovementEaad("", message.getAadReferenceCode(), message.getDateAndTimeOfValidation());
                writeElement("DispatchImportOffice", message.getDispatchImportOffice());                   
                writeElement("DeliveryPlaceCustomsOffice", message.getDeliveryPlaceCustomsOffice()); 
                writeElement("CompetentAuthorityDispatchOffice", message.getCompetentAuthorityDispatchOffice());
                writeHeaderEaad(message);                                                  //EI20110927
                writeEaadDraft(message);                
                writeElement("UpstreamARC", message.getUpstreamARC());	                   //EI20110711 V20 new Tag 
                writeTrader("ConsignorTrader", message.getConsignor());
                writeTrader("PlaceOfDispatchTrader", message.getPlaceOfDispatch());
                writeTrader("ConsigneeTrader", message.getConsignee());
                writeComplementConsignee(message.getComplementConsignee());
                writeTrader("DeliveryPlaceTrader", message.getDeliveryPlace());
                writeMovementGuarantee(message.getGuarantorTypeCode(), message.getGuarantorList()); 
                                                                                           //V20 - changed TransportMode structure:                             
                if (message.getTransportModeCode() != null || message.getComplementaryInformation() != null) {
                    openElement("Transport");
                    	writeElement("TransportModeCode", message.getTransportModeCode());
                    	if (message.getComplementaryInformation() != null) {  
                    		writeElementWithAttribute("ComplementaryInformation", 
                        				message.getComplementaryInformation().getText(), 
                        				"language", message.getComplementaryInformation().getLanguage());
                    	}
                    closeElement(); 
                }                
                writeTrader("TransportArrangerTrader", message.getTransportArranger());
                writeTrader("FirstTransporterTrader", message.getFirstTransporter());
                writeTransportDetailsList(message.getTransportDetailsList()); 
                        	                        	               
                if (message.getGoodsItemList() != null) {                        	                       
                    for (MsgValidDeclarationPos item : message.getGoodsItemList()) {	                        		
                    	writeGoodsItem(item);
                    }                            	
                }
                        	                              
                writeDocumentCertificateList(message.getDocumentCertificateList()); 
                writePdfInformation(message.getPdfInformation());
                            
            closeElement(); 
            closeElement(); 
            closeElement(); 
            closeElement(); 
        } catch (XMLStreamException e) {           
            e.printStackTrace();
        }
    }
	
	
    private void writeGoodsItem(MsgValidDeclarationPos goodsItem) throws XMLStreamException {
    	
    openElement("BodyEaad");
    	
    	writeElement("BodyRecordUniqueReference", goodsItem.getItemNumber());             //EI20111117 Tagname in xsd    	
      	writeElement("ExciseProductCode", goodsItem.getExciseProductCode());
      	writeElement("CnCode", goodsItem.getCnCode());
    	//for UIDS not defined: writeElement("ArticleNumber", goodsItem.getArticleNumber());
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
    	writePackageList(goodsItem.getPackageList());
    	writeWineProduct(goodsItem.getWineProduct());
    closeElement(); //BodyEaad
    }	

}    	
