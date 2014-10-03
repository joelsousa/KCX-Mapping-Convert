package com.kewill.kcx.component.mapping.countries.de.suma70.kids2fss;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.suma70.msg.MsgNotificationOfPresentationConfirmed;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.GoodsItem;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.ManifestReference;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.suma.messages.V70.MsgSBK;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.TsSBK;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.TsSBP;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.TsSBR;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageManifest20;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: Manifest<br>
 * Created		: 31.05.2013<br>
 * Description	: Mapping of KIDS MsgNotificationOfPresentationConfirmed to FSS SBK.
 * 
 * @author krzoska
 * @version 2.0.00
 */
public class MapNotificationOfPresentationConfirmedToSBK extends KidsMessageManifest20 {
	
	private MsgNotificationOfPresentationConfirmed message;
	private MsgSBK msgSBK;
	private String subversion = "";       //EO20130510
	
	public MapNotificationOfPresentationConfirmedToSBK(XMLEventReader parser, TsHead tsHead) throws XMLStreamException {		
		message = new MsgNotificationOfPresentationConfirmed(parser);
		msgSBK = new MsgSBK();		
		msgSBK.setHeadSubset(tsHead);
	}
	
	public String getMessage() {
    	String res = "";    	
    	    	
        try {   
        	if (this.getKidsHeader() != null) {                   //EI20120206:
        		if (!Utils.isStringEmpty(this.getKidsHeader().getRelease())) {   //EI20130422
    				subversion = Utils.removeDots(this.getKidsHeader().getRelease());
    			}
        	}
        	message.parse(HeaderType.KIDS);         	
            getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
            
            // read MessageID from KidsHeader.
            msgSBK.getHeadSubset().setMsgid(getKidsHeader().getMessageID());
            msgSBK.setSbkSubset(mapSBK());
            msgSBK.setSbrSubset(mapSBR(message.getReference()));
            
            if (message.getGoodsItemList() != null) {            	
			   for (GoodsItem item : message.getGoodsItemList()) {
				   if (item != null) {
					   msgSBK.addSbpList(mapSBP(item));
				   }
			   }
            }
         
            res = msgSBK.getFssString();
           
            Utils.log("(MapNotificationOfPresentationConfirmedToSBK getMessage) Msg = " + res);
		
	    } catch (FssException e) {	    	
	        e.printStackTrace();	        
	    }
		    
	    return res;
	}
	
	private TsSBK mapSBK() {
		TsSBK sbk = new TsSBK();

	
		sbk.setBeznr(message.getReferenceNumber());
		if (!Utils.isStringEmpty(message.getDateOfPresentation())) {
			if (message.getDateOfPresentation().length() >= 8) {
				sbk.setGstdat(message.getDateOfPresentation().substring(0, 8));
			}
		}
		
		sbk.setAnzcon(message.getContainerQuantity());
		sbk.setBelo(message.getPlaceOfLoading());
		if (message.getAgentTIN() != null) {
			sbk.setVtrkdnr(message.getAgentTIN().getCustomerIdentifier());
			sbk.setVtreori(message.getAgentTIN().getTIN());
			sbk.setVtrnlnr(message.getAgentTIN().getBO());
		}
		sbk.setIdart(message.getReferenceIdentifier());
		sbk.setKzncts(message.getNctsID());
		sbk.setAnkdat(message.getDateOfArrival());
		if (message.getHeaderExtensions() != null) {
			sbk.setSeekz(message.getHeaderExtensions().getMaritimTrafficID());
		}
		if (message.getPresenter() != null && message.getPresenter().getPartyTIN() != null) {
			sbk.setGsteori(message.getPresenter().getPartyTIN().getTIN());
			sbk.setGstkdnr(message.getPresenter().getPartyTIN().getCustomerIdentifier());
			sbk.setGstnlnr(message.getPresenter().getPartyTIN().getBO());
		}
		if (message.getCustomsOffices() != null) {
			sbk.setAndst(message.getCustomsOffices().getOfficeOfPresentation());	
		}
		if (message.getPreviousDocument() != null) {
			sbk.setVorart(message.getPreviousDocument().getType());
			sbk.setVornr(message.getPreviousDocument().getReference());
		}
		if (message.getTransport() != null) {
			sbk.setBefart(message.getTransport().getTransportMode());	
			//sbk.setBefkz(message.getTransport().getIdentity()); //bei befart 04 nicht füllen!	
			sbk.setBefson(message.getTransport().getDescription());	
			sbk.setBefnum(message.getTransport().getTransportationNumber());    //ersetzt wohl den FlugNumer
		}
		if (message.getContact() != null) {
			sbk.setSb(message.getContact().getIdentity());	
			sbk.setSbname(message.getContact().getName());	
			sbk.setSbdstl(message.getContact().getPosition());	
			sbk.setSbtel(message.getContact().getPhoneNumber());	
		}
		if (message.getTransportAtBorder() != null) {    //EI20130716
			sbk.setVkzwg(message.getTransportAtBorder().getTransportMode());	 	
		}
		sbk.setKzeindst(message.getSumACustomsOfficeIsOfficeIsOfFirstEntry());  //EI20130716
		
		return sbk;
	}
	
	private TsSBR mapSBR(ManifestReference ref) {
		if (ref == null) {
			return null;
		}
		TsSBR sbr = new TsSBR();		
		sbr.setBeznr(message.getReferenceNumber());
		sbr.setIdregnr(ref.getRegistrationNumber());
		sbr.setIdfltblo(ref.getPlaceOfLoading());
		if (ref.getPreviousDocument() != null) {
			sbr.setIdfltvpa(ref.getPreviousDocument().getType());	
		}		
	
		if (this.msgSBK != null && this.msgSBK.getSbkSubset() != null) {    //EI20130716
			sbr.setBefnum(this.msgSBK.getSbkSubset().getBefnum());  
			sbr.setAnkdat(this.msgSBK.getSbkSubset().getAnkdat());  
		}
		return sbr;
	}
	
	private TsSBP mapSBP(GoodsItem item) {
		if (item == null) {
			return null;
		}
		TsSBP sbp = new TsSBP();
		
		sbp.setBeznr(message.getReferenceNumber());
		sbp.setPosnr(item.getItemNumber());
		sbp.setKzbest(item.getConfirmationCode());
		if (item.getItemExtension() != null) {		
			sbp.setKzuvwm(item.getItemExtension().getTemporaryStorageCode());	
		}	
		sbp.setVland(item.getCountryOfDispatch());		
		sbp.setBesort(item.getDestinationPlace());
		//sbp.setZollst(item.getCustomsStatusOfGoods());
		if (!Utils.isStringEmpty(item.getCustomsStatusOfGoods())) {
			/* EI20131025: 				
			sbp.setZollst(this.calculateCustomsStatus(item.getCustomsStatusOfGoods().trim()));   //EI20131007
			die umsetztng findet im cmp2kids nicht hier statt, da dies fuer mehrere Kunden gelten muss		
			*/
			sbp.setZollst(item.getCustomsStatusOfGoods().trim()); //EI20131025
		}
		if (item.getReferencedSpecification() != null) {	//EI20130201
			sbp.setKzawb(item.getReferencedSpecification().getTypeOfSpecificationID());
			sbp.setSpo(item.getReferencedSpecification().getSpecificationID());
		} 
		if (item.getPackagesList() != null && item.getPackagesList().get(0) != null) {
			sbp.setColart(item.getPackagesList().get(0).getType());	
			sbp.setColanz(item.getPackagesList().get(0).getQuantity());	
		}			 												
		sbp.setRohm(Utils.addZabisDecimalPlaceV7(item.getGrossMass(), 3)); //V70 Kids as decimal
		if (subversion.equals("2001")) {     
			sbp.setRohm(item.getGrossMass());
		}
		
		sbp.setWabes(item.getItemDescription());
		sbp.setWakr(item.getRangeOfGoodsCode());
		

		if (item.getCustodian() != null && item.getCustodian().getPartyTIN() != null) {	//Verwahrer
			sbp.setVrwkdnr(item.getCustodian().getPartyTIN().getCustomerIdentifier());
			sbp.setVrweori(item.getCustodian().getPartyTIN().getTIN());
			sbp.setVrwnlnr(item.getCustodian().getPartyTIN().getBO());
		}
		sbp.setVrwort(item.getPlaceOfCustodyCode());
		
		if (item.getDisposal() != null && item.getDisposal().getPartyTIN() != null) {	//Verfügungsberechtigter
			sbp.setVfbkdnr(item.getDisposal().getPartyTIN().getCustomerIdentifier());
			sbp.setVfbeori(item.getDisposal().getPartyTIN().getTIN());
			sbp.setVfbnlnr(item.getDisposal().getPartyTIN().getBO());

		}
				
		if (item.getCustomsNotification() != null) {          //EI20130716
			sbp.setEmrn(item.getCustomsNotification().getContent());
			sbp.setEmrnpos(item.getCustomsNotification().getItemNumber());
		}
		sbp.setKzfrzone(item.getFreezoneFlag());

		return sbp;
		
	}

}
