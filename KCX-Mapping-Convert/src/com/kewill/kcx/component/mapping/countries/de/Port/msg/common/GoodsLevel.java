package com.kewill.kcx.component.mapping.countries.de.Port.msg.common;

import org.xml.sax.Attributes;

import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;


/**
 * Module      : Port.<br>
 * Created     : 28.10.2011<br>
 * Description : 
 * 
 * @author Iwaniuk
 * @version 1.0.00
 */
public class GoodsLevel extends KCXMessage {
	
	 private String         sequenceNumber;  //for level1 should be empty
	 private String         quantity;	     
	 private String         packingType;	 
	 private String         grossMass;	     
	 private String         volume;	         // aco_
	 private List<MrnStatement> mrnStatementList; // mrn list
	 private EADocument     eaDocument;        //AE 
	 //private List<String>   descriptionList;  //TXT list	
	 private String         itemRemark;         //txt: AEA
	 private String         description;        //txt: AAA    
	 private String         marks;      //txt: PCI
	 private DangerousGoods dangerousGoods;   //AG...
	
    public GoodsLevel() {
		super();  
    }
    
    public GoodsLevel(int lfd) {
		super();  
		if (lfd < 0) {
			lfd = 0;
		}
		this.sequenceNumber = "" + lfd;
    }
    
	public GoodsLevel(XmlMsgScanner scanner) {
	  	super(scanner);
	}
	 
		 private enum EGoodsLevel {	
				// Kids-TagNames, 			KFF
			 SequenceNumber,
			 Quantity,                 ItemPCS, ItemInnerPCS,	//fzpaco_colanz 
			 PackingType,              ItemPCS_UT, ItemInnerPCS_UT,//colart 					 
			 GrossMass,	               ItemWGT_UT,      //rohmas
			 Volume,                   ItemVOL_UT,      //brtvol
			 MrnStatement,  
			 EADocument,
			 ItemRemark,              ItemCargoItemRemarks, 			
			 Description,               //Description,     
			 Marks,						//Marks,
			 DangerousGoods;			        			
		 }	 
		 
		 public void startElement(Enum tag, String value, Attributes attr) {
			if (value == null) {		
				switch ((EGoodsLevel) tag) {
				case MrnStatement:		
					MrnStatement mrn = new MrnStatement(getScanner());
					mrn.parse(tag.name());
					addMrnStatementList(mrn);
					break;	
				case EADocument:
					eaDocument = new EADocument(getScanner());
					eaDocument.parse(tag.name());					
					break;
				case DangerousGoods:
					dangerousGoods = new DangerousGoods(getScanner());
					dangerousGoods.parse(tag.name());					
					break;
				default: return;			
				}
			} else {			
				switch ((EGoodsLevel) tag) {			
					case SequenceNumber:
						setSequenceNumber(value);
						break;
					case Quantity:
					case ItemInnerPCS:
						setQuantity(value);
						break;
					case PackingType:
					case ItemInnerPCS_UT:
						setPackingType(value);
						break;
					case GrossMass:
					case ItemWGT_UT:
						setGrossMass(value);
						break;					
					case Volume:
						case ItemVOL_UT:
						setVolume(value);
						break;					
					case ItemRemark:
					case ItemCargoItemRemarks:
						setItemRemark(value);
						break;						
					case Description:
						setDescription(value);
						break;
					case Marks:
						setMarks(value);
						break;
					default:
						return;
				}
			}
		}

		 public void stoppElement(Enum tag) {
		 }
		
		 public Enum translate(String token) {
			 try {
				return EGoodsLevel.valueOf(token);
			 } catch (IllegalArgumentException e) {
				return null;
			 }
		 }

	    public String getSequenceNumber() {
			return sequenceNumber;
		}
		public void setSequenceNumber(String argument) {
			this.sequenceNumber = argument;
		}					
		
		public String getQuantity() {
			return quantity;
		}
		public void setQuantity(String argument) {
			this.quantity = argument;
		}
		 
	    public String getPackingType() {
			return packingType;
		}
		public void setPackingType(String argument) {
			this.packingType = argument;
		}							
		
		public String getGrossMass() {
			return grossMass;
		}
		public void setGrossMass(String argument) {
			this.grossMass = argument;
		}

		public String getVolume() {
			return volume;
		}
		public void setVolume(String argument) {
			this.volume = argument;
		}
				
		public EADocument getEADocument() {
			return eaDocument;
		}
		public void setEADocument(EADocument argument) {
			this.eaDocument = argument;
		}
		
		public DangerousGoods getDangerousGoods() {
			return dangerousGoods;
		}
		public void setDangerousGoods(DangerousGoods argument) {
			this.dangerousGoods = argument;
		}		 		
			
		public List <MrnStatement> getMrnStatementList() {
			return mrnStatementList;
		}
		public void setMrnStatementList(List <MrnStatement> list) {
			this.mrnStatementList = list;
		}	
		public void addMrnStatementList(MrnStatement argument) {
			if (mrnStatementList == null) {
				mrnStatementList = new Vector<MrnStatement>();
			}
			this.mrnStatementList.add(argument);
		}

		public String getItemRemark() {
			return itemRemark;
		}
		public void setItemRemark(String text) {
			this.itemRemark = text;
		}
		
		public String getDescription() {
			return description;
		}
		public void setDescription(String text) {
			this.description = text;
		}
		
		public String getMarks() {
			return marks;
		}
		public void setMarks(String text) {
			this.marks = text;
		}
		
		public boolean isEmpty() {   
			return (Utils.isStringEmpty(this.quantity) && Utils.isStringEmpty(this.packingType) &&  
			        Utils.isStringEmpty(this.grossMass) && Utils.isStringEmpty(this.volume) &&
			        //EI20130820: weitere member erfasst (waren nur mandatory )
			        Utils.isStringEmpty(this.itemRemark) && Utils.isStringEmpty(this.description) &&
			        Utils.isStringEmpty(this.marks) && mrnStatementList == null &&			        
			        eaDocument == null && dangerousGoods == null);  
		}		
}


