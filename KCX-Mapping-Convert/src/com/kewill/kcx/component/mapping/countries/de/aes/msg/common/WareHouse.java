package com.kewill.kcx.component.mapping.countries.de.aes.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Export/aes<br>
 * Created		: 18.12.2009<br>
 * Description	: Contains Warehouse Data with all Fields used in  KIDS.
 * 
 * @author krzoska
 * @version 1.0.00
 */
public class WareHouse extends KCXMessage {

	private String warehouseType 			= "";
	private String warehouseIdentification 	= "";
	private String warehouseCountry			= "";

    private boolean debug   = false;

	private enum EWareHouseTags {
		//KIDS:						UIDS:
		WarehouseType,				Type,
		WarehouseIdentification,	Identity,
		WarehouseCountry,			CountryCodeISO;
    }
	
	public WareHouse() {
		super();  
	}

    public WareHouse(XmlMsgScanner scanner) {
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
  			switch ((EWareHouseTags) tag) {
  			default:
  					return;
  			}
  		} else {
  			switch ((EWareHouseTags) tag) {
  				case WarehouseType:
  				case Type:
  					setWarehouseType(value);
  					break;
  					
  				case WarehouseIdentification:
  				case Identity:
  					setWarehouseIdentification(value);
  					break;
  					
  				case WarehouseCountry:
  				case CountryCodeISO:
  					setWarehouseCountry(value);
  					break;
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}

  	public Enum translate(String token) {
  		try {
  			return EWareHouseTags.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	public String getWarehouseType() {
		return warehouseType;
	}

	public void setWarehouseType(String warehouseType) {
		this.warehouseType = Utils.checkNull(warehouseType);
	}

	public String getWarehouseIdentification() {
		return warehouseIdentification;
	
	}

	public void setWarehouseIdentification(String warehouseIdentification) {
		this.warehouseIdentification = Utils.checkNull(warehouseIdentification);
	}

	public String getWarehouseCountry() {
		return warehouseCountry;
	
	}

	public void setWarehouseCountry(String warehouseCountry) {
		this.warehouseCountry = Utils.checkNull(warehouseCountry);
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(warehouseType) && Utils.isStringEmpty(warehouseIdentification) &&			
				Utils.isStringEmpty(warehouseCountry)); 
	}
}
