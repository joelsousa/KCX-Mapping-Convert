package com.kewill.kcx.component.mapping.countries.de.aes.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Export/aes<br>
 * Created		: 10.09.2008<br>
 * Description	: Contains the WriteDownAmount Data with all Fields used in  KIDS/UIDS.
 * 
 * @author Houdek
 * @version 1.0.00
 */
public class WriteDownAmountV20 extends KCXMessage {

	private String msgFlag;  	//"K",  "U" 
    private String unitOfMeasurement;
    private String value;      		  // is for UIDS WriteOff.WriteoffValue 
    private String documentValue;     // is for UIDS WriteOff.Value            		
    private String qualifier; 		 			

 	private enum EWriteDownAmount {
		UnitOfMeasurement, Measurement, Unit,
		Value,				//same => is in Kids Document.Value
						  WriteoffValue, //WriteoffValue isn't in Kids, but will be mapped in Kids WriteoffValue.Value  
		Qualifier;			//-
    }      
    /*     
 	KIDS:							UIDS:
 	.....
 	Document.Value					Document.WriteOff.Value
 	Document.WriteDownAmount.Value  Document.WriteOff.WriteoffValue
 	.....
 	*/
 	
 	//unglückliche Namensgebung in der Definition von CT_WriteDownAmount:
    //KIDS-Value ist eigentlich writeOffValue
    //UIDS hat Value(==valueUids) - nur bei ProducedDocument.Writeoff - die landet bei KIDS direkt in Document.Value
    //UIDS hat WriteOffValue - die ist die eigentliche writeOffValue
    //also bleiben als member nur valueUIDS und writeOffValue, value von Kids wird in writeOffValue gespeichert
    //muss entsprechend in Bodys berücksichtigt werden!!!
 	
    public WriteDownAmountV20() {
	    super();
    }
    public WriteDownAmountV20(String msgFlag) {
	    super();
	    this.msgFlag = msgFlag;
    }

    public WriteDownAmountV20(XmlMsgScanner scanner) {
  		super(scanner);
  	} 
    
    public WriteDownAmountV20(XmlMsgScanner scanner, String msgFlag) {
  		super(scanner);
  		this.msgFlag = msgFlag;
  	}
    
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EWriteDownAmount) tag) {
  			default:
  					return;
  			}
  		} else {
  			switch ((EWriteDownAmount) tag) {
  				case UnitOfMeasurement:
  				case Measurement:  //EI20090430
  				case Unit:         //EI20090818  
  					setUnitOfMeasurement(value);
  					break;
  				case Value:
  					if (msgFlag != null) {
  						if (msgFlag.equals("K")) { 
  							setValue(value);
  						} else if (msgFlag.equals("U")) {
  							setDocumentValue(value);  							
  						}
  					}
  					break;  					
  				case WriteoffValue:  //only UIDS: filling of Member allways from KIDS point of view!
  					setValue(value);  					
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
  			return EWriteDownAmount.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	public String getUnitOfMeasurement() {
		return unitOfMeasurement;
	}
	public void setUnitOfMeasurement(String unitOfMeasurement) {
		this.unitOfMeasurement = unitOfMeasurement;
	}

	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getDocumentValue() {
		return documentValue;
	}
	public void setDocumentValue(String value) {
		this.documentValue = value;
	}
		
	public String getQualifier() {
		return qualifier;	
	}
	public void setQualifier(String qualifier) {
		this.qualifier = Utils.checkNull(qualifier);
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(value) &&  Utils.isStringEmpty(qualifier) &&  
		        Utils.isStringEmpty(documentValue) && Utils.isStringEmpty(unitOfMeasurement));       
	}
}
