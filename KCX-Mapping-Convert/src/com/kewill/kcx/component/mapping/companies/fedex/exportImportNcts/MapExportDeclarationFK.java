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
import com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.msg.common.AdditionalInfo;
import com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.msg.common.DeclnCust;
import com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.msg.common.HdrDoc;
import com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.msg.common.ItemShpDeclnComdty;
import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ApprovedTreatment;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Business;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CommodityCode;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.DocumentV20;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.IncoTerms;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PlaceOfLoading;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Statistic;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.WareHouse;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpDat;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpDatPos;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.common.PreviousDocumentV21;
import com.kewill.kcx.component.mapping.formats.kids.aes21.BodyExportDeclarationKids;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;

//import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PreviousDocument;

/**
 * Module : FEDEX-Export.<br>
 * Created : 13.12.2013<br>
 * Description : MapDeclnInput
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class MapExportDeclarationFK extends KidsMessage {
	private BodyExportDeclarationKids body = null;
	private MsgExpDat message = null;
	private MsgDeclnInput msgDecln = null;
	private String messageId = "";

	public MapExportDeclarationFK(MsgDeclnInput msgDecln,
			KidsHeader kidsHeader, String encoding) throws XMLStreamException {
		this.encoding = encoding;
		this.kidsHeader = kidsHeader;
		this.msgDecln = msgDecln;
		message = new MsgExpDat();
	}

	public void getMessage(XMLStreamWriter writer) {

		this.mapFedexDeclnToKids();
		this.setDefault();

		this.writer = writer;
		try {
			body = new BodyExportDeclarationKids(writer);

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
		DecimalFormat fm = new DecimalFormat("00");

		if (msgDecln.getCrnDeclnSecInput() != null) {
			/*
			 * TODO : joel 01/01/0000 00:00:00
			 */
			String auxDateStr = msgDecln.getCrnDeclnSecInput()
					.getArrival_tmstp().substring(0, 10);
			String[] parts = auxDateStr.split("/");
			String auxDateOfDeparture = parts[2] + parts[1] + parts[0];
			message.setDateOfDeparture(auxDateOfDeparture);
		}

		/*
		 * Issue 115 Version 43 TODO : Review
		 */
		if (msgDecln.getDeclSecInput() != null
				&& !Utils.isStringEmpty(msgDecln.getDeclSecInput()
						.getLocationCd())) {
			/*
			 * String auxStr = msgDecln.getDeclSecInput().getLocationCd();
			 * msgDecln
			 * .getDeclSecInput().setLocationCd(UtilsMapDeclarationFK.getLocation
			 * (auxStr));
			 */

		}

		/*
		 * Issue 115 Version 43 TODO : Review
		 */
		if (msgDecln.getShpDeclSecInput() != null) {
			if (!Utils.isStringEmpty(msgDecln.getShpDeclSecInput()
					.getGdsLocCd())) {

				/*
				 * String auxStr = msgDecln.getShpDeclSecInput().getGdsLocCd();
				 * msgDecln
				 * .getShpDeclSecInput().setGdsLocCd(UtilsMapDeclarationFK
				 * .getLocation(auxStr));
				 */
			}

			if (!Utils.isStringEmpty(msgDecln.getShpDeclSecInput()
					.getDestLocationCd())) {
				/*
				 * String auxStr =
				 * msgDecln.getShpDeclSecInput().getDestLocationCd();
				 * msgDecln.getShpDeclSecInput
				 * ().setDestLocationCd(UtilsMapDeclarationFK
				 * .getLocation(auxStr));
				 */
			}

			/*
			 * joel
			 * 
			 * Ticket 16979 (MoreData) KCX IE Mapping issue ID 75 Export -
			 * FedEX/Kids Mapping Version 28
			 */
			PlaceOfLoading placeOfLoading = new PlaceOfLoading();
			placeOfLoading.setCode(msgDecln.getShpDeclSecInput().getGdsLocCd());

			message.setPlaceOfLoading(placeOfLoading);

			/*
			 * joel
			 * 
			 * Ticket 16980 (MoreData) KCX IE Mapping issue ID 76 Export -
			 * FedEX/Kids Mapping Version 28
			 */
			message.setCustomsOfficeExport(msgDecln.getShpDeclSecInput()
					.getGdsLocCd());

			// EI20140528:message.setReferenceNumber(msgDecln.getShpDeclSecInput().getShipmentOidNbr());
			// EI20140603:
			// message.setReferenceNumber(msgDecln.getShpDeclSecInput().getDeclnOidNbr());
			// //EI20140528
			referenceNumber = msgDecln.getShpDeclSecInput().getDeclnOidNbr(); // EI20140603

			message.setDispatchCountry(msgDecln.getShpDeclSecInput()
					.getDispCtryCd());
			message.setDestinationCountry("US"); // EI20140703: Jira KCX295
			message.setGrossMass(msgDecln.getShpDeclSecInput().getGrossWt());
			message.setTotalNumberPositions(msgDecln.getShpDeclSecInput()
					.getCommodityCount());
			message.setTotalNumberOfPackages(msgDecln.getShpDeclSecInput()
					.getTotPkgNbr());

			if (!Utils.isStringEmpty(msgDecln.getShpDeclSecInput()
					.getTrptModInLndCd())
					|| !Utils.isStringEmpty(msgDecln.getShpDeclSecInput()
							.getTrptIdInLnd())) {
				TransportMeans inland = new TransportMeans();

				/*
				 * joel Issue 93 Country: all Version 36. If value is single
				 * number add leading zero e.g. 4 = 04, 3=03
				 */
				if (!Utils.isStringEmpty(msgDecln.getShpDeclSecInput()
						.getTrptModInLndCd())) {

					/*
					 * Issue 111 Version 41 When mapping to KIDS to KIDS tags
					 * Mode of Transport both Inland and Border/arrival. Where
					 * the country of declaration is NOT IE the value should be
					 * 1 digit therefore remove any leading zero's
					 */

					inland.setTransportMode(msgDecln.getShpDeclSecInput()
							.getTrptModInLndCd());
				}
				inland.setTransportationNumber(msgDecln.getShpDeclSecInput()
						.getTrptIdInLnd());
				inland.setTransportationCountry(msgDecln.getShpDeclSecInput()
						.getTrptCtryCd());
				// EI20140624:
				// inland.setPlaceOfLoadingCode(msgDecln.getShpDeclSecInput().getPlaLdgCd());
				inland.setPlaceOfLoadingCode(msgDecln.getShpDeclSecInput()
						.getGdsLocCd()); // EI20140624 JIRA KCX293
				message.setTransportMeansInland(inland);
			}

			if (!Utils.isStringEmpty(msgDecln.getShpDeclSecInput().getTrptId())
					|| !Utils.isStringEmpty(msgDecln.getShpDeclSecInput()
							.getTrptModCd())) {
				TransportMeans border = new TransportMeans();
				border.setTransportMode(msgDecln.getShpDeclSecInput()
						.getTrptModCd());
				border.setTransportationNumber(msgDecln.getShpDeclSecInput()
						.getTrptId());
				// if
				// (!Utils.isStringEmpty(msgDecln.getDeclSecInput().getCountryCd()))
				// {
				// border.setTransportationCountry(msgDecln.getDeclSecInput().getCountryCd());
				// //TODO-Max
				// }
				/*
				 * joel Country: All Issue ID 92 Version 36
				 */
				border.setTransportationNumber(msgDecln.getDeclSecInput()
						.getRouteNbr());

				message.setTransportMeansBorder(border);
			}

			Business business = new Business();
			business.setInvoicePrice(msgDecln.getShpDeclSecInput()
					.getInvTotAmt());
			business.setCurrency(msgDecln.getShpDeclSecInput().getInvCurCd());

			/*
			 * joel ignore the value from the Fedex Declaration field
			 * <INV_CUR_CD> and always set it to EUR in the KIDS declaration
			 */
			if ("IE".equals(this.kidsHeader.getCountryCode())) {
				business.setCurrency("EUR");
			}

			if (!Utils.isStringEmpty(msgDecln.getShpDeclSecInput()
					.getNatOfTrans())) {
				business.setBusinessTypeCode(msgDecln.getShpDeclSecInput()
						.getNatOfTrans());
			} else {
				// business.setBusinessTypeCode("11"); //EI20140624 JIRA KCX293
				String country = this.kidsHeader.getCountryCode();
				if (country.equals("SE")) { // EI20140624 JIRA KCX294
					business.setBusinessTypeCode("9");
				} else if (country.equals("DK") || country.equals("FI")
						|| country.equals("IE")) {
					business.setBusinessTypeCode("11");
				} else {
					business.setBusinessTypeCode(""); // eigentlich weis ich gar
														// nicht was hier kommen
														// soll
				}
			}
			/*
			 * EI20140624 auskommentiert und ersetzt durch "11" if
			 * (this.kidsHeader.getCountryCode() != null) { if
			 * (this.kidsHeader.getCountryCode().equals("SE") ||
			 * this.kidsHeader.getCountryCode().equals("FI")) {
			 * business.setBusinessTypeCode("3"); } else if
			 * (this.kidsHeader.getCountryCode().equals("DK")) {
			 * business.setBusinessTypeCode(""); } }
			 */
			message.setBusiness(business);

			if (!Utils.isStringEmpty(msgDecln.getShpDeclSecInput()
					.getIncotermCd())) {
				IncoTerms incoterms = new IncoTerms();
				incoterms.setIncoTerm(msgDecln.getShpDeclSecInput()
						.getIncotermCd());
				incoterms.setPlace(msgDecln.getShpDeclSecInput()
						.getIncotermCity());
				incoterms.setLocationCode(msgDecln.getShpDeclSecInput()
						.getIncotermLocCd());
				message.setIncoTerms(incoterms);
			}
			if (msgDecln.getShpDeclSecInput()
					.getShpDeclnAdditionalInfoSecInput() != null
					&& msgDecln.getShpDeclSecInput()
							.getShpDeclnAdditionalInfoSecInput()
							.getItemShpDeclnAdditionalInfoList() != null) {
				ArrayList<AdditionalInfo> infoList = msgDecln
						.getShpDeclSecInput()
						.getShpDeclnAdditionalInfoSecInput()
						.getItemShpDeclnAdditionalInfoList();
				for (AdditionalInfo info : infoList) {
					Text text = new Text();
					text.setCode(info.getAiStmt());
					text.setText(info.getAiStmtTxt());
					message.addAddInfoStatementList(text);
				}
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
			 */
			if (!Utils.isStringEmpty(msgDecln.getShpDeclSecInput()
					.getGdsLocCd())) {
				message.setIntendedOfficeOfExit(msgDecln.getShpDeclSecInput()
						.getGdsLocCd());
			}

		}

		if (msgDecln.getDeclSecInput() != null) { // decltrep
			message.setReferenceNumber(msgDecln.getDeclSecInput()
					.getDeclnOidNbr()); // EI20140603
			if (Utils
					.isStringEmpty(msgDecln.getDeclSecInput().getDeclnOidNbr())) {
				message.setReferenceNumber(referenceNumber); // EI20140603
			}
			messageId = msgDecln.getDeclSecInput()
					.getSystemMessageReferenceNbr();
			// DeclnTypeCd: two first characters: CO,EU,EX,IM, third is
			// entrytype: FullDec,SFD,PSA,SuppDec
			// two: CO(M)=SpecialTerritiry, EU=EFTA/ImportorExport,
			// EX/IM=Export/Import when not EU or COM,
			// third: A=FullDec arrived, C=SFD,PSA arrived, D=FD,EFD preLogged,
			// F=SFD,PSA preLogged,G=CFSP transit arrived,
			// H=CFSP transit preLogged, J=C21 arrived,K=C21 preLogged,
			// Y=SDP+FSD, Z=LCP
			if (msgDecln.getDeclSecInput().getDeclnTypeCd() != null
					&& msgDecln.getDeclSecInput().getDeclnTypeCd().length() > 1) {
				message.setAreaCode(msgDecln.getDeclSecInput().getDeclnTypeCd()
						.substring(0, 2));
			}

			if (this.kidsHeader.getCountryCode() != null
					&& "IE".equals(this.kidsHeader.getCountryCode())) {
				message.setAreaCode("EUD");
			}

			/*
			 * EI2040611: JIRA KCX-281: jetzt wieder anders: immer "3"
			 * message.setTypeOfRepresentation
			 * (msgDecln.getDeclSecInput().getDecltRep()); //EI20140528 if
			 * (Utils.isStringEmpty(msgDecln.getDeclSecInput().getDecltRep())) {
			 * message.setTypeOfRepresentation("3"); }
			 */
			message.setTypeOfRepresentation("3"); // EI2040611

			message.setTypeOfPermit("A"); // EI20140624 JIRA KCX293
			message.setTransportInContainer("0"); // EI20140624 JIRA KCX293

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

			message.setShipmentNumber(msgDecln.getDeclSecInput().getRouteNbr());

			if (!Utils.isStringEmpty(msgDecln.getDeclSecInput().getEmpNm())
					|| !Utils.isStringEmpty(msgDecln.getDeclSecInput()
							.getEmpId())) {
				ContactPerson cp = new ContactPerson();
				cp.setIdentity(msgDecln.getDeclSecInput().getEmpId());
				cp.setName(msgDecln.getDeclSecInput().getEmpNm());
				cp.setClerk(msgDecln.getDeclSecInput().getEmpNm());
				message.setContact(cp);
			}
		}

		// joel set Additional Documents
		// Scheduled Time of Departure (Code 1D23)
		/*
		 * joel
		 * 
		 * Ticket 16975 ID 71 Import/Export Version 28
		 * ExportDeclaration/GoodsDeclaration/AddInfoStatement
		 */

		List<Text> addInfoStatementList = new ArrayList<Text>();
		Text text = new Text();
		text.setCode("1D23");

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

		text.setText(dateFormatTarget.format(dateSource));

		addInfoStatementList.add(text);
		message.setAddInfoStatementList(addInfoStatementList);

		if (msgDecln.getDeclCustomerSecInput() != null) {
			ArrayList<DeclnCust> list = msgDecln.getDeclCustomerSecInput()
					.getDeclnCustList();

			if (list != null && !list.isEmpty()) {
				for (DeclnCust customer : list) {

					if (customer != null && customer.getCustomerType() != null) {
						Party party = new Party();
						party.setVATNumber(customer.getVatNbr());

						Address address = this.mapFedexDeclnToAddress(customer);

						/*
						 * joel Ticket 17007 KCX IE Mapping issue ID 85
						 * Import/Export - FedEX/Kids Mapping Version 33 TODO
						 * 
						 * Export:
						 * ExportDeclaration/GoodsDeclaration/ConsignorTIN/TIN
						 * ExportDeclaration/GoodsDeclaration/AgentTIN/TIN
						 * ExportDeclaration/GoodsDeclaration/ConsigneeTIN/TIN
						 */
						if (!address.isEmpty()) {
							party.setAddress(address);
						}

						TIN tin = new TIN();
						if (!Utils.isStringEmpty(customer.getVatNbr())) {

							tin.setTin(customer.getVatNbr());

						}
						party.setPartyTIN(tin);

						if (!Utils.isStringEmpty(customer.getName())) {
							ContactPerson contact = new ContactPerson();
							contact.setName(customer.getName());
							contact.setClerk(customer.getName());
							party.setContactPerson(contact);
						}
						//
						if (customer.getCustomerType().equalsIgnoreCase("CZ")) { // Consignor=Shipper
							TIN tinCustomer = new TIN();

							party.setPartyTIN(tinCustomer);

							if (this.kidsHeader.getCommonFieldsDTO() != null
									&& // EI20140603 JIRA KCX277
									!Utils.isStringEmpty(this.kidsHeader
											.getCommonFieldsDTO()
											.getEoriNumber())) {

								tinCustomer.setTin(this.kidsHeader
										.getCommonFieldsDTO().getEoriNumber());

							}

							if (this.kidsHeader.getCountryCode() != null
									&& "IE".equals(this.getKidsHeader()
											.getCountryCode())
									&& Utils.isStringEmpty(tinCustomer.getTin()))
								tinCustomer.setTin("NR");

							if (this.kidsHeader.getCountryCode() != null && // EI20140616
																			// JIRA-KCX285
									this.kidsHeader.getCountryCode().equals(
											"FI")) {
								party.setAddress(this.getConsignorAddressFi());
							}

							message.setConsignor(party);
						} else if (customer.getCustomerType().equalsIgnoreCase(
								"DT")) { // Declarant
							message.setDeclarant(party);
						} else if (customer.getCustomerType().equalsIgnoreCase(
								"CN")) { // Consignee

							if (party != null && party.getAddress() != null) { // EI20140528
								// EI20140703:
								// message.setDestinationCountry(party.getAddress().getCountry());
								party.getAddress().setCountry("US"); // EI20140703:
																		// Jira
																		// KCX295
							}

							if (this.kidsHeader.getCountryCode() != null
									&& "IE".equals(this.getKidsHeader()
											.getCountryCode())
									&& Utils.isStringEmpty(party.getPartyTIN()
											.getTin()))
								party.getPartyTIN().setTin("NR");

							message.setConsignee(party);
						} else if (customer.getCustomerType().equalsIgnoreCase(
								"WH")) { // Warehouse
							WareHouse warehouse = new WareHouse();
							// warehouse: WarehouseType,
							// WarehouseIdentification, WarehouseCountry,
							// warehouse.setAddress(address);
							// message.setWarehouse(warehouse);
						} else if (customer.getCustomerType().equalsIgnoreCase(
								"AG")) { // Agent

							if (this.kidsHeader.getCountryCode() != null
									&& "IE".equals(this.getKidsHeader()
											.getCountryCode())
									&& Utils.isStringEmpty(party.getPartyTIN()
											.getTin()))
								party.getPartyTIN().setTin("NR");

							message.setAgent(party);
							// message.setRepresented(party);
						} else if (customer.getCustomerType().equalsIgnoreCase(
								"IM")) { // Importeur
							// ???
						}
					}
				}
			}
		}

		// Shp_Decln_Sec_Input\Shp_Decln_Cmdty_Doc_Sec_Input\ITEM_SHP_DECLN_COMDTY_DOC
		// \HDR_DOC_CODE
		DocumentV20 document = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();

		cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);

		if (msgDecln.getShpDeclSecInput().getShpDeclnCmdtyDocSecInput() != null) {
			ArrayList<HdrDoc> listHdrDoc = msgDecln.getShpDeclSecInput()
					.getShpDeclnCmdtyDocSecInput()
					.getItemShpDeclnComdtyDocList();

			if (listHdrDoc != null) {

				// ArrayList<Document> documentList
				for (HdrDoc docHeader : listHdrDoc) {
					document = new DocumentV20();

					if (!Utils.isStringEmpty(docHeader.getHdrDocCode())) {
						document.setType(docHeader.getHdrDocCode());
					}
					if (!Utils.isStringEmpty(docHeader.getHdrDocRef())) {
						document.setReference(docHeader.getHdrDocRef());
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

		// GoodsItems:
		DecimalFormat dec2 = new DecimalFormat("##############0.00");
		dec2.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.US));
		BigDecimal total = new BigDecimal(0);
		if (msgDecln.getShpDeclCmdtySecInput() != null) {
			ArrayList<ItemShpDeclnComdty> list = msgDecln
					.getShpDeclCmdtySecInput().getItemShpDeclnComdtyList();
			ArrayList<PreviousDocumentV21> previousDocumentList = new ArrayList<PreviousDocumentV21>();

			for (ItemShpDeclnComdty cmdtyItem : list) {
				MsgExpDatPos item = this.mapFedexDeclnToGoodsItem(cmdtyItem);
				previousDocumentList.add(this
						.mapFedexDeclnToPreviousDocs(cmdtyItem));

				if (!Utils.isStringEmpty(item.getBusiness().getInvoicePrice()))
					total = total.add(new BigDecimal(item.getBusiness()
							.getInvoicePrice()));
				this.checkDocsExport(item);
				message.addGoodsItemList(item);
			}
			// <EXportDeclaration><GoodsDeclaration><Business><InvoicePrice>
			String value = dec2.format(total);
			message.getBusiness().setInvoicePrice(value);

			message.setPreviousDocumentList(previousDocumentList);
		}

	}

	private MsgExpDatPos mapFedexDeclnToGoodsItem(ItemShpDeclnComdty cmdtyItem) {
		if (cmdtyItem == null) {
			return null;
		}
		/*
		 * joel Ticket 16971 KCX IE Mapping issue ID 67 Export - FedEX/Kids
		 * Mapping Version 28
		 */
		if (!Utils.isStringEmpty(cmdtyItem.getLocCd())) {
			String auxStr = cmdtyItem.getLocCd();
			cmdtyItem.setLocCd(UtilsMapDeclarationFK.getLocation(auxStr));
		}
		if (cmdtyItem.getDeclnCmdtyTaxSecInput() != null
				&& !Utils.isStringEmpty(cmdtyItem.getDeclnCmdtyTaxSecInput()
						.getLocCd())) {
			String auxStr = cmdtyItem.getDeclnCmdtyTaxSecInput().getLocCd();
			cmdtyItem.getDeclnCmdtyTaxSecInput().setLocCd(
					UtilsMapDeclarationFK.getLocation(auxStr));
		}

		MsgExpDatPos item = new MsgExpDatPos();
		item.setItemNumber(cmdtyItem.getItemNbr());
		item.setDescription(cmdtyItem.getGoodsDesc());
		item.setOriginCountry(cmdtyItem.getItemOrgCtryCd());
		item.setNetMass(cmdtyItem.getItemNetWt());
		item.setGrossMass(cmdtyItem.getItemGrossWt());

		if (!Utils.isStringEmpty(cmdtyItem.getTaricCmdtyCd())
				|| !Utils.isStringEmpty(cmdtyItem.getTariffAddnCd1())
				|| !Utils.isStringEmpty(cmdtyItem.getTariffAddnCd2())
				|| !Utils.isStringEmpty(cmdtyItem.getTariffAddnCd3())) {
			CommodityCode commodityCode = new CommodityCode();

			/*
			 * joel
			 * 
			 * KCX IE Mapping issue ID 37 Export - FedEX/Kids Mapping Version 14
			 * 10-07-2014
			 */
			String auxTarifCode = cmdtyItem.getTaricCmdtyCd().substring(0, 8);

			commodityCode.setTarifCode(auxTarifCode);
			commodityCode.setAddition(cmdtyItem.getTariffAddnCd1());
			commodityCode.setAddition2(cmdtyItem.getTariffAddnCd2());
			commodityCode.setAddition3(cmdtyItem.getTariffAddnCd3());
			item.setCommodityCode(commodityCode);
		}

		if (!Utils.isStringEmpty(cmdtyItem.getCpcCd())) {
			ApprovedTreatment at = new ApprovedTreatment();
			at.setDeclared(cmdtyItem.getCpcCd());
			// TODO KB Need to find out what to do with remaining digits if
			// length > 4
			/*
			 * joel
			 * 
			 * TODO : FIX KCX IE Mapping issue ID 30 Export - FedEX/Kids Mapping
			 * Version 14 10-07-2014
			 */

			if (cmdtyItem.getCpcCd().length() > 3) {
				String auxDeclared = cmdtyItem.getCpcCd().substring(0, 2);
				at.setDeclared(auxDeclared);

				String auxPrevious = cmdtyItem.getCpcCd().substring(2, 4);
				at.setPrevious(auxPrevious);
			}
			/*
			 * joel Ticket 17005 KCX IE Mapping issue ID 82 Export - FedEX/Kids
			 * Mapping Version 31
			 */

			if (cmdtyItem.getCpcCd().length() > 4) {
				String auxNational = cmdtyItem.getCpcCd().substring(4, 7);
				at.setNational(auxNational);
			}
			item.setApprovedTreatment(at);
		}
		/*
		 * EI20140611: if (!Utils.isStringEmpty(cmdtyItem.getItemValue()) ||
		 * !Utils.isStringEmpty(cmdtyItem.getItemStatValue()) ||
		 * !Utils.isStringEmpty(cmdtyItem.getItemSuppUnits()) ||
		 * !Utils.isStringEmpty(cmdtyItem.getItemSuppUnitsCd())) {
		 */

		Statistic st = new Statistic();

		st.setValue(cmdtyItem.getItemValue());

		if (Utils.isStringEmpty(st.getValue())) { // EI20140605 JIRA KCX-279 if
													// empty or 0 then 1
			st.setValue("1");
		} else if (st.getValue().trim().equals("0")) {
			st.setValue("1");
		}

		st.setStatisticalValue(cmdtyItem.getItemStatValue());

		st.setAdditionalUnit(cmdtyItem.getItemSuppUnits());
		st.setAdditionalUnitCode(cmdtyItem.getItemSuppUnitsCd());
		item.setStatistic(st);

		// EI20140611: }

		// Consignee/Consignor are provided in header by Fedex and we will not
		// copy them to GoodsItem level either

		// Fedex seems to support only a single package per GoodsItem
		if (cmdtyItem.getPkgCount() != null || cmdtyItem.getPkgKind() != null
				|| cmdtyItem.getPkgMarks() != null) {
			Packages pack = new Packages();
			pack.setQuantity(cmdtyItem.getPkgCount());
			pack.setType(cmdtyItem.getPkgKind());
			if (Utils.isStringEmpty(cmdtyItem.getPkgKind())) { // EI20140603
				pack.setType("PK");
			}
			pack.setMarks(cmdtyItem.getPkgMarks());
			pack.setSequentialNumber("1");
			item.addPackagesList(pack);
		}
		// EI20140602: fixe Documente: JIRA KCX-276

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();

		cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		SimpleDateFormat dateFormatSource = new SimpleDateFormat("dd/MM/yyyy");

		// EI20140605: MK meint. die Reihenfolge
		// in JiraTicket war anders

		if (cmdtyItem.getShpHdrDoc() != null) {
			ArrayList<HdrDoc> list = cmdtyItem.getShpHdrDoc()
					.getItemShpDeclnComdtyDocList();
			if (list != null) {
				for (HdrDoc hdr : list) {
					if (hdr != null) {
						DocumentV20 doc = new DocumentV20();
						doc.setType(hdr.getHdrDocCode());
						doc.setReference(hdr.getHdrDocRef());
						/*
						 * joel Ticket 16928
						 * 
						 * KCX IE Mapping issue ID 34 - FedEX/Kids Mapping
						 * Version 20
						 */
						if (hdr.getDocDate() == null) {
							doc.setIssueDate(dateFormat.format(cal.getTime()));
						} else {
							doc.setIssueDate(hdr.getDocDate());
						}
						doc.setValue(hdr.getDocQty());
						doc.setReason(hdr.getHdrDocReasonCd());
						item.addDocumentList(doc);
					}
				}
			}
		}

		if (cmdtyItem.getShpDeclnAdditionalInfoSecInput() != null) {
			ArrayList<AdditionalInfo> list = cmdtyItem
					.getShpDeclnAdditionalInfoSecInput()
					.getItemShpDeclnAdditionalInfoList();
			if (list != null) {
				for (AdditionalInfo ai : list) {
					if (ai != null) {
						Text tx = new Text();
						tx.setText(ai.getAiStmtTxt());
						tx.setCode(ai.getAiStmt());
						item.addAddInfoStatementList(tx);
					}
				}
			}
		}

		/*
		 * joel
		 * 
		 * ID : 50
		 * 
		 * Issues Log Project: FedEx KCX POC Version 22 23-07-2014
		 */
		Business business = new Business();
		if (cmdtyItem.getItemLcncVal() != null
				&& !Utils.isStringEmpty(cmdtyItem.getItemLcncVal())) {

			business.setInvoicePrice(cmdtyItem.getItemLcncVal());

		}

		/*
		 * joel
		 * 
		 * <shp_Decln_Cmdty_Sec_input><ITEM_VALUE> to
		 * <ExportDeclaration><GoodsItem><Business><InvoicePrice>
		 */

		if (!Utils.isStringEmpty(cmdtyItem.getItemValue())) {

			business.setInvoicePrice(cmdtyItem.getItemValue());
		}

		if (!!Utils.isStringEmpty(message.getBusiness().getBusinessTypeCode())) {
			business.setBusinessTypeCode(message.getBusiness()
					.getBusinessTypeCode());
		}
		item.setBusiness(business);

		return item;
	}

	private PreviousDocumentV21 mapFedexDeclnToPreviousDocs(
			ItemShpDeclnComdty cmdtyItem) {
		PreviousDocumentV21 previousDocument = null;
		if (!Utils.isStringEmpty(cmdtyItem.getPrevDocClassCd())
				&& !Utils.isStringEmpty(cmdtyItem.getPrevDocTypCd())
				&& !Utils.isStringEmpty(cmdtyItem.getPrevDocRef())) {

			previousDocument = new PreviousDocumentV21();
			String pdClass = cmdtyItem.getPrevDocClassCd();
			String pdType = cmdtyItem.getPrevDocTypCd();
			String pdRef = cmdtyItem.getPrevDocRef();

			String auxPreviousDocumentNumber = pdClass + "-" + pdType + "-"
					+ pdRef;

			previousDocument.setReference(auxPreviousDocumentNumber);

		}
		return previousDocument;
	}

	private Address mapFedexDeclnToAddress(DeclnCust customer) {
		if (customer == null) {
			return null;
		}
		Address address = new Address();
		address.setName(customer.getCompanyName());
		address.setStreet(customer.getStreet());
		address.setCity(customer.getCity());
		address.setPostalCode(customer.getZip());
		address.setCountry(customer.getCountryCode());
		address.setCountrySubEntity(customer.getStateCode());

		return address;
	}

	private Address getConsignorAddressFi() { // EI20140616 JIRA-KCX285: nur
												// fuer KCX-TEST!!!
		Address address = null;

		address = new Address();
		address.setName("Viejä Oy");
		address.setStreet("Katuosoite");
		address.setCity("Kaupunki");
		address.setPostalCode("00000");
		address.setCountry("FI");

		return address;
	}

	private void checkDocsExport(MsgExpDatPos item) {
		SimpleDateFormat dateFormatSource = new SimpleDateFormat("dd/MM/yyyy");
		List<String> listAllTypesDocHeader = new ArrayList<String>();
		List<String> listAllTypesDocItem = new ArrayList<String>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();

		cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);

		if (item.getDocumentList() != null && item.getDocumentList().size() > 0) {
			for (DocumentV20 docs : item.getDocumentList()) {
				listAllTypesDocItem.add(docs.getType());
			}
		}

		if (message.getDocumentList() != null
				&& message.getDocumentList().size() > 0) {
			for (DocumentV20 docs : message.getDocumentList()) {
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
			DocumentV20 doc380 = new DocumentV20();
			if (msgDecln.getShpDeclSecInput() != null) {
				doc380.setReference(msgDecln.getShpDeclSecInput().getAwbNbr());
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
			DocumentV20 doc740 = new DocumentV20();

			if (msgDecln.getShpDeclSecInput() != null) {
				doc740.setReference(msgDecln.getShpDeclSecInput().getAwbNbr());

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

	public void setDefault() {
		MappingConverterFactory mapping = new MappingConverterFactory();
		MappingDefaultConverter defaultConverter = mapping
				.newConverter(this.kidsHeader.getCountryCode());
		if (defaultConverter != null)
			defaultConverter.setExportDefault(this);
	}

	public MsgExpDat getMessage() {
		return message;
	}

	public MsgDeclnInput getMsgDecln() {
		return msgDecln;
	}
}
