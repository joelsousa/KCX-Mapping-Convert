package com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL; 

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module                :       PORT - BL.
 * Created               :       10.04.2012
 * Description    		 :
 *
 * @author                      iwaniuk
 * @version 1.0.00
 */

public class TsParty extends Teilsatz {
	private String tsTyp2 		= ""; 
	private String count		= "";   //kopf(=0) / positionen 
	private String ediQualifier = "";
    private String partyId		= "";	 // Identifikation des Beteiligten
    private String taxRegistrationNumber = "";	 //  Steuernummer      
    private String reference1 = "";
    private String reference2 = "";
    private String reference3 = "";
    private String reference4 = "";
    private String reference5 = "";
    private String reference6 = "";
    private String reference7 = "";
    private String reference8 = "";
    
    public TsParty(String type) {
    	tsTyp = "PARTY";
	    this.tsTyp2 = type;
    }

    public void setFields(String[] fields) {
    	int size = fields.length;

    	if (size < 1) { return; }
    	tsTyp = fields[0];    	
	    if (size < 2) { return; }
	    tsTyp2 = fields[1];
	    if (size < 3) { return; }
	    count = fields[2];
	    if (size < 4) { return; }
	    ediQualifier = fields[3];	   
	    if (size < 5) { return; }
	    partyId = fields[4];
	    if (size < 6) { return; }
	    taxRegistrationNumber = fields[5];
	    if (size < 7) { return; }
	    reference1 = fields[6];
	    if (size < 8) { return; }
	    reference2 = fields[7];
	    if (size < 9) { return; }
	    reference3 = fields[8];
	    if (size < 10) { return; }
	    reference4 = fields[9];
	    if (size < 11) { return; }
	    reference5 = fields[10];
	    if (size < 12) { return; }
	    reference6 = fields[11];
	    if (size < 13) { return; }
	    reference7 = fields[12];    
	    if (size < 14) { return; }
	    reference8 = fields[13];    
    }

    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();
    	
    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(tsTyp2);
    	buff.append(trenner);
    	buff.append(count);
    	buff.append(trenner);
    	buff.append(ediQualifier);
    	buff.append(trenner);
    	buff.append(partyId);
    	buff.append(trenner);    	
    	buff.append(taxRegistrationNumber);
    	buff.append(trenner);
    	buff.append(reference1);
    	buff.append(trenner);
    	buff.append(reference2);
    	buff.append(trenner);
    	buff.append(reference3);
    	buff.append(trenner);
    	buff.append(reference4);
    	buff.append(trenner);
    	buff.append(reference5);
    	buff.append(trenner);
    	buff.append(reference6);
    	buff.append(trenner);
    	buff.append(reference7);
    	buff.append(trenner);
    	buff.append(reference8);
    	//buff.append(trenner);    	
    	
    	return new String(buff);
    }
    
    public String getPos() {
		return tsTyp2;
	}
	public void setPos(String tsTyp2) {
		this.tsTyp2 = Utils.checkNull(tsTyp2);
	}

    public String getPartyId() {
    	 return partyId;
    }
    public void setPartyId(String partyId) {
    	this.partyId = Utils.checkNull(partyId);
    }

    public String getTaxRegistrationNumber() {
    	 return taxRegistrationNumber;
    }
    public void setTaxRegistrationNumber(String taxRegistrationNumber) {
    	this.taxRegistrationNumber = Utils.checkNull(taxRegistrationNumber);
    }
    
    public String getReference1() {
   	 return reference1;
   }
   public void setReference1(String value) {
   	this.reference1 = Utils.checkNull(value);
   }

   public String getReference2() {
	   	 return reference2;
	   }
	   public void setReference2(String value) {
	   	this.reference2 = Utils.checkNull(value);
	   }
	   
	   public String getReference3() {
		   	 return reference3;
		   }
		   public void setReference3(String value) {
		   	this.reference3 = Utils.checkNull(value);
		   }
		   
		   public String getReference4() {
			   	 return reference4;
			   }
			   public void setReference4(String value) {
			   	this.reference4 = Utils.checkNull(value);
			   }
			   
			   public String getReference5() {
				   	 return reference5;
				   }
				   public void setReference5(String value) {
				   	this.reference5 = Utils.checkNull(value);
				   }
				   
				   public String getReference6() {
					   	 return reference6;
					   }
					   public void setReference6(String value) {
					   	this.reference6 = Utils.checkNull(value);
					   }
					   
	public String getReference7() {
		return reference7;
	}
	public void setReference7(String value) {
		this.reference7 = Utils.checkNull(value);
	}
		
	public String getReference8() {
		return reference8;
	}
	public void setReference8(String value) {
		this.reference8 = Utils.checkNull(value);
	}
	
	public String getEdiQualifier() {
		return ediQualifier;
	}
	public void setEdiQualifier(String value) {
		this.ediQualifier = Utils.checkNull(value);
	}	
		
	 public String getCount() {
	   	 	return count;
	    }
	    public void setCount(String unLocode) {
	    	this.count = Utils.checkNull(unLocode);
	    }
	    public void setCount(int i) {
	    	if (i > 0) {
	    		this.count = "" + i;
	    	} else {
	    		this.count = "0";
	    	}
	    }
	    
    public boolean isEmpty() {
    	return   Utils.isStringEmpty(tsTyp) &&  //auch wenn nur tsTyp gefüllt ist, muss der satz ausgegeben werden!  	
    	  		 Utils.isStringEmpty(partyId) && Utils.isStringEmpty(taxRegistrationNumber);

    }
}
