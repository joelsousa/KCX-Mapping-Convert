package com.kewill.kcx.component.mapping.countries.de.Port.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Port<br>
 * Created		: 07.11.2011<br>
 * Description	: Description.     //fzptxt 
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Description extends KCXMessage {
			
	 private Text declarationRemark;  //AAI
	 private Text stockMarker;        //PAC_B
	 private Text loadMarker;         //PAC_V 
	 private Text sacoMarker;         //SAC
	 private Text itemRemark;         //AEA
	 private Text goodsRemark;        //AAA 
	 private Text packageRemark;      //PCI
	 
     //private String code;	
     //private String text;
     
    public Description() {
		super();  
    }

	public Description(XmlMsgScanner scanner) {
	  	super(scanner);
	}
	 
		 private enum EContainer {	
			// Kids-TagNames, 			KFF				FSS
			 DeclarationRemark,          
			 StockMarker,                              			
		 }	 
		 
		 public void startElement(Enum tag, String value, Attributes attr) {
			if (value == null) {		
				switch ((EContainer) tag) {
					default: return;			
				}
			} else {			
				switch ((EContainer) tag) {			
					case DeclarationRemark:				
						//setDeclarationRemark(value);
						break;
					case StockMarker:					
						//setTyp(value);
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

	    public Text getDeclarationRemark() {
			return declarationRemark;
		}
		public void setDeclarationRemark(Text argument) {
			this.declarationRemark = argument;
		}					
		
		/*
		public boolean isEmpty() {
			return (Utils.isStringEmpty(this.declarationRemark) && Utils.isStringEmpty(this.stockMarker) && 			     
			        Utils.isStringEmpty(this.itemRemark) && Utils.isStringEmpty(this.packageRemark));  
		}
		*/
}


