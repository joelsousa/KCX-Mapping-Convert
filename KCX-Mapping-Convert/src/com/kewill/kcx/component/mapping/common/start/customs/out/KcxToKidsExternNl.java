/*
 * Funktion    : KcxToKids.java
 * Titel       :
 * Erstellt    : 11.12.2008
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
package com.kewill.kcx.component.mapping.common.start.customs.out;

import java.io.File;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.common.start.KcxToKidsConverter;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.formats.kcx.KcxEnvelope;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: KcxToKidsExtern<br>
 * Erstellt		: 18.05.2010<br>
 * Beschreibung	: KcxToKids ohne MULE-Abhängigkeiten. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class KcxToKidsExternNl extends KcxToKidsConverter {
    
    public String convert(String message, File outFile, String encoding) throws Exception {
        if (!Config.isConfigured()) {
            Config.configure();
        }
        if (Config.getLogXML()) {
            Utils.log("(KcxToKidsExternNl convert) KIDS message = \n" + message);
        }
        
        String xml = null;
        try {
        	xml = removeKcxEnvelope(message, encoding, EDirections.CustomerToCountry);
        } catch (Exception e) {
            Utils.processException("(KcxToKidsExternNl convert) Exception ", e, outFile.getName(), null);
        } catch (Error e) {
            Utils.processException("(KcxToKidsExternNl convert) Error ", e, outFile.getName(), null);
        }
        
        return xml;
    }

    public void logAudit(KcxEnvelope kcxEnvelope, KidsHeader kidsHeader, 
                                                    CommonFieldsDTO commonFieldsDTO) throws Exception {
        // Audit-Log wird hier nicht gebraucht.
    }
    
}

