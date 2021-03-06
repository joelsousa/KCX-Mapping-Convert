package com.kewill.kcx.component.mapping.formats.uids.emcs;

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgEventReport;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgEventReportPos;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageEMCS;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

 /**
 * Module        : EMCS V20<br>
 * Created	     : 21.07.2011<br>
 * Description   : Body of Uids-EMCSEventReport.
 *               : new for EMCS V20 
 * @author iwaniuk
 * @version 1.0.00
 */

public class BodyEventReportUids extends UidsMessageEMCS {
	
	private MsgEventReport message; 
	private String version = "";         
 
    public BodyEventReportUids(XMLStreamWriter writer) {
        this.writer = writer;
    }

	public MsgEventReport getMessage() {
		return message;
	}

	public void setMessage(MsgEventReport message) {
		this.message = message;
	}
    
	public void writeBody() {		
		
        try {  
        	version = this.uidsHeader.getMessageVersion();
        	version = Utils.removeDots(version.substring(0, 3));
        	
        	openElement("soap:Body");
            openElement("Submit");
                setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/emcs/body/200911");                
            openElement("EMCS");
            openElement("EMCSEventReport"); 
                
                writeElement("LocalReferenceNumber", message.getReferenceNumber());               
                writeElement("AadReferenceCode", message.getAadReferenceCode());                        
                writeElement("SequenceNumber", message.getSequenceNumber());                          
                writeElement("EventReportMessageType", message.getEventReportMessageType());
                //V20 - DateTime format changed:
                //writeStringToDateTime("DateAndTimeOfValidation",  message.getDateAndTimeOfValidation());
                writeFormattedDateTime("DateAndTimeOfValidation",  message.getDateAndTimeOfValidation(),
                    					EFormat.KIDS_DateTime, EFormat.ST_DateTimeTZ);	     //EI20111017
                writeElement("ExciseCustomsOffice", message.getExciseCustomsOffice());     
                writeElement("MemberStateCode", message.getMemberStateCode());
                writeElement("EventReportNumber", message.getEventReportNumber());    
                writeElement("SubmissionEventReportReference", message.getSubmissionEventReportReference());    
                writeElement("ChangedTransportArrangement", message.getChangedTransportArrangement());
                writeElement("DateOfEvent", message.getDateOfEvent());   
                if (message.getPlaceOfEvent() != null) {                    	
                    writeElementWithAttribute("PlaceOfEvent", message.getPlaceOfEvent().getText(),
  			                  	"language", message.getPlaceOfEvent().getLanguage());
                }  
                if (message.getComments() != null) {
                    writeElementWithAttribute("Comments", message.getComments().getText(),  
                    			"language", message.getComments().getLanguage());
                }  
                writeElement("IdentificationOfSenderCustomsOfficer", message.getCustomsOfficerID()); 
                writeSubmittingPerson(message.getSubmittingPerson());
                writeTrader("NewTransportArrangerTrader", message.getNewTransportArranger(), version); 
                writeTrader("NewTransporterTrader", message.getNewTransporter(), version);  
                writeEvidenceOfEventList(message.getEvidenceOfEventList());
                writeTransportDetailsList(message.getTransportDetailsList());
                                    
                if (message.getGoodsItemList() != null) {              //EI20110926
                    for (MsgEventReportPos item : message.getGoodsItemList()) {	                        		
                    	writeGoodsItem(item);                    		
                    }
                }
                writePdfInformation(message.getPDFInformation());   
                            
            closeElement(); 
            closeElement(); 
            closeElement(); 
            closeElement(); 
        } catch (XMLStreamException e) {           
            e.printStackTrace();
        }
    }

	private void writeGoodsItem(MsgEventReportPos goodsItem) throws XMLStreamException {
	    if (goodsItem == null) {
	    	return;
	    }
	    openElement("GoodsItem");
	    	
	    	writeElement("ItemNumber", goodsItem.getItemNumber());
	    	writeElement("EventTypeCode", goodsItem.getEventTypeCode());
	    	writeElement("IndicatorOfShortageOrExcess", goodsItem.getIndicatorOfShortageOrExcess());
	    	writeElement("ObservedShortageOrExcess", goodsItem.getObservedShortageOrExcess());
	    	if (goodsItem.getAssociatedInformation() != null) {
	         	writeElementWithAttribute("AssociatedInformation", goodsItem.getAssociatedInformation().getText(), 
	         			"language", goodsItem.getAssociatedInformation().getLanguage());
	    	}  
	    	
	    closeElement();	 
	}
}    	
