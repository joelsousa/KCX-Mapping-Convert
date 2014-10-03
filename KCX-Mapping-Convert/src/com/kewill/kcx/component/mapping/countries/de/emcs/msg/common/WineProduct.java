package com.kewill.kcx.component.mapping.countries.de.emcs.msg.common;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: EMCS.<br>
 * Created		: 03.05.2010<br>
 * Description  : Contains Message Structure of WineProduct with fields used in EMCSDeclaration.             
 * 
 * @author krzoska
 * @version 1.0.00
 */

public class WineProduct extends KCXMessage {
	
	private String					wineGrowingZoneCode;
	private String  				wineProductCategory;
	private String  				thirdCountryOfOrigin;
	private Text  					otherInformation;
	//private String  				wineOperationCode;
	private List <String>			wineOperationCodeList;
	
	private enum EWineProduct {
		//KIDS						//UIDS are same tags
		WineGrowingZoneCode,
		WineProductCategory,
		ThirdCountryOfOrigin,
		OtherInformation,
		WineOperationCode,			WineOperation;		
	}
	
	public WineProduct() {
  		super();
  	}

	public WineProduct(XmlMsgScanner scanner) {
  		super(scanner);
  	}	

	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {			
			switch ((EWineProduct) tag) {			
			default:
				return;
			}
		} else {				
			switch ((EWineProduct) tag) {
				case WineGrowingZoneCode:
					setWineGrowingZoneCode(value);
					break;
				
				case WineProductCategory:
					setWineProductCategory(value);
					break;
				
				case ThirdCountryOfOrigin:
					setThirdCountryOfOrigin(value);
					break;
				
				case OtherInformation:
					//otherInformation = new Text(value, attr.getValue("language"));
					otherInformation = new Text(value, attr);  //EI20110926
					break;

				case WineOperationCode:
				case WineOperation:
					if (wineOperationCodeList == null) {
						wineOperationCodeList = new Vector <String>(); 
					}
					wineOperationCodeList.add(value);
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
			return EWineProduct.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	
	public String getWineGrowingZoneCode() {
		return wineGrowingZoneCode;
	}

	public void setWineGrowingZoneCode(String wineGrowingZoneCode) {
		this.wineGrowingZoneCode = Utils.checkNull(wineGrowingZoneCode);
	}

	public String getWineProductCategory() {
		return wineProductCategory;
	
	}

	public void setWineProductCategory(String wineProductCategory) {
		this.wineProductCategory = Utils.checkNull(wineProductCategory);
	}

	public String getThirdCountryOfOrigin() {
		return thirdCountryOfOrigin;
	
	}

	public void setThirdCountryOfOrigin(String thirdCountryOfOrigin) {
		this.thirdCountryOfOrigin = Utils.checkNull(thirdCountryOfOrigin);
	}

	public Text getOtherInformation() {
		return otherInformation;
	
	}

	public void setOtherInformation(Text otherInformation) {
		this.otherInformation = otherInformation;
	}

	public void setWineOperationCodeList(List<String> argument) {
		this.wineOperationCodeList = argument;
	}

	public List<String> getWineOperationCodeList() {
		return wineOperationCodeList;
	
	}
	public void addWineOperationCodeList(String argument) {
		if (wineOperationCodeList == null) {
			wineOperationCodeList = new Vector <String>(); 
		}
		wineOperationCodeList.add(argument);	
	}	

	public boolean isEmpty() {
		
		int listSize = 0;
		String text = "";
		if (this.otherInformation != null) {
			text = this.otherInformation.getText();
		}
		if (this.wineOperationCodeList != null) {
			listSize = this.wineOperationCodeList.size();
		}
		return (Utils.isStringEmpty(this.wineGrowingZoneCode) &&
				Utils.isStringEmpty(this.wineProductCategory) &&
				Utils.isStringEmpty(this.thirdCountryOfOrigin) &&			
				Utils.isStringEmpty(text) && (listSize == 0));    					
	}	
}


