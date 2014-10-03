package com.kewill.kcx.component.mapping.countries.de.Port.msg.common;

import org.xml.sax.Attributes;

import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Port<br>
 * Created		: 24.10.2011<br>
 * Description	: Container Data.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Container extends KCXMessage {
			
	 private String containerNumber; 
	 private String type;	     // acr_ Containertyp ISO	 
	 private String owner;	     
	 private String ladingFlag;  // leer, voll, sammel 
	 private String grossMass;	 
	 private String tareMass;	
	 private String netMass;	 
	 private String tempUnit;	 
	 private String minTemp;	 
	 private String maxTemp;	
	 private String tempMode;	 // kühlen, heizen, aircondition
	 private List <String> sealNumberList;	 
	 private String bookingNumber;      	 
     private String ladingDescription;	//act_txt
     private String warenCode;      //EI20140108
		
     
    public Container() {
		super();  
    }

	public Container(XmlMsgScanner scanner) {
	  	super(scanner);
	}
	 
		 private enum EContainer {	
			// Kids-TagNames, 			KFF				FSS
			 ContainerNumber,          ContNo,          // acr_connr
			 Number, //BDP
			 Type, Typ,                ContType,        // acr_typ
			 Owner,                    ContOwner,       // acr_owner 		
			 LadingFlag,                                // acr_voller, acr_ladung       
			 GrossMass,	               CtnTotGrossWGT,  // acr_grgew 
			 TareMass, TaraMass,       TaraWGT, TareWGT, // acr_trgew
			 NetMass,                  CtnTOTWGT,       // acr_negew 
			 TemperaturUnit,           CtnTempUOM,      // acr_tempe
			 MaxPermitedTemperatur,    CtnHighestTemp,  // acr_tempo 
			 MinPermitedTemperatur,    CtnLowestTemp,   // acr_tempu   
			 TemperaturMode,                            // acr_mod 
			 SealNumber,   SealNo,           // acr_siegl1,2,3,4  			
			 SealNo2, SealNo3, SealNo4,    //EI20120423
			 BookingNumber,                             // acr_bunr  
			 CodeOfGoods,								// acr_prodid
			 LadingDescription,	      CtnRemarks;       // act_txt		        			
		 }	 
		 
		 public void startElement(Enum tag, String value, Attributes attr) {
			if (value == null) {		
				switch ((EContainer) tag) {
					default: return;			
				}
			} else {			
				switch ((EContainer) tag) {			
					case ContainerNumber:
					case ContNo:
					case Number:
						setContainerNumber(value);
						break;
					case Typ:
					case Type:
					case ContType:
						setType(value);
						break;
					case Owner:
					case ContOwner:
						setOwner(value);
						break;
					case LadingFlag:
						setLadingFlag(value);
						break;					
					case NetMass:
					case CtnTOTWGT:
						setNetMass(value);
						break;
					case TareMass:
					case TaraMass:
					case TaraWGT:
					case TareWGT:
						setTareMass(value);
						break;
					case GrossMass:
					case CtnTotGrossWGT:
						setGrossMass(value);
						break;
					case TemperaturUnit:
					case CtnTempUOM:
						setTemperaturUnit(value);
						break;
					case MaxPermitedTemperatur:
					case CtnHighestTemp:
						setMaxPermitedTemperatur(value);
						break;
					case MinPermitedTemperatur:
					case CtnLowestTemp:
						setMinPermitedTemperatur(value);
						break;
					case TemperaturMode:
						setTemperaturMode(value);
						break;
					case SealNumber:	
					case SealNo:
						addSealNumberList(value);
						break;
					case SealNo2:
						addSealNumberList(value);
						break;
					case SealNo3:
						addSealNumberList(value);
						break;
					case SealNo4:
						addSealNumberList(value);
						break;
					case BookingNumber:
						setBookingNumber(value);
						break;
					case LadingDescription:
					case CtnRemarks:
						setLadingDescription(value);
						break;
					case CodeOfGoods:
						setCodeOfGoods(value);
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
				return EContainer.valueOf(token);
			 } catch (IllegalArgumentException e) {
				return null;
			 }
		 }

	    public String getContainerNumber() {
			return containerNumber;
		}
		public void setContainerNumber(String argument) {
			this.containerNumber = argument;
		}					
		
		public String getType() {
			return type;
		}
		public void setType(String argument) {
			this.type = argument;
		}		
		 
	    public String getOwner() {
			return owner;
		}
		public void setOwner(String argument) {
			this.owner = argument;
		}					
	 
		public String getLadingFlag() {
			return ladingFlag;
		}
		public void setLadingFlag(String argument) {
			this.ladingFlag = argument;
		}	
		
		public String getGrossMass() {
			return grossMass;
		}
		public void setGrossMass(String argument) {
			this.grossMass = argument;
		}

		public String getNetMass() {
			return netMass;
		}
		public void setNetMass(String argument) {
			this.netMass = argument;
		}
		
		public String getTareMass() {
			return tareMass;
		}
		public void setTareMass(String argument) {
			this.tareMass = argument;
		}
		
	    public String getTemperaturUnit() {
			return tempUnit;
		}
		public void setTemperaturUnit(String argument) {
			this.tempUnit = argument;
		}					
			
		public String getMaxPermitedTemperatur() {
			return maxTemp;
		}
		public void setMaxPermitedTemperatur(String argument) {
			this.maxTemp = argument;
		}	
				
		public String getMinPermitedTemperatur() {
			return minTemp;
		}
		public void setMinPermitedTemperatur(String argument) {
			this.minTemp = argument;
		}
		
		public String getTemperaturMode() {
			return tempMode;
		}
		public void setTemperaturMode(String argument) {
			this.tempMode = argument;
		}	
		
		public List<String> getSealNumberList() {
			return sealNumberList;
		}
		public void setSealNumberList(List <String> list) {
			this.sealNumberList = list;
		}	
		public void addSealNumberList(String seal) {
			if (sealNumberList == null) {
				sealNumberList = new Vector<String>();
			}
			this.sealNumberList.add(seal);
		}

	    public String getBookingNumber() {
			return bookingNumber;
		}
		public void setBookingNumber(String argument) {
			this.bookingNumber = argument;
		}					
		
		public String getLadingDescription() {
			return ladingDescription;
		}
		public void setLadingDescription(String text) {
			this.ladingDescription = text;
		}	
	
		public String getCodeOfGoods() {
			return warenCode;
		}
		public void setCodeOfGoods(String argument) {
			this.warenCode = argument;
		}
		
		public boolean isEmpty() {
			return (Utils.isStringEmpty(this.containerNumber) && Utils.isStringEmpty(this.type) && 			     
			        Utils.isStringEmpty(this.ladingFlag) && Utils.isStringEmpty(this.bookingNumber));  
		}
}


