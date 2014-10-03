package com.kewill.kcx.tools.XLSReader.KCX;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jxl.Cell;
import jxl.Sheet;
import jxl.read.biff.BiffException;


import com.kewill.kcx.tools.XLSReader.DbKCXReader;
import com.kewill.kcx.tools.XLSReader.ParseXls2DbStart;
import com.kewill.kcx.tools.XLSReader.XLSData;
import com.kewill.kcx.tools.XLSReader.XLSReader;

/**
 * Class to read the KCXCodes from an Excel sheet.
 *
 * 27.07.2012: folgende countrycodes eingefügt: dk,fr,lu,be,se,no,it,es
 * 				gem. Jira ticket KCX-145 v. CK
 * 
 * @author messer
 * @version 0.0.10
 */
public class KCXXLSReader extends XLSReader {
	int KCXCODE = 0;
	int DE = 0;
	int PL = 0;
	int CH = 0;
	int NL = 0;
	int SI = 0;
	int GB = 0;
	int DK = 0;
	int FR = 0;
	int LU = 0;
	int BE = 0;
	int SE = 0;
	int NO = 0;
	int IT = 0;
	int ES = 0;
	/**
	 * Pattern for KCX Code IDs (like = KCX0123).
	 */
	protected Pattern patKcxCodeId = Pattern.compile("KCX[0-9]{4}");
	/**
	 * Pattern for KCX Codes (like = AB123).
	 */
	protected Pattern patKcxCode = Pattern.compile("[a-zA-Z]{2,2}[0-9]{3,3}");
	/**
	 * Matcher Variable for the Pattern.
	 */
	protected Matcher match = null;
	private Sheet sheet;
	private boolean updateAll=false;
	public KCXXLSReader(File f, boolean updateAll) throws BiffException, IOException {
		super(f);
		this.updateAll=updateAll;
	}

	/**
	 * If the cell matches a String like KCX0123 then the return value is true.
	 * This means the cell contains a KCXCODEID.
	 * Else it returns false, no KCXCODEID 
	 * @param c Cell
	 * @return boolean
	 */
	protected boolean isKCXCodeId(Cell c) {
		match = patKcxCodeId.matcher(c.getContents().trim());
		return match.matches();
	}
	/**
	 * If the cell matches a String like AB0123 then the return value is true.
	 * This means the cell contains a KCXCODE.
	 * Else it returns false, no KCXCODE
	 * 
	 * @param c Cell
	 * @return boolean
	 */
	protected boolean isKCXCode(Cell c) {
		match = patKcxCode.matcher(c.getContents().trim());
		return match.matches();
	}

	public ArrayList<XLSData> getDataObjects(int[] selCols, int workSheet) {
		sheet = workbook.getSheet(workSheet);
		//Initialize variables
		ArrayList<XLSData> dataList = new ArrayList<XLSData>();
		String kcxCode = "";
		String tag = "";
		String kcxCodeId = "";
		String de = "";
		String ch = "";
		String nl = "";
		String pl = "";
		String si = "";
		String gb = "";
		String dk = "";
		String fr = "";
		String lu = "";
		String be = "";
		String se = "";
		String no = "";
		String it = "";
		String es = "";
		//getColumn Value by CountryCode
		DE = getColumn("DE");
		PL = getColumn("PL");
		CH = getColumn("CH");
		NL = getColumn("NL");
		SI = getColumn("SI");
		GB = getColumn("GB");
		DK = getColumn("DK");
		FR = getColumn("FR");
		LU = getColumn("LU");
		BE = getColumn("BE");
		SE = getColumn("SE");
		NO = getColumn("NO");
		IT = getColumn("IT");
		ES = getColumn("ES");
		
		boolean update = true;
		
		for (int row = 0; row < sheet.getRows(); row++) {
			//Reset variables
			de="";
			ch="";
			nl="";
			pl="";
			si="";
			gb="";
			dk="";
			fr="";
			lu="";
			be="";
			se="";
			no="";
			it="";
			es="";
			kcxCode="";
			//get Cell which contains KCXCODE/KCXCODEID
			Cell kcxCell = sheet.getCell(KCXCODE, row);
			//For each Column:
			for (Integer col : selCols) {
				//get current cell (col;row)
				Cell cell = sheet.getCell(col, row);
				//if current column is kcxcode column
				if (KCXCODE == col) {
					//if this cell contains a kcxcodeid
					if (isKCXCodeId(cell)) {
						//set KCXCODEID and TAG
						kcxCodeId = cell.getContents().trim();
						tag = getTag(sheet.getCell(1, row).getContents().trim());
					//if the cell contains a KCXCODE
					} else if (isKCXCode(cell)) {
						//set KCXCODE
						kcxCode = cell.getContents().trim();
						//If the kcxcode is not already in the database
						if (!DbKCXReader.isKCXCodeInDb(kcxCode) && !kcxCode.equals("")) {
							//get values from all fields
							de = getCellContents(DE, row);
							ch = getCellContents(CH, row);
							nl = getCellContents(NL, row);
							pl = getCellContents(PL, row);
							si = getCellContents(SI, row);
							gb = getCellContents(GB, row);
							dk = getCellContents(DK, row);
							fr = getCellContents(FR, row);
							lu = getCellContents(LU, row);
							be = getCellContents(BE, row);
							se = getCellContents(SE, row);
							no = getCellContents(NO, row);
							it = getCellContents(IT, row);
							es = getCellContents(ES, row);
							//new Code means no update! INSERT
							update = false;
						} else {
							//if kcxcode is in Database just update
							update = true;
						}
					}
				//if current column is DE	
				} else if (DE == col){ 
					//if kcxcode cell contains a kcxcode and update is true or the value in db is empty
					if(isKCXCode(kcxCell) && (updateAll || DbKCXReader.isKCXValueEmpty(kcxCode, "DE"))) {
						//get Value
						de = cell.getContents().trim();	
					} else if(!isKCXCode(kcxCell) && !isKCXCodeId(kcxCell)&&!cell.getContents().trim().equals("")){
						ParseXls2DbStart.log(false, "[VALUE="+cell.getContents().trim()+"] hat keinen gültigen KCX-CODE! ROW:COL{"+row+":"+col+"}", "DE", kcxCell.getContents());
					} else if(!updateAll) {
						ParseXls2DbStart.log(false, "Ein Wert für dieses Feld mit diesem KCX-CODE besteht bereits. (Setzen Sie das Flag UPDATEALL=TRUE wenn sie diese Werte überschreiben wollen)! ROW:COL{"+row+":"+col+"}", "DE", kcxCell.getContents());
					}
				} else if (CH == col){
					if(isKCXCode(kcxCell) && (updateAll || DbKCXReader.isKCXValueEmpty(kcxCode, "CH"))) {
						ch = cell.getContents().trim();	
					} else if(!isKCXCode(kcxCell)&& !isKCXCodeId(kcxCell)&&!cell.getContents().trim().equals("")){
						ParseXls2DbStart.log(false, "[VALUE="+cell.getContents().trim()+"] hat keinen gültigen KCX-CODE! ROW:COL{"+row+":"+col+"}", "CH", kcxCell.getContents());
					} else if(!updateAll) {
						ParseXls2DbStart.log(false, "Ein Wert für dieses Feld mit diesem KCX-CODE besteht bereits. (Setzen Sie das Flag UPDATEALL=TRUE wenn sie diese Werte überschreiben wollen)! ROW:COL{"+row+":"+col+"}", "CH", kcxCell.getContents());
					}
				} else if (NL == col){
					if( isKCXCode(kcxCell) && (updateAll || DbKCXReader.isKCXValueEmpty(kcxCode, "NL"))) {
						nl = cell.getContents().trim();	
					} else if(!isKCXCode(kcxCell)&& !isKCXCodeId(kcxCell)&&!cell.getContents().trim().equals("")){
						ParseXls2DbStart.log(false, "[VALUE="+cell.getContents().trim()+"] hat keinen gültigen KCX-CODE! ROW:COL{"+row+":"+col+"}", "NL", kcxCell.getContents());
					} else if(!updateAll) {
						ParseXls2DbStart.log(false, "Ein Wert für dieses Feld mit diesem KCX-CODE besteht bereits. (Setzen Sie das Flag UPDATEALL=TRUE wenn sie diese Werte überschreiben wollen)! ROW:COL{"+row+":"+col+"}", "NL", kcxCell.getContents());
					}
				} else if (PL == col) {
					if(isKCXCode(kcxCell) && (updateAll || DbKCXReader.isKCXValueEmpty(kcxCode, "PL"))) {
						pl = cell.getContents().trim();	
					} else if(!isKCXCode(kcxCell)&& !isKCXCodeId(kcxCell)&&!cell.getContents().trim().equals("")){
						ParseXls2DbStart.log(false, "[VALUE="+cell.getContents().trim()+"] hat keinen gültigen KCX-CODE! ROW:COL{"+row+":"+col+"}", "PL", kcxCell.getContents());
					} else if(!updateAll) {
						ParseXls2DbStart.log(false, "Ein Wert für dieses Feld mit diesem KCX-CODE besteht bereits. (Setzen Sie das Flag UPDATEALL=TRUE wenn sie diese Werte überschreiben wollen)! ROW:COL{"+row+":"+col+"}", "PL", kcxCell.getContents());
					}
					
				} else if (SI == col ) {
					if(isKCXCode(kcxCell) && (updateAll || DbKCXReader.isKCXValueEmpty(kcxCode, "SI"))) {
						si = cell.getContents().trim();	
					} else if(!isKCXCode(kcxCell)&& !isKCXCodeId(kcxCell) && !cell.getContents().trim().equals("")){
						ParseXls2DbStart.log(false, "[VALUE="+cell.getContents().trim()+"] hat keinen gültigen KCX-CODE! ROW:COL{"+row+":"+col+"}", "SI", kcxCell.getContents());
					} else if(!updateAll) {
						ParseXls2DbStart.log(false, "Ein Wert für dieses Feld mit diesem KCX-CODE besteht bereits. (Setzen Sie das Flag UPDATEALL=TRUE wenn sie diese Werte überschreiben wollen)! ROW:COL{"+row+":"+col+"}", "SI", kcxCell.getContents());
					}
				} else if (GB == col ) {
					if(isKCXCode(kcxCell) && (updateAll || DbKCXReader.isKCXValueEmpty(kcxCode, "GB"))) {
						gb = cell.getContents().trim();	
					} else if(!isKCXCode(kcxCell)&& !isKCXCodeId(kcxCell) && !cell.getContents().trim().equals("")){
						ParseXls2DbStart.log(false, "[VALUE="+cell.getContents().trim()+"] hat keinen gültigen KCX-CODE! ROW:COL{"+row+":"+col+"}", "GB", kcxCell.getContents());
					} else if(!updateAll) {
						ParseXls2DbStart.log(false, "Ein Wert für dieses Feld mit diesem KCX-CODE besteht bereits. (Setzen Sie das Flag UPDATEALL=TRUE wenn sie diese Werte überschreiben wollen)! ROW:COL{"+row+":"+col+"}", "GB", kcxCell.getContents());
					}
				} else if (DK == col ) {
					if(isKCXCode(kcxCell) && (updateAll || DbKCXReader.isKCXValueEmpty(kcxCode, "DK"))) {
						dk = cell.getContents().trim();	
					} else if(!isKCXCode(kcxCell)&& !isKCXCodeId(kcxCell) && !cell.getContents().trim().equals("")){
						ParseXls2DbStart.log(false, "[VALUE="+cell.getContents().trim()+"] hat keinen gültigen KCX-CODE! ROW:COL{"+row+":"+col+"}", "DK", kcxCell.getContents());
					} else if(!updateAll) {
						ParseXls2DbStart.log(false, "Ein Wert für dieses Feld mit diesem KCX-CODE besteht bereits. (Setzen Sie das Flag UPDATEALL=TRUE wenn sie diese Werte überschreiben wollen)! ROW:COL{"+row+":"+col+"}", "DK", kcxCell.getContents());
					}
				} else if (FR == col ) {
					if(isKCXCode(kcxCell) && (updateAll || DbKCXReader.isKCXValueEmpty(kcxCode, "FR"))) {
						fr = cell.getContents().trim();	
					} else if(!isKCXCode(kcxCell)&& !isKCXCodeId(kcxCell) && !cell.getContents().trim().equals("")){
						ParseXls2DbStart.log(false, "[VALUE="+cell.getContents().trim()+"] hat keinen gültigen KCX-CODE! ROW:COL{"+row+":"+col+"}", "FR", kcxCell.getContents());
					} else if(!updateAll) {
						ParseXls2DbStart.log(false, "Ein Wert für dieses Feld mit diesem KCX-CODE besteht bereits. (Setzen Sie das Flag UPDATEALL=TRUE wenn sie diese Werte überschreiben wollen)! ROW:COL{"+row+":"+col+"}", "FR", kcxCell.getContents());
					}
				} else if (LU == col ) {
					if(isKCXCode(kcxCell) && (updateAll || DbKCXReader.isKCXValueEmpty(kcxCode, "LU"))) {
						lu = cell.getContents().trim();	
					} else if(!isKCXCode(kcxCell)&& !isKCXCodeId(kcxCell) && !cell.getContents().trim().equals("")){
						ParseXls2DbStart.log(false, "[VALUE="+cell.getContents().trim()+"] hat keinen gültigen KCX-CODE! ROW:COL{"+row+":"+col+"}", "LU", kcxCell.getContents());
					} else if(!updateAll) {
						ParseXls2DbStart.log(false, "Ein Wert für dieses Feld mit diesem KCX-CODE besteht bereits. (Setzen Sie das Flag UPDATEALL=TRUE wenn sie diese Werte überschreiben wollen)! ROW:COL{"+row+":"+col+"}", "LU", kcxCell.getContents());
					}		
				} else if (BE == col ) {
					if(isKCXCode(kcxCell) && (updateAll || DbKCXReader.isKCXValueEmpty(kcxCode, "BE"))) {
						be = cell.getContents().trim();	
					} else if(!isKCXCode(kcxCell)&& !isKCXCodeId(kcxCell) && !cell.getContents().trim().equals("")){
						ParseXls2DbStart.log(false, "[VALUE="+cell.getContents().trim()+"] hat keinen gültigen KCX-CODE! ROW:COL{"+row+":"+col+"}", "BE", kcxCell.getContents());
					} else if(!updateAll) {
						ParseXls2DbStart.log(false, "Ein Wert für dieses Feld mit diesem KCX-CODE besteht bereits. (Setzen Sie das Flag UPDATEALL=TRUE wenn sie diese Werte überschreiben wollen)! ROW:COL{"+row+":"+col+"}", "BE", kcxCell.getContents());
					}
				} else if (SE == col ) {
					if(isKCXCode(kcxCell) && (updateAll || DbKCXReader.isKCXValueEmpty(kcxCode, "SE"))) {
						se = cell.getContents().trim();	
					} else if(!isKCXCode(kcxCell)&& !isKCXCodeId(kcxCell) && !cell.getContents().trim().equals("")){
						ParseXls2DbStart.log(false, "[VALUE="+cell.getContents().trim()+"] hat keinen gültigen KCX-CODE! ROW:COL{"+row+":"+col+"}", "SE", kcxCell.getContents());
					} else if(!updateAll) {
						ParseXls2DbStart.log(false, "Ein Wert für dieses Feld mit diesem KCX-CODE besteht bereits. (Setzen Sie das Flag UPDATEALL=TRUE wenn sie diese Werte überschreiben wollen)! ROW:COL{"+row+":"+col+"}", "SE", kcxCell.getContents());
					}
				} else if (NO == col ) {
					if(isKCXCode(kcxCell) && (updateAll || DbKCXReader.isKCXValueEmpty(kcxCode, "NO"))) {
						no = cell.getContents().trim();	
					} else if(!isKCXCode(kcxCell)&& !isKCXCodeId(kcxCell) && !cell.getContents().trim().equals("")){
						ParseXls2DbStart.log(false, "[VALUE="+cell.getContents().trim()+"] hat keinen gültigen KCX-CODE! ROW:COL{"+row+":"+col+"}", "NO", kcxCell.getContents());
					} else if(!updateAll) {
						ParseXls2DbStart.log(false, "Ein Wert für dieses Feld mit diesem KCX-CODE besteht bereits. (Setzen Sie das Flag UPDATEALL=TRUE wenn sie diese Werte überschreiben wollen)! ROW:COL{"+row+":"+col+"}", "NO", kcxCell.getContents());
					}
				} else if (IT == col ) {
					if(isKCXCode(kcxCell) && (updateAll || DbKCXReader.isKCXValueEmpty(kcxCode, "IT"))) {
						it = cell.getContents().trim();	
					} else if(!isKCXCode(kcxCell)&& !isKCXCodeId(kcxCell) && !cell.getContents().trim().equals("")){
						ParseXls2DbStart.log(false, "[VALUE="+cell.getContents().trim()+"] hat keinen gültigen KCX-CODE! ROW:COL{"+row+":"+col+"}", "IT", kcxCell.getContents());
					} else if(!updateAll) {
						ParseXls2DbStart.log(false, "Ein Wert für dieses Feld mit diesem KCX-CODE besteht bereits. (Setzen Sie das Flag UPDATEALL=TRUE wenn sie diese Werte überschreiben wollen)! ROW:COL{"+row+":"+col+"}", "IT", kcxCell.getContents());
					}
				} else if (ES == col ) {
					if(isKCXCode(kcxCell) && (updateAll || DbKCXReader.isKCXValueEmpty(kcxCode, "ES"))) {
						es = cell.getContents().trim();	
					} else if(!isKCXCode(kcxCell)&& !isKCXCodeId(kcxCell) && !cell.getContents().trim().equals("")){
						ParseXls2DbStart.log(false, "[VALUE="+cell.getContents().trim()+"] hat keinen gültigen KCX-CODE! ROW:COL{"+row+":"+col+"}", "ES", kcxCell.getContents());
					} else if(!updateAll) {
						ParseXls2DbStart.log(false, "Ein Wert für dieses Feld mit diesem KCX-CODE besteht bereits. (Setzen Sie das Flag UPDATEALL=TRUE wenn sie diese Werte überschreiben wollen)! ROW:COL{"+row+":"+col+"}", "ES", kcxCell.getContents());
					}		
				}
				
			}
			
			// Cells which contains no data won't be stored.
			if (!kcxCode.equals("") && !kcxCodeId.equals("") && !tag.equals("")
					&& hasValue(selCols, de, ch, pl, nl, si, gb, dk, fr, lu, be, se, no, it, es)) {
				//Check if list already contains that kcxcode (must be unique)
				if (checkData(dataList, kcxCode)) {
					//if tag is longer then 50 chars, cut the rest off
					if (tag.length() > 50) {
						tag = tag.substring(0, 49);
					}
					//create new DataContainer for current row
					KCXData data = new KCXData(kcxCode, tag, kcxCodeId);
					//set all fields
					data.setDe(de);
					data.setCh(ch);
					data.setNl(nl);
					data.setPl(pl);
					data.setSi(si);
					data.setGb(gb);
					data.setDk(dk);
					data.setFr(fr);
					data.setLu(lu);
					data.setBe(be);
					data.setSe(se);
					data.setNo(no);
					data.setIt(it);
					data.setEs(es);
					data.setUpdate(update);
					//add to list
					dataList.add(data);
				}
			}
		}
		return dataList;
	}
	/**
	 * 
	 * @param cc
	 * @param row
	 * @return String
	 */
private String getCellContents(int cc, int row) {
	Object content = "";
	if (cc != -1) {
	content = sheet.getCell(cc, row).getContents().trim();
	}
	return content + "";
}
	private boolean hasValue(int[] selCols, String de, String ch, String pl,
			String nl, String si, String gb, String dk, String fr, String lu, String be, String se, String no, String it, String es) {
		for (int col : selCols) {
			if (col == DE) {
				if (!de.equals("")) {
					return true;
				}
			} else if (col == CH) {
				if (!ch.equals("")) {
					return true;
				}
			} else if (col == NL) {
				if (!nl.equals("")) {
					return true;
				}
			} else if (col == PL) {
				if (!pl.equals("")) {
					return true;
				}
			} else if (col == SI) {
				if (!si.equals("")) {
					return true;
				}
			} else if (col == GB) {
				if (!gb.equals("")) {
					return true;
				}
			} else if (col == DK) {
				if (!dk.equals("")) {
					return true;
				}
			} else if (col == FR) {
				if (!fr.equals("")) {
					return true;
				}
			} else if (col == LU) {
				if (!lu.equals("")) {
					return true;
				}
			} else if (col == BE) {
				if (!be.equals("")) {
					return true;
				}
			} else if (col == SE) {
				if (!se.equals("")) {
					return true;
				}
			} else if (col == NO) {
				if (!no.equals("")) {
					return true;
				}
			} else if (col == IT) {
				if (!it.equals("")) {
					return true;
				}
			} else if (col == ES) {
				if (!es.equals("")) {
					return true;
				}
			}
		}
		return false;
	}

	private int getColumn(String cc) {
		int col = -1;
		if (sheet.findLabelCell("value " + cc) != null) {
		col = sheet.findLabelCell("value " + cc).getColumn();
		}
		return col; 
	}

	/**
	 * Checks if the dataList already contains a KCXCode. KCXCode must be
	 * unique. returns true if kcxcode is unique otherwise false.
	 * 
	 * @param dataList ArrayList<XLSData> 
	 * @param val String
	 * @return boolean
	 */
	private boolean checkData(ArrayList<XLSData> dataList, String val) {
		for (XLSData x : dataList) {
			KCXData d = (KCXData) x;
			if (d.getKcxCode().equals(val)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * retrieves the last tag out of the xml Path. if that cell doesnt contain a
	 * tag, the whole content of that cell will be returned as String.
	 * 
	 * @param contents String
	 * @return String
	 */
	private String getTag(String contents) {
		int e = contents.indexOf("<");
		String result = "";
		if (e != -1) {
			result = contents.substring(0, e - 1);
		} else {
			result = contents;
		}
		return result;
	}
}
