package com.sqisoft.ssbr.datasync.util.validator;

import com.mitzz.frame.conf.Configuration;
import com.mitzz.frame.exception.LException;
import com.mitzz.frame.exception.SysException;
import com.mitzz.frame.util.DateUtil;

public class BizValid {
	public static final int checknull = 1;
	public static final int checkint = 2;
	public static final int checkdate = 3;
	public static final int varchar2_4000 = 2000;
	public static final int varchar2_2000 = 1000;
	public static final int varchar2_500 = 250;
	public static final int varchar2_100 = 50;
	public static final int varchar2_20 = 10;

	public static boolean checkNull(String str) {
		return (str == null) || (str.equals(""));
	}

	public static boolean checkInt(String str) {
		boolean ret = false;

		if (checkNull(str)) return ret;
		try
		{
			//int i = Integer.parseInt(str);
			ret = true;
		}
		catch (Exception localException)
		{
		}
		return ret;
	}

	public static boolean check(String str, String pattern) {
		boolean ret = false;

		if (checkNull(str)) return ret;
		try
		{
			//int i = Integer.parseInt(str);
			ret = true;
		}
		catch (Exception localException)
		{
		}
		return ret;
	}

	public static boolean checkLength(String str, int limit) {
		boolean ret = true;

		if (checkNull(str)) return ret;
		try
		{
			if (str.length() <= limit) ret = false; 
		}
		catch (Exception e) {
			ret = true;
		}

		return ret;
	}

	public static boolean checkByte(String str, int limit) {
		boolean ret = true;

		if (checkNull(str)) return ret;
		try
		{
			if (str.length() <= limit) ret = false; 
		}
		catch (Exception e) {
			ret = true;
		}

		return ret;
	}

	public static boolean checkDate(String str)	throws LException {
		boolean ret = false;
		if (checkNull(str)) return ret;
		String format = "";

		format = Configuration.getInstance().get("frame.date.format");

		if ((format == null) || (format.equals("")))
			throw new SysException("Date Format is Null. Check frame.conf file 'frame.date.format' attribute");
		try
		{
			String date = DateUtil.convertFormat(str, format);
			System.out.println(date);
			ret = true;
		} catch (LException e) {
			ret = false;
		}

		return ret;
	}
}