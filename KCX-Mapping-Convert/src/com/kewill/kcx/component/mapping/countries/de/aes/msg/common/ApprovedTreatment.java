package com.kewill.kcx.component.mapping.countries.de.aes.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Export/aes<br>
 * Created		: 09.09.2008<br>
 * Description	: Contains Data of ApprovedTreatment/Procedure with all Fields used in KIDS and UIDS. 
 * 
 * @author krzoska
 * @version 1.0.00
 */
public class ApprovedTreatment extends KCXMessage {
	
	private String declared;   		
	private String previous;  
	private String current;   //EI20120709 new for KIDS V21
	private String national;   		
	private String national2;
	private String national3;
	private String codeForRefund;	
	private String community;
	private String community2;
	private String community3;

	private enum EApprovedTreatmentTags {
		//Kids-TagNames, 		UIDS-TagNames;
		Declared,				//same 
		Previous,				//same 
		Current,
		National,				Additional,		
		National2,
		National3,
		CodeForRefund,
		Community,
		Community2,
		Community3;          //ExportRestitutionItem.RestitutionProcedure
	}
	
    public ApprovedTreatment() {
		super();
	}
       
    public ApprovedTreatment(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {		
			switch ((EApprovedTreatmentTags) tag) {
			default:
					return;
			}
		} else {			
			switch ((EApprovedTreatmentTags) tag) {
				case Declared:
					setDeclared(value);
					break;						
				case Previous:
					setPrevious(value);
					break;	
				case Current:
					setCurrent(value);
					break;
				case National:
				case Additional:
					setNational(value);
					break;					
				case CodeForRefund:
					setCodeForRefund(value);
					break;					
				case National2:
					setNational2(value);
					break;	
				case National3:
					setNational3(value);
					break;	
				case Community:
					setCommunity(value);
					break;						
				case Community2:
					setCommunity2(value);
					break;	
				case Community3:
					setCommunity3(value);
					break;	
			}
		}
	}
	
	public void stoppElement(Enum tag) {
	}

	
	public Enum translate(String token) {
		try {
			return EApprovedTreatmentTags.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getDeclared() {
		return declared;
	}
	public void setDeclared(String declared) {
		this.declared = declared;
	}

	public String getPrevious() {
		return previous;
	}
	public void setPrevious(String previous) {
		this.previous = previous;
	}

	public String getNational() {
		return national;
	}
	public void setNational(String national) {
		this.national = national;
	}

	public String getCodeForRefund() {
		return codeForRefund;
	}
	public void setCodeForRefund(String codeForRefund) {
		this.codeForRefund = codeForRefund;
	}

	public String getNational2() {
		return national2;
	}
	public void setNational2(String national2) {
		this.national2 = Utils.checkNull(national2);
	}
	public String getNational3() {
		return national3;
	}
	public void setNational3(String national3) {
		this.national3 = Utils.checkNull(national3);
	}

	public String getCommunity() {
		return community;	
	}
	public void setCommunity(String community) {
		this.community = Utils.checkNull(community);
	}

	public String getCommunity2() {
		return community2;	
	}
	public void setCommunity2(String community2) {
		this.community2 = Utils.checkNull(community2);
	}

	public String getCommunity3() {
		return community3;	
	}
	public void setCommunity3(String community3) {
		this.community3 = Utils.checkNull(community3);
	}
	
	public String getCurrent() {
		return current;
	}
	public void setCurrent(String value) {
		this.current = value;
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(declared) && Utils.isStringEmpty(previous) && 
				Utils.isStringEmpty(current) && Utils.isStringEmpty(national) &&
				Utils.isStringEmpty(national2) && Utils.isStringEmpty(national3) &&
		        Utils.isStringEmpty(codeForRefund) && Utils.isStringEmpty(community) &&
		        Utils.isStringEmpty(community2) && Utils.isStringEmpty(community3)); 
	}
}
