package com.cn.template.service.project;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cn.template.entity.Project;
import com.cn.template.repository.ProjectDao;
import com.cn.template.xutil.enums.Operator;
import com.cn.template.xutil.persistence.DynamicSpecifications;
import com.cn.template.xutil.persistence.SearchFilter;

/**
 * 项目管理的业务逻辑.
 * @author Libra
 *
 */
@Component  // Spring Bean的标识.
@Transactional // 类中所有public函数都纳入事务管理的标识.
public class ProjectService {
	
	/** 项目管理的数据访问接口 */
	private ProjectDao projectDao;
	
	@Autowired
	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	/**
	 * 根据ID获得项目记录.
	 * @param id
	 * @return
	 */
	public Project getProject(Long id) {
		return projectDao.findOne(id);
	}

	/**
	 * 保存项目信息.
	 * @param entity
	 */
	public void saveProject(Project entity) {
		projectDao.save(entity);
	}

	/**
	 * 单个删除项目记录.
	 * @param id
	 */
	public void deleteProject(Long id) {
		projectDao.delete(id);
	}

	/**
	 * 获得所有的项目记录.
	 * @return
	 */
	public List<Project> getAllProject() {
		return (List<Project>) projectDao.findAll();
	}

	/**
	 * 获取项目记录[查询、分页、排序].
	 * @param userId
	 * @param searchParams
	 * @param pageNumber
	 * @param pageSize
	 * @param sortType
	 * @return
	 */
	public Page<Project> getUserProject(Long userId, Map<String, Object> searchParams, int pageNumber, int pageSize,
			String sortType) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<Project> spec = buildSpecification(userId, searchParams);
		return projectDao.findAll(spec, pageRequest);
	}

	/**
	 * 创建分页\排序请求.
	 * @param pageNumber
	 * @param pagzSize
	 * @param sortType
	 * @return
	 */
	private PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {
		Sort sort = null;
		if ("auto".equals(sortType)) {
			sort = new Sort(Direction.ASC, "createTime");
		} else if ("name".equals(sortType)) {
			sort = new Sort(Direction.ASC, "name");
		}
		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}

	/**
	 * 创建动态查询条件组合.
	 * @param userId
	 * @param searchParams
	 * @return
	 */
	private Specification<Project> buildSpecification(Long userId, Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		filters.put("director", new SearchFilter("director", Operator.EQ, userId));
		Specification<Project> spec = DynamicSpecifications.bySearchFilter(filters.values(), Project.class);
		return spec;
	}
	
}
