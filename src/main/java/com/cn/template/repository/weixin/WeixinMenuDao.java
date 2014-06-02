package com.cn.template.repository.weixin;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cn.template.entity.weixin.WeixinMenu;

/**
 * 微信菜单的数据访问接口.
 * @author Libra
 *
 */
public interface WeixinMenuDao extends PagingAndSortingRepository<WeixinMenu, Long>, JpaSpecificationExecutor<WeixinMenu> {
	
}
