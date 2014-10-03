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

public class TsDetailsOnDocument extends Teilsatz {

    private String documentName		 			 = "";	 // kodierter Documentname
    private String formularLayoutKeyDakosy		 = "";	 // Fomular Lay-Out-Key (dreistelliger von DAKOSY vergebener Code)
    private String numberOfCopiesRequired		 = "";	 // Anzahl der erforderlichen Dokument-Kopien
    private String numberOfOriginalsRequired	 = "";	 // Anzahl der erforderlichen Dokument-Originale

    public TsDetailsOnDocument() {
	    tsTyp = "DETAILSONDOCUMENT";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;
    	
    	if (size < 1) { return; }
    	tsTyp = fields[0];
	    if (size < 2) { return; }
	    documentName = fields[1];
	    if (size < 3) { return; }
	    formularLayoutKeyDakosy = fields[2];
	    if (size < 4) { return; }
	    numberOfCopiesRequired = fields[3];
	    if (size < 5) { return; }
	    numberOfOriginalsRequired = fields[4];
    }



    public String getDocumentName() {
    	 return documentName;
    }
    public void setDocumentName(String documentName) {
    	this.documentName = Utils.checkNull(documentName);
    }

    public String getFormularLayoutKeyDakosy() {
    	 return formularLayoutKeyDakosy;
    }
    public void setFormularLayoutKeyDakosy(String formularLayoutKeyDakosy) {
    	this.formularLayoutKeyDakosy = Utils.checkNull(formularLayoutKeyDakosy);
    }

    public String getNumberOfCopiesRequired() {
    	 return numberOfCopiesRequired;
    }
    public void setNumberOfCopiesRequired(String numberOfCopiesRequired) {
    	this.numberOfCopiesRequired = Utils.checkNull(numberOfCopiesRequired);
    }

    public String getNumberOfOriginalsRequired() {
    	 return numberOfOriginalsRequired;
    }
    public void setNumberOfOriginalsRequired(String numberOfOriginalsRequired) {
    	this.numberOfOriginalsRequired = Utils.checkNull(numberOfOriginalsRequired);
    }

    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(documentName);
    	buff.append(trenner);
    	buff.append(formularLayoutKeyDakosy);
    	buff.append(trenner);
    	buff.append(numberOfCopiesRequired);
    	buff.append(trenner);
    	buff.append(numberOfOriginalsRequired);
    	//buff.append(trenner);

    	return new String(buff);  
    }

    public boolean isEmpty() {
    	return   Utils.isStringEmpty(documentName)
	 		&& Utils.isStringEmpty(formularLayoutKeyDakosy)
	 		&& Utils.isStringEmpty(numberOfCopiesRequired)
	 		&& Utils.isStringEmpty(numberOfOriginalsRequired);
    }

}
