/*
 * Function    : EdecItem(UIDS)
 * Titel       :
 * Date        : 14.04.2009
 * Author      : Kewill CSF /iwaniuk
 * Description : Item Data for Swiss - filled Permit- and SpecialMentions-Fields for KIDS
 * 			   : 
 * Parameters  :

 * Changes
 * -----------
 * Author      : EI
 * Date        : 18.05.2009
 * Label       :
 * Description : Kids/Uids checked - no changes
 */

package com.kewill.kcx.component.mapping.countries.de.aes.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: EdecItem<br>
 * Erstellt		: 14.04.2009<br>
 * Beschreibung	: Item Data for Swiss - filled Permit- and SpecialMentions-Fields for KIDS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class EdecItem extends KCXMessage {

	private String cleranceCode;	
	private String cleranceTypeCode;  	
	private String specialMentions;    			
    private String ecExportIdentifier;
    private String exportCountry;   
    private String remark;
    private Permit allowance;

	private enum EEdecItem {
		//UIDS:					//KIDS:
		CleranceCode,			//TypeOfArticle
		CleranceTypeCode,       //TypeOfDeclaration
		SpecialMentions,		//SpecialMention.TypeOfExport
		ECExportIdentifier,		//SpecialMention.ExportFromEU
		ExportCountry,			//SpecialMention.ExportFromCountry
		Remark,					
		Allowance;				//Permit
   }

	private boolean debug   = false;

	public EdecItem() {
		super();  
	}
	
	public EdecItem(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public boolean isDebug() {
  		return debug;
  	}

  	public void setDebug(boolean debug) {
  		this.debug = debug;
  	}

  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EEdecItem) tag) {
  				case Allowance:
  					allowance = new Permit(getScanner());  	
  					allowance.parse(tag.name());
  					break;
  			default:
  					return;  			
  			}
  		} else {
  			switch ((EEdecItem) tag) {
  				case CleranceCode:
  					setCleranceCode(value);
  					break;
  				case CleranceTypeCode:
  					setCleranceTypeCode(value);
  					break;  				
  				case SpecialMentions:
  					setSpecialMentions(value);
  					break;  	  				
  				case ECExportIdentifier:
  					setECExportIdentifier(value);					
  					break; 
  				case ExportCountry:
  					setExportCountry(value);
  					break;
  				case Remark:
  					setRemark(value);
  					break;  					
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}


  	public Enum translate(String token) {
  		try {
  			return EEdecItem.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	public String getCleranceCode() {
		return cleranceCode;
	
	}
	public void setCleranceCode(String argument) {
		this.cleranceCode = argument;
	}

	public void setCleranceTypeCode(String argument) {
		this.cleranceTypeCode = argument;
	}
	public String getCleranceTypeCode() {
		return cleranceTypeCode;
	
	}

	public String getSpecialMentions() {
		return specialMentions;
	
	}
	public void setSpecialMentions(String argument) {
		this.specialMentions = argument;
	}

	public String getECExportIdentifier() {
		return ecExportIdentifier;
	
	}
	public void setECExportIdentifier(String argument) {
		this.ecExportIdentifier = argument;
	}

	public String getExportCountry() {
		return exportCountry;
	}

	public void setExportCountry(String argument) {
		this.exportCountry = argument;
	}
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String argument) {
		this.remark = argument;
	}
	
	public Permit getAllowance() {
		return allowance;
	}

	public void setAllowance(Permit argument) {
		this.allowance = argument;
	}	
}
