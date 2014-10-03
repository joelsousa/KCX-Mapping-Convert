

package com.kewill.kcx.component.mapping.formats.fss.ncts.messages.V70;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.ncts.messages.FssMessage62;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.V70.TsVIR;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.V70.TsVNE;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.V70.TsVUR;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.V70.TsVUZ;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVNV;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVUC;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVUE;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVUL;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVUO;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVUP;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVUS;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVUV;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: NCTS<br>
 * Created		: 05.02.2013<br>
 * Description	: FSS-Message  Entladebericht.
 * 
 * @author iwaniuk
 * @version 7.0.00
 */
public class MsgVUR extends FssMessage62 {

	private TsVOR			vorSubset; 
	private TsHead	        headSubset;
	private TsVUR			vurSubset;  //V70 changes
	private TsVNE			vneSubset;  //V70 new
	private TsVUZ			vuzSubset;  //V70 changes
	private TsVIR           virSubset;  //V70 new
	private List< TsVUV >	vuvSubsetList;
	private TsVUE			vueSubset;
	private List< TsVNV >	vnvSubsetList;
	private List< TsVUO >	vuoSubsetList;
	
	private List< MsgVURPos >	posList;     //EI20110608
	
	/// SETTER & GETTER Methods
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
		
		public TsVUR getVurSubset() {
			return this.vurSubset;
		}
		public void setVurSubset(TsVUR vurSubset) {
			this.vurSubset	= vurSubset;
		}
		
		public TsVIR getVirSubset() {
			return virSubset;
		}
		public void setVirSubset(TsVIR vir) {
			this.virSubset = vir;
		}
		
		public TsVNE getVneSubset() {
			return vneSubset;
		}
		public void setVneSubset(TsVNE vne) {
			this.vneSubset = vne;
		}
		
		public TsVUZ getVuzSubset() {
			return this.vuzSubset;
		}
		public void setVuzSubset(TsVUZ vuzSubset) {
			this.vuzSubset	= vuzSubset;
		}
		
		public List< TsVUV > getVuvSubsetList() {
			return this.vuvSubsetList;
		}
		public void addVuvSubsetList(TsVUV vuvSubset) {
			if (vuvSubset == null) {
				return;
			}
			
			if (vuvSubsetList == null) {
				vuvSubsetList	= new Vector< TsVUV >();
			}
			
			this.vuvSubsetList.add(vuvSubset);
		}
		
		public TsVUE getVueSubset() {
			return this.vueSubset;
		}
		public void setVueSubset(TsVUE vueSubset) {
			this.vueSubset	= vueSubset;
		}
		
		public List< TsVNV > getVnvSubsetList() {
			return this.vnvSubsetList;
		}
		public void addVnvSubsetList(TsVNV vnvSubset) {
			if (vnvSubset == null) {
				return;
			}
			
			if (vnvSubsetList == null) {
				vnvSubsetList	= new Vector< TsVNV >();
			}
			
			this.vnvSubsetList.add(vnvSubset);
		}
		
		public List< TsVUO > getVuoSubsetList() {
			return this.vuoSubsetList;
		}
		public void addVuoSubsetList(TsVUO vuoSubset) {
			if (vuoSubset == null) {
				return;
			}			
			if (vuoSubsetList == null) {
				vuoSubsetList	= new Vector< TsVUO >();
			}			
			this.vuoSubsetList.add(vuoSubset);
		}
				
		public List< MsgVURPos > getPostList() {
			return this.posList;
		}
		public void addPosList(MsgVURPos pos) {
			if (pos == null) {
				return;
			}			
			if (posList == null) {
				posList	= new Vector< MsgVURPos >();
			}			
			this.posList.add(pos);
		}		
		public void setPosList(List< MsgVURPos > list) {
			this.posList = list;
		}
				
	
    /* Struktur der Nachricht 
     * 

    */
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
			
			if (vurSubset != null) {     //EI20111011 im Hauptsatz keine isEmpty Abfrage				
				res	= appendString(res, vurSubset.teilsatzBilden());
			}
			if (vneSubset != null && !vneSubset.isEmpty()) {
				res	= appendString(res, vneSubset.teilsatzBilden());
			}
			if (vuzSubset != null && !vuzSubset.isEmpty()) {
				res	= appendString(res, vuzSubset.teilsatzBilden());
			}
			if (virSubset != null && !virSubset.isEmpty()) {
				res	= appendString(res, virSubset.teilsatzBilden());
			}
			if (vuvSubsetList != null) {
				for (TsVUV vuvSubset : vuvSubsetList) {
					if (vuvSubset != null && !vuvSubset.isEmpty()) {
						res	= appendString(res, vuvSubset.teilsatzBilden());
					}
				}
			}
			if (vueSubset != null && !vueSubset.isEmpty()) {
				res	= appendString(res, vueSubset.teilsatzBilden());
			}
			if (vnvSubsetList != null) {
				for (TsVNV vnvSubset : vnvSubsetList) {
					if (vnvSubset != null && !vnvSubset.isEmpty()) {
						res	= appendString(res, vnvSubset.teilsatzBilden());
					}
				}
			}
			if (vuoSubsetList != null) {
				for (TsVUO vuoSubset : vuoSubsetList) {
					if (vuoSubset != null && !vuoSubset.isEmpty()) {
						res	= appendString(res, vuoSubset.teilsatzBilden());
					}
				}
			}
			if (posList != null && !posList.isEmpty()) {			
				for (MsgVURPos pos : posList) {
					if (pos != null) {						
						if (pos.getVupSubset() != null && !pos.getVupSubset().isEmpty()) {
		    				res = appendString(res, pos.getVupSubset().teilsatzBilden());
		    			}				
		    			if (pos.getVucSubsetList() != null) {
		    				for (TsVUC vucSubset : pos.getVucSubsetList()) {
		    					if (vucSubset != null && !vucSubset.isEmpty()) {
		    						res = appendString(res, vucSubset.teilsatzBilden());
		    					}
		    				}
		    			}				
		    			if (pos.getVulSubsetList() != null) {
		    				for (TsVUL vulSubset : pos.getVulSubsetList()) {
		    					if (vulSubset != null && !vulSubset.isEmpty()) {
		    						res = appendString(res, vulSubset.teilsatzBilden());
		    					}
		    				}
		    			}
		    			if (pos.getVusSubsetList() != null) {
		    				for (TsVUS vusSubset : pos.getVusSubsetList()) {
		    					if (vusSubset != null && !vusSubset.isEmpty()) {
		    						res = appendString(res, vusSubset.teilsatzBilden());
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
				String line	= "";
//				TsVUR			vurSubset		= null;
//				TsVUZ			vuzSubset		= null;
//				List< TsVUV >	vuvSubsetList	= null;
//				TsVUE			vueSubset		= null;
//				List< TsVNV >	vnvSubsetList	= null;
//				List< TsVUO >	vuoSubsetList	= null;
//				TsVUP			vupSubset		= null;
//				List< TsVUC >	vucSubsetList	= null;
//				List< TsVUL >	vulSubsetList	= null;
//				List< TsVUS >	vusSubsetList	= null;
//				TsVUA			vuaSubset		= null;
				
				MsgVURPos pos = new MsgVURPos();
				
				while ((line = message.readLine()) != null) {
					String lineType	= "";
					if (line.length() > 3) {
						lineType = line.substring(0, 3);
						Utils.log("lineType " + lineType);
					}					
					
					if (lineType.equalsIgnoreCase("VUR")) {
						vurSubset		= new TsVUR();
						String[] vur	= line.split("" + FssUtils.FSS_FS);
						vurSubset.setFields(vur);
						setVurSubset(vurSubset);
					} else if (lineType.equalsIgnoreCase("VNE")) {
						vneSubset		= new TsVNE();
						String[] vne	= line.split("" + FssUtils.FSS_FS);
						vneSubset.setFields(vne);
						setVneSubset(vneSubset);
					} else if (lineType.equalsIgnoreCase("VUZ")) {
						vuzSubset		= new TsVUZ();
						String[] vuz	= line.split("" + FssUtils.FSS_FS);
						vuzSubset.setFields(vuz);
						setVuzSubset(vuzSubset);
					} else if (lineType.equalsIgnoreCase("VIR")) {
						virSubset		= new TsVIR();
						String[] vir	= line.split("" + FssUtils.FSS_FS);
						virSubset.setFields(vir);
						setVirSubset(virSubset);
					} else if (lineType.equalsIgnoreCase("VUV")) {
						TsVUV vuvSubset	= new TsVUV();
						String[] vuv	= line.split("" + FssUtils.FSS_FS);
						vuvSubset.setFields(vuv);
						
						if (vuvSubsetList == null) {
							vuvSubsetList	= new Vector< TsVUV >();
						}
						
						addVuvSubsetList(vuvSubset);
					} else if (lineType.equalsIgnoreCase("VUE")) {
						vueSubset		= new TsVUE();
						String[] vue	= line.split("" + FssUtils.FSS_FS);
						vueSubset.setFields(vue);
						setVueSubset(vueSubset);
					} else if (lineType.equalsIgnoreCase("VNV")) {
						TsVNV vnvSubset	= new TsVNV();
						String[] vnv	= line.split("" + FssUtils.FSS_FS);
						vnvSubset.setFields(vnv);
						
						if (vnvSubsetList == null) {
							vnvSubsetList	= new Vector< TsVNV >();
						}
						
						addVnvSubsetList(vnvSubset);
					} else if (lineType.equalsIgnoreCase("VUO")) {
						TsVUO vuoSubset	= new TsVUO();
						String[] vuo	= line.split("" + FssUtils.FSS_FS);
						vuoSubset.setFields(vuo);
						
						if (vuoSubsetList == null) {
							vuoSubsetList	= new Vector< TsVUO >();
						}
						
						vuoSubsetList.add(vuoSubset);
					} else if (lineType.equalsIgnoreCase("VUP")) {
						pos = new MsgVURPos();
						TsVUP vupSubset	= new TsVUP();
						String[] vup	= line.split("" + FssUtils.FSS_FS);
						vupSubset.setFields(vup);
						
						if (posList == null) {
							posList = new Vector<MsgVURPos>();
						}
						pos.setVupSubset(vupSubset);
						posList.add(pos);						
					} else if (lineType.equalsIgnoreCase("VUC")) {
						TsVUC vucSubset	= new TsVUC();
						String[] vuc = line.split("" + FssUtils.FSS_FS);
						vucSubset.setFields(vuc);
						
						if (posList != null && pos != null) {							
							pos.addVucSubsetList(vucSubset);
						}											
					} else if (lineType.equalsIgnoreCase("VUL")) {
						TsVUL vulSubset	= new TsVUL();
						String[] vul = line.split("" + FssUtils.FSS_FS);
						vulSubset.setFields(vul);
						
						if (posList != null && pos != null) {
							pos.addVulSubsetList(vulSubset);
						}											
					} else if (lineType.equalsIgnoreCase("VUS")) {
						TsVUS vusSubset	= new TsVUS();
						String[] vus = line.split("" + FssUtils.FSS_FS);
						vusSubset.setFields(vus);
						
						if (posList != null && pos != null) {
							pos.addVusSubsetList(vusSubset);
						}											
						
					} else if (lineType.equals("")) {   
						Utils.log("empty line in FSS-message");
					} else {
						throw new FssException("Writing message line " + lineType);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
}

