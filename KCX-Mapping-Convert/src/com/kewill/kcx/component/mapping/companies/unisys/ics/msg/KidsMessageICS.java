package com.kewill.kcx.component.mapping.companies.unisys.ics.msg;

import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common.CtrySpec;
import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common.CustGoodsInfo;
import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common.CustRptInfo;
import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common.DateAndTime;
import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common.Detail;
import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common.Document;
import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common.FlightInfo;
import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common.GdsDetail;
import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common.PartInfo;
import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common.Participant;
import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common.Place;
import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common.Segment;
import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgEntrySummaryDeclaration;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.GoodsItemLong;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.IcsDocument;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: KidsMessageICS<br>
 * Erstellt		: 06.12.2010<br>
 * Beschreibung	: Methods used in all ICS-UnisysMessages .
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class KidsMessageICS extends KidsMessage {
		
	public Party getPartyFromUnisys(CustRptInfo custRptInfo, EUnisysParty qualifier, String countryTo) {
		Party party = null;
		if (custRptInfo == null) {
			return null;
		}
		if (custRptInfo.getTrader() == null) {
			return null;
		}
					
		List <Participant> list = custRptInfo.getTrader().getParticipantList();
		if (list != null && list.size() > 0) {
			for (Participant participant : list) {
				if (participant != null && participant.getType() != null) {
					if (participant.getType().equalsIgnoreCase(qualifier.toString())) {					
						party = getPartyFromUnisys(participant, countryTo);					
						break;
					}
				}
			}
		}
		return party;
	}
	public Party getPartyFromUnisys(PartInfo detailPartInfo, EUnisysParty qualifier, String countryTo) {
		Party party = null;
		if (detailPartInfo == null) {
			return null;
		}
		if (detailPartInfo.getParticipantList() == null) {
			return null;
		}
					
		List <Participant> list = detailPartInfo.getParticipantList();
		if (list.size() > 0) {
			for (Participant participant : list) {
				if (participant != null && participant.getType() != null) {
					if (participant.getType().equalsIgnoreCase(qualifier.toString())) {					
						party = getPartyFromUnisys(participant, countryTo);					
						break;
					}
				}
			}
		}
		return party;
	}

	public Party getPartyFromUnisys(Participant participant, String countryTo) {
		if (participant == null) {
			return null;
		}
		
		Party party = new Party();
		if (participant.getReference() != null && participant.getReference().getEori() != null) {
			TIN tin = new TIN();
			tin.setTin(participant.getReference().getEori());
			party.setPartyTIN(tin);
		}
		Address adr = new Address();
		adr.setName(participant.getName());
		adr.setStreet(participant.getAddr());		
		adr.setCity(participant.getCity());
		adr.setCountry(participant.getCountry());
		adr.setCountrySubEntity(participant.getState());
		adr.setPostalCode(participant.getPostcode());	
		
		if (countryTo.equals("DE")) {
			if (adr.getStreet().length() > 35) {
				adr.setStreet(participant.getAddr().substring(0, 35));
			}
		}
		
		party.setAddress(adr);
		
		String contact = participant.getContact();
		String phone = participant.getPhone();
		if (!Utils.isStringEmpty(contact) || !Utils.isStringEmpty(phone)) {
			ContactPerson partyContact = new ContactPerson();
			partyContact.setName(contact);
			partyContact.setClerk(contact);
			partyContact.setPhoneNumber(phone);	
			party.setContactPerson(partyContact);
		}
		
		
		return party;
	}
	public TransportMeans getTransportMeansBorderFromUni(FlightInfo flightInfo) {
		if (flightInfo == null) {
			return null;
		}
		String mode = flightInfo.getMode();
		if (mode == null) {
			mode = "";               //Ei20110526
		}
		if (mode.equals("A") || mode.equals("Air")) {   
			mode = "4";
		} else if (mode.equals("T") || mode.equals("Truck")) {
			mode = "3";
		}
		TransportMeans border = new TransportMeans();
		border.setTransportMode(mode);  
		if (flightInfo.getNationality() != null && !mode.equals("4")) {
			border.setTransportationCountry(flightInfo.getNationality().getCountry());
		}		
		return border;
	}
	public TransportMeans getTransportMeansBorderFromUnisys(FlightInfo flightInfo, CtrySpec spec) {
		if (flightInfo == null) {
			return null;
		}
		
		TransportMeans border = new TransportMeans();
		String mode = flightInfo.getMode();
		
		if (mode == null) {      //EI20110526
			mode = "";
		} else {
			if (mode.equals("A")) {      //Air
				mode = "4";
			} else if (mode.equals("T")) {  //Track
				mode = "3";
			}
		}
		
		border.setTransportMode(mode);  
		if (!mode.equals("4")) {
			if (flightInfo.getNationality() != null) {
				border.setTransportationCountry(flightInfo.getNationality().getCountry());
			}
			if (spec != null && spec.getCustoms() != null) {
				border.setTransportationNumber(spec.getCustoms().getConveyNo());
			}
		}
		return border;
	}
	public List <TransportMeans> getMeansOfTransportListFromUnisys(List<GdsDetail> list) {
		if (list == null) {
			return null;
		}
		List<TransportMeans> borderList = new Vector<TransportMeans>();
		for (GdsDetail goods : list) {	
			if (goods != null) {
				TransportMeans border = getTransportMeansBorderFromUnisys(goods.getFlightInfo(), goods.getCtrySpec());
				borderList.add(border);    
			}
		}
		return borderList;
	}
	public List <TransportMeans> getMeansOfTransportListFromUnisys(GdsDetail gdsItem) {
		if (gdsItem == null) {
			return null;
		}
		List<TransportMeans> borderList = new Vector<TransportMeans>();		
		TransportMeans border = getTransportMeansBorderFromUnisys(gdsItem.getFlightInfo(), gdsItem.getCtrySpec());
		borderList.add(border);    			
		
		return borderList;
	}
	public String getDateTimeFromUnisys(DateAndTime dateAndTime) {
		if (dateAndTime == null) {
			return null;
		}
		String date = getKidsDate(dateAndTime.getDate());
		String time = getTimeHhMm(dateAndTime.getTime()); 
		
		return date + time;
	}
	
	public List<Packages>  getPackagesListUnisys(List<GdsDetail> list) {
		if (list == null) {
			return null;
		}
		List<Packages> packageList = new Vector<Packages>();
		for (GdsDetail goods : list) {	
			if (goods.getManifested() != null) {  //EI20110708
				Packages pack = new Packages();
				pack.setType("NE"); 
				//pack.setQuantity(goods.getManifested().getPieces());  //TODO was ist richtig???
				pack.setSequentialNumber(goods.getManifested().getPieces());
				packageList.add(pack);     
			}
		}
		return packageList;
	}
	
	public List<IcsDocument> getDocumentListFromUnisys(List<Document> documentList) {
		List <IcsDocument>kidsList = null;
		IcsDocument kidsDoc = null;
		
		if (documentList != null) {
			kidsList = new Vector<IcsDocument>();
			for (Document unisysDoc : documentList) {
				kidsDoc = new IcsDocument();
			
				kidsDoc.setType(unisysDoc.getDocType());
				kidsDoc.setReference(unisysDoc.getDocNumber());
				kidsList.add(kidsDoc);
			}
		}
		
		return kidsList;
	}
	
	public String getDeclarationTimeFromHeader() {
		String dateTime = "";
		String time = "";
		dateTime = kidsHeader.getYear() + kidsHeader.getMonth() + kidsHeader.getDay(); 
		time = kidsHeader.getTime();
		if (time.length() == 8) {  //hh:mm:ss
			dateTime = dateTime + time.substring(0, 2) + time.substring(3, 5);
		} else {
			dateTime = dateTime + "0000";
		}
		
		return dateTime;
	}
	
							 	
	public String getKidsDate(String date) {
		if (date == null) {
			return "";
		}
		String kidsDate = "";
		String yyyy = "20";
		String mm = "";
		String dd = "";
		if (date.length() == 7) {
			yyyy = yyyy + date.substring(5, 7);
			mm = getMmFromMon(date.substring(2, 5));
			dd = date.substring(0, 2);
			kidsDate = yyyy + mm + dd;
		} else if (date.length() == 8) {
			kidsDate = date;
		} else {
			kidsDate = "";
		}
		return kidsDate;	
	}	
	private String getMmFromMon(String mon) {
		String mm = "XX";	
		if (mon == null) {
			return mm;
		}		
		
		if (mon.equals("JAN")) {		
			mm = "01";
		}
		if (mon.equals("FEB")) {		
			mm = "02";
		}
		if (mon.equals("MAR")) {		
			mm = "03";
		}
		if (mon.equals("APR")) {		
			mm = "04";
		}
		if (mon.equals("MAY")) {		
			mm = "05";
		}
		if (mon.equals("JUN")) {		
			mm = "06";
		}
		if (mon.equals("JUL")) {		
			mm = "07";
		}
		if (mon.equals("AUG")) {		
			mm = "08";
		}
		if (mon.equals("SEP")) {		
			mm = "09";
		}
		if (mon.equals("OCT")) {		
			mm = "10";
		}
		if (mon.equals("NOV")) {		
			mm = "11";
		}
		if (mon.equals("DEC")) {		
			mm = "12";
		}	
		return mm;	
	}	
	public String getTimeHhMm(String argument) {	
		String time = "";
		if (argument == null) {
			return "0000";
		}
		
		if (argument.substring(2, 3).equals(":") && argument.length() > 4) {
			time = argument.substring(0, 2) + argument.substring(3, 5);
		} else {
			time = argument.substring(0, 4); 
		}
		if (!checkInt(time)) {
			time =  "0000";
		}
		return time;
	}
	private boolean checkInt(String string) {       
        boolean ret;
        try {
			int value = Integer.parseInt(string);
			ret = true;
		} catch (NumberFormatException e) {
		   ret = false;
		}
		return ret;
	}	
	
	
	public void getKidsFromUnisys(MsgEntrySummaryDeclaration kidsMsg, MsgCustAwb msgCustAwb, String countryTo) {	
		
		kidsMsg.setDeclarationTime(getDeclarationTimeFromHeader()); 
		kidsMsg.setPersonLodgingSuma(getPartyFromUnisys(msgCustAwb.getCustRptInfo(), EUnisysParty.L, countryTo));
		//EI20110407: eigentlich, in der Testdatei wurde nur "L" geschickt, "S" und "C" in (master)Details
		kidsMsg.setRepresentative(getPartyFromUnisys(msgCustAwb.getCustRptInfo(), EUnisysParty.R, countryTo));
		kidsMsg.setCarrier(getPartyFromUnisys(msgCustAwb.getCustRptInfo(), EUnisysParty.I, countryTo));
		//EI20110407: kidsMsg.setConsignor(getPartyFromUnisys(msgCustAwb.getCustRptInfo(), EUnisysParty.S, countryTo));
		//EI20110407: kidsMsg.setConsignee(getPartyFromUnisys(msgCustAwb.getCustRptInfo(), EUnisysParty.C, countryTo));
		kidsMsg.setNotifyParty(getPartyFromUnisys(msgCustAwb.getCustRptInfo(), EUnisysParty.N, countryTo));
		
		// RESP ist fuer Response-messages
		int idx = 0;  //EI20110404:
		if (msgCustAwb.getDetailList() != null) {   
			for (Detail detail : msgCustAwb.getDetailList()) {				
				/* EI20110404: 
				kidsMsg.addGoodsItemList(setGoodsItemFromDetail(detail, countryTo)); // <== DETAIL kommt nur ein mal
				getKopfTagsFromDetail(detail);   
				*/
				idx = idx + 1;
				if (msgCustAwb.getDetailList().size() == 1) {  //alte Version mit nur einem Detail
					getKopfTagsFromDetail(kidsMsg, detail, "1", countryTo);  
					kidsMsg.addGoodsItemList(setGoodsItemFromDetail(detail, countryTo, 1));				 			
				} else if (msgCustAwb.getDetailList().size() > 1) {	
					if (idx == 1) {   //MAWB
						getKopfTagsFromDetail(kidsMsg, detail, "" + (msgCustAwb.getDetailList().size() - 1), countryTo);
					} else {	      //HAWB					
						//EI20110415: kidsMsg.addGoodsItemList(setGoodsItemFromDetail(detail, countryTo, idx));
						kidsMsg.addGoodsItemList(setGoodsItemFromDetail(detail, countryTo, 0)); //EO20110415: for many Details always 0
					}
				}
				
			}
		}		
	}
	
	public void getKopfTagsFromDetail(MsgEntrySummaryDeclaration kidsMsg, Detail detail, String totalNumberPositions,
									  String countryTo) {
		if (detail == null) {
			return;
		}
		if (!Utils.isStringEmpty(detail.getCustItem()))  {
			Utils.log("(MapCustAwbToKids) Detail-1 = " + detail.getCustItem());
		} 
		
		String originCountry = "";
		String ladingCountry = "";
		String unladingCountry = "";
		String destinationCountry = "";
		
		//EI20110404: kidsMsg.setTotalNumberPositions("1");   //Max: fix = 1
		kidsMsg.setTotalNumberPositions(totalNumberPositions); 
						
		if (detail.getCustAwbInfo() != null) {			
			kidsMsg.setPaymentType(detail.getCustAwbInfo().getPayCode());
			if (detail.getCustAwbInfo().getCustAWB() != null) {
				kidsMsg.setShipmentNumber(detail.getCustAwbInfo().getCustAWB().getAWB());
			}
			if (detail.getCustAwbInfo().getOrigin() != null) {
				originCountry = detail.getCustAwbInfo().getOrigin().getCountry();	
				if (!Utils.isStringEmpty(originCountry)) {
					kidsMsg.addCountryOfRoutingList(detail.getCustAwbInfo().getOrigin().getCountry());
				}
			}
			if (detail.getCustAwbInfo().getPartInfo() != null) {   //EI20110407
			kidsMsg.setConsignor(getPartyFromUnisys(detail.getCustAwbInfo().getPartInfo(), EUnisysParty.S, countryTo));
			kidsMsg.setConsignee(getPartyFromUnisys(detail.getCustAwbInfo().getPartInfo(), EUnisysParty.C, countryTo));
			}			
		}
				
		if (detail.getCustGoodsInfo() != null && detail.getCustGoodsInfo().getGdsDetailList() != null) {
			if (detail.getCustGoodsInfo().getGdsDetailList().size() > 0) {
				GdsDetail gds = detail.getCustGoodsInfo().getGdsDetailList().get(0);				
				if (gds != null) {						
					if (gds.getCtrySpec() != null && gds.getCtrySpec().getCustoms() != null) {
						kidsMsg.setReferenceNumber(gds.getCtrySpec().getCustoms().getEnsLref());	
						kidsMsg.setMrn(gds.getCtrySpec().getCustoms().getMrn());              //EI20110407 only for 313
					}
					if (gds.getManifested() != null) {						
						kidsMsg.setTotalNumberPackages(gds.getManifested().getPieces());						
						kidsMsg.setTotalGrossMass(gds.getManifested().getWeight());						
					}
					if (gds.getFlightInfo() != null) {												
						kidsMsg.setMeansOfTransportBorder(getTransportMeansBorderFromUnisys(gds.getFlightInfo(), null));
						kidsMsg.setConveyanceReference(gds.getFlightInfo().getCcd() + gds.getFlightInfo().getFlight());
					}
					if (gds.getArrival() != null) {
						kidsMsg.setDeclarationPlace(gds.getArrival().getStation());
						kidsMsg.setCustomsOfficeFirstEntry(gds.getArrival().getPortCode());
						kidsMsg.setDeclaredDateOfArrival(getDateTimeFromUnisys(gds.getArrival().getSta()));
					}							
			
					Segment segment = detail.getCustGoodsInfo().getGdsDetailList().get(0).getSegment();
					if (segment != null) {
						if (segment.getLading() != null) {						
							ladingCountry = segment.getLading().getCountry();					
							if (!Utils.isStringEmpty(ladingCountry) && !ladingCountry.equals(originCountry)) {
								kidsMsg.addCountryOfRoutingList(ladingCountry);
							}
							kidsMsg.setLoadingPlace(ladingCountry + segment.getLading().getStation());
						}
						if (segment.getUnlading() != null) {
							unladingCountry = segment.getUnlading().getCountry();
							if (!Utils.isStringEmpty(unladingCountry) && !unladingCountry.equals(originCountry)) {
								kidsMsg.addCountryOfRoutingList(unladingCountry);
							}
							kidsMsg.setUnloadingPlace(unladingCountry + segment.getUnlading().getStation());
						}
					}
				}
			}
		}
		
		if (detail.getCustAwbInfo() != null) {	
			if (detail.getCustAwbInfo().getDest() != null) {		
				destinationCountry = detail.getCustAwbInfo().getDest().getCountry();
				if (!Utils.isStringEmpty(destinationCountry) && !destinationCountry.equals(unladingCountry)) {
					kidsMsg.addCountryOfRoutingList(destinationCountry);
				}
			}
		}		
	}	
	
	//EI20110407: public GoodsItemLong setGoodsItemFromDetail(Detail detail, String countryTo) {
	public GoodsItemLong setGoodsItemFromDetail(Detail detail, String countryTo, int lfdPosition) {	
		//EO20110415: for only one Detail: lfdPosition=1, for many Details always 0 		
			
		if (detail.getCustItem() != null && 
			(detail.getCustItem().equals("MAWB") || detail.getCustItem().equals("MASTER"))) {  //EI20110407
			return null;
		}
		
		GoodsItemLong kidsItem = new GoodsItemLong();
		int lfd = 90;
		if (lfdPosition == 1) {  //for only one Detail "CustItem" is not sending
			kidsItem.setItemNumber("1");
			Utils.log("(MapCustAwbToKids) one Detail)");
		} else {			
			if (Utils.isStringEmpty(detail.getCustItem())) {
				kidsItem.setItemNumber("" + lfd);
				lfd = lfd + 1;
			} else {
				kidsItem.setItemNumber(detail.getCustItem());
			}		
		}
		
		if (detail.getCustAwbInfo() != null) {
			if (lfdPosition != 1) {   //EI20110415: solange Zabis-HGP anders plausibilisiert für 1 und mehrere Items 
			if (detail.getCustAwbInfo().getPartInfo() != null) {				
				List <Participant> list = detail.getCustAwbInfo().getPartInfo().getParticipantList();
				if (list != null && list.size() > 0) {
					for (Participant participant : list) {						
						if (participant.getType() != null && participant.getType().equals("S")) {
							kidsItem.setConsignor(getPartyFromUnisys(participant, countryTo));
						} else if (participant.getType() != null && participant.getType().equals("C")) {
							kidsItem.setConsignee(getPartyFromUnisys(participant, countryTo));
						} else if (participant.getType() != null && participant.getType().equals("N")) {
							kidsItem.setNotifyParty(getPartyFromUnisys(participant, countryTo));
						}
					}
				}						
			}	
			}
			kidsItem.setDescription(detail.getCustAwbInfo().getDescription());				
			kidsItem.setContainersList(detail.getCustAwbInfo().getUldNumbers());
			kidsItem.setCommodityCode(detail.getCustAwbInfo().getCommodityString());			
			kidsItem.setDocumentList(getDocumentListFromUnisys(detail.getCustAwbInfo().getDocumentList()));
			
			if (detail.getCustAwbInfo().getCustAWB() != null) {  //EI20110407
				String hawb = detail.getCustAwbInfo().getCustAWB().getHAWB();
				if (!Utils.isStringEmpty(hawb)) {
					IcsDocument document = new IcsDocument();
					document.setType("N703");
					document.setReference(hawb);
					kidsItem.addDocumentList(document);
				}
			}						
		}
		if (detail.getCustGoodsInfo() != null) {
			CustGoodsInfo goodsInfo = detail.getCustGoodsInfo();
			if (goodsInfo.getGdsDetailList() != null && goodsInfo.getGdsDetailList().size() > 0) {
				
				GdsDetail gds = goodsInfo.getGdsDetailList().get(0);						
				if (gds != null) {
					if (gds.getManifested() != null) {
						kidsItem.setGrossMass(gds.getManifested().getWeight());
					}
				
					if (gds.getSegment() != null && (lfdPosition != 1)) {  //EI20110415:  abfrage von lfdPosition, solange Zabis-HGP anders plausibilisiert für 1 und mehrere Items 
						if (gds.getSegment().getLading() != null) {				
							Place lading = gds.getSegment().getLading();					
							kidsItem.setLoadingPlace(lading.getCountry() + lading.getStation());
						}
						if (gds.getSegment().getUnlading() != null) {
							Place unlading = gds.getSegment().getUnlading();					
							kidsItem.setUnloadingPlace(unlading.getCountry() + unlading.getStation());
						}
					}
				}
				kidsItem.setPackagesList(getPackagesListUnisys(detail.getCustGoodsInfo().getGdsDetailList()));
				kidsItem.setMeansOfTransportBorderList(getMeansOfTransportListFromUnisys(goodsInfo.getGdsDetailList()));
			}		
		}		
		return kidsItem;
	}
				
}
