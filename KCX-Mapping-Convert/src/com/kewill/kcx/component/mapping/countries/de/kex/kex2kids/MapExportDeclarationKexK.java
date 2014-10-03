package com.kewill.kcx.component.mapping.countries.de.kex.kex2kids;

import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.SpecialMention;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpDat;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpDatPos;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ApprovedTreatment;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Business;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Cap;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CommodityCode;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.IncoTerms;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.LoadingTime;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PlaceOfLoading;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Route;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Statistic;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.WareHouse;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.formats.kex.common.Shaddress;
import com.kewill.kcx.component.mapping.formats.kex.common.Shcustomsaitext;
import com.kewill.kcx.component.mapping.formats.kex.common.Shcustomsdoc;
import com.kewill.kcx.component.mapping.formats.kex.common.Shpreviousdoc;
import com.kewill.kcx.component.mapping.formats.kex.common.Sicustomitem;
import com.kewill.kcx.component.mapping.formats.kex.msg.MsgKex;
import com.kewill.kcx.component.mapping.formats.kids.aes.BodyExportDeclarationKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: Export/aes<br>
 * Created		: 08.11.2011<br>
 * Description	: Mapping KEX to ExportDeclaration.
 * 				
 * @author krzoska  
 * @version 1.0.00
 */
public class MapExportDeclarationKexK extends KidsMessage {


	private MsgKex 		msgKex 	= null;   	
	private MsgExpDat 	msgKids;
	
	private String countryTo =  "";		//EI121212
		
	public MapExportDeclarationKexK(XMLEventReader parser, KidsHeader kidsHeader, String encoding) 
																				throws XMLStreamException {
			msgKex  = new MsgKex(parser);
			msgKids = new MsgExpDat();
	        
			this.kidsHeader	= kidsHeader;			
			this.encoding = encoding;
			this.countryTo = kidsHeader.getCountryCode();   //EI20121212
			if (Utils.isStringEmpty(countryTo)) {
         		countryTo = "";
         	}
	}
	
	public void getMessage(XMLStreamWriter writer) {
		 this.writer = writer;
	     try {
	    	 BodyExportDeclarationKids body = new BodyExportDeclarationKids(writer);

	         writeStartDocument(encoding, "1.0");
		         openElement("soap:Envelope");
		            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
		            
		            kidsHeader.writeHeader();		            
		            
		            msgKex.parse(HeaderType.KEX);	 //hier wird die KEX-Datei in MsgKex eingelesen
		            		         	
		            mapKexToKids();	         
		            getCommonFieldsDTO().setReferenceNumber(msgKids.getReferenceNumber());
		            
		            body.setKidsHeader(kidsHeader);
		            body.setMessage(msgKids);
		            body.writeBody();
	
		         closeElement();  // soap:Envelope
	         writer.writeEndDocument();

	         writer.flush();
	         writer.close();

	        } catch (XMLStreamException e) {
	            e.printStackTrace();
	        }
	}
	
	private void mapKexToKids() {	
		if (msgKex.getShheader() == null) {
			return;
		}
		if (msgKex.getShheader().getSheucustoms() != null) {
			msgKids.setAreaCode(msgKex.getShheader().getSheucustoms().getSaddeclarationdivision1());
			msgKids.setTypeOfPermit(msgKex.getShheader().getSheucustoms().getSaddeclarationdivision2());
			msgKids.setStatusCode(msgKex.getShheader().getSheucustoms().getEcsStatusCode());
			msgKids.setSituationCode(msgKex.getShheader().getSheucustoms().getSpecialcircumstance());
			msgKids.setPaymentType(msgKex.getShheader().getSheucustoms().getMethodofpayment());
			msgKids.setTransportInContainer(msgKex.getShheader().getSheucustoms().getContainerIdentifier());				
			msgKids.setShipmentNumber(msgKex.getShheader().getSheucustoms().getCommercialRefNr());  //AK20121016
			msgKids.setIntendedOfficeOfExit(msgKex.getShheader().getSheucustoms().getOfficeofexit());
			msgKids.setCustomsOfficeExport(msgKex.getShheader().getOfficeofdeclaration());      //EI20121210
				
			msgKids.setReferenceNumber(msgKex.getShheader().getSheucustoms().getEuducr());							
			//if (Utils.isStringEmpty(msgKids.getReferenceNumber())) {    //EI20121123 JIRA KCXSM-40
			if (countryTo.equals("NL")) {   //EI20121212
				msgKids.setReferenceNumber(msgKex.getShheader().getSheucustoms().getDeclNumber());
			}
				
			msgKids.setUCROtherSystem(msgKex.getShheader().getSheucustoms().getEumucr());
				/*
				in KidsToECustoms:
				 tempHH.setDeclnUcr(msgUKExpDat.getReferenceNumber());    	      
	     		 tempHH.setMasterUcr(msgUKExpDat.getUCROtherSystem());    
				*/
			if (!Utils.isStringEmpty(msgKex.getShheader().getSheucustoms().getInTransit())) { //EI20130129
				String transit = msgKex.getShheader().getSheucustoms().getInTransit().trim();
				if (transit.equalsIgnoreCase("true")) {
					transit = "Y";
				} else {
					transit = "N";
				}				
				msgKids.setTransferToTransitSystem(transit);	
			}
			this.setWareHouse();
			this.setMeansOfTransport();                       //AK20121016
			this.setMeansOfTransportDeparture();
			this.setMeansOfTransportBorder();
			this.setMeansOfTransportInland();                 //EI20121212
			this.setPlaceOfLoading();						
			this.setTransporttationList();
			this.setLoadingTime();
			this.setBusiness();
			this.setIncoTerms();								//AK20121016
			this.setHeadDocumentList();
			this.setCAP();
		}
       
		msgKids.setDispatchCountry(msgKex.getShheader().getCountryofdespatchcode());
		msgKids.setDestinationCountry(msgKex.getShheader().getCountryofdestinationcode());
		msgKids.setGrossMass(msgKex.getShheader().getTotalgrossweightcustoms());
		msgKids.setTotalNumberPositions(msgKex.getShheader().getTotnocus());
		msgKids.setTotalNumberOfPackages(msgKex.getShheader().getTotnopackscustom());		//EI20120118
											
		//EI20120125: msgKids.setConsignor(mapParty("Consignor", msgKex.getShheader().getConsignor()));
		msgKids.setConsignor(mapParty("Consignor", msgKex.getShheader().getExporter()));  //EI20120125
		msgKids.setForwarder(mapParty("Forwarder", msgKex.getShheader().getForwarder()));			
		msgKids.setDeclarant(mapParty("Declarant", msgKex.getShheader().getCustomsDeclarant()));
		msgKids.setAgent(mapParty("Agent", msgKex.getShheader().getAgents()));			
		msgKids.setConsignee(mapParty("Consignee", msgKex.getShheader().getConsignee()));
		msgKids.setCarrier(mapParty("Carrier", msgKex.getShheader().getCarrier()));	
		msgKids.setSupervisingCustomsOffice(mapParty("SupervisingCustomsOffice", 
											msgKex.getShheader().getSupervisingCustomsOffice())); //EI20120620		
		if (msgKex.getShheader().getCustomsDeclarant() != null &&                         //EI20120124
						!Utils.isStringEmpty(msgKex.getShheader().getCustomsDeclarant().getPartytypeedi())) {
			msgKids.setTypeOfRepresentation(msgKex.getShheader().getCustomsDeclarant().getPartytypeedi());
		} else if (msgKex.getShheader().getExporter() != null &&
					!Utils.isStringEmpty(msgKex.getShheader().getExporter().getPartytypeedi())) {
			msgKids.setTypeOfRepresentation(msgKex.getShheader().getExporter().getPartytypeedi());
		} else {
			msgKids.setTypeOfRepresentation("");
		}
		
		if (msgKex.getShheader().getShcustomsaitextsList() != null) {                  //EI20120321
			for (Shcustomsaitext ai : msgKex.getShheader().getShcustomsaitextsList()) {
				if (ai != null) {
					Text tx = new Text();
					tx.setCode(ai.getAicode());
					tx.setText(ai.geTaitext1());
					msgKids.addAddInfoStatementList(tx);
				}
			}
		}
			
		//GoodsItems:		
		if (msgKex.getShheader().getSicustomitemsList() == null) {	                   //EI20121001
			Utils.log("(MapExportDeclarationKexK: GoodsItems not provided");
		} else {	 
			int count = 0;    //EI20121213
			for (Sicustomitem sicustomitems : msgKex.getShheader().getSicustomitemsList()) {
				if (sicustomitems != null) {
					MsgExpDatPos goodsItem = new MsgExpDatPos();
					count = count + 1;
					goodsItem.setItemNumber("" + count);     //EI20121213
					goodsItem.setDescription(sicustomitems.getDescriptionofgoodscustoms());
					goodsItem.setOriginCountry(sicustomitems.getCountryoforigincode());
					goodsItem.setOriginFederalState(sicustomitems.getStateoforigin());
					goodsItem.setNetMass(sicustomitems.getNetweightcustoms());
					goodsItem.setGrossMass(sicustomitems.getGrossweightcustoms());
					goodsItem.setDangerousGoodsNumber(sicustomitems.getUnnumber());
					goodsItem.addPackagesList(getPackages(sicustomitems));
					goodsItem.setKindOfArticle(sicustomitems.getCategoryNumber());					
					if (sicustomitems.getSicustomsmessage() != null && 
						!Utils.isStringEmpty(sicustomitems.getSicustomsmessage().getProdOrganizationCode())) {          //AK20121015
						goodsItem.setCommodityBoard(sicustomitems.getSicustomsmessage().getProdOrganizationCode());
					}
					this.setItemDocumentsList(goodsItem, sicustomitems.getSicustomsdocsList()); //EI20120106
					this.setItemPrevDocList(goodsItem, sicustomitems.getSipreviousdocsList()); //EI20120106					
					if (!Utils.isStringEmpty(sicustomitems.getCommoditycode())) {          //EI20120106
						CommodityCode ccTemp = new CommodityCode();
						ccTemp.setTarifCode(sicustomitems.getCommoditycode());						
						goodsItem.setCommodityCode(ccTemp);  
					}
					if (!Utils.isStringEmpty(sicustomitems.getStatisticsvaluecustomsitem())) {          //EI20120106
						Statistic statistic = new Statistic();
						statistic.setStatisticalValue(sicustomitems.getStatisticsvaluecustomsitem());
						statistic.setValue(sicustomitems.getStatisticsvaluecustomsitem());   //EI20121204
						statistic.setCurrency(sicustomitems.getCustomscurrency());           //EI20121204
						if (sicustomitems.getSupplementaryunitscustomsq2() != null &&
								!sicustomitems.getSupplementaryunitscustomsq2().equals("0")) { //EI20120703
							statistic.setAdditionalUnit(sicustomitems.getSupplementaryunitscustomsq2());
						}
						goodsItem.setStatistic(statistic);  
					}
					String curr = sicustomitems.getCustomscurrency();
					if (count == 1 && !Utils.isStringEmpty(curr)) {   //EI20130204						
						if (msgKids.getBusiness() != null)  {	
							msgKids.getBusiness().setCurrency(curr);
						} else {
							Business b = new Business();
							b.setCurrency(curr);
							msgKids.setBusiness(b);
						}
					}
// AK20121015
//					String regimus = sicustomitems.getCustomsregimecus();					
//					if (!Utils.isStringEmpty(regimus) && regimus.length() > 6) {    
//						ApprovedTreatment approvedTreatment = new ApprovedTreatment();
//						approvedTreatment.setDeclared(regimus.substring(0, 2));
//						approvedTreatment.setPrevious(regimus.substring(2, 4));
//						approvedTreatment.setNational(regimus.substring(4, 7));
//						goodsItem.setApprovedTreatment(approvedTreatment);						
//					}
					
					addSpecialMentionList(sicustomitems, goodsItem);				// AK20121015
					setApprovedTreatment(sicustomitems, goodsItem);				    // AK20121015
					if (sicustomitems.getSicustomsaitextsList() != null) {          //EI20120321
						for (Shcustomsaitext ai : sicustomitems.getSicustomsaitextsList()) {
							if (ai != null) {
								Text tx = new Text();
								tx.setCode(ai.getAicode());
								tx.setText(ai.geTaitext1());
								goodsItem.addAddInfoStatementList(tx);
							}
						}
					}
					if (sicustomitems.getQuantity3() != null && 
							!sicustomitems.getQuantity3().equals("0")) { //EI20120730
						goodsItem.setThirdQuantity(sicustomitems.getQuantity3());   //EI20120321
					}					
					//EI20120704 alle adressen haben gefehlt:	
					//Consignee, Consignor==Exporter,warehouseKeeper, SupervisingCustomsOffice
					goodsItem.setConsignee(mapParty("Consignee", sicustomitems.getConsignee()));  
					goodsItem.setConsignor(mapParty("Consignor", sicustomitems.getExporter()));  
					goodsItem.setWarehouseKeeper(mapParty("Warehouse", sicustomitems.getWarehouse()));  
					goodsItem.setSupervisingCustomsOffice(mapParty("SupervisingCustomsOffice", 
															sicustomitems.getSupervisingCustomsOffice()));  								
					msgKids.addGoodsItemList(goodsItem);
				}
			}		  
		}
	}
	
	/////////////////////////
	
	private Packages getPackages(Sicustomitem sicustomitems) {
		Packages packages = new Packages();
		
		packages.setMarks(sicustomitems.getMarksnoscustoms());
		packages.setType(sicustomitems.getKindofpackagescodecustoms());
		packages.setSequentialNumber(sicustomitems.getPackagenumbers());
		packages.setQuantity(sicustomitems.getNoofpackagesfromcustoms());

		return packages;
	}

	private void setApprovedTreatment(Sicustomitem sicustomitems, MsgExpDatPos goodsItem) {
		if (sicustomitems == null) {
			return;
		}		
		
		if (countryTo.equals("NL")) {   //EI20130130: abhaengigkeit von GB/NL eingefuegt
			if (!Utils.isStringEmpty(sicustomitems.getCustRegulation1Code()) ||
						!Utils.isStringEmpty(sicustomitems.getCustRegulation2Code()) ||
						!Utils.isStringEmpty(sicustomitems.getExemptionProcCode())) {
					
				ApprovedTreatment at = new ApprovedTreatment();			
				at.setDeclared(sicustomitems.getCustRegulation1Code());
				at.setPrevious(sicustomitems.getCustRegulation2Code());
				if (!Utils.isStringEmpty(sicustomitems.getCustRegulation2Code()) && 
						sicustomitems.getCustRegulation2Code().equals("0")) {
					at.setPrevious("00");       //EI20121213
				}
				at.setNational(sicustomitems.getExemptionProcCode());
				
				goodsItem.setApprovedTreatment(at);
							
			}	
		} else {   //EI20130130
			String regimus = sicustomitems.getCustomsregimecus();					
			if (!Utils.isStringEmpty(regimus) && regimus.length() > 6) {    
				ApprovedTreatment at = new ApprovedTreatment();	
				at.setDeclared(regimus.substring(0, 2));
				at.setPrevious(regimus.substring(2, 4));
				at.setNational(regimus.substring(4, 7));
				goodsItem.setApprovedTreatment(at);						
			}
		}
	}
		
	private void addSpecialMentionList(Sicustomitem sicustomitems, MsgExpDatPos goodsItem) {
			if (sicustomitems == null) {
				return;
			}			
			if (Utils.isStringEmpty(sicustomitems.getSpecialConsCode()) &&
				Utils.isStringEmpty(sicustomitems.getSpecialDes())) {
				return;
			}	
			
			SpecialMention sm = new SpecialMention();			
			sm.setTypeOfExport(sicustomitems.getSpecialConsCode());
			sm.setText(sicustomitems.getSpecialDes());
				
			goodsItem.addSpecialMentionList(sm);			
	}
		
	private Party mapParty(String person, Shaddress kexAddress) {
		if (kexAddress == null) {
			return null;
		}
		
		Party party = new Party(person);

		if (!Utils.isStringEmpty(kexAddress.getEorino()) || !Utils.isStringEmpty(kexAddress.getAddresscode())) {
			TIN pTin = new TIN();
			
			//EI20120118: pTin.setCustomerIdentifier(kexAddress.getEorino());
			pTin.setTin(kexAddress.getEorino());  //EI20121018
			pTin.setCustomerIdentifier(kexAddress.getAddresscode());  //EI20120118
			party.setPartyTIN(pTin);
		}		
		
		party.setVATNumber(kexAddress.getVattaxregistrationcode());		
				
		Address address = new Address();
		address.setName(kexAddress.getNameof());
		//address.setStreet(kexAddress.getStreethousename1() + kexAddress.getStreethousename2());
		String street = "";
		if (kexAddress.getStreethousename1() != null) {
			 street = kexAddress.getStreethousename1();
		}
		if (kexAddress.getStreethousename2() != null) {
			street = street + " " + kexAddress.getStreethousename2();
		}
		address.setStreet(street);
		address.setHouseNumber(kexAddress.getHousenumber());
		address.setCity(kexAddress.getTowncity());
		address.setPostalCode(kexAddress.getPostalcodezipcode());		
		address.setCountry(kexAddress.getCountrycode());       //EI20120228
		address.setCountrySubEntity(kexAddress.getCountystate());
				
		if (!address.isEmpty()) {
			party.setAddress(address);
		}	
		
		ContactPerson contact = new ContactPerson();
		//contact.setClerk(kexAddress.getContactfirstname() + " " + kexAddress.getContactlastname());
		String clerk = "";
		if (kexAddress.getContactfirstname() != null) {
			clerk = kexAddress.getContactfirstname();
		}
		if (kexAddress.getContactlastname() != null) {
			clerk = clerk + " " + kexAddress.getContactlastname();	
		}
		contact.setClerk(clerk);
		contact.setEmail(kexAddress.getContactemailaddress());
		contact.setFaxNumber(kexAddress.getContactfaxnumber());
		contact.setPhoneNumber(kexAddress.getContacttelephonenumber());
		contact.setTitle(kexAddress.getContacttitle());
		
		if (!contact.isEmpty()) {
			party.setContactPerson(contact);
		}
		
		return party;
	}
		
	private void setWareHouse() {
		if (msgKex.getShheader().getSheucustoms() == null) {
			return;
		}		
		if (!Utils.isStringEmpty(msgKex.getShheader().getSheucustoms().getIdofwarehousedeparture())) {			
			WareHouse wh = new WareHouse();
			wh.setWarehouseIdentification(msgKex.getShheader().getSheucustoms().getIdofwarehousedeparture());		
			msgKids.setWareHouse(wh);
		}
	}

	private void setLoadingTime() {
		if (msgKex.getShheader().getSheucustoms() == null) {
			return;
		}			
		if (!Utils.isStringEmpty(msgKex.getShheader().getSheucustoms().getStartloadingdatetime()) ||
			!Utils.isStringEmpty(msgKex.getShheader().getSheucustoms().getEndloadingdatetime())) {
			
			LoadingTime lt = new LoadingTime();		
			lt.setBeginTime(FssUtils.reformateXMLDateTime(msgKex.getShheader().getSheucustoms().getStartloadingdatetime()));
			lt.setEndTime(FssUtils.reformateXMLDateTime(msgKex.getShheader().getSheucustoms().getEndloadingdatetime()));
			msgKids.setLoadingTime(lt);
		}
	}

	private void setBusiness() {  				 //EI20121212			
		if (Utils.isStringEmpty(msgKex.getShheader().getInvinvoiceamounttotal()) && 
				msgKex.getShheader().getShintrastats() == null) {
			return;
		}
		Business business = new Business();			
		business.setInvoicePrice(msgKex.getShheader().getInvinvoiceamounttotal());		
		//EI20130204: business.setCurrency(msgKex.getShheader().getHeadercurrency());		
		if (msgKex.getShheader().getShintrastats() != null) {
			business.setBusinessTypeCode(msgKex.getShheader().getShintrastats().getNatureoftransaction());
		}			
		msgKids.setBusiness(business);		
	}
	
	private void setTransporttationList() {
		if (msgKex.getShheader().getSheucustoms() == null) {
			return;
		}		
		if (!Utils.isStringEmpty(msgKex.getShheader().getSheucustoms().getRoutectycode())) {
			Route route = new Route();
			//EI20121214: route.addCountryList(msgKex.getShheader().getSheucustoms().getRoutectycode());
			//EI20121214:
			String codes = msgKex.getShheader().getSheucustoms().getRoutectycode();
			String[] countries = codes.split(",");  
			int len = countries.length;
			for (int i = 0; i < len; i++) {
				String code = countries[i];
				route.addCountryList(code);				
			}
			
			msgKids.setTransportationRoute(route);
		}
	}

	private void setMeansOfTransport() {
		if (msgKex.getShheader().getSheucustoms() == null) {
			return;
		}				
		if (!Utils.isStringEmpty(msgKex.getShheader().getSheucustoms().getPlaceOfGoods())) {		
			TransportMeans tm = new TransportMeans();
			tm.setPlaceOfLoading(msgKex.getShheader().getSheucustoms().getPlaceOfGoods());				
			msgKids.setMeansOfTransport(tm);			
		}		
	}
	
	private void setMeansOfTransportDeparture() {
		if (msgKex.getShheader().getSheucustoms() == null) {
			return;
		}			
		if (!Utils.isStringEmpty(msgKex.getShheader().getSheucustoms().getTransportmotdepart()) ||
			!Utils.isStringEmpty(msgKex.getShheader().getSheucustoms().getTransportidatdep()) ||
			!Utils.isStringEmpty(msgKex.getShheader().getSheucustoms().getTransportidatdepctycode())) {
			
			TransportMeans tm = new TransportMeans();				
			tm.setTransportationType(msgKex.getShheader().getSheucustoms().getTransportmotdepart());
			tm.setTransportMode(msgKex.getShheader().getSheucustoms().getTransportmotdepart()); //EI20120222
			tm.setTransportationNumber(msgKex.getShheader().getSheucustoms().getTransportidatdep());
			tm.setTransportationCountry(msgKex.getShheader().getSheucustoms().getTransportidatdepctycode());			
			msgKids.setTransportMeansDeparture(tm);  
		}
	}

	private void setMeansOfTransportBorder() {
		if (msgKex.getShheader().getSheucustoms() == null) {
			return;
		}		
		if (!Utils.isStringEmpty(msgKex.getShheader().getSheucustoms().getTransportmotborder()) || 
			!Utils.isStringEmpty(msgKex.getShheader().getSheucustoms().getTransportidatbord()) ||
			!Utils.isStringEmpty(msgKex.getShheader().getSheucustoms().getTransportidatbordctycode())) {
						
			TransportMeans tm = new TransportMeans();				
			tm.setTransportationType(msgKex.getShheader().getSheucustoms().getTransportmotborder());
			tm.setTransportMode(msgKex.getShheader().getSheucustoms().getTransportmotborder());   //EI20120222
			tm.setTransportationNumber(msgKex.getShheader().getSheucustoms().getTransportidatbord());
			tm.setTransportationCountry(msgKex.getShheader().getSheucustoms().getTransportidatbordctycode());				
			msgKids.setTransportMeansBorder(tm);  
		}
	} 

	private void setMeansOfTransportInland() {    //EI20121212			
		if (Utils.isStringEmpty(msgKex.getShheader().getMotcode())) {
			return;
		}
		TransportMeans tm = new TransportMeans();				
		tm.setTransportMode(msgKex.getShheader().getMotcode()); 			
		msgKids.setTransportMeansInland(tm);  			
	} 
	
	private void setPlaceOfLoading() {		
		/* EI20120223
		if (!Utils.isStringEmpty(msgKex.getShheader().getSheucustoms().getLocationofgoods()) ||
			!Utils.isStringEmpty(msgKex.getShheader().getSheucustoms().getLocationofgoodscode())) {
			
			PlaceOfLoading pl = new PlaceOfLoading();
			pl.setCode(msgKex.getShheader().getSheucustoms().getLocationofgoodscode());
			pl.setAgreedLocationOfGoods(msgKex.getShheader().getSheucustoms().getLocationofgoods());
			msgKids.setPlaceOfLoading(pl);
		*/	
		if (!Utils.isStringEmpty(msgKex.getShheader().getPortofloadingcode())) {  //EI20120223
			PlaceOfLoading pl = new PlaceOfLoading();
			pl.setCode(msgKex.getShheader().getPortofloadingcode());
			msgKids.setPlaceOfLoading(pl);
		}
	}


	private void setIncoTerms() {				
		if (msgKex.getShheader().getShtermses() == null) {
			return;
		}
		if (!Utils.isStringEmpty(msgKex.getShheader().getShtermses().getPlaceTerm()) || 
			!Utils.isStringEmpty(msgKex.getShheader().getShtermses().getDeliverytermincoterm())) { 
			IncoTerms it = new IncoTerms();
			it.setPlace(msgKex.getShheader().getShtermses().getPlaceTerm());	
			it.setIncoTerm(msgKex.getShheader().getShtermses().getDeliverytermincoterm());    //EI20121212
			msgKids.setIncoTerms(it);						
		}
	}
	
	private void setHeadDocumentList() {						
		if (msgKex.getShheader().getShcustomsdocsList() == null) {	
			return;
		}
		for (Shcustomsdoc shcustomsdoc : msgKex.getShheader().getShcustomsdocsList()) {					
			Document document = mapDocument(shcustomsdoc);
			if (document != null) {
				msgKids.addDocumentList(document);
			}										
		}
		if (msgKex.getShheader().getShpreviousdocsList() != null) {
			for (Shpreviousdoc shpreviousdoc : msgKex.getShheader().getShpreviousdocsList()) {	//EI20120117								
				PreviousDocument previousDocument = mapPreviousDocument(shpreviousdoc);
				if (previousDocument != null) {
					msgKids.addPreviousDocumentList(previousDocument);										
				}		
			}
		}
	}
	
	private void setItemDocumentsList(MsgExpDatPos item, List<Shcustomsdoc> list) {				
		if (item == null) {		
			return;
		}
		if (list == null) {		
			return;
		}	
		for (Shcustomsdoc shcustomsdoc : list) {			
			Document document = mapDocument(shcustomsdoc);
			if (document != null) {
				item.addDocumentList(document);						
			}		
		}
	}
	
	private void setItemPrevDocList(MsgExpDatPos item, List<Shpreviousdoc> list) {  //EI20120117				
		if (item == null) {		
			return;
		}
		if (list == null) {		
			return;
		}	
		for (Shpreviousdoc shpreviousdoc : list) {						
			PreviousDocument previousDocument = mapPreviousDocument(shpreviousdoc);
			if (previousDocument != null) {
				item.addPreviousDocumentList(previousDocument);													
			}		
		}
	}
		
	private PreviousDocument mapPreviousDocument(Shpreviousdoc shprev) {
		PreviousDocument previousDocument = null;
		
		if (shprev == null) {
			return null;
		}		
		if (!Utils.isStringEmpty(shprev.getPrevDocType()) || !Utils.isStringEmpty(shprev.getPrevDocRef())) {
			previousDocument = new PreviousDocument();
			previousDocument.setType(shprev.getPrevDocType());
			previousDocument.setMarks(shprev.getPrevDocRef());
			previousDocument.setAdditionalInformation(shprev.getPrevDocClass());  //EI20120118
		}		
		return previousDocument;
	}

	private Document mapDocument(Shcustomsdoc shdoc) {
		Document document = null;
		
		if (shdoc == null) {
			return null;
		}				
		if (!Utils.isStringEmpty(shdoc.getDocumentStatus()) ||
			!Utils.isStringEmpty(shdoc.getDocumentCode()) ||
			!Utils.isStringEmpty(shdoc.getDocumentRef()) ||
			!Utils.isStringEmpty(shdoc.getDocumentReason())) {		
			document = new Document();
			document.setQualifier(shdoc.getDocumentStatus());  
			document.setMsgFlag("K");
			document.setTypeKids(shdoc.getDocumentCode());
			document.setReference(shdoc.getDocumentRef());
			document.setReason(shdoc.getDocumentReason());
		}
		return document;
	}

	private void setCAP() {
		if (msgKex.getShheader().getSheucustoms() == null) {
			return;
		}
		Cap cap;
		if (!Utils.isStringEmpty(msgKex.getShheader().getSheucustoms().getIbeaclaimref()) ||
			!Utils.isStringEmpty(msgKex.getShheader().getSheucustoms().getIbeaclaimtype()) ||
			!Utils.isStringEmpty(msgKex.getShheader().getSheucustoms().getIbregnumber()) ||
			!Utils.isStringEmpty(msgKex.getShheader().getSheucustoms().getIbguaranteenumber())) {
			
			cap = new Cap();
			cap.setIBClaimRef(msgKex.getShheader().getSheucustoms().getIbeaclaimref());
			cap.setIBClaimType(msgKex.getShheader().getSheucustoms().getIbeaclaimtype());
			cap.setIBRegNo(msgKex.getShheader().getSheucustoms().getIbregnumber());
			cap.setIBGAN(msgKex.getShheader().getSheucustoms().getIbguaranteenumber());			
			msgKids.setCap(cap);
		}
	}

	
}
