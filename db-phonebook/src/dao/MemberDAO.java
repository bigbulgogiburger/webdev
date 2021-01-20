package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.MemberVO;

public class MemberDAO {
//	1. 전체 회원 목록
	public static ArrayList<MemberVO> selectAll(){
		ArrayList<MemberVO> memberList	= new ArrayList<MemberVO>();
		
		Connection con 				= AccessManager.getConnection();
		PreparedStatement pstmt 	= null;
		ResultSet rs 				= null;
		
		StringBuilder sql			= new StringBuilder();
		
		sql.append("select p.name							");
		sql.append("     , p.phonenumber					");
		sql.append("     , p.address						");
		sql.append("     , g.group_name						");
		sql.append("  from phone_info p						"); 
		sql.append("     , group_info g						"); 
		sql.append(" where p.group_number = g.group_number 	");
		
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberVO member = new MemberVO();
				member.setName(rs.getString("name"));
				member.setPhoneNumber(rs.getString("phonenumber"));
				member.setAddress(rs.getString("address"));
				member.setGroup(rs.getString("group_name"));
				memberList.add(member);
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			AccessManager.close(con,pstmt,rs);
		}
		return memberList;
	}
//	2. 특정 회원 목록
	public static ArrayList<MemberVO> selectByName(String name) {
		ArrayList<MemberVO> memberList	= new ArrayList<MemberVO>();
		
		Connection con 				= AccessManager.getConnection();
		PreparedStatement pstmt 	= null;
		ResultSet rs 				= null;
		
		StringBuilder sql			= new StringBuilder();
		
		sql.append("select p.name							");
		sql.append("     , p.phonenumber					");
		sql.append("     , p.address						");
		sql.append("     , g.group_name						");
		sql.append("  from phone_info p						"); 
		sql.append("     , group_info g						"); 
		sql.append(" where p.group_number = g.group_number 	");
		sql.append(" and p.name = ? 	");
		
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberVO member = new MemberVO();
				member.setName(rs.getString("name"));
				member.setPhoneNumber(rs.getString("phonenumber"));
				member.setAddress(rs.getString("address"));
				member.setGroup(rs.getString("group_name"));
				memberList.add(member);
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			AccessManager.close(con,pstmt,rs);
		}
		return memberList;
		
	}
	
	
//	3. 회원 추가
	
	public static int insertMember(MemberVO member) {
		Connection con = AccessManager.getConnection();
		PreparedStatement pstmt = null;
		int rowcnt = 0;
		StringBuilder sql= new StringBuilder();
		
		sql.append("insert into phone_info(name, phonenumber"
				+ ", address, group_number)	");
		sql.append("			values(?,?,?,?)				");
		
		try {
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getPhoneNumber());
			pstmt.setString(3, member.getAddress());
			pstmt.setInt(4, member.getGroupNumber());
			
			rowcnt=pstmt.executeUpdate();
			
			if(rowcnt>0) {
				System.out.println("회원추가 정상");
			}else {
				System.out.println("회원추가 에러");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			AccessManager.close(con,pstmt);
		}
		
		return rowcnt;
	}
//  4. 회원 수정

//	5. 회원 삭제
	public static int deleteMember(MemberVO member) {
		
		Connection con			= AccessManager.getConnection();
		PreparedStatement pstmt = null;
		int rowcnt				= 0;
		StringBuilder sql 		= new StringBuilder();
		
		sql.append("delete from phone_info	");
		sql.append(" where name = ? 		");
		sql.append("   and phonenumber =?	");
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getPhoneNumber());
			rowcnt = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			AccessManager.close(con,pstmt);
		}
		return rowcnt;
	}
}
