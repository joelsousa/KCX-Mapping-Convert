package com.kewill.kcx.component.mapping.formats.fss.common;

/**
 * Allgemeine FSS-Exception.
 * Datum: 02.02.2004
 *
 * @author Michael Schmidt
 * @version 1.0.00
 */
public class FssException extends Exception {
    private static final long serialVersionUID = 1L;
    
	public FssException(String message)	{
		super(message);
	}
}
