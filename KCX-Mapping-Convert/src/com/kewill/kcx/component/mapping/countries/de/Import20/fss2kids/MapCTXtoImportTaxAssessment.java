package com.kewill.kcx.component.mapping.countries.de.Import20.fss2kids;

import java.io.StringWriter;
import java.util.List;

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
import com.kewill.kcx.component.mapping.formats.fss.Import.messages.V64.FssAbgaben;
import com.kewill.kcx.component.mapping.formats.fss.Import.messages.V70.MsgCTX;
import com.kewill.kcx.component.mapping.formats.fss.Import.messages.V70.MsgCTXPos;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsAAB;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsADRCTX;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V70.TsASK;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V70.TsCPS;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsTXA;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsTXP;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V70.TsUNTCTX;
import com.kewill.kcx.component.mapping.formats.kids.Import20.BodyImportTaxAssessmentKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module		: Import 20<br>
 * Created		: 12.11.2012<br>
 * Description	: Mapping of FSS CTX to KIDS ImportTaxAssessment.
 *              : Utils.removeZabisDecimalPlaceV7
 *              
 * @author iwaniuk
 * @version 2.0.00
 */
public class MapCTXtoImportTaxAssessment extends KidsMessage {
	
	private MsgCTX msgCTX;	
	private MsgImportTaxAssessment message;
	private String subversion = "";    //EI20130510
	
	public MapCTXtoImportTaxAssessment() {
		msgCTX = new MsgCTX();	
		message = new MsgImportTaxAssessment();
	}

	public MapCTXtoImportTaxAssessment(CommonFieldsDTO commonFieldsDTO) {
		msgCTX = new MsgCTX();	
		message = new MsgImportTaxAssessment();
		this.setCommonFieldsDTO(commonFieldsDTO);   //EI20130523
		if (commonFieldsDTO != null && !Utils.isStringEmpty(commonFieldsDTO.getKidsRelease())) {
        	subversion = Utils.removeDots(commonFieldsDTO.getKidsRelease());        	
        }
	}
	
	public void setMsgCTX(MsgCTX argument) {
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
	        //header.setHeaderFields(msgCTX.getVorSubset());	
	        header.setHeaderFieldsFromHead(msgCTX.getVorSubset(), msgCTX.getHeadSubset());  //EI20130711
	        header.setMessageName("ImportTaxAssessment");
	        header.setMessageID(getMsgID());
	       
	        CommonFieldsDTO commonFieldsDTO = new CommonFieldsDTO();
	        commonFieldsDTO.setKcxId(header.getReceiver());
	        commonFieldsDTO.setCountryCode(header.getCountryCode());
	        commonFieldsDTO.setDirection(EDirections.CountryToCustomer);
	        header.setCommonFieldsDTO(commonFieldsDTO);

	        if (this.getCommonFieldsDTO() != null && !Utils.isStringEmpty(this.getCommonFieldsDTO().getKidsRelease())) {
	        	subversion = Utils.removeDots(this.getCommonFieldsDTO().getKidsRelease());
	        	if (subversion.equals("2001")) {
	            	header.setRelease(this.getCommonFieldsDTO().getKidsRelease());
	            }
	        }
	        
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
		mapASKListToKids(msgCTX.getASKList());		
		//DutyType not mapped in xls
		mapTXAListToKids(msgCTX.getTXAList());						
		
		if (msgCTX.getAnmelder() != null) {   
			traders.setDeclarantAddress(getAddres(msgCTX.getAnmelder()));				
		}
		if (msgCTX.getVertreter() != null) {    
			traders.setRepresentativeAddress(getAddres(msgCTX.getVertreter()));									
		}
		if (msgCTX.getEmpfaenger() != null) {    
			traders.setConsigneeAddress(getAddres(msgCTX.getEmpfaenger()));		
		}
		
		if (msgCTX.getDienstelle() != null) {  
			message.setCustomsOfficeOfClearanceAddress(getAddres(msgCTX.getDienstelle()));						
		}
		if (msgCTX.getZollstelle() != null) {  
			message.setCustomsOfficeOfPaymentAddress(getAddres(msgCTX.getZollstelle()));						
		}
		if (msgCTX.getRechtsbehelfsHZA() != null) {    
			message.setCustomsOfficeOfRemediesAddress(getAddres(msgCTX.getRechtsbehelfsHZA()));	
		}
		
		if (msgCTX.getFuerRechnung() != null) { 
			message.setAddressForInvoice(getAddres(msgCTX.getFuerRechnung()));			
		}	
		
		//mapSUAListToKids(msgCTX.getSUAList());
		//mapUNBListToKids(msgCTX.getUNBList());
			
		if (msgCTX.getCTXPosList() != null) {
			for (MsgCTXPos pos : msgCTX.getCTXPosList()) {								
				if (pos != null) {
					GoodsItemTaxAssessment item = mapCTXPosToGoodsItem(pos);							
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
		
		Office clearanceOffice = new Office();				
		clearanceOffice.setCode(msgCTX.getAB1Subset().getDstnr());
		clearanceOffice.setName(msgCTX.getAB1Subset().getDstnam());
		clearanceOffice.setOfficer(msgCTX.getAB1Subset().getZbearb());
		clearanceOffice.setPhone(msgCTX.getAB1Subset().getDsttel());		
		if (!clearanceOffice.isEmpty()) {		
			message.setCustomsOfficeOfClearance(clearanceOffice);		
		}
		
		message.setCustomsOfficeOfRemedies(msgCTX.getAB1Subset().getRebhza());
		
		Office paymentOffice = new Office();
		paymentOffice.setName(msgCTX.getAB1Subset().getZzsnam());
		if (msgCTX.getAB2Subset() != null) {
			paymentOffice.setAccountNumber(msgCTX.getAB2Subset().getZzskto());  
			paymentOffice.setBankID(msgCTX.getAB2Subset().getZzsblz());
			paymentOffice.setBank(msgCTX.getAB2Subset().getZzsbnk());
		}
		paymentOffice.setIBAN(msgCTX.getAB1Subset().getZzsiban());
		paymentOffice.setBIC(msgCTX.getAB1Subset().getZzsbic());
		if (!paymentOffice.isEmpty()) {		
			message.setCustomsOfficeOfPayment(paymentOffice);		
		}
		//  V70 statt xxxzb ist EORI + NL
		// V70: String anmzb = msgCTX.getAB1Subset().getAnmzb();  		
		if (!Utils.isStringEmpty(msgCTX.getAB1Subset().getAnmeori())) {
			TIN anmzbTin = new TIN();
			anmzbTin.setTIN(msgCTX.getAB1Subset().getAnmeori());
			anmzbTin.setBO(msgCTX.getAB1Subset().getAnmnl());
			message.getTraders().setDeclarantTIN(anmzbTin);
		}
		//V70: String vertzb = msgCTX.getAB1Subset().getVertzb();
		if (!Utils.isStringEmpty(msgCTX.getAB1Subset().getVerteori())) {
			TIN vertzbTin = new TIN();
			vertzbTin.setTIN(msgCTX.getAB1Subset().getVerteori());	
			vertzbTin.setBO(msgCTX.getAB1Subset().getVertnl());
			message.getTraders().setRepresentativeTIN(vertzbTin);
		}
		
		//V70: String empzb = msgCTX.getAB1Subset().getEmpzb();
		if (!Utils.isStringEmpty(msgCTX.getAB1Subset().getEmpeori())) {
			TIN empzbTin = new TIN();
			empzbTin.setTIN(msgCTX.getAB1Subset().getEmpeori());	
			empzbTin.setBO(msgCTX.getAB1Subset().getEmpnl());
			message.getTraders().setConsigneeTIN(empzbTin);
		}				
		
		if (msgCTX.getAB2Subset() != null) {
			message.setDateOfRegistration(msgCTX.getAB2Subset().getRegdat());
		
			message.setTotalAmountOfExemption(Utils.removeZabisDecimalPlaceV7(msgCTX.getAB2Subset().getGabgbe(), 2));
			message.setTotalAmountOfSecurity(Utils.removeZabisDecimalPlaceV7(msgCTX.getAB2Subset().getGsilbe(), 2));
			message.setTotalAmountOfCashSecurity(Utils.removeZabisDecimalPlaceV7(msgCTX.getAB2Subset().getBsicbe(), 2));
			message.setTotalAmountOfNonCashSecurity(Utils.removeZabisDecimalPlaceV7(msgCTX.getAB2Subset().getUnbsic(), 2));
			
			if (subversion.equals("2001")) {  //EI20130510            	
            	message.setTotalAmountOfExemption(msgCTX.getAB2Subset().getGabgbe());
            	message.setTotalAmountOfSecurity(msgCTX.getAB2Subset().getGsilbe());
            	message.setTotalAmountOfCashSecurity(msgCTX.getAB2Subset().getBsicbe());
            	message.setTotalAmountOfNonCashSecurity(msgCTX.getAB2Subset().getUnbsic());
            }		
			message.setTaxDeductionCode(msgCTX.getAB2Subset().getKzvsta());				
		}				
	}	
	
	private void mapTXAListToKids(List <TsTXA> list) {	
		if (list == null) {
			return;
		}
		if (list.isEmpty()) {
			return;
		}
		String text = "";
		for (TsTXA txt : list) {
			if (txt.getTxaart().equals("AAH")) {
				text = text + txt.getTxablk() + " ";
			}
		}
		message.setFindings(text);
	}
	private void mapASKListToKids(List <TsASK> list) {
		if (list == null) {
			return;
		}
		if (list.isEmpty()) {
			return;
		}
		for (TsASK ask : list) {		
			if (ask != null && !ask.isEmpty()) {
				Account account = new Account();				
				//V70: account.setTIN(ask.getAzbnr());
				account.setTIN(ask.getAeori()); //V70
				account.setBO(ask.getAnlnr());  //V70
				account.setAccountNumber(ask.getAkto());
				account.setDueDate(ask.getFaedat());
				account.setRegionalFinanceOffice(ask.getAofd());
				account.setPaymentType(ask.getZaart());
				account.setKindOfExemption(ask.getAbus());
				//account.setDefermentAccountType(ask.getAtnkz());    //EI20120928
				if (ask.getAtnkz() != null && ask.getAtnkz().equalsIgnoreCase("N")) {  //EI20121002
					account.setDefermentAccountType("0");
				} else if (ask.getAtnkz() != null && ask.getAtnkz().equalsIgnoreCase("J")) {
					account.setDefermentAccountType("1");
				}
				account.setAmountOfDuty(Utils.removeZabisDecimalPlaceV7(ask.getAbgb(), 2));
				if (subversion.equals("2001")) {  //EI20130510
					account.setAmountOfDuty(ask.getAbgb());
	            }
			
				message.addDefermentAccountList(account);
			}
		}
	}
	
	
	private Address getAddres(TsADRCTX subset) {
		if (subset == null || subset.isEmpty()) {
			return null;
		}
		String n1 = subset.getAnam1();
		String n2 = subset.getAnam2();
		String n3 = subset.getAnam3();
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
		//EI20120312: address.setName(subset.getAnam1()); 
		
		/*
		 * 
		writeElement("IWA-checkNull", Utils.checkNull(msg.getRepresentative().getAddress().getStreet()) + " " + 
							Utils.checkNull(msg.getRepresentative().getAddress().getHouseNumber()));
							
		 */
		address.setName(n1 + n2 + n3); 
		address.setStreet(subset.getAstr());
		address.setPOBox(subset.getApostf());
		address.setCountry(subset.getAlnd());
		address.setCity(subset.getAort());		
		address.setPostalCode(subset.getAplz());
		address.setCountrySubEntity(subset.getAoteil());
		
		return address;
	}
	
	
	private GoodsItemTaxAssessment mapCTXPosToGoodsItem(MsgCTXPos pos) {		
		if (pos == null) {
			return null;
		}
		GoodsItemTaxAssessment item = new GoodsItemTaxAssessment();		
		
		TsCPS cspSubset = pos.getCPSSubset();
		if (cspSubset == null) {
			return null;			
		} else {
			String regkz = cspSubset.getRegkz();				
			if (message.getRegistrationCode() != null && !message.getRegistrationCode().trim().equals(regkz.trim())) {   
				return null;
			}					
		}
		
		mapItemFromCSP(item, cspSubset);
		
		if (pos.getTXPList() != null && pos.getTXPList().size() > 0) {			
			String text = "";		
			for (TsTXP subset : pos.getTXPList()) {	
				if (subset != null) {
					if (message.getRegistrationCode().trim().equals(subset.getRegkz().trim()) && 
						subset.getTxpart().equals("AAH")) {   
						text = text + subset.getTxpblk() + " "; 		
					}
				}
			}
			item.setFindings(text);
		}
		/*		
		if (pos.getAHIList() != null && pos.getAHIList().size() > 0) {			
			String text = "";		
			for (TsAHICTX subset : pos.getAHIList()) {	
				if (subset != null) {
					if (message.getRegistrationNumber().equals(subset.getRegkz())) {   
						item.set???(subset.get???()); 
						
						item.add???(subset);
					}
				}
			}			
		}
		if (pos.getSOFList() != null && pos.getSOFList().size() > 0) {						
				for (TsSOF subset : pos.getSOFList()) {	
					if (subset != null) {
						item.set???(subset.get???());
					
						item.add???List(subset);
					}
				}
		}
		*/	
		if (pos.getABUList() != null && pos.getABUList().size() > 0) {				 
			for (FssAbgaben abgaben : pos.getABUList()) {	
				if (abgaben != null &&  abgaben.getABUSubset() != null) {
					Duties duties = new Duties();
					duties.setType(abgaben.getABUSubset().getAbus()); 
					duties.setAmount(Utils.removeZabisDecimalPlaceV7(abgaben.getABUSubset().getAbbe(), 2));
					if (subversion.equals("2001")) {  //EI20130510
						duties.setAmount(abgaben.getABUSubset().getAbbe());
		            }
					if (abgaben.getAABList() != null && !abgaben.getAABList().isEmpty()) {
						for (TsAAB aab : abgaben.getAABList()) {
							String rate = aab.getAbgsa();
							duties.addRateList(Utils.removeZabisDecimalPlaceV7(rate, 5)); 
							if (subversion.equals("2001")) {  //EI20130510
								duties.addRateList(rate);
				            }
						}
					}
					
					item.addDutiesList(duties);
				}
			}			
		}
		if (pos.getUNTList() != null && pos.getUNTList().size() > 0) {			
			for (TsUNTCTX subset : pos.getUNTList()) {	
				if (subset != null) {
					Document docu = new Document();
					docu.setType(subset.getUntar());
					docu.setNumber(subset.getUntnr());
				
					item.addDocumentList(docu);
				}
			}				
		}
		/*
		if (pos.getABWList() != null) {
			for (TsABW subset : pos.getABWList()) {	
				if (subset != null) {
					item.set???(subset.get???());
				
					item.add???(subset);
				}
			}		
		}
		if (pos.getAKGList() != null) {
			for (TsAKG subset : pos.getAKGList()) {	
				if (subset != null) {
					item.set???(subset.get???());
				
					item.add???(subset);
				}
			}					
		}
		*/
		
		return item;
	}
	
	private void mapItemFromCSP(GoodsItemTaxAssessment item, TsCPS cspSubset) {
		if (cspSubset == null || item == null) {
			return;
		}
		
		item.setItemNumber(cspSubset.getPosnr());	
		//V70: item.setExaminationText(cspSubset.getMittei());
		item.setAdditionalProof(cspSubset.getNachw());
		item.setTimeLimitAdditionalProof(cspSubset.getNawfri());
		item.setMessageOfDischarge(cspSubset.getMiterl());
		item.setAcceptanceDate(cspSubset.getEntdat());
		item.setReleaseDate(cspSubset.getUebdat());
		item.setGrantedBenefitCode(cspSubset.getKzbeg());
		item.setExaminationCode(cspSubset.getKzaord());
		item.setDischargeCode(cspSubset.getKzerl());
		item.setAcceptanceCode(cspSubset.getKzann());
		item.setDeviantAssessmentCode(cspSubset.getKzdv1());
		item.setVATValue(Utils.removeZabisDecimalPlaceV7(cspSubset.getEustwe(), 2));
		item.setAmountOfGarantee(Utils.removeZabisDecimalPlaceV7(cspSubset.getSilbet(), 2));
		item.setExchangeRate(Utils.removeZabisDecimalPlaceV7(cspSubset.getKunet(), 6));
		item.setCustomsValue(Utils.removeZabisDecimalPlaceV7(cspSubset.getZollw(), 2));			
		
		if (subversion.equals("2001")) {  //EI20130510
			item.setVATValue(cspSubset.getEustwe());
			item.setAmountOfGarantee(cspSubset.getSilbet());
			item.setExchangeRate(cspSubset.getKunet());
			item.setCustomsValue(cspSubset.getZollw());		
        }		
	}
}
