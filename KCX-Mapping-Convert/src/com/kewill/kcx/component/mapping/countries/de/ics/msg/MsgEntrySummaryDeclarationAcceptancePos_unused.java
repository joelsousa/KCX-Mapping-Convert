package com.kewill.kcx.component.mapping.countries.de.ics.msg;

import java.util.List;
import java.util.Vector;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.IcsDocument;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: MsgEntrySummaryDeclarationAcceptancePos<br>
 * Created		: 2010.07.16<br>
 * Description	: Contains Message Structure with fields used in ICSEntrySummaryDeclarationAcceptancePos.
 * 
 * @author Elbert Jude Eco
 * @version 1.0.00
 *
 */
public class MsgEntrySummaryDeclarationAcceptancePos_unused extends KCXMessage {

	private String 					msgName;
	private String					itemNumber;
	private String					shipmentNumber;
	private List<String>			containersList = null;
	private List<TransportMeans> 	meansOfTransportBorderList = null;
	private List<IcsDocument>		documentList;
	
	public MsgEntrySummaryDeclarationAcceptancePos_unused(XmlMsgScanner scanner, String msgName) {
		super(scanner);
		setMsgName(msgName);
	}

	private enum EMsgEntrySummaryDeclarationAcceptancePos {
		//KIDS								UIDS
		ItemNumber,						//same
		ShipmentNumber,					CommercialReferenceNumber,
		Containers,						ContainerNumber,
		MeansOFTransportBorder,			MeansOfTransportAtBorder,		MeansOfTransportBorder,
		Document,						Documents;
		
	} 
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EMsgEntrySummaryDeclarationAcceptancePos) tag) {
			case MeansOFTransportBorder:
			case MeansOfTransportBorder:
			case MeansOfTransportAtBorder:
				TransportMeans tempTransportMeans = new TransportMeans(getScanner());
				tempTransportMeans.parse(tag.name());
				if (meansOfTransportBorderList == null) {
				meansOfTransportBorderList = new Vector <TransportMeans>();
				}
				meansOfTransportBorderList.add(tempTransportMeans);
				break;
			case Document:
			case Documents:
				IcsDocument tempDocument = new IcsDocument(getScanner());			
				tempDocument.parse(tag.name());
				if (documentList == null) {
					documentList = new Vector <IcsDocument>();
				} 
				documentList.add(tempDocument);
				break;
			default:
				return;
			}
		} else {
			switch ((EMsgEntrySummaryDeclarationAcceptancePos) tag) {
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
				return;
			}
		}
	}
	
	public void stoppElement(Enum tag) {		
	}
	
	public Enum translate(String token) {
 		try {
  			return EMsgEntrySummaryDeclarationAcceptancePos.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}
	
	public String getMsgName() {
		return msgName;
	}

	public void setMsgName(String msgName) {
		this.msgName = msgName;
	}

	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	public String getShipmentNumber() {
		return shipmentNumber;
	}

	public void setShipmentNumber(String shipmentNumber) {
		this.shipmentNumber = shipmentNumber;
	}

	public List<String> getContainersList() {
		return containersList;
	}

	public void setContainersList(List<String> containersList) {
		this.containersList = containersList;
	}

	public List<TransportMeans> getMeansOfTransportBorderList() {
		return meansOfTransportBorderList;
	}

	public void setMeansOfTransportBorderList(
			List<TransportMeans> meansOfTransportBorderList) {
		
		this.meansOfTransportBorderList = meansOfTransportBorderList;
	}

	public List<IcsDocument> getDocumentList() {
		return documentList;
	}

	public void setDocumentList(List<IcsDocument> documentList) {
		this.documentList = documentList;
	}
	
	
}
