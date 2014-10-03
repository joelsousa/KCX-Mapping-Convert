package com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.business.MappingConverterFactory;
import com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.business.MappingDefaultConverter;
import com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.msg.MsgDeclnInput;
import com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.msg.common.DeclnCust;
import com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.msg.common.HdrDoc;
import com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.msg.common.ItemShpDeclnComdty;
import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.AdditionalCosts;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.AdditionalInformation;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.DV1;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.GoodsItemDeclaration;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.ImportPackage;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.IncoTerms;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Invoice;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Preference;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Statistic;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Traders;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.countries.de.Import20.msg.MsgImportDeclaration;
import com.kewill.kcx.component.mapping.countries.de.Import20.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Business;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CommodityCode;
import com.kewill.kcx.component.mapping.formats.kids.Import20.BodyImportDeclarationKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module : FEDEX-Import.<br>
 * Created : 31.10.2013<br>
 * Description : MapDeclnInput
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class MapImportDeclarationFK extends KidsMessage {
	private BodyImportDeclarationKids body = null;
	private MsgImportDeclaration message = null;
	private MsgDeclnInput msgDecln = null;

	// public MapImportDeclarationFK(XMLEventReader parser, KidsHeader
	// kidsHeader, String encoding) throws XMLStreamException {
	public MapImportDeclarationFK(MsgDeclnInput msgDecln,
			KidsHeader kidsHeader, String encoding) throws XMLStreamException {
		this.encoding = encoding;
		this.kidsHeader = kidsHeader;
		this.msgDecln = msgDecln;
		message = new MsgImportDeclaration();
	}

	public void getMessage(XMLStreamWriter writer) {
		this.mapFedexDeclnToKids();
		this.setDefault();

		this.writer = writer;
		try {
			body = new BodyImportDeclarationKids(writer);

			writeStartDocument(encoding, "1.0");
			openElement("soap:Envelope");
			setAttribute("xmlns:soap",
					"http://www.w3.org/2003/05/soap-envelope");

			kidsHeader.writeHeader();

			body.setKidsHeader(kidsHeader);
			body.setMessage(message);
			body.writeBody();

			closeElement(); // soap:Envelope
			writer.writeEndDocument();

			writer.flush();
			writer.close();

		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	private void mapFedexDeclnToKids() {

		String referenceNumber = ""; // EI20140603
		TransportMeans border = null;

		if (msgDecln.getShpDeclSecInput() != null) {

			if ("IE".equals(this.getKidsHeader().getCountryCode())) {
				// Shp_Decln_Sec_Input/GDS_LOC_CD
				// Shp_Decln_Sec_Input/DEST_LOCATION_CODE
				// If value is ORK output IEORK400
				// If value is SNN output IESNN400
				// If value is DUB output ISDUB400
				// else output original value.
				if (!Utils.isStringEmpty(msgDecln.getShpDeclSecInput()
						.getGdsLocCd())) {
					String auxStr = msgDecln.getShpDeclSecInput().getGdsLocCd();

					msgDecln.getShpDeclSecInput().setGdsLocCd(
							UtilsMapDeclarationFK.getLocation(auxStr));

				}

				if (!Utils.isStringEmpty(msgDecln.getShpDeclSecInput()
						.getDestLocationCd())) {
					String auxStr = msgDecln.getShpDeclSecInput()
							.getDestLocationCd();
					msgDecln.getShpDeclSecInput().setDestLocationCd(
							UtilsMapDeclarationFK.getLocation(auxStr));
				}
			}

			// EI20140602:
			// message.setReferenceNumber(msgDecln.getShpDeclSecInput().getShipmentOidNbr());
			// EI20140603:
			// message.setReferenceNumber(msgDecln.getShpDeclSecInput().getDeclnOidNbr());
			// //EI20140602
			referenceNumber = msgDecln.getShpDeclSecInput().getDeclnOidNbr();
			message.setDispatchCountry(msgDecln.getShpDeclSecInput()
					.getTrptCtryCd());
			message.setTotalNumberPositions(msgDecln.getShpDeclSecInput()
					.getCommodityCount());
			message.setGrossMass(msgDecln.getShpDeclSecInput().getGrossWt());
			message.setCustomerOrderNumber(msgDecln.getShpDeclSecInput()
					.getLocalShipmentOidNbr());
			/*
			 * joel
			 * 
			 * KCX IE Mapping issue ID 36 Import - Import Declaration Date
			 * 
			 * 27/11/2012 10:20:30 TO YYYYMMDDHHMM
			 */
			SimpleDateFormat dateFormatSource = new SimpleDateFormat(
					"dd/MM/yyyy HH:mm:ss");
			SimpleDateFormat dateFormatTarget = new SimpleDateFormat(
					"yyyyMMddHHmm");
			Date dateSource = null;
			try {
				dateSource = dateFormatSource.parse(msgDecln.getDeclSecInput()
						.getDeclnRouteArrTmstp());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			message.setDeclarationTime(dateFormatTarget.format(dateSource));

			if (!Utils.isStringEmpty(msgDecln.getShpDeclSecInput()
					.getNatOfTrans())) {
				Business business = new Business();
				business.setBusinessTypeCode(msgDecln.getShpDeclSecInput()
						.getNatOfTrans());
				message.setBusiness(business);
			}
			if (!Utils.isStringEmpty(msgDecln.getShpDeclSecInput()
					.getDutyBillToCd())) {
				String addressType = msgDecln.getShpDeclSecInput()
						.getDutyBillToCd();
				// TODO
				// DUTY_BILL_TO_ACCT_NBR
			}

			if (!Utils.isStringEmpty(msgDecln.getShpDeclSecInput()
					.getIncotermCd())) {
				IncoTerms incoterms = new IncoTerms();
				incoterms
						.setCode(msgDecln.getShpDeclSecInput().getIncotermCd());
				incoterms.setPlace(msgDecln.getShpDeclSecInput()
						.getIncotermCity());
				incoterms.setLocationCode(msgDecln.getShpDeclSecInput()
						.getIncotermLocCd());

				/*
				 * joel Ticket 17006 Kewill KCX IE Mapping issue ID 80 Export -
				 * FedEX/Kids Mapping Version 31
				 */
				/*
				 * joel
				 * 
				 * Issue 99 Version 36
				 * 
				 * Set Kids <IncoTerms><LocationCode> to '3' if Fedex
				 * <IncoTermCd> is "EXW", "FCA", "FAS" or "FOB". Otherwise keep
				 * value from <incoTermLocCd>.
				 */
				List<String> listLocationCode = new ArrayList<String>();
				listLocationCode.add("EXW");
				listLocationCode.add("FCA");
				listLocationCode.add("FAS");
				listLocationCode.add("FOB");

				if ("IE".equals(this.kidsHeader.getCountryCode())) {
					if (listLocationCode.contains(msgDecln.getShpDeclSecInput()
							.getIncotermCd())) {
						incoterms.setLocationCode("3");
					}
				}
				message.setIncoterms(incoterms);
			}
			if (!Utils.isStringEmpty(msgDecln.getShpDeclSecInput()
					.getInvTotAmt())) {
				Invoice invoice = new Invoice();
				invoice.setAmount(msgDecln.getShpDeclSecInput().getInvTotAmt());
				invoice.setCurrency(msgDecln.getShpDeclSecInput().getInvCurCd());
				/*
				 * joel ignore the value from the Fedex Declaration field
				 * <INV_CUR_CD> and always set it to EUR in the KIDS declaration
				 */
				if ("IE".equals(this.kidsHeader.getCountryCode())) {
					invoice.setCurrency("EUR");
				}

				message.setInvoice(invoice);
			}
			/*
			 * joel
			 * 
			 * Ticket 16918
			 * 
			 * KCX IE Mapping issue ID 42 Import - FedEX/Kids Mapping Version 20
			 * 
			 * FedEx to KIDS <TRPT_MOD_IN_LND_CD> not being mapped
			 * MeansofTransportBorder/TrannsportMode
			 * 
			 * Map SHPMT_DECLN/TRPT_MOD_IN_LND_CD from Shp_Decln_Sec_Input.xsd
			 * to ImportDeclaration/GoodsDeclaration/MeansofTransportBorder/
			 * TransportMode
			 * 
			 * all Import
			 */

			if (!Utils.isStringEmpty(msgDecln.getShpDeclSecInput()
					.getTrptModInLndCd())) {
				border = new TransportMeans();
				border.setTransportationType(msgDecln.getShpDeclSecInput()
						.getTrptModInLndCd());

				// Currently this is not generated. Therefore default
				// MeansOfTRansportBorder/TransportationCountry to value US and
				// this should then map to CMatters tag
				// RegistrationNationalityIdentifier

				if ("IE".equals(this.getKidsHeader().getCountryCode())) {
					border.setTransportationCountry("US");
				}
				message.setMeansOfTransportBorder(border); // Inland, Arrival
			}

			/*
			 * joel
			 * 
			 * Ticket 16927
			 * 
			 * KCX IE Mapping issue ID 33 - FedEX/Kids Mapping Version 20
			 * 
			 * FedEx to KIDS No FedEX/Kids mapping for Export:
			 * GoodsDEclaration/IntendedOfficeofExit or
			 * ImportGoodsdeclaration/CustomsOfficeofEntry.
			 * 
			 * Both tags should be mapped from FedEX tag: <GDS_LOC_CD>
			 * 
			 * All Export & Import
			 * 
			 * Version 26 ID 58
			 */

			if (!Utils.isStringEmpty(msgDecln.getShpDeclSecInput()
					.getGdsLocCd())) {
				message.setCustomsOfficeEntry(msgDecln.getShpDeclSecInput()
						.getGdsLocCd());
				message.setGoodsLocation(msgDecln.getShpDeclSecInput()
						.getGdsLocCd());

			}

			/*
			 * Joel
			 * 
			 * Version 26 ID 59
			 */

			if (!Utils.isStringEmpty(msgDecln.getShpDeclSecInput()
					.getTrptModCd())) {
				TransportMeans arrival = new TransportMeans();

				arrival.setTransportationType(msgDecln.getShpDeclSecInput()
						.getTrptModCd());
				message.setMeansOfTransportArrival(arrival);

			}

		}

		if (msgDecln.getDeclSecInput() != null) {

			if ("IE".equals(this.getKidsHeader().getCountryCode())) {
				// Decln_Sec_Input/LOCATION_CD
				// If value is ORK output IEORK400
				// If value is SNN output IESNN400
				// If value is DUB output ISDUB400
				// else output original value.
				if (!Utils.isStringEmpty(msgDecln.getDeclSecInput()
						.getLocationCd())) {
					String auxStr = msgDecln.getDeclSecInput().getLocationCd();
					msgDecln.getDeclSecInput().setLocationCd(
							UtilsMapDeclarationFK.getLocation(auxStr));
					// TODO : Mapping into Kids Format
				}

				if (!Utils.isStringEmpty(msgDecln.getShpDeclSecInput()
						.getDestLocationCd())) {
					String auxStr = msgDecln.getShpDeclSecInput()
							.getDestLocationCd();
					msgDecln.getShpDeclSecInput().setDestLocationCd(
							UtilsMapDeclarationFK.getLocation(auxStr));
				}
			}

			if (!Utils
					.isStringEmpty(msgDecln.getDeclSecInput().getLocationCd())) {
				message.setCustomsOfficeOfDeclaration(msgDecln
						.getDeclSecInput().getLocationCd());
			}

			message.setReferenceNumber(msgDecln.getDeclSecInput()
					.getDeclnOidNbr()); // EI20140603
			if (Utils
					.isStringEmpty(msgDecln.getDeclSecInput().getDeclnOidNbr())) {
				message.setReferenceNumber(referenceNumber); // EI20140603
			}
			if (!Utils.isStringEmpty(msgDecln.getDeclSecInput().getRouteNbr())) {
				if (border == null)
					border = new TransportMeans();

				border.setTransportationNumber(msgDecln.getDeclSecInput()
						.getRouteNbr());
				message.setMeansOfTransportBorder(border); // Inland, Arrival
			}

			/*
			 * joel Ticket id 16917 KCX IE Mapping issue ID 41 Import -
			 * FedEX/Kids Mapping Version 20 FedEx to KIDS Destination country
			 * (COUNTRY_CD) is not being mapped to KIDS
			 * GoodsDeclaration/DestinationCountry
			 * 
			 * Map DECLN/COUNTRY_CD from Decln_Sec-Input.xsd to
			 * ImportDeclaration/GoodsDeclaration/DestinationCountry
			 * 
			 * All Import
			 */
			if (!Utils.isStringEmpty(msgDecln.getDeclSecInput().getCountryCd())) {
				message.setDestinationCountry(msgDecln.getDeclSecInput()
						.getCountryCd());
			}

		}

		// joel set Additional Documents
		// Scheduled Time of Arrival (STA) (Code 1D24)
		/*
		 * joel
		 * 
		 * Ticket 16975 ID 71 Import/Export Version 28
		 */

		AdditionalInformation addInfo = new AdditionalInformation();
		addInfo.setStatementCode("1D24");

		SimpleDateFormat dateFormatSource = new SimpleDateFormat(
				"dd/MM/yyyy HH:mm:ss");
		SimpleDateFormat dateFormatTarget = new SimpleDateFormat(
				"dd/MM/yyyy HHmm");
		Date dateSource = null;
		try {
			dateSource = dateFormatSource.parse(msgDecln.getDeclSecInput()
					.getDeclnRouteArrTmstp());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * 201408050600 Format: StatementText should be in the format DD/MM/YYYY
		 * hhmm so tag would read <StatementText>06/08/20140600</StatementText>
		 */

		addInfo.setStatementText(dateFormatTarget.format(dateSource));
		message.setAddInfo(addInfo);

		if (msgDecln.getDeclCustomerSecInput() != null) {
			ArrayList<DeclnCust> list = msgDecln.getDeclCustomerSecInput()
					.getDeclnCustList();
			if (list != null && !list.isEmpty()) {
				Traders traders = new Traders();
				for (DeclnCust customer : list) {

					if (customer != null && customer.getCustomerType() != null) {

						/*
						 * joel Ticket 17007 KCX IE Mapping issue ID 85
						 * Import/Export - FedEX/Kids Mapping Version 33 TODO
						 * 
						 * So mapping required is: If VAT_NBR blank/null, output
						 * NR in TIN
						 */
						Address address = this.mapFedexDeclnToAddress(customer);
						/*
						 * CZ - ShipperConsignor
						 */
						if (customer.getCustomerType().equalsIgnoreCase("CZ")) {
							traders.setConsignorAddress(address);
							TIN tin = new TIN();
							if (!Utils.isStringEmpty(customer.getVatNbr())) {
								tin.setTIN(customer.getVatNbr());
							} else {
								tin.setTIN("NR");
							}
							traders.setConsignorTIN(tin);
						} else if (customer.getCustomerType().equalsIgnoreCase(
								"CN")) {
							/*
							 * CN - Consignee
							 */
							traders.setConsigneeAddress(address);
							TIN tin = new TIN();
							if (!Utils.isStringEmpty(customer.getVatNbr())) {
								tin.setTIN(customer.getVatNbr());
							} else {
								tin.setTIN("NR");
							}
							traders.setConsigneeTIN(tin);
						} else if (customer.getCustomerType().equalsIgnoreCase(
								"CD")) {
							/*
							 * CD - Declarant
							 */
							traders.setDeclarantAddress(address);
							TIN tin = new TIN();
							if (!Utils.isStringEmpty(customer.getVatNbr())) {
								tin.setTIN(customer.getVatNbr());
							} else {
								tin.setTIN("NR");
							}
							traders.setDeclarantTIN(tin);
						} else if (customer.getCustomerType().equalsIgnoreCase(
								"AG")) { // Agent
							/*
							 * joel
							 * 
							 * Version 27 : ID 64 FedEX are sending Declarant
							 * details in DECLN_CUST, CUSTOMER_TYPE_CD = AG.
							 * This data is not being mapped to
							 * Importdeclaration/Trader/Declarant
							 */
							traders.setDeclarantAddress(address);
							TIN tin = new TIN();
							if (!Utils.isStringEmpty(customer.getVatNbr())) {
								tin.setTIN(customer.getVatNbr());
							} else {
								tin.setTIN("NR");
							}
							traders.setDeclarantTIN(tin);
						}
					}
				}
				message.setTraders(traders);
			}
		}

		// Shp_Decln_Sec_Input\Shp_Decln_Cmdty_Doc_Sec_Input\ITEM_SHP_DECLN_COMDTY_DOC
		// \HDR_DOC_CODE
		Document document = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();

		cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);

		if (msgDecln.getShpDeclSecInput().getShpDeclnCmdtyDocSecInput() != null) {
			ArrayList<HdrDoc> listHdrDoc = msgDecln.getShpDeclSecInput()
					.getShpDeclnCmdtyDocSecInput()
					.getItemShpDeclnComdtyDocList();

			// ArrayList<Document> documentList

			if (listHdrDoc != null) {
				for (HdrDoc docHeader : listHdrDoc) {
					document = new Document();

					if (!Utils.isStringEmpty(docHeader.getHdrDocCode())) {
						document.setType(docHeader.getHdrDocCode());
					}
					if (!Utils.isStringEmpty(docHeader.getHdrDocRef())) {
						document.setNumber(docHeader.getHdrDocRef());
					}

					if (Utils.isStringEmpty(docHeader.getDocDate())) {
						document.setIssueDate(dateFormat.format(cal.getTime()));
					} else {

						document.setIssueDate(docHeader.getDocDate());
					}
					message.addDocumentList(document);
				}
			}
		}

		DecimalFormat dec2 = new DecimalFormat("##############0.00");
		dec2.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.US));
		BigDecimal total = new BigDecimal(0);
		if (msgDecln.getShpDeclCmdtySecInput() != null) {
			// items
			ArrayList<ItemShpDeclnComdty> list = msgDecln
					.getShpDeclCmdtySecInput().getItemShpDeclnComdtyList();
			// Shp_Decln_Cmdty_Sec_Input
			for (ItemShpDeclnComdty cmdtyItem : list) {
				GoodsItemDeclaration item = this
						.mapFedexDeclnToGoodsItem(cmdtyItem);
				if (!Utils.isStringEmpty(item.getInvoice().getAmount()))
					total = total.add(new BigDecimal(item.getInvoice()
							.getAmount()));
				this.checkDocsImport(item);
				message.addGoodsItemList(item);
			}
			// <ImportDeclaration><Invoice><Amount>
			String value = dec2.format(total);
			message.getInvoice().setAmount(value);

		}

		DV1 dv1 = new DV1();

		/*
		 * joel Ticket 17100 BuyerSellerRelationship depends on the statistical
		 * value: If statistical value <= 10.000, then 'S' otherwise 'A'
		 */
		BigDecimal bg = new BigDecimal("10000");
		int res = total.compareTo(bg);

		/*
		 * First Value is greater res == 1
		 */

		if (res == 1) {
			dv1.setBuyerSellerRelationship("A");
		} else {
			dv1.setBuyerSellerRelationship("S");
		}
		message.setDV1(dv1);
	}

	private GoodsItemDeclaration mapFedexDeclnToGoodsItem(
			ItemShpDeclnComdty cmdtyItem) {
		if (cmdtyItem == null) {
			return null;
		}

		if ("IE".equals(this.getKidsHeader().getCountryCode())) {
			// ITEM_SHP_DECLN_COMDTY/LOC_CD
			// If value is ORK output IEORK400
			// If value is SNN output IESNN400
			// If value is DUB output ISDUB400
			// else output original value.
			if (!Utils.isStringEmpty(cmdtyItem.getLocCd())) {
				String auxStr = cmdtyItem.getLocCd();
				cmdtyItem.setLocCd(UtilsMapDeclarationFK.getLocation(auxStr));
			}
		}

		if ("IE".equals(this.getKidsHeader().getCountryCode())) {
			// ITEM_DECLN_COMDTY_TAX/LOC_CD
			// If value is ORK output IEORK400
			// If value is SNN output IESNN400
			// If value is DUB output ISDUB400
			// else output original value.
			if (!Utils.isStringEmpty(cmdtyItem.getDeclnCmdtyTaxSecInput()
					.getLocCd())) {
				String auxStr = cmdtyItem.getDeclnCmdtyTaxSecInput().getLocCd();
				cmdtyItem.getDeclnCmdtyTaxSecInput().setLocCd(
						UtilsMapDeclarationFK.getLocation(auxStr));
			}
		}

		GoodsItemDeclaration item = new GoodsItemDeclaration();

		item.setItemNumber(cmdtyItem.getSequenceNbr());
		if (!Utils.isStringEmpty(cmdtyItem.getTaricCmdtyCd())) {
			CommodityCode commodityCode = new CommodityCode();
			commodityCode.setTarifCode(cmdtyItem.getTaricCmdtyCd());
			item.setCommodityCode(commodityCode);
		}
		/*
		 * joel Add 1V20
		 */
		if ("IE".equals(this.getKidsHeader().getCountryCode())) {
			AdditionalCosts additionsDeductions = new AdditionalCosts();
			additionsDeductions.setType("1V20");
			additionsDeductions.setAmount("0");

			item.addAdditionalList(additionsDeductions);
		}

		/*
		 * Armando - Ticket 16921 KCX IE Mapping issue ID 45 Import - FedEX/Kids
		 * Mapping Version 20
		 */
		if (!Utils.isStringEmpty(cmdtyItem.getGoodsDesc())) {
			item.setDescription(cmdtyItem.getGoodsDesc());
		}

		/*
		 * Armando - Ticket 16922 KCX IE Mapping issue ID 46 Import - FedEX/Kids
		 * Mapping Version 20
		 */
		if (!Utils.isStringEmpty(cmdtyItem.getPrefNbr())) {
			Preference preference = new Preference();
			preference.setPreferenceCertificate(cmdtyItem.getPrefNbr());
			item.setPreference(preference);
		}

		item.setProcedureCode(cmdtyItem.getCpcCd());
		item.setCountryOfOrigin(cmdtyItem.getItemOrgCtryCd());
		item.setNetMass(cmdtyItem.getItemNetWt());
		item.setGrossMass(cmdtyItem.getItemGrossWt());
		if (cmdtyItem.getPkgCount() != null || cmdtyItem.getPkgKind() != null
				|| cmdtyItem.getPkgMarks() != null) {
			ImportPackage pack = new ImportPackage();
			pack.setQuantity(cmdtyItem.getPkgCount());
			pack.setType(cmdtyItem.getPkgKind());
			if (Utils.isStringEmpty(cmdtyItem.getPkgKind())) { // EI20140603
				pack.setType("PK");
			}
			pack.setMarks(cmdtyItem.getPkgMarks());
			item.setImportPackage(pack);
		}

		Invoice invoice = new Invoice();
		if (!Utils.isStringEmpty(cmdtyItem.getItemLcncVal())) {
			invoice.setAmount(cmdtyItem.getItemLcncVal());
		}
		/*
		 * joel
		 * 
		 * <shp_Decln_Cmdty_Sec_input><ITEM_VALUE> to
		 * <ImportDeclaration><GoodsItem><Invoice><Amount>
		 */
		if ("IE".equals(this.kidsHeader.getCountryCode())
				&& !Utils.isStringEmpty(cmdtyItem.getItemValue())) {
			invoice.setAmount(cmdtyItem.getItemValue());
		}
		item.setInvoice(invoice);
		/*
		 * Shp_Decln_Cmdty_Sec_Input/ITEM_STAT_VALUE TO
		 * ImportDeclaration/GoodsItem/ Statistic/StatisticalValue
		 */
		Statistic statistic = new Statistic();

		/*
		 * joel <shp_Decln_Cmdty_Sec_input><ITEM_VALUE> to
		 * <ImportDeclaration><GoodsItem><Statistic><StatisticalValue>
		 */

		if (!Utils.isStringEmpty(cmdtyItem.getItemStatValue())) {
			statistic.setStatisticalValue(cmdtyItem.getItemStatValue());
		}

		if ("IE".equals(this.kidsHeader.getCountryCode())
				&& !Utils.isStringEmpty(cmdtyItem.getItemValue())) {
			statistic.setStatisticalValue(cmdtyItem.getItemValue());
		}

		item.setStatistic(statistic);

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();

		cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);

		if (cmdtyItem.getShpHdrDoc() != null) {
			ArrayList<HdrDoc> list = cmdtyItem.getShpHdrDoc()
					.getItemShpDeclnComdtyDocList();
			if (list != null) {
				for (HdrDoc hdr : list) {
					if (hdr != null) {
						/*
						 * joel Ticket 16977 (MoreData) KCX IE Mapping issue ID
						 * 73 Export/import - FedEX/Kids Mapping Version 28
						 * 
						 * ALL Export and import - FedEx to Kids Mapping
						 * Amendment Shp_Decln_cmdty_doc_Sec_Input/HDR_DOC_CODE
						 * should map to GoodsDeclaration/Document/Type
						 * Shp_Decln_cmdty_doc_Sec_Input/HDR_DOC_REF should map
						 * to GoodsDeclaration/Document/Reference
						 * GoodsDeclaration/Document/IssueDate default to
						 * current date -1 if field is empty.
						 */
						Document doc = new Document();
						doc.setType(hdr.getHdrDocCode());

						doc.setNumber(hdr.getHdrDocRef());

						if (hdr.getDocDate() == null) {
							doc.setIssueDate(dateFormat.format(cal.getTime()));
						} else {

							doc.setIssueDate(hdr.getDocDate());
						}
						item.addDocumentList(doc);
					}
				}
			}
		}

		/*
		 * joel
		 * 
		 * Ticket 16920
		 * 
		 * KCX IE Mapping issue ID 44 Import - FedEX/Kids Mapping Version 20
		 * 
		 * FedEx to KIDS
		 * 
		 * <PREV_DOC_CLASS_CD>Y</PREV_DOC_CLASS_CD>,
		 * <PREV_DOC_TYP_CD>CLE</PREV_DOC_TYP_CD>
		 * <PREV_DOC_REF>140709</PREV_DOC_REF> not being mapped to
		 * PreviousDocument in the format : a-yyy-nnnnnnnn e.y. Y-CLE-140709
		 * 
		 * All Import and Export
		 */

		if (!Utils.isStringEmpty(cmdtyItem.getPrevDocClassCd())
				&& !Utils.isStringEmpty(cmdtyItem.getPrevDocTypCd())
				&& !Utils.isStringEmpty(cmdtyItem.getPrevDocRef())) {
			PreviousDocument previousDocument = new PreviousDocument();
			String pdClass = cmdtyItem.getPrevDocClassCd();
			String pdType = cmdtyItem.getPrevDocTypCd();
			String pdRef = cmdtyItem.getPrevDocRef();

			String auxPreviousDocumentNumber = pdClass + "-" + pdType + "-"
					+ pdRef;

			previousDocument.setNumber(auxPreviousDocumentNumber);
			message.setPreviousDocument(previousDocument);
		}

		return item;
	}

	private Address mapFedexDeclnToAddress(DeclnCust customer) {
		if (customer == null) {
			return null;
		}
		Address address = new Address();
		address.setName(customer.getName());
		address.setStreet(customer.getStreet());
		address.setCity(customer.getCity());
		address.setPostalCode(customer.getZip());
		address.setCountry(customer.getCountryCode());
		address.setCountrySubEntity(customer.getStateCode());

		return address;
	}

	private void checkDocsImport(GoodsItemDeclaration item) {
		SimpleDateFormat dateFormatSource = new SimpleDateFormat("dd/MM/yyyy");
		List<String> listAllTypesDocHeader = new ArrayList<String>();
		List<String> listAllTypesDocItem = new ArrayList<String>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();

		cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);

		if (item.getDocumentList() != null && item.getDocumentList().size() > 0) {
			for (Document docs : item.getDocumentList()) {
				listAllTypesDocItem.add(docs.getType());
			}
		}

		if (message.getDocumentList() != null
				&& message.getDocumentList().size() > 0) {
			for (Document docs : message.getDocumentList()) {
				listAllTypesDocHeader.add(docs.getType());
			}
		}

		/*
		 * joel
		 * 
		 * ID : 62
		 * 
		 * Issues Log Project: FedEx KCX POC Version 27 25-07-2014
		 */
		if (!listAllTypesDocItem.contains("N380")) {
			Document doc380 = new Document();
			if (msgDecln.getShpDeclSecInput() != null) {
				doc380.setNumber(msgDecln.getShpDeclSecInput().getAwbNbr());
				if (msgDecln.getShpDeclSecInput().getInputDt() == null) {
					doc380.setIssueDate(dateFormat.format(cal.getTime()));
				} else {
					Date dateSource = null;
					try {
						dateSource = dateFormatSource.parse(msgDecln
								.getShpDeclSecInput().getInputDt());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					doc380.setIssueDate(dateFormat.format(dateSource));
				}
			}
			doc380.setType("N380");
			item.addDocumentList(doc380);
		}

		if (!listAllTypesDocHeader.contains("N740")) {
			Document doc740 = new Document();

			if (msgDecln.getShpDeclSecInput() != null) {
				doc740.setNumber(msgDecln.getShpDeclSecInput().getAwbNbr());

				if (msgDecln.getShpDeclSecInput().getInputDt() == null) {
					doc740.setIssueDate(dateFormat.format(cal.getTime()));
				} else {
					Date dateSource = null;
					try {
						dateSource = dateFormatSource.parse(msgDecln
								.getShpDeclSecInput().getInputDt());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					doc740.setIssueDate(dateFormat.format(dateSource));
				}
			}
			doc740.setType("N740");
			message.addDocumentList(doc740);
		}
	}

	public BodyImportDeclarationKids getBody() {
		return body;
	}

	public MsgImportDeclaration getMessage() {
		return message;
	}

	public MsgDeclnInput getMsgDecln() {
		return msgDecln;
	}

	public void setDefault() {
		MappingConverterFactory mapping = new MappingConverterFactory();
		MappingDefaultConverter defaultConverter = mapping
				.newConverter(this.kidsHeader.getCountryCode());
		if (defaultConverter != null)
			defaultConverter.setImportDefault(this);
	}

}
