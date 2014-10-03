package com.kewill.kcx.component.mapping.countries.de.ncts.msg.common;

import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLEventReader;
import org.xml.sax.Attributes;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: TransportationRoute
 * Created		: 02.09.2010
 * Description	: contains TransportationRoute data with fields used for KIDS/UIDS.
 * 
 * @author Lassiter
 * @version 4.0.00
 */
public class TransportationRoute extends KCXMessage {
	
	private List<String>			countryList;
	private String					suspensionIndicator;
	
	
	private XMLEventReader parser	= null;
	
	public TransportationRoute(XMLEventReader parser) {
		super(parser);
		this.setParser(parser);
	}
	public TransportationRoute(XmlMsgScanner scanner) {
		super(scanner);
	}
	public TransportationRoute() {  //EI20110524
		super();		
	}
	
	private enum ETransportationRoute {
		//kids						uids
		Country,             
		SuspensionIndicator
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
		
		if (value == null) {
   			switch ((ETransportationRoute) tag) {
   			
   			default:
   					return;
   			}
   		} else {
   			switch ((ETransportationRoute) tag) {
   			case Country:
   				if (countryList == null) {
   					countryList = new ArrayList<String>();
				}
   				countryList.add(value);
				break;	
				
   			case SuspensionIndicator:
   				setSuspensionIndicator(value);
   				break;
   			
   			default:
					return;
   			}
   		}
		
	}
	
	public void stoppElement(Enum tag) {
		
	}

	public Enum translate(String token) {
		try {
			return ETransportationRoute.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public List<String> getCountryList() {
		return countryList;
	}

	// Christine Kron 03.09.2010
	// used in MapNCTSDeclarationToVAN
	public String getFirstCountry() {
		if (countryList == null) {
			return "";
		}
		if (countryList.size() == 0) {
			return "";
		}
		return countryList.get(0);
	}
	
	public void setCountryList(List<String> countryList) {
		this.countryList = countryList;
	}

	public String getSuspensionIndicator() {
		return suspensionIndicator;
	}

	public void setSuspensionIndicator(String suspensionIndicator) {
		this.suspensionIndicator = suspensionIndicator;
	}
	public XMLEventReader getParser() {
		return parser;
	}
	public void setParser(XMLEventReader parser) {
		this.parser = parser;
	}

}
