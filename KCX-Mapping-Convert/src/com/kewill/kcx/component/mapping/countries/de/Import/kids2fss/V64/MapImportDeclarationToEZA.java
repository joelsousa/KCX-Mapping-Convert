package com.kewill.kcx.component.mapping.countries.de.Import.kids2fss.V64;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.MsgImportDeclaration;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.AdditionalCosts;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.DV1;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Deferment;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Excise;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.GoodsItemDeclaration;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Salary;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.SpecialStatement;
import com.kewill.kcx.component.mapping.formats.fss.Import.messages.V64.MsgEZA;
import com.kewill.kcx.component.mapping.formats.fss.Import.messages.V64.MsgEZAPos;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsADR;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsAHI;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsAN1;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsAN2;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsBEW;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsDVT;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsERC;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsERK;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsGEH;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsKON;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsMIN;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsPO1;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsPO2;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsPRN;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsSOF;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsTXT;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsUNP;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsUNT;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsVST;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: MapImportDeclarationToEZA<br>
 * Created		: 12.09.2011<br>
 * Description	: Mapping of KIDS format of ImportDeclaration to FSS format EZA.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MapImportDeclarationToEZA extends KidsMessage {
	
	private MsgImportDeclaration message;
	private MsgEZA msgEZA;
	private String beznr = "";
	private String transmitter = "";  //EI20120206: wird nur fuer KFF ausgewertet
	
	public MapImportDeclarationToEZA(XMLEventReader parser, TsVOR tsvor) throws XMLStreamException {		
		message = new MsgImportDeclaration(parser);
		msgEZA = new MsgEZA();
		msgEZA.setVorSubset(tsvor);
	}

	public String getMessage() {
    	String res = "";    	
    	
    	if (this.getKidsHeader() != null) {                   //EI20120206:
			transmitter = this.getKidsHeader().getTransmitter();
			if (Utils.isStringEmpty(transmitter)) {
				transmitter = this.kidsHeader.getReceiver();
			}
			if (transmitter.contains("TOLL") || transmitter.contains("KFF") ||
					transmitter.contains("HANK")) {          //EI20120420
				transmitter = "KFF";
			}
			if (this.getCommonFieldsDTO() != null && !Utils.isStringEmpty(this.getCommonFieldsDTO().getBOB())) {  //EI20130215
				transmitter = this.getCommonFieldsDTO().getBOB();				
			}
		}
    	
        try {         	
        	message.parse(HeaderType.KIDS);         	
            getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
            
            // read MessageID from KidsHeader.
            msgEZA.getVorSubset().setMsgid(getKidsHeader().getMessageID());
            //Setting all Ts in MsgEZA.
            msgEZA.setAN1Subset(getAN1());
            msgEZA.setAN2Subset(getAN2());
            mapEzaAddresses();
            mapEzaErcList();
            mapEzaUntList();
            mapEzaErkList();
            msgEZA.setDVTSubset(getDV1(message.getDV1()));
            msgEZA.setTxtSubset(getTXT("0"));
        	
            if (message.getGoodsItemList() != null) {            	
			   for (GoodsItemDeclaration item : message.getGoodsItemList()) {
				   if (item != null) {
					   msgEZA.addEZAPosList(getMsgEZAPos(item));
				   }
			   }
            }
         
            res = msgEZA.getFssString();
           
            Utils.log("(MapImportDeclarationToEZA getMessage) Msg = " + res);
		
	    } catch (FssException e) {	    	
	        e.printStackTrace();	        
	    }
		    
	    return res;
	}
	
	private void mapEzaAddresses() {
        if (message.getTraders() != null) {
        	if (message.getTraders().getDeclarantAddress() != null) {
        		msgEZA.setAnmelder(getADR(message.getTraders().getDeclarantAddress(), "1"));
        	}
        	if (message.getTraders().getConsignorAddress() != null) {
            	msgEZA.setVersender(getADR(message.getTraders().getConsignorAddress(), "3"));
        	}
        	if (message.getTraders().getConsigneeAddress() != null) {
        		msgEZA.setEmpfaenger(getADR(message.getTraders().getConsigneeAddress(), "4"));
        	}
        	if (message.getTraders().getAcquirerAddress() != null) {
        		msgEZA.setErwerber(getADR(message.getTraders().getAcquirerAddress(), "5"));
        	}        	
        	if (message.getTraders().getBuyerAddress() != null) {   //EI20130319
        		msgEZA.setKaeufer(getADR(message.getTraders().getBuyerAddress(), "6"));
        	}
        	if (message.getTraders().getSellerAddress() != null) {
        		msgEZA.setVerkaeufer(getADR(message.getTraders().getSellerAddress(), "7"));
        	}
        	if (message.getTraders().getCustomsValueDeclarantAddress() != null) {
            	msgEZA.setZollwertanmelder(getADR(message.getTraders().getCustomsValueDeclarantAddress(), "8"));
        	}
        	if (message.getTraders().getRepresentativeOfCustomsValueDeclarantAddress() != null) {
            	msgEZA.setVertreterZWA(getADR(message.getTraders().
            			getRepresentativeOfCustomsValueDeclarantAddress(), "9"));
        	}
        	if (message.getTraders().getDeclarantForInvoiceAddress() != null) {
            	msgEZA.setBeteiligterFuerRechnung(
            			getADR(message.getTraders().getDeclarantForInvoiceAddress(), "10"));
        	}
        }
	}

	private void mapEzaErcList() {
        if (message.getContainer() != null && message.getContainer().getNumberList() != null) {            	
		   for (String number : message.getContainer().getNumberList()) {	
			  TsERC erc = new TsERC();
			  erc.setCbeznr(beznr);
			  erc.setConnr(number);
			  msgEZA.addERCList(erc);
		   } 		        
         }
	}

	private TsAN1 getAN1() {
		TsAN1 subset = new TsAN1();
		
		beznr = message.getReferenceNumber();
		subset.setBeznr(beznr);
		subset.setKorrkz("0");    				 //EI20111014
		subset.setAntart("EZA");				 //EI20111014
		subset.setKzzvg(message.getDeclarationPriorPresentation());
		subset.setKzvtr(message.getAgentRepresentationCode());
		subset.setKzvsta(message.getPreTaxDeductionCode());
		//AK20130506 wieder auskommentiert
		//AK20130403    
		//if (message.getDV1() != null && !message.getDV1().isEmpty()) {
		//	subset.setKzdv1("1");
		//}
		subset.setKzcon(message.getTransportInContainer());
		subset.setKzza(message.getPaymentType());
		subset.setDst(message.getCustomsOfficeOfDeclaration());
		
		if (message.getTraders() != null && message.getTraders().getDeclarantTIN() != null) {
			subset.setAnmknr(message.getTraders().getDeclarantTIN().getCustomerId());
			subset.setAnmzb(message.getTraders().getDeclarantTIN().getTIN());
			subset.setAnmust(message.getTraders().getDeclarantTIN().getVATNumber());
		}
		if (message.getAgentRepresentationCode() != null && message.getAgentRepresentationCode().equals("1")) { 
			//EI20120308: JIRA KCX-114 (Max) nur wenn AgentRepresentationCode == 1 soll Representative ausgegeben werden
			if (message.getTraders() != null && message.getTraders().getRepresentativeTIN() != null) {
				subset.setVerknr(message.getTraders().getRepresentativeTIN().getCustomerId());
				subset.setVerzb(message.getTraders().getRepresentativeTIN().getTIN());
			}
		}
		if (message.getTraders() != null && message.getTraders().getConsignorTIN() != null) {
			subset.setVesknr(message.getTraders().getConsignorTIN().getCustomerId());
			subset.setVeszb(message.getTraders().getConsignorTIN().getTIN());
		}
		if (message.getTraders() != null && message.getTraders().getAcquirerTIN() != null) {
			subset.setErwknr(message.getTraders().getAcquirerTIN().getCustomerId());
			subset.setErwzb(message.getTraders().getAcquirerTIN().getTIN());
			subset.setErwust(message.getTraders().getAcquirerTIN().getVATNumber());
		}
		if (message.getTraders() != null && message.getTraders().getConsigneeTIN() != null) {
			subset.setEmpknr(message.getTraders().getConsigneeTIN().getCustomerId());
			subset.setEmpzb(message.getTraders().getConsigneeTIN().getTIN());
		}
		if (message.getTraders() != null && message.getTraders().getBuyerTIN() != null) {
			subset.setKfknr(message.getTraders().getBuyerTIN().getCustomerId());
			subset.setKfzb(message.getTraders().getBuyerTIN().getTIN());
		}
		if (message.getTraders() != null && message.getTraders().getSellerTIN() != null) {
			subset.setVekknr(message.getTraders().getSellerTIN().getCustomerId());
			subset.setVekzb(message.getTraders().getSellerTIN().getTIN());
		}
		if (message.getTraders() != null && message.getTraders().getCustomsValueDeclarantTIN() != null) {
			subset.setZwaknr(message.getTraders().getCustomsValueDeclarantTIN().getCustomerId());
			subset.setZwazb(message.getTraders().getCustomsValueDeclarantTIN().getTIN());
		}
		if (message.getTraders() != null && 
				message.getTraders().getRepresentativeOfCustomsValueDeclarantTIN() != null) {
			subset.setVwaknr(message.getTraders().getRepresentativeOfCustomsValueDeclarantTIN().getCustomerId());
			subset.setVwazb(message.getTraders().getRepresentativeOfCustomsValueDeclarantTIN().getTIN());
		}
		if (message.getTraders() != null && message.getTraders().getDeclarantForInvoiceTIN() != null) {
			subset.setRecknr(message.getTraders().getDeclarantForInvoiceTIN().getCustomerId());
			subset.setRechzb(message.getTraders().getDeclarantForInvoiceTIN().getTIN());
		}
		if (message.getTotalNumberPositions() != null) {              //EI20120308
			subset.setAnzpos(message.getTotalNumberPositions());
		} else {			
			if (message.getGoodsItemList() != null && message.getGoodsItemList().size() > 0) {
				String size = "" + message.getGoodsItemList().size();
				subset.setAnzpos(size);
			} else {
				subset.setAnzpos("0");
			}
		}
		if (message.getBusiness() != null && !Utils.isStringEmpty(message.getBusiness().getBusinessTypeCode())) { 
			subset.setGesart(message.getBusiness().getBusinessTypeCode());    //EI20120123
		}
		if (message.getBusiness() != null) { 
			subset.setGesart(message.getBusiness().getBusinessTypeCode());    //EI20120123
		}
		subset.setAusort(message.getPlaceOfDeclaration());    //EI20120123
		subset.setVland(message.getDispatchCountry());        //EI20120123
		
		return subset;
	}
	
	private TsADR getADR(Address adr, String typ) {
		int len = 0;
		if (adr == null || adr.isEmpty()) {
			return null;
		}
		TsADR subset = new TsADR();
		
		String street = adr.getStreet();
		if (!Utils.isStringEmpty(adr.getHouseNumber())) {
			street = street + " " + adr.getHouseNumber();
		}
		
		subset.setAbeznr(message.getReferenceNumber());		
		subset.setAdztyp(typ);
		
		if (adr.getName() != null) {
			len = adr.getName().length();			
			if (len > 0 && len <= 35) {
				subset.setAnam1(adr.getName());
			} else if (len > 35 && len <= 70) {
				subset.setAnam1(adr.getName().substring(0, 35));
				subset.setAnam2(adr.getName().substring(35, len));
			} else  if (len > 70) {
				subset.setAnam1(adr.getName().substring(0, 35));
				subset.setAnam2(adr.getName().substring(35, 70));
				if (len <= 105) {
					subset.setAnam3(adr.getName().substring(70, len));					
				} else if (len > 105) {
					subset.setAnam3(adr.getName().substring(70));
				}
			} 
		}
		subset.setAstr(street);
		subset.setApostf(adr.getPOBox());
		subset.setAlnd(adr.getCountry());
		subset.setAplz(adr.getPostalCode());
		subset.setAort(adr.getCity());
		subset.setAoteil(adr.getCountrySubEntity());
		    
		return subset;
	}
	
	private TsAN2 getAN2() {
		TsAN2 subset = new TsAN2();
		
		subset.setBeznr(message.getReferenceNumber());
		subset.setEinzst(message.getCustomsOfficeEntry());
		subset.setEinfsi(message.getImporterLocation());
		subset.setWaort(message.getGoodsLocation());
		subset.setBbland(message.getDestinationFederalState());
		subset.setFinamt(message.getTaxOffice());
		subset.setStzoll(message.getCustomsStatus());
		if (transmitter.equals("KFF") && message.getCustomsStatus() != null) {    //EI20120216 
			String cs = message.getCustomsStatus().trim();		
			if (cs.equals("AE003")) {
				subset.setStzoll("IM");
			} else if (cs.equals("AE001")) {
				subset.setStzoll("EU");
			} else if (cs.length() == 2) {
				subset.setStzoll(cs);
			} else {
				subset.setStzoll("");
			}
		}
		subset.setStstat(message.getStatisticalStatus());
		subset.setRohm(Utils.addZabisDecimalPlace(message.getGrossMass(), 1));
		
		if (message.getPreviousDocument() != null) {
			subset.setVorpnr(message.getPreviousDocument().getNumber());
			subset.setVorpar(message.getPreviousDocument().getType());
		}
		
		if (message.getMeansOfTransportBorder() != null) {
			subset.setBfvkzg(message.getMeansOfTransportBorder().getTransportMode());
			subset.setBfartg(message.getMeansOfTransportBorder().getTransportationType());
			subset.setBfnatg(message.getMeansOfTransportBorder().getTransportationCountry());
		}
		

		if (message.getMeansOfTransportInland() != null) {
			subset.setBfvkzi(message.getMeansOfTransportInland().getTransportMode());
			subset.setBfbesi(message.getMeansOfTransportInland().getDescription());
		}

		if (message.getMeansOfTransportArrival() != null) {
			subset.setBfkzi(message.getMeansOfTransportArrival().getTransportationNumber());
		}
		
		if (message.getContactPerson() != null) {
			subset.setSbname(message.getContactPerson().getClerk());
			subset.setSbstel(message.getContactPerson().getPosition());
			subset.setSbtele(message.getContactPerson().getPhoneNumber());
			subset.setSbnr(message.getContactPerson().getIdentity());
		}
		
		if (message.getIncoterms() != null) {
			subset.setLibsch(message.getIncoterms().getKey());
			subset.setLibart(message.getIncoterms().getCode());
			subset.setLibinc(message.getIncoterms().getDetails());
			subset.setLibort(message.getIncoterms().getPlace());
		}

		if (message.getInvoice() != null) {
			subset.setPreisr(Utils.addZabisDecimalPlace(message.getInvoice().getAmount(), 2));
			subset.setWaehr(message.getInvoice().getCurrency());
			subset.setKurs(Utils.addZabisDecimalPlace(message.getInvoice().getExchangeRate(), 6));
			subset.setMzp(message.getInvoice().getRelevantTime());
		}
		
		subset.setSuaart(message.getWriteOffSumAType());
		subset.setAzvbew(message.getWriteOffBonWarAvuvAuthNum());
		subset.setZlbez(message.getWriteOffBonWarRefNum());
		subset.setKuatnr(message.getCustomerOrderNumber());
		
		return subset;
	}
	
	
	// Unterlagen auf Kopf-Ebene
	private void mapEzaUntList() {
		TsUNT subset = null;
		if (message.getDocumentList() != null) {
			for (Document doc : message.getDocumentList()) {
				if (doc != null)  {
					subset = new TsUNT();
					
					subset.setBeznr(beznr);
					subset.setUntart(doc.getType());
					//EI20120209: schnelle Loesung fur Max, bis die eigentliche (um)codierung gemacht wird:
					if (doc.getType() != null && doc.getType().equals("BI119")) { 
						subset.setUntart("N380");
					} 
					subset.setUntnr(doc.getNumber());
					subset.setUntdat(doc.getIssueDate());
					msgEZA.addUNTList(subset);
				}
			}
		} 
	}
	
	// Ergänzungssatz Aufschubkonten
	private void mapEzaErkList() {
		TsERK subset = null;
		if (message.getDefermentList() != null) { 
			for (Deferment deferment : message.getDefermentList()) {
				if (deferment != null)  {
					subset = new TsERK();
					
					subset.setKbeznr(beznr);
					subset.setKtoaa(deferment.getType());
					subset.setKtozb(deferment.getTIN());
					subset.setKtokn(deferment.getCustomerId());
					msgEZA.addERKList(subset);
				}
			}
		}
	}

	// DV1-Satz
	private TsDVT getDV1(DV1 dv1) {
		if (dv1 == null || dv1.isEmpty()) {
			return null;
		}
		TsDVT subset = new TsDVT();		
		
		subset.setDvtbnr(beznr);		
		subset.setKzverb(dv1.getBuyerSellerRelationship());
		subset.setTxverb(dv1.getBuyerSellerRelationshipDetails());
		subset.setKzesch(dv1.getRestrictionsCode());
		subset.setTxesch(dv1.getRestrictionsText());
		subset.setKzlizg(dv1.getLicenseFeeDueCode());
		subset.setTxlizg(dv1.getLicenseFeeText());
		subset.setKzwuv(dv1.getResaleSurrenderUsageCode());
		subset.setTxwuv(dv1.getResaleSurrenderUsageText());
		subset.setKzvtrd(dv1.getAgentRepresentationDV1Code());
		subset.setKzbedl(dv1.getTermsServicesCode());
		subset.setTxbedl(dv1.getTermsServicesText());
		subset.setTxfren(dv1.getTextPreviousDecisions());
		return subset;
	}
	
	private TsTXT getTXT(String pos) {
		TsTXT subset = null;
		String text = "";               //EI20131028
		
		if (!Utils.isStringEmpty(message.getAdditionalInformation())) {
			text = message.getAdditionalInformation();
		} else if (message.getAdditionalInfoStatement() != null) {   //EI20131028
			text = message.getAdditionalInfoStatement().getStatementText();
		}
		
		if (!Utils.isStringEmpty(text)) {
			subset = new TsTXT(); 
			subset.setBeznr(beznr);
			subset.setPosnr(pos);
			subset.setText(message.getAdditionalInformation());
		}
		return subset;
	}
	
	
	private MsgEZAPos getMsgEZAPos(GoodsItemDeclaration item) {
		if (item == null) {
			return null;
		}
		MsgEZAPos pos = new MsgEZAPos();
		String    itemNumber = item.getItemNumber();
		
		pos.setPO1Subset(getPO1(item, itemNumber));
		pos.setPO2Subset(getPO2(item, itemNumber));
		pos.setPRNSubset(getPRN(item, itemNumber));
		pos.setUNPList(getUNPList(item, itemNumber));
		pos.setGEHList(getGEHList(item, itemNumber));
		pos.setVSTList(getVSTList(item, itemNumber));
		pos.setKONSubset(getKON(item, itemNumber));
		pos.setAHIList(getAHIList(item, itemNumber));
		pos.setMINList(getMINList(item, itemNumber));
		pos.setBEWList(getBEWList(item, itemNumber));
		pos.setSOFList(getSOFList(item, itemNumber));
		
		return pos;
	}

	private TsPO1 getPO1(GoodsItemDeclaration item, String itemNumber) {
		TsPO1 subset = new TsPO1();
		
		subset.setPo1bnr(beznr);
		subset.setPo1pnr(itemNumber);
		if (item.getCommodityCode() != null) {
			subset.setWanr(item.getCommodityCode().getTarifCode());
			subset.setWazus1(item.getCommodityCode().getTarifAddition1());
			subset.setWazus2(item.getCommodityCode().getTarifAddition2());
		}
		subset.setVerf(item.getProcedureCode());
		subset.setZuverf(item.getDutyControlCode());
		subset.setTxhiab(item.getAdditionsDeductionsDescription());
		subset.setTxzus(item.getSupplementaryText());
		subset.setWabes(item.getDescription());
		subset.setArtnr(item.getEAN());
		subset.setMenge(Utils.addZabisDecimalPlace(item.getAmount(), 3));
		subset.setMeinh(item.getUnitOfMeasurementAmount());
		subset.setQmeinh(item.getQualifierAmount());
		subset.setEucode(item.getEUCode());
		if (item.getQuota() != null) {
			subset.setKontnr(item.getQuota().getNumber1());
			subset.setKontnr2(item.getQuota().getNumber2());
		}

		
		return subset;
	}

	
	private TsPO2 getPO2(GoodsItemDeclaration item, String itemNumber) {
		TsPO2 subset = new TsPO2();
		
		subset.setPo2bnr(beznr);
		subset.setPo2pnr(itemNumber);
		subset.setVrbort(item.getPlaceOfIntroduction());
		subset.setAbgort(item.getPlaceOfDeparture());
		subset.setPuland(item.getCountryOfOrigin());
		subset.setEigm(Utils.addZabisDecimalPlace(item.getNetMass(), 1));
		subset.setProhm(Utils.addZabisDecimalPlace(item.getGrossMass(), 1));
		if (transmitter.equals("KFF")) {    //EI20120207 JIRA-Ticket KCX-94 (siehe calculateNetMass(...))
			//in tmpAPO muessten die Gewichte ohne Punkte und ohne Komma sein, wenn doch, dann ist es
			//sowieso falsch => in calculateNetMass(...) wird es nicht "umformatiert"			
			subset.setEigm(calculateNetMass(subset.getProhm(), subset.getEigm()));
		}
		if (item.getImportPackage() != null) {
			subset.setStkanz(item.getImportPackage().getQuantity());
			subset.setStkart(item.getImportPackage().getType());
			subset.setStkzei(item.getImportPackage().getMarks());
		}
		
		subset.setBeabeg(item.getRequestedPrivilege());
		subset.setBeding(item.getCondition());
		subset.setZollw(Utils.addZabisDecimalPlace(item.getCustomsValue(), 2));
		subset.setEust(Utils.addZabisDecimalPlace(item.getImportTurnoverTax(), 2));
		
		if (item.getInvoice() != null) {
			subset.setRpreis(Utils.addZabisDecimalPlace(item.getInvoice().getAmount(), 2));
			subset.setRabatt(Utils.addZabisDecimalPlace(item.getInvoice().getReductionSurcharge(), 2));
			subset.setSkonto(Utils.addZabisDecimalPlace(item.getInvoice().getDiscount(), 2));
		}
		
		if (item.getNetPrice() != null) {
			//	AK111027
			//subset.setNetto(item.getNetPrice().getAmount());
			subset.setWaenet(item.getNetPrice().getCurrency());
			subset.setKnet(Utils.addZabisDecimalPlace(item.getNetPrice().getRate(), 6));
			subset.setKznet(item.getNetPrice().getCode());
		}
		

		if (item.getIndirectPayment() != null) {
			subset.setMzahl(Utils.addZabisDecimalPlace(item.getIndirectPayment().getAmount(), 2));
			subset.setWaemz(item.getIndirectPayment().getCurrency());
			subset.setKmzahl(Utils.addZabisDecimalPlace(item.getIndirectPayment().getRate(), 6));
			subset.setKzzahl(item.getIndirectPayment().getCode());
		}
		
		if (item.getStatistic() != null) {
			subset.setWmahst(Utils.addZabisDecimalPlace(item.getStatistic().getQuantity(), 3));
			subset.setMeast(item.getStatistic().getMeasuringUnit());
			subset.setQmeast(item.getStatistic().getQualifierMeasuringUnit());
			subset.setAhwert(Utils.addZabisDecimalPlace(item.getStatistic().getStatisticalValue(), 2));
		}
		
		if (item.getGoodsQuantityCustoms() != null) {
			subset.setWmzoll(Utils.addZabisDecimalPlace(item.getGoodsQuantityCustoms().getQuantity(), 3));
			subset.setMezoll(item.getGoodsQuantityCustoms().getMeasuringUnit());
			subset.setQmezol(item.getGoodsQuantityCustoms().getQualifierMeasuringUnit());
		}
		

		if (item.getGoodsQuantityAgriculturalDuty() != null) {
			subset.setWmazol(Utils.addZabisDecimalPlace(item.getGoodsQuantityAgriculturalDuty().getQuantity(), 3));
			subset.setMeazol(item.getGoodsQuantityAgriculturalDuty().getMeasuringUnit());
			subset.setQmeazo(item.getGoodsQuantityAgriculturalDuty().getQualifierMeasuringUnit());
		}
		
		subset.setVered(Utils.addZabisDecimalPlace(item.getProcessingFeeValueIncrease(), 2));
		subset.setZuskeu(Utils.addZabisDecimalPlace(item.getExtraCostImportVAT(), 2));
		subset.setTabak(item.getTobaccoDutyCodeNumber());

		
		return subset;
	}

	private TsPRN getPRN(GoodsItemDeclaration item, String itemNumber) {
		TsPRN subset = null;
		if (item.getPreference() != null) {
			subset = new TsPRN();
			
			subset.setPrfbnr(beznr);
			subset.setPrfpnr(itemNumber);
			subset.setPrnw(item.getPreference().getPreferenceCertificate());
			subset.setPrnwnr(item.getPreference().getPreferenceCertificateNumber());
			subset.setPrndat(item.getPreference().getDate());
			subset.setPrnkzv(item.getPreference().getCodeExists());
		}
		return subset;
	}

	//Unterlagen zur Position 
	private List<TsUNP> getUNPList(GoodsItemDeclaration item, String itemNumber) {
		List<TsUNP> unpList = null;
		TsUNP subset = null;
		
		if (item.getDocumentList() != null && item.getDocumentList().size() > 0) {
			unpList = new Vector<TsUNP>();
			for (Document document : item.getDocumentList()) {
				if (document != null) {
				subset = new TsUNP();
				
				subset.setBeznr(beznr);
				subset.setPosnr(itemNumber);
				subset.setUntart(document.getType());
				//EI20120209: schnelle Loesung fur Max, bis die eigentliche (um)codierung gemacht wird:
				if (document.getType() != null && document.getType().equals("BI119")) { 
					subset.setUntart("N380");
					
				} 
				//EI20121121 inserted check != null 
				if (document.getType() != null) { 
					if (document.getType().equals("BI119") || document.getType().equals("N380")) { //EI20120319
						subset.setUntber("4");   
					}
				}
				subset.setUntnr(document.getNumber());
				subset.setUntdat(document.getIssueDate());
				subset.setKzvorl(document.getCodeExists());
				subset.setMenge(Utils.addZabisDecimalPlace(document.getWriteOffAmountValue(), 3));
				subset.setMeinh(document.getWriteOffMeasuringUnit());
				subset.setQmeinh(document.getWriteOffQualifierMeasuringUnit());
	  
				unpList.add(subset);
				}
			}
		} else {
			//TODO-heute
		}
		return unpList;
	}

	// Gehaltsangaben
	private List<TsGEH> getGEHList(GoodsItemDeclaration item, String itemNumber) {
		List<TsGEH> gehList = null;
		TsGEH subset = null;
		
		if (item.getSalaryList() != null && item.getSalaryList().size() > 0) {
			gehList = new Vector<TsGEH>();
			
			for (Salary salary : item.getSalaryList()) {
				subset = new TsGEH();

				subset.setGehbnr(beznr);
				subset.setGehpnr(itemNumber);
				subset.setGhart(salary.getType());
				subset.setGhprz(salary.getRateOrPercent());
				
				gehList.add(subset);
			}
		}
			
		return gehList;
	}

	// Verbrauchssteuer
	private List<TsVST> getVSTList(GoodsItemDeclaration item, String itemNumber) {
		List<TsVST> vstList = null;
		TsVST subset = null;
		
		if (item.getExciseList() != null && item.getExciseList().size() > 0) {
			vstList = new Vector<TsVST>();
			
			for (Excise excise : item.getExciseList()) {
				subset = new TsVST();

				subset.setVstbnr(beznr);
				subset.setVstpnr(itemNumber);
				subset.setVbstc(excise.getTaxCode());
				subset.setMenge(Utils.addZabisDecimalPlace(excise.getTaxQuantity(), 3));
				subset.setMeinh(excise.getMeasuringUnit());
				subset.setQmeinh(excise.getQualifierMeasuringUnit());
				subset.setGrprz(Utils.addZabisDecimalPlace(excise.getRateOrPercent(), 2));
				subset.setPreis(Utils.addZabisDecimalPlace(excise.getTaxValue(), 2));
				
				vstList.add(subset);
			}
		}
		return vstList;
	}
	
	// Kontingentangaben
	private TsKON getKON(GoodsItemDeclaration item, String itemNumber) {
		TsKON subset = null;
		if (item.getQuota() != null) {
			subset = new TsKON();
			
			subset.setKonbnr(beznr);
			subset.setKonpnr(itemNumber);
			subset.setBegmge(Utils.addZabisDecimalPlace(item.getQuota().getQuantity(), 3));
			subset.setBegmeh(item.getQuota().getMeasuringUnit());
			subset.setQbegme(item.getQuota().getQualifierMeasuringUnit());
		}
		return subset;
	}

	//Abzüge/Hinzurechnungen 
	private List<TsAHI> getAHIList(GoodsItemDeclaration item, String itemNumber) {
		List<TsAHI> ahiList = null;
		TsAHI subset = null;
		
		if (item.getAdditionsDeductionsList() != null && item.getAdditionsDeductionsList().size() > 0) {
			ahiList = new Vector<TsAHI>();
			
			for (AdditionalCosts  additionalCosts : item.getAdditionsDeductionsList()) {
				subset = new TsAHI();
				
				subset.setAhibnr(beznr);
				subset.setAhipnr(itemNumber);
				subset.setArhia(additionalCosts.getType());
				subset.setPreis(Utils.addZabisDecimalPlace(additionalCosts.getAmount(), 2));
				subset.setAhwae(additionalCosts.getCurrency());
				subset.setKzia(additionalCosts.getIATARateCode());
				subset.setKzku(additionalCosts.getRateAgreedCode());
				subset.setKurs(Utils.addZabisDecimalPlace(additionalCosts.getRate(), 6));
				subset.setKudat(additionalCosts.getDate());
				subset.setArhipr(Utils.addZabisDecimalPlace(additionalCosts.getPercentageFreightCost(), 2));
				
				ahiList.add(subset);
			}
		}
		return ahiList;
	}
	
	// Minderungsangaben 
	private List<TsMIN> getMINList(GoodsItemDeclaration item, String itemNumber) {
		List<TsMIN> minList = null;
		TsMIN subset = null;
		
		if (item.getReductionStatementList() != null && item.getReductionStatementList().size() > 0) {
			minList = new Vector<TsMIN>();
			
			for (SpecialStatement specialStatement : item.getReductionStatementList()) {
				subset = new TsMIN();
				
				subset.setMinbnr(beznr);
				subset.setMinpnr(itemNumber);
				subset.setMabgr(specialStatement.getGroup());
				subset.setMprs(Utils.addZabisDecimalPlace(specialStatement.getValue(), 2));
				minList.add(subset);
			}
		}
		return minList;
	}
	
	//Besondere Wertangaben
	private List<TsBEW> getBEWList(GoodsItemDeclaration item, String itemNumber) {
		List<TsBEW> bewList = null;
		TsBEW subset = null;
		
		if (item.getSpecialValueStatementList() != null && item.getSpecialValueStatementList().size() > 0) {
			bewList = new Vector<TsBEW>();
			
			for (SpecialStatement specialValueStatement : item.getSpecialValueStatementList()) {
				subset = new TsBEW();
				
				subset.setBewbnr(beznr);
				subset.setBewpnr(itemNumber);
				subset.setBewe(Utils.addZabisDecimalPlace(specialValueStatement.getValue(), 2));
				subset.setPrsar(specialValueStatement.getType());
				
				bewList.add(subset);
			}
		}
		return bewList;
	}

	// Sonderfallangaben  
	private List<TsSOF> getSOFList(GoodsItemDeclaration item, String itemNumber) {
		List<TsSOF> sofList = null;
		TsSOF subset = null;
		int posnr = 0;
		
		//EI20130426: if (item.getSpecialValueStatementList() != null && item.getSpecialValueStatementList().size() > 0) {
		if (item.getSpecialCaseStatementList() != null && item.getSpecialCaseStatementList().size() > 0) { //EI20130426
			
			sofList = new Vector<TsSOF>();
			
			for (SpecialStatement specialCaseStatement : item.getSpecialCaseStatementList()) {
				subset = new TsSOF();
				
				posnr++;
				subset.setRegkz(beznr);
				subset.setLfdnr(itemNumber);
				subset.setPosnr(posnr + "");
				subset.setSogru(specialCaseStatement.getGroup());
				subset.setSoanw(specialCaseStatement.getType());
				subset.setSosbf(Utils.addZabisDecimalPlace(specialCaseStatement.getValue(), 5));
				
				sofList.add(subset);
			}
		}
		return sofList;
	}

}
