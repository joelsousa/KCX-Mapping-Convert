package com.kewill.kcx.component.mapping.countries.ch.ncts20.kids2fss;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.de.ncts20.msg.MsgNCTSDeclaration;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCCN;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCVI;
import com.kewill.kcx.component.mapping.formats.fss.edec.ncts20.messages.MsgCTK;
import com.kewill.kcx.component.mapping.formats.fss.edec.ncts20.subsets.TsCTA;
import com.kewill.kcx.component.mapping.formats.fss.edec.ncts20.subsets.TsCTC;
import com.kewill.kcx.component.mapping.formats.fss.edec.ncts20.subsets.TsCTG;
import com.kewill.kcx.component.mapping.formats.fss.edec.ncts20.subsets.TsCTK;
import com.kewill.kcx.component.mapping.formats.fss.edec.ncts20.subsets.TsCTS;
import com.kewill.kcx.component.mapping.formats.fss.edec.ncts20.subsets.TsCTV;
import com.kewill.kcx.component.mapping.formats.fss.edec.ncts20.subsets.TsCTW;
import com.kewill.kcx.component.mapping.formats.fss.edec.ncts20.subsets.TsCTX;
import com.kewill.kcx.component.mapping.formats.fss.edec.ncts20.subsets.TsCTZ;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module      : EDEC Export 20
 * Created     : 09.11.2012
 * Description : Mapping of KIDS format of ExportDeclaration to CAN.
 * 
 * @author iwaniuk
 * @version 2.0.00
 * 
 * 
 * 			TODO unklar ob MsgNCTSDeclaration oder ExportDeclaration genommen werden soll 
 * 
 */

public class MapNCTSDeclarationToCTK extends KidsMessage {

	private MsgNCTSDeclaration message;
	private MsgCTK    msgCTK;
	private String beznr;

	public MapNCTSDeclarationToCTK(XMLEventReader parser) throws XMLStreamException {
		message = new MsgNCTSDeclaration(parser);
		msgCTK = new MsgCTK("");
	}

	public MapNCTSDeclarationToCTK(XMLEventReader parser, TsVOR tsvor) throws XMLStreamException {
		message = new MsgNCTSDeclaration(parser);
		msgCTK = new MsgCTK("");
		msgCTK.setVorSubset(tsvor);
	}
	
	public MapNCTSDeclarationToCTK(XMLEventReader parser, TsVOR tsvor, TsHead head) throws XMLStreamException {
		message = new MsgNCTSDeclaration(parser);
		msgCTK = new MsgCTK("");
		msgCTK.setVorSubset(tsvor);
		msgCTK.setHeadSubset(head);
	}

	public String getMessage() {		
		String res = "";
		
		try {
			message.parse(HeaderType.KIDS);
			beznr = message.getReferenceNumber();
			getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());

			beznr = message.getReferenceNumber();
			msgCTK.getVorSubset().setMsgid(getKidsHeader().getMessageID());
			/*
			msgCTK.setCtkSubset(mapCTK()); 			
			msgCTK.setCtaSubset(mapCTA(message.get???));
			msgCTK.setCtsSubset(mapCTS());
			msgCTK.setCtxSubset(mapCTX());
			msgCTK.setCtcSubset(mapCTC(message.get???));
			msgCTK.setCtgSubset(mapCTG());
				
			if (message.getTransRoute() != null && message.getTransRoute().getCountryList() != null) {   
				msgCTK.setCtwList(mapCtwList(message.getTransRoute().getCountryList());
			}
			if (message.get??? != null) {   
				msgCTK.setCtzList(mapCtzList());
			}
			if (message.getSeals() != null && message.getSeals().getSealNumbersList() != null) {   
				msgCTK.setCtvList(mapCtvList(message.getSeals().getSealNumbersList()));
			}
			 */
			if (this.writeHead()) { 				//EI20130213
            	res = msgCTK.getFssString("HEAD");
            } else {
            	res = msgCTK.getFssString();
            }  
			
			Utils.log("(MapExpDatToCAN getMessage) Msg = " + res);

		} catch (FssException e) {
			e.printStackTrace();
		}
			
		return res;
	}
	
    //---------------------
	
	private TsCTK mapCTK() {
		TsCTK subset = new TsCTK();

		subset.setBeznr(beznr);		
		
        /*
		
		*/
		
		return subset;
	}
	
	private TsCTA mapCTA(Address address) {
		TsCTA subset = new TsCTA();

		subset.setBeznr(beznr);
		subset.setTyp("3");
		subset.setName(address.getName());
		subset.setStr(address.getStreet());
		if (address.getStreet() != null && address.getHouseNumber() != null) { //EI20130326
			subset.setStr(address.getStreet() + address.getHouseNumber());
		}
		subset.setLand(address.getCountry());
		subset.setPlz(address.getPostalCode());
		subset.setOrt(address.getCity());

		return subset;
	}

	private TsCTS mapCTS() {
		TsCTS subset = new TsCTS();
		subset.setBeznr(beznr);
		/*
		subset.setSicart(message.getg);
		subset.setSicbsc(message);
		subset.setAccd(message);
		subset.setTC31nr(message);
		subset.setSicbtg(message);
		subset.setSicwrg(message);
		*/
		return subset;
	}
	
	private TsCTX mapCTX() {
		TsCTX subset = new TsCTX();
		subset.setBeznr(beznr);
		/*
		subset.setPosnr();
		subset.setDknrzo();
		*/
		return subset;
	}
	
	private TsCTG mapCTG() {
		TsCTG subset = new TsCTG();
		subset.setBeznr(beznr);
		
		return subset;
	}
	
	private TsCTC mapCTC(List<String> list) {				                         
		if (list == null) {
			return null;
		}
		TsCTC subset = new TsCTC();			
				
		int c = 0;		
		for (String number : list) {									
			c = c + 1;            
			switch (c) {
			case 1:
				subset.setConnr1(number); 				
				break;
			case 2:
				subset.setConnr2(number);
				break;
			case 3:
				subset.setConnr3(number);
				break;
			case 4:
				subset.setConnr4(number);
				break;
			case 5:
				subset.setConnr5(number);
				break;
			case 6:
				subset.setConnr6(number);
				break;
			case 7:
				subset.setConnr7(number);
				break;
			case 8:
				subset.setConnr8(number);
				break;
			case 9:
				subset.setConnr9(number);				
				break;
			default:
				break;
			}					
		}
		return subset;
	}
	
	private List<TsCTW> mapCtwList(List<String> list) {				                         
		if (list == null) {
			return null;
		}
		List<TsCTW>	ctwList = new Vector<TsCTW>();	
		int size = list.size();		
		TsCTW subset = null;	
		
		for (int i = 0; i < size; i++) {
			if (list.get(i) != null) {
				subset = new TsCTW();
				subset.setBeznr(beznr);
				//subset.setLand();				
				ctwList.add(subset);
			}
		}
		return ctwList;
	}


	
	private List<TsCTZ> mapCtzList(List<String> list) {				                         
		if (list == null) {
			return null;
		}
		List<TsCTZ>	ctzList = new Vector<TsCTZ>();	
		int size = list.size();		
		TsCTZ subset = null;		

		for (int i = 0; i < size; i++) {
			if (list.get(i) != null) {
				subset = new TsCTZ();
				subset.setBeznr(beznr);
				//subset.setDgzst();
				//subset.setAnkzst();				
				ctzList.add(subset);
			}
		}
		return ctzList;
	}
	
	private List<TsCTV> mapCtvList(List<String> list) {				                         
		if (list == null) {
			return null;
		}
		List<TsCTV>	ctvList = new Vector<TsCTV>();	
		int size = list.size();		
		TsCTV subset = null;		

		for (int i = 0; i < size; i++) {
			if (list.get(i) != null) {
				subset = new TsCTV();
				subset.setBeznr(beznr);
				//subset.setSeal();				
				ctvList.add(subset);
			}
		}
		return ctvList;
	}
}
