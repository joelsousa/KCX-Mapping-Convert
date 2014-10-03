package com.kewill.kcx.component.mapping.countries.de.emcs.msg;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module       : EMCS<br>
 * Created	    : 20.07.2011<br>
 * Description  : Contains Message Structure with fields used in GoodsItems of MsgExplanationOfReasonForShortage.
 *              : IE871 / C_SHR_EXP   
 * @author iwaniuk
 * @version 1.0.00
 */
public class MsgExplanationOfReasonForShortagePos extends KCXMessage {
	private String  itemNumber;
	private String  exciseProductCode;	
	private String	quantity;
	private Text	explanation;
	

	private enum EValidDeclarationPos {
		//KIDS						//UIDS
		ItemNumber,					BodyRecordUniqueReference,
		ExciseProductCode,	        //same	
		Quantity,					//same
		Explanation;	            //same	
	}
	
	public MsgExplanationOfReasonForShortagePos() {
		super();
	}

	public MsgExplanationOfReasonForShortagePos(XmlMsgScanner scanner)  {
		super(scanner);
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {			
			switch ((EValidDeclarationPos) tag) {		
			default:
					return;
			}
		} else {				
			switch ((EValidDeclarationPos) tag) {
				case ItemNumber:
				case BodyRecordUniqueReference:
					setItemNumber(value);
					break;
				
				case ExciseProductCode:
					setExciseProductCode(value);
					break;
								
				case Quantity:
					setQuantity(value);
					break;	
					
				case Explanation:
					//explanation = new Text(value, attr.getValue("language"));
					explanation = new Text(value, attr);  //EI20110926
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
			return EValidDeclarationPos.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(String itemNumber) {
		this.itemNumber = Utils.checkNull(itemNumber);
	}

	public String getExciseProductCode() {
		return exciseProductCode;	
	}
	public void setExciseProductCode(String exciseProductCode) {
		this.exciseProductCode = Utils.checkNull(exciseProductCode);
	}

	public String getQuantity() {
		return quantity;	
	}
	public void setQuantity(String quantity) {
		this.quantity = Utils.checkNull(quantity);
	}

	public Text getExplanation() {
		return explanation;	
	}
	public void setExplanation(Text text) {
		this.explanation = text;
	}	

}
