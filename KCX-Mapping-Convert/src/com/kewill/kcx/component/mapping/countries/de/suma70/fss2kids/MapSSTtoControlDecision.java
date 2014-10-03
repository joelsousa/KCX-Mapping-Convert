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
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.ReferencedSpecification;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.MsgControlDecision;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.ControlDecision;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.GoodsItemControl;
import com.kewill.kcx.component.mapping.formats.fss.suma.messages.V70.MsgSST;
import com.kewill.kcx.component.mapping.formats.fss.suma.messages.V70.MsgSSTPos;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.TsSSA;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.TsSSI;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.TsSSP;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageManifest20;
import com.kewill.kcx.component.mapping.formats.kids.manifest20.BodyControlDecisionKids;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module		: Manifest<br>
 * Created		: 02.07.2013<br>
 * Description	: Mapping of FSS SST to KIDS ControlDecision.
 * 
 * @author Alfred Krzoska
 * @version 2.0.00
 */
public class MapSSTtoControlDecision extends KidsMessageManifest20 {
	
	private MsgSST msgSST;	
	private MsgControlDecision message;
	
	public MapSSTtoControlDecision() {
		msgSST = new MsgSST();	
		message = new MsgControlDecision();
	}

	public void setMsgSST(MsgSST argument) {
		this.msgSST = argument;						
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
	        header.setHeaderFieldsFromHead(msgSST.getVorSubset(), msgSST.getHeadSubset());
	        
	        header.setMessageName("ControlDecision");
	        header.setMessageID(getMsgID());
	        CommonFieldsDTO commonFieldsDTO = new CommonFieldsDTO();
	        commonFieldsDTO.setKcxId(header.getReceiver());
	        commonFieldsDTO.setCountryCode(header.getCountryCode());
	        commonFieldsDTO.setDirection(EDirections.CountryToCustomer);
	        header.setCommonFieldsDTO(commonFieldsDTO);

	        header.writeHeader();
	        	      
	        BodyControlDecisionKids body   = new BodyControlDecisionKids(writer);
	        body.setMessage(message);

            getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
            body.writeBody();
	        
	        closeElement();  // soap:Envelope
	        writer.writeEndDocument();
	        
	        writer.flush();
	        writer.close();
	        
	        if (Config.getLogXML()) {
	            Utils.log("(MapSSTToControlDecision getMessage) Msg = " + xmlOutputString.toString());
	        }
	        
	    } catch (XMLStreamException e) {	        
	    	e.printStackTrace();
	    }
	    
	    return xmlOutputString.toString();
	}
			
	public void setMsgFields() {	
		if (msgSST.getSSTSubset() != null) {
			message.setReferenceNumber(msgSST.getSSTSubset().getBeznr());		
			message.setRegistrationNumber(msgSST.getSSTSubset().getRegnr());
			message.setDateTimeOfControlDecision(msgSST.getSSTSubset().getBekdat());			
			message.setEdifactNumber(msgSST.getSSTSubset().getEdinr());			
			if (!Utils.isStringEmpty(msgSST.getSSTSubset().getEmpdat())) {	
				String datetime = msgSST.getSSTSubset().getEmpdat();				
				if (datetime.length() == 14) {
					message.setDateTimeOfReceipt(datetime.substring(0, 12));
				} else if (datetime.length() == 12) {
					message.setDateTimeOfReceipt(datetime);
				}
			}
			
			ArrayList<MsgSSTPos> ssplist = msgSST.getPosList(); 
			if (ssplist == null) {
				return;
			}
			if (ssplist.isEmpty()) {
				return;
			}
			for (MsgSSTPos sstPos : ssplist) {
				if (sstPos != null) {
					message.addGoodsItemList(getGoodsItem(sstPos));
				}
			}
			//EI20140314: message.setLocalApplication(this.mapLocalApplication(msgSST.getHeadSubset(), msgSST.getKunSubset(), "CUSSTP"));
			message.setLocalApplication(mapLocalApplication("CUSSTP", msgSST.getHeadSubset(), msgSST.getKunSubset(), msgSST.getKupPosList()));	
			
			if (message.getLocalApplication() != null) {				
				message.setRegistrationDate(message.getLocalApplication().getRegistrationDate());				
			}
		}
    }

	private GoodsItemControl getGoodsItem(MsgSSTPos sstPos) {
		GoodsItemControl item = new GoodsItemControl();
		
		if (sstPos.getSspSubset() != null) {
			item.setItemNumber(sstPos.getSspSubset().getPosnr());
			item.setItemDescription(sstPos.getSspSubset().getWabes());   //EI20131022
			item.setReferenceNumber(sstPos.getSspSubset().getBeznr());
			item.setRegistrationNumber(sstPos.getSspSubset().getRegnr());
			item.setPackagesList(mapPackageList(sstPos.getSspSubset()));
			item.setReferencedSpecification(this.mapReferencedSpecification(sstPos.getSspSubset()));
			item.setControlDecision(this.mapControlDecision(sstPos.getSspSubset(), sstPos.getSsiSubset()));	
		}		
		TIN custodianTIN = mapCustodianTIN(sstPos.getSspSubset());
		Party custodian = mapCustodianAddress(sstPos.getSsaSubset());
		if (custodianTIN != null) {
			if (custodian == null) {
				custodian = new Party();
			}
			custodian.setPartyTIN(custodianTIN);
		}
		item.setCustodian(custodian);
		/*
		if (sstPos.getSsiSubset() != null) {
			item.setAdditionalInformation(sstPos.getSsiSubset().getText());
		}
		 */
		return item;
	}
		
	private ArrayList<Packages> mapPackageList(TsSSP ssp) {
		ArrayList<Packages> list = null;
		if (!Utils.isStringEmpty(ssp.getColanz()) ||
			!Utils.isStringEmpty(ssp.getColart()) ||
			!Utils.isStringEmpty(ssp.getWabes())) {
			
			list = new ArrayList<Packages>();
			Packages packages = new Packages();
			
			packages.setQuantity(ssp.getColanz());
			packages.setType(ssp.getColart());
			packages.setMarks(ssp.getWabes());
		}
		return list;
	}

	private ReferencedSpecification mapReferencedSpecification(TsSSP ssp) {
		
		ReferencedSpecification rs = null;
		
		if (!Utils.isStringEmpty(ssp.getKzawb()) || 
			!Utils.isStringEmpty(ssp.getAwbzzz())) {
				rs = new ReferencedSpecification();
				
				rs.setTypeOfSpecificationID(ssp.getKzawb());
				rs.setSpecificationID(ssp.getAwbzzz());
		}
		
		return rs;
	}

	private ControlDecision mapControlDecision(TsSSP ssp, TsSSI ssi) {
		if (ssp == null) {
			return null;
		}
		
		ControlDecision cd = new ControlDecision();		
		cd.setControlDecisionCode(ssp.getMcode());
		cd.setDeliverableFlag(ssp.getKzzus());
		if (ssi != null) {
			cd.setAdditionalInformation(ssi.getText());
		}		
		return cd;
	}


	private TIN mapCustodianTIN(TsSSP ssp) {
		if (ssp == null) {
			return null;
		}
		
		TIN custodianTIN = null;
		if (!Utils.isStringEmpty(ssp.getVrweori()) || !Utils.isStringEmpty(ssp.getVrwnl())) {
				custodianTIN = new TIN();
				custodianTIN.setTIN(ssp.getVrweori());
				custodianTIN.setBO(ssp.getVrwnl());
		}
		return custodianTIN;
	}

	private Party mapCustodianAddress(TsSSA ssa) {
			if (ssa == null) {
				return null;
			}
			Address address = new Address(); 
			StringBuffer buffer = new StringBuffer();
			Party party = new Party();
			
			buffer.append(ssa.getName1() + ssa.getName2() + ssa.getName3() + ssa.getStr() +
					ssa.getPlz() + ssa.getOrt() + ssa.getLand());
		
			if (buffer.length() > 0) {
				buffer.setLength(0);
				if (ssa.getName1() != null) {
					buffer.append(ssa.getName1());
				}
				
				if (ssa.getName2() != null) {
					buffer.append(ssa.getName2());
				}
				
				if (ssa.getName3() != null) {
					buffer.append(ssa.getName3());
				}

				if (buffer.length() > 0) {
					address.setName(buffer.toString());
				}
				address.setStreet(ssa.getStr());
				address.setPostalCode(ssa.getPlz());
				address.setCity(ssa.getOrt());
				address.setCountry(ssa.getLand());
			}
			
			party.setAddress(address);
			
			return party;
	}
	
	
	
}
