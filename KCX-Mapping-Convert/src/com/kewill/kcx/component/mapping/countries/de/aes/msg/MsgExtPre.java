package com.kewill.kcx.component.mapping.countries.de.aes.msg;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.TransportMeans;

/**
 * Modul		: Export<br>
 * Erstellt		: 06.07.2012.<br>
 * Beschreibung : KIDS MsgExitPresentation            
 * 
 * @author iwaniuk
 * @version 1.1.00
 */
 
public class MsgExtPre extends KCXMessage {
	
	private String msgName = "ExitPresentation";
	//Reihenfolge wie in xsd-definition:
	private String ucrNumber;
	private String presentationTime;
	private String ucrOtherSystem;
	private String referenceNumber;
	private ContactPerson contact;   
	private String kindOfInformation;  
	private String flagInformation;  
	private TransportMeans meansOfTransport;
	private String customsOfficeExport;	
	private String preCustomsClearance;     //EI20130812
    private Party forwarder;   
    private TIN forwarderTIN;
    private ContactPerson forwarderContactPerson;   	
	private List <MsgExtPrePos> goodsItemList;
		
	
	public MsgExtPre() {
			super();
	}
	
	public MsgExtPre(XMLEventReader parser) throws XMLStreamException {
		super(parser);
	}
	 
	private enum EExitPresentation {
		//KIDS-Name							//UIDS-Name        
		UCRNumber,							DocumentReferenceNumber,
		PresentationTime,			        //
		UCROtherSystem,						ExternalRegistrationNumber,				
		ReferenceNumber,					//same 		
		ContactPerson,	Contact,            //
		KindOfInformation,                  //
		FlagInformation,                    //
		MeansOfTransport,                   //
		CustomsOfficeExport,                //	
		PreCustomsClearance,
		Forwarder,							Shipper,     			
		ForwarderTIN,		                //Shipper,  				
		ForwarderContactPerson,	            //Shipper,  
				
		GoodsItem,								
	}

	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EExitPresentation) tag) {
			case Contact:					
				case ContactPerson:					
					contact = new ContactPerson(getScanner());
					contact.parse(tag.name());					
					break;	
					
				case MeansOfTransport:
					meansOfTransport = new TransportMeans(getScanner());  	
					meansOfTransport.parse(tag.name());
					break;	
					
				case Forwarder:
				case Shipper:
					forwarder = new Party(getScanner());
					forwarder.parse(tag.name());
					break;					
				case ForwarderTIN:
					forwarderTIN = new TIN(getScanner());
					forwarderTIN.parse(tag.name());				
				case ForwarderContactPerson:
					forwarderContactPerson = new ContactPerson(getScanner());
					forwarderContactPerson.parse(tag.name());
					break;							
												
				case GoodsItem:				
					MsgExtPrePos goodsItem = new MsgExtPrePos(getScanner());
					goodsItem.parse(tag.name());
					if (goodsItemList == null) {
                        goodsItemList = new Vector<MsgExtPrePos>();
					}					
					addGoodsItemList(goodsItem);
					break;
					
				default:
					return;
			}
		} else {
			switch ((EExitPresentation) tag) {				
				case UCRNumber:
				case DocumentReferenceNumber:
					setUCRNumber(value);
					break;
				
				case PresentationTime:
					setPresentationTime(value);
					break;
					
				case UCROtherSystem:
				case ExternalRegistrationNumber:					
					setUCROtherSystem(value);
					break;	
									
				case ReferenceNumber:
					setReferenceNumber(value);
					break;
									
				case KindOfInformation:
					setKindOfInformation(value);
					break;
				
				case FlagInformation:
					setFlagInformation(value);
					break;				
					
				case CustomsOfficeExport:						
					setCustomsOfficeExport(value);
					break;
				
				case PreCustomsClearance:
					setPreCustomsClearance(value);
					break;
					
				default: break;
				}
		}		
	}

	public void stoppElement(Enum tag) {
	}
		
	public Enum translate(String token) {
		try {
				return EExitPresentation.valueOf(token);
			} catch (IllegalArgumentException e) {
				return null;
			}
	}
	
	//-----------------------------------------------------------
	public String getMsgName() {
		return msgName;
	}
	public void setMsgName(String argument) {
		this.msgName = argument;
	}
	
	public String getUCRNumber() {
		return ucrNumber;
	}
	public void setUCRNumber(String argument) {
		this.ucrNumber = argument;
	}
	
	public String getPresentationTime() {
		return presentationTime;
	}
	public void setPresentationTime(String argument) {
		this.presentationTime = argument;
	}
	
	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String argument) {
		this.referenceNumber = argument;
	}
	
	public String getUCROtherSystem() {
		return ucrOtherSystem;
	}
	public void setUCROtherSystem(String argument) {
		this.ucrOtherSystem = argument;
	}

	public String getKindOfInformation() {
		return kindOfInformation;
	}
	public void setKindOfInformation(String argument) {
		this.kindOfInformation = argument;
	}

	public String getFlagInformation() {
		return flagInformation;
	}
	public void setFlagInformation(String argument) {
		this.flagInformation = argument;
	}
	
	public String getCustomsOfficeExport() {
		return customsOfficeExport;
	}
	public void setCustomsOfficeExport(String argument) {
		this.customsOfficeExport = argument;
	}
	
	
    public List <MsgExtPrePos> getGoodsItemList() {
    	return goodsItemList;
    }
    public void addGoodsItemList(MsgExtPrePos argument) {
    	if (this.goodsItemList == null) {
    		this.goodsItemList = new Vector<MsgExtPrePos>();
    	}
    	this.goodsItemList.add(argument);
    }
    public void setGoodsItemList(List <MsgExtPrePos> argument) {
    	this.goodsItemList = argument;  	
    }
		
	public void setContact(ContactPerson argument) {
		this.contact = argument;
	}
	public ContactPerson getContact() {
		return contact;
	}	
	
	public TransportMeans getMeansOfTransport() {
		return meansOfTransport;
	}

	public void setMeansOfTransport(TransportMeans meansOfTransport) {
		this.meansOfTransport = meansOfTransport;
	}

	
	public TIN getForwarderTIN() {
		return forwarderTIN;
	}

	public void setForwarderTIN(TIN tin) {
		this.forwarderTIN = tin;
	}
	
	public ContactPerson getForwarderContactPerson() {
		return forwarderContactPerson;
	}

	public String getPreCustomsClearance() {
		return preCustomsClearance;
	}
	public void setPreCustomsClearance(String preCustomsClearance) {
		this.preCustomsClearance = preCustomsClearance;
	}
	
	public void setForwarderContactPerson(ContactPerson contact) {
		this.forwarderContactPerson = contact;
	}	
	public void setForwarder(Party argument) {
		this.forwarder = argument;				    	
    }
	public Party getForwarder() {
		if (forwarderTIN != null) {
			if (forwarder == null) {
				forwarder = new Party();
			}
			forwarder.setPartyTIN(forwarderTIN);
		}
		if (forwarderContactPerson != null) {
			if (forwarder == null) {
				forwarder = new Party();
			}
			forwarder.setContactPerson(forwarderContactPerson);
		}
		return forwarder;
	}
}

