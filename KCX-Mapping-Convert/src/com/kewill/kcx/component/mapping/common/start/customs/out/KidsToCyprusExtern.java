package com.kewill.kcx.component.mapping.common.start.customs.out;

/*
 * Function    : KidsToCyprusExtern.java
 * Titel       :
 * Date        : 08.09.2009
 * Author      : Kewill CSF / Christine Kron
 * Description : KidsToUids ohne MULE-Abhängigkeiten. 
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
import com.kewill.kcx.component.mapping.common.start.KidsToCyprusConverter;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.MuleUtils;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module       : KidsToCyprusExtern<br>
 * Created 		: 08.06.2011<br>
 * Description  : KidsToCyprus ohne MULE-Abhängigkeiten.
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class KidsToCyprusExtern extends KidsToCyprusConverter {
    
    public Object convert(String message, File outFile, String encoding) throws Exception {
        if (!Config.isConfigured()) {
            Config.configure();
        }
        if (Config.getLogXML()) {
            Utils.log("(KidsToCyprusExtern convert) KIDS message = \n" + message);
        }
//        Object cyprus = readKids(message, encoding, outFile.getName(), EDirections.CustomerToCountry);
        Object cyprus = null;
        try {
            cyprus = readKids(message, encoding, outFile.getName(), EDirections.CustomerToCountry);
        } catch (Exception e) {
            Utils.processException("(KidsToGreeceMule convert) Exception ", e, outFile.getName(), null);
        } catch (Error e) {
            Utils.processException("(KidsToGreeceExtern convert) Error ", e, outFile.getName(), null);
        }
        
        MuleUtils.writeFile(outFile.getName(), encoding, cyprus, commonFieldsDTO);

        return cyprus;
    }

    public void logAudit(KidsHeader kidsHeader, CommonFieldsDTO commonFieldsDTO) throws Exception {
        // Audit-Log wird hier nicht gebraucht.
    }
    
}
