package com.hd.entity;
import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.Id;


/**
 * Resource 实体类
 * @author <a href="mailto:hzhh123@sina.cn">hzhh123</a>
 * @date 2018-01-20 05:59
 *
 * @version 1.0
 *
 */
@Entity
@Table(name = "resource")
public class Resource {
	private String id;
	@Id
	private String status;

	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id=id;
	}
	public String getStatus(){
		return status;
	}
	public void setStatus(String status){
		this.status=status;
	}
}
