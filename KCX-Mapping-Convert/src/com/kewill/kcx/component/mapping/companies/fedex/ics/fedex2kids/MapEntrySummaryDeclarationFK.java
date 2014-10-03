package com.kewill.kcx.component.mapping.companies.fedex.ics.fedex2kids;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.FedexHeader;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.MsgCC315A;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.MsgCC315APos;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.Connr2;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.CustomsOffice;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.Heahea;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.Idemeatragi970;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.Iti;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.Pacgs2;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.Prodocd2;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.Seaide529;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.Spemenmt2;
import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.SealNumber;
import com.kewill.kcx.component.mapping.countries.common.SpecialMention;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgEntrySummaryDeclaration;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.GoodsItemLong;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.IcsDocument;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.ics.BodyEntrySummaryDeclarationKids;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/** 
 * Module		: MapEntrySummaryDeclarationFK<br>
 * Created		: 27.12.2010
 * Description	: .
 * 
 * @author 	krzoska
 * @version	1.0.00
 */
public class MapEntrySummaryDeclarationFK extends KidsMessageFDX {
	private BodyEntrySummaryDeclarationKids body 		= null;
	private MsgCC315A	 	msgFedex = null;
	private FedexHeader		fHeader = null;
	private String          kcxId = "";         //EI20110329

		
	public MapEntrySummaryDeclarationFK(XMLEventReader parser, KidsHeader kidsHeader, String encoding, 
										FedexHeader fedexHeader) throws XMLStreamException {
	        msgFedex = new MsgCC315A(parser);
			this.encoding = encoding;
			this.kidsHeader = kidsHeader;
			fHeader 		= fedexHeader;

	}
	
	
	public void getMessage(XMLStreamWriter writer) {
		 this.writer = writer;
	        try {
	            body = new BodyEntrySummaryDeclarationKids(writer);

	            writeStartDocument(encoding, "1.0");
	            openElement("soap:Envelope");
	            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
	          	            	
	            msgFedex.parse(HeaderType.FEDEX);
				
	            if (msgFedex.getReceiver() != null && msgFedex.getReceiver().length() > 1) {
	            	//AK20101213
		            fHeader.setCountryCode(msgFedex.getReceiver().substring(0, 2));	            	
	            }
				
	            mapKidsHeaderFromMessage();
	            kidsHeader.writeHeader();
	            
	            MsgEntrySummaryDeclaration kidsMsg = getKidsFromFedex();
	            
	            if (msgFedex.getHeahea() != null) {
		            getCommonFieldsDTO().setReferenceNumber(msgFedex.getHeahea().getReferenceNumber());	            	
	            }


	            body.setKidsHeader(kidsHeader);
	            body.setMessage(kidsMsg);
	            body.writeBody();

	            closeElement();  // soap:Envelope
	            writer.writeEndDocument();

	            writer.flush();
	            writer.close();

	        } catch (XMLStreamException e) {
	            e.printStackTrace();
	        }
	}
	
	private void mapKidsHeaderFromMessage() {
		///String kcxId = ""; 
		String countryCode = "";
				
		if (this.msgFedex.getReceiver() != null && !Utils.isStringEmpty(this.msgFedex.getReceiver())) {
			countryCode = this.msgFedex.getReceiver().substring(0, 2);
		}			
		//AK20120418: kcxId = Utils.getKewillIdFromCustomer(this.msgFedex.getReceiver(), "KIDS");	
		kcxId = Utils.getKewillIdFromCustomer(this.msgFedex.getReceiver(), "FEDEX");	

		this.kidsHeader.setReceiver(kcxId);	
		this.kidsHeader.setTransmitter(this.msgFedex.getTransmitter());						
		this.kidsHeader.setCountryCode(countryCode);
		
		setMapping();
	}
	
	private MsgEntrySummaryDeclaration getKidsFromFedex() {
		MsgEntrySummaryDeclaration msgKids = new MsgEntrySummaryDeclaration();
		
		if (msgFedex.getHeahea() != null) {
			msgKids.setReferenceNumber(msgFedex.getHeahea().getReferenceNumber());
			msgKids.setMeansOfTransportBorder(setTransportMeansBorder(msgFedex.getHeahea()));
			msgKids.setTotalGrossMass(msgFedex.getHeahea().getTotalGrossMass());
			msgKids.setDeclarationPlace(msgFedex.getHeahea().getDeclarationPlace());
			msgKids.setSituationCode(msgFedex.getHeahea().getSituationCode());
			msgKids.setPaymentType(msgFedex.getHeahea().getPaymentType());
			if (!Utils.isStringEmpty(msgFedex.getHeahea().getShipmentNumber())) {
				msgKids.setShipmentNumber(msgFedex.getHeahea().getShipmentNumber());
			} else {
				msgKids.setShipmentNumber(msgFedex.getShipmentNumber());
			}
			
			//AK20110103
			msgKids.setTotalNumberPositions(msgFedex.getHeahea().getTotalNumberPositions());
			msgKids.setTotalNumberPackages(msgFedex.getHeahea().getTotalNumberPackages());
			
			msgKids.setConveyanceReference(msgFedex.getHeahea().getConveyanceReference());
			msgKids.setLoadingPlace(msgFedex.getHeahea().getLoadingPlace());
			msgKids.setLoadingPlaceLng(msgFedex.getHeahea().getLoadingPlaceLng());
			msgKids.setUnloadingPlace(msgFedex.getHeahea().getUnloadingPlace());
			msgKids.setUnloadingPlaceLng(msgFedex.getHeahea().getUnloadingPlaceLng());
			msgKids.setDeclarationTime(msgFedex.getHeahea().getDeclarationTime());
			
		}
			
		msgKids.setConsignor(setParty(msgFedex.getTraconco1(), "ConsignorAddress"));
		msgKids.setConsignee(setParty(msgFedex.getTraconce1(), "ConsigneeAddress"));
		msgKids.setNotifyParty(setParty(msgFedex.getNotpar670(), "NotifyPartyAddress"));
		msgKids.setCountryOfRoutingList(setCountryOfRoutingList(msgFedex.getItiList()));
		if (msgFedex.getCusofflon() != null) {
			msgKids.setCustomsOfficeOfLodgment(msgFedex.getCusofflon().getCustomsOfficeOfLodgement());
		}
		//msgKids.setNotifyParty(setParty(msgFedex.getNotpar670(), "NotifyPartyAddress"));
		msgKids.setRepresentative(setParty(msgFedex.getTrarep(), "RepresentativeAddress"));
		msgKids.setPersonLodgingSuma(setParty(msgFedex.getPerlodsumdec(), "PersonLodgingSumaAddress"));
		if (msgKids.getPersonLodgingSuma() == null) {
			Party party = new Party("PersonLodgingSuma");
			msgKids.setPersonLodgingSuma(party);
		}	
		checkPartyTin(kcxId, "IE315/PersonLodgingSumaTIN/TIN", msgKids.getPersonLodgingSuma());  //EI20110330
		
		msgKids.setSealIDList(setSealIDList(msgFedex.getSeaide529List()));
		
		if (msgFedex.getCusoffent730() != null) {
			msgKids.setCustomsOfficeFirstEntry(msgFedex.getCusoffent730().getCustomsOfficeOfFirstEntry());
			msgKids.setDeclaredDateOfArrival(msgFedex.getCusoffent730().getExpectedDateOfArrival());				
		}
		msgKids.setCustomsOfficeOfSubsequentEntryList(
				setCustomsOfficeOfSubsequentEntryList(msgFedex.getCusoffent740List()));		
		msgKids.setCarrier(setParty(msgFedex.getTracarent601(), "CarrierAddress"));
		
		if (msgFedex.getGoodsItemList() != null) {
			for (MsgCC315APos goodsItem : msgFedex.getGoodsItemList()) {
				writeGoodsItemList(goodsItem, msgKids); 
			}
		}

		return msgKids;
	}
	
	private List<String> setCustomsOfficeOfSubsequentEntryList(List<CustomsOffice> fedexCustomsOfficeList) {
		List<String> customsOfficeOfSubsequentEntryList = null;
		
		if (fedexCustomsOfficeList != null) {
			customsOfficeOfSubsequentEntryList = new Vector<String>();
			for (CustomsOffice customsOffice : fedexCustomsOfficeList) {
				customsOfficeOfSubsequentEntryList.add(customsOffice.getCustomsOfficeOfSubsequentEntry());
			}
		}
		return customsOfficeOfSubsequentEntryList;
	}
	

	private List<SealNumber> setSealIDList(List<Seaide529> seaide529List) {
		SealNumber sealID = null;		
		ArrayList<SealNumber> sealIdList = null;
				
		if (seaide529List != null) {
			sealIdList = new ArrayList<SealNumber>();
			
			for (Seaide529 seaide529 : seaide529List) {
				if (seaide529 != null) {
					sealID = new SealNumber();
					sealID.setNumber(seaide529.getSealsIdentity());
					sealID.setLanguage(seaide529.getSealsIdentityLng());
				}
				sealIdList.add(sealID);
			}
		}
		return sealIdList;
	}


	private List<String> setCountryOfRoutingList(List<Iti> itiList) {
		String countryOfRouting = null;
		List<String> countryOfRoutingList = null;
		
		if (itiList != null) {
			countryOfRoutingList = new Vector<String>();
			for (Iti iti : itiList) {
				countryOfRouting = new String(iti.getCountryOfRouting());
				
				countryOfRoutingList.add(countryOfRouting);
			}
		}
		return countryOfRoutingList;
	}


	private void writeGoodsItemList(MsgCC315APos fedexItem, MsgEntrySummaryDeclaration msgKids) {
		GoodsItemLong kidsItem = new GoodsItemLong();
		
		if (!Utils.isStringEmpty(fedexItem.getItemNumber())) { 
			kidsItem.setItemNumber(fedexItem.getItemNumber());
		} else if (msgKids.getGoodsItemList().size() == 1) {
			kidsItem.setItemNumber("1");
		}
		
		kidsItem.setDescription(fedexItem.getDescription());
		kidsItem.setDescriptionLng(fedexItem.getDescriptionLng());
		if (!Utils.isStringEmpty(fedexItem.getGrossMass())) {
			kidsItem.setGrossMass(fedexItem.getGrossMass());
		} else {
			kidsItem.setGrossMass(msgKids.getTotalGrossMass());
		}
		kidsItem.setPaymentType(fedexItem.getPaymentType());
		kidsItem.setShipmentNumber(fedexItem.getShipmentNumber());
		kidsItem.setDangerousGoodsNumber(fedexItem.getDangerousGoodsNumber());
		kidsItem.setLoadingPlace(fedexItem.getLoadingPlace());
		kidsItem.setLoadingPlaceLng(fedexItem.getLoadingPlaceLng());
		kidsItem.setUnloadingPlace(fedexItem.getUnloadingPlace());
		kidsItem.setUnloadingPlaceLng(fedexItem.getUnloadingPlaceLng());
		
		kidsItem.setDocumentList(setDocumentList(fedexItem.getProdocdc2List()));
		kidsItem.setSpecialMentionList(setSpSpecialMentionList(fedexItem.getSpecialMentionList()));
		kidsItem.setConsignor(setParty(fedexItem.getTraconco2(), "ConsignorAddress"));
		kidsItem.setConsignee(setParty(fedexItem.getTraconce2(), "ConsigneeAddress"));
		
		//AK20120427
		if (fedexItem.getComcodgoditm() != null) {
			String warenNr = fedexItem.getComcodgoditm().getCommodityCode();
			if (warenNr != null && warenNr.trim().length() > 4) {
				warenNr = warenNr.trim().substring(0, 4);
				kidsItem.setCommodityCode(warenNr);
			}
		}
		
		kidsItem.setContainersList(setContainersList(fedexItem.getConnr2List()));
		kidsItem.setMeansOfTransportBorderList(setMeansOfTransportBorderList(fedexItem.getIdemeatragi970List()));
		kidsItem.setPackagesList(setPackagesList(fedexItem.getPacgs2List()));
		
		msgKids.addGoodsItemList(kidsItem);
	}


	private List<Packages> setPackagesList(List<Pacgs2> pacgs2List) {
		Packages packages = null;
		List<Packages> packagesList = null;
		
		if (pacgs2List != null) {
			packagesList = new Vector<Packages>();
			
			for (Pacgs2 pacgs2 : pacgs2List) {
				packages = new Packages();
				
				packages.setType(pacgs2.getType());
				packages.setSequentialNumber(pacgs2.getNumber());
				packages.setQuantity(pacgs2.getQuantity());
				packages.setMarks(pacgs2.getMarks());
				packages.setMarksLng(pacgs2.getMarksLng());
				
				packagesList.add(packages);
			}
		}
		return packagesList;
	}


	private List<TransportMeans> setMeansOfTransportBorderList(List<Idemeatragi970> idemeatragi970List) {
		TransportMeans transportMeans = null;
		List<TransportMeans> transportMeansList = null;
		
		if (idemeatragi970List != null) {
			transportMeansList = new Vector<TransportMeans>();
			
			for (Idemeatragi970 idemeatragi970 : idemeatragi970List) {
				transportMeans = new TransportMeans();
				
				transportMeans.setTransportationCountry(idemeatragi970.getTransportationCountry());
				transportMeans.setTransportationNumber(idemeatragi970.getTransportationNumber());
				
				transportMeansList.add(transportMeans);
			}
		}
		
		return transportMeansList;
	}


	private List<String> setContainersList(List<Connr2> connr2List) {
		String container = null;
		List<String> containersList = null;
		
		if (connr2List != null) {
			containersList = new Vector<String>();
			for (Connr2 connr2 : connr2List) {
				container = new String(connr2.getContainer());
				
				containersList.add(container);
			}
		}
		
		return containersList;
	}


	private List<SpecialMention> setSpSpecialMentionList(List<Spemenmt2> spemenmt2List) {
		SpecialMention specialMention = null;
		List<SpecialMention> specialMentionList = null;
		
		if (spemenmt2List != null) {
			specialMentionList = new Vector<SpecialMention>();
			for (Spemenmt2 spemenmt2 : spemenmt2List) {
				specialMention = new SpecialMention();
				specialMention.setCode(spemenmt2.getAddInfCodMT23());
				specialMentionList.add(specialMention);
			}
		}
		
		return specialMentionList;
	}


	private List<IcsDocument> setDocumentList(List<Prodocd2> prodocdc2List) {
		IcsDocument document = null;
		List<IcsDocument> documentList = null;  
		
		if (prodocdc2List != null) {
			documentList = new Vector<IcsDocument>();
			for (Prodocd2 prodocd2 : prodocdc2List) {
				document = new IcsDocument();
				document.setType(prodocd2.getType());
				document.setReference(prodocd2.getReference());
				document.setReferenceLng(prodocd2.getReferenceLng());
				
				documentList.add(document);
			}
		}
		
		return documentList;
	}


	

	private TransportMeans setTransportMeansBorder(Heahea heahea) {
		TransportMeans trm = new TransportMeans();

		trm.setTransportMode(heahea.getTransportMode());
		trm.setTransportationCountry(heahea.getTransportationCountry());

		return trm;
	}		

}
