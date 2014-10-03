/*
 * Function    : BodyErrorInformation.java
 * Titel       :
 * Date        : 17.09.2008
 * Author      : Kewill CSF / Iwaniuk
 * Description : 
 * Parameters  : 

 * Changes 
 * -----------
 * Author      :
 * Date        :
 * Label       :
 * Description :
 *
 */

package com.kewill.kcx.component.mapping.formats.kids.aes;

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.common.MsgErrorPos;
import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgErrInf;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ErrorType;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PositionError;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.WriteDownAmount;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;


public class BodyErrorInformationKids extends KidsMessage {

	private MsgErrInf msgErrInf;
	private int listLength;
	private List errorList;
	private List<MsgErrorPos> goodsItemErrorList = null;    //AK20120272

    public BodyErrorInformationKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
    public void writeBody() {    	
        try {
            openElement("soap:Body");                
            	//openElement(msgErrInf.msgName);
            	openElement("ErrorMessage");
                //setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
                //setAttribute("xsi", "noNamespaceSchemaLocation");
                    openElement("GoodsDeclaration");
                        writeElement("UCRNumber", msgErrInf.getUCRNumber());
                        writeElement("UCROtherSystem", msgErrInf.getUCROtherSystem());
                        writeElement("ReferenceNumber", msgErrInf.getReferenceNumber());
                        //EI20081028: writeElement("ProcedureType", msgErrInf.getProcedureType());
                        writeElement("ProcedureType", msgErrInf.getProcedureType());    //AK20120227 
                        
                        errorList = msgErrInf.getErrorList();
                        if (errorList != null) {
	            			listLength = errorList.size();
	                        for(int i=0; i < listLength; i++) {
	                        	ErrorType tmpError = new ErrorType();
	            				tmpError = (ErrorType) errorList.get(i);
	                        	if(tmpError != null) {
	                            	openElement("Error");
	                        			//EI20081028: writeElement("UniqueNumber", tmpError.getUniqueNumber());
	                        			writeElement("Code", tmpError.getCode());
	                        			writeElement("Text", tmpError.getText());
	                        			writeElement("Pointer", tmpError.getPointer());
	                        			//EI20081028: writeElement("Number", tmpError.getNumber());
	                        		closeElement(); // Error
	                        	}
	                        }
                        }
                        
                        List<MsgErrorPos> goodsItemList = msgErrInf.getGoodsItemErrorList();
                        if (goodsItemList != null) {
                        	for (MsgErrorPos item : goodsItemList) {
                        		if (item != null) {
                        			openElement("GoodsItem");                        			
                        				writeElement("ItemNumber", item.getItemNumber());
                        				if (item.getInformationList() != null) {
                        					for (Text information : item.getInformationList()) {
                        						openElement("Information");
                        							writeElement("Type", information.getCode());
                        							writeElement("Text", information.getText());
                        						closeElement();
                        					}
                        				}
                        				
                        				if (item.getDocumentList() != null) {
                        					for (Document document : item.getDocumentList()) {
                        						openElement("Document");
                        							writeElement("Qualifier", document.getQualifier());
                        							writeElement("Type", document.getTypeKids());
                        			    			writeCodeElement("Type", document.getTypeKids(), "KCX0035");
                        							writeElement("Reference", document.getReference());
                        							writeElement("AdditionalInformation", document.getAdditionalInformation());
                        							writeElement("Detail", document.getDetail());
	                        		    			if (!Utils.isStringEmpty(document.getIssueDate())) {   
	                        		    				String idate = document.getIssueDate();
	                        		    				if (idate.length() == 10) {
	                        		            		    writeDateToString("IssueDate", idate);
	                        		            		} else {
	                        		            			writeElement("IssueDate", idate); 
	                        		            		}
	                        		    			}
	                        		    			if (!Utils.isStringEmpty(document.getExpirationDate())) {   
	                        		    				String edate = document.getIssueDate();
	                        		    				if (edate.length() == 10) {
	                        		            		    writeDateToString("ExpirationDate", edate);
	                        		            		} else {
	                        		            			writeElement("ExpirationDate", edate); 
	                        		            		}
	                        		    			}
	                        		    			writeElement("Value", document.getValue());
	                        		    			writeElement("Reason", document.getReason());
	                        			    		writeWriteDownAmount("WritedownAmount", document.getWriteDownAmount(), "KCX0016");
	                        			    		writeWriteDownAmount("WritedownQuantity", document.getWriteDownQuantity(), "");
	                        			    		writeElement("Offfice", document.getOffice());
	                        			    		writeElement("Present", document.getPresent());
	                        			    		writeElement("PresentLocation", document.getPresentLocation());
	                        			    	closeElement();
                        					}
                        				}

                        				if (item.getPositionErrorList() != null) {
                        					for (PositionError error : item.getPositionErrorList()) {
                        						openElement("PositionError");   
                        						 	 writeElement("KindOfError", error.getKindOfError());
	                        						 writeElement("OriginalError", error.getOriginOfError());
	                        						 writeElement("Modul", error.getModul());
	                        						 writeElement("ErrorCode", error.getErrorCode()); 
	                        						 writeElement("ErrorText", error.getErrorText());
	                        						 writeElement("DateOfErrorMessage", error.getDateOfErrorMessage());
	                        						 writeElement("TimeOfErrorMessage", error.getTimeOfErrorMessage());
                        						closeElement();  // PositionError
                        					}
                        				}
                        			closeElement(); // GoodsItem
                        		}
                        	}   	
                        }

                        
                    closeElement(); // GoodsDeclaration
                closeElement(); // ErrorMessage
            closeElement(); // soap:Body
        } catch (XMLStreamException e) {        	
            e.printStackTrace();
        }
    }

	public MsgErrInf getMsgErrInf() {
		return msgErrInf;
	}

	public void setMsgErrInf(MsgErrInf argument) {
		this.msgErrInf = argument;
	}
}
