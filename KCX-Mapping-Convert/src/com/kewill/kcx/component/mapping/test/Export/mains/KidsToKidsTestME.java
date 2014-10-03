package com.kewill.kcx.component.mapping.test.Export.mains;

/*
 * Function    : KidsToKidsConfirmationME.java
 * Titel       :
 * Date        : 29.09.2008
 * Author      : Kewill CSF / messer
 * Description : Test class specially used by ME
 * 			   : 
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;

import org.apache.tools.ant.filters.StringInputStream;

import com.kewill.kcx.component.mapping.common.start.customer.in.KidsToKidsExtern;
import com.kewill.kcx.component.mapping.util.Utils;

public class KidsToKidsTestME {

	public static void main(String[] args) {
		KidsToKidsExtern kidsToKids = new KidsToKidsExtern();
		
		String payload = getFileMessage(args[0]);
    	// Utils.log("KidsToKids : " + payload);
    	
    	InputStream ins = new StringInputStream(payload);
        InputStreamReader is = new InputStreamReader(ins);

        String xml = "";
        try {
        	XMLInputFactory factory = XMLInputFactory.newInstance();
        	XMLEventReader parser = factory.createXMLEventReader(is);
		
			xml = kidsToKids.convert(new File(args[0]), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
        Utils.log("(KidsToKids main) Converted Message = \n" + xml);
        // System.out.println("Formatted:\n"+formatXmlString(xml, "    "));
	}
	public static String formatXmlString(String s,String spacer) {
		s=s.replace(">\n", ">");
		int depth = -1;
		String startTag = "<([^/])[^<>]*?>";
		String endTag = "</[^<>]*?>";
		String comment="<!--[^<>]*?-->";
		String nullTag="<[^<>]*?/>";
		String encodingTag="<\\?xml version=.*?>";
		String sTagValueSTag = startTag+ "("+comment+")?" +"[^<>]+?" + startTag;
		String sTagValueTagE = startTag + "("+comment+")?"+"[^<>]*?" + endTag;
		
		Pattern strpat = null;
		Matcher strmat = null;
		String formatted = "";
		strpat = Pattern.compile(encodingTag+"|"+nullTag+"|"+comment+"|"+sTagValueTagE +"|"+sTagValueSTag+ "|" + startTag + "{1,1}|"
				+ endTag + "{1,1}");// "&[^&]*&");
		strmat = strpat.matcher(s);
		int count = 0;
		while (strmat.find()) {
			
			if (s.substring(strmat.start(), strmat.end())
					.matches(sTagValueTagE)||s.substring(strmat.start(), strmat.end())
					.matches(comment)||s.substring(strmat.start(), strmat.end())
					.matches(nullTag)||s.substring(strmat.start(), strmat.end())
					.matches(encodingTag)) {
				
				formatted +=insertSpacer(depth,spacer)
						+ s.substring(strmat.start(), strmat.end()) + "\n";
			} else if (s.substring(strmat.start(), strmat.end()).matches(
					startTag)||s.substring(strmat.start(), strmat.end())
					.matches(sTagValueSTag)) {
				if(s.substring(strmat.start(), strmat.end()).matches(startTag)) {
				formatted +=insertSpacer(depth,spacer)
						+ s.substring(strmat.start(), strmat.end()) + "\n";
				depth++;
				} else {
					String value="[^<>]+";
					String temp=s.substring(strmat.start(), strmat.end());
					Pattern strpatT = Pattern.compile(comment+"|"+ startTag + "{1,1}|"
							+ endTag + "{1,1}|"+value);// "&[^&]*&");
					Matcher strmatT = strpatT.matcher(temp);
					while(strmatT.find()) {
					if(temp.substring(strmatT.start(), strmatT.end()).matches(startTag)&&!temp.substring(strmatT.start(), strmatT.end()).matches(comment)) {
						formatted +=insertSpacer(depth,spacer)
						+ temp.substring(strmatT.start(), strmatT.end()) + "\n";
						depth++;
					} else if(temp.substring(strmatT.start(), strmatT.end()).matches(comment)) {
						formatted +=insertSpacer(depth,spacer)
						+ temp.substring(strmatT.start(), strmatT.end()) + "\n";
						
					} else if(temp.substring(strmatT.start(), strmatT.end()).matches(value)) {
						formatted +=insertSpacer(depth,spacer)
						+ temp.substring(strmatT.start(), strmatT.end()) + "\n";
					} 
					}
					
					
				}
			} else {
				depth--;
				formatted += insertSpacer(depth,spacer)
						+ s.substring(strmat.start(), strmat.end()) + "\n";

			}
			count++;
		}

		return formatted;
	}

	protected static String insertSpacer(int depth,String spacer) {
		String sp = "";
		for (int i = depth; i >= 0; i--) {
			sp += spacer;
		}
		return sp;
	}
	private static String getFileMessage(String fileName) {
		File inFile = new File(fileName);
		StringBuffer payload = new StringBuffer();
		
		try {
			FileReader fr = new FileReader(inFile);
			BufferedReader in = new BufferedReader(fr);
			String line = null;
			line = in.readLine();
			while (line != null) {
				line += Utils.LF;
				payload.append(line);
				line = in.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return payload.toString();
	}
}

