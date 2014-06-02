package com.cn.template.repository.weixin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cn.template.entity.weixin.NewsCategory;
import com.cn.template.entity.weixin.NewsMessage;

/**
 * 图文消息的数据访问接口.
 * @author Libra
 *
 */
public interface NewsMessageDao extends PagingAndSortingRepository<NewsMessage, Long>, JpaSpecificationExecutor<NewsMessage>{

	/**
	 * 获取类别下的图文消息.
	 * @param newsCategory
	 * @return
	 */
	public List<NewsMessage> findByNewsCategory(NewsCategory newsCategory);
}
