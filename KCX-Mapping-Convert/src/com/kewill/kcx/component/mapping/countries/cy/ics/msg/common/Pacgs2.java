package com.kewill.kcx.component.mapping.countries.cy.ics.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Pacgs2<br>
 * Date Created	: 23.06.2011<br>
 * Description	: Packages
 * 
 * @author Frederick T.
 * @version 1.0.00
 */
public class Pacgs2 extends KCXMessage {
	
	private String type;
	private String number;
	private String quantity;
	private String marks;
	private String marksLng;
	
	
	public Pacgs2() {
      	super();
	}

	public Pacgs2(XMLEventReader parser) {
		super(parser);
	}      

	public Pacgs2(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum EPacgs2Tags {
		KinOfPacGS23,
		NumOfPacGS24,
		NumOfPieGS25,
		MarNumOfPacGSL21,
		MarNumOfPacGSL21LNG;
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((EPacgs2Tags) tag) {
		
		default:
				return;
		}
	  } else {
		switch ((EPacgs2Tags) tag) {
		case KinOfPacGS23:  setType(value);
			break;
		case NumOfPacGS24:  setNumber(value);
			break;
		case NumOfPieGS25:  setQuantity(value);
			break;
		case MarNumOfPacGSL21:  setMarks(value);
			break;
		case MarNumOfPacGSL21LNG:  setMarksLng(value);
			break;

		default:
			return;
		} 
	  }
	}
	
	@Override
	public void stoppElement(Enum tag) {
		// TODO Auto-generated method stub
		
	}

	public Enum translate(String token) {
		try {
			return EPacgs2Tags.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getType() {
		return type;
	
	}

	public void setType(String type) {
		this.type = Utils.checkNull(type);
	}

	public String getNumber() {
		return number;
	
	}

	public void setNumber(String number) {
		this.number = Utils.checkNull(number);
	}

	public String getQuantity() {
		return quantity;
	
	}

	public void setQuantity(String quantity) {
		this.quantity = Utils.checkNull(quantity);
	}

	public String getMarks() {
		return marks;
	
	}

	public void setMarks(String marks) {
		this.marks = Utils.checkNull(marks);
	}

	public String getMarksLng() {
		return marksLng;
	
	}

	public void setMarksLng(String marksLng) {
		this.marksLng = Utils.checkNull(marksLng);
	}

}
