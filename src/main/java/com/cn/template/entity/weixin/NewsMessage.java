package com.cn.template.entity.weixin;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cn.template.entity.IdEntity;

/**
 * 图文消息
 * @author Libra
 *
 */
@Entity
@Table(name="tb_news_message")
public class NewsMessage extends IdEntity {
	
	/** 标题 */
	private String title;
	
	/** 描述 */
	private String description;
	
	/** 点击后跳转的链接 */
	private String url;
	
	/** 图文消息的图片链接，支持JPG、PNG格式，大图640*320 ，作为首条或单条图文信息时使用 */
	private String bigPicurl;
	
	/** 图文消息的图片链接，支持JPG、PNG格式，小图80*80 作为其他图文信息时使用 */
	private String smallPicurl;
	
	/** 文章内容 */
	private String content;
	
	/** 图文消息类别 */
	private NewsCategory newsCategory;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getBigPicurl() {
		return bigPicurl;
	}

	public void setBigPicurl(String bigPicurl) {
		this.bigPicurl = bigPicurl;
	}

	public String getSmallPicurl() {
		return smallPicurl;
	}

	public void setSmallPicurl(String smallPicurl) {
		this.smallPicurl = smallPicurl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@ManyToOne
	@JoinColumn(name="news_category_id")
	public NewsCategory getNewsCategory() {
		return newsCategory;
	}

	public void setNewsCategory(NewsCategory newsCategory) {
		this.newsCategory = newsCategory;
	}
	
}
