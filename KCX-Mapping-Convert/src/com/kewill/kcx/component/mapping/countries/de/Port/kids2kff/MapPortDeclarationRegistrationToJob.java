package com.kewill.kcx.component.mapping.countries.de.Port.kids2kff;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.Port.msg.MsgPortDeclarationRegistration;
import com.kewill.kcx.component.mapping.formats.kff.KffMessageJob;
import com.kewill.kcx.component.mapping.formats.kff.msg.MsgJOB;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;



/**
 * Module       : Port<br>
 * Created      : 20.01.2012<br>
 * Description	: Mapping of KIDS to KffJob.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MapPortDeclarationRegistrationToJob extends KffMessageJob {
	
	private MsgJOB msgJOB;	
	private MsgPortDeclarationRegistration message;
	
	public MapPortDeclarationRegistrationToJob(XMLEventReader parser, String encoding) throws XMLStreamException {		
		message = new MsgPortDeclarationRegistration(parser);
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
	            
	         Utils.log("JOB Message = " + xmlOutputString.toString());
	            
	        } catch (XMLStreamException e) {
	            e.printStackTrace();
	        }

	        return xmlOutputString.toString();
	}
			
	private void writeJobBody(KidsHeader kidsHeader) {
		
		try {
            openElement("JobInformation");            	
            	writeElement("FuncCode", "5");
            	//writeElement("FuncCode", "9");   //EI20120224  
            	writeElement("UNID", kidsHeader.getInReplyTo());
            	//writeElement("DeclNo", message.getReferenceNumber());
            	writeElement("SPRefNo", message.getReferenceNumber());   //EI20120425
            	
            	openElement("RefNos");            	
            	if (message.getPortRegistrationNumber() != null) {
            		openElement("RefNo");
            			//writeElement("RefNoSNO", message.getSequenceNumber()); 
            			writeElement("RefCode", "ZNR");
            			writeElement("RefNumber", message.getPortRegistrationNumber());
                	closeElement();
            	}             	
            	closeElement();
            closeElement();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	} 
 	
	
}