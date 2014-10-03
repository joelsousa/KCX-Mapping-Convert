/*
 * Function    : MapExpAmdToADN.java
 * Date        : 02.10.2008
 * Author      : Kewill CSF / SH
 * Description : Mapping of KIDS format of Amendment to FSS format ADN

 * Changes 
 * -----------
 * Author      :
 * Date        :
 * Label       :
 * Description:
 *
 */

package com.kewill.kcx.component.mapping.countries.de.aes.kids2fss.V60;

import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpAmd;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpAmdPos;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60.MsgADN;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAAK;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAAP;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Modul		: MapExpAmdToADN<br>
 * Erstellt		: 02.10.2008<br>
 * Beschreibung	: Mapping of KIDS format of Amendment to FSS format ADN.
 * 
 * @author heise
 * @version 6.0.00
 */
public class MapExpAmdToADN extends KidsMessage {
	
	private MsgADN msgADN;
	private MsgExpAmd msgExpAdn;
	
	public MapExpAmdToADN(XMLEventReader parser) throws XMLStreamException {
		msgExpAdn = new MsgExpAmd(parser);
		msgADN = new MsgADN("");
	}
	public MapExpAmdToADN(XMLEventReader parser, TsVOR tsvor) throws XMLStreamException {
		msgExpAdn = new MsgExpAmd(parser);
		msgADN = new MsgADN("");
		msgADN.setVorSubset(tsvor);
	}

	public String getMessage() {
    	StringBuffer res = new StringBuffer();
        try {
            msgExpAdn.parse(HeaderType.KIDS);
            getCommonFieldsDTO().setReferenceNumber(msgExpAdn.getReferenceNumber());
            
            // read MessageID from KidsHeader 
            msgADN.getVorSubset().setMsgid(getKidsHeader().getMessageID());
            msgADN.setAakSubset(setAak());
                       
            List <MsgExpAmdPos> goodsItemList =  msgExpAdn.getGoodsItemList(); 
            int size = 0;
            if (goodsItemList != null)  {
                size = goodsItemList.size();
            }
            	
	        for (int i = 0; i < size; i++) {
	           TsAAP aapSubset = new TsAAP();
	           aapSubset = setAap(goodsItemList.get(i));	           				           	
	           msgADN.addPosList(aapSubset);
	        }           
            	
            res = msgADN.writeADN();
            
            Utils.log("(MapExpAmdToADN getMessage) Msg = " + res);
		
	    } catch (FssException e) {
	        
	        e.printStackTrace();
	    }
		    
	    return res.toString();
	}
	
	private TsAAK setAak() {
		TsAAK aakSubset = new TsAAK();
		
		aakSubset.setBeznr(msgExpAdn.getReferenceNumber());
		aakSubset.setGsroh(msgExpAdn.getGrossMass());
		
		return aakSubset;
	}
	
	private TsAAP setAap(MsgExpAmdPos msgExpAdnPos) {
		if (msgExpAdnPos == null) {
		    return null;
		}
		
		TsAAP aapSubset = new TsAAP();
		
		aapSubset.setBeznr(msgExpAdn.getReferenceNumber());		
		aapSubset.setPosnr(msgExpAdnPos.getItemNumber());
		aapSubset.setEigmas(msgExpAdnPos.getNetMass());
		aapSubset.setRohmas(msgExpAdnPos.getGrossMass());
			
		if (msgExpAdnPos.getExportRefundItem() != null) {
            aapSubset.setMenge(msgExpAdnPos.getExportRefundItem().getAmount());     
		}
		
		return aapSubset;
	}

}
