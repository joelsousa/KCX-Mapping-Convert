package com.kewill.kcx.component.mapping.common.start.customs.out;

/*
 * Function    : KidsToGreeceExtern.java
 * Titel       :
 * Date        : 18.07.2011
 * Author      : Kewill CSF / schmidt
 * Description : KidsToGreece ohne MULE-Abhängigkeiten. 
 * 			   : 
 * Parameters  : 

 * Changes 
 * -----------
 * Author      : 
 * Date        : 
 * Label       : 
 * Description : 
 *
 */

import java.io.File;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.common.start.KidsToGreeceConverter;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.MuleUtils;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module       : KidsToGreeceExtern<br>
 * Created 		: 18.07.2011<br>
 * Description  : KidsToGreece ohne MULE-Abhängigkeiten.
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class KidsToGreeceExtern extends KidsToGreeceConverter {
    
    public Object convert(String message, File outFile, String encoding) throws Exception {
        if (!Config.isConfigured()) {
            Config.configure();
        }
        if (Config.getLogXML()) {
            Utils.log("(KidsToGreeceExtern convert) KIDS message = \n" + message);
        }
        Object greece = null;
        try {
            greece = readKids(message, encoding, outFile.getName(), EDirections.CustomerToCountry);
        } catch (Exception e) {
            Utils.processException("(KidsToGreeceMule convert) Exception ", e, outFile.getName(), null);
        } catch (Error e) {
            Utils.processException("(KidsToGreeceExtern convert) Error ", e, outFile.getName(), null);
        }
        
        MuleUtils.writeFile(outFile.getName(), encoding, greece, commonFieldsDTO);

        return greece;
    }

    public void logAudit(KidsHeader kidsHeader, CommonFieldsDTO commonFieldsDTO) throws Exception {
        // Audit-Log wird hier nicht gebraucht.
    }
    
}
