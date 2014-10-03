/*
 * Modul    	: KidsToUids.java
 * Titel       	:
 * Date        	: 17.10.2008
 * @author      : Kewill CSF / Christine Kron
 * Description 	: Contains the Message ExportDeclaration in the ZABIS FSS-Format  
 * 			    : Version 6.0
 * Parameters  	:
 *  
 * Changes 
 * -----------
 * Author      : EI
 * Date        : 05.05.2009
 * Label       : EI20090505
 * Description : removed argument "typ" from setConsignee(...) aso.
 *
 * Author      : EI
 * Date        : 14.08.2009
 * Label       : EI20090814
 * Description : BAV, BZL 
 */

package com.kewill.kcx.component.mapping.formats.fss.ncts.messages;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVPA;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVPC;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVPD;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVPE;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVPH;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVPL;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVPP;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVPU;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVPV;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;

import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: MsgVPH<br>
 * Erstellt		: 2010.09.03<br>
 * Beschreibung	: FSS Message for VPH.
 * 
 * @author Frederick T.
 * @version 6.0.00
 */
public class MsgVPH extends FssMessage62 {

	private TsVOR				vorSubset;
	private TsVPH				vphSubset;
	private List<TsVPV>			vpvSubsetList;
	
	private TsVPA				vpaConsignee;
	private TsVPA				vpaConsignor;
	private TsVPA				vpaPrincipal;
	
	private List<MsgVPPPos>		posList;
	
	public String getFssString() throws FssException {
		String res = "";
		
		res = appendString(res, vorSubset.teilsatzBilden());
		
		//if (vphSubset != null && !vphSubset.isEmpty()) {
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
	    	for (MsgVPPPos msgVPPPos : posList) {	    		
	    		if (msgVPPPos != null) {	
	    		
	    			if (msgVPPPos.getVppList() != null) {
	    				for (TsVPP vppSubset : msgVPPPos.getVppList()) {
	    					if (!vppSubset.isEmpty()) {
	    						res = appendString(res, vppSubset.teilsatzBilden());
	    					}
	    				}
	    			}
			
	    			if (msgVPPPos.getVpdConsignor() != null && !msgVPPPos.getVpdConsignor().isEmpty()) {
	    				res = appendString(res, msgVPPPos.getVpdConsignor().teilsatzBilden());
	    			}
			
	    			if (msgVPPPos.getVpdConsignee() != null && !msgVPPPos.getVpdConsignee().isEmpty()) {
	    				res = appendString(res, msgVPPPos.getVpdConsignee().teilsatzBilden());
	    			}
			
	    			if (msgVPPPos.getVplList() != null) {
	    				for (TsVPL vplSubset : msgVPPPos.getVplList()) {
	    					if (vplSubset != null && !vplSubset.isEmpty()) {
	    						res = appendString(res, vplSubset.teilsatzBilden());
	    					}
	    				}
	    			}
			
	    			if (msgVPPPos.getVpuList() != null) {
	    				for (TsVPU vpuSubset : msgVPPPos.getVpuList()) {
	    					if (vpuSubset != null && !vpuSubset.isEmpty()) {
	    						res = appendString(res, vpuSubset.teilsatzBilden());
	    					}
	    				}
	    			}
	    			if (msgVPPPos.getVpcList() != null) {
	    				for (TsVPC vpcSubset : msgVPPPos.getVpcList()) {
	    					if (vpcSubset != null && !vpcSubset.isEmpty()) {
	    						res = appendString(res, vpcSubset.teilsatzBilden());
	    					}
	    				}
	    			}
	    			
	    			if (msgVPPPos.getVpeList() != null) {	
	    				for (TsVPE vpeSubset : msgVPPPos.getVpeList()) {
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
			MsgVPPPos				msgVPPPos		= new MsgVPPPos();
			
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
							posList = new Vector<MsgVPPPos>();
						}
						posList.add(msgVPPPos);
						msgVPPPos = new MsgVPPPos();
						vppStart = false;
						vppEnd = false;
					}
					vppStart = true;
					TsVPP vppSubset = new TsVPP();
					String[] vpp = line.split("" + FssUtils.FSS_FS);
					vppSubset.setFields(vpp);
					
					msgVPPPos.addVppList(vppSubset);
				}  else if (lineType.equalsIgnoreCase("VPD")) {
					String vpdType = getFssSubType(line, 2);
					if (vpdType.equalsIgnoreCase("1")) {
						vpdConsignor = new TsVPD();
						String[] vpd = line.split("" + FssUtils.FSS_FS);
						vpdConsignor.setFields(vpd);
						msgVPPPos.setVpdConsignor(vpdConsignor);
					} else if (vpdType.equalsIgnoreCase("2")) {
						vpdConsignee = new TsVPD();
						String[] vpd = line.split("" + FssUtils.FSS_FS);
						vpdConsignee.setFields(vpd);
						msgVPPPos.setVpdConsignee(vpdConsignee);
					}
				}  else if (lineType.equalsIgnoreCase("VPL")) {
					TsVPL vplSubset = new TsVPL();
					String[] vpl = line.split("" + FssUtils.FSS_FS);
					vplSubset.setFields(vpl);
					
					msgVPPPos.addVplList(vplSubset);
				}  else if (lineType.equalsIgnoreCase("VPU")) {
					TsVPU vpuSubset = new TsVPU();
					String[] vpu = line.split("" + FssUtils.FSS_FS);
					vpuSubset.setFields(vpu);
					
					msgVPPPos.addVpuList(vpuSubset);
				}  else if (lineType.equalsIgnoreCase("VPC")) {
					TsVPC vpcSubset = new TsVPC();
					String[] vpc = line.split("" + FssUtils.FSS_FS);
					vpcSubset.setFields(vpc);
					
					msgVPPPos.addVpcList(vpcSubset);
				}  else if (lineType.equalsIgnoreCase("VPE")) {
					vppEnd = true;
					TsVPE vpeSubset = new TsVPE();
					String[] vpe = line.split("" + FssUtils.FSS_FS);
					vpeSubset.setFields(vpe);
					
					msgVPPPos.addVpeList(vpeSubset);
				}  else if (lineType.equalsIgnoreCase("NAC")) {
					Utils.log("ignore linetype NAC");
				} else if (lineType.equals("")) {
					Utils.log("empty line in FSS-message");
				} else {
					throw new FssException("Wrong message line " + lineType);
				}
			}
			if (posList == null) {
				posList = new Vector<MsgVPPPos>();
			}
			posList.add(msgVPPPos);
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

	public List<MsgVPPPos> getPosList() {
		return posList;
	}

	public void setPosList(List<MsgVPPPos> posList) {
		this.posList = posList;
	}
	
	public void addPosList(MsgVPPPos argument) {
		if (argument == null) {
			return;
		}
		if (posList == null) {
			posList = new Vector<MsgVPPPos>();
		}
		this.posList.add(argument);
	}
	
}

