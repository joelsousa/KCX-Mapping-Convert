package com.kewill.kcx.component.mapping.countries.de.cmp.kids2cmp;

import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.MsgCustomsResponse;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.Custst;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.HeaderDetail;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.ItemDetails;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.PartyDetails;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.ResponseBody;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.MsgGoodsReleasedExternal;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageManifest20;
import com.kewill.kcx.component.mapping.formats.kids.manifest20.BodyCustomsResponseKids;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module        : Manifest / Suma<br>
 * Created       : 10.07.2013<br>
 * Description   : Mapping of Kids-GoodsReleasedExternal to CMP-CustomsResponse.
 * 				 

 * @author iwaniuk
 * @version 1.0.00
 */

public class MapGoodsReleasedExternalKC extends KidsMessageManifest20 {
	
	private BodyCustomsResponseKids body = null;
	private MsgCustomsResponse cmp = null;
	private MsgGoodsReleasedExternal message = null;
	
	public MapGoodsReleasedExternalKC(XMLEventReader parser, String encoding) throws XMLStreamException {
		message = new MsgGoodsReleasedExternal(parser);
		cmp = new MsgCustomsResponse();
		this.encoding = encoding;	
	}
	
	public String getMessage() {
		   StringWriter xmlOutputString = new StringWriter();
	        
	        XMLOutputFactory factory = XMLOutputFactory.newInstance();
	        try {
	            writer = factory.createXMLStreamWriter(xmlOutputString);
	            body   = new BodyCustomsResponseKids(writer);
	            //kidsHeader = new KidsHeader();
	            kidsHeader.setWriter((writer));
	            
	            writeStartDocument(encoding, "1.0");
	            
	          //EI20131001: openElement("soap:Envelope");
	          //EI20131001: setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
	            
	            message.parse(HeaderType.KIDS); 
	            mapKidsToCmpHeader(message.getReferenceNumber());           
	            //kidsHeader.writeHeader();
	            	           
	            mapKidsToCmpBody();
	            body.setMessage(cmp);

	            this.getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
	            body.setCommonFieldsDTO(this.getCommonFieldsDTO());  
	            //EI20140214: body.writeBody("CUSCAN");
	            body.writeBody("CUSTST");  //EI20140214: 
	            
	          //EI20131001: closeElement();  // soap:Envelope
	            writer.writeEndDocument();
	            
	            writer.flush();
	            writer.close();
	            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return xmlOutputString.toString();
    }
	
	private void mapKidsToCmpBody() {
		//EI20140214: cmp.setMessageHeader(this.mapResponseHeader(message.getLocalApplication(), "CUSCAN", message.getReferenceNumber()));
		cmp.setMessageHeader(this.mapResponseHeader(message.getLocalApplication(), "CUSTST", message.getReferenceNumber())); //EI20140214
		cmp.setMessageBody(this.mapMessageBody());
	}
		
	private ResponseBody mapMessageBody() {
		ResponseBody mBody = new ResponseBody();
		ArrayList<ItemDetails> list = new ArrayList<ItemDetails>();			
		Custst custst = new Custst();
			
		if (message.getLocalApplication() != null) {
			//EI20140324:mBody.setDeclarationStatus(message.getLocalApplication().getDeclarationStatus());	
			//EI20140324: hier kann nur status 45 sein, aber wg. falschen Reihenfolge beim Zoll
			// kommt CUSTST vor CUSREC (es geht un Bruchstücken vor Sekunden)und deshalb bekommt LCAG status 30 (der in 
			// ZABIS erst nach erhalten von CUSREC auf 40 gesetzt wird). Da aber CUSTST von Zoll nur dann generiert wird, 
			// wenn vorhen CUSREC generiert wurde, ist dies hier OK:
			mBody.setDeclarationStatus("45");  
		}
		//mBody.setCuscan(cuscan);
		mBody.setCustst(custst);
		custst.setHeaderDetail(this.mapHeaderDetail());
		
		if (message.getCustodian() != null) {							
			list.add(this.mapItemDetails(message.getCustodian(), "1"));			
		}
		/*
		if (message.getPlaceOfCustody() != null) {							
			list.add(this.mapItemDetails(message.getPlaceOfCustody(), "2"));			
		}
		*/
		
		custst.setItemDetailsList(list);
		
		return mBody;
	}
	
	private HeaderDetail mapHeaderDetail() {
		if (message == null) {
			return null;
		}
		if (message.getLocalApplication() == null) { //ohne LocalApplication geht nicht, weil in ReferenceNumber MRN steht!
			return null;
		}
		HeaderDetail detail = new HeaderDetail();
		String flnr = message.getLocalApplication().getFlightNumber();	
		
		detail.setFlightNumber(flnr);
		detail.setAirportOfDeparture(message.getLocalApplication().getAirportOfDeparture()); 
		detail.setDepartureDate(this.calculateDepartureDate(message.getReferenceNumber(), flnr));  
		detail.setAirportOfArrival(message.getLocalApplication().getAirportOfArrival());	 //TODO-Daggi
		if (Utils.isStringEmpty(detail.getAirportOfArrival())) {
			detail.setAirportOfArrival(this.calculateAirportCodeFromKcxid(this.kidsHeader));
		}
		detail.setArrivalDate(message.getLocalApplication().getArrivalDate());				//TODO-Daggi 	
		
		detail.setCustomsRegistration(message.getRegistrationNumber());
		detail.setRegistrationDate(message.getRegistrationDate());
		
		return detail;
	}
	
	private ItemDetails mapItemDetails(Address adr, String lfd) {
		if (adr == null) {
			return null;
		}		
		
		ItemDetails detail = new ItemDetails();
		
		detail.setLineItemNumber(lfd);
		if (message.getLocalApplication() != null) {
			//detail.setLineStatus(message.getLocalApplication().getDeclarationStatus());  //EI20140317: es gibt nur den einen Item
			detail.setLineStatus("45");  //EI20140324: gilt das gleiche, wie fuer den Kopf
		}		
		
		if (adr != null) {			
			PartyDetails custodyDetails = new PartyDetails();
			
			custodyDetails.setName(adr.getName());
			custodyDetails.setStreet(adr.getStreet());
			custodyDetails.setZipCode(adr.getPostalCode());
			custodyDetails.setCity(adr.getCity());
			
			detail.setCustodyDetails(custodyDetails);
		}
				
		return detail;
	}
		
}
