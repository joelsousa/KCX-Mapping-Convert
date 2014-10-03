package com.kewill.kcx.component.mapping.countries.common;

import javax.xml.stream.XMLEventReader;
import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: KCX all modules<br>
 * Created		: 11.09.2008<br>
 * Description	: Contains the SpecialMention Data used in KIDS  (Swiss).
 * 
 * @author ???
 * @version 1.0.00
 */
public class SpecialMention extends KCXMessage {

	 private String   typeOfExport     		= "";
	 private String   exportFromEU			= "";	 
	 private String   exportFromCountry	  	= ""; 
	 private String   text				  	= "";    //EI20121105: Edec Import 
	 private String   language			  	= "";	 
	 private String   code				  	= "";   //EI20100617 for ICS only
	 private String   export;                       //EI20130111 
	 private String   addInfo;                       //EI20130206 
	 private String   bansAndLimitations;   //EI20130821
	 
	 private enum ESpecialMentionTags {
		 //KIDS:					UIDS:
		 TypeOfExport,     		//NCTS: Code						//EdecItem.SpecialMentions
		 ExportFromEU,		    ExportFromEC,			//EdecItem.ECExportIdentifier	
		 Export,                //same 
		 ExportFromCountry,	    //same 							//EdecItem.ExportCountry
		 Text,                  //same
		 AdditionalInformation, AdditionalInfo,            //Edec Import 
		 Language,	            //--
		 Code,					//same    //EI20100617 for ICS only
		 BansAndLimitations, 	//-		  //EI20130821 added for NCTS
	 }
	 	
	 public SpecialMention() {   //EI20090414
			super();			
	 }
	 	
	 public SpecialMention(XmlMsgScanner scanner) {
	  		super(scanner);
	  	}	 
	 
	 public void startElement(Enum tag, String value, Attributes attr) {
			if (value == null) {
			
				switch ((ESpecialMentionTags) tag) {
				default:
						return;
				}
			} else {
				
				switch ((ESpecialMentionTags) tag) {
			
					case TypeOfExport:
						setTypeOfExport(value);
						break;
					
					case ExportFromEU:
					case ExportFromEC:  //EI20130104
						setExportFromEU(value);
						break;
					/*
					case City:
						setCity(value);
						break;
					*/
					case ExportFromCountry:
						setExportFromCountry(value);
						break;
						
					case Text:
						setText(value);
						break;
					case AdditionalInformation:   //EI20130206
					case AdditionalInfo:
						setAdditionalInfo(value);
						break;
						
					case Language:
						setLanguage(value);
						break;
						
					case Code:
						setCode(value);
						break;
						
					case Export:
						setExport(value);
						break;
						
					case BansAndLimitations:
						setBansAndLimitations(value);
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
				return ESpecialMentionTags.valueOf(token);
			} catch (IllegalArgumentException e) {
				return null;
			}
	}

	public String getTypeOfExport() {
		return typeOfExport;
	}
	public void setTypeOfExport(String typeOfExport) {
		this.typeOfExport = typeOfExport;
	}

	public String getExportFromEU() {
		return exportFromEU;
	}
	public void setExportFromEU(String exportFromEU) {
		this.exportFromEU = exportFromEU;
	}
   /* in xsd not defined  and not used in KCX
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
    */
	public String getExportFromCountry() {
		return exportFromCountry;
	}
	public void setExportFromCountry(String exportFromCountry) {
		this.exportFromCountry = exportFromCountry;
	}

	public String getText() {
		if (Utils.isStringEmpty(text)) {  //EI20130206: addInfo war ursprünglich auch in case zusammen mit Text
			text = addInfo;
		}
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = Utils.checkNull(language);
	}

	public String getCode() {
		return code;
	}
	public void setCode(String argument) {
		this.code = argument;
	}
	
	public String getExport() {
		return export;
	}
	public void setExport(String value) {
		this.export = value;
	}
	
	public String getAdditionalInfo() {		
		return addInfo;
	}
	public void setAdditionalInfo(String value) {
		this.addInfo = value;
	}
	
	public String getBansAndLimitations() {
		return bansAndLimitations;
	}
	public void setBansAndLimitations(String bansAndLimitations) {
		this.bansAndLimitations = bansAndLimitations;
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(typeOfExport) && Utils.isStringEmpty(exportFromEU) && 
		        Utils.isStringEmpty(exportFromCountry) && Utils.isStringEmpty(text) && 
		        Utils.isStringEmpty(this.language) && Utils.isStringEmpty(this.code)); 		   
	}	
}
