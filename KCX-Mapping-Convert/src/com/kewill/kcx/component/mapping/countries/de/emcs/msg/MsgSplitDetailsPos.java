package com.kewill.kcx.component.mapping.countries.de.emcs.msg;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.EmcsPackage;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.WineProduct;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module       : EMCS<br>
 * Created		: 27.07.2011<br>
 * Description  : Contains Message Structure with fields used in GoodsItems of SplitDetailsEAD 
 *              : IE825 / E_SPL_SUB.     
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class MsgSplitDetailsPos extends KCXMessage {
	private String  				itemNumber;
	private String  				exciseProductCode;
	private String  				cnCode;
	private String  				articleNumber;
	private Text					commercialDescription;
	private Text			        brandNameOfProducts;
	private String					fiscalMarkUsedFlag;
	private Text			        fiscalMark;
	private String					quantity;
	private String					density;
	private String					netWeight;
	private String					grossWeight;
	private List <EmcsPackage>		emcsPackageList;
	
	private enum EDeclarationPos {
		//KIDS						//UIDS
		ItemNumber,					BodyRecordUniqueReference,
		ExciseProductCode,			//same
		CnCode,						//same
		ArticleNumber,				//missing in UIDS
		CommercialDescription,		//same		
		BrandNameOfProducts,		//same
		FiscalMarkUsedFlag,			//same
		FiscalMark,					//same
		Quantity,					//same		
		Density,					//same
		NetWeight,					//same
		GrossWeight,				//same
		Package;					//same		
	}
	
	public MsgSplitDetailsPos() {
		super();
	}

	public MsgSplitDetailsPos(XmlMsgScanner scanner)  {
		super(scanner);
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {			
			switch ((EDeclarationPos) tag) {
			case Package:
				EmcsPackage emcsPackage = new EmcsPackage(getScanner());
				emcsPackage.parse(tag.name());
				if (emcsPackageList == null)  {
					emcsPackageList = new Vector <EmcsPackage>();
				}
				emcsPackageList.add(emcsPackage);
			break;
			
			
			default:
					return;
			}
		} else { 
			switch ((EDeclarationPos) tag) {
				case ItemNumber:
				case BodyRecordUniqueReference:
					setItemNumber(value);
					break;
				
				case ExciseProductCode:
					setExciseProductCode(value);
					break;
				
				case CnCode:
					setCnCode(value);
					break;
				
				case ArticleNumber:
					setArticleNumber(value);
					break;

				case CommercialDescription:				
					//commercialDescription = new Text(value, attr.getValue("language"));
					commercialDescription = new Text(value, attr);  //EI20110926
					break;
								
				case BrandNameOfProducts:
					//brandNameOfProducts = new Text(value, attr.getValue("language"));
					brandNameOfProducts = new Text(value, attr);  //EI20110926					
					break;
				
				case FiscalMarkUsedFlag:
					setFiscalMarkUsedFlag(value);
					break;					
					
				case FiscalMark:
					//fiscalMark = new Text(value, attr.getValue("language"));
					fiscalMark = new Text(value, attr);  //EI20110926
					break;					

				case Quantity:
					setQuantity(value);
					break;	
									
				case Density:
					setDensity(value);
					break;	
					
				case NetWeight:
					setNetWeight(value);
					break;                       
					
				case GrossWeight:
					setGrossWeight(value);
					break;                       
				default:
					break;
			}
		}
		
	}

	public void stoppElement(Enum tag) {
	}

	public Enum translate(String token) {
		try {
			return EDeclarationPos.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getItemNumber() {
		return itemNumber;	
	}
	public void setItemNumber(String itemNumber) {
		this.itemNumber = Utils.checkNull(itemNumber);
	}

	public String getExciseProductCode() {
		return exciseProductCode;
	
	}
	public void setExciseProductCode(String exciseProductCode) {
		this.exciseProductCode = Utils.checkNull(exciseProductCode);
	}

	public String getCnCode() {
		return cnCode;	
	}
	public void setCnCode(String cnCode) {
		this.cnCode = Utils.checkNull(cnCode);
	}

	public String getArticleNumber() {
		return articleNumber;	
	}
	public void setArticleNumber(String articleNumber) {
		this.articleNumber = Utils.checkNull(articleNumber);
	}

	public Text getCommercialDescription() {
		return commercialDescription;	
	}
	public void setCommercialDescription(Text commercialDescription) {
		this.commercialDescription = commercialDescription;
	}

	public Text getBrandNameOfProducts() {
		return brandNameOfProducts;	
	}
	public void setBrandNameOfProducts(Text brandNameOfProducts) {
		this.brandNameOfProducts = brandNameOfProducts;
	}

	public String getFiscalMarkUsedFlag() {
		return fiscalMarkUsedFlag;	
	}
	public void setFiscalMarkUsedFlag(String fiscalMarkUsedFlag) {
		this.fiscalMarkUsedFlag = fiscalMarkUsedFlag;
	}

	public Text getFiscalMark() {
		return fiscalMark;	
	}
	public void setFiscalMark(Text fiscalMark) {
		this.fiscalMark = fiscalMark;
	}

	public String getQuantity() {
		return quantity;	
	}
	public void setQuantity(String quantity) {
		this.quantity = Utils.checkNull(quantity);
	}

	public String getDensity() {
		return density;	
	}
	public void setDensity(String density) {
		this.density = Utils.checkNull(density);
	}

	public String getNetWeight() {
		return netWeight;	
	}
	public void setNetWeight(String netWeight) {
		this.netWeight = Utils.checkNull(netWeight);
	}

	public String getGrossWeight() {
		return grossWeight;	
	}
	public void setGrossWeight(String grossWeight) {
		this.grossWeight = Utils.checkNull(grossWeight);
	}	

	public List<EmcsPackage> getPackageList() {
		return emcsPackageList;	
	}
	public void setPackageList(List<EmcsPackage> list) {
		this.emcsPackageList = list;
	}
}
