package com.cn.template.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 部门信息.
 * @author Libra
 *
 */
@Entity
@Table(name="tb_department")
public class Department extends IdEntity {
	
	/** 部门名称 */
	private String name;
	
	/** 描述 */
	private String description;

	/** 上级部门 */
	private Department higherDepartment;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne
	@JoinColumn(name="higher_department_id")
	public Department getHigherDepartment() {
		return higherDepartment;
	}

	public void setHigherDepartment(Department higherDepartment) {
		this.higherDepartment = higherDepartment;
	}
	
}
