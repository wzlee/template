package com.cn.template.entity.mail;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.cn.template.entity.IdEntity;
import com.cn.template.xutil.enums.Status;
import com.cn.template.xutil.enums.Whether;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 邮件内容信息.
 * @author Libra
 *
 */
@Entity
@Table(name="tb_email_content")
public class EmailContent extends IdEntity {
	
	/** 员工标识号 */
	private String openid;
	
	/** 邮件主题 */
	private String subject;
	
	/** 邮件发送日期 */
	private Date sentDate;
	
	/** 判断此邮件是否需要回执，如果需要回执返回"true",否则返回"false"  */
	private Whether replySign;
	
	/** 邮件是否已读 */
	private Whether hasRead;
	
	/**  判断此邮件是否包含附件  */
	private Whether isContainAttach;
	
	/** 发件人名称 */
	private String fromName;
	
	/** 发件人地址 */
	private String fromAddress;
	
	/** 收件人地址 */
	private String mailAddressTo;
	
	/** 抄送人地址 */
	private String mailAddressCc;
	
	/** 密送人地址 */
	private String mailAddressBcc;
	
	/** 收件时间 */
	private Date receiveDate;
	
	/** 邮件的Message-ID  */
	private String messageId;

	/** 邮件内容Text */
	private String bodyText;
	
	/** 邮件内容HTML */
	private String bodyHtml;
	
	/** 邮件状态 */
	private Status status;
	
	/** 邮件附件信息 */
	private List<EmailAttachment> attachments;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public Date getSentDate() {
		return sentDate;
	}

	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}

	@Enumerated(EnumType.ORDINAL)
	public Whether getReplySign() {
		return replySign;
	}

	public void setReplySign(Whether replySign) {
		this.replySign = replySign;
	}

	@Enumerated(EnumType.ORDINAL)
	public Whether getHasRead() {
		return hasRead;
	}

	public void setHasRead(Whether hasRead) {
		this.hasRead = hasRead;
	}

	@Enumerated(EnumType.ORDINAL)
	public Whether getIsContainAttach() {
		return isContainAttach;
	}

	public void setIsContainAttach(Whether isContainAttach) {
		this.isContainAttach = isContainAttach;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getMailAddressTo() {
		return mailAddressTo;
	}

	public void setMailAddressTo(String mailAddressTo) {
		this.mailAddressTo = mailAddressTo;
	}

	public String getMailAddressCc() {
		return mailAddressCc;
	}

	public void setMailAddressCc(String mailAddressCc) {
		this.mailAddressCc = mailAddressCc;
	}

	public String getMailAddressBcc() {
		return mailAddressBcc;
	}

	public void setMailAddressBcc(String mailAddressBcc) {
		this.mailAddressBcc = mailAddressBcc;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getBodyText() {
		return bodyText;
	}

	public void setBodyText(String bodyText) {
		this.bodyText = bodyText;
	}

	public String getBodyHtml() {
		return bodyHtml;
	}

	public void setBodyHtml(String bodyHtml) {
		this.bodyHtml = bodyHtml;
	}

	@Enumerated(EnumType.ORDINAL)
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="emailContent")
	public List<EmailAttachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<EmailAttachment> attachments) {
		this.attachments = attachments;
	}

}
