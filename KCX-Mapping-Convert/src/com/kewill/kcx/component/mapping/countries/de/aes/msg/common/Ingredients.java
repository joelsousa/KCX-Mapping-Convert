/*
 * Function    : Ingredients (KIDS) == Ingredients (UIDS)
 * Titel       :
 * Date        : 09.09.2008
 * Author      : Kewill CSF / Houdek
 * Description : Contains the Ingredients Data
 * 			   : with all Fields used in  KIDS
 * Parameters  :

 * Changes
 * -----------
 * Author      : EI
 * Date        : 15.05.2009
 * Label       :
 * Description : Kids/Uids checked - no changes
 * 
 * Author      : EI
 * Date        : 15.10.2009
 * Label       :
 * Description : new member for UK: licenceLineNumber,licenceStatus,licenceQuantity
 *
 * Author      : 
 * Date        : 13.08.2010
 * Label       :
 * Description : new tags ApplyFor, Measure1, Measure2, Beneficiary , Supplier
 *
 *
 * Author      : 
 * Date        : 
 * Label       :
 * Description : 
 *
 *
 */

package com.kewill.kcx.component.mapping.countries.de.aes.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: Ingredients<br>
 * Erstellt		: 09.09.2008<br>
 * Beschreibung	: Contains the Ingredients Data with all Fields used in  KIDS.
 * 
 * @author Houdek
 * @version 1.0.00
 */
public class Ingredients extends KCXMessage {

    private String sequentialNumber   	 = "";
    private String amount1               = "";		//n(7,4)
    private String kindOfCoefficient     = "";
    private String amount2               = "";		//n(7,4)
    private String rate                  = "";		//n(7,4)
    private String weightPercent         = "";		//n(7,3)
    private String weight                = "";		//n(9,1)
    private String uniqueFactoryNumber   = "";
    private String tarifNumber           = "";
    private String licenceNumber         = "";
    private String text                  = "";
    private String licenceLineNumber         = "";  //UK
    private String licenceStatus             = "";  //UK
    private String licenceQuantity           = "";  //UK
    private String applyFor				= "";
    private String measure1				= "";
    private String measure2				= "";
    private TIN    beneficiaryTIN;
    private Party  beneficiary;
    private TIN	   supplierTIN;
    private Party  supplier;

    

    private boolean debug   = false;
    private XMLEventReader 		parser	= null;

	private enum EIngredients {

		SequentialNumber,
		Amount1,				CalculationFactor1,
		KindOfCoefficient,		DivisionIndicator1,
		Amount2,				CalculationFactor2,
		Rate,					CalculationFactor3,
		WeightPercent,			WeightPercentage,
		Weight,					AmountPart,		
		UniqueFactoryNumber,	RecipeID,
		TarifNumber,			Key,
		LicenceNumber,			IngredientLicense,
		LicenceLineNumber,
		LicenceStatus,
		LicenceQuantity,
		Text,					IngredientDescription,
		ApplyFor,
		Measure1,
		Measure2,
		BeneficiaryTIN,
		Beneficiary,
		SupplierTIN,
		Supplier;		
     }
	
	public Ingredients() {
      	super();
    }

    public Ingredients(XMLEventReader parser) {
		super(parser);
		this.parser = parser;
	}
  
    public Ingredients(XmlMsgScanner scanner) {
		super(scanner);
	}	

  	public boolean isDebug() {
  		return debug;
  	}

  	public void setDebug(boolean debug) {
  		this.debug = debug;
  	}

  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EIngredients) tag) {
  			case BeneficiaryTIN:
				beneficiaryTIN = new TIN(getScanner());
				beneficiaryTIN.parse(tag.name());
			break;
  			case Beneficiary:
				beneficiary = new Party(getScanner());
				beneficiary.parse(tag.name());
			break;
  			case SupplierTIN:
				supplierTIN = new TIN(getScanner());
				supplierTIN.parse(tag.name());
			break;
  			case Supplier:
  				supplier = new Party(getScanner());
				supplier.parse(tag.name());
			break;
  			default:
  					return;
  			}
  		} else {

  			switch ((EIngredients) tag) {

  				case SequentialNumber:
  					setSequentialNumber(value);
  					break;

  				case Amount1:
  				case CalculationFactor1:
  					setAmount1(value);
  					break;

  				case KindOfCoefficient:
  				case DivisionIndicator1:
  					setKindOfCoefficient(value);
  					break;

  				case Amount2:
  				case CalculationFactor2:
  					setAmount2(value);
  					break;

  				case Rate:
  				case CalculationFactor3:
  					setRate(value);
  					break;

  				case WeightPercent:
  				case WeightPercentage:
  					setWeightPercent(value);
  					break;

  				case Weight:
  				case AmountPart:
  					setWeight(value);
  					break;

  				case UniqueFactoryNumber:
  				case RecipeID:
  					setUniqueFactoryNumber(value);
  					break;

  				case TarifNumber:
  				case Key:
  					setTarifNumber(value);
  					break;

  				case LicenceNumber:
  				case IngredientLicense:
  					setLicenceNumber(value);
  					break;

  				case Text:
  				case IngredientDescription:
  					setText(value);
  					break;
  					
  				case LicenceLineNumber:
  					setLicenceLineNumber(value);
  					break;
  				case LicenceStatus:
  					setLicenceStatus(value);
					break;
  				case LicenceQuantity: 
  					setLicenceQuantity(value);
  					break;
  				case ApplyFor:
  					setApplyFor(value);
  					break;
  				case Measure1:
  					setMeasure1(value);
  					break;
  				case Measure2:
  					setMeasure2(value);
  					break;

  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}


  	public Enum translate(String token) {
  		try {
  			return EIngredients.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}


// -----

	public String getSequentialNumber() {
		return sequentialNumber;
	}


	public void setSequentialNumber(String sequentialNumber) {
		this.sequentialNumber = sequentialNumber;
	}


	public String getAmount1() {
		return amount1;
	}


	public void setAmount1(String amount1) {
		this.amount1 = amount1;
	}


	public String getKindOfCoefficient() {
		return kindOfCoefficient;
	}


	public void setKindOfCoefficient(String kindOfCoefficient) {
		this.kindOfCoefficient = kindOfCoefficient;
	}


	public String getAmount2() {
		return amount2;
	}


	public void setAmount2(String amount2) {
		this.amount2 = amount2;
	}


	public String getRate() {
		return rate;
	}


	public void setRate(String rate) {
		this.rate = rate;
	}


	public String getWeightPercent() {
		return weightPercent;
	}


	public void setWeightPercent(String weightPercent) {
		this.weightPercent = weightPercent;
	}


	public String getWeight() {
		return weight;
	}


	public void setWeight(String weight) {
		this.weight = weight;
	}


	public String getUniqueFactoryNumber() {
		return uniqueFactoryNumber;
	}


	public void setUniqueFactoryNumber(String uniqueFactoryNumber) {
		this.uniqueFactoryNumber = uniqueFactoryNumber;
	}


	public String getTarifNumber() {
		return tarifNumber;
	}


	public void setTarifNumber(String tarifNumber) {
		this.tarifNumber = tarifNumber;
	}


	public String getLicenceNumber() {
		return licenceNumber;
	}


	public void setLicenceNumber(String licenceNumber) {
		this.licenceNumber = licenceNumber;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}

	public void setLicenceLineNumber(String argument) {
		this.licenceLineNumber = argument;
	}
	public String getLicenceLineNumber() {
		return this.licenceLineNumber;
	}
		
	public void setLicenceStatus(String argument) {
		this.licenceStatus = argument;
	}
	public String getLicenceStatus() {
		return this.licenceStatus;
	}
		
	public void setLicenceQuantity(String argument) {
		this.licenceQuantity = argument;
	}
	public String getLicenceQuantity() {
		return this.licenceQuantity;
	}

	public String getApplyFor() {
		return applyFor;
	
	}

	public void setApplyFor(String applyFor) {
		this.applyFor = Utils.checkNull(applyFor);
	}

	public String getMeasure1() {
		return measure1;
	
	}

	public void setMeasure1(String measure1) {
		this.measure1 = Utils.checkNull(measure1);
	}

	public String getMeasure2() {
		return measure2;
	
	}

	public void setMeasure2(String measure2) {
		this.measure2 = Utils.checkNull(measure2);
	}

	public TIN getBeneficiaryTIN() {
		return beneficiaryTIN;
	}

	public void setBeneficiaryTIN(TIN beneficiaryTIN) {
		this.beneficiaryTIN = beneficiaryTIN;
	}

	public Party getBeneficiary() {
		if (beneficiaryTIN != null) {
			if (beneficiary == null) {
				beneficiary = new Party();
			}
			beneficiary.setPartyTIN(beneficiaryTIN);
		}
		
		return beneficiary;
	}

	public void setBeneficiary(Party beneficiary) {
		this.beneficiary = beneficiary;
	}

	public TIN getSupplierTIN() {
		return supplierTIN;
	}

	public void setSupplierTIN(TIN supplierTIN) {
		this.supplierTIN = supplierTIN;
	}

	public Party getSupplier() {
		if (supplierTIN != null) {
			if (supplier == null) {
				supplier = new Party();
			}
			supplier.setPartyTIN(supplierTIN);
		}
		
		return supplier;
	}

	public void setSupplier(Party supplier) {
		this.supplier = supplier;
	}
}
