package com.kewill.kcx.component.mapping.formats.fss.ncts.messages.V70;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.ncts.messages.FssMessage62;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.V70.TsVPH;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVPA;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVPC;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVPD;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVPE;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVPL;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.V70.TsVPP;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVPU;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVPV;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: MsgVPH<br>
 * Erstellt		: 2010.09.03<br>
 * Beschreibung	: FSS Rückgabe der Entladeerlaubnis .
 * 
 * @author iwaniuk
 * @version 7.0.00
 */
public class MsgVPH extends FssMessage62 {

	private TsVOR				vorSubset;
	private TsHead	            headSubset;
	private TsVPH				vphSubset;  //V70 changes
	private List<TsVPV>			vpvSubsetList;	
	private TsVPA				vpaConsignee;
	private TsVPA				vpaConsignor;
	private TsVPA				vpaPrincipal;
	
	private List<MsgVPHPos>		posList;
	
	public String getFssString() throws FssException {
		return getFssString("");
	}
	public String getFssString(String firstTs) throws FssException {
		String res = "";
		
		if (firstTs != null && firstTs.equalsIgnoreCase("HEAD")) {
			headSubset.mapVor2Head(vorSubset);
			if (headSubset != null && !headSubset.isEmpty()) {		 
				res = appendString(res, headSubset.teilsatzBilden());
			}
		} else {			
			if (vorSubset != null && !vorSubset.isEmpty()) {	 
				res = appendString(res, vorSubset.teilsatzBilden());	
			}
		}
				
		if (vphSubset != null) {	//EI20111011 im Hauptsatz keine isEmpty Abfrage
			res = appendString(res, vphSubset.teilsatzBilden());
		}
		if (vpvSubsetList != null) {
			for (TsVPV vpvSubset : vpvSubsetList) {
				if (vpvSubset != null && !vpvSubset.isEmpty()) {
					res = appendString(res, vpvSubset.teilsatzBilden());
				}
			}
		}
		if (vpaConsignor != null && !vpaConsignor.isEmpty()) {
			res = appendString(res, vpaConsignor.teilsatzBilden());
		}
		
		if (vpaConsignee != null && !vpaConsignee.isEmpty()) {
			res = appendString(res, vpaConsignee.teilsatzBilden());
		}
		
		if (vpaPrincipal != null && !vpaPrincipal.isEmpty()) {
			res = appendString(res, vpaPrincipal.teilsatzBilden());
		}
		
	    if (posList != null) {
	    	for (MsgVPHPos msgVPHPos : posList) {	    		
	    		if (msgVPHPos != null) {	
	    		
	    			if (msgVPHPos.getVppSubset() != null) {	    				
	    				res = appendString(res, msgVPHPos.getVppSubset().teilsatzBilden());	    				
	    			}
			
	    			if (msgVPHPos.getVpdConsignor() != null && !msgVPHPos.getVpdConsignor().isEmpty()) {
	    				res = appendString(res, msgVPHPos.getVpdConsignor().teilsatzBilden());
	    			}
			
	    			if (msgVPHPos.getVpdConsignee() != null && !msgVPHPos.getVpdConsignee().isEmpty()) {
	    				res = appendString(res, msgVPHPos.getVpdConsignee().teilsatzBilden());
	    			}
			
	    			if (msgVPHPos.getVplList() != null) {
	    				for (TsVPL vplSubset : msgVPHPos.getVplList()) {
	    					if (vplSubset != null && !vplSubset.isEmpty()) {
	    						res = appendString(res, vplSubset.teilsatzBilden());
	    					}
	    				}
	    			}
			
	    			if (msgVPHPos.getVpuList() != null) {
	    				for (TsVPU vpuSubset : msgVPHPos.getVpuList()) {
	    					if (vpuSubset != null && !vpuSubset.isEmpty()) {
	    						res = appendString(res, vpuSubset.teilsatzBilden());
	    					}
	    				}
	    			}
	    			if (msgVPHPos.getVpcList() != null) {
	    				for (TsVPC vpcSubset : msgVPHPos.getVpcList()) {
	    					if (vpcSubset != null && !vpcSubset.isEmpty()) {
	    						res = appendString(res, vpcSubset.teilsatzBilden());
	    					}
	    				}
	    			}
	    			
	    			if (msgVPHPos.getVpeList() != null) {	
	    				for (TsVPE vpeSubset : msgVPHPos.getVpeList()) {
	    					if (!vpeSubset.isEmpty()) {
	    						res = appendString(res, vpeSubset.teilsatzBilden());
	    					}
	    				}
	    			}
	    			
	    		}
	    	}
	    }
		return res;
	}
	
	public void readMessage(BufferedReader message) throws FssException {
		try {
			String line = "";
			
			TsVPD					vpdConsignor 	= null;
			TsVPD					vpdConsignee 	= null;
			boolean					vppStart 		= false;
			boolean					vppEnd			= false;
			MsgVPHPos				msgVPHPos		= new MsgVPHPos();
			
			while ((line = message.readLine()) != null) {
				String lineType = "";
				if (line.length() > 3) {
					lineType = line.substring(0, 3);
					Utils.log("linetype " + lineType);
				}
				if (lineType.equalsIgnoreCase("VPH")) {
					vphSubset = new TsVPH();
					String[] vph = line.split("" + FssUtils.FSS_FS);
					vphSubset.setFields(vph);
				} else if (lineType.equalsIgnoreCase("VPV")) {
					TsVPV vpvSubset = new TsVPV();
					String[] vpv = line.split("" + FssUtils.FSS_FS);
					vpvSubset.setFields(vpv);
					
					if (vpvSubsetList == null) {
						vpvSubsetList = new Vector<TsVPV>();
					}
					vpvSubsetList.add(vpvSubset);
				}  else if (lineType.equalsIgnoreCase("VPA")) {
					String vpaType = getFssSubType(line, 1);
					if (vpaType.equalsIgnoreCase("CZ")) {
						vpaConsignor = new TsVPA();
						String[] vpa = line.split("" + FssUtils.FSS_FS);
						vpaConsignor.setFields(vpa);
					} else if (vpaType.equalsIgnoreCase("CN")) {
						vpaConsignee = new TsVPA();
						String[] vpa = line.split("" + FssUtils.FSS_FS);
						vpaConsignee.setFields(vpa);
					} else if (vpaType.equalsIgnoreCase("AF")) {
						vpaPrincipal = new TsVPA();
						String[] vpa = line.split("" + FssUtils.FSS_FS);
						vpaPrincipal.setFields(vpa);
					}
				} else if (lineType.equalsIgnoreCase("VPP")) {
					if (vppStart && vppEnd) {
						if (posList == null) {
							posList = new Vector<MsgVPHPos>();
						}
						posList.add(msgVPHPos);
						msgVPHPos = new MsgVPHPos();
						vppStart = false;
						vppEnd = false;
					}
					vppStart = true;
					TsVPP vppSubset = new TsVPP();
					String[] vpp = line.split("" + FssUtils.FSS_FS);
					vppSubset.setFields(vpp);					
					msgVPHPos.setVppSubset(vppSubset);
				}  else if (lineType.equalsIgnoreCase("VPD")) {
					String vpdType = getFssSubType(line, 2);
					if (vpdType.equalsIgnoreCase("1")) {
						vpdConsignor = new TsVPD();
						String[] vpd = line.split("" + FssUtils.FSS_FS);
						vpdConsignor.setFields(vpd);
						msgVPHPos.setVpdConsignor(vpdConsignor);
					} else if (vpdType.equalsIgnoreCase("2")) {
						vpdConsignee = new TsVPD();
						String[] vpd = line.split("" + FssUtils.FSS_FS);
						vpdConsignee.setFields(vpd);
						msgVPHPos.setVpdConsignee(vpdConsignee);
					}
				}  else if (lineType.equalsIgnoreCase("VPL")) {
					TsVPL vplSubset = new TsVPL();
					String[] vpl = line.split("" + FssUtils.FSS_FS);
					vplSubset.setFields(vpl);
					
					msgVPHPos.addVplList(vplSubset);
				}  else if (lineType.equalsIgnoreCase("VPU")) {
					TsVPU vpuSubset = new TsVPU();
					String[] vpu = line.split("" + FssUtils.FSS_FS);
					vpuSubset.setFields(vpu);
					
					msgVPHPos.addVpuList(vpuSubset);
				}  else if (lineType.equalsIgnoreCase("VPC")) {
					TsVPC vpcSubset = new TsVPC();
					String[] vpc = line.split("" + FssUtils.FSS_FS);
					vpcSubset.setFields(vpc);
					
					msgVPHPos.addVpcList(vpcSubset);
				}  else if (lineType.equalsIgnoreCase("VPE")) {
					vppEnd = true;
					TsVPE vpeSubset = new TsVPE();
					String[] vpe = line.split("" + FssUtils.FSS_FS);
					vpeSubset.setFields(vpe);
					
					msgVPHPos.addVpeList(vpeSubset);
				}  else if (lineType.equalsIgnoreCase("NAC")) {
					Utils.log("ignore linetype NAC");
				} else if (lineType.equals("")) {
					Utils.log("empty line in FSS-message");
				} else {
					throw new FssException("Wrong message line " + lineType);
				}
			}
			if (posList == null) {
				posList = new Vector<MsgVPHPos>();
			}
			posList.add(msgVPHPos);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getFssSubType(String line, int index) {
		String[] subType = line.split("" + FssUtils.FSS_FS);
		return subType[index];
	}

	public TsVOR getVorSubset() {
		return vorSubset;
	}
	public void setVorSubset(TsVOR vorSubset) {
		this.vorSubset = vorSubset;
	}

	public TsHead getHeadSubset() {
		return headSubset;
	}
	public void setHeadSubset(TsHead head) {
		this.headSubset = head;
	}
	
	public TsVPH getVphSubset() {
		return vphSubset;
	}

	public void setVphSubset(TsVPH vphSubset) {
		this.vphSubset = vphSubset;
	}

	public TsVPA getVpaConsignee() {
		return vpaConsignee;
	}

	public void setVpaConsignee(TsVPA vpaConsignee) {
		this.vpaConsignee = vpaConsignee;
	}

	public TsVPA getVpaConsignor() {
		return vpaConsignor;
	}

	public void setVpaConsignor(TsVPA vpaConsignor) {
		this.vpaConsignor = vpaConsignor;
	}

	public TsVPA getVpaPrincipal() {
		return vpaPrincipal;
	}

	public void setVpaPrincipal(TsVPA vpaPrincipal) {
		this.vpaPrincipal = vpaPrincipal;
	}

	public List<TsVPV> getVpvSubsetList() {
		return this.vpvSubsetList;
	}

	public void addVpvSubsetList(TsVPV vpvSubset) {
		if (vpvSubset == null) {
			return;
		}
		
		if (vpvSubsetList == null) {
			vpvSubsetList = new Vector<TsVPV>();
		}
		
		this.vpvSubsetList.add(vpvSubset);
	}

	public List<MsgVPHPos> getPosList() {
		return posList;
	}

	public void setPosList(List<MsgVPHPos> posList) {
		this.posList = posList;
	}
	
	public void addPosList(MsgVPHPos argument) {
		if (argument == null) {
			return;
		}
		if (posList == null) {
			posList = new Vector<MsgVPHPos>();
		}
		this.posList.add(argument);
	}
	
}

