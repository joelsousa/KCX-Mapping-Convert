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

package com.kewill.kcx.component.mapping.countries.de.aes.fss2kids.V60;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: MapCONToLocalAppResult<br>
 * Erstellt		: 06.05.2009<br>
 * Beschreibung	: Mapping of FSS-Format ERR into KIDS-Format of LocalAppResult..
 * 
 * @author houdek
 * @version 5.0.00
 */
public class MapCONToLocalAppResult extends KidsMessage {
	private TsVOR tsVOR;
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
            
            KidsHeader             header = new KidsHeader(writer);
            header.setHeaderFields(tsVOR);
            header.setMessageName("localAppResult");
            header.setMessageID(getMsgID());
            CommonFieldsDTO commonFieldsDTO = new CommonFieldsDTO();
	        commonFieldsDTO.setKcxId(header.getReceiver());
	        commonFieldsDTO.setCountryCode(header.getCountryCode());
	        commonFieldsDTO.setDirection(EDirections.CountryToCustomer);
	        header.setCommonFieldsDTO(commonFieldsDTO);
	        
            header.writeHeader();
            
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

	public String getUidsName() {
		return uidsName;
	}

	public void setUidsName(String uidsName) {
		this.uidsName = uidsName;
	}

}

