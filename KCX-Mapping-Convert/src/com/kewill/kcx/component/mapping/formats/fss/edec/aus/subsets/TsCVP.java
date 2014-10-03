package com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;
import java.util.List;

/**
 * Function.
 * @author iwaniuk
 * @version 1.0.00
 */
public class TsCVP extends Teilsatz {
	private String beznr = "";       //Bezugsnummer
	private String vpnr1 = "";		 //Registrier-Nummer
	private String vpnr2 = "";		 //Registrier-Nummer
	private String vpnr3 = "";		 //Registrier-Nummer
	private String vpnr4 = "";		 //Registrier-Nummer
	private String vpnr5 = "";		 //Registrier-Nummer
	private String vpnr6 = "";		 //Registrier-Nummer
	private String vpnr7 = "";		 //Registrier-Nummer
	private String vpnr8 = "";		 //Registrier-Nummer
	private String vpnr9 = "";		 //Registrier-Nummer
	
	
	public TsCVP() {
        tsTyp = "CVP";
    }

    public void setFields(String[] fields) {
    
		int size = fields.length;
		//String ausgabe = "FSS: " + fields[0] + " size = " + size;
		//Utils.log( ausgabe);
			
		
		if (size < 1) {
			return;		
		}
        tsTyp = fields[0];
        if (size < 2) {
        	return;	
        }
        beznr = fields[1];
        if (size < 3) {
        	return;	
        }
        vpnr1 = fields[2];
        if (size < 4) {
        	return;	
        }
        vpnr2 = fields[3];
        if (size < 5) {
        	return;	
        }
        vpnr3 = fields[4];        
        if (size < 6) {
        	return;	
        }
        vpnr4 = fields[5];
        if (size < 7) {
        	return;	
        }
        vpnr5 = fields[6];
        if (size < 8) {
        	return;	
        }
        vpnr6 = fields[7];
        if (size < 9) {
        	return;	
        }
        vpnr7 = fields[8];
        if (size < 10) {
        	return;	
        }
        vpnr8 = fields[9];
        if (size < 11) {
        	return;	
        }
        vpnr9 = fields[10];        
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(vpnr1);
        buff.append(trenner);
        buff.append(vpnr2);
        buff.append(trenner);
        buff.append(vpnr3);
        buff.append(trenner);
        buff.append(vpnr4);
        buff.append(trenner);
        buff.append(vpnr5);
        buff.append(trenner);
        buff.append(vpnr6);
        buff.append(trenner);
        buff.append(vpnr7);
        buff.append(trenner);
        buff.append(vpnr8);
        buff.append(trenner);
        buff.append(vpnr9);
        buff.append(trenner);

        return new String(buff);
      }

    public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}
	public String getBeznr() {
		return beznr;	
	}

	public String getVpnr1() {
		return vpnr1;
	}
	public void setVpnr1(String vpnr1) {
		this.vpnr1 = Utils.checkNull(vpnr1);
	}

	public String getVpnr2() {
		return vpnr2;	
	}
	public void setVpnr2(String vpnr2) {
		this.vpnr2 = Utils.checkNull(vpnr2);
	}

	public String getVpnr3() {
		return vpnr3;	
	}
	public void setVpnr3(String vpnr3) {
		this.vpnr3 = Utils.checkNull(vpnr3);
	}

	public String getVpnr4() {
		return vpnr4;	
	}
	public void setVpnr4(String vpnr4) {
		this.vpnr4 = Utils.checkNull(vpnr4);
	}

	public String getVpnr5() {
		return vpnr5;	
	}
	public void setVpnr5(String vpnr5) {
		this.vpnr5 = Utils.checkNull(vpnr5);
	}
	
	public String getVpnr6() {
		return vpnr6;	
	}
	public void setVpnr6(String vpnr6) {
		this.vpnr6 = Utils.checkNull(vpnr6);
	}

	public String getVpnr7() {
		return vpnr7;	
	}
	public void setVpnr7(String vpnr7) {
		this.vpnr7 = Utils.checkNull(vpnr7);
	}

	public String getVpnr8() {
		return vpnr8;	
	}
	public void setVpnr8(String vpnr8) {
		this.vpnr8 = Utils.checkNull(vpnr8);
	}

	public String getVpnr9() {
		return vpnr9;	
	}
	public void setVpnr9(String vpnr9) {
		this.vpnr9 = Utils.checkNull(vpnr9);
	}

	private void setRegistrierNr(List <String> col) {
	    for (int j = 0; j < col.size(); j++) {
    		switch (j) {
			case 0:
				this.setVpnr1(col.get(0));
				break;
			case 1:
				this.setVpnr2(col.get(1));
				break;
			case 2:
				this.setVpnr3(col.get(2));
				break;
			case 3:
				this.setVpnr4(col.get(3));
				break;
			case 4:
				this.setVpnr5(col.get(4));
				break;
			case 5:
				this.setVpnr6(col.get(5));
				break;
			case 6:
				this.setVpnr7(col.get(6));
				break;
			case 7:
				this.setVpnr8(col.get(7));
				break;
			case 8:
				this.setVpnr9(col.get(8));
				break;
			default:
				break;	
    		}
    	}
	}
		
	// List of Registriers
	public void setCvpSubset(List<String> col, String ref) {
		if (Utils.isStringEmpty(ref) || col.isEmpty()) {
			return;
		}
		this.setBeznr(ref);
		setRegistrierNr(col);
	}

	// List of Registriers
	public void setCvpSubset(List<String> col) {
		setRegistrierNr(col);
	}

	
	public boolean isEmpty() {
		if (Utils.isStringEmpty(vpnr1) &&
			Utils.isStringEmpty(vpnr2) &&
			Utils.isStringEmpty(vpnr3) &&
			Utils.isStringEmpty(vpnr4) &&
			Utils.isStringEmpty(vpnr5) &&
			Utils.isStringEmpty(vpnr6) &&
			Utils.isStringEmpty(vpnr7) &&
			Utils.isStringEmpty(vpnr8) &&
			Utils.isStringEmpty(vpnr9) 
		) {
			return true;
		} else {
			return false;
		}
	}	
}	
