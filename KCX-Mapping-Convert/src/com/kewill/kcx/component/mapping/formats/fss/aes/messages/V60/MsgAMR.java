package com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import org.apache.commons.codec.binary.Base64;

import com.kewill.kcx.component.mapping.countries.common.PDFInformation;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAMP;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAMR;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: Export/aes<br>
 * Created		: 28.08.2008<br>
 * Description	: Einlesen einer FSS-AMR == Kids-ExportConfirmation.
 * 				: ZABIS V70: neu TsHead
 * 
 * @author  schmidt
 * @version 1.1.00
 */

public class MsgAMR {
	
	private TsVOR	vorSubset;
	private TsHead	headSubset;     //EI20121128
	private TsAMR   amrSubset;   
	private TsAMP   ampSubset;
	private PDFInformation pdfInformation;
	
	public PDFInformation getPdfInformation() {
		return pdfInformation;
	}
	public void setPdfInformation(PDFInformation pdfInformation) {
		this.pdfInformation = pdfInformation;
	}

	public TsVOR getVorSubset() {
		return vorSubset;
	}
	public void setVorSubset(TsVOR vorSubset) {
		this.vorSubset = vorSubset;
	}
	
	public TsHead getHeadSubset() {
		return headSubset;
	}
	public void setHeadSubset(TsHead head) {
		this.headSubset = head;
	}
    
	public void setAMR(TsAMR amr) {
		amrSubset = amr;
    }	
	public void setAMP(TsAMP amp) {
		ampSubset = amp;
    }
	
	public TsAMR getAmrSubset() {
		return amrSubset;
	}
	public TsAMP getAmpSubset() {
		return ampSubset;
	}

    public void readMessage(BufferedReader message) throws FssException {
        try {
            String line = "";
            byte[] out = null;

            while ((line = message.readLine()) != null) {
                String lineType = line.substring(0, 3);
                Utils.log("linetype " + lineType);
                if (lineType.equalsIgnoreCase("AMR")) {
                	amrSubset = new TsAMR();
                    String[] amr = line.split("" + FssUtils.FSS_FS);
                    amrSubset.setFields(amr);
                    pdfInformation = new PDFInformation();
                } else if (lineType.equalsIgnoreCase("AMP")) {
                    ampSubset = new TsAMP();
                    String[] amp = line.split("" + FssUtils.FSS_FS);
                    ampSubset.setFields(amp);
                    pdfInformation = new PDFInformation();
                    pdfInformation.setName(ampSubset.getPdfnam());
                    pdfInformation.setDirectory(ampSubset.getPdfpfd());
                    pdfInformation.setNewDirectory(ampSubset.getSubdir());
                    
                } else if (lineType.equalsIgnoreCase("%PD")) {
                	
                	// PDF einlesen: diese Zeile in einen neuen StringBuffer schieben
                	// danach alle weiteren Zeilen einlesen
                	
                	
                	
                	
                	/* Beispiel zum Decodieren 
                	 * byte[] bytes1 = new byte[ 112 ]; 
                    new Random().nextBytes( bytes1 ); 
                 
                    // buf in String 
                    String s = new BASE64Encoder().encode( bytes1 ); 
                    System.out.println( s ); 
                 
                    // Zum Beispiel: 
                    // QFgwDyiQ28/4GsF75fqLMj/bAIWNwOuBmE/SCl3H2XQFpSsSz0jtyR0LU+kLiwWsnSUZljJr97Hy 
                    // LA3YUbf96Ym2zx9F9Y1N7P5lsOCb/vr2crTQ/gXs757qaJF9E3szMN+E0CSSslDrrzcNBrlcQg== 
                    // String in byte[] 
                    byte[] bytes2 = new BASE64Decoder().decodeBuffer( s ); 
                    System.out.println( Arrays.equals(bytes1, bytes2) );    // true 
*/                	
                	StringBuffer buff = new StringBuffer();
                	buff.append(line);
                	
                	while ((line = message.readLine()) != null) {
                		buff.append(line);
                	}
                	out = Base64.encodeBase64Chunked(buff.toString().getBytes());
            	    String str = new String(out);
            	    BufferedReader br = new BufferedReader(new StringReader(str));
            	    String line76 = null;
            		while ((line76 = br.readLine()) != null)  {
            		   	System.out.println(line76);	
            		   	pdfInformation.getPdflist().add(line76);
            		    
            	    }
                	
                } else if (lineType.equalsIgnoreCase("NAC")) {
                	// Nachlaufsatz NAC nicht verarbeiten
                } else {
                    throw new FssException("Wrong message line " + lineType);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	
}

