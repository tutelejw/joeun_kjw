package com.sqisoft.ssbr.datasync.util;

import com.mitzz.frame.exception.LException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserSessUtil {
	public static boolean isLoggedIn(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		return (session != null) && ((String)session.getAttribute("userNo") != null);
	}

	public static void getNewSess(HttpServletRequest req) throws LException {
		invalidate(req);
		req.getSession(true);
	}

	public static void invalidate(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if (session != null) {
			session.invalidate();
			session = null;
		}
	}

	public static String getUserNo(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if ((session != null) && ((String)session.getAttribute("userNo") != null)) {
			return (String)session.getAttribute("userNo");
		}
		return null;
	}

	public static String getUserName(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if ((session != null) && ((String)session.getAttribute("userName") != null)) {
			return (String)session.getAttribute("userName");
		}
		return null;
	}

	public static String getLastLoginTime(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if ((session != null) && ((String)session.getAttribute("lastLoginTime") != null)) {
			return (String)session.getAttribute("lastLoginTime");
		}
		return null;
	}
}