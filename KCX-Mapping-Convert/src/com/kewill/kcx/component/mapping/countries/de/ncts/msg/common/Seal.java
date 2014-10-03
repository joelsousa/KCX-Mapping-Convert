/*
 * Function    : Seal.java
 * Titel       :
 * Date        : 10.09.2008
 * Author      : Kewill CSF / Houdek
 * Description : Contains the Seal Data
 * 			   : with all Fields used in KIDS
 * Parameters  :

 * Changes
 * -----------
 * Author      : EI
 * Date        :
 * Label       : 18.05.2009
 * Description : Kids/Uids  checked - no changes
 */

package com.kewill.kcx.component.mapping.countries.de.ncts.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.SealNumber;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;
import java.util.List;
import java.util.Vector;

/**
 * Module		: Seal
 * Created		: 02.09.2010
 * Description	: contain Seal data with all fields used in KIDS/UIDS.
 * 
 * @author Lassiter
 * @version 4.0.00
 */

public class Seal extends KCXMessage {

    private String kind;  						
    private String number;						
    private String useOfTydenseals;            
    private String useOfTydensealStock;
    private List <SealNumber> sealNumbersList;
    
    private boolean debug   = false;

    public Seal() {
    	super();    	    
    }

    public Seal(XmlMsgScanner scanner) {
  		super(scanner);  		
  	}

  	private enum ESeal {
  		//Kids                  //UIDS
		Kind,					SealsType,
		Number,					Quantity,
		UseOfTydenSeals,		TydenSealFlag,
		UseOfTydenSealStock,	TydenSealStockFlag,	
		SealNumbers;
	}

  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((ESeal) tag) {  
  			case SealNumbers:
  				SealNumber sealNumber = new SealNumber(getScanner());
  				sealNumber.parse(tag.name());
				if (sealNumbersList == null) {
					sealNumbersList = new Vector<SealNumber>();
				}
				sealNumbersList.add(sealNumber);
				break;
  			default:
					return;	
  			}
  		} else {
  			switch ((ESeal) tag) {
	  			case TydenSealFlag:
	  			case UseOfTydenSeals:
	  				setUseOfTydenseals(value);
	  				break;
	  			case TydenSealStockFlag:
	  			case UseOfTydenSealStock:
	  				setUseOfTydensealStock(value);
	  				break;
	  			case Quantity:
	  			case Number:
	  				setNumber(value);
	  				break;
	  			case Kind:
	  			case SealsType:
	  				setKind(value);
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
  			return ESeal.valueOf(token);
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
  	
	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getUseOfTydenseals() {
		return useOfTydenseals;
	}

	public void setUseOfTydenseals(String useOfTydenseals) {
		this.useOfTydenseals = useOfTydenseals;
	}

	public String getUseOfTydensealStock() {
		return useOfTydensealStock;
	}

	public void setUseOfTydensealStock(String useOfTydensealStock) {
		this.useOfTydensealStock = useOfTydensealStock;
	}

	public List<SealNumber> getSealNumbersList() {
		return sealNumbersList;
	}

	public void setSealNumbersList(List<SealNumber> sealNumbersList) {
		this.sealNumbersList = sealNumbersList;
	}
	public void addSealNumberList(SealNumber argument) {
		if (sealNumbersList == null) {
			sealNumbersList = new Vector<SealNumber>();
		}
		this.sealNumbersList.add(argument);
	}

	
	public boolean isEmpty() {
		
		return (Utils.isStringEmpty(this.kind) && 
				Utils.isStringEmpty(this.number) && 
				Utils.isStringEmpty(this.useOfTydenseals) && 
				Utils.isStringEmpty(this.useOfTydensealStock) && 
				(this.sealNumbersList == null || 
						(this.sealNumbersList != null && this.sealNumbersList.isEmpty())) && 
						(this.sealNumbersList == null || 
								(this.sealNumbersList != null && 
										this.sealNumbersList.isEmpty())));
	}
	
	public boolean isTydenSealsEmpty() {
		
		return Utils.isStringEmpty(this.number) && 
			   Utils.isStringEmpty(this.useOfTydenseals) && 
			   Utils.isStringEmpty(this.useOfTydensealStock); 
				
	}
}