package com.kewill.kcx.component.mapping.formats.fr.ncts;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.MsgOperateur;
import com.kewill.kcx.component.mapping.countries.fr.ncts.msg.common.Evenement;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageNCTS;

/**
* Module  : FR-NCTS<br>
* Created : 13.11.2013<br>
* Description : Body of FR-NCTS-IE07 = NCTSArrivalNotification.
* 
* @author iwaniuk
* @version 1.0.00
*/

public class BodyIE07 extends KidsMessageNCTS {
	private MsgOperateur message;
	
	public BodyIE07(XMLStreamWriter writer) {
        this.writer = writer;
    }
	
	public MsgOperateur getMessage() {
		return message;
	}
	
	public void setMessage(MsgOperateur argument) {
		this.message = argument;
	}
	public void writeBody() {
		try {
		
			openElement("MessageOperateur");
			openElement("Messages");
			openElement("Message");
			    if (message.getEnveloppe() != null) {
				openElement("EnveloppeMessage");
					writeElement("schemaID", message.getEnveloppe().getSchemaId());  
					writeElement("partyId", message.getEnveloppe().getPartyId());
				closeElement();
			    }
				openElement("Notification");						
					writeEntete();				
					writeGen();
					writeEvenements();					
				closeElement();
				
			closeElement();
			closeElement();
			closeElement();
			
		} catch (XMLStreamException e) {
	            e.printStackTrace();
	    }     
	}
	
	public void writeEntete() throws XMLStreamException {    
		if (message.getNotification() != null && message.getNotification().getEntete() != null) {
    	openElement("Entete");             	                    	   
    		writeElement("refdec",  message.getNotification().getEntete().getRefdec());  	
	    closeElement();
		}
    }

	public void writeGen() throws XMLStreamException {	
		if (message.getNotification() != null && message.getNotification().getGen() != null) {
			
		openElement("Gen");
			writeElement("lieusousdouane", message.getNotification().getGen().getLieusousdouane());
			writeElement("lieunotif", message.getNotification().getGen().getLieunotif());
			if (message.getNotification().getGen().getLieuAutorise() != null) {
				writeElement("Code", message.getNotification().getGen().getLieuAutorise().getCode());
				writeElement("Lieu", message.getNotification().getGen().getLieuAutorise().getLieu());
			}
			writeElement("lieuagree", message.getNotification().getGen().getLieuagree());
			writeElement("procsimpl", message.getNotification().getGen().getProcsimpl());
			if (this.kidsHeader != null) {
				writeElement("date", this.kidsHeader.getSentAtDate());
			}
			writeElement("burpres", message.getNotification().getGen().getBurpres());			
			if (message.getNotification().getGen().getDestinataire() != null) {
				openElement("Destinataire");   
					writeElement("tin", message.getNotification().getGen().getDestinataire().getTin());	
					writeElement("nomoperateur", message.getNotification().getGen().getDestinataire().getNomoperateur());	
					writeElement("rueoperateur", message.getNotification().getGen().getDestinataire().getRueoperateur());	
					writeElement("villeoperateur", message.getNotification().getGen().getDestinataire().getVilleoperateur());	
					writeElement("codepostaloperateur", message.getNotification().getGen().getDestinataire().getCodepostaloperateur());				
					writeElement("paysoperateur", message.getNotification().getGen().getDestinataire().getPaysoperateur());
				closeElement();
			}			
		closeElement();
		}
	}

	public void writeEvenements() throws XMLStreamException {
		if (message.getNotification() != null && message.getNotification().getEvenements() != null) {		
    	openElement("Evenements");   
    	for (Evenement evenement : message.getNotification().getEvenements().getEvenementList()) {
    		writeEvenement(evenement);
    	}    	
	    closeElement();
		}
    }
	
	public void writeEvenement(Evenement argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}    
    	if (argument.isEmpty())  {  		
    	    return;
    	}    	
    	openElement("Evenement");             	                    	   
    		writeElement("lieu", argument.getLieu());
    		writeElement("pays", argument.getPays());
    		writeElement("nsti", argument.getNsti());
    		if (argument.getIncident() != null) {
    			openElement("Incident");    
    				writeElement("incident", argument.getIncident().getIncident());
    				writeElement("info", argument.getIncident().getInfo());
    				writeElement("date", argument.getIncident().getDate());
    				writeElement("autorite", argument.getIncident().getAutorite());
    				writeElement("lieu", argument.getIncident().getLieu());
    	    		writeElement("pays", argument.getIncident().getPays());
    			closeElement();
    		}
    		if (argument.getScelles() != null) {
    			openElement("Scelles");    
    				writeElement("nb", argument.getScelles().getNb());
    				if (argument.getScelles().getScellesidentites() != null && 
    					argument.getScelles().getScellesidentites().getScellesidentiteList() != null) {
    					openElement("Scellesidentites");
    					for (String sc : argument.getScelles().getScellesidentites().getScellesidentiteList()) {
    						writeElement("scellesidentite", sc);
    					}
    					closeElement();
    				}
    			closeElement();
    		}
    		if (argument.getTransbordement() != null) {
    			openElement("Transbordement");
    				writeElement("identite", argument.getTransbordement().getIdentite());
    				writeElement("nationalite", argument.getTransbordement().getNationalite());
    				writeElement("date", argument.getTransbordement().getDate());
    				writeElement("autorite", argument.getTransbordement().getAutorite());
    				writeElement("lieu", argument.getTransbordement().getLieu());
    				writeElement("pays", argument.getTransbordement().getPays());
        		closeElement();
    		}
    		if (argument.getConteneurs() != null && argument.getConteneurs().getConteneurList() != null) {
    			openElement("Conteneurs");
    			for (String container : argument.getConteneurs().getConteneurList()) {
    				writeElement("conteneur", container);
    			}
    			closeElement();
    		}
	    closeElement();
    }
	
}
