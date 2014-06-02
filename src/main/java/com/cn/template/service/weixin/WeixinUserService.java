package com.cn.template.service.weixin;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cn.template.entity.weixin.WeixinUser;
import com.cn.template.entity.weixin.WeixinUser;
import com.cn.template.repository.weixin.WeixinUserDao;
import com.cn.template.xutil.persistence.DynamicSpecifications;
import com.cn.template.xutil.persistence.SearchFilter;

/**
 * 微信订阅用户的业务逻辑.
 * @author Libra
 *
 */
@Component
@Transactional
public class WeixinUserService {
	
	/** 微信订阅用户的数据访问接口 */
	private WeixinUserDao weixinUserDao;

	@Autowired
	public void setWeixinUserDao(WeixinUserDao weixinUserDao) {
		this.weixinUserDao = weixinUserDao;
	}
	
	/**
	 * 根据公众号标识获得唯一的用户信息.
	 * @param openid
	 * @return
	 */
	public WeixinUser getWeixinUser(String openid){
		return weixinUserDao.findByOpenid(openid);
	}
	
	
	/**
	 * 保存微信订阅用户的信息.
	 * @param entity
	 */
	public void saveWeixinUser(WeixinUser entity) {
		weixinUserDao.save(entity);
	}
	
	
	/**
	 * 获取微信订阅者记录[查询、分页、排序].
	 * @param searchParams
	 * @param pageNumber
	 * @param pageSize
	 * @param sortType
	 * @return
	 */
	public Page<WeixinUser> getWeixinUser(Map<String, Object> searchParams, int pageNumber, int pageSize,
			String sortType) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<WeixinUser> spec = buildSpecification(searchParams);

		return weixinUserDao.findAll(spec, pageRequest);
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
			sort = new Sort(Direction.ASC, "nickname");
		}
		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}

	/**
	 * 创建动态查询条件组合.
	 * @param searchParams
	 * @return
	 */
	private Specification<WeixinUser> buildSpecification(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<WeixinUser> spec = DynamicSpecifications.bySearchFilter(filters.values(), WeixinUser.class);
		return spec;
	}
}
