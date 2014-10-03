package com.kewill.kcx.component.mapping.countries.de.aes;
/*
 * Function    : KidsToFssxtern.java
 * Titel       :
 * Date        : 05.09.2008
 * Author      : Kewill CSF / kron
 * Description  : Transformer called by external programs (i. e. not MULE) 
 *                to convert KIDS-Format to ZABIS FSS.
 * 			   : 
 * Parameters  : 

 * Changes 
 * -----------
 * Author      : krzoska
 * Date        : 31.03.2009
 * Label       :
 * Description:  copy of KidsToFss.java to KidsToFss60.java
 *
 * Author      : schmidt
 * Date        : 02.09.2009
 * Label       :
 * Description:  copy of KidsToFss60.java to KidsToFss60Export.java
 *
 */

import javax.xml.stream.XMLEventReader;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.common.start.KidsToFssConverter;
import com.kewill.kcx.component.mapping.countries.ch.aus.kids2fss.MapExpCanToCAU;
import com.kewill.kcx.component.mapping.countries.ch.aus.kids2fss.MapExpDatToCAN;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2fss.MapExpCanToACA;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2fss.V60.MapExpAmdToADN;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2fss.V60.MapExpDatToADA;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2fss.V60.MapExpEntToEAM;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2fss.V60.MapExpErlToAEP;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2fss.V60.MapExpExtToAXT;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;

/**
 * Modul		: KidsToFss60Export<br>
 * Erstellt		: 05.09.2008<br>
 * Description  : Transformer called by external programs (i. e. not MULE) 
 *                to convert KIDS-Format to ZABIS FSS.
 * Remark       : Copy of KidsToFss60.java
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class KidsToFss60Export extends KidsToFssConverter {

   	public String readKids(XMLEventReader parser, TsVOR vorSubset, KidsHeader kidsHeader, 
   	                                                CommonFieldsDTO commonFieldsDTO) throws Exception {
        vorSubset.setVersnr("6.0");
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
			case ConfirmInvestigation:  
				vorSubset.setNatyp("AXT");
				MapExpExtToAXT mapExpExtToAXT = new MapExpExtToAXT(parser, vorSubset);
				// to set messageID we need to set kidsHeader
				mapExpExtToAXT.setKidsHeader(kidsHeader);
				mapExpExtToAXT.setCommonFieldsDTO(commonFieldsDTO);
				xml = mapExpExtToAXT.getMessage();
				break;
			case Amendment:  
				vorSubset.setNatyp("ADN");
				MapExpAmdToADN mapExpAmdToADN = new MapExpAmdToADN(parser, vorSubset);
				// to set messageID we need to set kidsHeader
				mapExpAmdToADN.setKidsHeader(kidsHeader);
				mapExpAmdToADN.setCommonFieldsDTO(commonFieldsDTO);
				xml = mapExpAmdToADN.getMessage();
				break;					
		    default: throw new FssException("Unknown message type " + msg);
	    }
	    
        return xml;
	}
	
   	public void logAudit(KidsHeader kidsHeader, CommonFieldsDTO commonFieldDTO) {
   	    // Audit Log wird nicht benötigt.
   	}

}
