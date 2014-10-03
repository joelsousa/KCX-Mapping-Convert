package com.kewill.kcx.component.mapping.countries.nl.aes;

import javax.xml.stream.XMLEventReader;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.de.aes.EKidsMessages;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2kids.MapExpCanKK;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2kids.MapExpEntKK;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2kids.MapExpErlKK;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2kids.MapExpExtKK;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2kids.MapExpIndKK;
import com.kewill.kcx.component.mapping.countries.nl.aes.kids2kidsNl.MapExpDatToExpdatNl;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;

/**
 * Modul : KidsToKids<br>
 * Erstellt : 06.03.2009<br>
 * Beschreibung : Transformer called by Mule to convert KIDS-Format to KIDS
 * messages.
 * 
 * 				  This mapping is necessary for i-customs online because of the following 
 * 				  variations of KIDS:
 * 				  1. CustomsOfficeExport in KIDS 8 chars, in i-customs online the last 3 chars used
 * 				  2. HouseNumber in Address has to be filled if Street is filled, default "0"  
 * @author messer
 * @version 1.0.00
 * 
 * * Changes 
 * ------------
 * Author      : Christine Kron
 * Date        : Juli 2010
 * Label       :
 * Description : setting CommonFieldDTO - used for writing Header in correct Version deposited in DB
 * 				 keep CustomsOfficeExport unchanged 
 */
public class KidsToKidsExportNl  {
    public String readKids(XMLEventReader parser, KidsHeader kidsHeader, CommonFieldsDTO commonFieldsDTO)
                                                                                    throws Exception {
		
        String xml = null;
        String msg = kidsHeader.getMessageName();
        kidsHeader.setCommonFieldsDTO(commonFieldsDTO);
		switch (EKidsMessages.valueOf(msg)) {
		case Cancellation:
			MapExpCanKK mapExpCanKK = new MapExpCanKK(parser, "UTF-8");
			mapExpCanKK.setKidsHeader(kidsHeader);
			xml = mapExpCanKK.getMessage();
			break;
		case ExportDeclaration:
			MapExpDatToExpdatNl mapExpDatKK = new MapExpDatToExpdatNl(parser, "UTF-8");
			mapExpDatKK.setKidsHeader(kidsHeader);
			xml = mapExpDatKK.getMessage();
			break;
		case Completion:
			MapExpEntKK mapExpEntKK = new MapExpEntKK(parser, "UTF-8");
			mapExpEntKK.setKidsHeader(kidsHeader);
			xml = mapExpEntKK.getMessage();
			break;
		case ManualTermination:
			MapExpErlKK mapExpErlKK = new MapExpErlKK(parser, "UTF-8");
			mapExpErlKK.setKidsHeader(kidsHeader);
			xml = mapExpErlKK.getMessage();
			break;
		case ConfirmInvestigation:
			MapExpExtKK mapExpExtKK = new MapExpExtKK(parser, "UTF-8");
			mapExpExtKK.setKidsHeader(kidsHeader);
			xml = mapExpExtKK.getMessage();
			break;
		case PreNotification:
			MapExpIndKK mapExpIndKK = new MapExpIndKK(parser, "UTF-8");
			mapExpIndKK.setKidsHeader(kidsHeader);
			xml = mapExpIndKK.getMessage();
			break;
		default:
			throw new FssException("Unknown message type " + msg);
		}
		
		return xml;

	}

}
