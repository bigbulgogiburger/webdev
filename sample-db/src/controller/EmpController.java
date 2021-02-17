package controller;

import java.util.ArrayList;

import service.EmpService;
import view.EmpView;
import vo.EmpVO;

public class EmpController {
	public static void selectAll() {
		EmpService empService = new EmpService();
		ArrayList<EmpVO> empList = empService.selectAll();
		
		EmpView empView = new EmpView();
		empView.printAll(empList);
	}
	public static void main(String[] args) {
		//전사원 목록
		selectAll();
		
		
	}

}
