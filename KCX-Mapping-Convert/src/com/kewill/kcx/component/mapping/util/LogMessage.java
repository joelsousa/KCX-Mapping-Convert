package com.kewill.kcx.component.mapping.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.sql.Timestamp;

import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;
import org.mule.util.IOUtils;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.common.start.KidsToUidsConverter;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.util.KCXProperties;

/**
 * Schreibt die Nachricht in eine Datei.
 * Kann in einem Service direkt als Component aufgerufen werdn um z. B. den Inhalt der Nachricht zu prüfen.
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class LogMessage extends KidsToUidsConverter implements Callable {

    public Object onCall(MuleEventContext muleEventContext) throws Exception {
        Utils.log("(LogMessage onCall) ");
        Config.configure("conf", "kcx.ini");
        getConfiguration();

        muleEventContext.transformMessageToString();
        MuleMessage message   = muleEventContext.getMessage();
        
        Object payload = message.getPayload();
        if (Config.getLogXML()) {
            Utils.log("(LogMessage onCall) xml = \n" + payload);
        }

        message.setStringProperty("MessageReferenceNumber", "MessageReferenceNumberDummy");
        message.setBooleanProperty(KCXProperties.SYNTACTICALLY_CORRECT, true);
        
        if (Utils.isStringEmpty(message.getStringProperty("filename", null))) {
            String timestamp     = Utils.timestampToString(new Timestamp(System.currentTimeMillis()));
            String newName = "MS_TEST_" + timestamp + ".xml";
            message.setStringProperty("filename", newName);   
        } else {
            String newName = "MS_TEST_" + message.getStringProperty("filename", null);
            message.setStringProperty("filename", newName);
        }
        
        commonFieldsDTO = new CommonFieldsDTO();
        commonFieldsDTO.setDirection(EDirections.CountryToCustomer);
        MuleUtils.writeFileMessage(message, payload, commonFieldsDTO);
        
        if (payload instanceof byte[]) {
            String stringPayload = new String((byte[]) payload);
            Utils.log("(LogMessage onCall) stringPayload = " + stringPayload);
            InputStream is = IOUtils.toInputStream(stringPayload);
            
            String customerOutPath = Config.getCustomerOutPath();
            File outDir = new File(customerOutPath, "out");
            outDir = Utils.checkDir(outDir.getPath(), "Output path for customers");
            String fileName = "STREAM_" + message.getStringProperty("filename", "");
            String encoding = message.getEncoding();
            int len = ((byte[]) payload).length;
            Utils.log("(LogMessage onCall) len = " + len);
            writeFileFromInputStream(outDir, fileName, encoding, is, len);
        }
                
        return payload;
    }
    
    
    private void writeFileFromInputStream(File outDir, String fileName, String encoding, InputStream payload, int len) throws Exception {
        if (outDir == null) {
            return;
        }
        Utils.log("(LogMessage writeFileFromInputStream) len = " + len);

        File tmpFile = new File(outDir, fileName);
        String baseFileName = tmpFile.getName();
        tmpFile = new File(outDir, "." + baseFileName);
        File outFile = new File(outDir, baseFileName);
        Utils.log("(LogMessage writeFile) Writing payload to " + tmpFile.getPath());
        FileOutputStream fos = null;
        byte[] b = new byte[len];
        try {
            fos = new FileOutputStream(tmpFile);
            int read = payload.read(b);
            Utils.log("(LogMessage writeFileFromInputStream) bytes read: " + read);
            fos.write(b);
            fos.close();
            Utils.log("(LogMessage writeFile) Renaming " + tmpFile.getPath() + " to " + outFile.getPath());
            tmpFile.renameTo(outFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fos.close();
        }
    }
    

    
    
    public void logAudit(KidsHeader kidsHeader, CommonFieldsDTO commonFieldsDTO) throws Exception {
        return;
    }

    private static void getConfiguration() {
        Utils.setDebug(Config.getDebug());
        Utils.setLogLevel(Config.getLogLevel());
    }
    
    
}
