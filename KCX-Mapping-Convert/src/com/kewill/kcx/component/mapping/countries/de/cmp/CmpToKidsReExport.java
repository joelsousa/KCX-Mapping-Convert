package com.kewill.kcx.component.mapping.countries.de.cmp;

import javax.xml.stream.XMLEventReader;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.de.cmp.cmp2kids.MapCSNtoCustomsStatusNotificationCK;
import com.kewill.kcx.component.mapping.countries.de.cmp.cmp2kids.MapFFMReExportCK;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.MsgCmpCompleteData;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module    	: Manifest/SumA
 * Created     	: 21.08.2013
 * Description 	: Transformer to convert CMP-Format to KIDS-Suma-ReExport messages.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class CmpToKidsReExport {

	public String readCmp(XMLEventReader parser,  
			KidsHeader kidsHeader, CommonFieldsDTO commonFieldsDTO) throws Exception {
		
		String encoding = "UTF-8";
        String xml = "";	    
        String msg = "";
       
        MsgCmpCompleteData cmp = new MsgCmpCompleteData(parser);
        cmp.parse(HeaderType.KIDS);
        
        msg = "FlightManifestMessage";
        Utils.log("(CmpToReExport readCmp) MessageName = " + msg);
        
        switch(ECmpMessages.valueOf(msg)) {
	       
	    	case FlightManifestMessage:    	    		
	    		MapFFMReExportCK mapReExportCK = new MapFFMReExportCK(cmp, encoding);
	    		kidsHeader.setMessageName("ReExport");
	    		mapReExportCK.setKidsHeader(kidsHeader);
	    		mapReExportCK.setCommonFieldsDTO(commonFieldsDTO);
    			xml = mapReExportCK.getMessage();    			
                break;                  
	    	
	    	default: throw new FssException("Unknown CMP-Message " + msg);
        }

        Utils.log("(CmpToKidsManifest) converted message = \n" + xml);
        return xml;
	}
	
}
