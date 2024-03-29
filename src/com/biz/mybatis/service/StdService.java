package com.biz.mybatis.service;

import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import com.biz.mybatis.config.DBConnection;
import com.biz.mybatis.dao.StudentDao;
import com.biz.mybatis.model.StudentVO;

/*
 * Service 클래스에서 수행하던 DB관련 코드를
 * Dao 클래스로 분리했다.
 * 
 * 이제 Service 클래스는 Business Logic을 수행하는 코드
 * 
 */
public class StdService {
	
	private String st_num = null;
	Scanner scan = null;
	
	SqlSession sqlSession = null;
	StudentDao stdDao = null;
	
	public StdService() {
		scan = new Scanner(System.in);
		sqlSession = DBConnection.getSqlSessionFactory().openSession(true);
		stdDao = (StudentDao) sqlSession.getMapper(StudentDao.class);
	}
	
	/*
	 * 키보드에서 학번을 입력받고 해당하는 학생정보가 있는지 검사하는
	 */
	
	public boolean isNotStd() {
		System.out.println("학번 >>");
		st_num = scan.nextLine();
		// 학번에 해당하는 학생이 있으면 vo는 값을 가질 것이고
		// 없으면 null 값을 가질 것이다.
		StudentVO vo = stdDao.findByNum(st_num);
		if(vo != null) {
			System.out.println(vo);
			System.out.println("-------------------------");
			return false;
		} else {
			System.out.println("학생정보가 없습니다. 새로 입력하세요");
			return true;
		} 
		
	}
	
	public void insertStd() {
		//insertStd가 실행되는 경우는
		//학번을 입력했는데 학생정보를 못찾는 경우이다.
		//학번을 입력하엿을 때 class scope의 st_num에 저장하였으므로
		// insertStd 메서드에서도 st_num를 그대로 사용할 수 있다.
		
		System.out.println("이름 >> ");
		String st_name = scan.nextLine();
		
		System.out.println("전화번호 >> ");
		
		String st_tel = scan.nextLine();
		
		System.out.println("학년 >> ");
		String st_grade = scan.nextLine();
		int int_grade = 1;
		try {int_grade = Integer.valueOf(st_grade);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		StudentVO vo = new StudentVO(st_num, st_name, st_tel, int_grade);
		stdDao.insert(vo);
	}
	
	public String getSt_num() {
		return this.st_num;
		
	}
	
	
}
