/*
 * Funktion    : MuleUtils.java
 * Titel       :
 * Erstellt    : 19.11.2010
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
package com.kewill.kcx.component.mapping.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Timestamp;

import org.mule.api.MuleMessage;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.conf.Config;

/**
 * Modul		: MuleUtils<br>
 * Erstellt		: 19.11.2010<br>
 * Beschreibung	: Utility class for methods using Mule objects. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public final class MuleUtils {
    
    private MuleUtils() {
    }
    
    
    public static void writeFileMessage(MuleMessage message, Object payload, CommonFieldsDTO commonFieldsDTO) 
                                                                                                throws Exception {
        if (!Config.isWriteOutFiles()) {
            return;
        }
        
        // MS20111115 Begin
        String fileName = getFileName(message, commonFieldsDTO);
//        String fileName = message.getStringProperty("filename", "");
//        if (Utils.isStringEmpty(fileName)) {
//            fileName = message.getStringProperty("originalFilename", "");
//            if (Utils.isStringEmpty(fileName)) {
////                fileName = commonFieldsDTO.getKcxId() + "_" + commonFieldsDTO.getMessageReferenceNumber();
//                fileName = createFileName(commonFieldsDTO);
//            }
//        }
        // MS20111115 End
        String encoding = message.getEncoding();
        
        writeFile(fileName, encoding, payload, commonFieldsDTO);
    }

    public static void writeFile(String fileName, String encoding, Object payload, CommonFieldsDTO commonFieldsDTO) 
                                                                                                    throws Exception {
        File outDir = null;
        if (commonFieldsDTO.getDirection() == EDirections.CustomerToCountry) {
            String countryOutPath = Config.getCountryOutPath();
            if (Utils.isStringEmpty(countryOutPath)) {
                return;
            }
            String outDirTarget = commonFieldsDTO.getCountryCode();
            File outDirTemp = new File(countryOutPath, outDirTarget);
            outDir = new File(outDirTemp, "out");
            outDir = Utils.checkDir(outDir.getPath(), "Output path for " + commonFieldsDTO.getCountryCode());
        } else {
            String customerOutPath = Config.getCustomerOutPath();
            if (Utils.isStringEmpty(customerOutPath)) {
                return;
            }
            outDir = new File(customerOutPath, "out");
            outDir = Utils.checkDir(outDir.getPath(), "Output path for customers");
        }

        // MS20111115 Begin
        writeFile(outDir, fileName, encoding, payload);
//        if (outDir == null) {
//            return;
//        }
//        
//        File outFile = new File(outDir, fileName);
//        Utils.log("(MuleUtils writeFile) Writing payload to " + outFile.getPath());
//        FileOutputStream fos = null;
//        try {
//            fos = new FileOutputStream(outFile);
//            if (payload instanceof String) {
//                OutputStreamWriter out = new OutputStreamWriter(fos, encoding);
//                out.write((String) payload);
//                out.close();
//            } else {
//                fos.write((byte[]) payload);
//                fos.close();
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            fos.close();
//        }
        // MS20111115 End
    }

    
    public static void writeCustomerMessage(MuleMessage message, Object payload, CommonFieldsDTO commonFieldsDTO) 
                                                                                                  throws Exception {
        if (!Config.isWriteOutFiles()) {
            return;
        }
        
        String fileName = getFileName(message, commonFieldsDTO);
        String encoding = message.getEncoding();
        String customerOutPath = Config.getCustomerOutPath();
        if (Utils.isStringEmpty(customerOutPath)) {
            Utils.log("(MuleUtils writeCustomerFile) out.customerPath not set. Message not written to file.");
            return;
        }
        File outDir = new File(customerOutPath, commonFieldsDTO.getKcxId());
        outDir = Utils.checkDir(outDir.getPath(), "Output path for " + commonFieldsDTO.getKcxId());
        Utils.log("IWA writeCustomerMessage: fileName = " + fileName);
        writeFile(outDir, fileName, encoding, payload);
    }

    public static void writeCustomerFile(String fileName, String encoding, Object payload, 
                                                                 CommonFieldsDTO commonFieldsDTO) throws Exception {
        if (!Config.isWriteOutFiles()) {
            return;
        }
        
        String customerOutPath = Config.getCustomerOutPath();
        if (Utils.isStringEmpty(customerOutPath)) {
            Utils.log("(MuleUtils writeCustomerFile) out.customerPath not set. Message not written to file.");
            return;
        }
        File outDir = new File(customerOutPath, commonFieldsDTO.getKcxId());
        outDir = Utils.checkDir(outDir.getPath(), "Output path for " + commonFieldsDTO.getKcxId());

        writeFile(outDir, fileName, encoding, payload);
    }

    private static void writeFile(File outDir, String fileName, String encoding, Object payload) throws Exception {
        if (outDir == null) {
            return;
        }
        
        File tmpFile = new File(outDir, fileName);
        String baseFileName = tmpFile.getName();
        tmpFile = new File(outDir, "." + baseFileName);
        File outFile = new File(outDir, baseFileName);
        Utils.log("(MuleUtils writeFile) Writing payload to " + tmpFile.getPath());
        Utils.log("(MuleUtils writeFile) encoding " + encoding);  //EI20130404
        FileOutputStream fos = null;
        if (fileName.startsWith("KidsExpdat")) {  //iwa-heute
        	encoding = "ISO-8859-1";
        	Utils.log("(MuleUtils writeFile) encoding iso " + encoding);  //EI20130404
        }
        try {
            fos = new FileOutputStream(tmpFile);
            if (payload instanceof String) {
                OutputStreamWriter out = new OutputStreamWriter(fos, encoding);
                Utils.log("(MuleUtils writeFile) encoding out " + encoding);  //EI20130404
                out.write((String) payload);
                out.close();
            } else {
                fos.write((byte[]) payload);
                fos.close();
            }
            Utils.log("(MuleUtils writeFile) Renaming " + tmpFile.getPath() + " to " + outFile.getPath());
            tmpFile.renameTo(outFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fos.close();
        }
    }
    public static void writeInFileExtern(String fileName, String encoding, Object payload, 
                                                                    CommonFieldsDTO commonFieldsDTO) throws Exception {
        if (!Config.isWriteInFiles()) {
            return;
        }
        
        // MS20111020 Begin
        if (payload == null) {
            return;
        }
        // MS20111020 End
        
        if (Utils.isStringEmpty(fileName)) {
            fileName = createFileName(commonFieldsDTO);
        }
        writeInFile(fileName, encoding, payload, commonFieldsDTO);
    }
    
    public static void writeInFileMessage(MuleMessage message, Object payload, CommonFieldsDTO commonFieldsDTO) 
                                                                                                    throws Exception {
        if (!Config.isWriteInFiles()) {
            return;
        }
        
        // MS20111020 Begin
        if (payload == null) {
            return;
        }
        // MS20111020 End
        
        String fileName = message.getStringProperty("filename", "");
        Utils.log("(MuleUtils writeInFileMessage) filename = " + fileName);
        if (Utils.isStringEmpty(fileName)) {
            fileName = message.getStringProperty("originalFilename", "");
            Utils.log("(MuleUtils writeInFileMessage) originalFilename = " + fileName);
            if (Utils.isStringEmpty(fileName)) {
                fileName = createFileName(commonFieldsDTO);
                message.setStringProperty("filename", fileName);
                Utils.log("(MuleUtils writeInFileMessage) created fileName = " + fileName);
            }
        }
        String encoding = message.getEncoding();
        
        writeInFile(fileName, encoding, payload, commonFieldsDTO);
    }
    
    public static void writeInFile(String fileName, String encoding, Object payload, CommonFieldsDTO commonFieldsDTO) 
                                                                                                    throws Exception {
        File outDir = null;
        String inPath = null;
        if (commonFieldsDTO.getDirection() == EDirections.CustomerToCountry) {
            inPath = Config.getCustomerInPath();
        } else {
            inPath = Config.getCountryInPath();
        }
        if (Utils.isStringEmpty(inPath)) {
            return;
        }

        outDir = new File(inPath);
        outDir = Utils.checkDir(outDir.getPath(), "Input path");
        if (outDir == null) {
            return;
        }
        
        File outFile = new File(outDir, fileName);
        Utils.log("(MuleUtils writeInFile) Writing payload to " + outFile.getPath());
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(outFile);
            if (payload instanceof String) {
                OutputStreamWriter out = new OutputStreamWriter(fos, encoding);
                out.write((String) payload);
                out.close();
            } else {
                fos.write((byte[]) payload);
                fos.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fos.close();
        }
    }

    // MS20140219 Begin    
    private static String getFileName(MuleMessage message, CommonFieldsDTO commonFieldsDTO) {
    // MS20140219 End
        String fileName = message.getStringProperty("filename", "");
        Utils.log("IWA1 fileName " + fileName);
        if (Utils.isStringEmpty(fileName)) {
            fileName = message.getStringProperty("originalFilename", "");
            Utils.log("IWA2 fileName " + fileName);
            if (Utils.isStringEmpty(fileName)) {
                fileName = createFileName(commonFieldsDTO);
                Utils.log("IWA3 fileName " + fileName);
            }
        }
        return fileName;
    }
    
    public static String createFileName(CommonFieldsDTO commonFieldsDTO) {
        String kcxId           = commonFieldsDTO.getKcxId().toUpperCase();
        String[] tokens        = kcxId.split("[.]");
        String fromCountry     = tokens[0];
        // 20120625MS Begin
        // String company         = tokens[1];
        String company          = "";
        if (tokens.length > 1) {
            company = tokens[1];
        }
        // 20120625MS End
        String branch          = "";
        if (tokens.length > 2) {
            branch = tokens[2];
        }
        
        String referenceNumber = commonFieldsDTO.getReferenceNumber();
        String messageID       = commonFieldsDTO.getMessageReferenceNumber();
        Utils.log("IWA4 createFileName " + referenceNumber + "/" + "/" + messageID);
// MS20111021 Begin        
//        String refNr = null;
//        if (Utils.isStringEmpty(referenceNumber)) {
//            refNr = messageID;
//        } else {
//            refNr = referenceNumber;
//        }
        if (Utils.isStringEmpty(referenceNumber)) {
            referenceNumber = "NoReferenceNumber";
        } else {
            referenceNumber = referenceNumber.replaceAll("[ \t/:]+", "_");
        }
        if (Utils.isStringEmpty(messageID)) {
            messageID = "NoMessageId";
        } else {
            messageID = messageID.replaceAll("[ \t/:]+", "_");
        }
// MS20111021 End        

        
        String timestamp     = Utils.timestampMillisToString(new Timestamp(System.currentTimeMillis()));
//        long lastnum = Db.getRunningNumberForKcxId(kcxId);
//        String runningNumber = Utils.nFormat(lastnum, 9);
        String extension     = ".xml";
        
//      String newName = fromCountry + "01" + toCountry + "01_" + company + "_" + branch + "_" + 
//                                                                         timestamp + runningNumber + extension;
//      String newName = fromCountry + "01" + toCountry + "01_" + company + "_" + branch + "_" + timestamp + extension;
// MS20111021 Begin        
//        String newName = fromCountry + "_" + company + "_" + branch + "_" + timestamp + "_" + refNr + extension;
        String newName = fromCountry + "_" + company + "_" + branch + "_" + timestamp + "_" + referenceNumber + "_" + 
                                                                                                messageID + extension;
// MS20111021 End        
        Utils.log("IWA5 createFileName newName" + newName);
        return newName;
        
    }

    // MS20140221 Begin
    public static String getShortFileName(MuleMessage message, CommonFieldsDTO commonFieldsDTO) {
        String fileName = message.getStringProperty("filename", "");
        if (Utils.isStringEmpty(fileName)) {
            fileName = message.getStringProperty("originalFilename", "");
            if (Utils.isStringEmpty(fileName)) {
                fileName = createShortFileName(commonFieldsDTO);
            }
        }
        return fileName;
    }
    // MS20140221 End

    // MS20140221 Begin
    public static String createShortFileName(CommonFieldsDTO commonFieldsDTO) {
        String kcxId           = commonFieldsDTO.getKcxId().toUpperCase();
        String[] tokens        = kcxId.split("[.]");
        String fromCountry     = tokens[0];
        String company          = "";
        if (tokens.length > 1) {
            company = tokens[1];
        }
        String branch          = "";
        if (tokens.length > 2) {
            branch = tokens[2];
        }
        
        String messageID       = commonFieldsDTO.getMessageReferenceNumber();
        if (Utils.isStringEmpty(messageID)) {
            messageID = "NoMessageId";
        } else {
            messageID = messageID.replaceAll("[ \t/:]+", "_");
        }
        
        String newName = fromCountry + "_" + company + "_" + branch + "_" + messageID;
        return newName;
        
    }
    // MS20140221 End
}
