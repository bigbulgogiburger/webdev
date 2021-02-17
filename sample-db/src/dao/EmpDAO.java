package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.EmpVO;

public class EmpDAO {
	private Connection getConnection(){
		Connection con = null;
		String url 		= "jdbc:oracle:thin:@localhost:1521:xe";
		String user 	= "ora_user";
		String password = "hong";
		String driver 	= "oracle.jdbc.driver.OracleDriver";
		try {
			
			con= DriverManager.getConnection(url, user, password);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	private void close(Connection con,
			PreparedStatement pstmt,ResultSet rs) {
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
			e.printStackTrace();
		}
	}
	
	
	private void close(Connection con,
				PreparedStatement pstmt) {
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
	
//	1. 전사원목록
	public ArrayList<EmpVO> selectAll(){
		ArrayList<EmpVO> empList	= new ArrayList<EmpVO>();
		Connection con 				= getConnection();
		PreparedStatement pstmt 	= null;
		ResultSet rs 				= null;
		StringBuilder sql			= new StringBuilder();
		
		sql.append("select e.empno					");
		sql.append("     , e.empnm					");
		sql.append("     , e.salary					");
		sql.append("     , d.deptno					");
		sql.append("     , d.deptnm					");
		sql.append("  from emp e					"); 
		sql.append("     , dept d					"); 
		sql.append(" where e.deptno = d.deptno  	");
		
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				EmpVO emp = new EmpVO();
				emp.setEmpno(rs.getString("empno"));
				emp.setEmpnm(rs.getString("empnm"));
				emp.setSalary(rs.getInt("salary"));
				emp.setDeptnm(rs.getString("deptnm"));
				emp.setDeptno(rs.getString("deptno"));
				
				empList.add(emp);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(con,pstmt);
		}
		
		
		
		return empList;
	}
//	2. 사원추가 
	public int insertEmp(EmpVO emp) {
		int rowcnt = 0;
		
		
		
		return rowcnt;
	}
//	3. 사원수정 
	public int updateEmp(EmpVO emp) {
		int rowcnt=0;
		
		return rowcnt;
	}
//	4. 사원삭제 ->사번삭제
	public int deleteEmp(String empno) {
		int rowcnt=0;
		
		return rowcnt;
	}
//  5. 사원명검색
	public ArrayList<EmpVO> selectByName(String empnm){
		ArrayList<EmpVO> empList = new ArrayList<EmpVO>();
		
		return empList;
	}
}
