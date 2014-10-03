/*
 * Funktion    : UidsToKidsExtern.java
 * Titel       :
 * Erstellt    : 28.08.2009
 * Author      : CSF GmbH / schmidt
 * Beschreibung: 
 * Anmerkungen : 
 * Parameter   : 
 * Rückgabe    : keine
 * Aufruf      : 
 *
 * Änderungen:
 * -----------
 * Author      :
 * Datum       :
 * Kennzeichen :
 * Beschreibung:
 * Anmerkungen :
 * Parameter   :
 *
 */
package com.kewill.kcx.component.mapping.common.start.customs.in;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.common.start.UidsToKidsConverter;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.formats.kcx.KcxEnvelope;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.util.MuleUtils;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: UidsToKidsExtern<br>
 * Erstellt		: 28.08.2009<br>
 * Beschreibung	: UidsToKids ohne MULE-Abhängigkeiten. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class UidsToKidsExtern extends UidsToKidsConverter {

    public String convert(File inFile, String encoding) throws Exception {
        if (!Config.isConfigured()) {
            Config.configure();
        }
        String message = getFileMessage(inFile, encoding);
        message = message.replaceAll("><", ">\n  <");
        if (Config.getLogXML()) {
            Utils.log("(UidsToKidsExtern convert) UIDS message = \n" + message);
        }
        // CK2011-08-04
        // String xml = readUids(message, encoding, EDirections.CountryToCustomer);
        String xml = null;
        try {
            xml = readUids(message, encoding, EDirections.CountryToCustomer);
        } catch (Exception e) {
            Utils.processException("(UidsToKidsExtern convert) Exception ", e, inFile.getName(), null);
        } catch (Error e) {
            Utils.processException("(UidsToKidsExtern convert) Error ", e, inFile.getName(), null);
        }
        xml = xml.replaceAll("><", ">\n  <");
        if (Config.getLogXML()) {
            Utils.log("(UidsToKidsExtern convert) KIDS Message = \n" + xml);
        }
        MuleUtils.writeInFileExtern(inFile.getName(), encoding, message, commonFieldsDTO);
        return xml;
    }
    
    private String getFileMessage(File inFile, String encoding) throws Exception {
        StringBuffer payload = new StringBuffer();
         
        try {
            FileInputStream fis = new FileInputStream(inFile);
            Utils.log("(UidsToKidsExtern getFileMessage) encoding = " + encoding);
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
    
    public void logAudit(KcxEnvelope kcxEnvelope, UidsHeader uidsHeader, CommonFieldsDTO commonFieldsDTO)
            throws Exception {
        // Audit-Log wird hier nicht gebraucht.
    }
    
}
