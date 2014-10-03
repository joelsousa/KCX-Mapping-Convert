package com.kewill.kcx.component.mapping.util;

public class UtilsTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Utils.removeZabisDecimalPlaceV7("123456", 1);
		Utils.removeZabisDecimalPlaceV7("123456", 2);
		Utils.removeZabisDecimalPlaceV7("123456", 3);
		Utils.removeZabisDecimalPlaceV7("000056", 2);
		Utils.removeZabisDecimalPlaceV7("000056", 3);
		Utils.removeZabisDecimalPlaceV7("123000", 3);
		Utils.removeZabisDecimalPlaceV7("123000", 2);
		Utils.removeZabisDecimalPlaceV7("123000", 1);
		Utils.removeZabisDecimalPlaceV7("000700", 3);
		Utils.removeZabisDecimalPlaceV7("000700", 1);
		
	}

}
