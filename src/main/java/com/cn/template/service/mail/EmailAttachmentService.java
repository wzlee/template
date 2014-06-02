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

import com.cn.template.entity.mail.EmailAttachment;
import com.cn.template.entity.mail.EmailContent;
import com.cn.template.repository.mail.EmailAttachmentDao;
import com.cn.template.xutil.enums.AttachmentType;
import com.cn.template.xutil.persistence.DynamicSpecifications;
import com.cn.template.xutil.persistence.SearchFilter;

/**
 * 邮件附件管理的业务逻辑.
 * @author Libra
 *
 */
@Component  // Spring Bean的标识.
@Transactional // 类中所有public函数都纳入事务管理的标识.
public class EmailAttachmentService {
	
	/** 邮件附件管理的数据访问接口 */
	private EmailAttachmentDao emailAttachmentDao;
	
	@Autowired
	public void setEmailAttachmentDao(EmailAttachmentDao emailAttachmentDao) {
		this.emailAttachmentDao = emailAttachmentDao;
	}

	/**
	 * 根据ID获得邮件附件记录.
	 * @param id
	 * @return
	 */
	public EmailAttachment getEmailAttachment(Long id) {
		return emailAttachmentDao.findOne(id);
	}

	/**
	 * 保存邮件附件信息.
	 * @param entity
	 */
	public void saveEmailAttachment(EmailAttachment entity) {
		emailAttachmentDao.save(entity);
	}

	/**
	 * 单个删除邮件附件记录.
	 * @param id
	 */
	public void deleteEmailAttachment(Long id) {
		emailAttachmentDao.delete(id);
	}

	/**
	 * 获得所有的邮件附件记录.
	 * @return
	 */
	public List<EmailAttachment> getAllEmailAttachment() {
		return (List<EmailAttachment>) emailAttachmentDao.findAll();
	}
	
	/**
	 * 取得邮件对应类型的附件信息.
	 * @param emailContent
	 * @param attachmentType
	 * @return
	 */
	public List<EmailAttachment> getEmailAttachment(EmailContent emailContent,AttachmentType attachmentType){
		return emailAttachmentDao.findByEmailContentAndAttachmentType(emailContent, attachmentType);
	}
	

	/**
	 * 获取邮件附件记录[查询、分页、排序].
	 * @param searchParams
	 * @param pageNumber
	 * @param pageSize
	 * @param sortType
	 * @return
	 */
	public Page<EmailAttachment> getEmailAttachment(Map<String, Object> searchParams, int pageNumber, int pageSize,
			String sortType) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<EmailAttachment> spec = buildSpecification(searchParams);

		return emailAttachmentDao.findAll(spec, pageRequest);
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
		} else if ("fileName".equals(sortType)) {
			sort = new Sort(Direction.ASC, "fileName");
		}
		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}

	/**
	 * 创建动态查询条件组合.
	 * @param searchParams
	 * @return
	 */
	private Specification<EmailAttachment> buildSpecification(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<EmailAttachment> spec = DynamicSpecifications.bySearchFilter(filters.values(), EmailAttachment.class);
		return spec;
	}
	
}
