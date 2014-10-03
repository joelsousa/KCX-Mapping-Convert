package com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL; 

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.Address;

/**
 * Module                :       PORT - BL.
 * Created               :       10.04.2012
 * Description    		 :
 *
 * @author                      iwaniuk
 * @version 1.0.00
 */

public class TsAddressQualified extends Teilsatz {
	 
	 private String name1		= "";	
	 private String name2		= "";
	 private String name3		= "";
	 private String name4		= "";
	 private String name5		= "";
	 private String nameFormatCode = "";
	 private String street1		= "";	
	 private String street2		= "";	
	 private String street3		= "";	
	 private String street4		= "";	
	 private String city			= "";    
	 private String countrySubEntity = "";
	 private String postalCode		= "";		
	 private String countryCode		= "";
    
    public TsAddressQualified() {
	    tsTyp = "ADDRESSQUALIFIED";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;

    	if (size < 1) { return; }
    	tsTyp = fields[0];
	    if (size < 2) { return; }
	    name1 = fields[1];
	    if (size < 3) { return; }
	    name2 = fields[2];
	    if (size < 4) { return; }
	    name3 = fields[3];
	    if (size < 5) { return; }
	    name4 = fields[4];
	    if (size < 6) { return; }
	    name5 = fields[5];
	    if (size < 7) { return; }
	    nameFormatCode = fields[6];
	    if (size < 8) { return; }
	    street1 = fields[7];
	    if (size < 9) { return; }
	    street2 = fields[8];
	    if (size < 10) { return; }
	    street3 = fields[9];
	    if (size < 11) { return; }
	    street4 = fields[10];
	    if (size < 12) { return; }
	    city = fields[11];
	    if (size < 13) { return; }
	    countrySubEntity = fields[12];
	    if (size < 14) { return; }
	    postalCode = fields[13]; 
	    if (size < 15) { return; }
	    countryCode = fields[14];
    }

    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(name1);
    	buff.append(trenner);
    	buff.append(name2);
    	buff.append(trenner);    	
    	buff.append(name3);
    	buff.append(trenner);    	
    	buff.append(name4);
    	buff.append(trenner);    	
    	buff.append(name5);
    	buff.append(trenner);    	
    	buff.append(nameFormatCode);
    	buff.append(trenner);
    	buff.append(street1);
    	buff.append(trenner);
    	buff.append(street2);
    	buff.append(trenner);
    	buff.append(street3);
    	buff.append(trenner);
    	buff.append(street4);
    	buff.append(trenner);
    	buff.append(city);
    	buff.append(trenner);
    	buff.append(countrySubEntity);
    	buff.append(trenner);
    	buff.append(postalCode);
    	buff.append(trenner);
    	buff.append(countryCode);
    	//buff.append(trenner);
    	
    	return new String(buff);
    }
    
    public void setAllFields(Address address) {
    	if (address == null) {
    		return;
    	}
    	if (address.getPartyName() != null) {    		
    		name1 = Utils.checkNull(address.getPartyName().getFirstRow());   
    		nameFormatCode = Utils.checkNull(address.getPartyName().getNameFormatCode());  
    		if (address.getPartyName().getNextRowList() != null) {
    			int ii = 1;
    			for (String row : address.getPartyName().getNextRowList()) {
    				if (!Utils.isStringEmpty(row)) {
    					ii = ii + 1;
    					if (ii == 2) {
    						name2 = row;
    					}
    					if (ii == 3) {
    						name3 = row;
    					}
    					if (ii == 4) {
    						name4 = row;
    					}
    					if (ii == 5) {
    						name5 = row;
    					}
    				}
    			}
    		}
    	}    	
    	if (address.getStreetOrBox() != null) {    		
    		street1 = Utils.checkNull(address.getStreetOrBox().getFirstRow());      		
    		if (address.getStreetOrBox().getNextRowList() != null) {
    			int ii = 1;
    			for (String row : address.getStreetOrBox().getNextRowList()) {    				
    				if (!Utils.isStringEmpty(row)) {    					
        				ii = ii + 1;
        				if (ii == 2) {
        					street2 = row;
        				}
        				if (ii == 3) {
        					street3 = row;
        				}
        				if (ii == 4) {
        					street4 = row;
        				}        				
    				}
    			}
    		}
    	}
    	city = Utils.checkNull(address.getCity());   	 
    	countrySubEntity = Utils.checkNull(address.getCountrySubEntity());
    	postalCode = Utils.checkNull(address.getPostalCode());
    	countryCode = Utils.checkNull(address.getCountryCode());	
    }
    
    public boolean isEmpty() {
    	return  Utils.isStringEmpty(name1) && Utils.isStringEmpty(street1)
    		&& Utils.isStringEmpty(postalCode) && Utils.isStringEmpty(city)  
    		&& Utils.isStringEmpty(countryCode);  	
    }

	public String getName1() {
		return name1;
	}
	public void setName1(String name1) {
		this.name1 = Utils.checkNull(name1);
	}

	public String getName2() {
		return name2;
	}
	public void setName2(String name2) {
		this.name2 = Utils.checkNull(name2);
	}

	public String getName3() {
		return name3;
	}
	public void setName3(String name3) {
		this.name3 = Utils.checkNull(name3);
	}

	public String getName4() {
		return name4;
	}
	public void setName4(String name4) {
		this.name4 = Utils.checkNull(name4);
	}

	public String getName5() {
		return name5;
	}
	public void setName5(String name5) {
		this.name5 = Utils.checkNull(name5);
	}

	public String getNameFormatCode() {
		return nameFormatCode;
	}
	public void setNameFormatCode(String nameFormatCode) {
		this.nameFormatCode = Utils.checkNull(nameFormatCode);
	}

	public String getStreet1() {
		return street1;
	}
	public void setStreet1(String street1) {
		this.street1 = Utils.checkNull(street1);
	}

	public String getStreet2() {
		return street2;
	}
	public void setStreet2(String street2) {
		this.street2 = Utils.checkNull(street2);
	}

	public String getStreet3() {
		return street3;
	}
	public void setStreet3(String street3) {
		this.street3 = Utils.checkNull(street3);
	}

	public String getStreet4() {
		return street4;
	}
	public void setStreet4(String street4) {
		this.street4 = Utils.checkNull(street4);
	}

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = Utils.checkNull(city);
	}

	public String getCountrySubEntity() {
		return countrySubEntity;
	}
	public void setCountrySubEntity(String countrySubEntity) {
		this.countrySubEntity = Utils.checkNull(countrySubEntity);
	}

	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = Utils.checkNull(postalCode);
	}

	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = Utils.checkNull(countryCode);
	}
}

