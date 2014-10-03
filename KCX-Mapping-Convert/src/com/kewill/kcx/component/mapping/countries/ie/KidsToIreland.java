package com.kewill.kcx.component.mapping.countries.ie;

import javax.xml.stream.XMLEventReader;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.ie.kids2ie.MapExportDeclarationKI;
import com.kewill.kcx.component.mapping.countries.ie.kids2ie.MapImportDeclarationKI;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;

/**
 * Module : KidsToIreland<br>
 * Created : 23.05.2014<br>
 * Description : Converts KIDS-Format to Ireland messages format.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class KidsToIreland {
	private String msgCode = "";

	public String readKids(XMLEventReader parser, String encoding,
			KidsHeader kidsHeader, IrelandHeader irelandHeader, CommonFieldsDTO commonFieldsDTO)
			throws Exception {

		String xml = null;
		String msg = kidsHeader.getMessageName();

		switch (EIrelandMessages.valueOf(msg)) {
		case ExportDeclaration:			
			MapExportDeclarationKI mapExpDeclarationKI	= new MapExportDeclarationKI(parser, encoding);
			mapExpDeclarationKI.setKidsHeader(kidsHeader);
			mapExpDeclarationKI.setIrelandHeader(irelandHeader);     
			mapExpDeclarationKI.setCommonFieldsDTO(commonFieldsDTO);
			xml	= mapExpDeclarationKI.getMessage();
			break;	
			
		case ImportDeclaration:  			
			MapImportDeclarationKI mapImpDeclarationKI = new MapImportDeclarationKI(parser, encoding);
			mapImpDeclarationKI.setKidsHeader(kidsHeader);
			mapImpDeclarationKI.setIrelandHeader(irelandHeader);     
			mapImpDeclarationKI.setCommonFieldsDTO(commonFieldsDTO);
			xml = mapImpDeclarationKI.getMessage();
			break;	
			
		case NCTSDeclaration:			
			break;
		
		default:
			throw new FssException("Unknown message type " + msg);
		}

		return xml;
	}

}
