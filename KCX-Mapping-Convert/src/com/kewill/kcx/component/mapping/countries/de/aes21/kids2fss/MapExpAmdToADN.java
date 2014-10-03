package com.kewill.kcx.component.mapping.countries.de.aes21.kids2fss;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpAmd;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpAmdPos;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V70.MsgADN;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAAK;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAAP;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsADR;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: Export/aes<br>
 * Created		: 22.07.2012<br>
 * Description	: Mapping of KIDS-V21 of Amendment to FSS-V70 ADN.
 * 
 * @author iwaniuk
 * @version 2.1.00
 */

public class MapExpAmdToADN extends KidsMessage {
	
	private MsgADN msgADN;
	private MsgExpAmd msgKids;
	private String subversion = "";   //EI20120422
	
	public MapExpAmdToADN(XMLEventReader parser) throws XMLStreamException {
		msgKids = new MsgExpAmd(parser);
		msgADN = new MsgADN("");
	}
	public MapExpAmdToADN(XMLEventReader parser, TsVOR tsvor) throws XMLStreamException {
		msgKids = new MsgExpAmd(parser);
		msgADN = new MsgADN("");
		msgADN.setVorSubset(tsvor);
	}
	public MapExpAmdToADN(XMLEventReader parser, TsVOR vor, TsHead head) throws XMLStreamException {
		msgKids = new MsgExpAmd(parser);
		msgADN = new MsgADN("");
		msgADN.setVorSubset(vor);
		msgADN.setHeadSubset(head);
	}

	public String getMessage() {
    	//StringBuffer res = new StringBuffer();
		String res = "";
		
        try {
            msgKids.parse(HeaderType.KIDS);
            getCommonFieldsDTO().setReferenceNumber(msgKids.getReferenceNumber());
            
            if (this.getKidsHeader() != null && !Utils.isStringEmpty(this.getKidsHeader().getRelease())) {   //EI20130422
            	subversion = Utils.removeDots(this.getKidsHeader().getRelease());
			}
          
            msgADN.getVorSubset().setMsgid(getKidsHeader().getMessageID());            
            msgADN.setAakSubset(mapKidsToAak());
            msgADN.setDeclarantSubset(mapKidsToAdr(msgKids.getDeclarant(), "3"));  //new for V21
            msgADN.setAgentSubset(mapKidsToAdr(msgKids.getAgent(), "4"));          //new for V21
                        
            if (msgKids.getGoodsItemList() != null)  {           
            	for (MsgExpAmdPos item : msgKids.getGoodsItemList()) {
            		TsAAP aapSubset = new TsAAP();
            		aapSubset = mapKidsToAap(item);	           				           	
            		msgADN.addPosList(aapSubset);
            	}
	        }           
            	
            //res = msgADN.getFssString();
            /* EI20140206
            if (this.writeHead()) { 				//EI20130213
            	res = msgADN.getFssString("HEAD");
            } else {
            	res = msgADN.getFssString();
            }  
            */
            res = msgADN.getFssString("HEAD");
            
            Utils.log("(MapExpAmdToADN getMessage) Msg = " + res);
		
	    } catch (FssException e) {
	        
	        e.printStackTrace();
	    }
		    
	    //return res.toString();
	    return res;
	}
	
	private TsAAK mapKidsToAak() {
		TsAAK aakSubset = new TsAAK();
		
		aakSubset.setBeznr(msgKids.getReferenceNumber());
		// CK120824
		// aakSubset.setGsroh(msgKids.getGrossMass());
		aakSubset.setGsroh(Utils.addZabisDecimalPlaceV7(msgKids.getGrossMass(), 3));	
		if (subversion.equals("2101")) {     //EI20130422
			aakSubset.setGsroh(msgKids.getGrossMass());
		}
		
		return aakSubset;
	}
	
	private TsADR mapKidsToAdr(Party party, String typ) {
		if (party == null) {
			return null;
		}
		
		TsADR adrSubset = new TsADR(typ);
		adrSubset.setBeznr(msgKids.getReferenceNumber());
		adrSubset.setPosnr("0");
		adrSubset.setTyp(typ);
		adrSubset.setEtn(party.getETNAddress());
		
		if (party.getPartyTIN() != null) {
			adrSubset.setKdnr(party.getPartyTIN().getCustomerIdentifier());
			adrSubset.setTin(party.getPartyTIN().getTIN());
			adrSubset.setNl(party.getPartyTIN().getBO());
			adrSubset.setDtzo(party.getPartyTIN().getIdentificationType());			
		}		
		
		if (party.getAddress() != null) {
			String name1 = party.getAddress().getName();
			String name2 = "";
			String name3 = "";
			if (name1 != null) {				
				if (name1.length() > 40) {					
					name2 = name1.substring(40);
					name1 = name1.substring(0, 40);
				}				
				if (name2.length() > 40) {
					name3 = name2.substring(40);
					name2 = name2.substring(0, 40);
				}
			}
			adrSubset.setName1(name1);
			adrSubset.setName2(name2);
			adrSubset.setName3(name3);
			adrSubset.setStr(party.getAddress().getStreet());
			adrSubset.setOrt(party.getAddress().getCity());
			adrSubset.setPlz(party.getAddress().getPostalCode());
			adrSubset.setLand(party.getAddress().getCountry());
		}
		
		return adrSubset;
	}
	
	private TsAAP mapKidsToAap(MsgExpAmdPos msgKidsPos) {
		if (msgKidsPos == null) {
		    return null;
		}
		
		TsAAP aapSubset = new TsAAP();
		
		aapSubset.setBeznr(msgKids.getReferenceNumber());		
		aapSubset.setPosnr(msgKidsPos.getItemNumber());
		// CK120824
		// aapSubset.setEigmas(msgKidsPos.getNetMass());
		aapSubset.setEigmas(Utils.addZabisDecimalPlaceV7(msgKidsPos.getNetMass(), 3));
		// aapSubset.setRohmas(msgKidsPos.getGrossMass());
		aapSubset.setRohmas(Utils.addZabisDecimalPlaceV7(msgKidsPos.getGrossMass(), 3));
		if (subversion.equals("2101")) {     //EI20130422
			aapSubset.setEigmas(msgKidsPos.getNetMass());
			aapSubset.setRohmas(msgKidsPos.getGrossMass());
		}
		if (msgKidsPos.getExportRefundItem() != null) {
			// CK120824
            // aapSubset.setMenge(msgKidsPos.getExportRefundItem().getAmount());     
			aapSubset.setMenge(Utils.addZabisDecimalPlaceV7(msgKidsPos.getExportRefundItem().getAmount(), 1));
			if (subversion.equals("2101")) {     //EI20130422
				aapSubset.setMenge(msgKidsPos.getExportRefundItem().getAmount());    
			}
		}
		
		return aapSubset;
	}

}
