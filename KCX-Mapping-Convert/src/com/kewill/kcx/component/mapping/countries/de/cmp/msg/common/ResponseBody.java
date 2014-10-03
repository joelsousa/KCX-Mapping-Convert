package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.GoodsItem;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

import java.util.ArrayList;

/**
* Module		: CMP<br>
* Created		: 07.06.2013<br>
* Description	: ResponseBody.
* 
* @author iwaniuk
* @version 1.0.00
*/

public class ResponseBody extends KCXMessage {

    private String declarationStatus;
    private Tufsta tufsta;
    private Cuscan cuscan;
    private Cusfin cusfin;
    private Custst custst;
    private Cusstp cusstp;
    private Cusrec cusrec;
   
    private enum EResponseBody {
    	DeclarationStatus,
    	TUFSTA,   
    	CUSCAN,
    	CUSFIN,
    	CUSTST,
    	CUSSTP,
      	CUSREC;
    }        

    public ResponseBody() {
	      	super();	       
    }
    
    public ResponseBody(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((EResponseBody) tag) {
    			case TUFSTA:
    				tufsta = new Tufsta(getScanner());
    				tufsta.parse(tag.name());
    				break;
    				
    			case CUSCAN:
    				cuscan = new Cuscan(getScanner());
    				cuscan.parse(tag.name());
    				break;
    				
    			case CUSFIN:
    				cusfin = new Cusfin(getScanner());
    				cusfin.parse(tag.name());
    				break;
    				
    			case CUSTST:
    				custst = new Custst(getScanner());
    				custst.parse(tag.name());
    				break;
    				
    			case CUSSTP:
    				cusstp = new Cusstp(getScanner());
    				cusstp.parse(tag.name());
    				break;
    				
    			case CUSREC:
    				cusrec = new Cusrec(getScanner());
    				cusrec.parse(tag.name());
    				break;
    			
    			default:
    					return;
    			}
    		} else {

    			switch ((EResponseBody) tag) {

    				case DeclarationStatus:   
    					setDeclarationStatus(value);    					
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
    			return EResponseBody.valueOf(token);
    		} catch (IllegalArgumentException e) {
    			return null;
    		}
    	}
       

	public String getDeclarationStatus() {		
		return declarationStatus;
	}
	public void setDeclarationStatus(String value) {
		this.declarationStatus = value;		
	}	
	
	public Tufsta getTufsta() {
		return tufsta;
	}

	public void setTufsta(Tufsta tufsta) {
		this.tufsta = tufsta;
	}

	public Cuscan getCuscan() {
		return cuscan;
	}

	public void setCuscan(Cuscan cuscan) {
		this.cuscan = cuscan;
	}

	public Cusfin getCusfin() {
		return cusfin;
	}

	public void setCusfin(Cusfin cusfin) {
		this.cusfin = cusfin;
	}

	public Custst getCustst() {
		return custst;
	}

	public void setCustst(Custst custst) {
		this.custst = custst;
	}

	public Cusstp getCusstp() {
		return cusstp;
	}

	public void setCusstp(Cusstp cusstp) {
		this.cusstp = cusstp;
	}

	public Cusrec getCusrec() {
		return cusrec;
	}

	public void setCusrec(Cusrec cusrec) {
		this.cusrec = cusrec;
	}

	public boolean isEmpty() {
		return (Utils.isStringEmpty(declarationStatus) && 
				tufsta == null && cuscan == null &&
				cusfin == null && custst == null &&
				 cusstp == null && cusrec == null); 
	}
	
}
