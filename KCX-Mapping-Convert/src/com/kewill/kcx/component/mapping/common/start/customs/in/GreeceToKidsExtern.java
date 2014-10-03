/*
 * Funktion    : GreeceToKidsExtern.java
 * Titel       :
 * Erstellt    : 18.07.2011
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
import com.kewill.kcx.component.mapping.common.start.GreeceToKidsConverter;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.formats.greece.common.GreeceHeader;
import com.kewill.kcx.component.mapping.formats.kcx.KcxEnvelope;
import com.kewill.kcx.component.mapping.util.MuleUtils;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: GreeceToKidsExtern<br>
 * Erstellt		: 18.07.2011<br>
 * Beschreibung	: GreeceToKids ohne MULE-Abhängigkeiten. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class GreeceToKidsExtern extends GreeceToKidsConverter {

    public String convert(File inFile, String encoding) throws Exception {
        if (!Config.isConfigured()) {
            Config.configure();
        }
        String message = getFileMessage(inFile, encoding);
        message = message.replaceAll("><", ">\n  <");
        if (Config.getLogXML()) {
            Utils.log("(GreeceToKidsExtern convert) Greece message = \n" + message);
        }
//        String xml = readGreece(message, encoding, EDirections.CountryToCustomer);
        String xml = null;
        try {
            xml = readGreece(message, encoding, EDirections.CountryToCustomer);
        } catch (Exception e) {
            Utils.processException("(KidsToGreeceMule convert) Exception ", e, inFile.getName(), null);
        } catch (Error e) {
            Utils.processException("(KidsToGreeceExtern convert) Error ", e, inFile.getName(), null);
        }
        //TODO-EI20110707: sollte es hier CustomerToCountry  stehen?
        if (xml != null) {  // MS20111020
            xml = xml.replaceAll("><", ">\n  <");
            if (Config.getLogXML()) {
                Utils.log("(GreeceToKidsExtern convert) KIDS Message = \n" + xml);
            }
            
        }   // MS20111020
        if (!commonFieldsDTO.isAlreadyReceived()) {
            MuleUtils.writeInFileExtern(inFile.getName(), encoding, message, commonFieldsDTO);
        }

        return xml;
    }
    
    private String getFileMessage(File inFile, String encoding) throws Exception {
        StringBuffer payload = new StringBuffer();
         
        try {
            FileInputStream fis = new FileInputStream(inFile);
            Utils.log("(GreeceToKidsExtern getFileMessage) encoding = " + encoding);
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
    
    public void logAudit(KcxEnvelope kcxEnvelope, GreeceHeader header, CommonFieldsDTO commonFieldsDTO)
            throws Exception {
        // Audit-Log wird hier nicht gebraucht.
    }
    
}
