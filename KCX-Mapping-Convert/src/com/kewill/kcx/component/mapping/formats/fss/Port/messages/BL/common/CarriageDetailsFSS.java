package com.kewill.kcx.component.mapping.formats.fss.Port.messages.BL.common;

import java.util.ArrayList;
import java.util.List;

import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsCarriage;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsMeansOfTransport;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsPlace;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;

/**
 * Module		: Port BL.<br>
 * Created		: 10.04.2012<br>
 * Description	:
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class CarriageDetailsFSS {
	private TsCarriage		    tsCarriage;
	private TsMeansOfTransport 	tsMeansOfTransport;	  	
	private List<TsPlace> 		placeList;	  		//max 10 different Places
	
	public CarriageDetailsFSS() throws FssException {
		super(); 
	}
	
	public void setTsCarriage(TsCarriage ts) {	    	
		tsCarriage = ts;
    }	    
    public TsCarriage  getTsCarriage() {
        return tsCarriage;
    }
    
    public void setMeansOfTransport(TsMeansOfTransport ts) {	    	
    	tsMeansOfTransport = ts;
    }	    
    public TsMeansOfTransport  getMeansOfTransport() {
        return tsMeansOfTransport;
    }
    
	public void setPlaceList(List<TsPlace> list) {	    	
		placeList = list;
    }	    
    public List<TsPlace>  getPlaceList() {
        return placeList;
    }
    public void addPlaceList(TsPlace ts) {	
    	if (placeList == null) {
    		placeList = new ArrayList<TsPlace>();	
    	}
    	placeList.add(ts);
    }
}
