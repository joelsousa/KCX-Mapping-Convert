package com.kewill.kcx.component.mapping.companies.unisys.ics.msg.common;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: CustAwbInfo<br>
 * Erstellt		: 02.12.2010<br>
 * Beschreibung	: Conversion of Unisys to KIDS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class CustAwbInfo extends KCXMessage {
	
	private HashMap<String, String> enumMap = null;   
	
	private CustAwb custAWB;
	private Place origin;
	private Place dest;	
	private PartInfo partInfo;
	private CustTotals totals;                   
	private List<String> unNumbers;
	private List<String> uldNumbers;
	private List<String> descriptions;
	private List<String> commodity;
	private String payCode;	
	private List <Document> documentsList;
	
	 private enum ECustAwbInfo {
	 // Unisys-TagNames, 			KIDS-TagNames
		 CustAWB,
		 ORIGIN,
		 DEST,		
		 PartInfo,
		 TOTALS,         
		 UNNUMBERS,
		 ULDNUMBERS,		 
		 DESCRIPTIONS,		
		 COMMODITY,				   		
		 PayCode,	       	
		 DOCUMENTS;					   
	 }

	 private void initEnumMap() {
		    enumMap = new HashMap<String, String>();
		    enumMap.put("CUST-AWB", "CustAWB"); 
		    enumMap.put("PART-INFO", "PartInfo"); 
		    enumMap.put("PAY-CODE", "PayCode"); 		    	  
	 }	
	 
	 public CustAwbInfo() {
	      	super();
	      	initEnumMap(); 
	 }    
   
	 public CustAwbInfo(XmlMsgScanner scanner) {
		super(scanner);
		initEnumMap(); 
	 }

	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((ECustAwbInfo) tag) {
			case CustAWB:
				custAWB = new CustAwb(getScanner());  	
				//custAWB.parse(tag.name());
				custAWB.parse("CUST-AWB");
				break;	
			case ORIGIN:	
				origin = new Place(getScanner());
				origin.parse(tag.name());				
				break;
			case DEST:	
				dest = new Place(getScanner());
				dest.parse(tag.name());				
				break;
			case PartInfo:
				partInfo = new PartInfo(getScanner());
				//partInfo.parse(tag.name());
				partInfo.parse("PART-INFO");	
				break;
			case TOTALS:
				totals = new CustTotals(getScanner());
				totals.parse(tag.name());	
				break;
			case UNNUMBERS:
				StringList unList = new StringList(getScanner());
				unList.parse(tag.name());
				unNumbers = unList.getUnList();
				break;
			case ULDNUMBERS:		
				StringList uldList = new StringList(getScanner());
				uldList.parse(tag.name());
				uldNumbers = uldList.getUldList();
				break;
			case DESCRIPTIONS:
				StringList descList = new StringList(getScanner());
				descList.parse(tag.name());
				descriptions = descList.getDescList();
				break;
			case COMMODITY:				   		
				StringList htxList = new StringList(getScanner());
				htxList.parse(tag.name());
				commodity = htxList.getHtxList();
				break;
			case DOCUMENTS:	
				Document doc = new Document(getScanner());
				doc.parse(tag.name());
				addDocumentsList(doc);
				break;	
			default:
					return;
			}
		} else {

			switch ((ECustAwbInfo) tag) {
				case PayCode:
					setPayCode(value);
					break;  											
				default:
					break;
			}
		}
	}

	public void stoppElement(Enum tag) {
	}

	public Enum translate(String token) {
		String text = enumMap.get(token);  //MS
		if (text != null) {
			token = text;
		}
		try {
			return ECustAwbInfo.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public CustAwb getCustAWB() {
		return custAWB;	
	}
	public void setCustAWB(CustAwb argument) {
		this.custAWB = argument;
	}		
	 
	public Place getOrigin() {
		return origin;	
	}
	public void setOrigin(Place argument) {
		this.origin = argument;
	}
	
	public Place getDest() {
		return dest;	
	}
	public void setDest(Place argument) {
		this.dest = argument;
	}
	
	public PartInfo getPartInfo() {
		return partInfo;	
	}
	public void setDest(PartInfo argument) {
		this.partInfo = argument;
	}
	
	public CustTotals getTotals() {
		return totals;	
	}
	public void setTotals(CustTotals argument) {
		this.totals = argument;
	}
	
	public List<String> getUnNumbers() {
		return unNumbers;	
	}
	public void setUnNumbers(List<String> argument) {
		this.unNumbers = argument;
	}	 
	
	public List<String> getUldNumbers() {
		return uldNumbers;	
	}
	public void setUldNumbers(List<String> argument) {
		this.uldNumbers = argument;
	}
	
	public List<String> getDescriptions() {
		return descriptions;	
	}
	public void setDescriptions(List<String> argument) {
		this.descriptions = argument;
	}
	public String getDescription() {
		String description = "";
		if (this.descriptions != null)  {
			for (String desc : descriptions) {				
				description = description + desc;
				description = description + " ";
			}
		}
		return description;	
	}
	
	public List<String> getCommoditList() {
		return commodity;	
	}
	public void setCommodityList(List<String> argument) {
		this.commodity = argument;
	}
	public String getCommodityString() {
		String commodityStr = "";
		if (this.commodity != null)  {
			for (String comm : commodity) {				
				commodityStr = commodityStr + comm;
				commodityStr = commodityStr + " ";
			}
		}
		return commodityStr;	
	}
	
	public String getPayCode() {
		return payCode;	
	}
	public void setPayCode(String argument) {
		this.payCode = Utils.checkNull(argument);
	}
	
	public List<Document> getDocumentList() {
		return documentsList;	
	}
	public void setDocumentList(List <Document> argument) {
		this.documentsList = argument;
	}	
	private void addDocumentsList(Document argument) {
		if (documentsList == null) {
			documentsList = new Vector<Document>();
		}
		documentsList.add(argument);
	}
}
