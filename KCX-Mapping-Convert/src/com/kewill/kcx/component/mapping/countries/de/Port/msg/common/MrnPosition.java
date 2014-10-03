package com.kewill.kcx.component.mapping.countries.de.Port.msg.common;

import java.util.List;
import java.util.Vector;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module      : Port.<br>
 * Created     : 28.10.2011<br>
 * Description : 
 * 
 * @author Iwaniuk
 * @version 1.0.00
 */

public class MrnPosition extends KCXMessage {

	 private String positionNumber;        //mrn_mrnpo
	 private String mrnPositionComplete;   //mrn_kzkomp ???
	 private String mrnPositionMissing;   //mrn_kzkomp
	 private String reducedGoodsFlag;	   // mrnmin Kz. MRN-Mindermenge
	 private String netMass;               // eigmas    
	 private String grossMass;             // rohmas  	
	 private List<String> packageIdList;
	
	 public MrnPosition() {
		 super();  
	 }

	 public MrnPosition(XmlMsgScanner scanner) {
  		super(scanner);
	 }
 
	 private enum ENachlauf {	
			// Kids-TagNames, 			UIDS-TagNames;
		 PositionNumber,					
		 MRNPositionComplete,		
		 MRNPositionMissing,		//EI20130529
		 ReducedGoodsFlag,
		 NetMass,
		 GrossMass,		 
		 PackageId;
	 }	 
	 
	 public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {		
			switch ((ENachlauf) tag) {
				default: return;			
			}
		} else {			
			switch ((ENachlauf) tag) {			
				case PositionNumber:
					setPositionNumber(value);
					break;
				case MRNPositionComplete:
					setMrnPositionComplete(value);
					break;
				case MRNPositionMissing:
					setMrnPositionMissing(value);
					break;
				case ReducedGoodsFlag:
					setReducedGoodsFlag(value);
					break;	
				case NetMass:
					setNetMass(value);
					break;
				case GrossMass:
					setGrossMass(value);
					break;
				case PackageId:
					addPackageIdList(value);
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
			return ENachlauf.valueOf(token);
		 } catch (IllegalArgumentException e) {
			return null;
		 }
	 }

    public String getPositionNumber() {
		return positionNumber;
	}
	public void setPositionNumber(String argument) {
		this.positionNumber = argument;
	}					
		
	public String getMrnPositionComplete() {
		return mrnPositionComplete;
	}
	public void setMrnPositionComplete(String argument) {
		this.mrnPositionComplete = argument;
	}	
	
	public String getMrnPositionMissing() {
		return mrnPositionMissing;
	}
	public void setMrnPositionMissing(String argument) {
		this.mrnPositionMissing = argument;
	}
	
	public String getGrossMass() {
		return grossMass;
	}
	public void setGrossMass(String argument) {
		this.grossMass = argument;
	}
	
	public String getNetMass() {
		return netMass;
	}
	public void setNetMass(String argument) {
		this.netMass = argument;
	}
	
	public String getReducedGoodsFlag() {
		return reducedGoodsFlag;
	}
	public void setReducedGoodsFlag(String argument) {
		this.reducedGoodsFlag = argument;
	}
	public List<String>  getPackageIdList() {
		return packageIdList;
	}
	public void setPackageIdList(List<String>  list) {
		this.packageIdList = list;
	}
	public void addPackageIdList(String packageId) {
		if (packageIdList == null) {
			packageIdList = new Vector<String>();	
		}
		this.packageIdList.add(packageId);
	}
	
}
