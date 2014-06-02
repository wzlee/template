package com.cn.template.repository.weixin;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cn.template.entity.weixin.WeixinAuthLog;

/**
 * 微信与工号挂靠认证的记录.
 * @author Libra
 *
 */
public interface WeixinAuthLogDao extends PagingAndSortingRepository<WeixinAuthLog, Long>, JpaSpecificationExecutor<WeixinAuthLog>{

	/**
	 * 取得指定的认证记录.
	 * @param Ticket
	 * @param SceneId
	 * @param Openid
	 * @return
	 */
	public WeixinAuthLog findByTicketAndSceneIdAndOpenidOrderByCreateTimeDesc(String Ticket,Long SceneId,String Openid);
}
