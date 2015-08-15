package com.tarena.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;





public class DBUtil {
	private static String className;
	private static String url;
	private static String user;
	private static String password;
	private static BasicDataSource bs;
	private DBUtil(){}

	public static void loadParam() {
		Properties prop = new Properties();
		try {
			prop.load(DBUtil.class.getClassLoader().getResourceAsStream(
					"db.properties"));
			className = prop.getProperty("className");
			url = prop.getProperty("url");
			user = prop.getProperty("user");
			password = prop.getProperty("password");
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	static {
		loadParam();
		bs=new BasicDataSource();
		bs.setDriverClassName(className);
		bs.setUrl(url);
		bs.setUsername(user);
		bs.setPassword(password);
	}

	public static Connection getConn() throws SQLException {
		Connection conn = bs.getConnection();
		return conn;
	}

	public static void close(ResultSet rs, Statement stmt, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			try {
				if (stmt != null) {

					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} finally {
				try {
					if (conn != null) {
						conn.close();
						
					}
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
		}

	}
	
	public static void main(String[] args) throws SQLException {
		Connection conn = getConn();
		System.out.println(conn);
		close(null, null, conn);
		System.out.println(conn);
	}
	
}
