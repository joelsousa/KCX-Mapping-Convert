package com.kewill.kcx.component.mapping.common.start.customer.in;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.common.start.CMPToKidsConverter;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.formats.kcx.KcxEnvelope;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.MuleUtils;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        : CmpToKidsExtern<br>
 * Erstellt     : 14.06.2013<br>
 * Beschreibung : CmpToKids ohne MULE-Abhängigkeiten. 
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class CmpToKidsExtern extends CMPToKidsConverter {
    
    public String convert(File inFile, String encoding) throws Exception {
        if (!Config.isConfigured()) {
            Config.configure();
        }
        String message = getFileMessage(inFile, encoding);
        message = message.replaceAll("><", ">\n  <");
        if (Config.getLogXML()) {
            Utils.log("(CmpToKidsExtern convert) KIDS message = \n" + message);
        }

        String xml = null;
        try {
            xml = readCmp(message, encoding, EDirections.CustomerToCountry);
        } catch (Exception e) {
            Utils.processException("(CmpToKidsExtern onCall) Exception ", e, inFile.getName(), null);
        } catch (Error e) {
            Utils.processException("(CmpToKidsExtern onCall) Error ", e, inFile.getName(), null);
        }
        if (xml != null) {
            xml = xml.replaceAll("><", ">\n  <");
        }
        if (Config.getLogXML()) {
            Utils.log("(CmpToKidsExtern convert) KIDS Message = \n" + xml);
        }
        MuleUtils.writeInFileExtern(inFile.getName(), encoding, message, commonFieldsDTO);
        return xml;
    }
    
    private String getFileMessage(File inFile, String encoding) throws Exception {
        StringBuffer payload = new StringBuffer();
         
        try {
            FileInputStream fis = new FileInputStream(inFile);
            Utils.log("(CmpToKidsExtern getFileMessage) encoding = " + encoding);
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
    
    public void logAudit(KcxEnvelope kcxEnvelope, KidsHeader kidsHeader, CommonFieldsDTO commonFieldsDTO)
            throws Exception {
        // Audit-Log wird hier nicht gebraucht.
    }
    
}
