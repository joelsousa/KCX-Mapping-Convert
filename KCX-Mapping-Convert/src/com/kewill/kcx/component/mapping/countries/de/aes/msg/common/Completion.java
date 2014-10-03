/*
 * Function    : Completion (KIDS) == WriteOffATLAS.WriteOffZL/AVUV (UIDS)
 * Titel       :
 * Date        : 10.09.2008
 * Author      : Kewill CSF / Houdek
 * Description : Contains the Business Data
 * 			   : with all Fields used in  KIDS
 * Parameters  :

 * Changes
 * -----------
 * Author      : EI
 * Date        : 12.03.2009
 * Label       : EI20090312
 * Description : V60 - added Member CompletionItemList
 * 			   : V60 - added UIDS-Tags
 *
 * Author      : EI
 * Date        : 15.05.2009
 * Description : Kids/Uids checked - no changes
 */

package com.kewill.kcx.component.mapping.countries.de.aes.msg.common;

import org.xml.sax.Attributes;
import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: Completion<br>
 * Erstellt		: 10.09.2008<br>
 * Beschreibung	: Contains the Business Data with all Fields used in  KIDS.
 * 
 * @author Houdek
 * @version 1.0.00
 */
public class Completion extends KCXMessage {

    private String totalNumberOfPositions;
    private String authorizationNumber;
    private String referenceNumber;
    private String checked;
    private CompletionItem completionItem;
    private List<CompletionItem> completionItemList;
    
    private boolean debug   = false;	
  	
	private enum ECompletion {
		// KIDS					// UIDS
		TotalNumberOfPositions,  //--
		AuthorizationNumber,    Allowance,
		ReferenceNumber,		Reference,
		Checked,    			//--
		CompletionItem,         WriteOffData;
   }

	public Completion() {
		super();  
		completionItemList    = new Vector<CompletionItem>();
	}
	
	public Completion(XmlMsgScanner scanner)  {
  		super(scanner);
  		completionItemList    = new Vector<CompletionItem>();
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((ECompletion) tag) {
  				case CompletionItem:
  				case WriteOffData:
  					completionItem = new CompletionItem(getScanner()); 
  					completionItem.parse(tag.name());
  					this.completionItemList.add(completionItem);  //EI20090305
  					break; 
  			default:
  					return;
  			}
  		} else {

  			switch ((ECompletion) tag) {
  				case TotalNumberOfPositions:
  					setTotalNumberOfPositions(value);
  					break;
  					
  				case AuthorizationNumber:
  				case Allowance:
  					setAuthorizationNumber(value);
  					break;
  					
  				case ReferenceNumber:
  				case Reference:
  					setReferenceNumber(value);
  					break;
 					
  				case Checked:
  					setChecked(value);
  					break;	 					
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}


  	public Enum translate(String token) {
  		try {
  			return ECompletion.valueOf(token);
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
  	
	public void setTotalNumberOfPositions(String argument) {
		this.totalNumberOfPositions = argument;
	}
	public String getTotalNumberOfPositions() {
		return totalNumberOfPositions;
	}

	public void setAuthorizationNumber(String argument) {
		this.authorizationNumber = argument;
	}
	public String getAuthorizationNumber() {
		return authorizationNumber;
	}

	public void setReferenceNumber(String argument) {
		this.referenceNumber = argument;
	}
	public String getReferenceNumber() {
		return referenceNumber;
	}
    
    public void setChecked(String argument) {
		this.checked = argument;
	}
	public String getChecked() {
		return checked;
	}
	
	public CompletionItem getCompletionItem() {
		return completionItem;
	}
    public void setCompletionItem(CompletionItem argument) {
		this.completionItem = argument;
	}
    
	public List<CompletionItem> getCompletionItemList() {
		return completionItemList;
	}
    public void setCompletionItemList(List<CompletionItem> argument) {
		this.completionItemList = argument;
	}    
    public void addCompletionItemList(CompletionItem argument) {
		this.completionItemList.add(argument);
	}     
}
