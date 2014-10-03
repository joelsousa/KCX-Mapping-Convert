package com.kewill.kcx.component.mapping.companies.fedex.ics.MultiFedex;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.FedexMessage;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.MsgCC315A;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.MsgCC315APos;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.Connr2;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.CustomsOffice;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.FedexAddress;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.Heahea;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.Idemeatragi970;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.Iti;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.Pacgs2;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.Prodocd2;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.Seaide529;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.Spemenmt2;
import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.SealNumber;
import com.kewill.kcx.component.mapping.countries.common.SpecialMention;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgEntrySummaryDeclaration;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.GoodsItemLong;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.IcsDocument;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.db.CustomerProcedureDTO;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.ics.BodyEntrySummaryDeclarationKids;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

public class MultiMapICSEntrySummaryDeclarationFK extends KidsMessage  {

	private ConnexionMessages fedexMessages = null;
	
	public MultiMapICSEntrySummaryDeclarationFK(XMLEventReader parser, KidsHeader kidsHeader, String encoding) 
												throws XMLStreamException {			
	        
			this.kidsHeader	= kidsHeader;
			this.encoding = encoding;
			fedexMessages = new ConnexionMessages();
			//MapICSEntrySummaryDeclarationFK
	}
	
	
	public void getMessages() {   
		fedexMessages.parse(HeaderType.FEDEX);  //dazu muss in KCXMessage: START_TAG = "Messages" statt "MessageBody"
		for (FedexMessage msg : fedexMessages.getMessagesList()) {
			getMessage(msg);
		}       
	}
	public String getMessage(FedexMessage msg) {   
		StringWriter xmlOutputString = new StringWriter();
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
                
        BodyEntrySummaryDeclarationKids body = null;
        try {
            writer = factory.createXMLStreamWriter(xmlOutputString);
            body = new BodyEntrySummaryDeclarationKids(writer);
            kidsHeader.setWriter((writer));
            writeStartDocument(encoding, "1.0");
            
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
                       
            MsgEntrySummaryDeclaration kidsMsg = getKidsFromFedex(msg.getMessageBody().getMsgCC315A());
            
            mapKidsHeaderFromMessage(msg);
            kidsHeader.writeHeader();
            
            body.setMessage(kidsMsg); 
            body.setKidsHeader(kidsHeader);
            
            body.writeBody();
            
            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
            Utils.log("(MapICSEntrySummaryDeclarationFK getMessage) Msg = " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
        
        return xmlOutputString.toString();
    }	
	
	private void mapKidsHeaderFromMessage(FedexMessage msg) {	
		String receiver = msg.getMessageBody().getMsgCC315A().getReceiver();
		this.kidsHeader.setReceiver(receiver);
		String countryCode = "";
		if (!Utils.isStringEmpty(receiver)) {
			countryCode = receiver.substring(0, 2);
		}	
		this.kidsHeader.setCountryCode(countryCode);
		//und weitere tags von msg.getMessageHeader()
		setMapping();
	}
	public void setMapping() {	    	
	    	if (kidsHeader.getMap() == null) {
	            CustomerProcedureDTO customerProcedureDTO = Utils.getCustomerProceduresFromKcxId(
	            		                    kidsHeader.getReceiver(),
	            							kidsHeader.getProcedure().toUpperCase());
	            customerProcedureDTO.setMsgFormat("FEDEX");
	            String mappingCode = customerProcedureDTO.getMappingCode();	            	            
	            kidsHeader.setMapFrom(mappingCode); 	            	
	            kidsHeader.setMapTo(kidsHeader.getCountryCode());	           
	            	        
	            if (kidsHeader.getMapFrom().equalsIgnoreCase(kidsHeader.getMapTo())) {
	            	kidsHeader.setMap("0");
	            } else {
	            	kidsHeader.setMap("1");
	            }
			} else {
	        	// Sind in den Encoding-Angaben "mapFrom" und "mapTo" gleich
	        	// d.h. das Sender- und Empfängerland identisch, 
	        	// wird kein Mapping durchgeführt und die Angabe im Tag "Map" entsprechend
	        	// auf "0" gesetzt!
	        	if (kidsHeader.getMapFrom().equalsIgnoreCase(kidsHeader.getMapTo())) {
	        		kidsHeader.setMap("0");
	        	}
			}
	}

	private MsgEntrySummaryDeclaration getKidsFromFedex(MsgCC315A msgFedex) {  //stand von 1.12.2010
		if (msgFedex == null) {
			return null;
		}					
		MsgEntrySummaryDeclaration msgKids = new MsgEntrySummaryDeclaration();	
		
		if (msgFedex.getHeahea() != null) {
			msgKids.setReferenceNumber(msgFedex.getHeahea().getReferenceNumber());
			msgKids.setMeansOfTransportBorder(setTransportMeansBorder(msgFedex.getHeahea()));
			msgKids.setTotalGrossMass(msgFedex.getHeahea().getTotalGrossMass());
			msgKids.setDeclarationPlace(msgFedex.getHeahea().getDeclarationPlace());
			msgKids.setSituationCode(msgFedex.getHeahea().getSituationCode());
			msgKids.setPaymentType(msgFedex.getHeahea().getPaymentType());
			msgKids.setShipmentNumber(msgFedex.getHeahea().getShipmentNumber());
			
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
		msgKids.setNotifyParty(setParty(msgFedex.getNotpar670(), "NotifyPartyAddress"));
		msgKids.setRepresentative(setParty(msgFedex.getTrarep(), "RepresentativeAddress"));
		msgKids.setPersonLodgingSuma(setParty(msgFedex.getPerlodsumdec(), "PersonLodgingSumaAddress"));
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
		
		kidsItem.setItemNumber(fedexItem.getItemNumber());
		kidsItem.setDescription(fedexItem.getDescription());
		kidsItem.setDescriptionLng(fedexItem.getDescriptionLng());
		kidsItem.setGrossMass(fedexItem.getGrossMass());
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
		if (fedexItem.getComcodgoditm() != null) {
			kidsItem.setCommodityCode(fedexItem.getComcodgoditm().getCommodityCode());			
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


	private Party setParty(FedexAddress fedexaddress, String person) {
		Party  	party = null;
		Address kidsAddress = new Address();
		TIN 	tin = new TIN();
		
		if (fedexaddress != null) {
			party = new Party(person);
			kidsAddress.setName(fedexaddress.getName());
			kidsAddress.setStreet(fedexaddress.getStreet());
			kidsAddress.setPostalCode(fedexaddress.getPostalCode());
			kidsAddress.setCity(fedexaddress.getCity());
			kidsAddress.setCountry(fedexaddress.getCountry());
			kidsAddress.setLanguage(fedexaddress.getLng());
			
			party.setAddress(kidsAddress);
			tin.setTin(fedexaddress.getTin());
			party.setPartyTIN(tin);
			
		}
		
		return party;
	}

	private TransportMeans setTransportMeansBorder(Heahea heahea) {
		TransportMeans trm = new TransportMeans();

		trm.setTransportMode(heahea.getTransportMode());
		trm.setTransportationNumber(heahea.getTransportMode());
		trm.setTransportationCountry(heahea.getTransportationCountry());

		return trm;
	}	
}
