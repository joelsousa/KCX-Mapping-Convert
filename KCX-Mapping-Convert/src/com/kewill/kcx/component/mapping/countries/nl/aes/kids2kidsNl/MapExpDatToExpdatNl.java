package com.kewill.kcx.component.mapping.countries.nl.aes.kids2kidsNl;

import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpDat;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.formats.kids.aes.BodyExportDeclarationKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * 
 * Modul        : MapExpDatToExpdatNl<br>
 * Erstellt     : Juli 2010<br>
 * Beschreibung : Mapping of KIDS-Format into KIDS-Format with NL Specifications.  

 * @author messer
 * @version 1.0.00
 *
 * Changes 
 * ------------
 * Author      : Christine Kron
 * Date        : Juli 2010
 * Label       :
 * Description : 	keep CustomsOfficeExport unchanged
 * 					remove blank in PlaceOfLoading in MeansOfTransport
 * 					insert a DeclarationNumber unique per kcx-id max. 8 digits used in NL 
 * 					additionally to ReferenceNumber
 * Author      : CK
 * Date        : 22.11.2012
 * Label       : CK20121122
 * Description : DeclarationNumber set if not yet in the message 
 */
public class MapExpDatToExpdatNl extends KidsMessage {
	
	private MsgExpDat 				  msgExpDat	= null;
	private BodyExportDeclarationKids body 		= null;
	
	public MapExpDatToExpdatNl(XMLEventReader parser, String encoding) throws XMLStreamException {
			msgExpDat = new MsgExpDat(parser);
			this.encoding = encoding;
	}
	
	private void mapToNl() {
		//CustomsOffice nur 3 Stellig (letzten 3 Stellen)
		
		// Christine Kron 22.7.2010
		// after replacing customs application NL I-Customs_online with 
		// MyUnidoc from Minihouse:
		// sending OfficeExport complete with all 8 characters
//		String officeExport = msgExpDat.getCustomsOfficeExport();
//		if (!Utils.isStringEmpty(officeExport) && officeExport.length() > 3) {
//			officeExport = officeExport.substring(officeExport.length() - 3);
//		}
//		msgExpDat.setCustomsOfficeExport(officeExport);

		//Bei allen Adressen HouseNumber auf 0 setzen falls Street besetzt und housenumber leer
		for (Address adr : getAdressList()) {
			if (adr != null && (adr.getStreet() != null &&
					!adr.getStreet().equals("") && 
					(adr.getHouseNumber() == null ||
					adr.getHouseNumber().equals("")))) {
				adr.setHouseNumber("0");
			}
		}
		// Christine Kron 22.07.2010
		// MeansOfTransport/PlaceOfLoading: remove space if present to have a valid code for NL
		
		TransportMeans transport = msgExpDat.getMeansOfTransport();
		if (transport != null) {   //EI20120124: because of NullPointerException
			String place = transport.getPlaceOfLoading();
			transport.setPlaceOfLoading(Utils.getTrimmed(place));
			msgExpDat.setMeansOfTransport(transport);
		}
		// Christine Kron 22.07.2010
		// PlaceOfLoading: build up Code from PostalCode plus houseNumber for NL
		// PlaceOfLoading placeOfLoading = msgExpDat.getPlaceOfLoading();
		// String postalcode = placeOfLoading.getPostalCode();
		// String street = placeOfLoading.getStreet();
		// 
		// clarify if needed
		// msgExpDat.setPlaceOfLoading(placeOfLoadingCode.toString());
		
		
		// Christine Kron 22.07.2010
		// build up the "DeclarationNumber" 8 digits for application NL
		
		// CK20121122
		// dont change DeclarationNumber if customer sent it
		if (msgExpDat.getDeclarationNumber() == null || msgExpDat.getDeclarationNumber().isEmpty()) {
			String refnr = msgExpDat.getReferenceNumber();
			int declnum = Utils.getDeclNum(kidsHeader.getReceiver(), refnr);

			Utils.log("(KidsToKidsNl readKids) DeclarationNumber = " + declnum);
			msgExpDat.setDeclarationNumber(declnum+"");
		}
		
		
	}
	private List<Address> getAdressList() {
		List<Address> addressList = new ArrayList<Address>();
		//Mit Reflection Realisiert, falls adressen hinzukommen
		//muss nur darauf geachtet werden das die entsprechenden getter 
		//methoden zur verfügung stehen
		Method[] methods = msgExpDat.getClass().getDeclaredMethods();
		for (Method method : methods) {
			//Alle Methoden die Addresse oder Party als Return Wert haben
			if (method.getReturnType().isAssignableFrom(Address.class)) {
				try {
					//Address Object holen
					Address adr = (Address) method.invoke(msgExpDat);
					if (adr != null) {
						//Addresse zu liste hinzufügen, falls nicht null
						addressList.add(adr);
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			} else if (method.getReturnType().isAssignableFrom(Party.class)) {
				Party party = null;
				try {
					//erst Party Object holen
					party = (Party) method.invoke(msgExpDat);
					if (party != null) {
						//Wenn Party Object nicht null ist,
						Address adr = party.getAddress();
						//Adresse hinzufügen falls diese ebenfalls nicht null ist
						if (adr != null) {
							addressList.add(adr);
						}
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				
				
			}
		}
		return addressList;
	}
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            writer = factory.createXMLStreamWriter(xmlOutputString);
            body    = new BodyExportDeclarationKids(writer);
            kidsHeader.setWriter((writer));
            writeStartDocument(encoding, "1.0");
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
                   
            kidsHeader.writeHeader();
            msgExpDat.parse(HeaderType.KIDS);
            body.setMessage(msgExpDat); 
            body.setKidsHeader(kidsHeader);
            
            mapToNl();  
            
            body.writeBody();
            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
            Utils.log("(MapExpDatToICustoms getMessage) Msg = " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
        
        return xmlOutputString.toString();
    }
}
