package com.kewill.kcx.component.mapping.formats.uids.ics;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgArrivalNotification;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.EsumaDataReference;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.EsumaDetails;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.GoodsItemArn;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageICS;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module: MapArrivalNotificationUids<br>
 * Date Started: 13.07.2010<br>
 * Description	: UIDS-Body of ICSArrivalNotification.
 * 				: (KIDS: ICSArrivalNotification, IE347)
 * 
 * @author Frederick Topico 
 * @version 1.0.00
 * 
 * changes: EI20121025: removed unused import: ...de.ics.msg.common.GoodsItem
 */
public class BodyArrivalNotificationUids extends UidsMessageICS {

	private MsgArrivalNotification message;
	private String destinationCountry = "";  //EI20110209
	
	public BodyArrivalNotificationUids(XMLStreamWriter writer) {
        this.writer = writer;
    }
	
	public MsgArrivalNotification getMessage() {
		return message;
	}
	
	public void setMessage(MsgArrivalNotification argument) {
		this.message = argument;
	}
	
	public void writeBody() {
		destinationCountry = this.getUidsHeader().getTo().substring(0, 2);  //EI20110209
		try {
			openElement("soap:Body");
			openElement("Submit");
			setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/ics/body/200911"); //EI20130904
			openElement("ICS");
			openElement("ICSArrivalNotification");
			
				writeElement("LocalReferenceNumber", message.getReferenceNumber());
				writeElement("CommercialReferenceNumber", message.getShipmentNumber());
				writeElement("TotalNumberOfItems", message.getTotalNumberPositions());
				writeElement("TotalNumberOfPackages", message.getTotalNumberPackages());
				writeElement("TotalGrossMass", message.getTotalGrossMass());
				writeTransport("TransportAtBorder", message.getMeansOfTransportBorder());
				//if (destinationCountry.equals("CZ")) {     //EI20110210
				//	writeTransport("TransportAtBorderEntryKey", ???);
				//}
				writeElement("ConveyanceNumber", message.getConveyanceReference());				
				writeFormattedDateTime("ArrivalDateAndTime", message.getDateOfPresentation(), 
							message.getDateOfPresentationFormat(), EFormat.ST_DateTimeTZ);

				if (destinationCountry.equals("CZ")) {     //EI20110211
					writeCodeElementAE2CZ("ArrivalNotificationPlace", message.getCustomsOfficeFirstEntry(), "KCX0099");		
				}
				writeFormattedDateTime("ExpectedArrivalDateAndTime", message.getDeclaredDateOfArrival(), 
						message.getDeclaredDateOfArrivalFormat(), EFormat.ST_DateTimeTZ);				
				if (message.getCarrier() != null) {					
					if (message.getCarrier().getPartyTIN() != null) {
						openElement("EntryCarrier");
							writeElement("TIN", message.getCarrier().getPartyTIN().getTin());
							writeElement("TINType", message.getCarrier().getPartyTIN().getIdentificationType());
						closeElement();
					}				    				    
				}
				writeElement("OfficeOfFirstEntry", message.getCustomsOfficeFirstEntry());
				if (message.getCarrier() != null) {
					writeContact("Clerk", message.getCarrier().getContactPerson());
				}
				writeElement("OriginalCountryOfFirstEntry", message.getCountryOfficeFirstEntry());
				// *********
                // CK 20110204 temporär solange es vom Pool nicht geliefert wird für Frankreich!!
				if (destinationCountry.equals("FR")) {
					//EI14.02.2011: writeElement("OfficeOfNotification", message.getCountryOfficeFirstEntry());
					writeElement("OfficeOfNotification", message.getCustomsOfficeFirstEntry());
				}
				
				if (message.getGoodsItemList() != null) {
					for (GoodsItemArn goodsItem : message.getGoodsItemList()) {
						writeGoodsItem("ArrivalItem", goodsItem);
					}
				}
				
			closeElement();
			closeElement();
			closeElement();
			closeElement();		
		} catch (XMLStreamException e) {
            e.printStackTrace();
        }     
	}
	
	public void writeGoodsItem(String tag, GoodsItemArn goodsItem) throws XMLStreamException {
		openElement(tag);
	    	writeElement("ItemNumber", goodsItem.getItemNumber());  
	    	//CommertialReferenceNumber
	    	if (goodsItem.getDocument() != null) {        // MS20110905
	            openElement("Documents");
                    writeElement("Type", goodsItem.getDocument().getType());
                    writeElement("Reference", goodsItem.getDocument().getReference());
                closeElement();
	    	}                                             // MS20110905
	    	
	    	if (goodsItem.getEsumaDataReferenceList() != null) {
		    	for (EsumaDataReference esumaDataReference : goodsItem.getEsumaDataReferenceList()) {
		    		openElement("CustomsDataReference");
		    			Utils.log("CustomsDataReference...");
	    				writeElement("MRN", esumaDataReference.getMrn());
	    				if (esumaDataReference.getItemNumberEsumaLists() != null) {
	    					boolean mrndetails = false;
                        	for (EsumaDetails itemNumberEsuma : esumaDataReference.getItemNumberEsumaLists()) {
                        		mrndetails = true;
					    		openElement("MRNDetail");
                                writeElement("MRNItemNumber", itemNumberEsuma.getItemNumberEsuma());
					    		closeElement();
					    	}
                        	
    			    		// *********
                            // CK 20110204 temporär solange es vom Pool nicht geliefert wird für Frankreich!!
                        	if (!mrndetails && destinationCountry.equals("FR")) {
                        		openElement("MRNDetail");
                        		writeElement("MRNItemNumber", "1");                        			
    		    				closeElement();
                        	}
	    				} else {
	    					// *********
                            // CK 20110204 temporär solange es vom Pool nicht geliefert wird für Frankreich!!
			    			// writeElement("MRNItemNumber", itemNumberEsuma.getItemNumberEsuma());
	    					if (destinationCountry.equals("FR")) {
	    						openElement("MRNDetail");			    			
	    						writeElement("MRNItemNumber", "1");
	    						closeElement();
	    					}
	    				}
	    				writeElement("OriginalCountryOfFirstEntry", esumaDataReference.getCountryOfficeFirstEntry());
                    
	    				// *********
	    				// CK 20110204 temporär solange es vom Pool nicht geliefert wird für Frankreich!!
	    				if (destinationCountry.equals("FR")) {
	    					writeElement("CustomsStatusOfGoods", "T");
	    				}                    
					closeElement();
		    	}
	    	}
	    closeElement();		
	}
}
