/*
 * Function    : MapExpDatToADA.java
 * Date        : 02.10.2008
 * Author      : Kewill CSF / SH
 * Description : Mapping of KIDS format of ExportDeclaration to FSS format ADA

 * Changes 
 * -----------
 * Author      : EI
 * Date        : 28.04.2009
 * Label       : EI20090428
 * Description : corrected filling of EDA-Subset 
 *
 * Author      : EI
 * Date        : 05.05.2009
 * Label       : EI20090505
 * Description : changed setAdrSubset() in Pos to equals from Head 
 * 			   : removed argument "typ" from setConsignee(...) aso.
 * Author      : EI
 * Label       : EI20090609
 * Description : ContactPerson instead of clerk   
 */

package com.kewill.kcx.component.mapping.countries.de.aes.kids2fss.V60;

import java.math.BigDecimal;
import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.Seal;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpDat;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpDatPos;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ApprovedTreatment;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Business;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CommodityCode;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Completion;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CompletionItem;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ExportRefundHeader;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ExportRefundItem;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.IncoTerms;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Ingredients;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.LoadingTime;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PlaceOfLoading;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Route;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Statistic;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.WriteDownAmount;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60.MsgADA;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60.MsgADAPos;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsABF;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsACN;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsACO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAED;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAPO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsATI;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsATK;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsATP;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAVS;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAZV;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsBAV;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsBZL;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsDAT;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsEAM;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsEDA;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsEPO;
import com.kewill.kcx.component.mapping.formats.fss.base.subsets.TsSAP;
import com.kewill.kcx.component.mapping.formats.fss.base.subsets.TsSAS;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul    	: KidsToUids.java. 
 * Erstellt    	: 01.04.2009
 * Beshcreibung : Mapping of  Message ExportDeclaration from
 * 				: KIDS to ZABIS FSS-Format Version 6.0.
 * 
 * @author      : Kewill CSF / messer
 * @version     : 6.0.00
 */

public class MapExpDatToADA extends KidsMessage {

	private MsgExpDat msgExpDat;
	private MsgADA msgADA;
	private String transmitter = "";  //EI20120206: wird nur fuer KFF ausgewertet

	public MapExpDatToADA(XMLEventReader parser, TsVOR tsvor) throws XMLStreamException {
		msgExpDat = new MsgExpDat(parser);
		
		msgADA = new MsgADA();
		msgADA.setVorSubset(tsvor);
	}

	public String getMessage() {

		String res = "";
		//EI20120605: MsgExpDatPos msgExpDatPos = null;
		MsgADAPos msgADAPos = null;	
		String referenceNumber = null;
		
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
		Utils.log("KidsToFSS transmitter = " + transmitter);
		String itemNumber = "";		

		try {
			//msgADA = new MsgADA("data/fss/out/MapExpIndToADA.dat");

			msgExpDat.parse(HeaderType.KIDS);	
			referenceNumber = msgExpDat.getReferenceNumber();
            getCommonFieldsDTO().setReferenceNumber(msgExpDat.getReferenceNumber());

			// read MessageID from KidsHeader 
			msgADA.getVorSubset().setMsgid(getKidsHeader().getMessageID());			

			//füllen der übrigen ADA-Elementen:                	
			msgADA.setDatSubset(setDat());
			msgADA.setEdaSubset(setEda());
			msgADA.setSasSubset(setSas());

			Route  route = msgExpDat.getTransportationRoute();
			if (route != null) {
				List<String> list = route.getCountryList();
				if (list != null) {
					for (int i = 0, routeSize = list.size(); i < routeSize; i++) {
						msgADA.addAbfList(setAbf(list.get(i)));
					}
				}
			}
			msgADA.setAtkSubset(setAtk());

			msgADA.setConsignor(msgExpDat.getConsignor(), referenceNumber, "0");  //Consignor ist vom Typ 1
			msgADA.setConsignee(msgExpDat.getConsignee(), referenceNumber, "0");  //Consignee ist vom Typ 2
			msgADA.setDeclarant(msgExpDat.getDeclarant(), referenceNumber, "0");  //Declarant ist vom Typ 3
			msgADA.setAgent(msgExpDat.getAgent(), referenceNumber, "0");		// Agent ist vom Typ 4
			//Subcontractor ist vom Typ 5
			msgADA.setSubcontractor(msgExpDat.getSubcontractor(), referenceNumber, "0"); 
			
			msgADA.setEamSubset(setEam());

			if (msgExpDat.getSeal() != null) {			
				List<String> list = msgExpDat.getSeal().getSealNumberList();
				if (list != null) {
					for (int i = 0, listSizeSeal = list.size(); i < listSizeSeal; i++) {
						msgADA.addAvsList(setAvs(list.get(i)));
					}
				}
			}
			
			List <Document> documentList = msgExpDat.getDocumentList();
			if (documentList != null)	{
				for (int i = 0, listSize = documentList.size(); i < listSize; i++) {
					Document document = documentList.get(i);
					msgADA.addAedList(setAed(document, "0"));
				}
				documentList = null;
			}

			int listSizeItem = 0;
			boolean containerInKopf = this.checkContainerInKopf();
			boolean containerInPositionen = false;
			
			if (msgExpDat.getGoodsItemList() != null) {
			    listSizeItem = msgExpDat.getGoodsItemList().size();
			}									
			for (int i = 0; i < listSizeItem; i++) {
				//EI20120605: msgExpDatPos = msgExpDat.getGoodsItemList().get(i);
				MsgExpDatPos msgExpDatPos = msgExpDat.getGoodsItemList().get(i);    //EI20120605
				itemNumber = msgExpDatPos.getItemNumber();
				msgADAPos = new MsgADAPos();

				msgADAPos.setApoSubset(setApo(msgExpDatPos));
				msgADAPos.setEpoSubset(setEpo(msgExpDatPos.getStatistic(), itemNumber));
				msgADAPos.setSapSubset(setSap(msgExpDatPos));
//EI20090505:   msgADAPos.setAdrSubset(setAdr(msgExpDatPos.getConsignee(), "2", itemNumber));
                msgADAPos.setConsignee(msgExpDatPos.getConsignee(), referenceNumber, itemNumber);  //EI20090505
                msgADAPos.setAtpSubset(setAtp(msgExpDatPos.getApprovedTreatment(), 
                                              msgExpDatPos.getExportRefundItem(), itemNumber));
				
				if (msgExpDatPos.getPackagesList() != null) {
					List<Packages> list = msgExpDatPos.getPackagesList();
					if (list != null) {
					    for (int j = 0, listSizePackage = list.size(); j < listSizePackage; j++) {
							msgADAPos.addAcoList(setAco(list.get(j), itemNumber));
						}
					}
				}

				if (msgExpDatPos.getContainer() != null) {
					List<String> list = msgExpDatPos.getContainer().getNumberList();
					if (list != null) {
						for (int j = 0, listSizeNumber = list.size(); j < listSizeNumber; j++) {
							if (!Utils.isStringEmpty(list.get(j))) {
								msgADAPos.addAcnList(setAcn(list.get(j), itemNumber));
								containerInPositionen = true;
							}	
						}
					}
				}
				if (msgExpDatPos.getPreviousDocumentList() != null) {
					List<PreviousDocument> list = msgExpDatPos.getPreviousDocumentList();
					for (int j = 0, listSizePrev = list.size(); j < listSizePrev; j++) {
						msgADAPos.addAzvList(setAzv(list.get(j), itemNumber));
					}
				}
				
				if (msgExpDatPos.getDocumentList() != null)  {
    				List <Document> list = msgExpDatPos.getDocumentList();
    				for (int j = 0, listSize = list.size(); j < listSize; j++) {
    					msgADAPos.addAedList(setAed(list.get(j), itemNumber));
    				}
    			}
				
				//AK20090407-1
                msgADAPos.setBzlList(setBzlList(msgExpDatPos.getBondedWarehouseCompletion(), itemNumber));
                msgADAPos.setBavList(setBavList(msgExpDatPos.getInwardProcessingCompletion(), itemNumber));
 				
				msgADA.addPosList(msgADAPos);
			}
			
			if (msgExpDat.getContainer() != null && containerInKopf && !containerInPositionen) {  //EI20120605
				for (MsgADAPos pos : msgADA.getPosList()) {
					if (pos != null && pos.getApoSubset() != null) {
						String posnr = pos.getApoSubset().getPosnr();
						for (String number : msgExpDat.getContainer().getNumberList()) {
							if (!Utils.isStringEmpty(number)) {								
								pos.addAcnList(setAcn(number, posnr));									
							}								
						}
					}
				}
			}

			res = msgADA.getFssString();

			//Utils.log("(MapExpDatToADA getMessage) Msg = " + res);

		} catch (FssException e) {
			e.printStackTrace();
		}

		return res;
	}

	//AK20090407
	private TsATP setAtp(ApprovedTreatment appTreatment, ExportRefundItem refund, String posNr) {		
		if (refund == null && appTreatment == null) {
			return null;
		}
		
		TsATP atp = new TsATP();
		
		atp.setBeznr(this.msgExpDat.getReferenceNumber());
		atp.setPosnr(posNr);
		
		if (refund != null) {				
			//EI20090417: atp.setMeaest(refund.getUnitOfMeasurement());
			//atp.setAnwrta(refund.getPartA());
			//atp.setAnwrtb(refund.getPartB());
			//atp.setAnwrtc(refund.getPartC());
			//atp.setAnwrtd(refund.getPartD());
			atp.setAnwrta(Utils.addZabisDecimalPlace(refund.getPartA(), 2));
			atp.setAnwrtb(Utils.addZabisDecimalPlace(refund.getPartB(), 2));
			atp.setAnwrtc(Utils.addZabisDecimalPlace(refund.getPartC(), 2));
			atp.setAnwrtd(Utils.addZabisDecimalPlace(refund.getPartD(), 2));

			atp.setKzwert(refund.getAmountCode());
			//atp.setMenge(refund.getAmount());
			atp.setMenge(Utils.addZabisDecimalPlace(refund.getAmount(), 1));
			atp.setZfnai(refund.getTypeOfRefund());
			//atp.setApgket(refund.getAmountCoefficient());
			atp.setApgket(Utils.addZabisDecimalPlace(refund.getAmountCoefficient(), 4));
			atp.setUland(refund.getOriginCountry());			
			atp.setWberg1(refund.getAddition1());
			atp.setWberg2(refund.getAddition2());
			
			List <Ingredients> ingredientsList = refund.getIngredientsList();
			if (ingredientsList != null)  {
				for (int i = 0, listSizeInd = ingredientsList.size(); i < listSizeInd; i++) {
					Ingredients ingredient = ingredientsList.get(i);
					atp.addAtiList(setAtiSubset(ingredient, posNr));
				}
			}
		}
		
		if (appTreatment != null)  {
			atp.setAsvfr(appTreatment.getCodeForRefund());
		}
		
		return atp;
	}

	//AK20090407
	public TsATI setAtiSubset(Ingredients ingredients, String posNr) {
		if (ingredients == null) {
			return null;
		}
		
		TsATI ati = new TsATI();
		
		ati.setBeznr(this.msgExpDat.getReferenceNumber());
		ati.setPosnr(posNr);
		ati.setLfdnr(ingredients.getSequentialNumber());
		ati.setGhtant(ingredients.getWeightPercent());
		ati.setHeklnr(ingredients.getUniqueFactoryNumber());
		ati.setKeynr(ingredients.getTarifNumber());
		ati.setKzfkt1(ingredients.getKindOfCoefficient());
		ati.setLiznr(ingredients.getLicenceNumber());
		ati.setMgeant(ingredients.getWeight());
		ati.setText(ingredients.getText());
		ati.setUrfkt1(ingredients.getKindOfCoefficient());
		ati.setUrfkt2(ingredients.getAmount2());
		ati.setUrfkts(ingredients.getRate());
			
		return ati;
	}
			
//	private TsADR setAdr(Party party, String typ, String posnr) {
//		if (party == null) {
//			return null;
//		}
//		
//		TsADR adr = new TsADR();
//		adr.setBeznr(this.msgExpDat.getReferenceNumber());
//		adr.setPosnr(posnr);
//		// adr.setTyp(pos.get)?
//		
//		if (party.getAddress() != null) {
//			adr.setTyp(typ);
//			adr.setName1(party.getAddress().getName());
//			adr.setName2(party.getAddress().getName());
//			adr.setName3(party.getAddress().getName());
//			adr.setStr(party.getAddress().getStreet());
//			adr.setOrt(party.getAddress().getCity());
//			adr.setPlz(party.getAddress().getPostalCode());
//			adr.setLand(party.getAddress().getCountry());
//		}
//		if (party.getContactPerson() != null) {
//			adr.setSbstlg(party.getContactPerson().getPosition());						
//			adr.setSbname(party.getContactPerson().getName());
//			adr.setEmail(party.getContactPerson().getEmail());
//			adr.setTelnr(party.getContactPerson().getPhoneNumber());
//			adr.setFaxnr(party.getContactPerson().getFaxNumber());						
//		}
//		
//		return adr;
//	}

	private TsSAP setSap(MsgExpDatPos msgExpDatPos) {
		if (msgExpDatPos == null) {
			return null;
		}
		
		TsSAP tmpSAP = new TsSAP();			
		
		tmpSAP.setBeznr(this.msgExpDat.getReferenceNumber());
		tmpSAP.setPosnr(msgExpDatPos.getItemNumber());
		tmpSAP.setUndgnr(msgExpDatPos.getDangerousGoodsNumber());
		tmpSAP.setBfgkzw(msgExpDatPos.getPaymentType());
		tmpSAP.setKnrsdg(msgExpDatPos.getShipmentNumber());
		
		return tmpSAP;
	}
	
	private TsAED setAed(Document document, String posNr) {		
		if (document == null) {
			return null;
		}
		
		TsAED tempAED = new TsAED();			
				
		tempAED.setBeznr(this.msgExpDat.getReferenceNumber());
		tempAED.setPosnr(posNr);		
		
		if (document != null) {			
			//tempAED.setUntart(document.getQualifier());
			//tempAED.setUntqar(document.getTypeKids());	
			tempAED.setUntart(document.getTypeKids());
			tempAED.setUntqar(document.getQualifier());
			tempAED.setUntnr(document.getReference());
			tempAED.setUntzus(document.getAdditionalInformation());
			tempAED.setDetail(document.getDetail());
			tempAED.setUntdat(document.getIssueDate());
			tempAED.setGbdat(document.getExpirationDate());
			tempAED.setWert(document.getValue());        //EI20090430
			
			WriteDownAmount writeDownAmount = document.getWriteDownAmount(); 
			if (writeDownAmount != null) {
				//EI20090430: tempAED.setWert(writeDownAmount.getValue());
				tempAED.setMgeme(writeDownAmount.getUnitOfMeasurement());
				//EI20090817: tempAED.setAbgwrt(writeDownAmount.getValueKids());
				tempAED.setAbgwrt(writeDownAmount.getWriteoffValue());   //EI20090817
			}
		}
		return tempAED;
	}

	private TsATK setAtk() {
		TransportMeans transportMeans = msgExpDat.getTransportMeansDeparture();
		ExportRefundHeader expRef = msgExpDat.getExportRefundHeader();
		
		if (transportMeans == null && expRef == null) {
			return null;
		}
		
		TsATK tempATK = new TsATK();
	
		tempATK.setBeznr(this.msgExpDat.getReferenceNumber());
		if (transportMeans != null) {
			tempATK.setBfarta(transportMeans.getTransportationType());
			tempATK.setBfkza(transportMeans.getTransportationNumber());
			tempATK.setBfnata(transportMeans.getTransportationCountry());
			if (transmitter.equals("KFF")) {                //EI20120214
				tempATK.setBfarta("");
				tempATK.setBfkza("");
				tempATK.setBfnata("");
			}					
		}
		
		if (expRef != null) {
			tempATK.setText(expRef.getText());
			tempATK.setAsart(expRef.getPaymentKids());
			tempATK.setAszweg(expRef.getBankNumber());
			tempATK.setAszsbv(expRef.getAssignee());
			tempATK.setAsskto(expRef.getGuarantee());
			tempATK.setAskvb(expRef.getReservationCode());
		}

		return tempATK;
	}

	private TsABF setAbf(String country) {
		if (Utils.isStringEmpty(country)) {
			return null;
		}
		
		TsABF tempABF = new TsABF();

		tempABF.setBeznr(this.msgExpDat.getReferenceNumber());
		tempABF.setLdbf(country);
		return tempABF;
	}

	private TsSAS setSas() {
		
		TsSAS tmpSAS = new TsSAS();
		
		tmpSAS.setBeznr(this.msgExpDat.getReferenceNumber());
		tmpSAS.setBesust(msgExpDat.getSituationCode());
		tmpSAS.setBfgkzw(msgExpDat.getPaymentType());
		tmpSAS.setKnrsdg(msgExpDat.getShipmentNumber());
		
		return tmpSAS;
	}

	private TsACN setAcn(String containerNr, String posNr) {  		
		if (Utils.isStringEmpty(containerNr)) {
			return null;
		}
		
		TsACN tsACN = new TsACN();
		
		tsACN.setBeznr(this.msgExpDat.getReferenceNumber());
		tsACN.setPosnr(posNr);
		tsACN.setConnr(containerNr);

		return tsACN;
	}

	private TsACO setAco(Packages packages, String posNr) {		
		if (packages == null) {
			return null;
		}
		
		TsACO tmpACO = new TsACO();
		
		tmpACO.setBeznr(this.msgExpDat.getReferenceNumber());
		tmpACO.setPosnr(posNr);
		tmpACO.setColanz(packages.getQuantity());
		tmpACO.setLfdnr(packages.getSequentialNumber());
		tmpACO.setColart(packages.getType());
		tmpACO.setColzch(packages.getMarks());

		return tmpACO;
	}

	private TsEDA setEda() {
		TsEDA tmpEda = new TsEDA();

		LoadingTime loadingTime 					= msgExpDat.getLoadingTime();		
		Seal seal 									= msgExpDat.getSeal();
		PlaceOfLoading placeOfLoading 				= msgExpDat.getPlaceOfLoading();
		
		Party consignor 							= msgExpDat.getConsignor();
		Party agent									= msgExpDat.getAgent();
		Party subContractor 						= msgExpDat.getSubcontractor();		
		TIN consignorTIN 							= null; 
		TIN agentTIN 								= null; 
		TIN subContractorTIN 						= null;
		
		if (consignor != null) {
			consignorTIN = consignor.getPartyTIN();
		}
		if (agent != null) {
			agentTIN = agent.getPartyTIN();
		}
		if (subContractor != null) {
			subContractorTIN = subContractor.getPartyTIN();
		}

		tmpEda.setBeznr(this.msgExpDat.getReferenceNumber());
		tmpEda.setBewnr(msgExpDat.getAuthorizationNumber());		
		//tmpEda.setArtueb(msgExpDat.getAreaCode());
		//tmpEda.setArtaus(msgExpDat.getTypeOfPermit());
		tmpEda.setArtaus(msgExpDat.getAreaCode());      //EI20090428
		tmpEda.setArtueb(msgExpDat.getTypeOfPermit());  //EI20090428
		tmpEda.setVerm(msgExpDat.getAnnotation());
		tmpEda.setKzanau(msgExpDat.getDeclarantIsConsignor());
		tmpEda.setLdbe(msgExpDat.getDestinationCountry());
		tmpEda.setConkz(msgExpDat.getTransportInContainer());
		tmpEda.setFregnr(msgExpDat.getUCROtherSystem());	
		//tmpEda.setGsroh(msgExpDat.getGrossMass());
		tmpEda.setGsroh(Utils.addZabisDecimalPlace(msgExpDat.getGrossMass(), 3));
		tmpEda.setLdve(msgExpDat.getDispatchCountry());			
		tmpEda.setExtdst(msgExpDat.getIntendedOfficeOfExit());
		
		if (loadingTime != null) {
			tmpEda.setGststr(loadingTime.getBeginTime());
			tmpEda.setGstend(loadingTime.getEndTime());
		}
		if (consignorTIN != null) {
			tmpEda.setKdnrau(consignorTIN.getCustomerIdentifier());
			tmpEda.setTinau(consignorTIN.getTIN());
			tmpEda.setDtzoau(consignorTIN.getIsTINGermanApprovalNumber());
		}
		if (agentTIN != null) {
			tmpEda.setKdnrva(agentTIN.getCustomerIdentifier());
			tmpEda.setTinva(agentTIN.getTIN());
			tmpEda.setDtzova(agentTIN.getIsTINGermanApprovalNumber());
			//this.setEtnva(msgExpDat.getAgent().getETNAddress());  
		}

		if (subContractorTIN != null) {
			tmpEda.setKdnrsu(subContractorTIN.getCustomerIdentifier());
			tmpEda.setTinsu(subContractorTIN.getTIN());
			tmpEda.setDtzosu(subContractorTIN.getIsTINGermanApprovalNumber());
		}

		if (placeOfLoading != null) {
			tmpEda.setLdocde(placeOfLoading.getCode());
			tmpEda.setBeostr(placeOfLoading.getStreet());
			tmpEda.setBeoort(placeOfLoading.getCity());
			tmpEda.setBeoplz(placeOfLoading.getPostalCode());
			tmpEda.setBeozus(placeOfLoading.getAgreedLocationOfGoods());
		}

		if (seal != null) {
			tmpEda.setKztyd(seal.getUseOfTydenseals());
			tmpEda.setKzstock(seal.getUseOfTydensealStock());
			tmpEda.setAnzve(seal.getNumber());
			tmpEda.setVsart(seal.getKind());
		}

		return tmpEda;
	}

	private TsDAT setDat() {
		TsDAT tmpDat = new TsDAT();
		
		Party consignee	 = msgExpDat.getConsignee();
		Party declarant	 = msgExpDat.getDeclarant();
		TIN consigneeTIN = null; 
		TIN declarantTIN = null;
				
		if (consignee != null) {
			consigneeTIN = consignee.getPartyTIN();
		}
		if (declarant != null) {
			declarantTIN = declarant.getPartyTIN();
		}

		tmpDat.setBeznr(this.msgExpDat.getReferenceNumber());
		tmpDat.setKuatnr(msgExpDat.getOrderNumber());
		tmpDat.setMrn(msgExpDat.getUCRNumber());
				
			tmpDat.setExpdst(msgExpDat.getCustomsOfficeExport());
			tmpDat.setEamdst(msgExpDat.getCustomsOfficeForCompletion());		

		if (declarantTIN != null) {
			tmpDat.setKdnran(declarantTIN.getCustomerIdentifier());
			tmpDat.setTinan(declarantTIN.getTIN());
			tmpDat.setDtzoan(declarantTIN.getIsTINGermanApprovalNumber());
		}
		
		if (consigneeTIN != null) {
			tmpDat.setKdnrem(consigneeTIN.getCustomerIdentifier());
			tmpDat.setTinem(consigneeTIN.getTIN());
		}

		tmpDat.setQuelkz(msgExpDat.getPreviousProcedure());				
		//EI20090609: tmpDat.setSb(msgExpDat.getClerk());	
		if (msgExpDat.getContact() != null) {
		    tmpDat.setSb(msgExpDat.getContact().getIdentity());	//EI20090609
		}
		tmpDat.setVekan(msgExpDat.getReceiverCustomerNumber());
		
		return tmpDat;
	}

	private TsEAM setEam() {			
		
		TransportMeans meansBorder = msgExpDat.getTransportMeansBorder();
		TransportMeans meansInland = msgExpDat.getTransportMeansInland();
		Business business = msgExpDat.getBusiness();
		IncoTerms incoTerms = msgExpDat.getIncoTerms();

		if (meansBorder == null && meansInland == null && business == null && incoTerms == null) {
			return null;
		}
		
		TsEAM tmpEam = new TsEAM();
		
		tmpEam.setBeznr(this.msgExpDat.getReferenceNumber());
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
			//tmpEam.setGesprs(business.getInvoicePrice());
			tmpEam.setGesprs(Utils.addZabisDecimalPlace(business.getInvoicePrice(), 2));
			tmpEam.setGeswrg(business.getCurrency());
		}
		if (incoTerms != null) {
			tmpEam.setLibart(incoTerms.getIncoTerm());
			tmpEam.setLibinc(incoTerms.getText());
			tmpEam.setLibort(incoTerms.getPlace());
		}
		return tmpEam;
	}

	private TsAVS setAvs(String seal) {
		
		if (Utils.isStringEmpty(seal)) {
			return null;
		}

		TsAVS tmpAvs = new TsAVS();
		
		tmpAvs.setBeznr(this.msgExpDat.getReferenceNumber());
		tmpAvs.setSeal(seal);

		return tmpAvs;
	}

	private TsAPO setApo(MsgExpDatPos msgExpDatPos) {
		if (msgExpDatPos == null) {
			return null;
		}
		
		TsAPO 					tmpApo = new TsAPO();
											
		CommodityCode 			commodityCode = msgExpDatPos.getCommodityCode();
		ApprovedTreatment 		approvedTreatment = msgExpDatPos.getApprovedTreatment();
		Statistic 				statistic = msgExpDatPos.getStatistic();
		Party                   consignee = msgExpDatPos.getConsignee();	
		Completion 				completion 	= null;
		String 					annotation = msgExpDatPos.getAnnotation();	
		String 					annotation2 = msgExpDatPos.getAnnotation2();		
	
		tmpApo.setBeznr(this.msgExpDat.getReferenceNumber());
		tmpApo.setPosnr(msgExpDatPos.getItemNumber());
		tmpApo.setArtnr(msgExpDatPos.getArticleNumber());
		
		if (commodityCode != null) {
			tmpApo.setTnr(commodityCode.getTarifCode());
			tmpApo.setTnrtrc(commodityCode.getEUTarifCode());
			tmpApo.setTnrzu1(commodityCode.getTarifAddition1());
			tmpApo.setTnrzu2(commodityCode.getTarifAddition2());
			tmpApo.setTnrnat(commodityCode.getAddition());
		}

		tmpApo.setWbsch(msgExpDatPos.getDescription());
		tmpApo.setFregnr(msgExpDatPos.getUCROtherSystem());
		
		if (consignee != null)  {
			TIN tin = consignee.getPartyTIN();
			if (tin != null) {
				tmpApo.setKdnrem(tin.getCustomerIdentifier());
				tmpApo.setTinem(tin.getTIN());
			}
		}
		
		completion 	= msgExpDatPos.getInwardProcessingCompletion();
		if (completion == null) { 
			completion = msgExpDatPos.getBondedWarehouseCompletion();
		}
		
		if (completion != null) {
			tmpApo.setAzvbew(completion.getAuthorizationNumber());
			tmpApo.setZlbez(completion.getReferenceNumber()); //EI20090814
		}
			
		if (!(Utils.isStringEmpty(annotation2))) {
				annotation.concat(msgExpDatPos.getAnnotation2());
		}
		tmpApo.setVerm(annotation);
		
		//tmpApo.setEigmas(msgExpDatPos.getNetMass());
		tmpApo.setEigmas(Utils.addZabisDecimalPlace(msgExpDatPos.getNetMass(), 3));
		//tmpApo.setRohmas(msgExpDatPos.getGrossMass());
		tmpApo.setRohmas(Utils.addZabisDecimalPlace(msgExpDatPos.getGrossMass(), 3));
		if (this.transmitter.equals("KFF")) { //EI20120207: JIRA-Ticket KCX-94 (siehe calculateNetMass(...))
			//in tmpAPO muessten die Gewichte ohne Punkte und ohne Komma sein, wenn doch, dann ist es
			//sowieso falsch => in calculateNetMass(...) wird es nicht "umformatiert"
			tmpApo.setEigmas(calculateNetMass(tmpApo.getRohmas(), tmpApo.getEigmas()));
		}		
		
		tmpApo.setUbland(msgExpDatPos.getOriginFederalState());
		
		if (approvedTreatment != null) {
			tmpApo.setAnmvrf(approvedTreatment.getDeclared());
			tmpApo.setPrevrf(approvedTreatment.getPrevious());
			tmpApo.setNatvrf(approvedTreatment.getNational());
		}
		if (statistic != null) {
			//tmpApo.setWmahst(statistic.getAdditionalUnit());
			tmpApo.setWmahst(Utils.addZabisDecimalPlace(statistic.getAdditionalUnit(), 3));
			tmpApo.setAhwert(statistic.getStatisticalValue());
		}

		return tmpApo;
	}

	private TsEPO setEpo(Statistic statistic, String posNr) {				
		if (statistic == null) {
			return null;		
		}

		if (Utils.isStringEmpty(statistic.getValue()) && Utils.isStringEmpty(statistic.getCurrency())) {
			return null;
		}
		
		TsEPO tmpEpo = new TsEPO();

		tmpEpo.setBeznr(this.msgExpDat.getReferenceNumber());
		tmpEpo.setPosnr(posNr);
		tmpEpo.setBasbtg(Utils.addZabisDecimalPlace(statistic.getValue(), 2));
		tmpEpo.setBaswrg(statistic.getCurrency());

		return tmpEpo;
	}

	private TsAZV setAzv(PreviousDocument prevDoc, String posNr) {		
		if (prevDoc == null) {
			return null;		
		}

		TsAZV tsAZV = new TsAZV();
		
		tsAZV.setBeznr(this.msgExpDat.getReferenceNumber());
		tsAZV.setPosnr(posNr);
		tsAZV.setVptyp(prevDoc.getType());
		tsAZV.setAzvref(prevDoc.getMarks());
		//tsAZV.setAzvref(prevDoc.getMarksForFss());
		tsAZV.setAzvzus(prevDoc.getAdditionalInformation());

		return tsAZV;
	}
	
	//AK20090407-1
	private List<TsBZL> setBzlList(Completion bwCompletion, String itemNumber) {
		if (bwCompletion == null) {
			return null;
		}
		
		List <TsBZL>bzlList = null;
				
		List completionItemList = bwCompletion.getCompletionItemList();
		if (completionItemList  != null)  {
			bzlList = new Vector<TsBZL>();
			for (int i = 0, listSize = completionItemList.size(); i < listSize; i++) {
				CompletionItem completionItem = (CompletionItem) completionItemList.get(i);
				bzlList.add(setBzlItem(completionItem, itemNumber));
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
				tempBZL.setMgeabg(writeDownAmount.getWriteoffValue());  //EI20090818
				tempBZL.setQmeabg(writeDownAmount.getQualifier());
			}					
			if (tradeAmount != null) {
				tempBZL.setMehdl(tradeAmount.getUnitOfMeasurement());
				//EI20090818: tempBZL.setMgehdl(tradeAmount.getValueKids());
				tempBZL.setMgehdl(tradeAmount.getWriteoffValue());  //EI20090818
				tempBZL.setQmehdl(tradeAmount.getQualifier());
			}
		
		return tempBZL;
	}
	
	//AK20090407-2
	private List<TsBAV> setBavList(Completion ipCompletion, String itemNumber) {
		if (ipCompletion == null) {
			return null;			
		}
	
			List <TsBAV>bavList = null;
			
			List<CompletionItem> completionItemList = ipCompletion.getCompletionItemList();
			if (completionItemList  != null) {
				bavList = new Vector<TsBAV>();
				for (int i = 0, listSize = completionItemList.size(); i < listSize; i++) {
					CompletionItem completionItem = completionItemList.get(i);
					bavList.add(setBav(completionItem, itemNumber));
				}
			}
		
		return bavList;
	}
	
	//AK20090407-2, EI20090814
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
		if(msgExpDat != null && msgExpDat.getContainer() != null && !msgExpDat.getContainer().isEmpty()) {
			ret = true;
		}
		return ret;
	}
}
