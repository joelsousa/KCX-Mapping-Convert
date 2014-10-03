package com.kewill.kcx.component.mapping.common.start.customs.in;

/*
 * Function    : FssToKidsxtern.java
 * Titel       :
 * Date        : 02.09.2009
 * Author      : Kewill CSF / Michael Schmidt
 * Description : FssToKids ohne MULE-Abhängigkeiten. 
 * 			   : 
 * Parameters  : 

 * Changes 
 * -----------
 * Author      :
 * Date        :
 * Label       :
 * Description:
 *
 */


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.common.start.FssToKidsConverter;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.kcx.KcxEnvelope;
import com.kewill.kcx.component.mapping.util.MuleUtils;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        : FssToKids<br>
 * Erstellt     : 02.09.2009<br>
 * Beschreibung : FssToKids ohne MULE-Abhängigkeiten.
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class FssToKidsExtern extends FssToKidsConverter {

    public String convert(File inFile, String encoding) throws Exception {
        if (!Config.isConfigured()) {
            Config.configure();
        }
        String message = getFileMessage(inFile, encoding);
        if (Config.getLogXML()) {
            Utils.log("(FssToKidsExtern convert) FSS message = \n" + message);
        }
        // String xml = readFss(message, inFile.getName(), encoding);
        // CK20110804
        // String xml = readFss(message, inFile.getName(), encoding, EDirections.CountryToCustomer);
        String xml = null;
        try {
            xml = readFss(message, inFile.getName(), encoding, EDirections.CountryToCustomer);
        } catch (Exception e) {
            Utils.processException("(FssToKidsExtern convert) Exception ", e, inFile.getName(), null);
        } catch (Error e) {
            Utils.processException("(FssToKidsExtern convert) Error ", e, inFile.getName(), null);
        }
        xml = xml.replaceAll("><", ">\n  <");
        if (Config.getLogXML()) {
            Utils.log("(FssToKidsExtern convert) KIDS Message = \n" + xml);
        }
        MuleUtils.writeInFileExtern(inFile.getName(), encoding, message, commonFieldsDTO);
        return xml;
    }
    
    public String convert(String payload, File outFile, String encoding) throws Exception {
        if (!Config.isConfigured()) {
            Config.configure();
        }
        
        if (Config.getLogXML()) {
            Utils.log("(FssToKidsExtern convert) FSS message = \n" + payload);
        }
//        String xml = readFss(payload, outFile.getName(), encoding);
        String xml = readFss(payload, outFile.getName(), encoding, EDirections.CountryToCustomer);
        xml = xml.replaceAll("><", ">\n  <");
        if (Config.getLogXML()) {
            Utils.log("(FssToKidsExtern convert) KIDS Message = \n" + xml);
        }
        return xml;
    }
    
    private String getFileMessage(File inFile, String encoding) throws Exception {
        StringBuffer payload = new StringBuffer();
         
        try {
            FileInputStream fis = new FileInputStream(inFile);
            InputStreamReader isr = null;
            if (encoding == null) {
                isr = new InputStreamReader(fis);
            } else {
                isr = new InputStreamReader(fis, encoding);
            }
            
            BufferedReader in = new BufferedReader(isr);
            String line = null;
            line = in.readLine();
            while (line != null) {
                payload.append(line);
                payload.append(Utils.LF);
                line = in.readLine();
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } 
         
        return payload.toString();
    }
    
    public void logAudit(TsVOR tsVor, KcxEnvelope kcxEnvelope, CommonFieldsDTO commonFieldsDTO)
            throws FssException {
        // Audit-Log wird hier nicht gebraucht.
    }
    
}
