package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import java.util.ArrayList;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;


/**
 * Module		: CMP<br>
 * Created		: 18.07.2013<br>
 * Description	: FWB-MasterConsignment.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class MasterConsignmentFWB extends KCXMessage {
	
	private String 	id;
	private String 	additionalId;
	private String 	freightForwarderAssignedId;
	private String 	associatedReferenceId;
	private String 	nilCarriageValueIndicator;
	private String 	declaredValueForCarriageAmount;
	private String 	nilCustomsValueIndicator;
	private String 	declaredValueForCustomsAmount;
	private String 	nilInsuranceValueIndicator;
	private String 	insuranceValueAmount;
	private String 	totalChargePrepaidIndicator;
	private String 	totalDisbursementPrepaidIndicator;
	private String 	includedTareGrossWeightMeasure;
	private String 	grossVolumeMeasure;
	private String 	densityGroupCode;
	private String 	packageQuantity;
	private String 	totalPieceQuantity;	
	private String 	productId;
	private PartyType consignorParty;
	private PartyType consigneeParty;	
	private PartyType freightForwarderParty;
	private ArrayList<PartyType> associatedPartyList;
	private Location originLocation;
	private Location finalDestinationLocation;	
	private TransportMovementFWB specifiedLogisticsTransportMovement;
	private ArrayList<TransportEquipment> utilizedLogisticsTransportEquipmentList;
	private ArrayList<Description> handlingSPHInstructionsList;
	private ArrayList<Description> handlingSSRInstructionsList;
	private ArrayList<Description> handlingOSIInstructionsList;
	private ArrayList<CustomsNote> includedAccountingNoteList;
	private ArrayList<CustomsNote> includedCustomsNoteList;
	private  ArrayList<DocumentType> associatedReferenceDocumentList;
	private CustomsProcedure associatedConsignmentCustomsProcedure;
	private CurrencyExchange applicableOriginCurrencyExchange;
	private CurrencyExchange applicableDestinationCurrencyExchange;
	private PaymentType applicableLogisticsServiceCharge;	
	private ArrayList<PackageType> applicableLogisticsAllowanceChargeList;
	private ArrayList<RatingType> applicableRatingList;
	private ArrayList<RatingTotalType> applicableTotalRatingList;

	
	private enum EFwbMasterConsignment {
		ID,
		AdditionalID,
		FreightForwarderAssignedID,
		AssociatedReferenceID,
		NilCarriageValueIndicator,
		DeclaredValueForCarriageAmount,
		NilCustomsValueIndicator,
		DeclaredValueForCustomsAmount,
		NilInsuranceValueIndicator,
		InsuranceValueAmount,
		TotalChargePrepaidIndicator,		
		TotalDisbursementPrepaidIndicator,
		IncludedTareGrossWeightMeasure,
		GrossVolumeMeasure,
		DensityGroupCode,
		PackageQuantity,
		TotalPieceQuantity,
		ProductID,
		ConsignorParty,
		ConsigneeParty,
		FreightForwarderParty,
		AssociatedParty,
		OriginLocation,
		FinalDestinationLocation,
		SpecifiedLogisticsTransportMovement,
		UtilizedLogisticsTransportEquipment,
		HandlingSPHInstructions,
		HandlingSSRInstructions,
		HandlingOSIInstructions,
		IncludedAccountingNote,
		IncludedCustomsNote,
		AssociatedReferenceDocument,
		AssociatedConsignmentCustomsProcedure,
		ApplicableOriginCurrencyExchange,
		ApplicableDestinationCurrencyExchange,
		ApplicableLogisticsServiceCharge,
		ApplicableLogisticsAllowanceCharge,
		ApplicableRating,
		ApplicableTotalRating;
	    }        

	    public MasterConsignmentFWB() {
		      	super();	       
	    }
	    
	    public MasterConsignmentFWB(XmlMsgScanner scanner) {
	    	super(scanner);    	
	  	}
	    
	    public void startElement(Enum tag, String value, Attributes attr) {
	    		if (value == null) {
	    			switch ((EFwbMasterConsignment) tag) {
	    			case ConsignorParty:   
	    				consignorParty = new PartyType(getScanner());
	    				consignorParty.parse(tag.name());					
    					break;   
    					
	    			case ConsigneeParty:   
	    				consigneeParty = new PartyType(getScanner());
	    				consigneeParty.parse(tag.name());					
    					break; 
    					
	    			case FreightForwarderParty:   
	    				freightForwarderParty = new PartyType(getScanner());
	    				freightForwarderParty.parse(tag.name());					
    					break; 
    					
	    			case AssociatedParty:   
	    				PartyType associatedParty = new PartyType(getScanner());
	    				associatedParty.parse(tag.name());	
	    				addAssociatedPartyList(associatedParty);
    					break; 
    					
	    			case OriginLocation:
    					originLocation = new Location(getScanner());
    					originLocation.parse(tag.name());        				
    					break;
    					
	    			case FinalDestinationLocation:
	    				finalDestinationLocation = new Location(getScanner());
	    				finalDestinationLocation.parse(tag.name());        				
    					break;
    					
	    			case SpecifiedLogisticsTransportMovement:   
	    				specifiedLogisticsTransportMovement = new TransportMovementFWB(getScanner());
	    				specifiedLogisticsTransportMovement.parse(tag.name());					
    					break; 
    					    			
	    			case UtilizedLogisticsTransportEquipment:   
	    				TransportEquipment equipment = new TransportEquipment(getScanner());
	    				equipment.parse(tag.name());	
	    				addUtilizedLogisticsTransportEquipmentList(equipment);
    					break; 
    					
	    			case HandlingSPHInstructions:   
	    				Description sph = new Description(getScanner());
	    				sph.parse(tag.name());	
	    				addHandlingSPHInstructionsList(sph);
    					break; 
    					
	    			case HandlingSSRInstructions:   
	    				Description ssr = new Description(getScanner());
	    				ssr.parse(tag.name());	
	    				addHandlingSSRInstructionsList(ssr);
    					break; 
    					
	    			case HandlingOSIInstructions:   
	    				Description osi = new Description(getScanner());
	    				osi.parse(tag.name());	
	    				addHandlingOSIInstructionsList(osi);
    					break; 
    										
	    			case IncludedAccountingNote:   
	    				CustomsNote acc = new CustomsNote(getScanner());
	    				acc.parse(tag.name());	
	    				addIncludedAccountingNoteList(acc);
    					break; 
    				
	    			case IncludedCustomsNote:
    					CustomsNote note = new CustomsNote(getScanner());
    					note.parse(tag.name());
        				addIncludedCustomsNoteList(note);
    					break;
    					
	    			case AssociatedReferenceDocument:	    				 
	    				DocumentType document = new DocumentType(getScanner());
	    				document.parse(tag.name());		   
	    				this.addAssociatedReferenceDocumentList(document);
    					break;     					
    					    				
    				case AssociatedConsignmentCustomsProcedure:
    					associatedConsignmentCustomsProcedure  = new CustomsProcedure(getScanner());
    					associatedConsignmentCustomsProcedure.parse(tag.name());        				
    					break;
    							
    				case ApplicableOriginCurrencyExchange:
    					applicableOriginCurrencyExchange = new CurrencyExchange(getScanner());
    					applicableOriginCurrencyExchange.parse(tag.name());        				
    					break;
    					
    				case ApplicableDestinationCurrencyExchange:
    					applicableDestinationCurrencyExchange = new CurrencyExchange(getScanner());
    					applicableDestinationCurrencyExchange.parse(tag.name());        				
    					break;
    				
    				case ApplicableLogisticsServiceCharge:
    					applicableLogisticsServiceCharge  = new PaymentType(getScanner());
    					applicableLogisticsServiceCharge.parse(tag.name());        				
    					break;
    					
    				case ApplicableLogisticsAllowanceCharge:
    					PackageType pack = new PackageType(getScanner());
    					pack.parse(tag.name());
        				addApplicableLogisticsAllowanceChargeList(pack);
    					break;
    					
    				case ApplicableRating:
    					RatingType rating = new RatingType(getScanner());
    					rating.parse(tag.name());
        				addApplicableRatingList(rating);
    					break;
    					
    				case ApplicableTotalRating:
    					RatingTotalType item = new RatingTotalType(getScanner());
    					item.parse(tag.name());
        				addApplicableTotalRatingList(item);
    					break;
    					
    				default:
	    					return;
	    			}
	    		} else {

	    			switch ((EFwbMasterConsignment) tag) {
	    				case ID:
	    					setId(value);    					
	    					break; 
	    					
	    				case AdditionalID:
	    					setAdditionalId(value);    					
	    					break; 
	    					
	    				case FreightForwarderAssignedID:
	    					setFreightForwarderAssignedId(value);    					
	    					break; 
	    					
	    				case AssociatedReferenceID:
	    					setAssociatedReferenceId(value);    					
	    					break; 
	    					
	    				case NilCarriageValueIndicator:
	    					setNilCarriageValueIndicator(value);    					
	    					break; 
	    					
	    				case DeclaredValueForCarriageAmount:
	    					setDeclaredValueForCarriageAmount(value);    					
	    					break; 
	    					
	    				case NilCustomsValueIndicator:
	    					setNilCustomsValueIndicator(value);    					
	    					break; 
	    						    							    				
	    				case  DeclaredValueForCustomsAmount:
	    					setDeclaredValueForCustomsAmount(value);    					
	    					break; 
	    					
	    				case  NilInsuranceValueIndicator:
	    					setNilInsuranceValueIndicator(value);    					
	    					break; 
	    					
	    				case  InsuranceValueAmount:
	    					setInsuranceValueAmount(value);    					
	    					break; 
	    					    						
	    				case  TotalChargePrepaidIndicator:
	    					setTotalChargePrepaidIndicator(value);    					
	    					break; 
	    			
	    				case TotalDisbursementPrepaidIndicator:
	    					setTotalDisbursementPrepaidIndicator(value);    					
	    					break; 
	    				
	    				case IncludedTareGrossWeightMeasure:   
	    					setIncludedTareGrossWeightMeasure(value);    					
	    					break; 
	    					
	    				case GrossVolumeMeasure:   
	    					setGrossVolumeMeasure(value);    					
	    					break;   
	    					
	    				case DensityGroupCode:   
	    					setDensityGroupCode(value);    					
	    					break;   
	    					
	    				case PackageQuantity:   
	    					setPackageQuantity(value);    					
	    					break; 
	    					
	    				case TotalPieceQuantity:   
	    					setTotalPieceQuantity(value);    					
	    					break; 
	    					
	    				case ProductID:   
	    					setProductId(value);    					
	    					break;   
	    				
	    				default:
	    					break;
	    			}
	    		}
	    	}

	    	public void stoppElement(Enum tag) {
	    	}


	    	public Enum translate(String token) {
	    		try {
	    			return EFwbMasterConsignment.valueOf(token);
	    		} catch (IllegalArgumentException e) {
	    			return null;
	    		}
	    	}	      			
		
		    public String getId() {
				return id;
			}

			public void setId(String id) {
				this.id = id;
			}

			public String getAdditionalId() {
				return additionalId;
			}

			public void setAdditionalId(String additionalId) {
				this.additionalId = additionalId;
			}

			public String getFreightForwarderAssignedId() {
				return freightForwarderAssignedId;
			}

			public void setFreightForwarderAssignedId(String freightForwarderAssignedId) {
				this.freightForwarderAssignedId = freightForwarderAssignedId;
			}

			public String getAssociatedReferenceId() {
				return associatedReferenceId;
			}

			public void setAssociatedReferenceId(String associatedReferenceId) {
				this.associatedReferenceId = associatedReferenceId;
			}

			public String getNilCarriageValueIndicator() {
				return nilCarriageValueIndicator;
			}

			public void setNilCarriageValueIndicator(String nilCarriageValueIndicator) {
				this.nilCarriageValueIndicator = nilCarriageValueIndicator;
			}

			public String getDeclaredValueForCarriageAmount() {
				return declaredValueForCarriageAmount;
			}

			public void setDeclaredValueForCarriageAmount(
					String declaredValueForCarriageAmount) {
				this.declaredValueForCarriageAmount = declaredValueForCarriageAmount;
			}

			public String getNilCustomsValueIndicator() {
				return nilCustomsValueIndicator;
			}

			public void setNilCustomsValueIndicator(String nilCustomsValueIndicator) {
				this.nilCustomsValueIndicator = nilCustomsValueIndicator;
			}

			public String getDeclaredValueForCustomsAmount() {
				return declaredValueForCustomsAmount;
			}

			public void setDeclaredValueForCustomsAmount(
					String declaredValueForCustomsAmount) {
				this.declaredValueForCustomsAmount = declaredValueForCustomsAmount;
			}

			public String getNilInsuranceValueIndicator() {
				return nilInsuranceValueIndicator;
			}

			public void setNilInsuranceValueIndicator(String nilInsuranceValueIndicator) {
				this.nilInsuranceValueIndicator = nilInsuranceValueIndicator;
			}

			public String getInsuranceValueAmount() {
				return insuranceValueAmount;
			}

			public void setInsuranceValueAmount(String insuranceValueAmount) {
				this.insuranceValueAmount = insuranceValueAmount;
			}

			public String getTotalChargePrepaidIndicator() {
				return totalChargePrepaidIndicator;
			}

			public void setTotalChargePrepaidIndicator(String totalChargePrepaidIndicator) {
				this.totalChargePrepaidIndicator = totalChargePrepaidIndicator;
			}

			public String getTotalDisbursementPrepaidIndicator() {
				return totalDisbursementPrepaidIndicator;
			}

			public void setTotalDisbursementPrepaidIndicator(
					String totalDisbursementPrepaidIndicator) {
				this.totalDisbursementPrepaidIndicator = totalDisbursementPrepaidIndicator;
			}

			public PartyType getConsignorParty() {
				return consignorParty;
			}

			public void setConsignorParty(PartyType consignorParty) {
				this.consignorParty = consignorParty;
			}

			public PartyType getConsigneeParty() {
				return consigneeParty;
			}

			public void setConsigneeParty(PartyType consigneeParty) {
				this.consigneeParty = consigneeParty;
			}

			public PartyType getFreightForwarderParty() {
				return freightForwarderParty;
			}

			public void setFreightForwarderParty(PartyType freightForwarderParty) {
				this.freightForwarderParty = freightForwarderParty;
			}

			public ArrayList<PartyType> getAssociatedPartyList() {
				return associatedPartyList;
			}
			public void setAssociatedPartyList(ArrayList<PartyType> list) {
				this.associatedPartyList = list;
			}
			public void addAssociatedPartyList(PartyType argument) {				
				if (associatedPartyList == null) {
					associatedPartyList = new ArrayList<PartyType>();
				}
				this.associatedPartyList.add(argument);
			}

			public TransportMovementFWB getSpecifiedLogisticsTransportMovement() {
				return specifiedLogisticsTransportMovement;
			}

			public void setSpecifiedLogisticsTransportMovement(
					TransportMovementFWB specifiedLogisticsTransportMovement) {
				this.specifiedLogisticsTransportMovement = specifiedLogisticsTransportMovement;
			}

			public ArrayList<TransportEquipment> getUtilizedLogisticsTransportEquipmentList() {
				return utilizedLogisticsTransportEquipmentList;
			}
			public void setUtilizedLogisticsTransportEquipmentList(ArrayList<TransportEquipment> list) {
				this.utilizedLogisticsTransportEquipmentList = list;
			}
			public void addUtilizedLogisticsTransportEquipmentList(TransportEquipment argument) {				
				if (utilizedLogisticsTransportEquipmentList == null) {
					utilizedLogisticsTransportEquipmentList = new ArrayList<TransportEquipment>();
				}
				this.utilizedLogisticsTransportEquipmentList.add(argument);
			}
			
			public ArrayList<CustomsNote> getIncludedAccountingNoteList() {
				return includedAccountingNoteList;
			}
			public void setIncludedAccountingNoteList(ArrayList<CustomsNote> list) {
				this.includedAccountingNoteList = list;
			}
			public void addIncludedAccountingNoteList(CustomsNote argument) {				
				if (includedAccountingNoteList == null) {
					includedAccountingNoteList = new ArrayList<CustomsNote>();
				}
				this.includedAccountingNoteList.add(argument);
			}
			
			public ArrayList<DocumentType> getAssociatedReferenceDocumentList() {
				return associatedReferenceDocumentList;
			}

			public void setAssociatedReferenceDocumentList(ArrayList<DocumentType> list) {
				this.associatedReferenceDocumentList = list;
			}
			public void addAssociatedReferenceDocumentList(DocumentType argument) {				
				if (associatedReferenceDocumentList == null) {
					associatedReferenceDocumentList = new ArrayList<DocumentType>();
				}
				this.associatedReferenceDocumentList.add(argument);
			}

			public CurrencyExchange getApplicableOriginCurrencyExchange() {
				return applicableOriginCurrencyExchange;
			}

			public void setApplicableOriginCurrencyExchange(
					CurrencyExchange applicableOriginCurrencyExchange) {
				this.applicableOriginCurrencyExchange = applicableOriginCurrencyExchange;
			}

			public CurrencyExchange getApplicableDestinationCurrencyExchange() {
				return applicableDestinationCurrencyExchange;
			}

			public void setApplicableDestinationCurrencyExchange(
					CurrencyExchange applicableDestinationCurrencyExchange) {
				this.applicableDestinationCurrencyExchange = applicableDestinationCurrencyExchange;
			}

			public PaymentType getApplicableLogisticsServiceCharge() {
				return applicableLogisticsServiceCharge;
			}

			public void setApplicableLogisticsServiceCharge(
					PaymentType applicableLogisticsServiceCharge) {
				this.applicableLogisticsServiceCharge = applicableLogisticsServiceCharge;
			}

			public ArrayList<PackageType> getApplicableLogisticsAllowanceChargeList() {
				return applicableLogisticsAllowanceChargeList;
			}
			public void setApplicableLogisticsAllowanceChargeList(ArrayList<PackageType> list) {
				this.applicableLogisticsAllowanceChargeList = list;
			}
			public void addApplicableLogisticsAllowanceChargeList(PackageType argument) {				
				if (applicableLogisticsAllowanceChargeList == null) {
					applicableLogisticsAllowanceChargeList = new ArrayList<PackageType>();
				}
				this.applicableLogisticsAllowanceChargeList.add(argument);
			}
			
			public ArrayList<RatingType> getApplicableRatingList() {
				return applicableRatingList;
			}
			public void setApplicableRatingList(ArrayList<RatingType> applicableRatingList) {
				this.applicableRatingList = applicableRatingList;
			}
			public void addApplicableRatingList(RatingType argument) {
				if (applicableRatingList == null) {
					applicableRatingList = new ArrayList<RatingType>();
				}
				this.applicableRatingList.add(argument);
			}
			
			public ArrayList<RatingTotalType> getApplicableTotalRatingList() {
				return applicableTotalRatingList;
			}
			public void setApplicableTotalRatingList(ArrayList<RatingTotalType> list) {
				this.applicableTotalRatingList = list;
			}
			public void addApplicableTotalRatingList(RatingTotalType argument) {				
				if (applicableTotalRatingList == null) {
					applicableTotalRatingList = new ArrayList<RatingTotalType>();
				}
				this.applicableTotalRatingList.add(argument);
			}
			
			public String getIncludedTareGrossWeightMeasure() {
				return includedTareGrossWeightMeasure;
			}

			public void setIncludedTareGrossWeightMeasure(String weightMeasure) {
				this.includedTareGrossWeightMeasure = weightMeasure;
			}

			public String getGrossVolumeMeasure() {
				return grossVolumeMeasure;
			}

			public void setGrossVolumeMeasure(String grossVolumeMeasure) {
				this.grossVolumeMeasure = grossVolumeMeasure;
			}

			public String getDensityGroupCode() {
				return densityGroupCode;
			}

			public void setDensityGroupCode(String densityGroupCode) {
				this.densityGroupCode = densityGroupCode;
			}

			public String getPackageQuantity() {
				return packageQuantity;
			}

			public void setPackageQuantity(String packageQuantity) {
				this.packageQuantity = packageQuantity;
			}

			public String getTotalPieceQuantity() {
				return totalPieceQuantity;
			}

			public void setTotalPieceQuantity(String totalPieceQuantity) {
				this.totalPieceQuantity = totalPieceQuantity;
			}

			public String getProductId() {
				return productId;
			}
			public void setProductId(String value) {
				this.productId = value;
			}
		

			public Location getOriginLocation() {
				return originLocation;
			}

			public void setOriginLocation(Location originLocation) {
				this.originLocation = originLocation;
			}

			public Location getFinalDestinationLocation() {
				return finalDestinationLocation;
			}

			public void setFinalDestinationLocation(Location finalDestinationLocation) {
				this.finalDestinationLocation = finalDestinationLocation;
			}

		public ArrayList<Description> getHandlingSPHInstructionsList() {
				return handlingSPHInstructionsList;
			}
		public void setHandlingSPHInstructionsList(
					ArrayList<Description> handlingSPHInstructionsList) {
				this.handlingSPHInstructionsList = handlingSPHInstructionsList;
			}
		public void addHandlingSPHInstructionsList(Description argument) {
			if (handlingSPHInstructionsList == null) {
				handlingSPHInstructionsList = new ArrayList<Description>();
			}
			this.handlingSPHInstructionsList.add(argument);
		}

		public ArrayList<Description> getHandlingSSRInstructionsList() {
				return handlingSSRInstructionsList;
			}
		public void setHandlingSSRInstructionsList(
					ArrayList<Description> handlingSSRInstructionsList) {
				this.handlingSSRInstructionsList = handlingSSRInstructionsList;
			}		
		public void addHandlingSSRInstructionsList(Description argument) {
			if (handlingSSRInstructionsList == null) {
				handlingSSRInstructionsList = new ArrayList<Description>();
			}
			this.handlingSSRInstructionsList.add(argument);
		}

		public ArrayList<Description> getHandlingOSIInstructionsList() {
				return handlingOSIInstructionsList;
		}
		public void setHandlingOSIInstructionsList(
					ArrayList<Description> handlingOSIInstructionsList) {
				this.handlingOSIInstructionsList = handlingOSIInstructionsList;
			}
		public void addHandlingOSIInstructionsList(Description argument) {
			if (handlingOSIInstructionsList == null) {
				handlingOSIInstructionsList = new ArrayList<Description>();
			}
			this.handlingOSIInstructionsList.add(argument);
		}

		public ArrayList<CustomsNote> getIncludedCustomsNoteList() {
				return includedCustomsNoteList;
			}
		public void setIncludedCustomsNoteList(
					ArrayList<CustomsNote> includedCustomsNoteList) {
				this.includedCustomsNoteList = includedCustomsNoteList;
			}
		public void addIncludedCustomsNoteList(CustomsNote argument) {
			if (includedCustomsNoteList == null) {
				includedCustomsNoteList = new ArrayList<CustomsNote>();
			}
			this.includedCustomsNoteList.add(argument);
		}

		public CustomsProcedure getAssociatedConsignmentCustomsProcedure() {
				return associatedConsignmentCustomsProcedure;
		}

		public void setAssociatedConsignmentCustomsProcedure(CustomsProcedure argument) {
			this.associatedConsignmentCustomsProcedure = argument;
		}

		
		public boolean isEmpty() {
			return false;
			/*
			return Utils.isStringEmpty(includedTareGrossWeightMeasure) && Utils.isStringEmpty(totalPieceQuantity) &&
			Utils.isStringEmpty(id) && Utils.isStringEmpty(additionalId) &&
			//TODO
			Utils.isStringEmpty(totalPieceQuantity) && Utils.isStringEmpty(productId) &&			
			consignorParty == null && consigneeParty == null &&
			//TODO
			finalDestinationLocation == null; 
			*/
		}
}
