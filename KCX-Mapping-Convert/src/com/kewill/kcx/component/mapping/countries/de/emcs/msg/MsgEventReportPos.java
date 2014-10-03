package com.kewill.kcx.component.mapping.countries.de.emcs.msg;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module	     : EMCS.<br>
 * Created		 : 21.07.2010<br>
 * Description   : Contains Message Structure with fields used in GoodsItems of MsgEventReport 
 *               : IE840 / C_EVT_DAT    
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class MsgEventReportPos extends KCXMessage {
	private String  itemNumber;
	private String  eventTypeCode;	
	private String  indicatorOfShortageOrExcess;
	private String	observedShortageOrExcess;
	private Text    associatedInformation;	

	private enum EReportOfReceiptPos {
		//KIDS						 //UIDS
		ItemNumber,					 //same
	    EventTypeCode,			 	 //same		
		IndicatorOfShortageOrExcess, //same
		ObservedShortageOrExcess,    //same
		AssociatedInformation;		 //same
	}
	
	public MsgEventReportPos() {
		super();
	}

	public MsgEventReportPos(XmlMsgScanner scanner)  {
		super(scanner);
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {			
			switch ((EReportOfReceiptPos) tag) {			
			default:
					return;
			}
		} else {				
			switch ((EReportOfReceiptPos) tag) {
				case ItemNumber:
					setItemNumber(value);
					break;				
				case EventTypeCode:
					setEventTypeCode(value);
					break;							
				case IndicatorOfShortageOrExcess:
					setIndicatorOfShortageOrExcess(value);
					break;						
				case ObservedShortageOrExcess:
					setObservedShortageOrExcess(value);
					break;	
				case AssociatedInformation:
					//associatedInformation = new Text(value, attr.getValue("language"));
					associatedInformation = new Text(value, attr);  //EI20110926
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
			return EReportOfReceiptPos.valueOf(token);
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

	public String getEventTypeCode() {
		return eventTypeCode;	
	}
	public void setEventTypeCode(String exciseProductCode) {
		this.eventTypeCode = Utils.checkNull(exciseProductCode);
	}	

	public String getIndicatorOfShortageOrExcess() {
		return indicatorOfShortageOrExcess;	
	}

	public void setIndicatorOfShortageOrExcess(String articleNumber) {
		this.indicatorOfShortageOrExcess = Utils.checkNull(articleNumber);
	}

	public String getObservedShortageOrExcess() {
		return observedShortageOrExcess;
	}

	public void setObservedShortageOrExcess(String commercialDescription) {
		this.observedShortageOrExcess = commercialDescription;
	}

	public Text getAssociatedInformation() {
		return associatedInformation;	
	}

	public void setAssociatedInformation(Text text) {
		this.associatedInformation = text;
	}
}
