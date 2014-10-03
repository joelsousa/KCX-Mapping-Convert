/* Function    : SealNumber.java
* Titel       :
* Date        : 11.09.2008
* Author      : Kewill CSF / krzoska
* Description : Contains the SealNumbers Data for Seal
* 			  : used in KIDS and UIDS
* Parameters  :

* Changes
* -----------
* Author      : EI
 * Date        :
 * Label       : 18.05.2009
 * Description : Kids/Uids  checked - no changes
 * 
 * -----------
 * Author      : AK
 * Date        :
 * Label       : AK20091217
 * Description : SealingParty added
 * 
 * -----------
 * Author      : EI
 * Date        :
 * Label       : EI20100617
 * Description : move from ase.common into common, added elements in enum
*/

package com.kewill.kcx.component.mapping.countries.common;

import org.xml.sax.Attributes;

//import com.kewill.kcx.component.mapping.countries.common.Seal.ESeal;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: SealNumber<br>
 * Erstellt		: 11.09.2008<br>
 * Beschreibung	: Contains the SealNumbers Data for Seal used in KIDS and UIDS.
 * 
 * @author krzoska
 * @version 1.0.00
 */
public class SealNumber extends KCXMessage {
	
	 private String number;   	
	 private String language;  
	 private String sealingParty;
	 
     private boolean debug   = false;
	 
     public SealNumber() {
  		super();
  	}
     public SealNumber(XmlMsgScanner scanner) {
 		super(scanner); 		
 	}

	private enum ESealNumbersTags {
		// Kids-TagNames, 			UIDS-TagNames      KIDS-ICS
			Number, 				Identity,		SealsIdentity,			
									Language,       SealsIdentityLng,
			//AK20091217						
			SealingParty;			// same			
	}
	
 	public void startElement(Enum tag, String value, Attributes attr) {
 		if (value == null) {
 			switch ((ESealNumbersTags) tag) {
 			default:
 					return;
 			}
 		} else {
 			switch ((ESealNumbersTags) tag) {
 			
 				case Number:
 				case Identity:
 				case SealsIdentity:
 					setNumber(value);
 					break;
 				
 				case Language:
 				case SealsIdentityLng:
 					setLanguage(value);
 					break;
 				
 				//AK20091217	
				case SealingParty:
 					setSealingParty(value);
 					break; 					
 			}
 		}
 	}

 	public void stoppElement(Enum tag) {
 	}

 	public Enum translate(String token) {
 		try {
 			return ESealNumbersTags.valueOf(token);
 		} catch (IllegalArgumentException e) {
 			return null;
 		}
 	}

	public boolean isDebug() {
 		return debug;
 	}
 	public void setDebug(boolean debug) {
 		this.debug = debug;
 	}	
 	public String getNumber() {
		return number;
	}
 	
	public void setNumber(String argument) {
		this.number = argument;
	}
	
	public void setLanguage(String argument) {
		this.language = argument;
	}
	public String getLanguage() {
		return language;
	}
	public String getSealingParty() {
		return sealingParty;
	
	}
	public void setSealingParty(String sealingParty) {
		this.sealingParty = Utils.checkNull(sealingParty);
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.language) && Utils.isStringEmpty(this.number) && 
		        Utils.isStringEmpty(this.sealingParty));  
	}	
}
