package com.kewill.kcx.component.mapping.countries.de.ncts.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module    	: ResultsOfControl 
 * Created     	: 31.08.2008
 * Description 	: Contains the ResultsOfControl Data
 * 			   	  with all Fields used in KIDS/UIDS.
 * 
 * @author Jude Eco 
 * @version 1.0.00
 */

public class ResultsOfControl extends KCXMessage {
	private String type;
	private String description;
	private String controlIndicator;
	private String attributePointer;
	private String correctedValue;

	private boolean debug = false;
	private XMLEventReader parser = null;
	
	private enum EResultsOfControl {
		//UIDS					//KIDS
		Description,			//same
		ControlIndicator,		//same
		AttributePointer,		//same
		CorrectedValue;			//same
	}
	
	public ResultsOfControl(String type) {
		super();
		this.type = type;
	}

	public ResultsOfControl(XMLEventReader parser) {
	  	super(parser);
	  	this.parser = parser;
	 }
		 
	public ResultsOfControl(XmlMsgScanner scanner) {
		super(scanner);
	}
		 
  	public ResultsOfControl() {
		super();
	}

	public boolean isDebug() {
  		return debug;
  	}

  	public void setDebug(boolean debug) {
  		this.debug = debug;
  	}
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch((EResultsOfControl) tag) {
			default:
				return;
			}
		} else {
			switch((EResultsOfControl) tag) {
			case Description:
				setDescription(value);
				break;
				
			case ControlIndicator:
				setControlIndicator(value);
				break;
				
			case AttributePointer:
				setAttributePointer(value);
				break;
				
			case CorrectedValue:
				setCorrectedValue(value);
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
  			return EResultsOfControl.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getControlIndicator() {
		return controlIndicator;
	}

	public void setControlIndicator(String controlIndicator) {
		this.controlIndicator = controlIndicator;
	}

	public String getAttributePointer() {
		return attributePointer;
	}

	public void setAttributePointer(String attributePointer) {
		this.attributePointer = attributePointer;
	}

	public String getCorrectedValue() {
		return correctedValue;
	}

	public void setCorrectedValue(String correctedValue) {
		this.correctedValue = correctedValue;
	}

	public XMLEventReader getParser() {
		return parser;
	}

	public void setParser(XMLEventReader parser) {
		this.parser = parser;
	}

	public boolean isEmpty() {
		if (Utils.isStringEmpty(this.description) &&
	    		Utils.isStringEmpty(this.controlIndicator) &&
	    		Utils.isStringEmpty(this.attributePointer) &&
	    		Utils.isStringEmpty(this.correctedValue)) {    		
				return true;
			} else {
				return false;
			}
	}

}
