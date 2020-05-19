package com.yi.ex01;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class DataSourceTest {
    @Autowired //스프링 컨테이너 안에 데이타소스 타입을 가진 singleton을 자동주입시킨다. 
	private DataSource ds;
    //돌면서 싱글톤으로 만들어졌을 것 
    //이제 만들어 진것을 가지고 온다
    
    
    @Test
    public void testDs() {
    	System.out.println(ds);
    }
    @Test
    public void testConnection() {
    	try(Connection con = ds.getConnection()){
    		System.out.println(con);
    	}catch (Exception e) {
			e.printStackTrace();
		}
    }
    
}
