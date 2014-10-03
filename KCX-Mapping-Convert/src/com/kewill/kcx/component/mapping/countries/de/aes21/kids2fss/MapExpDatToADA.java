package com.kewill.kcx.component.mapping.countries.de.aes21.kids2fss;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.SealNumber;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Business;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Completion;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CompletionItem;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.DocumentV20;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.IncoTerms;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.MeansOfIdentification;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Product;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Reentry;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Statistic;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.WriteDownAmount;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.WriteDownAmountV20;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpDat;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpDatPos;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.common.PreviousDocumentV21;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V70.AdaAdo;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V70.MsgADA;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V70.MsgADAPos;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsABF;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsACN;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsACO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAED;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAVS;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAZV;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsBAV;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsBZL;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsAEZ;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsAND;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsANM;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsAPO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsAPV;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsAWE;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsDAT;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsEAM;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsEDA;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsEPO;
import com.kewill.kcx.component.mapping.formats.fss.base.subsets.TsSAP;
import com.kewill.kcx.component.mapping.formats.fss.base.subsets.TsSAS;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module    	: Export/aes 21. 
 * Created    	: 24.07.2012
 * Description  : Mapping of V21 KidsMessage ExportDeclaration to ZABIS FSS-Format
 * 				: ZABIS FSS-Format Version 7.0.
 * 				: EI20130827: PreviousDocumentV20 ersetzt mit PreviousDocumentV21 
 * 
 * @author      : iwaniuk
 * @version     : 2.1.00
 */

public class MapExpDatToADA extends KidsMessage {

	private MsgExpDat msgExpDat;
	private MsgADA msgADA;
	
	private String transmitter = "";  //EI20120206: wird nur fuer KFF ausgewertet
	private String subversion = "";   //EI20130426
	private boolean containerInKopf = false;
	private boolean containerInPositionen = false;
	private boolean isBDP = false;   //EI20130701

	public MapExpDatToADA(XMLEventReader parser, TsVOR tsvor) throws XMLStreamException {
		msgExpDat = new MsgExpDat(parser);		
		msgADA = new MsgADA("");
		msgADA.setVorSubset(tsvor);
	}

	public MapExpDatToADA(XMLEventReader parser, TsVOR tsvor, TsHead head) throws XMLStreamException {
		msgExpDat = new MsgExpDat(parser);		
		msgADA = new MsgADA("");
		msgADA.setVorSubset(tsvor);
		msgADA.setHeadSubset(head);
	}
	
	public String getMessage() {

		String res = "";				
		String referenceNumber = null;
		
		if (this.getKidsHeader() != null) {                   
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
			if (!Utils.isStringEmpty(this.getKidsHeader().getRelease())) {   //EI20130422
				subversion = Utils.removeDots(this.getKidsHeader().getRelease());
				if (this.getKidsHeader().getReceiver() != null && this.getKidsHeader().getReceiver().startsWith("DE.BDP")) {
					isBDP = true; 
				}
				if (this.getKidsHeader().getReceiver() != null && this.getKidsHeader().getReceiver().equals("DE.KCX.TST")) {
					isBDP = true;
				}  //EI20131219: nur zur Testzwecken für BDP
			}
		}
		Utils.log("KidsToFSS transmitter = " + transmitter);		

		try {			
			msgExpDat.parse(HeaderType.KIDS);	
			referenceNumber = msgExpDat.getReferenceNumber();
            getCommonFieldsDTO().setReferenceNumber(msgExpDat.getReferenceNumber());

			msgADA.getVorSubset().setMsgid(getKidsHeader().getMessageID());			
           	
			msgADA.setDatSubset(mapKidsToDat());
			msgADA.setEdaSubset(mapKidsToEda());
			msgADA.setSasSubset(mapKidsToSas());

			if (msgExpDat.getTransportationRoute() != null) {				
				if (msgExpDat.getTransportationRoute().getCountryList() != null) {
					for (String country : msgExpDat.getTransportationRoute().getCountryList()) {
						msgADA.addAbfList(mapKidsToAbf(country));
					}
				}
			}
			
			//EI20130618: pruefung eingebaut, 		
			if (!isBDP) {   //EI20130701: aber eben	sollte nur für BDP sein
				msgADA.setConsignor(msgExpDat.getConsignor(), referenceNumber, "0");  // Typ 1			
			} else {  //und andersrum,weil mit BDP so (eigentlich richtg) vereinbart wurde
				if (Utils.isStringEmpty(msgExpDat.getDeclarantIsConsignor()) ||     
						msgExpDat.getDeclarantIsConsignor().equals("0")) {									
					msgADA.setConsignor(msgExpDat.getConsignor(), referenceNumber, "0");  
					msgExpDat.setDeclarantIsConsignor("1");
				} else {
					msgExpDat.setDeclarantIsConsignor("0");
				}
			} 
			msgADA.setConsignee(msgExpDat.getConsignee(), referenceNumber, "0");  //Typ 2
			msgADA.setDeclarant(msgExpDat.getDeclarant(), referenceNumber, "0");  // Typ 3
			msgADA.setAgent(msgExpDat.getAgent(), referenceNumber, "0");		  // Typ 4			
			msgADA.setSubcontractor(msgExpDat.getSubcontractor(), referenceNumber, "0"); // Typ 5
			msgADA.setFinalUser(msgExpDat.getFinalRecipient(), referenceNumber, "0");	  // Typ 6
			
			msgADA.setEamSubset(this.mapKidsToEam());
			msgADA.setApvSubset(this.mapKidsToApv());
			
			if (msgExpDat.getOutwardProcessing() != null) {	
				if (msgExpDat.getOutwardProcessing().getReentryList() != null) {											
					for (Reentry reentry : msgExpDat.getOutwardProcessing().getReentryList()) {
						msgADA.addAweList(this.mapKidsToAwe(reentry));
					}				
				}				
				if (msgExpDat.getOutwardProcessing().getMeansOfIdentificationList() != null) {							
					for (MeansOfIdentification moi : msgExpDat.getOutwardProcessing().getMeansOfIdentificationList()) {
						msgADA.addAnmList(this.mapKidsToAnm(moi));
					}				
				}				
				if (msgExpDat.getOutwardProcessing().getProductList() != null) {									
					for (Product prod : msgExpDat.getOutwardProcessing().getProductList()) {
						msgADA.addAezList(this.mapKidsToAez(prod));
					}				
				}
			}
			msgADA.setAndSubset(this.mapKidsToAnd());			
			if (msgExpDat.getSeal() != null) {							
				if (msgExpDat.getSeal().getSealNumbersList() != null) {
					for (SealNumber snr : msgExpDat.getSeal().getSealNumbersList()) {
						msgADA.addAvsList(this.mapKidsToAvs(snr));
					}
				}
			}	
			/* EI20120919: jetzt AdaAdo == Dokomente new for V21
			if (msgExpDat.getDocumentList() != null)	{
				for (DocumentV20 document : msgExpDat.getDocumentList()) {					
					msgADA.addAedList(mapKidsToAed("0", document));
				}				
			}
            */
			containerInKopf = this.checkContainerInKopf();
			containerInPositionen = false;	
			
			if (msgExpDat.getGoodsItemList() != null) {										
				for (MsgExpDatPos item : msgExpDat.getGoodsItemList()) {												
					this.mapGoodsItemtoADAPos(item);
				}
			}	
			
			if (containerInKopf && !this.containerInPositionen && msgADA.getPosList() != null) {  
				for (MsgADAPos pos : msgADA.getPosList()) {
					if (pos != null && pos.getApoSubset() != null) {
						String posnr = pos.getApoSubset().getPosnr();
						for (String number : msgExpDat.getContainer().getNumberList()) {
							if (!Utils.isStringEmpty(number)) {								
								pos.addAcnList(this.mapKidsToAcn(posnr, number));									
							}								
						}
					}
				}
			}
			//AdaAdo == Dokomente im Kopf: new for V21	 TODO-V21 noch keine Zuordnung 	in xls	
			if (msgExpDat.getDocumentList() != null)	{
				for (DocumentV20 document : msgExpDat.getDocumentList()) {					
					//msgADA.addAedList(mapKidsToAed("0", document));
					msgADA.addAdoList(this.mapKidsToAdoList(document));	
				}				
			}
								
			//res = msgADA.getFssString();
			/* EI20140206
			if (this.writeHead()) { 				//EI20130213
				res = msgADA.getFssString("HEAD");
	        } else {
	            res = msgADA.getFssString();
	        } 
			*/
			res = msgADA.getFssString("HEAD"); //EI20140206
			
			//EI20130404: vergleich meiner dateien iwa-utf, iwa-iso mit einer fss die im KCX-test 
			//             generiert worden ist, hat ergeben, dass diese fss identisch mit iwa-iso ist 
			// input-datei:  c:/temp/KCY-UftIso/KidsExpdat-DE.xm
			//
			//this.writeTestUtfIso(res, "utf");  //EI20130404
	        //this.writeTestUtfIso(res, "iso");  //EI20130404

		} catch (FssException e) {
			e.printStackTrace();
		}

		return res;
	}
	

	private TsDAT mapKidsToDat() {
		TsDAT tmpDat = new TsDAT();
				
		tmpDat.setBeznr(this.msgExpDat.getReferenceNumber());
		tmpDat.setKuatnr(msgExpDat.getOrderNumber());
		tmpDat.setMrn(msgExpDat.getUCRNumber());
		tmpDat.setKzart("1");			
		tmpDat.setExpdst(msgExpDat.getCustomsOfficeExport());
		tmpDat.setEamdst(msgExpDat.getCustomsOfficeForCompletion());	
		tmpDat.setQuelkz(msgExpDat.getPreviousProcedure());
		if (msgExpDat.getContact() != null) {
		    tmpDat.setSb(msgExpDat.getContact().getIdentity());	
		}
		tmpDat.setZlbez("");   //immer noch nicht zugeordnet/keine Tags in KIDS
		tmpDat.setAvbez("");   //""
		tmpDat.setEmebez("");  //""

		return tmpDat;
	}

	private TsEDA mapKidsToEda() {
		TsEDA tmpEda = new TsEDA();
	
		tmpEda.setBeznr(this.msgExpDat.getReferenceNumber());
		tmpEda.setBewnr(msgExpDat.getAuthorizationNumber());	
		//if (msgExpDat.getAuthorizationTrustedExporter() != null && !msgExpDat.getAuthorizationTrustedExporter().equals("0")) {  //EI20120910
			tmpEda.setBewva(msgExpDat.getAuthorizationTrustedExporter());   //new for v21		
		//}
		if (msgExpDat.getPlaceOfLoading() != null) {
			tmpEda.setLdocde(msgExpDat.getPlaceOfLoading().getCode());				
			tmpEda.setBeostr(msgExpDat.getPlaceOfLoading().getStreet());
			tmpEda.setBeoort(msgExpDat.getPlaceOfLoading().getCity());
			tmpEda.setBeoplz(msgExpDat.getPlaceOfLoading().getPostalCode());
			tmpEda.setBeozus(msgExpDat.getPlaceOfLoading().getAgreedLocationOfGoods());
		}		
		tmpEda.setArtaus(msgExpDat.getAreaCode());    
		tmpEda.setArtvfr(msgExpDat.getProcedure());                    //new for v21
		tmpEda.setArtueb(msgExpDat.getTypeOfPermit()); 
		if (msgExpDat.getTransportMeansDeparture() != null) {		   //new for v21
			tmpEda.setBfarta(msgExpDat.getTransportMeansDeparture().getTransportationType());
			tmpEda.setBfkza(msgExpDat.getTransportMeansDeparture().getTransportationNumber());
			tmpEda.setBfnata(msgExpDat.getTransportMeansDeparture().getTransportationCountry());
		}
		tmpEda.setLdve(msgExpDat.getDispatchCountry());		
		tmpEda.setLdbe(msgExpDat.getDestinationCountry());	           
		tmpEda.setConkz(msgExpDat.getTransportInContainer());
		tmpEda.setFregnr(msgExpDat.getUCROtherSystem());
		
		tmpEda.setVerm(msgExpDat.getAnnotation());
		tmpEda.setKzanau(msgExpDat.getDeclarantIsConsignor());			
		//doppelt: tmpEda.setLdbe(msgExpDat.getDestinationCountry());
		//tmpEda.setFlhcde(msgExpDat);                               //no Tag in KIDS
		tmpEda.setExtdst(msgExpDat.getIntendedOfficeOfExit());
		//doppelt: tmpEda.setKzanau(msgExpDat.getDeclarantIsConsignor());
		//EI20120919: only ADI: tmpEda.setInddat(msgExpDat.getAdvanceNoticeTime());          
		tmpEda.setAnmdat(msgExpDat.getDeclarationTime());		
		if (msgExpDat.getLoadingTime() != null) {
			tmpEda.setGststr(msgExpDat.getLoadingTime().getBeginTime());
			tmpEda.setGstend(msgExpDat.getLoadingTime().getEndTime());
		}
		//doppelt: tmpEda.setVerm(msgExpDat.getAnnotation());		
		if (msgExpDat.getSeal() != null) {
			tmpEda.setKztyd(msgExpDat.getSeal().getUseOfTydenseals());
			tmpEda.setKzstock(msgExpDat.getSeal().getUseOfTydensealStock());
			tmpEda.setAnzve(msgExpDat.getSeal().getNumber());
			tmpEda.setVsart(msgExpDat.getSeal().getKind());
		}
		tmpEda.setGsroh(Utils.addZabisDecimalPlaceV7(msgExpDat.getGrossMass(), 3));		
		if (subversion.equals("2101")) {     //EI20130422
			tmpEda.setGsroh(msgExpDat.getGrossMass());
		}
		
		return tmpEda;
	}

	private TsSAS mapKidsToSas() {
		
		TsSAS tmpSAS = new TsSAS();
		
		tmpSAS.setBeznr(this.msgExpDat.getReferenceNumber());
		tmpSAS.setBesust(msgExpDat.getSituationCode());
		tmpSAS.setBfgkzw(msgExpDat.getPaymentType());
		tmpSAS.setKnrsdg(msgExpDat.getShipmentNumber());
		
		return tmpSAS;
	}
	
	private TsABF mapKidsToAbf(String country) {
		if (Utils.isStringEmpty(country)) {
			return null;
		}
		
		TsABF tempABF = new TsABF();

		tempABF.setBeznr(msgExpDat.getReferenceNumber());
		tempABF.setLdbf(country);
		return tempABF;
	}
	
	private TsEAM mapKidsToEam() {			
		
		TransportMeans meansBorder = msgExpDat.getTransportMeansBorder();
		TransportMeans meansInland = msgExpDat.getTransportMeansInland();
		Business business = msgExpDat.getBusiness();
		IncoTerms incoTerms = msgExpDat.getIncoTerms();

		if (meansBorder == null && meansInland == null && business == null && incoTerms == null) {
			return null;
		}		
		TsEAM tmpEam = new TsEAM();
		
		tmpEam.setBeznr(msgExpDat.getReferenceNumber());
		
		// CK20120828
		// eamdat nur in Nachricht EAM nicht in ADA ausgewertet lt. Doku
		// OfficeOf... ist sowieso falsch
		// tmpEam.setEamdat(msgExpDat.getCustomsOfficeForCompletion());      //new for v21		
		if (meansInland != null) {
			tmpEam.setBfvkzi(meansInland.getTransportMode());					
		}
		if (meansBorder != null) {
			tmpEam.setBfvkzg(meansBorder.getTransportMode());
			tmpEam.setBfartg(meansBorder.getTransportationType());
			tmpEam.setBfkzg(meansBorder.getTransportationNumber());
			tmpEam.setBfnatg(meansBorder.getTransportationCountry());
		}
		if (business != null) {
			tmpEam.setGesart(business.getBusinessTypeCode());			
			tmpEam.setGesprs(Utils.addZabisDecimalPlaceV7(business.getInvoicePrice(), 2));
			if (subversion.equals("2101")) {     //EI20130422
				tmpEam.setGesprs(business.getInvoicePrice());
			}
			tmpEam.setGeswrg(business.getCurrency());
		}
		if (incoTerms != null) {
			tmpEam.setLibart(incoTerms.getIncoTerm());
			tmpEam.setLibinc(incoTerms.getText());
			tmpEam.setLibort(incoTerms.getPlace());
		}
		tmpEam.setAdrkon(msgExpDat.getAddressCombination());      //new for v21	
		
		return tmpEam;
	}
	
	private TsAPV mapKidsToApv() {     							//new for v21			
		if (msgExpDat.getOutwardProcessing() == null) {
			return null;
		}
		TsAPV tmpApv = new TsAPV();
		
		tmpApv.setBeznr(msgExpDat.getReferenceNumber());
		tmpApv.setBewpv(msgExpDat.getOutwardProcessing().getAuthorizationNumber());
		tmpApv.setBewa7(msgExpDat.getOutwardProcessing().getAuthorizationLocalClearenceProcedure());
		tmpApv.setEindat(msgExpDat.getOutwardProcessing().getDateOfReExport());
		tmpApv.setKzstau(msgExpDat.getOutwardProcessing().getStandardExchange());
	  
		return tmpApv;
	}
	
	private TsAWE mapKidsToAwe(Reentry reentry) {    //new for v21	
		if (reentry == null) {
			return null;
		}
		if (Utils.isStringEmpty(reentry.getCountry())) {
			return null;
		}
		TsAWE tmpAwe = new TsAWE();
		tmpAwe.setBeznr(msgExpDat.getReferenceNumber());
		tmpAwe.setLdwe(reentry.getCountry());		
		
		return tmpAwe;
	}
	
	private TsANM mapKidsToAnm(MeansOfIdentification moi) {    //new for v21	
		if (moi == null) {
			return null;
		}
		TsANM tmpAnm = new TsANM();
		
		tmpAnm.setBeznr(msgExpDat.getReferenceNumber());
		tmpAnm.setArtnkm(moi.getType());
		tmpAnm.setText(moi.getText());
		
		return tmpAnm;
	}
	
	private TsAEZ mapKidsToAez(Product prod) {              //new for v21	
		if (prod == null) {
			return null;
		}
		TsAEZ tmpAez = new TsAEZ();
		
		tmpAez.setBeznr(msgExpDat.getReferenceNumber());
		tmpAez.setTrnr(prod.getTarifCode());
		tmpAez.setText(prod.getDescription());
		
		return tmpAez;
	}
	
	private TsAND mapKidsToAnd() {             //new for v21	
		TsAND tmpAnd = new TsAND();
		
		tmpAnd.setBeznr(msgExpDat.getReferenceNumber());
		//tmpAnd.setFlhtex();
		tmpAnd.setTexdst(msgExpDat.getRealOfficeOfExit());
		tmpAnd.setAusdat(msgExpDat.getDateOfExit());
		tmpAnd.setMzpdat(msgExpDat.getRelevantDate());
		tmpAnd.setIntra(msgExpDat.getFlagOfStatistic());
			
		return tmpAnd;
	}
	private TsAVS mapKidsToAvs(SealNumber sealNumber) {		
		if (sealNumber == null) {
			return null;
		}
		if (sealNumber.isEmpty()) {
			return null;
		}
		
		TsAVS tmpAvs = new TsAVS();
		
		tmpAvs.setBeznr(this.msgExpDat.getReferenceNumber());
		tmpAvs.setSeal(sealNumber.getNumber());

		return tmpAvs;
	}
	
	private TsAED mapKidsToAed(String posNr, DocumentV20 document) {		
		if (document == null) {
			return null;
		}
		
		TsAED tempAED = new TsAED();			
				
		tempAED.setBeznr(this.msgExpDat.getReferenceNumber());
		tempAED.setPosnr(posNr);		
		
		if (document != null) {						
			tempAED.setUntart(document.getType());
			tempAED.setUntqar(document.getQualifier());
			tempAED.setUntnr(document.getReference());
			tempAED.setUntzus(document.getAdditionalInformation());
			tempAED.setDetail(document.getDetail());
			tempAED.setUntdat(document.getIssueDate());
			tempAED.setGbdat(document.getExpirationDate());
			tempAED.setWert(document.getValue());       
			
			WriteDownAmountV20 writeDownAmount = document.getWriteDownAmount(); 
			if (writeDownAmount != null) {				
				tempAED.setMgeme(writeDownAmount.getUnitOfMeasurement());
				// CK120824
				tempAED.setAbgwrt(writeDownAmount.getValue().replace('.', ','));   
				// keine virtuellen Kommas sondern ein echtes - siehe Zabis-Docs
				// tempAED.setAbgwrt(Utils.addZabisDecimalPlaceV7(writeDownAmount.getValue(), 3));

			}
		}
		return tempAED;
	}
	
	private void mapGoodsItemtoADAPos(MsgExpDatPos item) {
		if (item == null) {
			return;
		}
		MsgADAPos adaPos = new MsgADAPos();
		String itemNumber = item.getItemNumber();
		String referenceNumber = msgExpDat.getReferenceNumber();
		
		adaPos.setApoSubset(mapKidsToApo(item));
		adaPos.setEpoSubset(mapKidsToEpo(itemNumber, item.getStatistic()));
		adaPos.setSapSubset(mapKidsToSap(item));
        adaPos.setConsignee(item.getConsignee(), referenceNumber, itemNumber); 
        adaPos.setFinalUser(item.getFinalRecipient(), referenceNumber, itemNumber); 
        //nur consignee und finaluser?? TODO-V21      
		if (item.getPackagesList() != null) {			
			for (Packages pack : item.getPackagesList()) {			    
				adaPos.addAcoList(mapKidsToAco(itemNumber, pack));				
			}
		}
		if (item.getContainer() != null) {			
			if (item.getContainer().getNumberList() != null) {
				for (String number : item.getContainer().getNumberList()) {
					if (!Utils.isStringEmpty(number)) {
						adaPos.addAcnList(mapKidsToAcn(itemNumber, number));
						containerInPositionen = true;
					}	
				}
			}
		}
		if (item.getPreviousDocumentList() != null) {			
			//for (PreviousDocumentV20 prev : item.getPreviousDocumentList()) {
			for (PreviousDocumentV21 prev : item.getPreviousDocumentList()) {	
				adaPos.addAzvList(mapKidsToAzv(itemNumber, prev));
			}
		}
		 adaPos.setBzlList(mapKidsToBzlList(itemNumber, item.getBondedWarehouseCompletion()));
	        adaPos.setBavList(mapKidsToBavList(itemNumber, item.getInwardProcessingCompletion()));
		if (item.getDocumentList() != null)  {			
			for (DocumentV20 doc : item.getDocumentList()) {
				adaPos.addAedList(mapKidsToAed(itemNumber, doc));
			}
		}		
       		
		msgADA.addPosList(adaPos);
	}	
		
	private TsAPO mapKidsToApo(MsgExpDatPos item) {
		if (item == null) {
			return null;
		}		
		TsAPO tmpApo = new TsAPO();
		
		Party                   consignee = item.getConsignee();	
		
		
		String 	annotation = item.getAnnotation();	
		String 	annotation2 = item.getAnnotation2();	
		if (!(Utils.isStringEmpty(annotation2))) {
			if (Utils.isStringEmpty(annotation)) {
				annotation = annotation2;
			} else {
				annotation = annotation + " " + annotation2;
			}
		}
	
		tmpApo.setBeznr(msgExpDat.getReferenceNumber());
		tmpApo.setPosnr(item.getItemNumber());
		tmpApo.setOripos(item.getItemNumber());
		tmpApo.setArtnr(item.getArticleNumber());		
		if (item.getCommodityCode() != null) {
			tmpApo.setTnr(item.getCommodityCode().getTarifCode());
			tmpApo.setTnrtrc(item.getCommodityCode().getEUTarifCode());
			tmpApo.setTnrzu1(item.getCommodityCode().getTarifAddition1());
			tmpApo.setTnrzu2(item.getCommodityCode().getTarifAddition2());
			tmpApo.setTnrnat(item.getCommodityCode().getAddition());
		}
		tmpApo.setWbsch(item.getDescription());
		tmpApo.setFregnr(item.getUCROtherSystem());
		tmpApo.setVerm(annotation);		
		tmpApo.setEigmas(Utils.addZabisDecimalPlaceV7(item.getNetMass(), 3));		
		tmpApo.setRohmas(Utils.addZabisDecimalPlaceV7(item.getGrossMass(), 3));	
		if (subversion.equals("2101")) {     //EI20130422
			tmpApo.setEigmas(item.getNetMass());		
			tmpApo.setRohmas(item.getGrossMass());	
		}
		if (item.getApprovedTreatment() != null) {
			tmpApo.setAnmvrf(item.getApprovedTreatment().getDeclared());
			tmpApo.setPrevrf(item.getApprovedTreatment().getPrevious());
			tmpApo.setNatvrf(item.getApprovedTreatment().getNational());
		}
		// CK20120828 asvfr ergänzt da es fehlte
		if (item.getApprovedTreatment() != null) {
			tmpApo.setAsvfr(item.getApprovedTreatment().getCodeForRefund());	
		}
		tmpApo.setUbland(item.getOriginFederalState());
		if (item.getStatistic() != null) {
			//tmpApo.setWmahst(statistic.getAdditionalUnit());
			tmpApo.setWmahst(Utils.addZabisDecimalPlaceV7(item.getStatistic().getAdditionalUnit(), 3));
			if (subversion.equals("2101")) {     //EI20130422
				tmpApo.setWmahst(item.getStatistic().getAdditionalUnit());
			}
			tmpApo.setAhwert(item.getStatistic().getStatisticalValue());
			tmpApo.setAhrico(item.getStatistic().getStatisticalValueSendFlag());	//EI20130808: fuer ASE22
		}
		tmpApo.setAdrkon(item.getAddressCombination());   //new for V21
		if (item.getBusiness() != null) {
			tmpApo.setGesart(item.getBusiness().getBusinessTypeCode());  //new for V21
		}
		if (item.getIncoTerms() != null) {								//new for V21
			tmpApo.setLibart(item.getIncoTerms().getIncoTerm());  
			tmpApo.setLibinc(item.getIncoTerms().getText());
			tmpApo.setLibort(item.getIncoTerms().getPlace());
		}		
		if (item.getInwardProcessingCompletion() != null) { 		
			tmpApo.setAzvbew(item.getInwardProcessingCompletion().getAuthorizationNumber());
			tmpApo.setZlbez(item.getInwardProcessingCompletion().getReferenceNumber());
			
		} else if (item.getBondedWarehouseCompletion() != null) { // CK120905 Beendigung ZL auch abfragen		
			tmpApo.setAzvbew(item.getBondedWarehouseCompletion().getAuthorizationNumber());
			tmpApo.setZlbez(item.getBondedWarehouseCompletion().getReferenceNumber()); 
		}
		
		if (this.transmitter.equals("KFF")) { //EI20120207: JIRA-Ticket KCX-94 (siehe calculateNetMass(...))
			//in tmpAPO muessten die Gewichte ohne Punkte und ohne Komma sein,
			//hier sind die gewichte bereits richtig formatiert:
			tmpApo.setEigmas(calculateNetMass(tmpApo.getRohmas(), tmpApo.getEigmas()));
			//calculateNetMass liefert einen auf integer gerundeten ergebnis == richtig fuer Zabis berechnet und formatiert
		}		
		
		return tmpApo;		
	}

	private TsEPO mapKidsToEpo(String posNr, Statistic statistic) {				
		if (statistic == null) {
			return null;		
		}

		if (Utils.isStringEmpty(statistic.getValue()) && Utils.isStringEmpty(statistic.getCurrency())) {
			return null;
		}
		
		TsEPO tmpEpo = new TsEPO();

		tmpEpo.setBeznr(msgExpDat.getReferenceNumber());
		tmpEpo.setPosnr(posNr);
		tmpEpo.setBasbtg(Utils.addZabisDecimalPlaceV7(statistic.getValue(), 2));
		if (subversion.equals("2101")) {     //EI20130422
			tmpEpo.setBasbtg(statistic.getValue());
		}
		tmpEpo.setBaswrg(statistic.getCurrency());
		//tmpEpo.setKziata();  //new for V21 noch kein Tag in Kids

		return tmpEpo;
	}
	
	private TsSAP mapKidsToSap(MsgExpDatPos item) {
		if (item == null) {
			return null;
		}
		
		TsSAP tmpSAP = new TsSAP();			
		
		tmpSAP.setBeznr(this.msgExpDat.getReferenceNumber());
		tmpSAP.setPosnr(item.getItemNumber());
		tmpSAP.setUndgnr(item.getDangerousGoodsNumber());
		tmpSAP.setBfgkzw(item.getPaymentType());
		tmpSAP.setKnrsdg(item.getShipmentNumber());
		
		return tmpSAP;
	}
	
	private TsACO mapKidsToAco(String posNr, Packages packages) {		
		if (packages == null) {
			return null;
		}
		
		TsACO tmpACO = new TsACO();
		
		tmpACO.setBeznr(this.msgExpDat.getReferenceNumber());
		tmpACO.setPosnr(posNr);
		tmpACO.setColanz(packages.getQuantity());
		tmpACO.setLfdnr(packages.getSequentialNumber());
		if (Utils.isStringEmpty(packages.getSequentialNumber())) {
			tmpACO.setLfdnr("1");    //EI20130614  fuer BDP:. sie haben immer nur ein Package pro Item
		}
		tmpACO.setColart(packages.getType());
		tmpACO.setColzch(packages.getMarks());

		return tmpACO;
	}	
	
	private TsACN mapKidsToAcn(String posNr, String containerNr) {		
		if (Utils.isStringEmpty(containerNr)) {
			return null;
		}		
		TsACN tsACN = new TsACN();
		
		tsACN.setBeznr(msgExpDat.getReferenceNumber());
		tsACN.setPosnr(posNr);
		tsACN.setConnr(containerNr);

		return tsACN;
	}
	
	//private TsAZV mapKidsToAzv(String posNr, PreviousDocumentV20 prevDoc) {	
	private TsAZV mapKidsToAzv(String posNr, PreviousDocumentV21 prevDoc) {	
		if (prevDoc == null) {
			return null;		
		}

		TsAZV tsAZV = new TsAZV();
		
		tsAZV.setBeznr(this.msgExpDat.getReferenceNumber());
		tsAZV.setPosnr(posNr);
		tsAZV.setVptyp(prevDoc.getType());		
		tsAZV.setAzvref(prevDoc.getMarks());            
		if (Utils.isStringEmpty(prevDoc.getMarks())) {   //EI20130827
			tsAZV.setAzvref(prevDoc.getReference());
		}
		tsAZV.setAzvzus(prevDoc.getAdditionalInformation());

		return tsAZV;
	}
		
	private List<TsBZL> mapKidsToBzlList(String itemNumber, Completion bwCompletion) {
		if (bwCompletion == null) {
			return null;
		}		
		List <TsBZL>bzlList = null;		
		if (bwCompletion.getCompletionItemList()  != null)  {
			bzlList = new Vector<TsBZL>();
			for (CompletionItem completionItem : bwCompletion.getCompletionItemList()) {
				if (completionItem != null) {
					bzlList.add(setBzlItem(completionItem, itemNumber));
				}
			}
		}
		
		return bzlList;
	}
	private TsBZL setBzlItem(CompletionItem completionItem, String itemNumber) {
		if (completionItem == null) {
			return null;
		}
		
		TsBZL tempBZL = new TsBZL();
		
		WriteDownAmount writeDownAmount = completionItem.getWriteDownAmount();
		WriteDownAmount tradeAmount = completionItem.getTradeAmount();
		
			tempBZL.setBeznr(this.msgExpDat.getReferenceNumber());
			tempBZL.setPosnr(itemNumber);			
			tempBZL.setAtlas(completionItem.getEntryInAtlas());
			tempBZL.setKzuebl(completionItem.getUsualFormOfHandling());
			tempBZL.setTxzus(completionItem.getText());
			tempBZL.setVposnr(completionItem.getPositionNumber());
			tempBZL.setVregnr(completionItem.getUCR());
			tempBZL.setWanr(completionItem.getTarifNumber());
			
			if (writeDownAmount != null) {
				tempBZL.setMeabg(writeDownAmount.getUnitOfMeasurement());
				//EI20090818:tempBZL.setMgeabg(writeDownAmount.getValueKids());
				
				// CK120824
				// tempBZL.setMgeabg(writeDownAmount.getWriteoffValue());  //EI20090818				
				tempBZL.setMgeabg(Utils.addZabisDecimalPlaceV7(writeDownAmount.getWriteoffValue(), 3));
				if (subversion.equals("2101")) {     //EI20130422
					tempBZL.setMgeabg(writeDownAmount.getWriteoffValue());
				}
				tempBZL.setQmeabg(writeDownAmount.getQualifier());
			}					
			if (tradeAmount != null) {
				tempBZL.setMehdl(tradeAmount.getUnitOfMeasurement());
				//EI20090818: tempBZL.setMgehdl(tradeAmount.getValueKids());
				
				// CK120824
				// tempBZL.setMgehdl(tradeAmount.getWriteoffValue());  //EI20090818
				tempBZL.setMgehdl(Utils.addZabisDecimalPlaceV7(tradeAmount.getWriteoffValue(), 3));
				if (subversion.equals("2101")) {     //EI20130422
					tempBZL.setMgehdl(tradeAmount.getWriteoffValue());
				}
				tempBZL.setQmehdl(tradeAmount.getQualifier());
			}
		
		return tempBZL;
	}
	
	private List<TsBAV> mapKidsToBavList(String itemNumber, Completion ipCompletion) {
		if (ipCompletion == null) {
			return null;			
		}	
		List <TsBAV>bavList = null;	
		if (ipCompletion.getCompletionItemList()  != null) {
			bavList = new Vector<TsBAV>();
			for (CompletionItem completionItem : ipCompletion.getCompletionItemList()) {
				if (completionItem != null) {
					bavList.add(setBav(completionItem, itemNumber));
				}
			}
		}
		
		return bavList;
	}	
	private TsBAV setBav(CompletionItem completionItem, String itemNumber) {
		if (completionItem == null) {
			return null;
		}
		
		TsBAV tempBAV = new TsBAV();

		tempBAV.setBeznr(this.msgExpDat.getReferenceNumber());
		tempBAV.setPosnr(itemNumber);			
		tempBAV.setAtlas(completionItem.getEntryInAtlas());
		tempBAV.setTxzus(completionItem.getText());
		tempBAV.setVposnr(completionItem.getPositionNumber());
		tempBAV.setVregnr(completionItem.getUCR());
	
		return tempBAV;
	}	
	
	private boolean checkContainerInKopf() {
		boolean ret = false;
		if (msgExpDat != null && msgExpDat.getContainer() != null && !msgExpDat.getContainer().isEmpty()) {
			if (msgExpDat.getContainer().getNumberList() != null) {
				ret = true;
			}
		}
		return ret;
	}
	
	private AdaAdo mapKidsToAdoList(DocumentV20 document) {        //TODI-V21 noch keine Zuordnung Kids2fss
		if (document == null) {
			return null;
		}
		AdaAdo adaado = null;
		/*  TODO-V21 
		adaado = new AdaAdo();
		/*  TODO-V21 
		TsADO ado = new TsADO();
		TsADR adr = new TsADR();
		private List<TsADB> adbList;   //Bemerkungen
		private List<TsADD> addList;   //Dokomentenposition	 
		*/
		return adaado;
	}
	
	private void writeTestUtfIso(String payload, String encode)  { //EI20130404
		String coding = "UTF-8";
		if (encode.equals("iso")) {
			coding = "ISO-8859-1";
		}
		File tmpFile = new File("c:/temp", "iwa-" + encode);
       
        Utils.log("(MuleUtils writeFile) Writing payload to " + tmpFile.getPath());
        Utils.log("(MuleUtils writeFile) coding " + coding);  
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(tmpFile);
            if (payload instanceof String) {
                OutputStreamWriter out = new OutputStreamWriter(fos, coding);
                out.write((String) payload);
                out.close();
            } else {               
                fos.close();
            }           
          
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 
	}
	
}
