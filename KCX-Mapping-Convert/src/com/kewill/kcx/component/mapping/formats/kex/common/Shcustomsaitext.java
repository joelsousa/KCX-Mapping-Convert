package com.kewill.kcx.component.mapping.formats.kex.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module       : KEX<br>
 * Created      : 15.12.2011<br>
 * Description	: V03.
 * 
 * @author iwaniuk
 * @version 0.3.00
 */
public class Shcustomsaitext extends KCXMessage {
					
	private String shaitextUnid;	
	private String aicode;	
	private String aitext1;	
	private String aitext2;	
	private String aitext3;	
	private String aitext4;	
	private String aitext5;	

	 public Shcustomsaitext() {
		 super();  
	 }

	 public Shcustomsaitext(XmlMsgScanner scanner) {
 		super(scanner);
	 }

	 private enum ESheucustomsesTags {	
			// KEX							KIDS
		 shaitextUnid,
		 aicode,
		 aitext1,
		 aitext2,
		 aitext3,
		 aitext4,
		 aitext5;
	 }	 

	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {		
			switch ((ESheucustomsesTags) tag) {
				default: return;			
			}
		} else {			
			switch ((ESheucustomsesTags) tag) {			
				case shaitextUnid:
					setShaitextUnid(value);
					break;
				case aicode:
					setAicode(value);
					break;
				case aitext1:
					setAitext1(value);
					break;
				case aitext2:
					setAitext2(value);
					break;
				case aitext3:
					setAitext3(value);
					break;
				case aitext4:
					setAitext4(value);
					break;
				case aitext5:
					setAitext5(value);
					break;
				default:
					return;
			}
		}
		
	}

	@Override
	public void stoppElement(Enum tag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Enum translate(String token) {
		try {
				return ESheucustomsesTags.valueOf(token);
		    } catch (IllegalArgumentException e) {
				return null;
		}	
	}

	public String getShaitextUnid() {
		return shaitextUnid;
	}
	public void setShaitextUnid(String value) {
		this.shaitextUnid = Utils.checkNull(value);
	}

	public String getAicode() {
		return aicode;
	}
	public void setAicode(String value) {
		this.aicode = Utils.checkNull(value);
	}

	public String geTaitext1() {
		return aitext1;
	}
	public void setAitext1(String value) {
		this.aitext1 = Utils.checkNull(value);
	}

	public String geTaitext2() {
		return aitext2;
	}
	public void setAitext2(String value) {
		this.aitext2 = Utils.checkNull(value);
	}
	
	public String geTaitext3() {
		return aitext3;
	}
	public void setAitext3(String value) {
		this.aitext3 = Utils.checkNull(value);
	}
	
	public String geTaitext4() {
		return aitext4;
	}
	public void setAitext4(String value) {
		this.aitext4 = Utils.checkNull(value);
	}
	
	public String geTaitext5() {
		return aitext5;
	}
	public void setAitext5(String value) {
		this.aitext5 = Utils.checkNull(value);
	}
	
}
