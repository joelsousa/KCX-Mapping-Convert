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

package com.kewill.kcx.component.mapping.formats.fss.ncts.messages.V70;

import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.formats.fss.ncts.messages.FssMessage62;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVUC;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVUL;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVUP;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVUS;

/**
 * Modul		: MsgVURPos<br>
 * Erstellt		: 08.06.2011<br>
 * Beschreibung	: .
 * 
 * @author iwaniuk
 * @version 6.0.00
 */
public class MsgVURPos extends FssMessage62 {
	
	private TsVUP			vupSubset;         //Positionsteil - mit jeweils: TsVUC, TsVUL, TSUS, TSVUA
	private List< TsVUC >	vucSubsetList;
	private List< TsVUL >	vulSubsetList;
	private List< TsVUS >	vusSubsetList;
	//private TsVUA			vuaSubset;  V70 -entfernt
		
		public TsVUP getVupSubset() {
			return this.vupSubset;
		}
		public void setVupSubset(TsVUP vupSubset) {
			this.vupSubset	= vupSubset;
		}
		
		public List< TsVUC > getVucSubsetList() {
			return this.vucSubsetList;
		}
		public void setVucSubsetList(List< TsVUC > list) {
			this.vucSubsetList	= list;
		}
		public void addVucSubsetList(TsVUC vucSubset) {
			if (vucSubset == null) {
				return;
			}			
			if (vucSubsetList == null) {
				vucSubsetList = new Vector< TsVUC >();
			}			
			this.vucSubsetList.add(vucSubset);
		}
		
		public List< TsVUL > getVulSubsetList() {
			return this.vulSubsetList;
		}
		public void addVulSubsetList(TsVUL vulSubset) {
			if (vulSubset == null) {
				return;
			}
			
			if (vulSubsetList == null) {
				vulSubsetList	= new Vector< TsVUL >();
			}
			
			this.vulSubsetList.add(vulSubset);
		}
		
		public List< TsVUS > getVusSubsetList() {
			return this.vusSubsetList;
		}
		public void addVusSubsetList(TsVUS vusSubset) {
			if (vusSubset == null) {
				return;
			}
			
			if (vusSubsetList == null) {
				vusSubsetList	= new Vector< TsVUS >();
			}
			
			this.vusSubsetList.add(vusSubset);
		}
				
}

