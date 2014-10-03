/*
 * Funktion    : TestMS.java
 * Titel       :
 * Erstellt    : 15.06.2010
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
package com.kewill.kcx.component.mapping.test;

import com.kewill.kcx.component.mapping.formats.uids.common.EUidsHeaderTags;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: TestMS<br>
 * Erstellt		: 15.06.2010<br>
 * Beschreibung	: Testklasse zum ausprobieren von Codeänderungen. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class TestMS {

    public static void main(String[] args) {
        TestMS ms = new TestMS();
        try {
            ms.replace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void replace() {
    	String content = "ABC\r\nDEF";
    	
        Utils.log("(TestMS replace) content = " + content);
    	content = content.replaceAll("[\r\n]", "");
        // content = content.replaceAll("[\r]", "");
        Utils.log("(TestMS replace) content = " + content);
    	content = "ABC\r\nDEF";
        Utils.log("(TestMS replace) content = " + content);
    	content = content.replaceAll("\n", "");
        // content = content.replaceAll("\r", "");
        Utils.log("(TestMS replace) content = " + content);

        String referenceNumber = "Reference: abc def/ghi     jkl".replaceAll("[ \t]+", "_");
        String messageID       = "MessageID: abc def/ghi \t jkl".replaceAll("[ \t]+", "_");
        
        Utils.log("(MuleUtils createFileName) referenceNumber = " + referenceNumber);
        Utils.log("(MuleUtils createFileName) messageID       = " + messageID);
        
    }
    
    public void setHeaderFields() throws Exception {
        String text = null;
        String[] tags = new String[] {"To", "From", "MessageId", "Procedure", "Direction"};
        int size = tags.length;
        
        EUidsHeaderTags tag =  null;
        
        for (int i = 0; i < size; i++) {
            text = tags[i];
            try {
                tag =  EUidsHeaderTags.valueOf(text);
            } catch (IllegalArgumentException e) {
//                e.printStackTrace();
                tag =  EUidsHeaderTags.valueOf("Unknown");
            }
            switch(tag) {
                case To:
                    Utils.log("(TestMS setHeaderFields) To");
                    break;
                case From:
                    Utils.log("(TestMS setHeaderFields) From");
                    break;
                case MessageID:
                    Utils.log("(TestMS setHeaderFields) MessageID");
                    break;
                case InReplyTo:
                    Utils.log("(TestMS setHeaderFields) InReplyTo");
                    break;
                case SentAt:
                    Utils.log("(TestMS setHeaderFields) SentAt");
                    break;
                case Act:
                    Utils.log("(TestMS setHeaderFields) Act");
                    break;
                case AdditionalInformation:
                    Utils.log("(TestMS setHeaderFields) AdditionalInformation");
                    break;
                case MessageType:
                    Utils.log("(TestMS setHeaderFields) MessageType");
                    break;
                case MessageVersion:
                    Utils.log("(TestMS setHeaderFields) MessageVersion");
                    break;                          
                    
                default: 
                    Utils.log("(TestMS setHeaderFields) Unknown UIDS header tag '" + text + "'.");
                    break;
            }
        }
                
    }

}
