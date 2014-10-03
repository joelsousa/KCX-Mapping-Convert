package com.kewill.kcx.component.mapping.countries.de.ics.msg;

import java.util.List;
import java.util.Vector;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.IcsDocument;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: MsgEntrySummaryDeclarationPos<br>
 * Erstellt		: 10.06.2010<br>
 * Beschreibung : Contains Message Structure with fields used for GoodsItems of ICSEntrySummaryDeclaration,.
 *                 
 * @author Alfred Krzoska
 * @version 1.0.00
 */
public class MsgAdvanceInterventionNotPos_unused extends KCXMessage {

	private String 				 msgName;
	private String 				 itemNumber; 
	private String				 shipmentNumber;
	private List<IcsDocument>	 documentList;   //99
	private List<String>		 containersList;	//99	
	private List<TransportMeans> meansOfTransportBorderList; //999 

	public MsgAdvanceInterventionNotPos_unused() {
		super();				
	}

	public MsgAdvanceInterventionNotPos_unused(XmlMsgScanner scanner, String msgName) {
		super(scanner);
		setMsgName(msgName);
	}
 
	private enum EMsgEntrySummaryDeclarationPos {
		//KIDS:							UIDS:
		ItemNumber,
		ShipmentNumber,				CommercialReferenceNumber,
		Document,					Documents,
		Containers,					ContainerNumber,
		MeansOFTransportBorder,		TransportAtBorder,	MeansOfTransportAtBorder,
		MeansOfTransportBorder;   //LC 2010 0720		
		//TODO check capital F in MeansOFTransportBorder with Daggi		
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EMsgEntrySummaryDeclarationPos) tag) {					
			case Document: 
			case Documents:
				IcsDocument tempDocument = new IcsDocument(getScanner());			
				tempDocument.parse(tag.name());
				if (documentList == null) {
					documentList = new Vector <IcsDocument>();
				}
				documentList.add(tempDocument);
				break;
			case MeansOfTransportBorder://LC 2010 0720	
			case MeansOFTransportBorder:
			case TransportAtBorder:
			case MeansOfTransportAtBorder:				//PT20100618
				TransportMeans tempTransportMeans = new TransportMeans(getScanner());
				tempTransportMeans.parse(tag.name());
				if (meansOfTransportBorderList == null) {
					meansOfTransportBorderList = new Vector <TransportMeans>();
				}
				meansOfTransportBorderList.add(tempTransportMeans);
				break;
				
			default:
				return;
			}
	    } else {
	    	switch ((EMsgEntrySummaryDeclarationPos) tag) {
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
    			default: break;
			}
	    }		
	}

	public void stoppElement(Enum tag) {		
	}

	public Enum translate(String token) {
 		try {
  			return EMsgEntrySummaryDeclarationPos.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}

	public String getMsgName() {
		return msgName;	
	}
	public void setMsgName(String msgName) {
		this.msgName = Utils.checkNull(msgName);
	}

	public String getItemNumber() {
		return itemNumber;	
	}
	public void setItemNumber(String itemNumber) {
		this.itemNumber = Utils.checkNull(itemNumber);
	}

	public String getShipmentNumber() {
		return shipmentNumber;	
	}
	public void setShipmentNumber(String shipmentNumber) {
		this.shipmentNumber = Utils.checkNull(shipmentNumber);
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

	public List<IcsDocument> getDocumentList() {
		return documentList;
	}
	public void setDocumentList(List<IcsDocument> list) {
		this.documentList = list;
	}
}
