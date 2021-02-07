package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.JoinVO;
import vo.MemberVO;

public class MemberDAO {
	AccessManager accessManager = new AccessManager();
	
	public ArrayList<MemberVO> selectAll(String id){
		ArrayList<MemberVO> memberList	= new ArrayList<MemberVO>();
		
		Connection con 	= accessManager.getConnection();
		PreparedStatement pstmt 	= null;
		ResultSet rs 		= null;
		
		StringBuilder sql		= new StringBuilder();
		
		
		System.out.println(id);
		sql.append(" 	  select c.name, c.phone1,c.phone2,c.phone3,c.address,g.group_name, c.membernum	");
		sql.append("      from contact c, group_info g										");
		sql.append("      where c.groupnum = g.group_number									");
		sql.append("      and id=?															");
		sql.append(" 	  and groupnum<4													");
		
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberVO member = new MemberVO();
				member.setName(rs.getString("name"));
				member.setPhone1(rs.getString("phone1"));
				member.setPhone2(rs.getString("phone2"));
				member.setPhone3(rs.getString("phone3"));
				member.setAddress(rs.getString("address"));
				member.setGroupName(rs.getString("group_name"));
				member.setMemberNum(Integer.parseInt(rs.getString("membernum")));
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
		query.append("	values(?,?,?)			");
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url,user,password);
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, join.getId());
			pstmt.setString(2, join.getPw());
			pstmt.setString(3, join.getName());
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

	public JoinVO searchJoin(String id) {
		Connection con 			= null;
		PreparedStatement pstmt = null;
		StringBuilder query		= new StringBuilder();
		ResultSet rs = null;
		
		String url 		="jdbc:oracle:thin:@localhost:1521:xe";
		String user 	= "ora_user";
		String password = "hong";
		String validpw = null;
		String validid = null;
		String validname = null;
		
		query.append("	select id,password,username from loginfo	");
		query.append("	where id=?				");
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url,user,password);
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			rs.next();
			validid = rs.getString("id");
			validpw = rs.getString("password");
			validname = rs.getString("username");
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
		
		return new JoinVO(validid, validpw, validname);
	}

	public MemberVO selectById(String id) {
		MemberVO member = new MemberVO();
		Connection con 			= null;
		PreparedStatement pstmt = null;
		StringBuilder query		= new StringBuilder();
		ResultSet rs = null;
		
		String url 		="jdbc:oracle:thin:@localhost:1521:xe";
		String user 	= "ora_user";
		String password = "hong";
		
		query.append(" select name, phone1,phone2,phone3,address,id	");
		query.append(" from contact where id=? and groupnum= 4  	");
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url,user,password);
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				member.setName(rs.getString("name"));
				member.setPhone1(rs.getString("phone1"));
				member.setPhone2(rs.getString("phone2"));
				member.setPhone3(rs.getString("phone3"));
				member.setAddress(rs.getString("address"));
			}
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
		return member;
	}

	public int selectByIdPw(String id, String pw) {
		Connection con 			= null;
		PreparedStatement pstmt = null;
		StringBuilder query		= new StringBuilder();
		int memberNum = 0;
		ResultSet rs = null;
		
		String url 		="jdbc:oracle:thin:@localhost:1521:xe";
		String user 	= "ora_user";
		String password = "hong";
		
		query.append(" select c.membernum 						");
		query.append("   from contact c inner join loginfo l	");
		query.append("   on c.id=l.id							"); 
		query.append("   where c.groupnum=4						");
		query.append("   and l.id=?								");
		query.append("   and l.password=?						");
		;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url,user,password);
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				memberNum = Integer.parseInt(rs.getString("membernum"));
				
			}
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
		return memberNum;
	
	}

	public void updateMember(MemberVO member) {
		Connection con 			= null;
		PreparedStatement pstmt = null;
		StringBuilder query = new StringBuilder();
		String url 		="jdbc:oracle:thin:@localhost:1521:xe";
		String user 	= "ora_user";
		String password = "hong";
		
		query.append(" update contact 			");
		query.append("    set name = ?			");
		query.append("   	 ,phone1 = ?			");
		query.append("       ,phone2 = ?			");
		query.append("       ,phone3 = ?			");
		query.append("       ,address = ?		");
		query.append("  where membernum = ?  	");
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url,user,password);
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getPhone1());
			pstmt.setString(3, member.getPhone2());
			pstmt.setString(4, member.getPhone3());
			pstmt.setString(5, member.getAddress());
			pstmt.setInt(6, member.getMemberNum());
			pstmt.executeUpdate();
			
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
	}

	public void deleteMember(int memberNum) {
		Connection con 			= null;
		PreparedStatement pstmt = null;
		StringBuilder query = new StringBuilder();
		String url 		="jdbc:oracle:thin:@localhost:1521:xe";
		String user 	= "ora_user";
		String password = "hong";
		System.out.println("회원번호 :"+ memberNum);
		query.append(" delete contact 			");
		query.append("  where membernum = ?  	");
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url,user,password);
			pstmt = con.prepareStatement(query.toString());
			pstmt.setInt(1, memberNum);
			pstmt.executeUpdate();
			
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
	}
	
	
}
