package com.kewill.kcx.component.mapping.countries.de.aes.msg.common;

import org.xml.sax.Attributes;
import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module       : aes - Export<br>
 * Created      : 2.07.2012<br>
 * Description	: Kids Version 2.0.00
 * 
 * @author iwaniuk
 * @version 2.0.00
 */
public class OutwardProcessing extends KCXMessage {

	 private String authorizationNumber;			
	 private String authorizationLocalClearenceProcedure;	
	 private String dateOfReExport;
	 private String standardExchange;   
	 private List<Reentry> reentryList;
	 private List<MeansOfIdentification> meansOfIdentificationList;
	 private List<Product> productList;
 
  	private enum EOutwardProcessing {
  	// Kids-TagNames, 							UIDS-TagNames
  		AuthorizationNumber,					AuthorisationID,	
		AuthorizationLocalClearenceProcedure,   LocalClearanceProcedure,
		DateOfReExport, 						DateOfReentry,			
		StandardExchange,						StandardReplacement,
		//der Rest ist (z.Z - muss ich noch klaeren) bei KIDS ausserhalb von OutwardProcessing
		Reentry,								CountryOfReentry,
		MeansOfIdentification, 					Identity,
		Product;								//same
  	} 

    public OutwardProcessing() {
    	super();    		
    }
    
    public OutwardProcessing(XmlMsgScanner scanner) {
  		super(scanner);
  	}
     
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EOutwardProcessing) tag) {
  			case Reentry:
  				Reentry reentry = new Reentry(getScanner());
  				reentry.parse(tag.name());
  				addReentryList(reentry);
  				break;
  			case MeansOfIdentification:
  			case Identity:
  				MeansOfIdentification identity = new MeansOfIdentification(getScanner());
  				identity.parse(tag.name());
  				addMeansOfIdentificationList(identity);
  				break;
  			case Product:
  				Product tarif = new Product(getScanner());
				tarif.parse(tag.name());
				addProductList(tarif);
  			default:
  					return;
  			}
  		} else {
  			switch ((EOutwardProcessing) tag) {  			
  				case AuthorizationNumber:  	
  				case AuthorisationID:
  					setAuthorizationNumber(value);
  					break;  					
  				case AuthorizationLocalClearenceProcedure:  
  				case LocalClearanceProcedure:
  					setAuthorizationLocalClearenceProcedure(value);
  					break;  					
  				case DateOfReExport:
  				case DateOfReentry:
  					setDateOfReExport(value);
  					break;  					
  				case StandardExchange:
  				case StandardReplacement:
  					setStandardExchange(value);
  					break;
  				case CountryOfReentry:
  					addReentryList(value);
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
  			return EOutwardProcessing.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

  	
	public String getAuthorizationLocalClearenceProcedure() {
		return authorizationLocalClearenceProcedure;
	}
	public void setAuthorizationLocalClearenceProcedure(String value) {
		this.authorizationLocalClearenceProcedure = value;
	}

	public String getAuthorizationNumber() {
		return authorizationNumber;
	}
	public void setAuthorizationNumber(String value) {
		this.authorizationNumber = value;
	}
	
	public String getDateOfReExport() {
		return dateOfReExport;
	}
	public void setDateOfReExport(String value) {
		this.dateOfReExport = value;
	}
	
	public String getStandardExchange() {
		return standardExchange;
	}
	public void setStandardExchange(String value) {
		this.standardExchange = value;
	}
	
	public List <Reentry> getReentryList() {
		return reentryList;
	}
	public void setReentryList(List <Reentry> argument) {
		this.reentryList = argument;
	}
	public void addReentryList(Reentry argument) {
		if (argument == null) {
			return;
		}
		if (reentryList == null) {
			reentryList = new Vector <Reentry>();
		}
		reentryList.add(argument);
	}
	public void addReentryList(String country) {
		if (Utils.isStringEmpty(country)) {
			return;
		}
		if (reentryList == null) {
			reentryList = new Vector <Reentry>();
		}
		Reentry reentry = new Reentry();
		reentry.setCountry(country);
		reentryList.add(reentry);
	}
	
	public List <MeansOfIdentification> getMeansOfIdentificationList() {
		return meansOfIdentificationList;
	}
	public void setMeansOfIdentificationList(List <MeansOfIdentification> argument) {
		this.meansOfIdentificationList = argument;
	}
	public void addMeansOfIdentificationList(MeansOfIdentification argument) {
		if (meansOfIdentificationList == null) {
			meansOfIdentificationList = new Vector <MeansOfIdentification>();
		}
		meansOfIdentificationList.add(argument);
	}
	
	public List <Product> getProductList() {
		return productList;
	}
	public void setProductList(List <Product> argument) {
		this.productList = argument;
	}
	public void addProductList(Product argument) {
		if (productList == null) {
			productList = new Vector <Product>();
		}
		productList.add(argument);
	}
	
	public boolean isEmpty() {
		return  Utils.isStringEmpty(authorizationNumber) && 
				Utils.isStringEmpty(authorizationLocalClearenceProcedure) && 
		        Utils.isStringEmpty(dateOfReExport) &&		       
		        Utils.isStringEmpty(standardExchange);  
	}
}
