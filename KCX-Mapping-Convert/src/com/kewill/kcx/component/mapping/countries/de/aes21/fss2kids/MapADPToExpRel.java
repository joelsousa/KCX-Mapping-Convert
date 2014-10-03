package com.kewill.kcx.component.mapping.countries.de.aes21.fss2kids;

import java.io.StringWriter;

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
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ApprovedTreatment;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Business;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CommodityCode;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Completion;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CompletionItem;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.DocumentV20;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ExportRefundItem;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.IncoTerms;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Ingredients;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.LoadingTime;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PlaceOfLoading;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Statistic;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.WriteDownAmount;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.WriteDownAmountV20;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpRel;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpRelPos;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.common.PreviousDocumentV21;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V70.MsgADP;
import com.kewill.kcx.component.mapping.formats.fss.aes.messages.V70.MsgADPPos;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsACN;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsACO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAED;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsATI;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAVS;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAZV;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsBAV;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsBZL;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsADR;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsAPN;
import com.kewill.kcx.component.mapping.formats.kids.aes21.BodyReverseDeclarationKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: Export/AES21<br>
 * Created		: 30.07.2012<br>
 * Description	: Mapping of FSS-Format ADP V70 to KIDS-Format ReverseDeclaration V21.
 *              : new Tags for v21
 *              : EI20130827: PreviousDocumentV20 ersetzt mit PreviousDocumentV21 
 * 
 * @author iwaniuk
 * @version 1.0.00
 * 
 * * Changes 
 * -----------
 * Author      : Christine Kron
 * Date        : 24.08.2012
 * Label       : CK120827
 * Description : Utils.removeZabisDecimalPlaceV7 used with decimal fields described in doc  
 *				 M:\develop\KCX\UmstellungDiehl2012\NK-StellenDecimals.xlsx and official Zabis docs. 		
 * 
 * EI20121005  : Header:MessageID and InReplyTo will be filled from TsVOR (and TsVOR from TsHEAD)     	
 */

public class MapADPToExpRel extends KidsMessage {
	private MsgADP msgADP;
	private MsgExpRel msgExpRel;		
	private String subversion = "";    //EI20130426
	
	public MapADPToExpRel() {
		msgExpRel = new MsgExpRel();
	}
	public MapADPToExpRel(CommonFieldsDTO commonFieldsDTO) {
		msgExpRel = new MsgExpRel();
		this.setCommonFieldsDTO(commonFieldsDTO);   //EI20130523
		if (commonFieldsDTO != null && !Utils.isStringEmpty(commonFieldsDTO.getKidsRelease())) {
        	subversion = Utils.removeDots(commonFieldsDTO.getKidsRelease());        	
        }
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
	        //EI20121005: header.setHeaderFields(msgADP.getVorSubset());	        
	        header.setHeaderFieldsFromHead(msgADP.getVorSubset(), msgADP.getHeadSubset());     //EI20121005
	        header.setMessageName("ReverseDeclaration");
	        //header.setMessageID(getMsgID()); wird in setHeaderFieldsFromHead gesetzt
	       
	        CommonFieldsDTO commonFieldsDTO = new CommonFieldsDTO();
	        commonFieldsDTO.setKcxId(header.getReceiver());
	        commonFieldsDTO.setCountryCode(header.getCountryCode());
	        commonFieldsDTO.setDirection(EDirections.CountryToCustomer);
	        header.setCommonFieldsDTO(commonFieldsDTO);	  
	        
	        if (this.getCommonFieldsDTO() != null && !Utils.isStringEmpty(this.getCommonFieldsDTO().getKidsRelease())) {
	        	subversion = Utils.removeDots(this.getCommonFieldsDTO().getKidsRelease());
	        	if (subversion.equals("2101")) {
	            	header.setRelease(this.getCommonFieldsDTO().getKidsRelease());
	            }
	        }
            
	        header.writeHeader();
	       
	        BodyReverseDeclarationKids body   = new BodyReverseDeclarationKids(writer); 
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
		
	public void setMsgADP(MsgADP argument) {
		
		this.msgADP = argument;						
    	this.setMsgFields();    	
    }

	public void setMsgFields() {
		mapDatToKids();
		mapEdaToKids();
		mapEamToKids();
		mapAdrToKids();
		mapApnToKids();
		mapDeaToKids();
		mapAspToKids();
		mapAdpToSeal();
		
		if (msgADP.getPosList() != null) {					
			for (MsgADPPos tmpAdpPos : msgADP.getPosList()) {	
				if (tmpAdpPos != null) {
					msgExpRel.addGoodsItemList(mapAdpPosToGoodsItem(tmpAdpPos));
				}
			}	
		}
		
		msgExpRel.setPdfInformation(msgADP.getPdfInformation());
    }
	
	private void mapDatToKids() {
		if (msgADP.getDatSubset() == null) {
			return;
		}		
		msgExpRel.setKindOfAnswer(msgADP.getDatSubset().getKzart());		
		msgExpRel.setUCRNumber(msgADP.getDatSubset().getMrn());
		msgExpRel.setReferenceNumber(msgADP.getDatSubset().getBeznr());	
		msgExpRel.setOrderNumber(msgADP.getDatSubset().getKuatnr());
		msgExpRel.setCustomsOfficeExport(msgADP.getDatSubset().getExpdst());
		msgExpRel.setCustomsOfficeForCompletion(msgADP.getDatSubset().getEamdst());		
		//EI20090421: msgExpRel.setPreviousProcedure(msgADP.getDatSubset().getQuelkz());
		if (!Utils.isStringEmpty(msgADP.getDatSubset().getSb())) {			
			ContactPerson tmpContact = new ContactPerson();
			tmpContact.setIdentity(msgADP.getDatSubset().getSb());
			msgExpRel.setContact(tmpContact);	
		}
	}
	
	private void mapEdaToKids() {
		if (msgADP.getEdaSubset() == null) {
			return;
		}
		msgExpRel.setAreaCode(msgADP.getEdaSubset().getArtaus());	
		msgExpRel.setTypeOfPermit(msgADP.getEdaSubset().getArtueb());
		msgExpRel.setDeclarationTime(msgADP.getEdaSubset().getAnmdat());		
		msgExpRel.setDispatchCountry(msgADP.getEdaSubset().getLdve());
		msgExpRel.setDestinationCountry(msgADP.getEdaSubset().getLdbe());
		//msgExpRel.setPreAnnouncementTime(msgADP.getEdaSubset().getInddat()); 
		msgExpRel.setTransportInContainer(msgADP.getEdaSubset().getConkz());	
		// CK120827
		// msgExpRel.setGrossMass(msgADP.getEdaSubset().getGsroh());
		msgExpRel.setGrossMass(Utils.removeZabisDecimalPlaceV7(msgADP.getEdaSubset().getGsroh(), 3));		
		if (subversion.equals("2101")) {        //EI20130426
			msgExpRel.setGrossMass(msgADP.getEdaSubset().getGsroh());
		}		
		
		msgExpRel.setUCROtherSystem(msgADP.getEdaSubset().getFregnr());
		msgExpRel.setAnnotation(msgADP.getEdaSubset().getVerm());		
		msgExpRel.setRealOfficeOfExit(msgADP.getEdaSubset().getExtdst());
			
		PlaceOfLoading tmpPoLoading = new PlaceOfLoading();
		tmpPoLoading.setCode(msgADP.getEdaSubset().getLdocde());
		tmpPoLoading.setStreet(msgADP.getEdaSubset().getBeostr());
		tmpPoLoading.setPostalCode(msgADP.getEdaSubset().getBeoplz());
		tmpPoLoading.setCity(msgADP.getEdaSubset().getBeoort());
		tmpPoLoading.setAgreedLocationOfGoods(msgADP.getEdaSubset().getBeozus());
		if (!tmpPoLoading.isEmpty()) {
			msgExpRel.setPlaceOfLoading(tmpPoLoading);		
		}
		
		msgExpRel.setAuthorizationNumber(msgADP.getEdaSubset().getBewnr());
		msgExpRel.setAuthorizationTrustedExporter(msgADP.getEdaSubset().getBewva());  //new V21
		msgExpRel.setDeclarantIsConsignor(msgADP.getEdaSubset().getKzanau());	
		
		
		//EI20120731 das oder von ASP???
		if (!Utils.isStringEmpty(msgADP.getEdaSubset().getGststr()) || !Utils.isStringEmpty(msgADP.getEdaSubset().getGstend())) {
			LoadingTime lod = new LoadingTime();
			lod.setBeginTime(msgADP.getEdaSubset().getGststr());
			lod.setEndTime(msgADP.getEdaSubset().getGstend());
			msgExpRel.setLoadingTime(lod);
		}
		msgExpRel.setProcedure(msgADP.getEdaSubset().getArtvfr());    //new V21
	}

	private void mapEamToKids() {		
		if (msgADP.getEamSubset() == null) {
			return;
		}
		msgExpRel.setCompletionTime(msgADP.getEamSubset().getEamdat());

		TransportMeans tmpInland = new TransportMeans();
		tmpInland.setTransportMode(msgADP.getEamSubset().getBfvkzi());
		if (!tmpInland.isEmpty()) {
			msgExpRel.setTransportMeansInland(tmpInland);	
		}
		TransportMeans tmpBorder = new TransportMeans();
		tmpBorder.setTransportMode(msgADP.getEamSubset().getBfvkzg());
		tmpBorder.setTransportationType(msgADP.getEamSubset().getBfartg());		
		tmpBorder.setTransportationNumber(msgADP.getEamSubset().getBfkzg());		
		tmpBorder.setTransportationCountry(msgADP.getEamSubset().getBfnatg());		
		if (!tmpBorder.isEmpty()) {
			msgExpRel.setTransportMeansBorder(tmpBorder);
		}
		Business tmpBusiness = new Business();
		tmpBusiness.setBusinessTypeCode(msgADP.getEamSubset().getGesart());		
		// CK120827
		// tmpBusiness.setInvoicePrice(msgADP.getEamSubset().getGesprs());	
		tmpBusiness.setInvoicePrice(Utils.removeZabisDecimalPlaceV7(msgADP.getEamSubset().getGesprs(), 2));
		if (subversion.equals("2101")) {        //EI20130510
			tmpBusiness.setInvoicePrice(msgADP.getEamSubset().getGesprs());	
		}
		tmpBusiness.setCurrency(msgADP.getEamSubset().getGeswrg());	
		if (!tmpBusiness.isEmpty()) {
			msgExpRel.setBusiness(tmpBusiness);
		}
		IncoTerms tmpIncoTerms  = new IncoTerms();
		tmpIncoTerms.setIncoTerm(msgADP.getEamSubset().getLibart());
		tmpIncoTerms.setText(msgADP.getEamSubset().getLibinc());
		tmpIncoTerms.setPlace(msgADP.getEamSubset().getLibort());
		if (!tmpIncoTerms.isEmpty()) {
			msgExpRel.setIncoTerms(tmpIncoTerms);	
		}
	}
	
	private void mapAdrToKids() {
		if (msgADP.getAdrList() == null) {
			return;
		}					
		for (TsADR tmpTsAdr : msgADP.getAdrList()) {
		 if (tmpTsAdr != null) {
			String typ = "";
  		  	if (!Utils.isStringEmpty(tmpTsAdr.getTyp())) {
  		  		typ = tmpTsAdr.getTyp();
  		  	}
  		  	Address tmpAdr = new Address();
  		  	String tmpName = tmpTsAdr.getName1() + " " + tmpTsAdr.getName2() + " " + tmpTsAdr.getName3();      				           
  		  	tmpAdr.setName(tmpName);
  		  	tmpAdr.setStreet(tmpTsAdr.getStr());
  		  	tmpAdr.setCity(tmpTsAdr.getOrt());
  		  	tmpAdr.setPostalCode(tmpTsAdr.getPlz());	
  		  	tmpAdr.setCountry(tmpTsAdr.getLand());	
  			
  		  	TIN tmpTIN = new TIN();      			
  		  	tmpTIN.setCustomerIdentifier(tmpTsAdr.getKdnr());
  		  	tmpTIN.setTIN(tmpTsAdr.getTin());
  		  	tmpTIN.setBO(tmpTsAdr.getNl());
  		  	tmpTIN.setIdentificationType(tmpTsAdr.getDtzo());
  			
            if (typ.equals("1")) {                     //Consignor	                     					
    			Party tmpParty = msgExpRel.getConsignor();					
    			if (tmpParty == null) {    				 
    				tmpParty = new Party();
    			}  					
    			tmpParty.setETNAddress(tmpTsAdr.getEtn());  
    			if (!tmpAdr.isEmpty()) {
    				tmpParty.setAddress(tmpAdr);   
    			}
    			if (!tmpTIN.isEmpty()) {
    				  tmpParty.setPartyTIN(tmpTIN); 
    			}
    			if (!tmpParty.isEmpty()) {
    				msgExpRel.setConsignor(tmpParty);	
    			}
            }
            if (typ.equals("2")) {                      //Consignee	                     					
  			    Party tmpParty = msgExpRel.getConsignee();					
  			    if (tmpParty == null) {
  				    tmpParty = new Party();
  			    }  					
  			    tmpParty.setETNAddress(tmpTsAdr.getEtn());
  			    if (!tmpAdr.isEmpty()) {
  			    	tmpParty.setAddress(tmpAdr);  	
  			    }
  			    if (!tmpTIN.isEmpty()) {
  				    tmpParty.setPartyTIN(tmpTIN);  
  			    }
  			    if (!tmpParty.isEmpty()) {
  			    	msgExpRel.setConsignee(tmpParty);	
  			    }
            }      		
			if (typ.equals("3")) {                         //Declarant											
				Party tmpParty = msgExpRel.getDeclarant();
				if (tmpParty == null) {
		            tmpParty = new Party();
				}
				tmpParty.setETNAddress(tmpTsAdr.getEtn());	
				if (!tmpAdr.isEmpty()) {
					tmpParty.setAddress(tmpAdr);  	
				}
  			    if (!tmpTIN.isEmpty()) {
  				    tmpParty.setPartyTIN(tmpTIN);  
  			    } 
  			    if (!tmpParty.isEmpty()) {
  			    	msgExpRel.setDeclarant(tmpParty);
  			    }
			}		
			if (typ.equals("4")) {                          //Agent									
				Party tmpParty = msgExpRel.getAgent();
				if (tmpParty == null) {
		           tmpParty = new Party();                                     
				}
				tmpParty.setETNAddress(tmpTsAdr.getEtn());	 
				if (!tmpAdr.isEmpty()) {
					tmpParty.setAddress(tmpAdr);
				}
  			    if (!tmpTIN.isEmpty()) {
  				    tmpParty.setPartyTIN(tmpTIN);  
  			    } 
  			    if (!tmpParty.isEmpty()) {
  			    	msgExpRel.setAgent(tmpParty);
  			    }
			}											
			if (typ.equals("5")) {                        //Subcontractor							
				Party tmpParty = msgExpRel.getSubcontractor();
				if (tmpParty == null) {
		           tmpParty = new Party(); 
				}
				tmpParty.setETNAddress(tmpTsAdr.getEtn());	  
				if (!tmpAdr.isEmpty()) {
					tmpParty.setAddress(tmpAdr); 
				}
  			    if (!tmpTIN.isEmpty()) {
  				    tmpParty.setPartyTIN(tmpTIN);  
  			    }
  			    if (!tmpParty.isEmpty()) {
				    msgExpRel.setSubcontractor(tmpParty);	
  			    }
			}
			if (typ.equals("6")) {                      //new for V21 FinalUser	                     					
				Party tmpParty = msgExpRel.getFinalRecipient();					
				if (msgExpRel.getFinalRecipient() == null) {
					tmpParty = new Party();	
				}  					
				tmpParty.setETNAddress(tmpTsAdr.getEtn());	
				if (!tmpAdr.isEmpty()) {
					tmpParty.setAddress(tmpAdr);
				}
				if (!tmpTIN.isEmpty()) {
					tmpParty.setPartyTIN(tmpTIN); 
				}
				if (!tmpParty.isEmpty()) {
					msgExpRel.setFinalRecipient(tmpParty);	
				}
           }
		 }
		}
				
	}
	
	private void mapApnToKids() {    //EI20121005
		if (msgADP.getApnList() == null) {
			return;
		}					
		for (TsAPN tmpTsApn : msgADP.getApnList()) {
			if (tmpTsApn != null) {
				String typ = "";
  		  		if (!Utils.isStringEmpty(tmpTsApn.getTyp())) {
  		  			typ = tmpTsApn.getTyp();
  		  		}
  		  		ContactPerson contact = new ContactPerson();  		  
  		  		contact.setName(tmpTsApn.getSbname());
  		  		contact.setClerk(tmpTsApn.getSbname());
  		  		contact.setPosition(tmpTsApn.getSbstlg());
  		  		contact.setPhoneNumber(tmpTsApn.getTelnr());
  		  		contact.setFaxNumber(tmpTsApn.getFaxnr());	
  		  		contact.setEmail(tmpTsApn.getEmail());	  			
  		  	
  		  		if (typ.equals("1")) {                     //Consignor	                     					
  		  			Party tmpParty = msgExpRel.getConsignor();					
  		  			if (tmpParty == null) {    				 
  		  				tmpParty = new Party();
  		  			}  					    			
  		  			tmpParty.setContactPerson(contact);    			
  		  			if (!tmpParty.isEmpty()) {
  		  				msgExpRel.setConsignor(tmpParty);	
  		  			}
  		  		}
  		  		if (typ.equals("2")) {                      //Consignee	                     					
  		  			Party tmpParty = msgExpRel.getConsignee();					
  		  			if (tmpParty == null) {
  		  				tmpParty = new Party();
  		  			}  					
  		  			tmpParty.setContactPerson(contact); 
  		  			if (!tmpParty.isEmpty()) {
  		  				msgExpRel.setConsignee(tmpParty);	
  		  			}
  		  		}      		
  		  		if (typ.equals("3")) {                         //Declarant											
  		  			Party tmpParty = msgExpRel.getDeclarant();
  		  			if (tmpParty == null) {
  		  				tmpParty = new Party();
  		  			}
  		  			tmpParty.setContactPerson(contact); 
  		  			if (!tmpParty.isEmpty()) {
  		  				msgExpRel.setDeclarant(tmpParty);
  		  			}
  		  		}		
  		  		if (typ.equals("4")) {                          //Agent									
  		  			Party tmpParty = msgExpRel.getAgent();
  		  			if (tmpParty == null) {
  		  				tmpParty = new Party();                                     
  		  			}
  		  			tmpParty.setContactPerson(contact); 
  		  			if (!tmpParty.isEmpty()) {
  		  				msgExpRel.setAgent(tmpParty);
  		  			}
  		  		}											
  		  		if (typ.equals("5")) {                        //Subcontractor							
  		  			Party tmpParty = msgExpRel.getSubcontractor();
  		  			if (tmpParty == null) {
  		  				tmpParty = new Party(); 
  		  			}
  		  			tmpParty.setContactPerson(contact); 
  		  			if (!tmpParty.isEmpty()) {
  		  				msgExpRel.setSubcontractor(tmpParty);	
  		  			}
  		  		}
  		  		if (typ.equals("6")) {                      //new for V21 FinalUser	                     					
  		  			Party tmpParty = msgExpRel.getFinalRecipient();					
  		  			if (msgExpRel.getFinalRecipient() == null) {
  		  				tmpParty = new Party();	
  		  			}  					
  		  			tmpParty.setContactPerson(contact); 
  		  			if (!tmpParty.isEmpty()) {
  		  				msgExpRel.setFinalRecipient(tmpParty);	
  		  			}
  		  		}
			 }
		}			
	}
	
	private void mapDeaToKids() {
		if (msgADP.getDaeSubset() == null) {
			return;
		}
		msgExpRel.setShortageInQuantity(msgADP.getDaeSubset().getKzmin());
		msgExpRel.setTotalNumberPositions(msgADP.getDaeSubset().getAnzpos());
		msgExpRel.setTotalNumberPackages(msgADP.getDaeSubset().getAnzcol());
		msgExpRel.setAlternativeDocument(msgADP.getDaeSubset().getAltnot());
	}
	
	private void mapAspToKids() {
		if (msgADP.getAspSubset() == null) {
			return;
		}
		//EI20090421: msgExpRel.setCancellationTime(msgADP.getAspSubset().getStodat());			
		msgExpRel.setDateOfExit(msgADP.getAspSubset().getExtdat()); 					
		msgExpRel.setReleaseTime(msgADP.getAspSubset().getUebdat());
		msgExpRel.setAcceptanceTime(msgADP.getAspSubset().getAndat());
		msgExpRel.setReceiveTime(msgADP.getAspSubset().getAckdat());		
		//EI20090421: msgExpRel.setTimeOfRejection(msgADP.getAspSubset().getGstdat());
		//EI20090421: msgExpRel.setBeginTimeOfProcessing(msgADP.getAspSubset().getBwbdat());
				
		LoadingTime tmpLoadingTime = new LoadingTime();
		tmpLoadingTime.setBeginTime(msgADP.getAspSubset().getGststr());
		tmpLoadingTime.setEndTime(msgADP.getAspSubset().getGstend());
		if (!tmpLoadingTime.isEmpty()) {
			msgExpRel.setLoadingTime(tmpLoadingTime);  		
		}
		TIN tmpTinD = new TIN();
		tmpTinD.setTIN(msgADP.getAspSubset().getTinan());
		tmpTinD.setIdentificationType(msgADP.getAspSubset().getDtzoan());
		tmpTinD.setBO(msgADP.getAspSubset().getNlan());
		if (!tmpTinD.isEmpty()) {
			if (msgExpRel.getDeclarant() != null) {
				if (msgExpRel.getDeclarant().getPartyTIN() == null || msgExpRel.getDeclarant().getPartyTIN().isEmpty()) {
					msgExpRel.getDeclarant().setPartyTIN(tmpTinD);
				}
			} else {
				Party declarant = new Party();
				declarant.setPartyTIN(tmpTinD);		
				msgExpRel.setDeclarant(declarant);
			}
		}
		TIN tmpTinA = new TIN();
		tmpTinA.setTIN(msgADP.getAspSubset().getTinva());
		tmpTinA.setIdentificationType(msgADP.getAspSubset().getDtzova());
		tmpTinA.setBO(msgADP.getAspSubset().getNlva());	
		if (!tmpTinA.isEmpty()) {				
			if (msgExpRel.getAgent() != null) {
				if (msgExpRel.getAgent().getPartyTIN() == null || msgExpRel.getAgent().getPartyTIN().isEmpty()) {
					msgExpRel.getAgent().setPartyTIN(tmpTinA);
				}
			} else {
				Party agent = new Party();
				agent.setPartyTIN(tmpTinA);
				msgExpRel.setAgent(agent);
			}
		}
	}
	
	private void mapAdpToSeal() {
		if (msgADP.getEdaSubset() == null && (msgADP.getAvsList() == null)) {
			return;
		}
		
		Seal tmpSeal = new Seal();
		if (msgADP.getEdaSubset() != null) {
			tmpSeal.setKind(msgADP.getEdaSubset().getVsart());
			tmpSeal.setNumber(msgADP.getEdaSubset().getAnzve());	
			tmpSeal.setUseOfTydenseals(msgADP.getEdaSubset().getKztyd());	
			tmpSeal.setUseOfTydensealStock(msgADP.getEdaSubset().getKzstock());		
		}
		if (msgADP.getAvsList() != null) {				
			for (TsAVS tmpAvs : msgADP.getAvsList()) {	
				if (tmpAvs != null) {
					String tmpSealNr = tmpAvs.getSeal();					
					tmpSeal.addSealNumberList(tmpSealNr);
				}
			}
		}
		if (!tmpSeal.isEmpty()) {
			msgExpRel.setSeal(tmpSeal);
		}
	}		
				
	private MsgExpRelPos mapAdpPosToGoodsItem(MsgADPPos position) {
		if (position == null) {
			return null;
		}
		if (position.getApoSubset() == null) {
			return null;
		}
    	MsgExpRelPos goodsItem = new MsgExpRelPos();
    	
     	goodsItem.setItemNumber(position.getApoSubset().getPosnr());    
     	goodsItem.setOriginItemNumber(position.getApoSubset().getOripos());     	    //new V21
     	goodsItem.setArticleNumber(position.getApoSubset().getArtnr());
     	goodsItem.setDescription(position.getApoSubset().getWbsch());
     	goodsItem.setUCROtherSystem(position.getApoSubset().getFregnr());
     	goodsItem.setAnnotation(position.getApoSubset().getVerm());	     	
     	goodsItem.setOriginFederalState(position.getApoSubset().getUbland());	
     	// CK120827
     	// goodsItem.setNetMass(position.getApoSubset().getEigmas());
     	goodsItem.setNetMass(Utils.removeZabisDecimalPlaceV7(position.getApoSubset().getEigmas(), 3));
     	// CK120827
     	// goodsItem.setGrossMass(position.getApoSubset().getRohmas());     	
     	goodsItem.setGrossMass(Utils.removeZabisDecimalPlaceV7(position.getApoSubset().getRohmas(), 3));
     	if (subversion.equals("2101")) {        //EI20130510
     		goodsItem.setNetMass(position.getApoSubset().getEigmas());
     		goodsItem.setGrossMass(position.getApoSubset().getRohmas()); 
		}
     	
     	goodsItem.setAddressCombination(position.getApoSubset().getAdrkon());    //new V21     	
     	if (!Utils.isStringEmpty(position.getApoSubset().getGesart())) {     	
     		Business tmpBusiness = new Business();
     		tmpBusiness.setBusinessTypeCode(msgADP.getEamSubset().getGesart());				
     		goodsItem.setBusiness(tmpBusiness);
     	}
     	
     	IncoTerms tmpIncoTerms  = new IncoTerms();
     	tmpIncoTerms.setIncoTerm(position.getApoSubset().getLibart()); //new V21
     	tmpIncoTerms.setText(position.getApoSubset().getLibinc());  //new V21
     	tmpIncoTerms.setPlace(position.getApoSubset().getLibort());  //new V21
     	if (!tmpIncoTerms.isEmpty()) {
     		msgExpRel.setIncoTerms(tmpIncoTerms);	
     	}
     	goodsItem.setWatermark(position.getApoSubset().getKzh2o());     //new V21
     	     	
      	CommodityCode tmpCommodityCode = new CommodityCode();
        tmpCommodityCode.setTarifCode(position.getApoSubset().getTnr());
        tmpCommodityCode.setEUTarifCode(position.getApoSubset().getTnrtrc());
        tmpCommodityCode.setTarifAddition1(position.getApoSubset().getTnrzu1());
        tmpCommodityCode.setTarifAddition2(position.getApoSubset().getTnrzu2());
        tmpCommodityCode.setAddition(position.getApoSubset().getTnrnat());   
        if (!tmpCommodityCode.isEmpty()) {
        	goodsItem.setCommodityCode(tmpCommodityCode);
        }	
        ApprovedTreatment tmpApprovedTreatment = new ApprovedTreatment();
        tmpApprovedTreatment.setDeclared(position.getApoSubset().getAnmvrf());
        tmpApprovedTreatment.setPrevious(position.getApoSubset().getPrevrf());
        tmpApprovedTreatment.setNational(position.getApoSubset().getNatvrf());   
        // CK120827
        // asvfr in Ts APO nicht ATP!
        if (position.getApoSubset().getAsvfr() != null) {      		
      		tmpApprovedTreatment.setCodeForRefund(position.getApoSubset().getAsvfr());
      	}
        if (!tmpApprovedTreatment.isEmpty()) {
        	goodsItem.setApprovedTreatment(tmpApprovedTreatment); 
		} 	
        Statistic  tmpStatistic = new Statistic();
        // CK120827
        // tmpStatistic.setAdditionalUnit(position.getApoSubset().getWmahst()); 
        tmpStatistic.setAdditionalUnit(Utils.removeZabisDecimalPlaceV7(position.getApoSubset().getWmahst(), 3));
        if (subversion.equals("2101")) {        //EI20130510
        	tmpStatistic.setAdditionalUnit(position.getApoSubset().getWmahst()); 
		}
        tmpStatistic.setStatisticalValue(position.getApoSubset().getAhwert()); 
        if (!tmpStatistic.isEmpty()) {
        	goodsItem.setStatistic(tmpStatistic);          	             	           
        }
        if (position.getSapSubset() != null) {
        	goodsItem.setShipmentNumber(position.getSapSubset().getKnrsdg());    
        	goodsItem.setDangerousGoodsNumber(position.getSapSubset().getUndgnr());  
        	goodsItem.setPaymentType(position.getSapSubset().getBfgkzw());   
     	}
        if (position.getAtpSubset() != null) {     		
     		ExportRefundItem tmpExportRefundItem = new ExportRefundItem();          	
     		tmpExportRefundItem.setAddition1(position.getAtpSubset().getWberg1());
     		tmpExportRefundItem.setAddition2(position.getAtpSubset().getWberg2());
     		tmpExportRefundItem.setOriginCountry(position.getAtpSubset().getUland());
     		tmpExportRefundItem.setAmountCode(position.getAtpSubset().getKzwert());
     		tmpExportRefundItem.setAmount(position.getAtpSubset().getZfnai());
     		// CK120827
     		// tmpExportRefundItem.setTypeOfRefund(position.getAtpSubset().getMenge());
     		tmpExportRefundItem.setTypeOfRefund(Utils.removeZabisDecimalPlaceV7(position.getAtpSubset().getMenge(),1));
     		// CK120827
     		// tmpExportRefundItem.setAmountCoefficient(position.getAtpSubset().getApgket());
     		tmpExportRefundItem.setAmountCoefficient(Utils.removeZabisDecimalPlaceV7(position.getAtpSubset().getApgket(),4));
     	    // CK120827
     		// tmpExportRefundItem.setPartA(position.getAtpSubset().getAnwrta());
     		tmpExportRefundItem.setPartA(Utils.removeZabisDecimalPlaceV7(position.getAtpSubset().getAnwrta(),2));
     	    // CK120827     		
     		// tmpExportRefundItem.setPartB(position.getAtpSubset().getAnwrtb());
     		tmpExportRefundItem.setPartB(Utils.removeZabisDecimalPlaceV7(position.getAtpSubset().getAnwrtb(),2));
     	    // CK120827     		
     		// tmpExportRefundItem.setPartC(position.getAtpSubset().getAnwrtc());
     		tmpExportRefundItem.setPartC(Utils.removeZabisDecimalPlaceV7(position.getAtpSubset().getAnwrtc(),2));
     	    // CK120827     		
     		// tmpExportRefundItem.setPartD(position.getAtpSubset().getAnwrtd());
     		tmpExportRefundItem.setPartD(Utils.removeZabisDecimalPlaceV7(position.getAtpSubset().getAnwrtd(),2));
     		tmpExportRefundItem.setUnitOfMeasurement(position.getAtpSubset().getMeaest());
     		if (subversion.equals("2101")) {        //EI20130510     			
    			tmpExportRefundItem.setTypeOfRefund(position.getAtpSubset().getMenge());
    			tmpExportRefundItem.setAmountCoefficient(position.getAtpSubset().getApgket());
    			tmpExportRefundItem.setPartA(position.getAtpSubset().getAnwrta());
    			tmpExportRefundItem.setPartB(position.getAtpSubset().getAnwrtb());
    			tmpExportRefundItem.setPartC(position.getAtpSubset().getAnwrtc());
    			tmpExportRefundItem.setPartD(position.getAtpSubset().getAnwrtd());
    		}
     	
     		if (position.getAtiList() != null) {     			
     			for (TsATI tmpAti : position.getAtiList()) {
     			if (tmpAti != null) {
     				Ingredients tmpIngredients = new Ingredients();
     				
     				tmpIngredients.setSequentialNumber(tmpAti.getLfdnr());
     			    // CK120827   
     				// tmpIngredients.setAmount1(tmpAti.getUrfkt1()); 
     				tmpIngredients.setAmount1(Utils.removeZabisDecimalPlaceV7(tmpAti.getUrfkt1(), 4));
     				tmpIngredients.setKindOfCoefficient(tmpAti.getKzfkt1()); 
     				// CK120827
     				// tmpIngredients.setAmount2(tmpAti.getUrfkt2()); 
     				tmpIngredients.setAmount2(Utils.removeZabisDecimalPlaceV7(tmpAti.getUrfkt2(), 4));
     				// CK120827
     				// tmpIngredients.setRate(tmpAti.getUrfkts()); 
     				tmpIngredients.setRate(Utils.removeZabisDecimalPlaceV7(tmpAti.getUrfkts(), 4));
     				// CK120827
     				// tmpIngredients.setWeightPercent(tmpAti.getGhtant()); 
     				tmpIngredients.setWeightPercent(Utils.removeZabisDecimalPlaceV7(tmpAti.getGhtant(), 3));
     				// CK120827
     				// tmpIngredients.setWeight(tmpAti.getMgeant()); 
     				tmpIngredients.setWeight(Utils.removeZabisDecimalPlaceV7(tmpAti.getMgeant(), 1));
     				tmpIngredients.setUniqueFactoryNumber(tmpAti.getHeklnr()); 
     				tmpIngredients.setTarifNumber(tmpAti.getKeynr()); 
     				tmpIngredients.setLicenceNumber(tmpAti.getLiznr()); 
     				tmpIngredients.setText(tmpAti.getText());  
      		
     				if (subversion.equals("2101")) {        //EI20130510     					
     					tmpIngredients.setAmount1(tmpAti.getUrfkt1()); 
     					tmpIngredients.setAmount2(tmpAti.getUrfkt2()); 
     					tmpIngredients.setRate(tmpAti.getUrfkts()); 
     					tmpIngredients.setWeightPercent(tmpAti.getGhtant());
     					tmpIngredients.setWeight(tmpAti.getMgeant()); 
     				}
     				tmpExportRefundItem.addIngredientsList(tmpIngredients);
     			}
     			}
     			if (!tmpExportRefundItem.isEmpty()) {
     				goodsItem.setExportRefundItem(tmpExportRefundItem);      
     			}
     	   }
       }
        
       if (position.getAcoList() != null) {
    	   for (TsACO tmpAco : position.getAcoList()) {	
    		   if (tmpAco != null) {
    			   Packages tmpPackage = new Packages();      					
    			   tmpPackage.setQuantity(tmpAco.getColanz());
    			   tmpPackage.setSequentialNumber(tmpAco.getLfdnr()); 
    			   tmpPackage.setType(tmpAco.getColart()); 
    			   tmpPackage.setMarks(tmpAco.getColzch());  
    			   if (!tmpPackage.isEmpty()) {
    				   goodsItem.addPackagesList(tmpPackage); 
    			   }

    		   }
    	   }      	 
      }            
      
      if (position.getAcnList() != null) {
    	  Container tmpContainer = new Container();      	
      		for (TsACN tmpAcn : position.getAcnList()) {	      		      		    	      			
      			tmpContainer.addNumberList(tmpAcn.getConnr());								 
      		}
      		if (!tmpContainer.isEmpty()) {
      			goodsItem.setContainer(tmpContainer); 
      		}
      }
      	     
      if (position.getAzvList() != null) {
       	for (TsAZV tmpAzv : position.getAzvList()) {
       		if (tmpAzv != null) {       			
       			//PreviousDocumentV20 tmpPreviousDoc = new PreviousDocumentV20();  
       			//tmpPreviousDoc.setType(tmpAzv.getVptyp());             
       			//tmpPreviousDoc.setMarks(tmpAzv.getAzvref());
       			//tmpPreviousDoc.setAdditionalInformation(tmpAzv.getAzvzus()); 
       			PreviousDocumentV21 tmpPreviousDoc = new PreviousDocumentV21(); //EI20130827
       			tmpPreviousDoc.setType(tmpAzv.getVptyp());  
       			tmpPreviousDoc.setReference(tmpAzv.getAzvref());
       			tmpPreviousDoc.setAdditionalInformation(tmpAzv.getAzvzus());   
       			if (!tmpPreviousDoc.isEmpty()) {
       				goodsItem.addPreviousDocumentList(tmpPreviousDoc);	
       			}
       		}
       	}
      }
      
      if (position.getAedList() != null) {
     	 for (TsAED tmpAed : position.getAedList()) {
     		if (tmpAed != null) {
     			DocumentV20 tmpProdDoc = new DocumentV20();   
     			tmpProdDoc.setMsgFlag("K");
     			tmpProdDoc.setQualifier(tmpAed.getUntqar());
     			tmpProdDoc.setType(tmpAed.getUntart());
     			tmpProdDoc.setReference(tmpAed.getUntnr());
     			tmpProdDoc.setAdditionalInformation(tmpAed.getUntzus()); 
     			tmpProdDoc.setDetail(tmpAed.getDetail());
     			tmpProdDoc.setIssueDate(tmpAed.getUntdat());
     			tmpProdDoc.setExpirationDate(tmpAed.getGbdat());  
     			tmpProdDoc.setValue(tmpAed.getWert());
     			goodsItem.addDocumentList(tmpProdDoc);
     			
     			WriteDownAmountV20 tmpWDA = new WriteDownAmountV20("K");          		   
         		tmpWDA.setUnitOfMeasurement(tmpAed.getMgeme());         		
         		tmpWDA.setValue(tmpAed.getAbgwrt()); 
         		tmpProdDoc.setWriteDownAmount(tmpWDA);
         		if (!tmpProdDoc.isEmpty()) {
         			goodsItem.addDocumentList(tmpProdDoc);	
         		}
     		}
     	 }
      }         
      if (position.getAdrList() != null) {	
          // only Consignee defined        
    	  for (TsADR tmpTsAdr : position.getAdrList()) { 	
    		if (tmpTsAdr != null) {
    		  String typ = "";
    		  if (!Utils.isStringEmpty(tmpTsAdr.getTyp())) {
    			  typ = tmpTsAdr.getTyp();
    		  }
    		  Address tmpAdr = new Address();
    		  String tmpName = tmpTsAdr.getName1() + " " + tmpTsAdr.getName2() + " " + tmpTsAdr.getName3();      				           
    		  tmpAdr.setName(tmpName);
    		  tmpAdr.setStreet(tmpTsAdr.getStr());
    		  tmpAdr.setCity(tmpTsAdr.getOrt());
    		  tmpAdr.setPostalCode(tmpTsAdr.getPlz());	
    		  tmpAdr.setCountry(tmpTsAdr.getLand());	
    			
    		  TIN tmpTIN = new TIN();      			
    		  tmpTIN.setCustomerIdentifier(tmpTsAdr.getKdnr());
    		  tmpTIN.setTIN(tmpTsAdr.getTin());
    		  tmpTIN.setBO(tmpTsAdr.getNl());
    		  tmpTIN.setIdentificationType(tmpTsAdr.getDtzo());
    			
              if (typ.equals("2")) {    				 //Consignee	                     					
      			  Party tmpParty = new Party();					      			 				
      			  tmpParty.setETNAddress(tmpTsAdr.getEtn());			
      			  tmpParty.setAddress(tmpAdr);
      			  tmpParty.setPartyTIN(tmpTIN);  
      			  if (!tmpParty.isEmpty()) {
      				  goodsItem.setConsignee(tmpParty);	
      			  }
              }
              if (typ.equals("6")) {     				//new for V21 FinalUser	                     					
      			  Party tmpParty = new Party();					      			  				
      			  tmpParty.setETNAddress(tmpTsAdr.getEtn());			
      			  tmpParty.setAddress(tmpAdr);
      			  tmpParty.setPartyTIN(tmpTIN);  	
      			  if (!tmpParty.isEmpty()) {
      				  goodsItem.setFinalRecipient(tmpParty);	
      			  }
              }              
    	    }
    	  }
      }
    
      //BZL == BondedWarehouseCompletion
      if (position.getApoSubset() != null) {    	 
    	  if (!Utils.isStringEmpty(position.getApoSubset().getZlbez()) && position.getBzlList() != null) {
    		 
		    if (position.getBzlList() != null) {		    
				Completion tmpBWCopml = new Completion();  
				tmpBWCopml.setAuthorizationNumber(position.getApoSubset().getAzvbew());
				tmpBWCopml.setReferenceNumber(position.getApoSubset().getZlbez());
    	 			
				int i = 0;
  				for (TsBZL tmpBzl : position.getBzlList()) {
  					if (tmpBzl != null) {
  						i = i + 1;
  						CompletionItem tmpComplItem = new CompletionItem();
  						WriteDownAmount tmpWDAmount = new WriteDownAmount("K");
  						WriteDownAmount tmpTrAmount = new WriteDownAmount("K");
  						tmpComplItem.setWriteDownAmount(tmpWDAmount); 
  						tmpComplItem.setTradeAmount(tmpTrAmount); 
  			
  						tmpComplItem.setSequentialNumber("" + i);
  						tmpComplItem.setPositionNumber(tmpBzl.getVposnr());
  						tmpComplItem.setUCR(tmpBzl.getVregnr());
  						tmpComplItem.setEntryInAtlas(tmpBzl.getAtlas());
  						tmpComplItem.setText(tmpBzl.getTxzus());
  						tmpComplItem.setTarifNumber(tmpBzl.getWanr());
  						tmpComplItem.setUsualFormOfHandling(tmpBzl.getKzuebl());
  						
  						tmpWDAmount.setUnitOfMeasurement(tmpBzl.getMeabg());  			
  						// CK120827
  						// tmpWDAmount.setWriteoffValue(tmpBzl.getMgeabg());  //EI20090818
  						tmpWDAmount.setWriteoffValue(Utils.removeZabisDecimalPlaceV7(tmpBzl.getMgeabg(), 3)); 
  			
  						tmpTrAmount.setUnitOfMeasurement(tmpBzl.getMehdl());
  						// CK120827
  						// tmpTrAmount.setWriteoffValue(tmpBzl.getMgehdl()); 		
  						tmpTrAmount.setWriteoffValue(Utils.removeZabisDecimalPlaceV7(tmpBzl.getMgehdl(), 3));
  			            
  						if (subversion.equals("2101")) {        //EI20130510
  							tmpWDAmount.setWriteoffValue(tmpBzl.getMgeabg());
  							tmpTrAmount.setWriteoffValue(tmpBzl.getMgehdl()); 
  						}
  						
  			            tmpBWCopml.addCompletionItemList(tmpComplItem); 
  			            
  					}
  				}
  				goodsItem.setBondedWarehouseCompletion(tmpBWCopml);
		     		
		    }
    	 }
      }
      
      //BAV == InwardProcessingCompletion
      if (position.getApoSubset() != null) {    	  
    	if (!Utils.isStringEmpty(position.getApoSubset().getAzvbew()) && position.getBavList() != null) {    		
    		if (position.getBavList() != null) {        	
        		Completion tmpIPCopml = new Completion();      		    		
        		tmpIPCopml.setAuthorizationNumber(position.getApoSubset().getAzvbew());
        		int i = 0;
        		for (TsBAV tmpBav : position.getBavList()) {  
        			if (tmpBav != null) {
        				i = i + 1;
        				CompletionItem tmpComplItem = new CompletionItem();    			
        				tmpComplItem.setSequentialNumber("" + i);
        				tmpComplItem.setPositionNumber(tmpBav.getVposnr());
        				tmpComplItem.setUCR(tmpBav.getVregnr());
        				tmpComplItem.setEntryInAtlas(tmpBav.getAtlas());
        				tmpComplItem.setText(tmpBav.getTxzus());
    			
        				tmpIPCopml.addCompletionItemList(tmpComplItem); 
        			}
        		}
        		goodsItem.setInwardProcessingCompletion(tmpIPCopml);      
        	
    		}
    		    
    	 }
      }
      
      return goodsItem;
    } 
	
}  //ende der klasse
