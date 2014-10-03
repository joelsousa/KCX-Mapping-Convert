package com.kewill.kcx.component.mapping.countries.de.ncts.msg;

import java.util.List;
import java.util.Vector;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.SpecialMention;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.CommodityCode;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Container;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Guarantee;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Packages;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.PartyNCTS;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.SensitiveGoods;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.WriteOff;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.WriteOffInfo;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.WriteOffSumA;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: MsgNCTSDeclarationPos
 * Created		: 02.09.2010
 * Description	: contains message structure with fields used for
 * 				:	GoodsItem for NCTSDeclaration.
 * 
 * @author	: Michelle Bauza
 * @version	: 1.0.00
 *
 */
public class MsgNCTSDeclarationPos extends KCXMessage {
	private String			msgName;
	private String			itemNumber;
	private String			typeOfDeclaration;
	private CommodityCode	commodityCode;			// validate
	private String			description;
	private String			articleNumber;
	private String			grossMass;
	private String			netMass;
	private String			dispatchCountry;
	private String			destinationCountry;
	private List< PreviousDocument >	previousDocumentList;
	private List< Document >			documentList;
	private List< SpecialMention >		specialMentionList;
	private Container		container;
	private List< Packages >		packagesList;
	private List< SensitiveGoods >	sensitiveGoodsList;
	private List< Guarantee >		guaranteeList;
	private PartyNCTS			consignee;		//consigneeTIN
	private TIN				consigneeTIN;			
	private PartyNCTS			consignor;		//consignorTIN
	private TIN				consignorTIN;
	private PartyNCTS			consigneeSecurity;		//consignorTIN
	private TIN				consigneeSecurityTIN;
	private PartyNCTS			consignorSecurity;		//consignorTIN
	private TIN				consignorSecurityTIN;
	private String          identificationCode;		    //EI20110607
	
	// CK 2011-02-11
	//EI20110609: private WriteOffSumA 	writeOffSuma;	
	private WriteOffInfo 	writeOffInfo;   //EI20110609
	
	/// CONSTRUCTOR(s)
		public MsgNCTSDeclarationPos() {
			super();
		}
		
		public MsgNCTSDeclarationPos(XmlMsgScanner scanner, String msgName) {
			super(scanner);
			setMsgName(msgName);
		}
		/// ENUM(s)
		private enum EMsgNCTSDeclarationPos {
			// KIDS					// UIDS
			ItemNumber,				// same
			TypeOfDeclaration,		// same
			CommodityCode,			CommodityCodeKN8,
			Description,			GoodsDescription,
			ArticleNumber,			// same
			GrossMass,				// same
			NetMass,				// same
			IdentificationCode,     //same	          //EI20110607: neu, noch unklar wo es stehen soll
			DispatchCountry,		CountryOfDispatch,
			DestinationCountry,		CountryOfDestination,
			PreviousDocument,		PreviousDocuments,
			Document,				ProducedDocuments,
			SpecialMention,			SpecialMentions,
			Containers,	Container,	ContainerNumber,
			Package,				Packages,
			SensitiveGoods,			SGICodes,
			Guarantee,				// same
			Consignee,				// same
			ConsigneeTIN,			// ----
			Consignor,				// same
			ConsignorTIN,			// ----
			ConsigneeSecurity,
			ConsigneeSecurityTIN,
			ConsignorSecurity,
			ConsignorSecurityTIN,
			BondedWarehouseCompletion,   WriteOffInfo, //WriteOffInfo.WriteOffZL
			InwardProcessingCompletion, //WriteOffInfo.WriteOffAVUV
			ManifestCompletion, 	    //WriteOffInfo.WriteOffSumA;						
		}
	
	/// METHODS
		@Override
		public void startElement(Enum tag, String value, Attributes attr) {
			if (value == null) {
				switch((EMsgNCTSDeclarationPos) tag) {
					case CommodityCode:
						commodityCode	= new CommodityCode(getScanner());
						commodityCode.parse(tag.name());
						break;
					
					case PreviousDocument:
					case PreviousDocuments:
						PreviousDocument tmpPrevDoc	= new PreviousDocument(getScanner());
							tmpPrevDoc.parse(tag.name());
							if (previousDocumentList == null) {
								previousDocumentList	= 
									new Vector< PreviousDocument >();
							}
							previousDocumentList.add(tmpPrevDoc);
						break;
					
					case Document:
					case ProducedDocuments:
						Document tmpDocument	= new Document(getScanner());
							tmpDocument.parse(tag.name());
							if (documentList == null) {
								documentList	= new Vector< Document >();
							}
							documentList.add(tmpDocument);
						break;
					
					case SpecialMention:
					case SpecialMentions:
						SpecialMention tmpSpMention	= new SpecialMention(getScanner());
							tmpSpMention.parse(tag.name());
							if (specialMentionList == null) {
								specialMentionList	= 
									new Vector< SpecialMention >();
							}
							specialMentionList.add(tmpSpMention);
						break;
					case Package:
					case Packages:
						Packages packages = new Packages(getScanner());
						packages.parse(tag.name());
						if (packagesList == null) {
							packagesList	= 
								new Vector< Packages >();
						}
						packagesList.add(packages);
					break;
					
					case SensitiveGoods:
					case SGICodes:
						SensitiveGoods goods = new SensitiveGoods(getScanner());
						goods.parse(tag.name());
						if (sensitiveGoodsList == null) {
							sensitiveGoodsList	= 
								new Vector< SensitiveGoods >();
						}
						sensitiveGoodsList.add(goods);
					break;
					case Guarantee:
						Guarantee gaurantee = new Guarantee(getScanner());
						gaurantee.parse(tag.name());
						if (guaranteeList == null) {
							guaranteeList	= 
								new Vector< Guarantee >();
						}
						guaranteeList.add(gaurantee);
						break;
					
					case Consignee:
						consignee	= new PartyNCTS(getScanner());
							consignee.parse(tag.name());
							if (consignee.getPartyTIN() != null) {
								setConsigneeTIN(consignee.getPartyTIN());
							}
						break;
					
					case ConsigneeTIN:
						consigneeTIN	= new TIN(getScanner());
						consigneeTIN.parse(tag.name());
						break;
					
					case Consignor:
						consignor	= new PartyNCTS(getScanner());
							consignor.parse(tag.name());
							if (consignor.getPartyTIN() != null) {
								setConsignorTIN(consignor.getPartyTIN());
							}
						break;
					
					case ConsignorTIN:
						consignorTIN	= new TIN(getScanner());
						consignorTIN.parse(tag.name());
						break;
						
					case ConsignorSecurity:
						consignorSecurity	= new PartyNCTS(getScanner());
						consignorSecurity.parse(tag.name());
							if (consignorSecurity.getPartyTIN() != null) {
								setConsignorSecurityTIN(
										consignorSecurity.getPartyTIN());
							}
						break;
					
					case ConsignorSecurityTIN:
						consignorSecurityTIN	= new TIN(getScanner());
						consignorSecurityTIN.parse(tag.name());
						break;	
						
					case ConsigneeSecurity:
						consigneeSecurity	= new PartyNCTS(getScanner());
						consigneeSecurity.parse(tag.name());
							if (consigneeSecurity.getPartyTIN() != null) {
								setConsigneeSecurityTIN(
										consigneeSecurity.getPartyTIN());
							}
						break;
					
					case ConsigneeSecurityTIN:
						consigneeSecurityTIN	= new TIN(getScanner());
						consigneeSecurityTIN.parse(tag.name());
						break;
						
					case Containers:
					case Container:   //BDP20130425    
						container = new Container(getScanner());
						container.parse(tag.name());
						break;
					case WriteOffInfo:
						writeOffInfo = new WriteOffInfo(getScanner());
						writeOffInfo.parse(tag.name());
						break;
					case ManifestCompletion:						
						WriteOffSumA writeOffSuma = new WriteOffSumA(getScanner());						
						writeOffSuma.parse(tag.name());
						if (writeOffInfo == null) {
							writeOffInfo = new WriteOffInfo();
						}
						writeOffInfo.addWriteOffSumAList(writeOffSuma);
						break;						
					case BondedWarehouseCompletion:	                       //EI20111011					
						WriteOff writeOffbwc = new WriteOff(getScanner());
						writeOffbwc.parse(tag.name());
						if (writeOffInfo == null) {
							writeOffInfo = new WriteOffInfo();
						}
						writeOffInfo.addWriteOffZLList(writeOffbwc);
						break;
					case InwardProcessingCompletion:		                //EI20111011					
						WriteOff writeOffipc = new WriteOff(getScanner());
						writeOffipc.parse(tag.name());
						if (writeOffInfo == null) {
							writeOffInfo = new WriteOffInfo();
						}
						writeOffInfo.addWriteOffAVUVList(writeOffipc);
						break;
					default:
						break;
				}
			} else {
				switch((EMsgNCTSDeclarationPos) tag) {
					case ItemNumber:
						setItemNumber(value);
						break;
					
					case TypeOfDeclaration:
						setTypeOfDeclaration(value);
						break;
					case CommodityCodeKN8:
						if (commodityCode == null) {
							commodityCode = new CommodityCode();
						}
						commodityCode.setTarifCode(value);
						break;
					case Description:
					case GoodsDescription:
						setDescription(value);
						break;
					
					case ArticleNumber:
						setArticleNumber(value);
						break;
					
					case GrossMass:
						setGrossMass(value);
						break;
					
					case NetMass:
						setNetMass(value);
						break;
					
					case DispatchCountry:
					case CountryOfDispatch:
						setDispatchCountry(value);
						break;
					
					case DestinationCountry:
					case CountryOfDestination:
						setDestinationCountry(value);
						break;
					
					case ContainerNumber:
						if (container == null) {
							container = new Container();
						}
						container.addNumberList(value);
						break;
					case IdentificationCode:
						setIdentificationCode(value);
						break;
						
					default:
						break;
				}
			}
		}
	
		@Override
		public void stoppElement(Enum tag) {
			// TODO Auto-generated method stub
			
		}
	
		@Override
		public Enum translate(String token) {
			try {
				return EMsgNCTSDeclarationPos.valueOf(token);
			} catch (IllegalArgumentException e) {
				return null;
			}
		}

	/// SETTER & GETTER Methods
		public void setMsgName(String msgName) {
			this.msgName	= msgName;
		}
		
		public String getMsgName() {
			return this.msgName;
		}
		
		public void setItemNumber(String itemNo) {
			this.itemNumber	= itemNo;
		}
		
		public String getItemNumber() {
			return this.itemNumber;
		}
		
		public void setTypeOfDeclaration(String typeOfDec) {
			this.typeOfDeclaration	 = typeOfDec;
		}
		
		public String getTypeOfDeclaration() {
			return this.typeOfDeclaration;
		}
		
		public void setCommodityCode(CommodityCode commCde) {
			this.commodityCode	= commCde;
		}
		
		public CommodityCode getCommodityCode() {
			return this.commodityCode;
		}
		
		public void setDescription(String desc) {
			this.description	= desc;
		}
		
		public String getDescription() {
			return this.description;
		}
		
		public void setArticleNumber(String articleNo) {
			this.articleNumber	= articleNo;
		}
		
		public String getArticleNumber() {
			return this.articleNumber;
		}
		
		public void setGrossMass(String gMass) {
			this.grossMass	= gMass;
		}
		
		public String getGrossMass() {
			return this.grossMass;
		}
		
		public void setNetMass(String netMass) {
			this.netMass	= netMass;
		}
		
		public String getNetMass() {
			return this.netMass;
		}
		
		public void setDispatchCountry(String dispatchCountry) {
			this.dispatchCountry	= dispatchCountry;
		}
		
		public String getDispatchCountry() {
			return this.dispatchCountry;
		}
		
		public void setDestinationCountry(String destinationCountry) {
			this.destinationCountry	= destinationCountry;
		}
		
		public String getDestinationCountry() {
			return this.destinationCountry;
		}
		
		public void setPreviousDocumentList(List< PreviousDocument > prevDocList) {
			this.previousDocumentList	= prevDocList;
		}
		
		public List< PreviousDocument > getPreviousDocumentList() {
			return this.previousDocumentList;
		}
		
		public void setDocumentList(List< Document > docList) {
			this.documentList	= docList;
		}
		
		public List< Document > getDocumentList() {
			return this.documentList;
		}
		
		public void setSpecialMentionList(List< SpecialMention > specMentionList) {
			this.specialMentionList	= specMentionList;
		}
		
		public List< SpecialMention > getSpecialMentionList() {
			return this.specialMentionList;
		}
		
		public void setConsignee(PartyNCTS consignee) {
			this.consignee	= consignee;
		}
		
		public PartyNCTS getConsignee() {
			return this.consignee;
		}
		
		public void setConsigneeTIN(TIN consigneeTIN) {
			this.consigneeTIN	= consigneeTIN;
		}
		
		public TIN getConsigneeTIN() {
			return this.consigneeTIN;
		}
		
		public void setConsignor(PartyNCTS consignor) {
			this.consignor	= consignor;
		}
		
		public PartyNCTS getConsignor() {
			return this.consignor;
		}
		
		public void setConsignorTIN(TIN consignorTIN) {
			this.consignorTIN	= consignorTIN;
		}
		
		public TIN getConsignorTIN() {
			return this.consignorTIN;
		}

		public List<Packages> getPackagesList() {
			return packagesList;
		}

		public void setPackagesList(List<Packages> packagesList) {
			this.packagesList = packagesList;
		}

		public List<SensitiveGoods> getSensitiveGoodsList() {
			return sensitiveGoodsList;
		}

		public void setSensitiveGoodsList(List<SensitiveGoods> sensitiveGoodsList) {
			this.sensitiveGoodsList = sensitiveGoodsList;
		}

		public List<Guarantee> getGuaranteeList() {
			return guaranteeList;
		}

		public void setGuaranteeList(List<Guarantee> guaranteeList) {
			this.guaranteeList = guaranteeList;
		}

		public Container getContainer() {
			return container;
		}

		public void setContainer(Container container) {
			this.container = container;
		}

		public PartyNCTS getConsigneeSecurity() {
			return consigneeSecurity;
		}

		public void setConsigneeSecurity(PartyNCTS consigneeSecurity) {
			this.consigneeSecurity = consigneeSecurity;
		}

		public TIN getConsigneeSecurityTIN() {
			return consigneeSecurityTIN;
		}

		public void setConsigneeSecurityTIN(TIN consigneeSecurityTIN) {
			this.consigneeSecurityTIN = consigneeSecurityTIN;
		}

		public PartyNCTS getConsignorSecurity() {
			return consignorSecurity;
		}

		public void setConsignorSecurity(PartyNCTS consignorSecurity) {
			this.consignorSecurity = consignorSecurity;
		}

		public TIN getConsignorSecurityTIN() {
			return consignorSecurityTIN;
		}

		public void setConsignorSecurityTIN(TIN consignorSecurityTIN) {
			this.consignorSecurityTIN = consignorSecurityTIN;
		}
        /* EI20110609:
		public WriteOffSumA getWriteOffSuma() {
			return writeOffSuma;
		}
		public void setWriteOffSuma(WriteOffSumA writeOffSuma) {
			this.writeOffSuma = writeOffSuma;
		}
		*/
		public WriteOffInfo getWriteOffInfo() {
			return writeOffInfo;
		}
		public void setWriteOffInfo(WriteOffInfo argument) {
			this.writeOffInfo = argument;
		}
		
		public String getIdentificationCode() {
			return identificationCode;
		}
		public void setIdentificationCode(String argument) {
			this.identificationCode = argument;
		}
}
