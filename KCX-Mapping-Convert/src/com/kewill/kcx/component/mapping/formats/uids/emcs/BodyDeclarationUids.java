package com.kewill.kcx.component.mapping.formats.uids.emcs;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgEMCSDeclaration;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgEMCSDeclarationPos;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageEMCS;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module      : EMCS
 * Created     : 05.05.2010
 * Description : create of UIDS-Message in Emcs
 * 			   : relates to Declaration message.
 * 
 *  @author    : krzoska
 *  @version 1.0.00
 */

public class BodyDeclarationUids extends UidsMessageEMCS  {
	
    private MsgEMCSDeclaration   message  = new MsgEMCSDeclaration();
    private String version = "";         //EI20110701
 
    public BodyDeclarationUids(XMLStreamWriter writer) {
        this.writer = writer;
    }

	public MsgEMCSDeclaration getMessage() {
		return message;
	}

	public void setMessage(MsgEMCSDeclaration message) {
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
            openElement("EMCSDeclaration"); 
            
            	writeElement("LocalReferenceNumber", message.getReferenceNumber());
                writeAttributes(message);                        	
                writeTrader("ConsignorTrader", message.getConsignor(), version);
                writeTrader("PlaceOfDispatchTrader", message.getPlaceOfDispatch(), version);
                writeElement("DispatchImportOffice", message.getDispatchImportOffice());
                writeTrader("ConsigneeTrader", message.getConsignee(), version);
                writeComplementConsignee(message.getComplementConsignee());
                writeTrader("DeliveryPlaceTrader", message.getDeliveryPlace(), version);
                writeElement("DeliveryPlaceCustomsOffice", message.getDeliveryPlaceCustomsOffice());
                writeEaadDraft(message);
                writeElement("CompetentAuthorityDispatchOffice", message.getCompetentAuthorityDispatchOffice());
                writeMovementGuarantee(message.getGuarantorTypeCode(), message.getGuarantorList(), version);
                writeHeaderEaad(message);
                        	
                openElement("Transport");
                    writeElement("TransportModeCode", message.getTransportModeCode());    
                    //V20 - new Tag 
                    if (!version.equals("10") && message.getComplementaryInformation() != null) {  //EI20110819
                    	writeElementWithAttribute("ComplementaryInformation", 
	                    				message.getComplementaryInformation().getText(),
	                    		        "language", message.getComplementaryInformation().getLanguage()); 
                    } 		                
                closeElement();
                
                writeTrader("TransportArrangerTrader", message.getTransportArranger(), version);
                writeTrader("FirstTransporterTrader", message.getFirstTransporter(), version);
                writeTransportDetailsList(message.getTransportDetailsList());

                if (message.getGoodsItemList() != null) {
                        for (int i = 0; i < message.getGoodsItemList().size(); i++) {
                           writeBodyEaad((MsgEMCSDeclarationPos) message.getGoodsItemList().get(i));
                        }  
                 }
                
                 writeDocumentCertificateList(message.getDocumentCertificateList());

            closeElement(); 
            closeElement();
            closeElement(); 
            closeElement();
        } catch (XMLStreamException e) {           
            e.printStackTrace();
        }
    }

	private void writeBodyEaad(MsgEMCSDeclarationPos argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		
		openElement("BodyEaad");
			writeElement("BodyRecordUniqueReference", argument.getItemNumber());
			writeElement("ExciseProductCode", argument.getExciseProductCode());			
			writeElement("CnCode", argument.getCnCode());
			writeElement("Quantity", argument.getQuantity());
			writeElement("GrossWeight", argument.getGrossWeight());
			writeElement("NetWeight", argument.getNetWeight());
			writeElement("AlcoholicStrength", argument.getAlcoholicStrength());
			writeElement("DegreePlato", argument.getDegreePlato());
			
			if (argument.getFiscalMark() != null) {
				writeElementWithAttribute("FiscalMark", argument.getFiscalMark().getText(), 
					                      "language", argument.getFiscalMark().getLanguage());
			}
			writeElement("FiscalMarkUsedFlag", argument.getFiscalMarkUsedFlag());			
			if (argument.getDesignationOfOrigin() != null) {
				writeElementWithAttribute("DesignationOfOrigin", argument.getDesignationOfOrigin().getText(), 
										  "language", argument.getDesignationOfOrigin().getLanguage());
			}			
			writeElement("SizeOfProducer", argument.getSizeOfProducer());
			writeElement("Density", argument.getDensity());			
			if (argument.getCommercialDescription() != null) {
				writeElementWithAttribute("CommercialDescription", argument.getCommercialDescription().getText(), 
					                      "language", argument.getCommercialDescription().getLanguage());
			}
			if (argument.getBrandNameOfProducts() != null) {
				writeElementWithAttribute("BrandNameOfProducts", argument.getBrandNameOfProducts().getText(), 
									      "language", argument.getBrandNameOfProducts().getLanguage());
			}
			writePackageList(argument.getEmcsPackageList());
			writeWineProduct(argument.getWineProduct());
					
		closeElement(); //BodyEaad
	}

}    	
