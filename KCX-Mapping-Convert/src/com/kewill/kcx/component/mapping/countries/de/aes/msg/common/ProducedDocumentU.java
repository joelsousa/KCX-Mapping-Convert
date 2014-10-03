/*
 * Function    : ProducedDocumentU.java
 * Titel       :
 * Date        : 16.09.2008
 * Author      : Kewill CSF / Houdek
 * Description : Contains the ProducedDocument Data
 * 			   : with all Fields used in UIDS 
 * Parameters  :

 * Changes
 * -----------
 * Author      :
 * Date        :
 * Label       :
 * Description:
 *
 */

package com.kewill.kcx.component.mapping.countries.de.aes.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: ProducedDocumentU<br>
 * Erstellt		: 16.09.2008<br>
 * Beschreibung	: Contains the ProducedDocument Data with all Fields used in UIDS. 
 * Parameters  :
 * 
 * @author Houdek
 * @version 1.0.00
 */
public class ProducedDocumentU extends KCXMessage {

    private DocumentU    documentU;
    private ArchiveInfoU archiveInfo;

	private enum EProducedDocumentU {
		Dokument,
		ArchiveInfo;
     }
	
     private boolean debug   = false;
     private XMLEventReader 		parser	= null;

     public ProducedDocumentU() {
	      	super();
     }

     public ProducedDocumentU(XMLEventReader parser) {
  		super(parser);
  		this.parser = parser;
  	}
  	 
  	 public ProducedDocumentU(XmlMsgScanner scanner) {
  	  		super(scanner);
  	 }
  	 
  	public boolean isDebug() {
  		return debug;
  	}

  	public void setDebug(boolean debug) {
  		this.debug = debug;
  	}

  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EProducedDocumentU) tag) {

			case Dokument:
			documentU = new DocumentU(parser);
			break;

			case ArchiveInfo:
			archiveInfo = new ArchiveInfoU(parser);
			break;

  			default:
  					return;
  			}
  		} else {

  			switch ((EProducedDocumentU) tag) {

  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}


  	public Enum translate(String token) {
  		try {
  			return EProducedDocumentU.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	public DocumentU getDocumentU() {
		return documentU;
	}

	public void setDocumentU(DocumentU documentU) {
		this.documentU = documentU;
	}

	public ArchiveInfoU getArchiveInfo() {
		return archiveInfo;
	}

	public void setArchiveInfo(ArchiveInfoU archiveInfo) {
		this.archiveInfo = archiveInfo;
	}

	public XMLEventReader getParser() {
		return parser;
	}

	public void setParser(XMLEventReader parser) {
		this.parser = parser;
	}

// -----

}
