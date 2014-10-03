package com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: Port - BillOfLading.<br>
 * Created		: 10.04.2012<br>
 * Description	:
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class PartyRows extends KCXMessage {
		
	private String firstRow;
	private List<String> nextRowList;
	private String nameFormatCode;
	
	private enum EPartyRows {	
		FirstRow,
		NextRow,
		NameFormatCode;
   }	

	public PartyRows() {
		super();  
	}
	public PartyRows(String name, String s1, String s2, String s3, String s4) {
		super();  
		this.firstRow = name;
		this.addNextRowList(Utils.checkNull(s1)); 
		this.addNextRowList(Utils.checkNull(s2)); 
		this.addNextRowList(Utils.checkNull(s3)); 
		this.addNextRowList(Utils.checkNull(s4)); 
	}
	
	public PartyRows(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EPartyRows) tag) {  
				default:
  					break;
  			}
  		} else {

  			switch((EPartyRows) tag) {   			
  				case FirstRow:
  					setFirstRow(value);
  					break;  
  				case NextRow:
  					addNextRowList(value);
  					break;
  				case NameFormatCode:
  					setNameFormatCode(value);
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
  			return EPartyRows.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}
	
	public String getFirstRow() {
		return firstRow;
	}    
	public void setFirstRow(String value) {
		this.firstRow = value;
	}
		
	public List<String> getNextRowList() {
		return nextRowList;
	}		
    public void setNextRowList(List<String> list) {
		this.nextRowList = list;
	}
    public void addNextRowList(String argument) {
    	if (nextRowList == null) {
    		nextRowList = new ArrayList<String>();
    	}
		this.nextRowList.add(argument);
	}
    
    public String getNameFormatCode() {
		return nameFormatCode;
	}    
	public void setNameFormatCode(String value) {
		this.nameFormatCode = value;
	}
}

