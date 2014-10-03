package com.kewill.kcx.component.mapping.countries.de.ncts20;

import javax.xml.stream.XMLEventReader;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.common.start.KidsToFssConverter;
import com.kewill.kcx.component.mapping.countries.de.ncts20.EKidsNCTSMessages;
import com.kewill.kcx.component.mapping.countries.de.ncts20.kids2fss.MapNCTSArrivalNotificationToVIA;
import com.kewill.kcx.component.mapping.countries.de.ncts20.kids2fss.MapNCTSDeclarationToVAN;
import com.kewill.kcx.component.mapping.countries.de.ncts20.kids2fss.MapNCTSUnloadingRemarksToVUR;
import com.kewill.kcx.component.mapping.countries.de.ncts20.kids2kids.MapNCTSDeclarationKK;
import com.kewill.kcx.component.mapping.countries.de.suma70.kids2fss.MapCustomsStatusNotificationToCSN;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;

/**
 * Module 		: NCTS<br>
 * Created 		: 13.02.2013<br>
 * Description 	: Transformer called by external programs (i. e. not MULE) to
 * 				  convert KIDS-Format to ZABIS FSS.
 * 
 * @author iwaniuk
 * @version 4.1.00
 */
public class KidsToFss70NCTS extends KidsToFssConverter {

	public String readKids(XMLEventReader parser, TsVOR vorSubset, TsHead headSubset,
			KidsHeader kidsHeader, CommonFieldsDTO commonFieldsDTO)  throws Exception {
		
		vorSubset.setVersnr("07.00");
		vorSubset.setModul("VE");
		
		headSubset.setVersion("07000");              
		headSubset.setModul(vorSubset.getModul());   	
		
		String msg = kidsHeader.getMessageName();
		String fss = "";

		  //EI20130213: nur die drei Nachrichten gehen an ZABIS, in V62 ist zu viel programmiert!
		switch (EKidsNCTSMessages.valueOf(msg)) {
		case NCTSDeclaration:
			/*
			if (kidsHeader != null && kidsHeader.getMethod() != null && kidsHeader.getCountryCode() != null &&
				      kidsHeader.getCountryCode().equals("AT") && kidsHeader.getMethod().equals("VEO")) {  //EI20131122
				//wen im BOB-CMP Method auf "CMP" gesetzt wird, dann muss hier auf "VEO" gesetzt werden
				kidsHeader.setMethod("VEO");   //EI20131122 nur fuer AT					
				MapNCTSDeclarationKK mapNCTSDeclarationKK = new MapNCTSDeclarationKK(parser, "UTF-8");
	    		mapNCTSDeclarationKK.setKidsHeader(kidsHeader);
	    		fss = mapNCTSDeclarationKK.getMessage();		    			    	
	    		break;	    		
			} else {  //so wie bisher	
			*/	
				vorSubset.setNatyp("VAN");
				headSubset.setMsgName("VAN");
				MapNCTSDeclarationToVAN mapDecDatToVAN = new MapNCTSDeclarationToVAN(parser, vorSubset, headSubset);
				mapDecDatToVAN.setKidsHeader(kidsHeader);
				mapDecDatToVAN.setCommonFieldsDTO(commonFieldsDTO);
				fss = mapDecDatToVAN.getMessage();
				break;
			//}			
		
		case ArrivalNotification:
			vorSubset.setNatyp("VIA");
			headSubset.setMsgName("VIA");
			MapNCTSArrivalNotificationToVIA mapNCTSArrivalNotificationToVIA = new MapNCTSArrivalNotificationToVIA(parser, vorSubset, headSubset);
			mapNCTSArrivalNotificationToVIA.setKidsHeader(kidsHeader);
			mapNCTSArrivalNotificationToVIA.setCommonFieldsDTO(commonFieldsDTO);
			fss = mapNCTSArrivalNotificationToVIA.getMessage();
			break;
		
		case UnloadingRemarks:
			vorSubset.setNatyp("VUR");
			headSubset.setMsgName("VUR");
			MapNCTSUnloadingRemarksToVUR mapNCTSUnloadingRemarksToVUR = new MapNCTSUnloadingRemarksToVUR(parser, vorSubset, headSubset);
			mapNCTSUnloadingRemarksToVUR.setKidsHeader(kidsHeader);
			mapNCTSUnloadingRemarksToVUR.setCommonFieldsDTO(commonFieldsDTO);
			fss = mapNCTSUnloadingRemarksToVUR.getMessage();
			break;
		
		case CustomsStatusNotification:  //EI20140206
			MapCustomsStatusNotificationToCSN mapCustStaNotToCsn = new MapCustomsStatusNotificationToCSN(parser, headSubset);
			mapCustStaNotToCsn.setKidsHeader(kidsHeader);
			mapCustStaNotToCsn.setCommonFieldsDTO(commonFieldsDTO);			
			fss = mapCustStaNotToCsn.getMessageWithKidsHeader();
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
