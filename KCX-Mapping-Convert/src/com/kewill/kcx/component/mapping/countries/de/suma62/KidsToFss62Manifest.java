package com.kewill.kcx.component.mapping.countries.de.suma62;

import javax.xml.stream.XMLEventReader;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.common.start.KidsToFssConverter;
import com.kewill.kcx.component.mapping.countries.de.suma62.kids2fss.MapNotificationOfPresentationToSUK;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;

/**
 * Module 		: Manifest<br>
 * Created 		: 21.12.2012<br>
 * Description 	: Transformer of Kids into FSS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class KidsToFss62Manifest extends KidsToFssConverter {

	public String readKids(XMLEventReader parser, TsVOR vorSubset,
			KidsHeader kidsHeader, CommonFieldsDTO commonFieldsDTO)
			throws Exception {
		vorSubset.setVersnr("06.20");
		vorSubset.setModul("SU");
		
		String msg = kidsHeader.getMessageName();
		String fss = "";

		switch (EKidsManifestMessages.valueOf(msg)) {
		case NotificationOfPresentation:
			vorSubset.setNatyp("SUK");
			MapNotificationOfPresentationToSUK mapDecDatToSuk = new MapNotificationOfPresentationToSUK(
					parser, vorSubset);
			mapDecDatToSuk.setKidsHeader(kidsHeader);
			mapDecDatToSuk.setCommonFieldsDTO(commonFieldsDTO);
			fss = mapDecDatToSuk.getMessage();
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
