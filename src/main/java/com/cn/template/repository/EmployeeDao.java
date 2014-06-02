package com.cn.template.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cn.template.entity.Employee;

/**
 * 用户信息的数据访问接口.
 * @author Libra
 *
 */
public interface EmployeeDao extends PagingAndSortingRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {
	
	/**
	 * 获得标识下的员工信息.
	 * @param openid
	 * @return
	 */
	public Employee findByOpenid(String openid);
	
	/**
	 * 根据工号获得员工信息.
	 * @param openid
	 * @return
	 */
	public Employee findByCode(String code);
	
}
