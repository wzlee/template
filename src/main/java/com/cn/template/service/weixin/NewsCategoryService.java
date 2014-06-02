package com.cn.template.service.weixin;

import java.util.HashMap;
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

import com.cn.template.entity.weixin.NewsCategory;
import com.cn.template.repository.weixin.NewsCategoryDao;
import com.cn.template.xutil.enums.MessageCategory;
import com.cn.template.xutil.enums.Operator;
import com.cn.template.xutil.persistence.DynamicSpecifications;
import com.cn.template.xutil.persistence.SearchFilter;

/**
 * 图文消息类别管理的业务逻辑.
 * @author Libra
 *
 */
@Component  // Spring Bean的标识.
@Transactional // 类中所有public函数都纳入事务管理的标识.
public class NewsCategoryService {
	
	/** 图文消息类别管理的数据访问接口 */
	private NewsCategoryDao newsCategoryDao;
	
	@Autowired
	public void setNewsCategoryDao(NewsCategoryDao newsCategoryDao) {
		this.newsCategoryDao = newsCategoryDao;
	}

	/**
	 * 根据ID获得图文消息类别记录.
	 * @param id
	 * @return
	 */
	public NewsCategory getNewsCategory(Long id) {
		return newsCategoryDao.findOne(id);
	}

	/**
	 * 保存图文消息类别信息.
	 * @param entity
	 */
	public void saveNewsCategory(NewsCategory entity) {
		newsCategoryDao.save(entity);
	}

	/**
	 * 单个删除图文消息类别记录.
	 * @param id
	 */
	public void deleteNewsCategory(Long id) {
		newsCategoryDao.delete(id);
	}

	/**
	 * 获得所有的图文消息类别记录.
	 * @return
	 */
	public List<NewsCategory> getAllNewsCategory() {
		return (List<NewsCategory>) newsCategoryDao.findAll();
	}
	
	/**
	 * 获取类型下的图文消息类别
	 * @param messageCategory
	 * @return
	 */
	public List<NewsCategory> getNewsCategory(MessageCategory messageCategory){
		Sort sort = new Sort(Direction.DESC, "sort");
		Specification<NewsCategory> spec =buildSpecification(messageCategory, new HashMap<String, Object>());
		return newsCategoryDao.findAll(spec, sort);
	}
	

	/**
	 * 获取图文消息类别记录[查询、分页、排序].
	 * @param userId
	 * @param searchParams
	 * @param pageNumber
	 * @param pageSize
	 * @param sortType
	 * @return
	 */
	public Page<NewsCategory> getNewsCategory(MessageCategory messageCategory, Map<String, Object> searchParams, int pageNumber, int pageSize,
			String sortType) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<NewsCategory> spec = buildSpecification(messageCategory, searchParams);

		return newsCategoryDao.findAll(spec, pageRequest);
	}

	/**
	 * 创建分页请求.
	 * @param pageNumber
	 * @param pagzSize
	 * @param sortType
	 * @return
	 */
	private PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {
		Sort sort = null;
		if ("auto".equals(sortType)) {
			sort = new Sort(Direction.DESC, "sort");
		} else if ("title".equals(sortType)) {
			sort = new Sort(Direction.ASC, "title");
		}
		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}

	/**
	 * 创建动态查询条件组合.
	 * @param userId
	 * @param searchParams
	 * @return
	 */
	private Specification<NewsCategory> buildSpecification(MessageCategory messageCategory, Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		filters.put("messageCategory", new SearchFilter("messageCategory", Operator.EQ, messageCategory));
		Specification<NewsCategory> spec = DynamicSpecifications.bySearchFilter(filters.values(), NewsCategory.class);
		return spec;
	}
	
}
