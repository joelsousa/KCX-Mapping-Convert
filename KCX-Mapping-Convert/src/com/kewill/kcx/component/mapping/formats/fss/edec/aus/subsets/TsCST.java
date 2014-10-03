/*
* Funktion    : TsCST.java
* Titel       :
* Erstellt    : 19.03.2010
* Author      : Alfred Krzoska
* Beschreibung: Ergaenzungsdaten Statistische Zusatzinformationen   
* Anmerkungen :
* Parameter   :
* Rückgabe    : keine
* Aufruf      :
*
*/

package com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpDatPos;
import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;
/**
 * Function : TsCST.
 * Description : Ergaenzungsdaten Statistische Zusatzinformationen 
 * 
 * @author krzoska
 * @version 1.0.00
 * 
 */
public class TsCST extends Teilsatz {

    private String refnr = "";       	// Dossiernummer Spediteur
    private String posnr = "";			// Positionsnummer
    
    //AK20110414 added name, value
    private String name  = "";          // Codierung    26 Lagernummer, 27 Export Code
    private String value = "";			// bei 26 die Lagernummer    bei 27 (Export Code)

    
    public TsCST() {
        tsTyp = "CST";
    }
    public void setFields(String[] fields) {
    
		int size = fields.length;
		//String ausgabe = "FSS: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
		
		if (size < 1) {
			return;		
		}
        tsTyp = fields[0];
        if (size < 2) {
        	return;	
        }
        refnr = fields[1];
        if (size < 3) {
        	return;	
        }
        posnr = fields[2];
        if (size < 4) {
        	return;	
        }
        name = fields[3];        
        if (size < 5) {
        	return;	
        }
        value = fields[4];       
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(refnr);
        buff.append(trenner);
        buff.append(posnr);
        buff.append(trenner);
        buff.append(name);
        buff.append(trenner);
        buff.append(value);
        
        return new String(buff);
      }


	public void setCSTSubset(MsgExpDatPos msgExpDatPos) {
		
	}	
	
	public boolean isEmpty() {
		return Utils.isStringEmpty(name) && Utils.isStringEmpty(value); 
	}
	
	public String getRefnr() {
		return refnr;
	
	}
	public void setRefnr(String refnr) {
		this.refnr = Utils.checkNull(refnr);
	}
	public String getPosnr() {
		return posnr;
	
	}
	public void setPosnr(String posnr) {
		this.posnr = Utils.checkNull(posnr);
	}
	public String getName() {
		return name;
	
	}
	public void setName(String name) {
		this.name = Utils.checkNull(name);
	}
	public String getValue() {
		return value;
	
	}
	public void setValue(String value) {
		this.value = Utils.checkNull(value);
	}
}


