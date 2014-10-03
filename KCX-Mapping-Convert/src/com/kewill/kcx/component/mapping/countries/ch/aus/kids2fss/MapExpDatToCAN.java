/*
 * Function    : MapExpDatToCAN.java
 * Date        : 20.10.2008
 * Author      : Kewill CSF / krzoska
 * Description : Mapping of KIDS format of ExportDeclaration to CAN
 * Changes 
 * -----------
 * Author      : EI
 * Date        : 29.04.2009
 * Label       :
 * Description : MsgKids replaced with MsgExpDatCH
 *
 * Changes 
 * -----------
 * Author      : AK
 * Date        : 17.03.2010
 * Label       : AK20100317
 * Description : MsgExpDatCH replaced with MsgExpDat
 *               MsgExpDatPosCH  replaced with MsgExpDatPos 
 *
 * Changes 
 * -----------
 * Author      : AK
 * Date        : 22.03.2010
 * Label       : AK20100322
 * Description : setCai
 *                
 *
 * Changes 
 * -----------
 * Author      : AK 
 * Date        : 06.05.2010
 * Label       : AK20100506
 * Description : CAN_MWSTNR from Consignor instead of Forwarder   
 *                
 *
 * Changes 
 * -----------
 * Author      : 
 * Date        : 
 * Label       : 
 * Description : 
 *                

 *
 */

package com.kewill.kcx.component.mapping.countries.ch.aus.kids2fss;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.common.SpecialMention;
import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpDat;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpDatPos;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CommodityCode;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.NonCustomsLaw;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Notifications;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Permit;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Refinement;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.SensitiveGoods;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Statistic;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.messages.MsgCAN;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.messages.MsgCANPos;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCAD;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCAI;
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
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Function : MapExpDatToCAN.java Date : 20.10.2008 Author : Kewill CSF /
 * Description : Mapping of KIDS format of ExportDeclaration to CAN.
 * 
 * @author krzoska
 * @version 1.0.00
 * 
 */
public class MapExpDatToCAN extends KidsMessage {

	//AK20100317
	private MsgExpDat                msgExpDatCH;
	private MsgCAN                   msgCAN;

	public MapExpDatToCAN(XMLEventReader parser) throws XMLStreamException {
		msgExpDatCH = new MsgExpDat(parser);
		msgCAN = new MsgCAN("");
	}

	public MapExpDatToCAN(XMLEventReader parser, TsVOR tsvor)
			throws XMLStreamException {
		msgExpDatCH = new MsgExpDat(parser);
		msgCAN = new MsgCAN("");
		msgCAN.setVorSubset(tsvor);
	}

	public String getMessage() {
		String beznr;
		String posnr;
		String res = "";
		
		try {
			msgExpDatCH.parse(HeaderType.KIDS);
			beznr = msgExpDatCH.getReferenceNumber();
			getCommonFieldsDTO().setReferenceNumber(msgExpDatCH.getReferenceNumber());

			msgCAN.getVorSubset().setMsgid(getKidsHeader().getMessageID());
			msgCAN.setCanSubset(getCan()); 
			msgCAN.setCaiSubset(getCai());
			
			if (msgExpDatCH.getConsignor() != null) {
				msgCAN.setConsignor(getCad("1", msgExpDatCH.getConsignor().getAddress(), beznr));
			}
			if (msgExpDatCH.getForwarder() != null) {
				msgCAN.setForwarder(getCad("2", msgExpDatCH.getForwarder().getAddress(), beznr));
			}
			
			if (msgExpDatCH.getConsignee() != null) {
				msgCAN.setConsignee(getCad("3", msgExpDatCH.getConsignee().getAddress(), beznr));
			}
			
			if (msgExpDatCH.getConsignorSecurity() != null) {
				msgCAN.setConsignorSecurity(getCad("5", msgExpDatCH.getConsignorSecurity().getAddress(), beznr));
			}

			if (msgExpDatCH.getConsigneeSecurity() != null) {
				msgCAN.setConsigneeSecurity(getCad("6", msgExpDatCH.getConsigneeSecurity().getAddress(), beznr));
			}
			
			if (msgExpDatCH.getCarrier() != null) {
				msgCAN.setCarrier(getCad("9", msgExpDatCH.getCarrier()
						.getAddress(), beznr));
			}

			if (msgExpDatCH.getContainer() != null) {
				msgCAN.setCcnList(getCcnList(msgExpDatCH.getContainer().getNumberList(), beznr));
			}
			//AK20110414 not used in Export "I"
			//msgCAN.setCvpSubset(setCvp(msgExpDatCH.getPreviousDocumentList(), beznr));

			if (msgExpDatCH.getPreviousDocumentList() != null) {   //EI20110429
				msgCAN.setCviList(getCviList(msgExpDatCH.getPreviousDocumentList(), beznr));
			}
			
			if (msgExpDatCH.getSpecialMentionList() != null) {   //EI20110429
				msgCAN.setCvmList(getCvmList(msgExpDatCH.getSpecialMentionList(), beznr, "0"));
			}

			for (int i = 0; i < msgExpDatCH.getGoodsItemList().size(); i++) {			
				MsgExpDatPos item = msgExpDatCH.getGoodsItemList().get(i);
				posnr = item.getItemNumber();

				MsgCANPos msgCanPos = new MsgCANPos("");
				msgCanPos.setCpoSubset(getCpo(item, beznr));
				msgCanPos.setCpzSubset(getCpz(item, beznr, posnr));  //AK201104
				msgCanPos.setCclList(getCclList(item.getPackagesList(), beznr, posnr));
				msgCanPos.setCbwList(getCbwList(item.getPermitList(), beznr, posnr));
				msgCanPos.setCunList(getCunList(item.getDocumentList(), beznr, posnr));
				//EI20110429 CVA not used in Export: 
				//msgCanPos.setCvaList(getCvaList(item.getPreviousDocumentList(), beznr, posnr));
				msgCanPos.setCvmList(getCvmList(item.getSpecialMentionList(), beznr, posnr));  //EI20110429
				msgCanPos.setCmdList(getCmdList(item.getNotificationCodeList(), beznr, posnr));  //EI20110429 new
				msgCanPos.setCnzList(getCnzList(item.getNonCustomsLaw(), beznr, posnr));  //EI20110429 new
				msgCanPos.setCveSubset(getCve(item.getRefinement(), beznr, posnr));  //EI20110429 new
				msgCanPos.setCstList(getCstList(item.getDetailList(), beznr, posnr));  //EI20110429 new
				msgCanPos.setCsgList(getCsgList(item.getSensitiveGoodsList(), beznr, posnr)); //EI20110429 new
				
				msgCAN.addCanPosList(msgCanPos);
			}

			res = msgCAN.writeCAN("I");  //EI20110429: I==Export, A==Ausfuhr"A", D==Ausfuhr"D"

			Utils.log("(MapExpDatToCAN getMessage) Msg = " + res);

		} catch (FssException e) {
			e.printStackTrace();
		}

		return res;
	}
	
    //---------------------
	
	private TsCAN getCan() {
		TsCAN canSubset = new TsCAN();

		canSubset.setBeznr(msgExpDatCH.getReferenceNumber());
		canSubset.setDklart(msgExpDatCH.getKindOfDeclaration());
		//AK20110419 removed
		//canSubset.setCdstat(msgExpDatCH.getTypeOfPermitCH());
		//canSubset.setTstat(msgExpDatCH.getNCTSType());
		canSubset.setLdvs(msgExpDatCH.getDispatchCountry());
		canSubset.setLdbe(msgExpDatCH.getDestinationCountry());
		canSubset.setKzcont(msgExpDatCH.getTransportInContainer());
		canSubset.setGsroh(msgExpDatCH.getGrossMass());
		canSubset.setAufnr(msgExpDatCH.getOrderNumber());
		canSubset.setKorant(msgExpDatCH.getCorrectionCode());
		canSubset.setSammnr(msgExpDatCH.getBunchNumber());
		canSubset.setLang(msgExpDatCH.getLanguage());
		canSubset.setKzncts(msgExpDatCH.getTransferToTransitSystem());
//AK20110418 new		
		canSubset.setGsroh(msgExpDatCH.getGrossMass());
		
//AK20110418 MeansTransportOfBorder instead of MeansOfTransportDeparture 
//		if (msgExpDatCH.getTransportMeansDeparture() != null) {
//			canSubset.setBfabkz(msgExpDatCH.getTransportMeansDeparture()
//					.getTransportationNumber());
//			canSubset.setBfabld(msgExpDatCH.getTransportMeansDeparture()
//					.getTransportationCountry());
//		}
		if (msgExpDatCH.getTransportMeansBorder() != null) {
			canSubset.setBfabkz(msgExpDatCH.getTransportMeansBorder()
				.getTransportationNumber());
			canSubset.setBfabld(msgExpDatCH.getTransportMeansBorder()
				.getTransportationCountry());
			canSubset.setVzgr(msgExpDatCH.getTransportMeansBorder()
					.getTransportMode());
		}

//AK20110418 .getTransportMode()) moved to previous if	
//		if (msgExpDatCH.getTransportMeansBorder() != null) {
//			canSubset.setVzgr(msgExpDatCH.getTransportMeansBorder()
//					.getTransportMode());
//		}
//AK20110418 removed
//		if (msgExpDatCH.getPlaceOfLoading() != null) {
//			canSubset.setLadort(msgExpDatCH.getPlaceOfLoading()
//					.getCity());
//		}
		canSubset.setAbgdst(msgExpDatCH.getCustomsOfficeExport());
		//AK20110418 removed
		//canSubset.setAusdst(msgExpDatCH.getIntendedOfficeOfExit());
		//AK20100506
		//if (msgExpDatCH.getForwarder() != null) {
		//	canSubset.setMwstnr(msgExpDatCH.getForwarder().getVATNumber());
		//}
		if (msgExpDatCH.getConsignor() != null) {
			if (msgExpDatCH.getConsignor().getPartyTIN() != null) {
				canSubset.setKunrvs(msgExpDatCH.getConsignor().getPartyTIN()
						.getCustomerIdentifier());
			}
			//AK20100506
			canSubset.setMwstnr(msgExpDatCH.getConsignor().getVATNumber());
		}
		if (msgExpDatCH.getDeclarant() != null) {
			if (msgExpDatCH.getDeclarant().getPartyTIN() != null) {
				canSubset.setSpdnr(msgExpDatCH.getDeclarant().getPartyTIN()
						.getTIN());
			}
		}
		
		return canSubset;
	}
	
	//AK20100322
	private TsCAI getCai() {
		TsCAI caiSubset = new TsCAI();

		caiSubset.setBeznr(msgExpDatCH.getReferenceNumber());
		caiSubset.setDectim(msgExpDatCH.getDeclarationTime());
		caiSubset.setBesums(msgExpDatCH.getSituationCode());
		caiSubset.setUcr(msgExpDatCH.getUCROtherSystem());
		//AK20110418
		caiSubset.setKzsec(msgExpDatCH.getSecurityIndicator());
		caiSubset.setKzinj(msgExpDatCH.getInjunctionType());
		if (msgExpDatCH.getPlaceOfLoading() != null) {
			caiSubset.setWaort(msgExpDatCH.getPlaceOfLoading().getAgreedLocationOfGoods());			
		}

		if (msgExpDatCH.getPlaceOfLoading() != null) {
            caiSubset.setWaort(msgExpDatCH.getPlaceOfLoading().getAgreedLocationOfGoods());
		}
		
		caiSubset.setCdtype(msgExpDatCH.getStatusCode());
		caiSubset.setCdprvgrd(msgExpDatCH.getTemporaryReason());
		
		if (msgExpDatCH.getBusiness() != null) {
			caiSubset.setCdgsart(msgExpDatCH.getBusiness().getBusinessTypeCode());
		}
		
		//AK20110415
		//caiSubset.setLadort(msgExpDatCH.getPlaceofUnloading());
		if (msgExpDatCH.getPlaceOfLoading() != null) {
			caiSubset.setLadort(msgExpDatCH.getPlaceOfLoading().getCity());			
		}


		if (msgExpDatCH.getIncoTerms() != null) {
			caiSubset.setCdinco(msgExpDatCH.getIncoTerms().getIncoTerm());
		}

		caiSubset.setFnrstpf(msgExpDatCH.getCompanyNumberTaxPayer());
		
		caiSubset.setVerort(msgExpDatCH.getPlaceOfDeclaration());
		// EI20110513:
		//if (msgExpDatCH.getDeclarant() != null && msgExpDatCH.getDeclarant().getPartyTIN() != null) {
		//	caiSubset.setTinspd(msgExpDatCH.getDeclarant().getPartyTIN().getTIN());
		//}
		if (msgExpDatCH.getForwarder() != null && msgExpDatCH.getForwarder().getPartyTIN() != null) {
			caiSubset.setTinspd(msgExpDatCH.getForwarder().getPartyTIN().getTIN());
		}
		if (msgExpDatCH.getConsignor() != null && msgExpDatCH.getConsignor().getPartyTIN() != null) {
			caiSubset.setTinvs(msgExpDatCH.getConsignor().getPartyTIN().getTIN());
		}
		
		if (msgExpDatCH.getConsignee() != null && msgExpDatCH.getConsignee().getPartyTIN() != null) {
			caiSubset.setTinem(msgExpDatCH.getConsignee().getPartyTIN().getTIN());
		}
		//AK20110419
		if (msgExpDatCH.getConsignorSecurityTIN() != null) {
			caiSubset.setTinvsc(msgExpDatCH.getConsignorSecurityTIN().getTIN());
		}
		if (msgExpDatCH.getConsigneeSecurityTIN() != null) {
			caiSubset.setTinvsc(msgExpDatCH.getConsigneeSecurityTIN().getTIN());
		}
		if (msgExpDatCH.getCarrierTIN() != null) {
			caiSubset.setTintra(msgExpDatCH.getCarrierTIN().getTIN());
		}
		if (msgExpDatCH.getForwarderTIN() != null) {
			caiSubset.setTintra(msgExpDatCH.getForwarderTIN().getTIN());
		}

		//AK20110415 
		caiSubset.setDknr(msgExpDatCH.getDeclarantNumber());
		
		return caiSubset;
	}
	

	public TsCAD getCad(String type, Address address, String abeznr) {
		if (address == null) {
			return null;
		}

		TsCAD cadSubset = new TsCAD();

		cadSubset.setBeznr(abeznr);
		cadSubset.setTyp(type);
		cadSubset.setName(address.getName());
		cadSubset.setStr(address.getStreet());
		cadSubset.setLand(address.getCountry());
		cadSubset.setPlz(address.getPostalCode());
		cadSubset.setOrt(address.getCity());

		return cadSubset;
	}

	
	private List<TsCCN> getCcnList(List<String> list, String beznr) {		
		                         //list == msgExpDatCH.getContainer().getNumberList()		
		if (list == null) {
			return null;
		}
		List<TsCCN>	ccnList = new Vector<TsCCN>();
		
		// TsCCN ccnSubset = new TsCCN(); //!!!GEFÄHRICH,  soll in der schleife erfolgen
		TsCCN ccnSubset = null;

		int size = list.size();
		int c = 0;
		int n = 0;
		for (int i = 0; i < size; i++) {
			String number = msgExpDatCH.getContainer().getNumberList().get(i);
			c = i - 9 * n;

			switch (c) {
			case 0:
				ccnSubset = new TsCCN();
				ccnSubset.setBeznr(beznr);
				ccnSubset.setConnr1(number);  
				ccnList.add(ccnSubset);  //EI20110429
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
				n = n + 1;
				break;
			default:
				break;
			}
			
			/* EI20110429
			if (c == 8 || i == size - 1) {
				ccnList.add(ccnSubset);
			}
            */
		}
		return ccnList;
	}

	/* AK20110414
	 * private TsCVP setCvp(List<PreviousDocument> list, String beznr) {
		if (list == null || Utils.isStringEmpty(beznr)) {
			return null;
		}
		TsCVP cvpSubset = null;

		int size = msgExpDatCH.getPreviousDocumentList().size();
		if (size > 0) {
			cvpSubset = new TsCVP();
			cvpSubset.setBeznr(beznr);

			for (int i = 0; i < size; i++) {
				String marks = msgExpDatCH.getPreviousDocumentList().get(i)
						.getMarks();
				switch (i) {
				case 0:
					cvpSubset.setVpnr1(marks);
					break;
				case 1:
					cvpSubset.setVpnr2(marks);
					break;
				case 2:
					cvpSubset.setVpnr3(marks);
					break;
				case 3:
					cvpSubset.setVpnr4(marks);
					break;
				case 4:
					cvpSubset.setVpnr5(marks);
					break;
				case 5:
					cvpSubset.setVpnr6(marks);
					break;
				case 6:
					cvpSubset.setVpnr7(marks);
					break;
				case 7:
					cvpSubset.setVpnr8(marks);
					break;
				case 8:
					cvpSubset.setVpnr9(marks);
					break;
				default:
					break;
				}
			}
		}
		return cvpSubset;
	}
*/
	
	private List<TsCVI> getCviList(List<PreviousDocument> list, String beznr) {		
        //list == msgExpDatCH.getPreviousDocumentList()		
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
				cviSubset.setVpref(list.get(i).getMarks());
				cviSubset.setVpinf(list.get(i).getAdditionalInformation());
				cviSubset.setVpart(list.get(i).getType());
				cviList.add(cviSubset);
			}
		}
		return cviList;
	}
	private List<TsCVM> getCvmList(List<SpecialMention> list, String beznr, String posnr) {		
	       //list == msgExpDatCH.getSpecialMentionList()		
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
	
	private TsCPO getCpo(MsgExpDatPos msgExpDatPosCH, String beznr) {
		TsCPO cpoSubset = new TsCPO();

		cpoSubset.setBeznr(beznr);
		cpoSubset.setPosnr(msgExpDatPosCH.getItemNumber());
		cpoSubset.setWabes(msgExpDatPosCH.getDescription());
//		AK20110420 neues Mapping in setWabes 		
//		cpoSubset.setVerm(msgExpDatPosCH.getAnnotation());
		cpoSubset.setRohm(msgExpDatPosCH.getGrossMass());
		cpoSubset.setEigm(msgExpDatPosCH.getNetMass());
		cpoSubset.setCdhw(msgExpDatPosCH.getKindOfArticle());
		cpoSubset.setCdabf(msgExpDatPosCH.getTypeOfArticle());
		//AK20110419 removed
		//cpoSubset.setCdabfa(msgExpDatPosCH.getTypeOfDeclaration());

		CommodityCode cc = msgExpDatPosCH.getCommodityCode();
		if (cc != null) {
			cpoSubset.setTnr(cc.getTarifCode());
			cpoSubset.setTnrsch(cc.getAddition());
		}

		Statistic st = msgExpDatPosCH.getStatistic();
		if (st != null) {
			cpoSubset.setZusm(st.getAdditionalUnit());
			cpoSubset.setStwt(st.getStatisticalValue());
		}

		//AK20110420 neues mapping		
//		SensitiveGoods sg = msgExpDatPosCH.getSensitiveGoods();
//		if (sg != null) {
//			cpoSubset.setSgicd(sg.getType());
//			cpoSubset.setSgimng(sg.getWeight());
//		}
		cpoSubset.setSgicd(msgExpDatPosCH.getRefundType());
		cpoSubset.setSgimng(msgExpDatPosCH.getRefundQuantity());
		

		if (msgExpDatPosCH.getNonCustomsLaw() != null && 
				msgExpDatPosCH.getNonCustomsLaw().getNonCustomsLawType() != null) {

			//ME20100422 NonCustomsLaw kann bis zu 9 mal vorkommen in der fss aber nur 1 mal
//			cpoSubset.setCdnzpf(msgExpDatPosCH.getNonCustomsLaw()
//					.getNonCustomsLawType().get(0));
			//AK20110420 cdnzpf neu mapping
			cpoSubset.setCdnzpf(msgExpDatPosCH.getNonCustomsLaw().getObligation());
		}
		
		// SpecialMention sp = msgExpDatPosCH.getSpecialMentionList().get(0);
		// Abfrage auf null eingebaut CK 15.04.2010
		
		List <SpecialMention> l = msgExpDatPosCH.getSpecialMentionList();
		if (l != null) {
			SpecialMention sp = l.get(0);
			if (sp != null) {
				cpoSubset.setZusang(sp.getTypeOfExport());
				cpoSubset.setCdexeu(sp.getExportFromEU());
				cpoSubset.setCdexld(sp.getExportFromCountry());
			}
		}

		Notifications no = msgExpDatPosCH.getNotifications();
		if (no != null) {
			cpoSubset.setCdmeld(no.getNotificationCode());
		}
		
		//AK20110420
		cpoSubset.setCdungf(msgExpDatPosCH.getDangerousGoodsNumber());

		return cpoSubset;
	}

	public List<TsCCL> getCclList(List<Packages> list, String beznr,
			String posnr) {
		if (list == null) {
			return null;
		}

		int size = list.size();
		List<TsCCL> cclList = new Vector<TsCCL>();

		for (int i = 0; i < size; i++) {
			TsCCL cclSubset = new TsCCL();
			Packages packages = list.get(i);

			cclSubset.setBeznr(beznr);
			cclSubset.setPosnr(posnr);
			cclSubset.setColanz(packages.getQuantity());
			cclSubset.setColart(packages.getType());
			cclSubset.setColzch(packages.getMarks());
			cclSubset.setColstk(packages.getQuantityCH());

			cclList.add(cclSubset);
		}
		return cclList;
	}

	public List<TsCBW> getCbwList(List<Permit> list, String beznr, String posnr) {
		if (list == null) {
			return null;
		}

		int size = list.size();
		List<TsCBW> cbwList = new Vector<TsCBW>();

		for (int i = 0; i < size; i++) {
			TsCBW cbwSubset = new TsCBW();
			Permit permit = list.get(i);

			cbwSubset.setBeznr(beznr);
			cbwSubset.setPosnr(posnr);
			cbwSubset.setBwstcd(permit.getPermitAuthority());
			cbwSubset.setBwnr(permit.getPermitNumber());
			cbwSubset.setBwdat(permit.getIssueDate());
			cbwSubset.setBwverm(permit.getAdditionalInformation());

			cbwList.add(cbwSubset);
		}

		return cbwList;
	}

	public List<TsCUN> getCunList(List<Document> list, String beznr,
			String posnr) {
		if (list == null) {
			return null;
		}

		int size = list.size();
		List<TsCUN> cunList = new Vector<TsCUN>();

		for (int i = 0; i < size; i++) {
			TsCUN cunSubset = new TsCUN();
			Document doc = list.get(i);

			cunSubset.setBeznr(beznr);
			cunSubset.setPosnr(posnr);
			cunSubset.setUntart(doc.getTypeKids()); // EI20090409
			cunSubset.setUntref(doc.getReference());
			cunSubset.setUntzus(doc.getAdditionalInformation());

			cunList.add(cunSubset);
		}

		return cunList;
	}

	public List<TsCVA> getCvaList(List<PreviousDocument> list, String beznr,
			String posnr) {
		if (list == null) {
			return null;
		}

		int size = list.size();
		List<TsCVA> cvaList = new Vector<TsCVA>();

		for (int i = 0; i < size; i++) {
			TsCVA cvaSubset = new TsCVA();
			PreviousDocument prevDoc = list.get(i);

			cvaSubset.setBeznr(beznr);
			cvaSubset.setPosnr(posnr);
			cvaSubset.setVpart(prevDoc.getType());
			cvaSubset.setVpnr(prevDoc.getMarks());
			cvaSubset.setVpzus(prevDoc.getAdditionalInformation());

			cvaList.add(cvaSubset);
		}

		return cvaList;
	}

	private TsCPZ getCpz(MsgExpDatPos msgExpDatPosCH, String beznr, String posnr) {
		TsCPZ cpzSubset = new TsCPZ();
		
		cpzSubset.setBeznr(beznr);
		cpzSubset.setPosnr(posnr);
		cpzSubset.setCdbwpf(msgExpDatPosCH.getPermitObligation());
		cpzSubset.setRohmri(msgExpDatPosCH.getGrossMassConfirmation());
		cpzSubset.setEigmri(msgExpDatPosCH.getNetMassConfirmation());
		
		if (msgExpDatPosCH.getStatistic() != null) {
			cpzSubset.setZusmri(msgExpDatPosCH.getStatistic().getAdditionalUnitConfirmation());
			cpzSubset.setStwtri(msgExpDatPosCH.getStatistic().getStatisticalValueConfirmation());
		}
		
		if (msgExpDatPosCH.getCommodityCode() != null) {
			cpzSubset.setTnrri(msgExpDatPosCH.getCommodityCode().getConfirmation());
		}
		return cpzSubset;
	}
		
	private TsCVE getCve(Refinement refinement, String beznr, String posnr) {	
		if (refinement == null || refinement.isEmpty()) {
			return null;
		}
		
		TsCVE cveSubset = new TsCVE();
		
		cveSubset.setRefnr(beznr);
		cveSubset.setPosnr(posnr);
		cveSubset.setRrdir(refinement.getDirection());
		cveSubset.setRretyp(refinement.getRefinementType());
		cveSubset.setRrvtyp(refinement.getProcessType());
		cveSubset.setRratyp(refinement.getBillingType());
		cveSubset.setRrkzfp(refinement.getTemporaryAdmission());
		cveSubset.setRrptyp(refinement.getPositionType());
		
		return cveSubset;
	}
	
	private List<TsCMD> getCmdList(List<String> list, String beznr, String posnr) {		
	       //list == msgExpDatCH.getNotificationCodeList()		
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
		
	private List<TsCNZ> getCnzList(NonCustomsLaw argument, String beznr, String posnr) {			       
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
	private List<TsCST> getCstList(List<Text> list, String beznr, String posnr) {		
	       //list == msgExpDatCH.getDetailList()		
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
	private List<TsCSG> getCsgList(List<SensitiveGoods> list, String beznr, String posnr) {		
	       //list == msgExpDatCHPos.getSensitiveGoodsList()		
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
				csgSubset.setGew(list.get(i).getWeight());
				csgList.add(csgSubset);
			}
		}
		return csgList;
	}
}
