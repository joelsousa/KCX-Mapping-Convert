package com.kewill.kcx.component.mapping.countries.de.Port.kids2fss;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.Port.msg.MsgBillOfLading;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.AllocatedEquipment;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.AttachedEquipment;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.Carriage;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.CarriageDetails;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.CommunicationContact;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.Contact;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.Currency;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.CurrencyDetails;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.DangerousGoods;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.DangerousGoodsDetails;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.EquipmentDetails;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.EquipmentQualifier;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.FreightAndCharge;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.GoodsItem;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.ItemDetails;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.ItemText;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.MeansOfTransport;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.MonetaryAmount;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.PackingLevel2;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.PackingLevel3;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.PartyDetails;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.PaymentInstructions;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.Place;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.Reference;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.ReferenceDetails;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.Seals;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.TextBL;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.TextOnEntireBL;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.TransportTerm;
import com.kewill.kcx.component.mapping.formats.fss.Port.messages.BL.MsgBL;
import com.kewill.kcx.component.mapping.formats.fss.Port.messages.BL.common.CarriageDetailsFSS;
import com.kewill.kcx.component.mapping.formats.fss.Port.messages.BL.common.CarriageFSS;
import com.kewill.kcx.component.mapping.formats.fss.Port.messages.BL.common.ContactFSS;
import com.kewill.kcx.component.mapping.formats.fss.Port.messages.BL.common.CurrencyFSS;
import com.kewill.kcx.component.mapping.formats.fss.Port.messages.BL.common.DangerousGoodsFSS;
import com.kewill.kcx.component.mapping.formats.fss.Port.messages.BL.common.EquipmentFSS;
import com.kewill.kcx.component.mapping.formats.fss.Port.messages.BL.common.FreightAndChargeFSS;
import com.kewill.kcx.component.mapping.formats.fss.Port.messages.BL.common.GoodsItemFSS;
import com.kewill.kcx.component.mapping.formats.fss.Port.messages.BL.common.PackingDetailsFSS;
import com.kewill.kcx.component.mapping.formats.fss.Port.messages.BL.common.PackingFirstLevelFSS;
import com.kewill.kcx.component.mapping.formats.fss.Port.messages.BL.common.PackingSecondLevelFSS;
import com.kewill.kcx.component.mapping.formats.fss.Port.messages.BL.common.PartyFSS;
import com.kewill.kcx.component.mapping.formats.fss.Port.messages.BL.common.PaymentInstructionsFSS;
import com.kewill.kcx.component.mapping.formats.fss.Port.messages.BL.common.TermsOfTransportFSS;
import com.kewill.kcx.component.mapping.formats.fss.Port.messages.BL.common.TextOnEntireBLFSS;
import com.kewill.kcx.component.mapping.formats.fss.Port.messages.BL.common.TextOnItemFSS;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsAddressQualified;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsAddressRows;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsAllocatedEquipment;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsAttachedEquipment;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsBillOfLading;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsCarriage;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsCommunicationContact;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsCurrencyDetails;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsDangerousGoodsDetails;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsDetailsOnDocument;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsEquipmentDetails;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsFiveElements;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsFreightAndCharge;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsGoodsItemDetails;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsMarksAndNumber;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsMeansOfTransport;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsMonetaryAmount;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsOneElement;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsPackingLevel;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsParty;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsPaymentInstructions;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsPlace;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsReference;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsSeals;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsTenElements;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsTermsOfTransport;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsTextOnBL;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsTotalsOnEntireBL;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessagePortBL;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;


/**
 * Module       : Port<br>
 * Created      : 21.10.2011<br>
 * Description	: Mapping of KIDS PortDeclaration to FSS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MapBillOfLadingToBL extends KidsMessagePortBL {
	
	private MsgBL msgBL;	
	private MsgBillOfLading message;
	private String beznr = "";
	private int version	= 1;
	private String blLocalId = "";
	
	public MapBillOfLadingToBL(XMLEventReader parser, TsVOR tsvor)  throws XMLStreamException {
		msgBL = new MsgBL();	
		message = new MsgBillOfLading(parser);		
		msgBL.setVorSubset(tsvor);
	}
	
	public String getMessage() {
		String res = ""; 
	
		try {  
        	message.parse(HeaderType.KIDS);         	
            getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
            beznr = message.getReferenceNumber();
            blLocalId = getBlLocalId(this.kidsHeader.getReceiver());
            this.replaceVorWithHead();  //EI20120511
                      
            //Setting all Ts in MsgBL.
            msgBL.setVersion(this.mapVersion());
            msgBL.setBillOfLading(this.mapBillOfLadingKidsToFss());
            msgBL.setSenderContact(this.mapSenderContactKidsToFss());	
            msgBL.setCommmunicationContactList(this.mapSenderCommunicationContactKidsToFss());
            if (message.getCurrencyGroup() != null) {
            	TsOneElement tsCG = new TsOneElement("CURRENCYGROUP");
            	tsCG.setElement(message.getCurrencyGroup().getCurrencyValidationDate());
            	msgBL.setCurrencyGroup(tsCG);
            	 if (message.getCurrencyGroup().getCurrencyList() != null) {					
                 	for (Currency cu : message.getCurrencyGroup().getCurrencyList()) {
                 		if (cu != null) {	                 			
                 			msgBL.addCurrencyList(this.mapCurrencyKidsToFss(cu));
                 		}
                 	}
            	 }        
			}
			if (message.getMonetaryAmountGroup() != null && message.getMonetaryAmountGroup().getMonetaryAmountList() != null) {
				int ima = 0;
				for (MonetaryAmount ma : message.getMonetaryAmountGroup().getMonetaryAmountList()) {
					if (ma != null) {
						ima = ima + 1;	
						if (Utils.isStringEmpty(ma.getEdiQualifier())) {
							ma.setEdiQualifier("63");
						}
						msgBL.addMonetaryAmountList(this.mapMonetaryAmountKidsToFss(ma, ima));	
					}
				}
			}			
		    			
			if (message.getTextOnEntireBL() != null) {	                      				
				msgBL.setTextOnEntireBLFSS(this.mapTextOnEntireKidsToFss(message.getTextOnEntireBL()));
			}
			
			if (message.getTotals() != null) {
				TsTotalsOnEntireBL tsTT = new TsTotalsOnEntireBL();
				tsTT.setGrossWeightKilogram(fillDecimalWithZero(message.getTotals().getGrossWeightKilogram(), 11, 3));
				tsTT.setGrossVolumeCubicMetre(fillDecimalWithZero(message.getTotals().getGrossVolumeCubicMetre(), 11, 3));  
				tsTT.setNumberOfEquipment(message.getTotals().getNumberOfEquipment());
				tsTT.setNumberOfPackages(message.getTotals().getNumberOfPackages());
				tsTT.setNumberOfPieces(message.getTotals().getNumberOfPieces());
				msgBL.setTotalsOnEntireBL(tsTT);
			}
			if (message.getDocumentDetails() != null) {
				TsDetailsOnDocument tsDD = new TsDetailsOnDocument();				
				if (!Utils.isStringEmpty(message.getDocumentDetails().getDocumentName())) {
					String ediQualifier = message.getDocumentDetails().getDocumentName().trim();					
					if (ediQualifier.equalsIgnoreCase("BillOfLadingOriginal")) {
						ediQualifier = "706";
					} else if (ediQualifier.equalsIgnoreCase("HouseBillOfLading")) {
						ediQualifier = "714";
					} else if (ediQualifier.equalsIgnoreCase("SeaWayBill")) {
						ediQualifier = "710";
					}
					tsDD.setDocumentName(ediQualifier);
				}				
				tsDD.setFormularLayoutKeyDakosy(message.getDocumentDetails().getFormularLayoutKeyDakosy());
				tsDD.setNumberOfCopiesRequired(message.getDocumentDetails().getNumberOfCopiesRequired());
				tsDD.setNumberOfOriginalsRequired(message.getDocumentDetails().getNumberOfOriginalsRequired());
				msgBL.setDetailsOnDocument(tsDD);
			}			
			if (message.getPlaceAndDateOf() != null) {				
				if (message.getPlaceAndDateOf().getPayment() != null) {		
					TsOneElement tsPa = new TsOneElement("PAYMENT");
					tsPa.setElement(message.getPlaceAndDateOf().getPayment().getDate());
					msgBL.setPayment(tsPa);	
					if (message.getPlaceAndDateOf().getPayment().getPlace() != null) {
						msgBL.setPaymentPlace(this.mapPlaceKidsToFss(message.getPlaceAndDateOf().
													getPayment().getPlace(), "", "57"));	
					}
				}
				if (message.getPlaceAndDateOf().getBillOfLadingIssue() != null) {					
					if (message.getPlaceAndDateOf().getBillOfLadingIssue() != null) {		
						TsOneElement tsIs = new TsOneElement("BILLOFLADINGISSUE");
						tsIs.setElement(message.getPlaceAndDateOf().getBillOfLadingIssue().getDate());
						msgBL.setBLIssue(tsIs);		
						if (message.getPlaceAndDateOf().getBillOfLadingIssue().getPlace() != null) {
							msgBL.setBLIssuePlace(this.mapPlaceKidsToFss(message.getPlaceAndDateOf().
													getBillOfLadingIssue().getPlace(), "", "91"));	
						}
					}			
				}				
			}
			
			if (message.getTermsOfTransportList() != null) {
				for (TransportTerm transportTerm : message.getTermsOfTransportList()) {
					if (transportTerm != null) {
						msgBL.addTermsOfTransportList(this.mapTransportTermKidsToFss(transportTerm));
					}
				}
			} 
			
    		if (message.getForwardersReference() != null) {    		
    			msgBL.setForwarderReference(this.mapReferenceKidsToFss("FORWARDERSREFERENCE", message.getForwardersReference(), 1));
			}	
				
			if (message.getAdditionalReferenceGroup() != null) {
				List<Reference> addList = message.getAdditionalReferenceGroup().getAdditionalReferenceList();
				if (addList != null) {
					int iar = 0;
					for (Reference addReference : addList) {
						if (addReference != null) {
							iar = iar + 1;							
							msgBL.addAdditionatReferenceList(this.mapReferenceKidsToFss("ADDITIONALREFERENCE",
									 																addReference, iar));
						}
					}
				}
			}			
			if (message.getPaymentInstructionsGroup() != null) {
				List<PaymentInstructions> piList = message.getPaymentInstructionsGroup().getPaymentInstructionseList();
				if (piList != null) {
					int pii = 0;
					for (PaymentInstructions pi : piList) {						
						if (pi != null) {
							pii = pii + 1;
							PaymentInstructionsFSS fssPI = new PaymentInstructionsFSS();
							TsPaymentInstructions tsPI = new TsPaymentInstructions();
							tsPI.setCount(pii);
							tsPI.setChargeCategoryCode(pi.getChargeCategoryCode());
							tsPI.setTransportChargeMethodOfPaymentCode(pi.getTransportChargeMethodOfPaymentCode());
							tsPI.setPrepaidCollectIndicator(pi.getPrepaidCollectIndicator());
							fssPI.setPaymentInstructions(tsPI);
							if (pi.getPaymentReferenceList() != null) {
								int ipr = 0;
								for (Reference pReference : pi.getPaymentReferenceList()) {
									if (pReference != null) {		
										ipr = ipr + 1;										
										fssPI.addPaymentReferenceList(this.
												mapReferenceKidsToFss("PAYMENTREFERENCE", pReference, ipr));										
									}
								}
							}
							if (pi.getCurrency() != null) {									
								fssPI.setCurrencyFSS(this.mapCurrencyKidsToFss(pi.getCurrency()));
							}
							if (pi.getPlaceList() != null) {	
								int i = 0;
								for (Place place : pi.getPlaceList()) {	
									if (place != null) {
										i = i + 1;
										TsPlace tsPlace = this.mapPlaceKidsToFss(place, "", "1");
										tsPlace.setCount(i);
										fssPI.addPlaceList(tsPlace);
									}
								}
							}
							if (pi.getMonetaryAmountList() != null) {
								int ipm = 0;
								for (MonetaryAmount monetary : pi.getMonetaryAmountList()) {
									if (monetary != null) {
										ipm = ipm + 1;		
										if (Utils.isStringEmpty(monetary.getEdiQualifier())) {
											monetary.setEdiQualifier("1");
										}
										fssPI.addMonetaryAmountList(this.mapMonetaryAmountKidsToFss(monetary, ipm));
									}
								}
							}	
							msgBL.addPaymentInstructionsList(fssPI);
						}						
					}
				}
			}			
			if (message.getFreightAndChargeGroup() != null) {
				List<FreightAndCharge> freightList = message.getFreightAndChargeGroup().getFreightAndChargeList();
				if (freightList != null) {
					int fi = 0;
					for (FreightAndCharge freight : freightList) {
						if (freight != null) {
							fi = fi + 1;
							FreightAndChargeFSS fssFC = new FreightAndChargeFSS();
							TsFreightAndCharge tsFC = new TsFreightAndCharge();
							tsFC.setCount(fi);
							tsFC.setFreightAndChargeId(this.getFreightAndChargeId(freight.getFreightAndChargeId()));
							tsFC.setFreightAndChargeText(freight.getFreightAndChargeText());
							tsFC.setPrepaidCollectIndicator(freight.getPrepaidCollectIndicator());
							tsFC.setItemNumber(freight.getItemNumber());
							tsFC.setRateOrTariffClassId(freight.getRateOrTariffClassId());
							tsFC.setRateOrTariffClassText(freight.getRateOrTariffClassText());
							fssFC.setFreightAndCharge(tsFC);
							
							if (freight.getPlace() != null) {																
								fssFC.setPlace(this.mapPlaceKidsToFss(freight.getPlace(), "", "1"));								
							}
							if (freight.getCurrency() != null) {									
								fssFC.setCurrencyFSS(this.mapCurrencyKidsToFss(freight.getCurrency()));
							}
							if (freight.getMonetaryAmountList() != null) {
								int ifm = 0;
								for (MonetaryAmount monetary : freight.getMonetaryAmountList()) {
									if (monetary != null) {
										ifm = ifm + 1;	
										if (!Utils.isStringEmpty(monetary.getEdiQualifier())) {
											String ediQualifier = monetary.getEdiQualifier().trim();
											if (ediQualifier.equalsIgnoreCase("Disbursements")) {
												ediQualifier = "50"; 
											} else if (ediQualifier.equalsIgnoreCase("FreightCharge")) {
												ediQualifier = "64";
											} 
											monetary.setEdiQualifier(ediQualifier);
										}
										fssFC.addMonetaryAmountList(this.mapMonetaryAmountKidsToFss(monetary, ifm));
									}
								}
							}
							msgBL.addFreightAndChargeList(fssFC);
						}
					}
				}
			}			
			
			Carriage carriage = message.getCarriage();   //EI20120418
			if (carriage != null) {
				CarriageFSS fssC = new CarriageFSS();
				
				if (carriage.getMainCarriage() != null) {	
					fssC.setMainCarriage(this.mapMainCarriageKidsToFss(carriage.getMainCarriage()));				
				}
				
				if (carriage.getPreCarriageList() != null) {
					int ipre = 0;	
					for (MeansOfTransport pre : carriage.getPreCarriageList()) {									
						if (pre != null) {
							ipre = ipre + 1;							
							fssC.addPreCarriageList(this.mapCarriageKidsToFss(pre, "PRECARRIAGE", "10", ipre));								
						}
					}
				}
				if (carriage.getOnCarriageList() != null) {
						int ion = 0;
						for (MeansOfTransport oc : carriage.getOnCarriageList()) {							
							if (oc != null) {
								ion = ion + 1;								
								fssC.addOnCarriageList(this.mapCarriageKidsToFss(oc, "ONCARRIAGE", "30", ion));
							}
						}								
				}													
				msgBL.setCarriageFSS(fssC);				
			}
			
			if (message.getParties() != null) {				
				msgBL.setShipper(mapPartyKidsToFss(message.getParties().getShipper(), "SHIPPER", 0));			
				msgBL.setConsignee(mapPartyKidsToFss(message.getParties().getConsignee(), "CONSIGNEE", 0));
				msgBL.setNotify(mapPartyKidsToFss(message.getParties().getNotify(), "NOTIFY", 0));
				msgBL.setBookingAgent(mapPartyKidsToFss(message.getParties().getBookingAgent(), "BOOKINGAGENT", 0));
				msgBL.setOriginalShipper(mapPartyKidsToFss(message.getParties().getOriginalShipper(), "ORIGINALSHIPPER", 0));
				msgBL.setSecondNotify(mapPartyKidsToFss(message.getParties().getSecondNotify(), "SECONDNOTIFY", 0));
				msgBL.setOrderOfShipper(mapPartyKidsToFss(message.getParties().getOrderOfShipper(), "ORDEROFSHIPPER", 0));
				msgBL.setDeconsolidator(mapPartyKidsToFss(message.getParties().getDeconsolidator(), "DECONSOLIDATOR", 0));
				msgBL.setCarrier(mapPartyKidsToFss(message.getParties().getCarrier(), "CARRIER", 0));
				msgBL.setFreightForwarder(mapPartyKidsToFss(message.getParties().getFreightForwarder(), "FREIGHTFORWARDER", 0));
				msgBL.setFreightForwarderRequestorBranch(mapPartyKidsToFss(message.getParties().
						getFreightForwarderRequestorBranch(), "FREIGHTFORWARDERREQUESTORBRANCH", 0));							
			}			
			
			if (message.getGoods() != null) {
				List<GoodsItem> goodsItemList = message.getGoods().getGoodsItemList();
				if (goodsItemList != null) {					
					for (GoodsItem item : goodsItemList)  {
						if (item != null) {
							int pos = Integer.parseInt(item.getGoodsItemNumber());
							msgBL.addGoodsItemList(this.mapGoodsItemKidsToFss(item, pos));
						}							
					}
				}
			}			
			if (message.getEquipment() != null) {
				List<EquipmentQualifier> equipmentList = message.getEquipment().getEquipmentQualifierList();
				if (equipmentList != null) {
					int iec = 0;
					int iet = 0;
					for (EquipmentQualifier equipment : equipmentList)  {						
						if (equipment != null) {	
							if (equipment.getContainer() != null) {
								iec = iec + 1;
								msgBL.addEquipmentContainerList(mapEquipmentKidsToFSS("CONTAINER", equipment.getContainer(), iec));
							} else if (equipment.getTrailer() != null) {
								iet = iet + 1;
								msgBL.addEquipmenTrailertList(mapEquipmentKidsToFSS("TRAILER", equipment.getTrailer(), iet));						
							}
						}
					}
				}
			}
			res = msgBL.getFssString();
            
            //Utils.log("(MapImportDeclarationConfirmationToCON getMessage) Msg = " + res);
            
		} catch (FssException e) {	    	
			e.printStackTrace();	        
		}		    
	    return res;
	}
///////////////////////
	private CurrencyFSS mapCurrencyKidsToFss(Currency currency) throws FssException {
		if (currency == null) {
			return null;
		}               	
        CurrencyFSS currFSS = new CurrencyFSS();
        TsOneElement tsCurr = new TsOneElement("CURRENCY");
        tsCurr.setElement(fillDecimalWithZero(currency.getRateOfExchange(), 4, 7));  
        currFSS.setCurrency(tsCurr);
        
        if (currency.getCurrencyDetailList() != null) {							            					
        	for (CurrencyDetails cd : currency.getCurrencyDetailList()) {
        		if (cd != null) {
        			TsCurrencyDetails tsCD = new TsCurrencyDetails();
        			tsCD.setCurrencyCode(cd.getCurrencyCode());								
        			tsCD.setCurrencyBaseRate(cd.getCurrencyRateBase());  //n4 
        			tsCD.setEdiQualifier(cd.getEDIQualifier());
        			currFSS.addCurrencyDetailsList(tsCD);		
        		}	            					
        	}						
        }
        
        return currFSS;               	       
	}
	
	private TsMonetaryAmount mapMonetaryAmountKidsToFss(MonetaryAmount monetaryAmount, int count) {
		if (monetaryAmount == null) {
			return null;
		}		
		TsMonetaryAmount tsMA = new TsMonetaryAmount();
		tsMA.setCount(count);
		tsMA.setEdiQualifier(monetaryAmount.getEdiQualifier());
		tsMA.setValue(fillDecimalWithZero(monetaryAmount.getValue(), 9, 2));
		tsMA.setCurrencyCode(monetaryAmount.getCurrencyCode());
		tsMA.setStatus(monetaryAmount.getStatus());		
		
		return tsMA;
	}
	
	private TextOnEntireBLFSS mapTextOnEntireKidsToFss(TextOnEntireBL texteKids) throws FssException {
	   if (texteKids == null) {
		  return null; 
	   }
	   TextOnEntireBLFSS texte = new TextOnEntireBLFSS();
	   int i = 0;
	   
		if (texteKids.getBillOfLadingRemarksList() != null) {
			i = 0;
			for (TextBL text : texteKids.getBillOfLadingRemarksList()) {
				i = i + 1;																								
				texte.addBLRemarksList(this.mapTextOnBLKidsToFss("TEXTONENTIREBL", "BILLOFLADINGREMARKS", text, i));								
			}
		}
		if (texteKids.getPackingMarkingInformationList() != null) {
			i = 0;
			for (TextBL text : texteKids.getPackingMarkingInformationList()) {
				i = i + 1;								
				texte.addPackagingInformationList(this.mapTextOnBLKidsToFss("TEXTONENTIREBL", 
																			"PACKINGMARKINGINFORMATION", text, i));
			}
		}
		if (texteKids.getDocumentationInstructionsList() != null) {
			i = 0;
			for (TextBL text : texteKids.getDocumentationInstructionsList()) {
				i = i + 1;								
				texte.addDocumentInstructionsList(this.mapTextOnBLKidsToFss("TEXTONENTIREBL", 
																			"DOCUMENTATIONINSTRUCTIONS", text, i));
			}
		}
		if (texteKids.getGoodsDimensionsInWords() != null) {								
			i = 1;			
			TextBL text = texteKids.getGoodsDimensionsInWords();
			texte.setGoodsDimensions(this.mapTextOnBLKidsToFss("TEXTONENTIREBL", "GOODSDIMENSIONSINWORDS", text, i));						
		}
		if (texteKids.getAdditionalInformationList() != null) {
			i = 0;
			for (TextBL text : texteKids.getAdditionalInformationList()) {
				i = i + 1;								
				texte.addAdditionalInformationList(this.mapTextOnBLKidsToFss("TEXTONENTIREBL", 
																			 "ADDITIONALINFORMATION", text, i));
			}
		}
		if (texteKids.getCargoRemarks() != null) {
			i = 1;
			TextBL text = texteKids.getCargoRemarks();		
			if (text != null && !Utils.isStringEmpty(text.getTextReference())) {	
				String textReference = text.getTextReference().trim();
				if (textReference.equalsIgnoreCase("containerized")) {
					textReference = "9";
				} else if (textReference.equalsIgnoreCase("non-containerized")) {
					textReference = "5";
				} else if (textReference.equalsIgnoreCase("combined")) {
					textReference = "7";
				} 
				text.setTextReference(textReference);
				texte.setCargoRemarks(this.mapTextOnBLKidsToFss("TEXTONENTIREBL", "CARGOREMARKS", text, i));
			}
		}		
		if (texteKids.getComplianceWasChecked() != null) {
			i = 1;
			TextBL text = texteKids.getComplianceWasChecked();							
			texte.setCompilanceChecked(this.mapTextOnBLKidsToFss("TEXTONENTIREBL", "COMPLIANCEWASCHECKED", text, i));
		}
		
		return texte;
	}
	
	private TsPlace mapPlaceKidsToFss(Place place, String type, String edi) {
		if (place == null) {
			return null;
		}	
		//TsPlace tsPlace = new TsPlace("PLACE");			
		TsPlace	tsPlace = new TsPlace(type, edi);
				
		tsPlace.setUnLocode(place.getUnLocode());
		tsPlace.setLocationClearText(place.getLocationClearText());		
		
		return tsPlace;
	}
	private TermsOfTransportFSS mapTransportTermKidsToFss(TransportTerm transportTerm) throws FssException {
		if (transportTerm == null) {
			return null;
		}
		TermsOfTransportFSS trFSS = new TermsOfTransportFSS();
		TsTermsOfTransport tsTr = new TsTermsOfTransport();						
		trFSS.setTermsOfTransport(tsTr);					
		
		tsTr.setCode(transportTerm.getCode());
		if (transportTerm.getTextList() != null) {
			int itr = 0;
			for (String text : transportTerm.getTextList()) {
				if (!Utils.isStringEmpty(text)) {
					itr = itr + 1;
					if (itr == 1) {
						tsTr.setText1(text);
					}
					if (itr == 2) {
						tsTr.setText2(text);
					}
				}
			}
		}						
		if (transportTerm.getPlace() != null) {			
			trFSS.setPlace(this.mapPlaceKidsToFss(transportTerm.getPlace(), "", "1"));								
		}		
	
		return trFSS;
	}
	
	private CarriageDetailsFSS mapMainCarriageKidsToFss(CarriageDetails carriage) throws FssException {
		if (carriage == null) {
			return null;
		}		
		CarriageDetailsFSS fssMain = new CarriageDetailsFSS();
		TsCarriage tsMC = new TsCarriage("MAINCARRIAGE");
		
		tsMC.setCount("1");
		tsMC.setEdiQualifier("20"); //main=20, pre 10, on 30
		tsMC.setEstimatedArrivalDate(carriage.getEstimatedArrivalDate());
		tsMC.setEstimatedDepartureDate(carriage.getEstimatedDepartureDate());
		tsMC.setShipReferenceNumber(carriage.getShipReferenceNumber());
		fssMain.setTsCarriage(tsMC);
		
		if (carriage.getMeansOfTransport() != null) {
			TsMeansOfTransport tsMT = new TsMeansOfTransport();
			tsMT.setAllFields(carriage.getMeansOfTransport());	
		    fssMain.setMeansOfTransport(tsMT);
	    }
				
		if (carriage.getPortsAndPlaces() != null) {
			TsPlace tsPlace;						
					if (carriage.getPortsAndPlaces().getPortOfLoading() != null) {
						tsPlace = new TsPlace("PORTOFLOADING", "9");
						tsPlace.setAllFields(carriage.getPortsAndPlaces().getPortOfLoading());
						fssMain.addPlaceList(tsPlace);									
					}									
					if (carriage.getPortsAndPlaces().getPortOfDischarge() != null) {
						tsPlace = new TsPlace("PORTOFDISCHARGE", "12");
						tsPlace.setAllFields(carriage.getPortsAndPlaces().getPortOfDischarge());
						fssMain.addPlaceList(tsPlace);
					}
					
					if (carriage.getPortsAndPlaces().getPlaceOfDeparture() != null) {
						tsPlace = new TsPlace("PLACEOFDEPARTURE", "5");
						tsPlace.setAllFields(carriage.getPortsAndPlaces().getPlaceOfDeparture());
						fssMain.addPlaceList(tsPlace);
					}	
					if (carriage.getPortsAndPlaces().getPlaceOfReceipt() != null) {
						tsPlace = new TsPlace("PLACEOFRECEIPT", "88");
						tsPlace.setAllFields(carriage.getPortsAndPlaces().getPlaceOfReceipt());
						fssMain.addPlaceList(tsPlace);
					}
					if (carriage.getPortsAndPlaces().getPlaceOfDelivery() != null) {
						tsPlace = new TsPlace("PLACEOFDELIVERY", "7");
						tsPlace.setAllFields(carriage.getPortsAndPlaces().getPlaceOfDelivery());
						fssMain.addPlaceList(tsPlace);
					}
					if (carriage.getPortsAndPlaces().getPlaceOfAcceptance() != null) {
						tsPlace = new TsPlace("PLACEOFACCEPTANCE", "10");
						tsPlace.setAllFields(carriage.getPortsAndPlaces().getPlaceOfAcceptance());
						fssMain.addPlaceList(tsPlace);
					}
					if (carriage.getPortsAndPlaces().getPlaceOfTranshipment() != null) {
						tsPlace = new TsPlace("PLACEOFTRANSSHIPMENT", "13");
						tsPlace.setAllFields(carriage.getPortsAndPlaces().getPlaceOfTranshipment());
						fssMain.addPlaceList(tsPlace);
					}
					if (carriage.getPortsAndPlaces().getOnCarriagePort() != null) {
						tsPlace = new TsPlace("ONCARRIAGEPORT", "62");
						tsPlace.setAllFields(carriage.getPortsAndPlaces().getOnCarriagePort());
						fssMain.addPlaceList(tsPlace);
					}
					if (carriage.getPortsAndPlaces().getPreCarriagePort() != null) {
						tsPlace = new TsPlace("PRECARRIAGEPORT", "82");
						tsPlace.setAllFields(carriage.getPortsAndPlaces().getPreCarriagePort());
						fssMain.addPlaceList(tsPlace);
					}
					if (carriage.getPortsAndPlaces().getPlaceOfDestination() != null) {
						tsPlace = new TsPlace("PLACEOFDESTINATION", "8");
						tsPlace.setAllFields(carriage.getPortsAndPlaces().getPlaceOfDestination());
						fssMain.addPlaceList(tsPlace);
					}
		}
		
		return fssMain;
	}

	private CarriageDetailsFSS mapCarriageKidsToFss(MeansOfTransport meansOfTransport, String type, String edi, 
																						int count) throws FssException {
		if (meansOfTransport == null) {
			return null;
		}
		CarriageDetailsFSS carriageFSS = new CarriageDetailsFSS();								
		TsCarriage tsCarriage = new TsCarriage(type);
		tsCarriage.setCount(count);
		tsCarriage.setEdiQualifier(edi); //pre 10, on 30								
		carriageFSS.setTsCarriage(tsCarriage);
									
		TsMeansOfTransport tsMT = new TsMeansOfTransport();
		tsMT.setAllFields(meansOfTransport);	
		carriageFSS.setMeansOfTransport(tsMT);
	
		return carriageFSS;
	}
	
	private GoodsItemFSS mapGoodsItemKidsToFss(GoodsItem item, int pos) throws FssException {
		if (item == null) {
			return null;
		}
		if (item.getFirstPackingLevel() == null) {
			return null;
		}
		GoodsItemFSS itemFSS = new GoodsItemFSS();	
		
		PackingFirstLevelFSS firstFSS = new PackingFirstLevelFSS();
		PackingDetailsFSS firstDetails = new PackingDetailsFSS();
		firstFSS.setPackingDetailsFSS(firstDetails);
		itemFSS.setFirstPackingLevel(firstFSS);
		
		TsPackingLevel tsFirst  = new TsPackingLevel("FIRST");				
		tsFirst.setItemNumber(item.getGoodsItemNumber());																
		tsFirst.setGrossWeightKilogram(fillDecimalWithZero(item.getFirstPackingLevel().getGrossWeightKilogram(), 8, 3));
		firstDetails.setTsPackingLevel(tsFirst);
			
		if (item.getFirstPackingLevel().getDetailsOnItem() != null) {
			firstDetails.setDetailslevel(this.mapDetailslevelKidsToFss(item.getFirstPackingLevel().getDetailsOnItem()));
			
			if (item.getFirstPackingLevel().getDetailsOnItem().getMarksAndNumbersList() != null) {
				TsMarksAndNumber marksAndNumbers = new TsMarksAndNumber();												
				marksAndNumbers.setAllFields(item.getFirstPackingLevel().getDetailsOnItem().getMarksAndNumbersList());
				firstDetails.setMarksAndNumbers(marksAndNumbers);
			}	
			
			if (item.getFirstPackingLevel().getDetailsOnItem().getOriginalShipper() != null) {
				firstDetails.setOriginalShipper(this.mapPartyKidsToFss(item.getFirstPackingLevel().getDetailsOnItem().
															getOriginalShipper(), "ORIGINALSHIPPER", pos));
			}
			if (item.getFirstPackingLevel().getDetailsOnItem().getUltimateConsignee() != null) {
				firstDetails.setUltimateConsignee(this.mapPartyKidsToFss(item.getFirstPackingLevel().getDetailsOnItem().
														getUltimateConsignee(), "ULTIMATECONSIGNEE", pos));			
			}
		}
		if (item.getFirstPackingLevel().getTextOnItem() != null) {
			firstDetails.setTextOnItem(this.mapTextOnItemKidsToFss(item.getFirstPackingLevel().getTextOnItem(), 1));	
		}
			
		if (item.getFirstPackingLevel().getAllocatedEquipmentList() != null) {
			int ial = 0;
			for (AllocatedEquipment al : item.getFirstPackingLevel().getAllocatedEquipmentList()) {
				if (al != null) {
					ial = ial + 1;						
					TsAllocatedEquipment tsAL = new TsAllocatedEquipment();						
					tsAL.setCount(ial);
					tsAL.setEquipmentIdentificationNumber(al.getEquipmentIdentificationNumber());
					if (al.getDistributedOverSeveralContainert() != null) {
						//EI20130412: fillDecimalWithZero() eingefuegt
						tsAL.setGrossVolumeCubicMetre(fillDecimalWithZero(al.getDistributedOverSeveralContainert().getGrossVolumeCubicMetre(), 8, 3));
						tsAL.setGrossWeightKilogram(fillDecimalWithZero(al.getDistributedOverSeveralContainert().getGrossWeightKilogram(), 8, 3));
						tsAL.setNetWeightKilogram(fillDecimalWithZero(al.getDistributedOverSeveralContainert().getNetWeightKilogram(), 8, 3));
						tsAL.setNumberOfPackages(al.getDistributedOverSeveralContainert().getNumberOfPackages());
						tsAL.setTareWeightKilogram(fillDecimalWithZero(al.getDistributedOverSeveralContainert().getTareWeightKilogram(), 8, 3));						
					}
					firstFSS.addAllocatedEquipmentList(tsAL);
				}
			}
		}
		if (item.getFirstPackingLevel().getDangerousGoodsList() != null) {
			int idg = 0;
			for (DangerousGoods dg : item.getFirstPackingLevel().getDangerousGoodsList()) {
				if (dg != null) {
					idg = idg + 1;																				      
					firstFSS.addDangerousGoodsList(this.mapDangerousGoodsKidsToFss(dg, idg));
				}
			}
		}
			
		if (item.getFirstPackingLevel().getPackingLevel2List() != null) {
			//int ip2 = 0;
			for (PackingLevel2 p2 : item.getFirstPackingLevel().getPackingLevel2List()) {
				if (p2 != null) {
					//ip2 = ip2 + 1;
					PackingSecondLevelFSS secondFSS = new PackingSecondLevelFSS();
					PackingDetailsFSS secondDetails = new PackingDetailsFSS();
					secondFSS.setPackingDetailsFSS(secondDetails);
					itemFSS.addSecondPackingLevelList(secondFSS);
										
					TsPackingLevel tsSecond = new TsPackingLevel("SECOND");								
					tsSecond.setItemNumber(item.getGoodsItemNumber());
					secondDetails.setTsPackingLevel(tsSecond);	
						
					if (p2.getDetailsOnItem() != null) {
						secondDetails.setDetailslevel(this.mapDetailslevelKidsToFss(p2.getDetailsOnItem()));
						if (p2.getDetailsOnItem().getMarksAndNumbersList() != null) {
							TsMarksAndNumber marksAndNumbers = new TsMarksAndNumber();	
							marksAndNumbers.setAllFields(p2.getDetailsOnItem().getMarksAndNumbersList());									
							secondDetails.setMarksAndNumbers(marksAndNumbers);
						}									
					}
					if (p2.getTextOnItem() != null) {
						secondDetails.setTextOnItem(this.mapTextOnItemKidsToFss(p2.getTextOnItem(), 2));												
					}
					if (p2.getPackingLevel3List() != null) {
						//int ip3 = 0;
						for (PackingLevel3 p3 : p2.getPackingLevel3List()) {
								//ip3 = ip3 + 1;
							PackingDetailsFSS thirdDetails = new PackingDetailsFSS();
							secondFSS.addThirdPackingLevelList(thirdDetails);
							
							TsPackingLevel tsThird = new TsPackingLevel("THIRD");
							tsThird.setItemNumber(item.getGoodsItemNumber());
							thirdDetails.setTsPackingLevel(tsThird);												
								
							if (p3.getDetailsOnItem() != null) {
								thirdDetails.setDetailslevel(this.mapDetailslevelKidsToFss(p3.getDetailsOnItem()));
								if (p3.getDetailsOnItem().getMarksAndNumbersList() != null) {
									TsMarksAndNumber marksAndNumbers = new TsMarksAndNumber();	
									marksAndNumbers.setAllFields(p3.getDetailsOnItem().getMarksAndNumbersList());									
									thirdDetails.setMarksAndNumbers(marksAndNumbers);
								}									
							}
							if (p3.getTextOnItem() != null) {
								thirdDetails.setTextOnItem(this.mapTextOnItemKidsToFss(p3.getTextOnItem(), 3));												
							}
						}							
					}					
				}										
			}
		}
		
		
		return itemFSS;
	}
	
	private EquipmentFSS mapEquipmentKidsToFSS(String type, EquipmentDetails equipment, int count) throws FssException {
		if (equipment == null) {
			return null;
		}	
		if (Utils.isStringEmpty(type)) {
			return null;
		}
		EquipmentFSS equipmentFSS = new EquipmentFSS();
        String ediQualifier = "CN";        
        if (type.equalsIgnoreCase("TRAILER")) {
        	ediQualifier = "TE";
        }

		TsEquipmentDetails tsDetails = new TsEquipmentDetails(type, ediQualifier);
		equipmentFSS.setEquipmentDetails(tsDetails);
		
		tsDetails.setCount(count);
		tsDetails.setEquipmentIdentificationNumber(equipment.getEquipmentIdentificationNumber());
		tsDetails.setTypeAndSizeISOCode(equipment.getTypeAndSizeISOCode());
		tsDetails.setTypeAndSize(equipment.getTypeAndSizeText());
		if (!Utils.isStringEmpty(equipment.getEquipmentSuppliedBy())) {
			String code = equipment.getEquipmentSuppliedBy();			
			if (code.equalsIgnoreCase("Shipper")) {
				code = "1";
			} else if (code.equalsIgnoreCase("Carrier")) {
				code = "2";
			}
			tsDetails.setFullEmptyIndicator(code);
		}
		if (!Utils.isStringEmpty(equipment.getFullEmptyIndicator())) {
			String code = equipment.getFullEmptyIndicator();
			if (code.equalsIgnoreCase("Empty")) {
				code = "4";
			} else if (code.equalsIgnoreCase("Full")) {
				code = "5";
			}
			tsDetails.setFullEmptyIndicator(code);
		}
		if (!Utils.isStringEmpty(equipment.getMovementTypeCode())) {
			String code = equipment.getMovementTypeCode().trim();			
			if (code.equalsIgnoreCase("LCL/LCL")) {
				code = "2";
			} else if (code.equalsIgnoreCase("FCL/FCL")) {
				code = "3";
			} else if (code.equalsIgnoreCase("FCL/LCL")) {
				code = "4";
			} else if (code.equalsIgnoreCase("LCL/FCL")) {
				code = "5";
			}
			tsDetails.setMovementTypeCode(code);
		}
		tsDetails.setMovementType(equipment.getMovementType());
		tsDetails.setEquipmentPlan(equipment.getEquipmentPlan());
		tsDetails.setWeightPerUnitKilogram(fillDecimalWithZero(equipment.getWeightPerUnitKilogram(), 8, 3));
		tsDetails.setTareWeightKilogram(fillDecimalWithZero(equipment.getTareWeightKilogram(), 8, 3));
//		tsDetails.setGrossWeightKilogram(equipment.getGrossWeightKilogram());
		tsDetails.setGrossVolumeCubicMetre(fillDecimalWithZero(equipment.getGrossVolumeCubicMetre(), 8, 3));
		tsDetails.setCustomsDeclarationNumber(equipment.getCustomsDeclarationNumber());
		
		
		if (equipment.getSealsList() != null) {
			int ics = 0;
			for (Seals seals : equipment.getSealsList()) {
				if (seals != null) {
					ics = ics + 1;
					TsSeals tsSeals = new TsSeals();
					equipmentFSS.addSealsList(tsSeals);
					
					tsSeals.setCount(ics);
					tsSeals.setSealingNumber(seals.getSealingNumber());
					tsSeals.setSealingParty(seals.getSealingParty());
					if (!Utils.isStringEmpty(seals.getSealingPartyCode())) {
						String code = seals.getSealingPartyCode();						
						if (code.equalsIgnoreCase("Carrier")) {
							code = "CA";
						} else if (code.equalsIgnoreCase("Zoll") || code.equalsIgnoreCase("Customs")) {
							code = "CU";
						} else if (code.equalsIgnoreCase("Shipper")) {
							code = "SH";
						} else if (code.contains("Terminal") || code.contains("TERMINAL")) {
							code = "TO";
						} 
						tsSeals.setSealingPartyCode(code);
					}
				}
			}
		}
		if (equipment.getAttachedEquipmentList() != null) {
			int ica = 0;								
			for (AttachedEquipment attached : equipment.getAttachedEquipmentList()) {
				if (attached != null) {
					ica = ica + 1;
					TsAttachedEquipment tsAtt = new TsAttachedEquipment();
					equipmentFSS.addAttachedEquipmentList(tsAtt);
					tsAtt.setCount(ica);											
					tsAtt.setAllFields(attached);												
				}
			}
		}
		
		return equipmentFSS;
	}
	
	private TsOneElement mapVersion() {
		TsOneElement ts = new TsOneElement("VERSION");
		ts.setElement("BL02");
		
		return ts;
	}
	
	private TsBillOfLading mapBillOfLadingKidsToFss() {
		if (message == null) {
			return null;
		}
		TsBillOfLading tsBOL = new TsBillOfLading();
		
		tsBOL.setReferenceNumber(message.getReferenceNumber());		
		if (!Utils.isStringEmpty(message.getMessageFunction())) {
			String mf = message.getMessageFunction().trim();			
			if (mf.equalsIgnoreCase("Original")) {
				mf = "9";			
			} else if (mf.equalsIgnoreCase("Replace")) {
				mf = "5";
			} else if (mf.equalsIgnoreCase("Cancellation")) {
				mf = "1";
			} 		
			tsBOL.setMessageFunction(mf);
		}
		tsBOL.setApplicationSenderId(message.getApplicationSenderId());
		tsBOL.setApplicationRecipientId(message.getApplicationRecipientId());
		
		tsBOL.setParticipantCode(getParticipantCodeFromCustomer());

		
		return tsBOL;
	}
	
	private String getParticipantCodeFromCustomer() {
		String participantCode = null;
		   //String localId = Utils.getCustomerIdFromKewill(this.kidsHeader.getReceiver(),
           //        "BL", this.kidsHeader.getCountryCode());
		   
	         if (blLocalId != null) {
	        	 String[] localIdArr = blLocalId.trim().split("-");
	        	 if (localIdArr.length >= 2) {
	        		 participantCode = localIdArr[1];	        		 
	        	 } else {
	        		 Utils.log("Falscher Eintrag " + blLocalId.trim() + 
	        				  " in customer LOCAL_ID_TYPE=BL: KDNR-Teilnehmercode");
	        	 }
	         } else {
	        	 Utils.log("Fehlender Eintrag in customer LOCAL_ID_TYPE=BL");
	         }
	     return participantCode;
	}

	private TsOneElement mapSenderContactKidsToFss() {
		if (message.getSenderContact() == null) {
			return null;
		}		
		TsOneElement subset = new TsOneElement("SENDERCONTACT");		
		subset.setElement(message.getSenderContact().getContactName());
				
		return subset;
	}
	private List<TsCommunicationContact> mapSenderCommunicationContactKidsToFss() {
		if (message.getSenderContact() == null) {
			return null;
		}
		if (message.getSenderContact().getCommunicationContactList() == null) {
			return null;
		}
		
		List<TsCommunicationContact> tsList = new ArrayList<TsCommunicationContact>();		
		int count = 0;
		for (CommunicationContact cc : message.getSenderContact().getCommunicationContactList()) {
			if (cc != null) {
				count = count + 1;
				TsCommunicationContact tsCC = new TsCommunicationContact();
				tsCC.setEdiQualifier(cc.getCommunicationQualifier());
				//tsCC.setCommunicationNumber(cc.getCommunicationNumber());
				if (!Utils.isStringEmpty(cc.getCommunicationNumber())) {
					String temp = "";
					temp = cc.getCommunicationNumber().replaceAll("@", "<A>");					
					tsCC.setCommunicationNumber(temp);
				}				
				tsCC.setCount(count);
				tsList.add(tsCC);
			}
		}
		
		return tsList;
	}
	
	private PartyFSS mapPartyKidsToFss(PartyDetails party, String type, int i)  throws FssException  {
		if (party == null) {
			return null;
		}
		PartyFSS fssParty = new PartyFSS();
		
		TsParty tsParty = new TsParty(type);		
			tsParty.setEdiQualifier(this.getEdiQualifierOfParty(type));		
			tsParty.setCount(i);
			tsParty.setPartyId(party.getPartyId());
			tsParty.setTaxRegistrationNumber(party.getTaxRegistrationNumber());	
			if (party.getReferenceList() != null) {
				int ir = 0;
				for (String ref : party.getReferenceList()) {
					ir = ir + 1;
					if (ir == 1) {
						tsParty.setReference1(ref);
					} else if (ir == 2) {
						tsParty.setReference2(ref);
					} else if (ir == 3) {
						tsParty.setReference3(ref);
					} else if (ir == 4) {
						tsParty.setReference4(ref);
					} else if (ir == 5) {
						tsParty.setReference5(ref);
					} else if (ir == 6) {
						tsParty.setReference6(ref);
					} else if (ir == 7) {
						tsParty.setReference7(ref);
					} else if (ir == 8) {
						tsParty.setReference8(ref);
					}					
				}			
			}
			fssParty.setTsParty(tsParty);

			if (party.getAddressQualified() != null) {				
				TsAddressQualified tsAddress = new TsAddressQualified();
				tsAddress.setAllFields(party.getAddressQualified());
				fssParty.setAddress(tsAddress);						
			} else if (party.getAddressUnqualified() != null) {			
				TsAddressRows tsAdrRow = new TsAddressRows();
				tsAdrRow.setAllFields(party.getAddressUnqualified());
				fssParty.setAddressRows(tsAdrRow);
			}
					
			if (party.getContact() != null) {
				//TsContactIwa tsContact = new TsContactIwa();
				//tsContact.setAllFields(party.getContact());
				TsOneElement tsContact = new TsOneElement("CONTACT");
				tsContact.setElement(party.getContact().getContactName());
				fssParty.setContact(tsContact);
				
				if (party.getContact().getCommunicationContactList() != null) {
					int icc = 0;
					for (CommunicationContact cc : party.getContact().getCommunicationContactList()) {
						icc = icc + 1;
						TsCommunicationContact tsCC = new TsCommunicationContact();
						tsCC.setCount(icc);
						tsCC.setEdiQualifier(cc.getCommunicationQualifier());
						//tsCC.setCommunicationNumber(cc.getCommunicationNumber());						
						if (!Utils.isStringEmpty(cc.getCommunicationNumber())) {
							String temp = "";
							temp = cc.getCommunicationNumber().replaceAll("@", "<A>");							
							tsCC.setCommunicationNumber(temp);
						}										
						fssParty.addCommmunicationContactList(tsCC);
					}
				}				
			}			
			
		return fssParty;
	}
	
	private TsGoodsItemDetails mapDetailslevelKidsToFss(ItemDetails itemDetails) {
		if (itemDetails == null) {
			return null;
		}		
		TsGoodsItemDetails tsDetail = new TsGoodsItemDetails();
			
		tsDetail.setNumberOfPackages(itemDetails.getNumberOfPackages());
		tsDetail.setTypeOfPackagesIdentification(itemDetails.getTypeOfPackagesIdentification());
		tsDetail.setTypeOfPackagesText(itemDetails.getTypeOfPackagesText());
			
		tsDetail.setHarmonizedSystemCommodityCode(itemDetails.getHarmonizedSystemCommodityCode());
		tsDetail.setNetWeightKilogram(fillDecimalWithZero(itemDetails.getNetWeightKilogram(), 8, 3));
		tsDetail.setTareWeightKilogram(fillDecimalWithZero(itemDetails.getTareWeightKilogram(), 8, 3));
		tsDetail.setGrossVolumeCubicMetre(fillDecimalWithZero(itemDetails.getGrossVolumeCubicMetre(), 8, 3));
		tsDetail.setBookingReferenceNumber(itemDetails.getBookingReferenceNumber());
		tsDetail.setCustomsDeclarationNumber(itemDetails.getCustomsDeclarationNumber());
		tsDetail.setArticleNumber(itemDetails.getArticleNumber());
		tsDetail.setCargoControlNumber(itemDetails.getCargoControlNumber());
		
		return tsDetail;
	}
	private TsTextOnBL mapTextOnBLKidsToFss(String type, String name, TextBL text, int count) {
		if (Utils.isStringEmpty(name)) {
			return null;
		}
		TsTextOnBL ts = new TsTextOnBL(type, name);					
		ts.setCount(count);
		ts.setEdiQualifier(this.getEdiQualifierOfText(name));    
		
		if (!Utils.isStringEmpty(text.getTextReference())) {   
			ts.setTextReference(text.getTextReference());			
		}
		if (name.equalsIgnoreCase("REMARKSBEFOREGOODSDESCRIPTION")) {   //EI20120514
			ts.setTextReference("3");
		}
		
		if (text.getTextList() != null) {	
			//int ci = i;	
			int ci = 0;
			for (String tx : text.getTextList()) {	
				if (!Utils.isStringEmpty(tx)) { 
					ci = ci + 1;
					if (ci == 1) {
						ts.setText1(tx);											
					} else if (ci == 2) {
						ts.setText2(tx);
					} else if (ci == 3) {
						ts.setText3(tx);
					} else if (ci == 4) {
						ts.setText4(tx);								
					} else if (ci == 5) {
						ts.setText5(tx);
					}
				}
			}	
		}
		
		ts.setLanguageCoded(text.getLanguageCoded());
		
		return ts;
	}
	private TextOnItemFSS mapTextOnItemKidsToFss(ItemText textKids, int level) throws FssException  {
		if (textKids == null) {
			return null;
		}
		
		TextOnItemFSS textFSS = new TextOnItemFSS();
		int itt = 0;
		if (textKids.getGoodsDescriptionList() != null) {
			itt = 0;
			for (TextBL text : textKids.getGoodsDescriptionList()) {
				if (text != null) {	
				itt = itt + 1;																				
				textFSS.addGoodsDescriptionList(this.
						mapTextOnBLKidsToFss("TEXTONITEM", "GOODSDESCRIPTION", text, itt));
				}
			}										
		}									
		if (textKids.getGoodsDescriptionNoPrintList() != null && level == 1) {
			itt = 0;
			for (TextBL text : textKids.getGoodsDescriptionNoPrintList()) {
				if (text != null) {	
				itt = itt + 1;											
				textFSS.addGoodsDescriptionList(
						this.mapTextOnBLKidsToFss("TEXTONITEM", "GOODSDESCRIPTIONNOPRINT", text, itt));
				}
			}
		}
		if (textKids.getRemarksBeforeGoodsDescriptionList() != null) {
			itt = 0;
			for (TextBL text : textKids.getRemarksBeforeGoodsDescriptionList()) {
				if (text != null) {											
				itt = itt + 1;											
				textFSS.addGoodsDescriptionList(this.
						mapTextOnBLKidsToFss("TEXTONITEM", "REMARKSBEFOREGOODSDESCRIPTION", text, itt));
				}
			}
		}
		if (textKids.getRemarksAfterGoodsDescriptionList() != null) {
			itt = 0;
			for (TextBL text : textKids.getRemarksAfterGoodsDescriptionList()) {
				if (text != null) {											
				itt = itt + 1;											
				textFSS.addGoodsDescriptionList(this.
						mapTextOnBLKidsToFss("TEXTONITEM", "REMARKSAFTERGOODSDESCRIPTION", text, itt));
				}
			}
		}
		if (textKids.getPackagingInformationList() != null) {
			itt = 0;
			for (TextBL text : textKids.getPackagingInformationList()) {
				if (text != null) {										
				itt = itt + 1;											
				textFSS.addGoodsDescriptionList(this.
						mapTextOnBLKidsToFss("TEXTONITEM", "PACKAGINGINFORMATION", text, itt));
				}
			}
		}
		
		return textFSS;
	}
	private TsReference mapReferenceKidsToFss(String tsTyp2, ReferenceDetails referenceDetails, int count) {
		if (referenceDetails == null) {
			return null;
		}
		TsReference tsReference = new TsReference(tsTyp2);
		String type = "";
		String dateTime = referenceDetails.getDateTime();
		
		tsReference.setCount(count);
		tsReference.setEdiQualifier("FF");
		tsReference.setReference(referenceDetails.getReference());
		tsReference.setDateTime(referenceDetails.getDateTime());
		if (dateTime != null && dateTime.length() == 12) {
			type = "203";
		} else if (dateTime != null && dateTime.length() == 8) {
			type = "102";
		}
		tsReference.setDateTimeType(type); 
		
		return tsReference;
	}
	private TsReference mapReferenceKidsToFss(String tsTyp2, Reference reference, int count) {
		if (reference == null) {
			return null;
		}
		if (reference.getReferenceDetail() == null) {
			return null;
		}
		TsReference tsReference = new TsReference(tsTyp2);
		String type = "";
		String dateTime = reference.getReferenceDetail().getDateTime();
		
		String edi = reference.getEdiQualifier();		
		if (Utils.isStringEmpty(edi)) {
			edi = this.getEdiQualifierOfReferenceName(reference.getReferenceName());
		}
		//getEdiQualifierOfReferenceName
		tsReference.setCount(count);
		tsReference.setEdiQualifier(edi);
		tsReference.setReference(reference.getReferenceDetail().getReference());
		tsReference.setDateTime(reference.getReferenceDetail().getDateTime());
		if (dateTime != null && dateTime.length() == 12) {
			type = "203";
		} else if (dateTime != null && dateTime.length() == 8) {
			type = "102";
		}
		tsReference.setDateTimeType(type); 
		
		return tsReference;
	}
	
	private DangerousGoodsFSS mapDangerousGoodsKidsToFss(DangerousGoods dg, int count)  throws FssException {
		if (dg == null) {
			return null;
		}
		String dgType = "";
		DangerousGoodsDetails dgd = null;
				
		if (dg.getImd() != null && !dg.getImd().isEmpty()) {
			dgType = "IMD";
			dgd = dg.getImd();
		} else if (dg.getRid() != null && !dg.getRid().isEmpty()) {
			dgType = "RID";
			dgd = dg.getRid();
		} else if (dg.getAdr() != null && !dg.getAdr().isEmpty()) {
			dgType = "ADR";
			dgd = dg.getAdr();
		} else if (dg.getAnr() != null && !dg.getAnr().isEmpty()) {
			dgType = "ANR";
			dgd = dg.getAnr();
		}
		
		DangerousGoodsFSS dgFSS = new DangerousGoodsFSS();
		
		TsDangerousGoodsDetails tsDG = this.mapDangerousGoodsDetailsKidsToFss(dgd);
		tsDG.setCount(count);
		tsDG.setEdiQualifier(dgType);
		dgFSS.setDGDetails(tsDG);
		
		
		TsTenElements tsTN = this.mapDangerousGoodsTechnicalNameKidsToFss(dgd);
		dgFSS.setTechnicalName(tsTN);
		
		if (dgd.getAdditionalInformation() != null) {
			TsFiveElements tsAdd = new TsFiveElements("ADDITIONALINFORMATIONDGR");			
			tsTN.setAllFields(dgd.getAdditionalInformation().getLevelOfMarinePollution(), 
					dgd.getAdditionalInformation().getTextList());
			dgFSS.setDangerousGoodsAdditional(tsAdd);
		}
		if (dgd.getEmergencyContactList() != null) {
			for (Contact contact : dgd.getEmergencyContactList()) {
				if (contact != null) {
					ContactFSS contactFSS = new ContactFSS();
					dgFSS.addContactFSSList(contactFSS);
					TsOneElement tsCN = new TsOneElement("CONTACT");
					tsCN.setElement(contact.getContactName());
					contactFSS.setContact(tsCN);
					if (contact.getCommunicationContactList() != null) {
						int icc = 0;
						for (CommunicationContact cc : contact.getCommunicationContactList()) {
							if (cc != null) {
								icc = icc + 1;
								TsCommunicationContact tsCC = new TsCommunicationContact();
								tsCC.setCount(icc);
								tsCC.setEdiQualifier(cc.getCommunicationQualifier());
								//tsCC.setCommunicationNumber(cc.getCommunicationNumber());	
								if (!Utils.isStringEmpty(cc.getCommunicationNumber())) {
									String temp = "";
									temp = cc.getCommunicationNumber().replaceAll("@", "<A>");							
									tsCC.setCommunicationNumber(temp);
								}	
								contactFSS.addCommunicationContactList(tsCC);
							}
						}
					}
				}
			}
		}
		
		return dgFSS;
	}
	private TsDangerousGoodsDetails mapDangerousGoodsDetailsKidsToFss(DangerousGoodsDetails dg) {
		if (dg == null) {
			return null;
		}
		
		TsDangerousGoodsDetails ts = new TsDangerousGoodsDetails();
		
		ts.setHazardCodeIdentification(dg.getHazardCodeIdentification());
		ts.setHazardItemPageNumber(dg.getHazardItemPageNumber());
		ts.setHazardCodeVersionNumber(dg.getHazardCodeVersionNumber());
		ts.setUNDGNumber(dg.getUNDGNumber());
		if (dg.getShipmentFlashpoint() != null) {
			ts.setFlashpoint(dg.getShipmentFlashpoint().getFlashpoint());
			ts.setFlashpointQualifier(dg.getShipmentFlashpoint().getFlashpointQualifier());

		}		
		if (!Utils.isStringEmpty(dg.getLevelOfDanger())) {
			String level = dg.getLevelOfDanger().trim();
			if (level.equalsIgnoreCase("great")) {
				level = "1";
			} else if (level.equalsIgnoreCase("medium")) {
				level = "2";
			} else if (level.equalsIgnoreCase("minor")) {
				level = "3";
			}
			ts.setLevelOfDanger(level);
		}		
		ts.setEMSNumber(dg.getEMSNumber());
		ts.setMFAG(dg.getMFAG());
		if (dg.getLabelMarkingList() != null) {
			int idg = 0;
			String label = "";
			for (String text : dg.getLabelMarkingList()) {				
				if (!Utils.isStringEmpty(text)) {
					idg = idg + 1;
					if (idg == 1) {
						label = text;
					} else {
						label = label + " " + text;
					}
				}
			}
			ts.setLabelMarking(label);
		}		
		ts.setGrossWeightKilogram(fillDecimalWithZero(dg.getGrossWeightKilogram(), 8, 3));
		ts.setNetWeightKilogram(fillDecimalWithZero(dg.getNetWeightKilogram(), 8, 3));
		ts.setNetNetWeightKilogram(fillDecimalWithZero(dg.getNetNetWeightKilogram(), 8, 3));
		ts.setNetExplosiveWeightKilogram(fillDecimalWithZero(dg.getNetExplosiveWeightKilogram(), 8, 3));
		ts.setRadioactiveIndexOfTransportNumber(fillDecimalWithZero(dg.getRadioactiveIndexOfTransportNumber(), 8, 3));	
		ts.setRadioactivityBecquerel(fillDecimalWithZero(dg.getRadioactivityBecquerel(), 8, 3)); 
		ts.setRadioactivityCurie(fillDecimalWithZero(dg.getRadioactivityCurie(), 8, 3));   	
		
		return ts;
	}
			
	private TsTenElements mapDangerousGoodsTechnicalNameKidsToFss(DangerousGoodsDetails dgd) {
		if (dgd == null) {
			return null;
		}
		if (dgd.getTechnicalName() == null) {
			return null;
		}	
		TsTenElements tsTN = new TsTenElements("TECHNICALNAME");
		
		if (dgd.getTechnicalName().getTEQ() != null && !dgd.getTechnicalName().getTEQ().isEmpty()) {			
			tsTN.setAllFields("TEQ", dgd.getTechnicalName().getTLQ().getTextList());
		} else if (dgd.getTechnicalName().getTLQ() != null  && !dgd.getTechnicalName().getTLQ().isEmpty()) {			
			tsTN.setAllFields("TLQ", dgd.getTechnicalName().getTLQ().getTextList());
		}
				
		return tsTN;
	}
	
	private void replaceVorWithHead() {
		TsVOR vor = msgBL.getVorSubset();
		TsHead head = new TsHead("HEAD");
		msgBL.setHeadSubset(head);
		//AK20120724
		head.setStruktur("001");
		head.setArt("inh");
		head.setRichtung("KD_ZO");
		head.setZeichensatz("1");
		head.setVersion("07000");
		
		head.setKdnr(this.getKdnr());             
		head.setTin("DE8999201");
		head.setNlnr("0000");
		head.setVbtyp("DBL");
		if (message.getPortSystem().equals("DAK")) {
			head.setEudstnr("DE009999");   //Dakosy DE009999, DE009996=BHT			
		} else if (message.getPortSystem().equals("BHT")) {
			head.setEudstnr("DE009996");  
		}
		
		head.setMsgName("DBL02A");
		//AK20120807
		if (getKidsHeader().getMessageID() != null) {
			if (getKidsHeader().getMessageID().length() >= 9) {
				head.setMsgid(Utils.removePrependingZeros(getKidsHeader().getMessageID().substring(4, 9)));
			}
			head.setDateiName(this.getKidsHeader().getMessageID());
		}
		//head.setMsgid(this.getKidsHeader().getMessageID());


		//head.setSubVersion("C.1.3");		
		if (this.kidsHeader.getReceiver().contains("TST")) {
			head.setTestFlag("T");
		} else {
			head.setTestFlag("");
		}
		if (message.getTestFlag() != null && message.getTestFlag().equalsIgnoreCase("T")) {  //AK20120807
				head.setTestFlag("T");
			} else {
				head.setTestFlag("P");	
		}
		
		head.setDateTime(FssUtils.getDateTime());
		//AK20120724
		//head.setMan
		//head.setNl
		//head.setModul
		//head.setStatus("2");
		//head.setMeldungkz("J");
		//head.setVerfahrenKz("");
		//head.setMsgid
		//head.setInRaplyTo("");
		//head.setSubtyp("000");
		//head.setAllgemein("OK");
		
		if (vor == null) {			
			head.setDateiName("B???L");
			//head.setMan(vor.getMan());
			//head.setNl(vor.getNl());
			head.setModul(this.getKidsHeader().getMethod());			
			
			head.setInReplyTo(this.getKidsHeader().getInReplyTo());
			head.setTestFlag(message.getTestFlag());
		} else {			
			head.setMan(vor.getMan().trim());
			head.setNl(vor.getNl().trim());		
			//head.setModul(vor.getModul().trim());
			//head.setStatus(vor.getKzsta().trim());
			//head.setMeldungkz(vor.getKzmeld().trim());
			//head.setMsgid(vor.getMsgid().trim());
			//head.setInRaplyTo("");			
		}
	}

	private String getKdnr() {
		String kdnr = "";
	
	         if (blLocalId != null) {
	        	 String[] localIdArr = blLocalId.split("-");
	        	 if (localIdArr.length > 0) {
		        	 kdnr = localIdArr[0];	        		 
	        	 } else {
	        		 Utils.log("Falscher Eintrag customer LOCAL_ID_TYPE=BL: KDNR-Teilnehmercode");
	        	 }
	         } else {
	        	 Utils.log("Fehlender Eintrag customer LOCAL_ID_TYPE=BL");
	         }
		return kdnr;
	}
	
	private String getBlLocalId(String kcxId) {
		String blLocal = "";
		blLocal = Utils.getCustomerIdFromKewill(this.kidsHeader.getReceiver(),
                "BL", this.kidsHeader.getCountryCode()); 	         
		return blLocal;
	}
}


