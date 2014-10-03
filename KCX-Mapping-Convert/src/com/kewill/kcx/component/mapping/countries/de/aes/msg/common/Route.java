/*
 * Function    : TransportationRoute (KIDS) == Itinerary (UIDS)
 * Titel       :
 * Date        : 10.09.2008
 * Author      : Kewill CSF / Houdek
 * Description : Contains the Route Data
 * 			   : with all Fields used in  KIDS
 * Parameters  :

 * Changes
 * -----------
 * Author      : EI
 * Date        :
 * Label       : 18.05.2009
 * Description : Kids/Uids  checked - no changes
 * 
 * Author      : AK
 * Datum       : 12.03.2009
 * Kennzeichen : AK20090312
 * Beschreibung: CountryOfRouting (UIDS) added
 * Anmerkungen :
 * Parameter   :
*/

package com.kewill.kcx.component.mapping.countries.de.aes.msg.common;

import org.xml.sax.Attributes;
import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: Route<br>
 * Erstellt		: 10.09.2008<br>
 * Beschreibung	: Contains the Route Data with all Fields used in  KIDS.
 * 
 * @author Houdek
 * @version 1.0.00
 */
public class Route extends KCXMessage {

    //private String country;
    private List <String> countryList;
    private boolean debug   = false;

    public Route() {
 	      	super(); 	      	
    }

    public Route(XmlMsgScanner scanner) {
    	super(scanner);
   	}

 	private enum ERoute {
 		Country,             CountryOfRouting;
    }
 	
   	public void startElement(Enum tag, String value, Attributes attr) {
   		if (value == null) {
   			switch ((ERoute) tag) {
   			default:
   					return;
   			}
   		} else {
   			switch ((ERoute) tag) {
   				case Country:
   				case CountryOfRouting:   					
   					addCountryList(value);
   					break;
   			}
   		}
   	}

   	public void stoppElement(Enum tag) {
   	}

   	public Enum translate(String token) {
   		try {
   			return ERoute.valueOf(token);
   		} catch (IllegalArgumentException e) {
   			return null;
   		}
   	}

   	public boolean isDebug() {
   		return debug;
   	}

   	public void setDebug(boolean debug) {
   		this.debug = debug;
   	}
   	   	
	public List<String> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<String> argument) {
		this.countryList = argument;
	}
	

	public void addCountryList(String argument) {
		if (countryList == null) {
			countryList = new Vector<String>();
		}
		this.countryList.add(argument);
	}
}
	
