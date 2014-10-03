package com.kewill.kcx.component.mapping.countries.de.emcs.msg.common;

import java.util.List;
import java.util.Vector;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module       : EMCS<br>
 * Created		: 28.05.2010<br>
 * Description  : ExportCrossCheckingDiagnoses - only UIDS.                 
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class ExportCrossCheckingDiagnoses extends KCXMessage {

	private String localReferenceNumber;			
    private String documentReferenceNumber;
    private String   cancelExport;
    private List<Diagnosis> diagnosisList;     
    
    private enum eExportCrossCheckingDiagnoses {	
		//UIDS:  only   KIDS : are simple tags
		LocalReferenceNumber,			        
		DocumentReferenceNumber,     	        
		CancelExport, CancelExportFlag,	
		Diagnosis;	   		      								
   }	

	public ExportCrossCheckingDiagnoses() {
  		super();
  	}	
	 
	public ExportCrossCheckingDiagnoses(XmlMsgScanner scanner) {
  		super(scanner);
  	}

	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((eExportCrossCheckingDiagnoses) tag) {  			
				case Diagnosis:
					Diagnosis tempDiagnosis = new Diagnosis(getScanner());
					tempDiagnosis.parse(tag.name());	
					if (diagnosisList == null) {
						diagnosisList = new Vector<Diagnosis>();
					}
					diagnosisList.add(tempDiagnosis);
					break;									
				default:
  					return;
  			}
  		} else {
  			switch ((eExportCrossCheckingDiagnoses) tag) {   
  				case LocalReferenceNumber:
  					setLocalReferenceNumber(value);
					break;   			
  				case DocumentReferenceNumber:
  					setDocumentReferenceNumber(value);
  					break;   					    				  				
  				case CancelExport:
  				case CancelExportFlag:
  					setCancelExport(value);
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
  			return eExportCrossCheckingDiagnoses.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}

	public String getLocalReferenceNumber() {
		return localReferenceNumber;
	}
	public void setLocalReferenceNumber(String argument) {
		this.localReferenceNumber = argument;
	}

	public String getDocumentReferenceNumber() {
		return documentReferenceNumber;
	}
	public void setDocumentReferenceNumber(String argument) {
		this.documentReferenceNumber = argument;
	}
	
	public String getCancelExport() {
		return this.cancelExport;
	}
	public void setCancelExport(String argument) {
		this.cancelExport = argument;
	}
	
	public List<Diagnosis> getDiagnosisList() {
		return this.diagnosisList;
	}
	public void setDiagnosisList(List<Diagnosis> argument) {
		this.diagnosisList = argument;
	}
	
	public boolean isEmpty() {
			
		return (Utils.isStringEmpty(this.cancelExport) &&		
    	   (diagnosisList == null) &&
    		Utils.isStringEmpty(this.documentReferenceNumber)  &&   		
    		Utils.isStringEmpty(this.localReferenceNumber));			
	}
}
   
