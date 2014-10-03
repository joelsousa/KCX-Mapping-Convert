package com.kewill.kcx.component.mapping.countries.de.Port.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Port<br>
 * Created		: 24.10.2011<br>
 * Description	: BookingData.
 * 
 * @author Iwaniuk
 * @version 1.0.00
 */

public class RadioactiveGoods extends KCXMessage {

	 private String unNumber;     //fzpagi_bfsnr: BFS-Nummer
	 private String packagingType;           //fzpagx_vptyp
	 private String cathegory;               //fzpagx_kat: Kategorie
	 private String radioactivityLevel;      //fzpagx_xratac: Radioactivity Wert
	 private String radioactivityQualifier;       //fzpagx_xraace: Radioactivity Einheit
	 private String bfsNumber;     //fzpagi_bfsnr: BFS-Nummer
	 private String transportIndex;      //fzpagx_xratrw: Raidoactivie index of transport Wert
	 private String csi;                     //fzpagx_xrakrw: Kritikatilätssicherheitskennzahl Wert
	 private String sheetNumber;
	
	 /*RadioactiveGoodsSpecification
	 private String bfsPermissionNumber;     //fzpagi_bfsnr: BFS-Nummer
	 private String packagingCode;           //fzpagx_vptyp
	 private String cathegory;               //fzpagx_kat: Ketegorie
	 private String radioactivityLevel;      //fzpagx_xratac: Radioactivity Wert
	 private String radioactivityUnit;       //fzpagx_xraace: Radioactivity Einheit
	 private String radioactivityTransportIndex;      //fzpagx_xratrw: Raidoactivie index of transport Wert
	 private String csi;                     //fzpagx_xrakrw: Kritikatilätssicherheitskennzahl Wert
	 private String radioactivityUnNumber;   //fzpagx_blttnr: Blattnummer
	*/
	 public RadioactiveGoods() {
		 super();  
	 }

	 public RadioactiveGoods(XmlMsgScanner scanner) {
  		super(scanner);
	 }
 
	 private enum EBookingData {	
			// Kids-TagNames, 
		 		UnNumber, UNNumber,							 		
		 		Cathegory,  Category,
		 		PackagingType,	PackingType,						 		   
		 		RadioactivityLevel,	
		 		RadioactivityQualifier,
		 		BfSNumber,	BfsNumber,
		 		TransportIndex,
		 		CriticalHazardIndex,	
		 		SheetNumber,            //EI20131016
			}	 
	 
	 public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {		
			switch ((EBookingData) tag) {
				default: return;			
			}
		} else {			
			switch ((EBookingData) tag) {			
				case BfSNumber:
				case BfsNumber:     //EI20130821
					setBfSNumber(value);
					break;
				case PackagingType:
				case PackingType:   //EI20130821
					setPackagingType(value);
					break;
				case Cathegory:
				case Category:     //EI20130821
					setCathegory(value);
					break;
				case RadioactivityLevel:
					setRadioactivityLevel(value);
					break;
				case RadioactivityQualifier:
					setRadioactivityQualifier(value);
					break;
				case TransportIndex:
					setTransportIndex(value);
					break;
				case UnNumber:
				case UNNumber:    //EI20130821
					setUnNumber(value);
					break;	
				case CriticalHazardIndex:
					setCriticalHazardIndex(value);
					break;	
				case SheetNumber:
					setSheetNumber(value);
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
			return EBookingData.valueOf(token);
		 } catch (IllegalArgumentException e) {
			return null;
		 }
	 }

    public String getBfSNumber() {
		return bfsNumber;
	}
	public void setBfSNumber(String argument) {
		this.bfsNumber = argument;
	}					
		
	public String getPackagingType() {
		return packagingType;
	}
	public void setPackagingType(String argument) {
		this.packagingType = argument;
	}	
	
	public String getCathegory() {
		return cathegory;
	}
	public void setCathegory(String argument) {
		this.cathegory = argument;
	}	
		
	public String getRadioactivityLevel() {
		return radioactivityLevel;
	}
	public void setRadioactivityLevel(String argument) {
		this.radioactivityLevel = argument;
	}					
	
	public String getRadioactivityQualifier() {
		return radioactivityQualifier;
	}
	public void setRadioactivityQualifier(String argument) {
		this.radioactivityQualifier = argument;
	}
		
	public String getTransportIndex() {
		return transportIndex;
	}
	public void setTransportIndex(String argument) {
		this.transportIndex = argument;
	}
	
	public String getUnNumber() {
		return unNumber;
	}
	public void setUnNumber(String argument) {
		this.unNumber = argument;
	}		
		
	
	public String getCriticalHazardIndex() {
		return csi;
	}
	public void setCriticalHazardIndex(String argument) {
		this.csi = argument;
	}
	
	public String getSheetNumber() {
		return sheetNumber;
	}
	public void setSheetNumber(String argument) {
		this.sheetNumber = argument;
	}	
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.unNumber) && Utils.isStringEmpty(this.sheetNumber) && 
		        Utils.isStringEmpty(this.packagingType) && Utils.isStringEmpty(this.cathegory) &&
		        Utils.isStringEmpty(this.transportIndex) && Utils.isStringEmpty(this.radioactivityQualifier) &&	
		        Utils.isStringEmpty(this.bfsNumber) && Utils.isStringEmpty(this.csi) &&			       
		        Utils.isStringEmpty(this.transportIndex));  
	}	
}
