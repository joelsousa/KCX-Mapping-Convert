package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import java.util.ArrayList;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;


/**
 * Module		: CMP<br>
 * Created		: 26.07.2013<br>
 * Description	: TotalPieceQuantity.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class HouseConsignment extends KCXMessage {
	
	private String 	grossWeightMeasure;
	private String 	totalGrossWeightMeasure;
	private String 	pieceQuantity;
	private String 	totalPieceQuantity;	
	private String 	transportSplitDescription;			
	private ArrayList<DocumentType> transportContractDocumentList;
	
	private enum EHouseConsignment {
		GrossWeightMeasure,
		TotalGrossWeightMeasure,		
		PieceQuantity,
		TotalPieceQuantity,		
		TransportSplitDescription,		
		TransportContractDocument,		
	    }        

	    public HouseConsignment() {
		      	super();	       
	    }
	    
	    public HouseConsignment(XmlMsgScanner scanner) {
	    	super(scanner);    	
	  	}
	    
	    public void startElement(Enum tag, String value, Attributes attr) {
	    		if (value == null) {
	    			switch ((EHouseConsignment) tag) {
	    			case TransportContractDocument:   
	    				DocumentType document = new DocumentType(getScanner());
	    				document.parse(tag.name());					    					
        				addTransportContractDocumentList(document);
    					break;
    					
    				default:
	    					return;
	    			}
	    		} else {

	    			switch ((EHouseConsignment) tag) {

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
	    			return EHouseConsignment.valueOf(token);
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

		public String getTotalGrossWeightMeasure() {
			return totalGrossWeightMeasure;
		}
		public void setTotalGrossWeightMeasure(String grossWeightMeasure) {
			this.totalGrossWeightMeasure = grossWeightMeasure;
		}
		
		public String getPieceQuantity() {
			return pieceQuantity;
		}
		public void setPieceQuantity(String packageQuantity) {
			this.pieceQuantity = packageQuantity;
		}

		public String getTotalPieceQuantity() {
			return totalPieceQuantity;
		}
		public void setTotalPieceQuantity(String totalPieceQuantity) {
			this.totalPieceQuantity = totalPieceQuantity;
		}
		
		public String getTransportSplitDescription() {
			return transportSplitDescription;
		}
		public void setTransportSplitDescription(String transportSplitDescription) {
			this.transportSplitDescription = transportSplitDescription;
		}
	
		public ArrayList<DocumentType> getTransportContractDocumentList() {
			return transportContractDocumentList;
		}
		public void setTransportContractDocumentList(ArrayList<DocumentType> list) {
				this.transportContractDocumentList = list;
		}	
		public void addTransportContractDocumentList(DocumentType argument) {
			if (transportContractDocumentList == null) {
				transportContractDocumentList = new ArrayList<DocumentType>();
			}
			this.transportContractDocumentList.add(argument);
		}

		public boolean isEmpty() {
			return Utils.isStringEmpty(grossWeightMeasure) && Utils.isStringEmpty(totalGrossWeightMeasure) &&
			Utils.isStringEmpty(pieceQuantity) && Utils.isStringEmpty(transportSplitDescription) &&			
			Utils.isStringEmpty(transportSplitDescription) && transportContractDocumentList == null; 
		}
}
