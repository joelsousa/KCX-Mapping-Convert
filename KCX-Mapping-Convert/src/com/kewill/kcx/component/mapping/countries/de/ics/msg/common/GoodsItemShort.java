package com.kewill.kcx.component.mapping.countries.de.ics.msg.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;
import com.kewill.kcx.component.mapping.countries.common.Container;

/**
 * Module		: GoodsItemShort<br>
 * Created		: 2010.12.16<br>
 * Description	: GoodsItem tag that is used in ICS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 *
 */
public class GoodsItemShort extends KCXMessage {

	private String 				 msgName;
	private String 				 itemNumber; 
	private String				 shipmentNumber;
	private List<String>		 containersList;		
	private List<TransportMeans> meansOfTransportBorderList; 
	private List<IcsDocument>	 documentList;  
	private Text                 motivation;   //EI20110204: neu for IE322

    private boolean debug   = false;

    private enum EDeclarationGoodsItemShort {
		//KIDS:						UIDS:
		ItemNumber,					//Same
		ShipmentNumber,			    CommercialReferenceNumber,
		Containers,					ContainerNumber,
		MeansOFTransportBorder,	MeansOfTransportBorder,	TransportAtBorder,	MeansOfTransportAtBorder, 
		Document,					Documents,
		Motivation,                 //same
		EntryRejectionMotivation
    }
	
	public GoodsItemShort() {
		super();
	}

    public GoodsItemShort(XmlMsgScanner scanner) {
  		super(scanner);
  	}
    public GoodsItemShort(XmlMsgScanner scanner, String msgName) {
  		super(scanner);
  		this.msgName = msgName;
  	}
  	public boolean isDebug() {
  		return debug;
  	}
  	public void setDebug(boolean debug) {
  		this.debug = debug;
  	}
 
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EDeclarationGoodsItemShort) tag) {
  				case MeansOFTransportBorder:
  				case TransportAtBorder:
  				case MeansOfTransportAtBorder:				
  				case MeansOfTransportBorder:
  					TransportMeans tempTransportMeans = new TransportMeans(getScanner());
  					tempTransportMeans.parse(tag.name());
  					if (meansOfTransportBorderList == null) {
  						meansOfTransportBorderList = new Vector <TransportMeans>(); 
  					}
  					meansOfTransportBorderList.add(tempTransportMeans);
  					break;
  				case Document:
  				case Documents:
					IcsDocument doc = new IcsDocument(getScanner());
					doc.parse(tag.name());
					addDocument(doc);
					break;
  				case Containers:  
  				case ContainerNumber:
  					Container container = new Container(getScanner());
  					container.parse(tag.name());
  					if (containersList == null) {
  						containersList = new Vector <String>(); 
  					}
  					containersList = container.getNumberList();				
  					break;
  				case Motivation:
  				case EntryRejectionMotivation:
  					motivation = new Text(getScanner());
  					motivation.parse(tag.name());
  					break;
  				
  				default:
  					return;
  			}
  		} else {
  			switch ((EDeclarationGoodsItemShort) tag) {
				case ItemNumber:
  					setItemNumber(value);
  					break;
  				case ShipmentNumber:					
  				case CommercialReferenceNumber:
  					setShipmentNumber(value);
  					break;  
  				case Containers:
  				case ContainerNumber:
  					if (containersList == null) {
  						containersList = new Vector <String>(); 
  					}
  					containersList.add(value);
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
  			return EDeclarationGoodsItemShort.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	public String getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(String argument) {
		itemNumber = argument;
	}

	public String getShipmentNumber() {
		return shipmentNumber;
	}

	public void setShipmentNumber(String argument) {
		this.shipmentNumber = argument;
	}

	private void addDocument(IcsDocument doc) {
  		if (documentList == null) {
  			documentList = new ArrayList<IcsDocument>(); 
  		}
  		documentList.add(doc);
	}
	public List<IcsDocument> getDocumentList() {
		return documentList;
	
	}	
	public void setDocumentList(List<IcsDocument> list) {
		this.documentList = list;
	}
	
	public List<String> getContainersList() {
		return containersList;
	}
	public void setContainersList(List<String> list) {
		this.containersList = list;
	}

	public List<TransportMeans> getMeansOfTransportBorderList() {
		return meansOfTransportBorderList;
	}
	public void setMeansOfTransportBorderList(List<TransportMeans> list) {
		this.meansOfTransportBorderList = list;
	}
	
	public Text getMotivation() {
		return this.motivation;
	}

	public void setMotivation(Text argument) {
		this.motivation = argument;
	}

}
