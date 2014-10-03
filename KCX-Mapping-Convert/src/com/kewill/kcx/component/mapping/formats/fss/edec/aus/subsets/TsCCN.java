package com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets;


import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;
import java.util.List;
import java.util.Vector;


public class TsCCN extends Teilsatz {
	private String beznr = "";       //Bezugsnummer
	private String connr1= "";		 //Container-Nummer
	private String connr2= "";		 //Container-Nummer
	private String connr3= "";		 //Container-Nummer
	private String connr4= "";		 //Container-Nummer
	private String connr5= "";		 //Container-Nummer
	private String connr6= "";		 //Container-Nummer
	private String connr7= "";		 //Container-Nummer
	private String connr8= "";		 //Container-Nummer
	private String connr9= "";		 //Container-Nummer
	
	
	public TsCCN() {
        tsTyp = "CCN";
    }

    public void setFields(String[] fields)
    {
		int size = fields.length;
		String ausgabe = "FSS: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
			
		
		if (size < 1 ) return;		
        	tsTyp = fields[0];
        if (size < 2 ) return;	
        	beznr = fields[1];
        if (size < 3 ) return;	
        	connr1 = fields[2];
        if (size < 4 ) return;	
        	connr2 = fields[3];
        if (size < 5 ) return;	
        	connr3 = fields[4];        
        if (size < 6 ) return;	
        	connr4 = fields[5];
        if (size < 7 ) return;	
        	connr5 = fields[6];
        if (size < 8 ) return;	
        	connr6 = fields[7];
        if (size < 9 ) return;	
        	connr7 = fields[8];
        if (size < 10 ) return;	
        	connr8 = fields[9];
        if (size < 11 ) return;	
        	connr9 = fields[10];
        	
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(connr1);
        buff.append(trenner);
        buff.append(connr2);
        buff.append(trenner);
        buff.append(connr3);
        buff.append(trenner);
        buff.append(connr4);
        buff.append(trenner);
        buff.append(connr5);
        buff.append(trenner);
        buff.append(connr6);
        buff.append(trenner);
        buff.append(connr7);
        buff.append(trenner);
        buff.append(connr8);
        buff.append(trenner);
        buff.append(connr9);
        buff.append(trenner);

        return new String(buff);
      }

    public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}

	public String getBeznr() {
		return beznr;
	
	}

	public String getConnr1() {
		return connr1;
	
	}

	public void setConnr1(String connr1) {
		this.connr1 = Utils.checkNull(connr1);
	}

	public String getConnr2() {
		return connr2;
	
	}

	public void setConnr2(String connr2) {
		this.connr2 = Utils.checkNull(connr2);
	}

	public String getConnr3() {
		return connr3;
	
	}

	public void setConnr3(String connr3) {
		this.connr3 = Utils.checkNull(connr3);
	}

	public String getConnr4() {
		return connr4;
	
	}

	public void setConnr4(String connr4) {
		this.connr4 = Utils.checkNull(connr4);
	}

	public String getConnr5() {
		return connr5;
	
	}

	public void setConnr5(String connr5) {
		this.connr5 = Utils.checkNull(connr5);
	}

	public String getConnr6() {
		return connr6;
	
	}

	public void setConnr6(String connr6) {
		this.connr6 = Utils.checkNull(connr6);
	}

	public String getConnr7() {
		return connr7;
	
	}

	public void setConnr7(String connr7) {
		this.connr7 = Utils.checkNull(connr7);
	}

	public String getConnr8() {
		return connr8;
	
	}

	public void setConnr8(String connr8) {
		this.connr8 = Utils.checkNull(connr8);
	}

	public String getConnr9() {
		return connr9;
	
	}

	public void setConnr9(String connr9) {
		this.connr9 = Utils.checkNull(connr9);
	}

	private void setContainer (List <String> col)  {
	    for (int j = 0; j < col.size(); j++) {
    		switch (j) {
			case 0:
				this.setConnr1( col.get(0) );
				break;
			case 1:
				this.setConnr2( col.get(1) );
				break;
			case 2:
				this.setConnr3( col.get(2) );
				break;
			case 3:
				this.setConnr4( col.get(3) );
				break;
			case 4:
				this.setConnr5( col.get(4) );
				break;
			case 5:
				this.setConnr6( col.get(5) );
				break;
			case 6:
				this.setConnr7( col.get(6) );
				break;
			case 7:
				this.setConnr8( col.get(7) );
				break;
			case 8:
				this.setConnr9( col.get(8) );
				break;
    		}
    	}
	}
	
	
	// List of Containers
	public void setCcnSubset(List<String> col, String ref) {
		if ( Utils.isStringEmpty(ref) || col.isEmpty() ) return;
		this.setBeznr(ref);
		setContainer(col);
	}

	// List of Containers
	public void setCcnSubset(List<String> col ) {
		setContainer(col);
	}

	
	public boolean isEmpty() {
		if ( 	Utils.isStringEmpty(connr1)
			 && Utils.isStringEmpty(connr2) 
			 && Utils.isStringEmpty(connr3) 
			 && Utils.isStringEmpty(connr4) 
			 && Utils.isStringEmpty(connr5)
			 && Utils.isStringEmpty(connr6)
			 && Utils.isStringEmpty(connr7) 
			 && Utils.isStringEmpty(connr8) 
			 && Utils.isStringEmpty(connr9) 
		) 
			return true;
		else
			return false;
	}	
}	
