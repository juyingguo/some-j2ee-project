package cn.itcast.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author:		传智播客 java学院	陈子枢
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014年11月15日
 */
public class PersonInfo implements Serializable {
	private Integer id;
	private String station;
	private Date joinDate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	
	
}
