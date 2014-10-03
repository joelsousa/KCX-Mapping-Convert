package com.kewill.kcx.component.mapping.formats.kids.aes;

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgFailure;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PositionError;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;

/**
 * Module		: AES /ALL
 * Created		: 24.09.2008
 * Description	: valid Names of KIDS-Messagenames in Export
 * 				  relates to kiff_export.xls and kiff-export-reply.xls.
 * @author kron
 * @version 1.0.00
 */

public class BodyLocalAppResultKids extends KidsMessage {
	
	private MsgFailure 	msgFailure;		
	private List<PositionError> 		errorList;
	
    public BodyLocalAppResultKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
    
    public void writeBody() {
        try {
            openElement("soap:Body");
                openElement("localAppResult");
                    openElement("GoodsDeclaration");
                        writeElement("UCRNumber", msgFailure.getUCRNumber());
                        writeElement("ReferenceNumber", msgFailure.getReferenceNumber());
                        writeElement("CorrectionNumber", msgFailure.getCorrectionNumber());
                        writeElement("TemporaryUCR", msgFailure.getTemporaryUCR());
                        writeElement("FileName", msgFailure.getFileName());
                        writeElement("ProcedureType", msgFailure.getProcedureType());
                        
                        errorList = msgFailure.getErrorList();                         
                        if (errorList != null) {
                        	for (int i = 0, listSize = errorList.size(); i < listSize; i++) {
                        		openElement("GoodsItem");                        		
                       			// writeElement("ItemNumber", "" + (i + 1));  //EI20110712
                        		// CK 09.12.2011 ItemNumber should be in the field "ErrorNumber" of 
                        		// Type PotitionError! There can be more than one error per ItemNumber
                        		// and not every item has errors - If empty: ItemNumber can be incremented!
                        		//*************************************************************************
                        		if (errorList.get(i).getErrorNumber() == null || 
                        				errorList.get(i).getErrorNumber().trim().equals("")) {                        				
                        			writeElement("ItemNumber", "" + (i + 1));
                        		} else {
                        			writeElement("ItemNumber", errorList.get(i).getErrorNumber());	
                        		}
                        			openElement("PositionError");
	                        			writeElement("KindOfError", errorList.get(i).getKindOfError());
	                        			writeElement("OriginOfError", errorList.get(i).getOriginOfError());
	                        			writeElement("Modul", errorList.get(i).getModul());
	                        			writeElement("ErrorCode", errorList.get(i).getErrorCode());
	                        			writeElement("ErrorText", errorList.get(i).getErrorText());
	                        			writeDateTimeToString("DateOfErrorMessage",
	                        					errorList.get(i).getDateOfErrorMessage());
	                        			writeElement("TimeOfErrorMessage", errorList.get(i).getTimeOfErrorMessage());
	                        		closeElement(); // PositionError
                        		closeElement(); // GoodsItem
                        	}
                        }
                    closeElement(); // GoodsDeclaration
                closeElement(); // DeclarationResponse
            closeElement(); // soap:Body
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
    }
    
	public MsgFailure getMsgKids() {
		return this.msgFailure;
	}

	public void setMessage(MsgFailure message) {
		this.msgFailure = message;
	}
    
}
