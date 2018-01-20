package com.hd.entity;
import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.Id;


/**
 * UserRole 实体类
 * @author <a href="mailto:hzhh123@sina.cn">hzhh123</a>
 * @date 2018-01-20 05:59
 *
 * @version 1.0
 *
 */
@Entity
@Table(name = "user_role")
public class UserRole {
	@Id
	private Integer id;
	private String name;

	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id=id;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name=name;
	}
}
