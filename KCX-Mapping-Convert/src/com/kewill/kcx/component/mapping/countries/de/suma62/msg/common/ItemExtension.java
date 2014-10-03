package com.kewill.kcx.component.mapping.countries.de.suma62.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Manifest<br>
 * Created		: 19.11.2012<br>
 * Description	: Contains the ItemExtension Data with all Fields used in KIDS Manifest.
 * 
 * @author Alfred Krzoska
 * @version 1.0.00
 */
public class ItemExtension extends KCXMessage {
	
	private String 	externalCode;
	private String 	temporaryStorageCode;   //EI20121211
	
	private enum EItemExtension {
		//KIDS	== UIDS
		ExternalCode,
		TemporaryStorageCode;
	}
	
	public ItemExtension() {
		super();  
	}

    public ItemExtension(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
  			switch ((EItemExtension) tag) {
  			default:
  					return;
  			}
  		} else {

  			switch ((EItemExtension) tag) {
  				case ExternalCode:  				
  					setExternalCode(value);
  					break;
  				case TemporaryStorageCode:
  					setTemporaryStorageCode(value);
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
				return EItemExtension.valueOf(token);
			 } catch (IllegalArgumentException e) {
				return null;
			 }
	}

	public String getExternalCode() {
		return externalCode;
	}
	public void setExternalCode(String externalCode) {
		this.externalCode = Utils.checkNull(externalCode);
	}
	
	public String getTemporaryStorageCode() {
		return temporaryStorageCode;
	}
	public void setTemporaryStorageCode(String externalCode) {
		this.temporaryStorageCode = Utils.checkNull(externalCode);
	}
}
