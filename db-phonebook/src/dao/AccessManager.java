package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



/**
 * @작성자 :  	편도훈
 * @작성일 : 		2021. 1. 21.
 * @filename : 	AccessManager.java
 * @package : 	dao
 * @description :
 */
public class AccessManager {
	
	public static Connection getConnection() {
		Connection con= null;
		String url 		= "jdbc:oracle:thin:@localhost:1521:xe";
		String user 	= "ora_user";
		String password = "hong";
		
		try {
			con = DriverManager.getConnection(url,user,password);
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return con;
		
	}
	
	public static void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
		
		try {
			if(rs!=null) {
				rs.close();
			}
			
			if(pstmt!=null) {
				pstmt.close();
			}
			
			if(con!=null) {
				con.close();
			}
			
		}catch(SQLException e) {
//			sql 오류구문 출력
			e.printStackTrace();
		}
	}
	
	public static void close(Connection con, PreparedStatement pstmt) {
		try {
			
			if(pstmt!=null) {
				
				pstmt.close();
			}
			
			if(con!=null) {
				
				con.close();
			}
			
		}catch(SQLException e) {
			
			e.printStackTrace();
		}
	}

}
