package com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Port - BillOfLading.<br>
 * Created		: 10.04.2012<br>
 * Description	:
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class Carriage extends KCXMessage {
		
	private CarriageDetails mainCarriage;
	private List<MeansOfTransport> preCarriageList;
	private List<MeansOfTransport> onCarriageList;
	
	private enum ECarriage {	
		MainCarriage,
		PreCarriage,
		OnCarriage;			       		
   }	

	public Carriage() {
		super();  
	}

	public Carriage(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((ECarriage) tag) {  
  				case MainCarriage:
  					mainCarriage = new CarriageDetails(getScanner());  	
  					mainCarriage.parse(tag.name());
					break; 
  				case PreCarriage:
  					MeansOfTransport pre = new MeansOfTransport(getScanner());  	
  					pre.parse(tag.name());
  					addPreCarriageList(pre);
  					break;
  				case OnCarriage:
  					MeansOfTransport on = new MeansOfTransport(getScanner());  	
  					on.parse(tag.name());
  					addOnCarriageList(on);
  					break;
				default:
  					break;
  			}
  		} else {

  			switch((ECarriage) tag) {   			  				   			
  				default:
  					break;
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}

  	public Enum translate(String token) {
  		try {
  			return ECarriage.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}
	
	public CarriageDetails getMainCarriage() {
		return mainCarriage;
	}    
	public void setMainCarriage(CarriageDetails value) {
		this.mainCarriage = value;
	}
		
	public List<MeansOfTransport> getPreCarriageList() {
		return preCarriageList;
	}    
	public void setPreCarriageList(List<MeansOfTransport> list) {
		this.preCarriageList = list;
	}
	public void addPreCarriageList(MeansOfTransport value) {
		if (preCarriageList == null) {
			preCarriageList = new ArrayList<MeansOfTransport>();
		}
		this.preCarriageList.add(value);
	}
	
	public List<MeansOfTransport> getOnCarriageList() {
		return onCarriageList;
	}    
	public void setOnCarriageList(List<MeansOfTransport> list) {
		this.onCarriageList = list;
	}
	public void addOnCarriageList(MeansOfTransport value) {
		if (onCarriageList == null) {
			onCarriageList = new ArrayList<MeansOfTransport>();
		}
		this.onCarriageList.add(value);
	}
	
	public boolean isEmpty() {
		return mainCarriage.isEmpty() && onCarriageList == null && preCarriageList == null; 
	}
}

