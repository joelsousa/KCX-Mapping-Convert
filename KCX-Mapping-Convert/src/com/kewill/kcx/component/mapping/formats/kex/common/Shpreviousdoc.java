package com.kewill.kcx.component.mapping.formats.kex.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module       : KEX<br>
 * Created      : 15.12.2011<br>
 * Description	: V03.
 * 
 * @author iwaniuk
 * @version 0.3.00
 */
public class Shpreviousdoc extends KCXMessage {
	
	//v03:
	private String shpreviousdocUnid;   				
	private String prevDocClass; 
	private String prevDocType;           	
	private String prevDocRef;            

	 public Shpreviousdoc() {
		 super();  
	 }

	 public Shpreviousdoc(XmlMsgScanner scanner) {
 		super(scanner);
	 }

	 private enum EShpreviousdoc {	
			// KEX							KIDS			
		 	shpreviousdocUnid,		 	
			prevdoctype,		
			prevdocref,			
			prevdocclass;
	 }	 

	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {		
			switch ((EShpreviousdoc) tag) {
				default: return;			
			}
		} else {			
			switch ((EShpreviousdoc) tag) {			
			
				case shpreviousdocUnid:
					setShpreviousdocUnid(value);
					break;				
				
				case prevdocclass:
					setPrevDocClass(value);
					break;
				
				case prevdoctype:
					setPrevDocType(value);
					break;
				
				case prevdocref:
					setPrevDocRef(value);
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

	@Override
	public Enum translate(String token) {
		try {
				return EShpreviousdoc.valueOf(token);
		    } catch (IllegalArgumentException e) {
				return null;
		}	
	}

	public String getShpreviousdocUnid() {
		return shpreviousdocUnid;
	}

	public void setShpreviousdocUnid(String value) {
		this.shpreviousdocUnid = Utils.checkNull(value);
	}
	
	public String getPrevDocType() {
		return prevDocType;
	}
	public void setPrevDocType(String value) {
		this.prevDocType = Utils.checkNull(value);
	}

	public String getPrevDocRef() {
		return prevDocRef;
	}
	public void setPrevDocRef(String value) {
		this.prevDocRef = Utils.checkNull(value);
	}

	public String getPrevDocClass() {
		return prevDocClass;
	}
	public void setPrevDocClass(String value) {
		this.prevDocClass = Utils.checkNull(value);
	}
	
}
