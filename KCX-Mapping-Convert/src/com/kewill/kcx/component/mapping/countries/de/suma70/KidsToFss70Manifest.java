package com.kewill.kcx.component.mapping.countries.de.suma70;

import javax.xml.stream.XMLEventReader;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.common.start.KidsToFssConverter;
import com.kewill.kcx.component.mapping.countries.de.suma70.kids2fss.MapCustomsStatusNotificationToCSN;
import com.kewill.kcx.component.mapping.countries.de.suma70.kids2fss.MapNotificationOfPresentationConfirmedToSBK;
import com.kewill.kcx.component.mapping.countries.de.suma70.kids2fss.MapNotificationOfPresentationToSUK;
import com.kewill.kcx.component.mapping.countries.de.suma70.kids2fss.MapReExportToSAK;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;

/**
 * Module 		: Manifest<br>
 * Created 		: 31.05.2013<br>
 * Description 	: Transformer of Kids into FSS.
 * 
 * @author krzoska
 * @version 2.0.00
 */
public class KidsToFss70Manifest extends KidsToFssConverter {

	public String readKids(XMLEventReader parser, TsVOR vorSubset, TsHead headSubset,
			KidsHeader kidsHeader, CommonFieldsDTO commonFieldsDTO)
			throws Exception {
        vorSubset.setVersnr("7.0");
        vorSubset.setModul("SU");
        headSubset.setVersion("07000");
        headSubset.setModul("SU");
		
		String msg = kidsHeader.getMessageName();
		String fss = "";

		switch (EKidsManifestMessages.valueOf(msg)) {
		case NotificationOfPresentation:			
			headSubset.setMsgName("SUK");
			MapNotificationOfPresentationToSUK mapNPToSuk = new MapNotificationOfPresentationToSUK(parser, headSubset);
			mapNPToSuk.setKidsHeader(kidsHeader);
			mapNPToSuk.setCommonFieldsDTO(commonFieldsDTO);
			fss = mapNPToSuk.getMessage();
			break;		
			
		case NotificationOfPresentationConfirmed:			
			headSubset.setMsgName("SBK");
			MapNotificationOfPresentationConfirmedToSBK mapNPCSbk = new MapNotificationOfPresentationConfirmedToSBK(parser, headSubset);
			mapNPCSbk.setKidsHeader(kidsHeader);
			mapNPCSbk.setCommonFieldsDTO(commonFieldsDTO);
			fss = mapNPCSbk.getMessage();
			break;
			
		case ReExport:		
			headSubset.setMsgName("SAK");
			MapReExportToSAK mapReToSak = new MapReExportToSAK(parser, headSubset);
			mapReToSak.setKidsHeader(kidsHeader);
			mapReToSak.setCommonFieldsDTO(commonFieldsDTO);
			fss = mapReToSak.getMessage();
			break;	
			
		case CustomsStatusNotification:
			MapCustomsStatusNotificationToCSN mapCustStaNotToCsn = new MapCustomsStatusNotificationToCSN(parser, headSubset);
			mapCustStaNotToCsn.setKidsHeader(kidsHeader);
			mapCustStaNotToCsn.setCommonFieldsDTO(commonFieldsDTO);
			//EI20140120: fss = mapCustStaNotToCsn.getMessage();
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
