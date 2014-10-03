/*
 * Function    : WriteOffAtlas (UIDS)
 * Titel       :
 * Date        : 12.03.2009
 * Author      : Kewill CSF / Iwaniuk
 * Description : Contains the WriteOffAtlas Data
 * 			   : with all Fields used in UIDS WriteOffAtlas
 * Parameters  : Version 60

 * Changes
 * -----------
 * Author      : EI
 * Date        : 18.05.2009
 * Label       :
 * Description : Kids/Uids checked - no changes
 */

package com.kewill.kcx.component.mapping.countries.de.aes.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: WriteOffAtlas<br>
 * Erstellt		: 12.03.2009<br>
 * Beschreibung	: Contains the WriteOffAtlas Data with all Fields used in UIDS WriteOffAtlas.
 * 
 * @author Iwaniuk
 * @version 6.0.00
 */
public class WriteOffAtlas extends KCXMessage {

    private Completion writeOffZL;
    private Completion writeOffAVUV;
    
    private boolean debug   = false;

       public WriteOffAtlas() {
 	      	super(); 	      	
       }

       public WriteOffAtlas(XmlMsgScanner scanner) {
   		super(scanner);   		
   	}

 	private enum EWriteOffAtlas {
 		WriteOffZL,             
 		WriteOffAVUV;
      }
 	
   	public void startElement(Enum tag, String value, Attributes attr) {
   		if (value == null) {
   			switch ((EWriteOffAtlas) tag) {
   			case WriteOffZL:
   				writeOffZL = new Completion(getScanner()); 
   				writeOffZL.parse(tag.name());					
				break;
				
   			case WriteOffAVUV:
   				writeOffAVUV = new Completion(getScanner()); 
   				writeOffAVUV.parse(tag.name());					
				break; 
				
   			default:
   					return;
   			}
   		} else {
   			switch ((EWriteOffAtlas) tag) {
   			default:
   					break;
   			}
   		}
   	}

   	public void stoppElement(Enum tag) {
   	}

   	public Enum translate(String token) {
   		try {
   			return EWriteOffAtlas.valueOf(token);
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
   	
	public Completion getWriteOffZL() {
		return writeOffZL;
	}

	public void setWriteOffZL(Completion argument) {
		this.writeOffZL = argument;
	}
	
	public Completion getWriteOffAVUV() {
		return writeOffAVUV;
	}

	public void setWriteOffAVUV(Completion argument) {
		this.writeOffAVUV = argument;
	}
}
