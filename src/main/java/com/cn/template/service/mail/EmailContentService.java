package com.cn.template.service.mail;

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

import com.cn.template.entity.mail.EmailContent;
import com.cn.template.repository.mail.EmailContentDao;
import com.cn.template.xutil.enums.Operator;
import com.cn.template.xutil.persistence.DynamicSpecifications;
import com.cn.template.xutil.persistence.SearchFilter;

/**
 * 邮件内容管理的业务逻辑.
 * @author Libra
 *
 */
@Component  // Spring Bean的标识.
@Transactional // 类中所有public函数都纳入事务管理的标识.
public class EmailContentService {
	
	/** 邮件内容管理的数据访问接口 */
	private EmailContentDao emailContentDao;
	
	@Autowired
	public void setEmailContentDao(EmailContentDao emailContentDao) {
		this.emailContentDao = emailContentDao;
	}

	/**
	 * 根据ID获得邮件内容记录.
	 * @param id
	 * @return
	 */
	public EmailContent getEmailContent(Long id) {
		return emailContentDao.findOne(id);
	}

	/**
	 * 保存邮件内容信息.
	 * @param entity
	 */
	public void saveEmailContent(EmailContent entity) {
		emailContentDao.save(entity);
	}

	/**
	 * 单个删除邮件内容记录.
	 * @param id
	 */
	public void deleteEmailContent(Long id) {
		emailContentDao.delete(id);
	}

	/**
	 * 获得所有的邮件内容记录.
	 * @return
	 */
	public List<EmailContent> getAllEmailContent() {
		return (List<EmailContent>) emailContentDao.findAll();
	}

	/**
	 * 获取邮件内容记录[查询、分页、排序].
	 * @param openid
	 * @param searchParams
	 * @param pageNumber
	 * @param pageSize
	 * @param sortType
	 * @return
	 */
	public Page<EmailContent> getUserEmailContent(Long openid, Map<String, Object> searchParams, int pageNumber, int pageSize,
			String sortType) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<EmailContent> spec = buildSpecification(openid, searchParams);

		return emailContentDao.findAll(spec, pageRequest);
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
			sort = new Sort(Direction.DESC, "sentDate");
		} else if ("subject".equals(sortType)) {
			sort = new Sort(Direction.ASC, "subject");
		}
		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}

	/**
	 * 创建动态查询条件组合.
	 * @param openid
	 * @param searchParams
	 * @return
	 */
	private Specification<EmailContent> buildSpecification(Long openid, Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		filters.put("openid", new SearchFilter("openid", Operator.EQ, openid));
		Specification<EmailContent> spec = DynamicSpecifications.bySearchFilter(filters.values(), EmailContent.class);
		return spec;
	}
	
}
