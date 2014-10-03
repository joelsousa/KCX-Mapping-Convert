/*
 * Funktion    : MsgCAN.java
 * Titel       :
 * Erstellt    : 20.10.2008
 * Author      : Alfred Krzoska
 * Beschreibung: ExportDeclaration KIDS2Fss EDEC
 * Anmerkungen :
 * Parameter   :
 * Rückgabe    : keine
 * Aufruf      :
 *
 *
 * Changes 
 * -----------
 * Author      : krzoska 
 * Date        : 22.03.2010
 * Label       : AK20100322 
 * Description : 
 *
 * Changes 
 * -----------
 * Author      : krzoska 
 * Date        : 23.03.2010
 * Label       : AK20100323 
 * Description : no output of empty TsCVP
 *
 * Changes 
 * -----------
 * Author      :  
 * Date        : 
 * Label       :  
 * Description : 
 *
 */

package com.kewill.kcx.component.mapping.formats.fss.edec.aus.messages;

import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.formats.fss.common.FssDatei;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCAD;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCAI;
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
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: MsgCAN.<br>
 * Erstellt		: 20.10.2008<br>
 * Beschreibung : ExportDeclaration KIDS2Fss EDEC 
 * 
 * @author krzoska
 * @version 1.1.00
 */
public class MsgCAN extends FssDatei {

	private TsVOR	vorSubset;
	private TsCAN   canSubset = null;
	private TsCAI   caiSubset = null;
	private TsCAD 	exporter = null;
	private TsCAD 	declarant = null;
	
	private TsCAD 	consignor = null;
	private TsCAD 	consignee = null;
	private TsCAD	forwarder = null;
	private TsCAD	consignorSecurity = null;
	private TsCAD	consigneeSecurity = null;
	private TsCAD	carrier			  = null;
	private List <TsCCN> ccnList;                //list of TsCCN, TsCCN consist 9 ContainerNumber
	private TsCVP cvpSubset; 	                      //AK20110314 not used for Export  
	private List <TsCVI> cviList;                 //EI20110429 new
	private List <TsCVM> cvmList;                 //EI20110429 new 
	private List <MsgCANPos >canPosList;
	

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

	public TsCAD getExporter() {
		return exporter;	
	}
	public void setExporter(TsCAD argument) {
		if (argument == null) {
			return;
		}
		if (exporter == null) {
			exporter = new TsCAD();
		}
		this.exporter = argument;
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

	public TsCAD getForwarder() {
		return forwarder;
	}
	public void setForwarder(TsCAD argument) {
		if (argument == null) {
			return;
		}
		if (forwarder == null) {
			forwarder = new TsCAD();
		}
		this.forwarder = argument;
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
	
	
	public TsCVP getCvpSubset() {
		return cvpSubset;	
	}
	public void setCvpSubset(TsCVP argument) {		
		this.cvpSubset = argument;
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
	
	public String writeCAN(String msgTyp) throws FssException {		
		StringBuffer res = new StringBuffer();	
		
		if (vorSubset != null) { 		 
			res.append(vorSubset.teilsatzBilden() + Utils.LF);
		}
		if (canSubset != null) {		
			res.append(canSubset.teilsatzBilden() + Utils.LF);
		}
		if (caiSubset != null && !caiSubset.isEmpty()) {		
			res.append(caiSubset.teilsatzBilden() + Utils.LF);
		}
		if (consignor != null && !consignor.isEmpty()) {
			res.append(consignor.teilsatzBilden() + Utils.LF);
		}
		if (consignee != null && !consignee.isEmpty()) {
			res.append(consignee.teilsatzBilden() + Utils.LF);	
		}
		if (ccnList != null) {
			res.append(writeCcnList());
		}
		if (msgTyp.equals("A") || msgTyp.equals("D")) {
			if (cvpSubset != null && !cvpSubset.isEmpty()) {
				res.append(cvpSubset.teilsatzBilden() + Utils.LF);	
			}
		}
		if (msgTyp.equals("I")) {		
			if (cviList != null) {
				res.append(writeCviList());
			}		
			if (cvmList != null) {
				res.append(writeCvmList(this.cvmList));
			}
		}
		
		if (canPosList != null) {
			res.append(writeCanPosList("I"));
		}

		return res.toString(); 	
	}

	private String writeCanPosList(String msgTyp) throws FssException {
		StringBuffer stb = new StringBuffer();
		if (canPosList != null) {					
			int size = canPosList.size();
		
			for (int i = 0; i < size; i++) {			
				MsgCANPos pos = canPosList.get(i);	
			
				TsCPO cpoSubset = pos.getCpoSubset();
				if (cpoSubset != null && !cpoSubset.isEmpty()) {
					stb.append(cpoSubset.teilsatzBilden() + Utils.LF);	
					if (msgTyp.equals("I") && pos.getCpzSubset() != null && !pos.getCpzSubset().isEmpty()) {
						stb.append(pos.getCpzSubset().teilsatzBilden() + Utils.LF);	
					}					
					stb.append(writeCclList(pos.getCclList()));
					stb.append(writeCbwList(pos.getCbwList()));
					stb.append(writeCunList(pos.getCunList()));
					if (msgTyp.equals("A")) {
						stb.append(writeCvaList(pos.getCvaList()));
					}
					if (msgTyp.equals("A") || msgTyp.equals("I")) {
						stb.append(writeCvmList(pos.getCvmList()));
					}
					if (msgTyp.equals("I")) {
						stb.append(writeCmdList(pos.getCmdList()));
						stb.append(writeCnzList(pos.getCnzList()));
						if (pos.getCveSubset() != null && !pos.getCveSubset().isEmpty()) {
							stb.append(pos.getCveSubset().teilsatzBilden() + Utils.LF);	
						}
						stb.append(writeCstList(pos.getCstList()));
						stb.append(writeCsgList(pos.getCsgList()));
					}
				}
			}
		}
		return stb.toString();
	}
	
	private String writeCcnList() throws FssException {
		StringBuffer stb = new StringBuffer();
		TsCCN ccnSubset = new TsCCN();
		if (ccnList != null) {
			int size = ccnList.size();
			for (int i = 0; i < size; i++) {
				ccnSubset = ccnList.get(i);
				if (ccnSubset != null && !ccnSubset.isEmpty()) {
					stb.append(ccnSubset.teilsatzBilden() + Utils.LF);
				}
			}
		}		
		return stb.toString();
	}
	
	private String writeCviList() throws FssException {
		StringBuffer stb = new StringBuffer();
		TsCVI cviSubset = new TsCVI();
		if (cviList != null) {
			int size = cviList.size();
			for (int i = 0; i < size; i++) {
				cviSubset = cviList.get(i);
				if (cviSubset != null && !cviSubset.isEmpty()) {
					stb.append(cviSubset.teilsatzBilden() + Utils.LF);
				}
			}
		}		
		return stb.toString();
	}
	private String writeCvmList(List <TsCVM> list)throws FssException {
		StringBuffer stb = new StringBuffer();
		TsCVM cvmSubset = new TsCVM();
		if (list != null) {
			int size = list.size();
			for (int i = 0; i < size; i++) {
				cvmSubset = list.get(i);
				if (cvmSubset != null && !cvmSubset.isEmpty()) {
					stb.append(cvmSubset.teilsatzBilden() + Utils.LF);
				}
			}
		}		
		return stb.toString();
	}
	private String writeCclList(List <TsCCL> list) throws FssException {				
		StringBuffer stb = new StringBuffer();
		
		if (list != null)  {
			int size = list.size();
			for (int i = 0; i < size; i++) {
				TsCCL cclSubset = list.get(i);
				if (cclSubset != null && !cclSubset.isEmpty()) {
					stb.append(cclSubset.teilsatzBilden() + Utils.LF);
				}
			}
		}		
		return stb.toString();		
	}

	private String writeCbwList(List <TsCBW> list) throws FssException {	
		StringBuffer stb = new StringBuffer();
		
		if (list != null)  {
			int size = list.size();
			for (int i = 0; i < size; i++) {
				TsCBW cbw = list.get(i);
				if (cbw != null && !cbw.isEmpty()) {
					stb.append(cbw.teilsatzBilden() + Utils.LF);
				}
			}
		}
		return stb.toString();
	}

	
	private String writeCunList(List <TsCUN> list) throws FssException {		
		StringBuffer stb = new StringBuffer();
		
		if (list != null)  {
			int size = list.size();
			for (int i = 0; i < size; i++) {
				TsCUN cun = list.get(i);
				if (cun != null && !cun.isEmpty()) {
					stb.append(cun.teilsatzBilden() + Utils.LF);
				}
			}
		}		
		return stb.toString();		
	}
	
	private String writeCvaList(List <TsCVA> list) throws FssException {		
		StringBuffer stb = new StringBuffer();
		
		if (list != null)  {
			int size = list.size();
			for (int i = 0; i < size; i++) {				
				TsCVA cva = list.get(i);
				if (cva != null && !cva.isEmpty()) {
					stb.append(cva.teilsatzBilden() + Utils.LF);
				}
			}
		}		
		return stb.toString();	
	}
	
	private String writeCmdList(List <TsCMD> list) throws FssException {
		StringBuffer stb = new StringBuffer();
		TsCMD cmdSubset = new TsCMD();
		if (list != null) {
			int size = list.size();
			for (int i = 0; i < size; i++) {
				cmdSubset = list.get(i);
				if (cmdSubset != null && !cmdSubset.isEmpty()) {
					stb.append(cmdSubset.teilsatzBilden() + Utils.LF);
				}
			}
		}		
		return stb.toString();
	}
	private String writeCnzList(List <TsCNZ> list) throws FssException {
		StringBuffer stb = new StringBuffer();
		TsCNZ cnzSubset = new TsCNZ();
		if (list != null) {
			int size = list.size();
			for (int i = 0; i < size; i++) {
				cnzSubset = list.get(i);
				if (cnzSubset != null && !cnzSubset.isEmpty()) {
					stb.append(cnzSubset.teilsatzBilden() + Utils.LF);
				}
			}
		}			
		return stb.toString();
	}
	private String writeCstList(List <TsCST> list) throws FssException {
		StringBuffer stb = new StringBuffer();
		TsCST cstSubset = new TsCST();
		if (list != null) {
			int size = list.size();
			for (int i = 0; i < size; i++) {
				cstSubset = list.get(i);
				if (cstSubset != null && !cstSubset.isEmpty()) {
					stb.append(cstSubset.teilsatzBilden() + Utils.LF);
				}
			}
		}			
		return stb.toString();
	}
	private String writeCsgList(List <TsCSG> list) throws FssException {
		StringBuffer stb = new StringBuffer();
		TsCSG csgSubset = new TsCSG();
		if (list != null) {
			int size = list.size();
			for (int i = 0; i < size; i++) {
				csgSubset = list.get(i);
				if (csgSubset != null && !csgSubset.isEmpty()) {
					stb.append(csgSubset.teilsatzBilden() + Utils.LF);
				}
			}
		}			
		return stb.toString();
	}
}
