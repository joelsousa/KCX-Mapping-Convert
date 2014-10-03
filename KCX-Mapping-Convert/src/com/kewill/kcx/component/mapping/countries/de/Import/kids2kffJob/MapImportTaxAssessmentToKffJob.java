package com.kewill.kcx.component.mapping.countries.de.Import.kids2kffJob;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.Import.msg.MsgImportTaxAssessment;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Account;
import com.kewill.kcx.component.mapping.formats.kff.KffMessageJob;
import com.kewill.kcx.component.mapping.formats.kff.msg.MsgJOB;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: Import<br>
 * Created		: 12.09.2011<br>
 * Description	: Mapping of FSS CTX to KIDS ImportTaxAssessment.
 *  
 * @author iwaniuk
 * @version 1.0.00
 */

public class MapImportTaxAssessmentToKffJob extends KffMessageJob {
	
	private MsgJOB msgJOB;	
	private MsgImportTaxAssessment message;
		
	public MapImportTaxAssessmentToKffJob(XMLEventReader parser, String encoding) throws XMLStreamException {		
		message = new MsgImportTaxAssessment(parser);
		msgJOB = new MsgJOB();	
		this.encoding = encoding;
	}
		
	public String getMessage() {		
	    StringWriter xmlOutputString = new StringWriter();	    
	    XMLOutputFactory factory = XMLOutputFactory.newInstance();
	    
	    try {
	    	 writer = factory.createXMLStreamWriter(xmlOutputString);
		     KidsHeader kidsHeader = this.getKidsHeader();	       
		        
		     message.parse(HeaderType.KIDS);
		     getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
		        
		     writeStartDocument(encoding, "1.0");  
		     
		     openElement("Job");	   	        
		     	writeJobHeader(kidsHeader);      	        	         
		        writeJobBody(kidsHeader);	           
		     closeElement();  
		         
		     writer.writeEndDocument();		           
		     writer.flush();
		     writer.close();
		            
		     Utils.log("JOB-Message of ImportTaxAssesment= " + xmlOutputString.toString());
		            
	    } catch (XMLStreamException e) {
	    	e.printStackTrace();
		}

	    return xmlOutputString.toString();
	}
			
	private void writeJobBody(KidsHeader kidsHeader) {
		
		try {
            openElement("JobInformation");  
            
            writeElement("FuncCode", "5");            
            writeElement("UNID", kidsHeader.getInReplyTo());                        
            writeElement("SPRefNo", message.getReferenceNumber());                                   
            	           
            //String description = "";
            String amount = "";
            if (message.getDefermentAccountList() != null) {                        
            	openElement("Costs");   
            	for (Account item : message.getDefermentAccountList()) {
            		if (item != null) {            			
            			amount = getDecimal(item.getAmountOfDuty(), 2, 2); //Zabis 11,2  JOB 13,3  AK20120327 JOB mit 2 decimals             					
            			openElement("Cost");            						
            				writeElement("CostActionCode", "U"); 
            				writeElement("CostChrgCode", item.getKindOfExemption());   
            				writeElement("CostCurrCode", "EUR");            						
            				writeElement("CostRate", amount);
            				writeElement("CostQuantity", "1");  
            				writeElement("CostQuantityUnit", "STD");  
            				writeElement("CostExRate", "1");
            				writeElement("CostAMTFC", amount);
            				writeElement("CostAMTLC", amount);
            				writeElement("CostVAT", "N");
            				writeElement("CostVATAmtLC", "0.00");            						
            				writeElement("CostHasPassThrough", item.getDefermentAccountType());            						
            				writeElement("CostDocType", "IV");            						
            			closeElement();            				            			
            		} 
            	}
                closeElement();
            }
            closeElement();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	} 
}
