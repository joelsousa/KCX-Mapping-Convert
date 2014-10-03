/*
 * Funktion    : CommonFieldsDTO.java
 * Titel       : Data Transfer Object for commonly used fields 
 * Erstellt    : 22.04.2009
 * Author      : CSF GmbH / schmidt
 * Beschreibung: Collection of fields used in several messages
 * Anmerkungen : 
 * Parameter   : 
 * Rückgabe    : keine
 * Aufruf      : 
 *
 * Änderungen:
 * -----------
 * Author      :
 * Datum       :
 * Kennzeichen :
 * Beschreibung:
 * Anmerkungen :
 * Parameter   :
 *
 */
package com.kewill.kcx.component.mapping.common;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.db.CustomerDataDTO;
import com.kewill.kcx.component.mapping.db.CustomerProcedureDTO;

/**
 * Modul		: CommonFieldsDTO<br>
 * Erstellt		: 22.04.2009<br>
 * Beschreibung	: Collection of fields used in several messages. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class CommonFieldsDTO {
    private String kcxId       = "";        // KCX customer ID
    private String uidsId      = "";        // ETN-Address used in UIDS messages
    private String fssId       = "";        // FSS customer ID
    private String localId     = "";        // Customer ID in local country format 
    private String countryCode = "";        // Destination or origin Country
    
//    private String uidsVersion = "";      // UIDS version identfier in namespace (year and month; YYYYMM)
//    private String fssVersion  = "";      // FSS version of customer
    private String kidsRelease  = "";       // EI20130425: KFF Kids.SubRelease n.n.01 wg. weiterhin
    									    //			   virtuellen Kommas in den neusten Kids				   
    private String referenceNumber = "";    // ReferenceNumber of the declaration
    
    private String filename = "";           // Name der Eingabedatei
    
    private CustomerDataDTO customerDataDTO = null; // Kundendaten aus der Tabelle CUSTOMER_DATA.
    private CustomerProcedureDTO customerProcedureDTO = null; // Kundendaaten aus der Tabelle CUSTOMER_PROCEDURES.
    
    private boolean     pdfExists                 = false;  // PDF in XML-datei vorhanden
    
    private EDirections direction                 = null;   // Direction of the message (to customs / to customer)
    private String      procedure                 = null;   // Zollverfahren (Export, EMCS, ...)
    
    private String      messageReferenceNumber    = null;   // MessageID of the (original) message (from customer)
    private boolean     functionalAcknowledgement = false;  // Can this message act as a funtional acknowledgement?
    
    private String targetMessageType              = null;   // Message type in target message format (für BOB-CY) 
    private String eoriNumber                     = null;   // EORI number of message sender         (für BOB-CY)
    private String actionType                     = null;   // Inhalt des <Action> Tags in BellDavies 
                                                            // WebService Nachrichten     (für BOB-GB-BellDavies)
//    private int    hashCode                       = 0;      // Hash code über die komplette Nachricht. 
                                                            // (z. Zt. nur bei GR Antwortnachrichten MS20111020)
    private boolean alreadyReceived               = false;  // Wurde die Antwort schon einmal empfangen? 
                                                            // (z. Zt. nur bei GR Antwortnachrichten MS20111104)
    private String bob = null;   //EI20120802: hier kommt info von welchem BOB die Nachricht kam
    private String nameSpaceText = null;   //EI20120914
    private boolean isBDP = false;         //EI20130508 
    
    public String getKcxId() {
        return kcxId;
    }
    public void setKcxId(String kcxId) {
        this.kcxId = kcxId;
    }
    public String getUidsId() {
        return uidsId;
    }
    public void setUidsId(String uidsId) {
        this.uidsId = uidsId;
    }
    public String getFssId() {
        return fssId;
    }
    public void setFssId(String fssId) {
        this.fssId = fssId;
    }
    /**
     * ReferenceNumber of the declaration (ie Customs reference number)
     * @return
     */
    public String getReferenceNumber() {
        return referenceNumber;
    }
    /**
     * ReferenceNumber of the declaration (ie Customs reference number)
     * @param referenceNumber
     */
    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }
    public String getUidsVersion() {
      //EI20100519
    	if (this.customerProcedureDTO == null) {
            return "";
    	} else {
    		return customerProcedureDTO.getUidsVersion();
    	}
    }
    public String getFssVersion() {
      //EI20100519
       	if (this.customerProcedureDTO == null) { 
    		return "";  
       	} else {
       		return customerProcedureDTO.getFssVersion();
       	}
    }
//    public void setFssVersion(String fssVersion) {
//        this.fssVersion = fssVersion;
//    }
    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }
    public String getPdfOutFormat() {
        return customerDataDTO.getPdfOutFormat();
    }
    public boolean isPdfTgz() {
        return customerDataDTO.getPdfOutFormat().equalsIgnoreCase("tgz");
    }
    public CustomerDataDTO getCustomerDataDTO() {
        return customerDataDTO;
    }
    public void setCustomerDataDTO(CustomerDataDTO customerDataDTO) {
        this.customerDataDTO = customerDataDTO;
    }
    public CustomerProcedureDTO getCustomerProcedureDTO() {
        return customerProcedureDTO;
    }
    public void setCustomerProcedureDTO(CustomerProcedureDTO customerProcedureDTO) {
        this.customerProcedureDTO = customerProcedureDTO;
    }
    public void updateFilename() {
    	// Christine Kron 
    	// Beim TGZ-Versenden Endung bei filename von ".xml" in ".tgz" ändern
    	// dies wird in KidsToUids in die Properties der MuleMessage eingetragen
		String name = "";
		if (filename.lastIndexOf(".xml") > 0) {
			name = filename.substring(0, filename.lastIndexOf(".xml"));	
        } else {
        	name = filename;
        }
		filename = name + ".tgz";
    }
	public boolean pdfExists() {
		return pdfExists;
	}
	public void setPdfExists(boolean pdfExists) {
		this.pdfExists = pdfExists;
	}
    public String getCountryCode() {
        return countryCode;
    }
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    public EDirections getDirection() {
        return direction;
    }
    public void setDirection(EDirections direction) {
        this.direction = direction;
    }
    public String getProcedure() {
        return procedure;
    }
    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }
    /**
     * MessageID of the (original) message (from customer)
     * @return
     */
    public String getMessageReferenceNumber() {
        return messageReferenceNumber;
    }
    /**
     * MessageID of the (original) message (from customer)
     * @param messageReferenceNumber
     */
    public void setMessageReferenceNumber(String messageReferenceNumber) {
        this.messageReferenceNumber = messageReferenceNumber;
    }
    public boolean isFunctionalAcknowledgement() {
        return functionalAcknowledgement;
    }
    public void setFunctionalAcknowledgement(boolean functionalAcknowledgement) {
        this.functionalAcknowledgement = functionalAcknowledgement;
    }
    public String getLocalId() {
        return localId;
    }
    public void setLocalId(String localId) {
        this.localId = localId;
    }
    public String getTargetMessageType() {
        return targetMessageType;
    }
    public void setTargetMessageType(String targetMessageType) {
        this.targetMessageType = targetMessageType;
    }
    public String getEoriNumber() {
        return eoriNumber;
    }
    public void setEoriNumber(String eoriNumber) {
        this.eoriNumber = eoriNumber;
    }
    public String getActionType() {
        return actionType;
    }
    public void setActionType(String actionType) {
        this.actionType = actionType;
    }
//    public int getHashCode() {
//        return hashCode;
//    }
//    public void setHashCode(int hashCode) {
//        this.hashCode = hashCode;
//    }
    public boolean isAlreadyReceived() {
        return alreadyReceived;
    }
    public void setAlreadyReceived(boolean alreadyReceived) {
        this.alreadyReceived = alreadyReceived;
    }
    
    public String getBOB() {
        return bob;
    }
    public void setBOB(String value) {
        this.bob = value;
    }

    public String getNameSpaceText() {
        return nameSpaceText;
    }
    public void setNameSpaceText(String value) {
        this.nameSpaceText = value;
    }
    
    public String getKidsRelease() {
    	if (kidsRelease != null) {
    		kidsRelease = kidsRelease.trim();
    	}
        return kidsRelease;
    }
    public void setKidsRelease(String value) {
        this.kidsRelease = value;
    }
    
}
