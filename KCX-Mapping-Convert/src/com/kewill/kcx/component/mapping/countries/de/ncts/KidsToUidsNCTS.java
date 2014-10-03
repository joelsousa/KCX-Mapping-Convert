package com.kewill.kcx.component.mapping.countries.de.ncts;

import javax.xml.stream.XMLEventReader;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2uids.MapExpNckKU;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2uids.MapFailureKU;
import com.kewill.kcx.component.mapping.countries.de.ncts.kids2uids.MapNCTSArrivalNotificationKU;
import com.kewill.kcx.component.mapping.countries.de.ncts.kids2uids.MapNCTSArrivalRejectionKU;
import com.kewill.kcx.component.mapping.countries.de.ncts.kids2uids.MapNCTSDeclarationKU;
import com.kewill.kcx.component.mapping.countries.de.ncts.kids2uids.MapNCTSDeclarationRejectedKU;
import com.kewill.kcx.component.mapping.countries.de.ncts.kids2uids.MapNCTSMRNAllocatedKU;
import com.kewill.kcx.component.mapping.countries.de.ncts.kids2uids.MapNCTSUnloadingPermissionKU;
import com.kewill.kcx.component.mapping.countries.de.ncts.kids2uids.MapNCTSUnloadingRemarksKU;
import com.kewill.kcx.component.mapping.countries.de.ncts.kids2uids.MapNCTSUnloadingRemarksRejectionKU;
import com.kewill.kcx.component.mapping.countries.de.ncts.kids2uids.MapNCTSWriteOffNotificationKU;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlPdfTgzIntern2;

/*
 * Function    : KidsToUids.java
 * Title       :
 * Date        : 23.08.2010
 * Author      : Frederick T
 * Description : transformer called to convert KIDS-Format to UIDS messages
 * 			   : 
 * Parameters  : 
 */

/**
 * Module		: KidsToUidsNCTS
 * Created		: 23.08.2010
 * Description	: transformer to convert NCTS KIDS-Format to UIDS messages.
 * 
 * @author Frederick T
 * @version 1.0.00
 */
public class KidsToUidsNCTS {

	public Object readKids(XMLEventReader parser, String encoding, 
			KidsHeader kidsHeader, CommonFieldsDTO commonFieldsDTO) throws Exception {
		
		String xml = "";
        Object tgz = null;
        boolean returnTgz = false;
	    String msg = kidsHeader.getMessageName();
	    
	    EDirections direction   = commonFieldsDTO.getDirection();
	    
	    if (direction == EDirections.CustomerToCountry) {
            kidsHeader.setDirection("FROM_CUSTOMER");
        } else {
            kidsHeader.setDirection("TO_CUSTOMER");
        }
	    
        switch (EKidsNCTSMessages.valueOf(msg)) {
        	case ArrivalNotification:
        		MapNCTSArrivalNotificationKU mapNCTSArrivalNotificationKU = 
        			new MapNCTSArrivalNotificationKU(parser, encoding);
        		mapNCTSArrivalNotificationKU.setKidsHeader(kidsHeader);
        		mapNCTSArrivalNotificationKU.setCommonFieldsDTO(commonFieldsDTO);
        		xml = mapNCTSArrivalNotificationKU.getMessage();
        		break;
        		
        	case ArrivalRejection:
        		MapNCTSArrivalRejectionKU mapNCTSArrivalRejectionKU	= 
        			new MapNCTSArrivalRejectionKU(parser, encoding);
        		mapNCTSArrivalRejectionKU.setKidsHeader(kidsHeader);
        		mapNCTSArrivalRejectionKU.setCommonFieldsDTO(commonFieldsDTO);
        		xml	= mapNCTSArrivalRejectionKU.getMessage();
        		break;
        		
        	case NCTSDeclarationRejected:
        		MapNCTSDeclarationRejectedKU mapNCTSDeclarationRejectedKU = 
        			new MapNCTSDeclarationRejectedKU(parser, encoding);
        		mapNCTSDeclarationRejectedKU.setKidsHeader(kidsHeader);
        		mapNCTSDeclarationRejectedKU.setCommonFieldsDTO(commonFieldsDTO);
        		xml = mapNCTSDeclarationRejectedKU.getMessage();
        		break;
        	
        	case MRNAllocated:
        		MapNCTSMRNAllocatedKU mapNCTSMRNAllocatedKU = new MapNCTSMRNAllocatedKU(parser, encoding);
        		mapNCTSMRNAllocatedKU.setKidsHeader(kidsHeader);
        		mapNCTSMRNAllocatedKU.setCommonFieldsDTO(commonFieldsDTO);
        		xml = mapNCTSMRNAllocatedKU.getMessage();
        		if (commonFieldsDTO.isPdfTgz() && commonFieldsDTO.pdfExists()) {
     			   Utils.log("es wird ein tgz generiert!!!");
     			   returnTgz = true;
//                    tgz = XmlPdfTgzIntern.createTgz(xml, Config.getPdfpath(), commonFieldsDTO.getFilename());
                    tgz = XmlPdfTgzIntern2.createTgz(xml, Config.getPdfpath(), commonFieldsDTO.getFilename());
     			   commonFieldsDTO.updateFilename();
     		   } 
        		break;
        		
        	case NCTSWriteOffNotification:
        		MapNCTSWriteOffNotificationKU mapNCTSWriteOffNotificationKU = 
        			new MapNCTSWriteOffNotificationKU(parser, encoding);
        		mapNCTSWriteOffNotificationKU.setKidsHeader(kidsHeader);
        		mapNCTSWriteOffNotificationKU.setCommonFieldsDTO(commonFieldsDTO);
        		xml = mapNCTSWriteOffNotificationKU.getMessage();
        		break;
        		
        	case UnloadingPermission:
        		MapNCTSUnloadingPermissionKU mapNCTSUnloadingPermissionKU = 
        			new MapNCTSUnloadingPermissionKU(parser, encoding);
        		mapNCTSUnloadingPermissionKU.setKidsHeader(kidsHeader);
        		mapNCTSUnloadingPermissionKU.setCommonFieldsDTO(commonFieldsDTO);
        		xml = mapNCTSUnloadingPermissionKU.getMessage();
        		break;
        		
        	case UnloadingRemarksRejection:
        		MapNCTSUnloadingRemarksRejectionKU mapNCTSUnloadingRemarksRejectionKU	= 
        			new MapNCTSUnloadingRemarksRejectionKU(parser, encoding);
        		mapNCTSUnloadingRemarksRejectionKU.setKidsHeader(kidsHeader);
        		mapNCTSUnloadingRemarksRejectionKU.setCommonFieldsDTO(commonFieldsDTO);
        		xml	= mapNCTSUnloadingRemarksRejectionKU.getMessage();
        		break;
        		
        	case UnloadingRemarks:
        		MapNCTSUnloadingRemarksKU mapNCTSUnloadingRemarksKU = 
        			new MapNCTSUnloadingRemarksKU(parser, encoding);
        		mapNCTSUnloadingRemarksKU.setKidsHeader(kidsHeader);
        		mapNCTSUnloadingRemarksKU.setCommonFieldsDTO(commonFieldsDTO);
        		xml = mapNCTSUnloadingRemarksKU.getMessage();
        		break;
        		
        	case NCTSDeclaration:
        		MapNCTSDeclarationKU mapDeclarationKU = 
        			new MapNCTSDeclarationKU(parser, encoding);
        		mapDeclarationKU.setKidsHeader(kidsHeader);
        		mapDeclarationKU.setCommonFieldsDTO(commonFieldsDTO);
        		xml = mapDeclarationKU.getMessage();
        		break;
        		
            case localAppResult:
         	    MapFailureKU mapFailureKU  = new MapFailureKU(parser, encoding);
         	    mapFailureKU.setKidsHeader(kidsHeader);
         	    mapFailureKU.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapFailureKU.getMessage();
                break;
                
            	
            case InternalStatusInformation:  //AK20110513
            	MapExpNckKU mapExpNckKU = new MapExpNckKU(parser, encoding);
                mapExpNckKU.setKidsHeader(kidsHeader);
                mapExpNckKU.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapExpNckKU.getMessage();
                break;               
        		
        	default: throw new FssException("Unknown message type " + msg);
        }
        
        if (returnTgz) {
        	return tgz;
        } else {
        	return xml;
        }
	}
}
