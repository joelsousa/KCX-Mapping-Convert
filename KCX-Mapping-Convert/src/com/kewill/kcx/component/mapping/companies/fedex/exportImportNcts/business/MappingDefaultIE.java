package com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.business;

import java.text.DecimalFormat;

import com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.MapExportDeclarationFK;
import com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.MapImportDeclarationFK;
import com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.msg.MsgDeclnInput;
import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Deferment;
import com.kewill.kcx.component.mapping.countries.de.Import20.msg.MsgImportDeclaration;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpDat;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpDatPos;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.common.PreviousDocumentV21;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;

public class MappingDefaultIE extends MappingDefaultConverter {

	@Override
	public void setImportDefault(MapImportDeclarationFK map) {
		MsgImportDeclaration message = map.getMessage();
		/*
		 * Issue 100 Version 36 IE Import
		 * 
		 * Set Kids <PaymentType> to default value 'F'. Other possible values:
		 * "A" (cash), "F" (deferred) , " J" (OPD) or " M"(secured)
		 */
		message.setPaymentType("F");
		/*
		 * joel
		 * 
		 * Issue 97 Version 36 Set default for KIDS <CustomsStatus> to IMY
		 * (simplified procedure).
		 */
		message.setCustomsStatus("IMY");

		/*
		 * joel
		 * 
		 * IE Mapping issue ID 100 Import - FedEX/Kids Mapping.
		 * 
		 * Set Kids <Deferment>/<TIN> to default value: VAT9507146I
		 */
		Deferment deferment = new Deferment();

		deferment.setTIN("VAT9507146I");

		message.addDefermentList(deferment);

		/*
		 * joel
		 * 
		 * Ticket 17081
		 * 
		 * IE Mapping issue ID 98 Version 37 Import - FedEX/Kids Mapping.
		 * Restrict PrecviousCustomsDocument/ReferenceIdentifier to 19
		 * characters if it starts with Y-CLE
		 */

		String number = message.getPreviousDocument().getNumber();

		String REF = "Y-CLE.*";

		if (!Utils.isStringEmpty(number) && number.matches(REF)) {
			if (number.length() > 19)
				message.getPreviousDocument()
						.setNumber(number.substring(0, 19));
		}

		/*
		 * joel
		 * 
		 * Ticket 17102 : TODO Set only for testing purposes
		 * 
		 * KCX IE Mapping issue ID 104 Import - FedEX/Kids Mapping Version 39
		 * 
		 * Restrict Kids element
		 * ImportDeclaration/GoodsDeclaration/ReferenceNumber to values less
		 * than 65535 (eg take last 5 digits and 'roll-over' if over 65535)
		 */
		Integer referenceNumber = new Integer(message.getReferenceNumber());

		if (referenceNumber.intValue() > 65535) {
			int lastFive = referenceNumber.intValue() % 100000;
			if (lastFive > 65535)
				lastFive = lastFive - 65535;

			message.setReferenceNumber(new Integer(lastFive).toString());
		}

		/*
		 * Issue 118 Version 43
		 * 
		 * For both exports and importa we need to send to IE the exchange rate.
		 * As this is not being sent by FedEX, the following KIDS tags need to
		 * default to 1 and also map to the IE tags. 
		 * 
		 * Import 
		 * Value 1 mapped to
		 * KIDS tag ImportDeclaration/Invoice/ExchangeRate which is then mapped
		 * to CM tag declaration/currencyexchnage/ratenumeric
		 */
		message.getInvoice().setExchangeRate("1");
		
	}

	@Override
	public void setExportDefault(MapExportDeclarationFK map) {
		MsgExpDat message = map.getMessage();
		MsgDeclnInput input = map.getMsgDecln();
		DecimalFormat fm = new DecimalFormat("00");
		PreviousDocumentV21 previousDocument = null;
		String previousFromAWB = null;
		/*
		 * Issue 105 Version 41 IE Export
		 * 
		 * Value in DECLN_INPUT/Shp_Decln_Sec_Input/TRPT_ID_IN_LND should be
		 * mapped to ExportDeclaration/GoodsDeclaration/MeansOfTransportInland/
		 * TransportationNumber which should then map to
		 * ExportCustomsDeclaration/Declaration
		 */

		/*
		 * Issue 106 Version 41
		 * 
		 * IE Export KIDS to IE
		 * 
		 * Mode of Transport both Inland and Border should be 2 digits. Add
		 * leading zero if source value is 1 digit. E.g. 3 = 03, 4=04
		 */
		String transportModeInland = message.getTransportMeansInland()
				.getTransportMode();

		if (!Utils.isStringEmpty(transportModeInland)) {
			message.getTransportMeansInland().setTransportMode(
					fm.format(new Integer(transportModeInland)));
		}

		String transportModeBorder = message.getTransportMeansBorder()
				.getTransportMode();

		if (!Utils.isStringEmpty(transportModeBorder)) {
			message.getTransportMeansBorder().setTransportMode(
					fm.format(new Integer(transportModeBorder)));
		}

		/*
		 * Issue 108 Version 41 IE Export FedEx to KIDS
		 * 
		 * Dispatch/Arrival time FedEx tag (<DECLN_ROUTE_ARR_TMSTP>) should be
		 * mapped as follows: Exports:
		 * ExportDeclaration/GoodsDeclaration/GoodsItem/AddInfoStatement Code =
		 * 1V20 Text = <DECLN_ROUTE_ARR_TMSTP>"
		 */

		for (MsgExpDatPos item : message.getGoodsItemList()) {

			/*
			 * Issue 109 Version
			 * 
			 * IE Export FedEx to KIDS
			 * 
			 * As part of the mapping from FedEX to KIDS add following default
			 * to GoodsItem/PreviousDocument: Z-740-nnnnnnnnn where nnnnnnn is
			 * the value in -<Shp_Decln_Sec_Input><AWB_NBR>"
			 */

			if (!Utils.isStringEmpty(input.getShpDeclSecInput().getAwbNbr())) {
				previousDocument = new PreviousDocumentV21();
				previousFromAWB = input.getShpDeclSecInput().getAwbNbr();
				previousDocument.setReference("Z-740-" + previousFromAWB);
				item.addPreviousDocumentList(previousDocument);
			}

			setDescriptionText(item);
		}

		defaultExportValuesTransport(map);
		defaultExportValuesLocation(map);
		
		
	}

	private void setDescriptionText(MsgExpDatPos item) {
		String auxStr = null;
		/*
		 * Issue 107 Version 41 If product description at item level
		 * (exportDeclaration
		 * /v1:ExportCustomsDeclaration/v1:Declaration/v1:GoodsShipment/v1:
		 * CustomsGoodsItem/v1:Commodity/v1:DescriptionText) is greater than 80
		 * chars, output the first 80 chars
		 */

		if (!Utils.isStringEmpty(item.getDescription())
				&& (item.getDescription()).length() > 80) {
			auxStr = item.getDescription();
			item.setDescription(auxStr.substring(0, 80));
		}

	}

	@Override
	public void setNCTSDefault(KidsMessage msg) {

	}

}
