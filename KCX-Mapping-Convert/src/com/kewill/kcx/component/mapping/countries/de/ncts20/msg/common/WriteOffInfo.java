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

package com.kewill.kcx.component.mapping.countries.de.ncts20.msg.common;

import org.xml.sax.Attributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: NCTS<br>
 * Erstellt		: 12.03.2009<br>
 * Beschreibung	: Contains the WriteOffAtlas Data with all Fields used in UIDS WriteOffAtlas.
 * 
 * @author Iwaniuk
 * @version 4.1.00
 */
public class WriteOffInfo extends KCXMessage {

    private ArrayList <Completion>  writeOffZLList;
    private ArrayList <Completion>  writeOffAVUVList;
    private ArrayList <ManifestCompletion> writeOffSumAList;
    
    private boolean debug   = false;

       public WriteOffInfo() {
 	      	super(); 	      	
       }

       public WriteOffInfo(XmlMsgScanner scanner) {
   		super(scanner);   		
   	}

 	private enum EWriteOffAtlas {
 		WriteOffZL,             
 		WriteOffAVUV,
 		WriteOffSumA;
      }
 	
   	public void startElement(Enum tag, String value, Attributes attr) {
   		if (value == null) {
   			switch ((EWriteOffAtlas) tag) {
   			
   			case WriteOffZL:
   				Completion writeOffZL = new Completion(getScanner()); 
   				writeOffZL.parse(tag.name());	
   				addWriteOffZLList(writeOffZL);
				break;
				
   			case WriteOffAVUV:
   				Completion writeOffAVUV = new Completion(getScanner()); 
   				writeOffAVUV.parse(tag.name());	
   				addWriteOffAVUVList(writeOffAVUV);
				break; 
				
   			case WriteOffSumA:
   				ManifestCompletion writeOffSumA = new ManifestCompletion(getScanner()); 
   				writeOffSumA.parse(tag.name());	
   				addWriteOffSumAList(writeOffSumA);
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
   
	public ArrayList <Completion> getWriteOffZLList() {
		return writeOffZLList;
	}
	public void setWriteOffZLList(ArrayList <Completion> list) {
		writeOffZLList = list;
	}
	public void addWriteOffZLList(Completion argument) {
		if (writeOffZLList == null) {
			writeOffZLList = new ArrayList<Completion>();
		}
		writeOffZLList.add(argument);
	}
	
	public ArrayList <Completion> getWriteOffAVUVList() {
		return writeOffAVUVList;
	}
	public void setWriteOffAVUVList(ArrayList <Completion> list) {
		this.writeOffAVUVList = list;
	}
	public void addWriteOffAVUVList(Completion argument) {
		if (writeOffAVUVList == null) {
			writeOffAVUVList = new ArrayList<Completion>();
		}
		writeOffAVUVList.add(argument);
	}
   	
   	public void setWriteOffSumAList(ArrayList< ManifestCompletion > list) {
		this.writeOffSumAList = list;
	}	
	public ArrayList< ManifestCompletion > getWriteOffSumAList() {
		return this.writeOffSumAList;
	}
	public void addWriteOffSumAList(ManifestCompletion argument) {
		if (writeOffSumAList == null) {
			writeOffSumAList = new ArrayList<ManifestCompletion>();
		}
		writeOffSumAList.add(argument);
	}
}
