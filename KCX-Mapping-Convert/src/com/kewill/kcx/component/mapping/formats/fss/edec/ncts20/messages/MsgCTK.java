package com.kewill.kcx.component.mapping.formats.fss.edec.ncts20.messages;

import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.formats.fss.common.FssDatei;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCCN;
import com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets.TsCVM;
import com.kewill.kcx.component.mapping.formats.fss.edec.ncts20.subsets.TsCTA;
import com.kewill.kcx.component.mapping.formats.fss.edec.ncts20.subsets.TsCTC;
import com.kewill.kcx.component.mapping.formats.fss.edec.ncts20.subsets.TsCTG;
import com.kewill.kcx.component.mapping.formats.fss.edec.ncts20.subsets.TsCTK;
import com.kewill.kcx.component.mapping.formats.fss.edec.ncts20.subsets.TsCTS;
import com.kewill.kcx.component.mapping.formats.fss.edec.ncts20.subsets.TsCTV;
import com.kewill.kcx.component.mapping.formats.fss.edec.ncts20.subsets.TsCTW;
import com.kewill.kcx.component.mapping.formats.fss.edec.ncts20.subsets.TsCTX;
import com.kewill.kcx.component.mapping.formats.fss.edec.ncts20.subsets.TsCTZ;

/**
 * Module        :   NCTS 70 
 * Created       :   07.06.2013
 * Description   :   MsgCTK Transitabmeldung.
 *  
 * @author         iwaniuk
 * @version        7.0.00
 */

public class MsgCTK extends FssDatei {

	private TsVOR	vorSubset;
	private TsHead	headSubset;
	private TsCTK   ctkSubset;   
	private TsCTA   ctaSubset;  
	private TsCTS   ctsSubset;  
	private TsCTX   ctxSubset;   
	private TsCTC   ctcSubset;   
	private TsCTG   ctgSubset;   
	private List <TsCTW> ctwList;                //max 99 
	private List <TsCTZ> ctzList;                //max 9 
	private List <TsCTV> ctvList;                //max 99 

	
	public MsgCTK(String outFileName) {
		super(outFileName);
		vorSubset = new TsVOR("");		
		//canPosList = new Vector<MsgCANPos>();
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

	public TsCTK getCtkSubset() {
		return ctkSubset;
	}
	public void setCtkSubset(TsCTK argument) {
		this.ctkSubset = argument;
	}
	
	public TsCTS getCtsSubset() {
		return ctsSubset;
	}
	public void setCtsSubset(TsCTS argument) {
		this.ctsSubset = argument;
	}
	
	public TsCTA getCtaSubset() {
		return ctaSubset;
	}
	public void setCtaSubset(TsCTA argument) {
		this.ctaSubset = argument;
	}
	
	public TsCTX getCtxSubset() {
		return ctxSubset;
	}
	public void setCtxSubset(TsCTX argument) {
		this.ctxSubset = argument;
	}
	
	public TsCTC getCtcSubset() {
		return ctcSubset;
	}
	public void setCtcSubset(TsCTC argument) {
		this.ctcSubset = argument;
	}
	
	public TsCTG getCtgSubset() {
		return ctgSubset;
	}
	public void setCtgSubset(TsCTG argument) {
		this.ctgSubset = argument;
	}
		
	public List<TsCTW> getCtwList() {
		return ctwList;
	}
	public void setCtwList(List<TsCTW> argument) {		
		this.ctwList = argument;
	}	
	public void addCtwList(TsCTW argument) {
		if (argument == null) {
			return;
		}		
		if (ctwList == null) {
			ctwList = new Vector<TsCTW>();
		}
		this.ctwList.add(argument);
	}	
	
	public List<TsCTZ> getCtzList() {
		return ctzList;
	}
	public void setCtzList(List<TsCTZ> argument) {		
		this.ctzList = argument;
	}	
	public void addCtzList(TsCTZ argument) {
		if (argument == null) {
			return;
		}		
		if (ctzList == null) {
			ctzList = new Vector<TsCTZ>();
		}
		this.ctzList.add(argument);
	}	
	
	public List<TsCTV> getCtvList() {
		return ctvList;
	}
	public void setCtvList(List<TsCTV> argument) {		
		this.ctvList = argument;
	}	
	public void addCtvList(TsCTV argument) {
		if (argument == null) {
			return;
		}		
		if (ctvList == null) {
			ctvList = new Vector<TsCTV>();
		}
		this.ctvList.add(argument);
	}	
	
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
		if (ctkSubset != null) {				
			res = appendString(res, ctkSubset.teilsatzBilden());
		}
		if (ctaSubset != null) {				
			res = appendString(res, ctaSubset.teilsatzBilden());
		}
		if (ctsSubset != null) {				
			res = appendString(res, ctsSubset.teilsatzBilden());
		}
		if (ctxSubset != null) {				
			res = appendString(res, ctxSubset.teilsatzBilden());
		}
		if (ctcSubset != null) {				
			res = appendString(res, ctcSubset.teilsatzBilden());
		}
		if (ctgSubset != null) {				
			res = appendString(res, ctgSubset.teilsatzBilden());
		}		
		if (ctwList != null) {
			res = res + writeCtwList();			
		}
		if (ctzList != null) {
			res = res + writeCtzList();			
		}
		if (ctvList != null) {
			res = res + writeCtvList();			
		}
		
		return res;
	}
	
	private String writeCtwList() throws FssException {				
		String stb = "";
		
		if (ctwList != null)  {			
			for (TsCTW ctwSubset : ctwList) {				
				if (ctwSubset != null && !ctwSubset.isEmpty()) {
					stb = appendString(stb, ctwSubset.teilsatzBilden());
				}
			}
		}		
		return stb;	
	}	
	
	private String writeCtzList() throws FssException {				
		String stb = "";
		
		if (ctzList != null)  {			
			for (TsCTZ ctzSubset : ctzList) {				
				if (ctzSubset != null && !ctzSubset.isEmpty()) {
					stb = appendString(stb, ctzSubset.teilsatzBilden());
				}
			}
		}		
		return stb;	
	}	
	
	private String writeCtvList() throws FssException {				
		String stb = "";
		
		if (ctvList != null)  {			
			for (TsCTV ctvSubset : ctvList) {				
				if (ctvSubset != null && !ctvSubset.isEmpty()) {
					stb = appendString(stb, ctvSubset.teilsatzBilden());
				}
			}
		}		
		return stb;	
	}	
}


