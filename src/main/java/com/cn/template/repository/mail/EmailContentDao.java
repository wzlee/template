package com.cn.template.repository.mail;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cn.template.entity.mail.EmailContent;

/**
 * 邮件内容处理的数据访问接口.
 * @author Libra
 *
 */
public interface EmailContentDao extends PagingAndSortingRepository<EmailContent, Long>, JpaSpecificationExecutor<EmailContent>{

}
