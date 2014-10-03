package com.kewill.kcx.component.mapping.xml;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;

import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        : XmlPdfTgzIntern2<br>
 * Erstellt     : 08.03.2010<br>
 * Beschreibung : Diese Klasse generiert ein gezipptes Archiv aus einer Xml- und einer Pdf-
 *                Datei und gibt das gezippte Archiv als Bytearray zurück.
 *                Im Gegensatz zu der alten Klasse wird nicht mehr die Appache-Commons 
 *                Bibliothek benutzt, sondern Klassen aus dem normalen Java-API.
 *                Die Erstellung ist dadurch um ein Vielfaches schneller.
 * 
 * @author schmidt
 * @version 1.0.00
 */
public final class XmlPdfTgzIntern2 {
    
    private XmlPdfTgzIntern2() { }
    
    public static void main(String[] args) {
        
        try {
            if (args.length != 3) {
                throw new IllegalArgumentException("Parameter Anzahl ungültig! <xmlFile> <path> <filename>");
            } else {
                BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
                String line = "";
                StringBuffer str = new StringBuffer();
                while ((line = br.readLine()) != null) {
                    str.append(line + Utils.LF);
                }
                XmlPdfTgzIntern2.createTgz(str.toString(), args[1], args[2]);
            }
        } catch (Exception e) {
            Utils.log("(XmlPdfTgzIntern2 main) Exception = " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Generiert ein gezipptes Archiv aus einer Xml- und einer Pdf-Datei im Ordner, 
     * der dem Parameter "path" entspricht und gibt das gezippte Archiv als Bytearray zurück.
     * (Die Pdf Datei muss sich im Ordner "path" befinden und den Namen tragen,
     * der als "filename" übergeben wurde!
     * Die Xml Datei wird erstellt und der Payload in sie geschrieben.
     * Die neu erstelle Xml Datei und die neu erstellte TGZ Datei,
     * werden im Ordner "path" mit dem Namen "filename" neu angelegt.
     * 
     * @param xmlPayLoad XML-Payload
     * @param path       Path of the PDF file
     * @param filename   Name of the PDF file
     * 
     * @return The tgz archive as byte[].
     * @throws IOException Bei einem I/O-Fehler
     */
    public static Object createTgz(String xmlPayLoad, String path, String filename) throws IOException {
        //Basis-Name (ohne Suffix und Pfad)
        String baseOutFileName = Utils.getFileBasename(new File(path, filename)); 
        Utils.log("(XmlPdfTgzIntern2 createTgz) path            = " + path);
        Utils.log("(XmlPdfTgzIntern2 createTgz) baseOutFileName = " + baseOutFileName);
        
        File xmlFile = new File(path, baseOutFileName + ".xml");
        File pdfFile = new File(path, baseOutFileName + ".pdf");
        File tgzFile = new File(path, baseOutFileName + ".tgz");
        Utils.log("(XmlPdfTgzIntern2 createTgz) xmlFile         = " + xmlFile.getAbsolutePath());
        Utils.log("(XmlPdfTgzIntern2 createTgz) pdfFile         = " + pdfFile.getAbsolutePath());
        Utils.log("(XmlPdfTgzIntern2 createTgz) tgzFile         = " + tgzFile.getAbsolutePath());
        System.out.flush();
        
        //Xml Payload in xmlFile schreiben
        writeXmlFile(xmlFile, xmlPayLoad);

        // tgz erstellen
        return createTarGz(xmlFile, pdfFile, tgzFile);
    }
    
    
    /**
     * Schreibt den Payload, also die xml Daten in die im Parameter übergebene
     * Datei xmlFile.
     * Existiert diese Datei nicht wird sie leer erstellt. 
     * @param xmlFile Die XmlDatei in die geschreiben werden soll.
     * @param payLoad Der Payload, also die Daten die in die XmlDatei geschreiben werden sollen
     * @throws IOException Bei einem I/O-Fehler
     */
    private static void writeXmlFile(File xmlFile, String payLoad) throws IOException {
        PrintWriter writer = null;
        try {
            if (!xmlFile.exists()) {
                xmlFile.createNewFile();
                Utils.log("(XmlPdfTgzIntern2 writeXmlFile) Datei erfolgreich erstellt: File = "
                                                                                   + xmlFile.getName());
            }
            // XMLStream in Datei schreiben
            writer = new PrintWriter(xmlFile);
            writer.write(payLoad);
            Utils.log("(XmlPdfTgzIntern2 writeXmlFile) Payload erfolgreich in Datei geschrieben: File = "
                                                                                   + xmlFile.getName());
        } catch (IOException e) {
            Utils.log("(XmlPdfTgzIntern2 writeXmlFile) IOException = " + e.getMessage());
            throw e;
        } finally {
            writer.close();
        }
    }
    
    
    /**
     * Generiert ein gezipptes Archiv aus einer Xml- und einer Pdf-
     * Datei und gibt das gezippte Archiv als Bytearray zurück.
     * 
     * @param xmlFile (File) Die zu archivierende Xml Datei
     * @param pdfFile (File) Die zu archivierende Pdf Datei
     * @param tgzFile (File) Die zu erstellende Tgz Datei
     * @return The tgz archive as byte[].
     * @throws IOException Bei einem I/O-Fehler
     */
    private static Object createTarGz(File xmlFile, File pdfFile, File tgzFile) throws IOException {
        // tar-Archiv erstellen
        File tarFile = new File(tgzFile.getParent(), Utils.getFileBasename(tgzFile) + ".tar");
        TarArchiveEntry xmlEntry = new TarArchiveEntry(xmlFile, xmlFile.getName());
        TarArchiveEntry pdfEntry = new TarArchiveEntry(pdfFile, pdfFile.getName());
        
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        GZIPOutputStream gos = null;
        TarArchiveOutputStream out = null;
        try {
            fos = new FileOutputStream(tgzFile);
            bos = new BufferedOutputStream(fos);
            gos = new GZIPOutputStream(bos);
            out = new TarArchiveOutputStream(gos);

            // Xml Archiv hinzufügen*******************
            out.putArchiveEntry(xmlEntry);
            byte[] b = getByteArrayFromFile(xmlFile);
            out.write(b, 0, (int) xmlFile.length());
            out.closeArchiveEntry();
            Utils.log("(XmlPdfTgzIntern2 createTar) File: " + xmlFile.getName() + 
                                 " hinzugefügt zu Archiv: " + tarFile.getName());
            // ****************************************
            
            // Pdf Archiv hinzufügen*******************
            out.putArchiveEntry(pdfEntry);
            b = getByteArrayFromFile(pdfFile);
            out.write(b, 0, (int) pdfFile.length());
            out.closeArchiveEntry();
            Utils.log("(XmlPdfTgzIntern2 createTar) File: " + pdfFile.getName()  + 
                                 " hinzugefügt zu Archiv: " + tarFile.getName());
            // ****************************************

        } catch (IOException e) {
            Utils.log("(XmlPdfTgzIntern2 createTar) IOException = " + e.getMessage());
            throw e;
        } finally {
            closeStream(out);
            closeStream(gos);
            closeStream(bos);
            closeStream(fos);
        }
        
        return getByteArrayFromFile(tgzFile); 
    }
    
    /**
     * Läd Daten der als Parameter übergebenen Datei ein
     * und gibt diese als byte[] zurück.
     * @param file Datei von der gelesen werden soll
     * @return byte[] Die Daten der Datei als byte array
     * @throws IOException Bei einem I/O-Fehler
     */
    private static byte[] getByteArrayFromFile(File file) throws IOException {
        byte[] b = new byte[(int) file.length()];
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            fis.read(b);
        } catch (IOException e) {
            Utils.log("(XmlPdfTgzIntern2 getByteArrayFromFile) IOException = " + e.getMessage());
            throw e;
        } finally {
            closeStream(fis);
        }
        return b;
    }
    
    
    /**
     * Schliesst einen InputStream.
     * @param is The input stream to close. 
     */
    private static void closeStream(InputStream is) {
        try {
            is.close();
        } catch (IOException e) {
            Utils.log("(XmlPdfTgzIntern2 closeStream) IOException = " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    
    /**
     * Schliesst einen OutputStream.
     * @param os The output stream to close. 
     */
    private static void closeStream(OutputStream os) {
        try {
            os.close();
        } catch (IOException e) {
            Utils.log("(XmlPdfTgzIntern2 closeStream) IOException = " + e.getMessage());
            e.printStackTrace();
        }
    }
}
