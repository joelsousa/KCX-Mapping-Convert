package com.kewill.kcx.component.mapping.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class XmlPdfTGZ {
	/**
	 * Starten der Verarbeitung
	 * @param xmlStream
	 * @param path
	 * @param name
	 * @return
	 */
	public static Object processTar(String xml, String path, String filename) {
		// C.K. Es wird der Dateiname ohne suffix benötigt
		String name = "";
		if (filename.lastIndexOf(".xml") > 0) {
			name = filename.substring(0, filename.lastIndexOf(".xml"));	
        } else {
        	name = filename;
        }
		createZipArchiv(xml, path, name);
		return loadZipArchiv(path, name);
	}
	
	/**
	 * Erstellt ein gezipptes Tar Archiv
	 * @param xmlStream
	 * @param path
	 * @param name
	 */
	private static void createZipArchiv(String xmlStream, String path, String name) {
		try {
			File file = new File(path, name + ".xml");
			if (!file.exists()) {
				file.createNewFile();
			}
			//XMLStream in Datei schreiben
			PrintWriter writer = new PrintWriter(file);
			writer.write(xmlStream);
			writer.close();
			
			//Commando für die Shell um ein Tgz zu erstellen.
			String[] str = new String[] {
					"/bin/bash",
					"-c",
					"tar cfvz " + path + "/" + name + ".tgz -C " + path + " " + name
							+ ".xml -C " + path + " " + name + ".pdf" };

			//Shell Aufruf
			Process p = Runtime.getRuntime().exec(str);
			
			
			int timeout = 10;
			while (!new File(path + name + ".tgz").exists() && timeout != 0) {
				//warten bis Datei erstellt wurde oder 10 sec vergangen sind
				try {
					timeout--;
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Lädt ein gezipptes Tar Archiv in ein bytearray
	 * @param path
	 * @param name
	 * @return
	 */
	private static Object loadZipArchiv(String path, String name) {
		File tgz = new File(path, name + ".tgz");
		byte[] data = null;
		try {
			//Tgz lesen
			FileInputStream gis = new FileInputStream(tgz);
			data = new byte[(int) tgz.length()];
			gis.read(data);
			gis.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Rückgabe bytearray als String
//		return new String(data);
		return data;
	}
}
