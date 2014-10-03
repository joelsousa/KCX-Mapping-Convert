package com.kewill.kcx.component.mapping.test.testUtils.db;

/**
 * Modul		: TestCaseDTO<br>
 * Erstellt		: 28.10.2008<br>
 * Beschreibung	: Data Transfer Object for the "TEST_VALUES_UIDS" database table. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class TestValuesDTO {
	private String   procedure   = null;               // KEY - EMCS, ICS, AES, NCTS, ...
	private String   messageType = null;               // KEY - UIDS, KIDS, ...
	private String   messageName = null;               // KEY - EMCSDeclaration, ...
	private String   testCase    = null;               // KEY - Name of the test case 
	private String   area        = null;               // Area in message (header, body, record type ABC, ...)
    private String   tagLevel1   = null;               // KEY - Tag name ((XML) structure level 1 below area)
    private String   tagLevel2   = null;               // KEY - Tag name ((XML) structure level 2 below area)
    private String   tagLevel3   = null;               // KEY - Tag name ((XML) structure level 3 below area)
    private String   value       = null;               // Value to asign to the tag in testCase      
    
    
    
    public String getProcedure() {
        return procedure;
    }
    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }
    public String getMessageType() {
        return messageType;
    }
    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
    public String getMessageName() {
        return messageName;
    }
    public void setMessageName(String messageName) {
        this.messageName = messageName;
    }
    public String getTestCase() {
        return testCase;
    }
    public void setTestCase(String testCase) {
        this.testCase = testCase;
    }
    public String getArea() {
        return area;
    }
    public void setArea(String area) {
        this.area = area;
    }
    public String getTagLevel1() {
        return tagLevel1;
    }
    public void setTagLevel1(String tagLevel1) {
        this.tagLevel1 = tagLevel1;
    }
    public String getTagLevel2() {
        return tagLevel2;
    }
    public void setTagLevel2(String tagLevel2) {
        this.tagLevel2 = tagLevel2;
    }
    public String getTagLevel3() {
        return tagLevel3;
    }
    public void setTagLevel3(String tagLevel3) {
        this.tagLevel3 = tagLevel3;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
	
	
}
