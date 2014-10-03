/*
 * Function    : Text
 * Titel       :
 * Date        : 02.12.2009
 * Author      : Kewill CSF / Iwaniuk
 * Description : Class for process different Code-Text's  
 * Parameters  :

 * Changes
 * ------------
 * Author      :  iwaniuk
 * Date        :  04.05.2010
 * Label       :  changed Enum-name from EBusiness into EText
 * Description : 
 *
 * Author      :  iwaniuk
 * Date        :  02.05.2011
 * Label       :  added new EText members: Name, Value for CH:Detail
 * Description : 
 
 */

package com.kewill.kcx.component.mapping.countries.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: Text<br>
 * Erstellt		: 02.12.2009<br>
 * Beschreibung	: Class for process different Code-Text's.
 * 
 * @author Iwaniuk
 * @version 1.0.00
 */
public class Text extends KCXMessage {
		
    private String code;	
    private String text;
    private String language;
  
  	private enum EText {
  		                //for CH (Ei20110502)
		Code,            Name,             Type,     //EI20111216
		Text,            Value,
		Lng, Language;
  	} 

    public Text() {
    	super();    		
    }
   
	public Text(String value, String code) {   //EI20111007
  		super();
  		this.text = value;
  		this.code = code;
	}
	
	public Text(String value, Attributes attribute) {  //EI20110926
  		super();
  		this.text = value;
  		if (attribute != null) {
  			this.language = attribute.getValue("language");
  		}
	}  		
  		
    public Text(XmlMsgScanner scanner) {
  		super(scanner);
  	}
     
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EText) tag) {
  			default:
  					return;
  			}
  		} else {
  			switch ((EText) tag) {
  			   
  				case Code:
  				case Name:
  				case Type:
  					setCode(value);
  					break;
  					
  				case Text:
  				case Value:
  					setText(value);
  					if (attr != null) {
  						setLanguage(attr.getValue("language"));  //EI20110516
  					}
  					break;
  					
  				case Lng:
  				case Language:
  					setLanguage(value);
  					break;
  					
  				default: break;
  				
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}

  	public Enum translate(String token) {
  		try {
  			return EText.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String argument) {
		this.code = argument;
	}
	public void setText(String argument) {
		this.text = argument;
	}			
	public String getText() {
		return this.text;
	}
	
	public String getLanguage() {
		return language;	
	}
	public void setLanguage(String language) {
		this.language = Utils.checkNull(language);
	}

	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.code) && Utils.isStringEmpty(this.language) && 
		        Utils.isStringEmpty(this.text));  
	}	

}
