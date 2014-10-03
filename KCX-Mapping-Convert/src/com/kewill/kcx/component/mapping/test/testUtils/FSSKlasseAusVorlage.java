package com.kewill.kcx.component.mapping.test.testUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Modul                :       FSSKlasseAusVorlage. 
 * Erstellt             :       
 * Beschreibung :       Erstellt aus vorlage.dat eine FSS KCX-Klasse
 * 
 * Parameter: Pfad zur vorlage.dat
 * 
 * Aufbau vorlage.dat:
 * ------------------------------------------
 * 1. Zeile  Name der Klasse
 * 
 * folgende Zeilen: package
 * 					import
 * 					Kommentar
 * 
 * In den Zeilen, die nach der Zeile mit dem Wort FELDER folgen, 
 * sind die Zeilen der zu bearbeiteten Felder beschrieben.
 * 
 * In diesen Zeilen kommen nach einem Komma die 
 * Beschreibungen der Felder aus der FSS-Doku.
 * z.B.
 * beznr, Bezugsnummer
 * ------------------------------------------
 *  
 * 
 * In der erstellten Klasse müssen:
 * IsStringEmpty angepasst werden und Getters/Setters includiert werden
 * 
 * 
 *
 * @author                      Alfred Krzoska
 * 
 */


public class FSSKlasseAusVorlage {
	private static String leer = "    ";
	private static final int MAX_LINES = 100;

	
	
	public static void main(String[] args) {
		String[] felder = lesenVorlage(args[0] + "vorlage.dat");
		writeClass(args[0], felder);
	}

	private static void writeClass(String pfad, String [] felder) {
		try {
			System.out.print("Ausgabe für " + felder[0] + " gestartet");
			int zae = 1;
			BufferedWriter out = new BufferedWriter(new FileWriter(pfad + felder[0] + ".java"));
		
			while (!felder[zae].equalsIgnoreCase("FELDER")) {
				out.write(felder[zae] + "\n");
				zae++;
			}
			zae++;

			out.write("public class " + felder[0] + " extends Teilsatz {\n\n");

			zae = writeVariables(out, felder, zae);
			zae++;

			writeConstructor(out, felder);
			
			writeSetFields(out, felder);
			
			writeGetSetters(out, felder);
			
			writeTeilsatzBilden(out, felder);
			
			writeIsEmpty(out, felder);
			
			out.write("}\n");

			out.close();
			System.out.print("\nAusgabe für " + felder[0] + " beendet");
		} catch (IOException e) {
			System.out.println("Fehler:  " + e);
			e.printStackTrace();
		}
		
	}

	private static void writeGetSetters(BufferedWriter out, String[] felder) throws IOException {
		int position = reset(felder);
		String feldFirstCapitalLetter;
		
		for (int zae = 2; felder[position] != null; zae++, ++position) {
			if ((feldFirstCapitalLetter = getFeldFirstCapitalLetter(felder, position)) == null) {
				break;				
			}

			out.write("\n\n" + leer + "public String get" + feldFirstCapitalLetter + "() {\n");
			out.write(leer + "\t return " + feldFirstCapitalLetter.toLowerCase()  +";\n");
			out.write(leer + "}\n");
			
			out.write("\n\n" + leer + "public void set" + feldFirstCapitalLetter + 
					"(String " +  feldFirstCapitalLetter  + ") {\n");
			out.write(leer + "\tthis." + feldFirstCapitalLetter.toLowerCase() +" = Utils.checkNull(" +
					feldFirstCapitalLetter.toLowerCase() + ");\n");
			out.write(leer + "}\n");
		}
	}
	
	private static String getFeldFirstCapitalLetter(String[] felder, int position) {
		String[] arrayFeld = felder[position].split(",");
		
		if (arrayFeld != null && !arrayFeld[0].isEmpty()) {
			return arrayFeld[0].substring(0, 1).toUpperCase() +	arrayFeld[0].substring(1);
		}
			
		return null;
	}

	private static void writeConstructor(BufferedWriter out, String[] felder) throws IOException {
		out.write("\n" + leer + "public " + felder[0] + "() {\n");
		out.write("\t" + leer + "tsTyp = \"" + felder[0].substring(2) + "\";\n" + leer + "}");
	}


	private static void writeSetFields(BufferedWriter out, String[] felder) throws IOException {
		int position = reset(felder);
		
		out.write("\n\n" + leer + "public void setFields(String[] fields) {\n");
		out.write(leer + "\tint size = fields.length;\n");
		out.write(leer + "\tString ausgabe = \"FSS: \" + fields[0] + \" size = \" + size;\n\n");
		out.write(leer + "\tif (size < 1) { return; }\n");	
		out.write(leer + "\ttsTyp = fields[0];\n");
		
		for (int zae = 2; felder[position] != null; zae++, ++position) {
			String[] str = felder[position].split(",");
			if (str[0].isEmpty()) {
				break;
			}
			out.write("\n\t" + leer + "if (size < " + zae + ") { return; }\n");
			int fieldsPos = zae - 1;
			out.write("\t" + leer  + str[0] + " = fields[" + fieldsPos + "];\n");
		}
		out.write(leer + "}\n\n");
	}

	private static void writeTeilsatzBilden(BufferedWriter out, String[] felder) throws IOException {
		int position = reset(felder);

		out.write("\n\n" + leer + "public String teilsatzBilden() {\n");
		out.write(leer + "\tStringBuffer buff = new StringBuffer();\n\n");
		
		out.write(leer + "\tbuff.append(tsTyp);\n");
		out.write(leer + "\tbuff.append(trenner);");

		for (int zae = 2;; zae++, ++position) {
			String[] str = felder[position].split(",");
			if (str[0].isEmpty()) {
				break;
			}
			out.write("\n" + leer + "\tbuff.append(" + str[0] + ");\n");
			out.write(leer + "\tbuff.append(trenner);");			
		}
		out.write("\n\n" + leer + "\treturn new String(buff);\n");
		out.write(leer + "\n" + leer + "}\n\n");
	}

	private static int writeVariables(BufferedWriter out, String[] felder, int zae) throws IOException {
		while (felder[zae] != null && !felder[zae].isEmpty()) {
			String[] str = felder[zae].split(",");
			out.write(leer + "private String " + str[0] + "\t\t = \"\";\t // " + str[1] + "\n");
			zae++;
		}
		return zae;
	}

	private static void writeIsEmpty(BufferedWriter out, String[] felder) throws IOException {
		int position = reset(felder);

		out.write("\n\n" + leer + "public boolean isEmpty() {\n");
		out.write("\treturn"); 

		for (int zae = 2;; zae++, ++position) {
			String[] str = felder[position].split(",");
			if (str[0].isEmpty()) {
				break;
			}
			out.write(leer + "\tUtils.isStringEmpty(" + str[0] + ") &&\n");
		}
		out.write(leer + "\n" + leer + "}\n\n");
	}


	private static String[] lesenVorlage(String dateiName) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(dateiName));
			
			String zeile;
			String[] felder = new String[MAX_LINES];
			int zae = 0;
			for (zae = 0; zae < 100; zae++) {
				felder[zae] = null;
			}
			zae = 0;
			while ((zeile = in.readLine()) != null) {
				felder[zae] = zeile;
				zae++;
			}
			in.close();
			return felder;
		} catch (FileNotFoundException e) {
			System.out.println("Fehler: Datei nicht vorhanden! :" + e);
		} catch (IOException e) {
			System.out.println("Fehler: ! :" + e);
		}
		return null;
	}
	
	private static int reset(String[] felder) {
		int zae = 0;
		
		while (!felder[zae].equalsIgnoreCase("FELDER")) {
			zae++;
		}
		return ++zae;
	}
}
