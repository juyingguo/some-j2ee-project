package test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import cn.itcast.domain.Person;

/**
 * @Description:
 * @Author:		传智播客 java学院	陈子枢
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014年11月15日
 */
public class TestPersonInfo {
	private SqlSessionFactory factory;
	
	@Before	//最先执行，初始化SessionFactory
	public void init() throws IOException{
		String resource = "sqlMapConfig.xml";
		InputStream is = Resources.getResourceAsStream(resource);
		factory = new SqlSessionFactoryBuilder().build(is);
	}
	
	@Test	//查询管理的一对一
	public void testPersonInfo(){
		SqlSession session = factory.openSession();
		Map map = new HashMap();
		List<Person> personList = session.selectList("cn.itcast.mapper.PersonInfoMapper.findPersonInfo", map);
		for(Person p : personList){
			System.out.println(p);
		}
	}
	
	@Test	//查询某个人员（人员和人员信息的一对一，人员和书籍的一对多
	public void testPersonBook(){
		SqlSession session = factory.openSession();
		Map map = new HashMap();
		map.put("name", "jenny");
		
		List<Person> personList = session.selectList("cn.itcast.mapper.PersonInfoMapper.findPersonInfoBook", map);
		System.out.println(personList.size());
	}
	
}
