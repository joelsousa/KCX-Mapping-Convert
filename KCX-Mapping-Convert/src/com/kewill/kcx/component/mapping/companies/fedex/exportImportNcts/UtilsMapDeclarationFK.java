package com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts;

public class UtilsMapDeclarationFK {

	public static String getLocation(String locationCode) {
		// * If value = CPH output DK007903 If value = STO output SE303400 If
		// * value = MMA output SE000050 If value = GOT output SE603303

		if ("ORK".equals(locationCode)) {
			return "IEORK400";
		} else if ("SNN".equals(locationCode)) {
			return "IESNN400";
		} else if ("DUB".equals(locationCode)) {
			/*
			 * Item 63 reopened as there was a typing error ins the Issue
			 * ISDUB400 should have been IEDUB400
			 */
			return "IEDUB400";
		} else if ("CPH".equals(locationCode)) {
			return "DK007903";
		} else if ("STO".equals(locationCode)) {
			return "SE303400";
		} else if ("MMA".equals(locationCode)) {
			return "SE000050";
		} else if ("GOT".equals(locationCode)) {
			return "SE603303";
		} else if ("HEL".equals(locationCode)) {
			return "FI015300";
		}

		return locationCode;
	}
	
	public static String getLocationCode(String locationCode) {

		if ("ORK".equals(locationCode)) {
			return "IEORK400";
		} else if ("SNN".equals(locationCode)) {
			return "IESNN400";
		} else if ("DUB".equals(locationCode)) {
			/*
			 * Item 63 reopened as there was a typing error ins the Issue
			 * ISDUB400 should have been IEDUB400
			 */
			return "IEDUB400";
		} else if ("CPH".equals(locationCode)) {
			return "DK007903";
		} else if ("STO".equals(locationCode)) {
			return "SE303400";
		} else if ("MMA".equals(locationCode)) {
			return "SE000050";
		} else if ("GOT".equals(locationCode)) {
			return "SE603303";
		} else if ("HEL".equals(locationCode)) {
			return "Z";
		}

		return locationCode;
	}
}
