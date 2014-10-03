package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import java.util.ArrayList;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Module		: CMP<br>
* Created		: 24.07.2013<br>
* Description	: ReportedStatusConsignment.
* 
* @author iwaniuk
* @version 1.0.00
*/

public class ReportedStatusConsignment extends KCXMessage {
		
	private String grossWeightMeasure;
	private String grossVolumeMeasure;
	private String densityGroupCode;
	private String pieceQuantity;
	private String transportSplitDescription;
	private String discrepancyDescriptionCode;
	private DocumentType associatedManifestDocument;
	private PaymentType applicableLogisticsServiceCharge;	
	private ArrayList<TransportMovementFSU> specifiedLogisticsTransportMovementList;	
	private PartyType notifiedParty;
	private PartyType deliveryParty;
	private PartyType associatedReceivedFromParty;
	private PartyType associatedTransferredFromParty;
	private ArrayList<Description> handlingOSIInstructionsList;
	private ArrayList<CustomsNote> includedCustomsNoteList;	
	private CustomsProcedure associatedConsignmentCustomsProcedure;
	private ArrayList<TransportEquipment> utilizedUnitLoadTransportEquipmentList;
	private ArrayList<HouseConsignment> includedHouseConsignmentList;
	       
    private enum EReportedStatusConsignment {    	
    	GrossWeightMeasure,
    	GrossVolumeMeasure,
    	DensityGroupCode,
    	PieceQuantity,
    	TransportSplitDescription,
    	DiscrepancyDescriptionCode,
    	AssociatedManifestDocument,
    	ApplicableLogisticsServiceCharge,
    	SpecifiedLogisticsTransportMovement,
    	NotifiedParty,
    	DeliveryParty,
    	AssociatedReceivedFromParty,
    	AssociatedTransferredFromParty,
    	HandlingOSIInstructions,
    	IncludedCustomsNote,
    	AssociatedConsignmentCustomsProcedure,
    	UtilizedUnitLoadTransportEquipment,
    	IncludedHouseConsignment,
    }

    public ReportedStatusConsignment() {
	      	super();	       
    }
    
    public ReportedStatusConsignment(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((EReportedStatusConsignment) tag) {
    			case AssociatedManifestDocument:
    				associatedManifestDocument = new DocumentType(getScanner());
    				associatedManifestDocument.parse(tag.name());        				
    				break;
    				
    			case ApplicableLogisticsServiceCharge:
    				applicableLogisticsServiceCharge = new PaymentType(getScanner());
    				applicableLogisticsServiceCharge.parse(tag.name());        				
    				break;
    				
    			case SpecifiedLogisticsTransportMovement:
    				TransportMovementFSU fsu = new TransportMovementFSU(getScanner());
					fsu.parse(tag.name());    
					addSpecifiedLogisticsTransportMovementList(fsu);
					break;
					
    			case NotifiedParty:
    				notifiedParty = new PartyType(getScanner());
    				notifiedParty.parse(tag.name());    					
					break;
					
    			case DeliveryParty:
    				deliveryParty = new PartyType(getScanner());
    				deliveryParty.parse(tag.name());        				
					break;
				
    			case AssociatedReceivedFromParty:
    				associatedReceivedFromParty = new PartyType(getScanner());
    				associatedReceivedFromParty.parse(tag.name());        				
					break;
					
    			case AssociatedTransferredFromParty:
    				associatedTransferredFromParty = new PartyType(getScanner());
    				associatedTransferredFromParty.parse(tag.name());        				
					break;
				
    			case HandlingOSIInstructions:
    				Description osi = new Description(getScanner());
					osi.parse(tag.name());    
					addHandlingOSIInstructionsList(osi);
					break;
					
    			case IncludedCustomsNote:
    				CustomsNote note = new CustomsNote(getScanner());
    				note.parse(tag.name());        
    				addIncludedCustomsNoteList(note);
    				break;
				
    			case AssociatedConsignmentCustomsProcedure:
    				associatedConsignmentCustomsProcedure = new CustomsProcedure(getScanner());
    				associatedConsignmentCustomsProcedure.parse(tag.name());        				
    				break;
    				
    			case UtilizedUnitLoadTransportEquipment:
    				TransportEquipment load = new TransportEquipment(getScanner());
    				load.parse(tag.name());    
    				addUtilizedUnitLoadTransportEquipmentList(load);
					break;
					
    			case IncludedHouseConsignment:
    				HouseConsignment house = new HouseConsignment(getScanner());
    				house.parse(tag.name());    
					addIncludedHouseConsignmentList(house);
					break;
					
    			
    			default:
    					return;
    			}
    		} else {

    			switch ((EReportedStatusConsignment) tag) {
    				
    				case GrossWeightMeasure :
    					setGrossWeightMeasure(value);
    					break;    					
    					
    				case GrossVolumeMeasure :
    					setGrossVolumeMeasure(value);
    					break;
    					
    				case DensityGroupCode :
    					setDensityGroupCode(value);
    					break;
    					
    				case PieceQuantity :
    					setPieceQuantity(value);
    					break;
    					
    				case TransportSplitDescription :
    					setTransportSplitDescription(value);
    					break;
    					
    				case DiscrepancyDescriptionCode :
    					setDiscrepancyDescriptionCode(value);
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
    			return EReportedStatusConsignment.valueOf(token);
    		} catch (IllegalArgumentException e) {
    			return null;
    		}
    }
	    	
		
	public String getGrossWeightMeasure() {
		return grossWeightMeasure;
	}
	public void setGrossWeightMeasure(String value) {
		this.grossWeightMeasure = value;
	}

	public String getGrossVolumeMeasure() {
		return grossVolumeMeasure;
	}
	public void setGrossVolumeMeasure(String value) {
		this.grossVolumeMeasure = value;
	}

	public String getDensityGroupCode() {
		return densityGroupCode;
	}
	public void setDensityGroupCode(String value) {
		this.densityGroupCode = value;
	}

	public String getPieceQuantity() {
		return pieceQuantity;
	}

	public void setPieceQuantity(String value) {
		this.pieceQuantity = value;
	}
	public String getTransportSplitDescription() {
		return transportSplitDescription;
	}
	
	public void setTransportSplitDescription(String value) {
		this.transportSplitDescription = value;
	}

	public String getDiscrepancyDescriptionCode() {
		return discrepancyDescriptionCode;
	}
	public void setDiscrepancyDescriptionCode(String value) {
		this.discrepancyDescriptionCode = value;
	}
     
	public DocumentType getAssociatedManifestDocument() {
		return associatedManifestDocument;
	}
	public void setAssociatedManifestDocument(DocumentType document) {
		this.associatedManifestDocument = document;
	}

	public PaymentType getApplicableLogisticsServiceCharge() {
		return applicableLogisticsServiceCharge;
	}
	public void setApplicableLogisticsServiceCharge(PaymentType charge) {
		this.applicableLogisticsServiceCharge = charge;
	}

	public ArrayList<TransportMovementFSU> getSpecifiedLogisticsTransportMovementList() {
		return specifiedLogisticsTransportMovementList;
	}
	public void setSpecifiedLogisticsTransportMovementList(
			ArrayList<TransportMovementFSU> specifiedLogisticsTransportMovementList) {
		this.specifiedLogisticsTransportMovementList = specifiedLogisticsTransportMovementList;
	}
	public void addSpecifiedLogisticsTransportMovementList(TransportMovementFSU fsu) {
		if (specifiedLogisticsTransportMovementList == null) {
			specifiedLogisticsTransportMovementList = new ArrayList<TransportMovementFSU>();
		}
		this.specifiedLogisticsTransportMovementList.add(fsu);
	}
	
	public PartyType getNotifiedParty() {
		return notifiedParty;
	}
	public void setNotifiedParty(PartyType notifiedParty) {
		this.notifiedParty = notifiedParty;
	}

	public PartyType getDeliveryParty() {
		return deliveryParty;
	}
	public void setDeliveryParty(PartyType deliveryParty) {
		this.deliveryParty = deliveryParty;
	}

	public PartyType getAssociatedReceivedFromParty() {
		return associatedReceivedFromParty;
	}
	public void setAssociatedReceivedFromParty(PartyType associatedReceivedFromParty) {
		this.associatedReceivedFromParty = associatedReceivedFromParty;
	}

	public PartyType getAssociatedTransferredFromParty() {
		return associatedTransferredFromParty;
	}
	public void setAssociatedTransferredFromParty(
			PartyType associatedTransferredFromParty) {
		this.associatedTransferredFromParty = associatedTransferredFromParty;
	}

	public ArrayList<Description> getHandlingOSIInstructionsList() {
		return handlingOSIInstructionsList;
	}
	public void setHandlingOSIInstructionsList(
			ArrayList<Description> handlingOSIInstructionsList) {
		this.handlingOSIInstructionsList = handlingOSIInstructionsList;
	}
	public void addHandlingOSIInstructionsList(Description description) {
		if (handlingOSIInstructionsList == null) {
			handlingOSIInstructionsList = new ArrayList<Description>();
		}
		this.handlingOSIInstructionsList.add(description);
	}
	
	public ArrayList<CustomsNote> getIncludedCustomsNoteList() {
		return includedCustomsNoteList;
	}
	public void setIncludedCustomsNoteList(
			ArrayList<CustomsNote> includedCustomsNoteList) {
		this.includedCustomsNoteList = includedCustomsNoteList;
	}
	public void addIncludedCustomsNoteList(CustomsNote note) {
		if (includedCustomsNoteList == null) {
			includedCustomsNoteList = new ArrayList<CustomsNote>();
		}
		this.includedCustomsNoteList.add(note);
	}
	
	public CustomsProcedure getAssociatedConsignmentCustomsProcedure() {
		return associatedConsignmentCustomsProcedure;
	}
	public void setAssociatedConsignmentCustomsProcedure(
			CustomsProcedure associatedConsignmentCustomsProcedure) {
		this.associatedConsignmentCustomsProcedure = associatedConsignmentCustomsProcedure;
	}

	public ArrayList<TransportEquipment> getUtilizedUnitLoadTransportEquipmentList() {
		return utilizedUnitLoadTransportEquipmentList;
	}
	public void setUtilizedUnitLoadTransportEquipmentList(
			ArrayList<TransportEquipment> utilizedUnitLoadTransportEquipmentList) {
		this.utilizedUnitLoadTransportEquipmentList = utilizedUnitLoadTransportEquipmentList;
	}           
	public void addUtilizedUnitLoadTransportEquipmentList(TransportEquipment equipment) {
		if (utilizedUnitLoadTransportEquipmentList == null) {
			utilizedUnitLoadTransportEquipmentList = new ArrayList<TransportEquipment>();
		}
		this.utilizedUnitLoadTransportEquipmentList.add(equipment);
	}
	
	public ArrayList<HouseConsignment> getIncludedHouseConsignmentList() {
		return includedHouseConsignmentList;
	}
	public void setIncludedHouseConsignmentList(
			ArrayList<HouseConsignment> includedHouseConsignmentList) {
		this.includedHouseConsignmentList = includedHouseConsignmentList;
	}

	public void addIncludedHouseConsignmentList(HouseConsignment house) {
		if (includedHouseConsignmentList == null) {
			includedHouseConsignmentList = new ArrayList<HouseConsignment>();
		}
		this.includedHouseConsignmentList.add(house);
	}
	
	public boolean isEmpty() {
		//todo
		return false;
	}

}
