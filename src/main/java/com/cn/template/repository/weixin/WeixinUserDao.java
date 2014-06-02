package com.cn.template.repository.weixin;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cn.template.entity.weixin.WeixinUser;

/**
 * 微信订阅用户的数据访问接口.
 * @author Libra
 *
 */
public interface WeixinUserDao extends PagingAndSortingRepository<WeixinUser, Long>, JpaSpecificationExecutor<WeixinUser> {
	
	/**
	 * 获取指定标识下的微信订阅用户.
	 * @param openid
	 * @return
	 */
	public WeixinUser findByOpenid(String openid);
}
