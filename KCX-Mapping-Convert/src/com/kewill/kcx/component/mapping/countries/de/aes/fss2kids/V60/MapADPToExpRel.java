/*
 * Function    : MapADPToExpRel.java
 * Titel       :
 * Date        : 02.10.2008
 * Author      : Kewill CSF / iwaniuk
 * Description : Mapping of KIDS-Format into FSS-Format ADI 
 * 			   : 
 * Parameters  : 

 * Changes 
 * -----------
 * Author      : EI
 * Date        : 03.03.2009
 * Label       :
 * Description : Version 6 - move and rename of MapADPToExpRel_V6.java
 *             :             from package '...fss2kids' into '...fss2kids.v6'
 * Author      : EI
 * Date        : 08.05.2009
 * Label       :
 * Description : replaced BodyExportADPtoReverseDeclarationKids with 
 *               BodyReverseDeclarationKids
 *                              		
 * Author      : AK
 * Date        : 12.05.2009
 * Label       : AK20090512
 * Description : tmpPdf.setPdflist(msgADP.getPdfInformation().getPdflist());
 *  			 
 * Author      : AK
 * Date        : 13.05.2009
 * Label       : AK20090513
 * Description : msgExpRel.setPdfInformation(msgADP.getPdfInformation());
 *                
 * Author      : EI
 * Date        : 25.05.2009 
 * Description : PlaceOfLoading wieder aktiviert    
 * 
 * Author      : EI
 * Label       : EI20090608
 * Description : ContactPerson instead of clerk  
 * 
 * Author      : EI
 * Label       : EI20100607
 * Description : new member: exitTime           
 */

package com.kewill.kcx.component.mapping.countries.de.aes.fss2kids.V60;

import java.io.StringWriter;
import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.Container;
import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.Seal;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpRel;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpRelPos;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ApprovedTreatment;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Business;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CommodityCode;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Completion;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CompletionItem;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ExportRefundHeader;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ExportRefundItem;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.IncoTerms;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Ingredients;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.LoadingTime;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PlaceOfLoading;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Route;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Statistic;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.WriteDownAmount;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60.MsgADP;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60.MsgADPos;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsABF;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsACN;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsACO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsADR;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAED;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsATI;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAVS;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAZV;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsBAV;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsBZL;
import com.kewill.kcx.component.mapping.formats.kids.aes.BodyReverseDeclarationKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: MapADPToExpRel<br>
 * Erstellt		: 01.04.2009<br>
 * Beschreibung	: Mapping of KIDS-Format into FSS-Format ADI.
 * 
 * @author iwaniuk
 * @version 6.0.00
 */
public class MapADPToExpRel extends KidsMessage {
	private MsgADP msgADP;
	private MsgExpRel msgExpRel;
	
	public MapADPToExpRel() {
		msgExpRel = new MsgExpRel();		
	}

	public void setMsgADP(MsgADP argument) {
		
		this.msgADP = argument;						
    	this.setMsgFields();    	
    }
	
	public String getMessage() {
	    StringWriter xmlOutputString = new StringWriter();
	    
	    XMLOutputFactory factory = XMLOutputFactory.newInstance();
	    try {
	        writer = factory.createXMLStreamWriter(xmlOutputString);

	        writeStartDocument(encoding, "1.0");
	        openElement("soap:Envelope");
	        setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
	        
	        KidsHeader  header = new KidsHeader(writer);
	        
	        CommonFieldsDTO commonFieldsDTO = new CommonFieldsDTO();
	        commonFieldsDTO.setKcxId(header.getReceiver());
	        commonFieldsDTO.setCountryCode(header.getCountryCode());
	        commonFieldsDTO.setDirection(EDirections.CountryToCustomer);
	        header.setCommonFieldsDTO(commonFieldsDTO);
	        
	        header.setHeaderFields(msgADP.getVorSubset());
	        header.setMessageName("ReverseDeclaration");
	        header.setMessageID(getMsgID());
	        
	        header.writeHeader();
	        
	        //BodyExportADPtoReverseDeclarationKids body   = new BodyExportADPtoReverseDeclarationKids(writer);
	        BodyReverseDeclarationKids body   = new BodyReverseDeclarationKids(writer); //EI20090508
	        body.setMessage(msgExpRel);
	        body.setKidsHeader(header);

	        getCommonFieldsDTO().setReferenceNumber(msgExpRel.getReferenceNumber());
	        body.writeBody();
	        
	        closeElement();  // soap:Envelope
	        writer.writeEndDocument();
	        
	        writer.flush();
	        writer.close();

	        if (Config.getLogXML()) {
	            Utils.log("(MapADPToExpRel getMessage) Msg = " + xmlOutputString.toString());
	        }
	        
	    	} catch (XMLStreamException e) {
	        
	    		e.printStackTrace();
	    	}
	    
	    	return xmlOutputString.toString();
		}
		
	
	public void setMsgFields() {
		if (msgADP.getDatSubset() != null) {
			msgExpRel.setKindOfAnswer(msgADP.getDatSubset().getKzart());
			//V6: msgExpRel.setDeclarationKind(kein fss_xyz);		
			msgExpRel.setUCRNumber(msgADP.getDatSubset().getMrn());
			msgExpRel.setReferenceNumber(msgADP.getDatSubset().getBeznr());	
			msgExpRel.setOrderNumber(msgADP.getDatSubset().getKuatnr());	
						
			msgExpRel.setCustomsOfficeExport(msgADP.getDatSubset().getExpdst());
			msgExpRel.setCustomsOfficeForCompletion(msgADP.getDatSubset().getEamdst());
			/*
			String tmpForwarderId = msgADP.getDatSubset().getKdnrsp(); //wird in ADP nicht mehr verwendet
			String tmpForwarderETN = msgADP.getDatSubset().getEtnsp(); //wird in ADP nicht mehr verwendet
			if (tmpForwarderId != null || tmpForwarderETN != null) {
				Party tmpParty = new Party();
				TIN tmpTIN = new TIN();
				tmpTIN.setCustomerIdentifier(tmpForwarderId);
				tmpParty.setETNAddress(tmpForwarderETN);
				msgExpRel.setForwarder(tmpParty);
				msgExpRel.setForwarderTIN(tmpTIN);
			}
			*/
			String tmpConsigneeId = msgADP.getDatSubset().getKdnrem();
			String tmpConsigneeTinNr = msgADP.getDatSubset().getTinem();
			if (!Utils.isStringEmpty(tmpConsigneeId) || 					
				!Utils.isStringEmpty(tmpConsigneeTinNr)) {
				Party tmpParty = msgExpRel.getConsignee();
				if (tmpParty == null) {
                    tmpParty = new Party();
				}
				TIN tmpTin = tmpParty.getPartyTIN();
				if (tmpTin == null) {
                    tmpTin = new TIN();
				}
				tmpTin.setCustomerIdentifier(tmpConsigneeId);
				tmpTin.setTIN(tmpConsigneeTinNr);
				tmpParty.setPartyTIN(tmpTin);
				msgExpRel.setConsignee(tmpParty);
			}
						
			String tmpDeclarantId = msgADP.getDatSubset().getKdnran();
			//EI20090421: String tmpDeclarantETN = msgADP.getDatSubset().getEtnan();  //? prüfen: 
			String tmpDeclarantTinNr = msgADP.getDatSubset().getTinan();
			String tmpDeclarantTinIs = msgADP.getDatSubset().getDtzoan();
			if (!Utils.isStringEmpty(tmpDeclarantId) || 
				!Utils.isStringEmpty(tmpDeclarantTinNr) || 
				!Utils.isStringEmpty(tmpDeclarantTinIs)) {
				Party tmpParty = msgExpRel.getDeclarant();	
				if (tmpParty == null) {
                    tmpParty = new Party();
				}
				//EI20090421: tmpParty.setETNAddress(tmpDeclarantETN);
				TIN tmpTin = tmpParty.getPartyTIN();
				if (tmpTin == null) {
                    tmpTin = new TIN();
				}
				tmpTin.setTIN(tmpDeclarantTinNr);
				tmpTin.setIsTINGermanApprovalNumber(tmpDeclarantTinIs);
				tmpTin.setCustomerIdentifier(tmpDeclarantId);
				tmpParty.setPartyTIN(tmpTin);
				msgExpRel.setDeclarant(tmpParty);				
			}		
		}
		
		if (msgADP.getEdaSubset() != null) {
			
			msgExpRel.setAreaCode(msgADP.getEdaSubset().getArtaus());	
			msgExpRel.setTypeOfPermit(msgADP.getEdaSubset().getArtueb());
			msgExpRel.setDeclarationTime(msgADP.getEdaSubset().getAnmdat());
			//msgExpRel.setDeclarationNumberForwarder(kein fss_xyz);
			//msgExpRel.setDeclarationNumberCustoms(kein fss_xyz);
			msgExpRel.setDispatchCountry(msgADP.getEdaSubset().getLdve());
			msgExpRel.setDestinationCountry(msgADP.getEdaSubset().getLdbe());
			//msgExpRel.setPreAnnouncementTime(msgADP.getEdaSubset().getInddat()); 
			msgExpRel.setTransportInContainer(msgADP.getEdaSubset().getConkz());
			msgExpRel.setGrossMass(msgADP.getEdaSubset().getGsroh());
			msgExpRel.setUCROtherSystem(msgADP.getEdaSubset().getFregnr());
			msgExpRel.setAnnotation(msgADP.getEdaSubset().getVerm());		
			msgExpRel.setRealOfficeOfExit(msgADP.getEdaSubset().getExtdst());
			
			PlaceOfLoading tmpPoLoading = new PlaceOfLoading();       
			tmpPoLoading.setCode(msgADP.getEdaSubset().getLdocde());
			tmpPoLoading.setStreet(msgADP.getEdaSubset().getBeostr());
			tmpPoLoading.setPostalCode(msgADP.getEdaSubset().getBeoplz());
			tmpPoLoading.setCity(msgADP.getEdaSubset().getBeoort());
			tmpPoLoading.setAgreedLocationOfGoods(msgADP.getEdaSubset().getBeozus());
			msgExpRel.setPlaceOfLoading(tmpPoLoading);			 			 
		
			String tmpConsignorId = msgADP.getEdaSubset().getKdnrau();
			String tmpConsignorTinIs = msgADP.getEdaSubset().getDtzoau();
			String tmpConsignorTinNr = msgADP.getEdaSubset().getTinau();
			if (!Utils.isStringEmpty(tmpConsignorId) || 
				!Utils.isStringEmpty(tmpConsignorTinIs) ||
			    !Utils.isStringEmpty(tmpConsignorTinNr)) {
				Party tmpParty = msgExpRel.getConsignor();
				if (tmpParty == null) {
                    tmpParty = new Party();
				}
				TIN tmpTin = tmpParty.getPartyTIN();
				if (tmpTin == null) {
                    tmpTin = new TIN();
				}
				tmpTin.setTIN(tmpConsignorTinNr);
				tmpTin.setIsTINGermanApprovalNumber(tmpConsignorTinIs);	
				tmpTin.setCustomerIdentifier(tmpConsignorId);	
				tmpParty.setPartyTIN(tmpTin);
				msgExpRel.setConsignor(tmpParty);				
			}	
			
			String tmpAgentId = msgADP.getEdaSubset().getKdnrva();
			//EI20090421: String tmpAgentETN = msgADP.getEdaSubset().getEtnva();
			String tmpAgentTinIs = msgADP.getEdaSubset().getDtzova();
			String tmpAgentTinNr = msgADP.getEdaSubset().getTinva();	//? prüfen
			if (!Utils.isStringEmpty(tmpAgentId) ||
		    	!Utils.isStringEmpty(tmpAgentTinIs) || 
		    	!Utils.isStringEmpty(tmpAgentTinNr)) {
				Party tmpParty = msgExpRel.getAgent();
				if (tmpParty == null) {
                    tmpParty = new Party();
				}
				TIN tmpTin = tmpParty.getPartyTIN();
				if (tmpTin == null) {
                    tmpTin = new TIN();
				}
				//EI20090421: tmpParty.setETNAddress(tmpAgentETN);
				tmpTin.setTIN(tmpAgentTinNr);
				tmpTin.setIsTINGermanApprovalNumber(tmpAgentTinIs);	
				tmpTin.setCustomerIdentifier(tmpAgentId);
				tmpParty.setPartyTIN(tmpTin);				
				msgExpRel.setAgent(tmpParty);					
			}	
			
			String tmpSubcontractorId = msgADP.getEdaSubset().getKdnrsu();
			String tmpSubcontractorETN = msgADP.getEdaSubset().getEtnsu();
			String tmpSubcontractorTinIs = msgADP.getEdaSubset().getDtzosu();
			String tmpSubcontractorTinNr = msgADP.getEdaSubset().getTinsu();	
			if (!Utils.isStringEmpty(tmpSubcontractorId) || 
				!Utils.isStringEmpty(tmpSubcontractorETN) || 
				!Utils.isStringEmpty(tmpSubcontractorTinIs) || 
				!Utils.isStringEmpty(tmpSubcontractorTinNr)) {			
				Party tmpParty = msgExpRel.getSubcontractor();	
				if (tmpParty == null) {
                    tmpParty = new Party();
				}
				TIN tmpTin = tmpParty.getPartyTIN();
				if (tmpTin == null) {
                    tmpTin = new TIN();
				}
				tmpParty.setETNAddress(tmpSubcontractorETN);				
				tmpTin.setTIN(tmpSubcontractorTinNr);
				tmpTin.setIsTINGermanApprovalNumber(tmpSubcontractorTinIs);
				tmpTin.setCustomerIdentifier(tmpSubcontractorId);
				tmpParty.setPartyTIN(tmpTin);
				msgExpRel.setSubcontractor(tmpParty);
			}			
		}	
		
		
				
		if (msgADP.getDaeSubset() != null) {
			msgExpRel.setShortageInQuantity(msgADP.getDaeSubset().getKzmin());
			msgExpRel.setTotalNumberPositions(msgADP.getDaeSubset().getAnzpos()); //? prüfen
			msgExpRel.setTotalNumberPackages(msgADP.getDaeSubset().getAnzcol());   //? prüfen
			msgExpRel.setAlternativeDocument(msgADP.getDaeSubset().getAltnot());
			}	
		
		if (msgADP.getAspSubset() != null) {
		//EI20090306: msgExpRel.setCancellationTime(msgADP.getAspSubset().getStodat());			
		msgExpRel.setExitTime(msgADP.getAspSubset().getExtdat());         //EI20100607
		msgExpRel.setReleaseTime(msgADP.getAspSubset().getUebdat());
		msgExpRel.setAcceptanceTime(msgADP.getAspSubset().getAndat());
		msgExpRel.setReceiveTime(msgADP.getAspSubset().getAckdat());		
		//EI20090306: msgExpRel.setTimeOfRejection(msgADP.getAspSubset().getGstdat());
		//EI20090306: msgExpRel.setBeginTimeOfProcessing(msgADP.getAspSubset().getBwbdat());
		//EI20090306: wird hier nicht ausgegeben
		//msgExpRel.setAuthorizationNumber(msgADP.getEdaSubset().getBewnr());
		//EI20090306: wird in ADP nicht mehr verwendet
		//msgExpRel.setPreviousProcedure(msgADP.getDatSubset().getQuelkz());
		msgExpRel.setReceiverCustomerNumber(msgADP.getDatSubset().getVekan());		
		msgExpRel.setDeclarantIsConsignor(msgADP.getEdaSubset().getKzanau());
		//EI090608: msgExpRel.setClerk(msgADP.getDatSubset().getSb());		
		ContactPerson tmpContact = new ContactPerson();
		tmpContact.setIdentity(msgADP.getDatSubset().getSb());
		msgExpRel.setContact(tmpContact);	//EI090608
		
		LoadingTime tmpLoadingTime = new LoadingTime();
		tmpLoadingTime.setBeginTime(msgADP.getAspSubset().getGststr());
		tmpLoadingTime.setEndTime(msgADP.getAspSubset().getGstend());
		msgExpRel.setLoadingTime(tmpLoadingTime);  		
		}
		
		//AK20090513
		msgExpRel.setPdfInformation(msgADP.getPdfInformation());
		/***
		if (msgADP.getAmpSubset() != null) {  //soll ausgegeben werden? in excel nicht zugeordnet!
			PDFInformation tmpPdf = new PDFInformation();
			
			tmpPdf.setName(msgADP.getAmpSubset().getPdfnam());
			tmpPdf.setDirectory(msgADP.getAmpSubset().getPdfpfd());	
			tmpPdf.setNewDirectory(msgADP.getAmpSubset().getSubdir());
			//AK20090512
			if (msgADP.getPdfInformation() != null) {
				tmpPdf.setPdflist(msgADP.getPdfInformation().getPdflist());
			}
			
			msgExpRel.setPdfInformation(tmpPdf);
		}
		****/
		if (msgADP.getSasSubset() != null) {
			//erst ab V.6
			msgExpRel.setSituationCode(msgADP.getSasSubset().getBesust());	
			msgExpRel.setPaymentType(msgADP.getSasSubset().getBfgkzw());			
			msgExpRel.setShipmentNumber(msgADP.getSasSubset().getKnrsdg());
		}		
		
		if (msgADP.getAdrList() != null) {
			for (int i = 0, listSize = msgADP.getAdrList().size(); i < listSize; i++) {
				TsADR tmpTsAdr = (TsADR) msgADP.getAdrList().get(i);
                Address tmpAdr = new Address();
                String tmpName = tmpTsAdr.getName1() + " " + 
                                 tmpTsAdr.getName2() + " " + 
                                 tmpTsAdr.getName3();
                tmpAdr.setName(tmpName);
                tmpAdr.setStreet(tmpTsAdr.getStr());
                tmpAdr.setCity(tmpTsAdr.getOrt());
                tmpAdr.setPostalCode(tmpTsAdr.getPlz());    
                tmpAdr.setCountry(tmpTsAdr.getLand());                  
				
                ContactPerson tmpCoPe = new ContactPerson();
                tmpCoPe.setPosition(tmpTsAdr.getSbstlg());
                tmpCoPe.setClerk(tmpTsAdr.getSbname());
                tmpCoPe.setEmail(tmpTsAdr.getEmail());
                tmpCoPe.setFaxNumber(tmpTsAdr.getFaxnr());                  
                tmpCoPe.setPhoneNumber(tmpTsAdr.getTelnr());

                String typ = tmpTsAdr.getTyp();
				if (typ.equals("1")) {
					//Consignor					
					Party tmpParty = msgExpRel.getConsignor();
					if (tmpParty == null) {
                        tmpParty = new Party();
					}
					tmpParty.setAddress(tmpAdr);
					tmpParty.setContactPerson(tmpCoPe);
					msgExpRel.setConsignor(tmpParty);					
					
				} else if (typ.equals("2")) {
					//Consignee							
					Party tmpParty = msgExpRel.getConsignee();
					if (tmpParty == null) {
                        tmpParty = new Party();                 
					}
					
					tmpParty.setAddress(tmpAdr);
					//tmpParty.setContactPerson(tmpCoPe);
					msgExpRel.setConsignee(tmpParty);					
					
				} else if (typ.equals("3")) {
					//Declarant					
					Party tmpParty = msgExpRel.getDeclarant();
					if (tmpParty == null) {
                        tmpParty = new Party();
					}
					tmpParty.setAddress(tmpAdr);
					tmpParty.setContactPerson(tmpCoPe);
					msgExpRel.setDeclarant(tmpParty);
					
				} else if (typ.equals("4")) {
					//Agent					
					Party tmpParty = msgExpRel.getAgent();
					if (tmpParty == null) {
                        tmpParty = new Party();                 
					}
					
					tmpParty.setAddress(tmpAdr);
					tmpParty.setContactPerson(tmpCoPe);
					msgExpRel.setAgent(tmpParty);
					
				} else if (typ.equals("5")) {
					//Subcontractor					
					Party tmpParty = msgExpRel.getSubcontractor();
					if (tmpParty == null) {
                        tmpParty = new Party();                     
					}
					
					tmpParty.setAddress(tmpAdr);
					tmpParty.setContactPerson(tmpCoPe);
					msgExpRel.setSubcontractor(tmpParty);					
				}
			}
		}	
				
		if (msgADP.getEamSubset() != null) {
			msgExpRel.setCompletionTime(msgADP.getEamSubset().getEamdat());
		
			TransportMeans tmpInland = new TransportMeans();
			tmpInland.setTransportMode(msgADP.getEamSubset().getBfvkzi());
			msgExpRel.setTransportMeansInland(tmpInland);	
			//TransportMeans tmpDeparture = new TransportMeans();
			//tmpDeparture.setTransportMode(msgADP.getEamSubset().getBfvkzi());		
			//msgExpRel.setTransportMeansDeparture(tmpDeparture);			
			TransportMeans tmpBorder = new TransportMeans();
			tmpBorder.setTransportMode(msgADP.getEamSubset().getBfvkzg());
			tmpBorder.setTransportationType(msgADP.getEamSubset().getBfartg());		
			tmpBorder.setTransportationNumber(msgADP.getEamSubset().getBfkzg());		
			tmpBorder.setTransportationCountry(msgADP.getEamSubset().getBfnatg());		
			msgExpRel.setTransportMeansBorder(tmpBorder);
			
			Business tmpBusiness = new Business();
			tmpBusiness.setBusinessTypeCode(msgADP.getEamSubset().getGesart());		
			tmpBusiness.setInvoicePrice(msgADP.getEamSubset().getGesprs());	
			tmpBusiness.setCurrency(msgADP.getEamSubset().getGeswrg());	
			msgExpRel.setBusiness(tmpBusiness);
			
			IncoTerms tmpIncoTerms  = new IncoTerms();
			tmpIncoTerms.setIncoTerm(msgADP.getEamSubset().getLibart());
			tmpIncoTerms.setText(msgADP.getEamSubset().getLibinc());
			tmpIncoTerms.setPlace(msgADP.getEamSubset().getLibort());		
			msgExpRel.setIncoTerms(tmpIncoTerms);					

		}
				
		if (msgADP.getAbfList() != null) {
			Route tmpRoute = msgExpRel.getRoute();
			if (tmpRoute == null) {
                tmpRoute = new Route();
			}
			
			List<TsABF> tmpAbfList = msgADP.getAbfList();
			
			for (int i = 0, abfSize = tmpAbfList.size(); i < abfSize; i++) {
				TsABF tmpAbf = new TsABF();
				tmpAbf = (TsABF) tmpAbfList.get(i);
				tmpRoute.addCountryList(tmpAbf.getLdbf());					
			}			
			msgExpRel.setRoute(tmpRoute);	
		}
		
		if (msgADP.getEdaSubset() != null || (msgADP.getAvsList() != null)) {
			Seal tmpSeal = new Seal();
			if (msgADP.getEdaSubset() != null) {
				tmpSeal.setKind(msgADP.getEdaSubset().getVsart());
				tmpSeal.setNumber(msgADP.getEdaSubset().getAnzve());	
				tmpSeal.setUseOfTydenseals(msgADP.getEdaSubset().getKztyd());	
				//EI20090306: tmpSeal.setUseOfTydensealStock(msgADP.getEdaSubset().getKzstock());
			}
			if (msgADP.getAvsList() != null) {
				List<TsAVS> tmpAvsList = new Vector<TsAVS>();
				tmpAvsList = msgADP.getAvsList();
								
				for (int i = 0, listSize = tmpAvsList.size(); i < listSize; i++) {
					TsAVS tmpAvs = new TsAVS();
					tmpAvs = (TsAVS) tmpAvsList.get(i);
					String tmpSealNr = tmpAvs.getSeal();					
					tmpSeal.addSealNumberList(tmpSealNr);
				}					
			}
			msgExpRel.setSeal(tmpSeal);
		}
		
		if (msgADP.getAtkSubset() != null) {
			//erst ab V.6
			ExportRefundHeader tmpERHeader = new ExportRefundHeader();
			tmpERHeader.setText(msgADP.getAtkSubset().getText());
			tmpERHeader.setPaymentKids(msgADP.getAtkSubset().getAsart());
			tmpERHeader.setBankNumber(msgADP.getAtkSubset().getAszweg());
			tmpERHeader.setAssignee(msgADP.getAtkSubset().getAszsbv());
			tmpERHeader.setGuarantee(msgADP.getAtkSubset().getAsskto());
			tmpERHeader.setReservationCode(msgADP.getAtkSubset().getAskvb());
			tmpERHeader.setDestinationCountry(msgADP.getAtkSubset().getAsldbe());
			msgExpRel.setExportRefundHeader(tmpERHeader);
		}		
			
		if (msgADP.getAedList() != null) {	
			List<TsAED> tmpAedList = new Vector<TsAED>();
			tmpAedList = msgADP.getAedList();
			
			for (int i = 0, listSize = tmpAedList.size(); i < listSize; i++) {
				TsAED tmpAed = new TsAED();
				tmpAed = (TsAED) tmpAedList.get(i);
				
				Document tmpDocument  = new Document();
				tmpDocument.setMsgFlag("K");
				tmpDocument.setQualifier(tmpAed.getUntqar());
				tmpDocument.setTypeKids(tmpAed.getUntart());	
				tmpDocument.setReference(tmpAed.getUntnr());	
				tmpDocument.setAdditionalInformation(tmpAed.getUntzus());	
				tmpDocument.setDetail(tmpAed.getDetail());	
				tmpDocument.setIssueDate(tmpAed.getUntdat());	
				tmpDocument.setExpirationDate(tmpAed.getGbdat());	
				tmpDocument.setValue(tmpAed.getWert());
				
		
				WriteDownAmount tmpWDAmount = new WriteDownAmount("K");
				//tmpWDAmount.setQualifier(msgADP.getAedSubset().getMgeqme());
				tmpWDAmount.setUnitOfMeasurement(tmpAed.getMgeme());
				//EI20090818: tmpWDAmount.setValueKids(tmpAed.getAbgwrt());
				tmpWDAmount.setWriteoffValue(tmpAed.getAbgwrt()); //EI20090818
				tmpDocument.setWriteDownAmount(tmpWDAmount);
				msgExpRel.addDocumentList(tmpDocument);
		    }			
		}
		
		List <MsgADPos> adpPosList = msgADP.getPosList();				
		if (adpPosList != null) {
			
			for (int i = 0, listSize = adpPosList.size(); i < listSize; i++) {
				MsgADPos tmpAdpPos = (MsgADPos) adpPosList.get(i);
				msgExpRel.addGoodsItemList(setExpRelPosition(tmpAdpPos));
			}
		}
    }
	
    private MsgExpRelPos setExpRelPosition(MsgADPos position) {
    	if (position == null) {
    		return null;
    	}
    	
    	MsgExpRelPos msgExpRelPos = new MsgExpRelPos();
    	
     	if (position.getApoSubset() != null) {
     		msgExpRelPos.setItemNumber(position.getApoSubset().getPosnr());      	
     		msgExpRelPos.setArticleNumber(position.getApoSubset().getArtnr());
     		msgExpRelPos.setDescription(position.getApoSubset().getWbsch());
     		msgExpRelPos.setUCROtherSystem(position.getApoSubset().getFregnr());
     		msgExpRelPos.setAnnotation(position.getApoSubset().getVerm());     		
      		msgExpRelPos.setOriginFederalState(position.getApoSubset().getUbland());	
      		msgExpRelPos.setNetMass(position.getApoSubset().getEigmas());
      		msgExpRelPos.setGrossMass(position.getApoSubset().getRohmas());
      		
      		CommodityCode tmpCommodityCode = new CommodityCode();
          	tmpCommodityCode.setTarifCode(position.getApoSubset().getTnr());
          	tmpCommodityCode.setEUTarifCode(position.getApoSubset().getTnrtrc());
          	tmpCommodityCode.setTarifAddition1(position.getApoSubset().getTnrzu1());
          	tmpCommodityCode.setTarifAddition2(position.getApoSubset().getTnrzu2());
          	tmpCommodityCode.setAddition(position.getApoSubset().getTnrnat());      	
          	msgExpRelPos.setCommodityCode(tmpCommodityCode);
          	
          	ApprovedTreatment tmpApprovedTreatment = new ApprovedTreatment();
          	tmpApprovedTreatment.setDeclared(position.getApoSubset().getAnmvrf());
          	tmpApprovedTreatment.setPrevious(position.getApoSubset().getPrevrf());
          	tmpApprovedTreatment.setNational(position.getApoSubset().getNatvrf());
          	if (position.getAtpSubset() != null) {
          		//erst ab V.6: 
          		tmpApprovedTreatment.setCodeForRefund(position.getAtpSubset().getAsvfr());
          	}
          	msgExpRelPos.setApprovedTreatment(tmpApprovedTreatment); 
          	
          	Statistic  tmpStatistic = new Statistic();         	
          	tmpStatistic.setAdditionalUnit(position.getApoSubset().getWmahst()); 
          	tmpStatistic.setStatisticalValue(position.getApoSubset().getAhwert()); 
          	msgExpRelPos.setStatistic(tmpStatistic);
          	
          	String tmpTinem = position.getApoSubset().getTinem();
          	String tmpKdnrem = position.getApoSubset().getKdnrem();
          	if (tmpTinem != null || tmpKdnrem != null) {  
          		Party tmpParty = msgExpRelPos.getConsignee();
          		if (tmpParty == null) {
                    tmpParty = new Party();                     
          		}
          		TIN tmpTin = tmpParty.getPartyTIN();
          		if (tmpTin == null) {
                    tmpTin = new TIN();
          		}
				tmpTin.setTIN(tmpTinem);				
				tmpTin.setCustomerIdentifier(tmpKdnrem);
				tmpParty.setPartyTIN(tmpTin);
				msgExpRelPos.setConsignee(tmpParty);          		
          	}
     	}
     	
     	if (position.getSapSubset() != null) {
     		msgExpRelPos.setShipmentNumber(position.getSapSubset().getKnrsdg());    //erst ab V.6: 
     		msgExpRelPos.setDangerousGoodsNumber(position.getSapSubset().getUndgnr());  //erst ab V.6: 
     		msgExpRelPos.setPaymentType(position.getSapSubset().getBfgkzw());   //erst ab V.6: 
     	}
     	      	
     	if (position.getAtpSubset() != null) {
     		// erst ab V.6:
     		ExportRefundItem tmpExportRefundItem = new ExportRefundItem();          	
     		tmpExportRefundItem.setAddition1(position.getAtpSubset().getWberg1());
     		tmpExportRefundItem.setAddition2(position.getAtpSubset().getWberg2());
     		tmpExportRefundItem.setOriginCountry(position.getAtpSubset().getUland());
     		tmpExportRefundItem.setAmountCode(position.getAtpSubset().getKzwert());
     		tmpExportRefundItem.setAmount(position.getAtpSubset().getZfnai());
     		tmpExportRefundItem.setTypeOfRefund(position.getAtpSubset().getMenge());
     		tmpExportRefundItem.setAmountCoefficient(position.getAtpSubset().getApgket());
     		tmpExportRefundItem.setPartA(position.getAtpSubset().getAnwrta());
     		tmpExportRefundItem.setPartB(position.getAtpSubset().getAnwrtb());
     		tmpExportRefundItem.setPartC(position.getAtpSubset().getAnwrtc());
     		tmpExportRefundItem.setPartD(position.getAtpSubset().getAnwrtd());
     		tmpExportRefundItem.setUnitOfMeasurement(position.getAtpSubset().getMeaest());
     	
     		if (position.getAtiList() != null) {     			
     			for (int i = 0, listSize = position.getAtiList().size(); i < listSize; i++) {
     				Ingredients tmpIngredients = new Ingredients();
     				TsATI tmpAti = new TsATI();
     				tmpAti = (TsATI) position.getAtiList().get(i);
			
     				tmpIngredients.setSequentialNumber(tmpAti.getLfdnr());
     				tmpIngredients.setAmount1(tmpAti.getUrfkt1()); 
     				tmpIngredients.setKindOfCoefficient(tmpAti.getKzfkt1()); 
     				tmpIngredients.setAmount2(tmpAti.getUrfkt2()); 
     				tmpIngredients.setRate(tmpAti.getUrfkts()); 
     				tmpIngredients.setWeightPercent(tmpAti.getGhtant()); 
     				tmpIngredients.setWeight(tmpAti.getMgeant()); 
     				tmpIngredients.setUniqueFactoryNumber(tmpAti.getHeklnr()); 
     				tmpIngredients.setTarifNumber(tmpAti.getKeynr()); 
     				tmpIngredients.setLicenceNumber(tmpAti.getLiznr()); 
     				tmpIngredients.setText(tmpAti.getText());  
      		
     				tmpExportRefundItem.addIngredientsList(tmpIngredients);      		
     			}
     			msgExpRelPos.setExportRefundItem(tmpExportRefundItem);       	
     		}
     	}
      	
      	
        if (position.getAdrList() != null) {	
        // only Consignee defined
      
            for (int i = 0, listSize = position.getAdrList().size(); i < listSize; i++) {
				//TsADR tmpTsAdr = new TsADR();
                TsADR tmpTsAdr = (TsADR) msgADP.getAdrList().get(i);
                String typ = tmpTsAdr.getTyp();
				
                if (typ.equals("2")) {
                    //Consignee							
    				Party tmpParty = new Party();					
    				if (msgExpRelPos.getConsignee() != null) {
    				    tmpParty = msgExpRelPos.getConsignee();
    				}
					
    				Address tmpAdr = new Address();
    				String tmpName = tmpTsAdr.getName1() + " " + 
    				                 tmpTsAdr.getName2() + " " + 
    				                 tmpTsAdr.getName3();
    				tmpAdr.setName(tmpName);
    				tmpAdr.setStreet(tmpTsAdr.getStr());
    				tmpAdr.setCity(tmpTsAdr.getOrt());
    				tmpAdr.setPostalCode(tmpTsAdr.getPlz());	
    				tmpAdr.setCountry(tmpTsAdr.getLand());					
    				tmpParty.setAddress(tmpAdr);
				
    				msgExpRelPos.setConsignee(tmpParty);	
                }
            }
        }
      	
      if (position.getAcoList() != null) {
      	for (int i = 0, listSize = position.getAcoList().size(); i < listSize; i++) {
      		Packages tmpPackage = new Packages();
      		TsACO tmpAco = new TsACO();
			tmpAco = (TsACO) position.getAcoList().get(i);
      		tmpPackage.setQuantity(tmpAco.getColanz());
      		tmpPackage.setSequentialNumber(tmpAco.getLfdnr()); 
      		tmpPackage.setType(tmpAco.getColart()); 
      		tmpPackage.setMarks(tmpAco.getColzch()); 
      		
      		msgExpRelPos.addPackagesList(tmpPackage);      		   	
      	}      	 
      }     
      
      if (position.getAcnList() != null) {
     	Container tmpContainer = new Container();      	
      	for (int i = 0, listSize = position.getAcnList().size(); i < listSize; i++) {
      		TsACN tmpAcn = new TsACN();
			tmpAcn = (TsACN) position.getAcnList().get(i);
			tmpContainer.addNumberList(tmpAcn.getConnr());	//? prüfen				
			  
      	}
      	msgExpRelPos.setContainer(tmpContainer); 
      }
          
      if (position.getAedList() != null) {
     	for (int i = 0, listSize = position.getAedList().size(); i < listSize; i++) {
     		Document tmpProdDoc = new Document(); 
     		TsAED tmpAed = new TsAED();          		
     		tmpAed = (TsAED) position.getAedList().get(i);
     		
     		tmpProdDoc.setMsgFlag("K");    //EI20090818
     		tmpProdDoc.setQualifier(tmpAed.getUntqar());
     		tmpProdDoc.setTypeKids(tmpAed.getUntart());
     		tmpProdDoc.setReference(tmpAed.getUntnr());
     		tmpProdDoc.setAdditionalInformation(tmpAed.getUntzus());
     		tmpProdDoc.setDetail(tmpAed.getDetail());
     		tmpProdDoc.setIssueDate(tmpAed.getUntdat());
     		tmpProdDoc.setExpirationDate(tmpAed.getGbdat());
     		tmpProdDoc.setValue(tmpAed.getWert());
     		
     		WriteDownAmount tmpWDA = new WriteDownAmount("K"); 
     		//tmpWDA.setQualifier(tmpAed.getMgeqme());     
     		tmpWDA.setUnitOfMeasurement(tmpAed.getMgeme());
     		//EI20090818:tmpWDA.setValueKids(tmpAed.getAbgwrt());
     		tmpWDA.setWriteoffValue(tmpAed.getAbgwrt());  //EI20090818
     		tmpProdDoc.setWriteDownAmount(tmpWDA);
     		
     		msgExpRelPos.addDocumentList(tmpProdDoc);	
     	}
      }
      
      if (position.getAzvList() != null) {
      	for (int i = 0, listSize = position.getAzvList().size(); i < listSize; i++) {
      		TsAZV tmpAzv = new TsAZV();
      		tmpAzv = (TsAZV) position.getAzvList().get(i);
      		PreviousDocument tmpPrevDoc = new PreviousDocument();      		      		
      		tmpPrevDoc.setType(tmpAzv.getVptyp());
      		tmpPrevDoc.setMarks(tmpAzv.getAzvref());
      		tmpPrevDoc.setAdditionalInformation(tmpAzv.getAzvzus());
      		
      		msgExpRelPos.addPreviousDocumentList(tmpPrevDoc);   
      	}
      }  
      
      //BZL == BondedWarehouseCompletion
      if (position.getApoSubset() != null) {
    	 //if (!Utils.isStringEmpty(position.getApoSubset().getZlbez()) || position.getBzlList() != null) {
    		  //EI20090817: wie erkenne ich dass ich BAV oder BZL machen soll?
    	  
    	  if (!Utils.isStringEmpty(position.getApoSubset().getZlbez()) && position.getBzlList() != null) {
    		  
    		int listSizeBzl = 0;
    		
		    if (position.getBzlList() != null) {
		    	listSizeBzl = position.getBzlList().size();
		    }
		    
		    if (listSizeBzl > 0) {
		    
				Completion tmpBWCopml = new Completion();  
				tmpBWCopml.setAuthorizationNumber(position.getApoSubset().getAzvbew());
				tmpBWCopml.setReferenceNumber(position.getApoSubset().getZlbez());
    	 			    		    	
  				for (int i = 0;  i < listSizeBzl; i++) {
  					TsBZL tmpBzl = new TsBZL();
  					tmpBzl = position.getBzlList().get(i);              //EI20090817
  					CompletionItem tmpComplItem = new CompletionItem();
  					WriteDownAmount tmpWDAmount = new WriteDownAmount("K");
  					WriteDownAmount tmpTrAmount = new WriteDownAmount("K");
  			
  					tmpComplItem.setSequentialNumber("" + i + 1);
  					tmpComplItem.setPositionNumber(tmpBzl.getVposnr());
  					tmpComplItem.setUCR(tmpBzl.getVregnr());
  					tmpComplItem.setEntryInAtlas(tmpBzl.getAtlas());
  					tmpComplItem.setText(tmpBzl.getTxzus());
  					tmpComplItem.setTarifNumber(tmpBzl.getWanr());
  					tmpComplItem.setUsualFormOfHandling(tmpBzl.getKzuebl());
  			
  					//EI20090312: tmpWDAmount.setQualifier(tmpBzl.getQmeabg());
  					tmpWDAmount.setUnitOfMeasurement(tmpBzl.getMeabg());
  					//EI20090818: tmpWDAmount.setValueKids(tmpBzl.getMgeabg());  
  					tmpWDAmount .setWriteoffValue(tmpBzl.getMgeabg());  //EI20090818
  			
  					//EI20090312: tmpTrAmount.setQualifier(tmpBzl.getQmehdl());
  					tmpTrAmount.setUnitOfMeasurement(tmpBzl.getMehdl());
  					tmpTrAmount.setWriteoffValue(tmpBzl.getMgehdl()); 		
  			
  					tmpComplItem.setWriteDownAmount(tmpWDAmount); 
  					tmpComplItem.setTradeAmount(tmpTrAmount); 
  			
  					tmpBWCopml.addCompletionItemList(tmpComplItem); 
  				}
  				msgExpRelPos.setBondedWarehouseCompletion(tmpBWCopml);
		    }  		
  		     
    	 }
      }
      
      //BAV == InwardProcessingCompletion
      if (position.getApoSubset() != null) {    	  
    	if (!Utils.isStringEmpty(position.getApoSubset().getAzvbew()) && position.getBavList() != null) {
    		int listSizeBav = 0;
    		if (position.getBavList() != null) {
        		listSizeBav = position.getBavList().size();
    		}
        				
        	if (listSizeBav > 0) {	
        		Completion tmpIPCopml = new Completion();      		    		
        		tmpIPCopml.setAuthorizationNumber(position.getApoSubset().getAzvbew());
    		
        		for (int i = 0; i < listSizeBav; i++) {
        			TsBAV tmpBav = new TsBAV();
        			tmpBav = position.getBavList().get(i);              //EI20090817
        			CompletionItem tmpComplItem = new CompletionItem();
    			
        			tmpComplItem.setSequentialNumber("" + i + 1);
        			tmpComplItem.setPositionNumber(tmpBav.getVposnr());
        			tmpComplItem.setUCR(tmpBav.getVregnr());
        			tmpComplItem.setEntryInAtlas(tmpBav.getAtlas());
        			tmpComplItem.setText(tmpBav.getTxzus());
    			
        			tmpIPCopml.addCompletionItemList(tmpComplItem); 
        		}
        		msgExpRelPos.setInwardProcessingCompletion(tmpIPCopml);      
        	}
    		    
    	 }
      }
      
      return msgExpRelPos;
    } // ende Position
    
}  //ende der klasse
