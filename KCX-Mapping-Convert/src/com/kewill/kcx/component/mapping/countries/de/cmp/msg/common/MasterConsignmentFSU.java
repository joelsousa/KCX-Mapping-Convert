package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import java.util.ArrayList;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;


/**
 * Module		: CMP<br>
 * Created		: 25.07.2013<br>
 * Description	: FSU-MasterConsignment.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class MasterConsignmentFSU extends KCXMessage {
	
	private String 	grossWeightMeasure;
	private String 	totalGrossWeightMeasure;
	private String 	pieceQuantity;
	private String 	totalPieceQuantity;	
	private String 	transportSplitDescription;		
	private DocumentType transportContractDocument;
	private Location originLocation;
	private Location finalDestinationLocation;	
	private ArrayList<Location> routingLocationList;
	private ArrayList<ReportedStatus> reportedStatusList;
	
	private enum EFsuMasterConsignment {
		GrossWeightMeasure,
		TotalGrossWeightMeasure,
		PieceQuantity,
		TotalPieceQuantity,		
		TransportSplitDescription,		
		TransportContractDocument,
		OriginLocation,
		FinalDestinationLocation,
		RoutingLocation,		
		ReportedStatus;
	    }        

	    public MasterConsignmentFSU() {
		      	super();	       
	    }
	    
	    public MasterConsignmentFSU(XmlMsgScanner scanner) {
	    	super(scanner);    	
	  	}
	    
	    public void startElement(Enum tag, String value, Attributes attr) {
	    		if (value == null) {
	    			switch ((EFsuMasterConsignment) tag) {
	    			
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
    					
	    			case RoutingLocation:   
	    				Location location = new Location(getScanner());
	    				location.parse(tag.name());	
	    				addRoutingLocationList(location);
    					break; 
    					    			
	    			case ReportedStatus:   
	    				ReportedStatus status = new ReportedStatus(getScanner());
	    				status.parse(tag.name());	
	    				addReportedStatusList(status);
    					break; 
    					
    				default:
	    					return;
	    			}
	    		} else {

	    			switch ((EFsuMasterConsignment) tag) {
	    				
	    				
	    				case GrossWeightMeasure:   
	    					setGrossWeightMeasure(value);    					
	    					break;   
	    					
	    				case TotalGrossWeightMeasure:   
	    					setTotalGrossWeightMeasure(value);    					
	    					break;   
	    					
	    				case PieceQuantity:   
	    					setPieceQuantity(value);    					
	    					break; 
	    					
	    				case TotalPieceQuantity:   
	    					setTotalPieceQuantity(value);    					
	    					break; 
	    					
	    				case TransportSplitDescription:   
	    					setTransportSplitDescription(value);    					
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
	    			return EFsuMasterConsignment.valueOf(token);
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

			public String getTotalGrossWeightMeasure() {
				return totalGrossWeightMeasure;
			}
			public void setTotalGrossWeightMeasure(String value) {
				this.totalGrossWeightMeasure = value;
			}

			public String getPieceQuantity() {
				return pieceQuantity;
			}
			public void setPieceQuantity(String value) {
				this.pieceQuantity = value;
			}

			public String getTotalPieceQuantity() {
				return totalPieceQuantity;
			}
			public void setTotalPieceQuantity(String value) {
				this.totalPieceQuantity = value;
			}
		
			public String getTransportSplitDescription() {
				return transportSplitDescription;
			}
			public void setTransportSplitDescription(String value) {
				this.transportSplitDescription = value;
			}
			
			public DocumentType getTransportContractDocument() {
				return transportContractDocument;
			}
			public void setTransportContractDocument(DocumentType argument) {
				this.transportContractDocument = argument;
			}

			public Location getOriginLocation() {
				return originLocation;
			}
			public void setOriginLocation(Location argument) {
				this.originLocation = argument;
			}

			public Location getFinalDestinationLocation() {
				return finalDestinationLocation;
			}
			public void setFinalDestinationLocation(Location argument) {
				this.finalDestinationLocation = argument;
			}

			public ArrayList<Location> getRoutingLocationList() {
				return routingLocationList;
			}
			public void setRoutingLocationList(ArrayList<Location> list) {
				this.routingLocationList = list;
			}
			public void addRoutingLocationList(Location argument) {				
				if (routingLocationList == null) {
					routingLocationList = new ArrayList<Location>();
				}
				this.routingLocationList.add(argument);
			}
			
			public ArrayList<ReportedStatus> getReportedStatusList() {
				return reportedStatusList;
			}
			public void setReportedStatusList(ArrayList<ReportedStatus> list) {
				this.reportedStatusList = list;
			}
			public void addReportedStatusList(ReportedStatus argument) {				
				if (reportedStatusList == null) {
					reportedStatusList = new ArrayList<ReportedStatus>();
				}
				this.reportedStatusList.add(argument);
			}

		public boolean isEmpty() {
			
			return Utils.isStringEmpty(grossWeightMeasure) && 
				Utils.isStringEmpty(totalGrossWeightMeasure) &&
				Utils.isStringEmpty(pieceQuantity) && 
				Utils.isStringEmpty(totalPieceQuantity) &&			
				Utils.isStringEmpty(transportSplitDescription) && 
				transportContractDocument == null &&			
				originLocation == null && finalDestinationLocation == null &&			
				routingLocationList == null && reportedStatusList == null; 
			
		}
	
}
