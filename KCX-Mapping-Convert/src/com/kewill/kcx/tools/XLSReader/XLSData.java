package com.kewill.kcx.tools.XLSReader;

/**
 * Data container for the XLSReader.
 * 
 * @author messer
 * @version 0.0.10
 */
public class XLSData {
	protected Object value = null;

	public XLSData() {

	}

	public XLSData(Object value) {
		this.value = value;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
}
