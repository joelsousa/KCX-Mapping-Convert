package com.kewill.kcx.component.mapping.countries.de.ncts;

import javax.xml.stream.XMLEventReader;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.common.start.KidsToFssConverter;
import com.kewill.kcx.component.mapping.countries.de.ncts.kids2fss.v62.MapNCTSArrivalNotificationToVIA;
import com.kewill.kcx.component.mapping.countries.de.ncts.kids2fss.v62.MapNCTSUnloadingPermissionToVPH;
import com.kewill.kcx.component.mapping.countries.de.ncts.kids2fss.v62.MapNCTSArrivalRejectionToVFI;
import com.kewill.kcx.component.mapping.countries.de.ncts.kids2fss.v62.MapNCTSDeclarationRejectedToVFO;
import com.kewill.kcx.component.mapping.countries.de.ncts.kids2fss.v62.MapNCTSDeclarationToVAN;
import com.kewill.kcx.component.mapping.countries.de.ncts.kids2fss.v62.MapNCTSMRNAllocatedToVMR;
import com.kewill.kcx.component.mapping.countries.de.ncts.kids2fss.v62.MapNCTSUnloadingRemarkRejectionsToVFI;
import com.kewill.kcx.component.mapping.countries.de.ncts.kids2fss.v62.MapNCTSUnloadingRemarksToVUR;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;

/**
 * Module : KidsToFss62NCTS<br>
 * Date Created : 08.31.2010<br>
 * Description : Transformer called by external programs (i. e. not MULE) to
 * convert KIDS-Format to ZABIS FSS.
 * 
 * @author Frederick T
 * @version 1.0.00
 */
public class KidsToFss62NCTS extends KidsToFssConverter {

	public String readKids(XMLEventReader parser, TsVOR vorSubset,
			KidsHeader kidsHeader, CommonFieldsDTO commonFieldsDTO)
			throws Exception {
		vorSubset.setVersnr("06.20");
		vorSubset.setModul("VE");
		
		String msg = kidsHeader.getMessageName();
		String fss = "";

		switch (EKidsNCTSMessages.valueOf(msg)) {
		case NCTSDeclaration:
			vorSubset.setNatyp("VAN");
			MapNCTSDeclarationToVAN mapDecDatToVAN = new MapNCTSDeclarationToVAN(
					parser, vorSubset);
			mapDecDatToVAN.setKidsHeader(kidsHeader);
			mapDecDatToVAN.setCommonFieldsDTO(commonFieldsDTO);
			fss = mapDecDatToVAN.getMessage();
			break;
		
		case NCTSDeclarationRejected:
			vorSubset.setNatyp("VFO");
			MapNCTSDeclarationRejectedToVFO mapNCTSDeclarationRejectedToVFO	= 
				new MapNCTSDeclarationRejectedToVFO(parser, vorSubset);
			mapNCTSDeclarationRejectedToVFO.setKidsHeader(kidsHeader);
			mapNCTSDeclarationRejectedToVFO.setCommonFieldsDTO(commonFieldsDTO);
			fss	= mapNCTSDeclarationRejectedToVFO.getMessage();
			break;

		case ArrivalRejection:
			vorSubset.setNatyp("E_ARR_REJ");
			MapNCTSArrivalRejectionToVFI mapNCTSArrivalRejectionToVFI = new MapNCTSArrivalRejectionToVFI(
					parser, vorSubset);
			mapNCTSArrivalRejectionToVFI.setKidsHeader(kidsHeader);
			mapNCTSArrivalRejectionToVFI.setCommonFieldsDTO(commonFieldsDTO);
			fss = mapNCTSArrivalRejectionToVFI.getMessage();
			break;

		case UnloadingRemarksRejection:
			vorSubset.setNatyp("E_ULD_REJ");
			MapNCTSUnloadingRemarkRejectionsToVFI mapNCTSUnloadingRemarkRejectionsToVFI = 
				new MapNCTSUnloadingRemarkRejectionsToVFI(parser, vorSubset);
			mapNCTSUnloadingRemarkRejectionsToVFI.setKidsHeader(kidsHeader);
			mapNCTSUnloadingRemarkRejectionsToVFI
					.setCommonFieldsDTO(commonFieldsDTO);
			fss = mapNCTSUnloadingRemarkRejectionsToVFI.getMessage();
			break;

		case MRNAllocated:
			vorSubset.setNatyp("VMR");
			MapNCTSMRNAllocatedToVMR mapNCTSMRNAllocatedToVMR = new MapNCTSMRNAllocatedToVMR(
					parser, vorSubset);
			mapNCTSMRNAllocatedToVMR.setKidsHeader(kidsHeader);
			mapNCTSMRNAllocatedToVMR.setCommonFieldsDTO(commonFieldsDTO);
			fss = mapNCTSMRNAllocatedToVMR.getMessage();
			break;
		
		case ArrivalNotification:
			vorSubset.setNatyp("VIA");
			MapNCTSArrivalNotificationToVIA mapNCTSArrivalNotificationToVIA = 
				new MapNCTSArrivalNotificationToVIA(
					parser, vorSubset);
			mapNCTSArrivalNotificationToVIA.setKidsHeader(kidsHeader);
			mapNCTSArrivalNotificationToVIA.setCommonFieldsDTO(commonFieldsDTO);
			fss = mapNCTSArrivalNotificationToVIA.getMessage();
			break;
		
		case UnloadingRemarks:
			vorSubset.setNatyp("VUR");
			MapNCTSUnloadingRemarksToVUR mapNCTSUnloadingRemarksToVUR = new MapNCTSUnloadingRemarksToVUR(
					parser, vorSubset);
			mapNCTSUnloadingRemarksToVUR.setKidsHeader(kidsHeader);
			mapNCTSUnloadingRemarksToVUR.setCommonFieldsDTO(commonFieldsDTO);
			fss = mapNCTSUnloadingRemarksToVUR.getMessage();
			break;
		
		case UnloadingPermission:
			vorSubset.setNatyp("VPH");
			MapNCTSUnloadingPermissionToVPH mapNCTSUnloadingPermissionToVPH = 
				new MapNCTSUnloadingPermissionToVPH(parser, vorSubset);
			mapNCTSUnloadingPermissionToVPH.setKidsHeader(kidsHeader);
			mapNCTSUnloadingPermissionToVPH.setCommonFieldsDTO(commonFieldsDTO);
			fss = mapNCTSUnloadingPermissionToVPH.getMessage();

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
