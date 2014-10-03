/*
 * Function    : ProducedDocumentU.java
 * Titel       :
 * Date        : 16.03.2009
 * Author      : Kewill CSF / iwaniuk
 * Description : Contains the ProducedDocument Data 
 * 			   : with all Fields used in UIDS 
 * Parameters  :

 * Changes
 * -----------
 * Author      : krzoska
 * Date        : 20090512
 * Label       : AK20090512
 * Description : Document instead of Dokument
 *
 */

package com.kewill.kcx.component.mapping.countries.de.aes.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: ProducedDocument<br>
 * Erstellt		: 16.03.2009<br>
 * Beschreibung	: Contains the ProducedDocument Data with all Fields used in UIDS. 
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class ProducedDocument extends KCXMessage {

    private DocumentU    documentU;  //for V5.3
    private Document    document;    //for V6.0
    private ArchiveInfoU archiveInfo;

	private enum EProducedDocument {
		Document,
		ArchiveInfo;
     }
	
     private boolean debug   = false;
     private XMLEventReader 		parser	= null;

     public ProducedDocument() {
	      	super();
     }

     public ProducedDocument(XMLEventReader parser) {
  		super(parser);
  		this.parser = parser;
  	}
  	 
  	 public ProducedDocument(XmlMsgScanner scanner) {
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
  			switch ((EProducedDocument) tag) {

			case Document:				
			document = new Document(getScanner(), "U");
			document.parse(tag.name());
			break;

			case ArchiveInfo:
			archiveInfo = new ArchiveInfoU(getScanner());
			archiveInfo.parse(tag.name());
			break;

  			default:
  					return;
  			}
  		} else {

  			switch ((EProducedDocument) tag) {

  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}


  	public Enum translate(String token) {
  		try {
  			return EProducedDocument.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
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
