package com.kewill.kcx.component.mapping.util;

import java.text.ParseException;

public class DateTimeTest {

	public static void main(String[] args) {

		try {
			KcxDateTime kcx = new KcxDateTime(EFormat.ST_DateTimeT, "2008-09-10T12:34:56 +0200");
			System.out.println(kcx.format(EFormat.KIDS_DateTime));

			//SH090428 Test without time zone
			KcxDateTime kcx8 = new KcxDateTime(EFormat.ST_DateTimeT, "2009-04-28T11:22:33");
			System.out.println(kcx8.format(EFormat.ST_DateTimeT));
			KcxDateTime kcx9 = new KcxDateTime(EFormat.ST_DateTime, "2009-04-28 11:22:33");
			System.out.println(kcx9.format(EFormat.ST_DateTime));
			
			KcxDateTime kcx2 = new KcxDateTime(EFormat.KIDS_DateTime, "200712130835");
			System.out.println(kcx2.format(EFormat.ST_DateTime));

			KcxDateTime kcx3 = new KcxDateTime(EFormat.ST_Time_Min, "12:34");
			System.out.println(kcx3.format(EFormat.ST_Time_Ms));
			
			KcxDateTime kcx4 = new KcxDateTime("2008", "7", "28", "23:45", "");
			System.out.println(kcx4.format(EFormat.ST_DateTime));
			
			KcxDateTime kcx5 = new KcxDateTime("2009", "12", "8", "11:22:33", "+0300");
			System.out.println(kcx5.format(EFormat.ST_DateTime));
			
			KcxDateTime kcx6 = new KcxDateTime("2006", "4", "5", "095658", "+0300");
			System.out.println(kcx6.format(EFormat.ST_DateTime));
			
			KcxDateTime kcx7 = new KcxDateTime("2008", "11", "10", "0406", "+0400");
			System.out.println(kcx7.format(EFormat.ST_DateTime));
			
		} catch (ParseException e) {
			System.out.println(e.toString());
		}
		
	}

}
