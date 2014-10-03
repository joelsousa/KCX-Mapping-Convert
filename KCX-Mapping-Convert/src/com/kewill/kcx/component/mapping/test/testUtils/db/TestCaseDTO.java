package com.kewill.kcx.component.mapping.test.testUtils.db;

/**
 * Modul		: TestCaseDTO<br>
 * Erstellt		: 28.10.2008<br>
 * Beschreibung	: Data Transfer Object for the "TEST_CASES" database table. 
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class TestCaseDTO {
	private String   procedure   = null;               // KEY - EMCS, ICS, AES, NCTS, ...
	private String   messageType = null;               // KEY - UIDS, KIDS, ...
	private String   messageName = null;               // KEY - EMCSDeclaration, ...
	private String   testCase    = null;               // KEY - Name of the test case 
	private String   description = null;               // Description of the test case
	
	
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
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
	
	
}
