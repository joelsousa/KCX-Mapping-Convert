package com.kewill.kcx.component.mapping.countries.de.suma62.kids2fss;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.Trader;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.MsgNotificationOfPresentation;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.GoodsItem;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.ManifestReference;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.suma.messages.V62.MsgSUK;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V62.TsSUA;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V62.TsSUK;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V62.TsSUP;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V62.TsSUR;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: Manifest<br>
 * Created		: 20.12.2012<br>
 * Description	: Mapping of KIDS MsgNotificationOfPresentation to FSS SUK.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MapNotificationOfPresentationToSUK extends KidsMessage {
	
	private MsgNotificationOfPresentation message;
	private MsgSUK msgSUK;
	
	public MapNotificationOfPresentationToSUK(XMLEventReader parser, TsVOR tsvor) throws XMLStreamException {		
		message = new MsgNotificationOfPresentation(parser);
		msgSUK = new MsgSUK();
		msgSUK.setVorSubset(tsvor);
	}

	public String getMessage() {
    	String res = "";    	
    	    	
        try {         	
        	message.parse(HeaderType.KIDS);         	
            getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
            
            // read MessageID from KidsHeader.
            msgSUK.getVorSubset().setMsgid(getKidsHeader().getMessageID());
            msgSUK.setSukSubset(mapSUK());
            msgSUK.setSurSubset(mapSUR(message.getReference()));
            msgSUK.setSuaSubset(mapSUA(message.getPresenter()));        	        
            
            if (message.getGoodsItemList() != null) {            	
			   for (GoodsItem item : message.getGoodsItemList()) {
				   if (item != null) {
					   msgSUK.addSupList(mapSUP(item));
				   }
			   }
            }
         
            res = msgSUK.getFssString();
           
            Utils.log("(NotificationOfPresentationToSUK getMessage) Msg = " + res);
		
	    } catch (FssException e) {	    	
	        e.printStackTrace();	        
	    }
		    
	    return res;
	}
	
	private TsSUK mapSUK() {
		TsSUK suk = new TsSUK();
		
		suk.setBeznr(message.getReferenceNumber());
		suk.setAposnr(message.getItemNumberAllocated());
		suk.setAregnr(message.getRegistrationNumberAllocated());
		//suk.setGstdat(message.getDateOfPresentation());
		if (!Utils.isStringEmpty(message.getDateOfPresentation())) {
			if (message.getDateOfPresentation().length() >= 8) {
				suk.setGstdat(message.getDateOfPresentation().substring(0, 8));
			}
		}
		suk.setAnzcon(message.getContainerQuantity());
		suk.setBelo(message.getPlaceOfLoading());
		suk.setVtrznr(message.getAgentTIN());
		suk.setIdart(message.getReferenceIdentifier());
		suk.setAkz(message.getMessageFunction());
		suk.setFltnum(message.getFlightNumber());
		suk.setKzncts(message.getNctsID());
		if (message.getHeaderExtensions() != null) {
			suk.setErfkz(message.getHeaderExtensions().getRegistrationID());
			suk.setKzvorz(message.getHeaderExtensions().getAdvanceProcedureID());
			suk.setSeekz(message.getHeaderExtensions().getMaritimTrafficID());
		}
		if (message.getPresenter() != null) {
			suk.setGstznr(message.getPresenter().getTIN());	
		}
		if (message.getCustomsOffices() != null) {
			suk.setDstnr(message.getCustomsOffices().getOfficeOfPresentation());	
		}
		if (message.getPreviousDocument() != null) {
			suk.setVorart(message.getPreviousDocument().getType());
			//suk.setVornr(message.getPreviousDocument().getReference());
			//suk.setVornr(message.getPreviousDocument().getMarks());   //EI20130118
			suk.setVornr(message.getPreviousDocument().getReference()); //EI20130201 doch vorher war richitg
		}
		if (message.getTransport() != null) {
			suk.setBefart(message.getTransport().getMode());	
			suk.setBefkz(message.getTransport().getIdentity());	
			suk.setBefson(message.getTransport().getDescription());	
		}
		if (message.getContact() != null) {
			suk.setSb(message.getContact().getIdentity());	
			suk.setSbname(message.getContact().getName());	
			suk.setSbdstl(message.getContact().getPosition());	
			suk.setSbtel(message.getContact().getPhoneNumber());	
		}
		/*
		 bfvkzg	
		 eindst	
		 kzeinds
		 befnum	
		 ankdat	
		 */
		
		return suk;
	}
	
	private TsSUR mapSUR(ManifestReference ref) {
		if (ref == null) {
			return null;
		}
		TsSUR sur = new TsSUR();		
		sur.setBeznr(message.getReferenceNumber());				
		sur.setIdfltnum(ref.getFlightNumber());			
		sur.setIdfltblo(ref.getPlaceOfLoading());
		sur.setIdvrwznr(ref.getCustodianTIN());
		if (ref.getPreviousDocument() != null) {
			sur.setIdfltvpa(ref.getPreviousDocument().getType());	
		}		
		if (ref.getReferencedSpecification() != null) {
			sur.setIdkzawb(ref.getReferencedSpecification().getTypeOfSpecificationID());	
			sur.setIdspo(ref.getReferencedSpecification().getSpecificationID());		
		}
		/*	
 		idbefnum	
 		idankdat   
		 */
		return sur;
	}
	
	private TsSUA mapSUA(Trader trader) {
		if (trader == null) {
			return null;
		}
		if (trader.getAddress() == null) {
			return null;
		}
		TsSUA sua = new TsSUA();
		sua.setBeznr(message.getReferenceNumber());	
		
		String name = trader.getAddress().getName();		
		if (name != null && name.length() > 0) {
			int len = name.length();
			if (name.length() < 36) {
				sua.setName1(name);
			} else if (name.length() < 71) {
				sua.setName1(name.substring(0, 35));
				sua.setName2(name.substring(35));
			} else {
				sua.setName1(name.substring(0, 35));
				sua.setName2(name.substring(35, 70));
				sua.setName3(name.substring(70));
			}
		}
		sua.setStr(trader.getAddress().getStreet());
		sua.setOrt(trader.getAddress().getCity());
		sua.setPlz(trader.getAddress().getPostalCode());
		sua.setLdkz(trader.getAddress().getCountry());
		sua.setPostf(trader.getAddress().getPOBox());
		
		return sua;
	}	
	
	private TsSUP mapSUP(GoodsItem item) {
		if (item == null) {
			return null;
		}
		TsSUP sup = new TsSUP();
		
		sup.setBeznr(message.getReferenceNumber());
		sup.setPosnr(item.getItemNumber());
		sup.setVland(item.getCountryOfDispatch());	
		sup.setBesort(item.getDestinationCode());	
		sup.setZollst(item.getCustomsStatusOfGoods());
		sup.setRohm(item.getGrossMass());	//TODO? masse muss von kunden mit virtuellem komma kommen
		sup.setWabes(item.getBriefCargoDescription());	
		sup.setWakr(item.getRangeOfGoodsCode());
		sup.setVrwort(item.getPlaceOfCustodyCode());	
		sup.setKzbest(item.getConfirmationCode());	
		if (item.getItemExtension() != null) {		
			sup.setKzuvwm(item.getItemExtension().getTemporaryStorageCode());	
		}	
		if (item.getPackaging() != null) {
			sup.setColart(item.getPackaging().getType());	
			sup.setColanz(item.getPackaging().getQuantity());	
		}		
		if (item.getCustodian() != null) {	
			sup.setVrwznr(item.getCustodian().getTIN());	
		}
		if (item.getDisposal() != null) {	
			sup.setVfbznr(item.getDisposal().getTIN());	
		} 
		if (item.getReferencedSpecification() != null) {	//EI20130201
			sup.setKzawb(item.getReferencedSpecification().getTypeOfSpecificationID());
			sup.setSpo(item.getReferencedSpecification().getSpecificationID());
		} 

		/*		
		 emrn	
		 emrnpos	
		 kzfrzone
		 */
		
		return sup;
		
	}

}
