package com.kewill.kcx.component.mapping.countries.de.Import;

import javax.xml.stream.XMLEventReader;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.common.start.KidsToFssConverter;
import com.kewill.kcx.component.mapping.countries.de.Import.kids2fss.V64.MapImportDeclarationConfirmationToCON;
import com.kewill.kcx.component.mapping.countries.de.Import.kids2fss.V64.MapImportDeclarationToEZA;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;

/**
 * Module	  : Import<br>
 * Created	  : 12.09.2011<br>
 * Description: transformer called by KidsToFss to convert KIDS to ZABIS-FSS format  
 * 				of Import version 70.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class KidsToFss64Import extends KidsToFssConverter {

	public String readKids(XMLEventReader parser, TsVOR vorSubset,
			KidsHeader kidsHeader, CommonFieldsDTO commonFieldsDTO)
			throws Exception {
		vorSubset.setVersnr("06.40");
		//vorSubset.setModul("IM");
		vorSubset.setModul("ZB");
		
		String msg = kidsHeader.getMessageName();
		String fss = "";

		switch (EKidsImportMessages.valueOf(msg)) {
		case ImportDeclaration:
			vorSubset.setNatyp("EZA");
			MapImportDeclarationToEZA mapDecDatToEZA = new MapImportDeclarationToEZA(parser, vorSubset);
			mapDecDatToEZA.setKidsHeader(kidsHeader);
			mapDecDatToEZA.setCommonFieldsDTO(commonFieldsDTO);
			fss = mapDecDatToEZA.getMessage();
			break;
		case ImportDeclarationConfirmation:
			vorSubset.setNatyp("CON");
			MapImportDeclarationConfirmationToCON mapD2C = new MapImportDeclarationConfirmationToCON(parser, vorSubset);
			mapD2C.setKidsHeader(kidsHeader);
			mapD2C.setCommonFieldsDTO(commonFieldsDTO);
			fss = mapD2C.getMessage();
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
