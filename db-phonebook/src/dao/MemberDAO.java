package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.MemberVO;

/**
 * @작성자 :  	편도훈
 * @작성일 : 		2021. 1. 21.
 * @filename : 	MemberDAO.java
 * @package : 	dao
 * @description :Oracle을 직접적으로 접속하여 데이터를 다루는 클래스
 */
public class MemberDAO {
//	1. 전체 회원 목록
	public ArrayList<MemberVO> selectAll(){
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
//	2.1 특정 회원 목록(이름으로 선택하기)
	public ArrayList<MemberVO> selectByName(String name) {
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
		sql.append(" and p.name like ? 						");
		
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, name+"%");
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
//	2.1 특정 회원 목록(번호로 선택하기)
	public ArrayList<MemberVO> selectByPhoneNumber(String phoneNumber) {
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
		sql.append(" and p.phonenumber = ? 					");
		
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, phoneNumber);
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
	
	public int insertMember(MemberVO member) {
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
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			AccessManager.close(con,pstmt);
		}
		
		return rowcnt;
	}
//  4. 회원 수정

//	5. 회원 삭제
	public int deleteMember(MemberVO member) {
		
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
