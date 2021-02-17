package vo;

public class EmpVO {
	private String empno;
	private String empnm;
	private int salary;
	private String deptno;
	private String deptnm;
	public String getEmpno() {
		return empno;
	}
	public void setEmpno(String empno) {
		this.empno = empno;
	}
	public String getEmpnm() {
		return empnm;
	}
	public void setEmpnm(String empnm) {
		this.empnm = empnm;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getDeptno() {
		return deptno;
	}
	public void setDeptno(String deptno) {
		this.deptno = deptno;
	}
	public String getDeptnm() {
		return deptnm;
	}
	public void setDeptnm(String deptnm) {
		this.deptnm = deptnm;
	}
	@Override
	public String toString() {
		return "[사원번호=" + empno 
				+ ", 사원명=" + empnm 
				+ ", 급여=" + salary 
				+ ", 부서번호=" + deptno 
				+ ", 부서명="+ deptnm + "]";
	}
	
	

}
