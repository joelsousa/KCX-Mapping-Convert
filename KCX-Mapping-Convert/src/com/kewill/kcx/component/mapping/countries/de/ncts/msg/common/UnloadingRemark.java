package com.kewill.kcx.component.mapping.countries.de.ncts.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;


/**
 * Module		: UnloadingRemark
 * Created		: 02.09.2010
 * Description	: contains UnloadingRemark data with all fields used in KIDS/UIDS.
 * 
 * @author Jude Eco
 * @version 1.0.00
 *
 */

public class UnloadingRemark extends KCXMessage {

	private String type;
	private String stateOfSealsOk;
	private String unloadingRemark;
	private String conform;
	private String unloadingCompletion;
	private String unloadingDate;
	
	private EFormat unloadingDateFormat;
	
	private boolean debug   = false;
	private XMLEventReader parser = null;
	
	private enum EUnloadingRemark {
		//UIDS					//KIDS
		StateOfSealsOk,			//same
		Remarks,				//same //EE20100914
		Conform,				//same
		UnloadingCompletion,	//same
		UnloadingDate,			//same
	}
	
	public UnloadingRemark() {
		super();
	}
	
	public UnloadingRemark(XMLEventReader parser) {
	  	super(parser);
	  	this.parser = parser;
	 }
		 
	public UnloadingRemark(XmlMsgScanner scanner) {
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
			switch ((EUnloadingRemark) tag) {
			default:
				return;
			}
		} else {
			switch ((EUnloadingRemark) tag) {
			case StateOfSealsOk:
				setStateOfSealsOk(value);
				break;
				
			case Remarks:
				setUnloadingRemark(value);
				break;
				
			case Conform:
				setConform(value);
				break;
				
			case UnloadingCompletion:
				setUnloadingCompletion(value);
				break;
				
			case UnloadingDate:
				setUnloadingDate(value);
				if (value.indexOf('-') == -1) {
					setUnloadingDateFormat(EFormat.KIDS_Date);
				} else {
					setUnloadingDateFormat(EFormat.ST_Date);
				}
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
  			return EUnloadingRemark.valueOf(token);
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

	public String getStateOfSealsOk() {
		return stateOfSealsOk;
	}

	public void setStateOfSealsOk(String stateOfSealsOk) {
		this.stateOfSealsOk = stateOfSealsOk;
	}

	public String getUnloadingRemark() {
		return unloadingRemark;
	}

	public void setUnloadingRemark(String unloadingRemark) {
		this.unloadingRemark = unloadingRemark;
	}

	public String getConform() {
		return conform;
	}

	public void setConform(String conform) {
		this.conform = conform;
	}

	public String getUnloadingCompletion() {
		return unloadingCompletion;
	}

	public void setUnloadingCompletion(String unloadingCompletion) {
		this.unloadingCompletion = unloadingCompletion;
	}

	public String getUnloadingDate() {
		return unloadingDate;
	}

	public void setUnloadingDate(String unloadingDate) {
		this.unloadingDate = unloadingDate;
	}

	public XMLEventReader getParser() {
		return parser;
	}

	public void setParser(XMLEventReader parser) {
		this.parser = parser;
	}

	public EFormat getUnloadingDateFormat() {
		return unloadingDateFormat;
	}

	public void setUnloadingDateFormat(EFormat format) {
		this.unloadingDateFormat = format;
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.stateOfSealsOk) && 
				Utils.isStringEmpty(this.unloadingRemark) && 
				Utils.isStringEmpty(this.conform) && 
				Utils.isStringEmpty(this.unloadingCompletion) && 
				Utils.isStringEmpty(this.unloadingDate));				
	}

}
