package javabean;

import java.sql.*;
import java.util.List;

public class LoginDB {
	Connection conn;
	Statement stmt;
	private String forname;
	private String url;
	private String user;
	private String pass;

	public LoginDB() {
		
		forname = "com.mysql.jdbc.Driver";
		url = "jdbc:mysql://localhost:8889/sales";
		user = "root";
		pass = "root";
		
	}

	public void connectDB(){
		try {
			Class.forName(this.forname);
			conn = DriverManager.getConnection(
					this.url, this.user, this.pass);
			stmt = conn.createStatement();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
	public int login(String name, String password) throws SQLException {
		try {
			String sql1 = "select count(0) from Person where username='"
					+ name + "'";
			ResultSet rs1 = stmt.executeQuery(sql1);
			if (rs1.next()) {
				int userCount = rs1.getInt(1);
				if (0 == userCount) {
					conn.close();
					return 1;
				}
				String sql2 = "select count(0) from Person where username='"
						+ name + "'and password='" + password + "'";
				ResultSet rs2 = stmt.executeQuery(sql2);
				if (rs2.next()) {
					int trueUserCount = rs2.getInt(1);
					if (0 == trueUserCount) {
						conn.close();
						return 2;
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		conn.close();
		return 3;

	}
	
	public boolean ProductExist(String name) throws SQLException{
		String sql =  "select count(0) from Product where productname='"
				+ name + "'";
		ResultSet rs1 = stmt.executeQuery(sql);
		if(rs1.next()){
			int trueUserCount = rs1.getInt(1);
			if (0 == trueUserCount) {
				conn.close();
				return false;
			}
		}
		conn.close();
		return true;

	}
	
	public int getUserid(String name) throws SQLException{
		String sql1 = "select id from Person where username='"
				+ name + "'";
		ResultSet rs1 = stmt.executeQuery(sql1);
		int result = rs1.getInt(1);
		conn.close();
		return result;
	}

	public int getProductid(String name) throws SQLException{
		String sql1 = "select id from Person where username='"
				+ name + "'";
		ResultSet rs1 = stmt.executeQuery(sql1);
		int result = rs1.getInt(1);
		conn.close();
		return result;
	}

	

}
