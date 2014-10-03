/*
 * Function    : MapExpDatToECustoms.java
 * Date        : 30.09.2009
 * Author      : Kewill CSF / iwaniuk
 * Description : Mapping of KIDS format of ExportDeclaration to eCustoms/BellDavis

 * Changes
 * -----------
 * Author      : EI
 * Date        : 
 * Label       : EI20100121
 * Description : MsgUKExpDat replaced with MsgExpDat
 *  
 * Author      : EI20100205
 * Date        :
 * Label       :
 * Description : zum Testzwecken localId auf  "test" gesetzt.
 * 
 * Author		: CK
 * Date			: 13.08.2012
 * Label		: CK20120813
 * Description	: The KIDS xml tag "GoodsDeclaration/IntendedOfficeOfExit" has to be always mapped into 
 * 					HH:EXIT_OFFICE instead of "GoodsDeclaration/CustomsOfficeExport"
 * 					see Jira-Ticket KCX-151.
 */

package com.kewill.kcx.component.mapping.countries.uk.exp.kids2Bdec;

import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpDat;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpDatPos;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ExportRefundItem;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Ingredients;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Invoice;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.formats.Bdec.messages.MsgECustoms;
import com.kewill.kcx.component.mapping.formats.Bdec.messages.MsgECustomsPos;
import com.kewill.kcx.component.mapping.formats.Bdec.subsets.TsAI;
import com.kewill.kcx.component.mapping.formats.Bdec.subsets.TsDB;
import com.kewill.kcx.component.mapping.formats.Bdec.subsets.TsDC;
import com.kewill.kcx.component.mapping.formats.Bdec.subsets.TsDD;
import com.kewill.kcx.component.mapping.formats.Bdec.subsets.TsDI;
import com.kewill.kcx.component.mapping.formats.Bdec.subsets.TsDL;
import com.kewill.kcx.component.mapping.formats.Bdec.subsets.TsDO;
import com.kewill.kcx.component.mapping.formats.Bdec.subsets.TsDP;
import com.kewill.kcx.component.mapping.formats.Bdec.subsets.TsDT;
import com.kewill.kcx.component.mapping.formats.Bdec.subsets.TsDV;
import com.kewill.kcx.component.mapping.formats.Bdec.subsets.TsHH;
import com.kewill.kcx.component.mapping.formats.Bdec.subsets.TsHR;
import com.kewill.kcx.component.mapping.formats.Bdec.subsets.TsHS;
import com.kewill.kcx.component.mapping.formats.Bdec.subsets.TsNA;
import com.kewill.kcx.component.mapping.formats.Bdec.subsets.TsTD;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul		: MapExpDatToECustoms<br>
 * Erstellt		: 130.09.2009<br>
 * Beschreibung	: Mapping of KIDS format of ExportDeclaration to to eCustoms/BellDavis.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class MapExpDatToECustoms extends KidsMessage {
		
	private MsgExpDat msgUKExpDat;
	private MsgECustoms msgECustoms;	
	private String edecType;

	public MapExpDatToECustoms(XMLEventReader parser) throws XMLStreamException {
		msgUKExpDat = new MsgExpDat(parser);
		msgECustoms = new MsgECustoms();	
	}
	
	//public String getMessage()  {
	public String getMessage(String edecType) throws Exception {	
		this.edecType = edecType;
    	String res = "";            	      
        
        try {  
        	//kidsMessageName = kidsHeader.getMessageName();
            msgUKExpDat.parse(HeaderType.KIDS);                        
    		//referenceNumber = msgUKExpDat.getReferenceNumber();
            //getCommonFieldsDTO().setReferenceNumber(msgUKExpDat.getReferenceNumber());    		
            //msgECustoms.getVorSubset().setMsgid(getKidsHeader().getMessageID());     		
               		       
            //füllen der übrigen Msg-Elementen:   
            //HH - Header
            msgECustoms.setHHSubset(getHH());            
            //HD - Header Document
       		if (msgUKExpDat.getDocumentList() != null) {
       			List <Document>list = msgUKExpDat.getDocumentList();       			
       			if (list != null) {
       				for (int i = 0, listSize = list.size(); i < listSize; i++) {
       					msgECustoms.addHDList(getDO("HD", list.get(i)));
       				}
       			}
       		}
       		//HS - Header Seals
       		if (msgUKExpDat.getSeal() != null) {
       			List<String> list = msgUKExpDat.getSeal().getSealNumberList();
				if (list != null) {
					for (int i = 0, listSize = list.size(); i < listSize; i++) {
						msgECustoms.addHSList(getHS(list.get(i)));
					}
				}
       		}
       		//HR - Header Route
       		if (msgUKExpDat.getTransportationRoute() != null) {
       			List<String> list = msgUKExpDat.getTransportationRoute().getCountryList();
				if (list != null) {
					for (int i = 0, listSize = list.size(); i < listSize; i++) {
						msgECustoms.addHRList(getHR(list.get(i)));
					}
				}
       		}
       		//HA(40) - Header AdditionalInformation 
       		if (msgUKExpDat.getAddInfoStatementList() != null) {
       			List<Text> list = msgUKExpDat.getAddInfoStatementList();       			
       			if (list != null) {
					for (int i = 0, listSize = list.size(); i < listSize; i++) {		
						if (list.get(i) != null) {    //EI20120911
							msgECustoms.addHAList(getAI("HA", list.get(i).getCode(), list.get(i).getText()));
						}
					}
				}       		
       		} 
       		
       	    //CZ=EUTrader	== Consignor
    		//CN=nonEUTrader == Consignee 
    		//DT=declarant	== Declarant
    		//WH=Warehouse	== WarehouseKeeper
    		//CM=Supervising == ?    		
    		msgECustoms.addNAList(getAddress("CZ", msgUKExpDat.getConsignor()));       		
    		msgECustoms.addNAList(getAddress("CN", msgUKExpDat.getConsignee()));       		
    		msgECustoms.addNAList(getAddress("DT", msgUKExpDat.getDeclarant()));       		
    		msgECustoms.addNAList(getAddress("WH", msgUKExpDat.getWarehousekeeper()));   
    		msgECustoms.addNAList(getAddress("CM", msgUKExpDat.getSupervisingCustomsOffice()));   //EI20120620
    		//EI20120704: adressen aus den positionen werden direkt in den DDs zu der NAList hinzugefuegt
    		
    		
       		//Positionen
    		//AK20120716
    		if (msgUKExpDat.getGoodsItemList() != null) {
	    		for (int i = 0, itemListSize = msgUKExpDat.getGoodsItemList().size(); i < itemListSize; i++) {
	    			MsgExpDatPos msgUKExpDatPos = msgUKExpDat.getGoodsItemList().get(i);    			
	    			MsgECustomsPos msgECustomsPos = new MsgECustomsPos();  
	    			
	    			//DD
	    			msgECustomsPos.setDDSubset(getDD(msgUKExpDatPos));
	    			
	    			//DP Packages,Marks&Number
	    			if (msgUKExpDatPos.getPackagesList() != null) {
	    				List <Packages> list = msgUKExpDatPos.getPackagesList();    				
	    				for (int j = 0, listSize = list.size(); j < listSize; j++) {
	                        msgECustomsPos.addDPList(getDP(list.get(j)));
	        			}
	    			}
	    			
	    			//DT TaxLine
	    			if (msgUKExpDatPos.getExportRefundItem() != null) {    
	    				//in KIDS ist ExportRefundItem keine Liste
	                    msgECustomsPos.addDTList(getDT(msgUKExpDatPos.getExportRefundItem()));    				      
	    			}
	    			
	    			//DA AdditionalInformation     			
	    			if (msgUKExpDatPos.getAddInfoStatementList() != null) {
	           			List<Text> list = msgUKExpDatPos.getAddInfoStatementList();       			
	           			if (list != null) {
	    					for (int j = 0, listSize = list.size(); j < listSize; j++) {						
	    						msgECustomsPos.addDAList(getAI("DA", list.get(j).getCode(), list.get(j).getText())); 
	    					}
	    				}       		
	           		}     			
	    		
	    			//DV  Previous Document  			    						
	    			if (msgUKExpDatPos.getPreviousDocumentList() != null) {
	    				List <PreviousDocument> list =  msgUKExpDatPos.getPreviousDocumentList();    				
	    				for (int j = 0, listSize = list.size(); j < listSize; j++) {
	    					msgECustomsPos.addDVList(getDV(list.get(j)));
	    				}    				
	    			}
	    			
	    			//DS Schemas (not used)
	    			
	    			//DL+DI+DB
	    			if (msgUKExpDatPos.getExportRefundItem() != null) {
	    				List <Ingredients> list = msgUKExpDatPos.getExportRefundItem().getIngredientsList();
	    				for (int j = 0, listSize = list.size(); j < listSize; j++) {
	    					msgECustomsPos.addDLList(getDL(list.get(j)));
	                        msgECustomsPos.addDIList(getDI(list.get(j)));
	    				}
	    				              
	                    msgECustomsPos.addDBList(getDB(msgUKExpDatPos.getExportRefundItem()));  //in KIDS keine Liste
	    			}
	    			//DO Documents
	    			if (msgUKExpDatPos.getDocumentList() != null)  {
	    				List <Document> list = msgUKExpDatPos.getDocumentList();
	    				for (int j = 0, listSize = list.size(); j < listSize; j++) {
	    					msgECustomsPos.addDOList(getDO("DO", list.get(j)));
	    				}
	    			}
	    			//DC Containers
	    			if (msgUKExpDatPos.getContainer() != null)  {
	    				List <String> list = msgUKExpDatPos.getContainer().getNumberList();    				
	    				for (int j = 0, listSize = list.size(); j < listSize; j++) {
	        				msgECustomsPos.addDCList(getDC(list.get(j)));
	    				}
	    			}
	    			//DU - UniqueProductCodes (not used)
	    			
	    			msgECustoms.addPosList(msgECustomsPos); 
	    			
	    			msgECustoms.addNAList(getAddress("CM", msgUKExpDat.getSupervisingCustomsOffice()));  
	    		}
        	}
    		       		
    		
            msgECustoms.setTDSubset(getTD());     
            
            res = msgECustoms.writeMessage();           
		
	    } catch (FssException e) {	    	
	        e.printStackTrace();
	    }
		    
	    return res;
	}

	////////////
	private TsHH getHH() {	
		if (msgUKExpDat == null) { return null; }
		
		TsHH 	tempHH 			= new TsHH();	
		 String localId = "";   //localId = "test";  		  
	     localId = Utils.getCustomerIdFromKewill(kidsHeader.getReceiver(), "BDEC", kidsHeader.getCountryCode()).trim();
	     tempHH.setClientRef(localId);   
	     
	     if (Utils.isStringEmpty(edecType)) {  //EI20110516
	    	 tempHH.setMessageType(msgUKExpDat.getTypeOfPermit()); 	
	     } else  {
	    	 tempHH.setMessageType(edecType);  //EI20110516
	     }
	     tempHH.setCapFlag("N");
	     if (msgUKExpDat.getCap() != null && !msgUKExpDat.getCap().isEmpty()) {
	    	 tempHH.setCapFlag("Y");  				  
	     } 
	     tempHH.setLocalCleranceFlag("N");   //TODO was ist das???  (Mussfeld)   		  
	     //not used: tempHH.setDeclnCrrn();    	     
	     tempHH.setDecltRep(msgUKExpDat.getTypeOfRepresentation());   
	     tempHH.setDestCntry(msgUKExpDat.getDestinationCountry());  
	     if (msgUKExpDat.getPlaceOfLoading() != null) {
	    	 String temp = "";	     
	    	 if (!Utils.isStringEmpty(msgUKExpDat.getPlaceOfLoading().getCode())) {
	    		 temp = msgUKExpDat.getPlaceOfLoading().getCode();	    		
	    	 } else { 
	    		 if (!Utils.isStringEmpty(msgUKExpDat.getPlaceOfLoading().getPostalCode())) {	
	    			 temp = msgUKExpDat.getPlaceOfLoading().getPostalCode();	   
	    		 }
	    		 if (!Utils.isStringEmpty(msgUKExpDat.getPlaceOfLoading().getCity())) {	
	    			temp = temp + " " + msgUKExpDat.getPlaceOfLoading().getCity();	   
	    		 }	    	 
	    	}
	    	tempHH.setEPlaLdg(temp.trim()); 
	     }	    
	     
	     // CK20120813 Begin
	     // tempHH.setExitOffice(msgUKExpDat.getCustomsOfficeExport()); 
	     tempHH.setExitOffice(msgUKExpDat.getIntendedOfficeOfExit());
	     // CK20120813 End
	     
	     if (msgUKExpDat.getLoadingTime() != null) {	     
	    	 //AK20120108 begin
	    	 if (edecType.equals("FDE")) {
		    	 tempHH.setGdsArrDtmInld(msgUKExpDat.getLoadingTime().getBeginTime());
	    		 tempHH.setGdsDepDtmInld(msgUKExpDat.getLoadingTime().getEndTime());
	    	 }
	    	 //AK20120108 end
	     }
	     tempHH.setDeclnUcr(msgUKExpDat.getReferenceNumber());    
	     //not used: tempHH.setDeclnPartNo();
	     //AK20120208
	     if (!edecType.equals("SDE")) {
	    	 tempHH.setMasterUcr(msgUKExpDat.getUCROtherSystem());
	     }
	     //not used: tempHH.setFirDan();    
	     //not used: tempHH.setFirDanPfx();   
	     if (msgUKExpDat.getTransportMeansBorder() != null) {   // 4=AIR, 3=TRUCK
	    	 //tempHH.setTrptModeCode(msgUKExpDat.getTransportMeansBorder().getTransportMode()); 
	    	 String mode = msgUKExpDat.getTransportMeansBorder().getTransportMode();
	    	 if (!Utils.isStringEmpty(mode) && mode.length() > 1 && mode.substring(0, 1).equals("0")) {
	    		 mode = mode.substring(1);	    		
	    	 } 
	    	 tempHH.setTrptModeCode(mode);
	    	 //AK20120208
	    	 if (edecType.equals("FDE"))  {
		    	 tempHH.setTrptId(msgUKExpDat.getTransportMeansBorder().getTransportationNumber());	    		 
	    	 }
	    	 tempHH.setTrptCntry(msgUKExpDat.getTransportMeansBorder().getTransportationCountry());   
	     }
	     //not used: tempHH.setCarrierName();  
	     if (msgUKExpDat.getTransportMeansInland() != null) {
	    	 //tempHH.setTrptModeInld(msgUKExpDat.getTransportMeansInland().getTransportMode());  
	    	 String mode = msgUKExpDat.getTransportMeansInland().getTransportMode();
	    	 if (!Utils.isStringEmpty(mode) && mode.length() > 1 && mode.substring(0, 1).equals("0")) {
	    		 mode = mode.substring(1);	    		
	    	 } 
	    	 //AK20120208   begin
	    	 if (!edecType.equals("PSA")) {
		    	 tempHH.setTrptModeInld(mode);	    		 
	    	 }
	    	 if (edecType.equals("FDE")) {
		    	 tempHH.setTrptIdInld(msgUKExpDat.getTransportMeansInland().getTransportationNumber());	    		 
	    	 }
	    	 //AK20120208   end
 	     }
	     if (msgUKExpDat.getConsignor() != null) {
			 if (msgUKExpDat.getConsignor().getPartyTIN() != null) {
			 	// C.K. 01.12.2011 CustomerIdentifier statt TIN in Feld ...shortname lt. Anweisung Max Ankirchner 
			 	// tempHH.setCnsgrShortname(msgUKExpDat.getConsignor().getPartyTIN().getTIN());   
				 tempHH.setCnsgrShortname(msgUKExpDat.getConsignor().getPartyTIN().getCustomerIdentifier());
			 }
	     }
	     if (msgUKExpDat.getConsignee() != null) {
			 if (msgUKExpDat.getConsignee().getPartyTIN() != null) {
			 	// C.K. 01.12.2011 CustomerIdentifier statt TIN in Feld ...shortname lt. Anweisung Max Ankirchner
			 	// tempHH.setCnsgeShortname(msgUKExpDat.getConsignee().getPartyTIN().getTIN());    
				 tempHH.setCnsgeShortname(msgUKExpDat.getConsignee().getPartyTIN().getCustomerIdentifier());
			 }
	     }
	     if (msgUKExpDat.getDeclarant() != null) {
			 if (msgUKExpDat.getDeclarant().getPartyTIN() != null) {
				// C.K. 01.12.2011 CustomerIdentifier statt TIN in Feld ...shortname lt. Anweisung Max Ankirchner
				// tempHH.setDesltShortname(msgUKExpDat.getDeclarant().getPartyTIN().getTIN());    
				 tempHH.setDesltShortname(msgUKExpDat.getDeclarant().getPartyTIN().getCustomerIdentifier());
			 }
	     }
	     //not used: tempHH.setGconTurn();    
	     //not used: tempHH.setRcnsgrTurn();    
	     //not used: tempHH.setRcnsgeTurn();    
	     if (msgUKExpDat.getWarehousekeeper() != null) {
			 if (msgUKExpDat.getWarehousekeeper().getPartyTIN() != null) {	
				// C.K. 01.12.2011 CustomerIdentifier statt TIN in Feld ...shortname lt. Anweisung Max Ankirchner
				// tempHH.setPermShortname(msgUKExpDat.getWarehousekeeper().getPartyTIN().getTIN());  
				 tempHH.setPermShortname(msgUKExpDat.getWarehousekeeper().getPartyTIN().getCustomerIdentifier());
			 }
	     }
	     //not used: tempHH.setInvTotAc(); <== EI20120223 habe ich doch gemapped
	     //EI20120223 Currency wird zuerst in Business gesucht, wenn nicht da/leer dann in Invoice
	     String invoiceCurrency = "";   //EI20120223 
	     String invoicePrice = "";      //EI20120223
	     if (msgUKExpDat.getBusiness() != null && !Utils.isStringEmpty(msgUKExpDat.getBusiness().getCurrency())) {
	    	 invoiceCurrency = msgUKExpDat.getBusiness().getCurrency();
	    	 invoicePrice = msgUKExpDat.getBusiness().getInvoicePrice();
	     } else {
	    	 Invoice invoice = msgUKExpDat.getInvoice();
	    	 if (invoice != null) {
		    	 invoiceCurrency = msgUKExpDat.getInvoice().getCurrency();
		    	 invoicePrice = msgUKExpDat.getInvoice().getInvoicePrice();
	    	 }
	     }
	    
	    	 //AK20120208
	     if (!edecType.equals("PSA")) {
	    	 //tempHH.setInvCrrn(msgUKExpDat.getInvoice().getCurrency());
	    	 tempHH.setInvCrrn(invoiceCurrency);
	    	 tempHH.setInvTotAc(invoicePrice);   //EI20120223 habe ich doch gemapped
	     }
	     
	     //AK20120208
	     if (!edecType.equals("PSA")) {
	    	 tempHH.setTotPkgs(msgUKExpDat.getTotalNumberOfPackages());
	     }
	     if (msgUKExpDat.getPlaceOfLoading() != null) {   								 //EI20110930
	    	 tempHH.setGdsLocn(msgUKExpDat.getPlaceOfLoading().getAgreedLocationOfGoods());   
	     }
	     
	     
	     if (edecType.equals("SDE")) {														//AK20120224
	    	 tempHH.setGdsLocnCntry(msgUKExpDat.getDispatchCountry());   					//EI20120223
	     }
	     
	     //not used: tempHH.setGdsShedId();   
	     if (msgUKExpDat.getCap() != null) {
	    	 tempHH.setIbClaimRef(msgUKExpDat.getCap().getIBClaimRef()); 
	    	 tempHH.setIbClaimType(msgUKExpDat.getCap().getIBClaimType()); 
	    	 tempHH.setIbRegNo(msgUKExpDat.getCap().getIBRegNo());
	    	 tempHH.setIbGan(msgUKExpDat.getCap().getIBGAN());
	     }
	     //not used: tempHH.setClientInfo()    ;   
	     //not used: tempHH.setDeclnType()     ; 
	     
	     //tempHH.setAcptncDTM(msgUKExpDat.getDeclarationTime()); 	
	     
	     if (!edecType.equals("PSA")) {
	    	 tempHH.setAcptncDTM(msgUKExpDat.getAcceptanceTime());   //EI20120123: Required for SDE (YYYYMMDDHHMM)
	     }	    
	    
	     tempHH.setDispCntry(msgUKExpDat.getDispatchCountry());   
	     //not used: tempHH.setFarpCode()      ;  
	     tempHH.setTdrOwnRefEnt(msgUKExpDat.getOrderNumber());   
	     //not used: tempHH.setAtrptCostAc()   ;   
	     //not used: tempHH.setFrgtChgeAc();   
	     //not used: tempHH.setFrgtChgeCrrn() ;   
	     //not used: tempHH.setFrgtAprtCode() ;   
	     //not used: tempHH.setInsAmtAc()     ;   
	     //not used: tempHH.setInsAmtCrrn()   ;   
	     //not used: tempHH.setOcdAc()        ;   
	     //not used: tempHH.setOcdCrrn()      ;   
	     //not used: tempHH.setVatAdjtAc()    ;   
	     //not used: tempHH.setVatAdjtCrrn()  ;   
	     //not used: tempHH.setInvDamtAc()    ;   
	     //not used: tempHH.setInvDamtCrrn()  ;   
	     //not used: tempHH.setInvDpct()      ;   
	     //not used: tempHH.setLocation();   
	     //not used: tempHH.setIDefaultPlace()    ;   
	     //not used: tempHH.setTemplateName()     ;   
	     if (msgUKExpDat.getContact() != null) {
	    	 tempHH.setUserRef(msgUKExpDat.getContact().getIdentity());   
	     }
	     tempHH.setMrn(msgUKExpDat.getUCRNumber());   
	     //not used: tempHH.setScndDan()          ;   
	     //not used: tempHH.setScndDanPfx()       ;   
	     //not used: tempHH.setNotifyShortname()  ;   
	     //not used: tempHH.setReprShortname()    ;   
	     //not used: tempHH.setEuArrLocnCode()    ;   
	     //not used: tempHH.setPlaUldgCode()      ;
	     //AK20120208
	     if (!edecType.equals("SDE")) {
	    	 tempHH.setTrptChgeMop(msgUKExpDat.getPaymentType());
	     }
	     //EI20120516: tempHH.setSpoffShortname(msgUKExpDat.getSupervisingCustomsOffice());   <== is was a string
	     if (msgUKExpDat.getSupervisingCustomsOffice() != null) {   //EI20120620	    	 	    
	     	 if (msgUKExpDat.getSupervisingCustomsOffice().getPartyTIN() != null) {			 	 
				 tempHH.setSpoffShortname(msgUKExpDat.getSupervisingCustomsOffice().getPartyTIN().getCustomerIdentifier());
			 }
	     }
	     if (msgUKExpDat.getSituationCode() != null) {	
	    	 if (msgUKExpDat.getSituationCode().equals("C")) {
	    		 tempHH.setStc("N");
	    	 } else {
	    		 tempHH.setStc("Y"); 
	    	 }	    		    	 
	     }	     
	     tempHH.setSpCirc(msgUKExpDat.getSituationCode());   
	     tempHH.setReason(msgUKExpDat.getCorrectionReason());  				
	     tempHH.setTransit(msgUKExpDat.getTransferToTransitSystem());
	     	     
	    String departureDate = msgUKExpDat.getDateOfDeparture();   	 //EI20120123	mandatory for SDE		    	 
	    if (!Utils.isStringEmpty(departureDate) && departureDate.length() > 8) {
	    	departureDate = departureDate.substring(0, 8);
	    }
	    tempHH.setGoodsDepDT(departureDate);  	   
	     
	    //tempHH.setEmcsFlag(msgUKExpDat.getEmcsFlag()); //EI20120123, new Tag (J/N); was soll rein?
		
		return tempHH;
	}
	
	private TsHS getHS(String number) {
		if (number == null) { return null; }
		if (Utils.isStringEmpty(number)) { return null; }
		
		TsHS tmpHS = new TsHS();		
		tmpHS.setSealId(number);
		//not used: tmpHS.setSealIdLng();		
		return tmpHS;
	}
	
	private TsHR getHR(String country) {
		if (country == null) { return null; }
		if (Utils.isStringEmpty(country)) { return null; }
		
		TsHR tempHR = new TsHR();
		//AK20120208
		if (!edecType.equals("SDE")) {
			tempHR.setRouteCntry(country);
		}

		return tempHR;
	}	

	private TsDD getDD(MsgExpDatPos msgUKExpDatPos)  {
		if (msgUKExpDatPos == null) { return null; }
		
		TsDD tempDD = new TsDD();
		
		if (msgUKExpDatPos.getApprovedTreatment() != null) {			
			String temp = ""; 				
			if (!Utils.isStringEmpty(msgUKExpDatPos.getApprovedTreatment().getDeclared())) {
				temp = msgUKExpDatPos.getApprovedTreatment().getDeclared();
			}			
			if (!Utils.isStringEmpty(msgUKExpDatPos.getApprovedTreatment().getPrevious())) {				
					//temp = temp + " " + msgUKExpDatPos.getApprovedTreatment().getPrevious();
				temp = temp + msgUKExpDatPos.getApprovedTreatment().getPrevious();				
			}										
			if (!Utils.isStringEmpty(msgUKExpDatPos.getApprovedTreatment().getCodeForRefund())) {
				//temp = temp + " " + msgUKExpDatPos.getApprovedTreatment().getCodeForRefund();
				temp = temp + msgUKExpDatPos.getApprovedTreatment().getCodeForRefund();
			} else if (!Utils.isStringEmpty(msgUKExpDatPos.getApprovedTreatment().getNational())) {					
				//temp = temp + " " + msgUKExpDatPos.getApprovedTreatment().getNational();
				temp = temp + msgUKExpDatPos.getApprovedTreatment().getNational();
			}			
			tempDD.setCpf(temp.trim());	
		}
		if (msgUKExpDatPos.getCommodityCode() != null) {
			tempDD.setTraficCmdtyCode(msgUKExpDatPos.getCommodityCode().getTarifCode());     
			tempDD.setEcSupplement(msgUKExpDatPos.getCommodityCode().getTarifAddition1());
			tempDD.setEcSupplement2(msgUKExpDatPos.getCommodityCode().getTarifAddition2());
		}
		if (msgUKExpDatPos.getExportRefundItem() != null) {
			 //AK20120108
	    	if (!edecType.equals("PSA")) {
				tempDD.setItemOrigCntry(msgUKExpDatPos.getExportRefundItem().getOriginCountry());	    		
	    	}
 			if (msgUKExpDatPos.getExportRefundItem().getIngredientsList() != null) {				
				if (msgUKExpDatPos.getExportRefundItem().getIngredientsList().size() > 0) {
				    //TODO in KIDS ist es eine Liste!?
					tempDD.setIbRecipeCode(msgUKExpDatPos.getExportRefundItem().
							getIngredientsList().get(0).getUniqueFactoryNumber());  
				}
			}
		}
		 //not used: tempDD.setItemDestCntry(msgUKExpDatPos); 	
		 //not used: tempDD.setItemDispCntry(msgUKExpDatPos);
		 tempDD.setItemGrossMass(msgUKExpDatPos.getGrossMass());	
		 tempDD.setItemNetMass(msgUKExpDatPos.getNetMass());
		 if (msgUKExpDatPos.getStatistic() != null) {
			 tempDD.setItemSuppUnits(msgUKExpDatPos.getStatistic().getAdditionalUnit());
			 tempDD.setItemStatvalDc(msgUKExpDatPos.getStatistic().getStatisticalValue());
			 tempDD.setItemPrcAc(msgUKExpDatPos.getStatistic().getValue());   
			 tempDD.setConfirmValue(msgUKExpDatPos.getStatistic().getStatisticalValueConfirmation());      
			 tempDD.setConfirmSuppUnits(msgUKExpDatPos.getStatistic().getAdditionalUnitConfirmation()); 			 
		 }
		 tempDD.setItemThrdQty(msgUKExpDatPos.getThirdQuantity());  
		
		 //not used: tempDD.setICnsgrShortname(msgUKExpDatPos);
		 if (msgUKExpDatPos.getConsignee() != null) {
			 if (msgUKExpDatPos.getConsignee().getPartyTIN() != null) {
				 //EI20111205: tempDD.setICnsgeShortname(msgUKExpDatPos.getConsignee().getPartyTIN().getTIN());
				 tempDD.setICnsgeShortname(msgUKExpDatPos.getConsignee().getPartyTIN().getCustomerIdentifier());
			 }
			 msgECustoms.addNAList(getAddress("CN", msgUKExpDatPos.getConsignee()));   //EI20120704
		 }			
		//EI20120516: tempDD.setISpoffShortname(msgUKExpDatPos.getSupervisingCustomsOffice());  <== it was a string		  
	     if (msgUKExpDatPos.getSupervisingCustomsOffice() != null) {   //EI20120516:
	    	 if (msgUKExpDatPos.getSupervisingCustomsOffice().getPartyTIN() != null) {
	    		 tempDD.setISpoffShortname(msgUKExpDatPos.getSupervisingCustomsOffice().getPartyTIN().getCustomerIdentifier());   
	    	 }
	    	 msgECustoms.addNAList(getAddress("CM", msgUKExpDatPos.getSupervisingCustomsOffice()));   //EI20120704
	     }
		 //AK20120108
   	 	if (!edecType.equals("SDE")) {
   			tempDD.setUndgCode(msgUKExpDatPos.getDangerousGoodsNumber());  		 
   			tempDD.setGdsDesc(msgUKExpDatPos.getDescription());   	 		
   	 	}
            		     
		//not used: tempDD.setIbtsSerilaNumber(msgUKExpDatPos);    
		//not used: tempDD.setClientInfo(msgUKExpDatPos);          
		//not used: tempDD.setPreference(msgUKExpDatPos);          
		//not used: tempDD.setQtaNo(msgUKExpDatPos);               
		//not used: tempDD.setValMthdCode(msgUKExpDatPos);         
		//not used: tempDD.setValAdjtCode(msgUKExpDatPos);        
		//not used: tempDD.setItemValAdjt(msgUKExpDatPos);        
		//not used: tempDD.setTemplateName(msgUKExpDatPos);        
		//not used: tempDD.setUserRef(msgUKExpDatPos);            
		 tempDD.setProductCode(msgUKExpDatPos.getArticleNumber());         
		 //AK20120108
	   	 if (!edecType.equals("SDE")) {
			 tempDD.setITrptChgeMop(msgUKExpDatPos.getPaymentType());	   	 		
	   	 }
		 //not used: tempDD.setIprProject(msgUKExpDatPos);
		 //not used: tempDD.setOprProject(msgUKExpDatPos);  
		 if (Utils.isStringEmpty(msgUKExpDatPos.getNetMass())) {
			 tempDD.setConfirmNetMass("N");
		 } else {
			 tempDD.setConfirmNetMass("Y");  		 
		 }
		 return tempDD;
	}
	
	private TsDP getDP(Packages packages) {

		if (packages == null) { return null; }

		TsDP tempDP = new TsDP();	
		tempDP.setPkgCount(packages.getQuantity());		
		tempDP.setPkgKind(packages.getType());
		tempDP.setPkgMarks(packages.getMarks());
		//not used: tempDP.setPkgLng(packages.???);		
		return tempDP;
	}
	
	private TsDT getDT(ExportRefundItem argument) {
		if (argument == null) { return null; }
		
		TsDT tempDT = new TsDT();  

		 //AK20120108
   	 	if (!edecType.equals("PSA")) {
   			tempDT.setTtyCode("350");  //??? TODO
   	 		tempDT.setMopCode("L");    //??? TODO		
   	 	}
		
		return tempDT;
	}
	
	private TsDV getDV(PreviousDocument prevDoc) {			
		if (prevDoc == null) {
			return null;
		}
		
		TsDV tempDV = new TsDV();
		tempDV.setPrevDocType(prevDoc.getType());
		tempDV.setPrevDocRef(prevDoc.getMarks());		
		//not used: tempDV.setPrevDocLng();
		if (prevDoc.getAdditionalInformation() != null && prevDoc.getAdditionalInformation().length() == 1) {
			tempDV.setPrevDocClass(prevDoc.getAdditionalInformation());
		}
		return tempDV;
	}

	private TsDL getDL(Ingredients argument) {
		if (argument == null) { return null; }
			
		TsDL tempDL	= new TsDL();
		tempDL.setLicenceRef(argument.getLicenceNumber());
		tempDL.setLicenceLineNo(argument.getLicenceLineNumber());   
		tempDL.setlicenceStatus(argument.getLicenceStatus());    
		tempDL.setLicenceQuantity(argument.getLicenceQuantity());  
		return tempDL;
	}
	
	private TsDI getDI(Ingredients argument) {
		if (argument == null) { return null; }
		
		TsDI tempDI	= new TsDI();
		tempDI.setIbIngredientCode(argument.getTarifNumber());
		tempDI.setIbIngredientQuantity(argument.getWeight());
		//not used: tempDI.setIbIngredientLicenceSn();		
		return tempDI;
	}
	
	private TsDB getDB(ExportRefundItem argument) {
		if (argument == null) {
			return null;
		}
		if (Utils.isStringEmpty(argument.getPartA()) && Utils.isStringEmpty(argument.getPartB())) {
			return null;
		}
			
		TsDB tempDB	= new TsDB();
		tempDB.setIbsdCode(argument.getPartA());
		tempDB.setPercentage(argument.getPartB());
		return tempDB;
	}
	
    private TsDC getDC(String containerNr) {    
		if (Utils.isStringEmpty(containerNr)) {
			return null;
		}
		
		TsDC tempDC = new TsDC();		
		tempDC.setCntrNo(containerNr);		
		return tempDC;
	}
    
////////
	private TsAI getAI(String type, String code, String text) {  //NEU in KIDS
		if (Utils.isStringEmpty(type) || Utils.isStringEmpty(code)) {
			return null;
		}
				
		TsAI tempAI = new TsAI(type);
		tempAI.setCode(code);  
		tempAI.setText(text);  	
		return tempAI;			
	}
	
	private TsDO getDO(String type, Document document) {
		if (document == null) { return null; }
			
		TsDO tempDO = new TsDO(type);
		
		tempDO.setCode(document.getTypeKids());
		tempDO.setReference(document.getReference()); 
		if (document.getQualifier() != null && document.getQualifier().length() < 3) {
			tempDO.setStatus(document.getQualifier()); 
		}
		tempDO.setReason(document.getAdditionalInformation()); 
		//not used: tempDO.setLanguage();
		tempDO.setPart(document.getDetail());
		//TODO was ist richtig???
		//tempDO.setQuantity(document.getValue());  
		// oder:
		if (document.getWriteDownAmount() != null) {
			tempDO.setQuantity(document.getWriteDownAmount().getWriteoffValue() );   			
	    }
		// oder:
	    //if (document.getWriteDownQuantity() != null) {
	    //	tempDO.setQuantity(document.getWriteDownQuantity().getWriteoffValue());
	    //}
		return tempDO;
	}
	private TsNA getAddress(String type, Party party) {
		if (Utils.isStringEmpty(type) || party == null) {
			return null;
		}
		
		TsNA tempNA = new TsNA();
		
		tempNA.setType(type);		
		
		if (party.getPartyTIN() != null) {
			tempNA.setTidEori(party.getPartyTIN().getTIN());			
			tempNA.setShortname(party.getPartyTIN().getCustomerIdentifier());  //EI20111205
		}	
		if (party.getAddress() != null) {
			tempNA.setLongname(party.getAddress().getName());
			tempNA.setStreet(party.getAddress().getStreet());
			if (!Utils.isStringEmpty(party.getAddress().getHouseNumber())) {  //EI20120228
				String str = tempNA.getStreet();
				if (!Utils.isStringEmpty(str)) {
					str = str + " " + party.getAddress().getHouseNumber();
					tempNA.setStreet(str);
				}
			}
			tempNA.setCity(party.getAddress().getCity());			
			if (Utils.isStringEmpty(party.getAddress().getPostalCode())) {
				tempNA.setPostcode("NA");    //EI20120620
			} else {
				tempNA.setPostcode(party.getAddress().getPostalCode());
			}
			tempNA.setCntry(party.getAddress().getCountry());
		}
		return tempNA;
	}
	private TsTD getTD() {  

		TsTD tempTD = new TsTD();		   
		tempTD.setTransmitter(kidsHeader.getTransmitter());
		tempTD.setReceiver(kidsHeader.getReceiver());
		tempTD.setMessageId(kidsHeader.getMessageID());
		
		return tempTD;
	}	
}
