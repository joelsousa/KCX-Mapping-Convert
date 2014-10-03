package com.kewill.kcx.component.mapping.formats.kids.common;

import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.Party;

/**
 * Module : Port<br>
 * Created : 10.05.2013<br>
 * Description : common methods for Kids bodies.
 * 				EI20130510: der einzige unterschied zu V10 ist Party.Bo 
 * 							(es gibt nur 3 Aenderungen in kids2fss)  
 * 
 * @author Iwaniuk
 * @version 1.0.00
 */
public class KidsMessagePortGpo20 extends KidsMessagePortGpo {

	public void writeParty(String tag, Party argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		openElement(tag);
			writeElement("CustomerIdentifier", argument.getCustomerIdentifier());
			writeElement("CustomsIdentifier", argument.getCustomsIdentifier());
			writeElement("AdditionalIdentifier", argument.getCustomsIdentifier());
			writeElement("TerminalCustomerNumber", argument.getTerminalCustomerNumber());
			writeElement("PortCode", argument.getPortCode());
			writeElement("TIN", argument.getTin());
			writeElement("BO", argument.getBo());  //EI20130510 
			writeAddress(argument.getAddress());
			//EI20131015: writeContactPerson(tag, argument.getContactPerson());
			writeContactPerson("ContactPerson", argument.getContactPerson());  //EI20131015
		closeElement();					   		    		  
	}

}
