package com.kewill.kcx.component.mapping.formats.kex.common;

import java.util.List;
import java.util.Vector;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**  
 * Module		: KEX<br>
 * Created		: 15.11.2011<br>
 * Description  : Contains Message Structure with item fields used in Kewill Export.  
 *                Items for KEX
 *                 
 * @author Alfred Krzoska
 * @version 1.0.00
 */ 
public class Sicustomitem extends KCXMessage {

	 public Sicustomitem() {
		 super();  
	 }

	 public Sicustomitem(XmlMsgScanner scanner) {
 		super(scanner);
	 }
	 
	 private String	descriptionofgoodscustoms;
	 private String	countryoforigincode;
	 private String	stateoforigin;
	 private String	netweightcustoms;
	 private String	grossweightcustoms;
	 private String	unnumber;
	 
	 private String	noofpackagesfromcustoms;
	 private String	packagenumbers;
	 private String	kindofpackagescodecustoms;
	 private String	marksnoscustoms;
	 private String commoditycode;                           //EI20120106
	 private String statisticsvaluecustomsitem;				 //EI20120118
	 private String supplementaryunitscustomsq2;				 //EI20120118
	 private List<Shcustomsdoc> sicustomsdocsList;           //EI20111215
	 private List<Shpreviousdoc> sipreviousdocsList;           //EI20120117
	 private List<Shcustomsaitext>sicustomsaitextsList;      //EI20111215
	 
	 private String customsregimecus;  				 //EI20120123
	 private String quantity3;   					 //EI20120321
	 
	//EI20120704 adressen waren gar nicht gemapped
	 private Shaddress consignee;
	 private Shaddress exporter;
	 private Shaddress warehouse;
	 private Shaddress supervisingCustomsOffice;     

	 
	 private String custRegulation1Code;
	 private String custRegulation2Code;
	 private String exemptionProcCode;	
	 private String categoryNumber;
	 private String specialConsCode;	
	 private String specialDes;			
	 private String prodOrganizationCod; 
	 
	 private Sicustomsmessage sicustomsmessage;
	 private String customscurrency;	//EI20121204
	 
	 private enum ESicustomitemsTags {	
			// KEX							KIDS
		 additionalinfocustoms,
		 alcoholpercentcus,
		 alcohollitrescus,
		 classifiertype,
		 classifierlabel,
		 classificationtype,
		 classificationlabel,
		 commoditytype,
		 commoditycode,
		 commoditygrouptypecodecus,
		 containerprefix1,
		 containernumber1,
		 containerprefix2,
		 containernumber2,
		 countryoforigincode,
		 customscurrency,
		 customsregimecus,
		 customitemUnid,
		 descriptionofgoodscustoms,	
		 ddtcexemption,
		 ddtcregno,
		 ddtcsigmilitaryind,
		 ddtceligiblepartyindicator,
		 ddtcusmlcategorycode,
		 ddtcuom,
		 ddtcquantity,
		 ddtclicenceline,
		 fiscalvaluecustomsitem,
		 grossweightcustoms,
		 hazmatflag,
		 intransitnonstats,
		 kindofpackagescustoms,
		 kindofpackagescodecustoms,
		 legalweightcustomsitem,
		 licensetype,
		 licensenumber,
		 licenselinenumber,
		 licensestatus,
		 licensequantity,
		 marksnoscustoms,
		 naftacriterianafta,
		 naftaproducernafta,
		 naftanetcostnafta,
		 netweightcustoms,
		 noofpackagesfromcustoms,
		 noofpackagestocustoms,
		 nonpreferenceflag,
		 originatinginvoicelinenumber,
		 packagenumbers,
		 printquantityonsed,
		 procedureregimecodecategory,
		 processrepairfocindicator,
		 quantity3,
		 quantity3unit,
		 
		 sealnumberctnr1a,
		 sealnumberctnr1b,
		 sealnumberctnr1c,
		 sealnumberctnr1d,
		 sealnumberctnr1e,
		 sealnumberctnr1f,
		 sealnumberctnr1g,
		 sealnumberctnr1h,		
		 sealnumberctnr2a,
		 sealnumberctnr2b,
		 sealnumberctnr2c,
		 sealnumberctnr2d,
		 sealnumberctnr2e,
		 sealnumberctnr2f,
		 sealnumberctnr2g,
		 sealnumberctnr2h,
		 seddomesticforeign,
		 sedlicenseexemptionindicator,
		 sedlicenseexpirationdate,
		 sedlicensetypecode,
		 sedeccnnumber,
		 sedusedvehicleynindicator,
		 sedusedvehicleidvpind,
		 sedrequiredflag,
		 sicustomsmessages,
		 sicusvehicles,
		 statisticsvaluecustomsitem,
		 stateoforigin,
		 supplementaryunitscustomsq2,
		 supplymentaryuomcustomsq2,		 
		 summarydecprevdocflag,		 
		 tcf,
		 totallitrescus,		 
		 transactioncodenot,		
		 unnumber,		 
		 volumecustomsitem,
		 sicustomsdoc,  sicustomsdocs,       //EI20111215 201201015: verwirrend - struktur heisst ..doc, in dem item aber docs
		 sicustomsaitext, sicustomsaitexts,  //EI20111215
         sipreviousdoc, sipreviousdocs,		 //EI20120117
         
         sicustomsaddresses,						//Address-Types
         runningProcedure,
         custRegulation1Code,					//GoodsItem/CustomsApprovedTreatment/Declared
         custRegulation2Code,					//GoodsItem/CustomsApprovedTreatment/Previous
         exemptionProcCode,						//GoodsItem/CustomsApprovedTreatment/National
         categoryNumber,						//GoodsItem/KindOfArticle
         specialConsCode,						//GoodsItem/SpecialMention/TypeOfExport
         specialDes,							//GoodsItem/SpecialMention/Text
         prodOrganizationCod,					//GoodsItem/CommodityBoard
         
         sicustomsmessage
         ;
         
         
	 }

	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((ESicustomitemsTags) tag) {
			case sicustomsdoc:     //EI20111215
			case sicustomsdocs:    //EI20120105
				Shcustomsdoc sicustomsdoc= new Shcustomsdoc(getScanner());
				sicustomsdoc.parse(tag.name());
				if (sicustomsdocsList == null) {
					sicustomsdocsList = new Vector<Shcustomsdoc>();
				}
				sicustomsdocsList.add(sicustomsdoc);
				break;
			case sicustomsaitext:   //EI20111215
			case sicustomsaitexts:  //EI20120105
				Shcustomsaitext sicustomsaitext = new Shcustomsaitext(getScanner());
				sicustomsaitext.parse(tag.name());
				if (sicustomsaitextsList == null) {
					sicustomsaitextsList = new Vector<Shcustomsaitext>();
				}
				sicustomsaitextsList.add(sicustomsaitext);
				break;
			case sipreviousdoc:
			case sipreviousdocs:
				Shpreviousdoc sipreviousdoc= new Shpreviousdoc(getScanner());
				sipreviousdoc.parse(tag.name());
				if (sipreviousdocsList == null) {
					sipreviousdocsList = new Vector<Shpreviousdoc>();
				}
				sipreviousdocsList.add(sipreviousdoc);
				break;
			case sicustomsaddresses:
				Shaddress siaddress = new Shaddress(getScanner());
				siaddress.parse(tag.name());		
				setAddress(siaddress);
				break;
				
			case sicustomsmessage:
				sicustomsmessage = new Sicustomsmessage(getScanner());  
				sicustomsmessage.parse(tag.name());
				break;
				
			default:
				return;
		}
	} else {
		switch ((ESicustomitemsTags) tag) {
			case descriptionofgoodscustoms: setDescriptionofgoodscustoms(value);
				break;
			case countryoforigincode: setCountryoforigincode(value);
				break;
			case stateoforigin: setStateoforigin(value);
				break;
			case netweightcustoms: setNetweightcustoms(value);
				break;
			case grossweightcustoms:setGrossweightcustoms(value);
			break;
			case unnumber:setUnnumber(value);
				break;
			case noofpackagesfromcustoms: setNoofpackagesfromcustoms(value);
				break;
			case packagenumbers: setPackagenumbers(value);
				break;
			case kindofpackagescodecustoms: setKindofpackagescodecustoms(value);
				break;
			case marksnoscustoms: setMarksnoscustoms(value);
				break;
			case commoditycode: setCommoditycode(value);
				break;
			case statisticsvaluecustomsitem: setStatisticsvaluecustomsitem(value);
				break;
			case supplementaryunitscustomsq2: setSupplementaryunitscustomsq2(value);
				break;
			case customsregimecus: setCustomsregimecus(value);
				break;
			case quantity3: setQuantity3(value);
				break;
			case custRegulation1Code: setCustRegulation1Code(value);
				break;
			case custRegulation2Code: setCustRegulation2Code(value);
				break;
			case exemptionProcCode: setExemptionProcCode(value);
				break;
			case categoryNumber: setCategoryNumber(value);
				break;
			case specialConsCode: setSpecialConsCode(value);
				break;
			case specialDes: setSpecialDes(value);
				break;
			case customscurrency: setCustomscurrency(value);
				break;
			default: 
				break;
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
  			return ESicustomitemsTags.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}

	public String getDescriptionofgoodscustoms() {
		return descriptionofgoodscustoms;
	}

	public void setDescriptionofgoodscustoms(String descriptionofgoodscustoms) {
		this.descriptionofgoodscustoms = Utils.checkNull(descriptionofgoodscustoms);
	}

	public String getCountryoforigincode() {
		return countryoforigincode;
	}

	public void setCountryoforigincode(String countryoforigincode) {
		this.countryoforigincode = Utils.checkNull(countryoforigincode);
	}

	public String getStateoforigin() {
		return stateoforigin;
	}

	public void setStateoforigin(String stateoforigin) {
		this.stateoforigin = Utils.checkNull(stateoforigin);
	}

	public String getNetweightcustoms() {
		return netweightcustoms;
	}

	public void setNetweightcustoms(String netweightcustoms) {
		this.netweightcustoms = Utils.checkNull(netweightcustoms);
	}

	public String getGrossweightcustoms() {
		return grossweightcustoms;
	}

	public void setGrossweightcustoms(String grossweightcustoms) {
		this.grossweightcustoms = Utils.checkNull(grossweightcustoms);
	}

	public String getUnnumber() {
		return unnumber;
	}

	public void setUnnumber(String unnumber) {
		this.unnumber = Utils.checkNull(unnumber);
	}

	
	public String getNoofpackagesfromcustoms() {
		return noofpackagesfromcustoms;
	}

	public void setNoofpackagesfromcustoms(String noofpackagesfromcustoms) {
		this.noofpackagesfromcustoms = Utils.checkNull(noofpackagesfromcustoms);
	}

	public String getPackagenumbers() {
		return packagenumbers;
	}
	public void setPackagenumbers(String packagenumbers) {
		this.packagenumbers = Utils.checkNull(packagenumbers);
	}

	public String getKindofpackagescodecustoms() {
		return kindofpackagescodecustoms;
	}
	public void setKindofpackagescodecustoms(String kindofpackagescodecustoms) {
		this.kindofpackagescodecustoms = Utils.checkNull(kindofpackagescodecustoms);
	}

	public String getMarksnoscustoms() {
		return marksnoscustoms;
	}
	public void setMarksnoscustoms(String marksnoscustoms) {
		this.marksnoscustoms = Utils.checkNull(marksnoscustoms);
	}

	public String getCommoditycode() {
		return commoditycode;
	}
	public void setCommoditycode(String value) {
		this.commoditycode = Utils.checkNull(value);
	}
	
	public List<Shcustomsdoc> getSicustomsdocsList() {
		return sicustomsdocsList;
	}
	public void setSicustomsdocsList(List<Shcustomsdoc> list) {
		this.sicustomsdocsList = list;
	}
	
	public List<Shcustomsaitext> getSicustomsaitextsList() {
		return sicustomsaitextsList;
	}
	public void setSicustomsaitextsList(List<Shcustomsaitext> list) {
		this.sicustomsaitextsList = list;
	}
	
	public List<Shpreviousdoc> getSipreviousdocsList() {
		return sipreviousdocsList;
	}
	public void setSipreviousdocsList(List<Shpreviousdoc> list) {
		this.sipreviousdocsList = list;
	}
	
	public String getStatisticsvaluecustomsitem() {         //EI20120118
		return statisticsvaluecustomsitem;
	}
	public void setStatisticsvaluecustomsitem(String value) {
		this.statisticsvaluecustomsitem = value;
	}
	
	public String getSupplementaryunitscustomsq2() {         //EI20120118
		return supplementaryunitscustomsq2;
	}
	public void setSupplementaryunitscustomsq2(String value) {
		this.supplementaryunitscustomsq2 = value;
	}
	
	public String getCustomsregimecus() {         //EI20120123
		return customsregimecus;
	}
	public void setCustomsregimecus(String value) {
		this.customsregimecus = value;
	}
	
	public String getQuantity3() {         //EI20120321
		return quantity3;
	}
	public void setQuantity3(String value) {
		this.quantity3 = value;
	}
	
	private void setAddress(Shaddress address) {   //EI20120704
		if (address == null) {
			return;
		}
		String type = "";		
		if (address.getShipmentaddresstypecode() != null) {
			type = address.getShipmentaddresstypecode();
		}
		
		if (type.equalsIgnoreCase("CONSIGNEE")) {
			consignee = address;
		} else if (type.equalsIgnoreCase("EXPORTER")) {
			exporter = address;
		} else if (type.equalsIgnoreCase("WAREHOUSE")) {
			warehouse = address;
		} else if (type.equalsIgnoreCase("CUSTOMSSUPERVISORYOFF")) {
			supervisingCustomsOffice = address;
		} else {
			return;
		}		
	}
	
	public Shaddress getConsignee() {         
		return consignee;
	}
	public void setConsignee(Shaddress address) {
		this.consignee = address;
	}
	
	public Shaddress getExporter() {         
		return exporter;
	}
	public void setExporter(Shaddress address) {
		this.exporter = address;
	}
	
	public Shaddress getWarehouse() {         
		return warehouse;
	}
	public void setWarehouse(Shaddress address) {
		this.warehouse = address;
	}
	
	public Shaddress getSupervisingCustomsOffice() {         
		return supervisingCustomsOffice;
	}
	public void setSupervisingCustomsOffice(Shaddress address) {
		this.supervisingCustomsOffice = address;
	}

	public String getCustRegulation1Code() {
		return custRegulation1Code;
	}

	public void setCustRegulation1Code(String custRegulation1Code) {
		this.custRegulation1Code = Utils.checkNull(custRegulation1Code);
	}

	public String getCustRegulation2Code() {
		return custRegulation2Code;
	}

	public void setCustRegulation2Code(String custRegulation2Code) {
		this.custRegulation2Code = Utils.checkNull(custRegulation2Code);
	}

	public String getExemptionProcCode() {
		return exemptionProcCode;
	}

	public void setExemptionProcCode(String exemptionProcCode) {
		this.exemptionProcCode = Utils.checkNull(exemptionProcCode);
	}

	public String getSpecialConsCode() {
		return specialConsCode;
	}

	public void setSpecialConsCode(String specialConsCode) {
		this.specialConsCode = Utils.checkNull(specialConsCode);
	}

	public String getSpecialDes() {
		return specialDes;
	}

	public void setSpecialDes(String specialDes) {
		this.specialDes = Utils.checkNull(specialDes);
	}

	public String getProdOrganizationCod() {
		return prodOrganizationCod;
	}

	public void setProdOrganizationCod(String prodOrganizationCod) {
		this.prodOrganizationCod = Utils.checkNull(prodOrganizationCod);
	}

	public String getCategoryNumber() {
		return categoryNumber;
	}

	public void setCategoryNumber(String categoryNumber) {
		this.categoryNumber = Utils.checkNull(categoryNumber);
	}

	public Sicustomsmessage getSicustomsmessage() {
		return sicustomsmessage;
	}

	public void setSicustomsmessage(Sicustomsmessage sicustomsmessage) {
		this.sicustomsmessage = sicustomsmessage;
	}
	
	public String getCustomscurrency() {
		return customscurrency;
	}

	public void setCustomscurrency(String value) {
		this.customscurrency = Utils.checkNull(value);
	}
	
}
