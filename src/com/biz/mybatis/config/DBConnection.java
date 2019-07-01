package com.biz.mybatis.config;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBConnection {

	private static SqlSessionFactory sqlSessionFactory=null;
	
	/*
	 * MyBatis의 기능으로
	 * DBConnection Pool을 생성하고
	 * 관리하는 관리주체
	 * 
	 * factory
	 * 어떤 클래스를 객체 그룹으로 생성해두고
	 * 필요한곳에 공급하는
	 * Manager 성격의 클래스
	 */

	static {
		String configFile 
		= "com/biz/mybatis/config/dbconfig.xml";
		
		/*
		 * Reader는 FileReader, BufferdReader의 할아버지급 Class
		 */
		try {
			Reader configResource = Resources.getResourceAsReader(configFile);
			if(sqlSessionFactory == null) {
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(configResource);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} //end static
	
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
}
