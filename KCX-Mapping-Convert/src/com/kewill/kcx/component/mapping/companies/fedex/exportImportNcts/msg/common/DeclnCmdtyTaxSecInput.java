package com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module : FEDEX-Export.<br>
 * Created : 16.12.2013<br>
 * Description : Common class for MsgDeclnInput: List of AdditionalInfo
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class DeclnCmdtyTaxSecInput extends KCXMessage {

	private String shpmtDeclnCommOidNbr;
	private String declnCommodityTaxOidNbr;
	private String commodityOidNbr;
	private String locSeqNbr;
	private String locCd;
	private String taxRateSeqNbr;
	private String itlnDeclTaxAmt;
	private String ttyCd;
	private String localEntryTaxCd;
	private String localTaxCdType;
	private String ttyOvrCd;
	private String addlCd;
	private String taxRateId;
	private String paymentMethodCd;
	private String itlnBaseAmt;
	private String itlnBaseQty;
	private String itlnBaseQtyCd;
	private String itlnChgeTaxAmt;
	private String fnlAdjAmt;
	private String fnlChrgAmt;
	private String tariffRateOidNbr;
	private String tariffRateSeqNbr;
	private String taxOverruleOidNbr;
	private String agriTaxCdOidNbr;

	public DeclnCmdtyTaxSecInput() {
		super();
	}

	public DeclnCmdtyTaxSecInput(XMLEventReader parser) {
		super(parser);
	}

	public DeclnCmdtyTaxSecInput(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum EDeclnCmdtyTaxSecInput {
		SHPMT_DECLN_COMM_OID_NBR,
		 DECLN_COMMODITY_TAX_OID_NBR,
		 COMMODITY_OID_NBR,
		 LOC_SEQ_NBR,
		 LOC_CD,
		 TAX_RATE_SEQ_NBR,
		 ITLN_DECL_TAX_AMT,
		 TTY_CD,
		 LOCAL_ENTRY_TAX_CD,
		 LOCAL_TAX_CD_TYPE,
		 TTY_OVR_CD,
		 ADDL_CD,
		 TAX_RATE_ID,
		 PAYMENT_METHOD_CD,
		 ITLN_BASE_AMT,
		 ITLN_BASE_QTY,
		 ITLN_BASE_QTY_CD,
		 ITLN_CHGE_TAX_AMT,
		 FNL_ADJ_AMT,
		 FNL_CHRG_AMT,
		 TARIFF_RATE_OID_NBR,
		 TARIFF_RATE_SEQ_NBR,
		 TAX_OVERRULE_OID_NBR,
		 AGRI_TAX_CD_OID_NBR,

	}

	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {

			switch ((EDeclnCmdtyTaxSecInput) tag) {
				
			default:
				return;
			}
		} else {
			switch ((EDeclnCmdtyTaxSecInput) tag) {
			
			case SHPMT_DECLN_COMM_OID_NBR:
				setShpmtDeclnCommOidNbr(value);
				break;
			case DECLN_COMMODITY_TAX_OID_NBR:
				setDeclnCommodityTaxOidNbr(value);
				break;
			case COMMODITY_OID_NBR:
				setCommodityOidNbr(value);
				break;
			case LOC_SEQ_NBR:
				setLocSeqNbr(value);
				break;
			case LOC_CD:
				setLocCd(value);
				break;
			case TAX_RATE_SEQ_NBR:
				setTaxRateSeqNbr(value);
				break;
			case ITLN_DECL_TAX_AMT:
				setItlnDeclTaxAmt(value);
				break;
			case TTY_CD:
				setTtyCd(value);
				break;
			case LOCAL_ENTRY_TAX_CD:
				setLocalEntryTaxCd(value);
				break;
			case LOCAL_TAX_CD_TYPE:
				setLocalTaxCdType(value);
				break;
			case TTY_OVR_CD:
				setTtyOvrCd(value);
				break;
			case ADDL_CD:
				setAddlCd(value);				
				break;
			case TAX_RATE_ID:
				setTaxRateId(value);
				break;
			case PAYMENT_METHOD_CD:
				setPaymentMethodCd(value);				
				break;
			case ITLN_BASE_AMT:
				setItlnBaseAmt(value);
				break;
			case ITLN_BASE_QTY:
				setItlnBaseQty(value);
				break;
			case ITLN_BASE_QTY_CD:
				setItlnBaseQtyCd(value);
				break;
			case ITLN_CHGE_TAX_AMT:
				setItlnChgeTaxAmt(value);
				break;
			case FNL_ADJ_AMT:
				setFnlAdjAmt(value);
				break;
			case FNL_CHRG_AMT:
				setFnlChrgAmt(value);
				break;
			case TARIFF_RATE_OID_NBR:
				setTariffRateOidNbr(value);
				break;
			case TARIFF_RATE_SEQ_NBR:
				setTariffRateSeqNbr(value);
				break;
			case TAX_OVERRULE_OID_NBR:
				setTaxOverruleOidNbr(value);
				break;
			case AGRI_TAX_CD_OID_NBR:
				setAgriTaxCdOidNbr(value);
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
			return EDeclnCmdtyTaxSecInput.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public boolean isEmpty() {
		return (
				Utils.isStringEmpty(shpmtDeclnCommOidNbr) && 
				Utils.isStringEmpty(declnCommodityTaxOidNbr) && 
				Utils.isStringEmpty(commodityOidNbr) && 
				Utils.isStringEmpty(locSeqNbr) && 
				Utils.isStringEmpty(locCd) && 
				Utils.isStringEmpty(taxRateSeqNbr) && 
				Utils.isStringEmpty(itlnDeclTaxAmt) && 
				Utils.isStringEmpty(ttyCd) && 
				Utils.isStringEmpty(localEntryTaxCd) && 
				Utils.isStringEmpty(localTaxCdType) && 
				Utils.isStringEmpty(ttyOvrCd) && 
				Utils.isStringEmpty(addlCd) && 
				Utils.isStringEmpty(taxRateId) && 
				Utils.isStringEmpty(paymentMethodCd) && 
				Utils.isStringEmpty(itlnBaseAmt) && 
				Utils.isStringEmpty(itlnBaseQty) && 
				Utils.isStringEmpty(itlnBaseQtyCd) && 
				Utils.isStringEmpty(itlnChgeTaxAmt) && 
				Utils.isStringEmpty(fnlAdjAmt) && 
				Utils.isStringEmpty(fnlChrgAmt) && 
				Utils.isStringEmpty(tariffRateOidNbr) && 
				Utils.isStringEmpty(tariffRateSeqNbr) && 
				Utils.isStringEmpty(taxOverruleOidNbr) && 
				Utils.isStringEmpty(agriTaxCdOidNbr) );
		
	}

	public String getShpmtDeclnCommOidNbr() {
		return shpmtDeclnCommOidNbr;
	}

	public void setShpmtDeclnCommOidNbr(String shpmtDeclnCommOidNbr) {
		this.shpmtDeclnCommOidNbr = shpmtDeclnCommOidNbr;
	}

	public String getDeclnCommodityTaxOidNbr() {
		return declnCommodityTaxOidNbr;
	}

	public void setDeclnCommodityTaxOidNbr(String declnCommodityTaxOidNbr) {
		this.declnCommodityTaxOidNbr = declnCommodityTaxOidNbr;
	}

	public String getCommodityOidNbr() {
		return commodityOidNbr;
	}

	public void setCommodityOidNbr(String commodityOidNbr) {
		this.commodityOidNbr = commodityOidNbr;
	}

	public String getLocSeqNbr() {
		return locSeqNbr;
	}

	public void setLocSeqNbr(String locSeqNbr) {
		this.locSeqNbr = locSeqNbr;
	}

	public String getLocCd() {
		return locCd;
	}

	public void setLocCd(String locCd) {
		this.locCd = locCd;
	}

	public String getTaxRateSeqNbr() {
		return taxRateSeqNbr;
	}

	public void setTaxRateSeqNbr(String taxRateSeqNbr) {
		this.taxRateSeqNbr = taxRateSeqNbr;
	}

	public String getItlnDeclTaxAmt() {
		return itlnDeclTaxAmt;
	}

	public void setItlnDeclTaxAmt(String itlnDeclTaxAmt) {
		this.itlnDeclTaxAmt = itlnDeclTaxAmt;
	}

	public String getTtyCd() {
		return ttyCd;
	}

	public void setTtyCd(String ttyCd) {
		this.ttyCd = ttyCd;
	}

	public String getLocalEntryTaxCd() {
		return localEntryTaxCd;
	}

	public void setLocalEntryTaxCd(String localEntryTaxCd) {
		this.localEntryTaxCd = localEntryTaxCd;
	}

	public String getLocalTaxCdType() {
		return localTaxCdType;
	}

	public void setLocalTaxCdType(String localTaxCdType) {
		this.localTaxCdType = localTaxCdType;
	}

	public String getTtyOvrCd() {
		return ttyOvrCd;
	}

	public void setTtyOvrCd(String ttyOvrCd) {
		this.ttyOvrCd = ttyOvrCd;
	}

	public String getAddlCd() {
		return addlCd;
	}

	public void setAddlCd(String addlCd) {
		this.addlCd = addlCd;
	}

	public String getTaxRateId() {
		return taxRateId;
	}

	public void setTaxRateId(String taxRateId) {
		this.taxRateId = taxRateId;
	}

	public String getPaymentMethodCd() {
		return paymentMethodCd;
	}

	public void setPaymentMethodCd(String paymentMethodCd) {
		this.paymentMethodCd = paymentMethodCd;
	}

	public String getItlnBaseAmt() {
		return itlnBaseAmt;
	}

	public void setItlnBaseAmt(String itlnBaseAmt) {
		this.itlnBaseAmt = itlnBaseAmt;
	}

	public String getItlnBaseQty() {
		return itlnBaseQty;
	}

	public void setItlnBaseQty(String itlnBaseQty) {
		this.itlnBaseQty = itlnBaseQty;
	}

	public String getItlnBaseQtyCd() {
		return itlnBaseQtyCd;
	}

	public void setItlnBaseQtyCd(String itlnBaseQtyCd) {
		this.itlnBaseQtyCd = itlnBaseQtyCd;
	}

	public String getItlnChgeTaxAmt() {
		return itlnChgeTaxAmt;
	}

	public void setItlnChgeTaxAmt(String itlnChgeTaxAmt) {
		this.itlnChgeTaxAmt = itlnChgeTaxAmt;
	}

	public String getFnlAdjAmt() {
		return fnlAdjAmt;
	}

	public void setFnlAdjAmt(String fnlAdjAmt) {
		this.fnlAdjAmt = fnlAdjAmt;
	}

	public String getFnlChrgAmt() {
		return fnlChrgAmt;
	}

	public void setFnlChrgAmt(String fnlChrgAmt) {
		this.fnlChrgAmt = fnlChrgAmt;
	}

	public String getTariffRateOidNbr() {
		return tariffRateOidNbr;
	}

	public void setTariffRateOidNbr(String tariffRateOidNbr) {
		this.tariffRateOidNbr = tariffRateOidNbr;
	}

	public String getTariffRateSeqNbr() {
		return tariffRateSeqNbr;
	}

	public void setTariffRateSeqNbr(String tariffRateSeqNbr) {
		this.tariffRateSeqNbr = tariffRateSeqNbr;
	}

	public String getTaxOverruleOidNbr() {
		return taxOverruleOidNbr;
	}

	public void setTaxOverruleOidNbr(String taxOverruleOidNbr) {
		this.taxOverruleOidNbr = taxOverruleOidNbr;
	}

	public String getAgriTaxCdOidNbr() {
		return agriTaxCdOidNbr;
	}

	public void setAgriTaxCdOidNbr(String agriTaxCdOidNbr) {
		this.agriTaxCdOidNbr = agriTaxCdOidNbr;
	}

}
