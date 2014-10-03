/*
 * Function    : CompletionItem (KIDS) == WriteOffData (UIDS)
 * Titel       :
 * Date        : 10.09.2008
 * Author      : Kewill CSF / Houdek
 * Description : Contains the Business Data
 * 			   : with all Fields used in  KIDS
 * Parameters  :

 * Changes
 * -----------
 * Author      : EI
 * Date        : 05.03.2009
 * Label       :
 * Description : renamed setTradeAmountg() into setTradeAmount()
 *             : V60 - added UIDS-Tags
 *             
 *             * Author      : EI
 * Date        : 15.05.2009
 * Description : Kids/Uids checked - no changes
 */

package com.kewill.kcx.component.mapping.countries.de.aes.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: CompletionItem<br>
 * Erstellt		: 10.09.2008<br>
 * Beschreibung	: Contains the Business Data with all Fields used in  KIDS.
 * 
 * @author Houdek
 * @version 1.0.00
 */
public class CompletionItem extends KCXMessage {

	private String sequentialNumber;
	private String positionNumber;
	private String ucr;
	private String entryInAtlas;
	private String text;
	private String tarifNumber;
	private String usualFormOfHandling;
	private WriteDownAmount writeDownAmount = null; 		//n(12,3)
	private WriteDownAmount tradeAmount = null;			//gibt es noch nicht

  	private enum ECompletionItem {
		SequentialNumber,		//--
		PositionNumber,			WriteOffItemNumber,
		UCR,					WriteOffRegNumber,
		EntryInAtlas,			ATLASCode,
		Text,					AdditionalText,
		Tarifnumber, TarifNumber,		WriteOffTariffCode,
		UsualFormOfHandling,	HandlingCode,
		WriteDownAmount, WritedownAmount, WriteOffUnit, WriteOffValue, //WriteOffUnitQualifier,
		TradeAmount, HandlingUnit, HandlingValue; //HandlingUnitQualifier; 
   }
  	
	private boolean debug = false;

    public CompletionItem() {
    	super();    		
     }
    public CompletionItem(XmlMsgScanner scanner) {
  		super(scanner);  		
  	}  	
  	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((ECompletionItem) tag) {
  			
  				case WriteDownAmount:
  				case WritedownAmount:
					writeDownAmount = new WriteDownAmount(getScanner(), "K");
					writeDownAmount.parse(tag.name());
					break;
					
				case TradeAmount:  
					tradeAmount = new WriteDownAmount(getScanner(), "K");
					tradeAmount.parse(tag.name());
					break;  
					
  			default:
  					return;
  			}
  		} else {

  			switch ((ECompletionItem) tag) {

  				case SequentialNumber:
  					setSequentialNumber(value);
  					break;
  					
  				case PositionNumber:
  				case WriteOffItemNumber:
  					setPositionNumber(value);
  					break;
  					
  				case UCR:
  				case WriteOffRegNumber:
  					setUCR(value);
  					break;
  					
  				case EntryInAtlas:
  				case ATLASCode:
  					setEntryInAtlas(value);
  					break;
  					
  				case Text:
  				case AdditionalText:
  					setText(value);
  					break;
  				case Tarifnumber:
  				case TarifNumber:
  				case WriteOffTariffCode:
  					setTarifNumber(value);
  					break;
  					
  				case UsualFormOfHandling:
  				case HandlingCode:
  					setUsualFormOfHandling(value);
  					break;
  					
  				case WriteOffUnit:
  					if (writeDownAmount == null) {
  						writeDownAmount = new WriteDownAmount("U");
  					}
  					writeDownAmount.setUnitOfMeasurement(value); //EI20090430 TODO ist es richtig???
  					break;
  					
  				case WriteOffValue:	
  					if (writeDownAmount == null) { 
  						writeDownAmount = new WriteDownAmount("U");
  					}
  					//EI20090818: writeDownAmount.setValueUids(value);
  					writeDownAmount.setWriteoffValue(value);
  					break;
  					
  				case HandlingUnit:
  					if (tradeAmount == null) {
  						tradeAmount = new WriteDownAmount("U");
  					}
  					tradeAmount.setUnitOfMeasurement(value);
  					break;
  				case HandlingValue:
  					if (tradeAmount == null) {
  						tradeAmount = new WriteDownAmount("U");
  					}
  				//EI20090818: tradeAmount.setValueUids(value);
  					tradeAmount.setWriteoffValue(value);
  					break;
  			}
  		}
  	}
  	
  	public void stoppElement(Enum tag) {
  	}

  	public Enum translate(String token) {
  		try {
  			return ECompletionItem.valueOf(token);
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

	public String getSequentialNumber() {
		return sequentialNumber;
	}
	public void setSequentialNumber(String argument) {
		this.sequentialNumber = argument;
	}
	
	public String getPositionNumber() {
		return positionNumber;
	}
	public void setPositionNumber(String argument) {
		this.positionNumber = argument;
	}
	
	public String getUCR() {
		return ucr;
	}
	public void setUCR(String argument) {
		this.ucr = argument;
	}

	public String getEntryInAtlas() {
		return entryInAtlas;
	}
	public void setEntryInAtlas(String argument) {
		this.entryInAtlas = argument;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String argument) {
		this.text = argument;
	}
	
	public String getTarifNumber() {
		return tarifNumber;
	}
	public void setTarifNumber(String argument) {
		this.tarifNumber = argument;
	}
	
	public String getUsualFormOfHandling() {
		return usualFormOfHandling;
	}
	public void setUsualFormOfHandling(String argument) {
		this.usualFormOfHandling = argument;
	}
	public WriteDownAmount getWriteDownAmount() {
		return writeDownAmount;
	}
	public void setWriteDownAmount(WriteDownAmount argument) {
		this.writeDownAmount = argument;
	}
	
	public WriteDownAmount getTradeAmount() {
		return tradeAmount;
	}
	public void setTradeAmount(WriteDownAmount argument) {
		this.tradeAmount = argument;
	}	
}
