package com.cn.template.entity.weixin;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.cn.template.entity.IdEntity;
import com.cn.template.xutil.enums.Whether;

/**
 * 微信菜单信息(数据表).
 * @author Libra
 *
 */
@Entity
@Table(name="tb_weixin_menu")
public class WeixinMenu extends IdEntity{
	
	/** 菜单名称 */
	private String name;
	
	/** 描述 */
	private String description;
	
	/** 是否当前在使用 */
	private Whether isUsing;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Enumerated(EnumType.ORDINAL)
	public Whether getIsUsing() {
		return isUsing;
	}

	public void setIsUsing(Whether isUsing) {
		this.isUsing = isUsing;
	}
	
	
}
