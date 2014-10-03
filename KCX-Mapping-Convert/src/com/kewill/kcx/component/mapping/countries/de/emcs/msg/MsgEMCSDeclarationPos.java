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
 * Created		: 03.05.2010<br>
 * Description  : Contains Message Structure with fields used in GoodsItems of EMCSDeclaration 
 *              : IE815 / N_EAD_SUB.     
 * 
 * @author krzoska
 * @version 1.0.00
 */

public class MsgEMCSDeclarationPos extends KCXMessage {
	private String  				itemNumber;
	private String  				exciseProductCode;
	private String  				cnCode;
	private String  				articleNumber;
	private Text					commercialDescription;
	private Text					designationOfOrigin;
	private Text			        brandNameOfProducts;
	private String					fiscalMarkUsedFlag;
	private Text			        fiscalMark;
	private String					quantity;
	private String					alcoholicStrength;
	private String					degreePlato;
	private String					sizeOfProducer;
	private String					density;
	private String					netWeight;
	private String					grossWeight;
	//private	EmcsPackage				emcsPackage;
	private List <EmcsPackage>		emcsPackageList;
	private WineProduct				wineProduct;

	private enum EDeclarationPosEMCS {
		//KIDS						//UIDS
		ItemNumber,					BodyRecordUniqueReference,
		ExciseProductCode,			//same
		CnCode,						//same
		ArticleNumber,				//missing in UIDS
		CommercialDescription,		//same
		DesignationOfOrigin,		//same
		BrandNameOfProducts,		//same
		FiscalMarkUsedFlag,			//same
		FiscalMark,					//same
		Quantity,					//same
		AlcoholicStrength,			//same
		DegreePlato,				//same
		SizeOfProducer,				//same
		Density,					//same
		NetWeight,					//same
		GrossWeight,				//same
		Package,					//same
		WineProduct;				//same
	}
	
	public MsgEMCSDeclarationPos() {
		super();
	}

	public MsgEMCSDeclarationPos(XmlMsgScanner scanner)  {
		super(scanner);
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {			
			switch ((EDeclarationPosEMCS) tag) {
			case Package:
				EmcsPackage emcsPackage = new EmcsPackage(getScanner());
				emcsPackage.parse(tag.name());
				if (emcsPackageList == null)  {
					emcsPackageList = new Vector <EmcsPackage>();
				}
				emcsPackageList.add(emcsPackage);
			break;
			
			case WineProduct:
				wineProduct = new WineProduct(getScanner());
				wineProduct.parse(tag.name());
			break;
			
			default:
					return;
			}
		} else { 
			switch ((EDeclarationPosEMCS) tag) {
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
				
				case DesignationOfOrigin:
					//designationOfOrigin = new Text(value, attr.getValue("language"));
					designationOfOrigin = new Text(value, attr);  //EI20110926
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
					
				case AlcoholicStrength:
					setAlcoholicStrength(value);
					break;	
					
				case DegreePlato:
					setDegreePlato(value);
					break;	
					
				case SizeOfProducer:
					setSizeOfProducer(value);
					break;	
					
				case Density:
					setDensity(value);
					break;	
					
				case NetWeight:
					setNetWeight(value);
					break;                       //EI20100616
					
				case GrossWeight:
					setGrossWeight(value);
					break;                       //EI20100616
				default:
					break;
			}
		}
		
	}

	public void stoppElement(Enum tag) {
	}

	public Enum translate(String token) {
		try {
			return EDeclarationPosEMCS.valueOf(token);
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

	public Text getDesignationOfOrigin() {
		return designationOfOrigin;	
	}
	public void setDesignationOfOrigin(Text designationOfOrigin) {
		this.designationOfOrigin = designationOfOrigin;
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

	public String getAlcoholicStrength() {
		return alcoholicStrength;	
	}
	public void setAlcoholicStrength(String alcoholicStrength) {
		this.alcoholicStrength = Utils.checkNull(alcoholicStrength);
	}

	public String getDegreePlato() {
		return degreePlato;	
	}
	public void setDegreePlato(String degreePlato) {
		this.degreePlato = Utils.checkNull(degreePlato);
	}

	public String getSizeOfProducer() {
		return sizeOfProducer;	
	}
	public void setSizeOfProducer(String sizeOfProducer) {
		this.sizeOfProducer = Utils.checkNull(sizeOfProducer);
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
	
	public WineProduct getWineProduct() {
		return wineProduct;	
	}
	public void setWineProduct(WineProduct wineProduct) {
		this.wineProduct = wineProduct;
	}

	public List<EmcsPackage> getEmcsPackageList() {
		return emcsPackageList;	
	}
	public void setEmcsPackageList(List<EmcsPackage> list) {
		this.emcsPackageList = list;
	}
}
