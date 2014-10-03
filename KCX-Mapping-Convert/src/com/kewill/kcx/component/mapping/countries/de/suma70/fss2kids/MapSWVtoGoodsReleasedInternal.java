package com.kewill.kcx.component.mapping.countries.de.suma70.fss2kids;


import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.ItemExtension;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.ReferencedSpecification;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.MsgGoodsReleasedInternal;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.Custodian;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.GoodsItem;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.NewCustodyValues;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.Transport;
import com.kewill.kcx.component.mapping.formats.fss.suma.messages.V70.MsgSWV;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.TsSWA;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.TsSWP;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageManifest20;
import com.kewill.kcx.component.mapping.formats.kids.manifest20.BodyGoodsReleasedInternalKids;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module		: Manifest<br>
 * Created		: 28.06.2013<br>
 * Description	: Mapping of FSS SWV to KIDS GoodsReleasedInternal.
 * 
 * @author 	Alfred Krzoska
 * @version 2.0.00
 */
public class MapSWVtoGoodsReleasedInternal extends KidsMessageManifest20 {
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
	        //header.setHeaderFields(msgSWV.getVorSubset());
	        header.setHeaderFieldsFromHead(msgSWV.getVorSubset(), msgSWV.getHeadSubset());  //EI20130711
	        header.setMessageName("GoodsReleasedInternal");
	        //header.setMessageID(getMsgID());
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
			if (!Utils.isStringEmpty(msgSWV.getSwvSubset().getBefnum())) {
				Transport trans = new Transport();
				trans.setTransportationNumber(msgSWV.getSwvSubset().getBefnum());
				message.setTransport(trans);
			}
			message.setDateOfArrival(msgSWV.getSwvSubset().getAnkdat());
			message.setReferenceNumber(msgSWV.getSwvSubset().getBeznr());
			message.setRegistrationNumber(msgSWV.getSwvSubset().getRegnr());
			message.setPlaceOfLoading(msgSWV.getSwvSubset().getBelo());
			message.setPreviousDocument(mapPreviousDocument());
			message.setEdifactNumber(msgSWV.getSwvSubset().getEdinr());			
			if (!Utils.isStringEmpty(msgSWV.getSwvSubset().getEmpdat())) {	
				String datetime = msgSWV.getSwvSubset().getEmpdat();				
				if (datetime.length() == 14) {
					message.setDateTimeOfReceipt(datetime.substring(0, 12));
				} else if (datetime.length() == 12) {
					message.setDateTimeOfReceipt(datetime);
				}
			}		
			if (msgSWV.getSwpList() != null) {			
				for (TsSWP swp : msgSWV.getSwpList()) {
					if (swp != null) {
						message.addGoodsItemList(getGoodsItem(swp));
					}
				}
			}
			//EI20140314: message.setLocalApplication(this.mapLocalApplication(msgSWV.getHeadSubset(), msgSWV.getKunSubset(), "CUSTST"));
			message.setLocalApplication(mapLocalApplication("CUSTST", msgSWV.getHeadSubset(), msgSWV.getKunSubset(), msgSWV.getKupPosList()));	
			
		}
    }
	
	private GoodsItem getGoodsItem(TsSWP swp) {
		GoodsItem item = null;
		
		if (!swp.isEmpty()) { 
			item = new GoodsItem();
			item.setItemNumber(swp.getPosnr());			
			item.setCustodian(mapVrwTIN(swp));
			//vwocd, vwobez
			item.setDisposal(mapVfbTIN(swp));
			item.setItemDescription(swp.getWabes()); 
			item.setCustomsStatusOfGoods(swp.getZollst());
			// wakbez, vwzeit,   
			item.setCustodyDeadline(swp.getAnmfri());
			//EI20131021: item.setDateOfReception(swp.getVwdat());	
			String date = "";
			if (!Utils.isStringEmpty(swp.getVwdat())) {
				date = swp.getVwdat();
				if (!Utils.isStringEmpty(swp.getVwzeit())) {
					date = swp.getVwdat() + swp.getVwzeit();
				} else {
					date = swp.getVwdat() + "0000";
				}
			}
			item.setDateTimeOfReceipt(date);		 //EI20131021
			//item.setGrossMass(swp.getRohm());
			item.setGrossMass(Utils.removeZabisDecimalPlaceV7(swp.getRohm(), 3));			
			item.setPlaceOfCustody(mapPlaceOfCustodyCity(swp.getVwobez()));
			item.setPlaceOfCustodyCode(swp.getVrwort());
			item.setRangeOfGoodsCode(swp.getWakr());
			item.setReferencedSpecification(mapReferencedSpecification(swp));
			item.setPackagesList(mapPackageList(swp));
			item.setNewCustodyValues(mapNewCustodyValues());			
			item.setItemExtension(mapItemExtension(swp));
			//frzonekz, vland
		}
		
		return item;
	}

	private Party mapVrwTIN(TsSWP swp) {
		Party party = null;
		TIN tin = null;
		
		if (swp != null && (!Utils.isStringEmpty(swp.getVrweori()) || !Utils.isStringEmpty(swp.getVrwnl()))) {
			tin = new TIN();
			party = new Party();
			tin.setTIN(swp.getVrweori());
			tin.setBO(swp.getVrwnl());	
			party.setPartyTIN(tin);
		}
		
		return party;
	}

	private Party mapVfbTIN(TsSWP swp) {
		TIN tin = null;
		Party party = null;
		
		if (swp != null && (!Utils.isStringEmpty(swp.getVfbeori()) || !Utils.isStringEmpty(swp.getVfbnl()))) {			
			tin = new TIN();
			tin.setTIN(swp.getVfbeori());
			tin.setBO(swp.getVfbnl());	
			party = new Party();
			//EI20140603: tin.setTIN(swp.getVrweori());
			//EI20140603: tin.setBO(swp.getVrwnl());	
			party.setPartyTIN(tin);
		}
		
		return party;
	}
	
	private ReferencedSpecification mapReferencedSpecification(TsSWP swp) {
		ReferencedSpecification referencedSpecification = null;
	
		if (!Utils.isStringEmpty(swp.getAwbzzz()) ||
			!Utils.isStringEmpty(swp.getKzawb())) {
			
			referencedSpecification = new ReferencedSpecification();
			referencedSpecification.setTypeOfSpecificationID(swp.getKzawb());
			referencedSpecification.setSpecificationID(swp.getAwbzzz());
		}
		return referencedSpecification;
	}

	private ArrayList<Packages> mapPackageList(TsSWP swp) {
		ArrayList<Packages> list = null;
		if (!Utils.isStringEmpty(swp.getStkanz()) ||
			!Utils.isStringEmpty(swp.getCollart()) ||
			!Utils.isStringEmpty(swp.getCollbez())) {
			
			list = new ArrayList<Packages>();
			Packages packages = new Packages();
			
			packages.setQuantity(swp.getStkanz());
			packages.setType(swp.getCollart());
			packages.setMarks(swp.getCollbez());
			
			list.add(packages);
		}
		return list;
	}

	private PreviousDocument mapPreviousDocument() {
		PreviousDocument previousDocument = null;
		if (msgSWV.getSwvSubset() != null && (!Utils.isStringEmpty(msgSWV.getSwvSubset().getVorart()) ||
				 							  !Utils.isStringEmpty(msgSWV.getSwvSubset().getVornr()))) {
				previousDocument = new PreviousDocument();
				previousDocument.setType(msgSWV.getSwvSubset().getVorart());
				previousDocument.setReference(msgSWV.getSwvSubset().getVornr());
		}
		return previousDocument;
	}
	
	private NewCustodyValues mapNewCustodyValues() {
		Custodian custodian = null;
		Custodian placeOfCostody = null;
		
		NewCustodyValues newCustodyValues = null;
		
		if (msgSWV.getSwaList() != null) {			
			for (TsSWA swa : msgSWV.getSwaList()) {
				if (swa.getAdrtyp() != null && swa.getAdrtyp().trim().equals("2")) {
					placeOfCostody = new Custodian();
					if (!Utils.isStringEmpty(swa.getEori()) || !Utils.isStringEmpty(swa.getNl()) || 
						!Utils.isStringEmpty(swa.getAdrtyp())) {
						TIN tin = new TIN();
						tin.setTin(swa.getEori());
						tin.setBO(swa.getNl());
						tin.setIdentificationType(swa.getAdrtyp());
						placeOfCostody.setTIN(tin);
						
					}
					placeOfCostody.setAddress(this.getAddress(swa));									
				}
				if (swa.getAdrtyp() != null && swa.getAdrtyp().trim().equals("3")) {					
					custodian = new Custodian();					
					if (!Utils.isStringEmpty(swa.getEori()) || !Utils.isStringEmpty(swa.getNl()) || 
							!Utils.isStringEmpty(swa.getAdrtyp())) {
							TIN tin = new TIN();
							tin.setTin(swa.getEori());
							tin.setBO(swa.getNl());
							tin.setIdentificationType(swa.getAdrtyp());
							custodian.setTIN(tin);
					}
					custodian.setAddress(this.getAddress(swa));									
				}
			}
		}
		if (placeOfCostody != null || custodian != null) {
			newCustodyValues = new NewCustodyValues();		
			newCustodyValues.setCustodian(custodian);
			newCustodyValues.setPlaceOfCustody(placeOfCostody);
		}
		return newCustodyValues;
	}
	
	private Address getAddress(TsSWA swa) {
		if (swa == null) {
			return null;
		}
		Address address = null;
		StringBuffer buff = new StringBuffer();

		buff.append(swa.getName1() + swa.getName2() + swa.getName3() + swa.getStr() +
				swa.getPlz() + swa.getOrt() + swa.getOteil() + swa.getLand() + swa.getEori() +
				swa.getNl());
	
		if (buff.length() > 0) {
			address = new Address();
			buff.setLength(0);
			
			if (swa.getName1() != null) {
				buff.append(swa.getName1());
			}
			
			if (swa.getName2() != null) {
				buff.append(swa.getName2());
			}

			if (swa.getName3() != null) {
				buff.append(swa.getName3());
			}

			address.setName(buff.toString());
			address.setStreet(swa.getStr());
			address.setPostalCode(swa.getPlz());
			address.setCity(swa.getOrt());
			address.setDistrict(swa.getOteil());
			address.setCountry(swa.getLand());
		}
		
		return address;
	}

	private ItemExtension mapItemExtension(TsSWP swp) {
		ItemExtension itemExtension = null;
		
		if (!Utils.isStringEmpty(swp.getFremd())) {
			itemExtension = new ItemExtension();
			
			itemExtension.setExternalCode(swp.getFremd());			
		}
		return itemExtension;
	}

	private Address mapPlaceOfCustodyCity(String vwobez) {
		if (Utils.isStringEmpty(vwobez)) {
			return null;
		}
		Address placeOfCustodyCity = new Address();			
		placeOfCustodyCity.setCity(vwobez);
		placeOfCustodyCity.setName(vwobez);   //EI20140221
		
		return placeOfCustodyCity;
	}
}
