package com.kewill.kcx.component.mapping.countries.de.emcs.msg;

import java.util.List;
import java.util.Vector;
import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.UnsatisfactoryReason;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module	     : EMCS.<br>
 * Created		 : 11.05.2010<br>
 * Description   : Contains Message Structure with fields used in GoodsItems of MsgReportOfReceipt 
 *               : IE818 / C_DEL_DAT    
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class MsgReportOfReceiptPos extends KCXMessage {
	private String  				itemNumber;
	private String  				exciseProductCode;
	private String  				refusedQuantity;
	private String  				indicatorOfShortageOrExcess;
	private String					observedShortageOrExcess;
	private List<UnsatisfactoryReason> unsatisfactoryReasonList;	

	private enum EReportOfReceiptPos {
		//KIDS						 //UIDS
		ItemNumber,					 //same
		ExciseProductCode,			 //same
		RefusedQuantity,			 //same
		IndicatorOfShortageOrExcess, //same
		ObservedShortageOrExcess,	 //same
		UnsatisfactoryReason;		 //same
	}
	
	public MsgReportOfReceiptPos() {
		super();
	}

	public MsgReportOfReceiptPos(XmlMsgScanner scanner)  {
		super(scanner);
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {			
			switch ((EReportOfReceiptPos) tag) {
			case UnsatisfactoryReason:
				UnsatisfactoryReason tempReason = new UnsatisfactoryReason(getScanner());
				tempReason.parse(tag.name());
				if (unsatisfactoryReasonList == null)  {
					unsatisfactoryReasonList = new Vector <UnsatisfactoryReason>();
				}
				unsatisfactoryReasonList.add(tempReason);
			default:
					return;
			}
		} else {				
			switch ((EReportOfReceiptPos) tag) {
				case ItemNumber:
					setItemNumber(value);
					break;
				
				case ExciseProductCode:
					setExciseProductCode(value);
					break;
				
				case RefusedQuantity:
					setRefusedQuantity(value);
					break;
				
				case IndicatorOfShortageOrExcess:
					setIndicatorOfShortageOrExcess(value);
					break;						
				case ObservedShortageOrExcess:
					setObservedShortageOrExcess(value);
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

	public String getExciseProductCode() {
		return exciseProductCode;	
	}
	public void setExciseProductCode(String exciseProductCode) {
		this.exciseProductCode = Utils.checkNull(exciseProductCode);
	}

	public String getRefusedQuantity() {
		return refusedQuantity;	
	}
	public void setRefusedQuantity(String cnCode) {
		this.refusedQuantity = Utils.checkNull(cnCode);
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

	public List<UnsatisfactoryReason> getUnsatisfactoryReasonList() {
		return unsatisfactoryReasonList;	
	}

	public void setUnsatisfactoryReasonList(List<UnsatisfactoryReason> argument) {
		this.unsatisfactoryReasonList = argument;
	}
}
