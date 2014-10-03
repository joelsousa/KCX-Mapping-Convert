package com.kewill.kcx.component.mapping.formats.fss.common;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Function		:	FssDatei (copied from FssDatei of project fssin)
 * Date			:	17.09.2008
 * Description  :	Functions for writing FSS-files in ZABIS format, writing lines (teilsatz). 
 * @author 			MS / SH
 * @version 1.0.00
 */
public class FssDatei {
    private String fssOutFileName;      // name of FSS-output file including full path
    private OutputStreamWriter fssOut;  // output stream for writing lines (teilsatz)
    
    /**
     * Initialize FssDatei.
     * 
     * @param outFileName   name of output file
     */
    public FssDatei(String outFileName) {
        fssOutFileName  = outFileName;
     }
    
    /**
     * open FSS output file with char set ISO-8859-1.
     * @throws FssException Allgemeine FSS-Ausnahme.
     */ 
    public void openOutput() throws FssException {
        try {
            FileOutputStream fos = new FileOutputStream(fssOutFileName);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            fssOut = new OutputStreamWriter(bos, "ISO-8859-1");
        } catch (IOException e) {
            throw new FssException("FssDatei: openOutput IO-Exception:" + e.getMessage());
        }
    }

    /**
     * open FSS output file.
     * @param charsetName (String) Name of char set for output file
     * @throws FssException Allgemeine FSS-Ausnahme.
     */ 
    public void openOutput(String charsetName) throws FssException {
        try {
            FileOutputStream fos = new FileOutputStream(fssOutFileName);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            fssOut = new OutputStreamWriter(bos, charsetName);
        } catch (IOException e) {
            throw new FssException("FssDatei: openOutput IO-Exception:" + e.getMessage());
        }
    }
    
    /**
     * Write String teilsatz into FSS output file and add a line feed. 
     * @param teilsatz Der zu schreibende Teilsatz.
     * @throws FssException  Allgemeine FSS-Ausnahme.
     */
    public void writeTeilsatz(String teilsatz) throws FssException  {
        try {
            fssOut.write(teilsatz);       // write a line (String teilsatz)
            fssOut.write(Utils.LF);       // line feed
        } catch (IOException e) {
            throw new FssException("FssDatei: writeTeilsatz IO-Exception:" + e.getMessage());
        }
    }
    
    /**
     * close output file.
     * @throws FssException  Allgemeine FSS-Ausnahme.
     */
    public void closeOutput() throws FssException {
        try {
            fssOut.close();     // close output file
        } catch (IOException e) {
            e.printStackTrace();
            throw new FssException("FssDatei: closeOutput IO-Exception:" + e.getMessage());
        }
    }

	public String getFssOutFileName() {
		return fssOutFileName;
	}

	public void setFssOutFileName(String fssOutFileName) {
		this.fssOutFileName = fssOutFileName;
	}
    
	public String appendString(String in, String append) throws FssException {
		return in + append + Utils.LF;
	}
}
