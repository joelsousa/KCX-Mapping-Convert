package com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.msg.common;

import javax.xml.stream.XMLEventReader;
import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module : FEDEX-Import.<br>
 * Created : 29.10.2013<br>
 * Description : Common class for MsgDeclnInput: Body Tags Of Declaration
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class ShpDeclnSecInput extends KCXMessage {

	private String shpmtDeclnOidNbr;
	private String declnOidNbr; // EI20140528
	private String plaUldgCd;
	private String shipmentOidNbr;
	private String natOfTrans;
	private String dutyBillToCd;
	private String dutyBillToAccNbr;
	private String trptCtryCd;
	private String commodityCount;
	private String grossWt;
	private String incotermCd;
	private String incotermCity;
	private String incotermLocCd;
	private String invTotAmt;
	private String invCurCd;
	private String localShipmentOidNbr;
	private String dispCtryCd; // Export
	private String totPkgNbr; // Export
	private String trptModInLndCd; // Export
	private String trptIdInLnd; // Export
	private String plaLdgCd; // Export
	private String gdsLocCd; // Export //EI20140624 JIRA KCX293
	private String trptModCd; // Export
	private String trptId; // Export
	private ShpDeclnCmdtyDocSecInput docSecInput;
	private ShpDeclnAdditionalInfoSecInput infoSecInput;
	private String awbNbr;
	private String inputDt;
	private String destLocationCd;

	public ShpDeclnSecInput() {
		super();
	}

	public ShpDeclnSecInput(XMLEventReader parser) {
		super(parser);
	}

	public ShpDeclnSecInput(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum EShpDeclnCmdtyDocSecInput {
		SHPMT_DECLN_OID_NBR, // NCTSDeclaration/ReferenceNumber <- jetzt nicht
								// mehr sondern aus DECLN_OID_NBR
		DECLN_OID_NBR, // EI20140528: NCTSDeclaration/ReferenceNumber
		AWB_NBR, SHIPMENT_OID_NBR, // ImportDeclaration/ReferenceNumber
		INPUT_DT, NAT_OF_TRANS, // ImportDeclaration/Business/BusinessTypeCode
		DUTY_BILL_TO_CD, // ImportDeclaration/Traders/DeclarantAddress
		DUTY_BILL_TO_ACCT_NBR, TRPT_CTRY_CD, // Body/ImportDeclaration/DispatchCountr
		COMMODITY_COUNT, // ImportDeclaration/TotalNumberPositions
		GROSS_WT, // ImportDeclaration/GrossMass
		INCOTERM_CD, // ImportDeclaration/IncoTerms/Code
		INCOTERM_CITY, // ImportDeclaration/IncoTerms/Place
		INCOTERM_LOC_CD, // ImportDeclaration/IncoTerms/LocationCode
		INV_TOT_AMT, // ImportDeclaration/Invoice/Amount
		INV_CUR_CD, // ImportDeclaration/Invoice/Currency
		LOCAL_SHIPMENT_OID_NBR, // ImportDeclaration/CustomerOrderNumber

		Shp_Decln_Cmdty_Doc_Sec_Input, Shp_Decln_Additional_Info_Sec_Input,

		DISP_CNTRY_CD, // Export
		TOT_PKG_NBR, // Export
		TRPT_MOD_IN_LND_CD, // Export
		TRPT_ID_IN_LND, // Export
		PLA_LDG_CD, // Export
		GDS_LOC_CD, // Export //EI20140624
		PLA_ULDG_CD, // NCTS
		TRPT_MOD_CD, // Export
		TRPT_ID, // Export
		DEST_LOCATION_CD,
	}

	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {

			switch ((EShpDeclnCmdtyDocSecInput) tag) {
			case Shp_Decln_Cmdty_Doc_Sec_Input:
				docSecInput = new ShpDeclnCmdtyDocSecInput(getScanner());
				docSecInput.parse(tag.name());
				break;
			case Shp_Decln_Additional_Info_Sec_Input:
				infoSecInput = new ShpDeclnAdditionalInfoSecInput(getScanner());
				infoSecInput.parse(tag.name());
				break;
			default:
				return;
			}
		} else {
			switch ((EShpDeclnCmdtyDocSecInput) tag) {
			case SHPMT_DECLN_OID_NBR:
				setShpmtDeclnOidNbr(value);
				break;
			case DECLN_OID_NBR:
				setDeclnOidNbr(value);
				break;
			case SHIPMENT_OID_NBR:
				setShipmentOidNbr(value);
				break;
			case NAT_OF_TRANS:
				setNatOfTrans(value);
				break;
			case DUTY_BILL_TO_CD:
				setDutyBillToCd(value);
				break;
			case DUTY_BILL_TO_ACCT_NBR:
				setDutyBillToAccNbr(value);
				break;
			case TRPT_CTRY_CD:
				setTrptCtryCd(value);
				break;
			case COMMODITY_COUNT:
				setCommodityCount(value);
				break;
			case GROSS_WT:
				setGrossWt(value);
				break;
			case INCOTERM_CD:
				setIncotermCd(value);
				break;
			case INCOTERM_CITY:
				setIncotermCity(value);
				break;
			case INCOTERM_LOC_CD:
				setIncotermLocCd(value);
				break;
			case INV_TOT_AMT:
				setInvTotAmt(value);
				break;
			case INV_CUR_CD:
				setInvCurCd(value);
				break;
			case LOCAL_SHIPMENT_OID_NBR:
				setLocalShipmentOidNbr(value);
				break;
			case DISP_CNTRY_CD:
				setDispCtryCd(value);
				break;
			case TRPT_MOD_IN_LND_CD:
				setTrptModInLndCd(value);
				break;
			case TRPT_ID_IN_LND:
				setTrptIdInLnd(value);
				break;
			case PLA_LDG_CD:
				setPlaLdgCd(value);
				break;
			case PLA_ULDG_CD:
				setPlaUldgCd(value);
				break;
			case TRPT_MOD_CD:
				setTrptModCd(value);
				break;
			case TRPT_ID:
				setTrptId(value);
				break;
			case TOT_PKG_NBR:
				setTotPkgNbr(value);
				break;
			case AWB_NBR:
				setAwbNbr(value);
				break;
			case INPUT_DT:
				setInputDt(value);
				break;
			case GDS_LOC_CD: // EI20140624
				setGdsLocCd(value);
				break;
				
			case DEST_LOCATION_CD:
				setDestLocationCd(value);
				break;	
				
			default:
				return;
			}
		}
	}

	@Override
	public void stoppElement(Enum tag) {
		// TODO Auto-generated method stub

	}

	public Enum translate(String token) {
		try {
			return EShpDeclnCmdtyDocSecInput.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getShipmentOidNbr() {
		return shipmentOidNbr;
	}

	public void setShipmentOidNbr(String shipmentOidNbr) {
		this.shipmentOidNbr = shipmentOidNbr;
	}

	public String getDutyBillToCd() {
		return dutyBillToCd;
	}

	public void setDutyBillToCd(String dutyBillToCd) {
		this.dutyBillToCd = dutyBillToCd;
	}

	public String getTrptCtryCd() {
		return trptCtryCd;
	}

	public void setTrptCtryCd(String trptCtryCd) {
		this.trptCtryCd = trptCtryCd;
	}

	public String getCommodityCount() {
		return commodityCount;
	}

	public void setCommodityCount(String commodityCount) {
		this.commodityCount = commodityCount;
	}

	public String getGrossWt() {
		return grossWt;
	}

	public void setGrossWt(String grossWt) {
		this.grossWt = grossWt;
	}

	public String getIncotermCd() {
		return incotermCd;
	}

	public void setIncotermCd(String incotermCd) {
		this.incotermCd = incotermCd;
	}

	public String getIncotermCity() {
		return incotermCity;
	}

	public void setIncotermCity(String incotermCity) {
		this.incotermCity = incotermCity;
	}

	public String getIncotermLocCd() {
		return incotermLocCd;
	}

	public void setIncotermLocCd(String incotermLocCd) {
		this.incotermLocCd = incotermLocCd;
	}

	public String getInvTotAmt() {
		return invTotAmt;
	}

	public void setInvTotAmt(String invTotAmt) {
		this.invTotAmt = invTotAmt;
	}

	public String getInvCurCd() {
		return invCurCd;
	}

	public void setInvCurCd(String incCurCd) {
		this.invCurCd = incCurCd;
	}

	public String getLocalShipmentOidNbr() {
		return localShipmentOidNbr;
	}

	public void setLocalShipmentOidNbr(String localShipmentOidNbr) {
		this.localShipmentOidNbr = localShipmentOidNbr;
	}

	public ShpDeclnCmdtyDocSecInput getShpDeclnCmdtyDocSecInput() {
		return docSecInput;
	}

	public void setShpDeclnCmdtyDocSecInput(ShpDeclnCmdtyDocSecInput docInput) {
		this.docSecInput = docInput;
	}

	public ShpDeclnAdditionalInfoSecInput getShpDeclnAdditionalInfoSecInput() {
		return infoSecInput;
	}

	public void setShpDeclnAdditionalInfoSecInput(
			ShpDeclnAdditionalInfoSecInput infoSecInput) {
		this.infoSecInput = infoSecInput;
	}

	public String getNatOfTrans() {
		return natOfTrans;
	}

	public void setNatOfTrans(String natOfTrans) {
		this.natOfTrans = natOfTrans;
	}

	public String getDutyBillToAccNbr() {
		return dutyBillToAccNbr;
	}

	public void setDutyBillToAccNbr(String dutyBillToAccNbr) {
		this.dutyBillToAccNbr = dutyBillToAccNbr;
	}

	public String getDispCtryCd() {
		return dispCtryCd;
	}

	public void setDispCtryCd(String value) {
		this.dispCtryCd = value;
	}

	public String getTotPkgNbr() {
		return totPkgNbr;
	}

	public void setTotPkgNbr(String value) {
		this.totPkgNbr = value;
	}

	public String getTrptModInLndCd() {
		return trptModInLndCd;
	}

	public void setTrptModInLndCd(String value) {
		this.trptModInLndCd = value;
	}

	public String getTrptIdInLnd() {
		return trptIdInLnd;
	}

	public void setTrptIdInLnd(String value) {
		this.trptIdInLnd = value;
	}

	public String getPlaLdgCd() {
		return plaLdgCd;
	}

	public void setPlaLdgCd(String value) {
		this.plaLdgCd = value;
	}

	public String getTrptModCd() {
		return trptModCd;
	}

	public void setTrptModCd(String value) {
		this.trptModCd = value;
	}

	public String getTrptId() {
		return trptId;
	}

	public void setTrptId(String value) {
		this.trptId = value;
	}

	public String getShpmtDeclnOidNbr() {
		return shpmtDeclnOidNbr;
	}

	public void setShpmtDeclnOidNbr(String shpmtDeclnOidNbr) {
		this.shpmtDeclnOidNbr = shpmtDeclnOidNbr;
	}

	public String getDeclnOidNbr() {
		return declnOidNbr;
	}

	public void setDeclnOidNbr(String declnOidNbr) {
		this.declnOidNbr = declnOidNbr;
	}

	public String getPlaUldgCd() {
		return plaUldgCd;
	}

	public void setPlaUldgCd(String plaUldgCd) {
		this.plaUldgCd = plaUldgCd;
	}

	public String getAwbNbr() {
		return awbNbr;
	}

	public void setAwbNbr(String value) {
		awbNbr = value;
	}

	public String getInputDt() {
		return inputDt;
	}

	public void setInputDt(String value) {
		inputDt = value;
	}

	public String getGdsLocCd() { // EI20140624
		return gdsLocCd;
	}

	public void setGdsLocCd(String value) { // EI20140624
		gdsLocCd = value;
	}

	public boolean isEmpty() {
		return (Utils.isStringEmpty(shipmentOidNbr)
				&& Utils.isStringEmpty(natOfTrans) && Utils
					.isStringEmpty(localShipmentOidNbr));
		// usw.
	}

	public String getDestLocationCd() {
		return destLocationCd;
	}

	public void setDestLocationCd(String destLocationCd) {
		this.destLocationCd = destLocationCd;
	}
}
