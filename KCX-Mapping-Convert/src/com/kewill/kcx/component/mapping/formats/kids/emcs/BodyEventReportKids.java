package com.kewill.kcx.component.mapping.formats.kids.emcs;


import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgEventReport;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgEventReportPos;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgExplanationOfReasonForShortagePos;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageEMCS;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module        : EMCS V20<br>
 * Created	     : 21.07.2011<br>
 * Description   : Body of Kids-EMCSEventReport.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class BodyEventReportKids extends KidsMessageEMCS {

	private MsgEventReport message; 
	private String version = "";         

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
        	version = this.kidsHeader.getRelease();
        	version = Utils.removeDots(version.substring(0, 3));
        	
            openElement("soap:Body");
            openElement("EMCS");                
            openElement("EMCSEventReport"); 
            
            	writeElement("ReferenceNumber", message.getReferenceNumber());
                writeElement("CustomerOrderNumber", message.getCustomerOrderNumber());
                writeElement("Clerk", message.getClerk());
                writeElement("AadReferenceCode", message.getAadReferenceCode());                        
                writeElement("SequenceNumber", message.getSequenceNumber());                          
                //writeCodeElement("EventReportMessageType", message.getEventReportMessageType(), "A0085");
                writeElement("EventReportMessageType", message.getEventReportMessageType());
                writeDateTimeToString("DateAndTimeOfValidation",  message.getDateAndTimeOfValidation());
                writeElement("ExciseCustomsOffice", message.getExciseCustomsOffice());     
                //writeCodeElement("MemberStateCode", message.getMemberStateCode(), "C0011");
                writeElement("MemberStateCode", message.getMemberStateCode());
                writeElement("EventReportNumber", message.getEventReportNumber());    
                writeElement("SubmissionEventReportReference", message.getSubmissionEventReportReference());    
                //writeCodeElement("ChangedTransportArrangement", message.getChangedTransportArrangement(), "A0070");
                writeElement("ChangedTransportArrangement", message.getChangedTransportArrangement());
                writeElement("DateOfEvent", message.getDateOfEvent());   
                if (message.getPlaceOfEvent() != null) {
                	//writeCodeElementWithAttribute("PlaceOfEvent",
                  	//	message.getPlaceOfEvent().getText(), "C0012", "language", 
                	//	message.getPlaceOfEvent().getLanguage());
                	writeElementWithAttribute("PlaceOfEvent",
                          	message.getPlaceOfEvent().getText(), "language", 
                        	message.getPlaceOfEvent().getLanguage());
                }  
                if (message.getComments() != null) {
                	//writeCodeElementWithAttribute("Comments",
                  	//	message.getComments().getText(), "C0012", "language", 
                	//	message.getComments().getLanguage());
                	writeElementWithAttribute("Comments",
                          	message.getComments().getText(),"language", 
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
                //writePDFInformation(message.getPDFInformation(), version);  //EI20111007
                
                
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
    	//writeCodeElement("EventTypeCode", goodsItem.getEventTypeCode(), "C0015");
    	writeElement("EventTypeCode", goodsItem.getEventTypeCode());
    	//writeCodeElement("IndicatorOfShortageOrExcess", goodsItem.getIndicatorOfShortageOrExcess(), "A0047");
    	writeElement("IndicatorOfShortageOrExcess", goodsItem.getIndicatorOfShortageOrExcess());
    	writeElement("ObservedShortageOrExcess", goodsItem.getObservedShortageOrExcess());
    	 if (goodsItem.getAssociatedInformation() != null) {
         	//writeCodeElementWithAttribute("AssociatedInformation",
         	//		goodsItem.getAssociatedInformation().getText(), "C0012", "language", 
         	//		goodsItem.getAssociatedInformation().getLanguage());
         	writeElementWithAttribute("AssociatedInformation",
         			goodsItem.getAssociatedInformation().getText(),  "language", 
         			goodsItem.getAssociatedInformation().getLanguage());
    	 }  
    	
    	 closeElement();	 
    }
}
