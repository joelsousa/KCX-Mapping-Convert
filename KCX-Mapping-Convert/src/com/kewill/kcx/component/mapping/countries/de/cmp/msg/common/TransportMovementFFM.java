package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import java.util.ArrayList;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Module		: CMP<br>
* Created		: 17.07.2013<br>
* Description	: FFM-TransportMovement.
* 
* @author iwaniuk
* @version 1.0.00
*/


public class TransportMovementFFM extends KCXMessage {

	  private String stageCode;
	  private String modeCode;
	  private String mode;
	  private String id;
	  private String sequenceNumeric;
	  private String totalGrossWeightMeasure;
	  private String totalGrossVolumeMeasure;
	  private String totalPackageQuantity;	  
	  private String totalPieceQuantity;
	  private PartyType masterResponsibleTransportPerson;
	  private TransportMeans usedLogisticsTransportMeans;
	  private DepartureEvent departureEvent;
	  private ArrayList<CustomsNote> includedCustomsNoteList;
	  private CustomsProcedure relatedConsignmentCustomsProcedure;
	  
	  
	  private enum EFfmTransportMovement {    	  			     	
		  StageCode,
		  ModeCode,
		  Mode,
	      ID,
	      SequenceNumeric,
	      TotalGrossWeightMeasure,
	      TotalGrossVolumeMeasure,
	      TotalPackageQuantity,
	      TotalPieceQuantity,
	      MasterResponsibleTransportPerson,
	      UsedLogisticsTransportMeans,
	      DepartureEvent,
	      IncludedCustomsNote,
	      RelatedConsignmentCustomsProcedure;
	  }        

	    public TransportMovementFFM() {
		      	super();	       
	    }
	    
	    public TransportMovementFFM(XmlMsgScanner scanner) {
	    	super(scanner);    	
	  	}
	    
	    public void startElement(Enum tag, String value, Attributes attr) {
	    		if (value == null) {
	    			switch ((EFfmTransportMovement) tag) {
	    			
	    			case MasterResponsibleTransportPerson:
	    				masterResponsibleTransportPerson = new PartyType(getScanner());
	    				masterResponsibleTransportPerson.parse(tag.name());
	    				break;
	    				
	    			case UsedLogisticsTransportMeans:
	    				usedLogisticsTransportMeans = new TransportMeans(getScanner());
	    				usedLogisticsTransportMeans.parse(tag.name());
	    				break;
	    				
	    			case DepartureEvent:
	    				departureEvent = new DepartureEvent(getScanner());
	    				departureEvent.parse(tag.name());
	    				break;
	    				
	    			case IncludedCustomsNote:
	    				CustomsNote note = new CustomsNote(getScanner());
	    				note.parse(tag.name());
	    				addIncludedCustomsNoteList(note);
	    				break;
	    				
	    			case RelatedConsignmentCustomsProcedure:
	    				relatedConsignmentCustomsProcedure = new CustomsProcedure(getScanner());
	    				relatedConsignmentCustomsProcedure.parse(tag.name());
	    				break;
	    				
	    			default:
	    					return;
	    			}
	    		} else {
	    			switch ((EFfmTransportMovement) tag) {
	    			case StageCode:
	    				setStageCode(value);
	    				break;
	    				
	    			case ModeCode:
	    				setModeCode(value);
	    				break;
	    				
	    			case Mode:
	    				setMode(value);
	    				break;
	    				
	    			case ID:
	    				setId(value);
	    				break;
	    				
	    			case SequenceNumeric:
	    				setSequenceNumeric(value);
	    				break;
	    				
	    			case TotalGrossWeightMeasure:
	    				setTotalGrossWeightMeasure(value);
	    				break;
	    				
	    			case TotalGrossVolumeMeasure:
	    				setTotalGrossVolumeMeasure(value);
	    				break;
	    				
	    			case TotalPackageQuantity:
	    				setTotalPackageQuantity(value);
	    				break;
	    				
	    			case TotalPieceQuantity:
	    				setTotalPieceQuantity(value);
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
	    		return EFfmTransportMovement.valueOf(token);
	    	} catch (IllegalArgumentException e) {
	    		return null;
	    	}
	    }

		public String getStageCode() {
			return stageCode;
		}

		public void setStageCode(String stageCode) {
			this.stageCode = stageCode;
		}

		public String getModeCode() {
			return modeCode;
		}

		public void setModeCode(String modeCode) {
			this.modeCode = modeCode;
		}

		public String getMode() {
			return mode;
		}

		public void setMode(String mode) {
			this.mode = mode;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getSequenceNumeric() {
			return sequenceNumeric;
		}

		public void setSequenceNumeric(String sequenceNumeric) {
			this.sequenceNumeric = sequenceNumeric;
		}

		public String getTotalGrossWeightMeasure() {
			return totalGrossWeightMeasure;
		}

		public void setTotalGrossWeightMeasure(String totalGrossWeightMeasure) {
			this.totalGrossWeightMeasure = totalGrossWeightMeasure;
		}

		public String getTotalGrossVolumeMeasure() {
			return totalGrossVolumeMeasure;
		}

		public void setTotalGrossVolumeMeasure(String totalGrossVolumeMeasure) {
			this.totalGrossVolumeMeasure = totalGrossVolumeMeasure;
		}

		public String getTotalPackageQuantity() {
			return totalPackageQuantity;
		}

		public void setTotalPackageQuantity(String totalPackageQuantity) {
			this.totalPackageQuantity = totalPackageQuantity;
		}

		public String getTotalPieceQuantity() {
			return totalPieceQuantity;
		}

		public void setTotalPieceQuantity(String totalPieceQuantity) {
			this.totalPieceQuantity = totalPieceQuantity;
		}

		public PartyType getMasterResponsibleTransportPerson() {
			return masterResponsibleTransportPerson;
		}

		public void setMasterResponsibleTransportPerson(
				PartyType masterResponsibleTransportPerson) {
			this.masterResponsibleTransportPerson = masterResponsibleTransportPerson;
		}

		public TransportMeans getUsedLogisticsTransportMeans() {
			return usedLogisticsTransportMeans;
		}

		public void setUsedLogisticsTransportMeans(
				TransportMeans usedLogisticsTransportMeans) {
			this.usedLogisticsTransportMeans = usedLogisticsTransportMeans;
		}

		public DepartureEvent getDepartureEvent() {
			return departureEvent;
		}

		public void setDepartureEvent(DepartureEvent departureEvent) {
			this.departureEvent = departureEvent;
		}

		public ArrayList<CustomsNote> getIncludedCustomsNoteList() {
			return includedCustomsNoteList;
		}

		public void setIncludedCustomsNoteList(ArrayList<CustomsNote> includedCustomsNoteList) {
			this.includedCustomsNoteList = includedCustomsNoteList;
		}
		public void addIncludedCustomsNoteList(CustomsNote dest) {
			if (this.includedCustomsNoteList == null) {
				this.includedCustomsNoteList = new ArrayList<CustomsNote>();
			}
			this.includedCustomsNoteList.add(dest);
		}
		
		public CustomsProcedure getRelatedConsignmentCustomsProcedure() {
			return relatedConsignmentCustomsProcedure;
		}

		public void setRelatedConsignmentCustomsProcedure(
				CustomsProcedure relatedConsignmentCustomsProcedure) {
			this.relatedConsignmentCustomsProcedure = relatedConsignmentCustomsProcedure;
		}

		public boolean isEmpty() {
			return 	Utils.isStringEmpty(stageCode) && Utils.isStringEmpty(modeCode) &&
				Utils.isStringEmpty(mode) && Utils.isStringEmpty(id) && Utils.isStringEmpty(sequenceNumeric) &&
				Utils.isStringEmpty(totalGrossWeightMeasure) && Utils.isStringEmpty(totalGrossVolumeMeasure) &&
				Utils.isStringEmpty(totalPackageQuantity) && Utils.isStringEmpty(totalPieceQuantity) &&
				(relatedConsignmentCustomsProcedure == null || relatedConsignmentCustomsProcedure.isEmpty()) &&
				(masterResponsibleTransportPerson == null || masterResponsibleTransportPerson.isEmpty()) &&
				(departureEvent == null || departureEvent.isEmpty()) &&
				(relatedConsignmentCustomsProcedure == null || relatedConsignmentCustomsProcedure.isEmpty()) &&
				(usedLogisticsTransportMeans == null || usedLogisticsTransportMeans.isEmpty()) &&
				includedCustomsNoteList == null; 
		}		
		
}
