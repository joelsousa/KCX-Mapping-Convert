/*
 * Function    : LoadingTime (KIDS == ShipmentPeriod (UIDS)
 * Titel       :
 * Date        : 10.09.2008
 * Author      : Kewill CSF / Houdek
 * Description : Contains the LoadingTime Data
 * 			   : with all Fields used in UIDS and  KIDS
 * Parameters  :

 * Changes
 * -----------
 * Author      : EI
 * Date        : 15.05.2009
 * Label       :
 * Description : Kids/Uids checked - no changes
 */

package com.kewill.kcx.component.mapping.countries.de.aes.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: LoadingTime<br>
 * Erstellt		: 10.09.2008<br>
 * Beschreibung	: Contains the LoadingTime Data with all Fields used in UIDS and  KIDS.
 * 
 * @author Houdek
 * @version 1.0.00
 */
public class LoadingTime extends KCXMessage {

    private String beginTime         	 = "";
    private String endTime      	     = "";
    
    private EFormat loadingBeginFormat;
    private EFormat loadingEndFormat;
    
    private boolean debug   = false;

  	private enum ELoadingTime {
  		// Kids-TagNames, 				UIDS-TagNames;
		BeginTime,   					Begin,  
		EndTime,	 					End;
     }
  	
    public  LoadingTime() {
    	super();
    }

    public  LoadingTime(XmlMsgScanner scanner) {
  		super(scanner);
  	}

  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((ELoadingTime) tag) {
  			default:
  					return;
  			}
  		} else {
  			switch ((ELoadingTime) tag) {
  			
 				case BeginTime:
 				case Begin:
					if (tag == ELoadingTime.BeginTime) {
   					 	setLoadingBeginFormat(EFormat.KIDS_DateTime);
   				 	} else {
   				 		setLoadingBeginFormat(getUidsDateAndTimeFormat(value)); 
   				 	}
  					setBeginTime(value);
  					break;
  					
  				case EndTime:
  				case End:
					if (tag == ELoadingTime.EndTime) {
						setLoadingEndFormat(EFormat.KIDS_DateTime);
	   			 	} else {
	   				 	setLoadingEndFormat(getUidsDateAndTimeFormat(value)); 
	   			 	}
  					setEndTime(value);
  					break;
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}

  	public Enum translate(String token) {
  		try {
  			return ELoadingTime.valueOf(token);
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
  	
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public EFormat getLoadingBeginFormat() {
		return loadingBeginFormat;
	}

	public void setLoadingBeginFormat(EFormat loadingBeginFormat) {
		this.loadingBeginFormat = loadingBeginFormat;
	}

	public EFormat getLoadingEndFormat() {
		return loadingEndFormat;
	}

	public void setLoadingEndFormat(EFormat loadingEndFormat) {
		this.loadingEndFormat = loadingEndFormat;
	}
	public EFormat getUidsDateAndTimeFormat(String value) {  
		EFormat eFormat;
		 
		 if (!value.substring(10, 11).equals("T")) {
			 eFormat = EFormat.ST_DateTime;
		 } else if (value.length() < 20 || !value.substring(19, 20).equals("Z")) {
			 eFormat = EFormat.ST_DateTimeT;
		 } else {
			 eFormat = EFormat.ST_DateTimeTZ;
		 }
		return eFormat;
	}	
	
	public boolean isEmpty() {
		return Utils.isStringEmpty(beginTime) && Utils.isStringEmpty(endTime);
				       
	}
}
