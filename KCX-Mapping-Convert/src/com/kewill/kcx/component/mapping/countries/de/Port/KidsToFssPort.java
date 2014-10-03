package com.kewill.kcx.component.mapping.countries.de.Port;

import javax.xml.stream.XMLEventReader;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.common.start.KidsToFssConverter;
import com.kewill.kcx.component.mapping.countries.de.Import.kids2fss.V64.MapImportDeclarationConfirmationToCON;
import com.kewill.kcx.component.mapping.countries.de.Port.kids2fss.MapBillOfLadingToBL;
import com.kewill.kcx.component.mapping.countries.de.Port.kids2fss.MapPortDeclarationToPOR;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;

/**
 * Module       : Port<br>
 * Created      : 21.10.2011<br>
 * Description: transformer called by KidsToFss to convert KIDS to ZABIS-FSS format  
 * 				of Import version 70.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class KidsToFssPort extends KidsToFssConverter {

	public String readKids(XMLEventReader parser, TsVOR vorSubset,
			KidsHeader kidsHeader, CommonFieldsDTO commonFieldsDTO)
			throws Exception {
		vorSubset.setVersnr("06.40");		
		vorSubset.setModul("ZP");
		
		String msg = kidsHeader.getMessageName();
		String fss = "";

		switch (EKidsPortMessages.valueOf(msg)) {
		case PortDeclaration:		
			vorSubset.setNatyp("POR");
			MapPortDeclarationToPOR mapDecToPor = new MapPortDeclarationToPOR(parser, vorSubset);
			mapDecToPor.setKidsHeader(kidsHeader);
			mapDecToPor.setCommonFieldsDTO(commonFieldsDTO);
			fss = mapDecToPor.getMessage();
			break;
		case PortDeclarationConfirmation:
			vorSubset.setNatyp("CON");
			MapImportDeclarationConfirmationToCON mapD2C = new MapImportDeclarationConfirmationToCON(parser, vorSubset);
			mapD2C.setKidsHeader(kidsHeader);
			mapD2C.setCommonFieldsDTO(commonFieldsDTO);
			fss = mapD2C.getMessage();
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
