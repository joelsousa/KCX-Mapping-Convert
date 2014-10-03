package com.kewill.kcx.component.mapping.formats.fss.edec.aus20.messages;

import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.formats.fss.common.FssDatei;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCAN;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCBW;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCCL;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCCN;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCMD;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCNZ;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCPO;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCSG;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCST;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCUN;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCVA;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCVI;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCVM;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCVP;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus20.subsets.TsCAD;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus20.subsets.TsCAI;

/**
 * Module		: EDEC Export 70<br>
 * Created		: 26.10.2012<br>
 * Description  : FSS Definition of ExportDeclaration. 
 * 
 * @author iwaniuk
 * @version 7.0.00
 */

public class MsgCAN extends FssDatei {

	private TsVOR	vorSubset;
	private TsHead	headSubset;
	private TsCAN   canSubset = null;
	private TsCAI   caiSubset = null;	
	
	private TsCAD 	consignor = null;   //Typen: A: 1,2,3,5,6 D: 1,2,3,5,6,8 I: 1,2,3,5,9  
	private TsCAD 	declarant = null;	//2 
	private TsCAD 	consignee = null;   //3	
	private TsCAD	consignorSecurity = null;  //5
	private TsCAD	consigneeSecurity = null;  //6
	private TsCAD	carrier			  = null;  //9
	private TsCAD	zuladeort		  = null;  //8   //EI20130517 
	
	private List <TsCCN> ccnList;                //max 99 ContainerNumber	
	private List <TsCVP> cvpList;                //max 9  //EI20130517
	private List <TsCVI> cviList;                //max 9 
	private List <TsCVM> cvmList;                //max 9 
	private List <MsgCANPos >canPosList;         //max 999
	

	public MsgCAN(String outFileName) {
		super(outFileName);
		vorSubset = new TsVOR("");		
		canPosList = new Vector<MsgCANPos>();
	}	
	
	public TsVOR getVorSubset() {
		return vorSubset;
	}
	public void setVorSubset(TsVOR vor) {
		this.vorSubset = vor;
	}

	public TsHead getHeadSubset() {
		return headSubset;
	}
	public void setHeadSubset(TsHead head) {
		this.headSubset = head;
	}	
	
	public TsCAN getCanSubset() {
		return canSubset;	
	}
	public void setCanSubset(TsCAN argument) {
		if (argument == null) {
			return;
		}
		if (canSubset == null) {
			canSubset = new TsCAN(); 
		}
		this.canSubset = argument;
	}

	public TsCAD getDeclarant() {
		return declarant;	
	}
	public void setDeclarant(TsCAD argument) {
		if (argument == null) {
			return;
		}
		if (declarant == null) {
			declarant = new TsCAD();
		}
		this.declarant = argument;
	}	

	public TsCAD getConsignor() {
		return consignor;	
	}
	public void setConsignor(TsCAD argument) {
		if (argument == null) {
			return;
		}
		if (consignor == null) {
			consignor = new TsCAD();
		}
		this.consignor = argument;
	}
	
	public TsCAD getConsignee() {
		return consignee;	
	}
	public void setConsignee(TsCAD argument) {
		if (argument == null) {
			return;
		}
		if (consignee == null) {
			consignee = new TsCAD();
		}
		this.consignee = argument;
	}			

	public TsCAI getCaiSubset() {
		return caiSubset;
	}
	public void setCaiSubset(TsCAI argument) {
		if (argument == null) {
			return;
		}
		if (caiSubset == null) {
			caiSubset = new TsCAI();
		}
		this.caiSubset = argument;
	}

	public TsCAD getConsignorSecurity() {
		return consignorSecurity;
	}
	public void setConsignorSecurity(TsCAD argument) {
		if (argument == null) {
			return;
		}
		if (consignorSecurity == null) { 	
			consignorSecurity = new TsCAD();
		}
		this.consignorSecurity = argument;
	}	
	
	public TsCAD getConsigneeSecurity() {
		return consigneeSecurity;
	}
	public void setConsigneeSecurity(TsCAD argument) {
		if (argument == null) {
			return;
		}
		if (consigneeSecurity == null) { 	
			consigneeSecurity = new TsCAD();
		}
		this.consigneeSecurity = argument;
	}	
	
	public TsCAD getCarrier() {
		return carrier;
	}
	public void setCarrier(TsCAD argument) {
		if (argument == null) {
			return;
		}
		if (carrier == null) { 	
			carrier = new TsCAD();
		}
		this.carrier = argument;
	}	

	public List<TsCCN> getCcnList() {
		return ccnList;
	}
	public void setCcnList(List<TsCCN> argument) {
		if (argument == null) {
			return;
		}		
		if (ccnList == null) {
			ccnList = new Vector<TsCCN>();
		}
		this.ccnList = argument;
	}	
	public void addCcnList(TsCCN argument) {
		if (argument == null) {
			return;
		}		
		if (ccnList == null) {
			ccnList = new Vector<TsCCN>();
		}
		this.ccnList.add(argument);
	}	
		
	public List<TsCVP> getCvpList() {
		return cvpList;
	}
	public void setCvpList(List<TsCVP> argument) {
		if (argument == null) {
			return;
		}		
		if (cvpList == null) {
			cvpList = new Vector<TsCVP>();
		}
		this.cvpList = argument;
	}		
	public void addCvpList(TsCVP argument) {
		if (argument == null) {
			return;
		}		
		if (cvpList == null) {
			cvpList = new Vector<TsCVP>();
		}
		this.cvpList.add(argument);
	}	
	
	public List<MsgCANPos> getCanPosList() {
		return canPosList;	
	}

	public void setCanPosList(List<MsgCANPos> list) {
		this.canPosList = list;
	}

	public void addCanPosList(MsgCANPos argument) {
		if (argument == null) {
			return;			
		}
		if (canPosList == null) {
			canPosList = new Vector<MsgCANPos>();
		}
		this.canPosList.add(argument);
	}

	public List<TsCVI> getCviList() {
		return cviList;
	}
	public void setCviList(List<TsCVI> argument) {		
		this.cviList = argument;
	}	
	public void addCviList(TsCVI argument) {
		if (argument == null) {
			return;
		}		
		if (cviList == null) {
			cviList = new Vector<TsCVI>();
		}
		this.cviList.add(argument);
	}	
	
	public List<TsCVM> getCvmList() {
		return cvmList;
	}
	public void setCvmList(List<TsCVM> argument) {		
		this.cvmList = argument;
	}	
	public void addCvmList(TsCVM argument) {
		if (argument == null) {
			return;
		}		
		if (cvmList == null) {
			cvmList = new Vector<TsCVM>();
		}
		this.cvmList.add(argument);
	}		
	
//--------------------	
	public String getFssString() throws FssException {	  //EI20121107
		return getFssString("");
	}
	
	public String getFssString(String firstTs) throws FssException {
		String res = "";
		String kind = "";
		
		if (firstTs != null && firstTs.equalsIgnoreCase("HEAD")) {
			if (headSubset != null && !headSubset.isEmpty()) {		 
				res = appendString(res, headSubset.teilsatzBilden());	
			}
		} else {
			if (vorSubset != null && !vorSubset.isEmpty()) {		 
				res = appendString(res, vorSubset.teilsatzBilden());	
			}
		}		
		if (canSubset != null) {	
			kind = canSubset.getDklart();
			res = appendString(res, canSubset.teilsatzBilden());
		}
		if (caiSubset != null && !caiSubset.isEmpty()) {		
			res = appendString(res, caiSubset.teilsatzBilden());
		}
		if (consignor != null && !consignor.isEmpty()) {
			res = appendString(res, consignor.teilsatzBilden());
		}
		if (consignee != null && !consignee.isEmpty()) {
			res = appendString(res, consignee.teilsatzBilden());	
		}
		if (declarant != null && !declarant.isEmpty()) {
			res = appendString(res, declarant.teilsatzBilden());
		}
		if (consignorSecurity != null && !consignorSecurity.isEmpty()) {
			res = appendString(res, consignorSecurity.teilsatzBilden());
		}
		if (consigneeSecurity != null && !consigneeSecurity.isEmpty()) {
			res = appendString(res, consigneeSecurity.teilsatzBilden());	
		}
		if (carrier != null && !carrier.isEmpty()) {
			res = appendString(res, carrier.teilsatzBilden());
		}
		if (kind.equals("D") || kind.equals("I")) {
			if (ccnList != null) {			
				res = res + writeCcnList(ccnList);	
			}	
		}
		if (kind.equals("D")) {
			if (cvpList != null) {			
				res = res + writeCvpList(cvpList);	
			}	
		}
		if (kind.equals("I")) {
			if (cviList != null) {				
				res = res + writeCviList(cviList);	
			}			
			if (cvmList != null) {			
				res = res + writeCvmList(cvmList);	
			}
				
		}		
		if (canPosList != null) {
			res = res + writeCanPosList(kind);			
		}

		return res;	
	}

	private String writeCanPosList(String kind) throws FssException {
		String stb = "";
		
		if (canPosList != null) {								
			for (MsgCANPos pos : canPosList) {							
				TsCPO cpoSubset = pos.getCpoSubset();
				if (cpoSubset != null && !cpoSubset.isEmpty()) {
					
					stb = appendString(stb, cpoSubset.teilsatzBilden());
					if (kind.equals("I")) {
						stb = appendString(stb, pos.getCpzSubset().teilsatzBilden());
					}
					stb = stb + writeCclList(pos.getCclList());
					if (kind.equals("D") || kind.equals("I")) {
						stb = stb + writeCbwList(pos.getCbwList());
					}
					stb = stb + writeCunList(pos.getCunList());	
					if (kind.equals("A")) {
						stb = stb + writeCvaList(pos.getCvaList());
					}
					if (kind.equals("I")) {
						stb = stb + writeCvmList(pos.getCvmList());	
						stb = stb + writeCmdList(pos.getCmdList());
						stb = stb + writeCnzList(pos.getCnzList());
						if (pos.getCveSubset() != null && !pos.getCveSubset().isEmpty()) {
							stb = appendString(stb, pos.getCveSubset().teilsatzBilden());	
						}
						stb = stb + writeCstList(pos.getCstList());
						stb = stb + writeCsgList(pos.getCsgList());
					}					
				}
			}
		}
		return stb;
	}
	
	private String writeCcnList(List <TsCCN> list) throws FssException {				
		String stb = "";
		
		if (list != null)  {			
			for (TsCCN ccnSubset : list) {				
				if (ccnSubset != null && !ccnSubset.isEmpty()) {
					stb = appendString(stb, ccnSubset.teilsatzBilden());
				}
			}
		}		
		return stb;	
	}
	
	private String writeCvmList(List <TsCVM> list)throws FssException {
		String stb = "";
		
		if (list != null) {			
			for (TsCVM cvmSubset : list) {				
				if (cvmSubset != null && !cvmSubset.isEmpty()) {
					stb = appendString(stb, cvmSubset.teilsatzBilden());
				}
			}
		}		
		return stb;
	}
	private String writeCvpList(List <TsCVP> list)throws FssException {
		String stb = "";
	
		if (list != null) {			
			for (TsCVP cvpSubset : list) {				
				if (cvpSubset != null && !cvpSubset.isEmpty()) {
					stb = appendString(stb, cvpSubset.teilsatzBilden());
				}
			}
		}		
		return stb;
	}
	private String writeCviList(List <TsCVI> list)throws FssException {
		String stb = "";
	
		if (list != null) {			
			for (TsCVI cviSubset : list) {				
				if (cviSubset != null && !cviSubset.isEmpty()) {
					stb = appendString(stb, cviSubset.teilsatzBilden());
				}
			}
		}		
		return stb;
	}
	private String writeCclList(List <TsCCL> list) throws FssException {				
		String stb = "";
		
		if (list != null)  {			
			for (TsCCL cclSubset : list) {				
				if (cclSubset != null && !cclSubset.isEmpty()) {
					stb = appendString(stb, cclSubset.teilsatzBilden());
				}
			}
		}		
		return stb;	
	}

	private String writeCbwList(List <TsCBW> list) throws FssException {	
		String stb = "";
		
		if (list != null)  {			
			for (TsCBW cbw : list) {				 
				if (cbw != null && !cbw.isEmpty()) {
					stb = appendString(stb, cbw.teilsatzBilden());
				}
			}
		}
		return stb;
	}

	
	private String writeCunList(List <TsCUN> list) throws FssException {		
		String stb = "";
		
		if (list != null)  {			
			for (TsCUN cun : list) {			
				if (cun != null && !cun.isEmpty()) {
					stb = appendString(stb, cun.teilsatzBilden());
				}
			}
		}		
		return stb;		
	}	
	
	private String writeCmdList(List <TsCMD> list) throws FssException {
		String stb = "";
		
		if (list != null) {			
			for (TsCMD cmdSubset : list) {				
				if (cmdSubset != null && !cmdSubset.isEmpty()) {
					stb = appendString(stb, cmdSubset.teilsatzBilden());
				}
			}
		}		
		return stb;
	}
	private String writeCnzList(List <TsCNZ> list) throws FssException {
		String stb = "";
		
		if (list != null) {			
			for (TsCNZ cnzSubset : list) {				
				if (cnzSubset != null && !cnzSubset.isEmpty()) {
					stb = appendString(stb, cnzSubset.teilsatzBilden());
				}
			}
		}			
		return stb;
	}
	private String writeCstList(List <TsCST> list) throws FssException {
		String stb = "";
		
		if (list != null) {			
			for (TsCST cstSubset : list) {				
				if (cstSubset != null && !cstSubset.isEmpty()) {
					stb = appendString(stb, cstSubset.teilsatzBilden());
				}
			}
		}			
		return stb;
	}
	private String writeCsgList(List <TsCSG> list) throws FssException {
		String stb = "";
		
		if (list != null) {			
			for (TsCSG csgSubset : list) {				
				if (csgSubset != null && !csgSubset.isEmpty()) {
					stb = appendString(stb, csgSubset.teilsatzBilden());
				}
			}
		}			
		return stb;
	}
	
	private String writeCvaList(List <TsCVA> list) throws FssException {
		String stb = "";
		
		if (list != null) {			
			for (TsCVA cvaSubset : list) {				
				if (cvaSubset != null && !cvaSubset.isEmpty()) {
					stb = appendString(stb, cvaSubset.teilsatzBilden());
				}
			}
		}			
		return stb;
	}
}
