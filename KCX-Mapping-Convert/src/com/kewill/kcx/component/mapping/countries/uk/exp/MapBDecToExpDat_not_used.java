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
 */

package com.kewill.kcx.component.mapping.countries.uk.exp;

import java.io.StringWriter;
import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.Container;
import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.Seal;
import com.kewill.kcx.component.mapping.countries.common.SealNumber;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpDat;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpDatPos;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ApprovedTreatment;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Cap;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CommodityCode;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ExportRefundItem;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Ingredients;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Invoice;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.LoadingTime;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PlaceOfLoading;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Route;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Statistic;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.WriteDownAmount;
import com.kewill.kcx.component.mapping.formats.Bdec.messages.MsgECustoms;
import com.kewill.kcx.component.mapping.formats.Bdec.messages.MsgECustomsPos;
import com.kewill.kcx.component.mapping.formats.Bdec.subsets.TsAI;
import com.kewill.kcx.component.mapping.formats.Bdec.subsets.TsDB;
import com.kewill.kcx.component.mapping.formats.Bdec.subsets.TsDC;
import com.kewill.kcx.component.mapping.formats.Bdec.subsets.TsDD;
import com.kewill.kcx.component.mapping.formats.Bdec.subsets.TsDI;
import com.kewill.kcx.component.mapping.formats.Bdec.subsets.TsDL;
import com.kewill.kcx.component.mapping.formats.Bdec.subsets.TsDO;
import com.kewill.kcx.component.mapping.formats.Bdec.subsets.TsDP;
import com.kewill.kcx.component.mapping.formats.Bdec.subsets.TsDV;
import com.kewill.kcx.component.mapping.formats.Bdec.subsets.TsHH;
import com.kewill.kcx.component.mapping.formats.Bdec.subsets.TsHR;
import com.kewill.kcx.component.mapping.formats.Bdec.subsets.TsHS;
import com.kewill.kcx.component.mapping.formats.Bdec.subsets.TsNA;
import com.kewill.kcx.component.mapping.formats.kids.aes.BodyExportDeclarationKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: MapBDecToExpDat.java<br>
 * Erstellt		: 05.01.2010<br>
 * Beschreibung	: Mapping of BDec into KIDS-Format. 
 * 
 * @author iwaniuk
 * @version 6.0.00
 */
public class MapBDecToExpDat_not_used extends KidsMessage {
	private MsgECustoms msgECustoms;
	private MsgExpDat msgExpDat;
	
	public MapBDecToExpDat_not_used() {
		msgExpDat = new MsgExpDat();			
	}

	public void setMessage(MsgECustoms argument) {
		
		this.msgECustoms = argument;						
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
	        header.setHeaderFields(msgECustoms.getVorSubset());
	        header.setMessageName("ExportDeclaration");
	        header.setMessageID(getMsgID());
	        header.writeHeader();
	        
	        //BodyExportADPtoReverseDeclarationKids body   = new BodyExportADPtoReverseDeclarationKids(writer);
	        BodyExportDeclarationKids body   = new BodyExportDeclarationKids(writer); //EI20090508
	        body.setMessage(msgExpDat);
	        body.setKidsHeader(header);

	        getCommonFieldsDTO().setReferenceNumber(msgExpDat.getReferenceNumber());
	        body.writeBody();
	        
	        closeElement();  // soap:Envelope
	        writer.writeEndDocument();
	        
	        writer.flush();
	        writer.close();
	        
	        Utils.log("(MsgExportDeclaration getMessage) Msg = " + xmlOutputString.toString());
	        
	    	} catch (XMLStreamException e) {
	        
	    		e.printStackTrace();
	    	}
	    
	    	return xmlOutputString.toString();
		}
		
	
	public void setMsgFields() {
		
		if (msgECustoms.getHHSubset() != null) {
			msgExpDat.setTypeOfPermit(msgECustoms.getHHSubset().getMessageType());
			msgExpDat.setTypeOfRepresentation(msgECustoms.getHHSubset().getDecltRep());
			msgExpDat.setDestinationCountry(msgECustoms.getHHSubset().getDestCntry());			
			msgExpDat.setPlaceOfLoading(getPlaceOfLoading(msgECustoms.getHHSubset().getGdsLocn(),
														  msgECustoms.getHHSubset().getEPlaLdg()));			
			msgExpDat.setCustomsOfficeExport(msgECustoms.getHHSubset().getExitOffice()); 						
			msgExpDat.setLoadingTime(getLoadingTime(msgECustoms.getHHSubset().getGdsArrDtmInld(), 
													msgECustoms.getHHSubset().getGdsDepDtmInld()));			
			msgExpDat.setReferenceNumber(msgECustoms.getHHSubset().getDeclnUcr());			
			msgExpDat.setUCROtherSystem(msgECustoms.getHHSubset().getMasterUcr());
			
			msgExpDat.setTransportMeansBorder(getTransportMeans(msgECustoms.getHHSubset().getTrptModeCode(),
					msgECustoms.getHHSubset().getTrptId(), msgECustoms.getHHSubset().getTrptCntry()));	
			msgExpDat.setTransportMeansInland(getTransportMeans(msgECustoms.getHHSubset().getTrptModeInld(),
					msgECustoms.getHHSubset().getTrptIdInld(), ""));	
			
		    msgExpDat.setConsignor(getParty(msgECustoms.getHHSubset().getCnsgrShortname()));	   //EI20110530
		    msgExpDat.setConsignee(getParty(msgECustoms.getHHSubset().getCnsgeShortname()));	   //EI20110530
		    msgExpDat.setDeclarant(getParty(msgECustoms.getHHSubset().getDesltShortname()));	   //EI20110530
		    msgExpDat.setWarehousekeeper(getParty(msgECustoms.getHHSubset().getPermShortname()));  //EI20110530
		    
			msgExpDat.setInvoice(getInvoice(msgECustoms.getHHSubset().getInvCrrn()));		    
			msgExpDat.setTotalNumberOfPackages(msgECustoms.getHHSubset().getTotPkgs());  						
			msgExpDat.setCap(getCap(msgECustoms.getHHSubset()));
			
			msgExpDat.setDispatchCountry(msgECustoms.getHHSubset().getDispCntry());   				
			msgExpDat.setOrderNumber(msgECustoms.getHHSubset().getTdrOwnRefEnt());					    
		    msgExpDat.setContact(getContact(msgECustoms.getHHSubset().getUserRef()));			
			msgExpDat.setUCRNumber(msgECustoms.getHHSubset().getMrn());			
			msgExpDat.setPaymentType(msgECustoms.getHHSubset().getTrptChgeMop());
			//msgExpDat.setSupervisingCustomsOffice(msgECustoms.getHHSubset().getSpoffShortname());	
			
			if (msgECustoms.getHHSubset().getStc().equals("N")) {
			     msgExpDat.setSituationCode("C");	
			} else {				
				   msgExpDat.setSituationCode(msgECustoms.getHHSubset().getSpCirc());			
			}			
			msgExpDat.setCorrectionReason(msgECustoms.getHHSubset().getReason());			
			msgExpDat.setTransferToTransitSystem(msgECustoms.getHHSubset().getTransit());			
	    }
		
		if (msgECustoms.getHDList() != null) {
			List listHD = msgECustoms.getHDList();			
			if (listHD.size() > 0) {
				for (int i = 0, listSize = listHD.size(); i < listSize; i++) {	
					TsDO tempDO = (TsDO) listHD.get(i);					
					msgExpDat .addDocumentList(getDocument(tempDO)); 						
				}									
			}
		}	
		
		if (msgECustoms.getHSList() != null) {
			List listHS = msgECustoms.getHSList();
			if (listHS.size() > 0) {
				Seal seal = new Seal();				
				List <SealNumber> sealNumbersList = new Vector<SealNumber>();
				for (int i = 0, listSize = listHS.size(); i < listSize; i++) {
					TsHS tempHS = (TsHS) listHS.get(i);				
					sealNumbersList.add(getSealNumber(tempHS));
				}
				seal.setSealNumbersList(sealNumbersList);
				msgExpDat.setSeal(seal);				
			}
		}
		
		if (msgECustoms.getHRList() != null) {
			List listHR = msgECustoms.getHRList();
			if (listHR.size() > 0) {
				Route route = new Route();
				List <String> countryList = new Vector<String>();					
				for (int i = 0, listSize = listHR.size(); i < listSize; i++) {
					TsHR tempHR = (TsHR) listHR.get(i);
					countryList.add(tempHR.getRouteCntry());
				}
				route.setCountryList(countryList);
				msgExpDat.setTransportationRoute(route);
			}							
		}
	
		if (msgECustoms.getHAList() != null) {
			List listHA = msgECustoms.getHAList();
			if (listHA.size() > 0) {
				List <Text> addInfoStatementList = new Vector<Text>();	
				for (int i = 0, listSize = listHA.size(); i < listSize; i++) {
					TsAI tempAI = (TsAI) listHA.get(i);
					Text text = new Text();
					text.setCode(tempAI.getCode());  
					text.setText(tempAI.getText()); 
					addInfoStatementList.add(text);
				}
				msgExpDat.setAddInfoStatementList(addInfoStatementList);
			}        	
		}			
		
		List <MsgECustomsPos> posList = msgECustoms.getPosList();				
		if (posList != null) {				
			for (int i = 0, listSize = posList.size(); i < listSize; i++) {				
				MsgECustomsPos tmpPos = (MsgECustomsPos) posList.get(i);				
				msgExpDat.addGoodsItemList(setExpDatPosition(tmpPos));
			}
		}
						
		if (msgECustoms.getNAList() != null) {
			List listNA = msgECustoms.getNAList();	
			if (listNA.size() > 0) {
				for (int i = 0, listSize = listNA.size(); i < listSize; i++) {
					TsNA tempNA = (TsNA) listNA.get(i);
					processNA(tempNA);									
				}		
			}     	
		}
		
		//brauche ich nicht: if (msgECustoms.getTDSubset() != null) {
				
    }
	
    private MsgExpDatPos setExpDatPosition(MsgECustomsPos position) {
    	if (position == null) {
    		return null;
    	}    	
    	MsgExpDatPos msgExpDatPos = new MsgExpDatPos();
    	ExportRefundItem tempERI = new ExportRefundItem();
    	Ingredients tempIng = new Ingredients();
    	tempERI.addIngredientsList(tempIng);
    	msgExpDatPos.setExportRefundItem(tempERI);
     	
		if (position.getDDSubset() != null) {
			TsDD tempDD = position.getDDSubset();			
			ApprovedTreatment tempAT = new ApprovedTreatment();
			tempAT.setDeclared(tempDD.getCpc());
			msgExpDatPos.setApprovedTreatment(tempAT);
			
			CommodityCode tempCC = new CommodityCode();
			tempCC.setTarifCode(tempDD.getTraficCmdtyCode());
			tempCC.setTarifAddition1(tempDD.getEcSupplement());
			tempCC.setTarifAddition2(tempDD.getEcSupplement2());
			msgExpDatPos.setCommodityCode(tempCC);					   
			//tempERI.setOriginCountry(tempDD.getItemOrigCntry());			
			//tempIng.setUniqueFactoryNumber(tempDD.getIbRecipeCode());
			//tempERI.addIngredientsList(tempIng);
			//msgExpDatPos.setExportRefundItem(tempERI);
			msgExpDatPos.getExportRefundItem().setOriginCountry(tempDD.getItemOrigCntry());
			msgExpDatPos.getExportRefundItem().getIngredientsList().get(0).
									setUniqueFactoryNumber(tempDD.getIbRecipeCode());
			msgExpDatPos.setGrossMass(tempDD.getItemGrossMass());  
			msgExpDatPos.setNetMass(tempDD.getItemNetMass()); 
			
			Statistic tempSt = new Statistic();
			tempSt.setAdditionalUnit(tempDD.getItemSuppUnits());
			tempSt.setStatisticalValue(tempDD.getItemStatvalDc());
			tempSt.setValue(tempDD.getItemPrcAc());
			tempSt.setStatisticalValueConfirmation(tempDD.getConfirmValue());
			tempSt.setAdditionalUnitConfirmation(tempDD.getConfirmSuppUnits());
			msgExpDatPos.setStatistic(tempSt);
			
			msgExpDatPos.setThirdQuantity(tempDD.getItemThrdQty()); 					
			msgExpDatPos.setConsignee(getParty(tempDD.getICnsgeShortname())); 			
			//msgExpDatPos.setSupervisingCustomsOffice(tempDD.getISpoffShortname()); 			
			msgExpDatPos.setDangerousGoodsNumber(tempDD.getUndgCode()); 
			msgExpDatPos.setDescription(tempDD.getGdsDesc()); 
			msgExpDatPos.setArticleNumber(tempDD.getProductCode()); 
			msgExpDatPos.setPaymentType(tempDD.getITrptChgeMop()); 
			msgExpDatPos.setNetMassConfirmation(tempDD.getConfirmNetMass()); 		 
		}		
		if (position.getDPList() != null) {		        
			for (int i = 0, listSize = position.getDPList().size(); i < listSize; i++) {
	        	TsDP tempDP = (TsDP) position.getDPList().get(i);	 	        	
	        	msgExpDatPos.addPackagesList(getPackage(tempDP)); 	        	
			}	        
	   }		
	   if (position.getDAList() != null) {
		   for (int i = 0, listSize = position.getDAList().size(); i < listSize; i++) {
			   TsAI tempAI = (TsAI) position.getDAList().get(i);       				         
			   msgExpDatPos.addAddInfoStatementList(getText(tempAI));
		   }
	  	}	  
	  	if (position.getDVList() != null) {
	  		for (int i = 0, listSize = position.getDVList().size(); i < listSize; i++) {
	  			TsDV tempDV = (TsDV) position.getDVList().get(i);	  			
	  			msgExpDatPos.addPreviousDocumentList(getPrevDoc(tempDV));
	  		}
	  	}	   	
        if (position.getDLList() != null) {
           for (int i = 0, listSize = position.getDLList().size(); i < listSize; i++) {         	
        	   TsDL tempDL = (TsDL) position.getDLList().get(i);  
        	   Ingredients tempIngr = new Ingredients();
        	   if (msgExpDatPos.getExportRefundItem().getIngredientsList().size() > i) { 
        		   //in die schon erfassten ingredients werden die licencen dazu geschrieben:
        		   tempIngr = msgExpDatPos.getExportRefundItem().getIngredientsList().get(i);
        		   if (tempIngr == null) {
        			   tempIngr = new Ingredients();        			   
        		   }
        		   tempIngr.setLicenceNumber(tempDL.getLicenceRef());
        		   tempIngr.setLicenceLineNumber(tempDL.getLicenceLineNo());
        		   tempIngr.setLicenceStatus(tempDL.getLicenceStatus());
        		   tempIngr.setLicenceQuantity(tempDL.getLicenceQuantity());
        		   msgExpDatPos.getExportRefundItem().getIngredientsList().set(i, tempIngr);
        	   } else {	        	
        		   tempIngr.setLicenceNumber(tempDL.getLicenceRef());
        		   tempIngr.setLicenceLineNumber(tempDL.getLicenceLineNo());
        		   tempIngr.setLicenceStatus(tempDL.getLicenceStatus());
        		   tempIngr.setLicenceQuantity(tempDL.getLicenceQuantity());
        		   msgExpDatPos.getExportRefundItem().getIngredientsList().add(tempIngr);
        	   }         
           }
        }      
        if (position.getDIList() != null) {
            for (int i = 0, listSize = position.getDIList().size(); i < listSize; i++) {
            	TsDI tempDI = (TsDI) position.getDIList().get(i);   
            	Ingredients tempIngr = new Ingredients();
            	if (msgExpDatPos.getExportRefundItem().getIngredientsList().size() > i) {
            		//in die schon erfassten ingredients werden die licencen dazu geschrieben:
         		   tempIngr = msgExpDatPos.getExportRefundItem().getIngredientsList().get(i);
         		   if (tempIngr == null) {
         			   tempIngr = new Ingredients();        			   
         		   }
         		   tempIngr.setTarifNumber(tempDI.getIbIngredientCode());
         		   tempIngr.setWeight(tempDI.getIbIngredientQuantity());
         		   msgExpDatPos.getExportRefundItem().getIngredientsList().set(i, tempIngr);
         	   } else {	        	           	               	
             		tempIngr.setTarifNumber(tempDI.getIbIngredientCode());
             		tempIngr.setWeight(tempDI.getIbIngredientQuantity());
             		msgExpDatPos.getExportRefundItem().getIngredientsList().add(tempIngr);    
           	  	}
            }
        }      
        if (position.getDBList() != null) {
         	for (int i = 0, listSize = position.getDBList().size(); i < listSize; i++) {
         		TsDB tempDB = (TsDB) position.getDBList().get(i);     		         		
         		if (msgExpDatPos.getExportRefundItem() != null && i == 0) {
         			msgExpDatPos.getExportRefundItem().setPartA(tempDB.getIbsdCode());
         			msgExpDatPos.getExportRefundItem().setPartB(tempDB.getPercentage());
         		} //else {  // for KIDS exists only one ExportRefundItem         		
         	}
        }  
        if (position.getDOList() != null) {
         	for (int i = 0, listSize = position.getDOList().size(); i < listSize; i++) {
         		TsDO tempDO = (TsDO) position.getDOList().get(i);           		
         		msgExpDatPos.addDocumentList(getDocument(tempDO));
         	}
        }
        if (position.getDCList() != null) {
    	    Container tempContainer = new Container(); 
            for (int i = 0, listSize = position.getDCList().size(); i < listSize; i++) {         		
         		TsDC tempDC = (TsDC) position.getDCList().get(i);          		
         		tempContainer.addNumberList(tempDC.getCntrNo());
            }
            msgExpDatPos.setContainer(tempContainer);
        }          
     
        return msgExpDatPos;
    } // ende Position
    
    private Party getParty(String shortName) {            //EI20110530    	
    	if (Utils.isStringEmpty(shortName)) {
    		return null;
    	}
    	Party party = new Party();
    	TIN partyTIN = new TIN();
    	partyTIN.setTIN(shortName);
    	party.setPartyTIN(partyTIN);
    	
    	return party;
    }
    private LoadingTime getLoadingTime(String beginn, String end) {            //EI20110530
    	if (Utils.isStringEmpty(beginn) && Utils.isStringEmpty(end)) {
    		return null;
    	}
    	LoadingTime loadingTime = new LoadingTime();
    	loadingTime.setBeginTime(beginn);
    	loadingTime.setEndTime(end);	
	
    	return loadingTime;
    }
    private PlaceOfLoading getPlaceOfLoading(String location, String city) {            //EI20110530
    	if (Utils.isStringEmpty(location) && Utils.isStringEmpty(city)) {
    		return null;
    	}
    	PlaceOfLoading placeOfLoading = new PlaceOfLoading();
    	placeOfLoading.setAgreedLocationOfGoods(location);
    	placeOfLoading.setCity(city);
    	//getEPlaLdg == PLZ + City (odre Code)
    	//placeOfLoading.setPostalCode()
    	//placeOfLoading.setCode()
    	return placeOfLoading;
    }
    
    private TransportMeans getTransportMeans(String type, String number, String country) {            //EI20110530
    	if (Utils.isStringEmpty(type) && Utils.isStringEmpty(number)) {
    		return null;
    	}
    	TransportMeans transport = new TransportMeans();
    	transport.setTransportationType(type);
    	transport.setTransportationNumber(number);
    	transport.setTransportationCountry(country);
    	
    	return transport;
    }
    private Invoice getInvoice(String currency) {            //EI20110530
    	if (Utils.isStringEmpty(currency)) {
    		return null;
    	}
    	Invoice invoice = new Invoice();
		invoice.setCurrency(currency);
    	
    	return invoice;
    }   
    private ContactPerson getContact(String identity) {            //EI20110530
    	if (Utils.isStringEmpty(identity)) {
    		return null;
    	}
    	ContactPerson contact = new ContactPerson();
    	contact.setIdentity(identity);
	    
    	return contact;
    }
    private Cap getCap(TsHH tsHH) {            //EI20110530
    	if (tsHH == null) {
    		return null;
    	}
    	Cap cap = new Cap();
		cap.setIBClaimRef(tsHH.getIbClaimRef());
		cap.setIBClaimType(tsHH.getIbClaimType());
		cap.setIBRegNo(tsHH.getIbRegNo());
		cap.setIBGAN(tsHH.getIbGan());
    	
		if (cap.isEmpty()) {
			return null;
		} else {
			return cap;
		}
    }
  
    private Document getDocument(TsDO tsDO) {            //EI20110530
    	if (tsDO == null) {
    		return null;
    	}
    	Document document = new Document();			
		document.setTypeKids(tsDO.getCode());
		document.setReference(tsDO.getReference());
		document.setQualifier(tsDO.getStatus());
		document.setAdditionalInformation(tsDO.getReason());
		document.setDetail(tsDO.getPart());
		
		if (tsDO.getQuantity() != null) {			
			WriteDownAmount wdQuantity = new WriteDownAmount();
			wdQuantity.setWriteoffValue(tsDO.getQuantity());
			document.setWriteDownQuantity(wdQuantity);			
		}
		    	
		if (document.isEmpty()) {
			return null;
		} else {
			return document;
		}
    }
    private SealNumber getSealNumber(TsHS tsHS) {            //EI20110530
    	if (tsHS == null) {
    		return null;
    	}
    	SealNumber number = new SealNumber();		
		number.setNumber(tsHS.getSealId());
		number.setLanguage(tsHS.getSealIdLng());
    	
		if (number.isEmpty()) {
			return null;
		} else {
			return number;
		}
    }
    private Packages getPackage(TsDP tsDP) {            //EI20110530
    	if (tsDP == null) {
    		return null;
    	}
    	Packages tempPa = new Packages(); 	        	
    	tempPa.setQuantity(tsDP.getPkgCount());					
    	tempPa.setType(tsDP.getPkgKind());
    	tempPa.setMarks(tsDP.getPkgMarks());
    	
		if (tempPa.isEmpty()) {
			return null;
		} else {
			return tempPa;
		}
    }
    private Text getText(TsAI tsAI) {            //EI20110530
    	if (tsAI == null) {
    		return null;
    	}
    	Text tempText = new Text(); 			  		         
		tempText.setCode(tsAI.getCode());
		tempText.setText(tsAI.getText());	   
		   
		if (tempText.isEmpty()) {
			return null;
		} else {
			return tempText;
		}
    }
    private PreviousDocument getPrevDoc(TsDV tsDV) {            //EI20110530
    	if (tsDV == null) {
    		return null;
    	}
    	PreviousDocument tempPrevDoc = new PreviousDocument(); 	  			
		tempPrevDoc.setType(tsDV.getPrevDocType());
		tempPrevDoc.setMarks(tsDV.getPrevDocRef());
		tempPrevDoc.setAdditionalInformation(tsDV.getPrevDocClass());
      
		//if (tempPrevDoc.isEmpty()) {
		//	return null;
		//} else {
			return tempPrevDoc;
		//}
    }
    private void processNA(TsNA tsNA) {            //EI20110530
    	if (tsNA == null) {
    		return;
    	}
		Address adr = new Address();
		TIN partyTin = new TIN();
		
		adr.setName(tsNA.getLongname());
		adr.setStreet(tsNA.getStreet());
		adr.setCity(tsNA.getCity());
		adr.setPostalCode(tsNA.getPostcode());
		adr.setCountry(tsNA.getCntry());
		
		//partyTin.setCustomerIdentifier(argument);
		//partyTin.setIsTINGermanApprovalNumber(argument):					
		partyTin.setTIN(tsNA.getTidEori());
		partyTin.setTIN(tsNA.getShortname());
		//TODO was ist richtig???
		
		if (tsNA.getType().equals("CZ")) {
			if (msgExpDat.getConsignor() == null) {
				Party party = new Party();
				msgExpDat.setConsignor(party);
			}
			msgExpDat.getConsignor().setAddress(adr);
			msgExpDat.getConsignor().getPartyTIN().setTIN(partyTin.getTIN());
		}
		if (tsNA.getType().equals("CN")) {
			if (msgExpDat.getConsignee() == null) {
				Party party = new Party();
				msgExpDat.setConsignor(party);
			}
			msgExpDat.getConsignee().setAddress(adr);
			msgExpDat.getConsignee().getPartyTIN().setTIN(partyTin.getTIN());
		}
		if (tsNA.getType().equals("DT")) {
			if (msgExpDat.getDeclarant() == null) {
				Party party = new Party();
				msgExpDat.setDeclarant(party);
			}
			msgExpDat.getDeclarant().setAddress(adr);	 
			msgExpDat.getDeclarant().getPartyTIN().setTIN(partyTin.getTIN());
		}
		if (tsNA.getType().equals("WH")) {
			if (msgExpDat.getWarehousekeeper() == null) {
				Party party = new Party();
				msgExpDat.setWarehousekeeper(party);
			}
			msgExpDat.getWarehousekeeper().setAddress(adr);
			msgExpDat.getWarehousekeeper().getPartyTIN().setTIN(partyTin.getTIN());
		}
    }
}  //ende der klasse
