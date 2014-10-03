/* Function    : SensitiveGoods(KIDS) == SpecialGoodsInformation(UIDS)
* Titel       :
* Date        : 11.09.2008
* Author      : Kewill CSF 
* Description : Contains the SensitiveGoods Data 
* 			  : used in KIDS (CH))
* Parameters  :

* Changes
* -----------
* Author      : EI
* Date        : EI20090518
* Label       : 18.05.2009
* Description : Kids/Uids checked - added Uids Tags
*
*/

package com.kewill.kcx.component.mapping.countries.de.aes.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: SensitiveGoods<br>
 * Erstellt		: 11.09.2008<br>
 * Beschreibung	: Contains the SensitiveGoods Data used in KIDS (CH)). 
 * 
 * @author ???
 * @version 1.0.00
 */
public class SensitiveGoods extends KCXMessage {
	 private String type;
	 private String weight;
	  
	 private enum ESensitiveGoods {
		 Type,					Code,
		 Weight,				Amount;
	 }
	 
	 public SensitiveGoods() {
	  		super();
   	 }
	 
	 public SensitiveGoods(XmlMsgScanner scanner) {
	  		super(scanner);
	 }

	 public void startElement(Enum tag, String value, Attributes attr) {
			if (value == null) {
				switch ((ESensitiveGoods) tag) {
				default:
						return;
				}
			} else {
				switch ((ESensitiveGoods) tag) {
					case Type:
					case Code:				//EI20090518
						setType(value);
						break;
					
					case Weight:
					case Amount:   			 //EI20090518
						setWeight(value);
						break;
				}
			}
		}
	 
	 
	public void stoppElement(Enum tag) {
	}

		
	public Enum translate(String token) {
		try {
				return ESensitiveGoods.valueOf(token);
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

	public String getWeight() {
		return type;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(type) && Utils.isStringEmpty(weight) );		       
	}
}
