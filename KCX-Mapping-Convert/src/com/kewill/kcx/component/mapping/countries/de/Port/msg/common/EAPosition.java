package com.kewill.kcx.component.mapping.countries.de.Port.msg.common;

import org.xml.sax.Attributes;

import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsADR;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsAED;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsAEP;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Port<br>
 * Created		: 24.10.2011<br>
 * Description	: EAD Export A... Document.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class EAPosition extends KCXMessage {
					 
	 private String sequenceNumber;	 //posnr Positionsnummer des Hafenauftrags 	 	
	 private String eaPositionNumber;	     // aepos KEY; Pos-Nr der AE
	 private String tarifCode;	     // tnr Warentarifnummer
	 private String description;	 // wbsch 
	 private String netMass;         // eigmas    
	 private String grossMass;       // rohmas  
	 private String procedureCode;	 // cdevfr Verfahrenscode
	 private List <String> annotationList;	     // verme1, 2, 3
	
    public EAPosition() {
		super();  
    }

	public EAPosition(XmlMsgScanner scanner) {
	  	super(scanner);
	}
	 
		 private enum EContainer {	
				// Kids-TagNames, 			UIDS-TagNames;
			 SequenceNumber,
			 EAPositionNumber,      
			 TarifCode,   		
			 Description,   
			 GrossMass,				   
			 NetMass,  
			 ProcedureCode,			 
			 Annotation;			        			
		 }	 
		 
		 public void startElement(Enum tag, String value, Attributes attr) {
			if (value == null) {		
				switch ((EContainer) tag) {
					default: return;			
				}
			} else {			
				switch ((EContainer) tag) {			
					case EAPositionNumber:
						setEaPositionNumber(value);
						break;
					case SequenceNumber:
						setSequenceNumber(value);
						break;
					case TarifCode:
						setTarifCode(value);
						break;								
					case NetMass:
						setNetMass(value);
						break;					
					case GrossMass:
						setGrossMass(value);
						break;
					case Description:
						setDescription(value);
						break;
					case ProcedureCode:
						setProcedureCode(value);
						break;					
					case Annotation:
						addAnnotationList(value);
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
				return EContainer.valueOf(token);
			 } catch (IllegalArgumentException e) {
				return null;
			 }
		 }

	    public String getEaPositionNumber() {
			return eaPositionNumber;
		}
		public void setEaPositionNumber(String argument) {
			this.eaPositionNumber = argument;
		}					
		
		public String getSequenceNumber() {
			return sequenceNumber;
		}
		public void setSequenceNumber(String argument) {
			this.sequenceNumber = argument;
		}
		 
	    public String getTarifCode() {
			return tarifCode;
		}
		public void setTarifCode(String argument) {
			this.tarifCode = argument;
		}					
	 
		public String getDescription() {
			return description;
		}
		public void setDescription(String argument) {
			this.description = argument;
		}	
		   		
		public String getGrossMass() {
			return grossMass;
		}
		public void setGrossMass(String argument) {
			this.grossMass = argument;
		}

		public String getNetMass() {
			return netMass;
		}
		public void setNetMass(String argument) {
			this.netMass = argument;
		}
		
	    public String getProcedureCode() {
			return procedureCode;
		}
		public void setProcedureCode(String argument) {
			this.procedureCode = argument;
		}					
			
		public List<String> getAnnotationList() {
			return annotationList;
		}
		public void setAnnotationList(List<String> list) {
			this.annotationList = list;
		}	
	
		public void addAnnotationList(String argument) {
			if (annotationList == null) {
				annotationList = new Vector<String>();
			}
			this.annotationList.add(argument);
		}

}


