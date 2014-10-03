package com.kewill.kcx.component.mapping.formats.kex.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

public class Sheucustoms extends KCXMessage {
					
	private String saddeclarationdivision1;	
	private String saddeclarationdivision2;	
	private String idofwarehousedeparture;	
	private String methodofpayment;			
	private String transportidatbord;		
	private String transportidatdep;		
	private String transportidatdepctycode;	
	private String transportmotdepart;		
	private String transportmotborder;		
	private String transportidatbordctycode;
	private String locationofgoodscode;		
	private String locationofgoods;			
	private String officeofexit;			
	private String routectycode;			
	private String startloadingdatetime;					
	private String endloadingdatetime;		
	private String specialcircumstance;		
	private String gbnesdocumentstatus;		
	private String gbnesdocumentcode;		
	private String gbnesdocumentref;		
	private String gbnesdocumentreason;		
	private String gbnesprevdoctype;		
	private String gbnesprevdocref;			
	private String ibeaclaimref;			
	private String ibeaclaimtype;			
	private String ibregnumber;
	private String ibguaranteenumber;
	
	private String  euducr;                  //EI20120106
	private String  ducrpart;                //EI20120106
	private String  eumucr;                  //EI20120106
	private String  mucrpart;                //EI20120106
	
	
	private String declNumber;	
	private String containerIdentifier;			
	private String placeOfGoods;				
	private String commercialRefNr;				
	private String ecsStatusCode;
	
	private String  inTransit;                  //EI20130129

	 public Sheucustoms() {
		 super();  
	 }

	 public Sheucustoms(XmlMsgScanner scanner) {
 		super(scanner);
	 }

	 private enum ESheucustomsesTags {	
			// KEX							KIDS
			saddeclarationdivision1,		//AreaCode
			saddeclarationdivision2,		//TypeOfPermit
			idofwarehousedeparture,			//Warehouse / WarehouseIdentification
			methodofpayment,				//PaymentType
			transportidatbord,				//MeansOfTransportBorder / TransportationNumber
			transportidatdep,				//MeansOfTransportDeparture / TransportationNumber
			transportidatdepctycode,		//MeansOfTransportDeparture / TransportationCountry
			transportmotdepart,				//MeansOfTransportDeparture / TransportationType
			transportmotborder,				//MeansOfTransportBorder / TransportMode
			transportidatbordctycode,		//MeansOfTransportBorder / TransportationCountry
			locationofgoodscode,			//PlaceOfLoading / Code
			locationofgoods,				//PlaceOfLoading / AgreedLocationOfGoods
			officeofexit,					//IntendedOfficeOfExit
			routectycode,					//TransportationRoute / Country
			startloadingdatetime,			//LoadingTime / BeginTime			
			endloadingdatetime,				//LoadingTime / EndTime
			specialcircumstance,			//SituationCode			
			ibeaclaimref,					//CAP / IBClaimRef
			ibeaclaimtype,					//CAP / IBClaimType
			ibregnumber,					//CAP / IBRegNo
			ibguaranteenumber,				//CAP / IBGAN
			
			//V03: die Tags wurden von hier entfernt: bilden neue klasse Shcustomsdoc
			gbnesdocumentstatus,			//Document / Qualifier
			gbnesdocumentcode,				//Document / Type
			gbnesdocumentref,				//Document / Reference
			gbnesdocumentreason,			//Document / Reason
			gbnesprevdoctype,				//PreviousDocument / DocumentType
			gbnesprevdocref,				//PreviousDocument / Reference
			
			ducrpart,                      //EI20120106
			mucrpart,                      //EI20120106
			eumucr,
			euducr,                        //EI20120106
			declNumber,					    //DeclarationNumber
			containerIdentifier,			//TransportInContainer
			placeOfGoods,					//MeansOfTransport/PlaceOfLoading
			commercialRefNr,				//UCRNumber
			ecsStatusCode,
			inTransit,
	 }	 

	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {		
			switch ((ESheucustomsesTags) tag) {
				default: return;			
			}
		} else {			
			switch ((ESheucustomsesTags) tag) {			
				case saddeclarationdivision1:
					setSaddeclarationdivision1(value);
					break;
				case saddeclarationdivision2:
					setSaddeclarationdivision2(value);
					break;
				case idofwarehousedeparture:
					setIdofwarehousedeparture(value);
					break;
				case methodofpayment:
					setMethodofpayment(value);
					break;
				case transportidatbord:
					setTransportidatbord(value);
					break;
				case transportidatdep:
					setTransportidatdep(value);
					break;
				case transportidatdepctycode:
					setTransportidatdepctycode(value);
					break;
				case transportmotdepart:
					setTransportmotdepart(value);
					break;
				case transportmotborder:
					setTransportmotborder(value);
					break;
				case transportidatbordctycode:
					setTransportidatbordctycode(value);
					break;
				case locationofgoodscode:
					setLocationofgoodscode(value);
					break;
				case locationofgoods:
					setLocationofgoods(value);
					break;
				case officeofexit:
					setOfficeofexit(value);
					break;
				case routectycode:
					setRoutectycode(value);
					break;
				case startloadingdatetime:
					setStartloadingdatetime(value);
					break;
				case endloadingdatetime:
					setEndloadingdatetime(value);
					break;
				case specialcircumstance:
					setSpecialcircumstance(value);
					break;
				case gbnesdocumentstatus:
					setGbnesdocumentstatus(value);
					break;
				case gbnesdocumentcode:
					setGbnesdocumentcode(value);
					break;
				case gbnesdocumentref:
					setGbnesdocumentref(value);
					break;
				case gbnesdocumentreason:
					setGbnesdocumentreason(value);
					break;
				case gbnesprevdoctype:
					setGbnesprevdoctype(value);
					break;
				case gbnesprevdocref:
					setGbnesprevdocref(value);
					break;
				case ibeaclaimref:
					setIbeaclaimref(value);
					break;
				case ibeaclaimtype:
					setIbeaclaimtype(value);
					break;
				case ibregnumber:
					setIbregnumber(value);
					break;
				case ibguaranteenumber:
					setIbguaranteenumber(value);
					break;
				case declNumber:
					setDeclNumber(value);
					break;
				case containerIdentifier:
					setContainerIdentifier(value);
					break;
				case placeOfGoods:
					setPlaceOfGoods(value);
					break;
				case commercialRefNr:
					setCommercialRefNr(value);
					break;
				case ecsStatusCode:
					setEcsStatusCode(value);
					break;
				case euducr:
					setEuducr(value);
					break;
				case ducrpart:                      //EI20120106
					setDucrpart(value);
					break;
				case eumucr:
					setEumucr(value);
					break;
				case mucrpart:                      //EI20120106
					setMucrpart(value);
					break;
				case inTransit:                    //EI20130129
					setInTransit(value);
					break;
				default:
					return;
			}
		}
		
	}

	@Override
	public void stoppElement(Enum tag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Enum translate(String token) {
		try {
				return ESheucustomsesTags.valueOf(token);
		    } catch (IllegalArgumentException e) {
				return null;
		}	
	}

	public String getSaddeclarationdivision1() {
		return saddeclarationdivision1;
	}

	public void setSaddeclarationdivision1(String saddeclarationdivision1) {
		this.saddeclarationdivision1 = Utils.checkNull(saddeclarationdivision1);
	}

	public String getSaddeclarationdivision2() {
		return saddeclarationdivision2;
	}

	public void setSaddeclarationdivision2(String saddeclarationdivision2) {
		this.saddeclarationdivision2 = Utils.checkNull(saddeclarationdivision2);
	}

	public String getIdofwarehousedeparture() {
		return idofwarehousedeparture;
	}

	public void setIdofwarehousedeparture(String idofwarehousedeparture) {
		this.idofwarehousedeparture = Utils.checkNull(idofwarehousedeparture);
	}

	public String getMethodofpayment() {
		return methodofpayment;
	}

	public void setMethodofpayment(String methodofpayment) {
		this.methodofpayment = Utils.checkNull(methodofpayment);
	}

	public String getTransportidatbord() {
		return transportidatbord;
	}

	public void setTransportidatbord(String transportidatbord) {
		this.transportidatbord = Utils.checkNull(transportidatbord);
	}

	public String getTransportidatdep() {
		return transportidatdep;
	}

	public void setTransportidatdep(String transportidatdep) {
		this.transportidatdep = Utils.checkNull(transportidatdep);
	}

	public String getTransportidatdepctycode() {
		return transportidatdepctycode;
	}

	public void setTransportidatdepctycode(String transportidatdepctycode) {
		this.transportidatdepctycode = Utils.checkNull(transportidatdepctycode);
	}

	public String getTransportmotdepart() {
		return transportmotdepart;
	}

	public void setTransportmotdepart(String transportmotdepart) {
		this.transportmotdepart = Utils.checkNull(transportmotdepart);
	}

	public String getTransportmotborder() {
		return transportmotborder;
	}

	public void setTransportmotborder(String transportmotborder) {
		this.transportmotborder = Utils.checkNull(transportmotborder);
	}

	public String getTransportidatbordctycode() {
		return transportidatbordctycode;
	}

	public void setTransportidatbordctycode(String transportidatbordctycode) {
		this.transportidatbordctycode = Utils.checkNull(transportidatbordctycode);
	}

	public String getLocationofgoodscode() {
		return locationofgoodscode;
	}

	public void setLocationofgoodscode(String locationofgoodscode) {
		this.locationofgoodscode = Utils.checkNull(locationofgoodscode);
	}

	public String getLocationofgoods() {
		return locationofgoods;
	}

	public void setLocationofgoods(String locationofgoods) {
		this.locationofgoods = Utils.checkNull(locationofgoods);
	}

	public String getOfficeofexit() {
		return officeofexit;
	}

	public void setOfficeofexit(String officeofexit) {
		this.officeofexit = Utils.checkNull(officeofexit);
	}

	public String getRoutectycode() {
		return routectycode;
	}

	public void setRoutectycode(String routectycode) {
		this.routectycode = Utils.checkNull(routectycode);
	}

	public String getStartloadingdatetime() {
		return startloadingdatetime;
	}

	public void setStartloadingdatetime(String startloadingdatetime) {
		this.startloadingdatetime = Utils.checkNull(startloadingdatetime);
	}

	public String getEndloadingdatetime() {
		return endloadingdatetime;
	}

	public void setEndloadingdatetime(String endloadingdatetime) {
		this.endloadingdatetime = Utils.checkNull(endloadingdatetime);
	}

	public String getSpecialcircumstance() {
		return specialcircumstance;
	}

	public void setSpecialcircumstance(String specialcircumstance) {
		this.specialcircumstance = Utils.checkNull(specialcircumstance);
	}

	public String getGbnesdocumentstatus() {
		return gbnesdocumentstatus;
	}

	public void setGbnesdocumentstatus(String gbnesdocumentstatus) {
		this.gbnesdocumentstatus = Utils.checkNull(gbnesdocumentstatus);
	}

	public String getGbnesdocumentcode() {
		return gbnesdocumentcode;
	}

	public void setGbnesdocumentcode(String gbnesdocumentcode) {
		this.gbnesdocumentcode = Utils.checkNull(gbnesdocumentcode);
	}

	public String getGbnesdocumentref() {
		return gbnesdocumentref;
	}

	public void setGbnesdocumentref(String gbnesdocumentref) {
		this.gbnesdocumentref = Utils.checkNull(gbnesdocumentref);
	}

	public String getGbnesdocumentreason() {
		return gbnesdocumentreason;
	}

	public void setGbnesdocumentreason(String gbnesdocumentreason) {
		this.gbnesdocumentreason = Utils.checkNull(gbnesdocumentreason);
	}

	public String getGbnesprevdoctype() {
		return gbnesprevdoctype;
	}
	public void setGbnesprevdoctype(String gbnesprevdoctype) {
		this.gbnesprevdoctype = Utils.checkNull(gbnesprevdoctype);
	}

	public String getGbnesprevdocref() {
		return gbnesprevdocref;
	}
	public void setGbnesprevdocref(String gbnesprevdocref) {
		this.gbnesprevdocref = Utils.checkNull(gbnesprevdocref);
	}

	public String getIbeaclaimref() {
		return ibeaclaimref;
	}
	public void setIbeaclaimref(String ibeaclaimref) {
		this.ibeaclaimref = Utils.checkNull(ibeaclaimref);
	}

	public String getIbeaclaimtype() {
		return ibeaclaimtype;
	}
	public void setIbeaclaimtype(String ibeaclaimtype) {
		this.ibeaclaimtype = Utils.checkNull(ibeaclaimtype);
	}

	public String getIbregnumber() {
		return ibregnumber;
	}
	public void setIbregnumber(String ibregnumber) {
		this.ibregnumber = Utils.checkNull(ibregnumber);
	}

	public String getIbguaranteenumber() {
		return ibguaranteenumber;
	}
	public void setIbguaranteenumber(String ibguaranteenumber) {
		this.ibguaranteenumber = Utils.checkNull(ibguaranteenumber);
	}

	public String getEuducr() {
		return euducr;
	}
	public void setEuducr(String value) {
		this.euducr = Utils.checkNull(value);
	}
	
	public String getDucrpart() {
		return ducrpart;
	}
	public void setDucrpart(String value) {
		this.ducrpart = Utils.checkNull(value);
	}
	
	public String getMucrpart() {
		return mucrpart;
	}
	public void setMucrpart(String value) {
		this.mucrpart = Utils.checkNull(value);
	}
		
	public String getEumucr() {
		return eumucr;
	}
	public void setEumucr(String value) {
		this.eumucr = Utils.checkNull(value);
	}

	public String getDeclNumber() {
		return declNumber;
	}

	public void setDeclNumber(String declNumber) {
		this.declNumber = Utils.checkNull(declNumber);
	}

	public String getContainerIdentifier() {
		return containerIdentifier;
	}

	public void setContainerIdentifier(String containerIdentifier) {
		this.containerIdentifier = Utils.checkNull(containerIdentifier);
	}

	public String getPlaceOfGoods() {
		return placeOfGoods;
	}
	public void setPlaceOfGoods(String placeOfGoods) {
		this.placeOfGoods = Utils.checkNull(placeOfGoods);
	}

	public String getCommercialRefNr() {
		return commercialRefNr;
	}

	public void setCommercialRefNr(String commercialRefNr) {
		this.commercialRefNr = Utils.checkNull(commercialRefNr);
	}

	public String getEcsStatusCode() {
		return ecsStatusCode;
	}

	public void setEcsStatusCode(String ecsStatusCode) {
		this.ecsStatusCode = Utils.checkNull(ecsStatusCode);
	}
		
	public String getInTransit() {
		return inTransit;
	}
	public void setInTransit(String value) {
		this.inTransit = value;
	}
}
