/*
 * Function    : ArchiveInfo.java
 * Titel       :
 * Date        : 16.09.2008
 * Author      : Kewill CSF / Houdek
 * Description : Contains theArchiveInfo Data
 * 			   : with all Fields used in UIDS 
 * Parameters  :

 * Changes
 * -----------
 * Author      : EI
 * Date        : 15.05.2009
 * Label       : 
 * Description : used only in UIDS - renamed into ArchiveInfoU
 *			   : any Body current needs ArchiveInfo
 */

package com.kewill.kcx.component.mapping.countries.de.aes.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: ArchiveInfoU<br>
 * Erstellt		: 16.09.2008<br>
 * Beschreibung	: Contains theArchiveInfo Data with all Fields used in UIDS. 
 * 
 * @author Houdek
 * @version 1.0.00
 */
public class ArchiveInfoU extends KCXMessage {

    private String location;
    private String support;
    private String code;
    private String content;

    private boolean debug   = false;
    private XMLEventReader 		parser	= null;

	private enum EArchiveInfo {
		Location,
		Support,
		Code,
		Content;
     }

     public ArchiveInfoU() {
	      	super();
     }

     public ArchiveInfoU(XMLEventReader parser) {
  		super(parser);
  		this.parser = parser;
  	 }
 	 public ArchiveInfoU(XmlMsgScanner scanner) {
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
  			switch ((EArchiveInfo) tag) {
  			default:
  					return;
  			}
  		} else {

  			switch ((EArchiveInfo) tag) {

  				case Location:
  					setLocation(value);
  					break;

  				case Support:
  					setSupport(value);
  					break;

  				case Code:
  					setCode(value);
  					break;

  				case Content:
  					setContent(value);
  					break;
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}

  	public Enum translate(String token) {
  		try {
  			return EArchiveInfo.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getSupport() {
		return support;
	}

	public void setSupport(String support) {
		this.support = support;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public XMLEventReader getParser() {
		return parser;
	}

	public void setParser(XMLEventReader parser) {
		this.parser = parser;
	}

}
