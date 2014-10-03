/*
 * Modul    	: KidsToUids.java
 * Titel       	:
 * Date        	: 17.10.2008
 * @author      : Kewill CSF / Christine Kron
 * Description 	: Contains the Message ExportDeclaration in the ZABIS FSS-Format  
 * 			    : Version 6.0
 * Parameters  	:
 *  
 * Changes 
 * -----------
 * Author      : EI
 * Date        : 05.05.2009
 * Label       : EI20090505
 * Description : removed argument "typ" from setConsignee(...) aso.
 *
 * Author      : EI
 * Date        : 14.08.2009
 * Label       : EI20090814
 * Description : BAV, BZL 
 */

package com.kewill.kcx.component.mapping.formats.fss.ncts.messages.V70;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import org.apache.commons.codec.binary.Base64;

import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.countries.common.PDFInformation;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.ncts.messages.FssMessage62;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVMP;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVMR;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVMV;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul : MsgADA<br>
 * Erstellt : 11.07.2013<br>
 * Beschreibung : dei nachricht ist unverändert - nur TsHead ist dazu gekommen
 * 
 * @author iwaniuk
 * @version 7.0.00
 */
public class MsgVMR extends FssMessage62 {

	private TsVOR vorSubset;
	private TsHead headSubset;  //EI20130711:
	private TsVMR vmrSubset;
	private TsVMP vmpSubset;
	private TsVMV vmvSubset;
	private PDFInformation pdfInformation;	// zum Abspeichern einer PDF-Datei falls vorhanden
	
	public String getFssString() throws FssException {
		return getFssString("");
	}
	public String getFssString(String firstTs) throws FssException {
		String res = "";
		
		if (firstTs != null && firstTs.equalsIgnoreCase("HEAD")) {
			headSubset.mapVor2Head(vorSubset);
			if (headSubset != null && !headSubset.isEmpty()) {		 
				res = appendString(res, headSubset.teilsatzBilden());
			}
		} else {			
			if (vorSubset != null && !vorSubset.isEmpty()) {	 
				res = appendString(res, vorSubset.teilsatzBilden());	
			}
		}
	
		//if (vmrSubset != null && !vmrSubset.isEmpty()) {
		if (vmrSubset != null) { 	//EI20111011 im Hauptsatz keine isEmpty Abfrage
			res = appendString(res, vmrSubset.teilsatzBilden());
		}
		if (vmpSubset != null && !vmpSubset.isEmpty()) {
			res = appendString(res, vmpSubset.teilsatzBilden());
		}
		if (vmvSubset != null && !vmvSubset.isEmpty()) {
			res = appendString(res, vmvSubset.teilsatzBilden());
		}
		return res;
	}
	
	public void readMessage(BufferedReader message) throws FssException {
        try {
            String line = "";
            byte[] out = null;            
            
            while ((line = message.readLine()) != null) {
            	String lineType	= "";
				if (line.length() > 3) {
					lineType = line.substring(0, 3);
					Utils.log("linetype " + lineType);
				}
				
                if (lineType.equalsIgnoreCase("VMR")) {
                	vmrSubset = new TsVMR();
                    String[] vmr = line.split("" + FssUtils.FSS_FS);
                    vmrSubset.setFields(vmr);
                } else if (lineType.equalsIgnoreCase("VMP")) {    
                	vmpSubset = new TsVMP();
                    String[] vmp = line.split("" + FssUtils.FSS_FS);
                    vmpSubset.setFields(vmp);
                    pdfInformation = new PDFInformation();
                    pdfInformation.setName(vmpSubset.getPdfnam());
                    pdfInformation.setDirectory(vmpSubset.getPdfpfd());
                    pdfInformation.setNewDirectory(vmpSubset.getSubdir());
                    
                } else if (lineType.equalsIgnoreCase("VMV")) {    
                	vmvSubset = new TsVMV();
                    String[] vmv = line.split("" + FssUtils.FSS_FS);
                    vmvSubset.setFields(vmv);  
                }  else if (lineType.equalsIgnoreCase("NAC")) {
					Utils.log("ignore linetype NAC");
                }  else if (lineType.equalsIgnoreCase("%PD")) {
            	
                	// PDF einlesen: diese Zeile in einen neuen StringBuffer schieben
                	// danach alle weiteren Zeilen einlesen
                	StringBuffer buff = new StringBuffer();
                	buff.append(line + Utils.LF);
            	
                	while ((line = message.readLine()) != null) {
            		buff.append(line + Utils.LF);
                	}
                	out = Base64.encodeBase64Chunked(buff.toString().getBytes());
                	String str = new String(out);
                	BufferedReader br = new BufferedReader(new StringReader(str));
                	String line76 = null;
                	if (pdfInformation == null) {
                		pdfInformation = new PDFInformation();
                	}
                	while ((line76 = br.readLine()) != null)  {
                		if (Config.getLogXML()) {
                			System.out.println(line76); 
                		}        		   	
                		pdfInformation.getPdflist().add(line76);
                	}
                } else if (lineType.equals("")) {                	
                	Utils.log("empty line in FSS-message");
                } else {
                    throw new FssException("Wrong message line " + lineType);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public TsVOR getVorSubset() {
		return vorSubset;
	}
	public void setVorSubset(TsVOR vor) {
		this.vorSubset = vor;
	}

	public TsHead getHeadSubset() {
		return headSubset;
	}
	public void setHeadSubset(TsHead head) {
		this.headSubset = head;
	}
	
	public TsVMR getVmrSubset() {
		return vmrSubset;
	}
	public void setVmrSubset(TsVMR vmrSubset) {
		this.vmrSubset = vmrSubset;
	}

	public TsVMP getVmpSubset() {
		return vmpSubset;
	}
	public void setVmpSubset(TsVMP vmpSubset) {
		this.vmpSubset = vmpSubset;
	}
	
	public TsVMV getVmvSubset() {
		return vmvSubset;
	}
	public void setVmvSubset(TsVMV vmvSubset) {
		this.vmvSubset = vmvSubset;
	}
	public PDFInformation getPdfInformation() {
		return pdfInformation;
	}
	public void setPdfInformation(PDFInformation pdfInformation) {
		this.pdfInformation = pdfInformation;
	}
	
}
