package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import vo.JoinVO;
import vo.MemberVO;

public class MemberDAO {

	public int insertMember(MemberVO member) {
		Connection con 			= null;
		PreparedStatement pstmt = null;
		StringBuilder query = new StringBuilder();
		int rowcnt =0;
		String url 		="jdbc:oracle:thin:@localhost:1521:xe";
		String user 	= "ora_user";
		String password = "hong";
		
		query.append("	insert into contact			 		 ");
		query.append("	values(numseq.nextval,?,?,?,?,?,?,?) ");
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url,user,password);
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getPhone1());
			pstmt.setString(3, member.getPhone2());
			pstmt.setString(4, member.getPhone3());
			pstmt.setString(5, member.getAddress());
			pstmt.setInt(6, member.getGroupnum());
			pstmt.setString(7, member.getId());
			rowcnt= pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return rowcnt;
	}
	
	public int insertJoin(JoinVO join) {
		
		Connection con 			= null;
		PreparedStatement pstmt = null;
		StringBuilder query		= new StringBuilder();
		int rowcnt 				= 0;
		String url 		="jdbc:oracle:thin:@localhost:1521:xe";
		String user 	= "ora_user";
		String password = "hong";
		
		query.append("	insert into loginfo	");
		query.append("	values(?,?)			");
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url,user,password);
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, join.getId());
			pstmt.setString(2, join.getPw());
			rowcnt = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return rowcnt;
	}
	
	
}
