package com.kewill.kcx.component.mapping.formats.fss.Port.messages.BL.common;

import java.util.ArrayList;
import java.util.List;

import com.kewill.kcx.component.mapping.formats.fss.common.FssException;

/**
 * Module		: Port BL.<br>
 * Created		: 10.04.2012<br>
 * Description	:
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class CarriageFSS {
	private CarriageDetailsFSS		    mainCarriage;           //1
	private List<CarriageDetailsFSS> 	preCarriageList;	  	//98x
	private List<CarriageDetailsFSS> 	onCarriageList;	  		//98x
	
	public CarriageFSS() throws FssException {
		super(); 
	}
	
	public void setMainCarriage(CarriageDetailsFSS ts) {	    	
		mainCarriage = ts;
    }	    
    public CarriageDetailsFSS  getMainCarriage() {
        return mainCarriage;
    }
    
	public void setPreCarriageList(List<CarriageDetailsFSS> list) {	    	
		preCarriageList = list;
    }	    
    public List<CarriageDetailsFSS> getPreCarriageList() {
        return preCarriageList;
    }
    public void addPreCarriageList(CarriageDetailsFSS cc) {	
    	if (preCarriageList == null) {
    		preCarriageList = new ArrayList<CarriageDetailsFSS>();	
    	}
    	preCarriageList.add(cc);
    }
    
    public void setOnCarriageList(List<CarriageDetailsFSS> list) {	    	
		onCarriageList = list;
    }	    
    public List<CarriageDetailsFSS> getOnCarriageList() {
        return onCarriageList;
    }
    public void addOnCarriageList(CarriageDetailsFSS cc) {	
    	if (onCarriageList == null) {
    		onCarriageList = new ArrayList<CarriageDetailsFSS>();	
    	}
    	onCarriageList.add(cc);
    }
}
