package com.kewill.kcx.component.mapping.countries.de.Port.kids2fss;

import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.GoodsItemDeclarationDecision;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.MsgPortMindermengen;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsCRP;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsTXT;
import com.kewill.kcx.component.mapping.formats.fss.Port.messages.V65.MsgMMA;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsMIK;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessagePortGpo;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;


/**
 * Module       : Port<br>
 * Created      : 28.11.2012<br>
 * Description	: Mapping of FSS CTX to KIDS ImportDeclarationDecision.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MapMMAtoMindermenge extends KidsMessagePortGpo {
	
	private MsgMMA msgMMA;	
	private MsgPortMindermengen message;   //KIDS: MsgPortMindermengen ist noch nicht definiert
	private String beznr = "";
	
	public MapMMAtoMindermenge(XMLEventReader parser, TsVOR tsvor)  throws XMLStreamException {
		msgMMA = new MsgMMA();	
		message = new MsgPortMindermengen();
		msgMMA.setVorSubset(tsvor);
	}	
	
	public String getMessage() {
		String res = ""; 
	
		try {  
        	message.parse(HeaderType.KIDS);         	
            getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
            beznr = message.getReferenceNumber();
            
            // read MessageID from KidsHeader.
            msgMMA.getVorSubset().setMsgid(getKidsHeader().getMessageID());        
            msgMMA.setMikSubset(this.mapKidsToMIK());
                    
            
            res = msgMMA.getFssString();
            
            //Utils.log("(MapImportDeclarationConfirmationToCON getMessage) Msg = " + res);
            
		} catch (FssException e) {	    	
			e.printStackTrace();	        
		}		    
	    return res;
	}
	
	private TsMIK mapKidsToMIK() {
		TsMIK subset = new TsMIK();
		
		subset.setBeznr(beznr);
		subset.setMrn(message.getMrn());
		//subset.setKnztst(message.get???);
		
		return subset;
	}	
	
}
