package com.kewill.kcx.component.mapping.countries.de.aes.msg;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Business;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CustomsOffices;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.IncoTerms;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.LoadingTime;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: Export/aes<br>
 * Created		: 16.03.2009<br>
 * Description	: Contains Message Structure with all Fields used in KIDS, UIDS 
 *              : to use for Completion Messages.
 * 				: V21 - new Tags
 * 
 * @author krzoska
 * @version 1.0.00
 */
public class MsgExpEnt extends KCXMessage {
	
	private String msgName;
	
	private String ucrNumber;
	private String completionTime;				//ST_DateTime	
	private String referenceNumber;
	private String shipmentNumber;              
	private LoadingTime loadingTime;             
	private String orderNumber;	
	//EI20090609 private String clerk;
	private ContactPerson contact; 
    private String officeOfExport;	
    private String officeForCompletion;	
    private TransportMeans transportInland;    
    private TransportMeans transportBorder;
    private Business business;    
    private Party consignee;
    private TIN consigneeTIN;        
    private Party declarant;
    private ContactPerson declarantContactPerson;
    private TIN declarantTIN;
    private IncoTerms incoTerms;  
    private MsgExpEntPos goodsItem;
    private List <MsgExpEntPos>goodsItemList;
    
	private XMLEventReader 	parser;
   
	//CK20100812 needed for NL, filled with content only if referenced ExportDeclaration
	// has a DeclarationNumber in the DB table DeclNums
	private int declarationNumber;  
    
	public MsgExpEnt() {
			super();
			goodsItemList = new Vector<MsgExpEntPos>();
	}
	
	public MsgExpEnt(XMLEventReader parser) throws XMLStreamException {
		super(parser);
		goodsItemList   = new Vector<MsgExpEntPos>();
	}
	 
	public MsgExpEnt(XMLEventReader parser, String msgName) throws XMLStreamException {
		super(parser);
		goodsItemList   = new Vector<MsgExpEntPos>();
		this.msgName = msgName;	
	}
	
	private enum EExpEnt {
		 UCRNumber,							DocumentReferenceNumber,
		 CompletionTime,					DateOfAdditionalDeclaration,
		 ShipmentNumber,      //for NL, no Tag in UIDS
		 ReferenceNumber,					//same				//for DE and CH
		 OrderNumber,						LocalReferenceNumber,
		 Clerk,							
		 ContactPerson,                 	Contact,   //Contact.Identity		 
		 MeansOfTransportInland,  			//MeansOfTransport, attr: Inland  			   
		 MeansOfTransportBorder,			//MeansOfTransport, attr: Departure		 
		 									MeansOfTransport,
		 CustomsOfficeExport,				CustomsOffices, //CustomsOffices.OfficeOfExport
		 CustomsOfficeForCompletion,		//CustomsOffices.OfficeOfAdditionalDeclarationExport
		 Business,							Transaction,
		 LoadingTime,    //for NL, no Tag in UIDS
		 Declarant,	  						//same		 
		 DeclarantTIN,						//
		 DeclarantContactPerson,			//
		 Consignee,							//same
		 ConsigneeTIN,						//	
		 IncoTerms,						    Incoterms, Incoterm,				
		 GoodsItem,							Goodsitem,                     	 
	}

	public void startElement(Enum tag, String value, Attributes attr) {
			if (value == null) {
				switch ((EExpEnt) tag) {
				
					case Contact:
					case ContactPerson:
						String clerk = "";
						if (contact != null) {
							clerk = contact.getClerk(); //EI090609 wohin damit???
						}
						contact = new ContactPerson(getScanner());
						contact.parse(tag.name());
						//this.clerk = contact.getClerk();
						break;
					
					case CustomsOffices:
						CustomsOffices customsOffices = new CustomsOffices(getScanner());
						customsOffices.parse(tag.name());
						this.officeOfExport = customsOffices.getOfficeOfExport();
                        this.officeForCompletion = customsOffices.getOfficeOfAdditionalDeclarationExport();
						break;

					case MeansOfTransportBorder:
						transportBorder = new TransportMeans(getScanner());  	
						transportBorder.parse(tag.name());
						break;
												
					case MeansOfTransportInland:
						transportInland = new TransportMeans(getScanner());  	
						transportInland.parse(tag.name());
						break;
											
					case MeansOfTransport:
						if (attr != null) {   //EI20120309
							String tt = attr.getValue("TransportType");
							if (!Utils.isStringEmpty(tt)) {
								if (tt.equals("Inland")) {
									transportInland = new TransportMeans(getScanner());
									transportInland.parse(tag.name());
								} else if (tt.equals("Border")) {
									transportBorder = new TransportMeans(getScanner());
									transportBorder.parse(tag.name());
								}
							}
						}
						break;

					case Business:
					case Transaction:
						business = new Business(getScanner());
						business.parse(tag.name());
						break;	
						
					case Declarant:
						declarant = new Party(getScanner());
						declarant.parse(tag.name());						
						break;

					case Consignee:
						consignee = new Party(getScanner());
						consignee.parse(tag.name());						
						break;
						
					case DeclarantTIN:
						declarantTIN = new TIN(getScanner());
						declarantTIN.parse(tag.name());
						break;	
						
					case DeclarantContactPerson:
						declarantContactPerson = new ContactPerson(getScanner());
						declarantContactPerson.parse(tag.name());
						break;

					case ConsigneeTIN:
						consigneeTIN = new TIN(getScanner());
						consigneeTIN.parse(tag.name());
						break;	
						
					case IncoTerms:
					case Incoterms:
						incoTerms = new IncoTerms(getScanner());
						incoTerms.parse(tag.name());
						break;						
						
					case LoadingTime:
						loadingTime = new LoadingTime(getScanner());
						loadingTime.parse(tag.name());						
						break;
						
					case Goodsitem:	
					case GoodsItem:
						goodsItem = new  MsgExpEntPos(getScanner(), msgName);
						goodsItem.parse(tag.name());						
						addGoodsItemList(goodsItem);
						break;
					
				default:
						return;
				}
			} else {
				switch ((EExpEnt) tag) {
											
					case UCRNumber:
					case DocumentReferenceNumber:
						setUCRNumber(value);
						break;
						
					case CompletionTime:
					case DateOfAdditionalDeclaration:
						setCompletionTime(value);
						break;
										
					case ReferenceNumber:
						setReferenceNumber(value);
						break;
						
					case Clerk:
						//setClerk(value);
						if (contact == null) {					
							contact = new ContactPerson();
						    contact.setClerk(value);
						}
						break;
					
					case CustomsOfficeExport:
						setCustomsOfficeExport(value);
						break;
						
					case CustomsOfficeForCompletion:
						setCustomsOfficeForCompletion(value);
						break;
						
					case OrderNumber:
					case LocalReferenceNumber:
						setOrderNumber(value);
						break;
						
					case ShipmentNumber:
						setShipmentNumber(value);
						break;
					
					default:
						break;
				}
			}
		}
	 	 

	public void stoppElement(Enum tag) {
	}
		
	public Enum translate(String token) {
		try {
				return EExpEnt.valueOf(token);
			} catch (IllegalArgumentException e) {
				return null;
			}
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String argument) {
		this.referenceNumber = argument;
	}

	public String getUCRNumber() {
		return ucrNumber;
	}
	public void setUCRNumber(String argument) {
		this.ucrNumber = argument;
	}

	public String getCompletionTime() {
		return completionTime;
	}
	public void setCompletionTime(String argument) {
		this.completionTime = argument;
	}

	
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
  
	public ContactPerson getContact() {
		return contact;
	}
	public void setContact(ContactPerson argument) {
		this.contact = argument;
	}
	
	public Business getBusiness() {
		return business;
	}
	public void setBusiness(Business argument) {
		this.business = argument;
	}
	
    public List <MsgExpEntPos>getGoodsItemList() {
    	return goodsItemList;
    }
    public void addGoodsItemList(MsgExpEntPos argument) {
    	this.goodsItemList.add(argument);
    }
    public void setGoodsItemList(List <MsgExpEntPos> argument) {
    	this.goodsItemList = argument;  	
    }

	public MsgExpEntPos getGoodsItem() {
    	return goodsItem;
    }
  
    public void setGoodsItemList(MsgExpEntPos argument) {
    	this.goodsItem = argument;  	
    }

	public String getMsgName() {
		return msgName;
	}

	public void setMsgName(String msgName) {
		this.msgName = msgName;
	}
	
	public String getCustomsOfficeExport() {
		return officeOfExport;
	}

	public void setCustomsOfficeExport(String argument) {
		this.officeOfExport = argument;
	}

	public String getCustomsOfficeForCompletion() {
		return officeForCompletion;
	}

	public void setCustomsOfficeForCompletion(String argument) {
		this.officeForCompletion = argument;
	}
	 
    public Party getDeclarant() {
    	if (declarantTIN != null || declarantContactPerson != null) {
    		if (this.declarant == null) {
    			this.declarant = new Party();
    		}
    		this.declarant.setPartyTIN(declarantTIN);
    		this.declarant.setContactPerson(declarantContactPerson);
    	}   	
    	return this.declarant;
    }    
	public void setDeclarant(Party argument) {
		this.declarant = argument;
	}
		
	public Party getConsignee() {
		if (consigneeTIN != null) {
			if (consignee == null) {
				this.consignee = new Party();
			}
			consignee.setPartyTIN(consigneeTIN);
		}
		return this.consignee;
	}
	
	public void setConsignee(Party argument) {
		this.consignee = argument;
	}
	
	public TransportMeans getTransportBorder() {
		return transportBorder;	
	}

	public void setTransportBorder(TransportMeans transportBorder) {
		if (transportBorder == null) { return; }
		this.transportBorder = transportBorder;
	}

	public TransportMeans getTransportInland() {
		return transportInland;
	
	}

	public void setTransportInland(TransportMeans transportInland) {
		this.transportInland = transportInland;
	}

	public IncoTerms getIncoTerms() {
		return incoTerms;
	}

	public void setIncoTerms(IncoTerms incoTerms) {
		this.incoTerms = incoTerms;
	}
	
	public String getShipmentNumber() {
		return shipmentNumber;
	}

	public void setShipmentNumber(String argument) {
		this.shipmentNumber = argument;
	}
	
		
	public LoadingTime getLoadingTime() {
		return loadingTime;
	}

	public void setLoadingTime(LoadingTime argument) {
		this.loadingTime = argument;
	}
	public int getDeclarationNumber() {
		return declarationNumber;
	}
	public void setDeclarationNumber(int declarationNumber) {
		this.declarationNumber = declarationNumber;
	}	
		
}

