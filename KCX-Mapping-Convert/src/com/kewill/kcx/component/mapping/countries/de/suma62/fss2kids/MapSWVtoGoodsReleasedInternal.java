package com.kewill.kcx.component.mapping.countries.de.suma62.fss2kids;


import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.MsgGoodsReleasedInternal;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.Address;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.Custodian;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.GoodsItem;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.ItemExtension;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.NewCustodyValues;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.PreviousDocument;
//import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.ReferencedSpecification;
import com.kewill.kcx.component.mapping.formats.fss.suma.messages.V62.MsgSWV;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V62.TsSWA;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V62.TsSWP;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.manifest.BodyGoodsReleasedInternalKids;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module		: Manifest<br>
 * Created		: 19.11.2012<br>
 * Description	: Mapping of FSS SWV to KIDS GoodsReleasedExternal.
 * 
 * @author 	Alfred Krzoska
 * @version 1.0.00
 */
public class MapSWVtoGoodsReleasedInternal extends KidsMessage {
	
	private 				MsgSWV msgSWV;	
	private 				MsgGoodsReleasedInternal message;
	
	public MapSWVtoGoodsReleasedInternal() {
		msgSWV = new MsgSWV();	
		message = new MsgGoodsReleasedInternal();
	}

	public void setMsgSWV(MsgSWV argument) {
		this.msgSWV = argument;						
    	this.setMsgFields();    	
    }
	
	public String getMessage() {
	    StringWriter xmlOutputString = new StringWriter();
	    
	    XMLOutputFactory factory = XMLOutputFactory.newInstance();
	    try {
	        writer = factory.createXMLStreamWriter(xmlOutputString);

	        writeStartDocument(encoding, "1.0");
	        openElement("soap:Envelope");
	        setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
	        
	        KidsHeader  header = new KidsHeader(writer);
	        header.setHeaderFields(msgSWV.getVorSubset());
	        header.setMessageName("GoodsReleasedInternal");
	        header.setMessageID(getMsgID());
	        CommonFieldsDTO commonFieldsDTO = new CommonFieldsDTO();
	        commonFieldsDTO.setKcxId(header.getReceiver());
	        commonFieldsDTO.setCountryCode(header.getCountryCode());
	        commonFieldsDTO.setDirection(EDirections.CountryToCustomer);
	        header.setCommonFieldsDTO(commonFieldsDTO);

	        header.writeHeader();
	        	      
	        BodyGoodsReleasedInternalKids body   = new BodyGoodsReleasedInternalKids(writer);
	        body.setMessage(message);

            getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
            body.writeBody();
	        
	        closeElement();  // soap:Envelope
	        writer.writeEndDocument();
	        
	        writer.flush();
	        writer.close();
	        
	        if (Config.getLogXML()) {
	            Utils.log("(MapSWVToGoodsReleasedInternal getMessage) Msg = " + xmlOutputString.toString());
	        }
	        
	    } catch (XMLStreamException e) {	        
	    	e.printStackTrace();
	    }
	    
	    return xmlOutputString.toString();
	}
			
	public void setMsgFields() {	
	
		if (msgSWV.getSwvSubset() != null) {
			message.setDateOfPresentation(msgSWV.getSwvSubset().getGstdat());
			message.setFlightNumber(msgSWV.getSwvSubset().getFltnum());
			message.setReferenceNumber(msgSWV.getSwvSubset().getBeznr());
			message.setRegistrationNumber(msgSWV.getSwvSubset().getRegnr());
			message.setPlaceOfLoading(msgSWV.getSwvSubset().getBelo());
			message.setPreviousDocument(getPreviousDocument());
			
			ArrayList<TsSWP> swpList = msgSWV.getSwpList(); 
			if (swpList == null) {
				return;
			}
			if (swpList.isEmpty()) {
				return;
			}
			
			for (TsSWP swp : swpList) {
				if (swp != null) {
					message.addGoodsItemList(getGoodsItem(swp));
				}
			}
		}
    }
	

	private GoodsItem getGoodsItem(TsSWP swp) {
		GoodsItem item = null;
		
		if (!swp.isEmpty()) { 
			item = new GoodsItem();
			item.setItemNumber(swp.getPosnr());
			item.setCustodianTIN(swp.getVrwznr());
			item.setDisposalTIN(swp.getVfbznr());
			item.setBriefCargoDescription(swp.getWabes());
			item.setDateOfApplication(swp.getAnmfri());
			item.setDateOfReception(swp.getVwdat());
			item.setGrossMass(swp.getRohm());
			item.setPlaceOfCustody(setPlaceOfCustodyCity(swp.getVwobez()));
			item.setPlaceOfCustodyCode(swp.getVwocd());
			item.setRangeOfGoodsCode(swp.getWakr());
			item.setReferencedSpecification(getReferencedSpecification(swp));
			item.setPackaging(getPackaging(swp));
			item.setNewCustodyValues(getNewCustodyValues());			
			item.setItemExtension(getItemExtension(swp));
		}
		
		return item;
	}

	private Packages getPackaging(TsSWP swp) {
		Packages packaging = null;
		if (!Utils.isStringEmpty(swp.getStkanz()) ||
			!Utils.isStringEmpty(swp.getCollart()) ||
			!Utils.isStringEmpty(swp.getCollart())) {
			
			packaging = new Packages();
			packaging.setQuantity(swp.getStkanz());
			packaging.setMarks(swp.getCollbez());
			packaging.setType(swp.getCollart());
		}
		
		return packaging;
	}

	private ReferencedSpecification getReferencedSpecification(TsSWP swp) {
		ReferencedSpecification referencedSpecification = null;
	
		if (!Utils.isStringEmpty(swp.getAwbzzz()) ||
			!Utils.isStringEmpty(swp.getKzawb())) {
			
			referencedSpecification = new ReferencedSpecification();
			referencedSpecification.setTypeOfSpecificationID(swp.getKzawb());
			referencedSpecification.setSpecificationID(swp.getAwbzzz());
		}
		
		return referencedSpecification;
	}

	private PreviousDocument getPreviousDocument() {
		PreviousDocument previousDocument = null;
		if (msgSWV.getSwvSubset() != null && (!Utils.isStringEmpty(msgSWV.getSwvSubset().getVorart()) ||
				 							  !Utils.isStringEmpty(msgSWV.getSwvSubset().getVornr()))) {
				previousDocument = new PreviousDocument();
				previousDocument.setType(msgSWV.getSwvSubset().getVorart());
				//previousDocument.setReference(msgSWV.getSwvSubset().getVornr());
				//previousDocument.setMarks(msgSWV.getSwvSubset().getVornr());            //EI20130118
				previousDocument.setReference(msgSWV.getSwvSubset().getVornr());
		}
		return previousDocument;
	}
	
	private NewCustodyValues getNewCustodyValues() {
		Custodian custodian = null;
		Custodian placeOfCostody = null;
		ReferencedSpecification referencedSpecification = null;
		
		NewCustodyValues newCustodyValues = null;
		
		if (msgSWV.getSwaList() != null) {			
			for (TsSWA swa : msgSWV.getSwaList()) {
				if (swa.getAdrtyp() != null && swa.getAdrtyp().trim().equals("2")) {
					placeOfCostody = new Custodian();
					placeOfCostody.setTIN(swa.getVrwznr());					
					placeOfCostody.setAddress(this.getAddress(swa));										
					if (!Utils.isStringEmpty(swa.getKzawb()) || !Utils.isStringEmpty(swa.getAwbzzz())) {
						referencedSpecification = new ReferencedSpecification();
						referencedSpecification.setSpecificationID(swa.getAwbzzz());
						referencedSpecification.setTypeOfSpecificationID(swa.getKzawb());
						placeOfCostody.setReferencedSpecification(referencedSpecification);
					}
				}
				if (swa.getAdrtyp() != null && swa.getAdrtyp().trim().equals("3")) {					
					custodian = new Custodian();					
					custodian.setTIN(swa.getVrwznr());
					custodian.setAddress(this.getAddress(swa));
					if (!Utils.isStringEmpty(swa.getKzawb()) || !Utils.isStringEmpty(swa.getAwbzzz())) {
						ReferencedSpecification ref = new ReferencedSpecification();
						ref.setSpecificationID(swa.getAwbzzz());
						ref.setTypeOfSpecificationID(swa.getKzawb());					
						custodian.setReferencedSpecification(ref);		
					}
				}
			}
		}
		if (placeOfCostody != null || custodian != null || referencedSpecification != null) {
			newCustodyValues = new NewCustodyValues();		
			newCustodyValues.setCustodian(custodian);
			newCustodyValues.setPlaceOfCustody(placeOfCostody);
			newCustodyValues.setReferencedSpecification(referencedSpecification);
		}
		return newCustodyValues;
	}
	
	// AK20121130
	/****
	private void setGoodsItemFromSWA(GoodsItem item, String posnr) {

		Address custodian = null;
		Address place	  = null;
		//AK20121130
		//ReferencedSpecification referencedSpecification2 = null;

		ArrayList<TsSWA> swaList = msgSWV.getSwaList();
		if (swaList != null) {
			for (TsSWA swa : swaList) {
				if (swa.getPosnr().trim().equals(posnr)) {
					if (swa.getAdrtyp() != null && swa.getAdrtyp().equals("3")) {
						custodian = getAddress(swa);
					}
					if (swa.getAdrtyp() != null && swa.getAdrtyp().equals("2")) {
						place = getAddress(swa);
					}
					if (!Utils.isStringEmpty(swa.getKzawb().trim()) ||
						!Utils.isStringEmpty(swa.getAwbzzz().trim())) {
						referencedSpecification2 = new ReferencedSpecification();
						
						referencedSpecification2.setTypeOfSpecificationID(swa.getKzawb());
						referencedSpecification2.setSpecificationID(swa.getAwbzzz());
					}
				}
			}
		}
		item.setCustodian(custodian);
		item.setPlaceOfCustody(place);
		item.setReferencedSpecification2(referencedSpecification2);  
	}****/ 
	
	private Address getAddress(TsSWA swa) {
		Address address = null;
		StringBuffer buff = new StringBuffer();

		buff.append(swa.getName1() + swa.getName2() + swa.getName3() + 
					swa.getStr() + swa.getPlz() + swa.getOrt() + swa.getOteil() +
				    swa.getPostf());
	

		if (!Utils.isStringEmpty(buff.toString())) {
			address = new Address();
			address.setName(swa.getName1() + " " + swa.getName2() + " " + swa.getName3());
			address.setStreetAndNumber(swa.getStr());
			address.setPostalCode(swa.getPlz());
			address.setCity(swa.getOrt());
			address.setDistrict(swa.getOteil());
			address.setCountryCodeISO(swa.getLdkz());
			address.setPoBox(swa.getPostf());		
		}
		

		return address;
	}

	private ItemExtension getItemExtension(TsSWP swp) {
		ItemExtension itemExtension = null;
		
		if (!Utils.isStringEmpty(swp.getFremd())) {
			itemExtension = new ItemExtension();
			
			itemExtension.setExternalCode(swp.getFremd());
		}
		
		return itemExtension;
	}
	
	private Address setPlaceOfCustodyCity(String vwobez) {
		if (Utils.isStringEmpty(vwobez)) {
			return null;
		}
		Address placeOfCustodyCity = new Address();			
		placeOfCustodyCity.setCity(vwobez);
		
		return placeOfCustodyCity;
	}
	
}
