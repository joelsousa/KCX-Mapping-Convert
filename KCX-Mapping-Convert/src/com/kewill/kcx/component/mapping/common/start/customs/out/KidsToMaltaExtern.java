package com.kewill.kcx.component.mapping.common.start.customs.out;

/*
 * Function    : KidsToMaltaExtern.java
 * Titel       :
 * Date        : 16.08.2013
 * Author      : krzoska
 * Description : KidsToMalta ohne MULE-Abhängigkeiten. 
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
import com.kewill.kcx.component.mapping.common.start.KidsToMaltaConverter;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.MuleUtils;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module       : KidsToMaltaExtern <br>
 * Created 		: 16.08.2013<br>
 * Description  : KidsToMalta ohne MULE-Abhängigkeiten.
 * 
 * @author krzoska
 * @version 1.0.00
 */
public class KidsToMaltaExtern extends KidsToMaltaConverter {
    
    public Object convert(String message, File outFile, String encoding) throws Exception {
        if (!Config.isConfigured()) {
            Config.configure();
        }
        if (Config.getLogXML()) {
            Utils.log("(KidsToMaltaExtern convert) KIDS message = \n" + message);
        }
        Object malta = null;
        try {
            malta = readKids(message, encoding, outFile.getName(), EDirections.CustomerToCountry);
        } catch (Exception e) {
            Utils.processException("(KidsToMaltaMule convert) Exception ", e, outFile.getName(), null);
        } catch (Error e) {
            Utils.processException("(KidsToMaltaExtern convert) Error ", e, outFile.getName(), null);
        }
        
        MuleUtils.writeFile(outFile.getName(), encoding, malta, commonFieldsDTO);

        return malta;
    }

    public void logAudit(KidsHeader kidsHeader, CommonFieldsDTO commonFieldsDTO) throws Exception {
        // Audit-Log wird hier nicht gebraucht.
    }
    
}
