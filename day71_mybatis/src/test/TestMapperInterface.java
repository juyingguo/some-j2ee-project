package test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
public class TestMapperInterface {
	private SqlSessionFactory factory;
	
	@Before	//最先执行，初始化SessionFactory
	public void init() throws IOException{
		String resource = "sqlMapConfig.xml";
		InputStream is = Resources.getResourceAsStream(resource);
		factory = new SqlSessionFactoryBuilder().build(is);
	}
	
	@Test
	public void testFind(){
		SqlSession session = factory.openSession();
		//获取接口对象
		PersonMapper mapper = session.getMapper(PersonMapper.class);
		Map map = new HashMap();
		map.put("name", "'t%'");
		
		List<Person> pList = mapper.find(map);
		System.out.println(pList.size());
	}
	
	@Test
	public void testGet(){
		SqlSession session = factory.openSession();
		PersonMapper mapper = session.getMapper(PersonMapper.class);
		Person p = mapper.get(1);
		System.out.println(p.getName());
		
	}
	
	@Test
	public void testInsert(){
		SqlSession session = factory.openSession();
		PersonMapper mapper = session.getMapper(PersonMapper.class);
		
		Person p = new Person();
		p.setId(4);
		p.setName("tom");
		p.setAge(8);
		
		mapper.insert(p);
		session.commit();
	}
	
	@Test
	public void testUpdate(){
		SqlSession session = factory.openSession();
		PersonMapper mapper = session.getMapper(PersonMapper.class);
		
		Person p = mapper.get(4);
		p.setAge(9);
		
		mapper.update(p);
		session.commit();
	}
	
	@Test
	public void testDeleteById(){
		SqlSession session = factory.openSession();
		PersonMapper mapper = session.getMapper(PersonMapper.class);
		
		mapper.deleteById(4);
		session.commit();
	}
	
	@Test 
	public void testDeleteList(){
		SqlSession session = factory.openSession();
		PersonMapper mapper = session.getMapper(PersonMapper.class);
		
		List<Integer> list = new ArrayList();
		list.add(5);
		list.add(6);
		
		mapper.deleteList(list);
		session.commit();
	}
	
	@Test 
	public void testDeleteMap(){
		SqlSession session = factory.openSession();
		PersonMapper mapper = session.getMapper(PersonMapper.class);
		
		Map map = new HashMap();
		map.put("ids", new Integer[]{4,5,6,7});
		
		mapper.deleteMap(map);
		session.commit();
	}
	
	@Test
	public void testCount(){
		SqlSession session = factory.openSession();
		PersonMapper mapper = session.getMapper(PersonMapper.class);
		
		int i = mapper.count();
		System.out.println(i);
	}
}
