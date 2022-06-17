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
import cn.itcast.mapper.PersonMapper;

/**
 * @Description:
 * @Author:		传智播客 java学院	陈子枢
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014年11月17日
 */
public class TestCache {
	private SqlSessionFactory factory;
	
	@Before	//最先执行，初始化SessionFactory
	public void init() throws IOException{
		String resource = "sqlMapConfig.xml";
		InputStream is = Resources.getResourceAsStream(resource);
		factory = new SqlSessionFactoryBuilder().build(is);
	}
	
	@Test	//一级缓存
	public void cacheLevel1(){
		SqlSession session = factory.openSession();
		PersonMapper mapper = session.getMapper(PersonMapper.class);
		Map map = new HashMap();
		map.put("name", "'tony'");
		
		List<Person> personList = mapper.find(map);
		System.out.println(personList.size());
		
		PersonMapper mapper2 = session.getMapper(PersonMapper.class);
		
		List<Person> personList2 = mapper2.find(map);
		System.out.println(personList2.size());
	
	}
	
	@Test	//二级缓存
	public void cacheLevel2(){
		SqlSession session = factory.openSession();
		PersonMapper mapper = session.getMapper(PersonMapper.class);
		Map map = new HashMap();
		map.put("name", "'tony'");
		
		List<Person> personList = mapper.find(map);
		System.out.println(personList.size());
		
		session.close();	//关闭session
		session = factory.openSession();
		
		PersonMapper mapper2 = session.getMapper(PersonMapper.class);
		
		List<Person> personList2 = mapper2.find(map);
		System.out.println(personList2.size());
		
	}
	
	@Test	//EhCache
	public void testEhcache(){
		SqlSession session = factory.openSession();
		PersonMapper mapper = session.getMapper(PersonMapper.class);
		Map map = new HashMap();
		map.put("name", "'tony'");
		
		List<Person> personList = mapper.find(map);
		System.out.println(personList.size());
		
		session.close();	//关闭session
		session = factory.openSession();
		
		PersonMapper mapper2 = session.getMapper(PersonMapper.class);
		
		List<Person> personList2 = mapper2.find(map);
		System.out.println(personList2.size());
	}
	
}
