package com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.business;

import java.util.HashMap;


/**
 * Responsible for creating an object for a converter between an input format
 * and an output format
 */
public class MappingConverterFactory {
	private static HashMap<String, Class> converterList = new HashMap<String, Class>();

	static {
		converterList.put("IE", MappingDefaultIE.class);
		converterList.put("DK", MappingDefaultDK.class);
		converterList.put("IS", MappingDefaultIS.class);
		converterList.put("SE", MappingDefaultSE.class);
		converterList.put("NO", MappingDefaultNO.class);
		converterList.put("FI", MappingDefaultFI.class);
	}

	/**
	 * Create a new instance for an input.
	 * 
	 * @param inputFormat
	 * @param outputFormat
	 * @return
	 */
	public MappingDefaultConverter newConverter(String input) {
		String compoundFormat = input;
		MappingDefaultConverter converterObject = null;
		Class<?> converterClass = null;

		try {
			converterClass = converterList.get(compoundFormat);
			if(converterClass == null)
				return null;
			converterObject = (MappingDefaultConverter) converterClass.newInstance();
		} catch (InstantiationException e) {
			converterObject = null;
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			converterObject = null;
			e.printStackTrace();
		} catch (Exception e) {
			converterObject = null;
			e.printStackTrace();
		}
		return converterObject;
	}
	
	
	
}
