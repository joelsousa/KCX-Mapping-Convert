package com.kewill.kcx.component.mapping.countries.de.aes21;

import javax.xml.stream.XMLEventReader;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.common.start.KidsToFssConverter;
import com.kewill.kcx.component.mapping.countries.ch.aus20.kids2fss.MapExpCanToCAU;
import com.kewill.kcx.component.mapping.countries.ch.aus20.kids2fss.MapExpDatToCAN;
import com.kewill.kcx.component.mapping.countries.de.aes.EKidsMessages;
import com.kewill.kcx.component.mapping.countries.de.aes21.kids2fss.MapExpAmdToADN;
import com.kewill.kcx.component.mapping.countries.de.aes21.kids2fss.MapExpCanToACA;
import com.kewill.kcx.component.mapping.countries.de.aes21.kids2fss.MapExpDatToADA;
import com.kewill.kcx.component.mapping.countries.de.aes21.kids2fss.MapExpEntToEAM;
import com.kewill.kcx.component.mapping.countries.de.aes21.kids2fss.MapExpErlToAEP;
import com.kewill.kcx.component.mapping.countries.de.aes21.kids2fss.MapExpExtToAXT;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;

/**
 * Module		: Export/aes<br>
 * Created		: 25.07.2012<br>
 * Description  : Transformer called by external programs (i. e. not MULE) 
 *                to convert KIDS-Format to ZABIS FSS V70.
 * 
 * @author iwaniuk
 * @version 2.1.00
 */
public class KidsToFss70Export extends KidsToFssConverter {

   	public String readKids(XMLEventReader parser, TsVOR vorSubset, TsHead headSubset, KidsHeader kidsHeader, 
   	                                                CommonFieldsDTO commonFieldsDTO) throws Exception {
        vorSubset.setVersnr("7.0");
        headSubset.setVersion("07000");     //EI20121107
		// bis hierhin aus DB !!
		
		String msg = kidsHeader.getMessageName();
		String fss = "";
		switch (EKidsMessages.valueOf(msg)) {
			case Cancellation:         
				if (kidsHeader.getCountryCode().equalsIgnoreCase("CH")) {
					vorSubset.setNatyp("CAU");
					headSubset.setMsgName("CAU");
					MapExpCanToCAU mapExpCanToCAU = new MapExpCanToCAU(parser, vorSubset, headSubset);					
					mapExpCanToCAU.setKidsHeader(kidsHeader);
					mapExpCanToCAU.setCommonFieldsDTO(commonFieldsDTO);
					fss = mapExpCanToCAU.getMessage();
				} else {
					vorSubset.setNatyp("ACA");         //laut aes-fss-70.doc ACA ist unveraendert
					headSubset.setMsgName("ACA");
					MapExpCanToACA mapExpCanToACA = new MapExpCanToACA(parser, vorSubset, headSubset);					
					mapExpCanToACA.setKidsHeader(kidsHeader);
					mapExpCanToACA.setCommonFieldsDTO(commonFieldsDTO);
					fss = mapExpCanToACA.getMessage();
				}
				break;
			case ExportDeclaration:  
				if (kidsHeader.getCountryCode().equalsIgnoreCase("CH")) {
					vorSubset.setNatyp("CAN");
					headSubset.setMsgName("CAN");
					MapExpDatToCAN mapExpCanToCAN = new MapExpDatToCAN(parser, vorSubset, headSubset);					
					mapExpCanToCAN.setKidsHeader(kidsHeader);
					mapExpCanToCAN.setCommonFieldsDTO(commonFieldsDTO);
					fss = mapExpCanToCAN.getMessage();
				} else {
                    vorSubset.setNatyp("ADA");
                    headSubset.setMsgName("ADA");
					MapExpDatToADA mapExpDatToADA = new MapExpDatToADA(parser, vorSubset, headSubset);					
					mapExpDatToADA.setKidsHeader(kidsHeader);
					mapExpDatToADA.setCommonFieldsDTO(commonFieldsDTO);
					fss = mapExpDatToADA.getMessage();
				}
				break;					
			case ManualTermination:       //laut aes-fss-70.doc AEP ist unveraendert
				vorSubset.setNatyp("AEP");
				headSubset.setMsgName("AEP");
				MapExpErlToAEP mapExpErlToAEP = new MapExpErlToAEP(parser, vorSubset, headSubset);				
				mapExpErlToAEP.setKidsHeader(kidsHeader);
				mapExpErlToAEP.setCommonFieldsDTO(commonFieldsDTO);
				fss = mapExpErlToAEP.getMessage();
				break;
			case Completion:  
				vorSubset.setNatyp("EAM");
				headSubset.setMsgName("EAM");
				MapExpEntToEAM mapExpEntToEAM = new MapExpEntToEAM(parser, vorSubset, headSubset);
				// to set messageID we need to set kidsHeader
				mapExpEntToEAM.setKidsHeader(kidsHeader);
				mapExpEntToEAM.setCommonFieldsDTO(commonFieldsDTO);
				fss = mapExpEntToEAM.getMessage();
				break;									
			case ConfirmInvestigation:       //laut aes-fss-70.doc AXT ist unveraendert
				vorSubset.setNatyp("AXT");
				headSubset.setMsgName("AXT");
				MapExpExtToAXT mapExpExtToAXT = new MapExpExtToAXT(parser, vorSubset, headSubset);
				// to set messageID we need to set kidsHeader
				mapExpExtToAXT.setKidsHeader(kidsHeader);
				mapExpExtToAXT.setCommonFieldsDTO(commonFieldsDTO);
				fss = mapExpExtToAXT.getMessage();
				break;
			case Amendment:  
				vorSubset.setNatyp("ADN");
				headSubset.setMsgName("ADN");
				MapExpAmdToADN mapExpAmdToADN = new MapExpAmdToADN(parser, vorSubset, headSubset);
				// to set messageID we need to set kidsHeader
				mapExpAmdToADN.setKidsHeader(kidsHeader);
				mapExpAmdToADN.setCommonFieldsDTO(commonFieldsDTO);
				fss = mapExpAmdToADN.getMessage();
				break;					
		    default: throw new FssException("Unknown message type " + msg);
	    }
	    
        return fss;
	}
	
   	public void logAudit(KidsHeader kidsHeader, CommonFieldsDTO commonFieldDTO) {
   	    // Audit Log wird nicht benötigt.
   	}

}
