package com.kewill.kcx.component.mapping.countries.de.Import.kids2kffJob;

import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.Import.msg.MsgImportTaxAssessment;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Account;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Duties;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.GoodsItemTaxAssessment;
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
public class MapImportTaxAssessmentToKffJob_old extends KffMessageJob {
	
	private MsgJOB msgJOB;	
	private MsgImportTaxAssessment message;
		
	public MapImportTaxAssessmentToKffJob_old(XMLEventReader parser, String encoding) throws XMLStreamException {		
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
            if (message.getGoodsItemList() != null) {                        
            	openElement("Costs");   
            	for (GoodsItemTaxAssessment item : message.getGoodsItemList()) {
            		if (item != null && item.getDutiesList() != null) {
            			for (Duties duty : item.getDutiesList()) {
            				if (duty != null) {            					
            					//description = getDescription(duty.getType());
            					amount = getDecimal(duty.getAmount(), 2, 2); //Zabis 11,2  JOB 13,3  AK20120327 mit 2 decimals 
            					
            					openElement("Cost");            						
            						writeElement("CostActionCode", "U"); 
            						writeElement("CostChrgCode", duty.getType());   //wenn andere code als A0000, B0000, dann muss es hier 
            						                                              //umgesetzt werden, oder in kids2kids, z.Z aber mappingcode==DE
            						//writeElement("CostChrgDesc", description);  //EI20120316 ?? loeschen? obwohl die codes ungueltig sind
            						writeElement("CostCurrCode", "EUR");            						
            						writeElement("CostRate", amount);
            						writeElement("CostQuantity", "1");  
            						writeElement("CostQuantityUnit", "STD");  
            						writeElement("CostExRate", "1");
            						writeElement("CostAMTFC", amount);
            						writeElement("CostAMTLC", amount);
            						writeElement("CostVAT", "N");
            						writeElement("CostVATAmtLC", "0.00");
            						//EI20100928: writeElement("CostHasPassThrough", "1");
            						writeElement("CostHasPassThrough", this.getDefermentAccountType(duty.getType())); //EI20120928            						
            						writeElement("CostDocType", "IV");
            						
            					closeElement();
            				}
            			}
            		} 
            	}
                closeElement();
            }
            closeElement();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	} 

	private String getDescription(String type) {
		String description = "";
		if (type.equalsIgnoreCase("A0000")) {
			description = "Deutscher Zollbetrag";
		} else if (type.equalsIgnoreCase("B0000")) {
			description = "Deutsche Einfuhrumsatzsteuer";
		} else {
			description = "weitere Abgaben";  
		}
		return description;
	}
	
	private String getDefermentAccountType(String kind) {
		String type = "";
		
		if (Utils.isStringEmpty(kind)) {
			return "";
		}
		if (message.getDefermentAccountList() == null)  {
			return "";
		}
		for (Account account : message.getDefermentAccountList()) {
			if (account != null) {
				if (account.getKindOfExemption() != null && account.getKindOfExemption().equalsIgnoreCase(kind)) {
					type = account.getDefermentAccountType();					
					/*  EI20121002: verlegt in fss2kids
					if (accountType != null && accountType.equalsIgnoreCase("N")) {
						accountType = "0";
					}
					if (accountType != null && accountType.equalsIgnoreCase("J")) {
						accountType = "1";
					}
					*/					
				}
			}
		}		
		return type;
	}
}
