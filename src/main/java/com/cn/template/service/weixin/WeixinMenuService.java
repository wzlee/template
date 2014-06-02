package com.cn.template.service.weixin;

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

import com.cn.template.entity.weixin.WeixinMenu;
import com.cn.template.repository.weixin.WeixinMenuDao;
import com.cn.template.xutil.persistence.DynamicSpecifications;
import com.cn.template.xutil.persistence.SearchFilter;

/**
 * 微信菜单管理的业务逻辑.
 * @author Libra
 *
 */
@Component
@Transactional
public class WeixinMenuService {
	
	/** 微信菜单管理的数据访问接口 */
	private WeixinMenuDao weixinMenuDao;
	
	@Autowired
	public void setWeixinMenuDao(WeixinMenuDao weixinMenuDao) {
		this.weixinMenuDao = weixinMenuDao;
	}

	/**
	 * 根据ID获得微信菜单记录.
	 * @param id
	 * @return
	 */
	public WeixinMenu getWeixinMenu(Long id) {
		return weixinMenuDao.findOne(id);
	}

	/**
	 * 保存微信菜单信息.
	 * @param entity
	 */
	public void saveWeixinMenu(WeixinMenu entity) {
		weixinMenuDao.save(entity);
	}

	/**
	 * 单个删除微信菜单记录.
	 * @param id
	 */
	public void deleteWeixinMenu(Long id) {
		weixinMenuDao.delete(id);
	}

	/**
	 * 获得所有的微信菜单记录.
	 * @return
	 */
	public List<WeixinMenu> getAllWeixinMenu() {
		return (List<WeixinMenu>) weixinMenuDao.findAll();
	}

	/**
	 * 获取微信菜单记录[查询、分页、排序].
	 * @param searchParams
	 * @param pageNumber
	 * @param pageSize
	 * @param sortType
	 * @return
	 */
	public Page<WeixinMenu> getWeixinMenu(Map<String, Object> searchParams, int pageNumber, int pageSize,
			String sortType) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<WeixinMenu> spec = buildSpecification(searchParams);

		return weixinMenuDao.findAll(spec, pageRequest);
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
			sort = new Sort(Direction.DESC, "id");
		} else if ("name".equals(sortType)) {
			sort = new Sort(Direction.ASC, "name");
		}
		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}

	/**
	 * 创建动态查询条件组合.
	 * @param searchParams
	 * @return
	 */
	private Specification<WeixinMenu> buildSpecification(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<WeixinMenu> spec = DynamicSpecifications.bySearchFilter(filters.values(), WeixinMenu.class);
		return spec;
	}
}
