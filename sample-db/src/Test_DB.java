import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// DataBase 연동
// select => 화면 출력
public class Test_DB {
//	전체 사원 목록 조회 메소드 - 반복사용
	public static void selectAll() {
//		connection 정보
//		ip, port, SID, USER(id),password
//		드라이버를 지정
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";//서버까지 접근 =>SID까지
		String user="ora_user"; // user
		String password="hong"; // password
		String driver = "oracle.jdbc.driver.OracleDriver";// driver명
		
//		변수 선언
		Connection con 			=null;//db연결을 처리하는 클래스
		PreparedStatement pstmt	=null;
		ResultSet rs 			=null;
		
		try {
			
	//		1. 드라이버를 로딩
			Class.forName(driver);//명령을 통해서 클래스 영역에 드라이버 로딩
	//		2. Connection
			con = DriverManager.getConnection(url, user, password);//getConnection은 스태틱한 메소드(클래스 메소드)
	//		4. SQL 생성 => SELECT
			StringBuilder sql = new StringBuilder();
			sql.append("select e.empno				");
			sql.append(" 	 , e.empnm				");
			sql.append(" 	 , e.salary				");
			sql.append(" 	 , e.deptno				");
			sql.append(" 	 , d.deptnm				");
			sql.append("  from emp e				");
		    sql.append("     , dept d				" );
		    sql.append(" where d.deptno=e.deptno	");

//			4-1 , preparedstatement(트럭) sql을 먼저 생성해야한다.
		    pstmt = con.prepareStatement(sql.toString());
	//		5. 트럭을 서버로 보낸다 (실행)=>결과를 반환한다.=>받아야 한다.=>ResultSet rs
	//		6. 서버 실행 결과가 트럭을 통해 오면=>받는다.(ResultSet)
		    rs=pstmt.executeQuery();
//		    start point
//		    1000	홍길동	001	피카부(.next)
//		    2000	고길동	002	현랑부(.next)
//		    3000	마이콜	003	인사부(.next)
//		    4000	둘리		004	관리부(.next)
//		    end point
//		7. ResultSet => 꺼내서 처리=> 화면에 출력
			while(rs.next()) {
				System.out.print("사원번호 : " + rs.getString("empno"));
				System.out.print(" | 사원명 : " + rs.getString("empnm"));
				System.out.print(" | 급여 : " + rs.getString("salary"));
				System.out.print(" | 부서번호 : " + rs.getString("deptno"));
				System.out.print(" | 부서명 : " + rs.getString("deptnm"));
				System.out.println();// 다음줄 처리.
			}
		}catch(Exception e) {
//			에러처리
		}finally {
			
//		8. close()
//			만든 역순으로 클로즈
			try {
				rs.close();
				pstmt.close();
				con.close();
				
			}catch(Exception e) {
				System.out.println("close error");
			}
		}//finally end, try end
	}
//	1.connection
//	2.sql생성
//  3.트럭(PreparedStatement)
//	4.트럭 실행=>resultset(조회 결과)
//	5.resultset(조회결과)=>화면출력
//	사원추가 =>DB, table(emp) insert 메소드
	
//	특정 사원 목록 이름 검색 메소드 - 반복사용
	public static void insertEmp(String empno,String empnm
			, String deptno, int salary) {
		
//		EMPNO EMPNM DEPTNO JID SALARY BONUS
//		데이터베이스 INSERT
//		전체에서 사용할 변수 선언
		Connection conn			= null;//
		PreparedStatement pstmt = null;// 데이터베이스에 명령 전송(truck)
		String driver 			= "oracle.jdbc.driver.OracleDriver";
		StringBuilder sql 		= new StringBuilder(); // 명령을 저장하는 문자열
		String url				= "jdbc:oracle:thin:@localhost:1521:xe";
		String user				= "ora_user";
		String password			= "hong";
	
		try {
//		1. connection
			conn = DriverManager.getConnection(url, user, password);
//		2. sql문 생성		
			sql.append("insert into emp(empno, empnm, deptno, salary)	");
			sql.append("			values(?,?,?,?)						");
//		3. 트럭(PreparedStatement)생성
			pstmt = conn.prepareStatement(sql.toString());
//		4. 값을 세팅
//			4.1 empno(String) setting
			pstmt.setString(1, empno);
//			4.1 empnm(String) setting
			pstmt.setString(2, empnm);
//			4.1 deptno(String) setting
			pstmt.setString(3, deptno);
//			4.1 salary(String) setting
			pstmt.setInt(4, salary);
//		5. 트럭을 실행 =>insert =>True/False
			int rowcnt = pstmt.executeUpdate(); // 반환된 row의 개수 반환(return)
			if(rowcnt>0) {
				System.out.println("사원추가 정상");
			}else {
				System.out.println("사원추가 에러");
			}
//			insert, update, delete=>executeUpdate

			
		}catch(SQLException e) {
			e.printStackTrace(); //어디서 어떤 에러가 발생했는지를 추적해서 출력
		}finally {
//			6. close()
			try {
				pstmt.close();
				conn.close();
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		

	}
	public static void selectByName(String empnm) {
		Connection con 			= null;
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;

		String url = "jdbc:oracle:thin:@localhost:1521:xe";//서버까지 접근 =>SID까지
		String user="ora_user"; // user
		String password="hong"; // password
		String driver = "oracle.jdbc.driver.OracleDriver";// driver명
//		이름으로 검색할 select
		StringBuffer sql = new StringBuffer();
		sql.append(" select e.empno				");
		sql.append("  	  , e.empnm				");
		sql.append("      , d.deptnm			");
		sql.append("   from emp e				");
		sql.append("	  , dept d				");
		sql.append("  where e.empnm = ?			");
		sql.append("    and e.deptno = d.deptno	");
		try {
			con= DriverManager.getConnection(url,user,password);
			pstmt= con.prepareStatement(sql.toString());
			pstmt.setString(1,empnm);
			pstmt.executeQuery();
			rs=pstmt.executeQuery();
			while(rs.next()) {
				System.out.print("사원번호 : " + rs.getString("empno"));
				System.out.print(" | 사원명 : " + rs.getString("empnm"));
				System.out.print(" | 부서명 : " + rs.getString("deptnm"));
				System.out.println();// 다음줄 처리.
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
//	메소드 처리 2가지 이유 - 반복사용, 복잡한 구조를 하나로 묶을때
	public static void main(String[] args) {

		selectAll();
//		insertEmp("6000","도우너","003",1000);
		System.out.println("=================검색 결과=================");
		selectByName("홍길동");
		System.out.println("========================================");
		selectAll();

		}//main end
	}//class end

