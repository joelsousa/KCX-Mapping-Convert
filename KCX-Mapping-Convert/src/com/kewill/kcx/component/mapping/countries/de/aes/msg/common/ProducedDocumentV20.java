package com.kewill.kcx.component.mapping.countries.de.aes.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: ProducedDocument<br>
 * Created		: 16.07.2012<br>
 * Description	: Contains the ProducedDocument Data with all Fields used in UIDS. 
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class ProducedDocumentV20 extends KCXMessage {

    private DocumentV20    document;   
    private ArchiveInfoU archiveInfo;

	private enum EProducedDocument {
		Document,
		ArchiveInfo;
     }
	 
     public ProducedDocumentV20() {
	      	super();
     }

  	 public ProducedDocumentV20(XmlMsgScanner scanner) {
  	  		super(scanner);
  	 }
  	   	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EProducedDocument) tag) {

			case Document:				
				document = new DocumentV20(getScanner(), "U");
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
  			default:
  				break;
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

	public DocumentV20 getDocument() {
		return document;
	}
	public void setDocument(DocumentV20 document) {
		this.document = document;
	}

	public ArchiveInfoU getArchiveInfo() {
		return archiveInfo;
	}
	public void setArchiveInfo(ArchiveInfoU archiveInfo) {
		this.archiveInfo = archiveInfo;
	}
}
