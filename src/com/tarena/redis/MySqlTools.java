package com.tarena.redis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.tarena.util.DBUtil;
import com.tarena.util.SystemConstant;

public class MySqlTools implements SystemConstant {
	public static String getPassword(String name){
		String sql = "select user_password from cn_user where user_name = ?";
		String password = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs=pstmt.executeQuery();
			if(rs.next()){
				password = rs.getString("user_password");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			DBUtil.close(rs, pstmt, conn);
		}
		return password;
	}
	public static Map<String,String> getUsers(){
		String sql = "select user_name,user_password from cn_user";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Map<String,String> users = new HashMap<String, String>();
		try {
			conn = DBUtil.getConn();
			stmt = conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				String name = rs.getString("user_name");
				String password = rs.getString("user_password");
				users.put(name, password);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			DBUtil.close(rs, stmt, conn);
		}
		return users;
	}
	public static Map<String,String> getNameIds(){
		String sql = "select user_name,user_id from cn_user";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Map<String,String> users = new HashMap<String, String>();
		try {
			conn = DBUtil.getConn();
			stmt = conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				String name = rs.getString("user_name");
				String id = rs.getString("user_id");
				users.put(name, id);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			DBUtil.close(rs, stmt, conn);
		}
		return users;
	}
}
