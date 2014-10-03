package com.kewill.kcx.component.mapping.formats.kids.Import20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.Import.msg.MsgImportDeclarationConfirmation;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Completion;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.GoodsItemDeclarationDecision;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Manifest;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageImport;

/**
 * Module	   : Import V20<br>
 * Created	   : 22.11.2012<br>
 * Description : BodyImportDeclarationDecisionKids.
 * 
 * @author iwaniuk
 * @version 2.0.00
 *
 */
public class BodyImportDeclarationConfirmationKids extends KidsMessageImport {

	private MsgImportDeclarationConfirmation message;	

    public BodyImportDeclarationConfirmationKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgImportDeclarationConfirmation getMessage() {
		return message;
	}
	
	public void setMessage(MsgImportDeclarationConfirmation argument) {
		this.message = argument;
	}
		
    public void writeBody() {
        try {
            openElement("soap:Body");
            openElement("ImportDeclarationDecision");
            openElement("GoodsDeclaration"); 
          
            	writeElement("ReferenceNumber", message.getReferenceNumber());  
            	writeElement("TemporaryMRN", message.getTemporaryMRN());
                writeElement("DeclarantTin", message.getDeclarantTIN());                
                writeRepresentativeContact(message.getRepresentative());    
                writeElement("GoodsLocation", message.getGoodsLocation());  
                writeElement("MeansOfTransportArrival", message.getMeansOfTransportArrival());  
                if (message.getPreviousDocument() != null) {
                	 writeElement("Type", message.getPreviousDocument().getType());  
                	 writeElement("Number", message.getPreviousDocument().getNumber());  
                }
                if (message.getManifestCompletionList() != null) {
	                for (Manifest manifest : message.getManifestCompletionList()) {
	                   writeManifest(manifest);
	                }
                } else if (message.getBondedWarehouseCompletionList() != null) {               	
                	for (Completion warehouse : message.getBondedWarehouseCompletionList()) {
     	               writeBondedWarehouseCompletion(warehouse);
                	}
                     
                } else if (message.getInwardProcessingCompletionList() != null) {                	
                	for (Completion inward : message.getInwardProcessingCompletionList()) {
                		writeInwardProcessingCompletion(inward);
                	}        
                } 
                                    
            closeElement();
            closeElement();
            closeElement();
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	}
   
}
