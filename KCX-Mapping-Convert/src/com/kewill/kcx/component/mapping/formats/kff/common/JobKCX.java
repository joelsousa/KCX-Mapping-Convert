package com.kewill.kcx.component.mapping.formats.kff.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;


/**
 * Module		: Port<br>
 * Created		: 05.07.2012<br>
 * Description	: JobKCX allow to distinguish between DAKOSY/BHT and HDS/BL
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class JobKCX extends KCXMessage {
	
	 private String dakMsgType;    //0 = HDS, 1 = BL    
	 private String dbhMsgType;    //0 = HDS, 1 = BL                 
	 private String forwarderReference;   
	 private String cargoRemark;
	 private String billofLading;  // FormularLayoutKeyDakosy
	 public JobKCX() {
		 super();  
	 }

	 public JobKCX(XmlMsgScanner scanner) {
 		super(scanner);
	 }

	 private enum EUserInforamtion {				
		 DakosyMsgType,					
		 DBHMsgType,
		 ForwarderReference,
		 CargoRemark,
		 BillofLading;							 			        			
	 }	 
	 
	 public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {		
			switch ((EUserInforamtion) tag) {
				default: return;			
			}
		} else {			
			switch ((EUserInforamtion) tag) {			
				case DakosyMsgType:
					setDakosyMsgType(value);
					break;
				case DBHMsgType:
					setDBHMsgType(value);
					break;				
				case ForwarderReference:
					setForwarderReference(value);
					break;
				case CargoRemark:
					setCargoRemark(value);
					break;
				case BillofLading:
					setBillofLading(value);
					break;
				default:
					return;
			}
		}
	}

	 public void stoppElement(Enum tag) {
	 }

	
	 public Enum translate(String token) {
		 try {
			return EUserInforamtion.valueOf(token);
		 } catch (IllegalArgumentException e) {
			return null;
		 }
	 }

   public String getDakosyMsgType() {
		return dakMsgType;
	}
	public void setDakosyMsgType(String argument) {
		this.dakMsgType = argument;
	}					
		
	public String getDBHMsgType() {
			return dbhMsgType;
	}
	public void setDBHMsgType(String argument) {
			this.dbhMsgType = argument;
	}

	public String getForwarderReference() {
		return forwarderReference;
	}

	public void setForwarderReference(String forwarderReference) {
		this.forwarderReference = Utils.checkNull(forwarderReference);
	}

	public String getCargoRemark() {
		return cargoRemark;
	}

	public void setCargoRemark(String cargoRemark) {
		this.cargoRemark = Utils.checkNull(cargoRemark);
	}

	public String getBillofLading() {
		return billofLading;
	}

	public void setBillofLading(String billOfLading) {
		this.billofLading = Utils.checkNull(billOfLading);
	}					
			
}
