package com.kewill.kcx.component.mapping.countries.de.emcs.msg;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.EmcsTrader;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.ExciseMovementEaad;

/**
 * Module		: EMCS<br>
 * Created		: 20.07.2011<br>
 * Description  : Contains Message Structure with fields used in MsgExplanationOfReasonForShortage) 
 *              : IE871 / C_SHR_EXP.  
 *                 
 * @author iwaniuk
 * @version 1.0.00
 */

public class MsgExplanationOfReasonForShortage extends KCXMessage {

	private String msgName = "EMCSExplanationOnReasonForShortage";
	private String referenceNumber;
	private String customerOrderNumber;
	private String clerk;
	private String aadReferenceCode;	
	private String dateAndTimeOfValidation;
	private String sequenceNumber;
	private String dispatchImportOffice;
	private String submitterType;
	private String dateOfAnalysis;
	private Text globalExplanation;	
	private EmcsTrader  consignee;	
	private EmcsTrader  consignor;
	private List<MsgExplanationOfReasonForShortagePos> goodsItemList;
	
 
	private enum EExplDelay {
		//KIDS:									UIDS:
		ReferenceNumber,        				LocalReferenceNumber,
		CustomerOrderNumber,     				//not defined
		Clerk,                  			    //not defined  
		AadReferenceCode,						ExciseMovementEaad, //ExciseMovementEaad.AadReferenceCode
		DateAndTimeOfValidation,                                    //ExciseMovementEaad.DateAndTimeOfValidation
		SequenceNumber,                                             //ExciseMovementEaad.SequenceNumber
		DispatchImportOffice,              		//same
		SubmitterType, 							//same  
		DateOfAnalysis,                        	//same
		GlobalExplanation,						//same
		Consignee,                      		ConsigneeTrader,
		Consignor,				        		ConsignorTrader,
		GoodsItem,								BodyAnalysis;

	}
	
	public MsgExplanationOfReasonForShortage() {
		super();
	}

	public MsgExplanationOfReasonForShortage(XMLEventReader parser) throws XMLStreamException {
	super(parser);
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EExplDelay) tag) {
			case Consignee:	
			case ConsigneeTrader:
				consignee = new EmcsTrader(getScanner());
				consignee.parse(tag.name());				
				break;	
			case Consignor:	
			case ConsignorTrader:
				consignor = new EmcsTrader(getScanner());
				consignor.parse(tag.name());				
				break;	
			case ExciseMovementEaad:
				ExciseMovementEaad exciseMovementEaad = new ExciseMovementEaad(getScanner());
				exciseMovementEaad.parse(tag.name());
				setExciseMovementEaad(exciseMovementEaad);
				break;
			case GoodsItem:	
			case BodyAnalysis:
				MsgExplanationOfReasonForShortagePos item = new MsgExplanationOfReasonForShortagePos(getScanner());
				item.parse(tag.name());	
				addGoodsItemList(item);
				break;	
			default:
				return;
			}
	    } else {
	    	switch ((EExplDelay) tag) {
			case ReferenceNumber:		
			case LocalReferenceNumber:
				 setReferenceNumber(value);
				 break;	
			case CustomerOrderNumber:
				 setCustomerOrderNumber(value);
				 break;		
			case Clerk:
				 setClerk(value);
				 break;	
			case AadReferenceCode:
				 setAadReferenceCode(value);
				 break;		
			case SequenceNumber:
				 setSequenceNumber(value);
				 break;					 
			case GlobalExplanation:
				//globalExplanation = new Text(value, attr.getValue("language"));	
				globalExplanation = new Text(value, attr);  //EI20110926
				 break;	
			case DispatchImportOffice:
				 setDispatchImportOffice(value);
				 break;				 
			case SubmitterType:
				 setSubmitterType(value);
				 break;				
			case DateAndTimeOfValidation:			
				 setDateAndTimeOfValidation(value);
				 break;	
			case DateOfAnalysis:
				setDateOfAnalysis(value);
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
  			return EExplDelay.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}

	public String getMsgName() {
		return this.msgName;
	}
	public void setMsgName(String argument) {
		this.msgName = argument;
	}
	
	public String getReferenceNumber() {
		return this.referenceNumber;
	}
	public void setReferenceNumber(String argument) {
		this.referenceNumber = argument;
	}
	
	public String getCustomerOrderNumber() {
		return this.customerOrderNumber;
	}
	public void setCustomerOrderNumber(String argument) {
		this.customerOrderNumber = argument;
	}	

	public String getClerk() {
		return this.clerk;
	}
	public void setClerk(String argument) {
		this.clerk = argument;
	}
	
	public String getAadReferenceCode() {
		return this.aadReferenceCode;
	}
	public void setAadReferenceCode(String argument) {
		this.aadReferenceCode = argument;
	}	
	
	public String get() {
		return this.aadReferenceCode;
	}
	public void set(String argument) {
		this.aadReferenceCode = argument;
	}	
	
	public String getSequenceNumber() {
		return this.sequenceNumber;
	}
	public void setSequenceNumber(String argument) {
		this.sequenceNumber = argument;
	}
	
	public Text getGlobalExplanation() {
		return this.globalExplanation;
	}
	public void setGlobalExplanation(Text argument) {
		this.globalExplanation = argument;
	}
	
	public String getDateAndTimeOfValidation() {
		return this.dateAndTimeOfValidation;
	}
	public void setDateAndTimeOfValidation(String argument) {
		this.dateAndTimeOfValidation = argument;
	}	
	
	public String getSubmitterType() {
		return this.submitterType;
	}
	public void setSubmitterType(String argument) {
		this.submitterType = argument;
	}
	
	public String getDispatchImportOffice() {
		return this.dispatchImportOffice;
	}
	public void setDispatchImportOffice(String argument) {
		this.dispatchImportOffice = argument;
	}	
	
	public void setExciseMovementEaad(ExciseMovementEaad argument) {
		if (argument == null) {
			return;
		}		
		this.aadReferenceCode = argument.getAadReferenceCode();
		this.dateAndTimeOfValidation =	argument.getDateAndTimeOfValidationOfEaad();
		this.sequenceNumber = argument.getSequenceNumber();		
	}
	
	
	public String getDateOfAnalysis() {
		return this.dateOfAnalysis;
	}
	public void setDateOfAnalysis(String argument) {
		this.dateOfAnalysis = argument;
	}	
	
	public EmcsTrader getConsignee() {
		return this.consignee;
	}
	public void setConsignee(EmcsTrader argument) {
		this.consignee = argument;
	}
	
	public EmcsTrader getConsignor() {
		return this.consignor;
	}
	public void setConsignor(EmcsTrader argument) {
		this.consignor = argument;
	}
	
	public List<MsgExplanationOfReasonForShortagePos> getGoodsItemList() {
		return goodsItemList;	
	}

	
	public void setGoodsItemList(List<MsgExplanationOfReasonForShortagePos> list) {
		this.goodsItemList = list;	
	}	
	public void addGoodsItemList(MsgExplanationOfReasonForShortagePos item) {
		if (goodsItemList == null) {
			goodsItemList =  new Vector <MsgExplanationOfReasonForShortagePos>();	
		}
		this.goodsItemList.add(item);	
	}
	
}
