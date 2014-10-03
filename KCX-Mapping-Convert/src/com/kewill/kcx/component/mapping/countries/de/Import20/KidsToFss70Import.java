package com.kewill.kcx.component.mapping.countries.de.Import20;

import javax.xml.stream.XMLEventReader;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.common.start.KidsToFssConverter;
import com.kewill.kcx.component.mapping.countries.ch.Import20.kids2fss.MapImportDeclarationToCKK;
import com.kewill.kcx.component.mapping.countries.de.Import.EKidsImportMessages;
import com.kewill.kcx.component.mapping.countries.de.Import20.kids2fss.MapImportDeclarationConfirmationToCON;
import com.kewill.kcx.component.mapping.countries.de.Import20.kids2fss.MapImportDeclarationToEZA;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;

/**
 * Module	  : Import 20<br>
 * Created	  : 12.11.2012<br>
 * Description: transformer called by KidsToFss to convert KIDS to ZABIS-FSS format  
 * 				of Import version 70.
 * 
 * @author iwaniuk
 * @version 2.0.00
 */
public class KidsToFss70Import extends KidsToFssConverter {

	public String readKids(XMLEventReader parser, TsVOR vorSubset, TsHead headSubset,
			KidsHeader kidsHeader, CommonFieldsDTO commonFieldsDTO)
			throws Exception {
		vorSubset.setVersnr("07.00");		
		 
		if (kidsHeader != null && 
			kidsHeader.getCountryCode() != null && kidsHeader.getCountryCode().equals("CH")) {
			vorSubset.setModul("ZB");	  //TODO heiss es auf fuer CH "ZB" ???
		} else {
			vorSubset.setModul("ZB");
		}
		headSubset.setVersion("07000");              //EI20121107
		headSubset.setModul(vorSubset.getModul());   //EI20121121
		
		String msg = kidsHeader.getMessageName();
		String fss = "";

		switch (EKidsImportMessages.valueOf(msg)) {
		
		case ImportDeclaration:		
			if (kidsHeader.getCountryCode().equalsIgnoreCase("CH")) {
				vorSubset.setNatyp("CKK");
				headSubset.setMsgName("CKK");
				//MapImportDeclarationToCKK mapImpDeclToCKK = new MapImportDeclarationToCKK(parser, vorSubset);	
				MapImportDeclarationToCKK mapImpDeclToCKK = new MapImportDeclarationToCKK(parser, vorSubset, headSubset);
				mapImpDeclToCKK.setKidsHeader(kidsHeader);
				mapImpDeclToCKK.setCommonFieldsDTO(commonFieldsDTO);
				fss = mapImpDeclToCKK.getMessage();
				break;
			} else {
				vorSubset.setNatyp("EZA");
				headSubset.setMsgName("EZA");
				MapImportDeclarationToEZA mapImpDeclToEZA = new MapImportDeclarationToEZA(parser, vorSubset, headSubset);
				mapImpDeclToEZA.setKidsHeader(kidsHeader);
				mapImpDeclToEZA.setCommonFieldsDTO(commonFieldsDTO);
				fss = mapImpDeclToEZA.getMessage();			
				break;
			}
		case ImportDeclarationConfirmation:			
			vorSubset.setNatyp("CON");			
			headSubset.setMsgName("CON");
			MapImportDeclarationConfirmationToCON mapD2C = new MapImportDeclarationConfirmationToCON(parser, vorSubset, headSubset);
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
