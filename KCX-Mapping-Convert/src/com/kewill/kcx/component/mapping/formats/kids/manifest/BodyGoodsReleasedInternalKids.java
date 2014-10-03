package com.kewill.kcx.component.mapping.formats.kids.manifest;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.suma62.msg.MsgGoodsReleasedInternal;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.Address;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.GoodsItem;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.NewCustodyValues;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageManifest;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module	   : Manifest.<br>
 * Created	   : 19.11.2012<br>
 * Description : BodyGoodsReleasedInternalKids
 * 
 * @author Alfred Krzoska
 * @version 1.0.00
 *
 */

public class BodyGoodsReleasedInternalKids extends KidsMessageManifest {

	private MsgGoodsReleasedInternal msg;	

    public BodyGoodsReleasedInternalKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgGoodsReleasedInternal getMessage() {
		return msg;
	}
	
	public void setMessage(MsgGoodsReleasedInternal argument) {
		this.msg = argument;
	}
		
    public void writeBody() {
        try {
            openElement("soap:Body");
            openElement("GoodsReleasedInternal");
            openElement("GoodsDeclaration"); 
            	writeElement("DateOfPresentation", msg.getDateOfPresentation());  
            	writeElement("FlightNumber", msg.getFlightNumber());
                writeElement("ReferenceNumber", msg.getReferenceNumber());            	
                writeElement("RegistrationNumber", msg.getRegistrationNumber());      
                writeElement("PlaceOfLoading", msg.getPlaceOfLoading());
                writePreviousDocument(msg.getPreviousDocument());
                
                if (msg.getGoodsItemList() != null) {
                	for (GoodsItem item : msg.getGoodsItemList()) {
                		writeGoodsItem(item);
                	}
                }
                
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
        		writeElement("CustodianTIN", item.getCustodianTIN());
        		writeElement("DisposalTIN", item.getDisposalTIN());
        		writeElement("BriefCargoDescription", item.getBriefCargoDescription());
        		writeElement("DateOfApplication", item.getDateOfApplication());
        		writeElement("DateOfReception", item.getDateOfReception());
        		writeElement("GrossMass", item.getGrossMass());
        		writePlaceOfCustodyCity(item.getPlaceOfCustody());
        		writeElement("PlaceOfCustodyCode", item.getPlaceOfCustodyCode());
        		writeElement("RangeOfGoodsCode", item.getRangeOfGoodsCode());
        		writeReferencedSpecification("", item.getReferencedSpecification());
        		writePackaging(item.getPackaging());
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
			
			/*
			if (newCustodyValues.getCustodian() == null) {
				return;
			}			
			if (Utils.isStringEmpty(newCustodyValues.getCustodian().getTIN())) {
				return;
			}			
			openElement("NewCustodyValues");	
				openElement("Custodian");			
					writeElement("TIN", newCustodyValues.getCustodian().getTIN());					
				closeElement();
		    closeElement();
		    */
			if (newCustodyValues.getPlaceOfCustody() == null && 
					newCustodyValues.getCustodian() == null &&
					newCustodyValues.getReferencedSpecification() == null) {
				return;
			}
			
		    openElement("NewCustodyValues");	//EI20130121	
		    if (newCustodyValues.getPlaceOfCustody() != null) {
		    	openElement("PlaceOfCustody");
		    		writeElement("TIN", newCustodyValues.getPlaceOfCustody().getTIN());		    		
					if (newCustodyValues.getPlaceOfCustody().getAddress() != null) {
						openElement("Address"); 
							writeAddress(newCustodyValues.getPlaceOfCustody().getAddress());
						closeElement();
					}
					if (newCustodyValues.getPlaceOfCustody().getReferencedSpecification() != null) {
				    	writeReferencedSpecification("", newCustodyValues.getPlaceOfCustody().getReferencedSpecification());
				    }
				closeElement();
		    }
		    if (newCustodyValues.getCustodian() != null) {
		    	openElement("Custodian");
		    		writeElement("TIN", newCustodyValues.getCustodian().getTIN());		    		
					if (newCustodyValues.getCustodian().getAddress() != null) {
						openElement("Address"); 
							writeAddress(newCustodyValues.getCustodian().getAddress());
						closeElement();
					}
					if (newCustodyValues.getCustodian().getReferencedSpecification() != null) {
				    	writeReferencedSpecification("", newCustodyValues.getCustodian().getReferencedSpecification());
				    }
				closeElement();
		    }
		    if (newCustodyValues.getReferencedSpecification() != null) {
		    	writeReferencedSpecification("", newCustodyValues.getReferencedSpecification());
		    }
			closeElement();	    
	}

	private void writePlaceOfCustodyCity(Address placeOfCustody) throws XMLStreamException {
		if (placeOfCustody == null) {
			return;
		}
		if (Utils.isStringEmpty(placeOfCustody.getCity())) {
			return;
		}
		
		openElement("PlaceOfCustody");
			openElement("Address");
				writeElement("City", placeOfCustody.getCity());
			closeElement();
		closeElement();
	}

	 private void writeAddress(Address address, String adrtype) throws XMLStreamException {
	    	if (address == null) {
	    	    return;
	    	}    	
	    	if (address.isEmpty()) {
	    	    return; 
	     	}
	     	
	     	openElement(adrtype);
	     	 	writeElement("Name", address.getName());
	     	 	writeElement("StreetAndNumber", address.getStreetAndNumber());
	     	 	writeElement("PostalCode", address.getPostalCode());
	     	 	writeElement("City", address.getCity());
	     	 	writeElement("District", address.getDistrict());
	     	 	writeElement("CountryCodeISO", address.getCountryCodeISO());
	     	 	writeElement("PoBox", address.getPoBox());
	        closeElement();
	    }  
}

