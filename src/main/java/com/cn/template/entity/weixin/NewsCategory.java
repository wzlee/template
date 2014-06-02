package com.cn.template.entity.weixin;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.cn.template.entity.IdEntity;
import com.cn.template.xutil.enums.MessageCategory;

/** 
 * 图文消息类别.
 * @author Libra
 *
 */
@Entity
@Table(name="tb_news_category")
public class NewsCategory extends IdEntity  {
	
	/** 标题 */
	private String title;
	
	/** 代码 */
	private String code;
	
	/** 排序 */
	private Integer sort;
	
	/** 消息分类 */
	private MessageCategory messageCategory;
	
	/** 描述 */
	private String description;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Enumerated(EnumType.ORDINAL)
	public MessageCategory getMessageCategory() {
		return messageCategory;
	}

	public void setMessageCategory(MessageCategory messageCategory) {
		this.messageCategory = messageCategory;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
}
