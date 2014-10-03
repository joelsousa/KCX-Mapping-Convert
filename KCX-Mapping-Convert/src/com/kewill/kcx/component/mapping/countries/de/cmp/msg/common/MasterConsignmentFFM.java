package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import java.util.ArrayList;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;


/**
 * Module		: CMP<br>
 * Created		: 18.07.2013<br>
 * Description	: FFM-MasterConsignment.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class MasterConsignmentFFM extends KCXMessage {
	
	private String 	grossWeightMeasure;
	private String 	grossVolumeMeasure;
	private String 	densityGroupCode;
	private String 	packageQuantity;
	private String 	totalPieceQuantity;
	private String 	summaryDescription;
	private String 	transportSplitDescription;	
	private String 	movementPriorityCode;
	private DocumentType transportContractDocument;
	private Location originLocation;
	private Location finalDestinationLocation;	
	private ArrayList<Description> handlingSPHInstructionsList;
	private ArrayList<Description> handlingSSRInstructionsList;
	private ArrayList<Description> handlingOSIInstructionsList;
	private ArrayList<CustomsNote> includedCustomsNoteList;
	private CustomsProcedure associatedConsignmentCustomsProcedure;
	private ArrayList<PackageType> transportLogisticsPackageList;
	private ArrayList<TransportCarriage> onCarriageTransportMovementList;
	private ArrayList<ConsignmentItemTypeFFM> includedMasterConsignmentItemList;

	
	private enum EFfmMasterConsignment {
		GrossWeightMeasure,
		GrossVolumeMeasure,
		DensityGroupCode,
		PackageQuantity,
		TotalPieceQuantity,
		SummaryDescription,
		TransportSplitDescription,
		MovementPriorityCode,
		TransportContractDocument,
		OriginLocation,
		FinalDestinationLocation,
		HandlingSPHInstructions,
		HandlingSSRInstructions,
		HandlingOSIInstructions,
		IncludedCustomsNote,
		AssociatedConsignmentCustomsProcedure,
		TransportLogisticsPackage,
		OnCarriageTransportMovement,
		IncludedMasterConsignmentItem;
	    }        

	    public MasterConsignmentFFM() {
		      	super();	       
	    }
	    
	    public MasterConsignmentFFM(XmlMsgScanner scanner) {
	    	super(scanner);    	
	  	}
	    
	    public void startElement(Enum tag, String value, Attributes attr) {
	    		if (value == null) {
	    			switch ((EFfmMasterConsignment) tag) {
	    			case TransportContractDocument:   
	    				transportContractDocument = new DocumentType(getScanner());
	    				transportContractDocument.parse(tag.name());					
    					break; 
    					
    				case OriginLocation:
    					originLocation = new Location(getScanner());
    					originLocation.parse(tag.name());        				
    					break;
    					
    				case FinalDestinationLocation:
    					finalDestinationLocation = new Location(getScanner());
    					finalDestinationLocation.parse(tag.name());        				
    					break;
    					
    				case HandlingSPHInstructions:
    					Description description = new Description(getScanner());
    					description.parse(tag.name());
        				addHandlingSPHInstructionsList(description);
    					break;
    					
    				case HandlingSSRInstructions:
    					Description instruction = new Description(getScanner());
    					instruction.parse(tag.name());
    					addHandlingSSRInstructionsList(instruction);
    					break;
    					
    				case HandlingOSIInstructions:
    					Description handling = new Description(getScanner());
    					handling.parse(tag.name());
    					addHandlingOSIInstructionsList(handling);
    					break;
    					
    				case IncludedCustomsNote:
    					CustomsNote note = new CustomsNote(getScanner());
    					note.parse(tag.name());
        				addIncludedCustomsNoteList(note);
    					break;
    					
    				case AssociatedConsignmentCustomsProcedure:
    					associatedConsignmentCustomsProcedure  = new CustomsProcedure(getScanner());
    					associatedConsignmentCustomsProcedure.parse(tag.name());        				
    					break;
    					
    				case TransportLogisticsPackage:
    					PackageType pack = new PackageType(getScanner());
    					pack.parse(tag.name());
        				addTransportLogisticsPackageList(pack);
    					break;
    					
    				case OnCarriageTransportMovement:
    					TransportCarriage carriage = new TransportCarriage(getScanner());
    					carriage.parse(tag.name());
        				addOnCarriageTransportMovementList(carriage);
    					break;
    					
    				case IncludedMasterConsignmentItem:
    					ConsignmentItemTypeFFM item = new ConsignmentItemTypeFFM(getScanner());
    					item.parse(tag.name());
        				addIncludedMasterConsignmentItemList(item);
    					break;
    					
    				default:
	    					return;
	    			}
	    		} else {

	    			switch ((EFfmMasterConsignment) tag) {

	    				case GrossWeightMeasure:   
	    					setGrossWeightMeasure(value);    					
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
	    					
	    				case SummaryDescription:   
	    					setSummaryDescription(value);    					
	    					break;  
	    					
	    				case TransportSplitDescription:   
	    					setTransportSplitDescription(value);    					
	    					break;  
	    					
	    				case MovementPriorityCode:   
	    					setMovementPriorityCode(value);    					
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
	    			return EFfmMasterConsignment.valueOf(token);
	    		} catch (IllegalArgumentException e) {
	    			return null;
	    		}
	    	}	      	

		
		
		public String getGrossWeightMeasure() {
				return grossWeightMeasure;
			}

			public void setGrossWeightMeasure(String grossWeightMeasure) {
				this.grossWeightMeasure = grossWeightMeasure;
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

			public String getSummaryDescription() {
				return summaryDescription;
			}

			public void setSummaryDescription(String summaryDescription) {
				this.summaryDescription = summaryDescription;
			}

			public String getTransportSplitDescription() {
				return transportSplitDescription;
			}

			public void setTransportSplitDescription(String transportSplitDescription) {
				this.transportSplitDescription = transportSplitDescription;
			}

			public String getMovementPriorityCode() {
				return movementPriorityCode;
			}

			public void setMovementPriorityCode(String movementPriorityCode) {
				this.movementPriorityCode = movementPriorityCode;
			}

			public DocumentType getTransportContractDocument() {
				return transportContractDocument;
			}

			public void setTransportContractDocument(DocumentType transportContractDocument) {
				this.transportContractDocument = transportContractDocument;
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

			public void setAssociatedConsignmentCustomsProcedure(
					CustomsProcedure associatedConsignmentCustomsProcedure) {
				this.associatedConsignmentCustomsProcedure = associatedConsignmentCustomsProcedure;
			}

		public ArrayList<PackageType> getTransportLogisticsPackageList() {
				return transportLogisticsPackageList;
			}
		public void setTransportLogisticsPackageList(
					ArrayList<PackageType> transportLogisticsPackageList) {
				this.transportLogisticsPackageList = transportLogisticsPackageList;
			}
		public void addTransportLogisticsPackageList(PackageType argument) {
			if (transportLogisticsPackageList == null) {
				transportLogisticsPackageList = new ArrayList<PackageType>();
			}
			this.transportLogisticsPackageList.add(argument);
		}

		public ArrayList<TransportCarriage> getOnCarriageTransportMovementList() {
				return onCarriageTransportMovementList;
			}

		public void setOnCarriageTransportMovementList(
					ArrayList<TransportCarriage> onCarriageTransportMovementList) {
				this.onCarriageTransportMovementList = onCarriageTransportMovementList;
			}
		public void addOnCarriageTransportMovementList(TransportCarriage argument) {
			if (onCarriageTransportMovementList == null) {
				onCarriageTransportMovementList = new ArrayList<TransportCarriage>();
			}
			this.onCarriageTransportMovementList.add(argument);
		}

		public ArrayList<ConsignmentItemTypeFFM> getIncludedMasterConsignmentItemList() {
				return includedMasterConsignmentItemList;
		}
		public void setIncludedMasterConsignmentItemList(
					ArrayList<ConsignmentItemTypeFFM> includedMasterConsignmentItemList) {
				this.includedMasterConsignmentItemList = includedMasterConsignmentItemList;
		}				
		public void addIncludedMasterConsignmentItemList(ConsignmentItemTypeFFM item) {
			if (includedMasterConsignmentItemList == null) {
				includedMasterConsignmentItemList = new ArrayList<ConsignmentItemTypeFFM>();
			}
			includedMasterConsignmentItemList.add(item);	
		}
		
		public boolean isEmpty() {
			return Utils.isStringEmpty(grossWeightMeasure) && Utils.isStringEmpty(grossVolumeMeasure) &&
			Utils.isStringEmpty(densityGroupCode) && Utils.isStringEmpty(packageQuantity) &&
			Utils.isStringEmpty(totalPieceQuantity) && Utils.isStringEmpty(summaryDescription) &&
			Utils.isStringEmpty(transportSplitDescription) && Utils.isStringEmpty(movementPriorityCode) &&
			//TODO
			transportContractDocument == null && originLocation == null &&
			finalDestinationLocation == null; 
		}
	
}
