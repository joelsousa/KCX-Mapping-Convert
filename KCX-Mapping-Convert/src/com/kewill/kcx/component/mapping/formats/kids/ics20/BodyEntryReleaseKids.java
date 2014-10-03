package com.kewill.kcx.component.mapping.formats.kids.ics20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.ImportOperationRelease;
import com.kewill.kcx.component.mapping.countries.de.ics20.msg.MsgEntryRelease;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageICS20;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: ICS20<br> 
 * Created		: 23.10.2012.<br>
 * Description  : Kids-Body of ICSEntryRelease
 *              : (IE330)
 * 				: new for V20: MeansOfTransportBorder, PersonLodgingSumA, Representative, DeclaredDateOfArrival,
 *              : StatusInformation  
 * 
 * @author 	iwaniuk
 * @version 2.0.00
 *
 */
public class BodyEntryReleaseKids extends KidsMessageICS20 {

	private MsgEntryRelease message;	

    public BodyEntryReleaseKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgEntryRelease getMessage() {
		return message;
	}
	
	public void setMessage(MsgEntryRelease argument) {
		this.message = argument;
	}
		
    public void writeBody() {
        try {
            openElement("soap:Body");
            openElement("ICSEntryRelease");
            openElement("GoodsDeclaration"); 
            
            	writeElement("ReferenceNumber", message.getReferenceNumber()); 
            	writeFormattedDateTime("DateOfPresentation", message.getDateOfPresentation(),
                		message.getDateOfPresentationFormat(), EFormat.KIDS_DateTime);
            	writeElement("MRN", message.getMrn());
                writeElement("ShipmentNumber", message.getShipmentNumber());
                writeElement("ConveyanceReference", message.getConveyanceReference());
                writeTransportMeansBorder(message.getMeansOfTransportBorder());                //new for V20
                writeElement("CustomsOfficeFirstEntry", message.getCustomsOfficeFirstEntry());
                  
                 if (message.getCarrier() != null) {
                    writePartyTIN("Carrier", message.getCarrier().getPartyTIN());
                    writePartyAddress("Carrier", message.getCarrier());
                 }
                 if (message.getPersonLodgingSuma() != null) {                                 //new for V20 
                     writePartyTIN("PersonLodgingSuma", message.getPersonLodgingSuma().getPartyTIN());
                     writePartyAddress("PersonLodgingSuma", message.getPersonLodgingSuma());
                 }
                 if (message.getRepresentative() != null) {                                     //new for V20 
                     writePartyTIN("Representative", message.getRepresentative().getPartyTIN());
                     writePartyAddress("Representative", message.getRepresentative());
                 }
                 writeFormattedDate("ControlResultDate", message.getControlResultDate(),
                 		message.getControlResultDateFormat(), EFormat.KIDS_Date);	
                                               	            
	             if (message.getImportOperationList() != null) {
	 		           for (ImportOperationRelease importOperation : message.getImportOperationList()) {
	 		                writeImportOperation(importOperation);
	 		           }
	 	          }
	             writeFormattedDate("DeclaredDateOfArrival", message.getDeclaredDateOfArrival(),
	                 		message.getDeclaredDateOfArrivalFormat(), EFormat.KIDS_DateTime);   //new for V20 	
	             writeStatusInformation(message.getStatusInformation());                        //new for V20           
	              	
            closeElement();
            closeElement();
            closeElement();
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	}
    
}
