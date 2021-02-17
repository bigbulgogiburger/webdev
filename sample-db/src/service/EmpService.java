package service;

import java.util.ArrayList;

import dao.EmpDAO;
import vo.EmpVO;

public class EmpService {

	
//	1. 전사원 목록보기
	
	public ArrayList<EmpVO> selectAll(){
		ArrayList<EmpVO> empList = new ArrayList<EmpVO>();
		EmpDAO empDao = new EmpDAO();
		empList = empDao.selectAll();
		return empList;
	}
	
//	2. 사원 수정
	public int updateEmp(EmpVO emp) {
		int rowcnt=0;
		EmpDAO empDao = new EmpDAO();
//		사원 검색
		empDao.selectByName(emp.getEmpnm());
		
//		사원 수정
		empDao.updateEmp(emp);
		
		return rowcnt;
	}
}
