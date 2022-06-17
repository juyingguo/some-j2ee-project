package cn.itcast.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Author:		传智播客 java学院	陈子枢
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014年11月15日
 */
public class Person implements Serializable {
	private PersonInfo personInfo;	//对一关联
	private List<Book> books;		//对多关联
	
	private Integer id;
	private String name;
	private Integer age;
	private String remark;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public PersonInfo getPersonInfo() {
		return personInfo;
	}
	public void setPersonInfo(PersonInfo personInfo) {
		this.personInfo = personInfo;
	}
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}


}
