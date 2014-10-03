/*
 * Function    : MsgExpDat.java
 * Titel       :
 * Date        : 12.03.2009
 * Author      : Kewill CSF / messer
 * Description : Contains Message Structure with all Fields used in KIDS,
 * 			   : to use for ExportDeclaration Messages 
 * Parameters  : 
 * -----------
 * Changes 
 * -----------
 * Author      : 
 * Date        : 
 * Label       : 
 * Description : 
 *
 */

package com.kewill.kcx.component.mapping.countries.de.aes.msg;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Container;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.SpecialMention;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CustomsOffices;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.EdecHeader;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PlaceOfLoading;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: MsgExpDatCH<br>
 * Erstellt		: 12.03.2009<br>
 * Beschreibung	: Contains Message Structure with all Fields used in KIDS, 
 *                to use for ExportDeclaration Messages.  
 * 
 * @author messer
 * @version 1.0.00
 */
public class MsgExpDatCH extends KCXMessage {
	private String msgName = "ExportDeclaration";	
				
	private String kindOfDeclaration; 
	private String typeOfPermitCH;						
	private String nctsType;								    
	private String dispatchCountry; 
	private String destinationCountry;	
    private String transportInContainer;
    private String grossMass;	//n(11,3)    
    private String referenceNumber;
    private String orderNumber;   
    private String correctionCode;
    private String bunchNumber;	
	private String language;
	private String transferToTransitSystem;
	
	private TransportMeans trmDeparture;
	private TransportMeans trmBorder;
	private PlaceOfLoading placeOfLoading;		
	private String customsOfficeExport;		   					
	private String intendedOfficeOfExit;		
	    
    private Party forwarder;     
    private Party consignor;  
    private TIN consignorTIN;    
    private Party declarant; 
    private TIN declarantTIN;  
    
    private Container container;	 
	private PreviousDocument previousDocument;
	private List <PreviousDocument>previousDocumentList; 
	private MsgExpDatPosCH goodsItem;
	private List <MsgExpDatPosCH>goodsItemList;
	
	private List <SpecialMention> specialMentionList;
    //-------------	
	    
	private XMLEventReader 	parser;
    private boolean debug;
	
	//EI private Party exporter;
    
	public MsgExpDatCH() {
			super();
	}
	
	public MsgExpDatCH(XMLEventReader parser) throws XMLStreamException {
		super(parser);
	}
	 
	private enum EDeclaration {
		//KIDS-Name							//UIDS-Name		
		KindOfDeclaration,                  EdecHeader,   // EdecHeader.TypeOfDeclaration, for CH
		TypeOfPermitCH,								// EdecHeader
		NCTSType,							// same 				
		DispatchCountry,					CountryOfDispatch,
		DestinationCountry,					//same 		
		TransportInContainer,				ContainerIndicator,
		GrossMass,							//same 
		ReferenceNumber,					//same 
		OrderNumber,						LocalReferenceNumber,
		CorrectionCode,         					//EdecHeader.CorrectionCode		
		BunchNumber,								//EdecHeader.CollectionNumber
		Language,							//same              	
		TransferToTransitSystem,					//EdecHeader.NCTSIdentifier
		MeansOfTransportDeparture,    		MeansOfTransport, 
		MeansOfTransportBorder,				//MeansOfTransport 
		PlaceOfLoading,						//same
		CustomsOfficeExport,				CustomsOffices,			
		IntendedOfficeOfExit,					//CustomsOffices,		
		Forwarder,							Shipper,     			
		Consignor,							Exporter, 
		ConsignorTIN,								
		Declarant,                          //same
		DeclarantTIN,									
		Container,                          ContainerRegistration, // nur bei KIDS im Kopf
		PreviousDocument,					PrevRegNr,
		Goodsitem,							GoodsItem,
		SpecialMention;
	}

	public void startElement(Enum tag, String value, Attributes attr) {
			if (value == null) {
				switch ((EDeclaration) tag) {
											
					case MeansOfTransportDeparture:    
						trmDeparture = new TransportMeans(getScanner());  
						trmDeparture.parse(tag.name());
						break;	
						
					case MeansOfTransportBorder:	
						trmBorder = new TransportMeans(getScanner());  
						trmBorder.parse(tag.name());
						break;	
						
					case MeansOfTransport:
						if (attr != null) {  //EI20120309
						String tt = attr.getValue("TransportType");
						if (!Utils.isStringEmpty(tt)) {
							if (tt.equals("Departure")) {
								trmDeparture = new TransportMeans(getScanner());
								trmDeparture.parse(tag.name());
							} else if (tt.equals("Border")) {
								trmBorder = new TransportMeans(getScanner());
								trmBorder.parse(tag.name());
							}
						}
						}
						break;
						
					case PlaceOfLoading:	
						placeOfLoading = new PlaceOfLoading(getScanner());
						placeOfLoading.parse(tag.name());
						break;					
						
					case Forwarder:
					case Shipper:
						forwarder = new Party(getScanner());
						forwarder.parse(tag.name());
						break;	
						
					case Consignor:
					case Exporter:
						consignor = new Party(getScanner());
						consignor.parse(tag.name());
						break;	
						
					case ConsignorTIN:
						consignorTIN = new TIN(getScanner());
						consignorTIN.parse(tag.name());						
						break;	
											
					case Declarant:
						declarant = new Party(getScanner());
						declarant.parse(tag.name());						
						break;
						
					case DeclarantTIN:
						declarantTIN = new TIN(getScanner());
						declarantTIN.parse(tag.name());					
						break;							
					
					case Container:   //ist nur bei KIDS im Kopf
						container = new  Container(getScanner());
						container.parse(tag.name());						
						break;
					
					case PreviousDocument:
						previousDocument = new  PreviousDocument(getScanner());
						previousDocument.parse(tag.name());
						addPreviousDocumentList(previousDocument);
						break;	
						
					case SpecialMention:
						//AK20110415
						SpecialMention specialMention = new SpecialMention(getScanner());
						specialMention.parse(tag.name());
						if (specialMentionList == null) {
							specialMentionList = new Vector <SpecialMention>();
				        }
						addSpecialMentionList(specialMention);
						break;						
						
					case GoodsItem:
					case Goodsitem:
						goodsItem = new  MsgExpDatPosCH(getScanner(), msgName);
						goodsItem.parse(tag.name());
						
						if (goodsItemList == null)	{
							goodsItemList = new Vector<MsgExpDatPosCH>();
						}
						
						addGoodsItemList(goodsItem);
						break;
						
					case EdecHeader:
						EdecHeader edecHeader = new EdecHeader(getScanner());
						edecHeader.parse(tag.name());						
						setFromEdecHeader(edecHeader); 
						break;							
					
					case CustomsOffices:
						CustomsOffices customsOffices = new CustomsOffices(getScanner());
						customsOffices.parse(tag.name());
						this.customsOfficeExport = customsOffices.getOfficeOfExport();
						this.intendedOfficeOfExit = customsOffices.getOfficeOfExit();
						break;
					
					default:
						return;
				}
			} else {
				switch ((EDeclaration) tag) {
					
					case KindOfDeclaration:
						setKindOfDeclaration(value);						
						break;
						
					case TypeOfPermitCH:						
						setTypeOfPermitCH(value);
						break;	
						
					case CorrectionCode:						
						setCorrectionCode(value);
						break;	
						
					case BunchNumber:						
						setBunchNumber(value);
						break;
						
					case TransferToTransitSystem:
						setTransferToTransitSystem(value);
						break;
					
					case NCTSType:
						setNCTSType(value);
						break;							
					
					case DispatchCountry:
					case CountryOfDispatch:
						setDispatchCountry(value);
						break;
						
					case DestinationCountry:
						setDestinationCountry(value);
						break;
									
					case TransportInContainer:
					case ContainerIndicator:
						setTransportInContainer(value);
						break;	
						
					case GrossMass:
						setGrossMass(value);
						break;
															
					case ReferenceNumber:
						setReferenceNumber(value);
						break;
						
					case OrderNumber:
					case LocalReferenceNumber:
						setOrderNumber(value);
						break;
										
					case Language:
						setLanguage(value);
						break;
											
					case CustomsOfficeExport:						
						setCustomsOfficeExport(value);
						break;
					
					case IntendedOfficeOfExit:						
						setIntendedOfficeOfExit(value);
						break;
					}
			}		
	}
	 	 
	public void stoppElement(Enum tag) {
	}
		
	public Enum translate(String token) {
		try {
				return EDeclaration.valueOf(token);
			} catch (IllegalArgumentException e) {
				return null;
			}
	}
	
	//-----------------------------------------------------------
	
	
	public void setKindOfDeclaration(String argument) {
		this.kindOfDeclaration = argument;
	}
	public String getKindOfDeclaration() {
		return kindOfDeclaration;
	}
	
	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String argument) {
		this.referenceNumber = argument;
	}
	
	public String getDispatchCountry() {
		return dispatchCountry;
	}
	public void setDispatchCountry(String argument) {
		this.dispatchCountry = argument;
	}

	public String getDestinationCountry() {
		return destinationCountry;
	}
	public void setDestinationCountry(String argument) {
		this.destinationCountry = argument;
	}
	
	public String getTransportInContainer() {
		return transportInContainer;
	}
	public void setTransportInContainer(String argument) {
		this.transportInContainer = argument;
	}

	public String getGrossMass() {
		return grossMass;
	}
	public void setGrossMass(String argument) {
		this.grossMass = argument;
	}
	
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public TransportMeans getTransportMeansDeparture() { 
		return trmDeparture;
	}
	public void setTransportMeansDeparture(TransportMeans argument) {
		this.trmDeparture = argument;
	}
	
	public TransportMeans getTransportMeansBorder() { 
		return trmBorder;
	}
	public void setTransportMeansBorder(TransportMeans argument) {
		this.trmBorder = argument;
	}

	public PlaceOfLoading getPlaceOfLoading() {
		return placeOfLoading; 
	}
	public void setPlaceOfLoading(PlaceOfLoading argument) {
		this.placeOfLoading = argument;
	}

    public MsgExpDatPosCH getMsgExpRelPos() {
    	return goodsItem;
    }
	public void setMsgExpRelPos(MsgExpDatPosCH argument) {
		this.goodsItem = argument;
	}
	
    public List <MsgExpDatPosCH>getGoodsItemList() {
    	return goodsItemList;
    }
    public void addGoodsItemList(MsgExpDatPosCH argument) {
    	this.goodsItemList.add(argument);
    }
    public void setGoodsItemList(List <MsgExpDatPosCH> argument) {
    	this.goodsItemList = argument;  	
    }

	public MsgExpDatPosCH getGoodsItem() {
    	return goodsItem;
    }  
    public void setGoodsItemList(MsgExpDatPosCH argument) {
    	this.goodsItem = argument;  	
    }
		
	public String getTypeOfPermitCH() {
		return typeOfPermitCH;	
	}
	public void setTypeOfPermitCH(String typeOfPermitCH) {
		this.typeOfPermitCH = Utils.checkNull(typeOfPermitCH);
	}

	public String getCorrectionCode() {
		return correctionCode;	
	}
	public void setCorrectionCode(String correctionCode) {
		this.correctionCode = Utils.checkNull(correctionCode);
	}
	
	public String getBunchNumber() {
		return bunchNumber;	
	}
	public void setBunchNumber(String bunchNumber) {
		this.bunchNumber = Utils.checkNull(bunchNumber);
	}

	public String getNCTSType() {
		return nctsType;	
	}
	public void setNCTSType(String type) {
		nctsType = Utils.checkNull(type);
	}

	public String getLanguage() {
		return language;	
	}
	public void setLanguage(String language) {
		this.language = Utils.checkNull(language);
	}

	public String getTransferToTransitSystem() {
		return transferToTransitSystem;	
	}
	public void setTransferToTransitSystem(String transferToTransitSystem) {
		this.transferToTransitSystem = Utils.checkNull(transferToTransitSystem);
	}

	public Container getContainer() {
		return container;	
	}
	public void setContainer(Container container) {
		this.container = container;
	}

	public PreviousDocument getPreviousDocument() {
		return previousDocument;	
	}
	public void setPreviousDocument(PreviousDocument previousDocument) {
		this.previousDocument = previousDocument;
	}
	public List<PreviousDocument> getPreviousDocumentList() {
		return previousDocumentList;	
	}
	
	public void setPreviousDocumentList(List<PreviousDocument> previousDocumentList) {
		this.previousDocumentList = previousDocumentList;
	}
	public void addPreviousDocumentList(PreviousDocument previousDocument) {
		if (this.previousDocumentList == null) {
			previousDocumentList = new Vector<PreviousDocument>();
		}
		this.previousDocumentList.add(previousDocument);
	}
	
	public void setForwarder(Party argument) {
		this.forwarder = argument;				    	
    }
	public Party getForwarder() {
		return forwarder;
	}
		public void setConsignor(Party argument) {
			this.consignor = argument;				    	
	    }
		public Party getConsignor() {
			if (consignorTIN != null) {
				if (consignor == null) {
					consignor = new Party();
				}
				consignor.setPartyTIN(consignorTIN);
			}				
			return consignor;
		}		
		
		public void setDeclarant(Party argument) {
			this.declarant = argument;
		}
		public Party getDeclarant() {
			if (declarantTIN != null) {
				if (declarant == null) {
					declarant = new Party();
				}
				declarant.setPartyTIN(declarantTIN);
			}
					
			return declarant;
		}			
		
	private void setFromEdecHeader(EdecHeader edecHeader) {
		if (edecHeader == null) {
			return;
		}
		List nrList = edecHeader.getPrevRegNrList();
		
		this.kindOfDeclaration = edecHeader.getTypeOfDeclaration();
		this.typeOfPermitCH = edecHeader.getStatuscode();
		this.correctionCode = edecHeader.getCorrectionCode();
		this.bunchNumber = edecHeader.getCollectionNumber();
		this.transferToTransitSystem = edecHeader.getNctsIdentifier();
		
		if (nrList != null) {
			int size = nrList.size();
			String nr;			
			for (int i = 0; i < size; i++) { 
				nr = (String) nrList.get(i);
				PreviousDocument pd = new PreviousDocument();
				pd.setMarks(nr);
				addPreviousDocumentList(pd);
			}
		}
	}

	public void setCustomsOfficeExport(String argument) {
		this.customsOfficeExport = argument;
	}
	public String getCustomsOfficeExport() {
		return customsOfficeExport;
	}				
	
	public void setIntendedOfficeOfExit(String argument) {
		this.intendedOfficeOfExit = argument;
	}
	public String getIntendedOfficeOfExit() {
		return intendedOfficeOfExit;
	}
		
	public String getMsgName() {
		return msgName;
	}
	public void setMsgName(String argument) {
		this.msgName = argument;
	}			
	
	public void addSpecialMentionList(SpecialMention argument) {
		if (specialMentionList == null) {
			specialMentionList  = new Vector<SpecialMention>();
		}
		specialMentionList .add(argument);
	}

	
	public void setSpecialMentionList(List <SpecialMention> argument) {
		this.specialMentionList = argument;
	}		
	public List <SpecialMention> getSpecialMentionList() {
		return specialMentionList;
	}	
}

