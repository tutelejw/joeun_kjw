package com.sqisoft.ssbr.datasync;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleToMysql{
	public String RetriveOracleData() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append(" desc INTRAWARE.V_VNS_USER ");

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1. driver loading success");

			String url = "jdbc:oracle:thin:@10.222.106.11:1521:ora8";
			con = DriverManager.getConnection(url, "vnskms", "vns2011");
			System.out.println("2. connection success : " + con);

			stmt = con.createStatement();
			System.out.println("3. statement create success : " + stmt);

			String sql = "SELECT * FROM INTRAWARE.V_VNS_USER";
			rs = stmt.executeQuery(sql);
			int count = 0;
			while (rs.next()) {
				System.out.println(rs.getString(1));
				System.out.println(rs.getString(2));
				System.out.println(rs.getString(2));

				count++;
			}
			System.out.println("search result count for : ( " + count + " ) ");
		}
		catch (ClassNotFoundException cnfe) {
			System.out.println("driver not found");
		}catch (SQLException se) {
			System.out.println("Connection failed or  statement create failed  or statement run failed");
		}
		try{
			if (rs != null) {
				rs.close();
			}
		} catch (Exception ee) {
			System.out.println("resultset close error" + ee.getMessage());
		}
		
		try{
			if (stmt != null) {
				stmt.close();
			}
		}
		catch (Exception ee) {
			System.out.println("statement close error" + ee.getMessage());
		}
		try {
			if (con != null) {
				con.close();
			}
		}catch (Exception ee) {
			System.out.println("statement close error" + ee.getMessage());
		}
		System.out.println("connection close success");

		String qry = strBuf.toString();

		return qry.substring(0, qry.length() - 1);
	}

	public boolean InsertMysql(String query) {
		PreparedStatement pstmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://122.199.145.221:13306/sample", "ssbr", "system");
			conn.setAutoCommit(false);

			String delQry = "delete from sample.test";

			System.out.println(delQry);

			pstmt = conn.prepareStatement(delQry);

			int delCount = pstmt.executeUpdate();
			System.out.println("delete =" + delCount);

			pstmt = conn.prepareStatement(query);

			int rowCount = pstmt.executeUpdate();
			System.out.println("rowCount =" + rowCount);

			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException ex) {
			System.out.println("SQLException : " + ex.getMessage());
			System.out.println("SQLState : " + ex.getSQLState());
			System.out.println("VendorError : " + ex.getErrorCode());
		}
		return true;
	}

	public static void main(String[] args) {
		OracleToMysql otm = new OracleToMysql();
		for (int i = 0; i < 10; i++) {
			String query = otm.RetriveOracleData();

			System.out.println(query);
			otm.InsertMysql(query);
		}
	}
}