package com.kewill.kcx.component.mapping.formats.uids.aes21;

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpSta;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ErrorType;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageV21;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module       : Export/aes.<br>
 * Created      : 17.07.2012<br>
 * Description	: Uids Body of ExportDeclarationResponse
 * 				: V21 - new Tags
 * 
 * @author iwaniuk
 * @version 2.1.00
 */

public class BodyExportDeclarationResponseUids extends UidsMessageV21 {
	
    private MsgExpSta message;
   
    public BodyExportDeclarationResponseUids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public void writeBody() {
        try { 
            openElement("soap:Body");
            openElement("Submit");               
            	//EI20120917: setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/export/body/200809");
                //EI20120917:
                if (this.getCommonFieldsDTO() != null && 
                		!Utils.isStringEmpty(this.getCommonFieldsDTO().getNameSpaceText())) {           	             
                    setAttribute("xmlns", this.getCommonFieldsDTO().getNameSpaceText());
                } else {
                    setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/export/body/200809");
                }
            
                openElement("Export");
                openElement("ExportDeclarationResponse");                        
                	writeFormattedDateTime("DateOfAcceptance", message.getAcceptanceTime(),
                    			message.getAcceptanceTimeFormat(), EFormat.ST_DateTimeZ);	                           
                    writeFormattedDateTime("DateOfCancellation", message.getCancellationTime(),
                    			message.getCancellationTimeFormat(), EFormat.ST_DateTimeZ);	                    
                    writeFormattedDateTime("DateOfClosing", message.getTimeOfCompletion(),
                			message.getTimeOfCompletionFormat(), EFormat.ST_DateTimeZ);	   //new for V21 
                    writeFormattedDateTime("DateOfExit", message.getExitTime(),
                    			message.getExitTimeFormat(), EFormat.ST_DateTimeZ);
                    if (message.getLoadingTime() != null) {                           
                        writeFormattedDateTime("DateOfLoadingBegin", message.getLoadingTime().getBeginTime(),
                        			message.getLoadingTime().getLoadingBeginFormat(), EFormat.ST_DateTimeZ);
                        writeFormattedDateTime("DateOfLoadingEnd", message.getLoadingTime().getEndTime(),
                        			message.getLoadingTime().getLoadingEndFormat(), EFormat.ST_DateTimeZ);
                    }                                              
                    writeFormattedDateTime("DateOfProcessing", message.getBeginTimeOfProcessing(),
                    			message.getBeginTimeOfProcessingFormat(), EFormat.ST_DateTimeZ);
                        
                   //writeStringToDateTime("DateOfPreAdvice", message.getTimeOfRejectionOfPreannoucement());                   
                   writeFormattedDateTime("DateOfPreAdviceRejection", message.getTimeOfRejectionOfPreannouncement(),
               			message.getTimeOfRejectionOfPreannouncementFormat(), EFormat.ST_DateTimeZ);   //new for V21                         
                    writeFormattedDateTime("DateOfReceipt", message.getReceiveTime(),
                    			message.getReceiveTimeFormat(), EFormat.ST_DateTimeZ);                      
                    writeFormattedDateTime("DateOfRejection", message.getTimeOfRejection(),
                    			message.getTimeOfRejectionFormat(), EFormat.ST_DateTimeZ);    
                    writeFormattedDateTime("DateOfRelease", message.getReleaseTime(),
                    			message.getReleaseTimeFormat(), EFormat.ST_DateTimeZ);                    
                    writeParty("Declarant", message.getDeclarant());                 //new for V21                   
                    if (message.getReason() != null || message.getStatusOfCompletion() != null) {
                    	openElement("Justification");
                        	writeElement("Code", message.getStatusOfCompletion());
                            writeElement("Description", message.getReason());                            
                        closeElement(); 
                    }
                    writeElement("ReferenceNumber", message.getReferenceNumber());                       
                    writeElement("EPUNumber", message.getEpuNumber());
                    writeElement("EntryNumber", message.getEntryNumber());
                    List<ErrorType> errorList = message.getErrorList();  
                    if (errorList != null) {
                    	for (ErrorType tmpError : errorList) {            				
                    		if (tmpError != null) {
                            openElement("Error");
                        		writeElement("Type", tmpError.getCode());
                        		writeElement("Pointer", tmpError.getPointer());  
                        		writeElement("Reason", tmpError.getText());
                        	closeElement(); 
                    		}
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
	
	public MsgExpSta getMessage() {
		return message;
	}

	public void setMessage(MsgExpSta message) {
		this.message = message;
	}
	
}

