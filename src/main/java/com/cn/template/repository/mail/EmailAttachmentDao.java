package com.cn.template.repository.mail;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cn.template.entity.mail.EmailAttachment;
import com.cn.template.entity.mail.EmailContent;
import com.cn.template.xutil.enums.AttachmentType;

/**
 * 邮件附件的数据访问接口.
 * @author Libra
 *
 */
public interface EmailAttachmentDao extends PagingAndSortingRepository<EmailAttachment, Long>, JpaSpecificationExecutor<EmailAttachment>{

	/**
	 * 取得邮件对应类型的附件信息.
	 * @param emailContent
	 * @param attachmentType
	 * @return
	 */
	public List<EmailAttachment> findByEmailContentAndAttachmentType(EmailContent emailContent,AttachmentType attachmentType);
}
