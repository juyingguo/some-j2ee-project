package cn.itcast.domain;

/**
 * @Description:
 * @Author:		传智播客 java学院	陈子枢
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014年11月15日
 */
public class Book {
	private Integer id;
	private String name;
	private Double money;
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
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	
}
