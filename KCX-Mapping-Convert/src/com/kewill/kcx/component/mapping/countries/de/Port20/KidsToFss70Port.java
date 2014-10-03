package com.kewill.kcx.component.mapping.countries.de.Port20;

import javax.xml.stream.XMLEventReader;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.common.start.KidsToFssConverter;
import com.kewill.kcx.component.mapping.countries.de.Port.EKidsPortMessages;
import com.kewill.kcx.component.mapping.countries.de.Port.kids2fss.MapBillOfLadingToBL;
import com.kewill.kcx.component.mapping.countries.de.Port20.kids2fss.MapPortDeclarationToPOR;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;

/**
 * Module       : Port V20<br>
 * Created      : 10.05.2013<br>
 * Description: transformer called by KidsToFss to convert KIDS to ZABIS-FSS format  
 * 				of Port version 70.
 * 
 * @author iwaniuk
 * @version 2.0.00
 */
public class KidsToFss70Port extends KidsToFssConverter {

	public String readKids(XMLEventReader parser, TsVOR vorSubset, TsHead headSubset,
			KidsHeader kidsHeader, CommonFieldsDTO commonFieldsDTO)
			throws Exception {
		vorSubset.setVersnr("07.00");	  	
		vorSubset.setModul("ZP");
		headSubset.setVersion("07000");             
		headSubset.setModul(vorSubset.getModul());   
		
		String msg = kidsHeader.getMessageName();
		String fss = "";

		switch (EKidsPortMessages.valueOf(msg)) {
		case PortDeclaration:		
			vorSubset.setNatyp("POR");
			headSubset.setMsgName("POR");
			MapPortDeclarationToPOR mapDecToPor = new MapPortDeclarationToPOR(parser, vorSubset, headSubset);
			mapDecToPor.setKidsHeader(kidsHeader);
			mapDecToPor.setCommonFieldsDTO(commonFieldsDTO);
			fss = mapDecToPor.getMessage();
			break;
		
		case BillOfLading:	                                  //EI20120410	
			vorSubset.setModul("BL");   //EI20120427
			vorSubset.setNatyp("BL");
			MapBillOfLadingToBL mapBoLToBL = new MapBillOfLadingToBL(parser, vorSubset);
			mapBoLToBL.setKidsHeader(kidsHeader);
			mapBoLToBL.setCommonFieldsDTO(commonFieldsDTO);
			fss = mapBoLToBL.getMessage();
			break;
			
		default:
			throw new FssException("Unknown message type " + msg);
		}

		return fss;
	}

	public void logAudit(KidsHeader kidsHeader, CommonFieldsDTO commonFieldDTO) {
		// Audit Log wird nicht benötigt.
	}
}
