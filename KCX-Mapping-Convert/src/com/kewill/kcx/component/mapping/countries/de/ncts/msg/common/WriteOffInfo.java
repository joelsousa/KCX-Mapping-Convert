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

package com.kewill.kcx.component.mapping.countries.de.ncts.msg.common;

import org.xml.sax.Attributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

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
public class WriteOffInfo extends KCXMessage {

    private List <WriteOff>  writeOffZLList;
    private List <WriteOff>  writeOffAVUVList;
    private List <WriteOffSumA> writeOffSumAList = new Vector<WriteOffSumA>();
    
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
   				WriteOff writeOffZL = new WriteOff(getScanner()); 
   				writeOffZL.parse(tag.name());	
   				addWriteOffZLList(writeOffZL);
				break;
				
   			case WriteOffAVUV:
   				WriteOff writeOffAVUV = new WriteOff(getScanner()); 
   				writeOffAVUV.parse(tag.name());	
   				addWriteOffAVUVList(writeOffAVUV);
				break; 
				
   			case WriteOffSumA:
   				WriteOffSumA writeOffSumA = new WriteOffSumA(getScanner()); 
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
   
	public List <WriteOff> getWriteOffZLList() {
		return writeOffZLList;
	}
	public void setWriteOffZLList(List <WriteOff> list) {
		writeOffZLList = list;
	}
	public void addWriteOffZLList(WriteOff argument) {
		if (writeOffZLList == null) {
			writeOffZLList = new ArrayList<WriteOff>();
		}
		writeOffZLList.add(argument);
	}
	
	public List <WriteOff> getWriteOffAVUVList() {
		return writeOffAVUVList;
	}
	public void setWriteOffAVUVList(List <WriteOff> list) {
		this.writeOffAVUVList = list;
	}
	public void addWriteOffAVUVList(WriteOff argument) {
		if (writeOffAVUVList == null) {
			writeOffAVUVList = new ArrayList<WriteOff>();
		}
		writeOffAVUVList.add(argument);
	}
   	
   	public void setWriteOffSumAList(List< WriteOffSumA > list) {
		this.writeOffSumAList = list;
	}
	
	public List< WriteOffSumA > getWriteOffSumAList() {
		return this.writeOffSumAList;
	}
	
	public void addWriteOffSumAList(WriteOffSumA argument) {
		if (writeOffSumAList == null) {
			writeOffSumAList = new ArrayList<WriteOffSumA>();
		}
		writeOffSumAList.add(argument);
	}
}
