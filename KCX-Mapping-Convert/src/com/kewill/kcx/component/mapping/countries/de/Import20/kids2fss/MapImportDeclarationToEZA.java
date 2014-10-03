package com.kewill.kcx.component.mapping.countries.de.Import20.kids2fss;

import java.util.ArrayList;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.AdditionalCosts;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Completion;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.DV1;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Deferment;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Excise;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.GoodsItemDeclaration;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Manifest;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Salary;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.SpecialStatement;
import com.kewill.kcx.component.mapping.countries.de.Import20.msg.MsgImportDeclaration;
import com.kewill.kcx.component.mapping.formats.fss.Import.messages.V64.MsgEZAPos;
import com.kewill.kcx.component.mapping.formats.fss.Import.messages.V70.MsgEZA;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsAHI;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsBEW;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsDVT;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsERC;
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
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V70.TsADR;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V70.TsAN1;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V70.TsAN2;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V70.TsEMP;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V70.TsERK;
import com.kewill.kcx.component.mapping.formats.fss.base.subsets.V70.TsBAV;
import com.kewill.kcx.component.mapping.formats.fss.base.subsets.V70.TsBSU;
import com.kewill.kcx.component.mapping.formats.fss.base.subsets.V70.TsBZL;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: Import 20<br>
 * Created		: 12.11.2012<br>
 * Description	: Mapping of KIDS format of ImportDeclaration to FSS format EZA.
 * 
 * @author iwaniuk
 * @version 2.0.00
 */
public class MapImportDeclarationToEZA extends KidsMessage {
	
	private MsgImportDeclaration message;
	private MsgEZA msgEZA;
	private String beznr = "";
	private String transmitter = "";  //EI20120206: wird nur fuer KFF ausgewertet
	private String subversion = "";      //EI20130425
	private boolean isBDP = false;      //EI20130620
	
	public MapImportDeclarationToEZA(XMLEventReader parser, TsVOR tsvor) throws XMLStreamException {		
		message = new MsgImportDeclaration(parser);
		msgEZA = new MsgEZA();
		msgEZA.setVorSubset(tsvor);
	}

	public MapImportDeclarationToEZA(XMLEventReader parser, TsVOR tsvor, TsHead head) throws XMLStreamException {		
		message = new MsgImportDeclaration(parser);
		msgEZA = new MsgEZA();
		msgEZA.setVorSubset(tsvor);
		msgEZA.setHeadSubset(head);
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
			if (this.getKidsHeader().getReceiver() != null && this.getKidsHeader().getReceiver().contains("BDP")) {
				isBDP = true;
			}
			if (this.getKidsHeader().getReceiver() != null && this.getKidsHeader().getReceiver().equals("DE.KCX.TST")) {
				isBDP = true;
			} //EI20131219: nur zur Testzwecken für BDP
		}
    	
        try {         	
        	message.parse(HeaderType.KIDS);         	
            getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
              
            if (this.getKidsHeader() != null && !Utils.isStringEmpty(this.getKidsHeader().getRelease())) {   //EI20130422
            	subversion = Utils.removeDots(this.getKidsHeader().getRelease());
			}
            
            msgEZA.getVorSubset().setMsgid(getKidsHeader().getMessageID());
            msgEZA.getHeadSubset().setMsgid(getKidsHeader().getMessageID());            
            
            msgEZA.setAN1Subset(mapAN1());
            msgEZA.setAN2Subset(mapAN2());
            //mapEMPList();      //V70 TODO: 
            mapEzaAddresses();
            mapEzaErcList();
            mapEzaBSUList(message.getManifestCompletionList());  //EI20130724 neu
            mapEzaBZLList(message.getBondedWarehouseCompletionList());  //EI20130724 neu
            mapEzaBAVList(message.getInwardProcessingCompletionList()); //EI20130724 neu
            mapEzaUntList(message.getDocumentList());    //EI20130724 jetzt mit Argument        
            mapEzaErkList();
            msgEZA.setDVTSubset(mapDV1(message.getDV1()));
            msgEZA.setTxtSubset(mapTXT("0"));
        	
            if (message.getGoodsItemList() != null) {            	
			   for (GoodsItemDeclaration item : message.getGoodsItemList()) {
				   if (item != null) {
					   msgEZA.addEZAPosList(getMsgEZAPos(item));
				   }
			   }
            }
         
            //res = msgEZA.getFssString();
            /* EI20140206
            if (this.writeHead()) { 				//EI20130213
            	res = msgEZA.getFssString("HEAD");
            } else {
            	res = msgEZA.getFssString();
            }
            */
            res = msgEZA.getFssString("HEAD");  //EI20140206 jetzt kann man immer nur HEAD schreiben
           
            Utils.log("(MapImportDeclarationToEZA getMessage) Msg = " + res);
		
	    } catch (FssException e) {	    	
	        e.printStackTrace();	        
	    }
		    
	    return res;
	}
	
	private void mapEzaAddresses() {
        if (message.getTraders() != null) {
        	if (message.getTraders().getDeclarantAddress() != null) {
        		msgEZA.setAnmelder(mapADR(message.getTraders().getDeclarantAddress(), "1"));
        		/*  EI20130906: wieder anders: sehe unten
        		if (!isBDP) {   //EI20130701
        			msgEZA.setAnmelder(mapADR(message.getTraders().getDeclarantAddress(), "1"));        		       	
        		} else {  // also BDP
        			if (Utils.isStringEmpty(message.getDeclarantIsConsignee()) ||      //EI20130701        		
        				message.getDeclarantIsConsignee().equals("0") || message.getDeclarantIsConsignee().equals("N")) {        				
        				msgEZA.setAnmelder(mapADR(message.getTraders().getDeclarantAddress(), "1"));
        			}
        		} 
        		*/
        	}
        	if (message.getTraders().getConsignorAddress() != null) {
        		msgEZA.setVersender(mapADR(message.getTraders().getConsignorAddress(), "3"));        		
        	}
        	if (message.getTraders().getConsigneeAddress() != null) {
        		msgEZA.setEmpfaenger(mapADR(message.getTraders().getConsigneeAddress(), "4"));        		
        	}
        	if (message.getTraders().getAcquirerAddress() != null) {
        		msgEZA.setErwerber(mapADR(message.getTraders().getAcquirerAddress(), "5"));
        	}
        	if (message.getTraders().getBuyerAddress() != null) {   //EI20130319
        		msgEZA.setKaeufer(mapADR(message.getTraders().getBuyerAddress(), "6"));
        	}
        	if (message.getTraders().getSellerAddress() != null) {
        		msgEZA.setVerkaeufer(mapADR(message.getTraders().getSellerAddress(), "7"));
        	}
        	if (message.getTraders().getCustomsValueDeclarantAddress() != null) {
            	msgEZA.setZollwertanmelder(mapADR(message.getTraders().getCustomsValueDeclarantAddress(), "8"));
        	}
        	if (message.getTraders().getRepresentativeOfCustomsValueDeclarantAddress() != null) {
            	msgEZA.setVertreterZWA(mapADR(message.getTraders().
            			getRepresentativeOfCustomsValueDeclarantAddress(), "9"));
        	}
        	if (message.getTraders().getDeclarantForInvoiceAddress() != null) {
            	msgEZA.setBeteiligterFuerRechnung(
            			mapADR(message.getTraders().getDeclarantForInvoiceAddress(), "10"));
        	}
        	
        	//EI20130996: nur fuer BDP: wee DeclarantIsConsignee == 1 oder J, dann den Empfaenger nicht ausgeben, 
        	//   		den anmelder ausgeben: gemapped von declarant, wenn aber declarant leer ist dann von consignee 
        	if (isBDP && !Utils.isStringEmpty(message.getDeclarantIsConsignee())) {   //EI20130906
        		if (message.getDeclarantIsConsignee().equals("1") || message.getDeclarantIsConsignee().equalsIgnoreCase("J")) {        			
        			if (message.getTraders().getDeclarantAddress() != null && !message.getTraders().getDeclarantAddress().isEmpty()) {
        				//msgEZA.setAnmelder() ist bereits mit message.getTraders().getDeclarantAddress() gefüllt 
        				msgEZA.setEmpfaenger(null);
        			} else {
        				msgEZA.setAnmelder(mapADR(message.getTraders().getConsigneeAddress(), "1"));  
        				msgEZA.setEmpfaenger(null);
        			}
        		}  //else beide sind bereits regulaer gefuellt      		
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

	
	private TsAN1 mapAN1() {
		TsAN1 subset = new TsAN1();
		
		beznr = message.getReferenceNumber();
		subset.setBeznr(beznr);
		//EI20140407: subset.setKorrkz("0");   
		subset.setKorrkz("2");    //EI20140407 "2" neu eingefuegt damit Kunde beides neu/korrektur senden kann
		/*EI20130508: ist doch nicht notwendig
		if (this.kidsHeader != null && this.isBDP(this.kidsHeader.getReceiver())) {	//EI20130506	
			if (!Utils.isStringEmpty(this.kidsHeader.getProcedureSpecification())) {
				if (this.kidsHeader.getProcedureSpecification().equals("1")) {
					subset.setKorrkz("1");  
				}				
			}			
		}
		*/
		subset.setAntart("EZA");				 
		subset.setKzzvg(message.getDeclarationPriorPresentation());
		subset.setKzvtr(message.getAgentRepresentationCode());
		if (!Utils.isStringEmpty(message.getDeclarantIsConsignee())) { //EI20130611
			String kz = message.getDeclarantIsConsignee();
			if (kz.equalsIgnoreCase("J") || kz.equals("1")) {				
				subset.setKzemp("J"); 			
			} else {
				subset.setKzemp(""); 
			}
		}		
		subset.setKzvsta(message.getPreTaxDeductionCode());
		//AK20130506  wieder rückgängig gemacht
		//AK20130403
		//if (message.getDV1() != null && !message.getDV1().isEmpty()) {
		//	subset.setKzdv1("1");
		//}
		subset.setKzcon(message.getTransportInContainer());
		subset.setKzza(message.getPaymentType());
		subset.setDst(message.getCustomsOfficeOfDeclaration());
		if (message.getTraders() != null && message.getTraders().getDeclarantTIN() != null) {
			subset.setAnmkd(message.getTraders().getDeclarantTIN().getCustomerId());
			//V70: subset.setAnmzb(message.getTraders().getDeclarantTIN().getTIN()); 
			subset.setAnmeori(message.getTraders().getDeclarantTIN().getTIN());  //V70
			subset.setAnmnl(message.getTraders().getDeclarantTIN().getBO());     //V70
			subset.setAnmust(message.getTraders().getDeclarantTIN().getVATNumber());	
		}
		if (message.getAgentRepresentationCode() != null && message.getAgentRepresentationCode().equals("1")) { 
			//EI20120308: JIRA KCX-114 (Max) nur wenn AgentRepresentationCode == 1 soll Representative ausgegeben werden
			if (message.getTraders() != null && message.getTraders().getRepresentativeTIN() != null) {
				subset.setVertkd(message.getTraders().getRepresentativeTIN().getCustomerId());
				//V70: subset.setVerzb(message.getTraders().getRepresentativeTIN().getTIN());
				subset.setVerteori(message.getTraders().getRepresentativeTIN().getTIN()); //V70
				subset.setVertnl(message.getTraders().getRepresentativeTIN().getBO());    //V70 
			}
		}
		if (message.getTraders() != null && message.getTraders().getConsignorTIN() != null) {
			subset.setVerskd(message.getTraders().getConsignorTIN().getCustomerId());
			//V70: subset.setVeszb(message.getTraders().getConsignorTIN().getTIN());
			subset.setVerseori(message.getTraders().getConsignorTIN().getTIN());     //V70
			subset.setVersnl(message.getTraders().getConsignorTIN().getBO());        //V70
		}
		if (message.getTraders() != null && message.getTraders().getAcquirerTIN() != null) {
			subset.setErwkd(message.getTraders().getAcquirerTIN().getCustomerId());
			//V70: subset.setErwzb(message.getTraders().getAcquirerTIN().getTIN());
			subset.setErweori(message.getTraders().getAcquirerTIN().getTIN());
			subset.setErwnl(message.getTraders().getAcquirerTIN().getBO());          //V70
			subset.setErwust(message.getTraders().getAcquirerTIN().getVATNumber()); //V70
		}
		if (message.getTraders() != null && message.getTraders().getConsigneeTIN() != null) {
			subset.setEmpfkd(message.getTraders().getConsigneeTIN().getCustomerId());
			//V70: subset.setEmpzb(message.getTraders().getConsigneeTIN().getTIN());
			subset.setEmpfeori(message.getTraders().getConsigneeTIN().getTIN());        //V70
			subset.setEmpfnl(message.getTraders().getConsigneeTIN().getBO());          //V70
		}
		if (message.getTraders() != null && message.getTraders().getBuyerTIN() != null) {
			subset.setKfkd(message.getTraders().getBuyerTIN().getCustomerId());
			//V70: subset.setKfzb(message.getTraders().getBuyerTIN().getTIN());
			subset.setKfeori(message.getTraders().getBuyerTIN().getTIN());            //V70
			subset.setKfnl(message.getTraders().getBuyerTIN().getBO());               //V70
		}
		if (message.getTraders() != null && message.getTraders().getSellerTIN() != null) {
			subset.setVerkkd(message.getTraders().getSellerTIN().getCustomerId());
			//V70: subset.setVekzb(message.getTraders().getSellerTIN().getTIN());
			subset.setVerkeori(message.getTraders().getSellerTIN().getTIN());         //V70
			subset.setVerknl(message.getTraders().getSellerTIN().getBO());            //V70
		}
		if (message.getTraders() != null && message.getTraders().getCustomsValueDeclarantTIN() != null) {
			subset.setZwakd(message.getTraders().getCustomsValueDeclarantTIN().getCustomerId());
			//V70: subset.setZwazb(message.getTraders().getCustomsValueDeclarantTIN().getTIN());
			subset.setZwaeori(message.getTraders().getCustomsValueDeclarantTIN().getTIN());   //V70
			subset.setZwanl(message.getTraders().getCustomsValueDeclarantTIN().getBO());      //V70
		}
		if (message.getTraders() != null && 
				message.getTraders().getRepresentativeOfCustomsValueDeclarantTIN() != null) {
			subset.setVzwakd(message.getTraders().getRepresentativeOfCustomsValueDeclarantTIN().getCustomerId());
			//V70: subset.setVwazb(message.getTraders().getRepresentativeOfCustomsValueDeclarantTIN().getTIN());
			subset.setVzwaeori(message.getTraders().getRepresentativeOfCustomsValueDeclarantTIN().getTIN()); //V70			
			subset.setVzwanl(message.getTraders().getRepresentativeOfCustomsValueDeclarantTIN().getBO());    //V70 
		}
		if (message.getTraders() != null && message.getTraders().getDeclarantForInvoiceTIN() != null) {
			subset.setRechkd(message.getTraders().getDeclarantForInvoiceTIN().getCustomerId());
			//V70: subset.setRechzb(message.getTraders().getDeclarantForInvoiceTIN().getTIN());
			subset.setRecheori(message.getTraders().getDeclarantForInvoiceTIN().getTIN());				//V70	
			subset.setRechnl(message.getTraders().getDeclarantForInvoiceTIN().getBO());					//V70	
		}
		if (message.getTotalNumberPositions() != null) {            
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
			subset.setGesart(message.getBusiness().getBusinessTypeCode());			
		}
		if (message.getBusiness() != null) { 
			subset.setGesart(message.getBusiness().getBusinessTypeCode());    
		}
		subset.setAusort(message.getPlaceOfDeclaration());    
		subset.setVland(message.getDispatchCountry());       
		
		return subset;
	}
	
	private TsADR mapADR(Address adr, String typ) {
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
		//subset.setApostf(adr.getPOBox());
		subset.setAlnd(adr.getCountry());
		subset.setAplz(adr.getPostalCode());
		subset.setAort(adr.getCity());
		subset.setAoteil(adr.getCountrySubEntity());
		    
		return subset;
	}
	
	private TsAN2 mapAN2() {
		TsAN2 subset = new TsAN2();
		
		subset.setBeznr(message.getReferenceNumber());
		subset.setEinzst(message.getCustomsOfficeEntry());
		subset.setEinfsi(message.getImporterLocation());
		subset.setWaort(message.getGoodsLocation());
		subset.setBbland(message.getDestinationFederalState());
		subset.setFinamt(message.getTaxOffice());
		subset.setStzoll(message.getCustomsStatus());
		if (transmitter.equals("KFF") && message.getCustomsStatus() != null) {    
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
		subset.setRohm(Utils.addZabisDecimalPlaceV7(message.getGrossMass(), 1));
		if (subversion.equals("2001")) {     //EI20130425
			subset.setRohm(message.getGrossMass());
		}
		
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
			subset.setPreisr(Utils.addZabisDecimalPlaceV7(message.getInvoice().getAmount(), 2));			
			subset.setKurs(Utils.addZabisDecimalPlaceV7(message.getInvoice().getExchangeRate(), 6));			
			if (subversion.equals("2001")) {     //EI20130422
				subset.setPreisr((message.getInvoice().getAmount()));
				subset.setKurs(message.getInvoice().getExchangeRate());
			}
			subset.setWaehr(message.getInvoice().getCurrency());
			subset.setMzp(message.getInvoice().getRelevantTime());
		}	
		//EI20130620: zuerst wird preis von Invoice genommen, wenn aber Business auch gefuellt ist, dann ueberschreiben
		if (isBDP && message.getBusiness() != null  &&               //EI20130620			 
			 !Utils.isStringEmpty(message.getBusiness().getInvoicePrice())  &&
			 !Utils.isStringEmpty(message.getBusiness().getCurrency())) { 
			subset.setPreisr(Utils.addZabisDecimalPlaceV7(message.getBusiness().getInvoicePrice(), 2));  //EI20130628
			if (subversion.equals("2001")) {     //EI20130628
				subset.setPreisr(message.getBusiness().getInvoicePrice()); 
			}
			subset.setWaehr(message.getBusiness().getCurrency());
		}
		
		subset.setSuaart(message.getWriteOffSumAType());
		subset.setAzvbew(message.getWriteOffBonWarAvuvAuthNum());
		subset.setZlbez(message.getWriteOffBonWarRefNum());
		subset.setKuatnr(message.getCustomerOrderNumber());
		//V70 TODO: neues Feld beland
		
		return subset;
	}
	
	private ArrayList<TsEMP> mapEMPList() {
		ArrayList<TsEMP> list = new ArrayList<TsEMP>();
		String dummy = "";
		
		/*
		if (message.??? (getTragers().???) != null) {
			for (??? aaa : message.get???List()) {
				if (aaa != null)  {
				*/
					TsEMP subset = new TsEMP();					
					subset.setBeznr(beznr);
					subset.setKunnr(dummy);
					subset.setEori(dummy);     
					subset.setNlnr(dummy);     
					subset.setUstid(dummy);    
					subset.setAnam1(dummy);    
					subset.setAnam2(dummy);    
					subset.setAnam3(dummy);    
					subset.setStr(dummy);      
					subset.setPostf(dummy);    
					subset.setLnd(dummy);      
					subset.setPlz(dummy);      
					subset.setOrt(dummy);      
					subset.setOteil(dummy);    
					subset.setTel(dummy);	  
					subset.setFax(dummy);	  
					subset.setEmail(dummy);    
					subset.setKontkt(dummy);   
					subset.setBemerk(dummy);   
					
					msgEZA.addEMPList(subset);
				//}
			//}
		//} 
		
		return list;
	}
	
	// Unterlagen auf Kopf-Ebene
	private void mapEzaUntList(ArrayList<Document> document) {
		if (document == null) {
			return;
		}		
		for (Document doc : message.getDocumentList()) {
			if (doc != null)  {
				TsUNT subset = new TsUNT();
					
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
	
	// Ergänzungssatz Aufschubkonten
	private void mapEzaErkList() {
		TsERK subset = null;
		if (message.getDefermentList() != null) { 
			for (Deferment deferment : message.getDefermentList()) {
				if (deferment != null)  {
					subset = new TsERK();
					
					subset.setKbeznr(beznr);
					subset.setKtoaa(deferment.getType());
					subset.setKtokd(deferment.getCustomerId());
					//V70: subset.setKtozb(deferment.getTIN());
					subset.setKtoeori(deferment.getTIN());   //V70
					subset.setKtonl(deferment.getBO());      //V70
					msgEZA.addERKList(subset);
				}
			}
		}
	}

	// DV1-Satz
	private TsDVT mapDV1(DV1 dv1) {
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
	/* EI20131028 auskommentiert/ersetzt
	private TsTXT mapTXT(String pos) {
		TsTXT subset = null;
		
		if (message.getAdditionalInformation() != null && !message.getAdditionalInformation().isEmpty()) {
			subset = new TsTXT(); 
			subset.setBeznr(beznr);
			subset.setPosnr(pos);
			subset.setText(message.getAdditionalInformation());
		}
		
		return subset;
	}
	*/
	private TsTXT mapTXT(String pos) {
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
		subset.setMenge(Utils.addZabisDecimalPlaceV7(item.getAmount(), 3));		
		if (subversion.equals("2001")) {     //EI20130422
			subset.setMenge(item.getAmount());
		}
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
		subset.setEigm(Utils.addZabisDecimalPlaceV7(item.getNetMass(), 1));
		subset.setProhm(Utils.addZabisDecimalPlaceV7(item.getGrossMass(), 1));	
		if (subversion.equals("2001")) {     //EI20130422
			subset.setEigm(item.getNetMass());
			subset.setProhm(item.getGrossMass());
		}
		if (transmitter.equals("KFF")) {    //EI20120207 JIRA-Ticket KCX-94 (siehe calculateNetMass(...))
			//in TsPO2 muessten die Gewichte ohne Punkte und ohne Komma sein, 
			//hier sind die gewichte bereits richtig formatiert:			
			subset.setEigm(calculateNetMass(subset.getProhm(), subset.getEigm()));
			//calculateNetMass liefert einen auf integer gerundeten ergebnis == richtig fuer Zabis berechnet und formatiert
		}
		if (item.getImportPackage() != null) {
			subset.setStkanz(item.getImportPackage().getQuantity());
			subset.setStkart(item.getImportPackage().getType());
			subset.setStkzei(item.getImportPackage().getMarks());
		}
		
		subset.setBeabeg(item.getRequestedPrivilege());
		subset.setBeding(item.getCondition());
		subset.setZollw(Utils.addZabisDecimalPlaceV7(item.getCustomsValue(), 2));
		subset.setEust(Utils.addZabisDecimalPlaceV7(item.getImportTurnoverTax(), 2));		
		if (subversion.equals("2001")) {     //EI20130422
			subset.setZollw(item.getCustomsValue());
			subset.setEust(item.getImportTurnoverTax());
		}
		if (item.getInvoice() != null) {
			subset.setRpreis(Utils.addZabisDecimalPlaceV7(item.getInvoice().getAmount(), 2));
			subset.setRabatt(Utils.addZabisDecimalPlaceV7(item.getInvoice().getReductionSurcharge(), 2));
			subset.setSkonto(Utils.addZabisDecimalPlaceV7(item.getInvoice().getDiscount(), 2));			
			if (subversion.equals("2001")) {     //EI20130422
				subset.setRpreis(item.getInvoice().getAmount());
				subset.setRabatt(item.getInvoice().getReductionSurcharge());
				subset.setSkonto(item.getInvoice().getDiscount());	
			}
		}
		
		if (item.getNetPrice() != null) {
			//	AK111027
			//subset.setNetto(item.getNetPrice().getAmount());
			subset.setWaenet(item.getNetPrice().getCurrency());
			subset.setKnet(Utils.addZabisDecimalPlaceV7(item.getNetPrice().getRate(), 6));			
			if (subversion.equals("2001")) {     //EI20130422
				subset.setKnet(item.getNetPrice().getRate());
			}
			subset.setKznet(item.getNetPrice().getCode());
		}
		

		if (item.getIndirectPayment() != null) {
			subset.setMzahl(Utils.addZabisDecimalPlaceV7(item.getIndirectPayment().getAmount(), 2));
			subset.setWaemz(item.getIndirectPayment().getCurrency());
			subset.setKmzahl(Utils.addZabisDecimalPlaceV7(item.getIndirectPayment().getRate(), 6));
			subset.setKzzahl(item.getIndirectPayment().getCode());			
			if (subversion.equals("2001")) {     //EI20130422
				subset.setMzahl(item.getIndirectPayment().getAmount());
				subset.setKmzahl(item.getIndirectPayment().getRate());
			}
		}
		
		if (item.getStatistic() != null) {
			subset.setWmahst(Utils.addZabisDecimalPlaceV7(item.getStatistic().getQuantity(), 3));
			subset.setMeast(item.getStatistic().getMeasuringUnit());
			subset.setQmeast(item.getStatistic().getQualifierMeasuringUnit());
			subset.setAhwert(Utils.addZabisDecimalPlaceV7(item.getStatistic().getStatisticalValue(), 2));			
			if (subversion.equals("2001")) {     //EI20130422
				subset.setWmahst(item.getStatistic().getQuantity());
				subset.setAhwert(item.getStatistic().getStatisticalValue());
			}
		}
		
		if (item.getGoodsQuantityCustoms() != null) {
			subset.setWmzoll(Utils.addZabisDecimalPlaceV7(item.getGoodsQuantityCustoms().getQuantity(), 3));
			subset.setMezoll(item.getGoodsQuantityCustoms().getMeasuringUnit());
			subset.setQmezol(item.getGoodsQuantityCustoms().getQualifierMeasuringUnit());			
			if (subversion.equals("2001")) {     //EI20130422
				subset.setWmzoll(item.getGoodsQuantityCustoms().getQuantity());	
			}
		}
		

		if (item.getGoodsQuantityAgriculturalDuty() != null) {
			subset.setWmazol(Utils.addZabisDecimalPlaceV7(item.getGoodsQuantityAgriculturalDuty().getQuantity(), 3));
			subset.setMeazol(item.getGoodsQuantityAgriculturalDuty().getMeasuringUnit());
			subset.setQmeazo(item.getGoodsQuantityAgriculturalDuty().getQualifierMeasuringUnit());			
			if (subversion.equals("2001")) {     //EI20130422
				subset.setWmazol(item.getGoodsQuantityAgriculturalDuty().getQuantity());
			}
		}
		
		subset.setVered(Utils.addZabisDecimalPlaceV7(item.getProcessingFeeValueIncrease(), 2));
		subset.setZuskeu(Utils.addZabisDecimalPlaceV7(item.getExtraCostImportVAT(), 2));
		subset.setTabak(item.getTobaccoDutyCodeNumber());	
		if (subversion.equals("2001")) {     //EI20130422
			subset.setVered(item.getProcessingFeeValueIncrease());
			subset.setZuskeu(item.getExtraCostImportVAT());
		}
		
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
	private ArrayList<TsUNP> getUNPList(GoodsItemDeclaration item, String itemNumber) {
		ArrayList<TsUNP> unpList = null;   //EI20130724: statt List jetzt ArrayList
		TsUNP subset = null;
		
		if (item.getDocumentList() != null && item.getDocumentList().size() > 0) {
			unpList = new ArrayList<TsUNP>();
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
				if (document.getType().equals("BI119") || document.getType().equals("N380")) { //EI20120319
					subset.setUntber("4");      
				}
				subset.setUntnr(document.getNumber());
				subset.setUntdat(document.getIssueDate());
				subset.setKzvorl(document.getCodeExists());
				subset.setMenge(Utils.addZabisDecimalPlaceV7(document.getWriteOffAmountValue(), 3));				
				if (subversion.equals("2001")) {     //EI20130422
					subset.setMenge(document.getWriteOffAmountValue());
				}
				subset.setMeinh(document.getWriteOffMeasuringUnit());
				subset.setQmeinh(document.getWriteOffQualifierMeasuringUnit());
	  
				unpList.add(subset);
				}
			}
		} else {
			//TODO-
		}
		return unpList;
	}

	// Gehaltsangaben
	private ArrayList<TsGEH> getGEHList(GoodsItemDeclaration item, String itemNumber) {
		ArrayList<TsGEH> gehList = null;   //EI20130724: statt List jetzt ArrayList
		TsGEH subset = null;
		
		if (item.getSalaryList() != null && item.getSalaryList().size() > 0) {
			gehList = new ArrayList<TsGEH>();
			
			for (Salary salary : item.getSalaryList()) {
				subset = new TsGEH();

				subset.setGehbnr(beznr);
				subset.setGehpnr(itemNumber);
				subset.setGhart(salary.getType());
				subset.setGhprz(Utils.addZabisDecimalPlaceV7(salary.getRateOrPercent(), 2));  //EI20130426
				if (subversion.equals("2001")) {   //EI20130426
					subset.setGhprz(salary.getRateOrPercent());
				}
				
				gehList.add(subset);
			}
		}
			
		return gehList;
	}

	// Verbrauchssteuer
	private ArrayList<TsVST> getVSTList(GoodsItemDeclaration item, String itemNumber) {
		ArrayList<TsVST> vstList = null;   //EI20130724: statt List jetzt ArrayList
		TsVST subset = null;
		
		if (item.getExciseList() != null && item.getExciseList().size() > 0) {
			vstList = new ArrayList<TsVST>();
			
			for (Excise excise : item.getExciseList()) {
				subset = new TsVST();

				subset.setVstbnr(beznr);
				subset.setVstpnr(itemNumber);
				subset.setVbstc(excise.getTaxCode());
				subset.setMenge(Utils.addZabisDecimalPlaceV7(excise.getTaxQuantity(), 3));
				subset.setMeinh(excise.getMeasuringUnit());
				subset.setQmeinh(excise.getQualifierMeasuringUnit());
				subset.setGrprz(Utils.addZabisDecimalPlaceV7(excise.getRateOrPercent(), 2));
				subset.setPreis(Utils.addZabisDecimalPlaceV7(excise.getTaxValue(), 2));				
				if (subversion.equals("2001")) {     //EI20130422
					subset.setMenge(excise.getTaxQuantity());
					subset.setGrprz(excise.getRateOrPercent());
					subset.setPreis(excise.getTaxValue());
				}
				
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
			subset.setBegmge(Utils.addZabisDecimalPlaceV7(item.getQuota().getQuantity(), 3));			
			if (subversion.equals("2001")) {     //EI20130422
				subset.setBegmge(item.getQuota().getQuantity());
			}
			subset.setBegmeh(item.getQuota().getMeasuringUnit());
			subset.setQbegme(item.getQuota().getQualifierMeasuringUnit());
		}
		return subset;
	}

	//Abzüge/Hinzurechnungen 
	private ArrayList<TsAHI> getAHIList(GoodsItemDeclaration item, String itemNumber) {
		ArrayList<TsAHI> ahiList = null;    //EI20130724: statt List jetzt ArrayList
		TsAHI subset = null;
		
		if (item.getAdditionsDeductionsList() != null && item.getAdditionsDeductionsList().size() > 0) {
			ahiList = new ArrayList<TsAHI>();
			
			for (AdditionalCosts  additionalCosts : item.getAdditionsDeductionsList()) {
				subset = new TsAHI();
				
				subset.setAhibnr(beznr);
				subset.setAhipnr(itemNumber);
				subset.setArhia(additionalCosts.getType());
				subset.setPreis(Utils.addZabisDecimalPlaceV7(additionalCosts.getAmount(), 2));
				subset.setAhwae(additionalCosts.getCurrency());
				subset.setKzia(additionalCosts.getIATARateCode());
				subset.setKzku(additionalCosts.getRateAgreedCode());
				subset.setKurs(Utils.addZabisDecimalPlaceV7(additionalCosts.getRate(), 6));
				subset.setKudat(additionalCosts.getDate());
				subset.setArhipr(Utils.addZabisDecimalPlaceV7(additionalCosts.getPercentageFreightCost(), 2));
				if (subversion.equals("2001")) {     //EI20130422
					subset.setPreis(additionalCosts.getAmount());
					subset.setKurs(additionalCosts.getRate());
					subset.setArhipr(additionalCosts.getPercentageFreightCost());
				}
				
				ahiList.add(subset);
			}
		}
		return ahiList;
	}
	
	// Minderungsangaben 
	private ArrayList<TsMIN> getMINList(GoodsItemDeclaration item, String itemNumber) {
		ArrayList<TsMIN> minList = null;    //EI20130724: statt List jetzt ArrayList
		TsMIN subset = null;
		
		if (item.getReductionStatementList() != null && item.getReductionStatementList().size() > 0) {
			minList = new ArrayList<TsMIN>();
			
			for (SpecialStatement specialStatement : item.getReductionStatementList()) {
				subset = new TsMIN();
				
				subset.setMinbnr(beznr);
				subset.setMinpnr(itemNumber);
				subset.setMabgr(specialStatement.getGroup());
				subset.setMprs(Utils.addZabisDecimalPlaceV7(specialStatement.getValue(), 2));				
				if (subversion.equals("2001")) {     //EI20130422
					subset.setMprs(specialStatement.getValue());	
				}
				minList.add(subset);
			}
		}
		return minList;
	}
	
	//Besondere Wertangaben
	private ArrayList<TsBEW> getBEWList(GoodsItemDeclaration item, String itemNumber) {
		ArrayList<TsBEW> bewList = null;    //EI20130724: statt List jetzt ArrayList
		TsBEW subset = null;
		
		if (item.getSpecialValueStatementList() != null && item.getSpecialValueStatementList().size() > 0) {
			bewList = new ArrayList<TsBEW>();
			
			for (SpecialStatement specialValueStatement : item.getSpecialValueStatementList()) {
				subset = new TsBEW();
				
				subset.setBewbnr(beznr);
				subset.setBewpnr(itemNumber);
				subset.setBewe(Utils.addZabisDecimalPlaceV7(specialValueStatement.getValue(), 2));				
				if (subversion.equals("2001")) {     //EI20130422
					subset.setBewe(specialValueStatement.getValue());
				}
				subset.setPrsar(specialValueStatement.getType());
				
				bewList.add(subset);
			}
		}
		return bewList;
	}

	// Sonderfallangaben  
	private ArrayList<TsSOF> getSOFList(GoodsItemDeclaration item, String itemNumber) {
		ArrayList<TsSOF> sofList = null;    //EI20130724: statt List jetzt ArrayList
		TsSOF subset = null;
		int posnr = 0;
		
		//EI20130426: if (getSpecialValueStatementList() != null && getSpecialValueStatementList().size() > 0) {
		if (item.getSpecialCaseStatementList() != null && item.getSpecialCaseStatementList().size() > 0) { //EI20130426
			sofList = new ArrayList<TsSOF>();
			
			for (SpecialStatement specialCaseStatement : item.getSpecialCaseStatementList()) {
				subset = new TsSOF();
				
				posnr++;
				subset.setRegkz(beznr);
				subset.setLfdnr(itemNumber);
				subset.setPosnr(posnr + "");
				subset.setSogru(specialCaseStatement.getGroup());
				subset.setSoanw(specialCaseStatement.getType());
				subset.setSosbf(Utils.addZabisDecimalPlaceV7(specialCaseStatement.getValue(), 5));				
				if (subversion.equals("2001")) {     //EI20130422
					subset.setSosbf(specialCaseStatement.getValue());
				}
				
				sofList.add(subset);
			}
		}
		return sofList;
	}

	private void mapEzaBSUList(ArrayList<Manifest> manifestList) {  //EI20130724 neu
		if (manifestList == null) {
			return;
		}				
		for (Manifest manifest : manifestList) {
			TsBSU bsu = new TsBSU();
			bsu.setBeznr(message.getReferenceNumber());
			bsu.setPosnr("0");
			bsu.setVregnr(manifest.getRegistrationNumber());
			bsu.setVposnr(manifest.getItemNumber());
			bsu.setSuastk(manifest.getNumberOfPieces());
			if (manifest.getCustodian() != null) {
				bsu.setVrwknr(manifest.getCustodian().getCustomerId());				
				bsu.setVerweori(manifest.getCustodian().getTIN()); 		
				bsu.setVerwId(manifest.getCustodian().getIdentificationType());  //EI20130802
			}
			if (manifest.getSpecificKey() != null) {
				bsu.setSpoart(manifest.getSpecificKey().getCode());
				bsu.setSponr(manifest.getSpecificKey().getNumber());				
			}
			bsu.setAzvagl(manifest.getATLASAlignment());
						
			msgEZA.addBSUList(bsu);
		}	
	}
	
	private void mapEzaBZLList(ArrayList<Completion> list) { //EI20130724 neu
		if (list == null) { 
			return;
		}		
		for (Completion completion : list) {
			TsBZL bzl = new TsBZL();
			bzl.setBeznr(message.getReferenceNumber());
			bzl.setVregnr(completion.getRegistrationNumber());
			bzl.setVposnr(completion.getItemNumber());
			bzl.setAtlas(completion.getATLASInFlow());
			bzl.setKzuebl(completion.getCommonUse());
			bzl.setWanr(completion.getCommodityCode());
			if (completion.getOutflow() != null) {				
				bzl.setMgeabg(Utils.addZabisDecimalPlaceV7(completion.getOutflow().getQuantity(), 3));
				bzl.setMeabg(completion.getOutflow().getUnit());
				bzl.setQmeabg(completion.getOutflow().getQualifier());
				if (subversion.equals("2001")) {     //EI20130425
					bzl.setMgeabg(completion.getOutflow().getQuantity());
				}
			}
			if (completion.getTradedVolume() != null) {
				bzl.setMgehdl(Utils.addZabisDecimalPlaceV7(completion.getTradedVolume().getQuantity(), 3));
				bzl.setMehdl(completion.getTradedVolume().getUnit());
				bzl.setQmehdl(completion.getTradedVolume().getQualifier());
				if (subversion.equals("2001")) {     //EI20130425
					bzl.setMgehdl(completion.getTradedVolume().getQuantity());
				}
			}
			bzl.setTxzus(completion.getInformation());
			bzl.setAzvagl(completion.getATLASAlignment());			
			
			msgEZA.addBZLList(bzl);
		}		
	}
	private void mapEzaBAVList(ArrayList<Completion> list) { //EI20130724 neu
		if (list == null) {
			return;
		}		
		for (Completion completion : list) {
			TsBAV bav = new TsBAV();
			bav.setBeznr(message.getReferenceNumber());
			bav.setVregnr(completion.getRegistrationNumber());
			bav.setVposnr(completion.getItemNumber());
			bav.setAtlas(completion.getATLASInFlow());
			bav.setTxzus(completion.getInformation());
			bav.setAzvagl(completion.getATLASAlignment());
			
			msgEZA.addBAVList(bav);
		}		
	}
	
	private boolean isBDP() {
		if (this.kidsHeader == null) {
			return false;
		}
		if (this.kidsHeader.getReceiver() != null && this.kidsHeader.getReceiver().contains("BDP")) {			
			return true;
		} else if (this.kidsHeader.getReceiver() != null && this.kidsHeader.getReceiver().equals("DE.KCX.TST")) {
			return true;
		} else {	
			return false;
		}
	}
}
