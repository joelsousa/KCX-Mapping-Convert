/*
 * Funktion    : BodyExportDeclarationResponseUids.java
 * Titel       :
 * Erstellt    : 25.09.2008
 * Author      : CSF GmbH / Christine Kron
 * Description : valid Names of UIDS-Messagenames to KIDS-Messagenames in Export
 * 			   : relates to (Declaration Response) kids-export-reply.xls 
 * Anmerkungen : 
 * Parameter   : 
 * Rückgabe    : keine
 * Aufruf      : 
 *
 * Änderungen:
 * -----------
 * Author      : Alfred Krzoska
 * Datum       : 12.03.2009
 * Kennzeichen : AK20090312
 * Beschreibung: sequence Error-> Pointer, Reason
 * Anmerkungen : 
 * Parameter   :
 *
 */

package com.kewill.kcx.component.mapping.formats.uids.aes;

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpSta;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ErrorType;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
import com.kewill.kcx.component.mapping.util.EFormat;

public class BodyExportDeclarationResponseUids extends UidsMessage {
	
    private MsgExpSta msgExpSta;
    private int 		len;
	private List 		errorList;
	private ErrorType 	tmpError;
    
    public BodyExportDeclarationResponseUids(XMLStreamWriter writer) {
        this.writer = writer;
    }
    
	public void writeBody() {
        try { 
            openElement("soap:Body");
                openElement("Submit");
                // C.K. Namespace Versionsabhängig setzen
                if (getUidsHeader().getUidsNamespaceVersion().trim().equals("200809")) {
                    setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/export/body/200809");
                } else {
                    setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/export/body/200601");
                }
                // setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/export/body/200601");
                    openElement("Export");
                    	openElement("ExportDeclarationResponse");
                        //writeStringToDateTime("DateOfAcceptance", msgExpSta.getAcceptanceTime());
                    	writeFormattedDateTime("DateOfAcceptance", msgExpSta.getAcceptanceTime(),
                    			msgExpSta.getAcceptanceTimeFormat(), EFormat.ST_DateTimeZ);	   
                        //writeStringToDateTime("DateOfCancellation", msgExpSta.getCancellationTime());
                    	writeFormattedDateTime("DateOfCancellation", msgExpSta.getCancellationTime(),
                    			msgExpSta.getCancellationTimeFormat(), EFormat.ST_DateTimeZ);	
                        //writeStringToDateTime("DateOfExit", msgExpSta.getExitTime());
                    	writeFormattedDateTime("DateOfExit", msgExpSta.getExitTime(),
                    			msgExpSta.getExitTimeFormat(), EFormat.ST_DateTimeZ);
                        if (msgExpSta.getLoadingTime() != null) {
                            //writeStringToDateTime("DateOfLoadingBegin", msgExpSta.getLoadingTime().getBeginTime());
                            //writeStringToDateTime("DateOfLoadingEnd", msgExpSta.getLoadingTime().getEndTime());
                        	writeFormattedDateTime("DateOfLoadingBegin", msgExpSta.getLoadingTime().getBeginTime(),
                        			msgExpSta.getLoadingTime().getLoadingBeginFormat(), EFormat.ST_DateTimeZ);
                        	writeFormattedDateTime("DateOfLoadingEnd", msgExpSta.getLoadingTime().getEndTime(),
                        			msgExpSta.getLoadingTime().getLoadingEndFormat(), EFormat.ST_DateTimeZ);
                        }
                        
                        //writeStringToDateTime("DateOfProcessing", msgExpSta.getBeginTimeOfProcessing());
                        writeFormattedDateTime("DateOfProcessing", msgExpSta.getBeginTimeOfProcessing(),
                    			msgExpSta.getBeginTimeOfProcessingFormat(), EFormat.ST_DateTimeZ);
                        
                        //fehlt xsd writeStringToDateTime("DateOfPreAdvice", msgExpSta.getTimeOfRejectionOfPreannoucement());
                        //fehlt xsd writeStringToDateTime("DateOfPreAdviceRejection", msgExpSta.getTimeOfRejectionOfPreannoucement());
                        
                        //writeStringToDateTime("DateOfReceipt", msgExpSta.getReceiveTime());
                        writeFormattedDateTime("DateOfReceipt", msgExpSta.getReceiveTime(),
                    			msgExpSta.getReceiveTimeFormat(), EFormat.ST_DateTimeZ);
                        //writeStringToDateTime("DateOfRejection", msgExpSta.getTimeOfRejection());
                        writeFormattedDateTime("DateOfRejection", msgExpSta.getTimeOfRejection(),
                    			msgExpSta.getTimeOfRejectionFormat(), EFormat.ST_DateTimeZ);
                        
                        //fehlt xsd writeStringToDateTime("DateOfClosing", msgExpSta.getTimeOfCompletion());
                        
                        //writeStringToDateTime("DateOfRelease", msgExpSta.getReleaseTime());
                        writeFormattedDateTime("DateOfRelease", msgExpSta.getReleaseTime(),
                    			msgExpSta.getReleaseTimeFormat(), EFormat.ST_DateTimeZ);
                        
                        if (msgExpSta.getReason() != null || msgExpSta.getStatusOfCompletion() != null) {
                            openElement("Justification");
                            	writeElement("Code", msgExpSta.getStatusOfCompletion());
                            	writeElement("Description", msgExpSta.getReason());                            
                            closeElement(); // Justification
                        }
                        writeElement("ReferenceNumber", msgExpSta.getReferenceNumber());
                        // CK 01.02.2012
                        writeElement("EPUNumber", msgExpSta.getEpuNumber());
                        writeElement("EntryNumber", msgExpSta.getEntryNumber());
                        errorList = msgExpSta.getErrorList();
            			len = errorList.size();
                        for(int i=0; i < len; i++) {
            				tmpError = (ErrorType)errorList.get(i);
                        	if(tmpError != null) {
                            	openElement("Error");
                        			writeElement("Type", tmpError.getCode());
                        			writeElement("Pointer", tmpError.getPointer());  //AK20090312
                        			writeElement("Reason", tmpError.getText());
                        		closeElement(); // Error
                        	}
                        }
                        closeElement(); // ExportDeclarationResponse
                    closeElement(); // Export
                closeElement(); // Submit
            closeElement(); // soap:Body
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
    }

	public MsgExpSta getMsgExpSta() {
		return msgExpSta;
	}

	public void setMsgExpSta(MsgExpSta msgExpSta) {
		this.msgExpSta = msgExpSta;
	}
	
}

