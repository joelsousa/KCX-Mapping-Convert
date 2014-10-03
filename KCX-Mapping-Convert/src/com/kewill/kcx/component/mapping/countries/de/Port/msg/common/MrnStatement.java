package com.kewill.kcx.component.mapping.countries.de.Port.msg.common;

import org.xml.sax.Attributes;

import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Port<br>
 * Created		: 24.10.2011<br>
 * Description	:.
 * 
 * @author Iwaniuk
 * @version 1.0.00
 */
public class MrnStatement extends KCXMessage {
		
	 private String mrn;	                    // mrn_mrn
	 private String mrnComplete;	            //mrn_mrnall Kz. MRN-Nummer vollständig in der Anmeldung
	 private String reducedGoodsFlag;	        // mrnmin Kz. MRN-Mindermenge
	 private List<MrnPosition> mrnPositionList; //mrn_mrnpo MRN-Positionsnummer		 	
	 private String destinationCountry;         // ldbe    
	 private String dispatchCountry;            // ldve  
	
	 
    public MrnStatement() {
		super();  
    }

	public MrnStatement(XmlMsgScanner scanner) {
	  	super(scanner);
	}
	 
	 private enum EMrnStatement {	
				// Kids-TagNames, 			UIDS-TagNames;
			 MRN,
			 MRNComplete,      
			 ReducedGoodsFlag,   		
			 MRNPosition,
			 DestinationCountry,
			 DispatchCountry;
		 }	 
		 
		 public void startElement(Enum tag, String value, Attributes attr) {
			if (value == null) {		
				switch ((EMrnStatement) tag) {
				case MRNPosition:
					MrnPosition pos = new MrnPosition(getScanner());
					pos.parse(tag.name());
					addMrnPositionList(pos);
					break;
				default: return;			
				}
			} else {			
				switch ((EMrnStatement) tag) {						
					case MRN:
						setMrn(value);
						break;					
					case MRNComplete:
						setMrnComplete(value);
						break;
					case ReducedGoodsFlag:
						setReducedGoodsFlag(value);
						break;	
					case DestinationCountry:
						setDestinationCountry(value);
						break;
					case DispatchCountry:
						setDispatchCountry(value);
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
				return EMrnStatement.valueOf(token);
			 } catch (IllegalArgumentException e) {
				return null;
			 }
		 }
		
		public String getMrn() {
			return mrn;
		}
		public void setMrn(String argument) {
			this.mrn = argument;
		}
		
		public String getMrnComplete() {
			return mrnComplete;
		}
		public void setMrnComplete(String argument) {
			this.mrnComplete = argument;
		}
		
		public String getReducedGoodsFlag() {
			return reducedGoodsFlag;
		}
		public void setReducedGoodsFlag(String argument) {
			this.reducedGoodsFlag = argument;
		}
	
	public List<MrnPosition> getMrnPositionList() {
		return mrnPositionList;
	}
	public void setMrnPositionList(List<MrnPosition> list) {
		this.mrnPositionList = list;
	}
	public void addMrnPositionList(MrnPosition document) {
		if (mrnPositionList == null) {
			mrnPositionList = new Vector<MrnPosition>();	
		}
		this.mrnPositionList.add(document);
	}

	public String getDestinationCountry() {
		return destinationCountry;
	}
	public void setDestinationCountry(String argument) {
		this.destinationCountry = argument;
	}
	
	public String getDispatchCountry() {
		return dispatchCountry;
	}
	public void setDispatchCountry(String argument) {
		this.dispatchCountry = argument;
	}
	
}
