package com.kewill.kcx.component.mapping.companies.unisys.ics.unisys2kids;


import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.KidsMessageICS;
import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.MsgCustAwb;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgDeclarationAmendment;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgEntrySummaryDeclaration;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.ics.BodyDeclarationAmendmentKids;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul		: MapCustAwbToKids<br>
 * Erstellt		: 06.12.2010<br>
 * Beschreibung	: Mapping of Unisys CUST-AWB-MSG Message to KIDS DeclarationAmendment.
 *                IE313 in ../customsXchange/Customers/Unisys/UNISYS_To_KIDS.xlsx
 * @author iwaniuk
 * @version 1.0.00
 */
public class MapDeclarationAmendmentUniK extends KidsMessageICS {
  //kopiert von MapEntrySummaryDeclarationUniK; ==>  Unterschied: MRN vorhanden

	private MsgCustAwb msgCustAwb = null;   	
	private MsgDeclarationAmendment kidsMsg	= null;
	
	private String countryTo = "";

		
	public MapDeclarationAmendmentUniK(XMLEventReader parser, KidsHeader kidsHeader, String encoding) 
																			throws XMLStreamException {
			msgCustAwb  = new MsgCustAwb(parser);
			kidsMsg = new MsgDeclarationAmendment();
	        
			this.kidsHeader	= kidsHeader;			
			this.encoding = encoding;
			countryTo = kidsHeader.getCountryCode();
	}
	
	public void getMessage(XMLStreamWriter writer) {
		 this.writer = writer;
	     try {
	    	    BodyDeclarationAmendmentKids body = new BodyDeclarationAmendmentKids(writer);
	          
	            writeStartDocument(encoding, "1.0");
	            openElement("soap:Envelope");
	            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
	          	            	
	            msgCustAwb.parse(HeaderType.UNISYS);
	            kidsHeader.writeHeader();
	            countryTo = kidsHeader.getCountryCode();
	            
	            MsgEntrySummaryDeclaration kidsDeclaration = new MsgEntrySummaryDeclaration();
	            getKidsFromUnisys(kidsDeclaration, msgCustAwb, countryTo);
	            getAmendmentFromDeclaration(kidsDeclaration);	
	            
	            getCommonFieldsDTO().setReferenceNumber(kidsMsg.getReferenceNumber());
	            
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
		
	//private MsgDeclarationAmendment getKidsFromUnisys() {
	private void getAmendmentFromDeclaration(MsgEntrySummaryDeclaration kidsDeclaration) {
		
		kidsMsg.setReferenceNumber(kidsDeclaration.getReferenceNumber());
		kidsMsg.setShipmentNumber(kidsDeclaration.getShipmentNumber());	
		kidsMsg.setMrn(kidsDeclaration.getMrn());  
		kidsMsg.setTotalNumberPositions(kidsDeclaration.getTotalNumberPositions()); 
		kidsMsg.setTotalNumberPackages(kidsDeclaration.getTotalNumberPackages());		
		kidsMsg.setTotalGrossMass(kidsDeclaration.getTotalGrossMass());
		kidsMsg.setMeansOfTransportBorder(kidsDeclaration.getMeansOfTransportBorder());
		kidsMsg.setConveyanceReference(kidsDeclaration.getConveyanceReference());
		kidsMsg.setLoadingPlace(kidsDeclaration.getLoadingPlace());
		kidsMsg.setUnloadingPlace(kidsDeclaration.getUnloadingPlace());
		kidsMsg.setPaymentType(kidsDeclaration.getPaymentType());		
		kidsMsg.setDeclarationTime(kidsDeclaration.getDeclarationTime()); 		
		kidsMsg.setDeclarationPlace(kidsDeclaration.getDeclarationPlace());
		kidsMsg.setConsignor(kidsDeclaration.getConsignor());
		kidsMsg.setConsignee(kidsDeclaration.getConsignee());
		kidsMsg.setNotifyParty(kidsDeclaration.getNotifyParty());		
		kidsMsg.setPersonLodgingSuma(kidsDeclaration.getPersonLodgingSuma());
		kidsMsg.setRepresentative(kidsDeclaration.getRepresentative());		
		kidsMsg.setCarrier(kidsDeclaration.getCarrier());   
		kidsMsg.setCustomsOfficeFirstEntry(kidsDeclaration.getCustomsOfficeFirstEntry());
		kidsMsg.setDeclaredDateOfArrival(kidsDeclaration.getDeclaredDateOfArrival());
		kidsMsg.setSealIDList(kidsDeclaration.getSealIDList());
		kidsMsg.setCountryOfRoutingList(kidsDeclaration.getCountryOfRoutingList());

		if (kidsDeclaration.getGoodsItemList() != null  && kidsDeclaration.getGoodsItemList().size() > 0) {			
			kidsMsg.setGoodsItemList(kidsDeclaration.getGoodsItemList());		
		}
	}
}
