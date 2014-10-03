package com.kewill.kcx.component.mapping.formats.uids.manifest20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.ItemExtension;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.ReferencedSpecification;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.MsgGoodsReleasedInternal;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.GoodsItem;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.NewCustodyValues;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageManifest20;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module 		: Manifest<br>
 * Date Started : 28.08.2013<br>
 * Description  : BodyGoodsReleasedInternal.java Messages.
 * 
 * @author iwaniuk
 * @version 2.0.00
 */

public class BodyGoodsReleasedInternalUids extends UidsMessageManifest20 {	

    private MsgGoodsReleasedInternal  message = new MsgGoodsReleasedInternal();
    
    public BodyGoodsReleasedInternalUids(XMLStreamWriter writer) {
        this.writer = writer;
    }

	public void writeBody() {
		
        try {
        	openElement("soap:Body");
               openElement("Submit");
                   setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/manifest/body/200601");
                 openElement("Manifest");
                    openElement("GoodsReleasedInternal");  
                    	writeStringToDate("DateOfPresentation", message.getDateOfPresentation());
                    	if (message.getTransport() != null) {
                    		writeElement("FlightNumber", message.getTransport().getTransportationNumber());
                    	}
                		writeElement("ReferenceNumber", message.getReferenceNumber());                	
                		writeElement("RegistrationNumber", message.getRegistrationNumber());
                		writeElement("PlaceOfLoading", message.getPlaceOfLoading());
                		writePreviousDocument(message.getPreviousDocument());
                		if (message.getGoodsItemList() != null) {
                			for (GoodsItem goodsItem : message.getGoodsItemList()) {
                				writeGoodsItem(goodsItem);
                			}
                		}

                	closeElement(); // MsgName
                 closeElement(); // Manifest
               closeElement(); // Submit
            closeElement(); // soap:Body
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        } 
    }
	
	 private void writeGoodsItem(GoodsItem goodsItem) throws XMLStreamException {
		if (goodsItem == null) {
			return;
		}
		openElement("GoodsItem");
			writeElement("ItemNumber", goodsItem.getItemNumber());
			if (goodsItem.getCustodian()!= null && goodsItem.getCustodian().getPartyTIN() != null) {
				writeElement("CustodianTIN", goodsItem.getCustodian().getPartyTIN().getTin());
			}
			if (goodsItem.getDisposal() != null && goodsItem.getDisposal().getPartyTIN() != null) {
				writeElement("DisposalTIN", goodsItem.getDisposal().getPartyTIN().getTin());
			}
			//writeElement("BriefCargoDescription", goodsItem.getBriefCargoDescription()); 
			writeElement("BriefCargoDescription", goodsItem.getItemDescription());  //EI20131022
			writeStringToDate("DateOfApplication", goodsItem.getCustodyDeadline());
			writeStringToDate("DateOfReception", goodsItem.getDateTimeOfReceipt());
			writeElement("GrossMass", goodsItem.getGrossMass());
			writePlaceOfCustodyCity(goodsItem.getPlaceOfCustody());
			writeElement("PlaceOfCustodyCode", goodsItem.getPlaceOfCustodyCode());
			writeElement("RangeOfGoodsCode", goodsItem.getRangeOfGoodsCode());
			writeReferencedSpecification(goodsItem.getReferencedSpecification(), "ReferencedSpecification");
			if (goodsItem.getPackagesList() != null && goodsItem.getPackagesList().get(0) != null) {
				writePackaging(goodsItem.getPackagesList().get(0));
			}
			writeNewCustodyValues(goodsItem.getNewCustodyValues());		
			writeItemExtension(goodsItem.getItemExtension());
		closeElement();
	}

	public MsgGoodsReleasedInternal getMsgGoodsReleasedInternal() {
		return message;
	}

	public void setMsgGoodsReleasedInternal(
			MsgGoodsReleasedInternal message) {
		this.message = message;
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
	    if (newCustodyValues.getCustodian() != null && 
	       (newCustodyValues.getCustodian().getTIN() != null || newCustodyValues.getCustodian().getAddress() != null)) {
	    	openElement("Custodian");
	    		if (newCustodyValues.getCustodian().getTIN() != null) {
	    			writeElement("TIN", newCustodyValues.getCustodian().getTIN().getTin());
	    		}
				if (newCustodyValues.getCustodian().getAddress() != null) {					
					writeAddress(newCustodyValues.getCustodian().getAddress(), "Address");					
				}
				//if (newCustodyValues.getCustodian().getReferencedSpecification() != null) {
			    // 	writeReferencedSpecification("", newCustodyValues.getCustodian().getReferencedSpecification());
			    //}
			closeElement();
	    }
	    if (newCustodyValues.getPlaceOfCustody() != null &&
	       (newCustodyValues.getCustodian().getTIN() != null || newCustodyValues.getPlaceOfCustody().getAddress() != null)) {
	    	openElement("PlaceOfCustody");
	    		if (newCustodyValues.getCustodian().getTIN() != null) {
	    			writeElement("TIN", newCustodyValues.getPlaceOfCustody().getTIN().getTin());	
	    		}
				if (newCustodyValues.getPlaceOfCustody().getAddress() != null) {					
					writeAddress(newCustodyValues.getPlaceOfCustody().getAddress(), "Address");					
				}
				//if (newCustodyValues.getPlaceOfCustody().getReferencedSpecification() != null) {
			    //	writeReferencedSpecification("", newCustodyValues.getPlaceOfCustody().getReferencedSpecification());
			    //}
			closeElement();
	    }	    
	    if (newCustodyValues.getReferencedSpecification() != null) {
	    	writeReferencedSpecification("", newCustodyValues.getReferencedSpecification());
	    }
		closeElement();	 
	}

	private void writeItemExtension(ItemExtension itemExtension) throws XMLStreamException {
		if (itemExtension == null) {
			return;
		}

		if (Utils.isStringEmpty(itemExtension.getExternalCode())) {
			return;
		}

		openElement("ItemExtension");
			writeElement("ExternalCode", itemExtension.getExternalCode());
		closeElement();
	}

	private void writePlaceOfCustodyCity(Address placeOfCustodyCity) throws XMLStreamException {
		if (placeOfCustodyCity == null) {
			return;
		}
		if (Utils.isStringEmpty(placeOfCustodyCity.getCity())) {
			return;
		}

		openElement("PlaceOfCustody");
			openElement("Address");
				writeElement("City", placeOfCustodyCity.getCity());
			closeElement();
		closeElement();
	}

	private void writeReferencedSpecification(ReferencedSpecification referencedSpecification,
			  String refType) throws XMLStreamException {
		if (referencedSpecification == null) {
			return;	
		}	

		if (referencedSpecification.isEmpty()) {
			return;
		}

		openElement(refType);
			writeElement("TypeOfSpecificationID", referencedSpecification.getTypeOfSpecificationID());
			writeElement("SpecificationID", referencedSpecification.getSpecificationID());
		closeElement();
	}
	
}
