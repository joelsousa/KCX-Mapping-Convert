package com.kewill.kcx.component.mapping.countries.de.aes.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Export/aes<br>
 * Created		: 11.09.2008<br>
 * Description	: Contains the TypeOfDeclaration Data with all Fields used in UIDS. 
 * 
 * @author Houdek
 * @version 1.0.00
 */
public class  TypeOfDeclaration extends KCXMessage {

	private String declarationCode;
    private String regionCode;
    private String procedureCode;
    private String transitCode;    
    //new for UIDS V20 beginn:
    private String procedure;
    private String statusCode;
    private String temporaryReason;    
    //new for UIDS V20 end
   
	private enum ETypeOfDeclaration {
		DeclarationCode,
		RegionCode,
		Procedure,
		ProcedureCode,
		TransitCode,
		StatusCode,
		TemporaryReason;
    }  
	
    public TypeOfDeclaration() {
	    super();
    }
    
    public TypeOfDeclaration(XmlMsgScanner scanner) {
  		super(scanner);
   	}
      	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((ETypeOfDeclaration) tag) {
  			default:
  					return;
  			}
  		} else {
  			switch ((ETypeOfDeclaration) tag) {
  				case DeclarationCode:
  					setDeclarationCode(value);
  					break;  				
  				case RegionCode:
  					setRegionCode(value);
  					break;
  				case Procedure:
  					setProcedure(value);
  					break;	
  				case ProcedureCode:
  					setProcedureCode(value);
  					break;  					
  				case TransitCode:
  					setTransitCode(value);
  					break;
  				case StatusCode:
  					setStatusCode(value);
  					break;  				
  				case TemporaryReason:
  					setRegionCode(value);
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
  			return ETypeOfDeclaration.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}
  	
  	public String getDeclarationCode() {
		return declarationCode;
	}
	public void setDeclarationCode(String declarationCode) {
		this.declarationCode = declarationCode;
	}
	public String getRegionCode() {
		return regionCode;
	}
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getProcedureCode() {
		return procedureCode;
	}
	public void setProcedureCode(String procedureCode) {
		this.procedureCode = procedureCode;
	}

	public String getTransitCode() {
		return transitCode;
	}
	public void setTransitCode(String transitCode) {
		this.transitCode = transitCode;
	}
	
	public String getProcedure() {
		return procedure;
	}
	public void setProcedure(String value) {
		this.procedure = value;
	}
	
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String value) {
		this.statusCode = value;
	}
	
	public String getTemporaryReason() {
		return temporaryReason;
	}
	public void setTemporaryReason(String value) {
		this.temporaryReason = value;
	}
}
