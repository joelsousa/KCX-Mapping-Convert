package com.kewill.kcx.component.mapping.common.start;

/*
 * Function    : ReplaceKcxId
 * Titel       :
 * Date        : 27.02.2012
 * Author      : Kewill GmbH / Christine Kron
 * Description : Replace the Content of KIDS Header tag "Receiver" by localID. 
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

import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module       : ReplaceKcxId<br>
 * Created 		: 27.02.2012<br>
 * Description  : Replace the Content of KIDS Header tag "Receiver" by localID.
 * 
 * @author Kron
 * @version 1.0.00
 */
public class ReplaceKcxId {    
    
    public String replaceKcxId(String message, String localid) throws Exception {
        if (Config.getLogXML()) {
            Utils.log("(ReplaceKcxId replaceKcxId) message = \n" + message);
        }
        StringBuffer content = null;        
        int from = 0;
        int to = 0;
       
        try {
        	
        	//1. XML-Datei bis zum Feld Receiver rausschneiden
        	to = message.indexOf("<Receiver>");
        	if (to == -1) {
        		return message;
        	} 
        	content = new StringBuffer(message.substring(from, to));
            
        	//2. Receiver gefüllt mit der localid anhängen
        	// content.append("<Receiver>" + localid + "</Receiver>");
        	// C.K. 5.4.2012 Ab Feld </Receiver> alles anhängen, da Mehtod manchmal fehlte
        	content.append("<Receiver>" + localid);
        	
        	// 3. Nach Feld Receiver heißt ab Feld Method alles anhängen
        	// from = message.indexOf("<Method>");
        	// C.K. 5.4.2012 Ab Feld </Receiver> alles anhängen, da Mehtod manchmal fehlte
        	from = message.indexOf("</Receiver>");
			if (from == -1) {
        		return message;
        	} 
			content.append(message.substring(from));

		} catch (Exception e) {
			Utils.log("(ReplaceKcxId replaceKcxId) xml = \n" + message);
		}
        
        return content.toString();
    }
}
