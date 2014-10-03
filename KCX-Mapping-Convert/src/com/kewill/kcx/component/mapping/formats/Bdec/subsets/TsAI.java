/*
* Function    : TsAI.java
* Title       :
* Created     : 19.10.2009
* Author      : Elisabeth Iwaniuk
* Description: AdditionalInformation (same for HA und DA)  for KIDS-AES to eCustoms
*/

package com.kewill.kcx.component.mapping.formats.Bdec.subsets;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: TsAI<br>
 * Erstellt		: 19.10.2009<br>
 * Beschreibung	: AdditionalInformation (same for HA und DA)  for KIDS-AES to eCustoms.
 * 
 * @author iwaniuk
 * @version 6.0.00
 */
public class TsAI extends Teilsatz {

    private String code    = "";     
    private String text = "";      
    private String langauge = "";      

    public TsAI(String type) {
        tsTyp = type;
        trenner = trennerUK;
    }

    public void setFields(String[] fields) {
		int size = fields.length;		
		
		if (size < 1) { return; }
        tsTyp = fields[0];
        if (size < 2) { return; }
        code     = fields[1];
        if (size < 3) { return; }	
        text     = fields[2];
        if (size < 3) { return; }
        langauge = fields[2];
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(code);
        buff.append(trenner);
        buff.append(text);
        buff.append(trenner);
        buff.append(langauge);
        //buff.append(trenner);

        return new String(buff);
      }

	public String getCode() {
		return code;	
	}
	
	public void setCode(String argument) {
		this.code = Utils.checkNull(argument);
	}

	public String getText() {
		return text;	
	}

	public void setText(String argument) {
		this.text = Utils.checkNull(argument);
	}
	
	public String getLangauge() {
		return langauge;	
	}

	public void setLangauge(String argument) {
		this.langauge = Utils.checkNull(argument);
	}	

	public boolean isEmpty() {		
        return (Utils.isStringEmpty(code) && Utils.isStringEmpty(text) && Utils.isStringEmpty(langauge));
	}
}


