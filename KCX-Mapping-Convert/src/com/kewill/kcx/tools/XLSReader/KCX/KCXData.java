package com.kewill.kcx.tools.XLSReader.KCX;

import com.kewill.kcx.tools.XLSReader.XLSData;

/**
 * Class which contains the data from an Excel sheet.
 *
 *  Änderungsnachweis
 * 
 * 27.07.2012: folgende countrycodes eingefügt: dk,fr,lu,be,se,no,it,es
 * 				gem. Jira ticket KCX-145 v. CK
 * 
 * @author messer
 * @version 0.0.10
 */
public class KCXData extends XLSData {
	private String kcxCode = "";
	private String kcxCodeId = "";
	private String tag = "";
	private String de = " ";
	private String ch = " ";
	private String pl = " ";
	private String nl = " ";
	private String si = " ";
	private String gb = " ";
	private String fi = " ";
	private String dk = " ";
	private String fr = " ";
	private String lu = " ";
	private String be = " ";
	private String se = " ";
	private String no = " ";
	private String it = " ";
	private String es = " ";
	private boolean update = true;

	public KCXData(String kcxCode, String tag, String kcxCodeId) {
		super();
		this.kcxCode = kcxCode;
		this.tag = tag;
		this.kcxCodeId = kcxCodeId;

		// setValue("'"+kcxCode+"', '"+tag+"', '"+kcxCodeId+"', '"+de+"',
		// '"+ch+"', '"+pl+"' ,'"+nl+"' ,'"+si+"'");
	}

	@Override
	public Object getValue() {

		return "'" + kcxCode + "', '" + tag + "', '" + kcxCodeId + "', '" + de
				+ "', '" + ch + "', '" + pl + "' ,'" + nl + "' ,'" + si  + "' ,'" + gb +  "' ,'" + fi + "','" + dk + "' ,'" + fr + "' ,'" + lu + "' ,'" + be + "' ,'" + se + "' ,'" + no + "' ,'" + it + "' ,'" + es + "'";
	}

	public String getCh() {
		return ch;
	}

	public void setCh(String ch) {
		if (!ch.equals("")) {
			this.ch = ch;
		}
	}

	public String getDe() {
		return de;
	}

	public void setDe(String de) {
		if (!de.equals("")) {
			this.de = de;
		}
	}

	public String getKcxCode() {
		return kcxCode;
	}

	public void setKcxCode(String kcxCode) {
		this.kcxCode = kcxCode;
	}

	public String getKcxCodeId() {
		return kcxCodeId;
	}

	public void setKcxCodeId(String kcxCodeId) {
		this.kcxCodeId = kcxCodeId;
	}

	public String getNl() {
		return nl;
	}

	public void setNl(String nl) {
		if (!nl.equals("")) {
			this.nl = nl;
		}
	}

	public String getPl() {
		return pl;
	}

	public void setPl(String pl) {
		if (!pl.equals("")) {
			this.pl = pl;
		}
	}

	public String getSi() {
		return si;
	}

	public void setSi(String si) {
		if (!si.equals("")) {
			this.si = si;
		}
	}
	public String getGb() {
		return gb;
	}

	public void setGb(String gb) {
		if (!gb.equals("")) {
			this.gb = gb;
		}
	}
	public String getFi() {
		return fi;
	}

	public void setFi(String fi) {
		if (!fi.equals("")) {
			this.fi = fi;
		}
	}
	
	public String getDk() {
		return dk;
	}

	public void setDk(String dk) {
		if (!dk.equals("")) {
			this.dk = dk;
		}
	}
	
	public String getFr() {
		return fr;
	}

	public void setFr(String fr) {
		if (!fr.equals("")) {
			this.fr = fr;
		}
	}
	
	public String getLu() {
		return lu;
	}

	public void setLu(String lu) {
		if (!lu.equals("")) {
			this.lu = lu;
		}
	}
	
	public String getBe() {
		return be;
	}

	public void setBe(String be) {
		if (!be.equals("")) {
			this.be = be;
		}
	}
	
	public String getSe() {
		return se;
	}

	public void setSe(String se) {
		if (!se.equals("")) {
			this.se = se;
		}
	}
	
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		if (!no.equals("")) {
			this.no = no;
		}
	}
	
	public String getIt() {
		return it;
	}

	public void setIt(String it) {
		if (!it.equals("")) {
			this.it = it;
		}
	}
	
	public String getEs() {
		return es;
	}

	public void setEs(String es) {
		if (!es.equals("")) {
			this.es = es;
		}
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public boolean isUpdate() {
		return update;
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}

}
