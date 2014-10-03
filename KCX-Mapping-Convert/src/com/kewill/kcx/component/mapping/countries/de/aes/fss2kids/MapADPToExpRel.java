/*
 * Function    : MapADPToExpRel.java
 * Titel       :
 * Date        : 02.10.2008
 * Author      : Kewill CSF / iwaniuk
 * Description : DE.AES Mapping of FSS-Format ADP to KIDS-Format ReverseDeclaration  
 * 			   : 
 * Parameters  : 

 * Changes 
 * -----------
 * Author      : EI
 * Date        : 08.05.2009
 * Label       :
 * Description : replaced BodyExportADPtoReverseDeclarationKids with 
 *               BodyReverseDeclarationKids

 * Author      : AK
 * Date        : 12.05.2009
 * Label       : AK20090512
 * Description : tmpPdf.setPdflist(msgADP.getPdfInformation().getPdflist());
 * 
 *  			 
 * Author      : AK
 * Date        : 13.05.2009
 * Label       : AK20090513
 * Description : tmpPdf.setPdflist(msgADP.getPdfInformation().getPdflist());
 * 
 * Author      : EI
 * Label       : EI20090608
 * Description : ContactPerson.identity instead of clerk   
 *
 * Author      : EI
 * Label       : EI20100607
 * Description : new member: exitTime    
 */

package com.kewill.kcx.component.mapping.countries.de.aes.fss2kids;

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
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.IncoTerms;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.LoadingTime;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PlaceOfLoading;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Statistic;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V53.MsgADP;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V53.MsgADPos;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsACN;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsACO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsADR;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsAED;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsAVS;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsAZV;
import com.kewill.kcx.component.mapping.formats.kids.aes.BodyReverseDeclarationKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: MapADPToExpRel<br>
 * Erstellt		: 02.10.2008<br>
 * Beschreibung	: DE.AES Mapping of FSS-Format ADP to KIDS-Format ReverseDeclaration.
 * 
 * @author iwaniuk
 * @version 1.0.00
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
	        header.setHeaderFields(msgADP.getVorSubset());
	        header.setMessageName("ReverseDeclaration");
	        header.setMessageID(getMsgID());
	        CommonFieldsDTO commonFieldsDTO = new CommonFieldsDTO();
	        commonFieldsDTO.setKcxId(header.getReceiver());
	        commonFieldsDTO.setCountryCode(header.getCountryCode());
	        commonFieldsDTO.setDirection(EDirections.CountryToCustomer);
	        header.setCommonFieldsDTO(commonFieldsDTO);	  

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
			//msgExpRel.setPdfInformation(msgADP.getPdfInformation());
			msgExpRel.setKindOfAnswer(msgADP.getDatSubset().getKzart());				
			msgExpRel.setUCRNumber(msgADP.getDatSubset().getMrn());
			msgExpRel.setReferenceNumber(msgADP.getDatSubset().getBeznr());	
			msgExpRel.setOrderNumber(msgADP.getDatSubset().getKuatnr());
			msgExpRel.setCustomsOfficeExport(msgADP.getDatSubset().getExpdst());
			msgExpRel.setCustomsOfficeForCompletion(msgADP.getDatSubset().getEamdst());
			msgExpRel.setCustomsOfficeExport(msgADP.getDatSubset().getExpdst());
			
			/* EI20090421
			String tmpForwarderId = msgADP.getDatSubset().getKdnrsp();
			String tmpForwarderETN = msgADP.getDatSubset().getEtnsp();
			
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
			if (!Utils.isStringEmpty(tmpConsigneeId) || !Utils.isStringEmpty(tmpConsigneeTinNr)) {
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
			//EI20090421: String tmpDeclarantETN = msgADP.getDatSubset().getEtnan();
			String tmpDeclarantTinNr = msgADP.getDatSubset().getTinan();	
			if (!Utils.isStringEmpty(tmpDeclarantId) || !Utils.isStringEmpty(tmpDeclarantTinNr)) {
				Party tmpParty = new Party();				
				//EI20090421: tmpParty.setETNAddress(tmpDeclarantETN);
				TIN tmpTin = new TIN();
				tmpTin.setTIN(tmpDeclarantTinNr);				
				tmpTin.setCustomerIdentifier(tmpDeclarantId);
				tmpParty.setPartyTIN(tmpTin);
				msgExpRel.setDeclarant(tmpParty);				
			}		
			//EI20090421: msgExpRel.setPreviousProcedure(msgADP.getDatSubset().getQuelkz());
			msgExpRel.setReceiverCustomerNumber(msgADP.getDatSubset().getVekan());
			//EI090608: msgExpRel.setClerk(msgADP.getDatSubset().getSb());
			if (msgADP.getDatSubset().getSb() != null) {    //EI20090608
				ContactPerson tmpContact = new ContactPerson();
				tmpContact.setIdentity(msgADP.getDatSubset().getSb());
				msgExpRel.setContact(tmpContact);	
			}
		}
		/* in Excel-Kids nicht da
      DAT-beozus  = " ";      // Zusatz zum Ladeort   
      DAT-flhcde  = " ";      // Flughafencode    
      DAT-extdst  = " ";      // Ausgangsdienststelle  <= ist in EDA 
		 */
		if (msgADP.getEdaSubset() != null) {
						
			msgExpRel.setAreaCode(msgADP.getEdaSubset().getArtaus());	
			msgExpRel.setTypeOfPermit(msgADP.getEdaSubset().getArtueb());
			msgExpRel.setDeclarationTime(msgADP.getEdaSubset().getAnmdat());
			msgExpRel.setDestinationCountry(msgADP.getEdaSubset().getLdbe());			
			msgExpRel.setTransportInContainer(msgADP.getEdaSubset().getConkz());			
			msgExpRel.setUCROtherSystem(msgADP.getEdaSubset().getFregnr());
			msgExpRel.setAnnotation(msgADP.getEdaSubset().getVerm());		
			
			PlaceOfLoading tmpPoLoading = new PlaceOfLoading();
			tmpPoLoading.setCode(msgADP.getEdaSubset().getLdocde());
			tmpPoLoading.setStreet(msgADP.getEdaSubset().getBeostr());
			tmpPoLoading.setPostalCode(msgADP.getEdaSubset().getBeoplz());
			tmpPoLoading.setCity(msgADP.getEdaSubset().getBeoort());
			//EI20090421: msgExpRel.setPlaceOfLoading(tmpPoLoading);		
		
			String tmpDeclarantTinIs = msgADP.getEdaSubset().getDtzoan();
			if (!Utils.isStringEmpty(tmpDeclarantTinIs)) {
				Party tmpParty = msgExpRel.getDeclarant();				
				if (tmpParty == null) {
                    tmpParty = new Party(); 
				}
				TIN tmpTin = tmpParty.getPartyTIN();
				if (tmpTin == null) {
                    tmpTin = new TIN();                     
				}
				tmpTin.setIsTINGermanApprovalNumber(tmpDeclarantTinIs);
				tmpParty.setPartyTIN(tmpTin);
				msgExpRel.setDeclarant(tmpParty);
			}
			
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
			String tmpAgentTinNr = msgADP.getEdaSubset().getTinva();			
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
			//EI20090421: msgExpRel.setAuthorizationNumber(msgADP.getEdaSubset().getBewnr());
			msgExpRel.setDeclarantIsConsignor(msgADP.getEdaSubset().getKzanau());	
			msgExpRel.setRealOfficeOfExit(msgADP.getEdaSubset().getExtdst());	
		}	
		
		if (msgADP.getDaeSubset() != null) {
			msgExpRel.setShortageInQuantity(msgADP.getDaeSubset().getKzmin());
			msgExpRel.setTotalNumberPositions(msgADP.getDaeSubset().getAnzpos());
			msgExpRel.setTotalNumberPackages(msgADP.getDaeSubset().getAnzcol());
			msgExpRel.setGrossMass(msgADP.getDaeSubset().getGsroh());
			}	
	
		if (msgADP.getAspSubset() != null) {
		//EI20090421: msgExpRel.setCancellationTime(msgADP.getAspSubset().getStodat());			
		msgExpRel.setExitTime(msgADP.getAspSubset().getExtdat()); 					//EI20100607
		msgExpRel.setReleaseTime(msgADP.getAspSubset().getUebdat());
		msgExpRel.setAcceptanceTime(msgADP.getAspSubset().getAndat());
		msgExpRel.setReceiveTime(msgADP.getAspSubset().getAckdat());		
		//EI20090421: msgExpRel.setTimeOfRejection(msgADP.getAspSubset().getGstdat());
		//EI20090421: msgExpRel.setBeginTimeOfProcessing(msgADP.getAspSubset().getBwbdat());
				
		LoadingTime tmpLoadingTime = new LoadingTime();
		tmpLoadingTime.setBeginTime(msgADP.getAspSubset().getGststr());
		tmpLoadingTime.setEndTime(msgADP.getAspSubset().getGstend());
		msgExpRel.setLoadingTime(tmpLoadingTime);  		
		}			
		
		//AK20090513
		msgExpRel.setPdfInformation(msgADP.getPdfInformation());
		/****
		if (msgADP.getAmpSubset() != null) {
			PDFInformation tmpPDF = new PDFInformation();	
			
			tmpPDF.setName(msgADP.getAmpSubset().getPdfnam());
			tmpPDF.setDirectory(msgADP.getAmpSubset().getPdfpfd());
			tmpPDF.setNewDirectory(msgADP.getAmpSubset().getSubdir());
			
			//AK20090512
			if (msgADP.getPdfInformation() != null)
				tmpPDF.setPdflist(msgADP.getPdfInformation().getPdflist());
			
			msgExpRel.setPdfInformation(tmpPDF);				   
		}
		****/
		if (msgADP.getAdrList() != null) {
			
			for (int i = 0; i < msgADP.getAdrList().size(); i++) {			
				TsADR tmpTsAdr = new TsADR();
				tmpTsAdr = (TsADR) msgADP.getAdrList().get(i);
				String typ = tmpTsAdr.getTyp();
				
				if (typ.equals("1")) {
					//Consignor					
					Party tmpParty = msgExpRel.getConsignor();
					if (tmpParty == null) {
                        tmpParty = new Party();                                                         
					}
					Address tmpAdr = new Address();
                    String tmpName = tmpTsAdr.getName1() + " " + tmpTsAdr.getName2() + " " + tmpTsAdr.getName3();
					tmpAdr.setName(tmpName);
					tmpAdr.setStreet(tmpTsAdr.getStr());
					tmpAdr.setCity(tmpTsAdr.getOrt());
					tmpAdr.setPostalCode(tmpTsAdr.getPlz());	
					tmpAdr.setCountry(tmpTsAdr.getLand());					
					tmpParty.setAddress(tmpAdr);
					msgExpRel.setConsignor(tmpParty);					
									
				} else if (typ.equals("2")) {
					//Consignee							
					Party tmpParty = msgExpRel.getConsignee();
					if (tmpParty == null) {
                        tmpParty = new Party();                                                         
					}
					Address tmpAdr = new Address();
                    String tmpName = tmpTsAdr.getName1() + " " + tmpTsAdr.getName2() + " " + tmpTsAdr.getName3();
					tmpAdr.setName(tmpName);
					tmpAdr.setStreet(tmpTsAdr.getStr());
					tmpAdr.setCity(tmpTsAdr.getOrt());
					tmpAdr.setPostalCode(tmpTsAdr.getPlz());	
					tmpAdr.setCountry(tmpTsAdr.getLand());					
					tmpParty.setAddress(tmpAdr);
					msgExpRel.setConsignee(tmpParty);
					
				} else if (typ.equals("3")) {
					//Declarant					
					Party tmpParty = msgExpRel.getDeclarant();
					if (tmpParty == null) {
                        tmpParty = new Party();
					}
					Address tmpAdr = new Address();
                    String tmpName = tmpTsAdr.getName1() + " " + tmpTsAdr.getName2() + " " + tmpTsAdr.getName3();
					tmpAdr.setName(tmpName);
					tmpAdr.setStreet(tmpTsAdr.getStr());
					tmpAdr.setCity(tmpTsAdr.getOrt());
					tmpAdr.setPostalCode(tmpTsAdr.getPlz());	
					tmpAdr.setCountry(tmpTsAdr.getLand());					
					tmpParty.setAddress(tmpAdr);
					msgExpRel.setDeclarant(tmpParty);					
					
				} else if (typ.equals("4")) {
					//Agent		
					Party tmpParty = msgExpRel.getAgent();
					if (tmpParty == null) {
                        tmpParty = new Party();                                     
					}
					Address tmpAdr = new Address();
                    String tmpName = tmpTsAdr.getName1() + " " + tmpTsAdr.getName2() + " " + tmpTsAdr.getName3();
					tmpAdr.setName(tmpName);
					tmpAdr.setStreet(tmpTsAdr.getStr());
					tmpAdr.setCity(tmpTsAdr.getOrt());
					tmpAdr.setPostalCode(tmpTsAdr.getPlz());	
					tmpAdr.setCountry(tmpTsAdr.getLand());					
					tmpParty.setAddress(tmpAdr);
					msgExpRel.setAgent(tmpParty);
								
					
				} else if (typ.equals("5")) {
					//Subcontractor
					Party tmpParty = msgExpRel.getSubcontractor();
					if (tmpParty == null) {
                        tmpParty = new Party(); 
					}
					Address tmpAdr = new Address();
                    String tmpName = tmpTsAdr.getName1() + " " + tmpTsAdr.getName2() + " " + tmpTsAdr.getName3();
					tmpAdr.setName(tmpName);
					tmpAdr.setStreet(tmpTsAdr.getStr());
					tmpAdr.setCity(tmpTsAdr.getOrt());
					tmpAdr.setPostalCode(tmpTsAdr.getPlz());	
					tmpAdr.setCountry(tmpTsAdr.getLand());					
					tmpParty.setAddress(tmpAdr);
					msgExpRel.setSubcontractor(tmpParty);
					
				}
			}
		}	
				
		if (msgADP.getEamSubset() != null) {
			
			msgExpRel.setCompletionTime(msgADP.getEamSubset().getEamdat());
		
			TransportMeans tmpInland = new TransportMeans();
			tmpInland.setTransportMode(msgADP.getEamSubset().getBfvkzi());
			msgExpRel.setTransportMeansInland(tmpInland);	
				
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
		
		if (msgADP.getEdaSubset() != null || (msgADP.getAvsList() != null)) {
			Seal tmpSeal = new Seal();
			if (msgADP.getEdaSubset() != null) {
				tmpSeal.setKind(msgADP.getEdaSubset().getVsart());
				tmpSeal.setNumber(msgADP.getEdaSubset().getAnzve());	
				tmpSeal.setUseOfTydenseals(msgADP.getEdaSubset().getKztyd());	
				tmpSeal.setUseOfTydensealStock(msgADP.getEdaSubset().getKzstock());		
			}
			if (msgADP.getAvsList() != null) {
				List<TsAVS> tmpAvsList = new Vector<TsAVS>();
				tmpAvsList = msgADP.getAvsList();
								
				for (int i = 0; i < tmpAvsList.size(); i++) {
					TsAVS tmpAvs = new TsAVS();
					tmpAvs = (TsAVS) tmpAvsList.get(i);
					String tmpSealNr = tmpAvs.getSeal();					
					tmpSeal.addSealNumberList(tmpSealNr);
				}					
			}
			msgExpRel.setSeal(tmpSeal);
		}		
				
		List<MsgADPos> adpPosList = msgADP.getPosList();						
		for (int i = 0; i < adpPosList.size(); i++) {	
			//msgExpRel.addGoodsItemList(setExpRelPosition((MsgADPos)adpPosList.get(i)));
			MsgADPos tmpAdpPos = (MsgADPos) adpPosList.get(i);
			msgExpRel.addGoodsItemList(setExpRelPosition(tmpAdpPos));
		}		
    }
	
    private MsgExpRelPos setExpRelPosition(MsgADPos position) {
    	
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
          	msgExpRelPos.setApprovedTreatment(tmpApprovedTreatment); 
          	
          	Statistic  tmpStatistic = new Statistic();         	
          	tmpStatistic.setAdditionalUnit(position.getApoSubset().getWmahst()); 
          	tmpStatistic.setStatisticalValue(position.getApoSubset().getAhwert()); 
          	msgExpRelPos.setStatistic(tmpStatistic);
          	
          	// APO-vptyp  in Excel-Kids nicht da
     	}     	             	
      	
      if (position.getAcoList() != null) {
      	for (int i = 0; i < position.getAcoList().size(); i++) {	
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
       //nveSubset
      
      if (position.getAcnList() != null) {
     	Container tmpContainer = new Container();      	
      	for (int i = 0; i < position.getAcnList().size(); i++) {	      		
      		TsACN tmpAcn = new TsACN();
			tmpAcn = (TsACN) position.getAcnList().get(i);
			tmpContainer.addNumberList(tmpAcn.getConnr());					
			  
      	}
      	msgExpRelPos.setContainer(tmpContainer); 
      }
      	     
      if (position.getAzvList() != null) {
       	for (int i = 0; i < position.getAzvList().size(); i++) {	    	
       		PreviousDocument tmpPreviousDoc = new PreviousDocument(); 
       		TsAZV tmpAzv = new TsAZV();     		
       		tmpAzv = (TsAZV) position.getAzvList().get(i);
       		tmpPreviousDoc.setMarks(tmpAzv.getAzvref());
       		tmpPreviousDoc.setAdditionalInformation(tmpAzv.getAzvzus());
       		
       		msgExpRelPos.addPreviousDocumentList(tmpPreviousDoc);	
       	}
      }
      
      if (position.getAedList() != null) {
     	for (int i = 0; i < position.getAedList().size(); i++) {	    	
     		Document tmpProdDoc = new Document(); 
     		TsAED tmpAed = new TsAED();     		
     		tmpAed = (TsAED) position.getAedList().get(i);
     		tmpProdDoc.setQualifier(tmpAed.getUntqar());
     		tmpProdDoc.setTypeKids(tmpAed.getUntart());
     		tmpProdDoc.setReference(tmpAed.getUntnr());
     		tmpProdDoc.setAdditionalInformation(tmpAed.getUntzus());     		
     		tmpProdDoc.setIssueDate(tmpAed.getUntdat());
     		tmpProdDoc.setExpirationDate(tmpAed.getGbdat());     		
     		
     		msgExpRelPos.addDocumentList(tmpProdDoc);	
     	}
      }         
      	
      return msgExpRelPos;
    } // ende Position
    
}  //ende der klasse
