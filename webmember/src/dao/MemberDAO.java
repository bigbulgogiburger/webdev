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
	AccessManager accessManager = AccessManager.getInstance();
	
	public ArrayList<MemberVO> selectAll(String id){
		ArrayList<MemberVO> memberList	= new ArrayList<MemberVO>();
		
		Connection con 	= accessManager.getConnection();
		PreparedStatement pstmt 	= null;
		ResultSet rs 		= null;
		
		StringBuilder sql		= new StringBuilder();
		
		
		System.out.println(id);
		sql.append(" 	  select c.name, c.phonenumber,c.address,g.group_name, c.membernum	");
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
				String phoneNumber = rs.getString("phonenumber");
				member.setPhone1(phoneNumber.substring(0, 3));
				member.setPhone2(phoneNumber.substring(3, 7));
				member.setPhone3(phoneNumber.substring(7));
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

		query.append("	insert into contact			 		 ");
		query.append("	values(numseq.nextval,?,?,?,?,?,?,?) ");
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = accessManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getPhone1()+member.getPhone2()+member.getPhone3());
			pstmt.setString(3, member.getAddress());
			pstmt.setInt(4, member.getGroupnum());
			pstmt.setString(5, member.getId());
			pstmt.setString(6, member.getDetail_address());
			pstmt.setString(7, member.getPostcode());
			rowcnt= pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			accessManager.close(con, pstmt);
		}
		
		return rowcnt;
	}
	
	public int insertJoin(JoinVO join) {
		
		Connection con 			= null;
		PreparedStatement pstmt = null;
		StringBuilder query = new StringBuilder();
		int rowcnt=0;
		query.append("	insert into loginfo	");
		query.append("	values(?,?,?)			");
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = accessManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, join.getId());
			pstmt.setString(2, join.getPw());
			pstmt.setString(3, join.getName());
			rowcnt = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			accessManager.close(con, pstmt);
		}
		
		return rowcnt;
	}

	public JoinVO searchJoin(String id) {
		Connection con 			= null;
		PreparedStatement pstmt = null;
		StringBuilder query		= new StringBuilder();
		ResultSet rs = null;
		
		String validpw = null;
		String validid = null;
		String validname = null;
		
		query.append("	select id,password,username from loginfo	");
		query.append("	where id=?				");
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = accessManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				validid = rs.getString("id");
				validpw = rs.getString("password");
				validname = rs.getString("username");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			accessManager.close(con, pstmt, rs);
		}
		
		return new JoinVO(validid, validpw, validname);
	}

	public MemberVO selectById(String id) {
		MemberVO member = new MemberVO();
		Connection con 			= null;
		PreparedStatement pstmt = null;
		StringBuilder query		= new StringBuilder();
		ResultSet rs = null;

		
		query.append(" select name, phonenumber,address,id,detail_address,postcode	");
		query.append(" from contact where id=? and groupnum= 4  	");
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = accessManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				member.setDetail_address(rs.getString("detail_address"));
				member.setPostcode(rs.getString("postcode"));
				member.setName(rs.getString("name"));
				String phoneNumber = rs.getString("phonenumber");
				member.setPhone1(phoneNumber.substring(0, 3));
				member.setPhone2(phoneNumber.substring(3, 7));
				member.setPhone3(phoneNumber.substring(7));
				member.setAddress(rs.getString("address"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			accessManager.close(con, pstmt, rs);
		}
		return member;
	}

	public int selectByIdPw(String id, String pw) {
		Connection con 			= null;
		PreparedStatement pstmt = null;
		StringBuilder query		= new StringBuilder();
		int memberNum = 0;
		ResultSet rs = null;
		

		
		query.append(" select c.membernum 						");
		query.append("   from contact c inner join loginfo l	");
		query.append("   on c.id=l.id							"); 
		query.append("   where c.groupnum=4						");
		query.append("   and l.id=?								");
		query.append("   and l.password=?						");
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con =accessManager.getConnection();
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
			accessManager.close(con, pstmt, rs);
		
		}
		return memberNum;
	
	}
	
	/**
	 * @param memberNum
	 * @return
	 */
	public MemberVO selectByMemberNum(int memberNum) {
		MemberVO member = new MemberVO();
		Connection con 			= null;
		PreparedStatement pstmt = null;
		StringBuilder query		= new StringBuilder();
		ResultSet rs = null;

		query.append(" 	  select c.name, c.phonenumber,c.address,g.group_name, c.membernum, c.detail_address, c.postcode			");
		query.append("      from contact c, group_info g											");
		query.append("      where c.groupnum = g.group_number										");
		query.append("      and membernum=?															");
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = accessManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setInt(1, memberNum);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				member.setName(rs.getString("name"));
				String phoneNumber = rs.getString("phonenumber");
				member.setDetail_address(rs.getString("detail_address"));
				member.setPostcode(rs.getString("postcode"));
				member.setPhone1(phoneNumber.substring(0, 3));
				member.setPhone2(phoneNumber.substring(3, 7));
				member.setPhone3(phoneNumber.substring(7));
				member.setAddress(rs.getString("address"));
				member.setGroupName(rs.getString("group_name"));
				member.setMemberNum(Integer.parseInt(rs.getString("membernum")));
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			accessManager.close(con,pstmt,rs);
		}
		
		return member;
	}

	public void updateMember(MemberVO member) {
		Connection con 			= null;
		PreparedStatement pstmt = null;
		StringBuilder query = new StringBuilder();
		String url 		="jdbc:oracle:thin:@localhost:1521:xe";
		String user 	= "ora_user";
		String password = "hong";
		
		query.append(" update contact 					");
		query.append("    set name = ?					");
		query.append("   	 ,phonenumber = ?			");
		query.append("       ,address = ?				");
		query.append("       ,groupnum = ?				");
		query.append("       ,detail_address = ?		");
		query.append("       ,postcode = ?				");
		query.append("  where membernum = ?  			");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url,user,password);
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getPhone1()+member.getPhone2()+member.getPhone3());
			pstmt.setString(3, member.getAddress());
			pstmt.setInt(4, member.getGroupnum());
			pstmt.setString(5, member.getDetail_address());
			pstmt.setString(6, member.getPostcode());
			pstmt.setInt(7, member.getMemberNum());
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
		
		System.out.println("회원번호 :"+ memberNum);
		query.append(" delete contact 			");
		query.append("  where membernum = ?  	");
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = accessManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setInt(1, memberNum);
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			accessManager.close(con, pstmt);
		}
	}

	public void selectNameById(String id) {
		MemberVO member = new MemberVO();
		Connection con 			= null;
		PreparedStatement pstmt = null;
		StringBuilder query		= new StringBuilder();
		ResultSet rs = null;

		query.append(" 	  select username from loginfo where id=?			");
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = accessManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				member.setName(rs.getString("username"));
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			accessManager.close(con,pstmt,rs);
		}
	}

	public void updateJoin(JoinVO join) {
		Connection con 			= null;
		PreparedStatement pstmt = null;
		StringBuilder query = new StringBuilder();

		
		query.append(" update loginfo 					");
		query.append("    set username = ?					");
		query.append("  where id = ? and password=?  	");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = accessManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			System.out.println(join.getName()+" : "+join.getPw()+":"+join.getId());
			pstmt.setString(1, join.getName());
			pstmt.setString(2, join.getId());
			pstmt.setString(3, join.getPw());
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			accessManager.close(con, pstmt);
		}
	}

	public ArrayList<MemberVO> selectByNameId(String category, String value, String id) {
		ArrayList<MemberVO> memberList	= new ArrayList<MemberVO>();
		
		Connection con 	= accessManager.getConnection();
		PreparedStatement pstmt 	= null;
		ResultSet rs 		= null;
		
		StringBuilder sql		= new StringBuilder();
		
		
		System.out.println(id);
		sql.append(" 	  select c.name, c.phonenumber,c.address,g.group_name, c.membernum	");
		sql.append("      from contact c, group_info g										");
		sql.append("      where c.groupnum = g.group_number									");
		sql.append("      and id=? and 														");
		sql.append(category);
		sql.append(" 	  like ?															");
		sql.append(" 	  and groupnum<4													");
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			pstmt.setString(2, "%"+value+"%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberVO member = new MemberVO();
				member.setName(rs.getString("name"));
				String phoneNumber = rs.getString("phonenumber");
				member.setPhone1(phoneNumber.substring(0, 3));
				member.setPhone2(phoneNumber.substring(3, 7));
				member.setPhone3(phoneNumber.substring(7));
				member.setAddress(rs.getString("address"));
				member.setGroupName(rs.getString("group_name"));
				member.setMemberNum(Integer.parseInt(rs.getString("membernum")));
				memberList.add(member);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			accessManager.close(con,pstmt,rs);
		}
		return memberList;
	}

	public boolean idChecker(String id) {
		Connection con 	= null;
		PreparedStatement pstmt 	= null;
		ResultSet rs 		= null;
		boolean check = true;
		
		StringBuilder sql		= new StringBuilder();
		
		
		System.out.println(id);
		sql.append(" 	  select id from loginfo	");
		sql.append(" 	  where id =?				");
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = accessManager.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				check=true;
			}else {
				check=false;
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			accessManager.close(con,pstmt,rs);
		}
		return check;
	}

	public int selectByPhoneNumber(String phoneNumber) {
		Connection con 	= null;
		PreparedStatement pstmt 	= null;
		ResultSet rs 		= null;
		int membernum =-1;
		
		StringBuilder sql		= new StringBuilder();
		
		
		sql.append(" 	  select membernum from contact		");
		sql.append(" 	  where phonenumber =?				");
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = accessManager.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, phoneNumber);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				membernum = rs.getInt("membernum");
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			accessManager.close(con,pstmt,rs);
		}
		return membernum;

	}

	public int findGroupNum(String id) {
		Connection con 	= null;
		PreparedStatement pstmt 	= null;
		ResultSet rs 		= null;
		int groupNum =0;
		
		StringBuilder sql		= new StringBuilder();
		
		
		sql.append(" 	  select groupnum from contact		");
		sql.append(" 	  where id =?						");
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = accessManager.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				groupNum = rs.getInt("groupnum");
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			accessManager.close(con,pstmt,rs);
		}
		return groupNum;
	}

	public ArrayList<MemberVO> selectAll() {
ArrayList<MemberVO> memberList	= new ArrayList<MemberVO>();
		
		Connection con 	= accessManager.getConnection();
		PreparedStatement pstmt 	= null;
		ResultSet rs 		= null;
		
		StringBuilder sql		= new StringBuilder();
		
		
		sql.append(" 	  select c.name, c.phonenumber,c.address,g.group_name, c.membernum  ");
		sql.append("      from contact c, group_info g										");
		sql.append("      where c.groupnum = g.group_number									");
		sql.append(" 	  and groupnum<5													");
		
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberVO member = new MemberVO();
				member.setName(rs.getString("name"));
				String phoneNumber = rs.getString("phonenumber");
				member.setPhone1(phoneNumber.substring(0, 3));
				member.setPhone2(phoneNumber.substring(3, 7));
				member.setPhone3(phoneNumber.substring(7));
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
	
	
}
