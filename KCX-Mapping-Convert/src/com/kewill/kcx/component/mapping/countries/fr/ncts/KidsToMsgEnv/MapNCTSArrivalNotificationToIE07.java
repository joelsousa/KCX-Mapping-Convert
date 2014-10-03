package com.kewill.kcx.component.mapping.countries.fr.ncts.KidsToMsgEnv;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Packages;
import com.kewill.kcx.component.mapping.countries.de.ncts20.msg.MsgNCTSArrivalNotification;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.MsgOperateur;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.Cdt;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.Conteneurs;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.Destinataire;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.Entete;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.Enveloppe;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.Evenement;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.Evenements;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.Gen;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.Incident;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.LieuAutorise;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.Notification;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.Scelles;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.Scellesidentites;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.Transbordement;
import com.kewill.kcx.component.mapping.formats.fr.ncts.BodyIE07;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module  : FR-NCTS<br>
 * Created : 13.11.2013<br>
 * Description : Mapping of KIDS-ArrivalNotification to FR-NCTS-IE07.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MapNCTSArrivalNotificationToIE07 extends KidsMessage {	
		
	private MsgNCTSArrivalNotification message = null;
	private MsgOperateur msgOperateur = null;	
	private BodyIE07 body = null;	
		
	public MapNCTSArrivalNotificationToIE07(XMLEventReader parser, String encoding) throws XMLStreamException {
		message = new MsgNCTSArrivalNotification(parser);
		msgOperateur = new MsgOperateur();
		this.encoding = encoding;
	}
		
	public String getMessage(KidsHeader kidsHeader) {
		StringWriter xmlOutputString = new StringWriter();
	        
	    XMLOutputFactory factory = XMLOutputFactory.newInstance();
	    try {
	            writer = factory.createXMLStreamWriter(xmlOutputString);
	            body   = new BodyIE07(writer);
	           
	            writeStartDocument(encoding, "1.0");
	            
	            openElement("soap:Envelope");
	            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
	            
	            message.parse(HeaderType.KIDS); 
	            mapKidsToIE07(kidsHeader);           
	           
	            body.setMessage(msgOperateur);
	            body.writeBody();
	            
	            closeElement();  // soap:Envelope
	            writer.writeEndDocument();
	            
	            writer.flush();
	            writer.close();
	            
	    } catch (XMLStreamException e) {
	    	e.printStackTrace();
	    }
	    return xmlOutputString.toString();
	}
		
	private void mapKidsToIE07(KidsHeader kidsHeader) {
		Enveloppe enveloppe = new Enveloppe();
		Notification msg = new Notification();
			
						
		enveloppe.setSchemaId("IE07"); //TODO "IE07" oder "NCTSArrivalNotification" ???
			
		if (kidsHeader != null) {
			String sentAtDate = kidsHeader.getSentAtDate();				
			enveloppe.setPartyId(kidsHeader.getReceiver()); //TODO ??? kcx_id oder ReferenceNumber? oder TIN?					
		}
			
		msg.setEntete(this.mapEntete());
		msg.setGen(this.mapGen());
		msg.setEvenements(this.mapEvenements());		
			
		msgOperateur.setEnveloppe(enveloppe);
		msgOperateur.setNotification(msg);		
	}
		
	private Entete mapEntete() {				
		if (Utils.isStringEmpty(message.getUCRNumber())) {
			return null;
		}
		Entete entete = new Entete();
		entete.setRefdec(message.getUCRNumber());
			
		return entete;
	}
	private Gen mapGen() {			
		Gen gen = new Gen();
		gen.setLieusousdouane(message.getCustomsSubPlace());
		gen.setLieunotif(message.getPlaceOfUnloadingCode());
		gen.setLieuAutorise(this.mapLieuAutorise());
		gen.setLieuagree(message.getAgreedLocationOfGoods());
		gen.setProcsimpl(message.getSimplifiedProcedure());
		//gen.setDate(???);
		gen.setBurpres(message.getPresentationOffice());
		gen.setDestinataire(this.mapDestinataire());
			
		return gen;
	}
	private LieuAutorise mapLieuAutorise() {				
		if (Utils.isStringEmpty(message.getUCRNumber())) {
			return null;
		}
		LieuAutorise lieuAutorise = new LieuAutorise();
		lieuAutorise.setCode(message.getAgreedLocationCode());
			
		return lieuAutorise;
	}
	private Destinataire mapDestinataire() {							
		Destinataire destinataire = new Destinataire();
		if (message.getAuthorisedConsigneeTIN() != null) {
			destinataire.setTin(message.getAuthorisedConsigneeTIN().getTIN());
		}
		if (message.getDestinationTrader() != null && message.getDestinationTrader().getAddress() != null) {
			destinataire.setNomoperateur(message.getDestinationTrader().getAddress().getName());
			if (!Utils.isStringEmpty(message.getDestinationTrader().getAddress().getStreet())) {
				String street = message.getDestinationTrader().getAddress().getStreet();
				if (!Utils.isStringEmpty(message.getDestinationTrader().getAddress().getHouseNumber())) {
					street = street + message.getDestinationTrader().getAddress().getHouseNumber();
				}
				destinataire.setRueoperateur(street);
			}
			destinataire.setVilleoperateur(message.getDestinationTrader().getAddress().getCity());
			destinataire.setPaysoperateur(message.getDestinationTrader().getAddress().getCountry());
			destinataire.setCodepostaloperateur(message.getDestinationTrader().getAddress().getPostalCode());
		}
		return destinataire;
	}
	private Evenements mapEvenements() {
		if (message.getEnRouteEvent() == null) {
			return null;
		}
		Evenements evens = new Evenements();
		Evenement eve = new Evenement();			
		
		eve.setNsti(message.getEnRouteEvent().getAlreadyInNCTS());
		eve.setLieu(message.getEnRouteEvent().getPlace());
		eve.setPays(message.getEnRouteEvent().getCountryCode());
		
		if (message.getEnRouteEvent().getIncident() != null) {			
			Incident incident = new Incident();
			incident.setIncident(message.getEnRouteEvent().getIncident().getIncidentFlag());
			incident.setInfo(message.getEnRouteEvent().getIncident().getIncidentInfo());
			incident.setDate(message.getEnRouteEvent().getIncident().getEndorsementDate());
			incident.setAutorite(message.getEnRouteEvent().getIncident().getEndorsementAuthority());
			incident.setLieu(message.getEnRouteEvent().getIncident().getEndorsementPlace());
			incident.setPays(message.getEnRouteEvent().getIncident().getEndorsementCountry());
			eve.setIncident(incident);
		}
			
		if (message.getEnRouteEvent().getTransShipment() != null) {
			Transbordement trans = new Transbordement();
			trans.setIdentite(message.getEnRouteEvent().getTransShipment().getNewTransportId());
			trans.setNationalite(message.getEnRouteEvent().getTransShipment().getNewTransportCountry());
			trans.setDate(message.getEnRouteEvent().getTransShipment().getEndorsementDate());
			trans.setAutorite(message.getEnRouteEvent().getTransShipment().getEndorsementAuthority());
			trans.setLieu(message.getEnRouteEvent().getTransShipment().getEndorsementPlace());
			trans.setPays(message.getEnRouteEvent().getTransShipment().getEndorsementCountry());			
			eve.setTransbordement(trans);
			if (message.getEnRouteEvent().getTransShipment().getContainerNumberList() != null) {
				Conteneurs conteneurs = new Conteneurs();
				for (String cont : message.getEnRouteEvent().getTransShipment().getContainerNumberList()) {
					conteneurs.addConteneurList(cont);
				}				
				eve.setConteneurs(conteneurs);
			}
		}
			
		Scelles scelles = new Scelles();
		scelles.setNb(message.getEnRouteEvent().getTotalNumberOfSeals());		
		if (message.getEnRouteEvent().getSealsIdentityList() != null) {
			Scellesidentites scs = new Scellesidentites();
			for (String nr : message.getEnRouteEvent().getSealsIdentityList()) {
				scs.addScellesidentiteList(nr);
			}
		}
		eve.setScelles(scelles);
						
		evens.addEvenementList(eve);			
		return evens;
	}
	
}