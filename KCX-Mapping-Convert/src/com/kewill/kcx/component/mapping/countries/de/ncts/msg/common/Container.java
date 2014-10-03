package com.kewill.kcx.component.mapping.countries.de.ncts.msg.common;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Container
 * Created		: 13.09.2010
 * Description	: contains the Container Data with all fields used in KIDS/UIDS.
 * 
 * @author Lassiter
 * @version 4.0.00
 */
public class Container extends KCXMessage {

	private List<String> numberList;

	private XMLEventReader parser = null;

	protected enum EContainerTags {
		// Kids-TagNames, 			UIDS-TagNames
		Number, 					ContainerNumber;
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
			default:
				return;
			}
		} else {
			switch ((EContainerTags) tag) {
			case Number:
			case ContainerNumber:
				addNumberList(value);
				break;
			default:
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

	public void addNumberList(String argument) {
		if (numberList == null) {
			numberList = new Vector<String>();
		}
		this.numberList.add(argument);
	}

	public List<String> getNumberList() {
		return numberList;
	}

	public void setNumberList(List<String> numberList) {
		this.numberList = numberList;
	}
	public boolean isEmpty() {
		
		if ((this.numberList == null 
				|| (this.numberList != null && this.numberList.isEmpty()))) 		  
		{    		
			return true;
		} else {
			return false;
		}
	}		

}
