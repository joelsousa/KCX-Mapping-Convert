package com.kewill.kcx.component.mapping.formats.fss.ncts.messages.V70;

import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVPC;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVPD;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVPE;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVPL;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.V70.TsVPP;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVPU;

/**
 * Module		: MsgVPHPos<br>
 * Created		: 2010.09.07<br>
 * Description	: Position subsets to MsgVPH.
 * 
 * @author iwaniuk
 * @version 7.0.00
 */
public class MsgVPHPos {

	private TsVPP 			    vppSubset;  //V70 aenderungen
	private TsVPD				vpdConsignor;
	private TsVPD				vpdConsignee;
	private List<TsVPL>			vplList;
	private List<TsVPU>			vpuList;
	private List<TsVPC>			vpcList;
	private List<TsVPE>			vpeList;
	
	public MsgVPHPos() {
		
	}

	public TsVPP getVppSubset() {
		return vppSubset;
	}

	public void setVppSubset(TsVPP vpp) {
		this.vppSubset = vpp;
	}

	public TsVPD getVpdConsignor() {
		return vpdConsignor;
	}

	public void setVpdConsignor(TsVPD vpdConsignor) {
		this.vpdConsignor = vpdConsignor;
	}

	public TsVPD getVpdConsignee() {
		return vpdConsignee;
	}

	public void setVpdConsignee(TsVPD vpdConsignee) {
		this.vpdConsignee = vpdConsignee;
	}

	public List<TsVPL> getVplList() {
		return vplList;
	}

	public void setVplList(List<TsVPL> vplList) {
		this.vplList = vplList;
	}

	public List<TsVPU> getVpuList() {
		return vpuList;
	}

	public void setVpuList(List<TsVPU> vpuList) {
		this.vpuList = vpuList;
	}

	public List<TsVPC> getVpcList() {
		return vpcList;
	}

	public void setVpcList(List<TsVPC> vpcList) {
		this.vpcList = vpcList;
	}

	public List<TsVPE> getVpeList() {
		return vpeList;
	}

	public void setVpeList(List<TsVPE> vpeList) {
		this.vpeList = vpeList;
	}	
	
	public void addVplList(TsVPL tsVpl) {
		if (tsVpl == null) {
			return;
		}
		if (vplList == null) {
			vplList = new Vector<TsVPL>();
		}
		vplList.add(tsVpl);
	}
	
	public void addVpuList(TsVPU tsVpu) {
		if (tsVpu == null) {
			return;
		}
		if (vpuList == null) {
			vpuList = new Vector<TsVPU>();
		}
		vpuList.add(tsVpu);
	}
	
	public void addVpcList(TsVPC tsVpc) {
		if (tsVpc == null) {
			return;
		}
		if (vpcList == null) {
			vpcList = new Vector<TsVPC>();
		}
		vpcList.add(tsVpc);
	}
	
	public void addVpeList(TsVPE tsVpe) {
		if (tsVpe == null) {
			return;
		}
		if (vpeList == null) {
			vpeList = new Vector<TsVPE>();
		}
		vpeList.add(tsVpe);
	}

}
