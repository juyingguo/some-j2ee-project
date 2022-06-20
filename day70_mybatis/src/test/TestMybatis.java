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
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import cn.itcast.domain.Person;

/**
 * @Description:
 * @Author:		传智播客 java学院	陈子枢
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014年11月15日
 */
public class TestMybatis {
	private SqlSessionFactory factory;
	
	@Before	//最先执行，初始化SessionFactory
	public void init() throws IOException{
		String resource = "sqlMapConfig.xml";
		InputStream is = Resources.getResourceAsStream(resource);
		factory = new SqlSessionFactoryBuilder().build(is);
	}
	
	@Test	//查询所有
	public void testFindAll() throws IOException{
		SqlSession session = factory.openSession();
		
		//如何访问mapper中的方法呢？  规则：命名空间+.+id
		List<Person> personList = session.selectList("cn.itcast.mapper.PersonMapper.find");
		System.out.println(personList.size());
		for(Person p : personList){
			System.out.println(p);
		}
	}
	
	@Test	//带条件查询
	public void testFind() throws IOException{
		SqlSession session = factory.openSession();
		
		//如何访问mapper中的方法呢？  规则：命名空间+.+id
		Map<String,Object> paraMap = new HashMap<String,Object>();		//参数
		//paraMap.put("name", "t%");
		//年龄查询大于等于18小于等于40
		paraMap.put("ageStart", 10);
		paraMap.put("ageEnd", 20);
		
		List<Person> personList = session.selectList("cn.itcast.mapper.PersonMapper.find", paraMap);
		System.out.println(personList.size());
		for(Person p : personList){
			System.out.println(p);
		}
	}
	
	@Test	//查询一条
	public void testGet(){
		SqlSession session = factory.openSession();
		Person p = session.selectOne("cn.itcast.mapper.PersonMapper.get", 1);
		System.out.println(p);
	}
	
	@Test	//新增
	public void testInsert(){
		SqlSession session = factory.openSession();
		Person p = new Person();
//		p.setId(4);
		p.setName("hellen");
		p.setAge(16);
		p.setRemark("xijiang");
		
		session.insert("cn.itcast.mapper.PersonMapper.insert", p);
		session.commit();			//提交事务
		System.out.println("after insert person id=:" + p.getId());
		System.out.println("insert finished.");
	}
	
	@Test	//修改
	public void testUpdate(){
		SqlSession session = factory.openSession();
		Person p = new Person();
		p.setId(4);
		//p.setName("hellen2");
		p.setAge(18);
		p.setRemark("beijing");
		
		session.update("cn.itcast.mapper.PersonMapper.update", p);
		session.commit();
	}
	
	@Test	//删除一条
	public void testDeleteById(){
		SqlSession session = factory.openSession();
		
		int delete = session.delete("cn.itcast.mapper.PersonMapper.deleteById", 4);
		session.commit();
		System.out.println("delete:" + delete);
	}
	
	@Test	//删除多条-数组
	public void testDeleteByArray(){
		SqlSession session = factory.openSession();
		int[] ids = {4,6};
		session.delete("cn.itcast.mapper.PersonMapper.deleteArray", ids);
		session.commit();
	}
	
	@Test	//删除多条-LIST
	public void testDeleteByList(){
		SqlSession session = factory.openSession();
		List<Integer> _list = new ArrayList<Integer>();
		_list.add(4);
		_list.add(6);
		
		session.delete("cn.itcast.mapper.PersonMapper.deleteList", _list);
		session.commit();
	}
	
	@Test	//删除多条 MAP
	public void testDeleteByMap(){
		SqlSession session = factory.openSession();
		Map<String,Object> paraMap = new HashMap<String,Object>();
		int[] ids = {4,6};
		paraMap.put("ids", ids);
		
		session.delete("cn.itcast.mapper.PersonMapper.deleteMap", paraMap);
		session.commit();
	}
	
	@Test	//获取记录总数
	public void testCount(){
		SqlSession session = factory.openSession();
		int i = session.selectOne("cn.itcast.mapper.PersonMapper.count");
		System.out.println(i);
	}

	@Test
	//获取多条-数组
	public void selectListByArray(){
		SqlSession session = factory.openSession();
		int[] ids = {5,6,7};
		List<Person> objects = session.selectList("cn.itcast.mapper.PersonMapper.selectListByArray", ids);
		System.out.println(objects);
		session.commit();
	}
	@Test
	//获取多条-数组,String
	public void selectListByArrayString(){
		SqlSession session = factory.openSession();
		String[] ids = {"jin","pim"};
		List<Person> objects = session.selectList("cn.itcast.mapper.PersonMapper.selectListByArrayString", ids);
		System.out.println(objects);
		session.commit();
	}
	//获取多条-List
	@Test
	public void selectListByList(){
		SqlSession session = factory.openSession();
		List<Integer> _list = new ArrayList<Integer>();
		_list.add(5);
		_list.add(6);
		List<Person> objects = session.selectList("cn.itcast.mapper.PersonMapper.selectListByList", _list);
		System.out.println(objects);
		session.commit();
	}
	/**
	 * 获取多条 MAP
	 */
	@Test
	public void selectListByMap(){
		SqlSession session = factory.openSession();
		Map<String,Object> paraMap = new HashMap<String,Object>();
		int[] ids = {5,6};
		paraMap.put("ids", ids);
		List<Person> objects = session.selectList("cn.itcast.mapper.PersonMapper.selectListByMap", paraMap);
		System.out.println(objects);
		session.commit();
	}
	/**
	 * 获取多条 MAP
	 */
	@Test
	public void selectListByMapList(){
		SqlSession session = factory.openSession();
		Map<String,Object> paraMap = new HashMap<String,Object>();
		List<Integer> _list = new ArrayList<Integer>();
		_list.add(5);
		_list.add(6);
		paraMap.put("_list", _list);
		List<Person> objects = session.selectList("cn.itcast.mapper.PersonMapper.selectListByMapList", paraMap);
		System.out.println(objects);
		session.commit();
	}
	/**
	 * 更新多条 MAP
	 */
	@Test
	public void updateListByMapListCase(){
		SqlSession session = factory.openSession();
		Map<String,Object> paraMap = new HashMap<String,Object>();
		List<Integer> _list = new ArrayList<Integer>();
		_list.add(5);
		_list.add(6);
		paraMap.put("_list", _list);
		int update = session.update("cn.itcast.mapper.PersonMapper.updateListByMapListCase", paraMap);
		System.out.println(update);
		session.commit();
	}
	/**
	 * 更新多条 MAP
	 */
	@Test
	public void updateListByMapListCase2(){
		SqlSession session = factory.openSession();
		Map<String,Object> paraMap = new HashMap<String,Object>();
		List<Integer> _list = new ArrayList<Integer>();
		_list.add(5);
		_list.add(6);
		paraMap.put("_list", _list);
		int update = session.update("cn.itcast.mapper.PersonMapper.updateListByMapListCase2", paraMap);
		System.out.println(update);
		session.commit();
	}
}
