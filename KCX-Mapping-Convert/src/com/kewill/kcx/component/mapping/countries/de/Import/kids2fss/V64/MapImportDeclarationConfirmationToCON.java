package com.kewill.kcx.component.mapping.countries.de.Import.kids2fss.V64;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.Import.msg.MsgImportDeclarationConfirmation;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Completion;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Manifest;
import com.kewill.kcx.component.mapping.formats.fss.Import.messages.V64.MsgCON;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsCON;
import com.kewill.kcx.component.mapping.formats.fss.base.subsets.TsBAV;
import com.kewill.kcx.component.mapping.formats.fss.base.subsets.TsBSU;
import com.kewill.kcx.component.mapping.formats.fss.base.subsets.TsBZL;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;


/**
 * Module		: Import<br>
 * Created		: 20.09.2011<br>
 * Description	: Mapping of FSS CON to KIDS ImportDeclarationConfirmation.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MapImportDeclarationConfirmationToCON extends KidsMessage {
	
	private MsgCON msgCON;	
	private MsgImportDeclarationConfirmation message;
	private String beznr = "";
	
	public MapImportDeclarationConfirmationToCON(XMLEventReader parser, TsVOR tsvor) throws XMLStreamException {		
		message = new MsgImportDeclarationConfirmation(parser);
		msgCON = new MsgCON();	
		msgCON.setVorSubset(tsvor);
	}
	
	public String getMessage() {
		String res = "";    	
    	 
		try {  
        	message.parse(HeaderType.KIDS);         	
            getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
            
            // read MessageID from KidsHeader.
            msgCON.getVorSubset().setMsgid(getKidsHeader().getMessageID());
        
            //Setting all Ts in MsgCON.
            msgCON.setCONSubset(mapKidsToCON());			
            msgCON.setBSUList(mapKidsToBSUList(message.getManifestCompletionList()));
            msgCON.setBZLList(mapKidsToBZLList(message.getBondedWarehouseCompletionList()));
            msgCON.setBAVList(mapKidsToBAVList(message.getInwardProcessingCompletionList()));
            
            res = msgCON.getFssString();
            
            Utils.log("(MapImportDeclarationConfirmationToCON getMessage) Msg = " + res);
            
		} catch (FssException e) {	    	
			e.printStackTrace();	        
		}
	   
		    
	    return res;
	}
			
	
	
	private TsCON mapKidsToCON() {
		TsCON subset = new TsCON();
		subset.setBeznr(message.getReferenceNumber());
		subset.setArbnr(message.getTemporaryMRN());
		subset.setKorant("0");
		subset.setAnmzb(message.getDeclarantTIN());
		if (message.getRepresentative() != null) {
			subset.setSbname(message.getRepresentative().getName());
			subset.setSbstel(message.getRepresentative().getPosition());
			subset.setSbtele(message.getRepresentative().getPhoneNumber());
			subset.setSbnr(message.getRepresentative().getIdentity());
		}
		subset.setWaort(message.getGoodsLocation());
		subset.setBfkzi(message.getMeansOfTransportArrival());
		if (message.getPreviousDocument() != null) {
		subset.setVorpnr(message.getPreviousDocument().getNumber());
		subset.setVorpar(message.getPreviousDocument().getType());
		}
		
		return subset;
	}
	private List<TsBSU> mapKidsToBSUList(List<Manifest> manifestList) {
		List<TsBSU> bsuList = new Vector<TsBSU>();
		for (Manifest manifest : manifestList) {
			TsBSU bsu = new TsBSU();
			bsu.setVregnr(manifest.getRegistrationNumber());
			bsu.setVposnr(manifest.getItemNumber());
			bsu.setSuastk(manifest.getNumberOfPieces());
			if (manifest.getCustodian() != null) {
				bsu.setVrwknr(manifest.getCustodian().getCustomerId());
				//bsu.setVerweorizb(manifest.getCustodian().getTIN());				
			}
			if (manifest.getSpecificKey() != null) {
				bsu.setSpoart(manifest.getSpecificKey().getCode());
				bsu.setSponr(manifest.getSpecificKey().getNumber());
			}
			bsu.setAzvagl(manifest.getATLASAlignment());
			
			bsuList.add(bsu);
		}
		return bsuList;
	}
	private List<TsBZL> mapKidsToBZLList(List<Completion> list) {
		List<TsBZL> bzlList = new Vector<TsBZL>();
		for (Completion completion : list) {
			TsBZL bzl = new TsBZL();
			bzl.setVregnr(completion.getRegistrationNumber());
			bzl.setVposnr(completion.getItemNumber());
			bzl.setAtlas(completion.getATLASInFlow());
			bzl.setKzuebl(completion.getCommonUse());
			bzl.setWanr(completion.getCommodityCode());
			if (completion.getOutflow() != null) {
				bzl.setMgeabg(completion.getOutflow().getQualifier());
				bzl.setMeabg(completion.getOutflow().getUnit());
				bzl.setQmeabg(completion.getOutflow().getQualifier());
			}
			if (completion.getTradedVolume() != null) {
				bzl.setMgehdl(completion.getTradedVolume().getQuantity());
				bzl.setMehdl(completion.getTradedVolume().getUnit());
				bzl.setQmehdl(completion.getTradedVolume().getQualifier());
			}
			bzl.setTxzus(completion.getInformation());
			bzl.setAzvagl(completion.getATLASAlignment());			
			
			bzlList.add(bzl);
		}
		return bzlList;
	}
	private List<TsBAV> mapKidsToBAVList(List<Completion> list) {
		List<TsBAV> bavList = new Vector<TsBAV>();
		for (Completion completion : list) {
			TsBAV bav = new TsBAV();
			bav.setVregnr(completion.getRegistrationNumber());
			bav.setVposnr(completion.getItemNumber());
			bav.setAtlas(completion.getATLASInFlow());
			bav.setTxzus(completion.getInformation());
			bav.setAzvagl(completion.getATLASAlignment());
			
			bavList.add(bav);
		}
		return bavList;
	}
	
}
