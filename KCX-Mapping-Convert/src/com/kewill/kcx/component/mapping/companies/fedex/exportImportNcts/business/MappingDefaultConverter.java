package com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.business;

import com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.MapExportDeclarationFK;
import com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.MapImportDeclarationFK;
import com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.UtilsMapDeclarationFK;
import com.kewill.kcx.component.mapping.countries.de.Import20.msg.MsgImportDeclaration;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PlaceOfLoading;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpDat;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;

public abstract class MappingDefaultConverter {
	public abstract void setImportDefault(MapImportDeclarationFK message);

	public abstract void setExportDefault(MapExportDeclarationFK message);

	public abstract void setNCTSDefault(KidsMessage msg);

	protected void defaultExportValuesTransport(MapExportDeclarationFK map) {
		/*
		 * Issue 113 Version 41
		 */
		MsgExpDat message = map.getMessage();

		/* Means Of Transport */
		setDefaultMeansOfTransport(message.getMeansOfTransport());

		/* Transport Means Inland */
		setDefaultMeansOfTransport(message.getTransportMeansInland());

		/* Transport Means Departure */
		setDefaultMeansOfTransport(message.getTransportMeansDeparture());

		/* Transport Means Border */
		setDefaultMeansOfTransport(message.getTransportMeansBorder());
		
	}
	
	
	protected void defaultExportValuesLocation(MapExportDeclarationFK map) {
		/*
		 * Issue 113 Version 41
		 */
		MsgExpDat message = map.getMessage();
		
		/* Place Of Loading */
		setDefaultPlaceOfLoading(message.getPlaceOfLoading());

		/* Customs Office Export */
		if (!Utils.isStringEmpty(message.getCustomsOfficeExport())) {
			String auxStr = message.getCustomsOfficeExport();
			message.setCustomsOfficeExport(UtilsMapDeclarationFK
					.getLocation(auxStr));
		}

		/* Customs Office For Completion */
		if (!Utils.isStringEmpty(message.getCustomsOfficeForCompletion())) {
			String auxStr = message.getCustomsOfficeForCompletion();
			message.setCustomsOfficeForCompletion(UtilsMapDeclarationFK
					.getLocation(auxStr));
		}

		/* Intended Office Of Exit */
		if (!Utils.isStringEmpty(message.getIntendedOfficeOfExit())) {
			String auxStr = message.getIntendedOfficeOfExit();
			message.setIntendedOfficeOfExit(UtilsMapDeclarationFK
					.getLocation(auxStr));
		}

		/* Place Of Declaration */
		if (!Utils.isStringEmpty(message.getPlaceOfDeclaration())) {
			String auxStr = message.getPlaceOfDeclaration();
			message.setPlaceOfDeclaration(UtilsMapDeclarationFK
					.getLocation(auxStr));
		}
	}

	private void setDefaultPlaceOfLoading(PlaceOfLoading pl) {
		
		if ( pl == null)
			return;
		
		if (!Utils.isStringEmpty(pl.getCode())) {
			String auxStr = pl.getCode();
			pl.setCode(UtilsMapDeclarationFK.getLocation(auxStr));
		}
	}

	private void setDefaultMeansOfTransport(TransportMeans mt) {

		if (mt == null)
			return;

		if (!Utils.isStringEmpty(mt.getPlaceOfLoading())) {
			String auxStr = mt.getPlaceOfLoading();
			mt.setPlaceOfLoading(UtilsMapDeclarationFK.getLocation(auxStr));
		}
		if (!Utils.isStringEmpty(mt.getPlaceOfLoadingCode())) {
			String auxStr = mt.getPlaceOfLoadingCode();
			mt.setPlaceOfLoadingCode(UtilsMapDeclarationFK.getLocationCode(auxStr));
		}
	}

	protected void defaultImportValuesLocation(MapImportDeclarationFK map) {
		/*
		 * Issue 113 Version 41
		 */

		MsgImportDeclaration message = map.getMessage();

		/* Place Of Declaration */
		if (!Utils.isStringEmpty(message.getPlaceOfDeclaration())) {
			String auxStr = message.getPlaceOfDeclaration();
			message.setPlaceOfDeclaration(UtilsMapDeclarationFK
					.getLocation(auxStr));
		}

		/* Customs Office Of Declaration */
		if (!Utils.isStringEmpty(message.getCustomsOfficeOfDeclaration())) {
			String auxStr = message.getCustomsOfficeOfDeclaration();
			message.setCustomsOfficeOfDeclaration(UtilsMapDeclarationFK
					.getLocation(auxStr));
		}

		/* Customs Office Entry */
		if (!Utils.isStringEmpty(message.getCustomsOfficeEntry())) {
			String auxStr = message.getCustomsOfficeEntry();
			message.setCustomsOfficeEntry(UtilsMapDeclarationFK
					.getLocation(auxStr));
		}

		/* Goods Location */
		if (!Utils.isStringEmpty(message.getGoodsLocation())) {
			String auxStr = message.getGoodsLocation();
			message.setGoodsLocation(UtilsMapDeclarationFK.getLocation(auxStr));
		}
	}

}
