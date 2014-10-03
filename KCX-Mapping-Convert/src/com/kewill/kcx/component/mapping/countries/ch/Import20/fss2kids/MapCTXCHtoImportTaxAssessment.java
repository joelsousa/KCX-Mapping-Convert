package com.kewill.kcx.component.mapping.countries.ch.Import20.fss2kids;

import java.io.StringWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.MsgImportTaxAssessment;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Account;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Duties;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.GoodsItemTaxAssessment;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Office;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Traders;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.messages.V70.MsgCTXCH;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.V70.TsABP;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.V70.TsADR;
import com.kewill.kcx.component.mapping.formats.kids.Import.BodyImportTaxAssessmentKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module		: EDEC Import 70<br>
 * Created		: 06.11.2012<br>
 * Description	: Mapping of FSS CTX to KIDS ImportTaxAssessment.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

			//TODO - wohin damit?  accessCode;      //EI20130305 for CH 

public class MapCTXCHtoImportTaxAssessment extends KidsMessage {
	
	private MsgCTXCH msgCTX;	
	private MsgImportTaxAssessment message;
	
	public MapCTXCHtoImportTaxAssessment() {
		msgCTX = new MsgCTXCH();	
		message = new MsgImportTaxAssessment();
	}

	public void setMsgCTX(MsgCTXCH argument) {
		this.msgCTX = argument;						
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
	        header.setHeaderFields(msgCTX.getVorSubset());	        
	        header.setMessageName("ImportTaxAssessment");
	        header.setMessageID(getMsgID());
	       
	        CommonFieldsDTO commonFieldsDTO = new CommonFieldsDTO();
	        commonFieldsDTO.setKcxId(header.getReceiver());
	        commonFieldsDTO.setCountryCode(header.getCountryCode());
	        commonFieldsDTO.setDirection(EDirections.CountryToCustomer);
	        header.setCommonFieldsDTO(commonFieldsDTO);

	        header.writeHeader();
	         
	        BodyImportTaxAssessmentKids body   = new BodyImportTaxAssessmentKids(writer);
	        body.setMessage(message);
	        body.setKidsHeader(header);  		

            getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
            body.writeBody();
	        
	        closeElement();  // soap:Envelope
	        writer.writeEndDocument();
	        
	        writer.flush();
	        writer.close();
	        
	        if (Config.getLogXML()) {
	            Utils.log("(MapVFIToNCTSArrivalRejection getMessage) Msg = " + xmlOutputString.toString());
	        }
	        
	    	} catch (XMLStreamException e) {
	        
	    		e.printStackTrace();
	    	}
	    
	    	return xmlOutputString.toString();
		}
			
	public void setMsgFields() {	
		if (msgCTX == null) {
			return;
		}
		Traders traders = new Traders();
		message.setTraders(traders);
				
		mapABSubsetsToKids();	
		
		if (msgCTX.getConsignor() != null) {   
			traders.setConsignorAddress(getAddres(msgCTX.getConsignor()));				
		}
		if (msgCTX.getConsignee() != null) {    
			traders.setConsigneeAddress(getAddres(msgCTX.getConsignee()));		
		}
		if (msgCTX.getRepresentative() != null) {    
			traders.setRepresentativeAddress(getAddres(msgCTX.getRepresentative()));									
		}
		if (msgCTX.getDeclarant() != null) {   
			traders.setDeclarantAddress(getAddres(msgCTX.getDeclarant()));				
		}
			
		if (msgCTX.getABPList() != null) {
			for (TsABP abp : msgCTX.getABPList()) {								
				if (abp != null) {
					GoodsItemTaxAssessment item = mapCTXPosToGoodsItem(abp);							
					if (item != null) {
						message.addGoodsItemList(item);		
					}
				}
			}				
		}						
    }
	
	private void mapABSubsetsToKids() {
		if (msgCTX.getAB1Subset() == null || msgCTX.getAB1Subset().isEmpty()) {
			return;
		}
		message.setReferenceNumber(msgCTX.getAB1Subset().getAb1bnr());
		message.setUCR(msgCTX.getAB1Subset().getRegnr());
		message.setRegistrationCode(msgCTX.getAB1Subset().getRegkz());
		//abgdat, abes
		
		String anmzb = msgCTX.getAB1Subset().getAnmzb();  
		if (!Utils.isStringEmpty(anmzb)) {
			TIN anmzbTin = new TIN();
			anmzbTin.setTIN(anmzb);
			message.getTraders().setDeclarantTIN(anmzbTin);
		}
		
		Office clearanceOffice = new Office();				
		clearanceOffice.setCode(msgCTX.getAB1Subset().getDstnr());
		clearanceOffice.setName(msgCTX.getAB1Subset().getDstnam());
		clearanceOffice.setOfficer(msgCTX.getAB1Subset().getZbearb());
		clearanceOffice.setPhone(msgCTX.getAB1Subset().getDsttel());			
		if (!clearanceOffice.isEmpty()) {		
			message.setCustomsOfficeOfClearance(clearanceOffice);		
		}
		
		String vertzb = msgCTX.getAB1Subset().getVertzb();
		if (!Utils.isStringEmpty(vertzb)) {
			TIN vertzbTin = new TIN();
			vertzbTin.setTIN(vertzb);			
			message.getTraders().setRepresentativeTIN(vertzbTin);
		}
		
		String empzb = msgCTX.getAB1Subset().getEmpzb();
		if (!Utils.isStringEmpty(empzb)) {
			TIN empzbTin = new TIN();
			empzbTin.setTIN(empzb);			
			message.getTraders().setConsigneeTIN(empzbTin);
		}
		
		if (msgCTX.getAB2Subset() != null) {
			message.setDateOfRegistration(msgCTX.getAB2Subset().getRegdat());
			Account deferment = new Account();							
			deferment.setPaymentType(msgCTX.getAB2Subset().getAtnkz());
			deferment.setName(msgCTX.getAB2Subset().getAnehm());
			deferment.setTIN(msgCTX.getAB2Subset().getAzbnr());
			deferment.setAccountNumber(msgCTX.getAB2Subset().getAkto());
			deferment.setAmountOfDuty(msgCTX.getAB2Subset().getAbgb());			
			if (!deferment.isEmpty()) {
				message.addDefermentAccountList(deferment);
			}			
		}				
	}			
	
	private Address getAddres(TsADR subset) {
		if (subset == null || subset.isEmpty()) {
			return null;
		}
		/* Adresstypen: 
		3=Versender  == Consignor
		4=Empfaenger == Consignee
		5=Importeur  == Delarant
		6=Spediteur  == Representative
	    */
		String n1 = subset.getName1();
		String n2 = subset.getName2();
		String n3 = subset.getName3();
		if (Utils.isStringEmpty(n1)) {
			n1 = "";
		}
		if (Utils.isStringEmpty(n2)) {
			n2 = "";
		}
		if (Utils.isStringEmpty(n3)) {
			n3 = "";
		}
		Address address = new Address();		
		address.setName(n1 + n2 + n3);  
		address.setStreet(subset.getStr());
		//address.setPOBox(subset.getPlz());
		address.setCountry(subset.getLand());
		address.setCity(subset.getOrt());		
		address.setPostalCode(subset.getPlz());
		address.setCountrySubEntity(subset.getOteil());
		
		return address;
	}
	
	
	private GoodsItemTaxAssessment mapCTXPosToGoodsItem(TsABP abp) {	
		if (msgCTX.getAB1Subset() == null) {
			return null;
		}
		if (abp == null) {
			return null;
		}
		GoodsItemTaxAssessment item = new GoodsItemTaxAssessment();	
		Duties duty = new Duties();
		Document doc = new Document();
		
		item.setItemNumber(abp.getLfdnr());			
		message.setDutyType(abp.getAbus());
		
		duty.setType(abp.getAbgru());   //TODO ???
		duty.setAmount(abp.getAbbe());
		duty.setType(abp.getVscd());
		duty.addRateList(abp.getVsmg());
		if (!duty.isEmpty()) {	
			item.addDutiesList(duty);
		}
		
		if ( msgCTX.getAB1Subset() != null) {
			item.setAcceptanceDate(msgCTX.getAB1Subset().getAbgdat());
			item.setExaminationCode(msgCTX.getAB1Subset().getAbes());
									
			if (!Utils.isStringEmpty(msgCTX.getAB1Subset().getAbgdat())) {
				doc.setIssueDate(msgCTX.getAB1Subset().getAbgdat());
				item.addDocumentList(doc);
			}
		}		
		return item;
	}
		
}
