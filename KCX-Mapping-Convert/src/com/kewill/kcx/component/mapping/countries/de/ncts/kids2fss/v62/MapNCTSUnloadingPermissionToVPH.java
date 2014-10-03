/*
 * Function    : MapNCTSUnloadingPermissionToVPH.java
 * Date        : 31.08.2010
 * Author      : Kewill CSF / Elisabeth Iwaniuk
 * Description : Mapping of KIDS format of NCTSUnloadingPermission to FSS format VPH

 * Changes 
 * -----------
 * Author      : 
 * Date        : 
 * Description : 
 */

package com.kewill.kcx.component.mapping.countries.de.ncts.kids2fss.v62;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.SealNumber;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSUnloadingPermission;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSUnloadingPermissionPos;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Packages;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.PartyNCTS;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.SensitiveGoods;
import com.kewill.kcx.component.mapping.countries.common.SpecialMention;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.ncts.messages.MsgVPH;
import com.kewill.kcx.component.mapping.formats.fss.ncts.messages.MsgVPPPos;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVPA;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVPC;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVPD;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVPE;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVPH;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVPL;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVPP;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVPU;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVPV;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul		: MapNCTSUnloadingPermissionToVPH<br>
 * Erstellt		: 2010.09.03<br>
 * Beschreibung	: Mapping of KIDS format of NCTSUnloadingPermission to FSS format VPH.
 * 
 * @author Frederick T.
 * @version 6.0.00
 */
public class MapNCTSUnloadingPermissionToVPH extends KidsMessage {
	
	private MsgNCTSUnloadingPermission msgNCTSUnloadingPermission;
	private MsgVPH msgVPH;
	
	public MapNCTSUnloadingPermissionToVPH(XMLEventReader parser, TsVOR tsvor) throws XMLStreamException {		
		msgNCTSUnloadingPermission = new MsgNCTSUnloadingPermission(parser);
		msgVPH = new MsgVPH();
		msgVPH.setVorSubset(tsvor);
	}

	public String getMessage() {
    	String res = "";
    	MsgVPPPos msgVPPPos = null;
    	
        try {        
        	msgNCTSUnloadingPermission.parse(HeaderType.KIDS);                      
            getCommonFieldsDTO().setReferenceNumber(msgNCTSUnloadingPermission.getReferenceNumber());
            
            // read MessageID from KidsHeader.                                          
            msgVPH.getVorSubset().setMsgid(getKidsHeader().getMessageID());
            
            //Setting all Ts in MsgVPH
            msgVPH.setVphSubset(getVph());
            if (msgNCTSUnloadingPermission.getSealNumbersList() != null) {
            	for (SealNumber sealNumber : msgNCTSUnloadingPermission.getSealNumbersList()) {
            		msgVPH.addVpvSubsetList(getVpv(sealNumber));
            	}
            }        
        	if (msgNCTSUnloadingPermission.getConsignor() != null) {
        		msgVPH.setVpaConsignor(getVpa("CZ", msgNCTSUnloadingPermission.getConsignor()));
        	}        	
        	if (msgNCTSUnloadingPermission.getConsignee() != null) {
        		msgVPH.setVpaConsignee(getVpa("CN", msgNCTSUnloadingPermission.getConsignee()));
        	}        	
        	if (msgNCTSUnloadingPermission.getPrincipal() != null) {
        		msgVPH.setVpaPrincipal(getVpa("AF", msgNCTSUnloadingPermission.getPrincipal()));
        	}
        	
        	if (msgNCTSUnloadingPermission.getGoodsItemList() != null) {
            	for (MsgNCTSUnloadingPermissionPos pos : msgNCTSUnloadingPermission.getGoodsItemList()) {
            	  if (pos != null) {
            		msgVPPPos = new MsgVPPPos();
            		if (pos.getSpecialMentionList() == null) {
            			msgVPPPos.addVppList(getVpp(pos));
            		} else {
	            		for (SpecialMention specialMention : pos.getSpecialMentionList()) {
	            			msgVPPPos.addVppList(setVpp(pos, specialMention));
	            		}
            		}
            	
        			//EI20110527: if (!msgNCTSUnloadingPermission.getConsignor().isEmpty()) {
            		if (pos.getConsignor() != null) {
        				msgVPPPos.setVpdConsignor(getVpd(pos.getItemNumber(), "1", pos.getConsignor()));
        			}
        			//EI20110527:if (!msgNCTSUnloadingPermission.getConsignee().isEmpty()) {
            		if (pos.getConsignee() != null) {
        				msgVPPPos.setVpdConsignee(getVpd(pos.getItemNumber(), "2", pos.getConsignee()));
        			}

        			//EI20110527: 
            		//if (pos.getPackageList().isEmpty()) {
        			//	msgVPPPos.addVplList(getVpl(pos));
        			//} else {
            		if (pos.getPackageList() != null) {
	        			for (Packages packages : pos.getPackageList()) {
	        				msgVPPPos.addVplList(getVpl(pos, packages));
	        			}
        			}
            		//EI20110527: 
        			//if (pos.getDocumentList().isEmpty()) {
        		    //	msgVPPPos.addVpuList(getVpu(pos));
        			//} else {
            		if (pos.getDocumentList() != null) {
	        			for (Document document : pos.getDocumentList()) {
	        				msgVPPPos.addVpuList(getVpu(pos.getItemNumber(), document));
	        			}
        			}
            		//EI20110527: 
        			//if (pos.getContainers().getNumberList().isEmpty()) {
        			//	msgVPPPos.addVpcList(setVpc(pos));
        			//} else {
            		if (pos.getContainers() != null) { 
            			if (pos.getContainers().getNumberList() != null) {
            				for (String numbers : pos.getContainers().getNumberList()) {
            					msgVPPPos.addVpcList(getVpc(pos.getItemNumber(), numbers));
            				}
            			}
            		}
            		//EI20110527: 
        			//if (pos.getSensitiveGoodsList().isEmpty()) {
        			//	msgVPPPos.addVpeList(getVpe(pos));
        			//} else {
            		if (pos.getSensitiveGoodsList() != null) {
	        			for (SensitiveGoods sensitiveGoods : pos.getSensitiveGoodsList()) {
	        				msgVPPPos.addVpeList(getVpe(pos.getItemNumber(), sensitiveGoods));
	        			}
        			}
        			msgVPH.addPosList(msgVPPPos);
            	}
              }
        	}
        	
        	res = msgVPH.getFssString();        	
        	Utils.log("(MapNCTSUnloadingPermissionToVPH getMessage) Msg = " + res);
        	
	    } catch (FssException e) {	    	
	        e.printStackTrace();
	    }
		    
	    return res;
	}
	
	private TsVPH getVph() {
		TsVPH vphSubset = new TsVPH();
		
		vphSubset.setBeznr(msgNCTSUnloadingPermission.getReferenceNumber());
		vphSubset.setMrn(msgNCTSUnloadingPermission.getUcrNumber());
		vphSubset.setAnmart(msgNCTSUnloadingPermission.getTypeOfDeclaration());
		vphSubset.setUbgort(msgNCTSUnloadingPermission.getPlaceOfTransfer());
		vphSubset.setLdvs(msgNCTSUnloadingPermission.getDispatchCountry());
		vphSubset.setLdbe(msgNCTSUnloadingPermission.getDestinationCountry());
		vphSubset.setBedst(msgNCTSUnloadingPermission.getOfficeOfDestination());
		vphSubset.setAbgdst(msgNCTSUnloadingPermission.getOfficeOfDeparture());
		vphSubset.setWgfri(msgNCTSUnloadingPermission.getEndOfPresentationDate());
		
		if (msgNCTSUnloadingPermission.getMeansOfTransportDeparture() != null) {
			vphSubset.setBfabkz(msgNCTSUnloadingPermission.
					getMeansOfTransportDeparture().getTransportationNumber());
			vphSubset.setBfabld(msgNCTSUnloadingPermission.
					getMeansOfTransportDeparture().getTransportationCountry());
		}
		
		if (msgNCTSUnloadingPermission.getConsignorTIN() != null) {
			vphSubset.setTinve(msgNCTSUnloadingPermission.getConsignorTIN().getTin());
		}
		if (msgNCTSUnloadingPermission.getConsigneeTIN() != null) {  //EI20110527
			vphSubset.setTinem(msgNCTSUnloadingPermission.getConsigneeTIN().getTin());
		}
		if (msgNCTSUnloadingPermission.getPrincipalTIN() != null) {  //EI20110527
			vphSubset.setTinhp(msgNCTSUnloadingPermission.getPrincipalTIN().getTin());
		}
		
		vphSubset.setGsroh(msgNCTSUnloadingPermission.getTotalGrossMass());
		vphSubset.setAnzpos(msgNCTSUnloadingPermission.getTotalNumberPositions());
		vphSubset.setAnzcol(msgNCTSUnloadingPermission.getTotalNumberPackages());
		vphSubset.setAnzve(msgNCTSUnloadingPermission.getTotalNumberSeals());
		if (msgNCTSUnloadingPermission.getConsignor() != null) {   //EI20110527
			vphSubset.setEtnabs(msgNCTSUnloadingPermission.getConsignor().getETNAddress());
		}
		return vphSubset;
	}
	
	private TsVPV getVpv(SealNumber sealNumber) {
		if (sealNumber == null) {
			return null;
		}
		if (sealNumber.isEmpty()) {
			return null;
		}
		TsVPV vpbSubset = new TsVPV();
		
		vpbSubset.setSeal(sealNumber.getNumber());
		return vpbSubset;
	}
	
	private TsVPA getVpa(String type, PartyNCTS party) {
		if (party == null) {
			return null;
		}
		if (party.getAddress() == null) {
			return null;
		}
		if (party.getAddress().isEmpty()) {
			return null;
		}
		TsVPA vpaSubset = new TsVPA();
				
		vpaSubset.setTyp(type);		
		vpaSubset.setName(party.getAddress().getName());			
		if (party.getAddress().getHouseNumber() == null) {
			vpaSubset.setStr(party.getAddress().getStreet());
		} else {
			vpaSubset.setStr(party.getAddress().getStreet() + " " + party.getAddress().getHouseNumber());
		}				
		vpaSubset.setOrt(party.getAddress().getCity());
		vpaSubset.setPlz(party.getAddress().getPostalCode());
		vpaSubset.setLand(party.getAddress().getCountry());		
		
		return vpaSubset;
	}
	
	private TsVPP setVpp(MsgNCTSUnloadingPermissionPos pos, SpecialMention specialMention) {
		if (pos == null || specialMention == null) {   //EI20110527
			return null;
		}
		if (specialMention.isEmpty()) {
			return null;
		}
		TsVPP vppSubset = new TsVPP();
		
		vppSubset.setBeznr(msgNCTSUnloadingPermission.getReferenceNumber());
		vppSubset.setPosnr(pos.getItemNumber());
		
		if (pos.getCommodityCode() != null) {
			vppSubset.setTnr(pos.getCommodityCode().getTarifCode());
		}
		
		vppSubset.setWabsch(pos.getDescription());
		vppSubset.setUnstm(specialMention.getText());
		vppSubset.setRohmas(pos.getGrossMass());
		vppSubset.setEigmas(pos.getNetMass());
		vppSubset.setPfehlt(pos.getMissingLineIndicator());	
		vppSubset.setUnstm(specialMention.getText());
		vppSubset.setKzexeu(specialMention.getExportFromEU());
		vppSubset.setLdexp(specialMention.getExportFromCountry());
		
		return vppSubset;
	}
	
	private TsVPP getVpp(MsgNCTSUnloadingPermissionPos pos) {
		if (pos == null) {   //EI20110527
			return null;
		}
		TsVPP vppSubset = new TsVPP();
		
		vppSubset.setBeznr(msgNCTSUnloadingPermission.getReferenceNumber());
		vppSubset.setPosnr(pos.getItemNumber());
		
		if (pos.getCommodityCode() != null) {
			vppSubset.setTnr(pos.getCommodityCode().getTarifCode());
		}
		
		vppSubset.setWabsch(pos.getDescription());
		vppSubset.setRohmas(pos.getGrossMass());
		vppSubset.setEigmas(pos.getNetMass());
		vppSubset.setPfehlt(pos.getMissingLineIndicator());
		
		return vppSubset;
	}
	
	private TsVPD getVpd(String posnr, String type, PartyNCTS party) {
		if (party == null) {   //EI20110527
			return null;
		}
		if (party.getAddress() == null) {
			return null;
		} 
		if (party.getAddress().isEmpty()) {   //EI20110527
			return null;
		}
		TsVPD vpdSubset = new TsVPD();
		
		vpdSubset.setPosnr(posnr);		
		vpdSubset.setTyp(type);			
		vpdSubset.setName(party.getAddress().getName());
					
		if (party.getAddress().getHouseNumber() == null) {
				vpdSubset.setStr(party.getAddress().getStreet());
		} else {
				vpdSubset.setStr(party.getAddress().getStreet() + " " + party.getAddress().getHouseNumber());
		}				
		vpdSubset.setOrt(party.getAddress().getCity());
		vpdSubset.setPlz(party.getAddress().getPostalCode());
		vpdSubset.setLand(party.getAddress().getCountry());
		
		return vpdSubset;
	}
	
	private TsVPL getVpl(MsgNCTSUnloadingPermissionPos pos, Packages packages) {
		if (pos == null || packages == null) {   //EI20110527
			return null;
		}
		if (packages.isEmpty()) {
			return null;
		}
		TsVPL vplSubset = new TsVPL();
		
		vplSubset.setPosnr(pos.getItemNumber());
		vplSubset.setColanz(packages.getQuantity());
		vplSubset.setColart(packages.getType());
		vplSubset.setCollz(packages.getMarks());
		
		return vplSubset;
	}
	
	private TsVPL getVpl(MsgNCTSUnloadingPermissionPos pos) {
		TsVPL vplSubset = new TsVPL();
		
		vplSubset.setPosnr(pos.getItemNumber());
		
		return vplSubset;
	}
	
	private TsVPU getVpu(String posnr, Document document) {
		if (posnr == null || document == null) {   //EI20110527
			return null;
		}
		TsVPU vpuSubset = new TsVPU();
		
		vpuSubset.setPosnr(posnr);
		vpuSubset.setUntart(document.getQualifier());
		vpuSubset.setUntref(document.getReference());
		vpuSubset.setUntzus(document.getAdditionalInformation());
		
		return vpuSubset;
	}
	
	private TsVPU getVpu(MsgNCTSUnloadingPermissionPos pos) {
		if (pos == null) {   //EI20110527
			return null;
		}
		TsVPU vpuSubset = new TsVPU();
		
		vpuSubset.setPosnr(pos.getItemNumber());
		
		return vpuSubset;
	}
	
	private TsVPC getVpc(String posnr, String number) {
		if (posnr == null || number == null) {   //EI20110527
			return null;
		}
		TsVPC vpcSubset = new TsVPC();
		
		vpcSubset.setPosnr(posnr);
		vpcSubset.setConnr(number);
		
		return vpcSubset;
	}
	
	private TsVPC setVpc(MsgNCTSUnloadingPermissionPos pos) {
		if (pos == null) {   //EI20110527
			return null;
		}
		TsVPC vpcSubset = new TsVPC();
		
		vpcSubset.setPosnr(pos.getItemNumber());
		
		return vpcSubset;
	}
	
	private TsVPE getVpe(String posnr, SensitiveGoods sensitiveGoods) {
		if (posnr == null || sensitiveGoods == null) {   //EI20110527
			return null;
		}
		TsVPE vpeSubset = new TsVPE();
		
		vpeSubset.setBeznr(msgNCTSUnloadingPermission.getReferenceNumber());
		vpeSubset.setPosnr(posnr);
		vpeSubset.setCode(sensitiveGoods.getType());
		vpeSubset.setMenge(sensitiveGoods.getWeight());
		
		return vpeSubset;
	}
	
	private TsVPE setVpe(MsgNCTSUnloadingPermissionPos pos) {
		if (pos == null) {   //EI20110527
			return null;
		}
		TsVPE vpeSubset = new TsVPE();
		
		vpeSubset.setBeznr(msgNCTSUnloadingPermission.getReferenceNumber());
		vpeSubset.setPosnr(pos.getItemNumber());
		
		return vpeSubset;
	}
}

