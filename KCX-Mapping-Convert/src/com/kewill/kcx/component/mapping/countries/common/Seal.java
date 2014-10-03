package com.kewill.kcx.component.mapping.countries.common;

import java.util.List;
import java.util.Vector;
import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: KCX<br>
 * Created		: 10.09.2008<br>
 * Descripion	: Contains the Seal Data with all Fields used in KIDS.
 * 
 * @author Houdek
 * @version 1.0.00
 */
public class Seal extends KCXMessage {

    private String kind;  						//UIDS: Type
    private String number;						//UIDS: Quantity
    private String useOfTydenseals;             //UIDS separatly Class:HeaderExtensions
    private String useOfTydensealStock;			//UIDS separatly Class:HeaderExtensions
    private List <String> sealNumberList;   			
    //V1.1
    private List <SealNumber> sealNumbersList;
    
    private String sealOK;    //EI20120703 KIDS-V20 
   
    public Seal() {
    	super();    	    
    }

    public Seal(XmlMsgScanner scanner) {
  		super(scanner);  		
  	}

  	private enum ESeal {
  		//Kids                  //UIDS
		Kind,					 Type,
		Number,					 Quantity,
		UseOfTydenseals,		//HeaderExtensions.UseOfTydenseals,
		UseOfTydensealStock,    //HeaderExtensions.UseOfTydensealStock		
		SealNumber,				SealsID,	//SealsID.Identity 	
		SealNumbers,
		SealOK;
     }

  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((ESeal) tag) {  			
				
  			
			case SealsID:			
				    SealNumber sealNumber = new SealNumber(getScanner());
  					sealNumber.parse(tag.name());  					
  					addSealNumberList(sealNumber.getNumber()); 
  					addSealsNumberList(sealNumber);   //EI20120919
  					break;	
			case SealNumbers:
			    	SealNumber sealNumbers = new SealNumber(getScanner());
			    	sealNumbers.parse(tag.name());
			    	if (sealNumbersList == null) {
						sealNumbersList = new Vector<SealNumber>();
			    	}
				    sealNumbersList.add(sealNumbers);
				    break;			
			default:
					return;				
  			}
  		} else {

  			switch ((ESeal) tag) {
  				case Kind:
  				case Type:
  					setKind(value);
  					break;  				
  				case Number:
  				case Quantity:
  					setNumber(value);
  					break;    				
  				case UseOfTydenseals:
  					setUseOfTydenseals(value);
  					break;
  				case UseOfTydensealStock:
  					setUseOfTydensealStock(value);
  					break;  				
  				case SealNumber:  				
  					addSealNumberList(value);
  					break;
  				case SealOK:  				
  					setSealOK(value);
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
  			return ESeal.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}  
  	
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}

	public String getUseOfTydenseals() {
		return useOfTydenseals;
	}
	public void setUseOfTydenseals(String useOfTydenseals) {
		this.useOfTydenseals = useOfTydenseals;
	}

	public String getUseOfTydensealStock() {
		return useOfTydensealStock;
	}
	public void setUseOfTydensealStock(String useOfTydensealStock) {
		this.useOfTydensealStock = useOfTydensealStock;
	}
	
	public List <String>getSealNumberList() {
		return sealNumberList;
	}

	public void setSealNumberList(List argument) {
		this.sealNumberList = argument;
	}
	
	public void addSealNumberList(String argument) {
		if (sealNumberList == null) {
			sealNumberList = new Vector<String>();
		}
		sealNumberList.add(argument);
	}
	

	//CT_SealNumbers
	public List <SealNumber>getSealNumbersList() {
		return sealNumbersList;
	}
	//CT_SealNumbers
	public void setSealNumbersList(List<SealNumber> sealNumbersList) {
		this.sealNumbersList = sealNumbersList;
	}
	public void addSealsNumberList( SealNumber snr) {
		if (sealNumbersList == null) {
			sealNumbersList = new Vector<SealNumber>();
		}
		sealNumbersList.add(snr);
	}
	
	public String getSealOK() {
		return sealOK;
	}
	public void setSealOK(String value) {
		this.sealOK = value;
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(kind) && Utils.isStringEmpty(number) && Utils.isStringEmpty(sealOK) &&
		        Utils.isStringEmpty(useOfTydenseals) && Utils.isStringEmpty(useOfTydensealStock) && 
                (sealNumberList == null || (sealNumberList != null && sealNumberList.isEmpty())) &&
                (sealNumbersList == null || (sealNumbersList != null && sealNumbersList.isEmpty())));
	}	
}
