package com.kewill.kcx.component.mapping.countries.fr.ncts;

import javax.xml.stream.XMLEventReader;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.common.start.KidsToFssConverter;
import com.kewill.kcx.component.mapping.countries.de.ncts.EKidsNCTSMessages;
import com.kewill.kcx.component.mapping.countries.fr.ncts.KidsToMsgEnv.MapNCTSArrivalNotificationToIE07;
import com.kewill.kcx.component.mapping.countries.fr.ncts.KidsToMsgEnv.MapNCTSDeclarationToIE13;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;

/**
 * Module : KidsToFrNCTS<br>
 * Created : 12.11.2013<br>
 * Description : Transformer to convert KIDS-Format to FR-NCTS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class KidsToFrNCTS extends KidsToFssConverter {

	public String readKids(XMLEventReader parser, KidsHeader kidsHeader, String encoding)
			throws Exception {
		
		String msg = kidsHeader.getMessageName();
		String xml = "";

		switch (EKidsNCTSMessages.valueOf(msg)) {
		case NCTSDeclaration:			
			MapNCTSDeclarationToIE13 mapDecDatToIE13 = new MapNCTSDeclarationToIE13(parser, encoding);						
			xml = mapDecDatToIE13.getMessage(kidsHeader);
			break;
	
		case ArrivalNotification:			
			MapNCTSArrivalNotificationToIE07 mapArrNotToIE07 = new MapNCTSArrivalNotificationToIE07(parser, encoding);						
			//xml = mapArrNotToIE45.getMessage(kidsHeader);
			break;
		
		default:
			throw new FssException("Unknown message type " + msg);
		}

		return xml;
	}

	public void logAudit(KidsHeader kidsHeader, CommonFieldsDTO commonFieldDTO) {
		// Audit Log wird nicht benötigt.
	}
}
