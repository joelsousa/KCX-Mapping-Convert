/*
 * Function    : MapCONToLocalAppResult.java
 * Titel       :
 * Date        : 06.05.2009
 * Author      : Kewill / Christine Kron
 * Description : Mapping of FSS-Format CON into KIDS-Format of LocalAppResult.
 *             : 
 * Parameters  : 

 * Changes 
 *
 */

package com.kewill.kcx.component.mapping.countries.de.aes21.fss2kids;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: Export/AES21<br>
 * Created		: 05.10.2012<br>
 * Description	: Mapping of FSS-Format ERR into KIDS-Format of LocalAppResult..
 *              : New in AES21:  Header MessageID and InReplyTo will be filled from TsVOR (and TsVOR from TsHEAD)
 *               
 * @author iwaniuk
 * @version 2.1.00
 */

public class MapCONToLocalAppResult extends KidsMessage {
	private TsVOR  tsVOR;
	private TsHead tsHead;   //EI20121128
	private String uidsName = "";
	
	public MapCONToLocalAppResult() {
	}
	
	public String getMessage() {
	    
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        OutputStreamWriter osw = null;
        try {
            osw = new OutputStreamWriter(bos, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
       
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            writer = factory.createXMLStreamWriter(osw);
    
            writeStartDocument(encoding, "1.0");
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            
            KidsHeader header = new KidsHeader(writer);
            //header.setHeaderFields(tsVOR);
            header.setHeaderFieldsFromHead(tsVOR, tsHead);     //EI20121005
            header.setMessageName("localAppResult");
            
            CommonFieldsDTO commonFieldsDTO = new CommonFieldsDTO();
	        commonFieldsDTO.setKcxId(header.getReceiver());
	        commonFieldsDTO.setCountryCode(header.getCountryCode());
	        commonFieldsDTO.setDirection(EDirections.CountryToCustomer);
	        header.setCommonFieldsDTO(commonFieldsDTO);
	        
            header.writeHeader();
            
          /*EI20140428: abhängig von kcx.ini wird Body für CON erstellt
            if (Config.isCreateLocalAppBody()) {
            	header.writeBody(tsHead);
            }
            ist doch nicht richtig, da durch BOB-DE gehen CONs für alle Kunden!
            also besser ueber DB, da dies betrifft nur KL (bisher)
         */   
            if (this.withBody()) {  //betrifft alle kcx-ids fuer KL            	
            	this.writeBody(tsHead);
            }
           
            
            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
      
        String xml = bos.toString();
        if (Config.getLogXML()) {
            Utils.log("(MapCONToLocalAppResult getMessage) xml = \n" + xml.replaceAll("><", ">\r\n  <"));
        }
        return xml;
    }

	public TsVOR getTsVOR() {
		return tsVOR;
	}
	public void setTsVOR(TsVOR tsVOR) {
		this.tsVOR = tsVOR;
	}

	public TsHead getTsHead() {
		return tsHead;
	}
	public void setTsHead(TsHead ts) {
		this.tsHead = ts;
	}
	
	public String getUidsName() {
		return uidsName;
	}

	public void setUidsName(String uidsName) {
		this.uidsName = uidsName;
	}
	
	private boolean withBody() {
		if (this.getCommonFieldsDTO() == null) {
			return false;
		}
		
		if (this.getCommonFieldsDTO().getCustomerDataDTO() != null &&
			this.getCommonFieldsDTO().getCustomerDataDTO().getBobName() != null &&		
			this.getCommonFieldsDTO().getCustomerDataDTO().getBobName().equals("KL")) {
			return true;
		} else  {
			return false;
		}
	}
	private void writeBody(TsHead head) throws XMLStreamException {         //EI20140428
    	if (head == null) {
    		return;
    	} 
    	String beznr = "";
        if (tsHead.getSubtyp() != null && tsHead.getSubtyp().equals("004")) {  //EI20140505 JIRA CHANGMAN-123
        	beznr = tsHead.getAllgemein();  
        }
        
    	openElement("soap:Body");
    	openElement("localAppResult");
    	openElement("GoodsDeclaration");   
    		writeElement("ReferenceNumber", beznr);       				//EI20140505
            writeElement("FileName", head.getDateiName());                                                 
        closeElement(); // GoodsDeclaration
        closeElement(); // localAppResult
        closeElement(); // soap:Body		    	
    }
}

