package com.kewill.kcx.component.mapping.countries.de.aes.msg.common;

import javax.xml.stream.XMLEventReader;
import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Export/aes<br>
 * Created		: 15.09.2008<br>
 * Description	: Contains the Customsoffices Data with all Fields used in UIDS (and KIDS).
 * 
 * @author Houdek
 * @version 1.0.00
 */

public class CustomsOffices extends KCXMessage {
	
	private String officeOfExport; 
    private String officeOfAdditionalDeclarationExport; 
    private String officeOfExit;
    private String officeOfActualExit;	
    private String officeOfTransit;			
	private String officeOfCommunityExit;   
	private String officeOfNationalExit;
	private String supervisingCustomsOffice; //EI20120711
	private String officeOfPresentation;     //EI20121211
	private String officeAtBorder;			 //AK20130524
	private String officeOfFirstEntry;         //EI20130711
	private String declaredOfficeOfFirstEntry; //EI20130711	
	
    private boolean debug   = false;
    private XMLEventReader 	parser	= null;

	private enum ECustomsOffices {
		  // Kids-TagNames, 			UIDS-TagNames
		  //CustomsOfficeExport,		OfficeOfExport,
		  //CustomsOfficeForCompletion,	OfficeOfAdditionalDeclarationExport,
		  //RealOfficeOfExit,			OfficeOfExit,
		  //IntendedOfficeOfExit,		OfficeOfActualExit,
										OfficeOfExport,
										OfficeOfAdditionalDeclarationExport,
										OfficeOfExit,
										OfficeOfActualExit,		  
			 							OfficeOfTransit,
			 							OfficeOfCommunityExit,
			 							OfficeOfNationalExit,
			 							OfficeOfRequest,
			 							OfficeOfActualExport,
			 							OfficeAtBorder,           //AK20130524
			 							OfficeOfPayment,
			 							OfficeOfDeparture,
			 							OfficeOfGuarantee,
			 							OfficeOfPresentation,  
			 							OfficeOfFirstEntry,         //EI20130711
			 							DeclaredOfficeOfFirstEntry, //EI20130711			 							
			 							OfficeOfValidation,
		  								OfficeOfControl,
		  								SupervisingCustomsOffice;   //EI20120711
     }

      public CustomsOffices() {
	      	super();
      }

      public CustomsOffices(XMLEventReader parser) {
  		super(parser);
  		this.parser = parser;
  	}
 	 
 	 public CustomsOffices(XmlMsgScanner scanner) {
 	  		super(scanner);
 	 }

  	 public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((ECustomsOffices) tag) {
  			default:
  					return;
  			}
  		} else {

  			switch ((ECustomsOffices) tag) {
  				//case CustomsOfficeExport:
  				case OfficeOfExport:
  					setOfficeOfExport(value);
  					break;  				
  				//case CustomsOfficeForCompletion:
  				case OfficeOfAdditionalDeclarationExport:
  					setOfficeOfAdditionalDeclarationExport(value);
  					break;  				
  				//case RealOfficeOfExit:
  				case OfficeOfExit:
  					setOfficeOfExit(value);
  					break;  					
  				//case IntendedOfficeOfExit:
  				case OfficeOfActualExit:
  					setOfficeOfActualExit(value);
  					break;  					
  				case OfficeOfTransit:
  					setOfficeOfTransit(value);
  					break;  					
  				case OfficeOfCommunityExit:
  					setOfficeOfCommunityExit(value);
  					break;  					
  				case OfficeOfNationalExit:
  					setOfficeOfNationalExit(value);
  					break;
  				case SupervisingCustomsOffice:
  					setSupervisingCustomsOffice(value);
					break;
  				case OfficeOfPresentation:
  					setOfficeOfPresentation(value);
  					break;
  				case OfficeAtBorder:
  					setOfficeAtBorder(value);
  					break;
  				case OfficeOfFirstEntry:         //EI20130711
  					setOfficeOfFirstEntry(value);
  					break;
  				case DeclaredOfficeOfFirstEntry: //EI20130711
  					setDeclaredOfficeOfFirstEntry(value);
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
  			return ECustomsOffices.valueOf(token);
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
  	
	public String getOfficeOfExport() {
		return officeOfExport;
	}

	public void setOfficeOfExport(String argument) {
		this.officeOfExport = argument;
	}

	public String getOfficeOfAdditionalDeclarationExport() {
		return officeOfAdditionalDeclarationExport;
	}

	public void setOfficeOfAdditionalDeclarationExport(String argument) {
		this.officeOfAdditionalDeclarationExport = argument;
	}

	public String getOfficeOfExit() {
		return officeOfExit;
	}

	public void setOfficeOfExit(String argument) {
		this.officeOfExit = argument;
	}

	public String getOfficeOfActualExit() {
		return officeOfActualExit;
	}

	public void setOfficeOfActualExit(String argument) {
		this.officeOfActualExit = argument;
	}

	public String getOfficeOfTransit() {
		return officeOfTransit;
	}

	public void setOfficeOfTransit(String officeOfTransit) {
		this.officeOfTransit = officeOfTransit;
	}

	public String getOfficeOfCommunityExit() {
		return officeOfCommunityExit;
	}

	public void setOfficeOfCommunityExit(String officeOfCommunityExit) {
		this.officeOfCommunityExit = officeOfCommunityExit;
	}

	public String getOfficeOfNationalExit() {
		return officeOfNationalExit;
	}
	public void setOfficeOfNationalExit(String officeOfNationalExit) {
		this.officeOfNationalExit = officeOfNationalExit;
	}
	
	public String getSupervisingCustomsOffice() {
		return supervisingCustomsOffice;
	}
	public void setSupervisingCustomsOffice(String value) {
		this.supervisingCustomsOffice = value;
	}
	
	public XMLEventReader getParser() {
		return parser;
	}
	public void setParser(XMLEventReader parser) {
		this.parser = parser;
	}
	
	public String getOfficeOfPresentation() {
		return officeOfPresentation;
	}
	public void setOfficeOfPresentation(String value) {
		this.officeOfPresentation = value;
	}
	
	public String getOfficeOfFirstEntry() {
		return officeOfFirstEntry;
	}

	public void setOfficeOfFirstEntry(String officeOfFirstEntry) {
		this.officeOfFirstEntry = officeOfFirstEntry;
	}

	public String getDeclaredOfficeOfFirstEntry() {
		return declaredOfficeOfFirstEntry;
	}

	public void setDeclaredOfficeOfFirstEntry(String declaredOfficeOfFirstEntry) {
		this.declaredOfficeOfFirstEntry = declaredOfficeOfFirstEntry;
	}

	public boolean isEmpty() {
		return (Utils.isStringEmpty(officeOfExport) && Utils.isStringEmpty(officeOfAdditionalDeclarationExport) && 
				Utils.isStringEmpty(officeOfExit) && Utils.isStringEmpty(officeOfActualExit) && 
				Utils.isStringEmpty(officeOfTransit) && Utils.isStringEmpty(officeOfCommunityExit) &&
		        Utils.isStringEmpty(officeOfCommunityExit) && Utils.isStringEmpty(officeOfNationalExit) &&
		        Utils.isStringEmpty(supervisingCustomsOffice) && Utils.isStringEmpty(officeAtBorder)); 
	}

	public String getOfficeAtBorder() {
		return officeAtBorder;
	}

	public void setOfficeAtBorder(String officeAtBorder) {
		this.officeAtBorder = Utils.checkNull(officeAtBorder);
	}
}
