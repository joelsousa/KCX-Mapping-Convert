package com.kewill.kcx.component.mapping.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.compressors.CompressorOutputStream;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.apache.commons.compress.utils.IOUtils;

import com.kewill.kcx.component.mapping.util.Utils;
/**
 * Diese Klasse generiert ein gezipptes
 * Archiv aus einer Xml- und einer Pdf-
 * Datei und gibt das gezippte Archiv als Bytearray zurück.
 * @author messer
 * @version 1.0.00
 */
public final class XmlPdfTgzIntern {
	private XmlPdfTgzIntern() { }
	
	public static void main(String[] args) {
		try {
			if (args.length != 3) {
				throw new IllegalArgumentException(
						"Parameter Anzahl ungültig! <xmlFile> <path> <filename>");
			} else {
			BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
			String line = "";
			String str = "";
			while ((line = br.readLine()) != null) {
				str += line;
			}
			byte[] b = (byte[]) XmlPdfTgzIntern.createTgz(str, args[1], args[2]);
//			byte[] b = (byte[]) XmlPdfTgzIntern.createTgz(new File("./testdaten/XMLANDPDF.xml"), 
//			                                              new File("./testdaten/XMLANDPDF.pdf"), 
//			                                              new File("./testdaten/XMLANDPDF.tgz"));
			Utils.log("TGZ byte[] =" + (new String(b)));
			}
		} catch (Exception e) {
			Utils.log("(XmlPdfTgzIntern main) Exception = "
					+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Generiert ein gezipptes Archiv aus einer Xml- und einer Pdf-
	 * Datei im Ordner, der dem Parameter "path" entspricht 
	 * und gibt das gezippte Archiv als Bytearray zurück.
	 * (Die Pdf Datei muss sich im Ordner "path" befinden und den Namen tragen,
	 * der als "filename" übergeben wurde!
	 * Die Xml Datei wird erstellt und der Payload in sie geschrieben.
	 * Die neu erstelle Xml Datei und die neu erstellte TGZ Datei,
	 * werden im Ordner "path" neu angelegt mit dem Namen "filename")
	 * @param xmlPayLoad (String) Der Xml-Payload
	 * @param path (String)Pfad der Xml/Pdf Datei
	 * @param filename (String)Dateiname der Xml/Pdf
	 * 
	 * @return Object byte[tgzFile]
	 * @throws Exception Bei einem Fehler beim Erstellen des tgz
	 */
	public static Object createTgz(String xmlPayLoad, String path, String filename) throws Exception {
		//Basis-Name (ohne Suffix und Pfad)
		String baseOutFileName = Utils.getFileBasename(new File(path, filename)); 
		Utils.log("(XmlPdfTgzIntern createTgz) path            = " + path);
		Utils.log("(XmlPdfTgzIntern createTgz) baseOutFileName = " + baseOutFileName);
		
		File xmlFile = new File(path, baseOutFileName + ".xml");
		
		//Xml Payload in xmlFile schreiben
		writeXmlFile(xmlFile, xmlPayLoad);
		
		File pdfFile = new File(path, baseOutFileName + ".pdf");
		
		Utils.log("(XmlPdfTgzIntern createTgz) xmlFile         = " + xmlFile.getAbsolutePath());
		Utils.log("(XmlPdfTgzIntern createTgz) pdfFile         = " + pdfFile.getAbsolutePath());
		
		File tgzFile = new File(path, baseOutFileName + ".tgz");
		
        return createTgz(xmlFile, pdfFile, tgzFile);
	}
	
	/**
	 * Generiert ein gezipptes Archiv aus einer Xml- und einer Pdf-
	 * Datei und gibt das gezippte Archiv als Bytearray zurück.
	 * 
	 * @param xmlFile (File) Die zu archivierende Xml Datei
	 * @param pdfFile (File) Die zu archivierende Pdf Datei
	 * @param tgzFile (File) Die zu erstellende Tgz Datei
	 * 
	 * @return Object byte[tgzFile]
	 * @throws Exception Bei einem Fehler beim Archiviern oder Packen des tgz files.
	 */
	public static Object createTgz(File xmlFile, File pdfFile, File tgzFile) throws Exception {
		byte[] data = null;
		// tar-Archive erstellen
		File tarFile = new File(tgzFile.getParent(), Utils.getFileBasename(tgzFile) + ".tar");
		createTar(xmlFile, pdfFile, tarFile);
        Utils.log("(XmlPdfTgzIntern createTgz) tarFile         = " + tarFile.getAbsolutePath());
        // tar-Archiv komprimieren mit Gzip
        zip(tarFile, tgzFile);
        Utils.log("(XmlPdfTgzIntern createTgz) tgzFile         = " + tgzFile.getAbsolutePath());
        // tarFile.delete() funktioniert nicht. Returncode war immer false.
        // tarFile.deleteOnExit();
        boolean ret = tarFile.delete();
        Utils.log("(XmlPdfTgzIntern createTgz) tarFile deleted = " + ret);
        //Tgz als bytearray lesen
        data = getByteArrayFromFile(tgzFile); 
		
        return data;
	}
	
	/**
	 * Schreibt den Payload, also die xml Daten in die im Parameter übergebene
	 * Datei xmlFile.
	 * Existiert diese Datei nicht wird sie leer erstellt. 
	 * @param xmlFile Die XmlDatei in die geschreiben werden soll.
	 * @param payLoad Der Payload, also die Daten die in die XmlDatei geschreiben werden sollen
	 * @throws IOException Bei einem Fehler beim Schreiben des XML-Files.
	 */
    private static void writeXmlFile(File xmlFile, String payLoad) throws IOException {
		PrintWriter writer = null;
		try {
			if (!xmlFile.exists()) {
				xmlFile.createNewFile();
				Utils.log("(XmlPdfTgzIntern writeXmlFile) "
						+ "Datei erfolgreich erstellt: File = "
						+ xmlFile.getName());
			}
			// XMLStream in Datei schreiben
			writer = new PrintWriter(xmlFile);
			writer.write(payLoad);
			Utils.log("(XmlPdfTgzIntern writeXmlFile) "
					+ "Payload erfolgreich in Datei geschrieben: File = "
					+ xmlFile.getName());
		} catch (IOException e) {
			Utils.log("(XmlPdfTgzIntern writeXmlFile) IOException = "
					+ e.getMessage());
			throw e;
		} finally {
			writer.close();
		}
	}
    
    /**
	 * Erstellt ein Archiv bestehend aus einer Xml Datei und der zugehörigen Pdf-Datei.
	 * 
	 * @param xmlFile      xml-Datei die dem Archiv hinzugefügt werden soll.
	 * @param pdfFile      pdf-Datei die dem Archiv hinzugefügt werden soll.
	 * @param tarFile      Die Archive Datei bestehend aus xml und pdf.
	 * @throws IOException Bei einem Fehler beim Erstellen des tar files.
	 */
    private static void createTar(File xmlFile, File pdfFile, File tarFile) throws IOException {
		// tar-Archiv erstellen
		TarArchiveEntry xmlEntry = new TarArchiveEntry(xmlFile, xmlFile.getName());
		TarArchiveEntry pdfEntry = new TarArchiveEntry(pdfFile, pdfFile.getName());

		FileOutputStream fos = null;
		TarArchiveOutputStream out = null;
		try {
			fos = new FileOutputStream(tarFile);
			out = new TarArchiveOutputStream(fos);

			// Xml Archiv hinzufügen*******************
			out.putArchiveEntry(xmlEntry);
			byte[] b = getByteArrayFromFile(xmlFile);
			out.write(b, 0, (int) xmlFile.length());
			out.closeArchiveEntry();
			Utils.log("(XmlPdfTgzIntern createTar) File: " + xmlFile.getName() 
					+ " hinzugefügt zu Archiv: " + tarFile.getName());
			// ****************************************
			
			// Pdf Archiv hinzufügen*******************
			out.putArchiveEntry(pdfEntry);
			b = getByteArrayFromFile(pdfFile);
			out.write(b, 0, (int) pdfFile.length());
			out.closeArchiveEntry();
			Utils.log("(XmlPdfTgzIntern createTar) File: " + pdfFile.getName() 
					+ " hinzugefügt zu Archiv: " + tarFile.getName());
			// ****************************************

		} catch (IOException e) {
			Utils.log("(XmlPdfTgzIntern createTar) IOException = "
					+ e.getMessage());
			throw e;
		} finally {
			closeStream(out);
			closeStream(fos);
		}
	}
    
    /**
     * Komprimiert eine Datei ins TGZ-Format.
     * @param tarFile Das Archiv das gezipped werden soll.
     * @param tgzFile Die Ausgabe-Datei
     * @throws CompressorException  Bei einem Fehler des Komprimierers
     * @throws IOException          Bei einem I/O-Fehler
     */
    private static void zip(File tarFile, File tgzFile) throws CompressorException, IOException {
		OutputStream os = null;
		CompressorStreamFactory csf = null;
		CompressorOutputStream cos = null;
		FileInputStream fis = null;
		try {
			os = new FileOutputStream(tgzFile);
			csf = new CompressorStreamFactory();
			cos = csf.createCompressorOutputStream("gz", os);
			fis = new FileInputStream(tarFile);
			IOUtils.copy(fis, cos);
			Utils.log("(XmlPdfTgzIntern zip) " +
					"TAR = " + tarFile.getName() + 
					" erfolgreich gezipped nach TGZ = " 
					+ tgzFile.getName());
		} catch (IOException e) {
			Utils.log("(XmlPdfTgzIntern zip) IOException = " + e.getMessage());
			throw e;
		} catch (CompressorException e) {
			Utils.log("(XmlPdfTgzIntern zip) CompressorException = " + e.getMessage());
			throw e;
		} finally {
			closeStream(fis);
			closeStream(cos);
			closeStream(os);
		}
	}
    
    /**
     * Lädt Daten der als Parameter übergebenen Datei ein und gibt diese als byte[] zurück.
     * 
     * @param file Datei von der gelesen werden soll
     * @return byte[] Die Daten der Datei als byte array
     * @throws IOException Bei einem Fehler beim Lesen einer Datei.
     */
    private static byte[] getByteArrayFromFile(File file) throws IOException {
        byte[] b = new byte[(int) file.length()];
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            fis.read(b);
        } catch (IOException e) {
        	Utils.log("(XmlPdfTgzIntern getByteArrayFromFile) IOException = " + e.getMessage());
        	throw e;
        } finally {
        	closeStream(fis);
        }
        return b;
    }
    
    /**
     * Schliesst einen InputStream.
     * @param is Der zu schliessende InputStream
     */
    private static void closeStream(InputStream is) {
    	try {
    		is.close();
    	} catch (IOException e) {
    		Utils.log("(XmlPdfTgzIntern closeStream) IOException = " + e.getMessage());
			e.printStackTrace();
    	}
    }
    
    /**
     * Schliesst einen OutputStream.
     * @param os Der zu schliessende OutputStream
     */
    private static void closeStream(OutputStream os) {
    	try {
    		os.close();
    	} catch (IOException e) {
    		Utils.log("(XmlPdfTgzIntern closeStream) IOException = " + e.getMessage());
			e.printStackTrace();
    	}
    }
}
