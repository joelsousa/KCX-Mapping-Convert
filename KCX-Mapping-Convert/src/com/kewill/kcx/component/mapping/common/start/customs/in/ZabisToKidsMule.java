package com.kewill.kcx.component.mapping.common.start.customs.in;

/*
 * Function    : FssToKidsule.java
 * Titel       :
 * Date        : 04.03.2009
 * Author      : Kewill CSF / Christine Kron
 * Description : transformer called by Mule 
 * 				 to convert ZABIS-Fss messages in KIDS-Format
 * 				 evaluates the version and the procedure to start the
 * 				 mapping classes
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


import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;

import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul : ZabisToKidsMule<br>
 * Erstellt : 27.05.2010<br>
 * Beschreibung : Transformer called by Mule to convert ZABIS formats (FSS/KIDS) to KIDS format.
 *                Tries to detect the message format and the calls the correct transformer. * 
 * @author schmidt
 * @version 1.0.00
 */
public class ZabisToKidsMule implements Callable {

    public String onCall(MuleEventContext muleEventContext) throws Exception {
        MuleMessage message = muleEventContext.getMessage();
        String      payload = message.getPayloadAsString();
        
        String first3 = payload.substring(0, 3);
        Utils.log("(ZabisToKidsMule onCall) first3 = " + first3);
        if (first3.equalsIgnoreCase("VOR") || first3.equalsIgnoreCase("HEA")) {
            FssToKidsMule fssToKidsMule = new FssToKidsMule();
            return fssToKidsMule.onCall(muleEventContext);
        } else {
            KidsToKidsMule kidsToKidsMule = new KidsToKidsMule();
            return kidsToKidsMule.onCall(muleEventContext);
        }
    }
}
