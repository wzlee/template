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

import com.cn.template.entity.weixin.WeixinAuthLog;
import com.cn.template.repository.weixin.WeixinAuthLogDao;
import com.cn.template.xutil.persistence.DynamicSpecifications;
import com.cn.template.xutil.persistence.SearchFilter;

/**
 * 微信员工认证管理的业务逻辑.
 * @author Libra
 *
 */
@Component  // Spring Bean的标识.
@Transactional // 类中所有public函数都纳入事务管理的标识.
public class WeixinAuthLogService {
	
	/** 微信员工认证管理的数据访问接口 */
	private WeixinAuthLogDao weixinAuthLogDao;
	
	@Autowired
	public void setWeixinAuthLogDao(WeixinAuthLogDao weixinAuthLogDao) {
		this.weixinAuthLogDao = weixinAuthLogDao;
	}

	/**
	 * 根据ID获得微信员工认证记录.
	 * @param id
	 * @return
	 */
	public WeixinAuthLog getWeixinAuthLog(Long id) {
		return weixinAuthLogDao.findOne(id);
	}

	/**
	 * 保存微信员工认证信息.
	 * @param entity
	 */
	public void saveWeixinAuthLog(WeixinAuthLog entity) {
		weixinAuthLogDao.save(entity);
	}

	/**
	 * 单个删除微信员工认证记录.
	 * @param id
	 */
	public void deleteWeixinAuthLog(Long id) {
		weixinAuthLogDao.delete(id);
	}

	/**
	 * 获得所有的微信员工认证记录.
	 * @return
	 */
	public List<WeixinAuthLog> getAllWeixinAuthLog() {
		return (List<WeixinAuthLog>) weixinAuthLogDao.findAll();
	}
	
	/**
	 * 取得指定的认证记录.
	 * @param Ticket
	 * @param SceneId
	 * @param Openid
	 * @return
	 */
	public WeixinAuthLog getWeixinAuthLog(String Ticket,Long SceneId,String Openid){
		return weixinAuthLogDao.findByTicketAndSceneIdAndOpenidOrderByCreateTimeDesc(Ticket, SceneId, Openid);
	}

	/**
	 * 获取微信员工认证记录[查询、分页、排序].
	 * @param userId
	 * @param searchParams
	 * @param pageNumber
	 * @param pageSize
	 * @param sortType
	 * @return
	 */
	public Page<WeixinAuthLog> getWeixinAuthLog(Map<String, Object> searchParams, int pageNumber, int pageSize,
			String sortType) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<WeixinAuthLog> spec = buildSpecification(searchParams);

		return weixinAuthLogDao.findAll(spec, pageRequest);
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
	 * @param userId
	 * @param searchParams
	 * @return
	 */
	private Specification<WeixinAuthLog> buildSpecification(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<WeixinAuthLog> spec = DynamicSpecifications.bySearchFilter(filters.values(), WeixinAuthLog.class);
		return spec;
	}
	
}
