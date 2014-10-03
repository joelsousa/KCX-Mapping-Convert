package com.kewill.kcx.tools.XLSReader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * Class for Reading and modifying Excel Sheets.
 * 
 * @author messer
 * @version 0.0.10
 */
public class XLSReader {
	protected File f = null;
	protected Workbook workbook = null;

	public XLSReader(File f) throws BiffException, IOException {
		this.f = f;
		workbook = Workbook.getWorkbook(f);
	}

	public Workbook getWorkbook() {
		return workbook;
	}

	public void setWorkbook(Workbook workbook) {
		this.workbook = workbook;
	}

	/**
	 * Reads the XLS File and stores the results into an arraylist. Only the
	 * Columns which are defined by selCols and which are on the number of the
	 * workSheet will be stored.
	 * 
	 * @param int[]
	 * @param int
	 * @return ArrayList<XLSData>
	 */
	public ArrayList<XLSData> getDataObjects(int[] selCols, int workSheet) {
		ArrayList<XLSData> dataList = new ArrayList<XLSData>();
		Sheet sheet = workbook.getSheet(workSheet);
		for (int row = 0; row < sheet.getRows(); row++) {
			for (int col : selCols) {
				XLSData data = new XLSData(sheet.getCell(col, row)
						.getContents());
				dataList.add(data);
			}
		}
		return dataList;
	}

}
