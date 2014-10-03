/*
 * Function    : KexToKids.java
 * Titel       :
 * Date        : 03.11.2010
 * Author      : Kewill CSF / krzoska
 * Description : transformer to convert Unisys-Format to KIDS ICS messages
 *             :
 * Parameters  :

 * Changes
 * ------------
 * Author      :
 * Date        :
 * Label       :
 * Description:
 *
 */
package com.kewill.kcx.component.mapping.countries.de.kex;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.de.kex.kex2kids.MapExportDeclarationKexK;
import com.kewill.kcx.component.mapping.db.CustomerProcedureDTO;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Transformer called to convert Kex-Format to KIDS ICS messages.
 * 
 * KEX messages have Kids header
 * 
 * @author krzoska
 * @version 1.0.00
 */
public class KexToKids {

	public String readKex(XMLEventReader parser, String encoding, KidsHeader kexHeader, CommonFieldsDTO commonFieldsDTO)
																									throws Exception {
        EDirections direction   = commonFieldsDTO.getDirection();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        XMLStreamWriter writer    = getWriter(bos, encoding);
        
        
        KidsHeader kidsHeader = kexHeader;
        kidsHeader.setWriter(writer);
        
        
    	//EI20120702:  kidsHeader.setMethod("KEX");    
        kidsHeader.setMethod("atNew");   //EI20120702  KEX macht z.Z. nur atNew
    	kidsHeader.setRelease("1.0.00");
        if (direction == EDirections.CustomerToCountry) {
            kidsHeader.setDirection("FROM_CUSTOMER");
        } else {
            kidsHeader.setDirection("TO_CUSTOMER");
        }       
     //   kidsHeader.setReceiver(commonFieldsDTO.getKcxId()); 
       // kidsHeader.setCountryCode(commonFieldsDTO.getCountryCode());
        //kidsHeader.setProcedure(commonFieldsDTO.getProcedure());  

        // needed in writeHeader!  CK20120523 
        kidsHeader.setCommonFieldsDTO(commonFieldsDTO);
        
              
        //switch	
        MapExportDeclarationKexK mapExportDeclarationKexK = new MapExportDeclarationKexK(parser, kidsHeader, encoding);
        prepareMessage(kexHeader, kidsHeader, mapExportDeclarationKexK, 
        		commonFieldsDTO, "ExportDeclaration", direction);
        mapExportDeclarationKexK.getMessage(writer);        	

        String xml = bos.toString();
        return xml;        
    }
	     
	private void prepareMessage(KidsHeader kexHeader, KidsHeader kidsHeader, 
			KidsMessage message, CommonFieldsDTO commonFieldsDTO, String messageName, EDirections direction)
	                                                                            throws XMLStreamException {
		message.kidsMessageName = messageName;
        //mapKexHeaderToKidsHeader(kexHeader, kidsHeader, messageName);
        //setKidsHeaderMappingFields(kexHeader, kidsHeader, direction);         
        message.setCommonFieldsDTO(commonFieldsDTO);
	}
	
    private XMLStreamWriter getWriter(ByteArrayOutputStream bos, String encoding) throws Exception {
        OutputStreamWriter osw = null;
        try {
            osw = new OutputStreamWriter(bos, encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        XMLStreamWriter writer   = factory.createXMLStreamWriter(osw);

	    return writer;
	}
   
}
