package cn.itcast.mapper;

import java.util.List;
import java.util.Map;

import cn.itcast.domain.Person;

/**
 * @Description:
 * @Author:		传智播客 java学院	陈子枢
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014年11月17日
 */
public interface PersonMapper {
	public List<Person> find(Map map);
	public Person get(Integer id);
	public void insert(Person person);
	public void update(Person person);
	public void deleteById(Integer id);
	
	public void deleteList(List list);
	public void deleteMap(Map map);
	public Integer count();
}
