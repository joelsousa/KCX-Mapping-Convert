package com.kewill.kcx.component.mapping.formats.ireland;

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.Party;
//import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.GoodsItemDeclaration;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.countries.de.Import20.msg.MsgImportDeclaration;
import com.kewill.kcx.component.mapping.countries.de.Import20.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.countries.ie.IrelandMessage;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module : Import Kids to Ireland<br>
 * Created : 26.05.2014<br>
 * Description : Body of Ireland-Format of ImportDeclaration.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class BodyImportDeclarationIreland extends IrelandMessage {

	private MsgImportDeclaration message;
	private List<GoodsItemDeclaration> goodsItemList = null;

	public BodyImportDeclarationIreland(XMLStreamWriter writer) {
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
			openElement("v1:ImportCustomsDeclaration");
			openElement("v1:Declaration");
			writeElement("v1:TypeCode", message.getCustomsStatus());
			writeElement("v1:IEMessageSendingDate", this.getKidsHeader()
					.getSentAtDate());
			// writeElement("v1:CustomsReferenceIdentifier",
			// message.getUCRNumber());
			writeElement("v1:DeclarationOfficeIdentifier",
					message.getCustomsOfficeOfDeclaration());
			writeElement("v1:GoodsItemQuantity",
					message.getTotalNumberPositions());
			// if (message.getBusiness() != null) {
			// writeElement("v1:InvoiceAmount",
			// message.getBusiness().getInvoicePrice());
			if (message.getInvoice() != null) {
				writeElement("v1:InvoiceAmount", message.getInvoice()
						.getAmount());
			}

			openElement("v1:GoodsShipment");
			writeElement("v1:ExportationCountryIdentifier",
					message.getDispatchCountry());
			if (message.getBusiness() != null) {
				writeElement("v1:TransactionNatureCode", message.getBusiness()
						.getBusinessTypeCode());
			}
			// writeWarehouse(message.getWarehouse); //in Kids ist Warehouse in
			// GoodsItems
			openElement("v1:Ucr"); // ist ein MUSS-tag, daher ohne if()...
			writeElement("v1:TraderReferenceIdentifier",
					message.getReferenceNumber());
			closeElement();
			if (message.getIncoterms() != null) {
				writeTradeTerm(message.getIncoterms().getCode(), message
						.getIncoterms().getPlace(), message.getIncoterms()
						.getLocationCode());
			}
			if (Utils.isStringEmpty(message.getCustomsOfficeEntry())) {
				openElement("v1:EntryCustomsOffice");
				writeElement("v1:CodeIdentifier",
						message.getCustomsOfficeEntry());
				closeElement();
			}
			if (Utils.isStringEmpty(message.getDestinationCountry())) {
				openElement("v1:DeliveryDestination");
				writeElement("v1:CountryCode", message.getDestinationCountry());
				closeElement();
			}

			// TODO : joel AdditionalDocument
			/*
			 * Scheduled Time of Arrival (STA) (Code 1D24)
			 */
			if (message.getDocumentList() != null) {
				for (Document doc : message.getDocumentList()) {
					openElement("v1:AdditionalDocument");
					writeElement("v1:TypeCode", doc.getType());
					writeElement("v1:ReferenceIdentifier", doc.getNumber());
					closeElement();

				}
			}

			// GoodsItems
			if (message.getGoodsItemList() != null) {
				for (GoodsItemDeclaration item : message.getGoodsItemList()) {
					writeCustomsGoodsItem(item);
				}
			}

			if (message.getTraders() != null) {
				writePartyImport("v1:Consignor", message.getTraders()
						.getConsignorTIN(), message.getTraders()
						.getConsignorAddress(), "");
			}

			writeConsignmentImport(message.getMeansOfTransportArrival(),
					message.getMeansOfTransportBorder(),
					message.getMeansOfTransportInland(),
					message.getGoodsLocation(), message.getPreviousDocument());

			if (message.getTraders() != null) {
				writePartyImport("v1:Consignee", message.getTraders()
						.getConsigneeTIN(), message.getTraders()
						.getConsigneeAddress(), "");
			}

			writeElement("v1:IEMethodOfPaymentCode", message.getPaymentType());
			if (message.getDefermentList() != null
					&& message.getDefermentList().get(0) != null
					&& !Utils.isStringEmpty(message.getDefermentList().get(0)
							.getTIN())) {
				openElement("v1:IEPayer");
				writeElement("v1:IEPayerIdentifier", message.getDefermentList()
						.get(0).getTIN());
				closeElement();
			}
			writeAdditionalInformation("v1:",
					message.getAdditionalInformation());

			if (message.getDocumentList() != null) {
				for (Document doc : message.getDocumentList()) {
					if (doc != null) {
						writeAdditionalDocument(doc.getType(), doc.getNumber());
					}
				}
			}
			// in Max-Docu nicht gemapped: todo-iwa ?
			// writeElement("v1:TransportMethodOfPaymentCode",
			// message.getPaymentType());

			closeElement();

			// if (message.getBusiness() != null &&
			// !Utils.isStringEmpty(message.getBusiness().getCurrency())) {
			if (message.getInvoice() != null) {
				openElement("v1:CurrencyExchange");
				writeElement("v1:CurrencyTypeIdentifier", message.getInvoice()
						.getCurrency());
				closeElement();
			}
			if (message.getTraders() != null) {
				writePartyImport("v1:Agent", message.getTraders()
						.getRepresentativeTIN(), message.getTraders()
						.getRepresentativeAddress(),
						message.getAgentRepresentationCode());
			}

			closeElement();
			closeElement();

		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	private void writeCustomsGoodsItem(GoodsItemDeclaration item)
			throws XMLStreamException {
		if (item == null) {
			return;
		}
		openElement("v1:CustomsGoodsItem");

		if (item.getImportPackage() != null) {
			openElement("v1:GoodsPackaging");
			writeElement("v1:MarkNumberText", item.getImportPackage()
					.getMarks());
			writeElement("v1:QuantityQuantity", item.getImportPackage()
					.getQuantity());
			writeElement("v1:TypeCode", item.getImportPackage().getType());
			closeElement();
		}
		writeElement("v1:SequenceIdentifier", item.getItemNumber());
		if (!Utils.isStringEmpty(item.getDescription())
				|| item.getCommodityCode() != null) {
			openElement("v1:Commodity");
			writeElement("v1:DescriptionText", item.getDescription());
			if (item.getCommodityCode() != null) {
				writeElement("v1:TariffClassificationCode", item
						.getCommodityCode().getTarifCode());
				writeElement("v1:EUTariffClassificationAdd1Code", item
						.getCommodityCode().getTarifAddition1());
				writeElement("v1:EUTariffClassificationAdd2Code", item
						.getCommodityCode().getTarifAddition2());
				writeElement("v1:EUDutyRegimeCode", item.getDutyControlCode());
				if (item.getQuota() != null) {
					writeElement("v1:EUQuotaIdentifier", item.getQuota()
							.getNumber1());
				}
			}
			closeElement();
		}
		if (!Utils.isStringEmpty(item.getCountryOfOrigin())) {
			openElement("v1:Origin");
			writeElement("v1:OriginCountryIdentifier",
					item.getCountryOfOrigin());
			closeElement();
		}
		if (!Utils.isStringEmpty(item.getGrossMass())
				|| !Utils.isStringEmpty(item.getNetMass())) {
			openElement("v1:GoodsMeasure");
			writeElement("v1:GrossMassMeasure", item.getGrossMass());
			writeElement("v1:NetNetWeightMeasure", item.getNetMass());
			closeElement();
		}
		if (!Utils.isStringEmpty(item.getProcedureCode())) {
			openElement("v1:CustomsProcedure");
			writeElement("v1:CurrentCode", item.getProcedureCode());
			writeElement("v1:PreviousCode", item.getProcedureCode());
			closeElement();
		}

		writeElement("v1:EUItemPriceAmount", item.getAmount());
		if (item.getDocumentList() != null) {
			for (Document doc : item.getDocumentList()) {
				openElement("v1:AdditionalDocument");
				writeElement("v1:TypeCode", doc.getType());
				writeElement("v1:ReferenceIdentifier", doc.getNumber());
				closeElement();
			}
		}
		if (item.getAdditionsDeductionsList() != null
				&& item.getAdditionsDeductionsList().get(0) != null) {
			openElement("v1:ValuationAdjustment");
			writeElement("v1:AmountAmount", item.getAdditionsDeductionsList()
					.get(0).getAmount());
			closeElement();
		}
		if (item.getStatistic() != null) {
			writeElement("v1:StatisticalValueAmount", item.getStatistic()
					.getStatisticalValue());
		}

		// ??? im jeden GoodsItem alles vom kopf? todo-iwa
		if (message.getContainer() != null
				&& message.getContainer().getNumberList() != null) {
			for (String containerNumber : message.getContainer()
					.getNumberList()) {
				openElement("v1:TransportEquipment");
				openElement("v1:EquipmentIdentification");
				writeElement("v1:IdentificationIdentifier", containerNumber);
				closeElement();
				closeElement();
			}
		}

		closeElement();
	}

	private void writeConsignmentImport(TransportMeans arrival,
			TransportMeans border, TransportMeans inland, String location,
			PreviousDocument previousDocument) throws XMLStreamException {
		openElement("v1:Consignment");
		if (arrival != null
				&& !Utils.isStringEmpty(arrival.getTransportationNumber())) {
			openElement("v1:ArrivalTransportMeans");
			writeElement("v1:IdentificationText",
					arrival.getTransportationNumber());
			closeElement();
		}
		if (border != null
				&& (!Utils.isStringEmpty(border.getTransportationCountry()) || !Utils
						.isStringEmpty(border.getTransportMode()))) {
			openElement("v1:BorderTransportMeans");
			writeElement("v1:RegistrationNationalityIdentifier",
					border.getTransportationCountry());
			writeElement("v1:ModeAndTypeCode", border.getTransportMode());
			closeElement();
		}
		if (inland != null) {
			writeElement("v1:IEInlandModeOfTransportCode",
					inland.getTransportMode());
		}
		if (!Utils.isStringEmpty(location)) {
			openElement("v1:GoodsLocation");
			writeElement("v1:LocationIdentifier", location);
			closeElement();
		}
		if (previousDocument != null) {
			writeElement("v1:IESummaryDeclarationIdentifier",
					previousDocument.getNumber());
		}
		closeElement();
	}

	private void writePartyImport(String name, TIN traderTin, Address address,
			String type) throws XMLStreamException {
		String tin = "";
		if (traderTin != null) {
			tin = traderTin.getTIN();
		}
		writeParty("v1:Consignor", tin, address, type);
	}
}