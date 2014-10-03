package com.kewill.kcx.tools;

import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.db.Db;


public class DbReoDeclnums {


	
	/**
	 * delete records from lastDeclnum older than number of days committed
     *  
     * @param args the number days .
	 */
	public static void main(String[] args) {
		int days;
		
		if (args == null) {
			System.out.println("DbReoDeclnums: no parameter : read number of days in kcx.ini...");
			Config.configure();
			days = Config.getReodays();
			
		} else if (args.length == 0) {
			System.out.println("DbReoDeclnums: no parameter : read number of days in kcx.ini...");
			if (!Config.isConfigured()) {
				Config.configure();
			}
			days = Config.getReodays();
        } else {
        	days = Integer.getInteger(args[0]);
        }
		System.out.println("DbReoDeclnums: Reo table declnums: deleting records older than " + days + " days");
		Db.deleteDeclNums(days);
		System.out.println("DbReoDeclnums: Reo table declnums finished");
	}
}
