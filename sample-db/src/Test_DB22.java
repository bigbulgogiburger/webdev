import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// DataBase 연동
// select => 화면 출력
public class Test_DB22 {

	public static void main(String[] args) {
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
	//		3. 트럭 생성(Statements,PreparedStatement)
	//		4. SQL 생성 => SELECT
			StringBuilder sql = new StringBuilder();
			sql.append(" create table testinsert(		");
			sql.append(" empno varchar2(10)					");
			sql.append(" ,empnm varchar2(10)				");
			sql.append(" ,deptno varchar2(20)				");
			sql.append(" ,deptnm varchar2(20)				");
			sql.append(")								");
//			4-1 , preparedstatement(트럭) sql을 먼저 생성해야한다.
		    pstmt = con.prepareStatement(sql.toString());
	//		5. 트럭을 서버로 보낸다 (실행)=>결과를 반환한다.=>받아야 한다.=>ResultSet rs
	//		6. 서버 실행 결과가 트럭을 통해 오면=>받는다.(ResultSet)
		    rs=pstmt.executeQuery();
		    sql=new StringBuilder();
		    
		    sql.append(" insert into testinsert				");
			sql.append(" values('1000','홍길동','001','인사부')	");
		    pstmt =con.prepareStatement(sql.toString());
		    pstmt.executeQuery();
		    
		    sql = new StringBuilder();
		    
			sql.append("select e.empno				");
			sql.append(" 	 , e.empnm				");
			sql.append(" 	 , e.deptno				");
			sql.append(" 	 , e.deptnm				");
			sql.append("  from emp e				");
//		    
			pstmt =con.prepareStatement(sql.toString());
		    rs=pstmt.executeQuery();
//			start point
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
		}
	}

}
