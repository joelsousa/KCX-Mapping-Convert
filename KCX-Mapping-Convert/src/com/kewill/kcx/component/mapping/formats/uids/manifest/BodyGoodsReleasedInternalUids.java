package com.kewill.kcx.component.mapping.formats.uids.manifest;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.MsgGoodsReleasedInternal;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.Address;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.GoodsItem;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.ItemExtension;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.NewCustodyValues;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.ReferencedSpecification;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageManifest;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module 		: Manifest<br>
 * Date Started : 12.11.2012
 * Description  : BodyGoodsReleasedInternal.java Messages.
 * 
 * @author kron
 * @version 1.0.00
 */

public class BodyGoodsReleasedInternalUids extends UidsMessageManifest {	

    private MsgGoodsReleasedInternal  msgGoodsReleasedInternal = new MsgGoodsReleasedInternal();
    
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
                    	writeStringToDate("DateOfPresentation", msgGoodsReleasedInternal.getDateOfPresentation());
                    	writeElement("FlightNumber", msgGoodsReleasedInternal.getFlightNumber());
                		writeElement("ReferenceNumber", msgGoodsReleasedInternal.getReferenceNumber());                	
                		writeElement("RegistrationNumber", msgGoodsReleasedInternal.getRegistrationNumber());
                		writeElement("PlaceOfLoading", msgGoodsReleasedInternal.getPlaceOfLoading());
                		writePreviousDocument(msgGoodsReleasedInternal.getPreviousDocument());
                		if (msgGoodsReleasedInternal.getGoodsItemList() != null) {
                			for (GoodsItem goodsItem : msgGoodsReleasedInternal.getGoodsItemList()) {
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
			writeElement("CustodianTIN", goodsItem.getCustodianTIN());
			writeElement("DisposalTIN", goodsItem.getDisposalTIN());
			writeElement("BriefCargoDescription", goodsItem.getBriefCargoDescription());
			writeStringToDate("DateOfApplication", goodsItem.getDateOfApplication());
			writeStringToDate("DateOfReception", goodsItem.getDateOfReception());
			writeElement("GrossMass", goodsItem.getGrossMass());
			writePlaceOfCustodyCity(goodsItem.getPlaceOfCustody());
			writeElement("PlaceOfCustodyCode", goodsItem.getPlaceOfCustodyCode());
			writeElement("RangeOfGoodsCode", goodsItem.getRangeOfGoodsCode());
			writeReferencedSpecification(goodsItem.getReferencedSpecification(), "ReferencedSpecification");
			writePackaging(goodsItem.getPackaging());
			writeNewCustodyValues(goodsItem.getNewCustodyValues());		
			writeItemExtension(goodsItem.getItemExtension());
		closeElement();
	}

	public MsgGoodsReleasedInternal getMsgGoodsReleasedInternal() {
		return msgGoodsReleasedInternal;
	}

	public void setMsgGoodsReleasedInternal(
			MsgGoodsReleasedInternal msgGoodsReleasedInternal) {
		this.msgGoodsReleasedInternal = msgGoodsReleasedInternal;
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
		
	    openElement("NewCustodyValues");	//EI20130121	
	    if (newCustodyValues.getCustodian() != null) {
	    	openElement("Custodian");
	    		writeElement("TIN", newCustodyValues.getCustodian().getTIN());		    		
				if (newCustodyValues.getCustodian().getAddress() != null) {
					openElement("Address"); 
						writeAddress(newCustodyValues.getCustodian().getAddress(), "Custodian");
					closeElement();
				}
				//if (newCustodyValues.getCustodian().getReferencedSpecification() != null) {
			    // 	writeReferencedSpecification("", newCustodyValues.getCustodian().getReferencedSpecification());
			    //}
			closeElement();
	    }
	    if (newCustodyValues.getPlaceOfCustody() != null) {
	    	openElement("PlaceOfCustody");
	    		writeElement("TIN", newCustodyValues.getPlaceOfCustody().getTIN());		    		
				if (newCustodyValues.getPlaceOfCustody().getAddress() != null) {
					openElement("Address"); 
						writeAddress(newCustodyValues.getPlaceOfCustody().getAddress(), "PlaceOfCustody");
					closeElement();
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
