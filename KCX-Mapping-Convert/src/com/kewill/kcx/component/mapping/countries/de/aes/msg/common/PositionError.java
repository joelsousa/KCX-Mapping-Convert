/*
 * Function    : PositionError (KIDS) == no UIDS-Class (Msg Fault)
 * Titel       :
 * Date        : 25.09.2008
 * Author      : Kewill CSF / Christine Kron
 * Description : Contains Error Text fields
 * 			   : with all Fields used in  KIDS
 * Parameters  :

 * Changes
 * ------------
 * Author      : EI
 * Date        : 23.04.2009
 * Label       : EI20090423
 * Description : errorNumber added
 *
 * Author      : EI
 * Date        : 18.05.2009
 * Description : Kids/Uids  checked - no changes
 */

package com.kewill.kcx.component.mapping.countries.de.aes.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: PositionError<br>
 * Erstellt		: 25.09.2008<br>
 * Beschreibung	: Contains Error Text fields with all Fields used in KIDS.
 * 
 * @author Kron
 * @version 1.0.00
 */
public class PositionError extends KCXMessage {

	private String errorNumber;   //EI20090423 
									// CK 9.12.2011 laufende Nummer oder zugehörige Positionsnummer
									// wird in BodyLocalAppResultKids in das xml tag "ItemNumber" geschrieben
									
	private String kindOfError;	
	private String originOfError;			
	private String modul;			
	private String errorCode;			
	private String errorText;			
	private String dateOfErrorMessage;
	private String timeOfErrorMessage;
	
    private boolean debug   = false;

    public PositionError() {
    	super();    		
    }
    
    public PositionError(XmlMsgScanner scanner) {
  		super(scanner);
  	}

	private enum EPositionError {
		// Kids-TagNames, 		
		KindOfError,
		OriginOfError,
		Modul,
		ErrorCode,
		ErrorText,
		DateOfErrorMessage,
		TimeOfErrorMessage;
    }
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EPositionError) tag) {
  			default:
  				return;
  			}
  		} else {
  			switch ((EPositionError) tag) {
  			case KindOfError:
				setKindOfError(value);
				break;
			case OriginOfError:
				setOriginOfError(value);
				break;
			case Modul:
				setModul(value);
				break;
			case ErrorCode:
				setErrorCode(value);
				break;
			case ErrorText:
				setErrorText(value);
				break;
			case DateOfErrorMessage:
				setDateOfErrorMessage(value);
				break;
			case TimeOfErrorMessage:
				setTimeOfErrorMessage(value);
				break;
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}

  	public Enum translate(String token) {
  		try {
  			return EPositionError.valueOf(token);
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

	public String getKindOfError() {
		return kindOfError;
	}

	public void setKindOfError(String kindOfError) {
		this.kindOfError = kindOfError;
	}

	public String getOriginOfError() {
		return originOfError;
	}

	public void setOriginOfError(String originOfError) {
		this.originOfError = originOfError;
	}

	public String getModul() {
		return modul;
	}

	public void setModul(String modul) {
		this.modul = modul;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorText() {
		return errorText;
	}

	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}

	public String getDateOfErrorMessage() {
		return dateOfErrorMessage;
	}

	public void setDateOfErrorMessage(String dateOfErrorMessage) {
		this.dateOfErrorMessage = dateOfErrorMessage;
	}

	public String getTimeOfErrorMessage() {
		return timeOfErrorMessage;
	}

	public void setTimeOfErrorMessage(String timeOfErrorMessage) {
		this.timeOfErrorMessage = timeOfErrorMessage;
	}
		
	public String getErrorNumber() {
		return errorNumber;
	}

	public void setErrorNumber(String argument) {
		this.errorNumber = argument;
	}
  	
}
