package com.kewill.kcx.component.mapping.test.testUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.kewill.kcx.component.mapping.test.testUtils.db.DbUtils;
import com.kewill.kcx.component.mapping.test.testUtils.db.TestValuesDTO;
import com.kewill.kcx.component.mapping.util.Utils;
import com.sun.xml.xsom.XSComplexType;
import com.sun.xml.xsom.XSContentType;
import com.sun.xml.xsom.XSElementDecl;
import com.sun.xml.xsom.XSFacet;
import com.sun.xml.xsom.XSModelGroup;
import com.sun.xml.xsom.XSParticle;
import com.sun.xml.xsom.XSRestrictionSimpleType;
import com.sun.xml.xsom.XSSchema;
import com.sun.xml.xsom.XSSchemaSet;
import com.sun.xml.xsom.XSSimpleType;
import com.sun.xml.xsom.XSTerm;
import com.sun.xml.xsom.XSType;
import com.sun.xml.xsom.parser.XSOMParser;

/**
 * Produce sample XML data from given XML schema.
 * 
 * @author Pete.thomas
 * @version 1.0.00
 *
 */
public class XmlFromXsdDb {

	private static final String ST_DATE_FMT = "yyyyMMdd";
	private static final String ST_DATETIME_FMT = "yyyyMMddHHmm";
	private static final String ST_DATEFORMATTED_FMT = "yyyy-MM-dd";
	private static final String ST_DATETIMEFORMATTED_FMT = "yyyy-MM-dd'T'HH:mm:ss Z";
	
	private static final String XSD_DATE_FMT = "yyyy-MM-dd";
	private static final String XSD_DATETIME_FMT = "yyyy-MM-dd'T'HH:mm:ss Z";
	
    private static boolean useFixedValues = true;
    
    private static TestValuesDTO testValuesDTO = new TestValuesDTO();
    private static int           level         = -2;
    private static String[]      tagName       = new String[10];
    private static boolean       inComplexType = false;
	
    @SuppressWarnings("unused")
	private final class SimpleTypeRestriction {
        private SimpleTypeRestriction() { };
        
	    private String[] enumeration = null;
		private int maxValue = -1;
	    private int minValue = -1;
	    private int length = -1;
	    private int maxLength = -1;
	    private int minLength = -1;
	    private String pattern = null;
	    private int totalDigits = -1;
	}

	private class ValueDispenser {
		private HashMap<String, Integer> stringCountMap = new HashMap<String, Integer>();
		private HashMap<Integer, String> fixedLengthStrings = new HashMap<Integer, String>();
		private int lastInteger = 0;
		private double lastDecimal = 0.12;
//		private GregorianCalendar lastDate = new GregorianCalendar();
        private GregorianCalendar lastDate = new GregorianCalendar(2000, 00, 00, 00, 00, 00);
		private boolean lastBoolean = false;
		
		public String nextString(String value) throws Exception {
			return nextString(value, new SimpleTypeRestriction());
		}
		
		public String nextString(String value, SimpleTypeRestriction restrictions) throws Exception {
			String workingValue = value;
			
			if (!useFixedValues) {
    			//Allow space for 1 additional digit in value if there is a max length restriction
    			if ((restrictions.maxLength >= 0) && (workingValue.length() >= restrictions.maxLength)) {
    				workingValue = workingValue.substring(0, restrictions.maxLength - 1);
    			}
    			
    			while (workingValue.length() > 0) {
    				Integer count = stringCountMap.get(workingValue);
    				
    				if (count == null) {
    					count = new Integer(0);
    				}
    				
    				count++;
    				
    				if ((restrictions.maxLength < 0) || 
    				        (count < Math.pow(10, (restrictions.maxLength - workingValue.length())))) { 
    					stringCountMap.put(workingValue, count);
    					workingValue = workingValue + count.toString();
    					
    					if (restrictions.minLength > workingValue.length()) {
    						workingValue = workingValue + makeFixedLengthString(
    								'_', restrictions.minLength - workingValue.length());
    					}
    					
    					return workingValue;
    				}
    				
    				workingValue = workingValue.substring(0, workingValue.length() - 1);
    			}
			}
			
			// if here then all truncated versions of the value with trailing digits that can
			// fit within the maximum length have already been used so now use purely
			// alphabetic fixed-length values instead.
			Integer key = new Integer(restrictions.maxLength);
			if (key < 0) {
			    key = value.length();
			}
			String fixedLengthString = fixedLengthStrings.get(key);

            int length = key.intValue();
			if (fixedLengthString == null) {
//				fixedLengthString = makeFixedLengthString('A', restrictions.maxLength);
                fixedLengthString = makeFixedLengthString('A', length);
			} else {
//				int pos = restrictions.maxLength - 1;
                int pos = length - 1;
				char [] charArray = fixedLengthString.toCharArray();
				
				while (pos >= 0) {
					if (charArray[pos] < 'Z') {
						charArray[pos] = ++charArray[pos];
						fixedLengthString = new String(charArray);
						break;
					}
					
					charArray[pos] = 'A';
					pos--;
				}
				
				if (pos < 0) {
					throw new Exception(String.format(
                            "Could not generate unique value for field '%s' with maximum length of %d",
							value,
							restrictions.maxLength));
				}
			}
			
			fixedLengthStrings.put(key, fixedLengthString);
			return fixedLengthString;
		}
		
		public String nextInteger() {
			lastInteger++;
			return String.valueOf(lastInteger);
		}
		
		public String nextDecimal() {
			lastDecimal++;
			return String.valueOf(lastDecimal);
		}
		
		public String nextDate(String formatString) {
			SimpleDateFormat sdf = new SimpleDateFormat(formatString);
			lastDate.add(Calendar.DAY_OF_MONTH, 1);
			return sdf.format(lastDate.getTime());
		}
		
		public String nextBoolean() {
			lastBoolean = !lastBoolean;
			return String.valueOf(lastBoolean);
		}
		
		private String makeFixedLengthString(char value, int size) {
			StringBuilder sBuilder = new StringBuilder(size);
			
			for (int myCount = 0; myCount < size; myCount++) {
				sBuilder.append(value);
			}
			
			return sBuilder.toString();
		}
	}
	
	private ValueDispenser valueDispenser = new ValueDispenser();
	
	public static void main(String[] args) {
		String xsdFileName = null;
		String rootElemTag = null;
		String rootElemTypeName = null;

		if (args.length < 2) {
            System.out.println("Parameters: xsd_file_name root_element_tag [root_element_type_name]");
			return;
		}
		
		xsdFileName = args[0];
		rootElemTag = args[1];
		
		if (args.length > 2) {
			rootElemTypeName = args[2];
		}
		
		// Testweise fix verdrahtet
		testValuesDTO.setProcedure("EMCS");
        testValuesDTO.setMessageType("UIDS");
        testValuesDTO.setMessageName("EMCSDeclaration");
        testValuesDTO.setTestCase("Diehl1");
		
		
		Config.configure("conf", "xsd2xml.ini");
		useFixedValues = Config.isUseFixedValues();
		
		XmlFromXsdDb obj = new XmlFromXsdDb();

		try {
            String targetFileName = null;
		    if (!Utils.isStringEmpty(Config.getTargetDirectory())) {
	            targetFileName = String.format("%s/%s.xml",
	                                           Config.getTargetDirectory(),
                                               rootElemTag);
		    } else {
	            targetFileName = String.format("%s_%tY%2$tm%2$td_%2$tH%2$tM%2$tS.xml",
                                               rootElemTag,
                                               new GregorianCalendar());
		    }
			File outFile =
				obj.parseToFile(xsdFileName, targetFileName, rootElemTag, rootElemTypeName);
			System.out.println("XML data written to " + outFile.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
		}
/*
		try {
			String xml = obj.parse(xsdFileName, rootElemTag, rootElemTypeName);
			System.out.println(xml);
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
	}

	/**
	 * Parse the named xml schema, producing a sample xml file that conforms to that schema. 
	 * 
	 * @param xsdFileName - full name of xml schema
	 * @param targetFileName - name of xml file to be created
	 * @param rootElemTag - name of root element for output xml.  Thia will also be selected as the
     *                      element to be read if found in the schema and the e<b>rootElemTypeNam</b>
	 * 						is not supplied.
	 * @param rootElemTypeName - if not null this parameter gives the type of the root element, allowing
	 *                           for the element to be generated even if there is no root element declaration
	 * 							 in the schema
	 * @return xml file object
	 * @throws Exception   Exceptio
	 */
	public File parseToFile(String xsdFileName, String targetFileName, String rootElemTag, 
	                        String rootElemTypeName) throws Exception {
		File targetFile;
		FileWriter fileWriter;
		String xml;
		
		xml = parse(xsdFileName, rootElemTag, rootElemTypeName);
		targetFile = new File(targetFileName);
		fileWriter = new FileWriter(targetFile);
		fileWriter.write(xml);
		fileWriter.flush();
		fileWriter.close();

		return targetFile;
	}
	
	/**
	 * Parse the named xml schema, producing a sample xml string that conforms to that schema. 
	 * 
	 * @param xsdFileName - full name of xml schema
	 * @param rootElemTag - name of root element for output xml.  Thia will also be selected as the
	 *                      element to be read if found in the schema and the e<b>rootElemTypeNam</b>
	 * 						is not supplied.
	 * @param rootElemTypeName - if not null this parameter gives the type of the root element, allowing
	 *                           for the element to be generated even if there is no root element declaration
	 * 							 in the schema
	 * @return xml sample data as a string
	 * @throws Exception   Exception
	 */
	public String parse(String xsdFileName, String rootElemTag, String rootElemTypeName) throws Exception {
		XSOMParser parser;
		File xsdFile;
		XSSchemaSet schemaSet;
		XSSchema xsSchema;
		
    	xsdFile = new File(xsdFileName);
        parser = new XSOMParser();
        parser.parse(xsdFile);
        schemaSet = parser.getResult();
        xsSchema = schemaSet.getSchema(1);
        return parse(xsSchema, rootElemTag, rootElemTypeName);
	}

	/**
	 * Parse the named xml schema, producing a sample xml string that conforms to that schema. 
	 * 
	 * @param xsSchema - XSOM schema object representation of the xml schema
	 * @param rootElemTag - name of root element for output xml.  Thia will also be selected as the
	 *                      element to be read if found in the schema and the e<b>rootElemTypeNam</b>
	 * 						is not supplied.
	 * @param rootElemTypeName - if not null this parameter gives the type of the root element, allowing
	 *                           for the element to be generated even if there is no root element declaration
	 * 							 in the schema
	 * @return xml sample data as a string
	 * @throws Exception       Exception
	 */
	public String parse(XSSchema xsSchema, String rootElemTag, String rootElemTypeName) throws Exception {
		XSType xsType;
		Document targetDom = DocumentHelper.createDocument();
		Element targetElem = targetDom.addElement(rootElemTag);

		if (isEmpty(rootElemTypeName)) {
			XSElementDecl xsElementDecl = xsSchema.getElementDecl(rootElemTag);
			
			if (xsElementDecl == null) {
				throw new Exception(
				        String.format("Root element tag '%s' has no element declaration in schema", 
						              rootElemTag));
			}
			
			xsType = xsElementDecl.getType();
		} else {
			xsType = xsSchema.getType(rootElemTypeName);
		}

		processType(targetElem, xsType);
		
		StringWriter sw = new StringWriter();
		OutputFormat outformat = OutputFormat.createPrettyPrint();
		XMLWriter writer = new XMLWriter(sw, outformat);
		writer.write(targetDom);
		writer.flush();
		
		return sw.toString();
		//return targetDom.asXML();
	}
	
	private boolean isEmpty(String value) {
		if ((value == null) || (value.trim().equals(""))) {
			return true;
		}
		
		return false;
	}

	private void processType(Element targetElem, XSType xsType) throws Exception {
		// Only process complex or simple types
		String name = targetElem.getName();
        Utils.log("(XMLFromXSD processType) Element name = " + name);
//        if (xsType.isComplexType()) {
//            XSType xsType2 = xsType.getBaseType(); 
//            if (xsType2 != null) {
//                String name2 = xsType2.getName();
//                Utils.log("(XMLFromXSD processType) Element name2 = " + name2);
//                XSType xsType3 = xsType.getBaseType(); 
//                if (xsType3.getBaseType() != null) {
//                    String name3 = xsType3.getName();
//                    Utils.log("(XMLFromXSD processType) Element name3 = " + name3);
//                    testValuesDTO.setTagLevel1(name);
//                    testValuesDTO.setTagLevel2(name2);
//                    testValuesDTO.setTagLevel3(name3);
//                } else {
//                    testValuesDTO.setTagLevel1(name);
//                    testValuesDTO.setTagLevel2(name2);
//                    testValuesDTO.setTagLevel3(" ");
//                }
//            } else {
//                testValuesDTO.setTagLevel1(name);
//                testValuesDTO.setTagLevel2(" ");
//                testValuesDTO.setTagLevel3(" ");
//            }
//        }
        if (xsType.isComplexType()) {
            inComplexType = true;
            level++;
            Utils.log("(XmlFromXsdDb processType) level           = " + level);
            if (level > 0) {
                tagName[level] = name;
                Utils.log("(XmlFromXsdDb processType) tagName[level]  = " + tagName[level]);
            }
        }
        if (xsType.isSimpleType()) {
            Utils.log("(XmlFromXsdDb processType) level = " + level);
            String name2 = tagName[2]; 
            if (name2 != null) {
                Utils.log("(XmlFromXsdDb processType) Element name2 = " + name2);
                String name3 = tagName[3]; 
                if (name3 != null) {
                    Utils.log("(XmlFromXsdDb processType) Element name3 = " + name3);
                    testValuesDTO.setTagLevel1(name);
                    testValuesDTO.setTagLevel2(name2);
                    testValuesDTO.setTagLevel3(name3);
                } else {
                    testValuesDTO.setTagLevel1(name);
                    testValuesDTO.setTagLevel2(name2);
                    testValuesDTO.setTagLevel3(" ");
                }
            } else {
                testValuesDTO.setTagLevel1(name);
                testValuesDTO.setTagLevel2(" ");
                testValuesDTO.setTagLevel3(" ");
            }
            if (inComplexType) {
                inComplexType = false;
                level = 0;
                tagName = new String[10];
            }
        }
        
        String value = DbUtils.getTestValues(testValuesDTO);
//        Utils.log("(XmlFromXsdDb processType) value = " +  value);
        
        if (xsType.isComplexType()) {
			processComplexType(targetElem, xsType.asComplexType());
		} else if (xsType.isSimpleType()) {
			processSimpleType(targetElem, xsType.asSimpleType());
		}
	}

	/**
	 * Produce generated sample data for a simple element, observing element type.
	 *  
	 * @param targetElem - Element in the target xml to assign value to 
	 * @param xsSimpleType - Type of the data for the element
	 * @throws Exception Exception
	 */
	private void processSimpleType(Element targetElem, XSSimpleType xsSimpleType) throws Exception {

		SimpleTypeRestriction restrictions = getSimpleTypeRestriction(xsSimpleType);

		String primitiveTypeName = xsSimpleType.getPrimitiveType().getName();
		
		if (xsSimpleType.getName().startsWith("ST_Date")) {
			if (xsSimpleType.getName().equals("ST_Date")) {
				targetElem.setText(valueDispenser.nextDate(ST_DATE_FMT));
			} else if (xsSimpleType.getName().equals("ST_DateTime")) {
				targetElem.setText(valueDispenser.nextDate(ST_DATETIME_FMT));
			} else if (xsSimpleType.getName().equals("ST_DateFormatted")) {
				targetElem.setText(valueDispenser.nextDate(ST_DATEFORMATTED_FMT));
			} else if (xsSimpleType.getName().equals("ST_DateTimeFormatted")) {
				targetElem.setText(valueDispenser.nextDate(ST_DATETIMEFORMATTED_FMT));
			}
		} else if (xsSimpleType.getName().equalsIgnoreCase("integer") ||
		          (xsSimpleType.getName().equalsIgnoreCase("int"))) {
			targetElem.setText(valueDispenser.nextInteger());
		} else if (primitiveTypeName.equalsIgnoreCase("string")) {
			targetElem.setText(valueDispenser.nextString(targetElem.getName(), restrictions));
		} else if ((primitiveTypeName.equalsIgnoreCase("decimal")) || 
		           (primitiveTypeName.equalsIgnoreCase("float")) || 
		           (primitiveTypeName.equalsIgnoreCase("double"))) {
			targetElem.setText(valueDispenser.nextDecimal());
		} else if (primitiveTypeName.equalsIgnoreCase("datetime")) {
			targetElem.setText(valueDispenser.nextDate(XSD_DATETIME_FMT));
		} else if (primitiveTypeName.equalsIgnoreCase("date")) {
			targetElem.setText(valueDispenser.nextDate(XSD_DATE_FMT));
		} else if (primitiveTypeName.equalsIgnoreCase("boolean")) {
			targetElem.setText(valueDispenser.nextBoolean());
		}
	}

	/**
	 * Process all components of a complex type.
	 * 
	 * @param targetParentElem - Element in the target xml to assign any child elements to
	 * @param xsComplexType - Definition of the structure of the complex type
	 * @throws Exception Exception
	 */
	private void processComplexType(Element targetParentElem, XSComplexType xsComplexType) throws Exception {
	    XSContentType xsContentType = xsComplexType.getContentType();
	    XSParticle particle = xsContentType.asParticle();
	    
	    if (particle != null) {
	        XSTerm term = particle.getTerm();
	        
	        //check if complex type contains child elements
	        
	        if (term.isModelGroup()) {
	        	
	        	//process child element group once, twice or minOccurs times, depending on
	        	//whether the complex type has repeats specified
	        	
	        	for (int grpCount = 0; grpCount < getMaxLoops(particle); grpCount++) {
		            XSModelGroup xsModelGroup = term.asModelGroup();
		            XSParticle[] particles = xsModelGroup.getChildren();
		            
		            for (XSParticle childParticle : particles) {
		                XSTerm pterm = childParticle.getTerm();
	
			        	//process each child element once, twice or minOccurs times, depending on
			        	//whether the child element has repeats specified
			        	
			        	for (int elemCount = 0; elemCount < getMaxLoops(childParticle); elemCount++) {
			                if (pterm.isElementDecl()) { //xs:element inside complex type
			        			XSElementDecl xsElementDecl = pterm.asElementDecl();
                                Element targetElem = targetParentElem.addElement(xsElementDecl.getName());
			        			processType(targetElem, xsElementDecl.getType());
			                } else if (pterm.isWildcard()) { //
                                targetParentElem.setText(valueDispenser.nextString(targetParentElem.getName()));
			                }
			        	}
		            }
	        	}
	        }
	    }
	}
	
	private int getMaxLoops(XSParticle particle) {
    	int maxLoops = 1;

    	if (!particle.getTerm().isWildcard()) {
	    	if (particle.getMinOccurs() > 1) {
	    		maxLoops = particle.getMinOccurs();
	    	} else if (particle.getMaxOccurs() != 1) {
	    		maxLoops = 2;
	    	}
    	}
    	
    	return maxLoops;
	}
	
	private SimpleTypeRestriction getSimpleTypeRestriction(XSSimpleType xsSimpleType) {
		SimpleTypeRestriction result = new SimpleTypeRestriction();
	    XSRestrictionSimpleType restriction = xsSimpleType.asRestriction();
	    
	    if (restriction != null) {
	        ArrayList<String> enumeration = new ArrayList<String>();
	        
	        Iterator< ? extends XSFacet> iter = restriction.getDeclaredFacets().iterator();
	        
	        while (iter.hasNext()) {
	            XSFacet facet = iter.next();
	            
	            if (facet.getName().equals(XSFacet.FACET_ENUMERATION)) {
	                enumeration.add(facet.getValue().value);
	            }
	            if (facet.getName().equals(XSFacet.FACET_MAXINCLUSIVE)) {
	                result.maxValue = Integer.parseInt(facet.getValue().value);
	            }
	            if (facet.getName().equals(XSFacet.FACET_MININCLUSIVE)) {
	                result.minValue = Integer.parseInt(facet.getValue().value);
	            }
	            if (facet.getName().equals(XSFacet.FACET_MAXEXCLUSIVE)) {
	                result.maxValue = Integer.parseInt(facet.getValue().value) - 1;
	            }
	            if (facet.getName().equals(XSFacet.FACET_MINEXCLUSIVE)) {
	                result.minValue = Integer.parseInt(facet.getValue().value) + 1;
	            }
	            if (facet.getName().equals(XSFacet.FACET_LENGTH)) {
	                result.length = Integer.parseInt(facet.getValue().value);
	            }
	            if (facet.getName().equals(XSFacet.FACET_MAXLENGTH)) {
	                result.maxLength = Integer.parseInt(facet.getValue().value);
	            }
	            if (facet.getName().equals(XSFacet.FACET_MINLENGTH)) {
	                result.minLength = Integer.parseInt(facet.getValue().value);
	            }
	            if (facet.getName().equals(XSFacet.FACET_PATTERN)) {
	                result.pattern = facet.getValue().value;
	            }
	            if (facet.getName().equals(XSFacet.FACET_TOTALDIGITS)) {
	                result.totalDigits = Integer.parseInt(facet.getValue().value);
	            }
	        }
	        
	        if (enumeration.size() > 0) {
	            result.enumeration = enumeration.toArray(new String[]{});
	        }
	    }
		
		return result;
	}
}
