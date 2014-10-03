package com.kewill.kcx.component.mapping.formats.fss.Import.messages;

/*
 * Funktion    : FssMessage60.java
 * Titel       :
 * Date	       : 31.03.2009
 * Author      : Kewill CSF / Alfred Krzoska
 * Description : lists used in mappings Kids to FSS 
 *
 *
 */

import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsACN;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsACO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAED;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAZV;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.util.Utils;

public class FssMessage {
	public String appendString ( String in, String append) throws FssException {
		return in + append + Utils.LF;
	}
}
