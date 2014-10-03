/*
 * Function    : KIDS: two Tags in Seal == HeaderExtensions (UIDS) 
 * Titel       :
 * Date        : 15.09.2008
 * Author      : Kewill CSF / Houdek
 * Description : Contains the HeaderExtensions
 * 			   : with all Fields used in UIDS 
 * Parameters  :

 * Changes
 * -----------
 * Author      : EI
 * Date        : 15.05.2009
 * Label       : 
 * Description : Kids/Uids checked - no changes
 */

package com.kewill.kcx.component.mapping.countries.de.aes.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: HeaderExtensions<br>
 * Erstellt		: 15.09.2008<br>
 * Beschreibung	: Contains the HeaderExtensions with all Fields used in UIDS. 
 * 
 * @author Houdek
 * @version 1.0.00
 */
public class HeaderExtensions extends KCXMessage {

    private String tydenSealsFlag;
    private String tydenSealsStockFlag;
    private String edecRevisionFlag;
    private String edecReleaseFlag;
    
    private boolean debug   = false;
    private XMLEventReader 		parser	= null;

	private enum EHeaderExtensionsU {
		TydenSealsFlag,
		TydenSealsStockFlag,
		EDECRevisionFlag,
		EDECReleaseFlag;
     }

      public HeaderExtensions() {
	      	super();
      }

      public HeaderExtensions(XMLEventReader parser) {
  		super(parser);
  		this.parser = parser;
  	}
  	 
  	 public HeaderExtensions(XmlMsgScanner scanner) {
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
  			switch ((EHeaderExtensionsU) tag) {
  			default:
  					return;
  			}
  		} else {

  			switch ((EHeaderExtensionsU) tag) {

  				case TydenSealsFlag:
  					setTydenSealsFlag(value);
  					break;

  				case TydenSealsStockFlag:
  					setTydenSealsStockFlag(value);
  					break;
  					
  				case EDECRevisionFlag:
  					setEDECRevisionFlag(value);
  					break;
  					
  				case EDECReleaseFlag:
  					setEDECReleaseFlag(value);
  					break;
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}


  	public Enum translate(String token) {
  		try {
  			return EHeaderExtensionsU.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	public String getTydenSealsFlag() {
		return tydenSealsFlag;
	}

	public void setTydenSealsFlag(String tydenSealsFlag) {
		this.tydenSealsFlag = tydenSealsFlag;
	}

	public String getTydenSealsStockFlag() {
		return tydenSealsStockFlag;
	}

	public void setTydenSealsStockFlag(String tydenSealsStockFlag) {
		this.tydenSealsStockFlag = tydenSealsStockFlag;
	}

	public XMLEventReader getParser() {
		return parser;
	}

	public void setParser(XMLEventReader parser) {
		this.parser = parser;
	}

	public void setEDECRevisionFlag(String argument) {
		this.edecRevisionFlag = argument;
	}
	public String getEDECRevisionFlag() {
		return edecRevisionFlag;
	}
	
	public void setEDECReleaseFlag(String argument) {
		this.edecReleaseFlag = argument;
	}
	public String getEDECReleaseFlag() {
		return edecReleaseFlag;
	}
// -----

}
