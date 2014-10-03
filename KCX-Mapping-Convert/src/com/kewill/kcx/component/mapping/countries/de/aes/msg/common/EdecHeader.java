/*
 * Function    : EdecHeader(UIDS - CH) Kids: several (simple) Tags  
 * Titel       :
 * Date        : 20.10.2008
 * Author      : Kewill CSF / krzoska
 * Description : Headar Data for Schweiz
 * 			   : 
 * Parameters  :

 * Changes
 * -----------
 * Author      :ME
 * Date        :13.03.2009
 * Label       :
 * Description : NCTSIdentifier hinzugefügt.
 *
 * Author      : EI
 * Date        : 15.04.2009
 * Label       : EI20090415
 * Description : PrevRegNrList added
 *
 * Author      : EI
 * Date        : 18.05.2009
 * Label       :
 * Description : Kids/Uids checked - no changes
 */

package com.kewill.kcx.component.mapping.countries.de.aes.msg.common;

import java.util.List;
import java.util.Vector;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: EdecHeader<br>
 * Erstellt		: 20.10.2008<br>
 * Beschreibung	: Header Data for Schweiz. 
 * 
 * @author krzoska
 * @version 1.0.00
 */
public class EdecHeader extends KCXMessage {

	private String typeOfDeclaration;	
	private String statuscode;  	
	private String correctionCode;    			
    private String collectionNumber;
    private String nctsIdentifier;
    private List<String> prevRegNrList;

	private enum EEdecHeader {
		//UIDS					 
		TypeOfDeclaration,
		Statuscode,     
		CorrectionCode,
		CollectionNumber,
		NCTSIdentifier,
		PrevRegNr;      //EI20090415
   }

	private boolean debug   = false;

	public EdecHeader() {
		super();  
	}
	
	public EdecHeader(XmlMsgScanner scanner) {
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
  			switch ((EEdecHeader) tag) {
  			default:
  					return;  			
  			}
  		} else {
  			switch ((EEdecHeader) tag) {
  				case TypeOfDeclaration:
  					setTypeOfDeclaration(value);
  					break;
  				case Statuscode:
  					setStatuscode(value);
  					break;  				
  				case CorrectionCode:
  					setCorrectionCode(value);
  					break;  	  				
  				case CollectionNumber:
  					setCollectionNumber(value);					
  					break; 
  				case NCTSIdentifier:
  					setNctsIdentifier(value);
  					break;
  					
  				case PrevRegNr:  					
  					addPrevRegNrList(value);
  					break;  					
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}


  	public Enum translate(String token) {
  		try {
  			return EEdecHeader.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	public String getTypeOfDeclaration() {
		return typeOfDeclaration;
	
	}

	public void setTypeOfDeclaration(String typeOfDeclaration) {
		this.typeOfDeclaration = typeOfDeclaration;
	}

	public String getStatuscode() {
		return statuscode;
	
	}

	public void setStatuscode(String statuscode) {
		this.statuscode = statuscode;
	}

	public String getCorrectionCode() {
		return correctionCode;
	
	}

	public void setCorrectionCode(String correctionCode) {
		this.correctionCode = correctionCode;
	}

	public String getCollectionNumber() {
		return collectionNumber;
	
	}

	public void setCollectionNumber(String collectionNumber) {
		this.collectionNumber = collectionNumber;
	}

	public String getNctsIdentifier() {
		return nctsIdentifier;
	}

	public void setNctsIdentifier(String nctsIdentifier) {
		this.nctsIdentifier = nctsIdentifier;
	}
	
	public List<String> getPrevRegNrList() {
		return prevRegNrList;
	}

	public void setPrevRegNrList(List<String> argument) {
		this.prevRegNrList = argument;
	}	
	
	public void addPrevRegNrList(String argument) {
		if (prevRegNrList == null) {
			prevRegNrList = new Vector <String>();
		}
		prevRegNrList.add(argument);
	}
}
