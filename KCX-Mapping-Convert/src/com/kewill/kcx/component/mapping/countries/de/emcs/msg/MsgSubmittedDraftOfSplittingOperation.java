package com.kewill.kcx.component.mapping.countries.de.emcs.msg;

/**
Changes: new for V20: ComplementaryInformation
*/

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.SplitDetailsEAD;

/**
 * Module	    : EMCS<br>
 * Created		: 27.07.2011<br>
 * Description  : Contains Message Structure with fields used in EMCSSubmittedDraftOfSplittingOperation. 
 *              : IE825 / E_SPL_SUB.
 *                 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MsgSubmittedDraftOfSplittingOperation extends KCXMessage {

	private String  referenceNumber;
	private String  customerOrderNumber;
	private String  clerk;
	private String  upstreamARC;
	private String  memberStateCode;
	private List<SplitDetailsEAD> splitDetailsEADList;

  
    public MsgSubmittedDraftOfSplittingOperation()  {
		super();		
	}

	public MsgSubmittedDraftOfSplittingOperation(XMLEventReader parser) throws XMLStreamException {
		super(parser);		
	}

    private enum EMsgDeclaration {
    	
    //KIDS                                         //UIDS
    	ReferenceNumber,							LocalReferenceNumber,
    	CustomerOrderNumber,						//missing in UIDS						
    	Clerk,										//missing in UIDS
    	UpstreamARC,                                //same 
    	MemberStateCode,                            //same 
    	SplitDetailsEAD;                            //same
    }
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EMsgDeclaration) tag) {			
			
			case SplitDetailsEAD:
				SplitDetailsEAD tempSplit = new SplitDetailsEAD(getScanner());
				tempSplit.parse(tag.name());
				if (splitDetailsEADList == null) {
					splitDetailsEADList = new Vector <SplitDetailsEAD>();					
				}
				splitDetailsEADList.add(tempSplit);
				break;
			default:
				return;
			}
	    } else {
	    	switch ((EMsgDeclaration) tag) {
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
			case UpstreamARC:
				 setUpstreamARC(value);
				 break;				
			case MemberStateCode:
				 setMemberStateCode(value);
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
			return EMsgDeclaration.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getReferenceNumber() {
		return referenceNumber;	
	}

	public void setReferenceNumber(String value) {
		this.referenceNumber = value;
	}

	public String getCustomerOrderNumber() {
		return customerOrderNumber;	
	}

	public void setCustomerOrderNumber(String value) {
		this.customerOrderNumber = value;
	}

	public String getClerk() {
		return clerk;	
	}

	public void setClerk(String value) {
		this.clerk = value;
	}

	public String getUpstreamARC() {
		return upstreamARC;	
	}

	public void setUpstreamARC(String value) {
		upstreamARC = value;
	}

	public String getMemberStateCode() {
		return memberStateCode;	
	}

	public void setMemberStateCode(String value) {
		this.memberStateCode = value;
	}

	public List<SplitDetailsEAD> getSplitDetailsEADList() {
		return splitDetailsEADList;	
	}

	public void setSplitDetailsEADList(List<SplitDetailsEAD> list) {
		splitDetailsEADList = list;
	}	

	
}
