package com.kewill.kcx.component.mapping.countries.de.ncts.msg.common;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module    	: WriteOffSumA 
 * Created     	: 31.08.2008
 * Description 	: Contains the WriteOffSumA Data
 * 			   	  KIDS: ManifestCompletion
 * 
 * @author Jude Eco 
 * @version 1.0.00
 */
public class WriteOffSumA extends KCXMessage {
	private String type;
	private String identification;
	private String reference;
	private String flightNumber;
	private List<WriteOffData> writeOffDataList;
	
	private String totalNumberOfPositions;     //EI20111011
	private String authorizationNumber;        //EI20111011
	private String checked;                    //EI20111011

	private XMLEventReader parser = null;
	
	private enum EWriteOffSumA {
		//UIDS						//KIDS
		Identification,				//-- //EI20111207: in KIDS-xsd nicht definiert, trotzdem werde ich es so mappen
		Reference,					ReferenceNumber,        //EI20111011
		FlightNumber,				//--									
									Checked,                //EI20111011
									TotalNumberOfPositions, //EI20111011
									AuthorizationNumber,    //EI20111011
		WriteOffData,				CompletionItem;
	}
	
	public WriteOffSumA() {
		super();
	}
	
	public WriteOffSumA(String type) {
		super();
		this.type = type;
	}

	public WriteOffSumA(XMLEventReader parser) {
	  	super(parser);
	  	this.parser = parser;
	 }
		 
	public WriteOffSumA(XmlMsgScanner scanner) {
		super(scanner);
	}
		 
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch((EWriteOffSumA) tag) {
			case WriteOffData:
			case CompletionItem:
				WriteOffData tempWriteOffData = new WriteOffData(getScanner());
				tempWriteOffData.parse(tag.name());
				addWriteOffDataList(tempWriteOffData);
				break;
				
			default:
				return;
			}
		} else {
			switch((EWriteOffSumA) tag) {
			case Identification:
				setIdentification(value);
				break;
				
			case Reference:
			case ReferenceNumber:        //EI20111011
				setReference(value);
				break;
				
			case FlightNumber:
				setFlightNumber(value);
				break;
				
			case Checked:                //EI20111011
				setChecked(value);
				break;
			case TotalNumberOfPositions: //EI20111011
				setTotalNumberOfPositions(value);
				break;
			case AuthorizationNumber:    //EI20111011
				setAuthorizationNumber(value);
				break;
			default:
				return;
			}
		}
		
	}

	public void stoppElement(Enum tag) {
		
	}

	public Enum translate(String token) {
		try {
  			return EWriteOffSumA.valueOf(token);
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

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public List<WriteOffData> getWriteOffDataList() {
		return writeOffDataList;
	}
	public void setWriteOffDataList(List<WriteOffData> list) {
		this.writeOffDataList = list;
	}
	public void addWriteOffDataList(WriteOffData argument) {
		if (writeOffDataList == null) {
			writeOffDataList = new ArrayList<WriteOffData>();
		}
		writeOffDataList.add(argument);
	}
	
	public XMLEventReader getParser() {
		return parser;
	}

	public void setParser(XMLEventReader parser) {
		this.parser = parser;
	}
		
	public String getChecked() {
		return checked;
	}
	public void setChecked(String value) {
		this.checked = value;
	}
	
	public String getTotalNumberOfPositions() {
		return totalNumberOfPositions;
	}
	public void setTotalNumberOfPositions(String value) {
		this.totalNumberOfPositions = value;
	}
	
	public String getAuthorizationNumber() {
		return authorizationNumber;
	}
	public void setAuthorizationNumber(String value) {
		this.authorizationNumber = value;
	}
}
