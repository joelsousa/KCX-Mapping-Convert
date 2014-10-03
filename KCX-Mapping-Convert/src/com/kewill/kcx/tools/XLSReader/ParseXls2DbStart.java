package com.kewill.kcx.tools.XLSReader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import jxl.read.biff.BiffException;

import com.kewill.kcx.tools.XLSReader.KCX.KCXData;
import com.kewill.kcx.tools.XLSReader.KCX.KCXXLSReader;

/**
 * Generates a text file which contains data from an Excel sheet. This data is
 * formed in sql statements.
 * 
 * 4 arguments are necessary: 
 * 1. Filename input (path+name of the xls file) 
 * 2. Filename output (path+name of the text file) 
 *    this file contains sql statements. 
 * 3. int worksheet 
 * 4. String countrycode "DE" or "NL" or "DE,NL,PL" 
 * Attention: if u choose more than 1 countrycode you have to use ','
 * after each countrycode!
 * 5. update='true' determines if all selected fields will be updated even if the fields 
 * in the Database arent empty.
 * 
 * Funktionsweise:
 * Die Excel-liste (argument input) wird zeilenweise gelesen.
 * In jeder Zeile wird dann Spaltenweise jede Spalte gelesen, 
 * die durch den Country Code bestimmt wird.
 * Spalte 1 (KCXCODE/KCXCODEID) wird immer gelesen. Die restlichen Spalten nur
 * wenn sie als Argument (Countrycode) übergeben wurden.
 * Wurde ein gültiger KCXCODE gefunden werden für alle gewünschten countrycodes
 * die Werte aus der entsprechenden Zeile gelesen und in einer Arraylist gespeichert.
 * Dabei wird geprüft ob der KCXCODE schon in der Datenbank ist, falls ja wird ein update
 * String erstellt, ansonsten ein insert.
 * Am Ende wird die Liste durchlaufen und für jeden Wert geprüft ob dieser leer ist.
 * Ist ein Wert leer wird er nicht geschrieben.
 * 
 * Zum Schluss wird eine Liste mit Sql statements erstellt (output)
 * 
 * Änderungsnachweis
 * 
 * 27.07.2012: folgende countrycodes eingefügt: dk,fr,lu,be,se,no,it,es
 * 				gem. Jira ticket KCX-145 v. CK
 * 
 * 	  
 * @author messer
 * @version 1.0.00 
 * 
 * 
 */
public final class ParseXls2DbStart {
	//input filename
	static File input = null;
	//output filename
	static File output = null;
	//worksheet in inputfile to read from
	static int worksheet = 0;
	//the countrys to get the values from in inputlist
	static String[] countrycode = null;
	//override db entrys
	static boolean update = false;
	//determines if this process gets logged
	static boolean log = true;
	private ParseXls2DbStart() {
	}

	public static void main(String[] args) {
		init(args); 
		readList();
	}
	public static void init(String[] args) {
		if (args.length == 4 || args.length == 5) {
			input = new File(args[0]);
			output = new File(args[1]);
			worksheet = Integer.parseInt(args[2]);
			countrycode = args[3].split(",");
			if (args.length == 5) {
				update = Boolean.parseBoolean(args[4]);
			}
		} else {
			throw new RuntimeException("Not supportet amount of Arguments!"
					+ " Remember 4 Arguments are necessary "
					+ "(String input(Filename),String output(Filename),"
					+ " int worksheet, String countrycode)\n" +
							"The 5 Argument is optional: update='true'" +
							"All selected fields will be updated.");
		}
	}
	public static void readList(){
		XLSReader read = null;
		try {
			log(false, "Input File=\"" + input + "\"", "NONE", "NONE");
			read = new KCXXLSReader(input, update);
			int[] cols = new int[countrycode.length + 1];
			cols[0] = 0; // Column which contains the KCXCode
			int x = 1;
			//For each CountryCode
			for (String cellname : countrycode) {
				//get Cell which contains "value countrycode"
				jxl.LabelCell cell = read.getWorkbook().getSheet(worksheet).findLabelCell(
						"value " + cellname);
				if (cell != null) {
				//Columne hinzufügen	
				cols[x] = cell.getColumn();
				} else {
				//Columne nicht gefunden	
				System.err.println("Kein Feld: 'value " + cellname + "' in xls gefunden!");
					System.exit(1);	
				}
				x++;
			}

			String soutput = "";

			// read data from xls and stores it into an arraylist
			ArrayList<XLSData> data = read.getDataObjects(cols, worksheet);
			// Check differences between DB and LIST (remove all matching elemants)
			checkDiff(data);
			//for each data in list
			for (XLSData d : data) {
				KCXData kd = (KCXData) d;
				//new KCXCode = insert
				if (!kd.isUpdate()) {
					soutput += "insert into kcx_codes (kcx_code,xml_tag,kcxcode_id,de,ch,pl,nl,si,gb,fi,dk,fr,lu,be,se,no,it,es)"
							+ "values(" + ((KCXData) d).getValue() + ");\n";
				} else {
					//Update
					String fields = "";
					int counter = 0;
					//get only countryCodes for update which contain a value
					String[] cc = getValuedCC(countrycode, kd);
					for (String f : cc) {
						fields += f + "='" + getCountryValue(f, kd) + "'";
						counter++;
						if (counter > 0 && counter != cc.length) {
							fields += ",";
						}
					}

					soutput += "update kcx_codes set " + fields
							+ " where kcx_code='" + kd.getKcxCode() + "';\n";
				}

			}

			// write soutput into file: output
			PrintWriter pw = new PrintWriter(output);
			pw.write(soutput);
			pw.close();
		} catch (BiffException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	/**
	 * 
	 * @param String[] countrys
	 * @param KCXData d
	 * @return String[]
	 */
private static String[] getValuedCC(String[] countrys, KCXData d) {
	String temp = "";
		//For each Country
		for (String country : countrys) {
			//get Value from current country
			String val = getCountryValue(country, d);
			//if val is not empty add field
			if (!val.trim().equals("")) {
				temp += country + ";";
			} else {
				log(false, "Feld hat keinen Wert und wird darum nicht berücksichtigt.",
						country, d.getKcxCode());
			}
		}
	
	return temp.split(";");
}
	/**
	 * returns the value from KCXData which belongs to the countrycode f.
	 * 
	 * @param String
	 * @param KCXData
	 * @return String
	 */
	private static String getCountryValue(String f, KCXData d) {
		if (f.equals("DE")) {
			return d.getDe();
		} else if (f.equals("CH")) {
			return d.getCh();
		} else if (f.equals("NL")) {
			return d.getNl();
		} else if (f.equals("PL")) {
			return d.getPl();
		} else if (f.equals("SI")) {
			return d.getSi();
		} else if (f.equals("GB")) {
			return d.getGb();
		} else if (f.equals("FI")) {
			return d.getFi();
		} else if (f.equals("DK")) {
			return d.getDk();
		} else if (f.equals("FR")) {
			return d.getFr();
		} else if (f.equals("LU")) {
			return d.getLu();
		} else if (f.equals("BE")) {
			return d.getBe();
		} else if (f.equals("SE")) {
			return d.getSe();
		} else if (f.equals("NO")) {
			return d.getNo();
		} else if (f.equals("IT")) {
			return d.getIt();
		} else if (f.equals("ES")) {
			return d.getEs();
			
		} else {
			return null;
		}

	}
	public static void log(boolean error, String msg, String field, String code) {
		if (log) {
			String state = "";
			if (error) {
				state = " -FAIL- ";
				String out = new Date() + state + "[FIELD=" + field + "] [CODE="
				+ code + "] : " + msg + "\n";
				System.out.print(out);	
				FileWriter fw;
				try {
					fw = new FileWriter(new File(
					"./KCXXlsReaderDiff.log"), true);
					fw.write(out);
					fw.flush();
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
			
				state = " -FINE- ";
				
				String out = new Date() + state + "[FIELD=" + field + "] [CODE="
				+ code + "] : " + msg + "\n";
				System.out.print(out);	
				FileWriter fw;
				try {
					fw = new FileWriter(new File(
					"./KCXXlsReaderCodes.log"), true);
					fw.write(out);
					fw.flush();
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			
			}
	}
	//**************************************************************
	/**
	 * Prüft auf differenzen zwischen liste und Datenbank.
	 * Und entfernt die Elemente die komplett mit einem Datenbankeintrag übereinstimmen.
	 */
	public static void checkDiff(ArrayList<XLSData> data) {
	ResultSet rs = DbKCXReader.getKCXValues();
		try {
			while (rs.next()) {
				String kcxcode = rs.getString("kcx_code").trim();
				String kcxcodeid = rs.getString("kcxcode_id").trim();
				//System.out.println("KCX CODE: " + kcxcode);
				//System.out.println("KCX CODE  ID: " + kcxcodeid);
				String de = rs.getString("DE").trim();
				String nl = rs.getString("NL").trim();
				String ch = rs.getString("CH").trim();
				String pl = rs.getString("PL").trim();
				String si = rs.getString("SI").trim();
				String gb = rs.getString("GB").trim();
				String fi = rs.getString("FI").trim();
				String dk = rs.getString("DK").trim();
				String fr = rs.getString("FR").trim();
				String lu = rs.getString("LU").trim();
				String be = rs.getString("BE").trim();
				String se = rs.getString("SE").trim();
				String no = rs.getString("NO").trim();
				String it = rs.getString("IT").trim();
				String es = rs.getString("ES").trim();
				boolean contains = false;
				for (XLSData xd : data) {
					KCXData d = (KCXData) xd;
					if (d.getKcxCode().equals(kcxcode)) {
						contains = true;
						if (containsCountryCode("NL") && !d.getNl().trim().equals(nl)) {
							contains = false;
							log(true, "Db and List are not Equal! " +
									"(id:" + kcxcodeid + ", DB NL:" + nl + 
									" != LIST NL:" + d.getNl() + ")", "NL", kcxcode);
							}
						if (containsCountryCode("PL") && !d.getPl().trim().equals(pl)) {
							contains = false;
							log(true, "Db and List are not Equal! " +
									"(id:" + kcxcodeid + ", DB PL:" + pl + 
									" != LIST PL:" + d.getPl() + ")", "PL", kcxcode);
						}
						if (containsCountryCode("CH") && !d.getCh().trim().equals(ch)) {
							contains = false;
							log(true, "Db and List are not Equal! " +
									"(id:" + kcxcodeid + ", DB CH:" + ch + 
									" != LIST CH:" + d.getCh() + ")", "CH", kcxcode);
						}
						if (containsCountryCode("DE") && !d.getDe().trim().equals(de)) {
							contains = false;
							log(true, "Db and List are not Equal! " +
									"(id:" + kcxcodeid + ", DB DE:" + de + 
									" != LIST DE:" + d.getDe()+")", "DE", kcxcode);
						}
						if (containsCountryCode("SI") && !d.getSi().trim().equals(si)) {
							contains = false;
							log(true, "Db and List are not Equal! " +
									"(id:" + kcxcodeid + ", DB SI:" + si + 
									" != LIST SI:" + d.getSi() + ")", "SI", kcxcode);
						}
						if (containsCountryCode("GB") && !d.getGb().trim().equals(gb)) {
							contains = false;
							log(true, "Db and List are not Equal! " +
									"(id:" + kcxcodeid + ", DB GB:" + gb + 
									" != LIST GB:" + d.getGb() + ")", "GB", kcxcode);
						}
						if (containsCountryCode("FI") && !d.getFi().trim().equals(fi)) {
							contains = false;
							log(true, "Db and List are not Equal! " +
									"(id:" + kcxcodeid + ", DB FI:" + fi + 
									" != LIST FI:" + d.getFi() + ")", "FI", kcxcode);
						}
						if (containsCountryCode("DK") && !d.getDk().trim().equals(dk)) {
							contains = false;
							log(true, "Db and List are not Equal! " +
									"(id:" + kcxcodeid + ", DB DK:" + dk + 
									" != LIST DK:" + d.getDk() + ")", "DK", kcxcode);
						}
						if (containsCountryCode("FR") && !d.getFr().trim().equals(fr)) {
							contains = false;
							log(true, "Db and List are not Equal! " +
									"(id:" + kcxcodeid + ", DB FR:" + fr + 
									" != LIST FR:" + d.getFr() + ")", "FR", kcxcode);
						}
						if (containsCountryCode("LU") && !d.getLu().trim().equals(lu)) {
							contains = false;
							log(true, "Db and List are not Equal! " +
									"(id:" + kcxcodeid + ", DB LU:" + lu + 
									" != LIST LU:" + d.getLu() + ")", "LU", kcxcode);
						}
						if (containsCountryCode("BE") && !d.getBe().trim().equals(be)) {
							contains = false;
							log(true, "Db and List are not Equal! " +
									"(id:" + kcxcodeid + ", DB BE:" + be + 
									" != LIST BE:" + d.getBe() + ")", "BE", kcxcode);
						}
						if (containsCountryCode("SE") && !d.getSe().trim().equals(se)) {
							contains = false;
							log(true, "Db and List are not Equal! " +
									"(id:" + kcxcodeid + ", DB SE:" + se + 
									" != LIST SE:" + d.getSe() + ")", "SE", kcxcode);
						}
						if (containsCountryCode("NO") && !d.getNo().trim().equals(no)) {
							contains = false;
							log(true, "Db and List are not Equal! " +
									"(id:" + kcxcodeid + ", DB NO:" + no + 
									" != LIST NO:" + d.getNo() + ")", "NO", kcxcode);
						}
						if (containsCountryCode("IT") && !d.getIt().trim().equals(it)) {
							contains = false;
							log(true, "Db and List are not Equal! " +
									"(id:" + kcxcodeid + ", DB IT:" + it + 
									" != LIST IT:" + d.getIt() + ")", "IT", kcxcode);
						}
						if (containsCountryCode("ES") && !d.getEs().trim().equals(es)) {
							contains = false;
							log(true, "Db and List are not Equal! " +
									"(id:" + kcxcodeid + ", DB ES:" + es + 
									" != LIST ES:" + d.getEs() + ")", "ES", kcxcode);
						}
						if (contains) {
							data.remove(xd);
							break;
						}
							
					}
					
								
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbKCXReader.close();
		}
		
	}
	/**
	 * prüft ob der übergebene countrycode im countrycode array enthalten ist.
	 * 
	 * @param String cc
	 * @return boolean contains
	 */
	public static boolean containsCountryCode(String cc) {
		for (String c : countrycode) {
			if (c.equals(cc)) {
				return true;
			}
		}
		return false;
	}
}
