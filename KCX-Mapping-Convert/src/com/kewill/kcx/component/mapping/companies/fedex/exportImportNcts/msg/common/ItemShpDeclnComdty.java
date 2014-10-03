package com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module : FEDEX-Import.<br>
 * Created : 29.10.2013<br>
 * Description : Common class for MsgDeclnInput: GoodsItem
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class ItemShpDeclnComdty extends KCXMessage {

	private String itemNbr;
	private String goodsDesc;
	private String taricCmdtyCd;
	private String tariffAddnCd1;
	private String tariffAddnCd2;
	private String tariffAddnCd3;
	private String itemOrgCtryCd;
	private String itemNetWt;
	private String itemGrossWt;
	private String cpcCd;
	private String sequenceNbr;
	private String prefNbr;
	private String itemSuppUnits;
	private String itemSuppUnitsCd;
	private String itemStatValue;
	private String pkgCount;
	private String pkgKind;
	private String pkgMarks;
	private String itemValue;
	private String itemLcncVal;
	private String prevDocClassCd;
	private String prevDocTypCd;
	private String prevDocRef;
	private ShpDeclnCmdtyDocSecInput shpHdrDoc;
	private ShpDeclnAdditionalInfoSecInput addInfo;
	private String locCd;
	private DeclnCmdtyTaxSecInput declnCmdtyTaxSecInput;

	public String getItemLcncVal() {
		return itemLcncVal;
	}

	public void setItemLcncVal(String itemLcncVal) {
		this.itemLcncVal = itemLcncVal;
	}

	public ItemShpDeclnComdty() {
		super();
	}

	public ItemShpDeclnComdty(XMLEventReader parser) {
		super(parser);
	}

	public ItemShpDeclnComdty(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum EItemShpDeclnComdty {
		SEQUENCE_NBR, // GoodsItem/ItemNumber
		ITEM_NBR, // oder GoodsItem/ItemNumber
		GOODS_DESC, TARIC_CMDTY_CD, // GoodsItem/CommodityCode/TarifCode
		TARIFF_ADDN_CD_1, TARIFF_ADDN_CD_2, TARIFF_ADDN_CD_3, CPC_CD, // GoodsItem/ProcedureCode
		ITEM_ORG_CTRY_CD, // GoodsItem/CountryOfOrigin
		ITEM_NET_WT, // GoodsItem/NetMass
		ITEM_GROSS_WT, // GoodsItem/GrossMass
		PKG_COUNT, // GoodsItem/Package/Quantity
		PKG_KIND, // GoodsItem/Package/Type
		PKG_MARKS, // GoodsItem/Package/Marks
		ITEM_SUPP_UNITS, // GoodsItem/Statistic/AdditionalUnit
		ITEM_SUPP_UNITS_CD, // GoodsItem/Statistic/AdditionalUnitCode
		ITEM_STAT_VALUE, // GoodsItem/Statistic/Value
		ITEM_VALUE, // GoodsItem/Invoice/Amount
		ITEM_LCNC_VAL, // GoodsItem/Invoice/Amount
		PREV_DOC_CLASS_CD, PREV_DOC_TYP_CD, PREV_DOC_REF, 
		Shp_Decln_Cmdty_Doc_Sec_Input, 
		Shp_Decln_Additional_Info_Sec_Input, 
		PREF_NBR, LOC_CD,
		Decln_Cmdty_Tax_Sec_Input,
	}

	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {

			switch ((EItemShpDeclnComdty) tag) {
			case Shp_Decln_Cmdty_Doc_Sec_Input:
				shpHdrDoc = new ShpDeclnCmdtyDocSecInput(getScanner());
				shpHdrDoc.parse(tag.name());
				break;
			case Decln_Cmdty_Tax_Sec_Input:
				setDeclnCmdtyTaxSecInput(new DeclnCmdtyTaxSecInput(getScanner()));
				getDeclnCmdtyTaxSecInput().parse(tag.name());
				break;
			case Shp_Decln_Additional_Info_Sec_Input:
				addInfo = new ShpDeclnAdditionalInfoSecInput(getScanner());
				addInfo.parse(tag.name());
				break;
			default:
				return;
			}
		} else {
			switch ((EItemShpDeclnComdty) tag) {
			case SEQUENCE_NBR:
				setSequenceNbr(value);
				break;
			case ITEM_NBR:
				setItemNbr(value);
				break;
			case GOODS_DESC:
				setGoodsDesc(value);
				break;
			case TARIC_CMDTY_CD:
				setTaricCmdtyCd(value);
				break;
			case TARIFF_ADDN_CD_1:
				setTariffAddnCd1(value);
				break;
			case TARIFF_ADDN_CD_2:
				setTariffAddnCd2(value);
				break;
			case TARIFF_ADDN_CD_3:
				setTariffAddnCd3(value);
				break;
			case CPC_CD:
				setCpcCd(value);
				break;
			case ITEM_ORG_CTRY_CD:
				setItemOrgCtryCd(value);
				break;
			case ITEM_NET_WT:
				setItemNetWt(value);
				break;
			case ITEM_GROSS_WT:
				setItemGrossWt(value);
				break;
			case PKG_COUNT:
				setPkgCount(value);
				break;
			case PKG_KIND:
				setPkgKind(value);
				break;
			case PKG_MARKS:
				setPkgMarks(value);
				break;
			case ITEM_VALUE:
				setItemValue(value);
				break;
			case ITEM_LCNC_VAL:
				setItemLcncVal(value);
				break;
			case PREV_DOC_CLASS_CD:
				setPrevDocClassCd(value);
				break;
			case PREV_DOC_TYP_CD:
				setPrevDocTypCd(value);
				break;
			case PREV_DOC_REF:
				setPrevDocRef(value);
				break;
			case ITEM_SUPP_UNITS:
				setItemSuppUnits(value);
				break;
			case ITEM_SUPP_UNITS_CD:
				setItemSuppUnitsCd(value);
				break;
			case ITEM_STAT_VALUE:
				setItemStatValue(value);
				break;
			case PREF_NBR:
				setPrefNbr(value);
				break;
			case LOC_CD:
				setLocCd(value);
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
			return EItemShpDeclnComdty.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getSequenceNbr() {
		return sequenceNbr;
	}

	public void setSequenceNbr(String sequenceNbr) {
		this.sequenceNbr = sequenceNbr;
	}

	public String getItemNbr() {
		return itemNbr;
	}

	public void setItemNbr(String item) {
		this.itemNbr = item;
	}

	public String getTaricCmdtyCd() {
		return taricCmdtyCd;
	}

	public void setTaricCmdtyCd(String taricCmdtyCd) {
		this.taricCmdtyCd = taricCmdtyCd;
	}

	public String getTariffAddnCd1() {
		return tariffAddnCd1;
	}

	public void setTariffAddnCd1(String tariff) {
		this.tariffAddnCd1 = tariff;
	}

	public String getTariffAddnCd2() {
		return tariffAddnCd2;
	}

	public void setTariffAddnCd2(String tariff) {
		this.tariffAddnCd2 = tariff;
	}

	public String getTariffAddnCd3() {
		return tariffAddnCd3;
	}

	public void setTariffAddnCd3(String tariff) {
		this.tariffAddnCd3 = tariff;
	}

	public String getCpcCd() {
		return cpcCd;
	}

	public void setCpcCd(String cpcCd) {
		this.cpcCd = cpcCd;
	}

	public String getItemOrgCtryCd() {
		return itemOrgCtryCd;
	}

	public void setItemOrgCtryCd(String itemOrgCtryCd) {
		this.itemOrgCtryCd = itemOrgCtryCd;
	}

	public String getItemNetWt() {
		return itemNetWt;
	}

	public void setItemNetWt(String itemNetWt) {
		this.itemNetWt = itemNetWt;
	}

	public String getItemGrossWt() {
		return itemGrossWt;
	}

	public void setItemGrossWt(String itemGrossWt) {
		this.itemGrossWt = itemGrossWt;
	}

	public String getPkgCount() {
		return pkgCount;
	}

	public void setPkgCount(String pkgCount) {
		this.pkgCount = pkgCount;
	}

	public String getPkgKind() {
		return pkgKind;
	}

	public void setPkgKind(String pkgKind) {
		this.pkgKind = pkgKind;
	}

	public String getPkgMarks() {
		return pkgMarks;
	}

	public void setPkgMarks(String pkgMarks) {
		this.pkgMarks = pkgMarks;
	}

	public String getItemValue() {
		return itemValue;
	}

	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}

	public ShpDeclnCmdtyDocSecInput getShpHdrDoc() {
		return shpHdrDoc;
	}

	public void setShpHdrDoc(ShpDeclnCmdtyDocSecInput shpHdrDoc) {
		this.shpHdrDoc = shpHdrDoc;
	}

	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String value) {
		this.goodsDesc = value;
	}

	public String getItemSuppUnits() {
		return itemSuppUnits;
	}

	public void setItemSuppUnits(String itemSuppUnits) {
		this.itemSuppUnits = itemSuppUnits;
	}

	public String getItemSuppUnitsCd() {
		return itemSuppUnitsCd;
	}

	public void setItemSuppUnitsCd(String itemSuppUnitsCd) {
		this.itemSuppUnitsCd = itemSuppUnitsCd;
	}

	public String getItemStatValue() {
		return itemStatValue;
	}

	public void setItemStatValue(String itemStatValue) {
		this.itemStatValue = itemStatValue;
	}

	public ShpDeclnAdditionalInfoSecInput getShpDeclnAdditionalInfoSecInput() {
		return addInfo;
	}

	public void setShpDeclnAdditionalInfoSecInput(
			ShpDeclnAdditionalInfoSecInput addInfo) {
		this.addInfo = addInfo;
	}

	public String getPrevDocClassCd() {
		return prevDocClassCd;
	}

	public void setPrevDocClassCd(String prevDocClassCd) {
		this.prevDocClassCd = prevDocClassCd;
	}

	public String getPrevDocTypCd() {
		return prevDocTypCd;
	}

	public void setPrevDocTypCd(String prevDocTypCd) {
		this.prevDocTypCd = prevDocTypCd;
	}

	public String getPrevDocRef() {
		return prevDocRef;
	}

	public void setPrevDocRef(String prevDocRef) {
		this.prevDocRef = prevDocRef;
	}

	public String getPrefNbr() {
		return prefNbr;
	}

	public void setPrefNbr(String prefNbr) {
		this.prefNbr = prefNbr;
	}

	public String getLocCd() {
		return locCd;
	}

	public void setLocCd(String locCd) {
		this.locCd = locCd;
	}

	public DeclnCmdtyTaxSecInput getDeclnCmdtyTaxSecInput() {
		return declnCmdtyTaxSecInput;
	}

	public void setDeclnCmdtyTaxSecInput(DeclnCmdtyTaxSecInput declnCmdtyTaxSecInput) {
		this.declnCmdtyTaxSecInput = declnCmdtyTaxSecInput;
	}

	public boolean isEmpty() {
		return Utils.isStringEmpty(sequenceNbr)
				&& Utils.isStringEmpty(taricCmdtyCd)
				&& Utils.isStringEmpty(cpcCd)
				&& Utils.isStringEmpty(itemOrgCtryCd)
				&& Utils.isStringEmpty(itemNetWt)
				&& Utils.isStringEmpty(itemGrossWt)
				&& Utils.isStringEmpty(pkgCount)
				&& Utils.isStringEmpty(pkgKind)
				&& Utils.isStringEmpty(pkgMarks)
				&& Utils.isStringEmpty(itemValue)
				&& Utils.isStringEmpty(prevDocClassCd)
				&& Utils.isStringEmpty(prevDocTypCd)
				&& Utils.isStringEmpty(prevDocRef)
				&& Utils.isStringEmpty(prefNbr)
				&& (shpHdrDoc == null || shpHdrDoc.isEmpty());
	}
}
