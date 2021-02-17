package view;

import java.util.ArrayList;

import vo.EmpVO;

public class EmpView {
// 전 사원 목록 화면에 출력
	public void printAll(ArrayList<EmpVO> empList) {
		for(int i=0 ; i<empList.size();i++) {
			System.out.println(empList.get(i));
			
			
		}
	}
	
}
