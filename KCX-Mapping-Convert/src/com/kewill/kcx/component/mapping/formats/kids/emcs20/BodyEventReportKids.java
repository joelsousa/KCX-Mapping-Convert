package com.kewill.kcx.component.mapping.formats.kids.emcs20;


import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgEventReport;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgEventReportPos;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageEMCS;

/**
 * Module        : EMCS V20<br>
 * Created	     : 21.07.2011<br>
 * Description   : Body of Kids-EMCSEventReport.
 *               : no changes for V20
 * 
 * @author iwaniuk
 * @version 2.0.00
 */
public class BodyEventReportKids extends KidsMessageEMCS {

	private MsgEventReport message; 
	
    public BodyEventReportKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgEventReport getMessage() {
		return message;
	}
	public void setMessage(MsgEventReport argument) {
		this.message = argument;
	}
		
    public void writeBody() {
        try {        	
            openElement("soap:Body");
            openElement("EMCS");                
            openElement("EMCSEventReport"); 
            
            	writeElement("ReferenceNumber", message.getReferenceNumber());
                writeElement("CustomerOrderNumber", message.getCustomerOrderNumber());
                writeElement("Clerk", message.getClerk());
                writeElement("AadReferenceCode", message.getAadReferenceCode());                        
                writeElement("SequenceNumber", message.getSequenceNumber());                          
                writeElement("EventReportMessageType", message.getEventReportMessageType());
                writeDateTimeToString("DateAndTimeOfValidation",  message.getDateAndTimeOfValidation());
                writeElement("ExciseCustomsOffice", message.getExciseCustomsOffice());     
                writeElement("MemberStateCode", message.getMemberStateCode());
                writeElement("EventReportNumber", message.getEventReportNumber());    
                writeElement("SubmissionEventReportReference", message.getSubmissionEventReportReference());    
                writeElement("ChangedTransportArrangement", message.getChangedTransportArrangement());
                writeElement("DateOfEvent", message.getDateOfEvent());   
                if (message.getPlaceOfEvent() != null) {
                	writeElementWithAttribute("PlaceOfEvent",
                  	message.getPlaceOfEvent().getText(),  "language", 
                	message.getPlaceOfEvent().getLanguage());
                }  
                if (message.getComments() != null) {
                	writeElementWithAttribute("Comments",
                  	message.getComments().getText(),  "language", 
                	message.getComments().getLanguage());
                }  
                writeElement("CustomsOfficerID", message.getCustomsOfficerID()); 
                writeSubmittingPerson(message.getSubmittingPerson());
                writeTrader("NewTransportArranger", message.getNewTransportArranger());                        
                writeTrader("NewTransporter", message.getNewTransporter());
                writeEvidenceOfEventList(message.getEvidenceOfEventList());
                writeTransportDetailsList(message.getTransportDetailsList());
                
                List<MsgEventReportPos> list = message.getGoodsItemList();
                if (list != null) {
                	for (MsgEventReportPos item : list) {	                        		
                		writeGoodsItem(item);                    		
                	}
                }
                writePDFInformation(message.getPDFInformation());  //EI20111007: doch nicht anders als in KidsMessage
               
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
         	writeElementWithAttribute("AssociatedInformation",
         			goodsItem.getAssociatedInformation().getText(), "language", 
         			goodsItem.getAssociatedInformation().getLanguage());
    	 }  
    	
    	 closeElement();	 
    }
}
