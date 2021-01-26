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
 * @description :Oracle을 직접적으로 접속(connection,close)하여 데이터를 다루는 클래스 
 */
public class MemberDAO {
	AccessManager accessManager = new AccessManager();
//	1. 전체 회원 목록
//	이 메소드는 전체 회원의 목록을 어레이리스트 형태로 리턴한다.
	public ArrayList<MemberVO> selectAll(){
		ArrayList<MemberVO> memberList	= new ArrayList<MemberVO>();
		
		Connection con 				= accessManager.getConnection();
		PreparedStatement pstmt 	= null;
		ResultSet rs 				= null;
		
		StringBuilder sql			= new StringBuilder();
		
		sql.append(" select p.name								");
		sql.append("      , p.phonenumber						");
		sql.append("      , p.address							");
		sql.append("      , g.group_name						");
		sql.append("   from phone_info p						"); 
		sql.append("      , group_info g						"); 
		sql.append("  where p.group_number = g.group_number 	");
		
		
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
			accessManager.close(con,pstmt,rs);
		}
		return memberList;
	}
//	2.1 특정 회원 목록(이름으로 선택하기)
//	이 메소드는 이름을 입력받아 sql문을 처리한 후 값을 어레이리스트로 보낸다.
	public ArrayList<MemberVO> selectByName(String name) {
		ArrayList<MemberVO> memberList	= new ArrayList<MemberVO>();
		
		Connection con 				= accessManager.getConnection();
		PreparedStatement pstmt 	= null;
		ResultSet rs 				= null;
		
		StringBuilder sql			= new StringBuilder();
		
		sql.append("select p.name							");
		sql.append("     , p.phonenumber					");
		sql.append("     , p.address						");
		sql.append("     , g.group_name						");
		sql.append("     , member_num						");
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
				member.setMemberNum(rs.getInt("member_num"));
				memberList.add(member);
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			accessManager.close(con,pstmt,rs);
		}
		return memberList;
		
	}
//	2.2 특정 회원 목록(번호로 선택하기)
//	이 메소드는 핸드폰 번호(String)을 입력받아 회원번호(int)를 리턴한다.
	public int selectByPhoneNumber(String phoneNumber) {
		Connection con 				= accessManager.getConnection();
		PreparedStatement pstmt 	= null;
		ResultSet rs 				= null;
		int member_num = -1;
		
		StringBuilder sql			= new StringBuilder();
		
		sql.append("	select p.member_num						");
		sql.append("      from phone_info p						"); 
		sql.append("     where p.phonenumber = ? 				");
		
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, phoneNumber);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				member_num = rs.getInt("member_num");
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			accessManager.close(con,pstmt,rs);
		}
		return member_num;
		
	}
	
//	3. 회원 추가
//	이 메소드는 VO를 파라미터로 입력받아 실행결과인 rowcnt를 리턴한다.
	public int insertMember(MemberVO member) {
		Connection con = accessManager.getConnection();
		PreparedStatement pstmt = null;
		int rowcnt = 0;
		StringBuilder sql= new StringBuilder();
		
		sql.append("	insert into phone_info(name, phonenumber	");
		sql.append("	    , address, group_number,member_num)		");
		sql.append("		values(?,?,?,?,member_num.nextval)		");
		
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
			accessManager.close(con,pstmt);
		}
		
		return rowcnt;
	}
	
//	4. 회원 수정 메소드
	public int updateMember(MemberVO member, int member_num) {
		Connection con			= accessManager.getConnection();
		PreparedStatement pstmt = null;
		int rowcnt				= 0;
		StringBuilder sql 		= new StringBuilder();
		
		sql.append("	          update phone_info	");
		sql.append("   	             set 			");
		sql.append("                name = ?		");
		sql.append(",        phonenumber = ?		");
		sql.append(",            address = ?		");
		sql.append(", 		group_number = ? 		");
		sql.append("    where member_num = ? 		");
		try {
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getPhoneNumber());
			pstmt.setString(3, member.getAddress());
			pstmt.setInt(4, member.getGroupNumber());
			pstmt.setInt(5, member_num);
			rowcnt=pstmt.executeUpdate();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			accessManager.close(con,pstmt);
		}
		
		return rowcnt;
	}
	


//	5. 회원 삭제 메소드
	public int deleteMember(int member_num) {
		
		Connection con			= accessManager.getConnection();
		PreparedStatement pstmt = null;
		int rowcnt				= 0;
		StringBuilder sql 		= new StringBuilder();
		
		sql.append("delete from phone_info		");
		sql.append(" where member_num = ? 		");
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, member_num);
			rowcnt = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			accessManager.close(con,pstmt);
		}
		return rowcnt;
	}
}
