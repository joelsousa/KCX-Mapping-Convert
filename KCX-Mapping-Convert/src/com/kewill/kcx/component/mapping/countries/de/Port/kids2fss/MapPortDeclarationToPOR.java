package com.kewill.kcx.component.mapping.countries.de.Port.kids2fss;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.Port.msg.MsgPortDeclaration;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.AdditionalData;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.ConsolidatedContainer;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.Container;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.DangerousGoods;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.EADocument;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.EAPosition;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.GoodsItem;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.GoodsLevel;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.MrnStatement;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.PostCarriage;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.PreCarriage;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.Voyage;
import com.kewill.kcx.component.mapping.formats.fss.Port.messages.V65.MsgPOR;
import com.kewill.kcx.component.mapping.formats.fss.Port.messages.V65.MsgPORPos;
import com.kewill.kcx.component.mapping.formats.fss.Port.messages.V65.common.AEData;
import com.kewill.kcx.component.mapping.formats.fss.Port.messages.V65.common.ContainerData;
import com.kewill.kcx.component.mapping.formats.fss.Port.messages.V65.common.PorPos;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsACO;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsACR;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsACT;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsADR;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsAED;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsAEP;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsAGI;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsAGR;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsAGT;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsAGX;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsAKA;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsAKD;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsAKI;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsAKN;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsAKR;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsAKS;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsAKT;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsAKV;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsAKZ;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsAPK;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsMRN;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsTXT;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessagePortGpo;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;


/**
 * Module       : Port<br>
 * Created      : 21.10.2011<br>
 * Description	: Mapping of KIDS PortDeclaration to FSS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MapPortDeclarationToPOR extends KidsMessagePortGpo {
	
	private MsgPOR msgPOR;	
	private MsgPortDeclaration message;
	private String beznr = "";	
	private boolean isZAPP;   			//EI20130510: unterschiedliche formatierung fuer ZAPP u. BHT 
	
	public MapPortDeclarationToPOR(XMLEventReader parser, TsVOR tsvor)  throws XMLStreamException {
		msgPOR = new MsgPOR();	
		message = new MsgPortDeclaration(parser);		
		msgPOR.setVorSubset(tsvor);
	}
	
	public String getMessage() {
		String res = ""; 
	
		try {  
        	message.parse(HeaderType.KIDS);         	
            getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
            beznr = message.getReferenceNumber();
            
            // read MessageID from KidsHeader.
            msgPOR.getVorSubset().setMsgid(getKidsHeader().getMessageID());
        
            //Setting all Ts in MsgCON.
            msgPOR.setAKRSubset(this.mapKidsToAKR());
            msgPOR.setAKISubset(this.mapKidsToAKI());	
            msgPOR.setAKASubset(this.mapKidsToAKA());	
            msgPOR.setAKSSubsetList(this.mapKidsToAKSList(message.getConsolidatedContainerList()));
            msgPOR.setAKDSubset(this.mapKidsToAKD());
            msgPOR.setAKTSubset(this.mapKidsToAKT(message.getVoyage()));	
            msgPOR.setAKNSubset(this.mapKidsToAKN(message.getPostCarriage()));
            msgPOR.setAKVSubset(this.mapKidsToAKV(message.getPreCarriage()));
            msgPOR.setAKZSubset(this.mapKidsToAKZ(message.getAdditionalData()));	 
            msgPOR.setTXTSubsetList(this.mapKidsToTXTList());
            msgPOR.setPosList(this.mapKidsToPosList(message.getGoodsItemList()));
            msgPOR.setContainerList(this.mapKidsToContainerList(message.getContainerList()));
            
            res = msgPOR.getFssString();
            
            //Utils.log("(MapImportDeclarationConfirmationToCON getMessage) Msg = " + res);
            
		} catch (FssException e) {	    	
			e.printStackTrace();	        
		}		    
	    return res;
	}

	private TsAKR mapKidsToAKR() {
		TsAKR subset = new TsAKR();
		
		subset.setBeznr(beznr);
		subset.setSptref(message.getShipperReferenceNumber());
		if (message.getShipper() != null) {
			//EI20111117: soll nicht mehr gef�llt werden - wird in Zabis mit einer glogal_user_id besetzt
			//subset.setSpdknr(message.getShipper().getCustomerIdentifier());
			subset.setSpdtin(message.getShipper().getTin());
		}
		subset.setBhtref(message.getPortRegistrationNumber());  //ZAPP Z-Number/BHT ReferenceNumber
		if (this.getKidsHeader().getSentAt() != null && this.getKidsHeader().getSentAt().length() > 9) {
			String datetime = this.getKidsHeader().getSentAt();
			String date = datetime.substring(0, 4) + datetime.substring(5, 7) +  datetime.substring(8, 10);
			date = date + datetime.substring(11, 13) + datetime.substring(14, 16); 
			subset.setSnddat(date);		
		}
		subset.setArtna(message.getMessageFunction()); 
		//EI20120424: in Zabis andere zuordnung: JOB=5 ist FSS=4 (5 wurde gestrichen)
		if (subset.getArtna() != null && subset.getArtna().equals("5")) {
			subset.setArtna("4");
			
		}
		subset.setKnztst(message.getTestMode());   
		subset.setKnztst("0");
		
		return subset;
	}
	
	private TsAKI mapKidsToAKI() {
		TsAKI subset = new TsAKI();
		
		subset.setBeznr(beznr);
		subset.setHasys(message.getPortSystem());
		subset.setArtauf(message.getDeclarationType());
		subset.setQartau(message.getDeclarationTypeSpecification());
		subset.setKzauft("1");
		subset.setKzsaco(message.getDeclarationMode());		
		subset.setLeiart(message.getActivityType());
		
		//EI20131104:
		if (!Utils.isStringEmpty(message.getPortSystem()) && 
				message.getPortSystem().equalsIgnoreCase("ZAPP")) {
			isZAPP = true;
		} else {
			isZAPP = false;
		}
		
		return subset;
	}
	
	private TsAKA mapKidsToAKA() {
		TsAKA subset = new TsAKA();
		
		subset.setBeznr(beznr);
		if (message.getShipper() != null) {
			subset.setSpdkai(message.getShipper().getTerminalCustomerNumber());
			if (message.getShipper().getContactPerson() != null) {
				subset.setSpdsa(message.getShipper().getContactPerson().getPosition());
				subset.setSpdsb(message.getShipper().getContactPerson().getName());
				subset.setSpdtel(message.getShipper().getContactPerson().getPhoneNumber());
				subset.setSpdfax(message.getShipper().getContactPerson().getFaxNumber());
				subset.setSpdmai(message.getShipper().getContactPerson().getEmail());	
			}
		}
		if (message.getContactPersonForDangerousGoods() != null) {
			subset.setGfgsb(message.getContactPersonForDangerousGoods().getName());
			subset.setGfgtel(message.getContactPersonForDangerousGoods().getPhoneNumber());
		}
		return subset;
	}	
	
	private TsAKD mapKidsToAKD() {
		TsAKD subset = new TsAKD();
		
		subset.setBeznr(beznr);
		subset.setKaicde(message.getTerminalCode());  //in MsgJob ist z.Z. dummy == immer leer; in ZABIS: wenn nicht gesendet wird aus Bearb.St. genommen (HAM_DKY)
		subset.setOffer(message.getOfferNumber());		
		subset.setExtdst(message.getCustomsOfficeExport());
		if (message.getAgent() != null) {
			subset.setRdrcde(message.getAgent().getPortCode());
			subset.setRdrknr(message.getAgent().getCustomerIdentifier());
			subset.setRdrkai(message.getAgent().getTerminalCustomerNumber());
		} else if (message.getVoyage() != null && message.getVoyage().getShipReferenceNumber() != null) {   //EI20120412
			if (message.getVoyage().getShipReferenceNumber().length() == 7) {							
				subset.setRdrcde(message.getVoyage().getShipReferenceNumber().substring(0, 3));				
			}
		}
		
		if (message.getInvoiceConsignee() != null) {
			subset.setArecde(message.getInvoiceConsignee().getPortCode());
			subset.setAreknr(message.getInvoiceConsignee().getCustomerIdentifier());
			subset.setArekai(message.getInvoiceConsignee().getTerminalCustomerNumber());
		}
		if (message.getFOBShipper() != null) {
			subset.setFobcde(message.getFOBShipper().getPortCode());
			subset.setFobknr(message.getFOBShipper().getCustomerIdentifier());
			if (message.getFOBShipper().getContactPerson() != null) {
				subset.setArefob(message.getFOBShipper().getContactPerson().getPosition());	
			}
		}		
		if (message.getTally() != null) {
			subset.setTlycde(message.getTally().getPortCode());
			subset.setTlyknr(message.getTally().getCustomerIdentifier());
		}
		return subset;
	}
		
	private TsAKT mapKidsToAKT(Voyage voyage) {
		if (voyage == null) {
			return null;
		}
		TsAKT subset = new TsAKT();
		
		subset.setBeznr(beznr);
		subset.setCrnnr(voyage.getShipReferenceNumber());		
		subset.setRswnr(voyage.getVoyageNumber());
		subset.setFrafco(voyage.getShipOwner());
		subset.setRufzei(voyage.getShipCallSign());
		subset.setShpnam(voyage.getShipName());
		//subset.setEta(voyage.getArrivalDate());
		if (voyage.getArrivalDate() != null) {
			String dateTime = voyage.getArrivalDate();
			if (dateTime.length() == 8) {
				subset.setEta(dateTime);
			}
			if (dateTime.length() == 12) {
				subset.setEta(dateTime.substring(0, 8));
			}
		}
		//subset.setEts(voyage.getDepartureDate());
		if (voyage.getDepartureDate() != null) {
			String dateTime = voyage.getDepartureDate();
			if (dateTime.length() == 8) {
				subset.setEts(dateTime);
			}
			if (dateTime.length() == 12) {
				subset.setEts(dateTime.substring(0, 8));
			}
		}
		subset.setPolloc(voyage.getLoadingPort());
		subset.setPodloc(voyage.getDischargePort());
		subset.setPoeloc(voyage.getFinalPort());
		subset.setOrtbco(voyage.getDestinationPlaceCode());		
		subset.setOrtbna(voyage.getDestinationPlaceName());
		subset.setImonr(voyage.getImoNumber());
		
		return subset;
	}
	
	private TsAKN mapKidsToAKN(PostCarriage nachlauf) {
		if (nachlauf == null) {
			return null;
		}
		TsAKN subset = new TsAKN();
		
		subset.setBeznr(beznr);
		subset.setNtpcde(nachlauf.getTransportType());
		subset.setNtpid(nachlauf.getCallSign());
		subset.setNtpken(nachlauf.getTransportationNumber());
	
		return subset;
	}	
		
	private TsAKV mapKidsToAKV(PreCarriage vorlauf) {
		if (vorlauf == null) {
			return null;
		}
		TsAKV subset = new TsAKV();
		
		subset.setBeznr(beznr);
		//subset.setVtpcde(vorlauf.getTransportType());
		subset.setVtpcde("3");             //EI20120412-TODO: 3 = Strassentr. ob dass immer OK ist???
		//subset.setVtpid(vorlauf.getCallSign()); fuer vtpcde=3 dieses feld wird nicht ausgewertet
		subset.setVtpken(vorlauf.getTransportationNumber());
		subset.setVlifda(vorlauf.getExpectedDeliveryDate());		
		subset.setTrkvrm(vorlauf.getAnnotation());
		subset.setTrknam(vorlauf.getForwarder());
		subset.setTrkcde(vorlauf.getForwarderCode());
		
		return subset;
	}
	
	private TsAKZ mapKidsToAKZ(AdditionalData data) {
		if (data == null) {
			return null;
		}
		TsAKZ subset = new TsAKZ();
		
		subset.setBeznr(beznr);
		subset.setKzlib(data.getDirectTakeover());
		subset.setBunr(data.getBookingNumber());
		subset.setBlnr(data.getBillOfLadingNumber());		
		subset.setSpdpwd(data.getPassword());		
		subset.setRgwech(data.getInvoiceNote());
		
		return subset;
	}
		
		
	private List<TsAKS> mapKidsToAKSList(List<ConsolidatedContainer> list) {		
		if (list == null) {
			return null;
		}
		List<TsAKS> aksList = new Vector<TsAKS>();		
		for (ConsolidatedContainer saco : list) {
			TsAKS aks = new TsAKS();
			
			aks.setBeznr(beznr);
			aks.setLfdnr(saco.getSequenceNumber());
			aks.setSaconr(saco.getPortReference());
			aks.setSavoll(saco.getLadingFlag());
			
			aksList.add(aks);			
		}		
		return aksList;
	}
	
	private List<TsTXT> mapKidsToTXTList() {
		if (message == null) {
			return null;
		}
		List<TsTXT> txtList = new Vector<TsTXT>();
		if (Utils.isStringEmpty(message.getDeclarationRemark())) {		
			TsTXT txt = new TsTXT();
			txt.setBeznr(beznr);
			txt.setKenn("AAI");
			txt.setTxt(message.getDeclarationRemark());	 			
			txtList.add(txt);
		}
		if (Utils.isStringEmpty(message.getStockMarker())) {		
			TsTXT txt = new TsTXT();
			txt.setBeznr(beznr);
			txt.setKenn("PAC_B");
			txt.setTxt(message.getStockMarker());	 			
			txtList.add(txt);
		}
		if (Utils.isStringEmpty(message.getLoadMarker())) {		
			TsTXT txt = new TsTXT();
			txt.setBeznr(beznr);
			txt.setKenn("PAC_V");
			txt.setTxt(message.getLoadMarker());	 			
			txtList.add(txt);
		}
		if (Utils.isStringEmpty(message.getConsolidatedContainerRemark())) {		
			TsTXT txt = new TsTXT();
			txt.setBeznr(beznr);
			txt.setKenn("SAC");
			txt.setTxt(message.getConsolidatedContainerRemark());	 			
			txtList.add(txt);
		}
		return txtList;
	}
	private List<ContainerData> mapKidsToContainerList(List<Container> list) {
		if (list == null) {
			return null;
		}
		List<ContainerData> containerDataList = new Vector<ContainerData>();
		for (Container container : list) {
			ContainerData containerData = new ContainerData();
			TsACR acr = new TsACR();			
			acr.setBeznr(beznr);						
			//acr.setConnr(container.getContainerNumber());
			acr.setConnr(this.getCntNrFromJOB(container.getContainerNumber()));   //EI20120423
			acr.setTyp(container.getType());
			acr.setOwner(container.getOwner());						
			acr.setLadung(container.getLadingFlag()); //1=sammel, 2=voll, 3=leer  
			//EI20131104:acr.setVoller(container.getLadingFlag()); //only BHT:  4=leer, 5=voll 
			//EI20131104:
			if (isZAPP) {								
				acr.setVoller("");              //only BHT
			} else {                            //only BHT:  4=leer, 5=voll	
				if (!Utils.isStringEmpty(container.getLadingFlag())) {
					String flag = container.getLadingFlag();			
					if (flag.equals(2)) {
						acr.setVoller("5");
					}
					if (flag.equals(3)) {
						acr.setVoller("4");
					}
				}
			}			
			acr.setGrgew(addZabisDecimalPlace(container.getGrossMass(), 3));			
			acr.setTrgew(roundKffDecimalPlaces(container.getTareMass())); //EI20120412
			acr.setNegew(roundKffDecimalPlaces(container.getNetMass()));  //EI20120412
			acr.setTempe(container.getTemperaturUnit());
			acr.setTempo(container.getMaxPermitedTemperatur());
			acr.setTempu(container.getMinPermitedTemperatur());
			acr.setMod(container.getTemperaturMode());
			acr.setBunr(container.getBookingNumber());			
			
			if (container.getSealNumberList() != null) {
				int count = 0;
				for (String seal : container.getSealNumberList()) {
					count = count + 1;
					if (count == 1) {
						acr.setSiegl1(seal);
					}
					if (count == 2) {
						acr.setSiegl2(seal);
					}
					if (count == 3) {
						acr.setSiegl3(seal);
					}
					if (count == 4) {
						acr.setSiegl4(seal);
					}
				}
			}
			containerData.setACRSubset(acr);
			
			if (!Utils.isStringEmpty(container.getLadingDescription())) {
				TsACT act = new TsACT();
				act.setBeznr(beznr);
				act.setConnr(container.getContainerNumber());
				act.setTxt(container.getLadingDescription());
				containerData.setACTSubset(act);
			}
			containerDataList.add(containerData);
		}
		return containerDataList;
	}
	
	private List<MsgPORPos> mapKidsToPosList(List<GoodsItem> list) {
		if (list == null) {
			return null;
		}
		List<MsgPORPos> posList = new Vector<MsgPORPos>();
		String posnr = "";
		for (GoodsItem item : list) {
		if (item != null) {
			MsgPORPos pos = new MsgPORPos();
			posnr = item.getItemNumber();
			TsAPK apk = new TsAPK();
			apk.setBeznr(beznr);
			apk.setPosnr(item.getItemNumber());
			apk.setArtzol(item.getCustomsDeclarationType());			
			//EI20120425: apk.setConnr(item.getContainerNumber());
			apk.setConnr(this.getCntNrFromJOB(item.getContainerNumber())); //EI20120425
			apk.setHandla(item.getHandlingMode());
			apk.setProdid(item.getTarifCode());
			apk.setKfzid(item.getTruckNumber());
			apk.setVerktr(item.getTransportationNumber());
			apk.setKzubeh(item.getTransportationAccesoryNumber());
			pos.setAPKSubset(apk);
						
			if (item.getFirstGoodsLevel() != null) {
				GoodsLevel level1 = item.getFirstGoodsLevel();	
				PorPos  porpos1 = new PorPos();
				
				TsACO aco = new TsACO();
				aco.setBeznr(beznr);
				aco.setPosnr(posnr);
				aco.setPe2lfd("");
				aco.setColanz(level1.getQuantity());
				//String colliArt = level1.getPackingType();
				//String colliArt = this.getColliArtFromJOB(level1.getPackingType());				
				//aco.setColart(colliArt);
				aco.setColart(level1.getPackingType());    //EI20130513
				//EI20111124: aco.setRohmas(level1.getGrossMass());
				aco.setRohmas(addZabisDecimalPlace(level1.getGrossMass(), 3));
				//EI20111124: aco.setBrtvol(level1.getVolume());
				aco.setBrtvol(addZabisDecimalPlace(level1.getVolume(), 3));
				
				porpos1.setACOSubset(aco);
				
				int po = 0;
				if (level1.getMrnStatementList() != null) {
					for (MrnStatement mrnSt : level1.getMrnStatementList()) {
						po = po + 1;
						TsMRN mrn = new TsMRN();
						mrn.setBeznr(beznr);					
						mrn.setPosnr(posnr);	
						mrn.setPe2lfd("");	
						mrn.setPe3lfd("");	
						mrn.setMrn(mrnSt.getMrn());	
						mrn.setMrnpo("" + po); 	
						//mrn.setMrnid(mrnSt.???);	ist doch innerhalb MRN-Position eine liste
						mrn.setMrnall(mrnSt.getMrnComplete());	
						//mrn.setMrnmin(mrnSt.??);	ist doch innerhalb MRN-Position
						//mrn.setEigmas(mrnSt.get);	
						//mrn.setRohm(mrnSt);	
						//mrn.setKzkomp(mrnSt);	
						//mrn.setLdbe(mrnSt);	
						//mrn.setLdve(mrnSt); 	TODO-IWA unklar wie es zu mappen ist				  
						porpos1.addMRNSubsetList(mrn);	
					}
				}
				
				if (!Utils.isStringEmpty(level1.getItemRemark())) {
					TsTXT txt = new TsTXT();
					txt.setBeznr(beznr);
					txt.setKenn("AEA");
					txt.setPos(posnr);	
					txt.setPe2lfd("");	
					txt.setTxt(level1.getItemRemark());	 								
					porpos1.addTXTSubsetList(txt);
				}
				if (!Utils.isStringEmpty(level1.getMarks())) {  //EI230120113
					TsTXT txt = new TsTXT();
					txt.setBeznr(beznr);
					txt.setKenn("PCI");
					txt.setPos(posnr);	
					txt.setPe2lfd("");	
					txt.setTxt(level1.getMarks());	 								
					porpos1.addTXTSubsetList(txt);
				}
				if (!Utils.isStringEmpty(level1.getDescription())) {  //EI230120113
					TsTXT txt = new TsTXT();
					txt.setBeznr(beznr);
					txt.setKenn("AAA");
					txt.setPos(posnr);	
					txt.setPe2lfd("");	
					txt.setTxt(level1.getDescription());	 								
					porpos1.addTXTSubsetList(txt);
				}				
				
				if (item.getFirstGoodsLevel().getEADocument() != null) {					
					porpos1.setAEData(mapAdeKidsToFss(item.getFirstGoodsLevel().getEADocument(), posnr));					
				}				
				
				if (level1.getDangerousGoods() != null) {										
					porpos1.setAGRSubset(mapKidsDGToAGR(level1.getDangerousGoods(), posnr, "")); //EI20120511
					porpos1.setAGISubset(mapKidsDGToAGI(level1.getDangerousGoods(), posnr, "")); //EI20120511
					porpos1.setAGTSubset(mapKidsDGToAGT(level1.getDangerousGoods(), posnr, "")); //EI20120511
					porpos1.setAGXSubset(mapKidsDGToAGX(level1.getDangerousGoods(), posnr, "")); //EI20120511					
				}
				pos.setPosEbene1(porpos1);
			}
			
			if (item.getSecondGoodsLevelList() != null) {
				int nr2 = 0;
				String pos2nr = "";		
				for (GoodsLevel level2 : item.getSecondGoodsLevelList()) {
					nr2 = nr2 + 1;
					pos2nr = "" + nr2;
					PorPos porpos2 = new PorPos();
					TsACO aco = new TsACO();
					aco.setBeznr(beznr);
					aco.setPosnr(posnr);
					aco.setPe2lfd(pos2nr);
					aco.setColanz(level2.getQuantity());
					String colliArt = level2.getPackingType();
					if (!Utils.isStringEmpty(colliArt) && colliArt.equals("PCS")) {
						//colliArt = "CO";
						colliArt = "PK";  //EI20130513
					}
					aco.setColart(colliArt);
					//aco.setRohmas(level2.getGrossMass());
					aco.setRohmas(addZabisDecimalPlace(level2.getGrossMass(), 3));  //EI20120116
					//aco.setBrtvol(level2.getVolume());
					aco.setBrtvol(addZabisDecimalPlace(level2.getVolume(), 3));  //EI20120116
					
					porpos2.setACOSubset(aco);	
					
					if (level2.getDangerousGoods() != null) {  //EI20120511		
						porpos2.setAGRSubset(mapKidsDGToAGR(level2.getDangerousGoods(), posnr, pos2nr)); 
						porpos2.setAGISubset(mapKidsDGToAGI(level2.getDangerousGoods(), posnr, pos2nr)); 
						porpos2.setAGTSubset(mapKidsDGToAGT(level2.getDangerousGoods(), posnr, pos2nr)); 
						porpos2.setAGXSubset(mapKidsDGToAGX(level2.getDangerousGoods(), posnr, pos2nr)); 			
					}
					pos.addPosEbene2List(porpos2);
				}	
			}
			posList.add(pos);
		}
		}
		return posList;
	}
	
	private TsAGR mapKidsDGToAGR(DangerousGoods dangerous, String posnr1, String posnr2) { //EI20120511
		if (dangerous == null) {
			return null;
		}
		TsAGR agr = new TsAGR();
		agr.setBeznr(beznr);
		agr.setPosnr(posnr1);	
		agr.setPe2lfd(posnr2);			
		agr.setImdg(dangerous.getImdgClass());
		agr.setUnnr(dangerous.getUnNumber());
		agr.setTechna(dangerous.getTechnicalSpecification());	
		agr.setMarpol(dangerous.getMarinePollutantFlag());		
		agr.setFlamp(dangerous.getFlashpoint());
		agr.setFlampe(dangerous.getFlashpointQualifier());
		agr.setVpsist(dangerous.getPackagingSafetyGroup());			
		 //ZAPP: not used: agr.setNetgew(dangerous.getNetWeight());
		agr.setNevgrp(dangerous.getDangerToleranceFlag());
		//EI20130510:agr.setExpgew(dangerous.getNetWeightExplosive());
		agr.setExpgew(addZabisDecimalPlace(dangerous.getNetWeightExplosive(), 3));
		agr.setExpspb(dangerous.getExplosiveGoodsCertificateFlag());
		if (dangerous.getSeaTransportDetails() != null) {
			agr.setStaukt(dangerous.getSeaTransportDetails().getStuffingCategory());
		}
		if (dangerous.getLandTransportDetails() != null) {
			agr.setStrkla(dangerous.getLandTransportDetails().getGGVSClass());
			agr.setStrzif(dangerous.getLandTransportDetails().getGGVSNumber());
			agr.setStrunr(dangerous.getLandTransportDetails().getUNNumberForLandTransport());
		}
		return agr;
	}
	
	private TsAGI mapKidsDGToAGI(DangerousGoods dangerous, String posnr1, String posnr2) { //EI20120511
		if (dangerous == null) {
			return null;
		}
		TsAGI agi = new TsAGI();
		agi.setBeznr(beznr);
		agi .setPosnr(posnr1);	
		agi.setPe2lfd(posnr2);	
		agi.setLimqu(dangerous.getLimitetQuantitiesCode());
		agi.setImdgc(dangerous.getImdgAmendments());
		agi.setExcqu(dangerous.getExpectedQuantitiesCode());
		if (dangerous.getSeaTransportDetails() != null) {
			agi.setEms(dangerous.getSeaTransportDetails().getEmsNumber());
			agi.setEms2(dangerous.getSeaTransportDetails().getEmsSecondNumber());	
			agi.setLabel1(dangerous.getSeaTransportDetails().getDangerLabel1());
			agi.setLabel2(dangerous.getSeaTransportDetails().getDangerLabel2());
			agi.setLabel3(dangerous.getSeaTransportDetails().getDangerLabel3());		
			agi.setWgkcde(dangerous.getSeaTransportDetails().getWatterPollutionClass());		
			agi.setMfag(dangerous.getSeaTransportDetails().getMfag1());
			agi.setMfag2(dangerous.getSeaTransportDetails().getMfag2());
		}
		if (dangerous.getRadioactiveGoodsDetails() != null) {
			agi.setBfsnr(dangerous.getRadioactiveGoodsDetails().getBfSNumber());
		}
		return agi;
	}
	
	private TsAGT mapKidsDGToAGT(DangerousGoods dangerous, String posnr1, String posnr2) { //EI20120511
		if (dangerous == null) {
			return null;
		}
		TsAGT agt = new TsAGT();
		agt.setBeznr(beznr);
		agt .setPosnr(posnr1);	
		agt.setPe2lfd(posnr2);	
		agt.setGfausl(dangerous.getDangerReleaser());
		agt.setEigens(dangerous.getDangerDescription());
		agt.setVerm(dangerous.getAnnotations());
		if (dangerous.getSeaTransportDetails() != null) {
			agt.setStautx(dangerous.getSeaTransportDetails().getStuffingDescription());
		}
		if (dangerous.getLandTransportDetails() != null) {
			agt.setGgvla(dangerous.getLandTransportDetails().getLandTransportDangerFlag());
			agt.setGgvlis(dangerous.getLandTransportDetails().getAnnotationsForLandTransport());
			agt.setGgvkem(dangerous.getLandTransportDetails().getKemmlerNumber());
		}
		
		return agt;
	}
	
	private TsAGX mapKidsDGToAGX(DangerousGoods dangerous, String posnr1, String posnr2) { //EI20120511
		if (dangerous == null) {
			return null;
		}
		TsAGX agx = new TsAGX();
		agx.setBeznr(beznr);
		agx .setPosnr(posnr1);	
		agx.setPe2lfd(posnr2);	
		if (dangerous.getRadioactiveGoodsDetails() != null) {
			agx.setKat(dangerous.getRadioactiveGoodsDetails().getCathegory());
			agx.setVptyp(dangerous.getRadioactiveGoodsDetails().getPackagingType());
			agx.setBlttnr(dangerous.getRadioactiveGoodsDetails().getUnNumber());
			agx.setXraace(dangerous.getRadioactiveGoodsDetails().getRadioactivityQualifier());
			agx.setXrakrw(dangerous.getRadioactiveGoodsDetails().getCriticalHazardIndex());
			agx.setXratac(dangerous.getRadioactiveGoodsDetails().getRadioactivityLevel());
			agx.setXratrw(dangerous.getRadioactiveGoodsDetails().getTransportIndex());
		}
					
		return agx;
	}
	
	private AEData mapAdeKidsToFss(EADocument eadoc, String pos) {
		if (eadoc == null) {
			return null;
		}
		AEData aeData = new AEData();
		
		TsAED tsAed = new TsAED();		
		aeData.setAEDSubset(tsAed);
	
		tsAed.setBeznr(beznr);
		tsAed.setPosnr(pos);
		tsAed.setRegnr(eadoc.getEaNumber());
		tsAed.setArtzol(eadoc.getCustomsProcedure());		
		tsAed.setLdbe(eadoc.getDestinationCountry());
		tsAed.setLdve(eadoc.getDispatchCountry());
		tsAed.setVeomrn(eadoc.getDispatchMrn());
		tsAed.setKz100(eadoc.getValueGreater1000Flag());
		tsAed.setKzbef(eadoc.getSimplificationFlag());
		tsAed.setKzmow(eadoc.getMarketOrderFlag());
		tsAed.setRegmow(eadoc.getSumaReferenceNumber());					
		tsAed.setEsuma(eadoc.getReference30A());
		tsAed.setAsuma(eadoc.getSumaSimplificationReason());
		tsAed.setVorpap(eadoc.getPreviousDocument());
		tsAed.setBewnr(eadoc.getAuthorizationNumber());			
		tsAed.setBefrei(eadoc.getSimplificationCode());
		tsAed.setPruef(eadoc.getOfficialStatement());
		tsAed.setExtdst(eadoc.getCustomsOfficeExport());
		
		if (eadoc.getDeclarant() != null) {
			tsAed.setZbanm(eadoc.getDeclarant().getCustomsIdentifier());
			tsAed.setKdnran(eadoc.getDeclarant().getCustomerIdentifier());
			tsAed.setAnmid(eadoc.getDeclarant().getAdditionalIdentification());
			if (eadoc.getDeclarant().getAddress() != null) {
				TsADR tsAdr1 = new TsADR();				
				tsAdr1.setBeznr(beznr);
				tsAdr1.setPosnr(pos);
				tsAdr1.setTyp("3");               //EI20121011
				tsAdr1.setName1(eadoc.getDeclarant().getAddress().getName());
				tsAdr1.setStr(eadoc.getDeclarant().getAddress().getStreet());
				tsAdr1.setOrt(eadoc.getDeclarant().getAddress().getCity());
				tsAdr1.setPlz(eadoc.getDeclarant().getAddress().getPostalCode());
				tsAdr1.setAlpha(eadoc.getDeclarant().getAddress().getCountry());	
				aeData.setADR1Subset(tsAdr1);				
			}
		}
		if (eadoc.getConsignor() != null) {			
			tsAed.setKdnrve(eadoc.getConsignor().getCustomerIdentifier());
			if (eadoc.getConsignor().getAddress() != null) {
				TsADR tsAdr2 = new TsADR();				
				tsAdr2.setBeznr(beznr);
				tsAdr2.setPosnr(pos);
				tsAdr2.setTyp("6");               //EI20121011
				tsAdr2.setName1(eadoc.getConsignor().getAddress().getName());
				tsAdr2.setStr(eadoc.getConsignor().getAddress().getStreet());
				tsAdr2.setOrt(eadoc.getConsignor().getAddress().getCity());
				tsAdr2.setPlz(eadoc.getConsignor().getAddress().getPostalCode());
				tsAdr2.setAlpha(eadoc.getConsignor().getAddress().getCountry());	
				aeData.setADR2Subset(tsAdr2);			
			}
		}
		if (eadoc.getConsignee() != null) {			
			tsAed.setKdnrem(eadoc.getConsignee().getCustomerIdentifier());
			tsAed.setEmpid(eadoc.getConsignee().getAdditionalIdentification());
			if (eadoc.getConsignee().getAddress() != null) {
				TsADR tsAdr3 = new TsADR();				
				tsAdr3.setBeznr(beznr);
				tsAdr3.setPosnr(pos);
				tsAdr3.setTyp("4");               //EI20121011
				tsAdr3.setName1(eadoc.getConsignee().getAddress().getName());
				tsAdr3.setStr(eadoc.getConsignee().getAddress().getStreet());
				tsAdr3.setOrt(eadoc.getConsignee().getAddress().getCity());
				tsAdr3.setPlz(eadoc.getConsignee().getAddress().getPostalCode());
				tsAdr3.setAlpha(eadoc.getConsignee().getAddress().getCountry());	
				aeData.setADR3Subset(tsAdr3);
			}
		}
		if (eadoc.getNotify() != null) {			
			tsAed.setKdnrmi(eadoc.getNotify().getCustomerIdentifier());
			if (eadoc.getNotify().getAddress() != null) {
				TsADR tsAdr4 = new TsADR();				
				tsAdr4.setBeznr(beznr);
				tsAdr4.setPosnr(pos);
				tsAdr4.setTyp("7");               //EI20121011
				tsAdr4.setName1(eadoc.getNotify().getAddress().getName());
				tsAdr4.setStr(eadoc.getNotify().getAddress().getStreet());
				tsAdr4.setOrt(eadoc.getNotify().getAddress().getCity());
				tsAdr4.setPlz(eadoc.getNotify().getAddress().getPostalCode());
				tsAdr4.setAlpha(eadoc.getNotify().getAddress().getCountry());
				aeData.setADR4Subset(tsAdr4);
			}
		}
		
		if (eadoc.getPositionList() != null) {
			for (EAPosition eapos : eadoc.getPositionList()) {
				int ip = 0;
				if (eapos != null) { 
					ip = ip + 1;
					if (ip > 1) {
						break;
					}
					TsAEP tsAep = new TsAEP();
					tsAep.setBeznr(beznr);
					tsAep.setPosnr(pos);					
					tsAep.setAepos(eapos.getEaPositionNumber());
					tsAep.setTnr(eapos.getTarifCode());
					tsAep.setWbsch(eapos.getDescription());
					//EI20130510: tsAep.setEigmas(eapos.getNetMass());
					//EI20130510: tsAep.setRohmas(eapos.getGrossMass());
					tsAep.setEigmas(addZabisDecimalPlace(eapos.getNetMass(), 3));
					tsAep.setRohmas(addZabisDecimalPlace(eapos.getGrossMass(), 3));					
					tsAep.setCdevfr(eapos.getProcedureCode());
					if (eapos.getAnnotationList() != null) {
						int i = 0;
						for (String ann : eapos.getAnnotationList()) {
							if (!Utils.isStringEmpty(ann)) {
								i = i  + 1;
								if (i == 1) {
									tsAep.setVerme1(ann);									
								}
								if (i == 2) {
									tsAep.setVerme2(ann);									
								}
								if (i == 3) {
									tsAep.setVerme3(ann);									
								}																
							}
						}						
					}					
					aeData.setAEPSubset(tsAep);						
				}
			}
		}
	
		return aeData;
	}
	
}
