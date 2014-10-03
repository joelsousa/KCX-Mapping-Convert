package com.kewill.kcx.component.mapping.formats.fss.ncts.messages;

import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: FssMessage62<br>
 * Created		: 2010.09.02<br>
 * Description	: Used by FSS Message classes.
 * 
 * @author Frederick T
 * @version 6.2.00
 *
 */
public class FssMessage62 {
	public String appendString(String in, String append) throws FssException {
		return in + append + Utils.LF;
	}
	
}
