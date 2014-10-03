package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import java.util.ArrayList;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;


/**
 * Module		: CMP<br>
 * Created		: 18.07.2013<br>
 * Description	: CSN-MasterConsignment.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class MasterConsignmentCSN extends KCXMessage {
	
	private String 	customsId;
	private String 	totalPieceQuantity;
	private ArrayList<PartyType> associatedPartyList;			
	private Location originLocation;
	private Location finalDestinationLocation;	
	private IdType bondedWarehouseLocation;
	private TransportMovementFWB specifiedLogisticsTransportMovement;
	private ArrayList<CustomsNote> includedCustomsNoteList;	
	private DocumentType associatedReferenceDocument;
	private CustomsProcedure associatedConsignmentCustomsProcedure;
	
	private enum ECsnMasterConsignment {
		CustomsID,
		TotalPieceQuantity,
		AssociatedParty,
		OriginLocation,
		FinalDestinationLocation,
		BondedWarehouseLocation,
		SpecifiedLogisticsTransportMovement,
		IncludedCustomsNote,
		AssociatedReferenceDocument,
		AssociatedConsignmentCustomsProcedure,		
	}        

	    public MasterConsignmentCSN() {
		      	super();	       
	    }
	    
	    public MasterConsignmentCSN(XmlMsgScanner scanner) {
	    	super(scanner);    	
	  	}
	    
	    public void startElement(Enum tag, String value, Attributes attr) {
	    		if (value == null) {
	    			switch ((ECsnMasterConsignment) tag) {
	    			
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
    					    			
	    			case BondedWarehouseLocation:   
	    				bondedWarehouseLocation = new IdType(getScanner());
	    				bondedWarehouseLocation.parse(tag.name());		    				
    					break; 
    				
	    			case IncludedCustomsNote:
    					CustomsNote note = new CustomsNote(getScanner());
    					note.parse(tag.name());
        				addIncludedCustomsNoteList(note);
    					break;
    					
	    			case AssociatedReferenceDocument:	    				 
	    				associatedReferenceDocument = new DocumentType(getScanner());
	    				associatedReferenceDocument.parse(tag.name());		    				
    					break;     					
    					    				
    				case AssociatedConsignmentCustomsProcedure:
    					associatedConsignmentCustomsProcedure  = new CustomsProcedure(getScanner());
    					associatedConsignmentCustomsProcedure.parse(tag.name());        				
    					break;
    				
    				default:
	    					return;
	    			}
	    		} else {

	    			switch ((ECsnMasterConsignment) tag) {
	    				case CustomsID:
	    					setCustomsId(value);    					
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
	    			return ECsnMasterConsignment.valueOf(token);
	    		} catch (IllegalArgumentException e) {
	    			return null;
	    		}
	    	}	      			
		
		    public String getCustomsId() {
				return customsId;
			}
			public void setCustomsId(String id) {
				this.customsId = id;
			}

			public IdType getBondedWarehouseLocation() {
				return bondedWarehouseLocation;
			}
			public void setBondedWarehouseLocation(IdType bondedWarehouseLocation) {
				this.bondedWarehouseLocation = bondedWarehouseLocation;
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
		
			public DocumentType getAssociatedReferenceDocument() {
				return associatedReferenceDocument;
			}

			public void setAssociatedReferenceDocument(
					DocumentType associatedReferenceDocument) {
				this.associatedReferenceDocument = associatedReferenceDocument;
			}
			
			public String getTotalPieceQuantity() {
				return totalPieceQuantity;
			}
			public void setTotalPieceQuantity(String totalPieceQuantity) {
				this.totalPieceQuantity = totalPieceQuantity;
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
			return Utils.isStringEmpty(customsId) && Utils.isStringEmpty(totalPieceQuantity) &&	
			originLocation == null && finalDestinationLocation == null &&
			associatedPartyList == null && bondedWarehouseLocation == null &&
			specifiedLogisticsTransportMovement == null && includedCustomsNoteList == null &&
			associatedReferenceDocument == null && associatedConsignmentCustomsProcedure == null;
		}
}
