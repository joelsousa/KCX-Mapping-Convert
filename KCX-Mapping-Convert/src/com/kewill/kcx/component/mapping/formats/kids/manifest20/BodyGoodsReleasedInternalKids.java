package com.kewill.kcx.component.mapping.formats.kids.manifest20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.suma70.msg.MsgGoodsReleasedInternal;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.Address;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.GoodsItem;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.NewCustodyValues;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageManifest20;

/**
 * Module	   : Manifest.<br>
 * Created	   : 08.07.2013<br>
 * Description : BodyGoodsReleasedInternalKids
 * 
 * @author Alfred Krzoska
 * @version 2.0.00
 *
 */

public class BodyGoodsReleasedInternalKids extends KidsMessageManifest20 {

	private MsgGoodsReleasedInternal message;	

    public BodyGoodsReleasedInternalKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgGoodsReleasedInternal getMessage() {
		return message;
	}
	
	public void setMessage(MsgGoodsReleasedInternal argument) {
		this.message = argument;
	}
		
    public void writeBody() {
        try {
            openElement("soap:Body");
            openElement("GoodsReleasedInternal");
            openElement("GoodsDeclaration"); 
            	writeDateToString("DateOfPresentation", message.getDateOfPresentation());  
                writeElement("ReferenceNumber", message.getReferenceNumber());            	
                writeElement("RegistrationNumber", message.getRegistrationNumber());  
                writeDateToString("RegistrationDate", message.getRegistrationDate()); 
                writeTransport("MeansOfTransport", message.getTransport());  
                writeDateToString("DateOfArrival", message.getDateOfArrival()); 
                writeElement("PlaceOfLoading", message.getPlaceOfLoading());
                writePreviousDocument(message.getPreviousDocument());
                writeElement("EdifactNumber", message.getEdifactNumber());
                writeDateTimeToString("DateTimeOfReceipt", message.getDateTimeOfReceipt());
                
                if (message.getGoodsItemList() != null) {
                	for (GoodsItem item : message.getGoodsItemList()) {
                		writeGoodsItem(item);
                	}
                }                
                writeLocalApplication(message.getLocalApplication());
                
            closeElement();
            closeElement();
            closeElement();
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	}

    private void writeGoodsItem(GoodsItem item) {
    	if (item == null) {
    		return;
    	}
    	try {
        	openElement("GoodsItem");
        		writeElement("ItemNumber", item.getItemNumber());
        		writeParty("Custodian", item.getCustodian());
        		writeParty("Disposal", item.getDisposal());
        		//writeElement("BriefCargoDescription", goodsItem.getBriefCargoDescription()); 
        		writeElement("ItemDescription", item.getItemDescription());   			//EI20131022 umbenannt
        		writeElement("GrossMass", item.getGrossMass()); 
        		writeElement("CustomsStatusOfGoods", item.getCustomsStatusOfGoods());        		
        		writeDateTimeToString("DateTimeOfReceipt", item.getDateTimeOfReceipt()); //EI20131022 umbenannt
        		writeDateToString("CustodyDeadline", item.getCustodyDeadline());   		 //EI20131022 umbenannt
        		writePartyTIN("PlaceOfCustody", item.getPlaceOfCustodyTIN()); 
        		writeAddress("PlaceOfCustody", item.getPlaceOfCustody());        		
        		writeElement("PlaceOfCustodyCode", item.getPlaceOfCustodyCode());
        		writeElement("RangeOfGoodsCode", item.getRangeOfGoodsCode());
        		writeReferencedSpecification(item.getReferencedSpecification());
        		writePackageList(item.getPackagesList());
        		writeNewCustodyValues(item.getNewCustodyValues());        		
        		writeItemExtension(item.getItemExtension());
             closeElement();
    	} catch (XMLStreamException e) {
            e.printStackTrace();
        } 
		
	}

	private void writeNewCustodyValues(NewCustodyValues newCustodyValues) throws XMLStreamException  {
			if (newCustodyValues == null) {
				return;
			}					
			if (newCustodyValues.getPlaceOfCustody() == null && 
					newCustodyValues.getCustodian() == null &&
					newCustodyValues.getReferencedSpecification() == null) {
				return;
			}
			
		    openElement("NewCustodyValues");		
		    if (newCustodyValues.getCustodian() != null) {
		    	openElement("Custodian");
		    	writePartyTIN("", newCustodyValues.getCustodian().getTIN());		    		
					if (newCustodyValues.getCustodian().getAddress() != null) {
						writeAddress("Address", newCustodyValues.getCustodian().getAddress());
					}
					if (newCustodyValues.getCustodian().getReferencedSpecification() != null) {
				    	writeReferencedSpecification("", newCustodyValues.getCustodian().getReferencedSpecification());
				    }
				closeElement();
		    }
		    if (newCustodyValues.getPlaceOfCustody() != null) {
		    	openElement("PlaceOfCustody");
		    	writePartyTIN("", newCustodyValues.getPlaceOfCustody().getTIN());		    		
					if (newCustodyValues.getPlaceOfCustody().getAddress() != null) {
						writeAddress("Address", newCustodyValues.getPlaceOfCustody().getAddress());
					}
					if (newCustodyValues.getPlaceOfCustody().getReferencedSpecification() != null) {
				    	writeReferencedSpecification("", newCustodyValues.getPlaceOfCustody().getReferencedSpecification());
				    }
				closeElement();
		    }
		    if (newCustodyValues.getReferencedSpecification() != null) {
		    	writeReferencedSpecification("", newCustodyValues.getReferencedSpecification());
		    }
			closeElement();	    
	}
	
}

