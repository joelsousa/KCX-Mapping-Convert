package com.kewill.kcx.component.mapping.countries.de.aes.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

import java.util.List;
import java.util.Vector;

/**
 * Module		: ExportRefundItem<br>
 * Created		: 10.09.2008<br>
 * Description	: Contains the ExportRefundItem Data with all Fields used in  KIDS.
 * 
 * @author Houdek
 * @version 1.0.00
 */
public class ExportRefundItem extends KCXMessage {

    private String addition1   	         = "";
    private String addition2             = "";
    private List <String> additionList;
    private String originCountry         = "";
    private String amountCode            = "";
    private String amount                = "";   //n(11,3)
    private String typeOfRefund          = "";
    private String amountCoefficient     = "";	 //n(11,3)
    private String partA;						 //n(11,3)
    private String partB;
    private String partC;
    private String partD;
    private List <String>  partList;
    private String unitOfMeasurement;
    //new for KIDS V2.1	beginn
    //TODO-IWA: viele (?neue?) tags
    //new for KIDS V2.1 end
    private Ingredients ingredients;
    private List <Ingredients>ingredientsList;
    private String restitutionProcedure;     //only UIDS, for KIDS is as CustomsApprovedTreatment.CodeForRefund
    
    private String applyFor;
    private String inAdvance;
    private String cBT;
    private String prefinancing;
    private String applicationForm;
    private String applicationDate;
    private String simplifiedDeclaration;
    private String simplifiedDeclarationDate;
   
    private enum EExportRefundItem {
      		Addition1,    			GoodsDescriptionExt,
      		Addition2,    			//also GoodsDescriptionExt
      								RestitutionProcedure, 
      		OriginCountry,			//same
      		AmountCode,				Estimated,
      		Amount,					//same
      		TypeOfRefund,			BudgetControl,
      		AmountCoefficient,		Coeficent,
      		PartA,					Equity,
      		PartB,					//also Equity
      		PartC,					//also Equity
      		PartD,					//also Equity
      		UnitOfMeasurement,		Measurement,		      		
      		ApplyFor,
      		InAdvance,
      		CBT,
      		Prefinancing,
      		ApplicationForm,
      		ApplicationDate,
      		SimplifiedDeclaration,
      		SimplifiedDeclarationDate,
      		Ingredient,				//same
      		Ingredients,
    }        

    public ExportRefundItem() {
	      	super();
	        ingredientsList = new Vector<Ingredients>();
	        additionList = new Vector<String>();
	        partList = new Vector<String>();
    }
    
    public ExportRefundItem(XmlMsgScanner scanner) {
    	super(scanner);
    	ingredientsList = new Vector<Ingredients>();
        additionList = new Vector<String>();                //EI20091015
        partList = new Vector<String>();    	            //EI20091015
  	}
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((EExportRefundItem) tag) {
    				case Ingredient:
    				case Ingredients:
    					ingredients = new Ingredients(getScanner());
    					ingredients.parse(tag.name());
    					addIngredientsList(ingredients);
    			default:
    					return;
    			}
    		} else {

    			switch ((EExportRefundItem) tag) {

    				case Addition1:   
    					setAddition1(value);    					
    					break;    				
    					
    				case Addition2:
    					setAddition2(value);    					
    					break;

    				case GoodsDescriptionExt:
    					additionList.add(value);
    					break;
    					
    				case OriginCountry:
    					setOriginCountry(value);
    					break;

    				case AmountCode:
    				case Estimated:
    					setAmountCode(value);
    					break;

    				case TypeOfRefund:
    				case BudgetControl:
    					setTypeOfRefund(value);
    					break;

    				case AmountCoefficient:
    				case Coeficent:
    					setAmountCoefficient(value);
    					break;

    				case Amount:
    					setAmount(value);
    					break;
    					

    				case PartA:
    					setPartA(value);    					
    					break;

    				case PartB:
    					setPartB(value);    					
    					break;

    				case PartC:
    					setPartC(value);    					
    					break;

    				case PartD:
    					setPartD(value);    					
    					break;

    				case Equity:
    					partList.add(value);
    					break;
    					
    				case UnitOfMeasurement:
    				case Measurement:
    					setUnitOfMeasurement(value);
    					break;
    					
    				case RestitutionProcedure:
    					setRestitutionProcedure(value);
    					break;

    				case ApplyFor:
    					setApplyFor(value);
    					break;
    				case InAdvance:
    					setInAdvance(value);
    					break;
    				case CBT:
    					setCBT(value);
    					break;
    				case Prefinancing:
    					setPrefinancing(value);
    					break;
    				case ApplicationForm:
    					setApplicationForm(value);
    					break;
    				case ApplicationDate:
    					setApplicationDate(value);
    					break;
    				case SimplifiedDeclaration:
    					setSimplifiedDeclaration(value);
    					break;
    				case SimplifiedDeclarationDate:
    					setSimplifiedDeclarationDate(value);
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
    			return EExportRefundItem.valueOf(token);
    		} catch (IllegalArgumentException e) {
    			return null;
    		}
    	}
       

	public String getAddition1() {
		if (additionList.size() > 0) {
			addition1 = (String) additionList.get(0);
		}
		return addition1;
	}
	public void setAddition1(String addition1) {
		this.addition1 = addition1;
		//this.additionList.add(0, addition1);
	}

	public String getAddition2() {
		if (additionList.size() > 1) {
			addition2 = (String) additionList.get(1);
		}
		return addition2;
	}
	public void setAddition2(String addition2) {
		this.addition2 = addition2;
		//this.additionList.add(1, addition2);
	}
	public List<String> getAdditionList() {
		return additionList;
	}
	public void setAdditionList(List<String> list) {
		this.additionList = list;
	}	
	
	public String getOriginCountry() {
		return originCountry;
	}
	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}

	public String getAmountCode() {
		return amountCode;
	}
	public void setAmountCode(String amountCode) {
		this.amountCode = amountCode;
	}
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getTypeOfRefund() {
		return typeOfRefund;
	}

	public void setTypeOfRefund(String typeOfRefund) {
		this.typeOfRefund = typeOfRefund;
	}
	public String getAmountCoefficient() {
		return amountCoefficient;
	}
	public void setAmountCoefficient(String amountCoefficient) {
		this.amountCoefficient = amountCoefficient;
	}


	public String getPartA() {
		if (partList.size() > 0) {
			partA = (String) partList.get(0);
		}
		return partA;
	}
	/*
	public void setPartA(String partA) {
		this.partA = partA;	
		if (partList.size() == 0) 
			initPartList();
		this.partList.add(0, partA);		
	}
	*/
	public void setPartA(String partA) {
		this.partA = partA;		
	}
	
	public String getPartB() {
		if (partList.size() > 1) {
			partB = (String) partList.get(1);
		}
		return partB;
	}
	public void setPartB(String partB) {
		this.partB = partB;
	}

	public String getPartC() {
		if (partList.size() > 2) {
			partC = (String) partList.get(2); 
		}
		return partC;
	}
	public void setPartC(String partC) {
		this.partC = partC;
	}

	public String getPartD() {
		if (partList.size() > 3) {
			partD = (String) partList.get(3); 
		}
		return partD;
	}
	public void setPartD(String partD) {			
		this.partD = partD;
	}
	
	public List<String> getPartList() {
		return partList;
	}
	public void setPartList(List<String> list) {
		this.partList = list;
	}	
	
	public String getUnitOfMeasurement() {
		return unitOfMeasurement;
	}
	public void setUnitOfMeasurement(String unitOfMeasurement) {
		this.unitOfMeasurement = unitOfMeasurement;
	}

	public List <Ingredients>getIngredientsList() {
		return ingredientsList;
	}
	public void addIngredientsList(Ingredients ing) {
		this.ingredientsList.add(ing);
	}

	public void setRestitutionProcedure(String argument) {
		this.restitutionProcedure = argument;
	}
	public String getRestitutionProcedure() {
		return this.restitutionProcedure;
	}
//	private void initPartList() {
//		partList.add(0, "");
//		partList.add(1, "");
//		partList.add(2, "");
//		partList.add(3, "");	
//	}
//	private void initAdditionalList() {
//		additionList.add(0, "");
//		additionList.add(1, "");
//	}

	public String getApplyFor() {
		return applyFor;
	
	}

	public void setApplyFor(String applyFor) {
		this.applyFor = Utils.checkNull(applyFor);
	}

	public String getInAdvance() {
		return inAdvance;
	
	}

	public void setInAdvance(String inAdvance) {
		this.inAdvance = Utils.checkNull(inAdvance);
	}

	public String getCBT() {
		return cBT;
	
	}

	public void setCBT(String cbt) {
		cBT = Utils.checkNull(cbt);
	}

	public String getPrefinancing() {
		return prefinancing;
	
	}

	public void setPrefinancing(String prefinancing) {
		this.prefinancing = Utils.checkNull(prefinancing);
	}

	public String getApplicationForm() {
		return applicationForm;
	
	}

	public void setApplicationForm(String applicationForm) {
		this.applicationForm = Utils.checkNull(applicationForm);
	}

	public String getApplicationDate() {
		return applicationDate;
	
	}

	public void setApplicationDate(String applicationDate) {
		this.applicationDate = Utils.checkNull(applicationDate);
	}

	public String getSimplifiedDeclaration() {
		return simplifiedDeclaration;
	
	}

	public void setSimplifiedDeclaration(String simplifiedDeclaration) {
		this.simplifiedDeclaration = Utils.checkNull(simplifiedDeclaration);
	}

	public String getSimplifiedDeclarationDate() {
		return simplifiedDeclarationDate;
	
	}
	public void setSimplifiedDeclarationDate(String simplifiedDeclarationDate) {
		this.simplifiedDeclarationDate = Utils.checkNull(simplifiedDeclarationDate);
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(amount) && Utils.isStringEmpty(addition1) && 
				Utils.isStringEmpty(typeOfRefund) && Utils.isStringEmpty(amountCoefficient) && 
		        Utils.isStringEmpty(partA) &&
		        additionList == null && partList == null && ingredientsList == null); 
	}
	
}
