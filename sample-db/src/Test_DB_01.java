import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.EmpVO;

public class Test_DB_01 {

//	1. return Connection 분리
	public static Connection getConnection() {
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";//서버까지 접근 =>SID까지
		String user="ora_user"; // user
		String password="hong"; // password
		String driver = "oracle.jdbc.driver.OracleDriver";// driver명
		Connection con=null;
		try {
			con = DriverManager.getConnection(url,user,password);
		}catch(SQLException e) {
			System.out.println("connection 에러");
		}
		return con;
	};
	
//	2. close() 분리 :오버로딩 => 동일이름의 메소드를 선언.(파라미터 상이)
	
	public static void close(Connection con,PreparedStatement pstmt, ResultSet rs) {
		try {
			if(rs !=null) {
				rs.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
			if(con!=null) {
				con.close();
			}
		}catch(SQLException e) {
			System.out.println("close error");
		}
	}
	
	public static void close(Connection con,PreparedStatement pstmt) {
		try {
			
			if(pstmt != null) {
				pstmt.close();
			}
			if(con!=null) {
				con.close();
			}
		}catch(SQLException e) {
			System.out.println("close error");
		}
	}
//	2.1 select Connection, PreparedStatement, ResultSet
	
//	2.2 insert/update/delete =>
	
//	3. 출력분리
	public static void printEmp(ArrayList<EmpVO> empList) {
	
			for(EmpVO x : empList) {
				System.out.println(x);
			
			}
	}

	
	
//	4. 전사원 조회
	public static void selectAll() {
		Connection con = getConnection();
		ResultSet rs= null;
		PreparedStatement pstmt=null;
		ArrayList<EmpVO> empList = new ArrayList<EmpVO>();
		StringBuilder sql = new StringBuilder();
		sql.append("select e.empno				");
		sql.append(" 	 , e.empnm				");
		sql.append(" 	 , e.salary				");
		sql.append(" 	 , e.deptno				");
		sql.append(" 	 , d.deptnm				");
		sql.append("  from emp e				");
	    sql.append("     , dept d				");
	    sql.append(" where d.deptno=e.deptno	");
	    
	    try {
	    	pstmt = con.prepareStatement(sql.toString());
	    	rs=pstmt.executeQuery();
	    	while(rs.next()) {
	    		EmpVO emp = new EmpVO(); // 한명의 사원정보를 저장.
				emp.setEmpno(rs.getString("empno"));
				emp.setEmpnm(rs.getString("empnm"));
				emp.setSalary(rs.getInt("salary"));
				emp.setDeptno(rs.getString("deptno"));
				emp.setDeptnm(rs.getString("deptnm"));
				empList.add(emp);
	    	}
	    	printEmp(empList);
	    }catch(SQLException e) {
	    	e.printStackTrace();
	    }finally {
	    	close(con,pstmt,rs);
	    }
	}
//	5. 특정 사원명으로 조회
	public static void selectByName(String empnm) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuilder sql= new StringBuilder();
		sql.append(" select e.empno				");
		sql.append("  	  , e.empnm				");
		sql.append("  	  , e.salary			");
		sql.append("  	  , e.deptno			");
		sql.append("      , d.deptnm			");
		sql.append("   from emp e				");
		sql.append("	  , dept d				");
		sql.append("  where e.empnm = ?			");
		sql.append("    and e.deptno = d.deptno	");
		ArrayList<EmpVO> empList= new ArrayList<EmpVO>();
		try {
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, empnm);
			rs = pstmt.executeQuery();
//			resultset을 다른 메소드에 넣고 빼서 닫으면 매우 위험. 어레이리스트에 넣다 뺐다 해야한다.
//			class DTO(data transfer object) or VO(value object) 를 가지고 있어야 한다.
//			ArrayList<EmpVO> 완성
			while(rs.next()) {
				EmpVO emp = new EmpVO(); // 한명의 사원정보를 저장.
				emp.setEmpno(rs.getString("empno"));
				emp.setEmpnm(rs.getString("empnm"));
				emp.setSalary(rs.getInt("salary"));
				emp.setDeptno(rs.getString("deptno"));
				emp.setDeptnm(rs.getString("deptnm"));
				empList.add(emp);
			}
			
			printEmp(empList);
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(con,pstmt,rs);
		}
		
	}
//	6. 사원추가
	public static void insertEmp(EmpVO emp) {
		Connection con = getConnection();
		PreparedStatement pstmt =null;
		StringBuilder sql = new StringBuilder();
		sql.append("insert into emp(empno"
				+ ", empnm, deptno, salary)	");
		sql.append("		values(?,?,?,?)	");
		int rowcnt=0;
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, emp.getEmpno());
			pstmt.setString(2, emp.getEmpnm());
			pstmt.setString(3, emp.getDeptno());
			pstmt.setInt(4, emp.getSalary());
			rowcnt = pstmt.executeUpdate();
			if(rowcnt>0 ) {
				System.out.println("사원 추가 정상처리");
			}else {
				System.out.println("사원 추가 비정상처리");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(con,pstmt);
		}
	}
	
	
	public static void main(String[] args) {
		
		selectAll();
		System.out.println("===============================");
		selectByName("고길동");
		System.out.println("===============================");
		EmpVO emp = new EmpVO();
		emp.setEmpno("8000"); //키보드 입력
		emp.setEmpnm("홍길순");
		emp.setDeptno("004");
		emp.setSalary(1000);
		insertEmp(emp);
		System.out.println("===============================");
		selectByName(emp.getEmpnm());
		System.out.println("===============================");
		selectAll();
	}

}
