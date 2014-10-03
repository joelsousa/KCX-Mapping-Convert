package com.kewill.kcx.component.mapping.countries.de.suma70.kids2fss;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.MsgNotificationOfPresentation;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.GoodsItem;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.ManifestReference;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.suma.messages.V70.MsgSUK;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.TsSUA;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.TsSUK;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.TsSUP;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.TsSUR;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageManifest20;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: Manifest<br>
 * Created		: 24.05.2013<br>
 * Description	: Mapping of KIDS MsgNotificationOfPresentation to FSS SUK.
 * 
 * @author krzoska
 * @version 2.0.00
 */
public class MapNotificationOfPresentationToSUK extends KidsMessageManifest20 {
	
	private MsgNotificationOfPresentation message;
	private MsgSUK msgSUK;
	private String subversion = "";      //EI20130625	
	
	public MapNotificationOfPresentationToSUK(XMLEventReader parser, TsHead tsHead) throws XMLStreamException {		
		message = new MsgNotificationOfPresentation(parser);
		msgSUK = new MsgSUK();		
		msgSUK.setHeadSubset(tsHead);
	}

	public String getMessage() {
    	String res = "";    	
    	    
    	if (!Utils.isStringEmpty(this.getKidsHeader().getRelease())) {   //EI20130625
			subversion = Utils.removeDots(this.getKidsHeader().getRelease());			
		}
    	
        try {         	
        	message.parse(HeaderType.KIDS);         	
            getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
                                 
            //EI20130625: ist doch schon im KidsToFssConverter gesetzt
            //msgSUK.getHeadSubset().setMsgid(getKidsHeader().getMessageID()); 
            
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
		if (!Utils.isStringEmpty(message.getDateOfPresentation())) {
			if (message.getDateOfPresentation().length() >= 8) {
				suk.setGstdat(message.getDateOfPresentation().substring(0, 8));
			}
		}		
		suk.setAnzcon(message.getContainerQuantity());
		suk.setBelo(message.getPlaceOfLoading());
		if (message.getAgentTIN() != null) {
			suk.setVtrkdnr(message.getAgentTIN().getCustomerIdentifier());
			suk.setVtreori(message.getAgentTIN().getTIN());
			suk.setVtrnlnr(message.getAgentTIN().getBO());
		}
		suk.setIdart(message.getReferenceIdentifier());
		suk.setAkz(message.getMessageFunction());
		suk.setKzncts(message.getNctsID());
		if (message.getHeaderExtensions() != null) {
			suk.setErfkz(message.getHeaderExtensions().getRegistrationID());
			suk.setKzvorz(message.getHeaderExtensions().getAdvanceProcedureID());
			suk.setSeekz(message.getHeaderExtensions().getMaritimTrafficID());
		}
		if (message.getPresenter() != null && message.getPresenter().getPartyTIN() != null) {
			suk.setGsteori(message.getPresenter().getPartyTIN().getTIN());
			suk.setGstkdnr(message.getPresenter().getPartyTIN().getCustomerIdentifier());
			suk.setGstnlnr(message.getPresenter().getPartyTIN().getBO());
		}
		if (message.getCustomsOffices() != null) {
			suk.setDstnr(message.getCustomsOffices().getOfficeOfPresentation());
			suk.setAndst(message.getCustomsOffices().getDeclaredOfficeOfFirstEntry());			
		}		
		if (message.getPreviousDocument() != null) {
			suk.setVorart(message.getPreviousDocument().getType());			
			suk.setVornr(message.getPreviousDocument().getReference()); 
		}
		if (message.getTransport() != null) {
			suk.setBefart(message.getTransport().getTransportMode());				
			suk.setBefson(message.getTransport().getDescription());
			//suk.setBefkz(message.getTransport().getIdentity());	//(an30), bei befart 04 nicht füllen!
			suk.setBefnum(message.getTransport().getTransportationNumber());  //(an8)			
		}
		if (message.getContact() != null) {
			suk.setSb(message.getContact().getIdentity());	
			suk.setSbname(message.getContact().getName());	
			suk.setSbdstl(message.getContact().getPosition());	
			suk.setSbtel(message.getContact().getPhoneNumber());	
		}
				
		if (message.getTransportAtBorder() != null) {
			suk.setVkzwg(message.getTransportAtBorder().getTransportMode());	 //Verkehrszweig an der Grenze an2		
		}
				
		 //kzeindst: Kennzeichen, ob die Zollstelle, bei der die Summarische Anmeldung abgegeben wird, 
		 //       tatsächliche die erste Eingangszollstelle oder eine nachfolgende Eingangszollstelle ist				 
		suk.setKzeindst(message.getSumACustomsOfficeIsOfficeIsOfFirstEntry());  //EI20130716
		suk.setAnkdat(message.getDateOfArrival());  //EI20130625
		
		return suk;
	}
	
	private TsSUR mapSUR(ManifestReference ref) {
		if (ref == null) {
			return null;
		}
		TsSUR sur = new TsSUR();		
		sur.setBeznr(message.getReferenceNumber());				
		sur.setIdfltblo(message.getPlaceOfLoading());
		if (ref.getPreviousDocument() != null) {
			sur.setIdfltvpa(ref.getPreviousDocument().getType());	
		}		
		if (ref.getReferencedSpecification() != null) {
			sur.setIdkzawb(ref.getReferencedSpecification().getTypeOfSpecificationID());	
			sur.setIdspo(ref.getReferencedSpecification().getSpecificationID());		
		}
		if (ref.getCustodianTIN() != null) {
			sur.setIdvrwkdnr(ref.getCustodianTIN().getCustomerIdentifier());
			sur.setIdvrweori(ref.getCustodianTIN().getTIN());
		}
		
		sur.setBefnum(message.getTransport().getTransportationNumber());	
		sur.setAnkdat(message.getDateOfArrival());  	
		
		return sur;
	}
	
	private TsSUA mapSUA(Party party) {
		if (party == null) {
			return null;
		}
		if (party.getAddress() == null) {
			return null;
		}
		TsSUA sua = new TsSUA();
		sua.setBeznr(message.getReferenceNumber());	
		
		String name = party.getAddress().getName();		
		if (name != null && name.length() > 0) {
			int len = name.length();
			if (len < 36) {
				sua.setName1(name);
			} else if (len < 71) {
				sua.setName1(name.substring(0, 35));
				sua.setName2(name.substring(35));
			} else {
				sua.setName1(name.substring(0, 35));
				sua.setName2(name.substring(35, 70));
				sua.setName3(name.substring(70));
			}
		}
		sua.setStr(party.getAddress().getStreet());
		sua.setOrt(party.getAddress().getCity());
		sua.setPlz(party.getAddress().getPostalCode());
		sua.setLand(party.getAddress().getCountry());
		sua.setOteil(party.getAddress().getDistrict());
		
		return sua;
	}	
	
	private TsSUP mapSUP(GoodsItem item) {
		if (item == null) {
			return null;
		}
		TsSUP sup = new TsSUP();
		
		sup.setBeznr(message.getReferenceNumber());
		sup.setPosnr(item.getItemNumber());
		sup.setKzbest(item.getConfirmationCode());
		if (item.getItemExtension() != null) {		
			sup.setKzuvwm(item.getItemExtension().getTemporaryStorageCode());	
		}		
		sup.setVland(item.getCountryOfDispatch());		
		sup.setBesort(item.getDestinationPlace());
		if (!Utils.isStringEmpty(item.getCustomsStatusOfGoods())) {
			/*	EI20131025:		
			sup.setZollst(this.this.calculateCustomsStatus(item.getCustomsStatusOfGoods().trim()));   //EI20131007
			die umsetztng findet im cmp2kids nicht hier statt, da dies fuer mehrere Kunden gelten muss			
			*/
			sup.setZollst(item.getCustomsStatusOfGoods().trim());   //EI20131025
		}
		if (item.getReferencedSpecification() != null) {	//EI20130201
			sup.setKzawb(item.getReferencedSpecification().getTypeOfSpecificationID());
			sup.setSpo(item.getReferencedSpecification().getSpecificationID());
		} 
		if (item.getPackagesList() != null) {
			for (Packages pack : item.getPackagesList()) {
				   if (pack != null) {
					   sup.setColart(pack.getType());	
					   sup.setColanz(pack.getQuantity());	
				   }
				   break;  //in Zabis nur ein Pack pro SUP
			   }
		}
		sup.setRohm(Utils.addZabisDecimalPlaceV7(item.getGrossMass(), 3));
		if (subversion.equals("2001")) {
			sup.setRohm(item.getGrossMass());	 				
		}				
		sup.setWabes(item.getItemDescription());
		sup.setWakr(item.getRangeOfGoodsCode());

		if (item.getCustodian() != null && item.getCustodian().getPartyTIN() != null) {	//Verwahrer
			sup.setVrweori(item.getCustodian().getPartyTIN().getTIN());
			sup.setVrwnlnr(item.getCustodian().getPartyTIN().getBO());
			sup.setVrwkdnr(item.getCustodian().getPartyTIN().getCustomerIdentifier());
		}
		sup.setVrwort(item.getPlaceOfCustodyCode());
		
		if (item.getDisposal() != null && item.getDisposal().getPartyTIN() != null) {	//Verfügungsberechtigter
			sup.setVfbeori(item.getDisposal().getPartyTIN().getTIN());
			sup.setVfbnlnr(item.getDisposal().getPartyTIN().getBO());
			sup.setVfbkdnr(item.getDisposal().getPartyTIN().getCustomerIdentifier());
		}
			
		if (item.getCustomsNotification() != null) {
			sup.setEmrn(item.getCustomsNotification().getContent());
			sup.setEmrnpos(item.getCustomsNotification().getItemNumber());
		}
		sup.setKzfrei(item.getFreezoneFlag());
		
		return sup;
		
	}

}
