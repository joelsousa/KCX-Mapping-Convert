package com.kewill.kcx.component.mapping.formats.kff.msg;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.Container;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.formats.kff.common.CargoItem;
import com.kewill.kcx.component.mapping.formats.kff.common.JobKCX;
import com.kewill.kcx.component.mapping.formats.kff.common.RefNo;
import com.kewill.kcx.component.mapping.formats.kff.common.Remark;
import com.kewill.kcx.component.mapping.formats.kff.common.Sea;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: PORT<br>
 * Erstellt		: 02.12.2010<br>
 * Beschreibung	: KFF Message JOB
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class MsgJOB extends KCXMessage {
	
	//private DocumentInforamtion  documentInfo;	
	//private UserInforamtion userInfo;	
	//private JobInformation jobInfo;	
	
	private String jobNo;  
	private String unid;    //EI20120425
	private String jobDate;           //BL
	private String jobType;           //EI20120424 TODO-IWA: GPODAK, GPOBHT, BLDAK, BLDBH
	private String funcCode;          //akr_artna; 9-Creation, 1-Deletion, 5-Correction
	private String carrierCode; 
	private String shprEORINo;  
	private String shprPIC;    
	private String shprName;     
	private String shprTel;      
	private String shprFax;     
	private String shprEmail;      //EI20120419   
	private String shprBEFunction; //EI20120427
	private String itemHazardContact; 
	private String itemHazardContactTel; 
	
	private String declPlace;
	
	private String motherVesselName;  
	private String motherVoyage;  		//AK20120403
	private String motherVesselETA;    //YYYYMMDDhhmm; in ZABIS nur YYYYMMDD
	private String motherVesselETD;    //YYYYMMDDhhmm; in ZABIS nur YYYYMMDD
	private String motherVesselCallSign;  
	private String motherVesselCode;
	private String motherVesselLioydsregno; //EI20120509
	private String motherVesselRegCtry;     //EI20120509
	
	private String polCity;   
	private String polCtry;   
	private String podCity;   
	private String podCtry;   
	private String destCity;  //Endhafen
	private String destCtry;   
	private String destName;  
	private String devryCity;  //Bestimmungsort
	private String devryCtry;
	private String devryName;  
	
	private String bookingNo; 
	private String bookingDate; 
	private String shpNo;   	
	private String srvReqTruckNo;  
	private String srvReqSubcName;   //EI20120412
	
	//private List <GoodsItem> cargoItemList;
	private List <CargoItem> cargoItemList;
	private List <RefNo> refNoList;   //???
	private List <Container> containerList;
	private List <Remark> remarkList;	
	private String customsDeclType;    //EI20120412
	private String kaiCde;    //EI20120412   TODO-IWA
	private String rdrCde;    //EI20120412   TODO-IWA
	
	private String shprPartyId;
	private String shprCtryCode;
	private String shprPostalCode;
	private List<String> shprAddrList = new ArrayList<String>(); //EI20130422 //new String[] {"", "", "", ""};  //EI20120419
	private String csgnName;
	private String csgnEORINo;  
	private String csgnPIC;    	
	private String csgnTel;      
	private String csgnFax;     
	private String csgnEmail;      //EI20120419  
	private String csgnPartyId;
	private String csgnCTRYCode;
	private String csgnPostalCode;
	private List<String> csgnAddrList = new ArrayList<String>(); //EI20130422 //new String[] {"", "", "", ""};  //EI20120419
	
	private String totGWGT; 					//EI20120419 BL
	private String totPCS; 					    //EI20120419 BL
	private String totVOL;						//EI20120419 BL
	private String cargoValue; 				    //EI20120419 BL
	private String cargoValueCurr;  			//EI20120419 BL
	private String typeOfPermit;                //hier steht for KFF "EXPORT"
	private String frtTerm;
	
	private JobKCX  jobKCX;
	private boolean isBL = false;   //EI20130517
	private Sea 	sea = null;
	private String  shpType = "";    //AK20130516
	
	
	

	public MsgJOB() {
		super();		
	}

	public MsgJOB(XMLEventReader parser) throws XMLStreamException {
		super(parser);		
	}		

	private enum EMsgKff {
		DocumentInforamtion,
		UserInforamtion,		
		//JobInformation;
		ShpType,				//AK20130516
		JobNo,                   //beznr
		UNID,
		JobDate,
		JobType,        
		TypeOfPermit,
		FuncCode,                //akr_artna
		CarrierCode,             //fzpakr_spdknr  
		ShprEORINo,	             //fzpakr_spdtin
		PartyID_Shpr,           						//EI20120419: for BL
		ShprName,                //  kein fzpaka_name dafuer aber _kaicde
		ShprAddr1, ShprAddr2, ShprAddr3, ShprAddr4,  	//EI20120419: for BL
		ShprCTRYCode, ShprCtryCode, ShprPostalCode,  					//EI20120419: for BL
		Shpr_PIC,                //fzpaka_spdsa
		Shpr_Tel,                //fzpaka_spdtel
		Shpr_Fax,                //fzpaka_spdfax
		ShprContactEmail,
		ShprBEFunction,
		CsgnEORINo,	Csgn_PIC, Csgn_Tel, Csgn_Fax, CsgnContactEmail,
		CsgnName, 
		PartyID_Csgn,            						//EI20120419: for BL//EI20120419: for BL
		CsgnCTRYCode, CsgnPostalCode,  					//EI20120419: for BL
		CsgnAddr1, CsgnAddr2, CsgnAddr3, CsgnAddr4,   //EI20120419: for BL
		//ItemHazardContact,       //fzpaka_gfgsb
		//ItemHazardContactTel,    //fzpaka_gfgtel		
		DeclPlace, declplace,    //fzpakd_extdst
		MotherVoyage,            //fzpakt_crnnr	
		MotherVesselName,        //fzpakt_shpnam 
		MotherVesselCallSign,    //fzpakt_rufzei
		MotherVesselCode,        //??? 
		MotherVesselETA,         //fzpakt_eta
		MotherVesselETD,         //fzpakt_ets
		MotherVesselLioydsregno, // ??? fzpakt_imonr, ist auch 7-stellig ???
		MotherVesselRegCtry,     //
		POLCity,                 //fzpakt_polloc 
		POLCtry,                 //fzpakt_polloc 
		PODCity,                 //fzpakt_podloc 
		PODCtry,                 //fzpakt_podloc 
		DestCity,                //fzpakt_poeloc 
		DestCtry,                //fzpakt_poeloc 
		DestName,                //fzpakt_ortna, fzpakt_ortbca=5 fix???		
		DevryCity,
		DevryCtry,
		DevryName,  
		//BookingNo,               //fzpakz_bunr
		Sea,        //Sea.SONo,
		BookingDate,
		ShpNo,                   //fzpakz_blnr
		SrvReqTruckNo,		     //fzpakv_trkcde	
		SrvReqSubcName,		     //fzpakv_trknam
		TOTGWGT,                                            
		TOTPCS,  
		TOTVOL, 
		CargoValue, CargoValue_Curr,
		FRTTerm, FRTType, PPCurCode, PPEXRate, CCCurCode, CCEXRate,
		//ContNo,  				//fzpacr_connr
		//ContType, 			//typ
		//CtnTotGrossWGT, 		//grgew
		//TaraWGT,				//trgew
		//CtnTOTWGT, 			//negew
		//CtnTempUOM, 			//temte
		//CtnLowestTemp, 		//temtu
		//CtnHighestTemp,  		//tempo
		//SealNo, 				//siegel1
		//SealNo2,				//siegel1		
		CustomsDeclType,        //in KIDS in GoodsItems.CustomsDeclarationType,       : artzol
		CargoItem,
		RefNo, 					//fzpmrn_mrn
		Container,     //Containers, 
		Jobkcxcustomdecl,             //EI20120705
		Remark,
	}

	public void startElement(Enum tag, String value, Attributes attr) {
		String dummy;
		dummy = "test";
		if (value == null) {			
			switch ((EMsgKff) tag) {					
				case CargoItem:
					CargoItem item = new CargoItem(getScanner());
					item.parse(tag.name());
					addCargoItemList(item);
					break;	
				case RefNo:
					RefNo mrn = new RefNo(getScanner());
					mrn.parse(tag.name());	
					addRefNoList(mrn);
					break;	
				case Container:
				//case Containers:
					Container cont = new Container(getScanner());
					cont.parse(tag.name()); 
					addContainerList(cont);
					break;
				case Sea:
					sea = new Sea(getScanner());
					sea.parse(tag.name());	
					if (sea != null && !Utils.isStringEmpty(sea.getBookingNumber())) {
						this.bookingNo = sea.getBookingNumber();
					}
					break;
				case Jobkcxcustomdecl:
					jobKCX = new JobKCX(getScanner());
					jobKCX.parse(tag.name());
					this.isBL();   //EI20130521
					break;
					
				case Remark: 
					Remark remark = new Remark(getScanner());
					remark.parse(tag.name());	
					addRemarkList(remark);
					break;
									
				default:
					return;
			}
		} else {
			switch ((EMsgKff) tag) {
			case JobNo:
				setJobNo(value);
				break;
			case JobDate:
				setJobDate(value);
				break;
			case FuncCode:
				setFuncCode(value);
				break;
			case CarrierCode:
				setCarrierCode(value);
				break;
			case ShprEORINo:
				setShprEORINo(value);
				break;
			case ShprName:
				setShprName(value);
				break;
			case Shpr_PIC:
				setShprPIC(value);
				break;
			case Shpr_Tel:
				setShprTel(value);
				break;
			case Shpr_Fax:
				setShprFax(value);
				break;
			case ShprContactEmail:
				setShprEmail(value);
				break;
			case ShprBEFunction:
				setShprBEFunction(value);
				break;
			//case ItemHazardContact:
			//case ItemHazardContactTel:
			case DeclPlace:
				setDeclPlace(value);
				break;
				
			case MotherVoyage:  //AK20120403
				setMotherVoyage(value);
				break;
			case MotherVesselCallSign:
				setMotherVesselCallSign(value);
				break;
			case MotherVesselName:
				setMotherVesselName(value);
				break;
			case MotherVesselETA:
				setMotherVesselEta(value);
				break;
			case MotherVesselETD:
				setMotherVesselEtd(value);
				break;				
			case MotherVesselCode:        
				setMotherVesselCode(value);
				break;
			case MotherVesselLioydsregno:
				setMotherVesselLioydsregno(value);
				break;
				
			case POLCity:
				setPolCity(value);
				break;
			case POLCtry:
				setPolCtry(value);
				break;
			case PODCity:
				setPodCity(value);
				break;
			case PODCtry:
				setPodCtry(value);
				break;
			case DestCity:
				setDestCity(value);
				break;
			case DestCtry:
				setDestCtry(value);
				break;
			case DestName:
				setDestName(value);
				break;
			case DevryCity:
				setDevryCity(value);
				break;
			case DevryCtry:
				setDevryCtry(value);
				break;
			case DevryName:
				setDevryName(value);
				break;
				/*
			case BookingNo:		
				setBookingNo(value);
				break;
			case BookingDate: 			
				setBookingDate(value);
				break;
				*/
			case ShpNo:
				setShpNo(value);
				break;
			case SrvReqTruckNo:
				setSrvReqTruckNo(value);
				break;
			case SrvReqSubcName:
				setSrvReqSubcName(value);
				break;
			case CustomsDeclType:
				setCustomsDeclType(value);
				break;
			case PartyID_Shpr:
				setShprPartyId(value);
				break;
			case ShprCTRYCode:
			case ShprCtryCode:
				setShprCtryCode(value);
				break;
			case ShprPostalCode:
				setShprPostalCode(value);
				break;
			case ShprAddr1:
				//shprAddrList[0] = value;
				shprAddrList.add(value);
				break;
			case ShprAddr2:
				//shprAddrList[1] = value;
				shprAddrList.add(value);
				break;
			case ShprAddr3:
				//shprAddrList[2] = value;
				shprAddrList.add(value);
				break;
			case ShprAddr4:
				//shprAddrList[3] = value;
				shprAddrList.add(value);
				break;
			case CsgnName:
				setCsgnName(value);
				break;
			case CsgnAddr1:
				//csgnAddrList[0] = value;
				csgnAddrList.add(value);
				break;
			case CsgnAddr2:
				//csgnAddrList[1] = value;
				csgnAddrList.add(value);
				break;
			case CsgnAddr3:
				//csgnAddrList[2] = value;
				csgnAddrList.add(value);
				break;
			case CsgnAddr4:
				//csgnAddrList[3] = value;
				csgnAddrList.add(value);
				break;
			case PartyID_Csgn: 
				setCsgnPartyId(value);
				break;
			case CsgnCTRYCode:
				setCsgnCTRYCode(value);
				break;
			case CsgnPostalCode:
				setCsgnPostalCode(value);
				break;
			case CsgnEORINo:
				setCsgnEORINo(value);
				break;
			case Csgn_PIC:
				setCsgnPIC(value);
				break;
			case Csgn_Tel:
				setCsgnTel(value);
				break;
			case Csgn_Fax:
				setCsgnFax(value);
				break;
			case CsgnContactEmail:
				setCsgnEmail(value);
				break;
			case TOTGWGT:
				setTotGWGT(value);
				break;
			case TOTPCS:
				setTotPCS(value);
				break;
			case TOTVOL:
				setTotVOL(value);
				break;
			case CargoValue:
				setCargoValue(value);
				break;
			case CargoValue_Curr:
				setCargoValueCurr(value);
				break;
			case JobType:
				setJobType(value);
				break;
			case UNID:
				setUnid(value);
				break;
			case ShpType:            //AK20130516
				setShpType(value);
				break;
				/*
				 ContNo,  //fzpacr_connr
		ContType, //typ
		CtnTotGrossWGT, //grgew
		TaraWGT, //trgew
		CtnTOTWGT, //negew
		CtnTempUOM, //temte
		CtnLowestTemp, //temtu
		CtnHighestTemp,  //tempo
		SealNo, //siegel1
		SealNo2,
				 */
			default:
				return;
			}
		}	
	}

	/*
	public void stoppElement(Enum tag) {
		String dummy;
		dummy = "";
	}

	@Override
	public Enum translate(String token) { 
		String dummy;
		dummy = "test";
		try {
  			return EMsgKff.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}	
	*/
	public void stoppElement(Enum tag) {
	}

	public Enum translate(String token) {
		try {
				return EMsgKff.valueOf(token);
			} catch (IllegalArgumentException e) {
				return null;
			}
	}
	
  
	public String getJobNo() {
		return jobNo;	
	}
	public void setJobNo(String argument) {
		this.jobNo = argument;
	}
	
	public String getJobDate() {
		return jobDate;	
	}
	public void setJobDate(String argument) {
		this.jobDate = argument;
	}
	
	public String getFuncCode() {
		return funcCode;	
	}
	public void setFuncCode(String argument) {
		this.funcCode = argument;
	}
	
	public String getCarrierCode() {
		return carrierCode;
	}
	public void setCarrierCode(String argument) {
		this.carrierCode = argument;
	}					
		
	public String getShprEORINo() {
		return shprEORINo;
	}
	public void setShprEORINo(String argument) {
		this.shprEORINo = argument;
	}
	
	public String getShprName() {
		return shprName;
	}
	public void setShprName(String argument) {
		this.shprName = argument;
	}	
	
	public String getShprPIC() {
		return shprPIC;
	}
	public void setShprPIC(String argument) {
		this.shprPIC = argument;
	}	
	
	public String getShprTel() {
		return shprTel;
	}
	public void setShprTel(String argument) {
		this.shprTel = argument;
	}
	
	public String getShprFax() {
		return shprFax;
	}
	public void setShprFax(String argument) {
		this.shprFax = argument;
	}
	
	public String getItemHazardContact() {
		return itemHazardContact;
	}
	public void setItemHazardContact(String argument) {
		this.itemHazardContact = argument;
	}
	
	public String getItemHazardContactTel() {
		return itemHazardContactTel;
	}
	public void setItemHazardContactTel(String argument) {
		this.itemHazardContactTel = argument;
	}
	
	public String getDeclPlace() {
		return declPlace;
	}
	public void setDeclPlace(String argument) {
		this.declPlace = argument;
	}

	public String getMotherVesselCallSign() {
		return motherVesselCallSign;
	}
	public void setMotherVesselCallSign(String argument) {
		this.motherVesselCallSign = argument;
	}	
		
	public String getMotherVesselName() {
		return motherVesselName;
	}
	public void setMotherVesselName(String argument) {
		this.motherVesselName = argument;
	}	
	
	public String getMotherVesselEta() {
		return motherVesselETA;
	}
	public void setMotherVesselEta(String argument) {
		//AK20121203 
		String dateETA = argument;
		if (dateETA != null && dateETA.length() > 8) {
			dateETA = dateETA.substring(0, 8); 
		}
		this.motherVesselETA = dateETA;
	}	
		
	public String getMotherVesselEtd() {
		return motherVesselETD;
	}
	public void setMotherVesselEtd(String argument) {
		//AK20121203
		String dateETD = argument;
		if (dateETD != null && dateETD.length() > 8) {
			dateETD = dateETD.substring(0, 8);
		}
		this.motherVesselETD = dateETD;
	}	
	
	public String getMotherVesselCode() {
		return motherVesselCode;
	}
	public void setMotherVesselCode(String argument) {
		this.motherVesselCode = argument;
	}
	
	public String getPolCity() {
		return polCity;
	}
	public void setPolCity(String argument) {
		this.polCity = argument;
	}	
		
	public String getPolCtry() {
		return polCtry;
	}
	public void setPolCtry(String argument) {
		this.polCtry = argument;
	}
	//Utils.checkNull
	public String getPlaceOfLoadingCode() {
		String code = "";
		if (polCtry != null && polCtry.length() == 2 &&
		    polCity != null &&  polCity.length() == 3) {
			code = polCtry + polCity;
		}
		return code;
	}
	
	public String getPodCity() {
		return podCity;
	}
	public void setPodCity(String argument) {
		this.podCity = argument;
	}	
	
	public String getPodCtry() {
		return podCtry;
	}
	public void setPodCtry(String argument) {
		this.podCtry = argument;
	}	
	public String getPlaceOfDischargeCode() {
		String code = "";
		if (podCtry != null && podCtry.length() == 2 &&
		    podCity != null &&  podCity.length() == 3) {
			code = podCtry + podCity;
		}
		return code;
	}
	
	public String getDestCity() {
		return destCity;
	}
	public void setDestCity(String argument) {
		this.destCity = argument;
	}	
	 
	public String getDestCtry() {
		return destCtry;
	}
	public void setDestCtry(String argument) {
		this.destCtry = argument;
	}	
	
	public String getDestName() {
		return destName;
	}
	public void setDestName(String argument) {
		this.destName = argument;
	}		
	
	public String getDevryCity() {
		return devryCity;
	}
	public void setDevryCity(String argument) {
		this.devryCity = argument;
	}	
	 
	public String getDevryCtry() {
		return devryCtry;
	}
	public void setDevryCtry(String argument) {
		this.devryCtry = argument;
	}	
	
	public String getDevryName() {
		return devryName;
	}
	public void setDevryName(String argument) {
		this.devryName = argument;
	}	
	
	public String getBookingNo() {
		return bookingNo;
	}
	public void setBookingNo(String argument) {
		this.bookingNo = argument;
	}	
	
	public String getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(String argument) {
		this.bookingDate = argument;
	}	
	
	public String getShpNo() {
		return shpNo;
	}
	public void setShpNo(String argument) {
		this.shpNo = argument;
	}	
	
	public String getSrvReqTruckNo() {
		return srvReqTruckNo;
	}
	public void setSrvReqTruckNo(String argument) {
		this.srvReqTruckNo = argument;
	}
	
	public String getSrvReqSubcName() {
		return srvReqSubcName;
	}
	public void setSrvReqSubcName(String argument) {
		this.srvReqSubcName = argument;
	}	
	/*
	public String getcarrierCode() {
		return carrierCode;
	}
	public void setcarrierCode(String argument) {
		this.carrierCode = argument;
	}	
*/
	public List <CargoItem> getCargoItemList() {
		return cargoItemList;
	}
	public void setCargoItemList(List <CargoItem> list) {
		this.cargoItemList = list;
	}	
	public void addCargoItemList(CargoItem item) {
		if (cargoItemList == null) {
			cargoItemList = new Vector <CargoItem>();
		}
		cargoItemList.add(item);
	}
	
	
	public List <RefNo> getRefNoList() {
		return refNoList;
	}
	public void setRefNoList(List <RefNo> list) {
		this.refNoList = list;
	}	
	public void addRefNoList(RefNo item) {
		if (refNoList == null) {
			refNoList = new Vector <RefNo>();
		}
		refNoList.add(item);
	}
		
	public List <Container> getContainerList() {
		return containerList;
	}
	public void setContainerList(List <Container> list) {
		this.containerList = list;
	}	
	public void addContainerList(Container item) {
		if (containerList == null) {
			containerList = new Vector <Container>();
		}
		containerList.add(item);
	}
	//AK20120403
	public String getMotherVoyage() {
		return motherVoyage;
	}

	public void setMotherVoyage(String motherVoyage) {
		this.motherVoyage = Utils.checkNull(motherVoyage);
	}
	
	
	public String getMotherVesselLioydsregno() {
		return motherVesselLioydsregno;
	}

	public void setMotherVesselLioydsregno(String motherVoyage) {
		this.motherVesselLioydsregno = Utils.checkNull(motherVoyage);
	}
	
	//EI20120412:	
	public String getCustomsDeclType() {
		return customsDeclType;
	}
	public void setCustomsDeclType(String value) {
		this.customsDeclType = value;
	}
	
	public String getKaiCde() {
		return kaiCde;
	}
	public void setKaiCde(String value) {
		this.kaiCde = value;
	}
	
	public String getRdrCde() {
		return rdrCde;
	}
	public void setRdrCde(String value) {
		this.rdrCde = value;
	}
	
	public String getShprPartyId() {
		return shprPartyId;
	}
	public void setShprPartyId(String value) {
		this.shprPartyId = value;
	}	
	
	public String getShprCtryCode() {
		return shprCtryCode;
	}
	public void setShprCtryCode(String value) {
		this.shprCtryCode = value;
	}	
	
	public String getShprPostalCode() {
		return shprPostalCode;
	}
	public void setShprPostalCode(String value) {
		this.shprPostalCode = value;
	}	
	
	public String getShprEmail() {
		return shprEmail;
	}
	public void setShprEmail(String value) {
		this.shprEmail = value;
	}	
	
	public String getShprBEFunction() {
		return shprBEFunction;
	}
	public void setShprBEFunction(String value) {
		this.shprBEFunction = value;
	}
	
	//public String[] getShprAddrList() {  
	public List<String> getShprAddrList() {   
		return shprAddrList;
	}
	public void setShprAddrList(List<String> list) {
		this.shprAddrList = list;
	}	
	
	public String getCsgnName() {
		return csgnName;
	}
	public void setCsgnName(String value) {
		this.csgnName = value;
	}
	
	//public String[] getCsgnAddrList() {   
	public List<String> getCsgnAddrList() {   
		return csgnAddrList;
	}
	public void setCsgnAddrList(List<String> list) {
		this.csgnAddrList = list;
	}
	
	public String getCsgnPartyId() {
		return csgnPartyId;
	}
	public void setCsgnPartyId(String value) {
		this.csgnPartyId = value;
	}	
	
	public String getCsgnCTRYCode() {
		return csgnCTRYCode;
	}
	public void setCsgnCTRYCode(String value) {
		this.csgnCTRYCode = value;
	}	
	
	public String getCsgnPostalCode() {
		return csgnPostalCode;
	}
	public void setCsgnPostalCode(String value) {
		this.csgnPostalCode = value;
	}
	
	public String getTotGWGT() {
		return totGWGT;
	}
	public void setTotGWGT(String value) {
		this.totGWGT = value;
	}	
	
	public String getTotPCS() {
		return totPCS;
	}
	public void setTotPCS(String value) {
		this.totPCS = value;
	}
	
	public String getTotVOL() {
		return totVOL;
	}
	public void setTotVOL(String value) {
		this.totVOL = value;
	}	
	
	public String getCargoValue() {
		return cargoValue;
	}
	public void setCargoValue(String value) {
		this.cargoValue = value;
	}
	
	public String getCargoValueCurr() {
		return cargoValueCurr;
	}
	public void setCargoValueCurr(String value) {
		this.cargoValueCurr = value;
	}
	
	public String getTypeOfPermit() {
		return typeOfPermit;
	}
	public void setTypeOfPermit(String value) {
		this.typeOfPermit = value;
	}
	
	public String getFRTTerm() {
		return frtTerm;
	}
	public void setFRTTerm(String value) {
		this.frtTerm = value;
	}

	public String getJobType() {
		return jobType;
	}
	public void setJobType(String value) {
		this.jobType = value;
	}
	
	public String getUnid() {
		return unid;
	}
	public void setUnid(String value) {
		this.unid = value;
	}
	
	public String getCsgnEORINo() {
		return csgnEORINo;
	}
	public void setCsgnEORINo(String argument) {
		this.csgnEORINo = argument;
	}
	
	public String getCsgnPIC() {
		return csgnPIC;
	}
	public void setCsgnPIC(String argument) {
		this.csgnPIC = argument;
	}	
	
	public String getCsgnTel() {
		return csgnTel;
	}
	public void setCsgnTel(String argument) {
		this.csgnTel = argument;
	}
	
	public String getCsgnFax() {
		return csgnFax;
	}
	public void setCsgnFax(String argument) {
		this.csgnFax = argument;
	}
	
	public String getCsgnEmail() {
		return csgnEmail;
	}
	public void setCsgnEmail(String value) {
		this.csgnEmail = value;
	}
	
	public JobKCX getJobKCX() {   //EI20120705
		return jobKCX;
	}
	public void setJobKCX(JobKCX argument) {
		this.jobKCX = argument;
	}
	public List <Remark> getRemarkList() {
		return remarkList;
	}
	public void setRemarkList(List <Remark> list) {
		this.remarkList = list;
	}	
	public void addRemarkList(Remark item) {
		if (remarkList == null) {
			remarkList = new Vector <Remark>();
		}
		remarkList.add(item);
	}

	public Sea getSea() {
		return sea;
	}

	public void setSea(Sea sea) {
		this.sea = sea;
	}


	public boolean isBL() {    //EI20130517
		if (this.jobKCX != null) {
			if (!Utils.isStringEmpty(this.jobKCX.getDakosyMsgType())) {
				if (this.jobKCX.getDakosyMsgType().equals("1")) {
					isBL = true;
				}
			}  else if (!Utils.isStringEmpty(this.jobKCX.getDBHMsgType())) {
				if (this.jobKCX.getDBHMsgType().equals("1")) {
					isBL = true;
				}
			}
		}
		return isBL;
	}

	public String getShpType() {
		return shpType;
	}

	public void setShpType(String shpType) {
		this.shpType = Utils.checkNull(shpType);
	}
	

}
