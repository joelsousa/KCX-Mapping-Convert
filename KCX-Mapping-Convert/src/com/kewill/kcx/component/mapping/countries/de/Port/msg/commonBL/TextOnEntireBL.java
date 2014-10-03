package com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Port - BillOfLading.<br>
 * Created		: 10.04.2012<br>
 * Description	:
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class TextOnEntireBL extends KCXMessage {
	
	private List<TextBL> billOfLadingRemarksList;
	private List<TextBL> packingMarkingInformationList;
	private List<TextBL> documentationInstructionsList;
	private TextBL goodsDimensionsInWords;
	private List<TextBL> additionalInformationList;
	private TextBL cargoRemarks;
	private TextBL complianceWasChecked;
	
	private enum ETextOnEntireBL {	
		BillOfLadingRemarks,
		PackingMarkingInformation,
		DocumentationInstructions,
		GoodsDimensionsInWords,
		AdditionalInformation,
		CargoRemarks,
		ComplianceWasChecked;
   }	

	public TextOnEntireBL() {
		super();  
	}

	public TextOnEntireBL(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((ETextOnEntireBL) tag) {
  				case BillOfLadingRemarks:
  					TextBL text1 = new TextBL(getScanner()); 
  					text1.parse(tag.name());  				
  					addBillOfLadingRemarksList(text1);  					
  					break;
				case PackingMarkingInformation:
					TextBL text2 = new TextBL(getScanner()); 
  					text2.parse(tag.name());
  					addPackingMarkingInformationList(text2);
  					break;
				case DocumentationInstructions:
					TextBL text3 = new TextBL(getScanner()); 
  					text3.parse(tag.name());
  					addDocumentationInstructionsList(text3);
  					break;
				case GoodsDimensionsInWords:
					goodsDimensionsInWords = new TextBL(getScanner());  	
					goodsDimensionsInWords.parse(tag.name());
  					break; 
				case AdditionalInformation:
					TextBL text4 = new TextBL(getScanner()); 
  					text4.parse(tag.name());
  					addAdditionalInformationList(text4);
  					break;
				case CargoRemarks:
					cargoRemarks = new TextBL(getScanner());  	
					cargoRemarks.parse(tag.name());
  					break; 
				case ComplianceWasChecked:
					complianceWasChecked = new TextBL(getScanner());  	
					complianceWasChecked.parse(tag.name());
  					break; 
				default:
  					break;
  			}
  		} else {
  			switch((ETextOnEntireBL) tag) {   			  				
  				default:
  					break;
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}

  	public Enum translate(String token) {
  		try {
  			return ETextOnEntireBL.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	
	public List<TextBL> getBillOfLadingRemarksList() {
		return billOfLadingRemarksList;
	}  
	public void setBillOfLadingRemarksList(List<TextBL> list) {
		billOfLadingRemarksList = list;
	}
	public void addBillOfLadingRemarksList(TextBL value) {
		if (billOfLadingRemarksList == null) {
			billOfLadingRemarksList = new ArrayList<TextBL>();
		}
		this.billOfLadingRemarksList.add(value);
	}
	public void setBillOfLadingRemarksList(Texte texte) {
		if (texte != null) {
			this.billOfLadingRemarksList = texte.getTextList();
		} else {
			this.billOfLadingRemarksList = null;
		}
	}	
	
	public List<TextBL> getPackingMarkingInformationList() {
		return packingMarkingInformationList;
	}    
	public void setPackingMarkingInformationList(List<TextBL> list) {
		this.packingMarkingInformationList = list;
	}	
	public void addPackingMarkingInformationList(TextBL value) {
		if (packingMarkingInformationList == null) {
			packingMarkingInformationList = new ArrayList<TextBL>();
		}
		this.packingMarkingInformationList.add(value);
	}
	public void setPackingMarkingInformationList(Texte texte) {
		if (texte != null) {
			this.packingMarkingInformationList = texte.getTextList();
		} else {
			this.packingMarkingInformationList = null;
		}
	}
	
	public List<TextBL> getDocumentationInstructionsList() {
		return documentationInstructionsList;
	}    
	public void setDocumentationInstructionsList(List<TextBL> list) {
		this.documentationInstructionsList = list;
	}	
	public void addDocumentationInstructionsList(TextBL value) {
		if (documentationInstructionsList == null) {
			documentationInstructionsList = new ArrayList<TextBL>();
		}
		this.documentationInstructionsList.add(value);
	}
	public void setDocumentationInstructionsList(Texte texte) {
		if (texte != null) {
			this.documentationInstructionsList = texte.getTextList();
		} else {
			this.documentationInstructionsList = null;
		}
	}
	
	public TextBL getGoodsDimensionsInWords() {
		return goodsDimensionsInWords;
	}    
	public void setGoodsDimensionsInWords(TextBL text) {
		this.goodsDimensionsInWords = text;
	}
	
	public List<TextBL> getAdditionalInformationList() {
		return additionalInformationList;
	}    
	public void setAdditionalInformationList(List<TextBL> list) {
		this.additionalInformationList = list;
	}	
	public void addAdditionalInformationList(TextBL value) {
		if (additionalInformationList == null) {
			additionalInformationList = new ArrayList<TextBL>();
		}
		this.additionalInformationList.add(value);
	}
	public void setAdditionalInformationList(Texte texte) {
		if (texte != null) {
			this.additionalInformationList = texte.getTextList();
		} else {
			this.additionalInformationList = null;
		}
	}
	
	public TextBL getCargoRemarks() {
		return cargoRemarks;
	}    
	public void setCargoRemarks(TextBL text) {
		this.cargoRemarks = text;
	}
	
	public TextBL getComplianceWasChecked() {
		return complianceWasChecked;
	}    
	public void setComplianceWasChecked(TextBL text) {
		this.complianceWasChecked = text;
	}
	
	public boolean isEmpty() {
		return (cargoRemarks == null  && complianceWasChecked == null && goodsDimensionsInWords == null &&
				packingMarkingInformationList == null && documentationInstructionsList == null &&
				billOfLadingRemarksList == null &&
				additionalInformationList == null);
				
	}
}
