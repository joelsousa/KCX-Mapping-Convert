package com.kewill.kcx.component.mapping.countries.de.cmp.cmp2kids;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.SpecialMention;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.FlightManifestMessage;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.FreightWayBill;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.MsgCmpCompleteData;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.ArrivalEvent;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.ConsignmentItemTypeFWB;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.ContactDetails;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.CustomsNote;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.CustomsProcedure;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.DepartureEvent;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.MasterConsignmentFFM;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.MasterConsignmentFWB;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.PartyType;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.TransportCargo;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.TransportCarriage;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.TransportEquipment;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.AddressNCTS;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Amount;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Container;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Guarantee;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Packages;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.PartyNCTS;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Reference;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.countries.de.ncts20.msg.MsgNCTSDeclaration;
import com.kewill.kcx.component.mapping.countries.de.ncts20.msg.MsgNCTSDeclarationPos;
import com.kewill.kcx.component.mapping.countries.de.ncts20.msg.common.ManifestCompletion;
import com.kewill.kcx.component.mapping.countries.de.ncts20.msg.common.ManifestCompletionItem;
import com.kewill.kcx.component.mapping.db.CustomerDTO;
import com.kewill.kcx.component.mapping.db.Db;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageManifest20;
import com.kewill.kcx.component.mapping.formats.kids.ncts20.BodyNCTSDeclarationKids;
import com.kewill.kcx.component.mapping.util.KcxMappingException;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: CMP<br>
 * Created		: 14.08.2013<br>
 * Description	: Mapping of CMP-Format into KIDS-Format of NCTSDeclaration message.
 * 				: EI201540211: beznr: Flugnr + DepartureDate (DDMMJJ) + POU
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class MapCMPtoNCTSDeclarationCK extends KidsMessageManifest20 {
	
	private BodyNCTSDeclarationKids body;
	private MsgNCTSDeclaration message;
	private MsgCmpCompleteData	cmp;	
	
	private String calculatedKcxId = "";  //EI20140116
	private String abbruchText = "";      //EI20140212
	private boolean testmode = false;     //EI20140321
	private ArrayList<String> awbList = null;  //EI20140508
	private boolean cummulate = true;          //EI20140508; eigentlich soll immer kumuliert werden, aber? wer weiss
	//private String custodianTinForNdl = "";  //EI20140527
	//private String custodianBoForNdl = "";   //EI20140527
	private String custodianKdnrForNdl = "";   //EI20140616
		
	public MapCMPtoNCTSDeclarationCK(XMLEventReader parser, String encoding) throws XMLStreamException {
		message = new MsgNCTSDeclaration(parser);	       
		this.encoding = encoding;
	}
	
	public MapCMPtoNCTSDeclarationCK(MsgCmpCompleteData cmpMessage, String encoding) throws XMLStreamException {
		cmp = cmpMessage;
		message = new MsgNCTSDeclaration();	       
		this.encoding = encoding;
	}
		
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
        	if (kidsHeader == null) {   //EI20140321
        		throw new KcxMappingException("KidsHeader is null");  //EI20140220        		
        	}
        	String cmpReceiver = kidsHeader.getReceiver().trim();  //EI20140321
            int len = cmpReceiver.length();
            String dummy = cmpReceiver.substring(len - 3, len);
            if (cmpReceiver.substring(len - 3, len).equals("TST")) {
             	testmode = true;
            } else {
             	testmode = false;
            }
            
            writer = factory.createXMLStreamWriter(xmlOutputString);
            body    = new BodyNCTSDeclarationKids(writer);
            kidsHeader.setWriter((writer));
            writeStartDocument(encoding, "1.0");
            openElement("soap:Envelope");
            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
                                             
             /* kidsHeader.setMessageName("NCTSDeclaration");   
             		//an der Stelle ist CountryCode noch DE (so wie sie von CMP kommt)
            if (kidsHeader.getCountryCode() != null && kidsHeader.getCountryCode().equals("AT")) {
            	//kidsHeader.setMethod("VEO");   //EI20131122 um auseinander halten zu koennen was als FSS was als KIDS
            	 							//an BOB-DE gehen soll, AT soll KIDS bekommen            	
            } 							                  
            kidsHeader.writeHeader();
            */
           
            if (!this.mapCmpToKids()) {            	
    			//throw new KcxMappingException("Missing mandatory Tags in CMP file ! mapping was stopped!");
            	throw new KcxMappingException(abbruchText);
            }
            
            kidsHeader.setMessageName("NCTSDeclaration");  
            
            kidsHeader.setReceiver(calculatedKcxId);
            this.getCommonFieldsDTO().setKcxId(calculatedKcxId); //EI29149123
           
            if (kidsHeader.getCountryCode() != null && kidsHeader.getCountryCode().equals("AT")) {
            	kidsHeader.setMethod("VE"); //EI20140117: soll nicht VEO sondern VE sein
            }           
            kidsHeader.writeHeader(); //EI20140116: erst in mapCmpToKids iwrd die richtige kcxId gesetzt
            
            body.setMessage(message); 
            body.setKidsHeader(kidsHeader);            
            body.writeBody();
            
            closeElement();  // soap:Envelope
            writer.writeEndDocument();
            
            writer.flush();
            writer.close();
            
            Utils.log("CmpToKids " + xmlOutputString.toString());
            
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
        
        return xmlOutputString.toString();
    }
	
	private boolean mapCmpToKids() {
		
		FlightManifestMessage ffm = cmp.getFlightManifestMessage();
		ArrayList<FreightWayBill> fwbList = cmp.getFreightWayBillList();		
		String referenceNumber = ""; 
		String flightNumber = "";  	        //EI20140115		
		String ddmmjjD = "";			    //EI20140211
		String arrivalLocationCode = "";	//EI20140115	
		
		if (ffm == null) {
			abbruchText = "CmpToKids NCTS:  FlightManifestMessage is missing";
			return false;	
		}
		if (fwbList == null) {
			abbruchText = "CmpToKids NCTS:FreightWayBillList is missing";
			return false;	
		}
		if (ffm.getBusinessHeaderDocument() == null) {
			abbruchText = "CmpToKids NCTS:BusinessHeaderDocument is missing";
			return false;	
		}
		if (ffm.getLogisticsTransportMovement() == null) {
			abbruchText = "CmpToKids NCTS: LogisticsTransportMovement is missing";
			return false;	
		}
			
		flightNumber = ffm.getLogisticsTransportMovement().getId();   //EI20140115
		
		/*EI20140115: 
		referenceNumber = ffm.getBusinessHeaderDocument().getId();		
		message.setReferenceNumber(referenceNumber); 
		if (Utils.isStringEmpty(referenceNumber)) {
			Utils.log("BusinessHeaderDocument.ID == ReferenceNumber is missing");
			return false;
		}
		*/
		/*EI20140124: verschoben unterhalb calculatedkcxid, weil für AT andere Werte
		message.setControlResultDateLimit(this.getToday());  	//van.wgdat	
		TransportMeans transport = new TransportMeans();
		transport.setTransportMode("30");    //van.bfvkzg
		transport.setTransportationType("33");	//van.bfgrcd			
		message.setMeansOfTransportBorder(transport);
		*/
		ContactPerson contact = new ContactPerson();
		contact.setIdentity("FSS");
		message.setClerk(contact);				
				
		CustomsProcedure cupro = ffm.getLogisticsTransportMovement().getRelatedConsignmentCustomsProcedure();
		if (cupro != null && cupro.getGoodsStatusCode() != null) {					
			message.setTypeOfDeclaration(cupro.getGoodsStatusCode());	//van.anmart		
		}
		DepartureEvent depev = ffm.getLogisticsTransportMovement().getDepartureEvent();	
		String departureCode = "";
		if (depev != null) {
			String dateTime = depev.getDepartureOccurrenceDateTime();
			//EI20140606: ddmmjjD = this.calculateDate(dateTime, "MMDDYY");	
			ddmmjjD = this.calculateDate(dateTime, "DDMMYY");	//EI20140606
			if (depev.getOccurrenceDepartureLocation() != null) {
				departureCode = depev.getOccurrenceDepartureLocation().getId();
				message.setDispatchCountry(Utils.getLandFromAirport(departureCode)); //van.ldvs				
			}
		}
		if (Utils.isStringEmpty(departureCode)) {
			abbruchText = "CmpToKids NCTS: KcxId could not be calculated because of DepartureLocationCode is null";
			return false;
		}
		calculatedKcxId = this.calculateKcxId(kidsHeader, departureCode, testmode);   //EI20140116
		Utils.log("(CmpToKidsNCTS20 mapFFM) calculated KcxId with DepartureLocationCode (" + departureCode + ") = " + calculatedKcxId); 
		if (calculatedKcxId.length() > 7 && calculatedKcxId.substring(2, 3).equals(".")) { //bsp: DE.LHC.DUS
			String country = calculatedKcxId.substring(0, 2);
			//custodianTinForNdl = "";
			//custodianBoForNdl = "";
			custodianKdnrForNdl = "";
			CustomerDTO customerDTO = Db.getCustomerFromKidsId(calculatedKcxId, "KIDS", country);	
			if (customerDTO != null && customerDTO.getBranchName() != null) {
				//String branch = customerDTO.getBranchName().trim();		
				//if (branch.length() > 8 &&  Utils.isNumeric(branch.substring(0, 4))) {
				//	custodianBoForNdl = branch.substring(0, 4);
				//	custodianTinForNdl = branch.substring(5).trim();
				//}
				custodianKdnrForNdl = customerDTO.getBranchName().trim();
			}			
		}
		//EI20140124: verschoben hierher:
		if (calculatedKcxId != null && calculatedKcxId.startsWith("AT")) {
			//EI20140428: message.setControlResultDateLimit(this.getToday());  	
			TransportMeans transport = new TransportMeans();
			transport.setTransportMode("3");    
			transport.setTransportationType("14");				
			message.setMeansOfTransportBorder(transport);
		} else {
			//EI20140428: message.setControlResultDateLimit(this.getToday());  	//van.wgdat	
			TransportMeans transport = new TransportMeans();
			transport.setTransportMode("30");    //van.bfvkzg
			transport.setTransportationType("33");	//van.bfgrcd			
			message.setMeansOfTransportBorder(transport);
		}
		if (ffm.getArrivalEventList() != null &&  ffm.getArrivalEventList().get(0) != null) { 
			ArrivalEvent arev = ffm.getArrivalEventList().get(0);
			
			if (arev.getOccurrenceArrivalLocation() != null) {	
				arrivalLocationCode = arev.getOccurrenceArrivalLocation().getId();						
			}			
			String destland = Utils.getLandFromAirport(arrivalLocationCode);
					
			if (Utils.isStringEmpty(arrivalLocationCode) && 
					arev.getAssociatedTransportCargoList() != null && arev.getAssociatedTransportCargoList().get(0) != null &&
					arev.getAssociatedTransportCargoList().get(0).getUtilizedUnitLoadTransportEquipment() != null) {
				
				TransportCarriage octm = arev.getAssociatedTransportCargoList().get(0).getUtilizedUnitLoadTransportEquipment().
																					   getOnCarriageTransportMovement();
				if (octm != null && octm.getArrivalDestinationEvent() != null &&	
					octm.getArrivalDestinationEvent().getOccurrenceDestinationLocation() != null) {	
					//arrivalLocationCode = octm.getArrivalDestinationEvent().getOccurrenceDestinationLocation().getId(); //EI20140127
					String arrivalDestinationLocation = octm.getArrivalDestinationEvent().getOccurrenceDestinationLocation().getId(); //EI20140213
					destland = Utils.getLandFromAirport(arrivalDestinationLocation);
				}			
			}			
			//TODO: aus welchem Tag soll eigentlich destland gefuelt sein? in Excel steht sogar: aus Consignee?!
			message.setDestinationCountry(destland); //van.ldbe			
		}		
		
		//Addressen TsVAK werden nicht gemapped = keine CMP-Tags vorhanden in FFM,
		//Addressen werden auf GoodsItem-Ebene gemapped, und wenn alle gleich werden dann im Kopf geschrieben und aus Items gelöscht
		
		
		//////// GoodsItems == TsVPO ///////////////
		
		if (ffm.getArrivalEventList() != null) {
			int ae = 0;
			int imc = 0;		
			for (ArrivalEvent arrivalEvent : ffm.getArrivalEventList()) {	
				ae = ae + 1;
				if (arrivalEvent.getAssociatedTransportCargoList() == null) {  //EI20140210
					continue;
				}
				for (TransportCargo transportCargo : arrivalEvent.getAssociatedTransportCargoList()) {
					if (transportCargo == null) {
						continue;
					}	
					String containerNumber = "";
					if (transportCargo.getUtilizedUnitLoadTransportEquipment() != null) {
						containerNumber = transportCargo.getUtilizedUnitLoadTransportEquipment().getId();
					}
					for (MasterConsignmentFFM includedMasterConsignmentFFM : transportCargo.getIncludedMasterConsignmentList()) {					
						if (includedMasterConsignmentFFM != null) {
							imc = imc + 1;	
							message.addGoodsItemList(this.mapGoodsItem(imc, includedMasterConsignmentFFM, containerNumber));	
						}
					}									
				}
				/*break;  //im aktuellen LCAG-Bsp nur ein ArrivalEvent
				if (ae > 1) {
				Utils.log("CmpToKidsNCTS: more then one ArrivalEvent can not be prozessed");  //EI20140210			
				return false;
				}
				*/
			}
			this.checkAndSetParties();   //EI20140203
		}
		
		String typeCalculate = this.calculateDeclarationTypeFromItems();
		if (Utils.isStringEmpty(message.getTypeOfDeclaration())) {
			message.setTypeOfDeclaration(typeCalculate);			
		} else {
			if (!Utils.isStringEmpty(typeCalculate) && !message.getTypeOfDeclaration().equals(typeCalculate)) {
				Utils.log("CmpToKidsNCTS: TypeOfDeclaration: " + message.getTypeOfDeclaration() + " overwritten with calculated from GoodsItems: " + typeCalculate);
				message.setTypeOfDeclaration(typeCalculate);
			} 
		}
		
		//EI20140115:
		//NCTS und Reexport: beznr = flugnr + ddmmjjD + POU
		if (Utils.isStringEmpty(flightNumber) || Utils.isStringEmpty(ddmmjjD) || Utils.isStringEmpty(arrivalLocationCode)) {			
			Utils.log("CmpToKids NCTS: flightNumber/ddmmjj/arrivalLocation: " + flightNumber + "/" + ddmmjjD + "/" + arrivalLocationCode);
			abbruchText = "CmpToKids NCTS: ReferenceNumber could not be calculated";
			return false;
		}							
		referenceNumber = this.calculateReferenceNumber(flightNumber, ddmmjjD, arrivalLocationCode); 
		message.setReferenceNumber(referenceNumber);	
		Utils.log("CmpToKids NCTS: ReferenceNumber: " + referenceNumber);
		
		//EI20140508 - JIRA KCX-263: AWB information must be declared in one NCTS-OUT item line per AWB. 
		//In case of "Split Shipment" the Number of pieces and weight always has to be summarized on AWB level by Kewill. 
		//If the goods description will be different for one AWB transmitted in one FFM on multiple lines, the converter will set it to "Consolidation".
		
		cummulate = false; //EI20140617: rollback: kein Kumulieren, Items ausgeben so wie sie kommen
		if (cummulate) {  //Jira KCX263					
			this.cumulateSplitedAWBs();		
		}
		return true;
	}
		
	private MsgNCTSDeclarationPos mapGoodsItem(int itemNr, MasterConsignmentFFM includedMasterConsignmentFFM, String containerNumber) {
		if (includedMasterConsignmentFFM == null) {			
			return null;
		}			
		MsgNCTSDeclarationPos item = new MsgNCTSDeclarationPos();		
		String awbReference = "";
		String awb = "";
		
		item.setItemNumber("" + itemNr);			
		item.setTypeOfDeclaration(includedMasterConsignmentFFM.getAssociatedConsignmentCustomsProcedure().
								getGoodsStatusCode());		
		item.setGrossMass(includedMasterConsignmentFFM.getGrossWeightMeasure());	
		
		if (includedMasterConsignmentFFM.getOriginLocation() != null) {
			String land = Utils.getLandFromAirport(includedMasterConsignmentFFM.getOriginLocation().getId());
			item.setDispatchCountry(land);  //sup.setVland 			
		}
		if (includedMasterConsignmentFFM.getFinalDestinationLocation() != null) {
			String land = Utils.getLandFromAirport(includedMasterConsignmentFFM.getFinalDestinationLocation().getId()); 
			item.setDestinationCountry(land); //sup.setBesort
		}
				
		if (includedMasterConsignmentFFM.getTransportContractDocument() != null) { 
			awbReference = includedMasterConsignmentFFM.getTransportContractDocument().getId();
			if (!Utils.isStringEmpty(awbReference)) {				
				awb = awbReference.replace("-", "");									
			}	
		}
		
		if (awbList == null) {  //EI20140508
			awbList = new ArrayList<String>();
		}
		awbList.add(awb);        //EI20140508	ein AWB pro GoodsItem (AWB ist eben GoodsItem)
		item.setAwbForCMP(awb);  //EI20140508	
		
		Packages packNCTS = new Packages();
		packNCTS.setQuantity(includedMasterConsignmentFFM.getPackageQuantity());
		packNCTS.setType("PK");
		packNCTS.setMarks(awb);		
		item.addPackagesList(packNCTS);	
		
		item.setIdentificationCode("AWB");		
		ManifestCompletion mc = new ManifestCompletion();
		ManifestCompletionItem mci = new ManifestCompletionItem();					
			mc.setIdentification("AWB");
			mc.setReference(awb);		
			mci.setNumberOfPieces(includedMasterConsignmentFFM.getPackageQuantity());
			mci.setSpoArt("AWB");
			mci.setSpo(awb);
			//EI20131202: mci.setAtlasAlignment("1");	
			//EI20140527-beginn:
			//if (!Utils.isStringEmpty(custodianTinForNdl)) { //nur fuer die kcx_ids, die NCTS schicken
			if (!Utils.isStringEmpty(custodianKdnrForNdl)) { //nur fuer die kcx_ids, die NCTS schicken		
				TIN tin = new TIN();
				//tin.setTin(custodianTinForNdl);
				//tin.setBO(custodianBoForNdl);
				//tin.setIdentificationType("2");
				tin.setCustomerIdentifier(custodianKdnrForNdl);
				mci.setCustodianTIN(tin);
			}
			//EI20140527-end
		mc.addCompletionItemList(mci);		
		item.addManifestCompletionList(mc);    		//item.setWriteOffInfo() == TsBSU
		
		PreviousDocument prev = new PreviousDocument();
		if (calculatedKcxId != null && calculatedKcxId.startsWith("AT")) {
			prev.setType("ZZZ");
		} else {
			prev.setType("ATNEU");
		}
		item.addPreviousDocumentList(prev);
		
		//TsVCN
		if (!Utils.isStringEmpty(containerNumber)) {
			Container container = new Container();
			container.addNumberList(containerNumber);
			item.setContainer(container);
		}
		
		//TsVAG
		Guarantee guarantee = new Guarantee();
		Reference reference = new Reference();		
		Amount amount = new Amount();
		amount.setLocalAmount("7000");
		reference.setAmount(amount);
		guarantee.addReferenceList(reference);
		
		/*EI20140513: doch, aus FWB!
		String description = "";	
		description = includedMasterConsignmentFFM.getSummaryDescription();
		item.setDescription(description.trim());
		//oder: aus einem entsprechenden FWB?  
		*/
		
		MasterConsignmentFWB fwbMC = this.getReferencedMasterConsignmentFWB(awbReference, cmp.getFreightWayBillList());		
		if (fwbMC != null) {
			/*EI20140513:
			if (fwbMC.getApplicableRatingList() != null && fwbMC.getApplicableRatingList().get(0) != null) {
				ArrayList<ConsignmentItemTypeFWB> list = fwbMC.getApplicableRatingList().get(0).getIncludedMasterConsignmentItemList();			
				if (list != null && list.get(0) != null) {				
					if (list.get(0).getNatureIdentificationTransportCargo() != null) {					
						description = list.get(0).getNatureIdentificationTransportCargo().getIdentification();					
						if (!Utils.isStringEmpty(description)) {
							item.setDescription(description.trim());
						}							
					}			
				}
			} */
			String description = getReferencedFWBCumulatedDescription(awbReference, cmp.getFreightWayBillList()); //EI20140513
			item.setDescription(description.trim());
						
			// ConsignorTIN/Tin <== es gibt keine TIN in Items!
			// ConsigneeTIN/Tin <== es gibt keine TIN in Items!
				
			// Addressen:
			item.setConsignor(this.getNCTSParty(fwbMC.getConsignorParty()));
			item.setConsignee(this.getNCTSParty(fwbMC.getConsigneeParty()));
						
			if (fwbMC.getIncludedCustomsNoteList() != null) {
				for (CustomsNote note :  fwbMC.getIncludedCustomsNoteList()) {
					if (note != null && note.getContent() != null) {
						String content = note.getContent();
						//es gab keinen entspr. KIDS-Tag ich habe in SpecialMention "BansAndLimitations" definiert
						if (content != null && content.startsWith("VUB-")) {							
							content = content.substring(4);	//also alles ab 4-ten position	
							SpecialMention sp = new SpecialMention();
							sp.setBansAndLimitations("1");
							sp.setText(content);
							item.addSpecialMentionList(sp);
						}
					}
				}		
			}  
			/*  SpecialMention belegt wohl Zabis !?			
			else {
				SpecialMention sp = new SpecialMention();
				sp.setBansAndLimitations("0");
				item.addSpecialMentionList(sp);
			} */			
		}
		
		//Rest ist NA			
		return item;
	}
	/// ende item
	
	private PartyNCTS getNCTSParty(PartyType partyFWB) {
		if (partyFWB == null) {
			return null;
		}
		PartyNCTS partyNCTS = new PartyNCTS();
		//partyNCTS.setPrimaryId(partyFWB.getPrimaryId());        //EI20140203, doch nicht
		//partyNCTS.setAdditionalId(partyFWB.getAdditionalId());  //EI20140203, doch nicht
		AddressNCTS adressNCTS = new AddressNCTS();
		adressNCTS.setName(partyFWB.getName());
		if (partyFWB.getAddress() != null) {
			adressNCTS.setStreet(partyFWB.getAddress().getStreetName());
			adressNCTS.setCity(partyFWB.getAddress().getCityName());
			adressNCTS.setPostalCode(partyFWB.getAddress().getPostcodeCode());
			adressNCTS.setCountry(partyFWB.getAddress().getCountryId());
		}
		partyNCTS.setAddress(adressNCTS);
		
		if (partyFWB.getDefinedTradeContactList() != null) {
			for (ContactDetails contactFWB : partyFWB.getDefinedTradeContactList()) {
				if (contactFWB != null) {
					ContactPerson contactNCTS = new ContactPerson();	
					contactNCTS.setName(contactFWB.getPersonName());
					contactNCTS.setPosition(contactFWB.getDepartmentName());
					if (contactFWB.getDirectTelephoneCommunication() != null) {
					contactNCTS.setPhoneNumber(contactFWB.getDirectTelephoneCommunication().getCompleteNumber());
					}
					if (contactFWB.getFaxCommunication() != null) {
					contactNCTS.setFaxNumber(contactFWB.getFaxCommunication().getCompleteNumber());
					}
					if (contactFWB.getUriEmailCommunication() != null) {
					contactNCTS.setEmail(contactFWB.getUriEmailCommunication().getCompleteNumber());
					}
					partyNCTS.addPartyContactList(contactNCTS);
				}
			}
		}
		
		return partyNCTS;
	}
	
	private MasterConsignmentFWB getReferencedMasterConsignmentFWB(String awb, ArrayList<FreightWayBill> fwbList) {		
		MasterConsignmentFWB mcFWB = null;
		if (fwbList == null) {
			return null;
		}
		for (FreightWayBill item : fwbList) {
			if (item.getBusinessHeaderDocument() != null) {
				if (item.getBusinessHeaderDocument().getId() != null &&
						item.getBusinessHeaderDocument().getId().equalsIgnoreCase(awb)) {
					mcFWB = item.getMasterConsignment();
					break;
				}
			}
		}		
		return mcFWB;
	}	
	//EI20140513: weil man nicht eindeutig die (mehreren) abws in FFM und Waybill zuordnen kann wird description für alle awbs
	// gleich sein: entweder Nature...(wenn für alle awbs gleich) oder "Consolidation" wenn unterschiedliche Nature... 
	private String getReferencedFWBCumulatedDescription(String awb, ArrayList<FreightWayBill> fwbList) {		
		MasterConsignmentFWB fwbMC = null;
		if (fwbList == null) {
			return null;
		}
		String description = "";
		for (FreightWayBill item : fwbList) {
			if (item.getBusinessHeaderDocument() != null) {
				if (item.getBusinessHeaderDocument().getId() != null &&
						item.getBusinessHeaderDocument().getId().equalsIgnoreCase(awb)) {
					fwbMC = item.getMasterConsignment();
					String fwbDescription = this.getDescription(fwbMC);
					if (description.equals("")) {
						description = fwbDescription;
					}
					if (!description.equals(fwbDescription)) {
						description = "Consolidation";
						break;
					}				
				}
			}
		}		
		return description;
		
	}
	private String getDescription(MasterConsignmentFWB mc) {
		String description = "";
		
		if (mc.getApplicableRatingList() != null && mc.getApplicableRatingList().get(0) != null) {
			ArrayList<ConsignmentItemTypeFWB> list = mc.getApplicableRatingList().get(0).getIncludedMasterConsignmentItemList();			
			if (list != null && list.get(0) != null) {				
				if (list.get(0).getNatureIdentificationTransportCargo() != null) {					
					description = list.get(0).getNatureIdentificationTransportCargo().getIdentification();					
					if (description != null) {
						description = description.trim();
					}							
				}			
			}
		} 
		return description;
	}
	
	private String calculateDeclarationTypeFromItems() {
		String type = "";
		boolean t1 = false;
		boolean t2 = false;
		boolean t3 = false;
		if (message.getGoodsItemList() == null) {
			return "";
		}
		if (message.getGoodsItemList().size() == 0) {
			return "";
		}					
		for (MsgNCTSDeclarationPos item : message.getGoodsItemList()) {			
			if (item.getTypeOfDeclaration() != null) {
				if (item.getTypeOfDeclaration().equals("T1") || 
					item.getTypeOfDeclaration().equals("TD")) {
					t1 = true;					
					if (item.getTypeOfDeclaration().equals("TD")) {
						Utils.log("CmpToKidsMCTS: GoodsItem.TypeOfDeclaration TD mapped to T1 ");
					}
					item.setTypeOfDeclaration("T1");
				} else if (item.getTypeOfDeclaration().equals("T2") || 
					item.getTypeOfDeclaration().equals("C") ||
					item.getTypeOfDeclaration().equals("TF") || 
					item.getTypeOfDeclaration().equals("X")) {	
					t2 = true;
					if (!item.getTypeOfDeclaration().equals("T2")) {
						Utils.log("CmpToKidsMCTS: GoodsItem.TypeOfDeclaration " + item.getTypeOfDeclaration() + " mapped to T2 ");
					}
					item.setTypeOfDeclaration("T2");					
				} else {
					t3 = true;
					Utils.log("CmpToKidsMCTS: GoodsItem: unknown TypeOfDeclaration: " + item.getTypeOfDeclaration());
				}
			}					
		}
	
		//in Zabis erlaubt nur T1, T2, T-, T2F, TIR 
		//wenn in allen items identisch darf type nur im Kopf stehen 
		
		for (MsgNCTSDeclarationPos item : message.getGoodsItemList()) {			
			if (t1 && !t2 && !t3) {
			   item.setTypeOfDeclaration("");				
			   type = "T1";			   
			} else if (!t1 && t2 && !t3) { 						
				item.setTypeOfDeclaration("");
				type = "T2";				
			} else if (t1 && t2 && !t3) {					
				type = "T-";				
			} else {
				type = "";
			}
		}
			
		if (t1 && !t2 && !t3) {			  
			Utils.log("CmpToKidsMCTS: TypeOfDeclaration mapped to T1 because in all GoodsItems is T1");
		} else if (!t1 && t2 && !t3) { 										
			Utils.log("CmpToKidsMCTS: TypeOfDeclaration mapped to T2 because in all GoodsItems is T2");
		} else if (t1 && t2 && !t3) {					
			Utils.log("CmpToKidsMCTS: TypeOfDeclaration mapped to T- because in GoodsItems is T1 and T2");
		} 
			
		return type;
	}
	
	public String getCalculatedKcxId() {   //EI20140116
		return calculatedKcxId;
	}
	
	private void checkAndSetParties() {
		if (this.message == null) {
			return;
		}
		
		if (this.message.getGoodsItemList() == null) {
			return;
		}
		if (this.message.getGoodsItemList().size() == 0) {
			return;
		}
		int countConsignee = 0;
		int countConsignor = 0;
		boolean consigneeEquals = true;
		boolean consignorEquals = true;
		PartyNCTS consigneeFirst = null;
		PartyNCTS consignorFirst = null;
		for (MsgNCTSDeclarationPos item : this.message.getGoodsItemList()) {			
			if (item.getConsignee() != null && item.getConsignee().getAddress() != null) {
				if (countConsignee == 0) {
					consigneeFirst = item.getConsignee();
					countConsignee = 1;					
				} else {
					countConsignee = countConsignee + 1;
					if (consigneeEquals) {
						//consigneeEquals = this.comparePartyId(consigneeFirst, item.getConsignee());
						consigneeEquals = this.compareParties(consigneeFirst, item.getConsignee());	
					}
				}
			}	
			if (item.getConsignor() != null && item.getConsignor().getAddress() != null) {
				if (countConsignor == 0) {
					consignorFirst = item.getConsignor();
					countConsignor = 1;					
				} else {
					countConsignor = countConsignor + 1;
					if (consignorEquals) {
						consignorEquals = this.compareParties(consignorFirst, item.getConsignor());					
					}
				}
			}	
		}
		boolean deleteConsigneeInAllItems = false;
		boolean deleteConsignorInAllItems = false;
		if (countConsignee == 1 || (countConsignee > 1 && consigneeEquals)) {			
			this.message.setConsignee(consigneeFirst);
			deleteConsigneeInAllItems = true;
		}
		if (countConsignor == 1 || (countConsignor > 1 && consignorEquals)) {			
			this.message.setConsignor(consignorFirst);
			deleteConsignorInAllItems = true;
		}
		for (MsgNCTSDeclarationPos item : this.message.getGoodsItemList()) {	
			if (item != null) {
				if (deleteConsigneeInAllItems) {
					item.setConsignee(null);
				}
				if (deleteConsignorInAllItems) {
					item.setConsignor(null);
				}
			}
		}
	}
	/*  doch nicht
	private boolean comparePartyId(PartyNCTS party1, PartyNCTS party2) {
		boolean areEquals;
		
		if (party1 == null && party2 == null) {
			return true;
		} else if (party1 == null || party2 == null) {
			return false;
		}
		
		areEquals = false;
		if (party1 != null && party2 != null) { 			
			String p1 = party1.getPrimaryId();	
			String p2 = party2.getPrimaryId();	
			String a1 = party1.getAdditionalId();
			String a2 = party2.getAdditionalId();
			
			if (twoStringsAreEquals(p1, p2)) {	
				areEquals = twoStringsAreEquals(a1, a2);							
			} else {								
				areEquals = false;			
			}
		}		
		return areEquals;
	}
	*/
	private boolean compareParties(PartyNCTS party1, PartyNCTS party2) {
		boolean areEquals;
		
		if (party1 == null && party2 == null) {
			return true;
		} else if (party1 == null || party2 == null) {
			return false;
		}
		areEquals = false;	
		if (party1 != null && party2 != null) { 
			AddressNCTS a1 = party1.getAddress();
			AddressNCTS a2 = party2.getAddress();
			if (a1 == null && a1 == null) {
				return true;
			} else if (a1 == null || a2 == null) {
				return false;
			}
			if (a1 != null && a2 != null) { 
				String n1 = a1.getName();	
				String n2 = a2.getName();								
				if (!twoStringsAreEquals(n1, n2)) {	
					return false;												
				} 
					
				String p1 = a1.getPostalCode();
				String p2 = a2.getPostalCode();	
				if (!twoStringsAreEquals(p1, p2)) {	
					return false;												
				} 
	
				String c1 = a1.getCity();
				String c2 = a2.getCity();
				if (!twoStringsAreEquals(c1, c2)) {	
					return false;												
				} 	
				
				String s1 = a1.getStreet();
				String s2 = a2.getStreet();	
				if (!twoStringsAreEquals(s1, s2)) {	
					return false;											
				} 
				
				String l1 = a1.getCountry();
				String l2 = a2.getCountry();
				if (!twoStringsAreEquals(l1, l2)) {	
					return false;											
				} 
				
				String su1 = a1.getCountrySubEntity();
				String su2 = a2.getCountrySubEntity();	
				
				if (twoStringsAreEquals(su1, su2)) {
					return true;	
				} else {
					return false;	
				}
			}		
		}
		
		return areEquals;
	}
	
	private boolean twoStringsAreEquals(String a1, String a2) {
		boolean areEquals = false;
		if (a1 == null && a2 == null) {
			return true;
		} else if (a1 == null || a2 == null) {
			return false;
		}
				
		if (a1 != null && a2 != null) {
			if (a1 != null) {
				a1 = a1.trim();
			}
			if (a2 != null) {
				a2 = a2.trim();
			}
			areEquals = a1.equals(a2);
		} 
		return areEquals;
	}
		
	private void iwaCountForFssoutProjectTNT70() {
		int lineNumber = 50;
		int listSize = 30;
		int tntCount = 0;
		String [] outFields = null;
		for (int i = 0; i < listSize; i++) {							
			if (i == 4 * tntCount) {
					
					outFields = new String[7];
					outFields[0] = "FirstLine: " + lineNumber;
					outFields[3] = "1.Container: " + 1;
					outFields[4] = "           ";
					outFields[5] = "           ";
					outFields[6] = "           ";						
			} else if (i == 4 * tntCount + 1) {
					outFields[4] = "2.Container: " + 1;
			} else if (i == 4 * tntCount + 2) {
					outFields[5] = "3.Container: " + 1;
			} else if (i == 4 * tntCount + 3) {
					outFields[6] = "4.Container: " + 1;
			}
			
			if (i == 4 * (tntCount + 1) - 1 || i == listSize - 1) {
					tntCount = tntCount + 1;
					//TntTsEDT tnttsedt = new TntTsEDT();
					//String line = tnttsedt.createLine(outFields);
					//out.write(line);
					lineNumber++;
					Utils.log("iwa: " + tntCount + "/" + lineNumber);						
			}
		}		
	}
	
	
	private void cumulateSplitedAWBs() {
		ArrayList<MsgNCTSDeclarationPos> newGoodsItemList = new ArrayList<MsgNCTSDeclarationPos>();
		ArrayList<String> duobleAwbList = new ArrayList<String>();
		boolean found = false;
		
		if (this.message == null) {
			return;
		}
		if (this.message.getGoodsItemList() == null) {
			return;
		}
		if (this.message.getGoodsItemList().size() == 0 || this.message.getGoodsItemList().size() == 1) {
			return;
		}		
		if (awbList == null) {
			return;
		}
		
		// zuerst eine duobleAwbList erstellen in der die mehrfach vorkommende AWB aufgenommen werden 
		int count = 0;
		found = false;
		for (String awb1 : awbList) {	
			count = 0;
			for (String awb2 : awbList) {
				if (awb2.equals(awb1)) {
					count = count + 1;
				}
			}
			if (count > 1) {			
				for (String awb3 : duobleAwbList) {
					if (awb3.equals(awb1)) {
						found = true;
					}
				}
				if (!found) {
					duobleAwbList.add(awb1);
				}
			}			
		}
		
		//dann alle nur eimal vorhandene GoodsItems in die newGoodsItemList kopieren: 
		found = false;
		for (MsgNCTSDeclarationPos item : message.getGoodsItemList()) {
			found = false;
			if (item != null) {				
				for (String awb : duobleAwbList) {
					if (awb.equalsIgnoreCase(item.getAwbForCMP())) {
						found = true;
						break;
					}
				}
				if (!found) {					
					newGoodsItemList.add(item);					
				}
			}
		}
		
		//dann die mehrfache AWB kummulieren
		//und nur einen==kummulierten GoodsItem in die newGoodsItemList kopieren
		for (String duobleAwb : duobleAwbList) {
			int anzahl = 0;
			double grossMass = 0.0;
			String firstItem = "";			
			String description = "";			
				
			//kummulieren
			for (MsgNCTSDeclarationPos item : message.getGoodsItemList()) {				
					//if(item.getPackagesList() != null && item.getPackagesList().get(0) != null &&				
					//duobleAwb.equals(item.getPackagesList().get(0).getMarks())) {	  <== in Marks steht AWB				
				if (item != null && duobleAwb.equalsIgnoreCase(item.getAwbForCMP())) {						
					if (firstItem.equals("")) {
						firstItem = item.getItemNumber(); 
					}
					int anz = Integer.parseInt(item.getPackagesList().get(0).getQuantity());
					double mass = Double.parseDouble(item.getGrossMass());
					anzahl = anzahl + anz;
					grossMass = grossMass + mass;	
					//jetzt in allen awbs steht die gleiche Description
					description = item.getDescription();													
				}			
			}
			
			//dann in dem ersten mehrfachen Item die eben errechneten Werte einfuegen,
			//und nur dieses Item in die newGoodsItemList einfügen 
			for (MsgNCTSDeclarationPos item : message.getGoodsItemList()) {
				if (item != null && item.getItemNumber().equals(firstItem)) {
					item.setDescription(description);
					item.setGrossMass("" + grossMass);
					if (item.getPackagesList() == null) {
						ArrayList<Packages> pList = new ArrayList<Packages>();
						item.setPackagesList(pList);
					}
					item.getPackagesList().get(0).setQuantity("" + anzahl);
					//int idx = Integer.parseInt(item.getItemNumber());
					newGoodsItemList.add(item);					
				}				
			}
		}	
		//und noch zum schluss neue durchnummerierung der übriggebliebener Items
		int idx = 1;
		for (MsgNCTSDeclarationPos item : newGoodsItemList) {
			if (item != null) {
				item.setItemNumber("" + idx);
				idx = idx + 1;
			}
		}
		message.setGoodsItemList(newGoodsItemList);
	}
}
