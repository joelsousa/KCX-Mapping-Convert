package com.kewill.kcx.component.mapping.countries.de.ncts20.fss2kids;

import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.AddressNCTS;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.FunctionalErrorNcts;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.PartyNCTS;
import com.kewill.kcx.component.mapping.countries.de.ncts20.msg.MsgNCTSWriteOffNotification;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.LocalAppPosition;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.LocalApplication;
import com.kewill.kcx.component.mapping.formats.fss.ncts.messages.V70.MsgVSO;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.V70.TsVEP;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.V70.TsVER;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.V70.TsVSP;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVFP;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVGP;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVKA;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVSO;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.ncts20.BodyNCTSWriteOffNotificationKids;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module		: NCTS<br>
 * Created		: 11.05.2011<br>
 * Description	: Mapping of FSS-VSO to KIDS-NCTSWriteOffNotification.
 *              : V70 new TsVSP = Ansprechpartner == Contact
 * 
 * @author iwaniuk
 * @version 4.1.00
 */
public class MapVSOToNCTSWriteOffNotification extends KidsMessage {
	
	private MsgVSO msgVSO;	
	private MsgNCTSWriteOffNotification message;
	
	public MapVSOToNCTSWriteOffNotification() {
		msgVSO = new MsgVSO();	
		message = new MsgNCTSWriteOffNotification();
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
	        //header.setHeaderFields(msgVSO.getVorSubset());
	        header.setHeaderFieldsFromHead(msgVSO.getVorSubset(), msgVSO.getHeadSubset());  //EI20130711
	        header.setMessageName("NCTSWriteOffNotification");
	        header.setMessageID(getMsgID());
	        CommonFieldsDTO commonFieldsDTO = new CommonFieldsDTO();
	        commonFieldsDTO.setKcxId(header.getReceiver());
	        commonFieldsDTO.setCountryCode(header.getCountryCode());
	        commonFieldsDTO.setDirection(EDirections.CountryToCustomer);
	        header.setCommonFieldsDTO(commonFieldsDTO);

	        header.writeHeader();
	        	      
	        BodyNCTSWriteOffNotificationKids body   = new BodyNCTSWriteOffNotificationKids(writer);
	        body.setMessage(message);

            getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
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
		String beznr = "";                 //EI20140212
		
		if (msgVSO.getVsoSubset() != null) {
			TsVSO vsoTs = msgVSO.getVsoSubset();
			message.setReferenceNumber(vsoTs.getBeznr());
			beznr = vsoTs.getBeznr();
			message.setTemporaryUCR(vsoTs.getArbnr());
			message.setUCRNumber(vsoTs.getMrn());
			message.setStatusOfControl(vsoTs.getStacde());
			message.setReceiveTime(vsoTs.getAckdat());
			message.setReceiveTimeFormat(EFormat.KIDS_DateTime); //EI20110527
			message.setAcceptanceTime(vsoTs.getAndat() + vsoTs.getAntime());
			message.setAcceptanceTimeFormat(EFormat.KIDS_DateTime); //EI20110527
			message.setReleaseTime(vsoTs.getUebdat() + vsoTs.getUebtim());
			message.setReleaseTimeFormat(EFormat.KIDS_DateTime); //EI20110527
			message.setCancellationTime(vsoTs.getStodat());	
			message.setCancellationTimeFormat(EFormat.KIDS_DateTime); //EI20110527
			message.setBeginTimeOfProcessing(vsoTs.getBwbdat());
			message.setBeginProcessingTimeFormat(EFormat.KIDS_DateTime); //EI20110527
			message.setStatusInformation(vsoTs.getText());	
			message.setCompletionTime(vsoTs.getOkdat());	
			message.setCompletionTimeFormat(EFormat.KIDS_DateTime); //EI20110527
			message.setReferencedAmountsCharging(vsoTs.getGuadat());		
			message.setReferencedAmountsChargingFormat(EFormat.KIDS_DateTime); //EI20110527
		}
		if (msgVSO.getVkaSubset() != null && msgVSO.getVkaSubset().getAdrtyp().equals("3")) {
			message.setPrincipal(getParty(msgVSO.getVkaSubset(), "Principal"));
		}	
		if (msgVSO.getVspSubset() != null) {     //EI20130207: V70 new			
			message.setContact(this.getContact(msgVSO.getVspSubset()));	
		}
		if (msgVSO.getVrlSubset() != null) {
			message.setWriteOffType(msgVSO.getVrlSubset().getErlart());
			message.setWriteOffText(msgVSO.getVrlSubset().getText());
			message.setUseOfTydenSeals(msgVSO.getVrlSubset().getKztyd());
			message.setRepresentativeName(msgVSO.getVrlSubset().getSb());
		}
		
		if (msgVSO.getVgpList() != null) {
			for (TsVGP vgpTs : msgVSO.getVgpList()) {
				FunctionalErrorNcts grn = new FunctionalErrorNcts();
				grn.setGRN(vgpTs.getSicbsc());
				grn.setCode(vgpTs.getGuacde());
				grn.setText(vgpTs.getGuatxt());
				message.addGrnErrorList(grn);
			}
		}
		
		if (msgVSO.getVfpList() != null) {
			for (TsVFP vfpTs : msgVSO.getVfpList()) {
				FunctionalErrorNcts error = new FunctionalErrorNcts();				
				error.setCode(vfpTs.getErrcde());
				error.setText(vfpTs.getErrtxt());
				error.setPointer(vfpTs.getZeiger());
				message.addFunctionalErrorList(error);
			}
		}
		
		message.setLocalApplication(this.mapLocalApplication("TUFSTA", beznr, msgVSO.getVerSubset(), msgVSO.getVepList()));  //EI20130712: CMP/LCAG
		
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
	
	private ContactPerson getContact(TsVSP subset) {		
		if (subset == null) {
			return null;
		}
		if (subset.isEmpty()) {
			return null;
		}	
		ContactPerson contact = new ContactPerson();
		contact.setName(msgVSO.getVspSubset().getSbname());
		contact.setClerk((msgVSO.getVspSubset().getSbname()));
		contact.setPosition(msgVSO.getVspSubset().getStellg());
		contact.setPhoneNumber(msgVSO.getVspSubset().getTelnr());
		contact.setFaxNumber(msgVSO.getVspSubset().getFaxnr());
		contact.setEmail(msgVSO.getVspSubset().getEmail());		
		
		return contact;
	}
	
	private LocalApplication mapLocalApplication(String messageName, String beznr, TsVER ver,  
			ArrayList<TsVEP> vepList) { //EI20140314
		if (ver == null) {
			return null;
		}		
		if (beznr == null) {
			return null;
		}	
		LocalApplication app = new LocalApplication();
		
		app.setMessageType(messageName); //TUFSTA, (CUSREC)
		app.setMessageSubType("NCTS");		//EI20140128 TODO-Max: ist es NCTS?
		if (msgVSO.getVgpList() == null && msgVSO.getVfpList() == null) { //EI20140128
			app.setMessageFunction("INF");		//ERR, INF, 
		} else {
			app.setMessageFunction("ERR");
		}
		app.setRegistrationNumber(ver.getSponr());  //EI20140128
		app.setRegistrationDate(ver.getUebdat());	//EI20140128 
		//EI20140306: DeclarationStatus muss aus TsVER kommen, denn getStacde liefert ZollStatus
		//if (msgVSO.getVsoSubset() != null) {        //EI20140128
		//	app.setDeclarationStatus(msgVSO.getVsoSubset().getStacde());     //13, 15, 37, 38	
		//}
		app.setDeclarationStatus(ver.getStatus());  //EI20140306
		//app.setPositionStatus("");	
		
		/*  EI20140212: jetzt kann man die drei Werte aus beznr ermitteln (der "kleine Konverter macht das falsch)
		app.setFlightNumber(ver.getFlugnr());       //EI20140128   
		app.setAirportOfArrival(ver.getArrcode()); 	//EI20140128
		app.setDepartureDate(ver.getUebdat());      //EI20140128
		*/		
		String flnr = calculateFlightNumber(beznr);       //EI20140212 		
		app.setFlightNumber(flnr);						  //EI20140212	
		app.setAirportOfArrival(this.calculateArrivalCode(beznr)); //EI20140212
		app.setDepartureDate(this.calculateDepartureDate(beznr, flnr));  //EI20140212
		
		app.setArrivalDate(ver.getWgdat());         //EI20140128
		
		if (msgVSO.getHeadSubset() != null && msgVSO.getHeadSubset().getNl() != null) {     //EI20140128
			//app.setAirportOfDeparture(msgVSO.getHeadSubset().getNl());	
			String departureCode = msgVSO.getHeadSubset().getNl().trim();    //EI20140214
			if (departureCode != null && departureCode.equals("TST")) {
				departureCode = "FRA";
			}
			if (departureCode != null && departureCode.equals("TVI")) {
				departureCode = "VIE";
			}
			if (departureCode != null && departureCode.equals("TMU")) {
				departureCode = "MUE";
			}
			app.setAirportOfDeparture(departureCode);			
		}		
		
		if (vepList != null) {   //EI20140314
			for (TsVEP vep : vepList) {
				LocalAppPosition appPos = new LocalAppPosition();
				appPos.setPositionNumber(vep.getPosnr());
				appPos.setPositionStatus(vep.getStatus());
				appPos.setPositionAWB(vep.getAwb());
				app.addPositionList(appPos);
			}
		}
		return app;		
	}
	
	public String calculateFlightNumber(String beznr) {    //EI20140212
		String flightNumber = "";
			
		if (Utils.isStringEmpty(beznr)) {
			return "";
		}
		int len = beznr.length();
		if (len < 14) {       //5/6/6 + 6 + 3
			return "";
		}
		if (!Utils.isSignNumeric(beznr.substring(6, 7))) { //7 + 6 + 3
			flightNumber = beznr.substring(0, 7);
		} else {
			if (len == 14) {
				flightNumber = beznr.substring(0, 5); //5 + 6 + 3
			} else if (len == 15) {
				flightNumber = beznr.substring(0, 6); //6 + 6 + 3
			} 
		}
		
		return flightNumber;
	}
	
	public String calculateDepartureDate(String referenceNumber, String flugnr)  {  //EI20140212
		String departureDate = "";
		String date = "";     //ddmmjj
		
		if (referenceNumber == null) {  // fnr + ddmmjj + ABC
			return "";
		} 
		if (flugnr == null) {
			return "";
		}
		referenceNumber = referenceNumber.trim();
		flugnr = flugnr.trim();
		
		if (Utils.isStringEmpty(referenceNumber)) {
			return "";
		}
		if (Utils.isStringEmpty(flugnr)) {
			return "";
		}
		
		int lenFnr = flugnr.length();
		int lenRef = referenceNumber.length();
		if (lenRef < 14) {
			return "";
		}
		
		String rest = "";   //EI20140704
		int len = 0;  		//EI20140704
		String dd = "";     //EI20140704
		String mm = "";     //EI20140704
		String yy = "";     //EI20140704
		if (lenFnr == 5) {								
			//date = referenceNumber.substring(5, 11);  
			rest = referenceNumber.substring(5); 			
		} else if (lenFnr == 6) {
			//date = referenceNumber.substring(6, 12);   
			rest = referenceNumber.substring(6);
		} else if (lenFnr == 7) {		
			//date = referenceNumber.substring(7, 13);
			rest = referenceNumber.substring(7);
		} 
		len = rest.length();
		if (len > 8) {   //ddmmyy+ABC
			date = rest.substring(0, len - 3);			
		}
		len = date.length();
		if (len == 6 && Utils.isNumeric(date)) {
			dd = date.substring(0, 2);
			mm = date.substring(2, 4);
			yy = date.substring(4, 6);
			departureDate = "20" + yy + mm + dd;
		} else if (len == 7) {
			String mon = "";
			dd = date.substring(0, 2);
			mon = date.substring(2, 5);
			yy = date.substring(5, 7);
			if (Utils.isNumeric(dd) &&  Utils.isNumeric(yy) && Utils.isStringAlpha(mon)) {
				mm = this.mapMonthToMM(mon);
				departureDate = "20" + yy + mm + dd;
			}
		}
		return departureDate;
	}
	
	public String calculateArrivalCode(String referenceNumber)   {  //EI20140212
		if (referenceNumber == null) {
			return "";
		}		
		referenceNumber = referenceNumber.trim();
		if (Utils.isStringEmpty(referenceNumber)) {
			return "";
		}		
		int len = referenceNumber.length();			
		if (len < 14) {				
			return "";
		}				
		return referenceNumber.substring(len - 3, len);
	}
	public String mapMonthToMM(String month) {  //EI20140704 kopiert von KidsMessageManifest20
			String mm = "";
			if (Utils.isStringEmpty(month)) {
				return "";
			}
			if (month.equalsIgnoreCase("JAN")) {
				mm = "01";
			} else if (month.equalsIgnoreCase("FEB")) {
				mm = "02";
			} else if (month.equalsIgnoreCase("MAR")) {
				mm = "03";
			} else if (month.equalsIgnoreCase("ARP")) {
				mm = "04";
			} else if (month.equalsIgnoreCase("MAY")) {
				mm = "05";
			} else if (month.equalsIgnoreCase("JUN")) {
				mm = "06";
			} else if (month.equalsIgnoreCase("JUL")) {
				mm = "07";
			}  else if (month.equalsIgnoreCase("AUG")) {
				mm = "08";
			} else if (month.equalsIgnoreCase("SEP")) {
				mm = "09";
			} else if (month.equalsIgnoreCase("OCT")) {
				mm = "10";
			} else if (month.equalsIgnoreCase("NOV")) {
				mm = "11";
			} else if (month.equalsIgnoreCase("DEC")) {
				mm = "12";
			} else {
				mm = month;
			}
			return mm;
		}
}
