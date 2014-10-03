/*
 * Funktion    : BodyExportConfirmation.java
 * Titel       :
 * Erstellt    : 02.09.2008
 * Author      : Kewill CSF  / Christine Kron
 * Description : valid Names of UIDS-Messagenames to KIDS-Messagenames in Export
 * 			   : relates to (Confirmation) kiff-export-reply.xls 
 * Anmerkungen : 
 * Parameter   : 
 * Rückgabe    : keine
 * Aufruf      : 
 *
 * Änderungen:
 * -----------
 * Author      :
 * Datum       :
 * Kennzeichen :
 * Beschreibung:
 * Anmerkungen :
 * Parameter   :
 *
 */
package com.kewill.kcx.component.mapping.formats.uids.aes;

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.common.PDFInformation;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpCon;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: BodyExportConfirmation<br>
 * Erstellt     : 02.09.2008<br>
 * Beschreibung : valid Names of UIDS-Messagenames to KIDS-Messagenames in Export
 * 			      relates to (Confirmation) kiff-export-reply.xls.
 * 
 * @author kron
 * @version 1.0.00
 *
 */
public class BodyExportConfirmationUids extends UidsMessage {
	
    private MsgExpCon msgExpCon;
    private PDFInformation pdfInformation;
	private List<String> pdflist;    
    
    public BodyExportConfirmationUids(XMLStreamWriter writer) {
        this.writer = writer;
    }
    
	public void writeBody() {
        try {
        	int listLength = 0;
        	openElement("soap:Body");
                openElement("Submit");
                // 	C.K. Namespace Versionsabhängig setzen
                if (getUidsHeader().getUidsNamespaceVersion().trim().equals("200809")) {
                    setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/export/body/200809");
                } else {
                    setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/export/body/200601");
                }
                    openElement("Export");
                    	openElement("Confirmation");
                    	
                        //EI20110518: writeStringToDateTime("DateOfReceipt", msgExpCon.getReceiveTime());
                        writeFormattedDateTime("DateOfReceipt", msgExpCon.getReceiveTime(),   
                        		msgExpCon.getReceiveTimeFormat(), EFormat.ST_DateTimeZ);	   
                    	writeElement("DocumentReferenceNumber", msgExpCon.getUCRNumber());
                        writeElement("FunctionCode", msgExpCon.getKindOfAnswer());
                        /* EI20110811:
                        pdfInformation = msgExpCon.getPdfInformation();
                        if (pdfInformation != null && !pdfInformation.isEmpty()) {
                            openElement("PDF");
                            writeElement("Name", pdfInformation.getName());
                            writeElement("Directory", pdfInformation.getDirectory());
                            writeElement("Subdirectory", pdfInformation.getNewDirectory());
                           	pdflist = pdfInformation.getPdflist();
                           	if (pdflist != null) {
                           		listLength = pdflist.size();
                                for (int i = 0; i < listLength; i++) {
                           			writeElement("base64", pdflist.get(i));
                                }
                           	} else {
                            	Utils.log("(BodyExportConfirmationUids writeBody) pdflist is empty");
                            }
                        	closeElement(); // PDF
                        }
                        */
                        writePDFInformationList(msgExpCon.getPdfInformationList());     //EI20110811
                        
                        writeElement("ReferenceNumber", msgExpCon.getReferenceNumber());
                        closeElement(); // Confirmation
                    closeElement(); // Export
                closeElement(); // Submit
            closeElement(); // soap:Body
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

/*	public void setSampleFields() {
		msgExpCon.setReferenceNumber("ChristinesBezugsnummer");
		msgExpCon.setKindOfAnswer("1");
		msgExpCon.setUCRNumber("08DE655102276486E5");
		msgExpCon.setReceiveTime("2008-09-02T15:23:00 +0200");
		
	}*/
	
	public MsgExpCon getMsgExpCon() {
		return msgExpCon;
	}

	public void setMsgExpCon(MsgExpCon msgExpCon) {
		this.msgExpCon = msgExpCon;
	}
	
}
