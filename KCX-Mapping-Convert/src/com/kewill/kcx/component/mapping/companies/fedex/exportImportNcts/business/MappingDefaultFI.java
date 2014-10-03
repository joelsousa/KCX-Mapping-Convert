package com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.business;

import com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.MapExportDeclarationFK;
import com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.MapImportDeclarationFK;
import com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.UtilsMapDeclarationFK;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PlaceOfLoading;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpDat;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;

public class MappingDefaultFI extends MappingDefaultConverter {
	@Override
	public void setImportDefault(MapImportDeclarationFK map) {
		/*
		 * Issue 113 Version 41
		 */
		defaultImportValuesLocation(map);
	}

	@Override
	public void setExportDefault(MapExportDeclarationFK map) {
		/*
		 * TODO : Issue 113 Version 41
		 */
		defaultExportValuesTransport(map);
		defaultExportValuesLocation(map);
	}

	@Override
	public void setNCTSDefault(KidsMessage msg) {
		// TODO Auto-generated method stub
	}
}
