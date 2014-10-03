package com.kewill.kcx.component.mapping.countries.mt.ics.kids2mt;

import java.io.StringWriter;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgDeclarationAmendment;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.GoodsItemLong;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.countries.mt.ics.msg.MaltaMessage;
import com.kewill.kcx.component.mapping.formats.malta.common.MaltaHeader;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: MapICSEntrySummaryDeclarationAmendmentKM<br>
 * Created		: 15.08.2013<br>
 * Description	: Mapping of KIDS-Format into Malta-Format of 
 * 				  ICSEntrySummaryDeclarationAmendment message (IE313).
 * 				
 * @author Alfred Krzoska.
 * @version 1.0.00
 */

public class MapICSDeclarationAmendmentKM extends MaltaMessage {

	private MsgDeclarationAmendment 	msgKids;
	private String						nmsp="";

	
	public MapICSDeclarationAmendmentKM(XMLEventReader parser, 
										String 		   encoding, 
										String 		   nameSpace) throws XMLStreamException {
		msgKids = new MsgDeclarationAmendment(parser);
		this.encoding = encoding;
		if(!Utils.isStringEmpty(nameSpace)) {
			this.nmsp = nameSpace + ":";
		}
	}
	
	public String getMessage() {
		StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        		
        try {
        	writer = factory.createXMLStreamWriter(xmlOutputString);
        	
            msgKids.parse(HeaderType.KIDS);
			getCommonFieldsDTO().setReferenceNumber(msgKids.getReferenceNumber());   
            getCommonFieldsDTO().setTargetMessageType("CC313A");   
            
        	createRootTag();

			writeHeaderFields(nmsp);
//        	if (getKidsHeader() != null) {

					//writeTagsFromKidsHeader(getKidsHeader(), nmsp);
//				}
				writeBody();
				
        	closeElement();
        	
        	writer.writeEndDocument();           
            writer.flush();
            writer.close();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        
        return xmlOutputString.toString();
	}

	private void writeBody() {
		try {			                   
			writeHeahea(msgKids);					
			writeTraderConco(msgKids.getConsignor(), "TRACONCO1", nmsp);  
			writeTraderConce(msgKids.getConsignee(), "TRACONCE1", nmsp);  
			writeTraderNotpar(msgKids.getNotifyParty(), "NOTPAR670", nmsp);  				
			writeGooIteGdsMT(msgKids.getGoodsItemList(), "", msgKids.getMeansOfTransportBorder()); 
			writeIti(msgKids.getCountryOfRoutingList());                
			writeTraderRep(msgKids.getRepresentative(), "");    	
			writeTraderPer(msgKids.getPersonLodgingSuma(), "PERLODSUMDEC"); 
			writeSeaId(msgKids.getSealIDList());			
			writeCusOffEntry(msgKids.getCustomsOfficeFirstEntry(),  msgKids.getDeclaredDateOfArrival(), "");  
			writeCusoffSent740(msgKids.getCustomsOfficeOfSubsequentEntryList());
			writeTraderCar(msgKids.getCarrier(), "");   			
			
		} catch (XMLStreamException e) {
            e.printStackTrace();
        }  
	}

	private void writeHeahea(MsgDeclarationAmendment msg) {
		if (msg == null) {
			return;
		}
		try {
			openElement(nmsp + "HEAHEA");
				writeElement(nmsp + "DocNumHEA5", msg.getMrn());
				writeTransportBorder(msg.getMeansOfTransportBorder(), nmsp);				
				writeElement(nmsp + "TotNumOfIteHEA305", msg.getTotalNumberPositions());
				writeElement(nmsp + "TotNumOfPacHEA306", msg.getTotalNumberPackages());
				writeElement(nmsp + "TotGroMasHEA307", msg.getTotalGrossMass());
				writeElement(nmsp + "AmdPlaHEA598", msg.getDeclarationPlace());
				writeElement(nmsp + "SpeCirIndHEA1", msg.getSituationCode());
				writeElement(nmsp + "TraChaMetOfPayHEA1", msg.getPaymentType());
				writeElement(nmsp + "ComRefNumHEA", msg.getShipmentNumber());
				writeElement(nmsp + "ConRefNumHEA", msg.getConveyanceReference());
				writeElement(nmsp + "PlaLoaGOOITE334", msg.getLoadingPlace());
				writeElement(nmsp + "PlaLoaGOOITE334LNG", msg.getLoadingPlaceLng());
				writeElement(nmsp + "PlaUnlGOOITE334", msg.getUnloadingPlace());
				writeElement(nmsp + "CodPlUnHEA357LNG", msg.getUnloadingPlaceLng());
				writeElement(nmsp + "DatTimAmeHEA113", msg.getDeclarationTime());				
														
			closeElement();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}	

	private void writeGooIteGdsMT(List<GoodsItemLong> list, String msgNumber, TransportMeans borderKopf) 
		throws XMLStreamException {
		if (list == null) {
			return;
		}
		if (list.isEmpty()) {
			return;
		}
		String kopfMode = "";
		if (borderKopf != null) {
			kopfMode = borderKopf.getTransportMode();
			if (Utils.isStringEmpty(kopfMode)) {
				kopfMode = "";
			}
		}

		for (GoodsItemLong goodsItemLong : list) {
			if (goodsItemLong != null) {
				openElement(nmsp + "GOOITEGDS" + msgNumber);
					writeElement(nmsp + "IteNumGDS7", goodsItemLong.getItemNumber());
					writeElement(nmsp + "GooDesGDS23", goodsItemLong.getDescription());
					writeElement(nmsp + "GooDesGDS23LNG", goodsItemLong.getDescriptionLng());
					writeElement(nmsp + "GroMasGDS46", goodsItemLong.getGrossMass());
					writeElement(nmsp + "MetOfPayGDI12", goodsItemLong.getPaymentType());
					writeElement(nmsp + "ComRefNumGIM1", goodsItemLong.getShipmentNumber());
					writeElement(nmsp + "UNDanGooCodGDI1", goodsItemLong.getDangerousGoodsNumber());
					writeElement(nmsp + "PlaLoaGOOITE333", goodsItemLong.getLoadingPlace());
					writeElement(nmsp + "PlaLoaGOOITE333LNG", goodsItemLong.getLoadingPlaceLng());
					writeElement(nmsp + "PlaUnlGOOITE333", goodsItemLong.getUnloadingPlace());
					writeElement(nmsp + "PlaUnlGOOITE333LNG", goodsItemLong.getUnloadingPlaceLng());						
					writeDocList(goodsItemLong.getDocumentList(), msgNumber);
					writeSpemenmt(goodsItemLong.getSpecialMentionList());
					writeTraconco(goodsItemLong.getConsignor());					
					//writeElement("ComNomCMD1", goodsItemLong.getCommodityCode());	
					writeComCodMT(goodsItemLong.getCommodityCode());
					writeTraconceMT(goodsItemLong.getConsignee());
					writeConnr(goodsItemLong.getContainersList(), msgNumber);
					if (!kopfMode.equals("4")) {  //EI20110714
						writeIdemeatragi(goodsItemLong.getMeansOfTransportBorderList(), msgNumber);		
					}
					writePacgs(goodsItemLong.getPackagesList());
					writePrtnot(goodsItemLong.getNotifyParty());
				closeElement();
			}
		}
	}	
	
    private void  writeComCodMT(String comcod)  throws XMLStreamException {
    	if (Utils.isStringEmpty(comcod)) {
			return;
		}
    	openElement(nmsp + "COMCODGODITM");
    		writeElement(nmsp + "ComNomCMD1", comcod);	
    	closeElement();
    }
    
    private void writeTraconceMT(Party consignee)   throws XMLStreamException {
		if (consignee == null) {
			return;
		}
		if (consignee.isEmpty()) {
			return;
		}
		Address address = consignee.getAddress();
		
		if (address != null) {
			openElement(nmsp + "TRACONCE2");
				writeElement(nmsp + "NamCE27", address.getName());
				if (Utils.isStringEmpty(address.getHouseNumber())) {
					writeElement(nmsp + "StrAndNumCE222", address.getStreet());
				} else {
					writeElement(nmsp + "StrAndNumCE222", address.getStreet() + " " + address.getHouseNumber());
				}
				writeElement(nmsp + "PosCodCE223", address.getPostalCode());
				writeElement(nmsp + "CitCE224", address.getCity());
				writeElement(nmsp + "CouCE225", address.getCountry());
				writeElement(nmsp + "NADLNGGICE", address.getLanguage());
				if (consignee.getPartyTIN() != null) {
					writeElement(nmsp + "TINCE259", consignee.getPartyTIN().getTIN());
				}
			closeElement();
		}
	}
    
	private void createRootTag() throws XMLStreamException {
		if(Utils.isStringEmpty(this.nmsp)) {
			openElement("http://ces.gov.mt/ics/schemas/CC313A", "CC313A");
		} else {
			openElement(this.nmsp + "CC313A");
		}
	}

}
