package com.kewill.kcx.component.mapping.util;


import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;


/**
 * Date utilities based on a DateTypeFactory.
 * 
 * @author schmidt
 * @version 1.0.00
 */
public final class DateUtils {
    private static Logger logger = Logger.getLogger(DateUtils.class);
    private static DatatypeFactory df = null; 
    
    private DateUtils() { }
    
    static {
        try {
            df = DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException e) {
            logger.error(e.getMessage());
        }
    }

    public static XMLGregorianCalendar getXMLGregorianCalendar(Date inputDate) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeInMillis(inputDate.getTime());
        return df.newXMLGregorianCalendar(gc);
    }

}
