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

package com.kewill.kcx.component.mapping.countries.de.aes.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module       : aes - Export<br>
 * Created      : 05.07.2012<br>
 * Description	: Kids Version 2.1.00
 * 
 * @author iwaniuk
 * @version 2.1.00
 */
public class MeansOfIdentification extends KCXMessage {
		
    private String type;	
    private String text;
   
  	private enum EMeansOfIdentification {
      //KIDS:			 UIDS:		               
  		Type,            IdentityOfGoods,            
		Text,            Remark;		
  	} 

    public MeansOfIdentification() {
    	super();    		
    }		
  		
    public MeansOfIdentification(XmlMsgScanner scanner) {
  		super(scanner);
  	}
     
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EMeansOfIdentification) tag) {
  			default:
  					return;
  			}
  		} else {
  			switch ((EMeansOfIdentification) tag) {  			     			
  				case Type:
  				case IdentityOfGoods:
  					setType(value);
  					break;  					
  				case Text:
  				case Remark:
  					setText(value);  					
  					break;
  				default: break;
  				
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}

  	public Enum translate(String token) {
  		try {
  			return EMeansOfIdentification.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	public String getType() {
		return this.type;
	}
	public void setType(String argument) {
		this.type = argument;
	}
				
	public String getText() {
		return this.text;
	}
	public void setText(String argument) {
		this.text = argument;
	}
		
	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.type) && Utils.isStringEmpty(this.text));  
	}	
}
