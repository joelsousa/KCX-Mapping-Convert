package com.kewill.kcx.component.mapping.countries.de.ncts20.msg;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
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
import com.kewill.kcx.component.mapping.countries.de.ncts20.msg.common.WriteOffInfo;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.WriteOffSumA;
import com.kewill.kcx.component.mapping.countries.de.ncts20.msg.common.Completion;
import com.kewill.kcx.component.mapping.countries.de.ncts20.msg.common.ManifestCompletion;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: MsgNCTSDeclarationPos
 * Created		: 02.02.2013
 * Description	: contains message structure with fields used for
 * 				:	GoodsItem for NCTSDeclaration.
 * 
 * @author	: iwaniuk
 * @version	: 4.1.00
 *
 */
public class MsgNCTSDeclarationPos extends KCXMessage {
	private String			msgName;
	private String			itemNumber;
	private String			typeOfDeclaration;
	private CommodityCode	commodityCode;			// validate
	private String			description;
	private String			articleNumber;
	private String			dispatchCountry;
	private String			destinationCountry;
	private String			grossMass;
	private String			netMass;
	private String          identificationCode;		    
	private List<PreviousDocument> previousDocumentList;
	private List<Document>	       documentList;
	private List<SpecialMention>   specialMentionList;
	private Container		       container;
	private List<Packages>		   packagesList;
	private List<SensitiveGoods>   sensitiveGoodsList;
	private List<Guarantee>		   guaranteeList;
	private PartyNCTS			   consignor;		//consignorTIN
	private TIN				       consignorTIN;
	private ContactPerson	       consignorContact;	
	private PartyNCTS			   consignee;		//consigneeTIN
	private TIN				       consigneeTIN;	
	private ContactPerson	       consigneeContact;
	private PartyNCTS			   consignorSecurity;		//consignorTIN
	private TIN				       consignorSecurityTIN;
	private ContactPerson	       consignorSecurityContact;	
	private PartyNCTS			   consigneeSecurity;		//consignorTIN
	private TIN				       consigneeSecurityTIN;
	private ContactPerson	       consigneeSecurityContact;	
	
	//EI20131128 private WriteOffInfo 	writeOffInfo;
	private ArrayList<Completion> bondedWarehouseCompletionList;   //EI20131128
	private ArrayList<Completion> inwardProcessingCompletionList;  //EI20131128
	private ArrayList<ManifestCompletion> manifestCompletionList;       //EI20131128
		
	private String	awbForCMP;  //EI20140508: only an internal member for easier handling of Items with this same AWB in CMP/LCAG
	
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
			Containers,				ContainerNumber,
			Package,				Packages,
			SensitiveGoods,			SGICodes,
			Guarantee,				// same
			ConsigneeAddress,		Consignee,
			ConsigneeTIN,			
			ConsigneeContact,
			ConsignorAddress,		Consignor,
			ConsignorTIN,			
			ConsignorContact,
			ConsigneeSecurityAddress, ConsigneeSecurity,
			ConsigneeSecurityTIN,
			ConsigneeSecurityContact,
			ConsignorSecurityAddress, ConsignorSecurity,
			ConsignorSecurityTIN,
			ConsignorSecurityContact,
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
						this.addPreviousDocumentList(tmpPrevDoc);
						break;
					
					case Document:
					case ProducedDocuments:
						Document tmpDocument	= new Document(getScanner());
						tmpDocument.parse(tag.name());
						this.addDocumentList(tmpDocument);	
						break;
					
					case SpecialMention:
					case SpecialMentions:
						SpecialMention tmpSpMention	= new SpecialMention(getScanner());
							tmpSpMention.parse(tag.name());							
							addSpecialMentionList(tmpSpMention);
						break;
					case Package:
					case Packages:
						Packages packages = new Packages(getScanner());
						packages.parse(tag.name());						
						this.addPackagesList(packages);
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
					case ConsigneeAddress:
						consignee	= new PartyNCTS(getScanner());
						consignee.parse(tag.name());						
						break;					
					case ConsigneeTIN:
						consigneeTIN	= new TIN(getScanner());
						consigneeTIN.parse(tag.name());
						break;
					case ConsigneeContact:
						consigneeContact = new ContactPerson(getScanner());
						consigneeContact.parse(tag.name());
						break;
						
					case Consignor:
					case ConsignorAddress:
						consignor = new PartyNCTS(getScanner());
						consignor.parse(tag.name());							
						break;					
					case ConsignorTIN:
						consignorTIN	= new TIN(getScanner());
						consignorTIN.parse(tag.name());
						break;
					case ConsignorContact:
						consignorContact = new ContactPerson(getScanner());
						consignorContact.parse(tag.name());
						break;
						
					case ConsignorSecurity:
					case ConsignorSecurityAddress:
						consignorSecurity	= new PartyNCTS(getScanner());
						consignorSecurity.parse(tag.name());							
						break;					
					case ConsignorSecurityTIN:
						consignorSecurityTIN	= new TIN(getScanner());
						consignorSecurityTIN.parse(tag.name());
						break;	
					case ConsigneeSecurityContact:
						consigneeSecurityContact = new ContactPerson(getScanner());
						consigneeSecurityContact.parse(tag.name());
						break;
						
					case ConsigneeSecurity:
					case ConsigneeSecurityAddress:
						consigneeSecurity	= new PartyNCTS(getScanner());
						consigneeSecurity.parse(tag.name());							
						break;					
					case ConsigneeSecurityTIN:
						consigneeSecurityTIN	= new TIN(getScanner());
						consigneeSecurityTIN.parse(tag.name());
						break;
					case ConsignorSecurityContact:
						consignorSecurityContact = new ContactPerson(getScanner());
						consignorSecurityContact.parse(tag.name());
						break;
						
					case Containers:
						container = new Container(getScanner());
						container.parse(tag.name());
						break;
					
						/* EI20131128
				   case WriteOffInfo:
						WriteOffInfo writeOffInfo = new WriteOffInfo(getScanner());
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
						*/
					case WriteOffInfo:
						WriteOffInfo writeOffInfo = new WriteOffInfo(getScanner());
						writeOffInfo.parse(tag.name());
						this.setWriteOffInfo(writeOffInfo);
						break;
					case ManifestCompletion:
						ManifestCompletion manifestCompletion = new ManifestCompletion(getScanner());	
						manifestCompletion.parse(tag.name());
						addManifestCompletionList(manifestCompletion);
						break;
					case BondedWarehouseCompletion:	
						Completion bondedWarehouseCompletion = new Completion(getScanner());	
						bondedWarehouseCompletion.parse(tag.name());
						addBondedWarehouseCompletionList(bondedWarehouseCompletion);
						break;
					case InwardProcessingCompletion:
						Completion inwardProcessingCompletion = new Completion(getScanner());	
						inwardProcessingCompletion.parse(tag.name());
						addInwardProcessingCompletionList(inwardProcessingCompletion);
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
	
	
		public void stoppElement(Enum tag) {		
		}
		
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
		public void addPreviousDocumentList(PreviousDocument prev) {  //EI20130819
			if (previousDocumentList == null) {
				previousDocumentList = new Vector< PreviousDocument >();
			}
			previousDocumentList.add(prev);
		}
		
		public void setDocumentList(List< Document > docList) {
			this.documentList	= docList;
		}		
		public List< Document > getDocumentList() {
			return this.documentList;
		}
		public void addDocumentList(Document doc) {  //EI20130821		
			if (documentList == null) {
				documentList = new Vector<Document>();
			}
			documentList.add(doc);	
		}
		
		public void setSpecialMentionList(List< SpecialMention > specMentionList) {
			this.specialMentionList	= specMentionList;
		}		
		public List< SpecialMention > getSpecialMentionList() {
			return this.specialMentionList;
		}
		public void addSpecialMentionList(SpecialMention specialMention) {
			if (specialMentionList == null) {
				specialMentionList	= new Vector<SpecialMention>();
			}
			specialMentionList.add(specialMention);
		}
		
		public void setConsignee(PartyNCTS consignee) {
			this.consignee	= consignee;
		}
		
		public PartyNCTS getConsignee() {
			if (consigneeTIN != null) {
				if (consignee == null) {
					consignee = new PartyNCTS();
				}
				consignee.setPartyTIN(consigneeTIN);
			}
			if (consigneeContact != null) {
				if (consignee == null) {
					consignee = new PartyNCTS();
				}
				consignee.setContactPerson(consigneeContact);
			}	
			return this.consignee;
		}			
		
		public void setConsignor(PartyNCTS consignor) {
			this.consignor	= consignor;
		}
		
		public PartyNCTS getConsignor() {
			if (consignorTIN != null) {
				if (consignor == null) {
					consignor = new PartyNCTS();
				}
				consignor.setPartyTIN(consignorTIN);
			}
			if (consignorContact != null) {
				if (consignor == null) {
					consignor = new PartyNCTS();
				}
				consignor.setContactPerson(consignorContact);
			}	
			return this.consignor;
		}
				
		public List<Packages> getPackagesList() {
			return packagesList;
		}
		public void setPackagesList(List<Packages> packagesList) {
			this.packagesList = packagesList;
		}
		public void addPackagesList(Packages packages) {
			if (packagesList == null) {
				packagesList = new Vector< Packages >();
			}
			this.packagesList.add(packages);
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
			if (consigneeSecurityTIN != null) {
				if (consigneeSecurity == null) {
					consigneeSecurity = new PartyNCTS();
				}
				consigneeSecurity.setPartyTIN(consigneeSecurityTIN);
			}
			if (consigneeSecurityContact != null) {
				if (consigneeSecurity == null) {
					consigneeSecurity = new PartyNCTS();
				}
				consigneeSecurity.setContactPerson(consigneeSecurityContact);
			}	
			return consigneeSecurity;
		}

		public void setConsigneeSecurity(PartyNCTS consigneeSecurity) {
			this.consigneeSecurity = consigneeSecurity;
		}
		

		public PartyNCTS getConsignorSecurity() {
			if (consignorSecurityTIN != null) {
				if (consignorSecurity == null) {
					consignorSecurity = new PartyNCTS();
				}
				consignorSecurity.setPartyTIN(consignorSecurityTIN);
			}
			if (consignorSecurityContact != null) {
				if (consignorSecurity == null) {
					consignorSecurity = new PartyNCTS();
				}
				consignorSecurity.setContactPerson(consignorSecurityContact);
			}	
			return consignorSecurity;
		}

		public void setConsignorSecurity(PartyNCTS consignorSecurity) {
			this.consignorSecurity = consignorSecurity;
		}

		public String getIdentificationCode() {
			return identificationCode;
		}
		public void setIdentificationCode(String argument) {
			this.identificationCode = argument;
		}
		
	public ArrayList<ManifestCompletion> getManifestCompletionList() {
		return manifestCompletionList;
	}
	public void setManifestCompletionList(ArrayList<ManifestCompletion> list) {
		manifestCompletionList = list;
	}
	public void addManifestCompletionList(ManifestCompletion argument) {
		if (argument == null) {
			return;
		}
		if (manifestCompletionList == null) {
			manifestCompletionList = new ArrayList<ManifestCompletion>();
		}
		manifestCompletionList.add(argument);
	}
		
	public ArrayList<Completion> getBondedWarehouseCompletionList() {
			return bondedWarehouseCompletionList;
	}
	public void setBondedWarehouseCompletionList(ArrayList<Completion> list) {
			bondedWarehouseCompletionList = list;
	}
	public void addBondedWarehouseCompletionList(Completion argument) {
			if (argument == null) {
				return;
			}
			if (bondedWarehouseCompletionList == null) {
				bondedWarehouseCompletionList = new ArrayList<Completion>();
			}
			bondedWarehouseCompletionList.add(argument);
	}
		
	public ArrayList<Completion> getInwardProcessingCompletionList() {
			return inwardProcessingCompletionList;
	}
	public void setInwardProcessingCompletionList(ArrayList<Completion> list) {
			inwardProcessingCompletionList = list;
	}
	public void addInwardProcessingCompletionList(Completion argument) {
			if (argument == null) {
				return;
			}
			if (inwardProcessingCompletionList == null) {
				inwardProcessingCompletionList = new ArrayList<Completion>();
			}
			inwardProcessingCompletionList.add(argument);
	}
		
	private List mapMC(ArrayList<ManifestCompletion> listC) {
		WriteOffSumA woff = new WriteOffSumA();
		List<WriteOffSumA> list = null;  
		//todo
		
		return list;
	}
	private List mapBWC(ArrayList<Completion> listC) {
		WriteOff woff = new WriteOff();
		List<WriteOff> list = null;  
		//todo		
		return list;
	}
	private List mapIPC(ArrayList<Completion> listC) {
		WriteOff woff = new WriteOff();
		List<WriteOff> list = null;  
		//todo
		
		return list;
	}
	
	private void setWriteOffInfo(WriteOffInfo argument) {
		if (argument == null) {
			return;
		}
		if (argument.getWriteOffZLList() != null) {			
			this.setBondedWarehouseCompletionList(argument.getWriteOffZLList());			
		}
		if (argument.getWriteOffAVUVList() != null) {			
			this.setInwardProcessingCompletionList(argument.getWriteOffAVUVList());			
		}
		if (argument.getWriteOffSumAList() != null) {			
			this.setManifestCompletionList(argument.getWriteOffSumAList());			
		}		
	}
	public WriteOffInfo getWriteOffInfo() {
		if (bondedWarehouseCompletionList == null &&
			inwardProcessingCompletionList == null &&
			manifestCompletionList == null) {
			return null;
		}
		WriteOffInfo info = new WriteOffInfo();				
		if (bondedWarehouseCompletionList != null) {			
			info.setWriteOffZLList(this.getBondedWarehouseCompletionList());
		}
		if (inwardProcessingCompletionList != null) {				
			info.setWriteOffAVUVList(this.getInwardProcessingCompletionList());
		}
		if (manifestCompletionList != null) {				
			info.setWriteOffSumAList(this.getManifestCompletionList());
		}		
		return info;
	}
	
	public void setAwbForCMP(String awb) {
		this.awbForCMP = awb;
	}		
	public String getAwbForCMP() {
		return this.awbForCMP;
	}
}
