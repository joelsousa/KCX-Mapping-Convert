package com.kewill.kcx.component.mapping.countries.common.msgBDP_not_used;

import java.util.List;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Container;
import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.SpecialMention;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ApprovedTreatment;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Business;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CommodityCode;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Completion;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.DocumentV20;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ExportRefundItem;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.GoodsIdentification;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.IncoTerms;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.NonCustomsLaw;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Notifications;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Permit;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PreviousDocumentV20;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Refinement;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.SensitiveGoods;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Statistic;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.WareHouse;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module       : Export/aes.<br>
 * Created      : 12.07.2012<br>
 * Description	: Kids Version 2.1.00
 * 
 * @author iwaniuk
 * @version 2.1.00
 */

public class MsgBDPDeclarationPos_not_used extends KCXMessage {
	
	private String msgName;
	private String itemNumber; 
	private String articleNumber; 
	private String description; 
	private String uCROtherSystem; 
	private String annotation; 
	private String annotation2;                
	private String shipmentNumber; 
	private String originCountry;               
	private String originFederalState; 
	private String destinationCountry;
	private String netMass;						
	private String netMassConfirmation;			  
	private String grossMass; 					
	private String grossMassConfirmation; 
	private String dangerousGoodsNumber; 
	private String paymentType; 
	private String kindOfArticle;               	
	private String typeOfArticle;               
	private String commodityBoard;              
	private String thirdQuantity;					
	private Party supervisingCustomsOffice;               
	private TIN supervisingCustomsOfficeTIN;               
	private String additionalCommodityBoardCode;	
	private String descriptionLanguage;
	private CommodityCode commodityCode;  
	private ExportRefundItem exportRefundItem;
	private ApprovedTreatment approvedTreatment;
	private Statistic statistic;
	private GoodsIdentification goodsIdentification;	
	private List<SensitiveGoods> sensitiveGoodsList;
	private SpecialMention specialMention; 
	private List <SpecialMention> specialMentionList;
	private NonCustomsLaw nonCustomsLaw;
	private String permitObligation;	
	private List <Permit> permitList;
    
	private Party consignee;
	private TIN consigneeTIN;	
	private Party consignor;
	private TIN consignorTIN;
	private Party warehouseKeeper;
    private TIN warehouseKeeperTIN;
		
	private List <Packages>packagesList;
	private Container container;	
	private List <DocumentV20>documentList; 	  
	private List <PreviousDocumentV20>previousDocumentList;  	
	private List <Text>addInfoStatementList;  				  
	
	private Completion bondedWarehouseCompletion;   
	private Completion inwardProcessingCompletion;  
	
	private WareHouse wareHouse;   
	private Refinement refinement;                 
	private List<Text> detailList;                 
	private String refundType; 
	private String refundQuantity; 
	
	private Notifications notifications;
	private List <String> notificationCodeList;	
	private String restitutionProcedure;    //only UIDS   
	
 //new for KIDS V2.1	begin	
	private Business  business;
	private IncoTerms incoTerms;
  	private String    addressCombination; 
  	private TIN       finalRecipientTIN;			
  	private Party     finalRecipient;    
 //new for KIDS V2.1 end

    //----------------
  	
	public MsgBDPDeclarationPos_not_used() {
			super();			
	}
	public MsgBDPDeclarationPos_not_used(XMLEventReader parser) {
		super(parser);				
		}	
	
	public MsgBDPDeclarationPos_not_used(XmlMsgScanner scanner) {
  		super(scanner);  		
  	}	
	public MsgBDPDeclarationPos_not_used(XmlMsgScanner scanner, String msgName) {
  		super(scanner);
  		this.msgName = msgName;  		
  	}

	 private enum EBdpDeclarationPos {
			//Kids							
		 	ItemNumber,				//Export, Import		
		 	TypeOfDeclaration, 		//------, ------, NCTS
		 	Container,				//Export, Import, NCTS(Containers), PORT(ContainerNumber)	
		 	Guarantee,         		//------, Import, NCTS
		 	ManifestCompletion,		//------, ------, NCTS
			ArticleNumber,			//Export, Import, NCTS	 							
			Description,			//Export, Import, NCTS		
			DescriptionLanguage,
			CommodityCode,			//Export, Import, NCTS, PORT (TarifCode)			
			ProcedureCode,			//Export, Import, ---- (ApprovedTreatment.Declared+Previous)
			DutyControlCode,		//------, Import, ----
			Text,					//------, Import, ----
			SupplementaryText,		//------, Import, ----
			EAN,					//------, Import, ----
			Amount,					//------, Import, ----
			UnitOfMeasurementAmount, //-----, Import, ----
			QualifierAmount,		//------, Import, ----
			CountryOfOrigin,		//Export(OriginCountry), Import, ----
			OriginFederalState,		//Export, ------, ---- 
			DestinationCountry,		//Export, ------, NCTS
			EUCode,					//------, Import, ----
			Quota,					//------, Import, ----
			PlaceOfIntroduction,	//------, Import, ----
			PlaceOfDeparture,		//------, Import, ----
			NetMass,				//Export, Import, NCTS	
			GrossMass,				//Export, Import, NCTS	
			Package,				//Export, Import, NCTS	
			RequestedPrivilege,		//------, Import, ----
			Condition,				//------, Import, ----
			CustomsValue,			//------, Import, ----
			ImportTurnoverTax,		//------, Import, ----
			Invoice,				//------, Import, ----
			NetPrice,				//------, Import, ----
			IndirectPayment,		//------, Import, ----
			Statistic,				//Export, Import, ----	
			    Quantity,			//AdditionalUnit, Quantity, ----
			    MeasuringUnit,		//AdditionalUnitCode,MeasuringUnit, ---- 
			    QualifierMeasuringUnit,//QualifierAdditionalUnitCode, QualifierMeasuringUnit, ----
			GoodsQuantityCustoms,		    //------, Import, ----
			GoodsQuantityAgriculturalDuty,  //------, Import, ----
			ProcessingFeeValueIncrease,		//----- , Import, ----
			ExtraCostImportVAT,				//------, Import, ----
			TobaccoDutyCodeNumber,			//------, Import, ----
			Preference,						//------, Import, ----
			Document,						//Export, Import, NCTS	
			    Number,						//Reference, Number, Reference
			Salary,							//------, Import, ----
			Excise,							//------, Import, ----
			AdditionsDeductions,			//------, Import, ----
			AdditionsDeductionsDescription,	//------, Import, ----
			ReductionStatement,				//------, Import, ----
			SpecialValueStatement,			//------, Import, ----
			SpecialCaseStatement,			//------, Import, ----
			ConsigneeTIN,					//Export, ------, NCTS
			Consignee,						//Export, ------, NCTS
			ConsignorTIN,					//Export, ------, NCTS
			Consignor,						//Export, ------, NCTS
			FinalRecipientTIN,				//Export, ------, ----
			FinalRecipient,					//Export, ------, ----
			PORT,							//Export, ------, ----
			
	}
	 
	 public void startElement(Enum tag, String value, Attributes attr) {
			if (value == null) {			
				switch ((EBdpDeclarationPos) tag) {
				default:
						return;
				}
			} else {				
				switch ((EBdpDeclarationPos) tag) {
				default:
					return;
				}
			}
		}

		public void stoppElement(Enum tag) {
		}

		public Enum translate(String token) {
			try {
				return EBdpDeclarationPos.valueOf(token);
			} catch (IllegalArgumentException e) {
				return null;
			}
		}	 
	
}
