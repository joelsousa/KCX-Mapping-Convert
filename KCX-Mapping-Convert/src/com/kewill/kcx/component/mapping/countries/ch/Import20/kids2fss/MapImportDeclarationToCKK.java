package com.kewill.kcx.component.mapping.countries.ch.Import20.kids2fss;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.ch.aus20.common.Infos;
import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.SpecialMention;
import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.MsgImportDeclaration;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.AdditionalCosts;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Deferment;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Excise;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.GoodsItemDeclaration;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.ImportPackage;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.SpecialStatement;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Traders;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.NonCustomsLaw;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Permit;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Refinement;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.SensitiveGoods;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpDat;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.messages.V70.MsgCKK;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.messages.V70.MsgCKKPos;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.TsCPA;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.TsCPB;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.TsCPC;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.TsCPD;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.TsCPE;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.TsCPF;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.TsCPN;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.TsCPR;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.TsCPS;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.TsCPU;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.TsCPV;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.V70.TsCKA;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.V70.TsCKC;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.V70.TsCKK;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.V70.TsCKR;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.V70.TsCKV;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCMD;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCNZ;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCSG;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCST;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus20.messages.MsgCAN;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: Import CH 20<br>
 * Created		: 21.11.2012<br>
 * Description	: Mapping of KIDS-ExportDeclaration FSS-CKK.
 * 
 * @author iwaniuk
 * @version 2.0.00
 * 
 */
public class MapImportDeclarationToCKK extends KidsMessage {

	// AK20100317
	private MsgImportDeclaration message;
	private MsgCKK msgCKK;

	public MapImportDeclarationToCKK(XMLEventReader parser) throws XMLStreamException {
		message = new MsgImportDeclaration(parser);
		msgCKK = new MsgCKK("");
	}

	public MapImportDeclarationToCKK(XMLEventReader parser, TsVOR tsvor)
			throws XMLStreamException {
		message = new MsgImportDeclaration(parser);
		msgCKK = new MsgCKK("");
		msgCKK.setVorSubset(tsvor);
	}
	public MapImportDeclarationToCKK(XMLEventReader parser, TsVOR tsvor, TsHead head) throws XMLStreamException {
		message = new MsgImportDeclaration(parser);
		msgCKK = new MsgCKK("");
		msgCKK.setVorSubset(tsvor);
		msgCKK.setHeadSubset(head);
	}

	public String getMessage() {
		String posnr;
		String res = "";
		String refnr;

		try {
			message.parse(HeaderType.KIDS);
			refnr = message.getReferenceNumber();
			getCommonFieldsDTO().setReferenceNumber(
					message.getReferenceNumber());

			msgCKK.getVorSubset().setMsgid(getKidsHeader().getMessageID());
			msgCKK.setCkkSubset(mapCKK());
			this.mapCKAs(message.getTraders(), refnr);

			if (message.getContainer() != null && message.getContainer().getNumberList() != null) {
				msgCKK.setCkcList(this.mapCKCList(message.getContainer()
						.getNumberList(), refnr));
			}			
			if (message.getPreviousDocument() != null) { 
				msgCKK.addCkvList(this.mapCKV(message.getPreviousDocument(), refnr));
			}

			if (message.getAdditionalInformation() != null) { 
				msgCKK.addCkrList(mapCKR(message.getAdditionalInformation(), refnr));
			}

			if (message.getGoodsItemList() != null) {
				for (GoodsItemDeclaration item : message.getGoodsItemList()) {				
					posnr = item.getItemNumber();

					MsgCKKPos msgCkkPos = new MsgCKKPos("");
					msgCkkPos.setCpsSubset(mapCPS(item, refnr, posnr));
					msgCkkPos.setCpcSubset(mapCPC(item, refnr, posnr)); 					
					msgCkkPos.setCpvList(mapCPVList(item.getImportPackage(), refnr, posnr));
				    msgCkkPos.setCpuList(mapCPUList(item.getDocumentList(), refnr, posnr));
				    msgCkkPos.setCpbList(mapCPBList(item.getPermit(), refnr, posnr));				
				    msgCkkPos.setCpnList(mapCPNList(item.getNonCustomsLaw(), refnr, posnr));				    				   				    
				    //msgCkkPos.setCpmList(mapCPMList(item.get???(),refnr, posnr)); 
				    msgCkkPos.setCprList(mapCPRList(item.getSpecialMentionList(), refnr, posnr)); 
				    msgCkkPos.setCpfSubset(mapCPFList(item.getAdditionsDeductionsList(), refnr, posnr)); 				   
				    msgCkkPos.setCpaList(mapCPAList(item.getExciseList(), refnr, posnr));
				    msgCkkPos.setCpeSubset(mapCPESubset(item.getRefinement(), refnr, posnr)); 
				    msgCkkPos.setCpdList(mapCPDList(item.getSpecialValueStatementList(), refnr, posnr)); 

				    msgCKK.addCkkPosList(msgCkkPos);
				}
			}

			//res = msgCKK.getFssString("");
			if (this.writeHead()) { 				//EI20130213
            	res = msgCKK.getFssString("HEAD");
            } else {
            	res = msgCKK.getFssString();
            }  

			Utils.log("(MapExpDatToCAN getMessage) Msg = " + res);

		} catch (FssException e) {
			e.printStackTrace();
		}

		return res;
	}

	// ---------------------

	private TsCKK mapCKK() {
		TsCKK ckkSubset = new TsCKK();

		ckkSubset.setRefnr(message.getReferenceNumber());
		ckkSubset.setKorant("0");
		ckkSubset.setKorrcd("1");
		ckkSubset.setSvtype("1");
		ckkSubset.setVersion("0");
		ckkSubset.setLang(message.getLanguageCode()); 
		if (message.getTraders() != null) {
			if (message.getTraders().getDeclarantTIN() != null) {
				ckkSubset.setAbspdnr(message.getTraders().getDeclarantTIN()
						.getTIN());
				ckkSubset.setImkdnr(message.getTraders().getDeclarantTIN()
						.getCustomerId());
			}
			if (message.getTraders().getConsignorTIN() != null) {
				ckkSubset.setVekdnr(message.getTraders().getConsignorTIN()
						.getCustomerId());
			}
			if (message.getTraders().getConsigneeTIN() != null) {
				ckkSubset.setEmkdnr(message.getTraders().getConsigneeTIN()
						.getCustomerId());
			}
			if (message.getTraders().getRepresentativeTIN() != null) {
				ckkSubset.setDknr(message.getTraders().getRepresentativeTIN()
						.getTIN()); // TODO ???
			}
		}
		ckkSubset.setAufnr(message.getCustomerOrderNumber());
		ckkSubset.setSamsdnr(message.getBunchNumber()); 
		ckkSubset.setLoc(message.getPlaceOfDeclaration());
		ckkSubset.setCdstus(message.getDeclarationPriorPresentation());
		ckkSubset.setCdtype(message.getReason()); 
		ckkSubset.setDstnr(message.getCustomsOfficeEntry());
		/*if (message.getDispatchCountry() != null) { 
			ckkSubset.setLde(message.getDispatch().getCode());
			ckkSubset.setLderc(message.getDispatch().getConfirmation());
		}*/
		ckkSubset.setLde(message.getDispatchCountry());
		ckkSubset.setLderc(message.getDispatchCountryConfirmation());
		//ckkSubset.setGscolli
		//ckkSubset.setGsrohm(message.getGrossMass());
		ckkSubset.setGsrohm(Utils.addZabisDecimalPlaceV7(message.getGrossMass(), 1));   //EI20130107
		// ckkSubset.setGsrohmk
		if (message.getInvoice() != null) {
			ckkSubset.setGswert(message.getInvoice().getAmount());

			String curr = message.getInvoice().getCurrency();
			if (!Utils.isStringEmpty(curr)) {
				if (curr.equalsIgnoreCase("CHF")) {
					ckkSubset.setWhcod("1");
				} else if (curr.equalsIgnoreCase("EUR")) {
					ckkSubset.setWhcod("2");
					// } else if (this.isEuropaCurrency(curr)) {
					// ckkSubset.setWhcod("3");
				} else if (curr.equalsIgnoreCase("USD")) {
					ckkSubset.setWhcod("4");
				} else {
					ckkSubset.setWhcod("5");
				}
			}
		}
		if (message.getMeansOfTransportBorder() != null) {
			ckkSubset.setCdvz(message.getMeansOfTransportBorder()
					.getTransportMode());
			ckkSubset.setCdbefart(message.getMeansOfTransportBorder()
					.getTransportationType());
			ckkSubset.setTrspm(message.getMeansOfTransportBorder()
					.getTransportationNumber());
			ckkSubset.setLdim(message.getMeansOfTransportBorder()
					.getTransportationCountry());
		}
		if (message.getDefermentList() != null) {
			for (Deferment def : message.getDefermentList()) {
				if (def != null && def.getType() != null) {
					if (def.getType().equals("1")) {
						ckkSubset.setKtomw(def.getCustomerId());
					} else if (def.getType().equals("2")) {
						ckkSubset.setMwstnr(def.getCustomerId());
					} else if (def.getType().equals("3")) {
						ckkSubset.setKtonr(def.getCustomerId());
					}
				}
			}
		}
		ckkSubset.setKzcont(message.getTransportInContainer());
		ckkSubset.setAblort(message.getGoodsLocation());
		ckkSubset.setAzvvz(message.getInjunctionType());
		if (message.getAdministrativeInfosList() != null) {
			for (Infos info : message.getAdministrativeInfosList()) {
				if (info != null && !Utils.isStringEmpty(info.getType())) {
					if (info.getType().equals("1")) {
						ckkSubset.setDklhin(info.getText());
					} else if (info.getType().equals("2")) {
						ckkSubset.setVehin(info.getText());
					} else if (info.getType().equals("3")) {
						ckkSubset.setImhin(info.getText());
					} else if (info.getType().equals("4")) {
						ckkSubset.setEmhin(info.getText());
					}
				}
			}
		}
		if (message.getContactPerson() != null) {
			ckkSubset.setSbnr(message.getContactPerson().getIdentity());
		}
		if (message.getIncoterms() != null) {
			ckkSubset.setCdinco(message.getIncoterms().getCode());
		}

		return ckkSubset;
	}

	private void mapCKAs(Traders traders, String refnr) {
		if (traders == null) {
			return;
		}
		if (traders.getConsignorAddress() != null) {
			msgCKK.setConsignor(mapCKA("1", traders.getConsignorAddress(),
					refnr));
		}
		if (traders.getConsigneeAddress() != null) {
			msgCKK.setConsignee(mapCKA("2", traders.getConsigneeAddress(),
					refnr));
		}
		if (traders.getDeclarantAddress() != null) {
			msgCKK.setDeclarant(mapCKA("3", traders.getDeclarantAddress(),
					refnr));
		}
		if (traders.getRepresentativeAddress() != null) {
			msgCKK.setRepresentative(mapCKA("4", traders
					.getRepresentativeAddress(), refnr));
		}
	}

	private TsCKA mapCKA(String type, Address address, String refnr) {
		if (address == null) {
			return null;
		}
		TsCKA cka = new TsCKA(type);
		cka.setRefnr(refnr);
		cka.setTyp(type);
		cka.setName(address.getName()); // name2, name3
		cka.setStr(address.getStreet());
		cka.setOrt(address.getCity());
		cka.setLand(address.getCountry());
		// cka.setRef(); <== z.Z. leer
		cka.setPlz(address.getPostalCode());
		if (address.getName() != null && address.getName().length() > 35) {
			cka.setName(address.getName().substring(0, 35));
			cka.setName1(address.getName().substring(35));
		}
		if (address.getName() != null && address.getName().length() > 70) {
			cka.setName(address.getName().substring(0, 35));
			cka.setName1(address.getName().substring(35, 70));
			cka.setName2(address.getName().substring(70));
		}

		return cka;
	}

	private List<TsCKC> mapCKCList(List<String> list, String refnr) {		
		if (list == null) {
			return null;
		}
		List<TsCKC> ckcList = new Vector<TsCKC>();
		
		for (String nr : list) {
			TsCKC ckc = new TsCKC();
			ckc.setRefnr(refnr);
			ckc.setCtnr(nr);			
			ckcList.add(ckc);
		}
		return ckcList;
	}

	private TsCKV mapCKV(Document doc, String refnr) {
		if (doc == null) {
			return null;
		}
		TsCKV ckv = new TsCKV();
		ckv.setRefnr(refnr);
		ckv.setVoart(doc.getType());
		ckv.setVornr(doc.getNumber());
		ckv.setVorzus(doc.getAdditionalInformation());		
		
		return ckv;
	}

	private TsCKR mapCKR(String vermerk, String refnr) {
		if (Utils.isStringEmpty(vermerk)) {
			return null;
		}
		TsCKR ckr = new TsCKR();
		ckr.setRefnr(refnr);
		ckr.setVm(vermerk);
		
		return ckr;
	}
		

	private TsCPS mapCPS(GoodsItemDeclaration item, String refnr, String posnr) {
		TsCPS cps = new TsCPS();

		cps.setRefnr(refnr);
		cps.setPosnr(posnr);
		cps.setArtnr(item.getEAN());           	
		if (item.getCommodityCode() != null) {
			cps.setTnr(item.getCommodityCode().getTarifCode());
			cps.setTnrsch(item.getCommodityCode().getAddition());
		}
		if (item.getCountrySpecificValues() != null && item.getCountrySpecificValues().getCh() != null) {
			cps.setCdhw(item.getCountrySpecificValues().getCh().getKindOfArticle()); 			
			cps.setNttgew(Utils.addZabisDecimalPlaceV7(item.getCountrySpecificValues().getCh().getCustomsNetWeigth(), 3));  //EI20130107
			cps.setKznett(item.getCountrySpecificValues().getCh().getNetDuty());			
			cps.setTazu(Utils.addZabisDecimalPlaceV7(item.getCountrySpecificValues().getCh().getTaraAddition(), 1));        //EI20130107			
			cps.setZolans(Utils.addZabisDecimalPlaceV7(item.getCountrySpecificValues().getCh().getNonRegularDutyRate(), 2)); //EI20130107
			cps.setCdmwst(item.getCountrySpecificValues().getCh().getVATCode());
			
		}
		cps.setCdabf(item.getProcedureCode());
		//cps.setKzunvp("N/A");                  	
		cps.setWabesch(item.getDescription());		
		//cps.setRohm(item.getGrossMass());   
		cps.setRohm(Utils.addZabisDecimalPlaceV7(item.getGrossMass(), 1));   //EI20130107
		//cps.setEigm(item.getNetMass());		
		cps.setEigm(Utils.addZabisDecimalPlaceV7(item.getNetMass(), 1));   //EI20130107
		if (item.getStatistic() != null) {
			//cps.setStzusm(item.getStatistic().getQuantity());
			cps.setStzusm(Utils.addZabisDecimalPlaceV7(item.getStatistic().getQuantity(), 1));  //EI20130107
		}
		if (item.getWahrehouse() != null) {   
			cps.setCdlag(item.getWahrehouse().getWarehouseType());
		}
		//cps.setTbkart("N/A");                 
		if (item.getPermit() != null) {
			cps.setCdbwpf(item.getPermit().getPermitObligation());
		}		
		if (item.getNonCustomsLaw() != null) {	//TODO falsch in doc		
			cps.setCdnzpf(item.getNonCustomsLaw().getObligation());
		}
		if (item.getStatistic() != null) {
			cps.setStwert(item.getStatistic().getStatisticalValue());			
		}
		cps.setMswert(item.getVATValue());
		cps.setUrld(item.getCountryOfOrigin());
		cps.setCdprf(item.getRequestedPrivilege());
		//cps.setItembz(item);
		//cps.setItemwt(item);
		cps.setCdbeg(item.getDutyControlCode());
		//cps.setCdgebw(item);
		//cps.setNtrate(item);
		//cps.setInjtyp(item);
		
		return cps;
	}

	private TsCPC mapCPC(GoodsItemDeclaration item, String refnr, String posnr) {
		if (item == null) {
			return null;
		}
		TsCPC cpc = new TsCPC();

		cpc.setBeznr(refnr);
		cpc.setPosnr(posnr);		
		
		if (item.getCountrySpecificValues() != null && item.getCountrySpecificValues().getCh() != null) {
			if (item.getCountrySpecificValues().getCh().getConformationCodes() != null) {
			cpc.setTnrrc(item.getCountrySpecificValues().getCh().getConformationCodes().getTarifCodeConfirmation()); 
			cpc.setRohmrc(item.getCountrySpecificValues().getCh().getConformationCodes().getGrossMassConfirmation());   
			cpc.setEigmrc(item.getCountrySpecificValues().getCh().getConformationCodes().getNetMassConfirmation());
			cpc.setStzusmrc(item.getCountrySpecificValues().getCh().getConformationCodes().getQuantityConfirmation());
			cpc.setStwertrc(item.getCountrySpecificValues().getCh().getConformationCodes().getStatisticalValueConfirmation());
			cpc.setMswertrc(item.getCountrySpecificValues().getCh().getConformationCodes().getCustomsValueConfirmation());
			cpc.setTazurc(item.getCountrySpecificValues().getCh().getConformationCodes().getTaraSupplementConfirmation());
			cpc.setZolansrc(item.getCountrySpecificValues().getCh().getConformationCodes().getNonRegularCustomsRateConfirmation());
			cpc.setCdmwstrc(item.getCountrySpecificValues().getCh().getConformationCodes().getVATCodeConfirmation());			
			}	
		}
		return cpc;
	}
	
	private List<TsCPV> mapCPVList(ImportPackage pack, String refnr, String posnr) {
		if (pack == null) {
			return null;
		}
		TsCPV cpv = new TsCPV();
		List<TsCPV> list = new Vector<TsCPV>();

		cpv.setRefnr(refnr);
		cpv.setPosnr(posnr);
		cpv.setVpart(pack.getType());
		cpv.setVpanz(pack.getQuantity());
		cpv.setVpzch(pack.getMarks());
		
		list.add(cpv);
		return list;
	}
	
		
	public List<TsCPU> mapCPUList(List<Document> list, String refnr, String posnr) {
		if (list == null) {
			return null;
		}
		List<TsCPU> cpuList = new Vector<TsCPU>();

		for (Document docu : list) {
			TsCPU cpu = new TsCPU();			
			cpu.setRefnr(refnr);
			cpu.setPosnr(posnr);
			cpu.setUntart(docu.getType());
			cpu.setUntnr(docu.getNumber());
			cpu.setUntdat(docu.getIssueDate());
			cpu.setUntzus(docu.getAdditionalInformation());

			cpuList.add(cpu);
		}
		return cpuList;
	}
	
	//public List<TsCPB> getCbwList(List<Permit> list, String refnr, String posnr) {
	public List<TsCPB> mapCPBList(Permit permit, String refnr, String posnr) {
		//if (list == null) {
		if (permit == null) {
			return null;
		}
		List<TsCPB> cpbList = new Vector<TsCPB>();

		//for (Permit permit : list) {
			TsCPB cpb = new TsCPB();
			
			cpb.setRefnr(refnr);
			cpb.setPosnr(posnr);
			cpb.setBwtyp(permit.getType());
			cpb.setBwstel(permit.getPermitAuthority());
			cpb.setBwttyp(permit.getTobaccoPermitType());
			cpb.setBwnr(permit.getPermitNumber());
			cpb.setBwdat(permit.getIssueDate());
			cpb.setBwzus(permit.getAdditionalInformation());

			cpbList.add(cpb);
		//}
		return cpbList;
	}

	public List<TsCPN> mapCPNList(NonCustomsLaw non, String refnr, String posnr) {
		if (non == null) {
			return null;
		}
		if (non.getNonCustomsLawTypeList() == null) {   
			return null;
		}
		List<TsCPN> cpnList = new Vector<TsCPN>();

		for (String type : non.getNonCustomsLawTypeList()) {   //TODO: falsch in doc
			TsCPN cpn = new TsCPN();			

			cpn.setRefnr(refnr);
			cpn.setPosnr(posnr);
			cpn.setCdnz(type); 			
			cpnList.add(cpn);
		}

		return cpnList;
	}
	
   /*for KIDS not defined
	public List<TsCPM> mapCPMList(???, String refnr, String posnr) {
		if (??? == null) {
			return null;
		}		
		List<TsCPM> cpmList = new Vector<TsCPM>();

		for (??? : ???)) {
			TsCPM cpm = new TsCPM();			

			cpm.setRefnr(refnr);
			cpm.setPosnr(posnr);
			cpm.setMldstl(???); 			
			cpmList.add(cpm);
		}
		
		return cpmList;
	}
	*/
	
	public List<TsCPR> mapCPRList(List<SpecialMention> list, String refnr, String posnr) {
		if (list == null) {
			return null;       //TODO: falsch in doc: Text oder ist AdditionalInformation?
		}		
		List<TsCPR> cprList = new Vector<TsCPR>();

		for (SpecialMention mention : list) {
			TsCPR cpr = new TsCPR();			
            if (mention != null && !Utils.isStringEmpty(mention.getText())) {
            	cpr.setRefnr(refnr);
            	cpr.setPosnr(posnr);
            	cpr.setVm(mention.getText()); 			
            	cprList.add(cpr);
            }
		}
		
		return cprList;
	}
	
	public TsCPF mapCPFList(List<AdditionalCosts> list, String refnr, String posnr) {
		if (list == null) {
			return null;                 
		}
		TsCPF cpf = new TsCPF();	

		for (AdditionalCosts costs : list) {
						
			cpf.setRefnr(refnr);
			cpf.setPosnr(posnr);
			//TODO: was soll in KIDS fuer Type stehen???			
			if (costs.getType() != null && Utils.isStringEmpty(costs.getType())) {
				if (costs.getType().equalsIgnoreCase("NetPrice")) {   //TODO: was soll hier fuer Fracht stehen???				
					cpf.setNpwg(costs.getCurrency());
					if (costs.getCurrency() != null && costs.getCurrency().equals("CHF")) {					
						//cpf.setNpchf(costs.getAmount()); 
						cpf.setNpchf(Utils.addZabisDecimalPlaceV7(costs.getAmount(), 2));  //EI20130107
					} else {
						//cpf.setNpbt(costs.getAmount()); 
						cpf.setNpbt(Utils.addZabisDecimalPlaceV7(costs.getAmount(), 2)); //EI20130107
					}
				} else if (costs.getType().equalsIgnoreCase("Frachtkosten")) {//TODO: 
					cpf.setFkwg(costs.getCurrency());
					//cpf.setFkbt(costs.getAmount()); 
					cpf.setFkbt(Utils.addZabisDecimalPlaceV7(costs.getAmount(), 2));   //EI20130107
					cpf.setFkprz(costs.getPercentageFreightCost());
				} else if (costs.getType().equalsIgnoreCase("Versicherung")) {//TODO: 
					cpf.setVswg(costs.getCurrency());
					if (costs.getCurrency() != null && costs.getCurrency().equals("CHF")) {					
						//cpf.setVschf(costs.getAmount()); 
						cpf.setVschf(Utils.addZabisDecimalPlaceV7(costs.getAmount(), 2));   //EI20130107
					} else {
						//cpf.setVsbt(costs.getAmount()); 	
						cpf.setVsbt(Utils.addZabisDecimalPlaceV7(costs.getAmount(), 2));   //EI20130107
					}
				} else if (costs.getType().equalsIgnoreCase("StatistischerWert")) {//TODO: 
					cpf.setKswg(costs.getCurrency());
					if (costs.getCurrency() != null && costs.getCurrency().equals("CHF")) {					
						//cpf.setKschf(costs.getAmount()); 
						cpf.setKschf(Utils.addZabisDecimalPlaceV7(costs.getAmount(), 2));   //EI20130107
					} else {
						//cpf.setKsbt(costs.getAmount());
						cpf.setKsbt(Utils.addZabisDecimalPlaceV7(costs.getAmount(), 2));   //EI20130107
					}
				} else if (costs.getType().equalsIgnoreCase("MWST")) {//TODO: 
					cpf.setKmwg(costs.getCurrency());
					if (costs.getCurrency() != null && costs.getCurrency().equals("CHF")) {					
						//cpf.setKmchf(costs.getAmount()); 
						cpf.setKmchf(Utils.addZabisDecimalPlaceV7(costs.getAmount(), 2));   //EI20130107
					} else {
						//cpf.setKmbt(costs.getAmount()); 
						cpf.setKmbt(Utils.addZabisDecimalPlaceV7(costs.getAmount(), 2));   //EI20130107
					}
				}				
			}
		}
		
		return cpf;
	}
	
	public List<TsCPA> mapCPAList(List<Excise> list, String refnr, String posnr) {
		if (list == null) {
			return null;
		}
		List<TsCPA> cvaList = new Vector<TsCPA>();

		for (Excise excise : list) {
			TsCPA cvaSubset = new TsCPA();
			
			cvaSubset.setRefnr(refnr);
			cvaSubset.setPosnr(posnr);
			cvaSubset.setArt("ZA");
			cvaSubset.setAbgart(excise.getTaxCode());
			//cvaSubset.setAbgschl(excise.???);             //TODO: 
			//cvaSubset.setAbgmg(excise.getTaxQuantity());
			cvaSubset.setAbgmg(Utils.addZabisDecimalPlaceV7(excise.getTaxQuantity(), 3)); //EI20130107
			//cvaSubset.setAbgalk(excise.getRateOrPercent());
			cvaSubset.setAbgalk(Utils.addZabisDecimalPlaceV7(excise.getRateOrPercent(), 2)); //EI20130107
			//cvaSubset.setGbbtg(excise.getTaxValue());
			cvaSubset.setGbbtg(Utils.addZabisDecimalPlaceV7(excise.getTaxValue(), 2)); //EI20130107
			cvaSubset.setGbbtgrc(excise.getRateConfirmation());

			cvaList.add(cvaSubset);
		}

		return cvaList;
	}

	private TsCPE mapCPESubset(Refinement refinement, String refnr, String posnr) {
		if (refinement == null) {
			return null;
		}
		TsCPE cpeSubset = new TsCPE();

		cpeSubset.setRefnr(refnr);
		cpeSubset.setPosnr(posnr);
		cpeSubset.setRrdir(refinement.getDirection());
		cpeSubset.setRretyp(refinement.getRefinementType());
		cpeSubset.setRrvtyp(refinement.getProcessType());
		cpeSubset.setRratyp(refinement.getBillingType());
		cpeSubset.setRrkzfp(refinement.getFreePass());
		cpeSubset.setRrptyp(refinement.getPositionType());
		cpeSubset.setRrexwtfw(refinement.getExportValueAmount());
		cpeSubset.setRrexwtwg(refinement.getExportValueCurrency());
		//cpeSubset.setRrexwtku(refinement.getExportExchangeRate());
		cpeSubset.setRrexwtku(Utils.addZabisDecimalPlaceV7(refinement.getExportExchangeRate(), 6)); //EI20130107
		cpeSubset.setRrexwtfk(refinement.getExportExchangeFactor());
		//cpeSubset.setRrexwt(refinement);
		cpeSubset.setRrlokofw(refinement.getLabourCostAmount());
		cpeSubset.setRrlokowg(refinement.getLabourCostCurrency());
		//cpeSubset.setRrlokoku(refinement.getLabourCostExchangeRate());
		cpeSubset.setRrlokoku(Utils.addZabisDecimalPlaceV7(refinement.getLabourCostExchangeRate(), 6)); //EI20130107
		cpeSubset.setRrlokofk(refinement.getLabourCostFactor());
		//cpeSubset.setRrloko(refinement);
		cpeSubset.setRrfrkofw(refinement.getFreightToBorderAmount());
		cpeSubset.setRrfrkowg(refinement.getFreightToBorderCurrency());
		//cpeSubset.setRrfrkoku(refinement.getFreightToBorderExchangeRate());
		cpeSubset.setRrfrkoku(Utils.addZabisDecimalPlaceV7(refinement.getFreightToBorderExchangeRate(), 6)); //EI20130107
		cpeSubset.setRrfrkofk(refinement.getFreightToBorderFactor());
		//cpeSubset.setRrfrko(refinement);
		cpeSubset.setRrmawtfw(refinement.getNewMaterialValue());
		cpeSubset.setRrmawtwg(refinement.getNewMaterialCurrency());
		//cpeSubset.setRrmawtku(refinement.getNewMaterialExchangeRate());
		cpeSubset.setRrmawtku(Utils.addZabisDecimalPlaceV7(refinement.getNewMaterialExchangeRate(), 6)); //EI20130107
		cpeSubset.setRrmawtfk(refinement.getNewMaterialFactor());
		//cpeSubset.setRrmawt(refinement);
		cpeSubset.setDstnr(refinement.getOfficeCode());


		return cpeSubset;
	}	

	private List<TsCPD> mapCPDList(List<SpecialStatement> list, String refnr, String posnr) {		
		if (list == null) {
			return null;
		}
		List<TsCPD> cpdList = new Vector<TsCPD>();
				
		for (SpecialStatement special : list) {
			if (special != null) {
				TsCPD cpdSubset = null;
				cpdSubset = new TsCPD();
				cpdSubset.setRefnr(refnr);
				cpdSubset.setPosnr(posnr);
				cpdSubset.setCode(special.getType());
				cpdSubset.setValue(special.getValue());
				
				cpdList.add(cpdSubset);
			}
		}
		return cpdList;
	}

}
