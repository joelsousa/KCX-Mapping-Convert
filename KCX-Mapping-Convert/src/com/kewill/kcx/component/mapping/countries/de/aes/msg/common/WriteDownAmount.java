/*
 * Function    : WriteDownAmount(KIDS) == Writeoff (UIDS)
 * Titel       :
 * Date        : 10.09.2008
 * Author      : Kewill CSF / Houdek
 * Description : Contains the WriteDownAmount Data
 * 			   : with all Fields used in  KIDS/UIDS
 * Parameters  :

 * Changes
 * -----------
 * Author      : E.Iwaniuk
 * Date        : 10.03.2009
 * Label       : EI20090310
 * Description : new Member for V60: writeOffValue
 * 
 * -----------
 * Author      : AK
 * Date        : 12.03.2009
 * Label       : AK20090312 / AK20090408
 * Description : Qualifier removed / added
 *
 * Author      : EI
 * Date        : 18.05.2009
 * Label       :
 * Description : Kids/Uids checked - no changes
 * Author      : EI
 * Label       : EI200908
 * Description : Beendigungsanteile ZL, AVUV, Measurement heisst auch Unit,
 */

package com.kewill.kcx.component.mapping.countries.de.aes.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: WriteDownAmount<br>
 * Erstellt		: 10.09.2008<br>
 * Beschreibung	: Contains the WriteDownAmount Data with all Fields used in  KIDS/UIDS.
 * 
 * @author Houdek
 * @version 1.0.00
 */
public class WriteDownAmount extends KCXMessage {

	private String msgFlag;  	//"K" for Kids, "U" for UIDS    //EI20090430
    private String unitOfMeasurement;
    //private String valueKids;      			//n12,3
    private String valueUids; 					//EI20090430
    private String writeOffValue;    			//EI20090310
    private String qualifier; 		 			//AK20090408

    private boolean debug   = false;
    
    //unglückliche Namensgebung in der Definition von CT_WriteDownAmount:
    //KIDS-Value ist eigentlich writeOffValue
    //UIDS hat Value(==valueUids) - nur bei ProducedDocument.Writeoff - die landet bei KIDS direkt in Document.Value
    //UIDS hat WriteOffValue - die ist die eigentliche writeOffValue
    //also bleiben als member nur valueUIDS und writeOffValue, value von Kids wird in writeOffValue gespeichert
    //muss entsprechend in Bodys berücksichtigt werden!!!
 	private enum EWriteDownAmount {
		UnitOfMeasurement, Measurement, Unit,
		Value,				//same
						  WriteoffValue,
		Qualifier;			//-
    }      
         
    public WriteDownAmount() {
	    super();
    }
    public WriteDownAmount(String msgFlag) {
	    super();
	    this.msgFlag = msgFlag;
    }

    public WriteDownAmount(XmlMsgScanner scanner) {
  		super(scanner);
  	} 
    
    public WriteDownAmount(XmlMsgScanner scanner, String msgFlag) {
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
  						if (msgFlag.equals("U")) { //EI20090430
  							setValueUids(value);
  						} else {
  							//EI20090818: setValueKids(value);
  							setWriteoffValue(value);
  						}
  					}
  					break;
  					
  				case WriteoffValue:
  					setWriteoffValue(value);  					
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

 	public boolean isDebug() {
  		return debug;
  	}
  	public void setDebug(boolean debug) {
  		this.debug = debug;
  	}

	public String getUnitOfMeasurement() {
		return unitOfMeasurement;
	}
	public void setUnitOfMeasurement(String unitOfMeasurement) {
		this.unitOfMeasurement = unitOfMeasurement;
	}
/* EI20090818:
	public String getValueKids() {
		return valueKids;
	}
	public void setValueKids(String value) {
		this.valueKids = value;
	}
	*/
	public String getValueUids() {
		return valueUids;
	}
	public void setValueUids(String value) {
		this.valueUids = value;
	}
	
	public String getWriteoffValue() {
		return writeOffValue;
	}
	public void setWriteoffValue(String value) {
		this.writeOffValue = value;
	}

	public String getQualifier() {
		return qualifier;
	
	}

	public void setQualifier(String qualifier) {
		this.qualifier = Utils.checkNull(qualifier);
	}
}
