package com.kewill.kcx.component.mapping.countries.ch.aus20.kids2fss;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.common.SpecialMention;
import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CommodityCode;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.DocumentV20;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.NonCustomsLaw;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Notifications;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Permit;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.common.PreviousDocumentV21;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Refinement;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.SensitiveGoods;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Statistic;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpDat;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpDatPos;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCAN;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCBW;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCCL;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCCN;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCMD;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCNZ;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCPO;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCPZ;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCSG;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCST;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCUN;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCVA;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCVE;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCVI;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCVM;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus20.messages.MsgCAN;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus20.messages.MsgCANPos;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus20.subsets.TsCAD;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus20.subsets.TsCAI;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module      : EDEC Export 20
 * Created     : 09.11.2012
 * Description : Mapping of KIDS format of ExportDeclaration to CAN.
 * 
 * @author iwaniuk
 * @version 2.0.00
 * 
 */

public class MapExpDatToCAN extends KidsMessage {

	private MsgExpDat message;
	private MsgCAN    msgCAN;

	public MapExpDatToCAN(XMLEventReader parser) throws XMLStreamException {
		message = new MsgExpDat(parser);
		msgCAN = new MsgCAN("");
	}

	public MapExpDatToCAN(XMLEventReader parser, TsVOR tsvor) throws XMLStreamException {
		message = new MsgExpDat(parser);
		msgCAN = new MsgCAN("");
		msgCAN.setVorSubset(tsvor);
	}
	
	public MapExpDatToCAN(XMLEventReader parser, TsVOR tsvor, TsHead head) throws XMLStreamException {
		message = new MsgExpDat(parser);
		msgCAN = new MsgCAN("");
		msgCAN.setVorSubset(tsvor);
		msgCAN.setHeadSubset(head);
	}

	public String getMessage() {
		String beznr;
		String posnr;
		String res = "";
		
		try {
			message.parse(HeaderType.KIDS);
			beznr = message.getReferenceNumber();
			getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());

			msgCAN.getVorSubset().setMsgid(getKidsHeader().getMessageID());
			msgCAN.setCanSubset(mapCAN()); 
			msgCAN.setCaiSubset(mapCAI());
			
			if (message.getConsignor() != null) {
				msgCAN.setConsignor(mapCAD("1", message.getConsignor().getAddress(), beznr));
			}									
			if (message.getConsignee() != null) {
				msgCAN.setConsignee(mapCAD("3", message.getConsignee().getAddress(), beznr));
			}
			if (message.getDeclarant() != null) {
				msgCAN.setDeclarant(mapCAD("2", message.getDeclarant().getAddress(), beznr));
			}
			if (message.getConsignorSecurity() != null) {
				msgCAN.setConsignorSecurity(mapCAD("5", message.getConsignorSecurity().getAddress(), beznr));
			}
			if (message.getConsigneeSecurity() != null) {
				msgCAN.setConsigneeSecurity(mapCAD("6", message.getConsigneeSecurity().getAddress(), beznr));
			}			
			if (message.getCarrier() != null) {
				msgCAN.setCarrier(mapCAD("9", message.getCarrier().getAddress(), beznr));
			}
			//Zuladeort = 8
			
			if (message.getContainer() != null) {
				msgCAN.setCcnList(mapCCNList(message.getContainer().getNumberList(), beznr));
			}
			/* EI20130517 muss noch in KIDS definiert werden
			if (message.getPreviousDocumentList() != null) {
				msgCAN.setCvpList(mapCVPList(message.getPreviousDocumentList, beznr));
			}	
			*/
			if (message.getPreviousDocumentList() != null) {   
				msgCAN.setCviList(mapCVIList(message.getPreviousDocumentList(), beznr));
			}			
			if (message.getSpecialMentionList() != null) {   
				msgCAN.setCvmList(mapCVMList(message.getSpecialMentionList(), beznr, "0"));
			}

			if (message.getGoodsItemList() != null) {
				for (MsgExpDatPos item : message.getGoodsItemList()) {	
					MsgCANPos msgCanPos = new MsgCANPos("");				
					posnr = item.getItemNumber();
				
					msgCanPos.setCpoSubset(mapCPO(item, beznr));
					msgCanPos.setCpzSubset(mapCPZ(item, beznr, posnr));  
					msgCanPos.setCclList(mapCCLList(item.getPackagesList(), beznr, posnr));
					msgCanPos.setCbwList(mapCBWList(item.getPermitList(), beznr, posnr));
					msgCanPos.setCunList(mapCUNList(item.getDocumentList(), beznr, posnr));	
					msgCanPos.setCvaList(mapCVAList(item.getPreviousDocumentList(), beznr, posnr));	//EI20130517
					msgCanPos.setCvmList(mapCVMList(item.getSpecialMentionList(), beznr, posnr));  //EI20110429
					msgCanPos.setCmdList(mapCMDList(item.getNotificationCodeList(), beznr, posnr));  //EI20110429 new
					msgCanPos.setCnzList(mapCNZList(item.getNonCustomsLaw(), beznr, posnr));  //EI20110429 new
					msgCanPos.setCveSubset(mapCVE(item.getRefinement(), beznr, posnr));  //EI20110429 new
					msgCanPos.setCstList(mapCSTList(item.getDetailList(), beznr, posnr));  //EI20110429 new
					msgCanPos.setCsgList(mapCSGList(item.getSensitiveGoodsList(), beznr, posnr)); //EI20110429 new
				
					msgCAN.addCanPosList(msgCanPos);
				}
		    }
            
			//res = msgCAN.getFssString();
			if (this.writeHead()) { 				//EI20130213
            	res = msgCAN.getFssString("HEAD");
            } else {
            	res = msgCAN.getFssString();
            }  

			Utils.log("(MapExpDatToCAN getMessage) Msg = " + res);

		} catch (FssException e) {
			e.printStackTrace();
		}

		return res;
	}
	
    //---------------------
	
	private TsCAN mapCAN() {
		TsCAN canSubset = new TsCAN();

		canSubset.setBeznr(message.getReferenceNumber());		
		canSubset.setKorant(message.getCorrectionCode());
		canSubset.setDklart(message.getKindOfDeclaration());
		canSubset.setSammnr(message.getBunchNumber());
		canSubset.setAufnr(message.getOrderNumber());
		//tstat, cdstat, 
		canSubset.setLang(message.getLanguage());
		if (message.getConsignor() != null) {
			if (message.getConsignor().getPartyTIN() != null) {
				canSubset.setKunrvs(message.getConsignor().getPartyTIN().getCustomerIdentifier());
			}			
			canSubset.setMwstnr(message.getConsignor().getVATNumber());
		}
		if (message.getDeclarant() != null) {
			if (message.getDeclarant().getPartyTIN() != null) {
				canSubset.setSpdnr(message.getDeclarant().getPartyTIN().getTIN()); //TODO-IWA: in docu steht DeclarantNumber???
			}
		}
		//ausdst, 		
		canSubset.setAbgdst(message.getCustomsOfficeExport());  
		canSubset.setKzncts(message.getTransferToTransitSystem());			
		canSubset.setLdvs(message.getDispatchCountry());
		canSubset.setLdbe(message.getDestinationCountry());
		if (message.getTransportMeansBorder() != null) {
			canSubset.setVzgr(message.getTransportMeansBorder().getTransportMode());
			canSubset.setBfabkz(message.getTransportMeansBorder().getTransportationNumber());
			canSubset.setBfabld(message.getTransportMeansBorder().getTransportationCountry());			
		}
		canSubset.setKzcont(message.getTransportInContainer());
		if (message.getPlaceOfLoading() != null) {                         //EI20130326
			canSubset.setLadort(message.getPlaceOfLoading().getCode());		
		}
		canSubset.setGsroh(Utils.addZabisDecimalPlaceV7(message.getGrossMass(), 3));	//EI20130107		
		//mwstnr - ist schon oben gefuellt
		
		
		return canSubset;
	}
	
	private TsCAI mapCAI() {
		TsCAI caiSubset = new TsCAI();

		caiSubset.setBeznr(message.getReferenceNumber());
		caiSubset.setCdkorgrd(message.getCorrectionReason());   //EI20130326
		caiSubset.setDectim(message.getDeclarationTime());
		if (message.getWareHouse() != null) {
			caiSubset.setBezlag(message.getWareHouse().getWarehouseType());  //EI20130326
		}
		caiSubset.setVerort(message.getPlaceOfDeclaration());
		//kzzv: nur fuer NCTS
		caiSubset.setKzsec(message.getSecurityIndicator());
		if (message.getConsignor() != null && message.getConsignor().getPartyTIN() != null) {
			caiSubset.setTinvs(message.getConsignor().getPartyTIN().getTIN());
		}
		if (message.getConsignee() != null && message.getConsignee().getPartyTIN() != null) {
			caiSubset.setTinem(message.getConsignee().getPartyTIN().getTIN());
		}
		if (message.getConsignorSecurity() != null && message.getConsignorSecurity().getPartyTIN() != null) {
			caiSubset.setTinvsc(message.getConsignorSecurity().getPartyTIN().getTIN());
			caiSubset.setKunrvsc(message.getConsignorSecurity().getPartyTIN().getCustomerIdentifier());  //EI20130326
		}
		if (message.getConsigneeSecurity() != null && message.getConsigneeSecurity().getPartyTIN() != null) {
			caiSubset.setTinesc(message.getConsigneeSecurity().getPartyTIN().getTIN());
			caiSubset.setKunresc(message.getConsigneeSecurity().getPartyTIN().getCustomerIdentifier());  //EI20130326
		}
		//tinzul, 
		if (message.getCarrier() != null && message.getCarrier().getPartyTIN() != null) {
			caiSubset.setTintra(message.getCarrier().getPartyTIN().getTIN());
			caiSubset.setKunrtra(message.getCarrier().getPartyTIN().getCustomerIdentifier()); //EI20130326
		}	
		if (message.getForwarder() != null && message.getForwarder().getPartyTIN() != null) {
			caiSubset.setTinspd(message.getForwarder().getPartyTIN().getTIN());
		}				
		caiSubset.setBesums(message.getSituationCode());  //TODO-IWA: in docu: SpecificCircumstanceIndicator???		
		caiSubset.setUcr(message.getUCROtherSystem());	 //TODO-IWA: in docu: UCRNumber			
		caiSubset.setKzinj(message.getInjunctionType());
		if (message.getPlaceOfLoading() != null) {
			caiSubset.setWaort(message.getPlaceOfLoading().getAgreedLocationOfGoods());			
		}

		if (message.getPlaceOfLoading() != null) {
            caiSubset.setWaort(message.getPlaceOfLoading().getAgreedLocationOfGoods());
		}		
		caiSubset.setCdtype(message.getStatusCode());
		caiSubset.setCdprvgrd(message.getTemporaryReason());		
		if (message.getBusiness() != null) {
			caiSubset.setCdgsart(message.getBusiness().getBusinessTypeCode());
		}		
		if (message.getPlaceOfLoading() != null) {
			caiSubset.setAbladort(message.getPlaceOfLoading().getCity());			
		}
		if (message.getIncoTerms() != null) {
			caiSubset.setCdinco(message.getIncoTerms().getIncoTerm());
		}
		caiSubset.setFnrstpf(message.getCompanyNumberTaxPayer());
		caiSubset.setKunrem(message.getReceiverCustomerNumber());          //EI20130326
		//kunrvsc, kunresc, kunrzul, kunrtra
		caiSubset.setDknr(message.getDeclarantNumber());
		caiSubset.setWhcod(message.getInvoiceCurrencyType()); //EI20130326
		
		return caiSubset;
	}
	
	public TsCAD mapCAD(String type, Address address, String abeznr) {
		if (address == null) {
			return null;
		}

		TsCAD cadSubset = new TsCAD();

		cadSubset.setBeznr(abeznr);
		cadSubset.setTyp(type);
		cadSubset.setName(address.getName());
		cadSubset.setStr(address.getStreet());
		if (address.getStreet() != null && address.getHouseNumber() != null) { //EI20130326
			cadSubset.setStr(address.getStreet() + address.getHouseNumber());
		}
		cadSubset.setLand(address.getCountry());
		cadSubset.setPlz(address.getPostalCode());
		cadSubset.setOrt(address.getCity());

		return cadSubset;
	}

	
	private List<TsCCN> mapCCNList(List<String> list, String beznr) {				                         
		if (list == null) {
			return null;
		}
		List<TsCCN>	ccnList = new Vector<TsCCN>();			
		TsCCN ccnSubset = null;

		int i = 0;
		int c = 0;
		int n = 0;
		for (String number : list) {						
			//EI20130326: c = i - 9 * n;
			c = i;
            i = i + 1;          //EI20130326
			switch (c) {
			case 0:
				ccnSubset = new TsCCN();
				ccnSubset.setBeznr(beznr);
				ccnSubset.setConnr1(number);  
				ccnList.add(ccnSubset);   				
				break;
			case 1:
				ccnSubset.setConnr2(number);
				break;
			case 2:
				ccnSubset.setConnr3(number);
				break;
			case 3:
				ccnSubset.setConnr4(number);
				break;
			case 4:
				ccnSubset.setConnr5(number);
				break;
			case 5:
				ccnSubset.setConnr6(number);
				break;
			case 6:
				ccnSubset.setConnr7(number);
				break;
			case 7:
				ccnSubset.setConnr8(number);
				break;
			case 8:
				ccnSubset.setConnr9(number);
				//EI20130326: n = n + 1;
				i = 0; //EI20130326
				break;
			default:
				break;
			}					
		}
		return ccnList;
	}
		
	//private List<TsCVI> mapCVIList(List<PreviousDocumentV20> list, String beznr) {
	private List<TsCVI> mapCVIList(List<PreviousDocumentV21> list, String beznr) {		
        //list == message.getPreviousDocumentList()		
		if (list == null) {
			return null;
		}
		List<TsCVI>	cviList = new Vector<TsCVI>();	
		int size = list.size();		
		TsCVI cviSubset = null;		

		for (int i = 0; i < size; i++) {
			if (list.get(i) != null) {
				cviSubset = new TsCVI();
				cviSubset.setBeznr(beznr);
				cviSubset.setPosnr("0");
				cviSubset.setVpart(list.get(i).getType());
				//cviSubset.setVpref(list.get(i).getMarks());
				cviSubset.setVpref(list.get(i).getReference());  //EI20130327
				cviSubset.setVpinf(list.get(i).getAdditionalInformation());				
				cviList.add(cviSubset);
			}
		}
		return cviList;
	}
	//private List<TsCVA> mapCVAList(List<PreviousDocumentV20> list, String beznr, String posnr) {	//EI20130517	
	private List<TsCVA> mapCVAList(List<PreviousDocumentV21> list, String beznr, String posnr) {	//EI20130517	
        //list == message.getPreviousDocumentList()		
		if (list == null) {
			return null;
		}
		List<TsCVA>	cvaList = new Vector<TsCVA>();	
		int size = list.size();		
		TsCVA cvaSubset = null;		

		for (int i = 0; i < size; i++) {
			if (list.get(i) != null) {
				cvaSubset = new TsCVA();
				cvaSubset.setBeznr(beznr);
				cvaSubset.setPosnr(posnr);
				cvaSubset.setVpart(list.get(i).getType());
				//cviSubset.setVpref(list.get(i).getMarks());
				cvaSubset.setVpnr(list.get(i).getReference());  
				cvaSubset.setVpzus(list.get(i).getAdditionalInformation());				
				cvaList.add(cvaSubset);
			}
		}
		return cvaList;
	}
	private List<TsCVM> mapCVMList(List<SpecialMention> list, String beznr, String posnr) {		
	       //list == message.getSpecialMentionList()		
		if (list == null) {
			return null;
		}
		List<TsCVM>	cvmList = new Vector<TsCVM>();	
		int size = list.size();		
		TsCVM cvmSubset = null;

		for (int i = 0; i < size; i++) {
			if (list.get(i) != null) {
				cvmSubset = new TsCVM();
				cvmSubset.setRefnr(beznr);
				cvmSubset.setPosnr(posnr);				
				cvmSubset.setVm(list.get(i).getText());
				cvmList.add(cvmSubset);
			}
		}
		return cvmList;
	}
	
	private TsCPO mapCPO(MsgExpDatPos item, String beznr) {
		TsCPO cpoSubset = new TsCPO();

		cpoSubset.setBeznr(beznr);
		cpoSubset.setPosnr(item.getItemNumber());
		cpoSubset.setCdhw(item.getKindOfArticle());
		//cdabf2
		cpoSubset.setCdabf(item.getTypeOfArticle());
		//cdabfa
		if (item.getNonCustomsLaw() != null && 
				item.getNonCustomsLaw().getNonCustomsLawType() != null) {
			cpoSubset.setCdnzpf(item.getNonCustomsLaw().getObligation());
		}
		CommodityCode cc = item.getCommodityCode();
		if (cc != null) {
			cpoSubset.setTnr(cc.getTarifCode());
			cpoSubset.setTnrsch(cc.getAddition());
		}
		cpoSubset.setWabes(item.getDescription());				
		cpoSubset.setRohm(Utils.addZabisDecimalPlaceV7(item.getGrossMass(), 3));	//EI20130107		
		cpoSubset.setEigm(Utils.addZabisDecimalPlaceV7(item.getNetMass(), 3));	//EI20130107		
		Statistic st = item.getStatistic();
		if (st != null) {
			//cpoSubset.setZusm(st.getAdditionalUnit());
			cpoSubset.setZusm(Utils.addZabisDecimalPlaceV7(st.getAdditionalUnit(), 1)); //EI20130107
			cpoSubset.setStwt(st.getStatisticalValue());
			//EI20130327: cpoSubset.setRpreis(Utils.addZabisDecimalPlaceV7(st.getValue(), 2));
			if (!Utils.isStringEmpty(st.getCurrency()) && !st.getCurrency().equals("CHF")) {    //EI20130327
				cpoSubset.setRpwae(st.getCurrency());  
				cpoSubset.setRpreis(Utils.addZabisDecimalPlaceV7(st.getValue(), 2));      
			}
		}		
		cpoSubset.setSgicd(item.getRefundType());		
		cpoSubset.setSgimng(Utils.addZabisDecimalPlaceV7(item.getRefundQuantity(), 3)); //EI20130107
		Notifications no = item.getNotifications();
		if (no != null) {
			cpoSubset.setCdmeld(no.getNotificationCode());
		}				
		List <SpecialMention> l = item.getSpecialMentionList();
		if (l != null) {
			SpecialMention sp = l.get(0);
			if (sp != null) {
				cpoSubset.setZusang(sp.getTypeOfExport());
				cpoSubset.setCdexeu(sp.getExportFromEU());
				cpoSubset.setCdexld(sp.getExportFromCountry());
				cpoSubset.setVerm(sp.getText());   //EI20130327
			}
		}
		cpoSubset.setCdungf(item.getDangerousGoodsNumber());

		return cpoSubset;
	}

	private TsCPZ mapCPZ(MsgExpDatPos item, String beznr, String posnr) {
		TsCPZ cpzSubset = new TsCPZ();
		
		cpzSubset.setBeznr(beznr);
		cpzSubset.setPosnr(posnr);
		cpzSubset.setCdbwpf(item.getPermitObligation());
		cpzSubset.setRohmri(item.getGrossMassConfirmation());
		cpzSubset.setEigmri(item.getNetMassConfirmation());
		
		if (item.getStatistic() != null) {
			cpzSubset.setZusmri(item.getStatistic().getAdditionalUnitConfirmation());
			cpzSubset.setStwtri(item.getStatistic().getStatisticalValueConfirmation());
		}		
		if (item.getCommodityCode() != null) {
			cpzSubset.setTnrri(item.getCommodityCode().getConfirmation());
		}
		cpzSubset.setArtnr(item.getArticleNumber());   //EI20130327
		
		return cpzSubset;
	}
	
	public List<TsCCL> mapCCLList(List<Packages> list, String beznr,
			String posnr) {
		if (list == null) {
			return null;
		}

		int size = list.size();
		List<TsCCL> cclList = new Vector<TsCCL>();

		for (int i = 0; i < size; i++) {			
			Packages packages = list.get(i);
            if (packages != null) {
            	TsCCL cclSubset = new TsCCL();
            	cclSubset.setBeznr(beznr);
            	cclSubset.setPosnr(posnr);
            	cclSubset.setColart(packages.getType());
            	cclSubset.setColanz(packages.getQuantity());
            	cclSubset.setColstk(packages.getQuantityCH());
            	cclSubset.setColzch(packages.getMarks());
            	
            	cclList.add(cclSubset);
            }
		}
		return cclList;
	}

	public List<TsCBW> mapCBWList(List<Permit> list, String beznr, String posnr) {
		if (list == null) {
			return null;
		}
		int size = list.size();
		List<TsCBW> cbwList = new Vector<TsCBW>();

		for (int i = 0; i < size; i++) {
			Permit permit = list.get(i);
            if (permit != null) {
            	TsCBW cbwSubset = new TsCBW();
            	cbwSubset.setBeznr(beznr);
            	cbwSubset.setPosnr(posnr);
            	cbwSubset.setBwstcd(permit.getPermitAuthority());
            	cbwSubset.setBwnr(permit.getPermitNumber());
            	cbwSubset.setBwdat(permit.getIssueDate());
            	//EI20130327: cbwSubset.setBwverm(permit.getAdditionalInformation());
            	cbwSubset.setBwverm(permit.getType());   //EI20130327
            	cbwSubset.setZusang(permit.getAdditionalInformation()); //EI20130327
            			
            	cbwList.add(cbwSubset);
            }
		}

		return cbwList;
	}

	public List<TsCUN> mapCUNList(List<DocumentV20> list, String beznr,
			String posnr) {
		if (list == null) {
			return null;
		}

		List<TsCUN> cunList = new Vector<TsCUN>();

		for (DocumentV20 doc : list) {
			if (doc != null) {
				TsCUN cunSubset = new TsCUN();
			
				cunSubset.setBeznr(beznr);
				cunSubset.setPosnr(posnr);
				cunSubset.setUntart(doc.getType());
				cunSubset.setUntref(doc.getReference());
				cunSubset.setUntzus(doc.getAdditionalInformation());
				cunSubset.setDatum(doc.getIssueDate());            //EI20130327

				cunList.add(cunSubset);
			}
		}

		return cunList;
	}	
		
	private List<TsCMD> mapCMDList(List<String> list, String beznr, String posnr) {		
	       //list == message.getNotificationCodeList()		
		if (list == null) {
			return null;
		}
		List<TsCMD>	cmdList = new Vector<TsCMD>();	
		int size = list.size();		
		TsCMD cmdSubset = null;

		for (int i = 0; i < size; i++) {
			if (list.get(i) != null) {
				cmdSubset = new TsCMD();
				cmdSubset.setRefnr(beznr);
				cmdSubset.setPosnr(posnr);				
				cmdSubset.setMldstl(list.get(i));
				cmdList.add(cmdSubset);
			}
		}
		return cmdList;
	}
		
	private List<TsCNZ> mapCNZList(NonCustomsLaw argument, String beznr, String posnr) {			       
		if (argument == null) {
			return null;
		}
		List <String> list = argument.getNonCustomsLawType();
		if (list == null) {
			return null;
		}
		List<TsCNZ>	cnzList = new Vector<TsCNZ>();	
		int size = list.size();		
		TsCNZ cnzSubset = null;

		for (int i = 0; i < size; i++) {
			if (list.get(i) != null) {
				cnzSubset = new TsCNZ();
				cnzSubset.setRefnr(beznr);
				cnzSubset.setPosnr(posnr);				
				cnzSubset.setCdnz(list.get(i));
				cnzList.add(cnzSubset);
			}
		}
		return cnzList;
	}
	
	private TsCVE mapCVE(Refinement refinement, String beznr, String posnr) {	
		if (refinement == null || refinement.isEmpty()) {
			return null;
		}	
		
		TsCVE cveSubset = new TsCVE();		
		cveSubset.setRefnr(beznr);
		cveSubset.setPosnr(posnr);
		cveSubset.setRrdir(refinement.getDirection());
		cveSubset.setRretyp(refinement.getRefinementType());
		cveSubset.setRratyp(refinement.getBillingType());
		cveSubset.setRrkzfp(refinement.getTemporaryAdmission());
		cveSubset.setRrvtyp(refinement.getProcessType());		
		cveSubset.setRrptyp(refinement.getPositionType());
		
		return cveSubset;
	}
		
	private List<TsCST> mapCSTList(List<Text> list, String beznr, String posnr) {		
	       //list == message.getDetailList()		
		if (list == null) {
			return null;
		}
		List<TsCST>	cstList = new Vector<TsCST>();	
		int size = list.size();		
		TsCST cstSubset = null;
		
		for (int i = 0; i < size; i++) {
			if (list.get(i) != null) {
				cstSubset = new TsCST();
				cstSubset.setRefnr(beznr);
				cstSubset.setPosnr(posnr);				
				cstSubset.setName(list.get(i).getCode());
				cstSubset.setValue(list.get(i).getText());
			
				cstList.add(cstSubset);
			} 
		}
		return cstList;
	}
	private List<TsCSG> mapCSGList(List<SensitiveGoods> list, String beznr, String posnr) {		
	       //list == messagePos.getSensitiveGoodsList()		
		if (list == null) {
			return null;
		}
		List<TsCSG>	csgList = new Vector<TsCSG>();	
		int size = list.size();		
		TsCSG csgSubset = null;

		for (int i = 0; i < size; i++) {
			if (list.get(i) != null) {
				csgSubset = new TsCSG();
				csgSubset.setRefnr(beznr);
				csgSubset.setPosnr(posnr);				
				csgSubset.setCdsgi(list.get(i).getType());				
				csgSubset.setGew(Utils.addZabisDecimalPlaceV7(list.get(i).getWeight(), 3));  //EI20130107
				
				csgList.add(csgSubset);
			}
		}
		return csgList;
	}
}
