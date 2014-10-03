package com.kewill.kcx.component.mapping.countries.de.ncts.fss2kids;

import java.io.StringWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSWriteOffNotification;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.AddressNCTS;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.FunctionalErrorNcts;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.PartyNCTS;
import com.kewill.kcx.component.mapping.formats.fss.ncts.messages.MsgVSO;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVFP;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVGP;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVKA;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVSO;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.ncts.BodyNCTSWriteOffNotificationKids;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module		: MapVSOToNCTSWriteOffNotification<br>
 * Created		: 11.05.2011<br>
 * Description	: Mapping of FSS to KIDS format of VSO.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MapVSOToNCTSWriteOffNotification extends KidsMessage {
	
	private MsgVSO msgVSO;	
	private MsgNCTSWriteOffNotification msgNCTSWriteOffNotification;
	
	public MapVSOToNCTSWriteOffNotification() {
		msgVSO = new MsgVSO();	
		msgNCTSWriteOffNotification = new MsgNCTSWriteOffNotification();
	}

	public void setMsgVSO(MsgVSO argument) {
		this.msgVSO = argument;						
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
	        header.setHeaderFields(msgVSO.getVorSubset());
	        header.setMessageName("NCTSWriteOffNotification");
	        header.setMessageID(getMsgID());
	        CommonFieldsDTO commonFieldsDTO = new CommonFieldsDTO();
	        commonFieldsDTO.setKcxId(header.getReceiver());
	        commonFieldsDTO.setCountryCode(header.getCountryCode());
	        commonFieldsDTO.setDirection(EDirections.CountryToCustomer);
	        header.setCommonFieldsDTO(commonFieldsDTO);

	        header.writeHeader();
	        	      
	        BodyNCTSWriteOffNotificationKids body   = new BodyNCTSWriteOffNotificationKids(writer);
	        body.setMessage(msgNCTSWriteOffNotification);

            getCommonFieldsDTO().setReferenceNumber(msgNCTSWriteOffNotification.getReferenceNumber());
            body.writeBody();
	        
	        closeElement();  // soap:Envelope
	        writer.writeEndDocument();
	        
	        writer.flush();
	        writer.close();
	        
	        if (Config.getLogXML()) {
	            Utils.log("(MapVSOToNCTSWriteOffNotification getMessage) Msg = " + xmlOutputString.toString());
	        }
	        
	    	} catch (XMLStreamException e) {
	        
	    		e.printStackTrace();
	    	}
	    
	    	return xmlOutputString.toString();
		}
			
	public void setMsgFields() {	
		
		if (msgVSO.getVsoSubset() != null) {
			TsVSO vsoTs = msgVSO.getVsoSubset();
			msgNCTSWriteOffNotification.setReferenceNumber(vsoTs.getBeznr());
			msgNCTSWriteOffNotification.setTemporaryUCR(vsoTs.getArbnr());
			msgNCTSWriteOffNotification .setUCRNumber(vsoTs.getMrn());
			msgNCTSWriteOffNotification.setStatusOfControl(vsoTs.getStacde());
			msgNCTSWriteOffNotification.setReceiveTime(vsoTs.getAckdat());
			msgNCTSWriteOffNotification.setReceiveTimeFormat(EFormat.KIDS_DateTime); //EI20110527
			msgNCTSWriteOffNotification.setAcceptanceTime(vsoTs.getAndat() + vsoTs.getAntime());
			msgNCTSWriteOffNotification.setAcceptanceTimeFormat(EFormat.KIDS_DateTime); //EI20110527
			msgNCTSWriteOffNotification.setReleaseTime(vsoTs.getUebdat() + vsoTs.getUebtim());
			msgNCTSWriteOffNotification.setReleaseTimeFormat(EFormat.KIDS_DateTime); //EI20110527
			msgNCTSWriteOffNotification.setCancellationTime(vsoTs.getStodat());	
			msgNCTSWriteOffNotification.setCancellationTimeFormat(EFormat.KIDS_DateTime); //EI20110527
			msgNCTSWriteOffNotification.setBeginTimeOfProcessing(vsoTs.getBwbdat());
			msgNCTSWriteOffNotification.setBeginProcessingTimeFormat(EFormat.KIDS_DateTime); //EI20110527
			msgNCTSWriteOffNotification.setStatusInformation(vsoTs.getText());	
			msgNCTSWriteOffNotification.setCompletionTime(vsoTs.getOkdat());	
			msgNCTSWriteOffNotification.setCompletionTimeFormat(EFormat.KIDS_DateTime); //EI20110527
			msgNCTSWriteOffNotification.setReferencedAmountsCharging(vsoTs.getGuadat());		
			msgNCTSWriteOffNotification.setReferencedAmountsChargingFormat(EFormat.KIDS_DateTime); //EI20110527
		}
		if (msgVSO.getVkaSubset() != null && msgVSO.getVkaSubset().getAdrtyp().equals("3")) {
			msgNCTSWriteOffNotification.setPrincipal(getParty(msgVSO.getVkaSubset(), "Principal"));
		}	
		
		if (msgVSO.getVrlSubset() != null) {
			msgNCTSWriteOffNotification.setWriteOffType(msgVSO.getVrlSubset().getErlart());
			msgNCTSWriteOffNotification.setWriteOffText(msgVSO.getVrlSubset().getText());
			msgNCTSWriteOffNotification.setUseOfTydenSeals(msgVSO.getVrlSubset().getKztyd());
			msgNCTSWriteOffNotification.setRepresentativeName(msgVSO.getVrlSubset().getSb());
		}
		
		if (msgVSO.getVgpList() != null) {
			for (TsVGP vgpTs : msgVSO.getVgpList()) {
				FunctionalErrorNcts grn = new FunctionalErrorNcts();
				grn.setGRN(vgpTs.getSicbsc());
				grn.setCode(vgpTs.getGuacde());
				grn.setText(vgpTs.getGuatxt());
				msgNCTSWriteOffNotification.addGrnErrorList(grn);
			}
		}
		
		if (msgVSO.getVfpList() != null) {
			for (TsVFP vfpTs : msgVSO.getVfpList()) {
				FunctionalErrorNcts error = new FunctionalErrorNcts();				
				error.setCode(vfpTs.getErrcde());
				error.setText(vfpTs.getErrtxt());
				error.setPointer(vfpTs.getZeiger());
				msgNCTSWriteOffNotification.addFunctionalErrorList(error);
			}
		}
    }
	
	private PartyNCTS getParty(TsVKA subset, String person) {		
		if (subset == null) {
			return null;
		}
		if (subset.isEmpty()) {
			return null;
		}		
		PartyNCTS party = new PartyNCTS(person);		
		AddressNCTS address = new AddressNCTS();		
		address.setName(subset.getName());
		address.setStreet(subset.getStr());
		address.setCity(subset.getOrt());
		address.setPostalCode(subset.getPlz());
		address.setCountry(subset.getLand());	
		
		party.setAddress(address);		
		return party;
	}
}
