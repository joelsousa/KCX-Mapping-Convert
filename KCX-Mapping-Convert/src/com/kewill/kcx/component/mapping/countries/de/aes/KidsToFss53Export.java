/*
 * Function    : KidsToFss.java
 * Titel       :
 * Date        : 05.09.2008
 * Author      : Kewill CSF / kron
 * Description  : Transformer called by external programs (i. e. not MULE) 
 *                to convert KIDS-Format to ZABIS FSS.
 *             : 
 * Parameters  : 

 * Changes 
 * -----------
 * Author      : krzoska
 * Date        : 31.03.2009
 * Label       :
 * Description:  copy of KidsToFss.java to KidsToFss53.java
 *
 */
package com.kewill.kcx.component.mapping.countries.de.aes;

import javax.xml.stream.XMLEventReader;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.common.start.KidsToFssConverter;
import com.kewill.kcx.component.mapping.countries.ch.aus.kids2fss.MapExpCanToCAU;
import com.kewill.kcx.component.mapping.countries.ch.aus.kids2fss.MapExpDatToCAN;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2fss.MapExpCanToACA;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2fss.MapExpDatToADA;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2fss.MapExpEntToEAM;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2fss.MapExpErlToAEP;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;

/**
 * Modul		: KidsToFss<br>
 * Erstellt		: 05.09.2008<br>
 * Description  : Transformer called by external programs (i. e. not MULE) 
 *                to convert KIDS-Format to ZABIS FSS.
 * 
 * @author kron
 * @version 1.0.00
 * 
 * 
 * 
 */
public class KidsToFss53Export extends KidsToFssConverter {

	public String readKids(XMLEventReader parser, TsVOR vorSubset, 
	                            KidsHeader kidsHeader, CommonFieldsDTO commonFieldsDTO) throws Exception {
        vorSubset.setVersnr("5.0");
		// bis hierhin aus DB !!
		
		String msg = kidsHeader.getMessageName();
		String xml = "";
		switch (EKidsMessages.valueOf(msg)) {
			case Cancellation:  
				if (kidsHeader.getCountryCode().equalsIgnoreCase("CH")) {
					vorSubset.setNatyp("CAU");
					MapExpCanToCAU mapExpCanToCAU = new MapExpCanToCAU(parser, vorSubset);
					// to set messageID we need to set kidsHeader
					mapExpCanToCAU.setKidsHeader(kidsHeader);
                    mapExpCanToCAU.setCommonFieldsDTO(commonFieldsDTO);
					xml = mapExpCanToCAU.getMessage();
				} else {
					vorSubset.setNatyp("ACA");
					MapExpCanToACA mapExpCanToACA = new MapExpCanToACA(parser, vorSubset);
					// to set messageID we need to set kidsHeader
					mapExpCanToACA.setKidsHeader(kidsHeader);
					mapExpCanToACA.setCommonFieldsDTO(commonFieldsDTO);
					xml = mapExpCanToACA.getMessage();
				}
				break;
			case ExportDeclaration:  
				if (kidsHeader.getCountryCode().equalsIgnoreCase("CH")) {
					vorSubset.setNatyp("CAN");
					MapExpDatToCAN mapExpCanToCAN = new MapExpDatToCAN(parser, vorSubset);
					// to set messageID we need to set kidsHeader
					mapExpCanToCAN.setKidsHeader(kidsHeader);
					mapExpCanToCAN.setCommonFieldsDTO(commonFieldsDTO);
					xml = mapExpCanToCAN.getMessage();
				} else {
                    vorSubset.setNatyp("ADA");
					MapExpDatToADA mapExpDatToADA = new MapExpDatToADA(parser, vorSubset);
					// to set messageID we need to set kidsHeader
					mapExpDatToADA.setKidsHeader(kidsHeader);
					mapExpDatToADA.setCommonFieldsDTO(commonFieldsDTO);
					xml = mapExpDatToADA.getMessage();
				}
				break;					
			case ManualTermination: 
				vorSubset.setNatyp("AEP");
				MapExpErlToAEP mapExpErlToAEP = new MapExpErlToAEP(parser, vorSubset);
				// to set messageID we need to set kidsHeader
				mapExpErlToAEP.setKidsHeader(kidsHeader);
				mapExpErlToAEP.setCommonFieldsDTO(commonFieldsDTO);
				xml = mapExpErlToAEP.getMessage();
				break;
			case Completion:  
				vorSubset.setNatyp("EAM");
				MapExpEntToEAM mapExpEntToEAM = new MapExpEntToEAM(parser, vorSubset);
				// to set messageID we need to set kidsHeader
				mapExpEntToEAM.setKidsHeader(kidsHeader);
				mapExpEntToEAM.setCommonFieldsDTO(commonFieldsDTO);
				xml = mapExpEntToEAM.getMessage();
				break;									
		    default: throw new FssException("Unknown message type " + msg);
	    }
	    
        return xml;
	}
	
    public void logAudit(KidsHeader kidsHeader, CommonFieldsDTO commonFieldDTO) {
        // Audit Log wird nicht benötigt.
    }
	
}
