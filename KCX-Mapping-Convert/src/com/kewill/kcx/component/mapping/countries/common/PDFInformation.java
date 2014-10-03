/*
 * Function    : PDFInformation(KIDS) == PDF(UIDS)
 * Titel       :
 * Date        : 10.09.2008
 * Author      : Kewill CSF / Houdek
 * Description : Contains the PDFInformation Data
 * 			   : with all Fields used in UIDS and  KIDS
 * Parameters  :

 * Changes
 * -----------
 * Author      : Christine Kron
 * Date        : 02.10.2008
 * Label       : 
 * Description:  added pdflist to handle pdfdata inside the xml message
 * 
 * Author      : EI
 * Date        : 18.05.2009
 * Label       :
 * Description : Kids/Uids checked - no changes
 */

package com.kewill.kcx.component.mapping.countries.common;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: PDFInformation<br>
 * Erstellt		: 10.09.2008<br>
 * Beschreibung	: Contains the PDFInformation Data
 *                with all Fields used in UIDS and  KIDS.
 * 
 * @author  Houdek
 * @version 1.0.00
 */
public class  PDFInformation extends KCXMessage {

    private String name;
    private String directory;
    private String newDirectory;
    private String remark;                  //EI20110812 new member for V12
    private List<String> pdflist = new ArrayList<String>(); //PP 20100825 

    private boolean debug = false;
//    private XMLEventReader 	parser	= null;

	private enum EPDFInformation {
		// Kids-TagNames, 			UIDS-TagNames
			Name,					//same 
			Directory,				//same 
			NewDirectory,			Subdirectory, 	SubDirectory,
			Remark,                 //same
			base64Text,				base64;
	}
	
    public PDFInformation() {
    	super();
    	pdflist = new ArrayList<String>(); 
    }
      
    public PDFInformation(XMLEventReader parser) {
    	super(parser);
    	pdflist = new ArrayList<String>(); 
  	}
    
    public PDFInformation(XmlMsgScanner scanner) {
  		super(scanner);
  		pdflist = new ArrayList<String>(); 
  	}    
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  		    return;
  		} else {
  			switch ((EPDFInformation) tag) {
  				case Name:
  					setName(value);
  					break;
  					
  				case Directory:
  					setDirectory(value);
  					break;
  					
  				case NewDirectory:
  				case Subdirectory:
  				case SubDirectory:
  					setNewDirectory(value);
  					break;
  					
  				case base64Text:
  				case base64:  					
  					pdflist.add(value);
  					break;
  					
  				case Remark:
  					setRemark(value);
  					break;
  					
  				default: 
  				    break;
  			}
  		}
  	}
  	
  	public void stoppElement(Enum tag) {
  	}

  	public Enum translate(String token) {
  		try {
  			return EPDFInformation.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}
  	public boolean isDebug() {
  		return debug;
  	}
  	public void setDebug(boolean debug) {
  		this.debug = debug;
  	}
  	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDirectory() {
		return directory;
	}
	public void setDirectory(String directory) {
		this.directory = directory;
	}
	
	public String getNewDirectory() {
		return newDirectory;
	}
	public void setNewDirectory(String newDirectory) {
		this.newDirectory = newDirectory;
	}

	public List<String> getPdflist() {
		return pdflist;
	}

	public void setPdflist(List<String> pdflist) {
		this.pdflist = pdflist;
	}

	public String getRemark() {
		return remark;
	}
	public void setRemark(String value) {
		this.remark = value;
	}
	
	
	public boolean isEmpty() {
	    boolean result = true;
	    
	    result = result && (Utils.isStringEmpty(name));
	    result = result && (Utils.isStringEmpty(directory));
        result = result && (Utils.isStringEmpty(newDirectory));
        if (pdflist != null) {
            result = result && (pdflist.size() == 0);
        }
	    
	    return result; 
	}

}
