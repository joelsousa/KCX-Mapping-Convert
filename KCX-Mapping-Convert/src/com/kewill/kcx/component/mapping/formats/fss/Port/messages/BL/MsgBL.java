package com.kewill.kcx.component.mapping.formats.fss.Port.messages.BL;

import java.util.ArrayList;
import java.util.List;

import com.kewill.kcx.component.mapping.formats.fss.Import.messages.FssMessage;
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
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsAllocatedEquipment;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsAttachedEquipment;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsBillOfLading;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsCommunicationContact;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsCurrencyDetails;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsDetailsOnDocument;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsMonetaryAmount;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsOneElement;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsPlace;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsReference;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsSeals;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsTextOnBL;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsTotalsOnEntireBL;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module       : Port - BillOfLading.<br>
 * Created      : 10.04.2012<br>
 * Description	: 
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class MsgBL extends FssMessage {
 
	private TsVOR							tsVor;
	private TsHead							tsHead;             //EI20120511
	private TsOneElement					tsVersion;
	private TsBillOfLading 					tsBOL; 
	
	private TsOneElement 					tsSenderContact;   
	private List<TsCommunicationContact> 	commmunicationContactList;	
	private TsOneElement                    tsCurrencyGroup;	
	private List<CurrencyFSS> 				currencyList;	  		//2x
	private List<TsMonetaryAmount> 			monetaryAmountList;         //99x
	private TextOnEntireBLFSS 				tsTextOnEntireBL;         
	private TsTotalsOnEntireBL 				tsTotalsOnEntireBL;   		
	private TsDetailsOnDocument 			tsDetailsOnDocument;
	//private TsPlaceAndDateOf 				tsPlaceAndDateOf;	
	private TsOneElement					tsPayment;
	private TsPlace                         tsPaymentPlace; 
	private TsOneElement					tsBLIssue;
	private TsPlace                         tsBLIssuePlace; 
	private List<TermsOfTransportFSS> 		termsOfTransportList;   //2x
	private TsReference 					tsForwarderReference;   
	private List<TsReference> 				additionatReferenceList; //998x
	private List<PaymentInstructionsFSS> 	paymentInstructionsList;  //9x
	private List<FreightAndChargeFSS> 		freightAndChargeFSSList;  //99x
	//private List<CarriageFSS> 				carriageFSSList;  //99x
	private CarriageFSS  				    carriageFSS;  //99x
	private PartyFSS shipper;
	private PartyFSS consignee;
	private PartyFSS notify;
	private PartyFSS bookingAgent;
	private PartyFSS originalShipper;
	private PartyFSS secondNotify;
	private PartyFSS orderOfShipper;
	private PartyFSS deconsolidator;
	private PartyFSS carrier;
	private PartyFSS freightForwarder;
	private PartyFSS freightForwarderRequestorBranch;	
	
	private List<GoodsItemFSS> goodsItemList;  //999x
	private List<EquipmentFSS> eqContainerList;  //999x
	private List<EquipmentFSS> eqTrailerList;
	private String kdnr = "";
		
	public MsgBL() {
        super();
	}
	  
	public String getFssString() throws FssException {
		String res = "";	
		
		if (tsHead != null) {	 
			res = appendString(res, tsHead.teilsatzBilden());	
		} else if (tsVor != null) {	 
			res = appendString(res, tsVor.teilsatzBilden());	
		}
		
		if (tsVersion != null) {	 
			res = appendString(res, tsVersion.teilsatzBilden());	
		}
		if (tsBOL != null && !tsBOL.isEmpty()) {	 
			res = appendString(res, tsBOL.teilsatzBilden());	
		}
		if (tsSenderContact != null && !tsSenderContact.isEmpty()) {	 
			res = appendString(res, tsSenderContact.teilsatzBilden());	
		}
		if (commmunicationContactList != null) {                     					 			
			for (TsCommunicationContact cc : commmunicationContactList) {				
				if (cc != null && !cc.isEmpty()) {
					res = appendString(res, cc.teilsatzBilden());
				}
			}
		}
		if (tsCurrencyGroup != null && !tsCurrencyGroup.isEmpty()) {	 
			res = appendString(res, tsCurrencyGroup.teilsatzBilden());	
		
			if (currencyList != null) {                     					 			
				for (CurrencyFSS currFSS : currencyList) {				
					if (currFSS != null) {					
						res = res + this.currencyBilden(currFSS);
					}
				}
			}
		}	
		
		if (monetaryAmountList != null) {                     					 			
			for (TsMonetaryAmount mon : monetaryAmountList) {				
				if (mon != null && !mon.isEmpty()) {
					res = appendString(res, mon.teilsatzBilden());
				}
			}
		}
		
		if (tsTextOnEntireBL != null) {   
			if (tsTextOnEntireBL.getBLRemarksList() != null) {
				for (TsTextOnBL tx : tsTextOnEntireBL.getBLRemarksList()) {				
					if (tx != null && !tx.isEmpty()) {
						res = appendString(res, tx.teilsatzBilden());
					}
				}
			}
			if (tsTextOnEntireBL.getPackagingInformationList() != null) {
				for (TsTextOnBL tx : tsTextOnEntireBL.getPackagingInformationList()) {				
					if (tx != null && !tx.isEmpty()) {
						res = appendString(res, tx.teilsatzBilden());
					}
				}
			}
			if (tsTextOnEntireBL.getDocumentInstructionsList() != null) {
				for (TsTextOnBL tx : tsTextOnEntireBL.getDocumentInstructionsList()) {				
					if (tx != null && !tx.isEmpty()) {
						res = appendString(res, tx.teilsatzBilden());
					}
				}
			}
			if (tsTextOnEntireBL.getGoodsDimensions() != null) {
				 res = appendString(res, tsTextOnEntireBL.getGoodsDimensions().teilsatzBilden());
			}
			if (tsTextOnEntireBL.getAdditionalInformationList() != null) {
				for (TsTextOnBL tx : tsTextOnEntireBL.getAdditionalInformationList()) {				
					if (tx != null && !tx.isEmpty()) {
						res = appendString(res, tx.teilsatzBilden());
					}
				}
			}
													
			if (tsTextOnEntireBL.getCargoRemarks() != null) {   
					    res = appendString(res, tsTextOnEntireBL.getCargoRemarks().teilsatzBilden());
			}				
			if (tsTextOnEntireBL.getCompilanceChecked() != null) {   
			    res = appendString(res, tsTextOnEntireBL.getCompilanceChecked().teilsatzBilden());
			}
		}
	
		if (tsTotalsOnEntireBL != null && !tsTotalsOnEntireBL.isEmpty()) {	 
			res = appendString(res, tsTotalsOnEntireBL.teilsatzBilden());	
		}
		
		if (tsDetailsOnDocument != null && !tsDetailsOnDocument.isEmpty()) {	 
			res = appendString(res, tsDetailsOnDocument.teilsatzBilden());	
		}
		
		if (tsPayment != null && tsPaymentPlace != null && !tsPaymentPlace.isEmpty()) {	 
			res = appendString(res, tsPayment.teilsatzBilden());			
			res = appendString(res, tsPaymentPlace.teilsatzBilden());	
		}
		if (tsBLIssue != null && tsBLIssuePlace != null && !tsBLIssuePlace.isEmpty()) {	 
			res = appendString(res, tsBLIssue.teilsatzBilden());			
			res = appendString(res, tsBLIssuePlace.teilsatzBilden());	
		}
		
		if (termsOfTransportList != null) {                     					 			
			for (TermsOfTransportFSS terms : termsOfTransportList) {				
				if (terms != null) {					
					if (terms.getTermsOfTransport() != null && terms.getPlace() != null) {	 							
						res = appendString(res, terms.getTermsOfTransport().teilsatzBilden());
						res = appendString(res, terms.getPlace().teilsatzBilden());	
					}
				}
			}
		}
		if (tsForwarderReference != null && !tsForwarderReference.isEmpty()) {	 
			res = appendString(res, tsForwarderReference.teilsatzBilden());	
		}		
		if (additionatReferenceList != null) {                     					 			
			for (TsReference addRef : additionatReferenceList) {				
				if (addRef != null && !addRef.isEmpty()) {									
					res = appendString(res, addRef.teilsatzBilden());
				}
			}
		}		
		if (paymentInstructionsList != null) {                     					 			
			for (PaymentInstructionsFSS payInst : paymentInstructionsList) {				
				if (payInst != null) {						
					res = res + this.paymentInstructionsBilden(payInst);
				}
			}
		}		
		if (freightAndChargeFSSList != null) {                     					 			
			for (FreightAndChargeFSS freightAndCharge : freightAndChargeFSSList) {				
				if (freightAndCharge != null) {	
					res = res + this.freightAndChargeBilden(freightAndCharge);
				}
			}
		}
				
		if (carriageFSS != null) {				
			res = res + this.carrigeBilden(carriageFSS);
		}
		
		if (shipper != null) {	 
			res = res + this.partyBilden(shipper);
		}
		if (consignee != null) {	 
			res = res + this.partyBilden(consignee);
		}
		if (notify != null) {	 
			res = res + this.partyBilden(notify);
		}	
		if (bookingAgent != null) {	 
			res = res + this.partyBilden(bookingAgent);
		}
		if (originalShipper != null) {	 
			res = res + this.partyBilden(originalShipper);
		}
		if (secondNotify != null) {	 
			res = res + this.partyBilden(secondNotify);
		}
		if (orderOfShipper != null) {	 
			res = res + this.partyBilden(orderOfShipper);
		}	
		if (deconsolidator != null) {	 
			res = res + this.partyBilden(deconsolidator);
		}
		if (carrier != null) {	 
			res = res + this.partyBilden(carrier);
		}
		if (freightForwarder != null) {	 
			res = res + this.partyBilden(freightForwarder);
		}
		if (freightForwarderRequestorBranch != null) {	 		
			res = res + this.partyBilden(freightForwarderRequestorBranch);
		}	
		
		
		if (goodsItemList != null) {                     					 			
			for (GoodsItemFSS item : goodsItemList) {				
				if (item != null) {
					res = res + this.itemBilden(item);
				}
			}
		}
		if (eqContainerList != null) {                     					 			
			for (EquipmentFSS equipment : eqContainerList) {				
				if (equipment != null) {					
					res = res + this.equipmentBilden(equipment);
				}
			}
		} 
		//else if (eqTrailerList != null) {
		if (eqTrailerList != null) {
			for (EquipmentFSS equipment : eqTrailerList) {				
				if (equipment != null) {
					res = res + this.equipmentBilden(equipment);
				}
			}
		}
		
		return res;
	}

////// getter setter
	public TsVOR getVorSubset() {
		return tsVor;
	}
	public void setVorSubset(TsVOR vorSubset) {
		this.tsVor = vorSubset;
	}
	  
	public TsHead getHeadSubset() {
		return tsHead;
	}
	public void setHeadSubset(TsHead tsHead) {
		this.tsHead = tsHead;
	}
	
	    public void setSenderContact(TsOneElement contact) {	
	    	tsSenderContact = contact; 
	    	if (contact != null && contact.getTsTyp() != null) {
	    		if (contact.getTsTyp().equals("SENDER")) {
	    			tsSenderContact = contact;	
	    		}
	    	}
	    }
	    public TsOneElement getSenderContact() {
	        return tsSenderContact;
	    }
	    
	    public void setBillOfLading(TsBillOfLading bol) {	    	
	    	tsBOL = bol;
	    }	    
	    public TsBillOfLading getBillOfLading() {
	        return tsBOL;
	    }
	    
	    public void setCurrencyGroup(TsOneElement group) {	    	
	    	tsCurrencyGroup = group;
	    }	    
	    public TsOneElement  getCurrencyGroup() {
	        return tsCurrencyGroup;
	    }
	                     
	    public void setCurrencyList(List<CurrencyFSS> list) {	    	
	    	currencyList = list;
	    }	    
	    public List<CurrencyFSS>  getCurrencyList() {
	        return currencyList;
	    }
	    public void addCurrencyList(CurrencyFSS cc) {	
	    	if (currencyList == null) {
	    		currencyList = new ArrayList<CurrencyFSS>();	
	    	}
	    	currencyList.add(cc);
	    }
	   
	    public List<TsCommunicationContact> getCommmunicationContactList() {
	        return commmunicationContactList;
	    } 
	    public void setCommmunicationContactList(List<TsCommunicationContact> list) {
	        commmunicationContactList = list;
	    }
	    public void addCommmunicationContactList(TsCommunicationContact cc) {
	       if (commmunicationContactList == null) {
	    	   commmunicationContactList = new ArrayList<TsCommunicationContact>();
	       }
	       commmunicationContactList.add(cc);
	    }
	  
	    
	    public void setMonetaryAmountList(List<TsMonetaryAmount> list) {	    	
	    	monetaryAmountList = list;
	    }	    
	    public List<TsMonetaryAmount>  getMonetaryAmountList() {
	        return monetaryAmountList;
	    }
	    public void addMonetaryAmountList(TsMonetaryAmount ma) {	
	    	if (monetaryAmountList == null) {
	    		monetaryAmountList = new ArrayList<TsMonetaryAmount>();	
	    	}
	    	monetaryAmountList.add(ma);
	    }
	   		
	    public void setTextOnEntireBLFSS(TextOnEntireBLFSS text) {	    	
	    	tsTextOnEntireBL = text;
	    }	    
	    public TextOnEntireBLFSS  getTextOnEntireBLFSS() {
	        return tsTextOnEntireBL;
	    }
	   
	    
	    public void setTotalsOnEntireBL(TsTotalsOnEntireBL cg) {	    	
	    	tsTotalsOnEntireBL = cg;
	    }	    
	    public TsTotalsOnEntireBL getTotalsOnEntireBL() {
	        return tsTotalsOnEntireBL;
	    }
	    
	    public void setDetailsOnDocument(TsDetailsOnDocument cg) {	    	
	    	tsDetailsOnDocument = cg;
	    }	    
	    public TsDetailsOnDocument getDetailsOnDocument() {
	        return tsDetailsOnDocument;
	    }	    
    	
	    public void setPayment(TsOneElement ts) {	    	
	    	tsPayment = ts;
	    }	    
	    public TsOneElement getPayment() {
	        return tsPayment;
	    }
	    public void setPaymentPlace(TsPlace ts) {	    	
	    	tsPaymentPlace = ts;
	    }	    
	    public TsPlace getPaymentPlace() {
	        return tsPaymentPlace;
	    }
	    
	    public void setBLIssue(TsOneElement ts) {	    	
	    	tsBLIssue = ts;
	    }	    
	    public TsOneElement getBLIssue() {
	        return tsBLIssue;
	    }
	    public void setBLIssuePlace(TsPlace ts) {	    	
	    	tsBLIssuePlace = ts;
	    }	    
	    public TsPlace getBLIssuePlace() {
	        return tsBLIssuePlace;
	    }
	    
	    public void setTermsOfTransportList(List<TermsOfTransportFSS> list) {	    	
	    	termsOfTransportList = list;
	    }	    
	    public List<TermsOfTransportFSS>  getTermsOfTransportList() {
	        return termsOfTransportList;
	    }
	    public void addTermsOfTransportList(TermsOfTransportFSS ts) {	
	    	if (termsOfTransportList == null) {
	    		termsOfTransportList = new ArrayList<TermsOfTransportFSS>();	
	    	}
	    	termsOfTransportList.add(ts);
	    }
	    
	    public void setForwarderReference(TsReference ts) {	    	
	    	tsForwarderReference = ts;
	    }	    
	    public TsReference getForwarderReference() {
	        return tsForwarderReference;
	    }
	    
	    public void setAdditionatReferenceList(List<TsReference> list) {	    	
	    	additionatReferenceList = list;
	    }	    
	    public List<TsReference>  getAdditionatReferenceList() {
	        return additionatReferenceList;
	    }
	    public void addAdditionatReferenceList(TsReference ts) {	
	    	if (additionatReferenceList == null) {
	    		additionatReferenceList = new ArrayList<TsReference>();	
	    	}
	    	additionatReferenceList.add(ts);
	    }
	    
	    public void setPaymentInstructionsList(List<PaymentInstructionsFSS> list) {	    	
	    	paymentInstructionsList = list;
	    }	    
	    public List<PaymentInstructionsFSS>  getPaymentInstructionsList() {
	        return paymentInstructionsList;
	    }
	    public void addPaymentInstructionsList(PaymentInstructionsFSS ts) {	
	    	if (paymentInstructionsList == null) {
	    		paymentInstructionsList = new ArrayList<PaymentInstructionsFSS>();	
	    	}
	    	paymentInstructionsList.add(ts);
	    }
	    	   
	    public void setFreightAndChargeList(List<FreightAndChargeFSS> list) {	    	
	    	freightAndChargeFSSList = list;
	    }	    
	    public List<FreightAndChargeFSS>  getFreightAndChargeList() {
	        return freightAndChargeFSSList;
	    }
	    public void addFreightAndChargeList(FreightAndChargeFSS ts) {	
	    	if (freightAndChargeFSSList == null) {
	    		freightAndChargeFSSList = new ArrayList<FreightAndChargeFSS>();	
	    	}
	    	freightAndChargeFSSList.add(ts);
	    }
	  
	    public void setCarriageFSS(CarriageFSS list) {	    	
	    	carriageFSS = list;
	    }	    
	    public CarriageFSS  getCarriageFSS() {
	        return carriageFSS;
	    }	     
		
	    public void setGoodsItemList(List<GoodsItemFSS> list) {	    	
	    	goodsItemList = list;
	    }	    
	    public List<GoodsItemFSS>  getGoodsItemList() {
	        return goodsItemList;
	    }
	    public void addGoodsItemList(GoodsItemFSS ts) {	
	    	if (goodsItemList == null) {
	    		goodsItemList = new ArrayList<GoodsItemFSS>();	
	    	}
	    	goodsItemList.add(ts);
	    }
	    
	    public void setEquipmentContainerList(List<EquipmentFSS> list) {	    	
	    	eqContainerList = list;
	    }	    
	    public List<EquipmentFSS>  getEquipmentContainerList() {
	        return eqContainerList;
	    }
	    public void addEquipmentContainerList(EquipmentFSS ts) {	
	    	if (eqContainerList == null) {
	    		eqContainerList = new ArrayList<EquipmentFSS>();	
	    	}
	    	eqContainerList.add(ts);
	    }
	    
		public void setEquipmentTrailerList(List<EquipmentFSS> list) {	    	
			eqTrailerList = list;
		}	    
		public List<EquipmentFSS>  getEquipmentTrailerList() {
		        return eqTrailerList;
		}
		public void addEquipmenTrailertList(EquipmentFSS ts) {	
		    	if (eqTrailerList == null) {
		    		eqTrailerList = new ArrayList<EquipmentFSS>();	
		    	}
		    	eqTrailerList.add(ts);
		}
		    
	    public void setShipper(PartyFSS party) {	    	
	    	shipper = party;
	    }	    
	    public PartyFSS getShipper() {
	        return shipper;
	    }	
	
	    public void setConsignee(PartyFSS party) {	    	
	    	consignee = party;
	    }	    
	    public PartyFSS getConsignee() {
	        return consignee;
	    }	
	    
	    public void setNotify(PartyFSS party) {	    	
	    	notify = party;
	    }	    
	    public PartyFSS getNotify() {
	        return notify;
	    }	
	    
	    public void setBookingAgent(PartyFSS party) {	    	
	    	bookingAgent = party;
	    }	    
	    public PartyFSS getBookingAgent() {
	        return bookingAgent;
	    }	
	    
	    public void setOriginalShipper(PartyFSS party) {	    	
	    	originalShipper = party;
	    }	    
	    public PartyFSS getOriginalShipper() {
	        return bookingAgent;
	    }	
	    
	    public void setSecondNotify(PartyFSS party) {	    	
	    	secondNotify = party;
	    }	    
	    public PartyFSS getSecondNotify() {
	        return secondNotify;
	    }	
	    
	    public void setOrderOfShipper(PartyFSS party) {	    	
	    	orderOfShipper = party;
	    }	    
	    public PartyFSS getOrderOfShipper() {
	        return orderOfShipper;
	    }	
	    
	    public void setDeconsolidator(PartyFSS party) {	    	
	    	deconsolidator = party;
	    }	    
	    public PartyFSS getDeconsolidator() {
	        return deconsolidator;
	    }	
	    
	    public void setCarrier(PartyFSS party) {	    	
	    	carrier = party;
	    }	    
	    public PartyFSS getCarrier() {
	        return carrier;
	    }	
	    
	    public void setFreightForwarder(PartyFSS party) {	    	
	    	freightForwarder = party;
	    }	    
	    public PartyFSS getFreightForwarder() {
	        return freightForwarder;
	    }	
	    
	    public void setFreightForwarderRequestorBranch(PartyFSS party) {	    	
	    	freightForwarderRequestorBranch = party;
	    }	    
	    public PartyFSS getFreightForwarderRequestorBranch() {
	        return freightForwarderRequestorBranch;
	    }	
	
	    public void setVersion(TsOneElement ts) {	    	
	    	tsVersion = ts;
	    }	    
	    public TsOneElement getVersion() {
	        return tsVersion;
	    }
	    
///////////////////
		
		 
		
		 private String carrigeBilden(CarriageFSS carrige) throws FssException {
			String ret = "";
				
			if (carrige == null) {
				return ret;
			}
			if (carrige.getMainCarriage() != null) {						
				if (carrige.getMainCarriage().getTsCarriage() != null) {	 
					ret = appendString(ret, carrige.getMainCarriage().getTsCarriage().teilsatzBilden());	
				}
				if (carrige.getMainCarriage().getMeansOfTransport() != null) {	 
					ret = appendString(ret, carrige.getMainCarriage().getMeansOfTransport().teilsatzBilden());	
				}
				if (carrige.getMainCarriage().getPlaceList() != null)	{				
					for (TsPlace place : carrige.getMainCarriage().getPlaceList()) {
						if (place != null) {
							ret = appendString(ret, place.teilsatzBilden());	
						}					
					}
				}
			}
			if (carrige.getPreCarriageList() != null) {			
				for (CarriageDetailsFSS pre : carrige.getPreCarriageList()) {
					if (pre != null) {
						if (pre.getTsCarriage() != null) {	 
							ret = appendString(ret, pre.getTsCarriage().teilsatzBilden());	
						}
						if (pre.getMeansOfTransport() != null) {	 
							ret = appendString(ret, pre.getMeansOfTransport().teilsatzBilden());	
						}
					}
				}
			}
			if (carrige.getOnCarriageList() != null) {
				for (CarriageDetailsFSS on : carrige.getOnCarriageList()) {
					if (on != null) {
						if (on.getTsCarriage() != null) {	 
							ret = appendString(ret, on.getTsCarriage().teilsatzBilden());	
						}
						if (on.getMeansOfTransport() != null) {	 
							ret = appendString(ret, on.getMeansOfTransport().teilsatzBilden());	
						}
					}
				}	
			}	
			return ret;
		}
		 
		 
		 
		
		 
	    
///////////////	 
	
	private String paymentInstructionsBilden(PaymentInstructionsFSS piFSS) throws FssException {	 
		 String ret = "";
		 
		if (piFSS == null) {	
			return ret;
		}
		if (piFSS.getPaymentInstructions() != null) {
				ret = appendString(ret, piFSS.getPaymentInstructions().teilsatzBilden());	
		}
		if (piFSS.getPaymentReferenceList() != null) {
			for (TsReference tsRef : piFSS.getPaymentReferenceList()) {
				if (tsRef != null) {
					ret = appendString(ret, tsRef.teilsatzBilden());	
				}
			}
		}
		if (piFSS.getCurrencyFSS() != null) {
			if (piFSS.getCurrencyFSS().getCurrency() != null) {
				ret = appendString(ret, piFSS.getCurrencyFSS().getCurrency().teilsatzBilden());
			}
			if (piFSS.getCurrencyFSS().getCurrencyDetailsList() != null) {
				for (TsCurrencyDetails tsCD : piFSS.getCurrencyFSS().getCurrencyDetailsList()) {
					if (tsCD != null) {
						ret = appendString(ret, tsCD.teilsatzBilden());
					}
				}
			}
		}
		if (piFSS.getPlaceList() != null) {
			for (TsPlace tsPl : piFSS.getPlaceList()) {
				if (tsPl != null) {
					ret = appendString(ret, tsPl.teilsatzBilden());	
				}
			}
		}
		if (piFSS.getMonetaryAmountList() != null) {
			for (TsMonetaryAmount tsMA : piFSS.getMonetaryAmountList()) {
				if (tsMA != null) {
					ret = appendString(ret, tsMA.teilsatzBilden());	
				}
			}
		}
			
		return ret;
	}
	 
	private String freightAndChargeBilden(FreightAndChargeFSS facFSS) throws FssException {	 
		 String ret = "";
		 
		if (facFSS == null) {	
			return ret;
		}
		if (facFSS.getFreightAndCharge() != null) {
				ret = appendString(ret, facFSS.getFreightAndCharge().teilsatzBilden());	
		}
		if (facFSS.getPlace() != null) {			
			ret = appendString(ret, facFSS.getPlace().teilsatzBilden());				
		}
		if (facFSS.getCurrencyFSS() != null) {
			if (facFSS.getCurrencyFSS().getCurrency() != null) {
				ret = appendString(ret, facFSS.getCurrencyFSS().getCurrency().teilsatzBilden());
			}
			if (facFSS.getCurrencyFSS().getCurrencyDetailsList() != null) {
				for (TsCurrencyDetails tsCD : facFSS.getCurrencyFSS().getCurrencyDetailsList()) {
					if (tsCD != null) {
						ret = appendString(ret, tsCD.teilsatzBilden());
					}
				}
			}
		}
		
		if (facFSS.getMonetaryAmountList() != null) {
			for (TsMonetaryAmount tsMA : facFSS.getMonetaryAmountList()) {
				if (tsMA != null) {
					ret = appendString(ret, tsMA.teilsatzBilden());	
				}
			}
		}
			
		return ret;
	}
	 
	private String currencyBilden(CurrencyFSS currFSS) throws FssException {	 
		 String ret = "";
		 
		if (currFSS == null) {	
			return ret;
		}
		if (currFSS.getCurrency() != null) {
			ret = appendString(ret, currFSS.getCurrency().teilsatzBilden());	
		}
		if (currFSS.getCurrencyDetailsList() != null) {
			for (TsCurrencyDetails tsCD : currFSS.getCurrencyDetailsList()) {
				if (tsCD != null) {
					ret = appendString(ret, tsCD.teilsatzBilden());	
				}
			}
		}
		
		return ret;
	}
	
	private String partyBilden(PartyFSS party) throws FssException {
		String ret = "";
				
				if (party == null) {
					return ret;
				}
				if (party.getTsPatry() != null) {	 
					ret = appendString(ret, party.getTsPatry().teilsatzBilden());	
				}
				
				if (party.getAddressRows() != null) {	 
					ret = appendString(ret, party.getAddressRows().teilsatzBilden());	
				} else if (party.getAddress() != null) {	 
					ret = appendString(ret, party.getAddress().teilsatzBilden());	
				}
				
				if (party.getContact() != null) {	 
					ret = appendString(ret, party.getContact().teilsatzBilden());	
				}
				if (party.getCommmunicationContactList() != null) {	
					for (TsCommunicationContact tsCC : party.getCommmunicationContactList()) {
						if (tsCC != null) {
							ret = appendString(ret, tsCC.teilsatzBilden());	
						}
					}
				}
							
				return ret;
	}		 
	    
	private String itemBilden(GoodsItemFSS item) throws FssException {
		String ret = "";
			
			if (item == null) {
				return ret;
			}
			if (item.getFirstPackingLevel() == null) {
				return ret;
			}	
			PackingFirstLevelFSS firstLevel = item.getFirstPackingLevel();
			if (firstLevel.getPackingDetailsFSS() != null) {
				if (firstLevel.getPackingDetailsFSS().getTsPackingLevel() != null) {	 
					ret = appendString(ret, firstLevel.getPackingDetailsFSS().getTsPackingLevel().teilsatzBilden());	
				}
				if (firstLevel.getPackingDetailsFSS().getDetailslevel() != null) {
					ret = appendString(ret, firstLevel.getPackingDetailsFSS().getDetailslevel().teilsatzBilden());
				}
				if (firstLevel.getPackingDetailsFSS().getOriginalShipper() != null) {
					firstLevel.getPackingDetailsFSS().getOriginalShipper().setContact(null);
					firstLevel.getPackingDetailsFSS().getOriginalShipper().setCommmunicationContactList(null);
					ret = ret + this.partyBilden(firstLevel.getPackingDetailsFSS().getOriginalShipper());
				}
				if (firstLevel.getPackingDetailsFSS().getUltimateConsignee() != null) {
					firstLevel.getPackingDetailsFSS().getUltimateConsignee().setContact(null);
					firstLevel.getPackingDetailsFSS().getUltimateConsignee().setCommmunicationContactList(null);
					ret = ret + this.partyBilden(firstLevel.getPackingDetailsFSS().getUltimateConsignee());
				}
				if (firstLevel.getPackingDetailsFSS().getMarksAndNumbers() != null) {				
				ret = appendString(ret, firstLevel.getPackingDetailsFSS().getMarksAndNumbers().teilsatzBilden());				
				}			
				if (firstLevel.getPackingDetailsFSS().getTextOnItem() != null) {
					if (firstLevel.getPackingDetailsFSS().getTextOnItem().getGoodsDescriptionList() != null) {
					for (TsTextOnBL text : firstLevel.getPackingDetailsFSS().getTextOnItem().getGoodsDescriptionList()) {
						ret = appendString(ret, text.teilsatzBilden());
					}
					}			
					if (firstLevel.getPackingDetailsFSS().getTextOnItem().getGoodsDescriptionNoPrintList() != null) {
					for (TsTextOnBL text : firstLevel.getPackingDetailsFSS().getTextOnItem().getGoodsDescriptionNoPrintList()) {
						ret = appendString(ret, text.teilsatzBilden());
					}
					}
					if (firstLevel.getPackingDetailsFSS().getTextOnItem().getRemarksBeforeGoodsDescriptionList() != null) {
					for (TsTextOnBL text : firstLevel.getPackingDetailsFSS().getTextOnItem().getRemarksBeforeGoodsDescriptionList()) {
						ret = appendString(ret, text.teilsatzBilden());
					}
					}
					if (firstLevel.getPackingDetailsFSS().getTextOnItem().getRemarksAfterGoodsDescriptionList() != null) {
					for (TsTextOnBL text : firstLevel.getPackingDetailsFSS().getTextOnItem().getRemarksAfterGoodsDescriptionList()) {
						ret = appendString(ret, text.teilsatzBilden());
					}
					}
					if (firstLevel.getPackingDetailsFSS().getTextOnItem().getPackagingInformationList() != null) {
					for (TsTextOnBL text : firstLevel.getPackingDetailsFSS().getTextOnItem().getPackagingInformationList()) {
						ret = appendString(ret, text.teilsatzBilden());
					}
					}					
				}								
			}		
			if (firstLevel.getAllocatedEquipmentList() != null) {                     					 			
				for (TsAllocatedEquipment container : firstLevel.getAllocatedEquipmentList()) {				
					if (container != null) {
						ret = appendString(ret, container.teilsatzBilden());
					}
				}
			}
			if (firstLevel.getDangerousGoodsListList() != null) {                     					 			
				for (DangerousGoodsFSS dgFSS : firstLevel.getDangerousGoodsListList()) {				
					if (dgFSS != null) {
						if (dgFSS.getDGDetails() != null) {
							ret = appendString(ret, dgFSS.getDGDetails().teilsatzBilden());
						}
						if (dgFSS.getTechnicalName() != null) {
							ret = appendString(ret, dgFSS.getTechnicalName().teilsatzBilden());
						}
						if (dgFSS.getDangerousGoodsAdditional() != null) {
							ret = appendString(ret, dgFSS.getDangerousGoodsAdditional().teilsatzBilden());
						}
						if (dgFSS.getContactFSSList() != null) {
							for (ContactFSS contact : dgFSS.getContactFSSList()) {
								if (contact.getContact() != null) {
									ret = appendString(ret, contact.getContact().teilsatzBilden());
								}
								if (contact.getCommunicationContactList() != null) {							
									for (TsCommunicationContact tsCC : contact.getCommunicationContactList()) {
										ret = appendString(ret, tsCC.teilsatzBilden());
									}
								}
							}
						}
					}
				}
			}
			if (item.getSecondPackingLevelListList() != null) {                     					 			
				for (PackingSecondLevelFSS secondLevel : item.getSecondPackingLevelListList()) {				
					if (secondLevel != null && secondLevel.getPackingDetailsFSS() != null) {
						if (secondLevel.getPackingDetailsFSS().getTsPackingLevel() != null) {	 
							ret = appendString(ret, secondLevel.getPackingDetailsFSS().getTsPackingLevel().teilsatzBilden());	
						}
						if (secondLevel.getPackingDetailsFSS().getDetailslevel() != null) {
							ret = appendString(ret, secondLevel.getPackingDetailsFSS().getDetailslevel().teilsatzBilden());
						}
						if (secondLevel.getPackingDetailsFSS().getOriginalShipper() != null) {
							secondLevel.getPackingDetailsFSS().getOriginalShipper().setContact(null);
							secondLevel.getPackingDetailsFSS().getOriginalShipper().setCommmunicationContactList(null);
							ret = ret + this.partyBilden(secondLevel.getPackingDetailsFSS().getOriginalShipper());
						}
						if (secondLevel.getPackingDetailsFSS().getUltimateConsignee() != null) {
							secondLevel.getPackingDetailsFSS().getUltimateConsignee().setContact(null);
							secondLevel.getPackingDetailsFSS().getUltimateConsignee().setCommmunicationContactList(null);
							ret = ret + this.partyBilden(secondLevel.getPackingDetailsFSS().getUltimateConsignee());
						}
						if (secondLevel.getPackingDetailsFSS().getMarksAndNumbers() != null) {				
							ret = appendString(ret, secondLevel.getPackingDetailsFSS().getMarksAndNumbers().teilsatzBilden());				
						}
						if (secondLevel.getPackingDetailsFSS().getTextOnItem() != null) {
							if (secondLevel.getPackingDetailsFSS().getTextOnItem().getGoodsDescriptionList() != null) {
								for (TsTextOnBL text : secondLevel.getPackingDetailsFSS().getTextOnItem().getGoodsDescriptionList()) {
									ret = appendString(ret, text.teilsatzBilden());
								}
							}			
							if (secondLevel.getPackingDetailsFSS().getTextOnItem().getGoodsDescriptionNoPrintList() != null) {
								for (TsTextOnBL text : secondLevel.getPackingDetailsFSS().getTextOnItem().getGoodsDescriptionNoPrintList()) {
									ret = appendString(ret, text.teilsatzBilden());
								}
							}
							if (secondLevel.getPackingDetailsFSS().getTextOnItem().getRemarksBeforeGoodsDescriptionList() != null) {
								for (TsTextOnBL text : secondLevel.getPackingDetailsFSS().getTextOnItem().getRemarksBeforeGoodsDescriptionList()) {
									ret = appendString(ret, text.teilsatzBilden());
								}
							}
							if (secondLevel.getPackingDetailsFSS().getTextOnItem().getRemarksAfterGoodsDescriptionList() != null) {
								for (TsTextOnBL text : secondLevel.getPackingDetailsFSS().getTextOnItem().getRemarksAfterGoodsDescriptionList()) {
									ret = appendString(ret, text.teilsatzBilden());
								}
							}
							if (secondLevel.getPackingDetailsFSS().getTextOnItem().getPackagingInformationList() != null) {
								for (TsTextOnBL text : secondLevel.getPackingDetailsFSS().getTextOnItem().getPackagingInformationList()) {
									ret = appendString(ret, text.teilsatzBilden());
								}
							}	
						}
						if (secondLevel.getThirdPackingLevelList() != null) {
							for (PackingDetailsFSS thirdLevel : secondLevel.getThirdPackingLevelList()) {
								if (thirdLevel != null) {
									if (thirdLevel.getTsPackingLevel() != null) {	 
										ret = appendString(ret, thirdLevel.getTsPackingLevel().teilsatzBilden());	
									}
									if (thirdLevel.getDetailslevel() != null) {
										ret = appendString(ret, thirdLevel.getDetailslevel().teilsatzBilden());
									}
									if (thirdLevel.getOriginalShipper() != null) {
										thirdLevel.getOriginalShipper().setContact(null);
										thirdLevel.getOriginalShipper().setCommmunicationContactList(null);
										ret = ret + this.partyBilden(thirdLevel.getOriginalShipper());
									}
									if (thirdLevel.getUltimateConsignee() != null) {
										thirdLevel.getUltimateConsignee().setContact(null);
										thirdLevel.getUltimateConsignee().setCommmunicationContactList(null);
										ret = ret + this.partyBilden(thirdLevel.getUltimateConsignee());
									}
									if (thirdLevel.getMarksAndNumbers() != null) {				
										ret = appendString(ret, thirdLevel.getMarksAndNumbers().teilsatzBilden());				
									}
									if (thirdLevel.getTextOnItem() != null) {
										if (thirdLevel.getTextOnItem().getGoodsDescriptionList() != null) {
											for (TsTextOnBL text : thirdLevel.getTextOnItem().getGoodsDescriptionList()) {
												ret = appendString(ret, text.teilsatzBilden());
											}
										}			
										if (thirdLevel.getTextOnItem().getGoodsDescriptionNoPrintList() != null) {
											for (TsTextOnBL text : thirdLevel.getTextOnItem().getGoodsDescriptionNoPrintList()) {
												ret = appendString(ret, text.teilsatzBilden());
											}
										}
										if (thirdLevel.getTextOnItem().getRemarksBeforeGoodsDescriptionList() != null) {
											for (TsTextOnBL text : thirdLevel.getTextOnItem().getRemarksBeforeGoodsDescriptionList()) {
												ret = appendString(ret, text.teilsatzBilden());
											}
										}
										if (thirdLevel.getTextOnItem().getRemarksAfterGoodsDescriptionList() != null) {
											for (TsTextOnBL text : thirdLevel.getTextOnItem().getRemarksAfterGoodsDescriptionList()) {
												ret = appendString(ret, text.teilsatzBilden());
											}
										}
										if (thirdLevel.getTextOnItem().getPackagingInformationList() != null) {
											for (TsTextOnBL text : thirdLevel.getTextOnItem().getPackagingInformationList()) {
												ret = appendString(ret, text.teilsatzBilden());
											}
										}	
									}	
								}
							}
							
						}
					}
				}
			}
		
		return ret;
	}
	    
	private String equipmentBilden(EquipmentFSS equipment) throws FssException {
			String ret = "";
			
			if (equipment == null) {
				return ret;
			}				
			if (equipment.getEquipmentDetails() != null) {	 
				ret = appendString(ret, equipment.getEquipmentDetails().teilsatzBilden());	
			}			
			if (equipment.getSealsList() != null) {
				for (TsSeals seals : equipment.getSealsList()) {
					if (seals != null) {
						ret = appendString(ret, seals.teilsatzBilden());	
					}
				}
			}
			if (equipment.getAttachedEquipmentList() != null) {
				for (TsAttachedEquipment tsAttached : equipment.getAttachedEquipmentList()) {
					if (tsAttached != null) {						
						ret = appendString(ret, tsAttached.teilsatzBilden());	
					}
				}
			}
			return ret;
	}

	public String getKdnr() {
		return kdnr;
	}

	public void setKdnr(String kdnr) {
		this.kdnr = Utils.checkNull(kdnr);
	}
}
