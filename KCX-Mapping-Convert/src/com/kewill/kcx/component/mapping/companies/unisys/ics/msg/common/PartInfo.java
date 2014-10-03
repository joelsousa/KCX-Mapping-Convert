package com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common;

import java.util.List;
import java.util.Vector;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: PartInfo<br>
 * Erstellt		: 02.12.2010<br>
 * Beschreibung	: Conversion of Unisys to KIDS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class PartInfo extends KCXMessage {
			
	 private List <Participant> participantList;

	 private enum EPartInfo {
		 // Unisys-TagNames, 			KIDS-TagNames
			 PARTICIPANT;
	 }
	 
	 public PartInfo() {
	      	super();	
	 }    
   
	 public PartInfo(XmlMsgScanner scanner) {
		super(scanner);		
	 }	 
	 
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EPartInfo) tag) {
			case PARTICIPANT:
				Participant party = new Participant(getScanner());  	
				party.parse(tag.name());
				addParticipantList(party);				
				break;				
			default:
					return;
			}
		} else {

			switch ((EPartInfo) tag) {				 										
				default:
					break;
			}
		}
	}

	public void stoppElement(Enum tag) {
	}

	public Enum translate(String token) {				
		try {
			return EPartInfo.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public List<Participant> getParticipantList() {
		return participantList;	
	}
	public void setParticipantList(List<Participant> list) {
		this.participantList = list;
	}

	public void addParticipantList(Participant argument) {
		if (participantList == null) {
			participantList = new Vector<Participant>();
		}
		participantList.add(argument);
	}
	
}
