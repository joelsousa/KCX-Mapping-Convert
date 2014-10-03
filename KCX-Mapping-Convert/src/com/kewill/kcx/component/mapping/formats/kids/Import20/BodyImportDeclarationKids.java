package com.kewill.kcx.component.mapping.formats.kids.Import20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.Import20.msg.MsgImportDeclaration;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.AdditionalCosts;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Completion;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Deferment;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Excise;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.GoodsItemDeclaration;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Manifest;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Salary;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.SpecialStatement;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageImport20;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module : Import 20<br>
 * Created : 09.02.2012<br>
 * Description : BodyImportDeclarationKids (for KidsToKids).
 * 
 * TODO KIDS not defined
 * 
 * @author iwaniuk
 * @version 2.0.00
 * 
 */
public class BodyImportDeclarationKids extends KidsMessageImport20 {

	private MsgImportDeclaration message;

	public BodyImportDeclarationKids(XMLStreamWriter writer) {
		this.writer = writer;
	}

	public MsgImportDeclaration getMessage() {
		return message;
	}

	public void setMessage(MsgImportDeclaration argument) {
		this.message = argument;
	}

	public void writeBody() {
		try {
			openElement("soap:Body");
			openElement("ImportDeclaration");
			openElement("GoodsDeclaration");

			writeElement("ReferenceNumber", message.getReferenceNumber());
			writeCodeElement("DeclarationPriorPresentation",
					message.getDeclarationPriorPresentation(), "KCX0001");
			// writeCodeElement("DeclarationPriorPresentation",
			// message.getDeclarationPriorPresentation(), "KCX0068");
			// //EI20120813

			/*
			 * joel
			 * 
			 * KCX IE Mapping issue ID 36 Import - Import Declaration Date
			 */
			writeDateTimeToString("DeclarationTime",
					message.getDeclarationTime());

			/*
			 * joel
			 * 
			 * change the fixed value code for the <AgentRepresentationCode>
			 * from 1 to 3
			 */
			if ("IE".equals(this.kidsHeader.getCountryCode())) {
				writeElement("AgentRepresentationCode", "3");
			} else {
				writeCodeElement("AgentRepresentationCode",
						message.getAgentRepresentationCode(), "KCX0046");
			}

			// writeCodeElement("AgentRepresentationCode",
			// message.getAgentRepresentationCode(), "KCX0069"); //EI20120813
			writeCodeElement("PreTaxDeductionCode",
					message.getPreTaxDeductionCode(), "KCX0001");
			// writeCodeElement("PreTaxDeductionCode",
			// message.getPreTaxDeductionCode(), "KCX0070"); //EI20120813
			writeCodeElement("TransportInContainer",
					message.getTransportInContainer(), "KCX0001");
			// writeCodeElement("TransportInContainer",
			// message.getTransportInContainer(), "KCX0071"); //EI20120813
			writeBusiness(message.getBusiness());

			if ("IE".equals(this.kidsHeader.getCountryCode())) {
				writeElement("PaymentType", message.getPaymentType());
			} else {
				writeCodeElement("PaymentType", message.getPaymentType(),
						"KCX0019");
			}
			// writeCodeElement("PaymentType", message.getPaymentType(),
			// "KCX0072"); //EI20120813

			writeElement("PlaceOfDeclaration", message.getPlaceOfDeclaration());
			writeElement("CustomsOfficeOfDeclaration",
					message.getCustomsOfficeOfDeclaration());
			writeElement("DeclarantIsConsignee",
					message.getDeclarantIsConsignee()); // EI20130611
			if (this.kidsHeader != null
					&& this.kidsHeader.getReceiver() != null
					&& this.kidsHeader.getReceiver().equalsIgnoreCase(
							"DE.TOLL.TST") && message.getTraders() != null) { // EI20130820
				if (message.getTraders().getDeclarantTIN() != null
						&& !Utils.isStringEmpty(message.getTraders()
								.getDeclarantTIN().getTIN())
						&& message.getTraders().getDeclarantAddress() != null
						&& !message.getTraders().getDeclarantAddress()
								.isEmpty()) {
					message.getTraders().setDeclarantAddress(null);
				}
				if (message.getTraders().getRepresentativeTIN() != null
						&& !Utils.isStringEmpty(message.getTraders()
								.getRepresentativeTIN().getTIN())
						&& message.getTraders().getRepresentativeAddress() != null
						&& !message.getTraders().getRepresentativeAddress()
								.isEmpty()) {
					message.getTraders().setRepresentativeAddress(null);
				}
				if (message.getTraders().getConsigneeTIN() != null
						&& !Utils.isStringEmpty(message.getTraders()
								.getConsigneeTIN().getTIN())
						&& message.getTraders().getConsigneeAddress() != null
						&& !message.getTraders().getConsigneeAddress()
								.isEmpty()) {
					message.getTraders().setConsigneeAddress(null);
				}
				if (message.getTraders().getConsignorTIN() != null
						&& !Utils.isStringEmpty(message.getTraders()
								.getConsignorTIN().getTIN())
						&& message.getTraders().getConsignorAddress() != null
						&& !message.getTraders().getConsignorAddress()
								.isEmpty()) {
					message.getTraders().setConsignorAddress(null);
				}
				if (message.getTraders().getCustomsValueDeclarantTIN() != null
						&& !Utils.isStringEmpty(message.getTraders()
								.getCustomsValueDeclarantTIN().getTIN())
						&& message.getTraders()
								.getCustomsValueDeclarantAddress() != null
						&& !message.getTraders()
								.getCustomsValueDeclarantAddress().isEmpty()) {
					message.getTraders().setCustomsValueDeclarantAddress(null);
				}
			}
			writeTraders(message.getTraders(), "EZA");
			writeElement("DispatchCountry", message.getDispatchCountry());
			writeElement("CustomsOfficeEntry", message.getCustomsOfficeEntry());
			writeElement("ImporterLocation", message.getImporterLocation());
			writeElement("GoodsLocation", message.getGoodsLocation());
			writeElement("DestinationCountry", message.getDestinationCountry()); // EI20131028
			writeElement("DestinationFederalState",
					message.getDestinationFederalState());
			writeElement("TaxOffice", message.getTaxOffice());
			/*
			 * joel
			 * 
			 * KCX IE Mapping Issue ID 39 Import - FedEX/Kids Mapping Version 14
			 * 
			 * AND
			 * 
			 * Issue 97 Version 36 Set default for KIDS <CustomsStatus> to IMY
			 * (simplified procedure). This should be done in
			 * MapImportDeclarationFK by setting message.setCustomsStatus("IMY")
			 * instead of doing so in BodyImportDeclarationKids
			 */
			if ("IE".equals(this.kidsHeader.getCountryCode())) {
				writeElement("CustomsStatus", message.getCustomsStatus());
			} else {

				writeCodeElement("CustomsStatus", message.getCustomsStatus(),
						"KCX0005");
			}
			// writeCodeElement("CustomsStatus", message.getCustomsStatus(),
			// "KCX0073"); //EI20120813
			writeCodeElement("StatisticalStatus",
					message.getStatisticalStatus(), "KCX9974");
			// EI20120813 - bei Aktivierung von der unteren Zeile muss
			// DB.kcx_codes.kcxcode_id geändert werden von kcx9974 auf kcx0074
			// writeCodeElement("StatisticalStatus",
			// message.getStatisticalStatus(), "KCX0074"); //EI20120813 - bei
			// Aktivierung ...
			writeElement("TotalNumberPositions",
					message.getTotalNumberPositions()); // EI20120308 new in xsd
			writeElement("GrossMass", message.getGrossMass());
			writePreviousDocument(message.getPreviousDocument());
			writeMeansOfTransport("Border", message.getMeansOfTransportBorder());
			writeMeansOfTransport("Inland", message.getMeansOfTransportInland());
			writeMeansOfTransport("Arrival",
					message.getMeansOfTransportArrival());
			writeContactPerson("", message.getContactPerson());
			writeIncoTerms(message.getIncoterms());
			writeInvoice(message.getInvoice());
			writeElement("WriteOffSumAType", message.getWriteOffSumAType());
			writeElement("WriteOffBonWarAvuvAuthNum",
					message.getWriteOffBonWarAvuvAuthNum());
			writeElement("WriteOffBonWarRefNum",
					message.getWriteOffBonWarRefNum());
			if (message.getManifestCompletionList() != null) { // EI20130724
				for (Manifest manifest : message.getManifestCompletionList()) {
					writeManifest(manifest);
				}
			} else if (message.getBondedWarehouseCompletionList() != null) {
				for (Completion warehouse : message
						.getBondedWarehouseCompletionList()) {
					writeBondedWarehouseCompletion(warehouse);
				}

			} else if (message.getInwardProcessingCompletionList() != null) {
				for (Completion inward : message
						.getInwardProcessingCompletionList()) {
					writeInwardProcessingCompletion(inward);
				}
			}
			writeElement("CustomerOrderNumber",
					message.getCustomerOrderNumber());
			if (message.getDocumentList() != null) {
				for (Document doc : message.getDocumentList()) {
					writeDocument(doc, "head");
				}
			}
			writeContainer(message.getContainer());

			if (message.getDefermentList() != null) {
				for (Deferment def : message.getDefermentList()) {
					writeDeferment(def);
				}
			}
			// writeElement("AdditionalInformation",
			// message.getAdditionalInformation());
			/*
			 * joel
			 * 
			 * Ticket 16975 KCX IE Mapping issue ID 71 Export - FedEX/Kids
			 * Mapping Version 28
			 */
			writeAdditionalInformation(message.getAddInfo());

			writeAdditionalInfoStatement(message.getAdditionalInfoStatement()); // EI20131028
			writeDV1(message.getDV1());

			if (message.getGoodsItemList() != null) {
				for (GoodsItemDeclaration item : message.getGoodsItemList()) {
					writeGoodsItem(item);
				}
			}

			closeElement();
			closeElement();
			closeElement();

		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	private void writeGoodsItem(GoodsItemDeclaration item)
			throws XMLStreamException {
		if (item == null) {
			return;
		}

		openElement("GoodsItem");
		writeElement("ItemNumber", item.getItemNumber());
		writeElement("Text", item.getText());
		writeCommodityCodeImport(item.getCommodityCode());
		writeElement("ProcedureCode", item.getProcedureCode());
		writeElement("DutyControlCode", item.getDutyControlCode());
		writeElement("SupplementaryText", item.getSupplementaryText());
		writeElement("Description", item.getDescription());
		writeElement("EAN", item.getEAN());
		writeElement("Amount", item.getAmount());
		writeCodeElement("UnitOfMeasurementAmount",
				item.getUnitOfMeasurementAmount(), "KCX0025");
		writeCodeElement("QualifierAmount", item.getQualifierAmount(),
				"KCX0025");
		writeElement("CountryOfOrigin", item.getCountryOfOrigin());
		writeElement("EUCode", item.getEUCode());
		writeQuota(item.getQuota());
		writeElement("PlaceOfIntroduction", item.getPlaceOfIntroduction());
		writeElement("PlaceOfDeparture", item.getPlaceOfDeparture());
		writeElement("NetMass", item.getNetMass());
		writeElement("GrossMass", item.getGrossMass());
		writeImportPackage(item.getImportPackage());
		writeElement("RequestedPrivilege", item.getRequestedPrivilege());
		writeElement("Condition", item.getCondition());
		writeElement("CustomsValue", item.getCustomsValue());
		writeElement("ImportTurnoverTax", item.getImportTurnoverTax());
		writeItemInvoice(item.getInvoice()); // EI20130601
		writePayment("NetPrice", item.getNetPrice());
		writePayment("IndirectPayment", item.getIndirectPayment());

		writeStatisticAndOther("Statistic", item.getStatistic());
		writeStatisticAndOther("GoodsQuantityCustoms",
				item.getGoodsQuantityCustoms());
		writeStatisticAndOther("GoodsQuantityAgriculturalDuty",
				item.getGoodsQuantityAgriculturalDuty());

		writeElement("ProcessingFeeValueIncrease",
				item.getProcessingFeeValueIncrease());
		writeElement("ExtraCostImportVAT", item.getExtraCostImportVAT());
		writeElement("TobaccoDutyCodeNumber", item.getTobaccoDutyCodeNumber());
		writePreference(item.getPreference());
		if (item.getDocumentList() != null) {
			for (Document doc : item.getDocumentList()) {
				writeDocument(doc, "item");
			}
		}
		if (item.getSalaryList() != null) {
			for (Salary sal : item.getSalaryList()) {
				writeSalary(sal);
			}
		}
		if (item.getExciseList() != null) {
			for (Excise exc : item.getExciseList()) {
				writetExcise(exc);
			}
		}
		if (item.getAdditionsDeductionsList() != null) {
			for (AdditionalCosts add : item.getAdditionsDeductionsList()) {
				writeAdditionalCosts(add);
			}
		}
		writeElement("AdditionsDeductionsDescription",
				item.getAdditionsDeductionsDescription());
		if (item.getReductionStatementList() != null) {
			for (SpecialStatement red : item.getReductionStatementList()) {
				writeSpecialStatement("ReductionStatement", red);
			}
		}
		if (item.getSpecialValueStatementList() != null) {
			for (SpecialStatement spe : item.getSpecialValueStatementList()) {
				writeSpecialStatement("SpecialValueStatement", spe);
			}
		}
		if (item.getSpecialCaseStatementList() != null) {
			for (SpecialStatement cas : item.getSpecialCaseStatementList()) {
				writeSpecialStatement("SpecialCaseStatement", cas);
			}
		}
		closeElement();
	}

}
