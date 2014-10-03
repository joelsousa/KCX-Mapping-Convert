/*
 * Function    : Container (KIDS) == UIDS: no Class (directly Tag(s) ContainerRegistration
 * Titel       :
 * Date        : 09.09.2008
 * Author      : Kewill CSF / krzoska
 * Description : Contains Container Data 
 * 			   : with all Fields used in UIDS and  KIDS 
 * Parameters  : 

 * Changes 
 * -----------
  * Author      : EI
 * Date        : 15.05.2009
 * Description : Kids/Uids checked - no changes
 *             : eigentlich ist hier "ContainerRegistration" überflüssig, da von UIDS.Seite nicht
 *			   : im (über)Tag Container vorkommt, sondern direkt als (mehrere) ContainerRegistration
 *
 */

package com.kewill.kcx.component.mapping.countries.common;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: Container<br>
 * Erstellt		: 09.09.2008<br>
 * Beschreibung	: Contains Container Data with all Fields used in UIDS and  KIDS. 
 * 
 * @author krzoska
 * @version 1.0.00
 */
public class Container extends KCXMessage {
	
	//EI20090415: private String number; 
	private List <String> numberList;
	private TransportMeans transportMeans;   //EI20111005 z.Z. nowhere used
	
	private boolean         debug   = false;
	private XMLEventReader 	parser	= null;

	private enum EContainerTags {
		// Kids-TagNames, 		UIDS		            UIDS-NCTS
		 Number,				ContainerRegistration, 	ContainerNumber,	
		 MaensOfTransport;
	}
    
    public Container() {
		super();		
	} 
    
    public Container(XMLEventReader parser) {
		super(parser);
		this.parser = parser;		
	}
    
    public Container(XmlMsgScanner scanner) {
  		super(scanner);  		
  	}

    public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {		
			switch ((EContainerTags) tag) {
				case MaensOfTransport:
					transportMeans = new TransportMeans(getScanner());  	
					transportMeans.parse(tag.name());
					break; 
			default:
					return;
			}
		} else {			
			switch ((EContainerTags) tag) {
				case Number :
				case ContainerRegistration:
				case ContainerNumber:
					addNumberList(value);
					break;
			}
		}
	}
	
	public void stoppElement(Enum tag) {
	}
	
	public Enum translate(String token) {
		try {
			return EContainerTags.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}	
	
	public List <String>getNumberList() {
		return numberList;
	}

	public void setNumberList(List <String>argument) {
		this.numberList = argument;
	}
	public void addNumberList(String argument) {
		if (numberList == null) {
			numberList = new Vector<String>();
		}
		this.numberList.add(argument);
	}
		
	public TransportMeans getTransportMeans() {            		//EI20111005
		return transportMeans;
	}
	public void setTransportMeans(TransportMeans argument) {  	//EI20111005
		transportMeans = argument;
	}
	
	public boolean isEmpty() {
		return ((numberList == null || (numberList != null && numberList.isEmpty())) && 
                (transportMeans == null || (transportMeans != null && transportMeans.isEmpty())));
	}		
}
