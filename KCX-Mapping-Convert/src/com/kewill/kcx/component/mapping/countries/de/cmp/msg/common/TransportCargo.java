package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import java.util.ArrayList;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;


/**
 * Module		: CMP<br>
 * Created		: 18.07.2013<br>
 * Description	: TransportCargo
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class TransportCargo extends KCXMessage {
	
	private String 	typeCode;
	private String 	identification;
	private TransportEquipment 	utilizedunitLoadTransportEquipment;
	private ArrayList<MasterConsignmentFFM> includedMasterConsignmentList;

	
	private enum ETransportCargo {
		 TypeCode,
		 Identification,
		 UtilizedUnitLoadTransportEquipment,
		 IncludedMasterConsignment;
	}        

	    public TransportCargo() {
		      	super();	       
	    }
	    
	    public TransportCargo(XmlMsgScanner scanner) {
	    	super(scanner);    	
	  	}
	    
	    public void startElement(Enum tag, String value, Attributes attr) {
	    		if (value == null) {
	    			switch ((ETransportCargo) tag) {
	    			case UtilizedUnitLoadTransportEquipment:   
	    				utilizedunitLoadTransportEquipment = new TransportEquipment(getScanner());
	    				utilizedunitLoadTransportEquipment.parse(tag.name());					
    					break;  
    					
    				case IncludedMasterConsignment:
    					MasterConsignmentFFM master = new MasterConsignmentFFM(getScanner());
        				master.parse(tag.name());
        				addIncludedMasterConsignmentList(master);
    					break;
	    			default:
	    					return;
	    			}
	    		} else {

	    			switch ((ETransportCargo) tag) {

	    				case TypeCode:   
	    					setTypeCode(value);    					
	    					break;    				    					
	    				
	    				case Identification:
	    					setIdentification(value);    					
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
	    			return ETransportCargo.valueOf(token);
	    		} catch (IllegalArgumentException e) {
	    			return null;
	    		}
	    	}	      	

		public String getTypeCode() {
			return typeCode;
		}
		public void setTypeCode(String typeCode) {
			this.typeCode = Utils.checkNull(typeCode);
		}

		public String getIdentification() {
			return identification;
		}
		public void setIdentification(String identification) {
			this.identification = identification;
		}

		public TransportEquipment getUtilizedUnitLoadTransportEquipment() {
			return utilizedunitLoadTransportEquipment;
		}
		public void setUtilizedUnitLoadTransportEquipment(TransportEquipment argument) {
			this.utilizedunitLoadTransportEquipment = argument;
		}

		public ArrayList<MasterConsignmentFFM> getIncludedMasterConsignmentList() {
			return includedMasterConsignmentList;
		}

		public void setIncludedMasterConsignmentList(ArrayList<MasterConsignmentFFM> list) {
			this.includedMasterConsignmentList = list;
		}
		public void addIncludedMasterConsignmentList(MasterConsignmentFFM argument) {
			if (includedMasterConsignmentList == null) {
				includedMasterConsignmentList = new ArrayList<MasterConsignmentFFM>();
			}
			this.includedMasterConsignmentList.add(argument);
		}

		public boolean isEmpty() {
			return Utils.isStringEmpty(typeCode) && Utils.isStringEmpty(identification) && 
				utilizedunitLoadTransportEquipment == null  && includedMasterConsignmentList == null; 
		}
}
