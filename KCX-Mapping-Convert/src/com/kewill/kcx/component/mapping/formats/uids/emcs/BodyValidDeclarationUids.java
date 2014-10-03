package com.kewill.kcx.component.mapping.formats.uids.emcs;

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgValidDeclaration;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgValidDeclarationPos;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageEMCS;

 /**
 * Module		: EMCS<br>
 * Created		: 04.05.2010<br>
 * Description	: Body of UidsMessages ValidDeclaration.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class BodyValidDeclarationUids extends UidsMessageEMCS {
	
	private MsgValidDeclaration message; 
	private String version = "";         //EI20110701
 
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
        	version = this.uidsHeader.getMessageVersion();
        	version = Utils.removeDots(version.substring(0, 3));
        	
        	openElement("soap:Body");
            openElement("Submit");
                setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/emcs/body/200911");                
            openElement("EMCS");
            openElement("EMCSValidDeclaration"); 
            
                writeElement("LocalReferenceNumber", message.getReferenceNumber());                
                //EI20110919: writeElement("DeliveryPlaceCustomsOffice", message.getDeliveryPlaceCustomsOffice());
                writeExciseMovementEaad("", message.getAadReferenceCode(), message.getDateAndTimeOfValidation(), 
                    						version);
                writeElement("DispatchImportOffice", message.getDispatchImportOffice());                   
                writeElement("DeliveryPlaceCustomsOffice", message.getDeliveryPlaceCustomsOffice()); 
                writeElement("CompetentAuthorityDispatchOffice", message.getCompetentAuthorityDispatchOffice());
                writeHeaderEaad(message, version);               //EI20110927
                writeEaadDraft(message);
                //V20 - new Tag
                if (!version.equalsIgnoreCase("10")) {           //EI20110711
                    writeElement("UpstreamARC", message.getUpstreamARC());	                    	
                }                   
                writeTrader("ConsignorTrader", message.getConsignor(), version);
                writeTrader("PlaceOfDispatchTrader", message.getPlaceOfDispatch(), version);
                writeTrader("ConsigneeTrader", message.getConsignee(), version);
                writeComplementConsignee(message.getComplementConsignee());
                writeTrader("DeliveryPlaceTrader", message.getDeliveryPlace(), version);
                writeMovementGuarantee(message.getGuarantorTypeCode(), message.getGuarantorList(), version); 
                //V20 - changed TransportMode structure:
                if (version.equalsIgnoreCase("10")) {           //EI20110919
                    writeElement("TransportMode", message.getTransportModeCode());
                } else {                	
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
                }
                writeTrader("TransportArrangerTrader", message.getTransportArranger(), version);
                writeTrader("FirstTransporterTrader", message.getFirstTransporter(), version);
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
    	if (version.equals("10")) {           //EI20111117
    		writeElement("ItemNumber", goodsItem.getItemNumber());
    	} else {
    		writeElement("BodyRecordUniqueReference", goodsItem.getItemNumber());  //EI20111117 tagname in xsd
    	}
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
