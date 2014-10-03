package com.kewill.kcx.component.mapping.countries.de.suma70.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.ReferencedSpecification;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Manifest<br>
 * Created		: 13.03.2014<br>
 * Description	: LocalApplication PositionList: == TsKUP 
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class LocalAppPosition extends KCXMessage {
		
	private String positionNumber;
	private String positionStatus; 		
	private String positionDestinationPlace; 
	private String positionAWB;
	
	private enum ELocalAppPosition {
		//KIDS		
		PositionNumber,
		PositionStatus,
		PositionDestinationPlace,
		PositionAWB,
	}
	
	public LocalAppPosition() {
		super();  
	}

    public LocalAppPosition(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
  			switch ((ELocalAppPosition) tag) {  
  			
  			default:
  					return;
  			}
  		} else {

  			switch ((ELocalAppPosition) tag) {
  			
  			case PositionNumber:
  				setPositionNumber(value);
  				break;
  				
  			case PositionStatus:
  				setPositionStatus(value);
  				break;
  			case PositionDestinationPlace:
  				setPositionDestinationPlace(value);
  				break;
  			case PositionAWB:
  				setPositionAWB(value);
  				break;
  			
  			default:
  				break;
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
				return ELocalAppPosition.valueOf(token);
			 } catch (IllegalArgumentException e) {
				return null;
			 }
	}


	public String getPositionNumber() {
		return positionNumber;
	}
	public void setPositionNumber(String number) {
		this.positionNumber = number;
	}
	
	public String getPositionStatus() {
		return positionStatus;
	}
	public void setPositionStatus(String declarationStatus) {
		this.positionStatus = declarationStatus;
	}

	public String getPositionDestinationPlace() {
		return positionDestinationPlace;
	}
	public void setPositionDestinationPlace(String value) {
		this.positionDestinationPlace = value;
	}
	
	public String getPositionAWB() {
		return positionAWB;
	}
	public void setPositionAWB(String value) {
		this.positionAWB = value;
	}
}
