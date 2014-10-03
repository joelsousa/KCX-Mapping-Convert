package com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.msg;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.msg.common.CrnDeclnSecInput;
import com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.msg.common.DeclnCustomerSecInput;
import com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.msg.common.DeclnReasonSecInput;
import com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.msg.common.DeclnSecInput;
import com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.msg.common.ShpDeclnCmdtySecInput;
import com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.msg.common.ShpDeclnSecInput;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: FEDEX-Import.<br>
 * Created		: 29.10.2013<br>
 * Description	: MsgDeclnInput
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class MsgDeclnInput extends KCXMessage {

	private DeclnSecInput declSecInput; 
	private ShpDeclnSecInput shpDeclSecInput; 
	private ShpDeclnCmdtySecInput shpDeclCmdtySecInput; 
	private DeclnCustomerSecInput declCustSecInput; 
	private DeclnReasonSecInput declReasonSecInput; 
	private CrnDeclnSecInput crnDeclnSecInput;
	
	public MsgDeclnInput() {
		super();
	}

	public MsgDeclnInput(XMLEventReader parser) throws XMLStreamException {
		super(parser);
	}
	public MsgDeclnInput(XmlMsgScanner scanner)  {
		super(scanner);
	}
	
	private enum EDeclnInput {
		//DECLN_INPUT,
		Decln_Sec_Input,
		Shp_Decln_Sec_Input,
		Shp_Decln_Cmdty_Sec_Input,
		Decln_Customer_Sec_Input,
		Decln_Reason_Sec_Input,
		Crn_Decln_Sec_Input;		
	}
	


	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EDeclnInput) tag) {
			//case DECLN_INPUT:
			//	break;
			case Decln_Sec_Input: 	
				declSecInput = new DeclnSecInput(getScanner());  	
				declSecInput.parse(tag.name());
				break;
			
			case Shp_Decln_Sec_Input:	
				shpDeclSecInput = new ShpDeclnSecInput(getScanner());  	
				shpDeclSecInput.parse(tag.name());
				break;
		 	
			case Shp_Decln_Cmdty_Sec_Input:	
				shpDeclCmdtySecInput = new ShpDeclnCmdtySecInput(getScanner());  	
				shpDeclCmdtySecInput.parse(tag.name());
				break;
			
			case Decln_Customer_Sec_Input: 
				declCustSecInput = new DeclnCustomerSecInput(getScanner());  	
				declCustSecInput.parse(tag.name());
				break;
		 	
			case Decln_Reason_Sec_Input:	
				declReasonSecInput = new DeclnReasonSecInput(getScanner());  	
				declReasonSecInput.parse(tag.name());
				break;
				
			case Crn_Decln_Sec_Input:	
				crnDeclnSecInput = new CrnDeclnSecInput(getScanner());  	
				crnDeclnSecInput.parse(tag.name());
				break;	
				
			default:
					return;
			}
		} else {
			switch ((EDeclnInput) tag) {			
			
			default:
					return;
			}
		}
		
	}

	@Override
	public void stoppElement(Enum tag) {
	}

	@Override
	public Enum translate(String token) {
		try {
			return EDeclnInput.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public DeclnSecInput getDeclSecInput() {
		return declSecInput;
	}

	public void setDeclSecInput(DeclnSecInput declSecInput) {
		this.declSecInput = declSecInput;
	}

	public ShpDeclnSecInput getShpDeclSecInput() {
		return shpDeclSecInput;
	}

	public void setShpDeclSecInput(ShpDeclnSecInput shpDeclSecInput) {
		this.shpDeclSecInput = shpDeclSecInput;
	}

	public ShpDeclnCmdtySecInput getShpDeclCmdtySecInput() {
		return shpDeclCmdtySecInput;
	}

	public void setShpDeclCmdtySecInput(ShpDeclnCmdtySecInput shpDeclCmdSecInput) {
		this.shpDeclCmdtySecInput = shpDeclCmdSecInput;
	}

	public DeclnCustomerSecInput getDeclCustomerSecInput() {
		return declCustSecInput;
	}

	public void setDeclCustomerSecInput(DeclnCustomerSecInput declCustSecInput) {
		this.declCustSecInput = declCustSecInput;
	}

	public DeclnReasonSecInput getDeclReasonSecInput() {
		return declReasonSecInput;
	}

	public void setDeclReasonSecInput(DeclnReasonSecInput declReasonSecInput) {
		this.declReasonSecInput = declReasonSecInput;
	}		
	
	public CrnDeclnSecInput getCrnDeclnSecInput() {
		return crnDeclnSecInput;
	}

	public void setCrnDeclnSecInput(CrnDeclnSecInput crnDeclnSecInput) {
		this.crnDeclnSecInput = crnDeclnSecInput;
	}
	
	public boolean isEmpty() {
		return 	(declSecInput == null || declSecInput.isEmpty()) &&
				(shpDeclSecInput == null || shpDeclSecInput.isEmpty()) &&
				(shpDeclCmdtySecInput == null || shpDeclCmdtySecInput.isEmpty()) &&
				(declCustSecInput == null || declCustSecInput.isEmpty()) &&
				(declReasonSecInput == null || declReasonSecInput.isEmpty()) && 
				( crnDeclnSecInput == null ||  crnDeclnSecInput.isEmpty()); 
	}
}
