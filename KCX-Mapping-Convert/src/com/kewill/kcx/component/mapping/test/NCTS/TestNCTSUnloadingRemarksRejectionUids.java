package com.kewill.kcx.component.mapping.test.NCTS;

import java.util.ArrayList;

import org.incava.util.diff.Difference;

import com.kewill.kcx.component.mapping.test.TestUidsToUidsFromCustomer;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TestNCTSUnloadingRemarksRejectionUids
 * Created		: 31.08.2010
 * Description	: conversion of an NCTSUnloadingRemarksRejection from UIDS to KIDS and back to UIDS
 * 				: original and converted UIDS messages must be identical.
 * 
 * @author	: Michelle Bauza
 * @version	: 1.0.00
 *
 */
public class TestNCTSUnloadingRemarksRejectionUids extends TestUidsToUidsFromCustomer {
	
	public TestNCTSUnloadingRemarksRejectionUids(String name) {
		super(name);
		Utils.log("(TestNCTSUnloadingRemarksRejection " +
				"TestNCTSUnloadingRemarksRejection) name = " + name);
	}

	@Override
	protected void setInputFileName() {
		inputFileName	= "NCTSUnloadingRemarksRejection_20100831_135334.xml";
		encoding		= "UTF-8";
	}
	
	protected void runDiff(boolean split) {
		ArrayList< Difference > expected	= new ArrayList< Difference >();
		
		expected.add(new Difference(7, 7, 7, 7));
		expected.add(new Difference(19, 19, 19, 19));
		expected.add(new Difference(25,  Difference.NONE, 25, 26));
		expected.add(new Difference(30,  Difference.NONE, 32, 33));
		
		differencesExpected	= expected.size();
		expectedDifferences	= expected.toArray();
		errorMessage	= String.format("Number of differences is greater than %d", 
				differencesExpected);
		super.runDiff(true);
	}
}
