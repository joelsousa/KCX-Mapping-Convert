package com.kewill.kcx.component.mapping.formats.kex.common;

import java.util.List;
import java.util.Vector;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

public class Shheader extends KCXMessage {
	
	 private String 			countryofdespatchcode;
	 private String 			countryofdestinationcode;
	 private String 			totalgrossweightcustoms;
	 private String				totnocus;
	 private String				totnopackscustom;            //EI20120118
	 private Sheucustoms		sheucustoms;
	 private List <Sheucustoms> sheucustomsList;            //EI20111215: liste war in V02, in V03 nicht mehr
	 private List<Shcustomsdoc> shcustomsdocsList;           //EI20111215
	 private List<Shpreviousdoc> shpreviousdocsList;         //EI20120117
	 private List<Shcustomsaitext>shcustomsaitextsList;      //EI20111215

	
	 private Shaddress  		forwarder;
	 private Shaddress  		consignor;
	 private Shaddress  		customsDeclarant;
	 private Shaddress  		exporter;   //EI20120105: nicht gemapped, trotzdem eingefuegt
	 private Shaddress  		agents;
	 private Shaddress  		consignee;
	 private Shaddress  		warehouse;
	 private Shaddress  		carrier;
	 private Shaddress  		supervisingCustomsOffice;
	 
	 private String portofloadingcode;      //EI2012023	 
	 private Shtermses shtermses;           //AK20121016
	 private String  officeofdeclaration;   //EI20121210  
	 private Shintrastats shintrastats;   	//EI20121212
	 private String  motcode;  			    //EI20121212       
	 private String  invinvoiceamounttotal;   //EI20121212 
	 private String  headercurrency;  			    //EI20121212   
	 
	 private List<Sicustomitem> sicustomitemsList;
	 private List<Sicommercialitem> sicommercialitemsList;   //EI20121213
	 
	 public Shheader() {
		 super();  
	 }

	 public Shheader(XmlMsgScanner scanner) {
 		super(scanner);
	 }

	 private enum EShheaderTags {	
			// KEX							KIDS
		 countryofdespatchcode,				//DestinationCountry
		 countryofdestinationcode,			//GoodsDeclaration / DestinationCountry
		 totalgrossweightcustoms,			//GrossMass		 
		 totnocus,							//TotalNumberPositions
		 totnopackscustoms,      //EI20120118: TotalNumberOfPackages EI20120224: was: totnopackscustom		 
		 shaddresses,						//Address-Types
		 sheucustomses,    //EI29111215 aus sheucustomses wird sheucustoms, und doc- und text-tags bilden neue strukturen:
		 sheucustoms,      // shcustomsdoc und shcustomsaitext  
		 shcustomsdoc,  shcustomsdocs,   		//EI20111215 aus sheucustoms sollen entspr. (wiederholbaren) Tags entfernt und in die neuen		 
		 shcustomsaitext, shcustomsaitexts, 	//EI20111215 shcustomsdoc und shcustomsaitext verschoben werden
		 shpreviousdoc, shpreviousdocs, 	 	
		 portofloadingcode,                       
		 shtermses,								
		 officeofdeclaration,
		 shintrastats,
		 motcode,
		 invinvoiceamounttotal,
		 headercurrency,		 
		 sicustomitems,                     //GoodsItems	
		 sicommercialitems,
	 }

	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {		
			switch ((EShheaderTags) tag) {
			case shaddresses:
				Shaddress shaddress = new Shaddress(getScanner());
				shaddress.parse(tag.name());		
				setAddress(shaddress);
			break;
			case sicustomitems:			
  				Sicustomitem item = new Sicustomitem(getScanner());				
  				item.parse(tag.name());
  				if (sicustomitemsList == null) {
  					sicustomitemsList = new Vector<Sicustomitem>();
				}
  				sicustomitemsList.add(item);
				break;
			case sheucustomses:	
			case sheucustoms:
				sheucustoms = new Sheucustoms(getScanner());
				sheucustoms.parse(tag.name());
				if (sheucustomsList == null) {
  					sheucustomsList = new Vector<Sheucustoms>();
				}
				sheucustomsList.add(sheucustoms);
				break;
		
			case shcustomsdoc:
			case shcustomsdocs:
				Shcustomsdoc shcustomsdoc = new Shcustomsdoc(getScanner());
				shcustomsdoc.parse(tag.name());
				if (shcustomsdocsList == null) {
					shcustomsdocsList = new Vector<Shcustomsdoc>();				
				}
				shcustomsdocsList.add(shcustomsdoc);
				break;
			case  shpreviousdoc:
			case shpreviousdocs:
				Shpreviousdoc shpreviousdoc = new Shpreviousdoc(getScanner());
				shpreviousdoc.parse(tag.name());
				if (shpreviousdocsList == null) {
					shpreviousdocsList = new Vector<Shpreviousdoc>();				
				}
				shpreviousdocsList.add(shpreviousdoc);
				break;
			case shcustomsaitext:
			case shcustomsaitexts:
				Shcustomsaitext shcustomsaitext = new Shcustomsaitext(getScanner());
				shcustomsaitext.parse(tag.name());
				if (shcustomsaitextsList == null) {
					shcustomsaitextsList = new Vector<Shcustomsaitext>();
				}
				shcustomsaitextsList.add(shcustomsaitext);
				break;
				
			case shtermses:
				shtermses = new Shtermses(getScanner());
				shtermses.parse(tag.name());
				break;
				
			case shintrastats:
				shintrastats = new Shintrastats(getScanner());
				shintrastats.parse(tag.name());
				break;		
				
			case sicommercialitems:
				Sicommercialitem sicommercialitem = new Sicommercialitem(getScanner());
				sicommercialitem.parse(tag.name());
				//addSicommercialitemList(sicommercialitem);
				break;
				
			default: return;			
			}
		} else {			
			switch ((EShheaderTags) tag) {			
				case countryofdespatchcode:
					setCountryofdespatchcode(value);
				break;
				case countryofdestinationcode:
					setCountryofdestinationcode(value);
				break;
				case totalgrossweightcustoms:
					setTotalgrossweightcustoms(value);
				break;
				case totnocus:
					setTotnocus(value);
				break;	
				case totnopackscustoms:
					setTotnopackscustom(value);
					break;
				case portofloadingcode:
					setPortofloadingcode(value);
					break;
				case officeofdeclaration:
					setOfficeofdeclaration(value);
					break;
				case motcode:
					setMotcode(value);
					break;
				case invinvoiceamounttotal:
					setInvinvoiceamounttotal(value);
					break;
				case headercurrency:
					setHeadercurrency(value);
					break;
				default: break;
			}
		}
	}
		
	//Kex address types stored as described in "Shipment+XML_API_v1.3.2.doc" 25.3	shipmentaddresstypecode
    private enum EShaddressesTypes {
    	CONSIGNEE,
    	BUYER,
    	EXPORTER,
    	WAREHOUSE,
    	PRODUCER,
    	INTERMEDIATECONSIGNEE,
    	PLACEOFDELIVERY,
    	AGENTS,
    	FORWARDER,
    	CARRIER,
    	CUSTOMSSUPERVISORYOFF,  SUPERVISINGCUSTOMSOFFICE,
    	PAYMENTBILLTO,
    	CUSTOMSBROKERS,
    	NOTIFY1,
    	NOTIFY2,
    	BANKLOCAL,
    	BANKOVERSEAS,
    	CUSTOMSDECLARANT,
    	CUSTOMSPRINCIPAL,
    	EXCISEWAREHOUSE,
    	CUSTOMSUPERVISORYOFF,
    	AUTHORISEDATDESPATCH,
    	TAXREPRESENTITIVE,
    	GUARANTEEORGANISATION,
    	CANADABOX19,
    	CANADABOX20,
    	CONSIGNOR,
    	OTHER1,
    	OTHER2;
	}


	private void setAddress(Shaddress shaddress) {
		if (shaddress == null) {
			return;
		}
		Enum <EShaddressesTypes>type = translateAddress(shaddress.getShipmentaddresstypecode());
		
		if (type == null) {  //EI20120925
			Utils.log("KEX: Unimplemented Addresstype of KIDS EXPORT: " + shaddress.getShipmentaddresstypecode() );	
			return;
		}
		switch ((EShaddressesTypes) type) {			
			case FORWARDER:
				forwarder = shaddress;
			break;
			case CONSIGNOR:
				consignor = shaddress;
			break;
			case CUSTOMSDECLARANT:
				customsDeclarant = shaddress;
			break;
			case AGENTS:
				agents = shaddress;
			break;
			case CONSIGNEE:
				consignee = shaddress;
			break;
			case CARRIER:
				carrier = shaddress;
				break;
			case EXPORTER: //EI20120105: nicht gemapped, trotzdem eingefuegt
				exporter = shaddress;
				break;
			case SUPERVISINGCUSTOMSOFFICE:   //EI201200620
			case CUSTOMSSUPERVISORYOFF:      //EI20120621 laut LeeMartin   
				supervisingCustomsOffice = shaddress;
				break;
		
			default: Utils.log("Addresstype is not used in KIDS-EXPORT: " + type.toString());
			break;
		}
	}

	@Override
	public void stoppElement(Enum tag) {
		
	}

	@Override
	public Enum translate(String token) {
		try {
  			return EShheaderTags.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}

	
	private Enum<EShaddressesTypes> translateAddress(String token) {
		try {
  			return EShaddressesTypes.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}

	public String getCountryofdespatchcode() {
		return countryofdespatchcode;
	}
	public void setCountryofdespatchcode(String countryofdespatchcode) {
		this.countryofdespatchcode = Utils.checkNull(countryofdespatchcode);
	}

	public String getTotalgrossweightcustoms() {
		return totalgrossweightcustoms;
	}
	public void setTotalgrossweightcustoms(String totalgrossweightcustoms) {
		this.totalgrossweightcustoms = Utils.checkNull(totalgrossweightcustoms);
	}

	public String getTotnocus() {
		return totnocus;
	}
	public void setTotnocus(String totnocus) {
		this.totnocus = Utils.checkNull(totnocus);
	}
	
	public String getCountryofdestinationcode() {
		return countryofdestinationcode;
	}
	public void setCountryofdestinationcode(String countryofdestinationcode) {
		this.countryofdestinationcode = Utils.checkNull(countryofdestinationcode);
	}	

	public Shaddress getExporter() {
		return exporter;
	}
	public void setExporter(Shaddress exporter) {
		this.exporter = exporter;
	}

	public Shaddress getForwarder() {
		return forwarder;
	}
	public void setForwarder(Shaddress forwarder) {
		this.forwarder = forwarder;
	}

	public Shaddress getConsignor() {
		return consignor;
	}
	public void setConsignor(Shaddress consignor) {
		this.consignor = consignor;
	}

	public Shaddress getAgents() {
		return agents;
	}
	public void setAgents(Shaddress agents) {
		this.agents = agents;
	}

	public Shaddress getConsignee() {
		return consignee;
	}
	public void setConsignee(Shaddress consignee) {
		this.consignee = consignee;
	}

	public Shaddress getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(Shaddress warehouse) {
		this.warehouse = warehouse;
	}

	public Shaddress getCarrier() {
		return carrier;
	}
	public void setCarrier(Shaddress carrier) {
		this.carrier = carrier;
	}
	
	public Shaddress getSupervisingCustomsOffice() {
		return supervisingCustomsOffice;
	}
	public void setSupervisingCustomsOffice(Shaddress carrier) {
		this.supervisingCustomsOffice = carrier;
	}
	
	public Sheucustoms getSheucustoms() {
		return sheucustoms;
	}
	public void setSheucustoms(Sheucustoms sheucustoms) {
		this.sheucustoms = sheucustoms;
	}	
	
	public List<Sheucustoms> getSheucustomsList() {
		return sheucustomsList;
	}
	public void setSheuCustomsesList(List<Sheucustoms> list) {
		this.sheucustomsList = list;
	}
	
	public List<Sicustomitem> getSicustomitemsList() {
		return sicustomitemsList;
	}
	public void setSicustomitemsList(List<Sicustomitem> sicustomitemsList) {
		this.sicustomitemsList = sicustomitemsList;
	}

	public List<Shcustomsdoc> getShcustomsdocsList() {
		return shcustomsdocsList;
	}
	public void setShcustomsdocsList(List<Shcustomsdoc> list) {
		this.shcustomsdocsList = list;
	}
	
	public List<Shpreviousdoc> getShpreviousdocsList() {  //EI20120117
		return shpreviousdocsList;
	}
	public void setShpreviousdocsList(List<Shpreviousdoc> list) {
		this.shpreviousdocsList = list;
	}
	
	public List<Shcustomsaitext> getShcustomsaitextsList() {
		return shcustomsaitextsList;
	}
	public void setShcustomsaitextsList(List<Shcustomsaitext> list) {
		this.shcustomsaitextsList = list;
	}	
	
	public Shaddress getCustomsDeclarant() {
		return customsDeclarant;
	}
	public void setCustomsDeclarant(Shaddress customsDeclarant) {
		this.customsDeclarant = customsDeclarant;
	}
	
	public String getTotnopackscustom() {   //EI20120118
		return totnopackscustom;
	}
	public void setTotnopackscustom(String value) {
		this.totnopackscustom = value;
	}	

	public String getPortofloadingcode() {   //EI20120223
		return portofloadingcode;
	}
	public void setPortofloadingcode(String value) {
		this.portofloadingcode = value;
	}

	public Shtermses getShtermses() {
		return shtermses;
	}
	public void setShtermses(Shtermses shtermses) {
		this.shtermses = shtermses;
	}
	
	public String getOfficeofdeclaration() {   //EI20120223
		return officeofdeclaration;
	}
	public void setOfficeofdeclaration(String value) {
		this.officeofdeclaration = value;
	}
	
	public Shintrastats getShintrastats() {
		return shintrastats;
	}
	public void setShintrastats(Shintrastats stats) {
		this.shintrastats = stats;
	}
	
	public String getMotcode() {
		return motcode;
	}
	public void setMotcode(String value) {
		this.motcode = value;
	}
	
	public String getInvinvoiceamounttotal() {
		return invinvoiceamounttotal;
	}
	public void setInvinvoiceamounttotal(String value) {
		this.invinvoiceamounttotal = value;
	}
	
	public String getHeadercurrency() {
		return headercurrency;
	}
	public void setHeadercurrency(String value) {
		this.headercurrency = value;
	}
	
	/*
	public List<Sicommercialitem> getSicommercialitemList() {
		return sicommercialitemsList;
	}
	public void setSicommercialitemList(List<Sicommercialitem> list) {
		this.sicommercialitemsList = list;
	}
	public void addSicommercialitemList(Sicommercialitem item) {
		if (sicommercialitemsList == null) {
			sicommercialitemsList = new Vector<Sicommercialitem>();
		}
		sicommercialitemsList.add(item);
	}
	*/
}
